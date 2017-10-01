package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetailRacikan;
import id.edmaputra.uwati.repository.transaksi.BatalPenjualanDetailRacikanRepository;

@Service
public class BatalPenjualanDetailRacikanService {

	@Autowired
	private BatalPenjualanDetailRacikanRepository batalPenjualanDetailRacikanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(BatalPenjualanDetailRacikan batalPenjualanDetailRacikan) {		
		batalPenjualanDetailRacikanRepository.save(batalPenjualanDetailRacikan);
	}

	public BatalPenjualanDetailRacikan dapatkan(Long id) {
		return batalPenjualanDetailRacikanRepository.findOne(id);
	}

	public Page<BatalPenjualanDetailRacikan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return batalPenjualanDetailRacikanRepository.findAll(expression, request);
	}
	
	public List<BatalPenjualanDetailRacikan> dapatkanSemua(){
		return batalPenjualanDetailRacikanRepository.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(BatalPenjualanDetailRacikan batalPenjualanDetailRacikan) {
		batalPenjualanDetailRacikanRepository.delete(batalPenjualanDetailRacikan);
	}

	public List<BatalPenjualanDetailRacikan> dapatkanList(BooleanExpression exp) {
		return (List<BatalPenjualanDetailRacikan>) batalPenjualanDetailRacikanRepository.findAll(exp);
	}

	public List<BatalPenjualanDetailRacikan> dapatkanByBatalPenjualanDetail(BatalPenjualanDetail batalPenjualanDetail) {
		return batalPenjualanDetailRacikanRepository.findByBatalPenjualanDetail(batalPenjualanDetail);
	}
}
