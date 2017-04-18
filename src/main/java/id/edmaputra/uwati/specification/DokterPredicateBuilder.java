package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.QDokter;

public class DokterPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QDokter.dokter.nama.containsIgnoreCase(cari)
					.or(QDokter.dokter.spesialis.containsIgnoreCase(cari))
					.or(QDokter.dokter.sip.containsIgnoreCase(cari))
					.or(QDokter.dokter.alamat.containsIgnoreCase(cari))
					.or(QDokter.dokter.userInput.containsIgnoreCase(cari))
					.or(QDokter.dokter.userEditor.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QDokter.dokter.nama.containsIgnoreCase(cari)
					.or(QDokter.dokter.spesialis.containsIgnoreCase(cari))
					.or(QDokter.dokter.sip.containsIgnoreCase(cari))
					.or(QDokter.dokter.alamat.containsIgnoreCase(cari))
					.or(QDokter.dokter.userInput.containsIgnoreCase(cari))
					.or(QDokter.dokter.userEditor.containsIgnoreCase(cari)));
		}
	}

}
