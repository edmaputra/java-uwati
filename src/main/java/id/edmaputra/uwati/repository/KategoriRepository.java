package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Kategori;

public interface KategoriRepository extends JpaRepository<Kategori, Integer>, QueryDslPredicateExecutor<Kategori>{

	Kategori findByNama(String nama);

}
