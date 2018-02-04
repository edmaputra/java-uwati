package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailRacikan;
import id.edmaputra.uwati.repository.transaksi.PenjualanDetailRacikanRepository;

@Service
public class PenjualanDetailRacikanService {

	@Autowired
	private PenjualanDetailRacikanRepository penjualanDetailRacikanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(PenjualanDetailRacikan penjualanDetailRacikan) {		
		penjualanDetailRacikanRepository.save(penjualanDetailRacikan);
	}

	public PenjualanDetailRacikan dapatkan(Long id) {
		return penjualanDetailRacikanRepository.findOne(id);
	}

	public Page<PenjualanDetailRacikan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return penjualanDetailRacikanRepository.findAll(expression, request);
	}
	
	public List<PenjualanDetailRacikan> dapatkanSemua(){
		return penjualanDetailRacikanRepository.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(PenjualanDetailRacikan penjualanDetailRacikan) {
		penjualanDetailRacikanRepository.delete(penjualanDetailRacikan);
	}

	public List<PenjualanDetailRacikan> dapatkanList(BooleanExpression exp) {
		return (List<PenjualanDetailRacikan>) penjualanDetailRacikanRepository.findAll(exp);
	}

	public List<PenjualanDetailRacikan> dapatkanByPenjualanDetail(PenjualanDetail penjualanDetail) {
		return penjualanDetailRacikanRepository.findByPenjualanDetail(penjualanDetail);
	}
}
