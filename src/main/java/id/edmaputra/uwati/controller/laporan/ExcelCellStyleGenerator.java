package id.edmaputra.uwati.controller.laporan;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelCellStyleGenerator {
	
	public static HSSFCellStyle headerStyleCell(Workbook workbook) {
		HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setFillForegroundColor(IndexedColors.BLUE.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);		
		cellStyle.setFont(font);
		
		return cellStyle;
	}
	
	public static HSSFCellStyle boldWeightFontStyleCell(Workbook workbook, short weight) {
		HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();	
		
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		
		return cellStyle;
	}
	
	public static HSSFCellStyle rowStyleCell(Workbook workbook) {
		HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderRight((short) 1);
		
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setFontHeightInPoints((short) 10);	
		cellStyle.setFont(font);
		
		return cellStyle;
	}
	
	public static HSSFCellStyle summaryStyleCell(Workbook workbook) {
		HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setFillForegroundColor(IndexedColors.AQUA.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setFontHeightInPoints((short) 10);	
		cellStyle.setFont(font);
		
		return cellStyle;
	}


}
