package id.edmaputra.uwati.repository.pasien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.RekamMedisDetailTemp;

public interface RekamMedisDetailTempRepository extends JpaRepository<RekamMedisDetailTemp, Long>, QueryDslPredicateExecutor<RekamMedisDetailTemp>{

	List<RekamMedisDetailTemp> findByNomor(String nomor);

	RekamMedisDetailTemp findByNomorAndIdObat(String nomor, String idObat);

	void deleteByNomorAndIdObat(String nomor, String idObat);

	void deleteByNomor(String nomor);

	List<RekamMedisDetailTemp> findByNomorAndTipePenggunaan(String nomor, int tipePenggunaan);

	List<RekamMedisDetailTemp> findByRandomIdAndTipePenggunaan(String randomId, int tipePenggunaan);

	RekamMedisDetailTemp findByRandomIdAndIdObat(String randomId, String idObat);

	List<RekamMedisDetailTemp> findByRandomId(String randomId);

	

}