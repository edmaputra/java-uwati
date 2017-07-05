package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.repository.obat.ObatExpiredRepository;

@Service
public class ObatExpiredService {

	@Autowired
	private ObatExpiredRepository obatExpiredRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(ObatExpired obatExpired) {
		obatExpiredRepo.save(obatExpired);
	}

	public ObatExpired dapatkan(Long id) {
		return obatExpiredRepo.findOne(id);
	}

	public Page<ObatExpired> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return obatExpiredRepo.findAll(expression, request);
	}
	
	public List<ObatExpired> temukanByObats(Obat obat){
		return obatExpiredRepo.findByObat(obat);
	}

	public void hapus(ObatExpired obatExpired) {
		obatExpiredRepo.delete(obatExpired);
	}
}
