package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.ReturPembelianDetailTemp;

public interface ReturPembelianDetailTempRepository extends JpaRepository<ReturPembelianDetailTemp, Long>, QueryDslPredicateExecutor<ReturPembelianDetailTemp>{
	
	List<ReturPembelianDetailTemp> findByRandomId(String randomId);
	
	void deleteByNomorFakturAndPengguna(String nomorFaktur, String pengguna);

	ReturPembelianDetailTemp findByRandomIdAndIdObat(String randomId, Long idObat);
}
