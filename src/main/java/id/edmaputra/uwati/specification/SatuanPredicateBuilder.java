package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.QSatuan;

public class SatuanPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QSatuan.satuan.nama.containsIgnoreCase(cari)
					.or(QSatuan.satuan.userInput.containsIgnoreCase(cari))
					.or(QSatuan.satuan.userEditor.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(
					QSatuan.satuan.nama.containsIgnoreCase(cari)
					.or(QSatuan.satuan.userInput.containsIgnoreCase(cari))
					.or(QSatuan.satuan.userEditor.containsIgnoreCase(cari)));
		}
	}

}
