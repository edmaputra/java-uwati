package id.edmaputra.uwati.repository.transaksi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.transaksi.PenjualanDetailTemp;

public interface PenjualanDetailTempRepository extends JpaRepository<PenjualanDetailTemp, Long>, QueryDslPredicateExecutor<PenjualanDetailTemp>{
	
	void deleteByNomorFakturAndPengguna(String nomorFaktur, String pengguna);

	PenjualanDetailTemp findByIdAndNomorFaktur(Long id, String nomorFaktur);

	List<PenjualanDetailTemp> findByNomorFaktur(String nomorFakturTemp);

	List<PenjualanDetailTemp> findByNomorFakturAndPengguna(String nomorFaktur, String pengguna);

	PenjualanDetailTemp findByObatAndNomorFaktur(String obat, String nomorFaktur);

	PenjualanDetailTemp findByRandomIdAndIdObat(String randomId, String idObat);

	List<PenjualanDetailTemp> findByRandomId(String randomId);
	
}
