package id.edmaputra.uwati.repository.transaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.PenjualanRacikan;

public interface PenjualanRacikanRepository extends JpaRepository<PenjualanRacikan, Long>, QueryDslPredicateExecutor<PenjualanRacikan>{

	

}
