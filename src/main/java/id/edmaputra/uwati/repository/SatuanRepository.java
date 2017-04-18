package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Satuan;

public interface SatuanRepository extends JpaRepository<Satuan, Integer>, QueryDslPredicateExecutor<Satuan>{

	Satuan findByNama(String nama);

}
