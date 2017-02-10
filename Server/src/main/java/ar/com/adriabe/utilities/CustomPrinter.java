package ar.com.adriabe.utilities;


public interface CustomPrinter {
	
	public void print(CustomPrintable listing) throws Exception;

	public String getPrinterType();
}
