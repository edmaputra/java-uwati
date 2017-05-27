package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Dokter;

public interface DokterRepository extends JpaRepository<Dokter, Integer>, QueryDslPredicateExecutor<Dokter>{

	Dokter findByNama(String nama);

}
