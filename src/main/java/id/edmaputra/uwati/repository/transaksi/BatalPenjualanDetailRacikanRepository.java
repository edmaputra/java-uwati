package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetailRacikan;

public interface BatalPenjualanDetailRacikanRepository extends JpaRepository<BatalPenjualanDetailRacikan, Long>, QueryDslPredicateExecutor<BatalPenjualanDetailRacikan>{

	List<BatalPenjualanDetailRacikan> findByBatalPenjualanDetail(BatalPenjualanDetail batalPenjualanDetail);

	

}
