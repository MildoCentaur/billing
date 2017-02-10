/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.printer;

import ar.com.billing.workstation.WorkStation;
import ar.com.billing.workstation.utilities.Configurator;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommDriver;
import javax.comm.CommPortIdentifier;
import javax.comm.ParallelPort;

/**
 *
 * @author Mildo
 */
public class PrinterController {

    Enumeration portList;
    CommPortIdentifier portId;
    ParallelPort parallelPort;
    
    Configurator config;
    String port;
    String app;
    private static PrinterController instance=null;
    private static CommDriver commDriver;
    public static PrinterController getInstance(){
        if(instance==null){
            instance = new PrinterController();
        }
        
        return instance;
    }
    private PrinterController() {
        System.setSecurityManager(null);
        try {
            commDriver = (CommDriver)Class.forName("com.sun.comm.Win32Driver").newInstance();
            commDriver.initialize();
            config = Configurator.getInstance();
            port = (String) config.findProperty(Configurator.PRINT_PORT);
            app = (String) config.findProperty(Configurator.APP_NAME);
            
        } catch (Exception e) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, e.getMessage());
        }
    }
    public void print(String fabricLabelName, String colorName, String stripeName, String weigthStr, String partida, String date,String barcode) throws Exception {
        StringBuffer labelCommand;
        OutputStream outputStream;
        boolean printed=false;
        try {
            DataOutputStream dataOutputStream;
            fabricLabelName = removeLatinChars(fabricLabelName);
            colorName = removeLatinChars(colorName);
            stripeName = removeLatinChars(stripeName);
            colorName = removeLatinChars(colorName);
            
            weigthStr = removeLatinChars(weigthStr);
            
            portList = CommPortIdentifier.getPortIdentifiers();
            
            labelCommand = generateLabelCommand(barcode, date, fabricLabelName, colorName, weigthStr, partida,stripeName);
            String alta = fabricLabelName + ";" + colorName + ";" + weigthStr + ";" +stripeName + ";"+ barcode;
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, alta);
            while (portList.hasMoreElements() && !printed) {
                portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
                    if (portId.getName().compareToIgnoreCase(port) ==0 ) {
                        printed=true;
                        parallelPort = (ParallelPort) portId.open(app, 2000);
                        outputStream = parallelPort.getOutputStream();
                        dataOutputStream = new DataOutputStream(outputStream);
                        dataOutputStream.writeUTF(labelCommand.toString());
                        dataOutputStream.close();
                        parallelPort.close();
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, ex.getMessage());
            throw new Exception("No se pudo imprimir la etiqueta");
        }
    }
 
    private String removeLatinChars(String str) {
        str = str.replace('á','a');
        str = str.replace('é','e');
        str = str.replace('í','i');
        str = str.replace('ó','o');
        str = str.replace('ú','u');
        str = str.replace('ñ','n');
        str = str.replace('Ñ','N');
        str = str.replace('Á','A');
        str = str.replace('É','E');
        str = str.replace('Í','I');
        str = str.replace('Ó','O');
        str = str.replace('Ú','U');
        return str;  
    }

    private StringBuffer generateLabelCommand(String barcode, String date, String code, String colorStr, String weight, String part,String stripeName) {
        
        StringBuffer labelCommand = new StringBuffer();
        labelCommand.append("^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR2,2~SD15^JUS^LRN^CI0^XZ");
        labelCommand.append("^XA");
        labelCommand.append("^MMT");
        labelCommand.append("^PW448");
        labelCommand.append("^LL0519");
        labelCommand.append("^LS0");
        labelCommand.append("^FT24,266^A0N,28,28^FH\\^FDFecha: "+date+"^FS");
        labelCommand.append("^FT24,295^A0N,28,28^FH\\^FDPartida: "+part+"^FS");
        labelCommand.append("^BY3,3,129^FT24,448^BCN,,Y,N");
        labelCommand.append("^FD>;"+barcode+"^FS");
        labelCommand.append("^FT24,232^A0N,47,55^FH\\^FDPeso: "+weight+"^FS");
        labelCommand.append("^FT24,182^A0N,47,55^FH\\^FD"+stripeName+"^FS");
        labelCommand.append("^FT24,131^A0N,47,55^FH\\^FD"+colorStr+"^FS");
        labelCommand.append("^FT24,81^A0N,47,55^FH\\^FD"+code+" Friza Pesada^FS");
        
        labelCommand.append("^PQ1,0,1,Y^XZ");
        
        
        return labelCommand;
    }

    
}