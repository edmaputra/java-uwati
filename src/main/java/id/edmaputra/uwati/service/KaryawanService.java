package id.edmaputra.uwati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Karyawan;
import id.edmaputra.uwati.entity.pengguna.Pengguna;
import id.edmaputra.uwati.repository.KaryawanRepository;

@Service
public class KaryawanService {

	@Autowired
	private KaryawanRepository karyawanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Karyawan karyawan) {
		karyawanRepository.save(karyawan);
	}

	public Karyawan dapatkan(Integer id) {
		return karyawanRepository.findOne(id);
	}

	public Page<Karyawan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return karyawanRepository.findAll(expression, request);
	}

	public void hapus(Karyawan karyawan) {
		karyawanRepository.delete(karyawan);
	}

	public Karyawan dapatkan(String nama) {
		return karyawanRepository.findByNama(nama);
	}

	public List<Karyawan> dapatkanListByNama(BooleanExpression exp) {
		return (List<Karyawan>) karyawanRepository.findAll(exp);
	}

	public Karyawan dapatkan(Pengguna p) {
		return karyawanRepository.findByPengguna(p);
	}
}
