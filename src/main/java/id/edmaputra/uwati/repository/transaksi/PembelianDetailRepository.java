package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;

public interface PembelianDetailRepository extends JpaRepository<PembelianDetail, Long>, QueryDslPredicateExecutor<PembelianDetail>{

	List<PembelianDetail> findByPembelian(Pembelian pembelian);

	

}
