package id.edmaputra.uwati.repository.pasien;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDiagnosa;

public interface RekamMedisDiagnosaRepository
		extends JpaRepository<RekamMedisDiagnosa, Long>, QueryDslPredicateExecutor<RekamMedisDiagnosa> {

	List<RekamMedisDiagnosa> findByRekamMedis(RekamMedis rekamMedis);

	void deleteByRekamMedis(RekamMedis rekamMedis);

	@Query(value = "SELECT rekam_medis_diagnosa.diagnosa, COUNT(rekam_medis_diagnosa.diagnosa) as total "
			+ "FROM rekam_medis_diagnosa "
			+ "JOIN rekam_medis ON rekam_medis_diagnosa.id_rekam_medis = rekam_medis.id \n"
			+ "WHERE rekam_medis.tanggal BETWEEN ?1 AND ?2 \n"
			+ "GROUP BY rekam_medis_diagnosa.diagnosa \n" 
			+ "ORDER BY total DESC ", nativeQuery = true)
	List<Object[]> top10Diagnosa(Date from, Date to);

}