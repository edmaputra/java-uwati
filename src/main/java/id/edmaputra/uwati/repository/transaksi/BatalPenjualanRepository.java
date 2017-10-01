package id.edmaputra.uwati.repository.transaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BatalPenjualan;

public interface BatalPenjualanRepository extends JpaRepository<BatalPenjualan, Long>, QueryDslPredicateExecutor<BatalPenjualan>{

	BatalPenjualan findByNomorFaktur(String nomorFaktur);

}
