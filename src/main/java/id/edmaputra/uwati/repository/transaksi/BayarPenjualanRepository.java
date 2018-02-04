package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BayarPenjualan;
import id.edmaputra.uwati.entity.transaksi.Penjualan;

public interface BayarPenjualanRepository extends JpaRepository<BayarPenjualan, Long>, QueryDslPredicateExecutor<BayarPenjualan>{

	List<BayarPenjualan> findByPenjualan(Penjualan p);
	

}
