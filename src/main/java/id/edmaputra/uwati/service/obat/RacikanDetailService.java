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
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;
import id.edmaputra.uwati.repository.obat.RacikanDetailRepository;

@Service
public class RacikanDetailService {

	@Autowired
	private RacikanDetailRepository racikanDetailRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(RacikanDetail racikanDetail) {
		racikanDetailRepository.save(racikanDetail);
	}

	public RacikanDetail dapatkan(Long id) {
		return racikanDetailRepository.findOne(id);
	}

	public Page<RacikanDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return racikanDetailRepository.findAll(expression, request);
	}
	
	public List<RacikanDetail> dapatkanSemua(){
		return racikanDetailRepository.findAll(new Sort(Direction.ASC, "nama"));
	}
	
	public void hapus(RacikanDetail racikanDetail) {
		racikanDetailRepository.delete(racikanDetail);
	}

	public List<RacikanDetail> dapatkanListByNama(BooleanExpression exp) {
		return (List<RacikanDetail>) racikanDetailRepository.findAll(exp);
	}

	public List<RacikanDetail> dapatkanByRacikan(Racikan racikan) {		
		return racikanDetailRepository.findByRacikan(racikan);
	}

	public void hapusBatch(Racikan r) {
		racikanDetailRepository.deleteInBatch(dapatkanByRacikan(r));		
	}
}
