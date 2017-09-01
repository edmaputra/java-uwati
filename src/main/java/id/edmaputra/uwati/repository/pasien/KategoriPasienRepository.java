package id.edmaputra.uwati.repository.pasien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.KategoriPasien;

public interface KategoriPasienRepository extends JpaRepository<KategoriPasien, Integer>, QueryDslPredicateExecutor<KategoriPasien>{

	KategoriPasien findByNama(String nama);

}
