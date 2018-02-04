package id.edmaputra.uwati.service.transaksi;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.repository.transaksi.PenjualanDetailRepository;

@Service
public class PenjualanDetailService {

	@Autowired
	private PenjualanDetailRepository penjualanDetailRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(PenjualanDetail penjualanDetail) {		
		penjualanDetailRepository.save(penjualanDetail);
	}

	public PenjualanDetail dapatkan(Long id) {
		return penjualanDetailRepository.findOne(id);
	}

	public Page<PenjualanDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return penjualanDetailRepository.findAll(expression, request);
	}
	
	public List<PenjualanDetail> dapatkanSemua(){
		return penjualanDetailRepository.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(PenjualanDetail penjualanDetail) {
		penjualanDetailRepository.delete(penjualanDetail);
	}

	public List<PenjualanDetail> dapatkanList(BooleanExpression exp) {
		return (List<PenjualanDetail>) penjualanDetailRepository.findAll(exp);
	}

	public List<PenjualanDetail> dapatkanByPenjualan(Penjualan penjualan) {
		return penjualanDetailRepository.findByPenjualan(penjualan);
	}
	
	public List<Object[]> top10PenjualanObat(Date from, Date to){		
		return penjualanDetailRepository.top10PenjualanObat(from, to);
	}
}
