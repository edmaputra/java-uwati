package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.QCekStok;
import id.edmaputra.uwati.entity.master.obat.QTindakan;

public class CekStokPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}
	
	public void randomId(String randomId){
		if (hasil == null) {
			hasil = QCekStok.cekStok.randomId.eq(randomId);
		} else {
			hasil = hasil.and(QCekStok.cekStok.randomId.eq(randomId));
		}
	}
	
	public void randomIdAndIdObat(String randomId, String idObat){
		if (hasil == null) {
			hasil = QCekStok.cekStok.randomId.eq(randomId)
					.and(QCekStok.cekStok.idObat.eq(idObat));
		} else {
			hasil = hasil.and(QCekStok.cekStok.randomId.eq(randomId)
					.and(QCekStok.cekStok.idObat.eq(idObat)));
		}
	}
	
	public void randomIdAndNamaObat(String randomId, String obat){
		if (hasil == null) {
			hasil = QCekStok.cekStok.randomId.eq(randomId)
					.and(QCekStok.cekStok.obat.eq(obat));
		} else {
			hasil = hasil.and(QCekStok.cekStok.randomId.eq(randomId)
					.and(QCekStok.cekStok.obat.eq(obat)));
		}
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QTindakan.tindakan.nama.containsIgnoreCase(cari)
					.or(QTindakan.tindakan.userInput.containsIgnoreCase(cari))
					.or(QTindakan.tindakan.userEditor.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QTindakan.tindakan.nama.containsIgnoreCase(cari)
					.or(QTindakan.tindakan.userInput.containsIgnoreCase(cari))
					.or(QTindakan.tindakan.userEditor.containsIgnoreCase(cari)));
		}
	}

}
