package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Diagnosa;
import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.repository.obat.DiagnosaRepository;

@Service
public class DiagnosaService {

	@Autowired
	private DiagnosaRepository diagnosaRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Diagnosa diagnosa) {
		diagnosaRepository.save(diagnosa);
	}

	public Diagnosa dapatkan(Long id) {
		return diagnosaRepository.findOne(id);
	}
	
	public Diagnosa dapatkanByNama(String nama){
		return diagnosaRepository.findByNama(nama);
	}
	
	public Diagnosa dapatkanByKode(String kode){
		return diagnosaRepository.findByKode(kode);
	}

	public Page<Diagnosa> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return diagnosaRepository.findAll(expression, request);
	}
	
	public Page<Diagnosa> muatDaftar(Integer halaman, BooleanExpression expression, int size) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "nama");
		return diagnosaRepository.findAll(expression, request);
	}

	public void hapus(Diagnosa diagnosa) {
		diagnosaRepository.delete(diagnosa);
	}

	public List<Diagnosa> dapatkanListByNama(BooleanExpression exp) {
		return (List<Diagnosa>) diagnosaRepository.findAll(exp);
	}
}
