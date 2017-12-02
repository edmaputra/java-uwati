package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QPembelian;
import id.edmaputra.uwati.support.TanggalSupport;

public class PembelianPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QPembelian.pembelian.nomorFaktur.containsIgnoreCase(cari)
					.or(QPembelian.pembelian.pengguna.nama.containsIgnoreCase(cari))
					.or(QPembelian.pembelian.supplier.containsIgnoreCase(cari))
					.or(QPembelian.pembelian.pembelianDetail.any().obat.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QPembelian.pembelian.nomorFaktur.containsIgnoreCase(cari)
					.or(QPembelian.pembelian.pengguna.nama.containsIgnoreCase(cari))
					.or(QPembelian.pembelian.supplier.containsIgnoreCase(cari))
					.or(QPembelian.pembelian.pembelianDetail.any().obat.containsIgnoreCase(cari)));
		}
	}

	public void tanggal(Date firstDate, Date lastDate) {
		if (hasil == null) {
			hasil = QPembelian.pembelian.waktuTransaksi.between(firstDate, lastDate);			
		} else {
			hasil = hasil.and(QPembelian.pembelian.waktuTransaksi.between(firstDate, lastDate));
		}
	}
	
	public void lunas(Boolean isLunas) {
		if (hasil == null) {
			hasil = QPembelian.pembelian.lunas.eq(isLunas);			
		} else {
			hasil = hasil.and(QPembelian.pembelian.lunas.eq(isLunas));
		}
	}

	public void tahun(String tahun) {
		Date firstDate = TanggalSupport.buatHariPertamaDariTahun(tahun);
		Date lastDate = TanggalSupport.buatHariTerakhirDariTahun(tahun);
		if (hasil == null) {
			hasil = QPembelian.pembelian.waktuTransaksi.between(firstDate, lastDate);
		} else {
			hasil = hasil.and(QPembelian.pembelian.waktuTransaksi.between(firstDate, lastDate));
		}
	}
	
	public void nomorFaktur(String nomorFaktur){
		if (hasil == null) {
			hasil = QPembelian.pembelian.nomorFaktur.containsIgnoreCase(nomorFaktur);
		} else {
			hasil = hasil.and(QPembelian.pembelian.nomorFaktur.containsIgnoreCase(nomorFaktur));
		}
	}

}
