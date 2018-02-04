package id.edmaputra.uwati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Satuan;
import id.edmaputra.uwati.repository.SatuanRepository;

@Service
public class SatuanService {

	@Autowired
	private SatuanRepository satuanRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(Satuan satuan) {
		satuanRepo.save(satuan);
	}

	public Satuan dapatkan(Integer id) {
		return satuanRepo.findOne(id);
	}

	public Page<Satuan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return satuanRepo.findAll(expression, request);
	}
	
	public List<Satuan> dapatkanSemua(){
		return satuanRepo.findAll(new Sort(Direction.ASC, "nama"));
	}
	
	public Satuan dapatkanByNama(String nama){
		return satuanRepo.findByNama(nama);
	}

	public void hapus(Satuan satuan) {
		satuanRepo.delete(satuan);
	}

	public List<Satuan> dapatkanListByNama(BooleanExpression exp) {
		return (List<Satuan>) satuanRepo.findAll(exp);
	}
}
