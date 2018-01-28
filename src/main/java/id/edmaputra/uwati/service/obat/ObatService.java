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
	private ObatRepository repository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Obat obat) {
		repository.save(obat);
	}

	public Obat dapatkan(Long id) {
		return repository.findOne(id);
	}
	
	public Obat dapatkanByNama(String nama){
		return repository.findByNama(nama);
	}

	public Page<Obat> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return repository.findAll(expression, request);
	}
	
	public Page<Obat> muatDaftar(Integer halaman, BooleanExpression expression, int size) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return repository.findAll(expression, request);
	}

	public void hapus(Obat obat) {
		repository.delete(obat);
	}

	public List<Obat> dapatkanListByNama(BooleanExpression exp) {
		return (List<Obat>) repository.findAll(exp);
	}

	public Integer countObatAkanKadaluarsa() {
		return repository.countObatAkanKadaluarsa();
	}

	public Integer countObatSudahKadaluarsa() {
		return repository.countObatSudahKadaluarsa();
	}

	public Integer countObatAkanHabis() {		
		return repository.countObatAkanHabis();
	}
	
	public List<Object[]> obatAkanHabis(int a, int b){		
		return repository.obatAkanHabis(a, b);
	}
	
	public List<Object[]> obatAkanKadaluarsa(int a, int b){		
		return repository.obatAkanKadaluarsa(a, b);
	}
	
	public List<Object[]> obatSudahKadaluarsa(int a, int b){		
		return repository.obatSudahKadaluarsa(a, b);
	}

}
