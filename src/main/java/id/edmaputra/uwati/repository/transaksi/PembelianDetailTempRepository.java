package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.PembelianDetailTemp;

public interface PembelianDetailTempRepository extends JpaRepository<PembelianDetailTemp, Long>, QueryDslPredicateExecutor<PembelianDetailTemp>{
	
	void deleteByNomorFakturAndPengguna(String nomorFaktur, String pengguna);

	PembelianDetailTemp findByRandomIdAndIdObat(String randomId, Long idObat);

	List<PembelianDetailTemp> findByRandomId(String randomId);
	
}
