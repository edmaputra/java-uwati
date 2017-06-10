package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.QPasien;

public class PasienPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QPasien.pasien.nama.containsIgnoreCase(cari)
					.or(QPasien.pasien.identitas.containsIgnoreCase(cari))
					.or(QPasien.pasien.alamat.containsIgnoreCase(cari))
					.or(QPasien.pasien.kontak.containsIgnoreCase(cari))
					.or(QPasien.pasien.jaminanKesehatan.containsIgnoreCase(cari))
					.or(QPasien.pasien.nomorJaminan.containsIgnoreCase(cari))
					.or(QPasien.pasien.pekerjaan.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QPasien.pasien.nama.containsIgnoreCase(cari)
					.or(QPasien.pasien.identitas.containsIgnoreCase(cari))
					.or(QPasien.pasien.alamat.containsIgnoreCase(cari))
					.or(QPasien.pasien.kontak.containsIgnoreCase(cari))
					.or(QPasien.pasien.jaminanKesehatan.containsIgnoreCase(cari))
					.or(QPasien.pasien.nomorJaminan.containsIgnoreCase(cari))
					.or(QPasien.pasien.pekerjaan.containsIgnoreCase(cari)));
		}
	}

}
