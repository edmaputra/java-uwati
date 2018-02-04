package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.BayarPembelian;
import id.edmaputra.uwati.entity.transaksi.Pembelian;

public interface BayarPembelianRepository extends JpaRepository<BayarPembelian, Long>, QueryDslPredicateExecutor<BayarPembelian>{

	List<BayarPembelian> findByPembelian(Pembelian pembelian);
	

}
