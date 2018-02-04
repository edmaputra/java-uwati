package id.edmaputra.uwati.repository.transaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BatalPembelian;

public interface BatalPembelianRepository extends JpaRepository<BatalPembelian, Long>, QueryDslPredicateExecutor<BatalPembelian>{

	BatalPembelian findByNomorFaktur(String nomorFaktur);

	

}
