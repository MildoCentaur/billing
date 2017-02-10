package ar.com.adriabe.utilities;

import javax.print.DocFlavor;
import javax.print.attribute.Attribute;
import javax.print.attribute.PrintRequestAttributeSet;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

public abstract class DocumentPrintable extends CustomPrintable implements Printable {

	@Override
	public String getDocuemntType() {
		return "printer";
	}

	@Override
	public Boolean isDocuementReady() {
		// TODO Auto-generated method stub
		return null;
	}

	public double fromCMToPPI(double cm) {
		return toPPI(cm * 0.393700787);
	}

	public double toPPI(double inch) {
		return inch * 72d;
	}

	public boolean validateAttribute(PrinterJob printJob, Attribute att, PrintRequestAttributeSet attributeSet) {
		return printJob.getPrintService().isAttributeValueSupported(att, DocFlavor.SERVICE_FORMATTED.PAGEABLE, attributeSet);
	}

	public abstract PageFormat getPageFormat(PrinterJob printJob);

	public abstract PrintRequestAttributeSet getPrintRequestAttributeSet(PrinterJob printJob);

	public void drawLongString(Graphics g, String s, int x, int y, int width) {
		// FontMetrics gives us information about the width,
		// height, etc. of the current Graphics object's Font.
		FontMetrics fm = g.getFontMetrics();

		int lineHeight = fm.getHeight();

		int curX = x;
		int curY = y;

		String[] words = s.split(" ");

		for (String word : words) {
			// Find out thw width of the word.

			int wordWidth = fm.stringWidth(word + " ");

			// If text exceeds the width, then move to next line.
			if (curX + wordWidth >= x + width) {
				curY += lineHeight;
				curX = x;
			}

			g.drawString(word + " ", curX, curY);

			// Move over to the right for next word.
			curX += wordWidth;
		}
	}
	public void drawStringInTheCenter(Graphics graphics, String str, double width, double x, double y) {
		FontMetrics metrics= graphics.getFontMetrics();
		int length = metrics.stringWidth(str);
		int posx = (int) x + (int)((fromCMToPPI(width) - length)/2);
		int posy = (int) y ;
		graphics.drawString(str, posx, posy);
	}
	public void drawStringAlignRight(Graphics graphics, String str, double width, double x, double y) {
		FontMetrics metrics= graphics.getFontMetrics();
		int length = metrics.stringWidth(str);
		int posx = (int) x + (int)((fromCMToPPI(width) - length));
		int posy = (int) y ;
		graphics.drawString(str, posx, posy);
	}
	// print header -- deberia ser el mismo
	// print body -- deberia ser distinto
}
