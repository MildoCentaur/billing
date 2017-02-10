/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kendoposclient.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kendoposclient.KendoClientConstants;
import kendoposclient.configuration.Configurator;
import kendoposclient.configuration.KendoPOSException;
import kendoposclient.printer.POSPrinter;

/**
 *
 * @author Adriabe
 */
public class Server {

    private ServerSocket serverSock = null;
    private Configurator config;
    private Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public Server() throws Exception {
        try {
            logger.log(Level.ALL, "Buscando server data");
            config = Configurator.getInstance();
            int port = Integer.parseInt((String) config.findProperty(KendoClientConstants.SERVER_PORT));
            logger.log(Level.ALL, "Levantando el servidor");
            this.serverSock = new ServerSocket(port);
            logger.log(Level.ALL, "Socket abierto");

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.ALL, "No se pudo abrir el puerto");
            throw new KendoPOSException("No se pudo abrir el puerto");
            //throw new KendoPOSException(KendoClientConstants.SERVER_PORT_ERROR, e);
        }

    }

    public void waitForConnections() throws IOException {
        Socket sock = null;
        DataInputStream sockInput = null;
        PrintWriter sockOutput = null;
        List<String> lines;
        POSPrinter printer = new POSPrinter();
        while (true) {
            try {
                logger.log(Level.ALL, "Escucho por una conexion");
                // This method call, accept(), blocks and waits (forever if necessary) until some other program
                // opens a socket connection to our server.  When some other program opens a connection to our server,
                // accept() creates a new socket to represent that connection and returns.
                sock = serverSock.accept();
                logger.log(Level.ALL, "Atiendo una conexion");
                // From this point on, no new socket connections can
                // be made to our server until we call accept() again.
                sockInput = new DataInputStream(sock.getInputStream());
//                sockOutput = new DataOutputStream(sock.getOutputStream());
                logger.log(Level.ALL, "Creo el stream de salida.");
                sockOutput = new PrintWriter(sock.getOutputStream(),true);
                // Do something with the socket - read bytes from the
                // socket and write them back to the socket until the
                // other side closes the connection.
                lines = handleConnection(sockInput, sockOutput);
                printer.print(lines);
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.ALL, "------------------------------------");
                logger.log(Level.ALL, e.getMessage());
                logger.log(Level.ALL, "------------------------------------");
            } finally {
                logger.log(Level.INFO, "cierro el socket con el cliente");

                if (sock != null) {
                    sock.close();
                }
            }
        }
    }
    
        // All this method does is wait for some bytes from the
    // connection, read them, then write them back again, until the
    // socket is closed from the other side.
    public List<String> handleConnection(DataInputStream sockInput, PrintWriter sockOutput) throws KendoPOSException {
        while (true) {
            try {
                logger.log(Level.ALL, "Esperando conexiones\n");
                int linesRead = 0;
                List<String> lines = new ArrayList<String>();
                String line;
                // This call to read() will wait forever, until the
                // program on the other side either sends some data,
                // or closes the socket.
                
                linesRead = sockInput.readInt();
                for (int i = 0; i < linesRead; i++) {
                    line = new String(sockInput.readUTF());
                    logger.log(Level.ALL, line);
                    lines.add(line);
                }
                logger.log(Level.ALL, "Fin lectura del ticket");
                logger.log(Level.ALL, "Respondo con lineas de ticket leidas");
                sockOutput.print(lines.size());
                
                return lines;

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.log(Level.ALL, ex.getMessage());
                throw new KendoPOSException("Error en al escuchar el pedido");
                //throw new KendoPOSException(KendoClientConstants.SERVER_ERROR, ex);
            }
        }
    }

}
