package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.repository.transaksi.PenjualanRepository;

@Service
public class PenjualanService {

	@Autowired
	private PenjualanRepository penjualanRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Penjualan penjualan) {		
		penjualanRepository.save(penjualan);
	}

	public Penjualan dapatkan(Long id) {
		return penjualanRepository.findOne(id);
	}

	public Page<Penjualan> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return penjualanRepository.findAll(expression, request);
	}
	
	public List<Penjualan> dapatkanSemua(){
		return penjualanRepository.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}
	
	public Penjualan dapatkanByNomorFaktur(String nomorFaktur){
		return penjualanRepository.findByNomorFaktur(nomorFaktur);
	}

	public void hapus(Penjualan penjualan) {
		penjualanRepository.delete(penjualan);
	}

	public List<Penjualan> dapatkanList(BooleanExpression exp) {
		return (List<Penjualan>) penjualanRepository.findAll(exp);
	}
	
	public Penjualan dapatkan(BooleanExpression expression){
		return penjualanRepository.findOne(expression);
	}
}
