package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;

public interface PembelianDetailRepository extends JpaRepository<PembelianDetail, Long>, QueryDslPredicateExecutor<PembelianDetail>{

	List<PembelianDetail> findByPembelian(Pembelian pembelian);

	@Query(nativeQuery = true, 
			value = "SELECT * FROM pembelian " + 
			"JOIN pembelian_detail " + 
			"ON pembelian.id = pembelian_detail.id_pembelian " + 
			"WHERE pembelian.nomor_faktur = ?2 " + 
			"AND pembelian_detail.id = ?1 "
			+ "AND pembelian.supplier = ?3")
	PembelianDetail findByIdAndNomorFakturAndSupplier(Long id, String nomorFaktur, String supplier);
	
	@Query(nativeQuery = true, 
			value = "SELECT * FROM pembelian " + 
			"JOIN pembelian_detail " + 
			"ON pembelian.id = pembelian_detail.id_pembelian " + 
			"WHERE pembelian.nomor_faktur = ?2 " + 
			"AND pembelian_detail.obat = ?1 "
			+ "AND pembelian.supplier = ?3")
	PembelianDetail findByObatAndNomorFakturAndSupplier(String obat, String nomorFaktur, String supplier);
	
	@Query(nativeQuery = true, value = "UPDATE pembelian,pembelian_detail SET pembelian_detail.isReturned = ?4 " + 
			"WHERE pembelian_detail.obat = ?1 " + 
			"AND pembelian.nomor_faktur = ?2 " + 
			"AND pembelian.supplier = ?3")
	void setStatusReturObat(String obat, String nomorFaktur, String supplier, int status);

	PembelianDetail findByIdAndPembelian(Long id, Pembelian pembelian);

	PembelianDetail findByObatAndPembelian(String obat, Pembelian pembelian);
	

}
