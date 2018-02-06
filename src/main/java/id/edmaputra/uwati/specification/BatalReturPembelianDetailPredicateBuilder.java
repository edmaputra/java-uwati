package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QBatalReturPembelianDetail;

public class BatalReturPembelianDetailPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QBatalReturPembelianDetail.batalReturPembelianDetail.nomorFaktur.containsIgnoreCase(cari)
					.or(QBatalReturPembelianDetail.batalReturPembelianDetail.supplier.containsIgnoreCase(cari))
					.or(QBatalReturPembelianDetail.batalReturPembelianDetail.pengguna.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QBatalReturPembelianDetail.batalReturPembelianDetail.nomorFaktur.containsIgnoreCase(cari)
					.or(QBatalReturPembelianDetail.batalReturPembelianDetail.supplier.containsIgnoreCase(cari))
					.or(QBatalReturPembelianDetail.batalReturPembelianDetail.pengguna.containsIgnoreCase(cari)));
		}
	}

	public void tanggal(Date from, Date to) {
		if (hasil == null) {
			hasil = QBatalReturPembelianDetail.batalReturPembelianDetail.tanggal.between(from, to);
		} else {
			hasil = hasil.and(QBatalReturPembelianDetail.batalReturPembelianDetail.tanggal.between(from, to));
		}
	}

}
