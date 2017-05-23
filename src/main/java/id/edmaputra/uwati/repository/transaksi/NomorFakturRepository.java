package id.edmaputra.uwati.repository.transaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.NomorFaktur;

public interface NomorFakturRepository extends JpaRepository<NomorFaktur, Long>, QueryDslPredicateExecutor<NomorFaktur>{
	
	
	
}
