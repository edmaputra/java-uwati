package id.edmaputra.uwati.repository.transaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.Penjualan;

public interface PenjualanRepository extends JpaRepository<Penjualan, Long>, QueryDslPredicateExecutor<Penjualan>{

	Penjualan findByNomorFaktur(String nomorFaktur);

	

}
