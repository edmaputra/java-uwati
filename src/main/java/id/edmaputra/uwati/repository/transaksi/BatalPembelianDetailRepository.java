package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BatalPembelian;
import id.edmaputra.uwati.entity.transaksi.BatalPembelianDetail;

public interface BatalPembelianDetailRepository extends JpaRepository<BatalPembelianDetail, Long>, QueryDslPredicateExecutor<BatalPembelianDetail>{

	List<BatalPembelianDetail> findByBatalPembelian(BatalPembelian BatalPembelianDetail);
	
}
