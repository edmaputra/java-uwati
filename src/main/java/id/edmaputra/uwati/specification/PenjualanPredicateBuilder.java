package id.edmaputra.uwati.specification;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.QPenjualan;

public class PenjualanPredicateBuilder {

	private BooleanExpression hasil = null;
	
	@Autowired
	@Qualifier("entity-manager")
	private LocalContainerEntityManagerFactoryBean entityManager;
	
	public List<Penjualan> cobaPenjualan(Date from, Date to) {
		JPAQuery query = new JPAQuery();
		QPenjualan qPenjualan = QPenjualan.penjualan;		
//		List<Penjualan> q = query.from(qPenjualan).where(qPenjualan.waktuTransaksi.between(from, to)).list(qPenjualan);
		List<Penjualan> q = query.from(qPenjualan).list(qPenjualan);
		return q;
	}

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
