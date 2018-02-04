package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QReturPembelianDetail;

public class ReturPembelianDetailPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QReturPembelianDetail.returPembelianDetail.nomorFaktur.containsIgnoreCase(cari)
					.or(QReturPembelianDetail.returPembelianDetail.supplier.containsIgnoreCase(cari))
					.or(QReturPembelianDetail.returPembelianDetail.pengguna.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QReturPembelianDetail.returPembelianDetail.nomorFaktur.containsIgnoreCase(cari)
					.or(QReturPembelianDetail.returPembelianDetail.supplier.containsIgnoreCase(cari))
					.or(QReturPembelianDetail.returPembelianDetail.pengguna.containsIgnoreCase(cari)));
		}
	}

	public void tanggal(Date from, Date to) {
		if (hasil == null) {
			hasil = QReturPembelianDetail.returPembelianDetail.tanggal.between(from, to);
		} else {
			hasil = hasil.and(QReturPembelianDetail.returPembelianDetail.tanggal.between(from, to));
		}
	}

}
