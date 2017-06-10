package id.edmaputra.uwati.repository.pengguna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import id.edmaputra.uwati.entity.master.Karyawan;
import id.edmaputra.uwati.entity.pengguna.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, Integer>, QueryDslPredicateExecutor<Pengguna>{

	Pengguna findByNama(String nama);

	Pengguna findByKaryawan(Karyawan karyawan);
}
