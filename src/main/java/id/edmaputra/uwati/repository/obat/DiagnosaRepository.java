package id.edmaputra.uwati.repository.obat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Diagnosa;
import id.edmaputra.uwati.entity.master.obat.Tindakan;

public interface DiagnosaRepository extends JpaRepository<Diagnosa, Long>, QueryDslPredicateExecutor<Diagnosa>{

	Diagnosa findByNama(String nama);

	Diagnosa findByKode(String kode);
	

}
