package id.edmaputra.uwati.repository.pasien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDiagnosa;

public interface RekamMedisDiagnosaRepository extends JpaRepository<RekamMedisDiagnosa, Long>, QueryDslPredicateExecutor<RekamMedisDiagnosa>{

	List<RekamMedisDiagnosa> findByRekamMedis(RekamMedis rekamMedis);

	void deleteByRekamMedis(RekamMedis rekamMedis);


}