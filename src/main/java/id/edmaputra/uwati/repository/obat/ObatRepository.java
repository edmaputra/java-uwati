package id.edmaputra.uwati.repository.obat;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.obat.Obat;

public interface ObatRepository extends JpaRepository<Obat, Long>, QueryDslPredicateExecutor<Obat> {

	Obat findByNama(String nama);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM obat JOIN obat_expired "
			+ "ON obat.id = obat_expired.id_obat "
			+ "WHERE DATEDIFF(obat_expired.tanggalExpired, CURRENT_DATE) "
			+ "BETWEEN 0 AND (SELECT apotek.tenggat_kadaluarsa FROM apotek) "
			+ "AND obat.tipe = 0 ")
	Integer countObatAkanKadaluarsa();

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM obat JOIN obat_expired "
			+ "ON obat.id = obat_expired.id_obat "
			+ "WHERE DATEDIFF(obat_expired.tanggalExpired, CURRENT_DATE) < 0 "
			+ "AND obat.tipe = 0 ")
	Integer countObatSudahKadaluarsa();

	@Query("SELECT COUNT(o.nama) FROM Obat o, ObatStok os " + "WHERE o.id = os.obat " + "AND os.stok < o.stokMinimal AND o.tipe = 0 ")
	Integer countObatAkanHabis();

	@Query(nativeQuery = true, value = "SELECT obat.nama, obat_expired.tanggalExpired, DATEDIFF(tanggalExpired, CURRENT_DATE) as 'SELISIH' "
			+ "FROM obat JOIN obat_expired " 
			+ "ON obat.id = obat_expired.id_obat "
			+ "WHERE DATEDIFF(obat_expired.tanggalExpired, CURRENT_DATE) BETWEEN 0 AND (SELECT apotek.tenggat_kadaluarsa FROM apotek) "
			+ "AND obat.tipe = 0 "
			+ "ORDER BY obat.nama ASC " + "LIMIT ?1, ?2")
	List<Object[]> obatAkanKadaluarsa(int a, int b);

	@Query(nativeQuery = true, value = "SELECT obat.nama, obat_expired.tanggalExpired, DATEDIFF(tanggalExpired, CURRENT_DATE) as 'SELISIH' "
			+ "FROM obat JOIN obat_expired " 
			+ "ON obat.id = obat_expired.id_obat "
			+ "WHERE DATEDIFF(obat_expired.tanggalExpired, CURRENT_DATE) < 0 AND obat.tipe = 0 " 
			+ "ORDER BY obat.nama ASC "
			+ "LIMIT ?1, ?2")
	List<Object[]> obatSudahKadaluarsa(int a, int b);

	@Query(nativeQuery = true, value = "SELECT obat.nama, obat.stok_minimal, obat_stok.stok "
			+ "FROM obat JOIN obat_stok " 
			+ "ON obat.id = obat_stok.id_obat "
			+ "WHERE obat_stok.stok < obat.stok_minimal AND obat.tipe = 0 " 
			+ "ORDER BY obat.nama ASC " 
			+ "LIMIT ?1, ?2")
	List<Object[]> obatAkanHabis(int a, int b);

}
