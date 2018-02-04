package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.RacikanDetailTemporary;
import id.edmaputra.uwati.repository.obat.RacikanDetailTempRepository;

@Service
public class RacikanDetailTempService {

	@Autowired
	private RacikanDetailTempRepository racikanDetailTempRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(RacikanDetailTemporary racikanDetailTemp) {
		racikanDetailTempRepository.save(racikanDetailTemp);
	}

	public RacikanDetailTemporary dapatkan(Long id) {
		return racikanDetailTempRepository.findOne(id);
	}		

	public Page<RacikanDetailTemporary> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return racikanDetailTempRepository.findAll(expression, request);
	}
	
	public List<RacikanDetailTemporary> dapatkanSemua(){
		return racikanDetailTempRepository.findAll(new Sort(Direction.ASC, "nama"));
	}
	
	public void hapus(RacikanDetailTemporary racikanDetailTemp) {
		racikanDetailTempRepository.delete(racikanDetailTemp);
	}

	public List<RacikanDetailTemporary> dapatkanListByNama(BooleanExpression exp) {
		return (List<RacikanDetailTemporary>) racikanDetailTempRepository.findAll(exp);
	}

	public List<RacikanDetailTemporary> dapatkanListByRandomId(String randomId) {
		// TODO Auto-generated method stub
		return racikanDetailTempRepository.findByRandomId(randomId);
	}

	public RacikanDetailTemporary dapatkan(String randomId, String idObat) {
		return racikanDetailTempRepository.findByRandomIdAndIdObat(randomId, idObat);
	}
	
	public void hapusBatch(String randomId){
		racikanDetailTempRepository.deleteInBatch(dapatkanListByRandomId(randomId));
	}

}
