package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.QKaryawan;

public class KaryawanPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QKaryawan.karyawan.nama.containsIgnoreCase(cari)
					.or(QKaryawan.karyawan.spesialis.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.sip.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.alamat.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.userInput.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.userEditor.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.jabatan.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QKaryawan.karyawan.nama.containsIgnoreCase(cari)
					.or(QKaryawan.karyawan.spesialis.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.sip.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.alamat.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.userInput.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.userEditor.containsIgnoreCase(cari))
					.or(QKaryawan.karyawan.jabatan.containsIgnoreCase(cari)));
		}
	}
	
	public void jabatan(String jabatan){
		if (hasil == null){
			hasil = QKaryawan.karyawan.jabatan.containsIgnoreCase(jabatan);
		} else {
			hasil = hasil.and(QKaryawan.karyawan.jabatan.containsIgnoreCase(jabatan));
		}
	}

	public void nama(String nama) {
		if (hasil == null){
			hasil = QKaryawan.karyawan.nama.containsIgnoreCase(nama);
		} else {
			hasil = hasil.and(QKaryawan.karyawan.nama.containsIgnoreCase(nama));
		}		
	}

}
