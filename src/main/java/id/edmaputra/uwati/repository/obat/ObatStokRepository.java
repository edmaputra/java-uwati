package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatStok;

public interface ObatStokRepository extends JpaRepository<ObatStok, Long>, QueryDslPredicateExecutor<ObatStok>{

	List<ObatStok> findByObat(Obat obat);

}
