package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.BatalPenjualan;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.repository.transaksi.BatalPenjualanDetailRepository;

@Service
public class BatalPenjualanDetailService {

	@Autowired
	private BatalPenjualanDetailRepository batalPenjualanDetailRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(BatalPenjualanDetail batalPenjualanDetail) {		
		batalPenjualanDetailRepository.save(batalPenjualanDetail);
	}

	public BatalPenjualanDetail dapatkan(Long id) {
		return batalPenjualanDetailRepository.findOne(id);
	}

	public Page<BatalPenjualanDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return batalPenjualanDetailRepository.findAll(expression, request);
	}
	
	public List<BatalPenjualanDetail> dapatkanSemua(){
		return batalPenjualanDetailRepository.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(BatalPenjualanDetail batalPenjualanDetail) {
		batalPenjualanDetailRepository.delete(batalPenjualanDetail);
	}

	public List<BatalPenjualanDetail> dapatkanList(BooleanExpression exp) {
		return (List<BatalPenjualanDetail>) batalPenjualanDetailRepository.findAll(exp);
	}

	public List<BatalPenjualanDetail> dapatkanByBatalPenjualan(BatalPenjualan batalPenjualan) {
		return batalPenjualanDetailRepository.findByBatalPenjualan(batalPenjualan);
	}
}
