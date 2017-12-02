package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.QPenjualan;

public class PenjualanPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}
	
	
	public void cari(String cari){
		if (hasil == null) {			
			hasil = QPenjualan.penjualan.pelanggan.containsIgnoreCase(cari)
					.or(QPenjualan.penjualan.pengguna.containsIgnoreCase(cari))
					.or(QPenjualan.penjualan.penjualanDetail.any().obat.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QPenjualan.penjualan.pelanggan.containsIgnoreCase(cari)
					.or(QPenjualan.penjualan.pengguna.containsIgnoreCase(cari))
					.or(QPenjualan.penjualan.penjualanDetail.any().obat.containsIgnoreCase(cari)));
		}
	}
	
	public void tipe(int tipe){
		if (hasil == null) {			
			hasil = QPenjualan.penjualan.tipe.eq(tipe);
		} else {
			hasil = hasil.and(QPenjualan.penjualan.tipe.eq(tipe));
		}
	}
	
	public void tanggal(Date tanggalAwal, Date tanggalAkhir){
		if (hasil == null) {			
			hasil = QPenjualan.penjualan.waktuTransaksi.between(tanggalAwal, tanggalAkhir);
		} else {
			hasil = hasil.and(QPenjualan.penjualan.waktuTransaksi.between(tanggalAwal, tanggalAkhir));
		}
	}


	public void lunas(boolean b) {
		if (hasil == null) {			
			hasil = QPenjualan.penjualan.lunas.eq(b);
		} else {
			hasil = hasil.and(hasil = QPenjualan.penjualan.lunas.eq(b));
		}		
	}

}
