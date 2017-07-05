package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.QRacikan;

public class RacikanPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QRacikan.racikan.nama.containsIgnoreCase(cari);
		} else {
			hasil = hasil.and(QRacikan.racikan.nama.containsIgnoreCase(cari));
		}
	}
	
	public void id(Long id) {
		if (hasil == null) {
			hasil = QRacikan.racikan.id.eq(id);
		} else {
			hasil = hasil.and(QRacikan.racikan.id.eq(id));
		}
	}

}
