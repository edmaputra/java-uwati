package id.edmaputra.uwati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Dokter;
import id.edmaputra.uwati.repository.DokterRepository;

@Service
public class DokterService {

	@Autowired
	private DokterRepository dokterRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(Dokter dokter) {
		dokterRepo.save(dokter);
	}

	public Dokter dapatkan(Integer id) {
		return dokterRepo.findOne(id);
	}

	public Page<Dokter> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return dokterRepo.findAll(expression, request);
	}

	public void hapus(Dokter dokter) {
		dokterRepo.delete(dokter);
	}

	public Dokter dapatkan(String nama) {
		return dokterRepo.findByNama(nama);
	}
}
