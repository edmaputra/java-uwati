package id.edmaputra.uwati.support;

import java.util.Calendar;
import java.util.Date;

public class TanggalSupport {
	public static Date buatHariPertamaDariTahun(String tahun) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(tahun));
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	public static Date buatHariTerakhirDariTahun(String tahun) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(tahun));
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}
}
