package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QPembelian;

public class PembelianPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QPembelian.pembelian.nomorFaktur.containsIgnoreCase(cari)
					.or(QPembelian.pembelian.pengguna.nama.containsIgnoreCase(cari))
					.or(QPembelian.pembelian.supplier.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QPembelian.pembelian.nomorFaktur.containsIgnoreCase(cari)
					.or(QPembelian.pembelian.pengguna.nama.containsIgnoreCase(cari))
					.or(QPembelian.pembelian.supplier.containsIgnoreCase(cari)));
		}
	}

	public void tanggal(Date firstDate, Date lastDate) {
		if (hasil == null) {
			hasil = QPembelian.pembelian.waktuTransaksi.between(firstDate, lastDate);
		} else {
			hasil = hasil.and(QPembelian.pembelian.waktuTransaksi.between(firstDate, lastDate));
		}
	}

}
