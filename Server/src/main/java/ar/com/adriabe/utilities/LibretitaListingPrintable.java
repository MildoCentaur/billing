package ar.com.adriabe.utilities;


import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.constant.EurekaConstants;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.PrintQuality;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class LibretitaListingPrintable extends DocumentPrintable {
	protected static final Double MARGIN_TOP = 1.0;
	protected static final Double MARGIN_LEFT = 1.0;
	protected static final Double MARGIN_BOTTOM = 1.0;
	protected static final Double MARGIN_RIGHT = 1.0;
	
	private static final Double PAPER_HEIGHT = 29.7 -MARGIN_TOP - MARGIN_BOTTOM;
	private static final Double PAPER_WIDTH = 21.0 - MARGIN_LEFT -MARGIN_RIGHT;
	
	
	private static final Double INNERMARGIN_COLUMN= 0.5;
	
	private static final Double RECT1_X_COLUMN_NAME = INNERMARGIN_COLUMN;
	private static final Double RECT1_WIDTH_COLUMN_NAME=  4.0;
	private static final Double RECT1_X_COLUMN_AMOUNT = RECT1_X_COLUMN_NAME + RECT1_WIDTH_COLUMN_NAME +INNERMARGIN_COLUMN*3 ;
	private static final Double RECT1_WIDTH_COLUMN_AMOUNT= 2.5;
	private static final Double RECT_WIDTH= RECT1_X_COLUMN_AMOUNT  + RECT1_WIDTH_COLUMN_AMOUNT;
	private static final Double RECT2_X_COLUMN_NAME = RECT_WIDTH + RECT1_X_COLUMN_NAME;
	private static final Double RECT2_X_COLUMN_AMOUNT = RECT_WIDTH + RECT1_X_COLUMN_AMOUNT;
	
	
	///values
	private int RECT2_X_NAME_VALUE = (int) Math.round(fromCMToPPI(RECT2_X_COLUMN_NAME));
	private int RECT2_X_AMOUNT_VALUE = (int) Math.round(fromCMToPPI(RECT2_X_COLUMN_AMOUNT));
	private int RECT1_X_NAME_VALUE = (int) Math.round(fromCMToPPI(RECT1_X_COLUMN_NAME));
	private int RECT1_X_AMOUNT_VALUE = (int) Math.round(fromCMToPPI(RECT1_X_COLUMN_AMOUNT));
	
	
	
	private static final int LINES_RECT = 10;
	
	private List<Client> clients;
	
	public LibretitaListingPrintable(List<Client> clients) {
		this.clients=clients;
		SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
		setTitle("Libretita fecha:" + format.format(new Date()));
	}
	
	@Override
	public PrintRequestAttributeSet getPrintRequestAttributeSet(PrinterJob printJob) {
		PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

		// Name the print job
		JobName jobName = new JobName(getTitle(), null);

		if (validateAttribute(printJob, jobName, attributeSet)) {
			attributeSet.add(jobName);
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
	@Override
	public PageFormat getPageFormat(PrinterJob printJob) {
		PageFormat pf = printJob.defaultPage();
		Paper p = pf.getPaper();
		double width = fromCMToPPI(PAPER_WIDTH);
		double height = fromCMToPPI(PAPER_HEIGHT);

		p.setSize(width+2*fromCMToPPI(MARGIN_LEFT), height+2*fromCMToPPI(MARGIN_TOP));
		p.setImageableArea(fromCMToPPI(MARGIN_LEFT), fromCMToPPI(MARGIN_TOP), width, height);
		pf.setPaper(p);
		pf.setOrientation(PageFormat.PORTRAIT);
		PageFormat pf2 = printJob.validatePage(pf);

		return pf2;
	}
	
	int[] pageBreaks;  // array of page break line positions.
	int[] blocksBreaks;
	int pages=0; 
	int blocksPage=0;
	int heightBlock =0;
	private void initLibretita(Graphics graphics) {
		
		Collections.sort(clients,new Comparator<Client>() {

			@Override
			public int compare(Client c0, Client c1) {
				return c0.getName().compareToIgnoreCase(c1.getName());
			}
		});
		
		FontMetrics fm = graphics.getFontMetrics();
		
		int lines = (clients.size());
		int rects = lines/LINES_RECT +1;
		heightBlock = (int) (fm.getAscent()*1.5*LINES_RECT + fromCMToPPI(1)); // 1cm de margen superior
		int blocks = rects/2 +1;
		blocksPage = (int) (fromCMToPPI(PAPER_HEIGHT)/heightBlock);
		pages =  blocks/blocksPage +1; 
		
		pageBreaks= new int[pages];
		blocksBreaks= new int[blocks];
		for(int i=0;i<blocks;i++) {
			blocksBreaks[i]=i*LINES_RECT*2;
		}
		for(int i=0;i<pages;i++) {
			pageBreaks[i]=i*blocksPage;
		}
	}  

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// header
		
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
		
		
		Font font = new Font("Arial", Font.PLAIN, 10);
		g2d.setFont(font);
		if(pages==0) {
			initLibretita(graphics);
		}
		FontMetrics fm = graphics.getFontMetrics();
		if(pageIndex>=pages) {
			return NO_SUCH_PAGE;
		}
		int acumHeight=5;
		int offset= 0; /// para separ del borde
		int block=pageBreaks[pageIndex];
		int index=0;
		Client c;
		int xRect = (int) Math.round(fromCMToPPI(MARGIN_LEFT));
		int widthRect = (int) Math.round(fromCMToPPI(RECT_WIDTH));
		int widthValue = (int) Math.round(fromCMToPPI(RECT1_WIDTH_COLUMN_AMOUNT));
		String value = null;
		int aux=0;
		for(int i=0;i<blocksPage;i++) {
			if(i+block<blocksBreaks.length) {
				index=blocksBreaks[block+i];
				acumHeight= acumHeight +  offset;
				graphics.drawRect(0, acumHeight, widthRect, heightBlock);
				graphics.drawRect(xRect+widthRect, acumHeight, widthRect, heightBlock);
				acumHeight= acumHeight + (int) Math.round(fromCMToPPI(MARGIN_TOP)) ;
				for(int h=0; h<LINES_RECT;h++) {
					if(h+index<clients.size()) {
						c=clients.get(h+index);
						graphics.drawString(c.getName(), RECT1_X_NAME_VALUE, acumHeight + offset);
						value=String.format(new Locale("es", "ES"), "%1$,.2f $",c.getBalanceActivity());
						if (fm.stringWidth(value) > widthValue) {
							aux= RECT1_X_AMOUNT_VALUE - fm.stringWidth(value) + widthValue;
							graphics.drawString( value, aux, acumHeight + offset);
						}else {	
							graphics.drawString( value, RECT1_X_AMOUNT_VALUE, acumHeight + offset);
						}
						offset=(int) (offset + fm.getAscent()*1.5);
					}
				}
				index=index + LINES_RECT;
				offset=0;
				for(int h=0; h<LINES_RECT;h++) {
					if(h+index<clients.size()) {
						c=clients.get(h+index);
						graphics.drawString(c.getName(), RECT2_X_NAME_VALUE, acumHeight + offset);
						graphics.drawString(String.format(new Locale("es", "ES"), "%1$,.2f $",c.getBalanceActivity()), RECT2_X_AMOUNT_VALUE, acumHeight + offset);
						offset=(int) (offset + fm.getAscent()*1.5);
					}
				}
			}
		}	
		return PAGE_EXISTS;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
