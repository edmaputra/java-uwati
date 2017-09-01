package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.QKategoriPasien;

public class KategoriPasienPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QKategoriPasien.kategoriPasien.nama.containsIgnoreCase(cari)
					.or(QKategoriPasien.kategoriPasien.userEditor.containsIgnoreCase(cari))
					.or(QKategoriPasien.kategoriPasien.userInput.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QKategoriPasien.kategoriPasien.nama.containsIgnoreCase(cari)
					.or(QKategoriPasien.kategoriPasien.userEditor.containsIgnoreCase(cari))
					.or(QKategoriPasien.kategoriPasien.userInput.containsIgnoreCase(cari)));
		}
	}

}
