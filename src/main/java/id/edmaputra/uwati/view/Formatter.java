package id.edmaputra.uwati.view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {

	public static String formatTanggal(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return sdf.format(date);
	}

}
