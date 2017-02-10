/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation;

import ar.com.billing.workstation.devices.ScaleWorker;
import ar.com.billing.workstation.synch.SynchManager;
import ar.com.billing.workstation.utilities.Configurator;
import ar.com.billing.workstation.utilities.KendoLogger;
import ar.com.billing.workstation.views.MainWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommDriver;

/**
 *
 * @author Mildo
 */
public class WorkStation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    
                    KendoLogger.setup();
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Sistema iniciado");
                    Configurator config = Configurator.getInstance();
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Configuraciones cargadas "); 
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Iniciando MainWindow"); 
                    MainWindow mw = MainWindow.getInstance();
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Iniciando Synch Manager"); 
                    SynchManager sm = new SynchManager(mw);
                    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Synch Manager Iniciado"); 
                    //ScaleWorker scale = new ScaleWorker(mw.getWeightTextBox());
                    //scale.execute();
                    mw.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(WorkStation.class.getName()).log(Level.SEVERE, ex.getMessage());
                }
                
            }
        });
    }
}
