package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Pasien;

public interface PasienRepository extends JpaRepository<Pasien, Integer>, QueryDslPredicateExecutor<Pasien>{

	Pasien findByNama(String nama);

}
