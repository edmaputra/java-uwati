package id.edmaputra.uwati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pengguna.Pengguna;
import id.edmaputra.uwati.repository.PenggunaRepository;

@Service
public class PenggunaService {
	
	@Autowired
	private PenggunaRepository repository;
	
	private static final int PAGE_SIZE = 25;
	
	public void simpan(Pengguna pengguna){
		repository.save(pengguna);
	}
	
	public Pengguna temukan(String nama){
		return repository.findByNama(nama);
	}
	
	public Page<Pengguna> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return repository.findAll(expression, request);
	}

	public Pengguna dapatkan(Integer id) {
		return repository.findOne(id);
	}

	public List<Pengguna> dapatkanSemua() {
		return repository.findAll();
	}

	public void hapus(Pengguna pengguna) {
		repository.delete(pengguna);		
	}

}
