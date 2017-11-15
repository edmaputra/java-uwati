package id.edmaputra.uwati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Apotek;

public interface ApotekRepository extends JpaRepository<Apotek, Integer>, QueryDslPredicateExecutor<Apotek> {

}
