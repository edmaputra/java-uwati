package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pengguna.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, Integer>, QueryDslPredicateExecutor<Pengguna>{

	Pengguna findByNama(String nama);
}
