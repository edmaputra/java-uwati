package id.edmaputra.uwati.support;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converter {

	public static Date stringToDate(String tgl) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			if (tgl.equals("") || tgl.equals(null)) {
				date = new Date();
			}
			date = formatter.parse(tgl);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date stringToDateSlashSeparator(String tgl) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			if (tgl.equals("") || tgl.equals(null)) {
				date = new Date();
			}
			date = formatter.parse(tgl);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int stringtoInt(String nominal) {
		int returnValue = 0;
		if (nominal.equals("") || nominal.equals("0") || nominal == ""
				|| nominal.equals(null)) {
			nominal = "0";
		}
		nominal = nominal.replaceAll("\\D+", "");
		returnValue = Integer.valueOf(nominal).intValue();
		return returnValue;
	}

	public static BigDecimal stringToBigDecimal(String nominal) {
		BigDecimal returnValue = new BigDecimal(nominal);
		return returnValue;
	}
	
	public static String thousandSeparator(String angka){		
		String str = String.format("%.d", angka);
		return str;
	}
	
	public static String dateToString(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
		return df.format(date);		
	}
	
	public static String dateToStringDashSeparator(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");		
		return df.format(date);		
	}
	
	public static String patternCurrency(BigDecimal angka) {
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols dfs = formatter.getDecimalFormatSymbols();
		dfs.setGroupingSeparator('.');
		dfs.setDecimalSeparator(',');
		formatter.setDecimalFormatSymbols(dfs);
		return formatter.format(angka.doubleValue());
	}
	
	public static String patternCurrency(Integer angka) {
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols dfs = formatter.getDecimalFormatSymbols();
		dfs.setGroupingSeparator('.');
		dfs.setDecimalSeparator(',');
		formatter.setDecimalFormatSymbols(dfs);
		return formatter.format(angka.doubleValue());
	}

	// 0: BeritaAcaraAtm
//	public static String nomorSuratGenerator(String nomor, Date date, int tipe, String kodeCabang) {
//		String nomorSurat = null;
//		nomorSurat = nomor;
//		String tipeSurat = "";
//		if (tipe == 0){
//			tipeSurat = "BA-ATM";
//		} else if (tipe == 1){
//			tipeSurat = "BAST";
//		}
//		nomorSurat = nomorSurat + "/" + tipeSurat;
//		String inisialCabang = inisialKodeCabang(kodeCabang);
//		nomorSurat = nomorSurat + "/" + inisialCabang;
//		String tanggalDanTahun = dateToRomanAndYear(date);
//		nomorSurat = nomorSurat + "/" + tanggalDanTahun;
//		return nomorSurat;
//	}
	
	public static String inisialKodeCabang(String kodeCabang){
		String inisialCabang = null;
		if (kodeCabang.equals("07")){
			inisialCabang = "BPD-TJS";
		}
		return inisialCabang;
	}

//	public static String dateToRomanAndYear(Date date) {
//		String romanAndYear = null;
//		String bulan = new SimpleDateFormat("MM", AppConfig.locale)
//				.format(date);
//		if (bulan.equals("01")) {
//			romanAndYear = "I";
//		} else if (bulan.equals("02")) {
//			romanAndYear = "II";
//		} else if (bulan.equals("03")) {
//			romanAndYear = "III";
//		} else if (bulan.equals("04")) {
//			romanAndYear = "IV";
//		} else if (bulan.equals("05")) {
//			romanAndYear = "V";
//		} else if (bulan.equals("06")) {
//			romanAndYear = "VI";
//		} else if (bulan.equals("07")) {
//			romanAndYear = "VII";
//		} else if (bulan.equals("08")) {
//			romanAndYear = "VIII";
//		} else if (bulan.equals("09")) {
//			romanAndYear = "IX";
//		} else if (bulan.equals("10")) {
//			romanAndYear = "X";
//		} else if (bulan.equals("11")) {
//			romanAndYear = "XI";
//		} else if (bulan.equals("12")) {
//			romanAndYear = "XII";
//		}
//		String tahun = new SimpleDateFormat("yyyy", AppConfig.locale)
//				.format(date);
//		romanAndYear = romanAndYear + "/" + tahun;
//		return romanAndYear;
//	}
//	
	public static String dateToStringIndonesianLocale(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy", new Locale("id"));
		String returnValue = sdf.format(date);
		return returnValue;
	}
	
	public static String hilangkanTandaTitikKoma(String s){
		if (s == null) {
			return null;
		}
		return s.replaceAll("[.,]", "");
	}
}
