package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.BayarPenjualan;
import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.repository.transaksi.BayarPenjualanRepository;

@Service
public class BayarPenjualanService {

	@Autowired
	private BayarPenjualanRepository bayarPenjualanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(BayarPenjualan bayarPenjualan) {		
		bayarPenjualanRepository.save(bayarPenjualan);
	}

	public BayarPenjualan dapatkan(Long id) {
		return bayarPenjualanRepository.findOne(id);
	}

	public Page<BayarPenjualan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return bayarPenjualanRepository.findAll(expression, request);
	}
	
	public List<BayarPenjualan> dapatkanSemua(){
		return bayarPenjualanRepository.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}
	
	public void hapus(BayarPenjualan bayarPenjualan) {
		bayarPenjualanRepository.delete(bayarPenjualan);
	}

	public List<BayarPenjualan> dapatkanList(BooleanExpression exp) {
		return (List<BayarPenjualan>) bayarPenjualanRepository.findAll(exp);
	}
	
	public BayarPenjualan dapatkan(BooleanExpression expression){
		return bayarPenjualanRepository.findOne(expression);
	}

	public List<BayarPenjualan> dapatkanByPenjualan(Penjualan p) {
		return bayarPenjualanRepository.findByPenjualan(p);
	}
}
