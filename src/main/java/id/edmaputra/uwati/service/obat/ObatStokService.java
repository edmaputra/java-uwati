package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.repository.obat.ObatStokRepository;

@Service
public class ObatStokService {

	@Autowired
	private ObatStokRepository obatStokRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(ObatStok obatStok) {
		obatStokRepo.save(obatStok);
	}

	public ObatStok dapatkan(Long id) {
		return obatStokRepo.findOne(id);
	}

	public Page<ObatStok> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return obatStokRepo.findAll(expression, request);
	}
	
	public List<ObatStok> temukanByObats(Obat obat){
		return obatStokRepo.findByObat(obat);
	}

	public void hapus(ObatStok obatStok) {
		obatStokRepo.delete(obatStok);
	}
}
