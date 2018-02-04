package id.edmaputra.uwati.repository.pasien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.RekamMedisDiagnosaTemp;

public interface RekamMedisDiagnosaTempRepository extends JpaRepository<RekamMedisDiagnosaTemp, Long>, QueryDslPredicateExecutor<RekamMedisDiagnosaTemp>{

	RekamMedisDiagnosaTemp findByRandomIdAndIdDiagnosa(String randomId, String idDiagnosa);

	List<RekamMedisDiagnosaTemp> findByRandomId(String randomId);

	List<RekamMedisDiagnosaTemp> findByNomor(String nomor);

	RekamMedisDiagnosaTemp findByNomorAndIdDiagnosa(String nomor, String idDiagnosa);

	void deleteByNomor(String nomor);

	void deleteByNomorAndIdDiagnosa(String nomor, String idDiagnosa);

	

}