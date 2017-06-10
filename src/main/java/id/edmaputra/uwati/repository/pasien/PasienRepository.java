package id.edmaputra.uwati.repository.pasien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.Pasien;

public interface PasienRepository extends JpaRepository<Pasien, Long>, QueryDslPredicateExecutor<Pasien>{

	Pasien findByNama(String nama);

	Pasien findByIdentitas(String identitas);

}
