package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailRacikan;

public interface PenjualanDetailRacikanRepository extends JpaRepository<PenjualanDetailRacikan, Long>, QueryDslPredicateExecutor<PenjualanDetailRacikan	>{

	List<PenjualanDetailRacikan> findByPenjualanDetail(PenjualanDetail penjualanDetail);

	

}
