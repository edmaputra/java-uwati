package id.edmaputra.uwati.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class Formatter {

	public static String formatTanggal(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return sdf.format(date);
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
	
	public static String hilangkanTitik(String angka){
		return angka.replaceAll("[.]", "");
	}
	
	public static String hilangkanTitikKoma(String angka){
		return angka.replaceAll("[.,]", "");
	}
	
	public static String hitungUsia(Date tanggalLahir, Date hariIni){
		LocalDate today = new LocalDate(hariIni);
		LocalDate birth = new LocalDate(tanggalLahir);
		Period period = new Period(birth, today, PeriodType.yearMonthDay());
		String usiaTahun = period.getYears()+" th ";
		String usiaBulan = period.getMonths()+" bl";	    
		return usiaTahun + usiaBulan;
	}

}
