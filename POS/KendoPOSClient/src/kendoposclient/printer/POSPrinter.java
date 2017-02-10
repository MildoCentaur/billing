/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kendoposclient.printer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import kendoposclient.KendoClientConstants;
import kendoposclient.configuration.Configurator;
import kendoposclient.configuration.KendoPOSException;

/**
 *
 * @author Adriabe
 */
public class POSPrinter {

    Enumeration portList;
    CommPortIdentifier portId;
    SerialPort serialPort;
    OutputStream outputStream;
    Configurator config;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void print(List<String> lines) throws KendoPOSException {


        try {

            logger.log(Level.ALL, "Iniciando impresion");
            config = Configurator.getInstance();
            String port = (String) config.findProperty(KendoClientConstants.PRINT_PORT);
            String app = (String) config.findProperty(KendoClientConstants.APP_NAME);
            portList = CommPortIdentifier.getPortIdentifiers();

            while (portList.hasMoreElements()) {
                logger.log(Level.ALL, "itero por el portList");
                portId = (CommPortIdentifier) portList.nextElement();
                logger.log(Level.ALL, "chequeo que el tipo de puerto sea serial");
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals(port)) {
                        logger.log(Level.ALL, "dentro del puerto correcto");
                        //if (portId.getName().equals("/dev/term/a")) {
                        logger.log(Level.ALL, "abri el puerto");
                        serialPort = (SerialPort) portId.open(app, 2000);
                        //serialPort.setOutputBufferSize(10240);
                        outputStream = serialPort.getOutputStream();
                        sendJobToPrinter(lines);
                        serialPort.close();
                    }
                }
            }
        
        } catch (IOException ex) {
            logger.log(Level.ALL, "Hubo un error al enviar informacion a la impresora.");
            logger.log(Level.ALL, ex.getMessage());
            throw new KendoPOSException("Hubo un error al enviar informacion a la impresora.");
        } catch (PortInUseException ex) {
            logger.log(Level.ALL, "Hubo un error al abrir el puerto, puerto en uso");
            logger.log(Level.ALL, ex.getMessage());
            throw new KendoPOSException("Hubo un error al abrir el puerto, puerto en uso");
        }
    }

    public boolean checkPortAvailable(Configurator config) throws KendoPOSException {

        logger.log(Level.ALL, "Checkeando si el puerto esta disponible.");
        config = Configurator.getInstance();
        CommPortIdentifier checkPortId;

        logger.log(Level.ALL, "Leyendo propiedades");
        String port = (String) config.findProperty(KendoClientConstants.PRINT_PORT);
        String app = (String) config.findProperty(KendoClientConstants.APP_NAME);
        logger.log(Level.ALL, "property app: " + app);
        logger.log(Level.ALL, "property port: " + port);
        Enumeration checkPortList = CommPortIdentifier.getPortIdentifiers();
        logger.log(Level.ALL, "obtengo el portList");
        logger.log(Level.ALL, "portList: " + checkPortList == null ? "null" : checkPortList.toString());
        logger.log(Level.ALL, "se encontraron puertos: " + (checkPortList.hasMoreElements() ? "si" : "no"));

        while (checkPortList.hasMoreElements()) {
            logger.log(Level.ALL, "itero por el portList");
            checkPortId = (CommPortIdentifier) checkPortList.nextElement();
            logger.log(Level.ALL, "chequeo que el tipo de puerto sea serial");
            if (checkPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (checkPortId.getName().equals(port)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void sendJobToPrinter(List<String> lines) throws IOException {
        DataOutputStream dataOutputStream;
        logger.log(Level.ALL, "tengo el outputstrem " + outputStream.toString());
        dataOutputStream = new DataOutputStream(outputStream);
        /*serialPort.setSerialPortParams(9600,
         SerialPort.DATABITS_8,
         SerialPort.STOPBITS_1,
         SerialPort.PARITY_NONE); */
        for (int i = 0; i < 2; i++) {
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            for (String line : lines) {
                dataOutputStream.writeBytes(line);
            }
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("--------------------------------------\n");
        }
    }
}
