package id.edmaputra.uwati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Apotek;
import id.edmaputra.uwati.repository.ApotekRepository;

@Service
public class ApotekService {

	@Autowired
	private ApotekRepository apotekRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(Apotek apotek) {
		apotekRepo.save(apotek);
	}

	public Apotek dapatkan(Integer id) {
		return apotekRepo.findOne(id);
	}

	public Page<Apotek> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return apotekRepo.findAll(expression, request);
	}

	public Page<Apotek> muatDaftar(Integer halaman) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return apotekRepo.findAll(request);
	}
	
	public List<Apotek> semua(){
		return apotekRepo.findAll();
	}

	public void hapus(Apotek apotek) {
		apotekRepo.delete(apotek);
	}
}
