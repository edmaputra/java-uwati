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
import id.edmaputra.uwati.repository.transaksi.BatalPenjualanRepository;

@Service
public class BatalPenjualanService {

	@Autowired
	private BatalPenjualanRepository batalPenjualanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(BatalPenjualan batalPenjualan) {		
		batalPenjualanRepository.save(batalPenjualan);
	}

	public BatalPenjualan dapatkan(Long id) {
		return batalPenjualanRepository.findOne(id);
	}

	public Page<BatalPenjualan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return batalPenjualanRepository.findAll(expression, request);
	}
	
	public List<BatalPenjualan> dapatkanSemua(){
		return batalPenjualanRepository.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}
	
	public BatalPenjualan dapatkanByNomorFaktur(String nomorFaktur){
		return batalPenjualanRepository.findByNomorFaktur(nomorFaktur);
	}

	public void hapus(BatalPenjualan batalPenjualan) {
		batalPenjualanRepository.delete(batalPenjualan);
	}

	public List<BatalPenjualan> dapatkanList(BooleanExpression exp) {
		return (List<BatalPenjualan>) batalPenjualanRepository.findAll(exp);
	}
	
	public BatalPenjualan dapatkan(BooleanExpression expression){
		return batalPenjualanRepository.findOne(expression);
	}
}
