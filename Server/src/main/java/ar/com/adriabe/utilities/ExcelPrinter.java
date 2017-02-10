package ar.com.adriabe.utilities;


import ar.com.adriabe.model.constant.EurekaConstants;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;


public class ExcelPrinter implements CustomPrinter {
	
	@Override
	public void print(CustomPrintable doc) throws Exception {
		ExcelPrintable data;
		if (doc instanceof ExcelPrintable) {
			data = (ExcelPrintable) doc;
		} else {
			throw new Exception("Tipo de archivo invalido");
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(data.getTitle());
		
		Set<Integer> rows = data.getRows();
		Row row;
		int cellnum = 0;
		List<Object> rowData;
		SimpleDateFormat format = new SimpleDateFormat(
				EurekaConstants.PATTERN_READABLE_SHORT);
		String aux = null;
		for (Integer rowNumber : rows) {
			row = sheet.createRow(rowNumber);
			rowData = data.getRow(rowNumber);
			cellnum = 0;
			for (Object obj : rowData) {
				Cell cell = row.createCell(cellnum);
				if(obj==null) {
					continue;
				}
				if(obj instanceof Date) 
					cell.setCellValue(format.format((Date) obj));
		        else if(obj instanceof Boolean)
					cell.setCellValue(String.valueOf((Boolean) obj));
		        else if(obj instanceof String)
		            cell.setCellValue((String)obj);
		        else if(obj instanceof Double) {
		        	aux = String.format(new Locale("es", "ES"), "%1$,.2f", (Double) obj);
					cell.setCellValue(aux);
		        }else{
		        	cell.setCellValue(String.valueOf(obj));
				}
				cellnum++;
		    }
		}
		 
		try {
			String fileNameAndPath = EurekaConstants.FILE_PATH + "/" + data.getFilepath() + ".xls";  
			File file = new File(fileNameAndPath);
			FileOutputStream out = new FileOutputStream(file);
			
		    workbook.write(out);
		    out.close();
		     
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	public String getPrinterType() {
		return "Excel";
	}

}
