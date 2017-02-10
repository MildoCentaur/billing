/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kendoposclient;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommDriver;
import kendoposclient.communication.Server;
import kendoposclient.configuration.Configurator;
import kendoposclient.configuration.KendoLogger;
import kendoposclient.printer.POSPrinter;
import kendoposclient.printer.PrinterComDriver;

/**
 *
 * @author Adriabe
 */
public class KendoPOSClient {
	
    /**
     * @param args the command line arguments
     */
    private static CommDriver commDriver;
    
    public static void main(String[] args) {
        
        
        try{
            Configurator config = Configurator.getInstance();
            KendoLogger.setup();
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "Arranca la app.");

            PrinterComDriver driver = new PrinterComDriver();
            driver.load();
            
            
            
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "inicio del log");
            Boolean hasPrinter = Boolean.parseBoolean( (String)config.findProperty(KendoClientConstants.HAS_PRINTER));
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "chequeo si tiene impresora");
            if(hasPrinter){
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "Se encontro una impresora");
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "Inicio servidor");
                Server server = new Server();
                POSPrinter printer = new POSPrinter();
                if (KendoClientConstants.DEBUG || printer.checkPortAvailable(config)){
                    //browserHandler.openBrowserErrorPrinter();           
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "servidor creado, espero conexiones");
                    server.waitForConnections();
                }
                else{
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "No se encontro la impresora.");
                }    
            }else{
               Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "El equipo no posee impresora");
            }    
            
        }catch(Exception e){
            e.printStackTrace();
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "Hubo un error.");
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, e.getMessage());
    
        }
    } 

}
