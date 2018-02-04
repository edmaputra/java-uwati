package id.edmaputra.uwati.repository.obat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Racikan;

public interface RacikanRepository extends JpaRepository<Racikan, Long>, QueryDslPredicateExecutor<Racikan>{

	Racikan findByNama(String nama);

}
