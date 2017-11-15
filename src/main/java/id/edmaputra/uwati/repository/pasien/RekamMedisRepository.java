package id.edmaputra.uwati.repository.pasien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.Pasien;
import id.edmaputra.uwati.entity.pasien.RekamMedis;

public interface RekamMedisRepository extends JpaRepository<RekamMedis, Long>, QueryDslPredicateExecutor<RekamMedis>{

	RekamMedis findByNomor(String nomor);

	List<RekamMedis> findByPasien(Pasien pasien);

}
