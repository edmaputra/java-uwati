package id.edmaputra.uwati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Tindakan;
import id.edmaputra.uwati.repository.TindakanRepository;

@Service
public class TindakanService {

	@Autowired
	private TindakanRepository tindakanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Tindakan tindakan) {
		tindakanRepository.save(tindakan);
	}

	public Tindakan dapatkan(Integer id) {
		return tindakanRepository.findOne(id);
	}

	public Page<Tindakan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return tindakanRepository.findAll(expression, request);
	}
	
	public Page<Tindakan> muatDaftar(Integer halaman, Integer size, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return tindakanRepository.findAll(expression, request);
	}

	public void hapus(Tindakan tindakan) {
		tindakanRepository.delete(tindakan);
	}

	public Tindakan dapatkan(String nama) {
		return tindakanRepository.findByNama(nama);
	}
}
