package ar.com.adriabe.utilities;


import org.apache.logging.log4j.LogManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class TicketPrinter implements CustomPrinter {

	public static final int PRINTER_LINE_WIDTH = 38;
	public static final String NEW_LINE = "\n";
	//	PRINTER COMMANDS
	public static final byte ESCAPE = 0x1b;
	public static final byte r_COMMAND = 0x72;
	public static final String PRINT_RED = new String(new byte[] {ESCAPE, r_COMMAND, 1});
	public static final String PRINT_BLACK = new String(new byte[] {ESCAPE, r_COMMAND, 0});
    protected final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TicketPrinter.class);
    protected List<String> lines = null;

	@Override
	public void print(CustomPrintable listing) throws Exception {
        getTicketLinesToPrint(listing);

        File file = new File("/Users/Mildo/Documents/TicketExample.txt");
        if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for(String str : lines) {
			bw.write(str);
		}
		bw.close();
	}

    protected void getTicketLinesToPrint(CustomPrintable listing) throws Exception {
        TicketPrintable tp;
        if (listing instanceof TicketPrintable) {
            tp = (TicketPrintable) listing;
        } else {
            throw new Exception();
        }
        lines = tp.getPrintableTicket();
    }


    @Override
    public String getPrinterType() {
		return "POSPrinter";
	}

}
