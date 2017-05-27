package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Pelanggan;

public interface PelangganRepository extends JpaRepository<Pelanggan, Integer>, QueryDslPredicateExecutor<Pelanggan>{

	Pelanggan findByNama(String nama);

}
