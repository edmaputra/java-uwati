package id.edmaputra.uwati.repository.pasien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetail;

public interface RekamMedisDetailRepository extends JpaRepository<RekamMedisDetail, Long>, QueryDslPredicateExecutor<RekamMedisDetail>{

	List<RekamMedisDetail> findByRekamMedis(RekamMedis rekamMedis);

	void deleteByRekamMedis(RekamMedis rekamMedis);

	RekamMedisDetail findByRekamMedisAndTerapi(RekamMedis rekamMedis, String terapi);

	RekamMedisDetail findByTerapi(String nama);

	List<RekamMedisDetail> findByRekamMedisAndTipe(RekamMedis rekamMedis, Integer tipe);

}