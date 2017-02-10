package ar.com.adriabe.utilities;


import ar.com.adriabe.model.Bill;
import ar.com.adriabe.model.BillItem;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.constant.EurekaConstants;
import ar.com.adriabe.model.constant.IVA_TYPE;
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
import java.util.Locale;

public class BillingAPrintable extends DocumentPrintable {

	private static final Double MARGIN_TOP = 1.2;
	private static final Double MARGIN_LEFT = 1.1;
	private static final Double MARGIN_BOTTOM = 1.1;
	private static final Double MARGIN_RIGHT = 1.1;
	
	// paper size
	private static final Double PAPER_HEIGHT = MARGIN_TOP + 27.1 + MARGIN_BOTTOM;
	private static final Double PAPER_WIDTH = MARGIN_LEFT + 18.8 + MARGIN_RIGHT;
	
	
	// header positions
	private static final Double DATE_HEIGHT = MARGIN_TOP + 1.8;
	private static final Double DATE_WIDTH = MARGIN_LEFT + 12.2;
	private static final Double CLIENT_NAME_WIDTH = MARGIN_LEFT + 2.0;
	private static final Double CLIENT_NAME_HEIGHT = MARGIN_TOP + 3.8;
	private static final Double ADDRESS_WIDTH = MARGIN_LEFT + 2.0;
	private static final Double ADDRESS_HEIGHT = MARGIN_TOP + 4.7;
	private static final Double IVA_CONDITION_WIDTH = MARGIN_LEFT + 1.0;
	private static final Double IVA_CONDITION_HEIGHT = MARGIN_TOP + 5.6;
	private static final Double SALE_CONDITION_WIDTH = MARGIN_LEFT + 3.2;
	private static final Double SALE_CONDITION_HEIGHT = MARGIN_TOP + 6.5;
	private static final Double CUIT_WIDTH = MARGIN_LEFT + 13.0;
	private static final Double CUIT_HEIGHT = MARGIN_TOP + 5.6;
	private static final Double ORDER_WIDTH = MARGIN_LEFT + 12.0;
	private static final Double ORDER_HEIGHT = MARGIN_TOP + 6.5;
	private static final Double REMITO_WIDTH = MARGIN_LEFT + 16.5;
	private static final Double REMITO_HEIGHT = MARGIN_TOP + 6.5;
	//header position for remito
	private static final Double CUIT_WIDTH_REMITO = MARGIN_LEFT + 13.0;
	private static final Double CUIT_HEIGHT_REMITO = MARGIN_TOP + 5.6;
	private static final Double ORDER_WIDTH_REMITO = MARGIN_LEFT + 13.5;
	private static final Double ORDER_HEIGHT_REMITO = MARGIN_TOP + 6.5;



	private static final Double TOTAL_WIDTH = MARGIN_LEFT + 15.5;
	private static final Double TOTAL_HEIGHT = MARGIN_TOP + 26.6;
	private static final Double SUBTOTAL_HEIGHT = MARGIN_TOP + 22.7;
	private static final Double SUBTOTAL_WIDTH = MARGIN_LEFT + 15.5;
	private static final Double SUBTOTAL2_HEIGHT = MARGIN_TOP + 24.2;
	private static final Double SUBTOTAL2_WIDTH = MARGIN_LEFT + 15.5;

	private static final Double DESCUENTO = 0.0;
	private static final Double DESCUENTO_HEIGHT = MARGIN_TOP + 23.5;
	private static final Double DESCUENTO_WIDTH = MARGIN_LEFT + 15.5;
	private static final String IVA_INSC_PORCENTAGE = "21";
	private static final Double IVA_INSC_PORCENTAGE_WIDTH = MARGIN_LEFT + 14.0;
	private static final Double IVA_INSC_PORCENTAGE_HEIGHT = MARGIN_TOP + 25.0;
	private static final Double IVA_INSC_VALUE_HEIGHT = MARGIN_TOP + 25.0;
	private static final Double IVA_INSC_VALUE_WIDTH = MARGIN_LEFT + 15.5;
	// private static final String IVA_NOINSC_PORCENTAGE = null;
	// private static final String IVA_NOINSC_VALUE = null;
	// private static final Double IVA_NOINSC_VALUE_WIDTH = null;
	// private static final Double IVA_NOINSC_VALUE_HEIGHT = null;
	// private static final Double IVA_NOINSC_PORCENTAGE_HEIGHT = null;
	// private static final Double IVA_NOINSC_PORCENTAGE_WIDTH = null;
	private static final Double COLUMN_CODE_WIDTH = MARGIN_LEFT + 0.5;
	private static final Double COLUMN_AMOUNT_WIDTH = MARGIN_LEFT + 2.2;
	private static final Double COLUMN_DETAIL_WIDTH = MARGIN_LEFT + 5.0;
	private static final Double COLUMN_PRICE_WIDTH = MARGIN_LEFT + 13.5;
	private static final Double COLUMN_VALUE_WIDTH = MARGIN_LEFT + 16.0;
	private static final Double BASE_ROW_HEIGHT = MARGIN_TOP + 8.0;
	private static final String DISCHARGE = "No se admiten reclamos pasadas las 48hs, ní se aceptan devoluciones sobre la mercadería fraccionada o cortada. La mercadería viaja por cuenta y riesgo del comprador."
			+ "Esta factura debera abonarse en la calle 10 Nro 4038 de Villa Lynch - Prov. Bs. As. La mora en en su pago se producirá en forma automatica y desde su vencimiento devengará "
			+ "un interes mensual del    % mensual o la actualización monetaria según indice precios mayoristas nivel gral INDEC más un interes del    % anual, a opcion del acreedor."
			+ "El cobro de la factura no importará renuncia al reclamo de actualización monetaria o intereses, desde la mora hasta el día del efectivo pago. "
			+ "A los efectos judiciales de la presente se pacta la juridicción de los tribunales Ordinarios de la Cap. Fed. Los pagos deberan ser efectuados con cheques o valores"
			+ "sobre Bs. As. a la orden de ADRIABE S.A.C.I.F.I.A. Careceran de valor todos aquellos recibos que no sean emitidos en talonario oficial de la firma.";
	private static final Double DISCHARGE_WIDTH = MARGIN_LEFT + 0.5;
	private static final Double DISCHARGE_HEIGHT = MARGIN_TOP + 17.5;
	private static final Double DISCHARGE_LENGTH = 18.0;

	private static final Double STRING_VALUE_WIDTH = MARGIN_LEFT + 0.5;
	private static final Double STRING_VALUE_HEIGHT = MARGIN_TOP + 24.0;
	private static final double STRING_VALUE_LENGTH = 11.0;
	
	private static final Double REMITO_ITEMS_WIDTH = MARGIN_LEFT + 0.5;
	private static final Double REMITO_ITEMS_HEIGHT = MARGIN_TOP + 22.0;

	private Bill bill;

	public BillingAPrintable(Bill bill) {
		this.bill = bill;
		setTitle("Factura " + bill.getBillNumber());
	}

	public PrintRequestAttributeSet getPrintRequestAttributeSet(PrinterJob printJob) {
		PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

		// Name the print job
		JobName jobName = new JobName(getTitle(), null);

		if (validateAttribute(printJob, jobName, attributeSet)) {
			attributeSet.add(jobName);
		}

		// Print out a page range
		PageRanges range = new PageRanges("1-5");
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

	public PageFormat getPageFormat(PrinterJob printJob) {
		PageFormat pf = printJob.defaultPage();
		Paper p = pf.getPaper();
		double width = fromCMToPPI(PAPER_WIDTH);
		double height = fromCMToPPI(PAPER_HEIGHT);

		p.setSize(width, height);
		p.setImageableArea(0, 0, width, height);
		pf.setPaper(p);
		pf.setOrientation(PageFormat.PORTRAIT);
		PageFormat pf2 = printJob.validatePage(pf);

		return pf2;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// header
		int result = NO_SUCH_PAGE;
		if (pageIndex < 6) {
			Graphics2D g2d = (Graphics2D) graphics;
			double width = pageFormat.getImageableWidth() - fromCMToPPI(2);
			// double height = pageFormat.getImageableHeight();

			g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

			Font font = new Font("Arial", Font.PLAIN, 10);
			g2d.setFont(font);
			if (pageIndex < 2) {
				drawHeader(g2d, width);
				drawBody(g2d, width);
				drawFooter(g2d, width);
			} else {
				drawHeaderRemito(g2d, width);
				drawBodyRemito(g2d, width);
				drawFooterRemito(g2d, width);

			}
			result = PAGE_EXISTS;
		}
		return result;
	}

	

	private Graphics drawHeader(Graphics graphics, double width) {
		SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
		graphics = drawString(graphics, format.format(bill.getDate()), DATE_WIDTH, DATE_HEIGHT);
		Client c = bill.getClient();
		graphics = drawString(graphics, c.getId() + " - " + StringUtils.capitalize(c.getName()), CLIENT_NAME_WIDTH, CLIENT_NAME_HEIGHT);
		graphics = drawString(graphics, bill.getAddress(), ADDRESS_WIDTH, ADDRESS_HEIGHT);
		String iva = bill.getIvaCategory().getLabel() ;
		graphics = drawString(graphics, iva, IVA_CONDITION_WIDTH, IVA_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getSaleCondition().getLabel(), SALE_CONDITION_WIDTH, SALE_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getCuit(), CUIT_WIDTH, CUIT_HEIGHT);
		graphics = drawString(graphics, getOrderNumber(bill.getOrderNumber()), ORDER_WIDTH, ORDER_HEIGHT);
		graphics = drawString(graphics, getOrderNumber(bill.getOrderNumber()), REMITO_WIDTH, REMITO_HEIGHT);

		return graphics;
	}
	private void drawBody(Graphics graphics, double width) {
		String[] array = null;
		String concept = null;
        String code;
		String str = null;
		FontMetrics fm = graphics.getFontMetrics();
		double offset = 0;
		int rowHeight = 0;
		int codeWidth = 0;

		for (BillItem bi : bill.getItems()) {
			concept = bi.getProductDescription();

            code = bi.getProductFamily().getFabric().getCode();
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_CODE_WIDTH));
			rowHeight = (int) (Math.round(fromCMToPPI(BASE_ROW_HEIGHT)) + offset);
			graphics.drawString(code, codeWidth, rowHeight);

			str = String.format(new Locale("es", "ES"), "%1$,.2f Kg", bi.getAmount());
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_AMOUNT_WIDTH));
			graphics.drawString(str, codeWidth, rowHeight);

			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_DETAIL_WIDTH));
            concept = StringUtils.capitalize(concept.toLowerCase());
			graphics.drawString(concept, codeWidth, rowHeight);

			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_PRICE_WIDTH));
			graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f $", bi.getPrice()), codeWidth, rowHeight);

			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_VALUE_WIDTH));
			graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f $", bi.getSubtotal()), codeWidth, rowHeight);
			offset = offset + fm.getAscent() *1.5;
		}
		Font font = new Font("Arial", Font.PLAIN, 8);
		Font oldFont = graphics.getFont();
		graphics.setFont(font);
		
		int x = (int) Math.round(fromCMToPPI(DISCHARGE_WIDTH));
		int y = (int) Math.round(fromCMToPPI(DISCHARGE_HEIGHT));
		int length = (int) Math.round(fromCMToPPI(DISCHARGE_LENGTH));
		drawLongString(graphics, DISCHARGE, x, y, length);
		graphics.setFont(oldFont);

	}
	private Graphics drawFooter(Graphics graphics, double width) {

		graphics = drawString(graphics, String.format(new Locale("es", "ES"), "%1$,.2f $", bill.getSubTotal()), SUBTOTAL_WIDTH, SUBTOTAL_HEIGHT);
		graphics = drawString(graphics, String.format(new Locale("es", "ES"), "%1$,.2f $", DESCUENTO), DESCUENTO_WIDTH, DESCUENTO_HEIGHT);
		graphics = drawString(graphics, String.format(new Locale("es", "ES"), "%1$,.2f $", bill.getSubTotal()), SUBTOTAL2_WIDTH, SUBTOTAL2_HEIGHT);
		graphics = drawString(graphics, IVA_INSC_PORCENTAGE, IVA_INSC_PORCENTAGE_WIDTH, IVA_INSC_PORCENTAGE_HEIGHT);
		graphics = drawString(graphics, String.format(new Locale("es", "ES"), "%1$,.2f $", bill.getIvaTax()), IVA_INSC_VALUE_WIDTH, IVA_INSC_VALUE_HEIGHT);

		graphics = drawString(graphics, String.format(new Locale("es", "ES"), "%1$,.2f $", bill.getTotal()), TOTAL_WIDTH, TOTAL_HEIGHT);

		int x = (int) Math.round(fromCMToPPI(STRING_VALUE_WIDTH));
		int y = (int) Math.round(fromCMToPPI(STRING_VALUE_HEIGHT));
		int length = (int) Math.round(fromCMToPPI(STRING_VALUE_LENGTH));
		drawLongString(graphics, BillingPrinter.numberToWords(bill.getTotal()), x, y, length);

		return graphics;
	}

	
	private Graphics drawHeaderRemito(Graphics graphics, double width) {
		SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
		graphics = drawString(graphics, format.format(bill.getDate()), DATE_WIDTH, DATE_HEIGHT);
		Client c = bill.getClient();
		graphics = drawString(graphics, c.getId() + " - " + StringUtils.capitalize(c.getName()), CLIENT_NAME_WIDTH, CLIENT_NAME_HEIGHT);
		graphics = drawString(graphics, bill.getAddress(), ADDRESS_WIDTH, ADDRESS_HEIGHT);
		String iva = bill.getIvaCategory().getLabel();
		graphics = drawString(graphics, iva, IVA_CONDITION_WIDTH, IVA_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getSaleCondition().getLabel(), SALE_CONDITION_WIDTH, SALE_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getCuit(), CUIT_WIDTH_REMITO, CUIT_HEIGHT_REMITO);
		graphics = drawString(graphics, getOrderNumber(bill.getOrderNumber()), ORDER_WIDTH_REMITO, ORDER_HEIGHT_REMITO);

		return graphics;
	}
	
	private void drawBodyRemito(Graphics graphics, double width) {
		String[] array = null;
		String code = null;
		String str = null;
		FontMetrics fm = graphics.getFontMetrics();
		double offset = 0;
		int rowHeight = 0;
		int codeWidth = 0;

		for (BillItem bi : bill.getItems()) {



            code = bi.getProductFamily().getFabric().getCode();
            str = String.format(new Locale("es", "ES"), "%1$,.2f Kg", bi.getAmount());

			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_CODE_WIDTH));
			rowHeight = (int) (Math.round(fromCMToPPI(BASE_ROW_HEIGHT)) + offset);
			graphics.drawString(code, codeWidth, rowHeight);

			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_AMOUNT_WIDTH));
			graphics.drawString(str, codeWidth, rowHeight);

			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_DETAIL_WIDTH));
			graphics.drawString(StringUtils.capitalize(bi.getProductDescription()), codeWidth, rowHeight);

			offset = offset + fm.getAscent() *1.5;
		}
		
		Font font = new Font("Arial", Font.PLAIN, 8);
		Font oldFont = graphics.getFont();
		graphics.setFont(font);
		
		int x = (int) Math.round(fromCMToPPI(DISCHARGE_WIDTH));
		int y = (int) Math.round(fromCMToPPI(DISCHARGE_HEIGHT));
		int length = (int) Math.round(fromCMToPPI(DISCHARGE_LENGTH));
		drawLongString(graphics, DISCHARGE, x, y, length);
		graphics.setFont(oldFont);
	}

	private void drawFooterRemito(Graphics g2d, double width) {
		String string = "Cantidad de items: " + bill.getPackages();
		g2d = drawString(g2d, string, REMITO_ITEMS_WIDTH, REMITO_ITEMS_HEIGHT);
	}
	

	private String getOrderNumber(Long number) {
		String result = String.valueOf(number);
		for (int i = result.length(); i < 8; i++) {
			result = "0" + result;
		}
		return result;
	}

	private Graphics drawString(Graphics g, String str, Double x, Double y) {
		g.drawString(str, (int) Math.round(fromCMToPPI(x)), (int) Math.round(fromCMToPPI(y)));
		return g;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
