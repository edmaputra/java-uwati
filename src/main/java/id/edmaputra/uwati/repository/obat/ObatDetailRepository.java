package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;

public interface ObatDetailRepository extends JpaRepository<ObatDetail, Long>, QueryDslPredicateExecutor<ObatDetail>{

	List<ObatDetail> findByObat(Obat obat);

}
