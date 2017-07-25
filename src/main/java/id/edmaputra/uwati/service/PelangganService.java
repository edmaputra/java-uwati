package id.edmaputra.uwati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Pelanggan;
import id.edmaputra.uwati.repository.PelangganRepository;

@Service
public class PelangganService {

	@Autowired
	private PelangganRepository pelangganRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(Pelanggan pelanggan) {
		pelangganRepo.save(pelanggan);
	}

	public Pelanggan dapatkan(Integer id) {
		return pelangganRepo.findOne(id);
	}

	public Page<Pelanggan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return pelangganRepo.findAll(expression, request);
	}

	public void hapus(Pelanggan pelanggan) {
		pelangganRepo.delete(pelanggan);
	}

	public Pelanggan dapatkan(String nama) {
		return pelangganRepo.findByNama(nama);
	}
}
