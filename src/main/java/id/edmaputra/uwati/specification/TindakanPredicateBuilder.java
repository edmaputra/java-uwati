package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.QTindakan;

public class TindakanPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
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
