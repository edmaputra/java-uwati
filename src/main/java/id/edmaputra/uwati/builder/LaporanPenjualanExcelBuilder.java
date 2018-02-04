package id.edmaputra.uwati.builder;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.support.Converter;

public class LaporanPenjualanExcelBuilder extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment;filename=\"laporan.xls\"");
		List<Penjualan> penjualanList = (List<Penjualan>) model.get("penjualanList");
		Sheet sheet = workbook.createSheet("Laporan Penjualan");
		sheet.setDefaultColumnWidth(50);

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		font.setBold(true);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Tanggal");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Nomor Faktur");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("Total Pembelian");
		header.getCell(2).setCellStyle(style);
		//
		int rowNum = 1;
		for (Penjualan p : penjualanList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(Converter.dateToString(p.getWaktuTransaksi()));
			row.createCell(1).setCellValue(p.getNomorFaktur());
			row.createCell(2).setCellValue(Converter.patternCurrency(p.getTotalPembelian()));
		}

	}

}
