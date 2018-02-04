package id.edmaputra.uwati.repository.transaksi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.Pembelian;

public interface PembelianRepository extends JpaRepository<Pembelian, Long>, QueryDslPredicateExecutor<Pembelian>{

	Pembelian findByNomorFaktur(String nomorFaktur);

	Pembelian findByNomorFakturAndSupplier(String nomorFaktur, String supplier);


}
