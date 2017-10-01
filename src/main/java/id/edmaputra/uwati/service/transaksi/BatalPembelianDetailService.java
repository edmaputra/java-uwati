package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.BatalPembelian;
import id.edmaputra.uwati.entity.transaksi.BatalPembelianDetail;
import id.edmaputra.uwati.repository.transaksi.BatalPembelianDetailRepository;

@Service
public class BatalPembelianDetailService {

	@Autowired
	private BatalPembelianDetailRepository batalPembelianDetailRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(BatalPembelianDetail batalPembelianDetail) {		
		batalPembelianDetailRepository.save(batalPembelianDetail);
	}

	public BatalPembelianDetail dapatkan(Long id) {
		return batalPembelianDetailRepository.findOne(id);
	}

	public Page<BatalPembelianDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return batalPembelianDetailRepository.findAll(expression, request);
	}
	
	public List<BatalPembelianDetail> dapatkanSemua(){
		return batalPembelianDetailRepository.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(BatalPembelianDetail batalPembelianDetail) {
		batalPembelianDetailRepository.delete(batalPembelianDetail);
	}

	public List<BatalPembelianDetail> dapatkanList(BooleanExpression exp) {
		return (List<BatalPembelianDetail>) batalPembelianDetailRepository.findAll(exp);
	}

	public List<BatalPembelianDetail> dapatkanByBatalPembelian(BatalPembelian batalPembelian) {
		return batalPembelianDetailRepository.findByBatalPembelian(batalPembelian);
	}
}
