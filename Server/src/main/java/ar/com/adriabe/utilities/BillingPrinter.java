package ar.com.adriabe.utilities;

import ar.com.adriabe.daos.SettingDao;
import ar.com.adriabe.model.constant.EurekaConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.PrintService;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.HashMap;
import java.util.Map;

@Component("billingPrinter")
public class BillingPrinter implements CustomPrinter {
	
	private static final long MAX_NUMBER = 1000l;
    protected static Map<Long, String> NUMBER_TO_WORDS = null;
    private SettingDao settingDao;

	
    @Autowired
    public BillingPrinter(SettingDao settingDao) {
        this.settingDao = settingDao;
    }

    protected static void populateNUMBER_TO_WORDS() {
        if (NUMBER_TO_WORDS == null) {
            NUMBER_TO_WORDS = new HashMap<Long, String>();
            NUMBER_TO_WORDS.put(0l, "");
            NUMBER_TO_WORDS.put(1l, "Uno");
            NUMBER_TO_WORDS.put(2l, "Dos");
            NUMBER_TO_WORDS.put(3l, "Tres");
            NUMBER_TO_WORDS.put(4l, "Cuatro");
            NUMBER_TO_WORDS.put(5l, "Cinco");
            NUMBER_TO_WORDS.put(6l, "Seis");
            NUMBER_TO_WORDS.put(7l, "Siete");
            NUMBER_TO_WORDS.put(8l, "Ocho");
            NUMBER_TO_WORDS.put(9l, "Nueve");
            NUMBER_TO_WORDS.put(10l, "Diez");
            NUMBER_TO_WORDS.put(11l, "Once");
            NUMBER_TO_WORDS.put(12l, "Doce");
            NUMBER_TO_WORDS.put(13l, "Tresce");
            NUMBER_TO_WORDS.put(14l, "Catorce");
            NUMBER_TO_WORDS.put(15l, "Quince");
            NUMBER_TO_WORDS.put(16l, "Dieciséis");
            NUMBER_TO_WORDS.put(17l, "Diecisiete");
            NUMBER_TO_WORDS.put(18l, "Dieciocho");
            NUMBER_TO_WORDS.put(19l, "Diecinueve");
            NUMBER_TO_WORDS.put(20l, "Veinte");
            NUMBER_TO_WORDS.put(21l, "Veintiuno");
            NUMBER_TO_WORDS.put(22l, "Veintidós");
            NUMBER_TO_WORDS.put(23l, "Veintitrés");
            NUMBER_TO_WORDS.put(24l, "Veinticuatro");
            NUMBER_TO_WORDS.put(25l, "Veinticinco");
            NUMBER_TO_WORDS.put(26l, "Veintiséis");
            NUMBER_TO_WORDS.put(27l, "Veintisiete");
            NUMBER_TO_WORDS.put(28l, "Veintiocho");
            NUMBER_TO_WORDS.put(29l, "Veintinueve");
            NUMBER_TO_WORDS.put(30l, "Treinta");
            NUMBER_TO_WORDS.put(40l, "Cuarenta");
            NUMBER_TO_WORDS.put(50l, "Cincuenta");
            NUMBER_TO_WORDS.put(60l, "Sesenta");
            NUMBER_TO_WORDS.put(70l, "Setenta");
            NUMBER_TO_WORDS.put(80l, "Ochenta");
            NUMBER_TO_WORDS.put(90l, "Noventa");
            NUMBER_TO_WORDS.put(100l, "Ciento");
            NUMBER_TO_WORDS.put(200l, "Doscientos");
            NUMBER_TO_WORDS.put(300l, "Trescientos");
            NUMBER_TO_WORDS.put(400l, "Cuatrocientos");
            NUMBER_TO_WORDS.put(500l, "Quinientos");
            NUMBER_TO_WORDS.put(600l, "Seiscientos");
            NUMBER_TO_WORDS.put(700l, "Sietecientos");
            NUMBER_TO_WORDS.put(800l, "Ochocientos");
            NUMBER_TO_WORDS.put(900l, "Novecientos");
        }
    }

	public static String numberToWords(Double total) {
        long iPart = total.longValue();
        long fPart = (long) (total * 100 - iPart * 100);

        populateNUMBER_TO_WORDS();
        String value = longNumberToWords(iPart).toLowerCase();
        char first = Character.toUpperCase(value.charAt(0));
        value = first + value.substring(1);

        return value + " con " + fPart + "/100";
    }

    public static String longNumberToWords(long number) {
        return longNumberToWords(number, 0);
    }

    public static String longNumberToWords(long number, int pos) {
        if (number < MAX_NUMBER) {
            String result = "";

            if (number < 2 && pos == 3) {
                return "";
            }

            //Analizo las centenas
            long centena = number / 100;
            long decena = (number % 100) / 10;
            long unidad = (number % 100) % 10;

            result = (centena == 1 && unidad == 0 && decena == 0) ? "Cien" : NUMBER_TO_WORDS.get(centena * 100l);

            if (decena <= 2) {
                result = result + " " + NUMBER_TO_WORDS.get(number % 100);
                return result;
            }
            if (NUMBER_TO_WORDS.containsKey(decena * 10)) {
                result = result + " " + NUMBER_TO_WORDS.get(decena * 10);
            }
            if (number % 10 == 1 && pos == 3) {
                result = result + " y un";
            } else {
                result = result + " y " + NUMBER_TO_WORDS.get(number % 10);
            }

            return result;
        }

        long resto = number % MAX_NUMBER;
        long cociente = number / MAX_NUMBER;
        String restoStr = longNumberToWords(resto, pos);
        pos = pos + 3;
        String cocienteStr = longNumberToWords(cociente, pos);
        int cocientePos = pos / 3;
        String unidad = (cocientePos % 2 == 1) ? " mil " : ((cociente > 1) ? " millones " : " millon ");
        String value = "";
        value = cocienteStr + unidad + restoStr;
        return StringUtils.capitalize(value);


	}

    @Override
    public void print(CustomPrintable doc) throws Exception {
        DocumentPrintable data;
        String printerName;
        if (doc instanceof BillingAPrintable) {
            data = (BillingAPrintable) doc;
            printerName = settingDao.getPrinterNameBillA();
        } else if (doc instanceof BillingBPrintable) {
            data = (BillingBPrintable) doc;
            printerName = settingDao.getPrinterNameBillB();
        } else if (doc instanceof ReceiptPrintable) {
            data = (ReceiptPrintable) doc;
            printerName = settingDao.getPrinterNameReceipt();
        } else {
            throw new PrinterException("Tipo de archivo invalido");
        }

        try {


            PrintService[] services = PrinterJob.lookupPrintServices();
            PrintService service = null;
            if (services.length > 0) {
                for (PrintService printService : services) {
                    if (printService.getName().toLowerCase().contains(printerName.toLowerCase())) {
                        service = printService;
                    }
                }
                PrinterJob printJob = PrinterJob.getPrinterJob();
                PageFormat pf = data.getPageFormat(printJob);
                printJob.setPrintService(service);
                printJob.setPrintable(data, pf);
                if (EurekaConstants.DEBUG) {
                    printJob.printDialog();
                }
                printJob.print(data.getPrintRequestAttributeSet(printJob));
            } else {
                throw new PrinterException();
            }

        } catch (PrinterException pe) {
            throw new PrinterException("Error con la impresora, revisar la conexión.");
        } catch (Exception e) {
            throw new PrinterException("Error con la impresora, revisar la conexión.");
        }


	}

    @Override
    public String getPrinterType() {
        return "printer";
    }
	
}
