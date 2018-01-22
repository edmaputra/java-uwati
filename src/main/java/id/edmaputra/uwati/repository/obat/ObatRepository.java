package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Obat;

public interface ObatRepository extends JpaRepository<Obat, Long>, QueryDslPredicateExecutor<Obat>{

	Obat findByNama(String nama);
	
	@Query("SELECT o.nama, o.stokMinimal, os.stok FROM Obat o, ObatStok os "
			+ "WHERE o.id = os.obat "
			+ "AND os.stok < o.stokMinimal")
	List<Obat> warningStokObat();

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM obat_expired "
			+ "WHERE DATEDIFF(tanggalExpired, CURRENT_DATE) "
			+ "BETWEEN 0 AND (SELECT apotek.tenggat_kadaluarsa FROM apotek)")
	Integer countObatAkanKadaluarsa();
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM obat_expired "
			+ "WHERE DATEDIFF(tanggalExpired, CURRENT_DATE) < 0")
	Integer countObatSudahKadaluarsa();
	
	@Query("SELECT COUNT(o.nama) FROM Obat o, ObatStok os "
			+ "WHERE o.id = os.obat "
			+ "AND os.stok < o.stokMinimal")
	Integer countObatAkanHabis();
	
//	SELECT obat.nama, obat.stok_minimal, obat_stok.stok 
//	FROM `obat` JOIN obat_stok 
//	ON obat.id = obat_stok.id_obat
//	WHERE obat_stok.stok < obat.stok_minimal
	
	
//	SELECT *, DATEDIFF(tanggalExpired, CURRENT_DATE) as 'SELISIH' FROM `obat_expired` 
//	WHERE DATEDIFF(tanggalExpired, CURRENT_DATE) BETWEEN 0 AND (SELECT apotek.tenggat_kadaluarsa FROM apotek)
	

}
