package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.QObat;

public class ObatPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QObat.obat.nama.containsIgnoreCase(cari)
					.or(QObat.obat.kode.containsIgnoreCase(cari))
					.or(QObat.obat.batch.containsIgnoreCase(cari))
					.or(QObat.obat.barcode.containsIgnoreCase(cari))
					.or(QObat.obat.userInput.containsIgnoreCase(cari))
					.or(QObat.obat.userEditor.containsIgnoreCase(cari))
					.or(QObat.obat.satuan.nama.containsIgnoreCase(cari))
					.or(QObat.obat.kategori.nama.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QObat.obat.nama.containsIgnoreCase(cari)
					.or(QObat.obat.kode.containsIgnoreCase(cari))
					.or(QObat.obat.batch.containsIgnoreCase(cari))
					.or(QObat.obat.barcode.containsIgnoreCase(cari))
					.or(QObat.obat.userInput.containsIgnoreCase(cari))
					.or(QObat.obat.userEditor.containsIgnoreCase(cari))
					.or(QObat.obat.satuan.nama.containsIgnoreCase(cari))
					.or(QObat.obat.kategori.nama.containsIgnoreCase(cari)));
		}
	}
	
	public void nama(String nama){
		hasil = QObat.obat.nama.containsIgnoreCase(nama);
	}
	
	public void tipe(Integer tipe){
		if (hasil == null){
			hasil = QObat.obat.tipe.eq(tipe);
		} else {
			hasil = hasil.and(QObat.obat.tipe.eq(tipe));
		}
	}
	
	public void tipe(Integer obat, Integer tindakan){
		if (hasil == null){
			hasil = QObat.obat.tipe.eq(obat).or(QObat.obat.tipe.eq(tindakan));
		} else {
			hasil = hasil.and(QObat.obat.tipe.eq(obat).or(QObat.obat.tipe.eq(tindakan)));
		}
	}
	
	public void stok(int stok){
		if (hasil == null){
			hasil = QObat.obat.stok.any().stok.goe(stok);
		} else {
			hasil = hasil.and(QObat.obat.stok.any().stok.goe(stok));
		}
	}
	
	public void tanggalKadaluarsa() {
		
	}
	

}
