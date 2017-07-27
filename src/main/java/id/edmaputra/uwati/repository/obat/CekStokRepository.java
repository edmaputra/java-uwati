package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.CekStok;

public interface CekStokRepository extends JpaRepository<CekStok, Integer>, QueryDslPredicateExecutor<CekStok>{

	List<CekStok> findByRandomId(String randomId);

	CekStok findByRandomIdAndObat(String randomId, String obat);

	CekStok findByRandomIdAndIdObat(String randomId, String idObat);

	

}
