package ar.com.billing.workstation.devices;

import ar.com.billing.workstation.WorkstationConstants;
import ar.com.billing.workstation.controllers.PrepararPedidoController;
//import ar.com.billing.workstation.model.Order;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Mildo
 */
public class BarcodeReaderWorker extends SwingWorker<Integer, String> {

    private PrepararPedidoController prepararPedidoController = null;
    private static final long THRESHOLD = 100;
    private static final int MIN_BARCODE_LENGTH = 16;
    
    private final StringBuffer barcode = new StringBuffer();
    private long lastEventTimeStamp = 0L;


    @Override
    protected Integer doInBackground() throws Exception {
       try {
                
                barcode.delete(0,barcode.length());
                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        String read;
                        Logger logger =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME) ;
      //                  logger.log(Level.INFO, "Detecte un evento en el lector");
                        if (e.getID() != KeyEvent.KEY_RELEASED) {
                            return false;
                        }
        //                logger.log(Level.INFO, "chequeo que los eventos esten distanciados en el tiempo");
      //                  logger.log(Level.INFO, "e.getWhen(): "+e.getWhen()+ " lastEventTimeStamp: "+lastEventTimeStamp+" THRESHOLD:"+THRESHOLD);
                        if (e.getWhen() - lastEventTimeStamp > THRESHOLD) {
                            barcode.delete(0, barcode.length());
                        }
                        lastEventTimeStamp = e.getWhen();
      //                  logger.log(Level.INFO, "tiempo correcto -- chequeo longitud del string leido");

                        if (barcode.length() < WorkstationConstants.BARCODE_LENGTH) {
                            barcode.append(e.getKeyChar());
                        }
                        
                       if (barcode.length() == WorkstationConstants.BARCODE_LENGTH) {
                           read = barcode.toString(); 
                           logger.log(Level.INFO, "string leido:" + read);
                            
                            fireBarcode(read);
                            barcode.delete(0, barcode.length());
                            
                        }
                      

                        return false;
                    }

                });
        } catch (Exception ex) {
        //    ex.printStackTrace();
             Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, ex.getMessage());
        }
        return 1;
    }
    protected void fireBarcode(String barcode) {
       publish(barcode);
    }
//    @Override
//    protected void process(List<String> codes) {
//        OrderWindow window = OrderWindow.getInstance();
//        prepararPedidoController = new PrepararPedidoController();
//        Order order;
//        if (window.isActive() || window.isFocused()) {
//            order = window.getOrder();
//            if(order!=null){
//                for (String code : codes) {
//                    prepararPedidoController.processBarcode(code, order);
//                }
//            }
//        }
//    }

}
