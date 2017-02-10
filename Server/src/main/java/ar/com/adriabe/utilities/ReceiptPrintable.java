package ar.com.adriabe.utilities;

import ar.com.adriabe.model.*;
import ar.com.adriabe.model.constant.EurekaConstants;
import org.apache.commons.lang.StringUtils;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrintQuality;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ReceiptPrintable extends DocumentPrintable {

    private static final double CURRENCY_WIDTH = 2.0;
    // header positions
    private static final Double DATE_HEIGHT = 1.5;
    private static final Double DATE_WIDTH = 17.0;
    private static final Double NUMBER_WIDTH = 2.0;
    private static final Double CLIENT_NAME_WIDTH = 2.0;
    private static final Double CLIENT_NAME_HEIGHT = 2.8;
    private static final Double ADDRESS_WIDTH = 2.0;
    private static final Double ADDRESS_HEIGHT = 3.5;
    private static final Double IVA_CONDITION_WIDTH = 2.0;
    private static final Double IVA_CONDITION_HEIGHT = 4.5;
    private static final Double CUIT_WIDTH = 13.0;
    private static final Double CUIT_HEIGHT = 4.5;


    // paper size
    private static final Double A4_HEIGHT = 29.7;
    private static final Double A4_WIDTH = 21.0;

    //body

    ////Tabla de facturas pagadas
    private static final Double BILL_ROW_HEIGHT = 6.0;
    private static final Double BILL_COLUMN_DATE_WIDTH = 2.1;
    private static final Double BILL_COLUMN_NUMBER_WIDTH = 4.1;
    private static final Double BILL_COLUMN_VALUE_WIDTH = 6.1;

    ////Tabla de cheques recibidos
    private static final Double CHEQUE_HEIGHT = 6.0;
    private static final Double CHEQUE_COLUMN_BANCO_WIDTH = 9.1;
    private static final Double CHEQUE_COLUMN_NUMERO_WIDTH = 12.1;
    private static final Double CHEQUE_COLUMN_FECHA_WIDTH = 15.1;
    private static final Double CHEQUE_COLUMN_VALUE_WIDTH = 17.1;

    //Footer
    private static final Double VALUE_IN_WORDS_HEIGHT = 11.0;
    private static final Double VALUE_IN_WORDS_WIDTH = 2.0;

    private Receipt p;
    private List<Bill> bills = null;
    private Client client;
    private Graphics graphics;

    public ReceiptPrintable(Receipt p, Client client, List<Bill> bills) {
        this.p = p;
        this.client = client;
        this.bills = bills;
        setTitle("Recibo" + p.getNumber());
    }

    public ReceiptPrintable(Receipt receipt) {
        this.p = receipt;
        this.client = receipt.getClient();
        this.bills = receipt.getBills();
        setTitle("Recibo" + p.getNumber());
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        int result = NO_SUCH_PAGE;
        this.graphics = graphics;
        if (pageIndex < 3) {
            Graphics2D g2d = (Graphics2D) graphics;
            double width = pageFormat.getImageableWidth() - fromCMToPPI(2);
            // double height = pageFormat.getImageableHeight();

            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            Font font = new Font("Arial", Font.PLAIN, 9);
            g2d.setFont(font);

            drawHeader(g2d, width);
            drawBody(g2d, width);
            drawFooter(g2d, width);

            result = PAGE_EXISTS;
        }
        return result;
    }

    private void drawFooter(Graphics graphics, double width) {

        int x = (int) Math.round(fromCMToPPI(VALUE_IN_WORDS_WIDTH));
        int y = (int) Math.round(fromCMToPPI(VALUE_IN_WORDS_HEIGHT));
        String value = StringUtils.capitalize(BillingPrinter.numberToWords(p.getValue()).trim());
        drawLongString(graphics, "Recibi la cantidad de pesos: " + value + " pesos.", x, y, (int) Math.round(width * 0.7));

    }

    private void drawBody(Graphics2D g2d, double width) {
        //tabla de facturas pagadas
//		double widthTabla = fromCMToPPI(6);
        double height = fromCMToPPI(10) * 0.75;

//		widthTabla = fromCMToPPI(7);
        Graphics graphics = (Graphics) g2d;

        FontMetrics fm = graphics.getFontMetrics();
        graphics = drawBillTable(graphics, fm);

        double heightCheques = drawChequeTable(graphics, fm);
        height = (height > heightCheques) ? height : heightCheques;
        drawOtherCashAndTransferPayment(graphics, fm, (int) Math.round(height));


    }

    /**
     * @param graphics
     * @param fm
     * @param rowHeight
     */
    private void drawOtherCashAndTransferPayment(Graphics graphics, FontMetrics fm, int rowHeight) {
        rowHeight = (int) (rowHeight + fm.getAscent() * 1.5);

        int codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_FECHA_WIDTH));
        graphics.drawString("Efectivo: ", codeWidth - 10, rowHeight);

        String value = String.format(new Locale("es", "ES"), "%1$,.2f $", p.getCash());
        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH));
        codeWidth = codeWidth + formatAlignRight(value, CURRENCY_WIDTH);
        graphics.drawString(value, codeWidth, rowHeight);

        rowHeight = (int) (rowHeight + fm.getAscent() * 1.5);
        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_FECHA_WIDTH));
        graphics.drawString("Transferencia: ", codeWidth - 10, rowHeight);

        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH));
        Double acumTransfer = 0.0;
        for (Transfer transfer : p.getTransfers()) {
            acumTransfer = acumTransfer + transfer.getValue();
        }
        ;
        value = String.format(new Locale("es", "ES"), "%1$,.2f $", acumTransfer);
        codeWidth = codeWidth + formatAlignRight(value, CURRENCY_WIDTH);
        graphics.drawString(value, codeWidth, rowHeight);

        rowHeight = (int) (rowHeight + fm.getAscent() * 1.5);
        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_FECHA_WIDTH));
        graphics.drawString("Total: ", codeWidth - 10, rowHeight);

        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH));
        value = String.format(new Locale("es", "ES"), "%1$,.2f $", p.getValue());
        codeWidth = codeWidth + formatAlignRight(value, CURRENCY_WIDTH);
        graphics.drawString(value, codeWidth, rowHeight);


    }

    /**
     * @param graphics
     * @param fm
     * @return
     */
    private int drawChequeTable(Graphics graphics, FontMetrics fm) {
        int codeWidth;
        int x1Line = 0;
        int x2Line = 0;
        int yLine = 0;


        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);

        int rowHeight = (int) Math.round(fromCMToPPI(CHEQUE_HEIGHT));
        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_BANCO_WIDTH));

        //linea sobre los titulos de columna
        x1Line = codeWidth - (int) Math.round(fromCMToPPI(0.1));
        x2Line = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH + CURRENCY_WIDTH + 0.1));
        int y2Line = (int) Math.round(fromCMToPPI(CHEQUE_HEIGHT) - fm.getAscent() * 1.5);
        graphics.drawLine(x1Line, y2Line, x2Line, y2Line);


        graphics.drawString("Banco", codeWidth, rowHeight);

        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_NUMERO_WIDTH));
        graphics.drawString("Nº Cheque", codeWidth, rowHeight);

        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_FECHA_WIDTH));
        graphics.drawString("Fecha", codeWidth, rowHeight);

        codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH));
        graphics.drawString("Valor", codeWidth, rowHeight);
        yLine = (int) (rowHeight + fm.getAscent() * 0.25);
        graphics.drawLine(x1Line, yLine, x2Line, yLine);


        String value = null;
        for (Cheque cheque : p.getCheques()) {
            rowHeight = (int) (rowHeight + fm.getAscent() * 1.5);
            yLine = (int) (rowHeight + fm.getAscent() * 0.25);

            codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_BANCO_WIDTH));

            graphics.drawString(String.valueOf(cheque.getBank().getShortName()), codeWidth, rowHeight);

            codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_NUMERO_WIDTH));
            graphics.drawString(String.valueOf(cheque.getNumber()), codeWidth, rowHeight);

            codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_FECHA_WIDTH));
            graphics.drawString(format.format(cheque.getExpirationDate()), codeWidth, rowHeight);

            codeWidth = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH));
            value = String.format(new Locale("es", "ES"), "%1$,.2f $", cheque.getValue());
            codeWidth = codeWidth + formatAlignRight(value, CURRENCY_WIDTH);
            graphics.drawString(value, codeWidth, rowHeight);

            graphics.drawLine(x1Line, yLine, x2Line, yLine);
        }

        //draw Columns
        int xColumn = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH + CURRENCY_WIDTH + 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);

        xColumn = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_VALUE_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);
        xColumn = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_FECHA_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);
        xColumn = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_NUMERO_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);
        xColumn = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_BANCO_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);


        return rowHeight;
    }

    /**
     * @param graphics
     * @param fm
     */
    private Graphics drawBillTable(Graphics graphics, FontMetrics fm) {
        int x1Line = 0;
        int x2Line = 0;
        int yLine = 0;

        int rowHeight = 0;
        int codeWidth = 0;
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
        long base = Math.round(fromCMToPPI(BILL_ROW_HEIGHT));

        //draw line before table header
        x1Line = (int) Math.round(fromCMToPPI(BILL_COLUMN_DATE_WIDTH - 0.1));
        x2Line = (int) Math.round(fromCMToPPI(BILL_COLUMN_VALUE_WIDTH + CURRENCY_WIDTH + 0.1));
        yLine = (int) (Math.round(fromCMToPPI(BILL_ROW_HEIGHT) - fm.getAscent() * 1.5));
        graphics.drawLine(x1Line, yLine, x2Line, yLine);
        //draw table header
        codeWidth = (int) Math.round(fromCMToPPI(BILL_COLUMN_DATE_WIDTH));
        rowHeight = (int) base;
        graphics.drawString("Fecha", codeWidth, rowHeight);

        codeWidth = (int) Math.round(fromCMToPPI(BILL_COLUMN_NUMBER_WIDTH));
        graphics.drawString("Número", codeWidth, rowHeight);

        codeWidth = (int) Math.round(fromCMToPPI(BILL_COLUMN_VALUE_WIDTH));
        graphics.drawString("Valor", codeWidth, rowHeight);


        yLine = (int) (rowHeight + fm.getAscent() * 0.25);
        graphics.drawLine(x1Line, yLine, x2Line, yLine);


        String value;
        if (bills != null) {
            for (Bill b : bills) {
                rowHeight = (int) (rowHeight + fm.getAscent() * 1.5);
                yLine = (int) (rowHeight + fm.getAscent() * 0.25);
                codeWidth = (int) Math.round(fromCMToPPI(BILL_COLUMN_DATE_WIDTH));

                graphics.drawString(format.format(b.getDate()), codeWidth, rowHeight);

                codeWidth = (int) Math.round(fromCMToPPI(BILL_COLUMN_NUMBER_WIDTH));
                graphics.drawString(String.format("%08d", b.getBillNumber()), codeWidth, rowHeight);

                codeWidth = (int) Math.round(fromCMToPPI(BILL_COLUMN_VALUE_WIDTH));
                value = String.format(new Locale("es", "ES"), "%1$,.2f $", b.getTotal());
                codeWidth = codeWidth + formatAlignRight(value, CURRENCY_WIDTH);
                graphics.drawString(value, codeWidth, rowHeight);


                graphics.drawLine(x1Line, yLine, x2Line, yLine);

            }
        }

        //draw Columns
        int y2Line = (int) Math.round(fromCMToPPI(BILL_ROW_HEIGHT) - fm.getAscent() * 1.5);
        int xColumn = (int) Math.round(fromCMToPPI(BILL_COLUMN_VALUE_WIDTH + CURRENCY_WIDTH + 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);

        xColumn = (int) Math.round(fromCMToPPI(BILL_COLUMN_VALUE_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);
        xColumn = (int) Math.round(fromCMToPPI(BILL_COLUMN_DATE_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);
        xColumn = (int) Math.round(fromCMToPPI(BILL_COLUMN_NUMBER_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);
        xColumn = (int) Math.round(fromCMToPPI(CHEQUE_COLUMN_BANCO_WIDTH - 0.1));
        graphics.drawLine(xColumn, yLine, xColumn, y2Line);

        return graphics;
    }

    private Graphics drawHeader(Graphics graphics, double width) {
        graphics = drawString(graphics, "Recibo Nº: " + p.getNumber(), NUMBER_WIDTH, DATE_HEIGHT);
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
        String printString = "Fecha: " + format.format(p.getDate());
        graphics = drawString(graphics, printString, DATE_WIDTH, DATE_HEIGHT);
        printString = "Señor: " + client.getName();
        graphics = drawString(graphics, printString, CLIENT_NAME_WIDTH, CLIENT_NAME_HEIGHT);
        printString = "Domicilio: " + client.getFullAddress();
        graphics = drawString(graphics, printString, ADDRESS_WIDTH, ADDRESS_HEIGHT);
        String iva = "Iva: " + client.getIVACondition().getLabel();
        graphics = drawString(graphics, iva, IVA_CONDITION_WIDTH, IVA_CONDITION_HEIGHT);
        printString = "CUIT: " + client.getCuit();
        graphics = drawString(graphics, printString, CUIT_WIDTH, CUIT_HEIGHT);

        return graphics;
    }

    @Override
    public PageFormat getPageFormat(PrinterJob printJob) {
        PageFormat pf = printJob.defaultPage();
        Paper p = pf.getPaper();
        double width = fromCMToPPI(A4_WIDTH);
        double height = fromCMToPPI(A4_HEIGHT);

        p.setSize(width, height);
        p.setImageableArea(0, 0, width, height);
        pf.setPaper(p);
        pf.setOrientation(PageFormat.PORTRAIT);
        PageFormat pf2 = printJob.validatePage(pf);

        return pf2;
    }

    public PrintRequestAttributeSet getPrintRequestAttributeSet(PrinterJob printJob) {
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        // Name the print job
        JobName jobName = new JobName(getTitle(), null);

        if (validateAttribute(printJob, jobName, attributeSet)) {
            attributeSet.add(jobName);
        }

        // Print out a page range
        PageRanges range = new PageRanges("1-2");
        if (validateAttribute(printJob, range, attributeSet)) {
            attributeSet.add(range);
        }

        // Black and white print
        if (validateAttribute(printJob, Chromaticity.MONOCHROME, attributeSet)) {
            attributeSet.add(Chromaticity.MONOCHROME);
        }

        // Draft print quality
        if (validateAttribute(printJob, PrintQuality.DRAFT, attributeSet)) {
            attributeSet.add(PrintQuality.DRAFT);
        }

        return attributeSet;
    }

    private Graphics drawString(Graphics g, String str, Double x, Double y) {
        g.drawString(str, (int) Math.round(fromCMToPPI(x)),
                (int) Math.round(fromCMToPPI(y)));
        return g;
    }

    public String formatCurrency(double value) {
        return String.format(new Locale("es", "ES"), "%1$,.2f", value);
    }

    public int formatAlignRight(String str, double width) {
        FontMetrics metrics = graphics.getFontMetrics();
        width = fromCMToPPI(width);
        double wString = metrics.stringWidth(str);
        double space = (width - wString);
        space = (double) Math.round(space * 100) / 100;
        if (space > 0) {
            return (int) space;
        }
        return 0;
    }

    public int formatAlignMiddle(String str, double width) {
        FontMetrics metrics = graphics.getFontMetrics();
        width = fromCMToPPI(width);
        double wString = metrics.stringWidth(str);
        double space = (width - wString) / 2;
        space = (double) Math.round(space * 100) / 100;
        if (space > 0) {
            return (int) space;
        }
        return 0;
    }

}
