package id.edmaputra.uwati.repository.transaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BatalReturPembelianDetail;

public interface BatalReturPembelianDetailRepository extends JpaRepository<BatalReturPembelianDetail, Long>, QueryDslPredicateExecutor<BatalReturPembelianDetail>{

	

}
