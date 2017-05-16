package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.RacikanDetail;

public interface RacikanDetailRepository extends JpaRepository<RacikanDetail, Long>, QueryDslPredicateExecutor<RacikanDetail>{

}
