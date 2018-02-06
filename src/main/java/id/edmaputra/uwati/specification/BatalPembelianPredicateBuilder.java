package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QBatalPembelian;
import id.edmaputra.uwati.entity.transaksi.QBatalReturPembelianDetail;

public class BatalPembelianPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QBatalPembelian.batalPembelian.nomorFaktur.containsIgnoreCase(cari)
					.or(QBatalPembelian.batalPembelian.supplier.containsIgnoreCase(cari))
					.or(QBatalPembelian.batalPembelian.pengguna.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QBatalPembelian.batalPembelian.nomorFaktur.containsIgnoreCase(cari)
					.or(QBatalPembelian.batalPembelian.supplier.containsIgnoreCase(cari))
					.or(QBatalPembelian.batalPembelian.pengguna.containsIgnoreCase(cari)));
		}
	}

	public void tanggal(Date from, Date to) {
		if (hasil == null) {
			hasil = QBatalPembelian.batalPembelian.waktuTransaksi.between(from, to);
		} else {
			hasil = hasil.and(QBatalPembelian.batalPembelian.waktuTransaksi.between(from, to));
		}
	}

}
