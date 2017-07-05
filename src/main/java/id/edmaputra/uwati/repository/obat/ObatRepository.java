package id.edmaputra.uwati.repository.obat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Obat;

public interface ObatRepository extends JpaRepository<Obat, Long>, QueryDslPredicateExecutor<Obat>{

	Obat findByNama(String nama);

}
