package id.edmaputra.uwati.controller.laporan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.support.Converter;

public class LaporanPenjualanExcelReportView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment;filename=\"laporan.xls\"");
		List<Penjualan> penjualans = (List<Penjualan>) model.get("penjualans");
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Penjualan");		
		Row title = sheet.createRow(0);
		title.createCell(0).setCellValue("LAPORAN PENJUALAN");
		title.getCell(0).setCellStyle(ExcelCellStyleGenerator.boldWeightFontStyleCell(workbook, HSSFFont.BOLDWEIGHT_BOLD));
		
		Row tanggal = sheet.createRow(1);
		String from = Converter.dateToString(penjualans.get(0).getWaktuTransaksi());
		String to = Converter.dateToString(penjualans.get(penjualans.size() - 1).getWaktuTransaksi());
		tanggal.createCell(0).setCellValue("TANGGAL : "+from+" - "+to);
		tanggal.getCell(0).setCellStyle(ExcelCellStyleGenerator.boldWeightFontStyleCell(workbook, HSSFFont.BOLDWEIGHT_BOLD));

		BigDecimal grandTotal = BigDecimal.ZERO;
		Integer jumlahPenjualan = 0;
		
		int rowNumber = 3;
		for (Penjualan penjualan : penjualans) {
			Row header = sheet.createRow(rowNumber++);			
			header.createCell(0).setCellValue("NOMOR FAKTUR");
//			header.getCell(0).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			header.createCell(1).setCellValue("TANGGAL TRANSAKSI");
//			header.getCell(1).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			header.createCell(2).setCellValue("KASIR");
//			header.getCell(2).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			header.createCell(3).setCellValue("PELANGGAN");
//			header.getCell(3).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			header.createCell(4).setCellValue("DOKTER");
//			header.getCell(4).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));

			Row row = sheet.createRow(rowNumber++);
			row.createCell(0).setCellValue(penjualan.getNomorFaktur());
//			row.getCell(0).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
			row.createCell(1).setCellValue(Converter.dateToString(penjualan.getWaktuTransaksi()));
//			row.getCell(1).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
			row.createCell(2).setCellValue(penjualan.getPengguna());
//			row.getCell(2).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
			row.createCell(3).setCellValue(penjualan.getPelanggan());
//			row.getCell(3).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
			row.createCell(4).setCellValue(penjualan.getDokter());
//			row.getCell(4).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
			
			Row rowDetailHeader = sheet.createRow(rowNumber++);
			rowDetailHeader.createCell(0).setCellValue("OBAT");
//			rowDetailHeader.getCell(0).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			rowDetailHeader.createCell(1).setCellValue("HARGA JUAL");
//			rowDetailHeader.getCell(1).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			rowDetailHeader.createCell(2).setCellValue("JUMLAH");
//			rowDetailHeader.getCell(2).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			rowDetailHeader.createCell(3).setCellValue("TOTAL");
//			rowDetailHeader.getCell(3).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			rowDetailHeader.createCell(4).setCellValue("DISKON");
//			rowDetailHeader.getCell(4).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			rowDetailHeader.createCell(5).setCellValue("TOTAL-DISKON");
//			rowDetailHeader.getCell(5).setCellStyle(ExcelCellStyleGenerator.headerStyleCell(workbook));
			
			List<PenjualanDetail> penjualanDetails = penjualan.getPenjualanDetail();
			for (PenjualanDetail penjualanDetail : penjualanDetails) {
				Row rowDetail = sheet.createRow(rowNumber++);
				rowDetail.createCell(0).setCellValue(penjualanDetail.getObat());
//				rowDetail.getCell(0).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
				rowDetail.createCell(1).setCellValue(penjualanDetail.getHargaJual().doubleValue());
//				rowDetail.getCell(1).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
				rowDetail.createCell(2).setCellValue(penjualanDetail.getJumlah());
//				rowDetail.getCell(2).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
				rowDetail.createCell(3).setCellValue(penjualanDetail.getHargaJual().doubleValue());
//				rowDetail.getCell(3).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
				rowDetail.createCell(4).setCellValue(penjualanDetail.getDiskon().doubleValue());
//				rowDetail.getCell(4).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
				rowDetail.createCell(5).setCellValue(penjualanDetail.getHargaTotal().doubleValue());
//				rowDetail.getCell(5).setCellStyle(ExcelCellStyleGenerator.rowStyleCell(workbook));
			}
			Row rowSummary1 = sheet.createRow(rowNumber++);
			rowSummary1.createCell(0).setCellValue("TOTAL");
//			rowSummary1.getCell(0).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary1.createCell(1).setCellValue(penjualan.getTotalPembelian().doubleValue());
//			rowSummary1.getCell(1).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary1.createCell(2).setCellValue("GRAND TOTAL");
//			rowSummary1.getCell(2).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary1.createCell(3).setCellValue(penjualan.getGrandTotal().doubleValue());
//			rowSummary1.getCell(3).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));

			Row rowSummary2 = sheet.createRow(rowNumber++);
			rowSummary2.createCell(0).setCellValue("DISKON");
//			rowSummary2.getCell(0).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary2.createCell(1).setCellValue(penjualan.getDiskon().doubleValue());
//			rowSummary2.getCell(1).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary2.createCell(2).setCellValue("PEMBAYARAN");
//			rowSummary2.getCell(2).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary2.createCell(3).setCellValue(penjualan.getBayar().doubleValue());
//			rowSummary2.getCell(3).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));

			Row rowSummary3 = sheet.createRow(rowNumber++);
			rowSummary3.createCell(0).setCellValue("PAJAK");
//			rowSummary3.getCell(0).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary3.createCell(1).setCellValue(penjualan.getPajak().doubleValue());
//			rowSummary3.getCell(1).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary3.createCell(2).setCellValue("KEMBALIAN");
//			rowSummary3.getCell(2).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));
			rowSummary3.createCell(3).setCellValue(penjualan.getKembali().doubleValue());
//			rowSummary3.getCell(3).setCellStyle(ExcelCellStyleGenerator.summaryStyleCell(workbook));

			Row rowBlank = sheet.createRow(rowNumber++);
			rowBlank.createCell(0).setCellValue("");
			
			grandTotal = grandTotal.add(penjualan.getGrandTotal());
			jumlahPenjualan++;
		}
		
		Row rowSummary = sheet.createRow(rowNumber++);
		rowSummary.createCell(0).setCellValue("Jumlah Penjualan");
		rowSummary.createCell(1).setCellValue(jumlahPenjualan);
		rowSummary.createCell(2).setCellValue("Total (RP.)");
		rowSummary.createCell(3).setCellValue(grandTotal.doubleValue());
		
	}

	
}
