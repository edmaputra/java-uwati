package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BatalPenjualan;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetail;

public interface BatalPenjualanDetailRepository extends JpaRepository<BatalPenjualanDetail, Long>, QueryDslPredicateExecutor<BatalPenjualanDetail>{

	List<BatalPenjualanDetail> findByBatalPenjualan(BatalPenjualan batalPenjualan);

}
