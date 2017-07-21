package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.repository.obat.RacikanRepository;

@Service
public class RacikanService {

	@Autowired
	private RacikanRepository racikanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Racikan racikan) {
		racikanRepository.save(racikan);
	}

	public Racikan dapatkan(Long id) {
		return racikanRepository.findOne(id);
	}

	public Page<Racikan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return racikanRepository.findAll(expression, request);
	}
	
	public Page<Racikan> muatDaftar(Integer halaman, BooleanExpression expression, Integer pageSize) {
		PageRequest request = new PageRequest(halaman - 1, pageSize, Sort.Direction.ASC, "nama");
		return racikanRepository.findAll(expression, request);
	}
	
	public List<Racikan> dapatkanSemua(){
		return racikanRepository.findAll(new Sort(Direction.ASC, "nama"));
	}
	
	public Racikan dapatkanByNama(String nama){
		return racikanRepository.findByNama(nama);
	}

	public void hapus(Racikan racikan) {
		racikanRepository.delete(racikan);
	}

	public List<Racikan> dapatkanListByNama(BooleanExpression exp) {
		return (List<Racikan>) racikanRepository.findAll(exp);
	}
}
