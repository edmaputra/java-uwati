package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Tindakan;

public interface TindakanRepository extends JpaRepository<Tindakan, Integer>, QueryDslPredicateExecutor<Tindakan>{

	Tindakan findByNama(String nama);

}
