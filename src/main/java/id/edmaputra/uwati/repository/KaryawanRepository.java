package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Karyawan;

public interface KaryawanRepository extends JpaRepository<Karyawan, Integer>, QueryDslPredicateExecutor<Karyawan>{

	Karyawan findByNama(String nama);	

}
