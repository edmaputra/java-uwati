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
	private ObatRepository obatRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(Obat obat) {
		obatRepo.save(obat);
	}

	public Obat dapatkan(Long id) {
		return obatRepo.findOne(id);
	}
	
	public Obat dapatkanByNama(String nama){
		return obatRepo.findByNama(nama);
	}

	public Page<Obat> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return obatRepo.findAll(expression, request);
	}

	public void hapus(Obat obat) {
		obatRepo.delete(obat);
	}

	public List<Obat> dapatkanListByNama(BooleanExpression exp) {
		return (List<Obat>) obatRepo.findAll(exp);
	}

}
