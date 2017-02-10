/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.synch;

import ar.com.billing.workstation.views.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Mildo
 */
public class SynchManager implements ActionListener{
     private final static Logger LOGGER = Logger.getLogger(SynchManager.class.getName());

    /*
     cuando opere sobre las collecciones debo synchronizarlas tambien
      synchronized (c) {
      Iterator i = c.iterator(); // Must be in the synchronized block
      while (i.hasNext())
         foo(i.next());
        }
     */
   
    private SynchWorker worker = null;
    

    public SynchManager(MainWindow mw) {
        
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Creando el SynchWorker"); 
        worker = new SynchWorker(true); 
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "SynchWorker cerado y ejecutandose"); 
        worker.execute();
        
            
        
        Timer timer = new Timer(600000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        worker = new SynchWorker(false); 
        worker.execute();
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "ejecutando SynchWorker tras 10 minutos."); 
    }
    
   
}
