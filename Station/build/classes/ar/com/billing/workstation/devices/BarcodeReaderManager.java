/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.devices;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 *
 * @author Mildo
 */
public class BarcodeReaderManager implements ActionListener{
     private final static Logger LOGGER = Logger.getLogger(BarcodeReaderManager.class.getName());

    /*
     cuando opere sobre las collecciones debo synchronizarlas tambien
      synchronized (c) {
      Iterator i = c.iterator(); // Must be in the synchronized block
      while (i.hasNext())
         foo(i.next());
        }
     */
   
    private BarcodeReaderWorker worker = null;
    

    public BarcodeReaderManager() {
        
        worker = new BarcodeReaderWorker(); 
        worker.execute();
        
        
      //  Timer timer = new Timer(1000, this);
      //  timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        worker = new BarcodeReaderWorker(); 
        worker.execute();
        
    }
    
   
}
