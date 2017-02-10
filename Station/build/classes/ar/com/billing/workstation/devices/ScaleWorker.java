/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.devices;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Mildo
 */
public class ScaleWorker extends SwingWorker<Double, Double> implements ActionListener{
    JTextComponent weightText;
    Double previous = 0d;
    
    public ScaleWorker(final JTextComponent weightText){
        this.weightText = weightText;
        if(weightText.getText()==null || weightText.getText().isEmpty()){
            weightText.setText("0.00");
        }
        previous=new Double(weightText.getText());
    }
    @Override
    protected Double doInBackground() throws Exception {
        //portList = CommPortIdentifier.getPortIdentifiers();
//        DataOutputStream dataOutputStream;
//        
//        while (portList.hasMoreElements()) {
//            logger.log("itero por el portList");
//            portId = (CommPortIdentifier) portList.nextElement();
//            logger.log("chequeo que el tipo de puerto sea serial");
//            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//                 if (portId.getName().equals(port)) {
//                     logger.log("dentro del puerto correcto");
//                     logger.log("abri el puerto");
//                     serialPort = (SerialPort) portId.open(app, 2000);
//                    outputStream = serialPort.getOutputStream();
//                    logger.log("tengo el outputstrem " + outputStream.toString());
//                    
//                    dataOutputStream = new DataOutputStream(outputStream);
//                    
//                    /*serialPort.setSerialPortParams(9600,
//                            SerialPort.DATABITS_8,
//                            SerialPort.STOPBITS_1,
//                            SerialPort.PARITY_NONE); */
//                    
//                    serialPort.close();
//                }
//            }
        Timer timer = new Timer(1000, this);
        while(true){
            
            timer.start();
            
        }

            
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        publish(20.32);
    }
    
    @Override
    protected void process(List<Double> chunks) {
        try{
            previous=new Double(weightText.getText());
        }catch(Exception e){
            previous=-1d;
        }
        if(chunks.get(0)!=previous || previous==-1d){
            weightText.setText(String.valueOf(chunks.get(0)));
            previous=chunks.get(0);
        }
        
    }
    
    
}
