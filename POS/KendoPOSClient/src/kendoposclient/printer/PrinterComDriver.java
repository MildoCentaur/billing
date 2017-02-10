/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kendoposclient.printer;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommDriver;
import kendoposclient.KendoClientConstants;
import kendoposclient.configuration.KendoPOSException;

/**
 *
 * @author Mildo
 */
public class PrinterComDriver {
    private static CommDriver commDriver;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public void load() throws KendoPOSException{
        String os = System.getProperty("os.name").toLowerCase();
        
 
        if (os.indexOf( "win" ) < 0) {
            logger.log(Level.ALL, "La computadora no tiene windows ignoro el driver");
            return;
        }
        
        System.setSecurityManager(null);
        try {
            logger.log(Level.ALL, "Inicio a operar con java.com.");

            File myDir = new File (KendoClientConstants.DLL_DIR);
            logger.log(Level.ALL, "No te olvides que win32com debe estar en: " + myDir.getCanonicalPath());
            File myDll = new File (myDir, "win32com.dll");
            String     myPath;
            myPath = myDll.getCanonicalPath ();
            System.load (myPath);
             commDriver = (CommDriver)Class.forName("com.sun.comm.Win32Driver").newInstance();
             commDriver.initialize();
        } catch (Exception e) { 
             e.printStackTrace();
             logger.log(Level.ALL, "hubo un error");
             logger.log(Level.ALL, e.getMessage());
             throw new KendoPOSException("Error al inicializar el driver de la comandera.");
        }
    }
}
