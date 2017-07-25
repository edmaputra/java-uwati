package id.edmaputra.uwati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Kategori;
import id.edmaputra.uwati.repository.KategoriRepository;

@Service
public class KategoriService {

	@Autowired
	private KategoriRepository kategoriRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(Kategori kategori) {
		kategoriRepo.save(kategori);
	}

	public Kategori dapatkan(Integer id) {
		return kategoriRepo.findOne(id);
	}
	
	public void hapus(Kategori kategori) {
		kategoriRepo.delete(kategori);
	}

	public Page<Kategori> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return kategoriRepo.findAll(expression, request);
	}
	
	public List<Kategori> dapatkanSemua(){
		return kategoriRepo.findAll(new Sort(Direction.ASC, "nama"));
	}
	
	public Kategori dapatkanByNama(String nama){
		return kategoriRepo.findByNama(nama);
	}

	public List<Kategori> dapatkanListByNama(BooleanExpression exp) {
		return (List<Kategori>) kategoriRepo.findAll(exp);
	}

	
}
