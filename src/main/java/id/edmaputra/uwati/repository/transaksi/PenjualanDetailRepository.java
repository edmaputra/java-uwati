package id.edmaputra.uwati.repository.transaksi;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;

public interface PenjualanDetailRepository extends JpaRepository<PenjualanDetail, Long>, QueryDslPredicateExecutor<PenjualanDetail>{

	List<PenjualanDetail> findByPenjualan(Penjualan penjualan);

	@Query(value="SELECT penjualan_detail.obat, SUM(penjualan_detail.jumlah) as total \n" + 
			"FROM penjualan \n" + 
			"JOIN penjualan_detail \n" + 
			"ON penjualan_detail.id_penjualan = penjualan.id \n" + 
			"WHERE penjualan.waktu_transaksi BETWEEN ?1 AND ?2 \n" + 
			"GROUP BY penjualan_detail.obat \n" + 
			"ORDER BY total DESC", nativeQuery = true)
	List<Object[]> top10PenjualanObat(Date from, Date to);

}