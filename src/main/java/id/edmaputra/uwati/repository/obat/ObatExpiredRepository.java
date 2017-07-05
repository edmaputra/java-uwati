package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;

public interface ObatExpiredRepository extends JpaRepository<ObatExpired, Long>, QueryDslPredicateExecutor<ObatExpired>{

	List<ObatExpired> findByObat(Obat obat);

}
