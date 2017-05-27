package id.edmaputra.uwati.repository.transaksi;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.NomorFaktur;

public interface NomorFakturRepository extends JpaRepository<NomorFaktur, Long>, QueryDslPredicateExecutor<NomorFaktur>{

	NomorFaktur findByNomor(Integer nomorUrut);

	NomorFaktur findByNomorAndTanggal(Integer nomorUrut, Date tanggal);

	NomorFaktur findByNomorAndTanggalAndIsTerpakai(Integer nomorUrut, Date tanggal, Boolean isTerpakai);

	NomorFaktur findByNomorAndTanggalAndIsTerpakaiAndIsSelesai(Integer nomorUrut, Date tanggal, Boolean isTerpakai,
			Boolean isSelesai);	
	
}
