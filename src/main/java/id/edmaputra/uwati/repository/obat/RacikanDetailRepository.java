package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;

public interface RacikanDetailRepository extends JpaRepository<RacikanDetail, Long>, QueryDslPredicateExecutor<RacikanDetail>{

	List<RacikanDetail> findByRacikan(Racikan racikan);

}
