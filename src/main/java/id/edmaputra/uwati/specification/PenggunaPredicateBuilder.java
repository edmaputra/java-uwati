package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pengguna.QPengguna;

public class PenggunaPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void aktif(Boolean isAktif){
		if (hasil == null){
			hasil = QPengguna.pengguna.isAktif.eq(isAktif);
		} else {
			hasil = hasil.and(QPengguna.pengguna.isAktif.eq(isAktif));
		}
	}
	
	public void pertamaKali(Boolean pertamaKali){
		if (hasil == null){
			hasil = QPengguna.pengguna.isPertamaKali.eq(pertamaKali);
		} else {
			hasil = hasil.and(QPengguna.pengguna.isPertamaKali.eq(pertamaKali));
		}		
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QPengguna.pengguna.nama.containsIgnoreCase(cari)					
					.or(QPengguna.pengguna.userEditor.containsIgnoreCase(cari))
					.or(QPengguna.pengguna.userInput.containsIgnoreCase(cari))
					.or(QPengguna.pengguna.karyawan.nama.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QPengguna.pengguna.nama.containsIgnoreCase(cari)					
					.or(QPengguna.pengguna.userEditor.containsIgnoreCase(cari))
					.or(QPengguna.pengguna.userInput.containsIgnoreCase(cari))
					.or(QPengguna.pengguna.karyawan.nama.containsIgnoreCase(cari)));
		}
	}
	
	public void cariRole(String nama){
		QPengguna.pengguna.roles.size();
		if (hasil == null){
		} else {
		}
	}

}
