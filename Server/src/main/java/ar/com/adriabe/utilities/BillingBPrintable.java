package ar.com.adriabe.utilities;

import ar.com.adriabe.model.Bill;
import ar.com.adriabe.model.BillItem;
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
import java.util.Locale;

public class BillingBPrintable extends DocumentPrintable {
	private static final Double MARGIN_TOP = 1.1;
	private static final Double MARGIN_LEFT = 1.1;
	private static final Double MARGIN_BOTTOM = 1.1;
	private static final Double MARGIN_RIGHT = 1.1;
	
	private static final Double PAPER_HEIGHT = MARGIN_TOP + 19.0 + MARGIN_BOTTOM;
	private static final Double PAPER_WIDTH = MARGIN_LEFT + 14.7 + MARGIN_RIGHT;
	
	private static final Double DATE_HEIGHT = MARGIN_TOP + 2.3;
	private static final Double DATE_WIDTH = MARGIN_LEFT + 10.5;
	private static final Double CLIENT_NAME_WIDTH = MARGIN_LEFT + 2.0;
	private static final Double CLIENT_NAME_HEIGHT = MARGIN_TOP +4.6;
	private static final Double ADDRESS_WIDTH = MARGIN_LEFT + 1.8;
	private static final Double ADDRESS_HEIGHT = MARGIN_TOP + 5.5;
	private static final Double IVA_CONDITION_WIDTH = MARGIN_LEFT + 1.5;
	private static final Double IVA_CONDITION_HEIGHT = MARGIN_TOP + 6.2;
	private static final Double SALE_CONDITION_WIDTH = MARGIN_LEFT + 3.5;
	private static final Double SALE_CONDITION_HEIGHT = MARGIN_TOP + 7.3;
	private static final Double CUIT_WIDTH = MARGIN_LEFT + 10.3;
	private static final Double CUIT_HEIGHT = MARGIN_TOP + 6.3;
	private static final Double REMITO_WIDTH = MARGIN_LEFT + 11.0;
	private static final Double REMITO_HEIGHT = MARGIN_TOP + 7.3;
	
	
	private static final Double TOTAL_WIDTH = MARGIN_LEFT + 12.0;
	private static final Double TOTAL_HEIGHT = MARGIN_TOP + 18.0;
	private static final Double TOTAL_HEIGHT_REMITO = MARGIN_TOP + 16.9;
	
	private static final Double COLUMN_CODE_WIDTH = MARGIN_LEFT + 0.5;
	private static final Double COLUMN_AMOUNT_WIDTH = MARGIN_LEFT + 2.0;
	private static final Double COLUMN_DETAIL_WIDTH = MARGIN_LEFT + 3.8;
	private static final Double COLUMN_PRICE_WIDTH = MARGIN_LEFT + 10.0;
	private static final Double COLUMN_VALUE_WIDTH = MARGIN_LEFT + 12.0;
	private static final Double BASE_ROW_HEIGHT = MARGIN_TOP + 8.8;

    private static final String DISCHARGE = "No se admiten reclamos pasadas las 48hs, ní se aceptan devoluciones sobre la mercadería fraccionada o cortada. La mercadería viaja por cuenta y riesgo del comprador."
            + "Esta factura debera abonarse en la calle 10 Nro 4038 de Villa Lynch - Prov. Bs. As. La mora en en su pago se producirá en forma automatica y desde su vencimiento devengará "
            + "un interes mensual del    % mensual o la actualización monetaria según indice precios mayoristas nivel gral INDEC más un interes del    % anual, a opcion del acreedor."
            + "El cobro de la factura no importará renuncia al reclamo de actualización monetaria o intereses, desde la mora hasta el día del efectivo pago. "
            + "A los efectos judiciales de la presente se pacta la juridicción de los tribunales Ordinarios de la Cap. Fed. Los pagos deberan ser efectuados con cheques o valores"
            + "sobre Bs. As. a la orden de ADRIABE S.A.C.I.F.I.A. Careceran de valor todos aquellos recibos que no sean emitidos en talonario oficial de la firma.";
	private static final Double DISCHARGE_WIDTH = MARGIN_LEFT + 0.5;
	private static final Double DISCHARGE_HEIGHT =MARGIN_TOP + 13.5;

	private static final Double STRING_VALUE_WIDTH = MARGIN_LEFT + 1.0;
	private static final Double STRING_VALUE_HEIGHT = MARGIN_TOP + 17.3;

	private static final Double REMITO_ITEMS_WIDTH = MARGIN_LEFT + 1.0;
	private static final Double REMITO_ITEMS_HEIGHT = MARGIN_TOP + 16.0;

	private Bill bill;

	public BillingBPrintable(Bill bill) {
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
		PageRanges range = new PageRanges("1-4");
		if (validateAttribute(printJob, range, attributeSet)) {
			attributeSet.add(range);
		}
		// Black and white print
		if (validateAttribute(printJob, Chromaticity.MONOCHROME, attributeSet)) {
			attributeSet.add(Chromaticity.MONOCHROME);
		}

		// Draft print quality
		 if(validateAttribute(printJob, PrintQuality.DRAFT, attributeSet)) {
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
		if (pageIndex < 5) {
			Graphics2D g2d = (Graphics2D) graphics;
			double width = pageFormat.getImageableWidth() - fromCMToPPI(MARGIN_LEFT+MARGIN_RIGHT);
			
			g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
			int x = (int) Math.round(fromCMToPPI(MARGIN_LEFT));
			int y = (int) Math.round(fromCMToPPI(MARGIN_TOP));
			int widthRec = (int) Math.round(fromCMToPPI(PAPER_WIDTH-MARGIN_LEFT-MARGIN_RIGHT));
			int height= (int) Math.round(fromCMToPPI(PAPER_HEIGHT-MARGIN_TOP-MARGIN_BOTTOM));
			g2d.drawRect(x,y,widthRec,height);
					
			Font font = new Font("Arial", Font.PLAIN, 10);
			g2d.setFont(font);
			if (pageIndex < 2) {
				drawHeader(g2d,width);
				drawBody(g2d,width);
				drawFooter(g2d,width);
			} else {
				drawHeaderRemito(g2d,width);
				drawBodyRemito(g2d,width);
				drawFooterRemito(g2d,width);

			}
			result = PAGE_EXISTS;
		}
		return result;
	}

	private Graphics drawHeader(Graphics graphics,double width) {
		SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
		graphics = drawString(graphics, format.format(bill.getDate()), DATE_WIDTH, DATE_HEIGHT);
		String string = bill.getClient().getId() + " - " + bill.getClient().getName();
		graphics = drawString(graphics, string, CLIENT_NAME_WIDTH, CLIENT_NAME_HEIGHT);
		graphics = drawString(graphics, bill.getAddress(), ADDRESS_WIDTH, ADDRESS_HEIGHT);
		String iva = bill.getIvaCategory().getLabel();
		graphics = drawString(graphics, iva, IVA_CONDITION_WIDTH, IVA_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getSaleCondition().getLabel(), SALE_CONDITION_WIDTH, SALE_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getCuit(), CUIT_WIDTH, CUIT_HEIGHT);
		graphics = drawString(graphics, getOrderNumber(bill.getOrderNumber()), REMITO_WIDTH, REMITO_HEIGHT);
		return graphics;
	}

	private void drawBody(Graphics graphics,double width) {
		String[] array = null;
		String string = null;
		FontMetrics fm = graphics.getFontMetrics();
		double offset = 0;
		int rowHeight = 0;
		int codeWidth = 0;

		for (BillItem bi : bill.getItems()) {



			string = StringUtils.capitalize(bi.getProductDescription());
			
			rowHeight = (int) (Math.round(fromCMToPPI(BASE_ROW_HEIGHT)) + offset);
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_CODE_WIDTH));
			graphics.drawString(bi.getProductFamily().getFabric().getCode(), codeWidth, rowHeight);
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_AMOUNT_WIDTH));
			graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f Kg", bi.getAmount()), codeWidth, rowHeight);
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_DETAIL_WIDTH));
			graphics.drawString(string, codeWidth, rowHeight);
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_PRICE_WIDTH));
			graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f $", bi.getPrice()), codeWidth, rowHeight);
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_VALUE_WIDTH));
			graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f $", bi.getSubtotal()), codeWidth, rowHeight);
			
			offset = offset + fm.getAscent()*1.5;
		}

		Font font = new Font("Arial", Font.PLAIN, 8);
		Font oldFont = graphics.getFont();
		graphics.setFont(font);
		
		int x = (int) Math.round(fromCMToPPI(DISCHARGE_WIDTH));
		int y = (int) Math.round(fromCMToPPI(DISCHARGE_HEIGHT));
		int length = (int) Math.round(fromCMToPPI(PAPER_WIDTH-MARGIN_LEFT-MARGIN_RIGHT-0.1));
		drawLongString(graphics, DISCHARGE, x, y, length);
		graphics.setFont(oldFont);
	}

	

	private Graphics drawFooter(Graphics graphics,double width) {
		
		graphics = drawString(graphics, String.format(new Locale("es", "ES"), "%1$,.2f $", bill.getTotal()), TOTAL_WIDTH, TOTAL_HEIGHT);
		
		int x = (int) Math.round(fromCMToPPI(STRING_VALUE_WIDTH));
		int y = (int) Math.round(fromCMToPPI(STRING_VALUE_HEIGHT));
		int length = (int) Math.round(fromCMToPPI(PAPER_WIDTH-MARGIN_LEFT-MARGIN_RIGHT));
		drawLongString(graphics, BillingPrinter.numberToWords(bill.getTotal()),
				x, y, length);
		
		return graphics;
	}

	private Graphics drawHeaderRemito(Graphics graphics,double width) {
		SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
		graphics = drawString(graphics, format.format(bill.getDate()), DATE_WIDTH, DATE_HEIGHT);
		String string = bill.getClient().getId() + " - " + bill.getClient().getName();
		graphics = drawString(graphics, string, CLIENT_NAME_WIDTH, CLIENT_NAME_HEIGHT);
		graphics = drawString(graphics, bill.getAddress(), ADDRESS_WIDTH, ADDRESS_HEIGHT);
		String iva = bill.getIvaCategory().getLabel();
		graphics = drawString(graphics, iva, IVA_CONDITION_WIDTH, IVA_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getSaleCondition().getLabel(), SALE_CONDITION_WIDTH, SALE_CONDITION_HEIGHT);
		graphics = drawString(graphics, bill.getCuit(), CUIT_WIDTH, CUIT_HEIGHT);
		
		return graphics;
	}
	private void drawBodyRemito(Graphics graphics,double width) {
		String string = null;
		FontMetrics fm = graphics.getFontMetrics();
		double offset = 0;
		int rowHeight = 0;
		int codeWidth = 0;
		String aux;
		for (BillItem bi : bill.getItems()) {

			aux = String.format(new Locale("es", "ES"), "%1$,.2f Kg ", bi.getAmount());
            string = aux+ " " + StringUtils.capitalize(bi.getProductDescription());
			string = string.replace('-', ' ');
			
			rowHeight = (int) (Math.round(fromCMToPPI(BASE_ROW_HEIGHT)) + offset);
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_CODE_WIDTH));
			graphics.drawString(string, codeWidth, rowHeight);
			
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_PRICE_WIDTH));
			graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f $", bi.getPrice()), codeWidth, rowHeight);
			
			codeWidth = (int) Math.round(fromCMToPPI(COLUMN_VALUE_WIDTH));
			graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f $", bi.getSubtotal()), codeWidth, rowHeight);
			
			offset = offset + fm.getAscent()*1.5;
		}
		Font font = new Font("Arial", Font.PLAIN, 8);
		Font oldFont = graphics.getFont();
		graphics.setFont(font);
		
		int x = (int) Math.round(fromCMToPPI(DISCHARGE_WIDTH));
		int y = (int) Math.round(fromCMToPPI(DISCHARGE_HEIGHT - 2)); // correccion sobre el remito distinto formato de papel
		int length = (int) Math.round(fromCMToPPI(PAPER_WIDTH-MARGIN_LEFT-MARGIN_RIGHT-0.1));
		drawLongString(graphics, DISCHARGE, x, y, length);
		graphics.setFont(oldFont);
	}
	
	private void drawFooterRemito(Graphics g2d, double width) {
		g2d = drawString(g2d, String.format(new Locale("es", "ES"), "%1$,.2f $", bill.getTotal()), TOTAL_WIDTH, TOTAL_HEIGHT_REMITO);
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
