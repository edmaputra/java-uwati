package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.repository.obat.ObatRepository;

@Service
public class ObatService {

	@Autowired
	private ObatRepository obatRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Obat obat) {
		obatRepository.save(obat);
	}

	public Obat dapatkan(Long id) {
		return obatRepository.findOne(id);
	}
	
	public Obat dapatkanByNama(String nama){
		return obatRepository.findByNama(nama);
	}

	public Page<Obat> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return obatRepository.findAll(expression, request);
	}
	
	public Page<Obat> muatDaftar(Integer halaman, BooleanExpression expression, int size) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return obatRepository.findAll(expression, request);
	}

	public void hapus(Obat obat) {
		obatRepository.delete(obat);
	}

	public List<Obat> dapatkanListByNama(BooleanExpression exp) {
		return (List<Obat>) obatRepository.findAll(exp);
	}

}
