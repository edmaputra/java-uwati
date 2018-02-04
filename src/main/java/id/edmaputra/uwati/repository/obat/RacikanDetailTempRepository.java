package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.RacikanDetailTemporary;

public interface RacikanDetailTempRepository extends JpaRepository<RacikanDetailTemporary, Long>, QueryDslPredicateExecutor<RacikanDetailTemporary>{

	List<RacikanDetailTemporary> findByRandomId(String randomId);

	RacikanDetailTemporary findByRandomIdAndIdObat(String randomId, String idObat);

	

}
