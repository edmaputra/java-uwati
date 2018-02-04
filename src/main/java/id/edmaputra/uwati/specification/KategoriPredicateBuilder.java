package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.QKategori;

public class KategoriPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QKategori.kategori.nama.containsIgnoreCase(cari)
					.or(QKategori.kategori.userEditor.containsIgnoreCase(cari))
					.or(QKategori.kategori.userInput.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QKategori.kategori.nama.containsIgnoreCase(cari)
					.or(QKategori.kategori.userEditor.containsIgnoreCase(cari))
					.or(QKategori.kategori.userInput.containsIgnoreCase(cari)));
		}
	}

}
