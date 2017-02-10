package ar.com.adriabe.utilities;

import ar.com.adriabe.generic.KendoExecutionException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TicketPrinterRemote extends TicketPrinter {

    private String serverHostname = "192.168.0.104";
    private int serverPort = 54321;
    private Socket sock = null;
    private DataInputStream sockInput = null;
    private DataOutputStream sockOutput = null;

    public TicketPrinterRemote(String serverHostname, int serverPort) {
        this.serverPort = serverPort;
        this.serverHostname = serverHostname;
    }

    @Override
    public void print(CustomPrintable listing) throws Exception {
        // Genero la lista de lineas a enviar
        super.getTicketLinesToPrint(listing);
        try {
            // Abro el socket para comunicar
            openSocket();

            logger.debug("About to start reading/writing to/from socket.");
            sendData();
            logger.debug("Done reading/writing to/from socket, closing socket.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error de comunicación con la ticketeadora.");
        } finally {
            if (sock != null) {
                sock.close();
            }

        }
        logger.debug("Exiting.");
    }

    /**
     * @throws IOException
     * @throws Exception
     */
    private void sendData() throws IOException, Exception {
        int lines_read = 0;
        int iterate = 0;
        int i = 0;
        while (iterate < 3) {
            sockOutput.writeInt(lines.size());
            while (i < lines.size()) {
                sockOutput.writeUTF(lines.get(i++));
            }
            lines_read = sockInput.readInt();
            if (lines_read == lines.size()) {
                iterate = 3;
            } else {
                iterate++;
            }
        }
        // Sleep for a bit so the action doesn't happen to fast - this is
        // purely for reasons of demonstration, and not required
        // technically.
        // try { Thread.sleep(50);} catch (Exception e) {};

        //check data sent properly
        if (lines_read != lines.size() && iterate == 3) {
            throw new Exception();
        }
    }

    /**
     * @throws IOException
     */
    private void openSocket() throws IOException {
        logger.debug("Opening connection to " + serverHostname + " port " + serverPort);
        sock = new Socket(serverHostname, serverPort);
        sockInput = new DataInputStream(sock.getInputStream());
        sockOutput = new DataOutputStream(sock.getOutputStream());
    }

    @Override
    public String getPrinterType() {
        return "POSPrinterRemote";
    }

    public String getServerHostname() {
        return serverHostname;
    }

    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public Socket getSock() {
        return sock;
    }

    public void setSock(Socket sock) {
        this.sock = sock;
    }
}