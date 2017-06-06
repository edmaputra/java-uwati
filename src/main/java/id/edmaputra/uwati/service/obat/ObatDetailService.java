package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.repository.obat.ObatDetailRepository;

@Service
public class ObatDetailService {

	@Autowired
	private ObatDetailRepository obatDetailRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(ObatDetail obatDetail) {
		obatDetailRepo.save(obatDetail);
	}

	public ObatDetail dapatkan(Long id) {
		return obatDetailRepo.findOne(id);
	}	

	public Page<ObatDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return obatDetailRepo.findAll(expression, request);
	}
	
	public List<ObatDetail> temukanByObat(Obat obat){
		return obatDetailRepo.findByObat(obat);
	}

	public void hapus(ObatDetail obatDetail) {
		obatDetailRepo.delete(obatDetail);
	}
}
