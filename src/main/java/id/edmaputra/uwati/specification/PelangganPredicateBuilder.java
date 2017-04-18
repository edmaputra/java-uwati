package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.QPelanggan;

public class PelangganPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QPelanggan.pelanggan.nama.containsIgnoreCase(cari)
					.or(QPelanggan.pelanggan.kode.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.kontak.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.alamat.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.userInput.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.userEditor.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QPelanggan.pelanggan.nama.containsIgnoreCase(cari)
					.or(QPelanggan.pelanggan.kode.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.kontak.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.alamat.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.userInput.containsIgnoreCase(cari))
					.or(QPelanggan.pelanggan.userEditor.containsIgnoreCase(cari)));
		}
	}

}
