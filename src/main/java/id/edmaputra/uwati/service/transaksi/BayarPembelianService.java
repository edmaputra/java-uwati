package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.BayarPembelian;
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.repository.transaksi.BayarPembelianRepository;

@Service
public class BayarPembelianService {

	@Autowired
	private BayarPembelianRepository bayarPembelianRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(BayarPembelian bayarPenjualan) {		
		bayarPembelianRepository.save(bayarPenjualan);
	}

	public BayarPembelian dapatkan(Long id) {
		return bayarPembelianRepository.findOne(id);
	}

	public Page<BayarPembelian> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return bayarPembelianRepository.findAll(expression, request);
	}
	
	public List<BayarPembelian> dapatkanByPembelian(Pembelian pembelian){
		return bayarPembelianRepository.findByPembelian(pembelian);
	}
	
	public List<BayarPembelian> dapatkanSemua(){
		return bayarPembelianRepository.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}
	
	public void hapus(BayarPembelian bayarPenjualan) {
		bayarPembelianRepository.delete(bayarPenjualan);
	}

	public List<BayarPembelian> dapatkanList(BooleanExpression exp) {
		return (List<BayarPembelian>) bayarPembelianRepository.findAll(exp);
	}
	
	public BayarPembelian dapatkan(BooleanExpression expression){
		return bayarPembelianRepository.findOne(expression);
	}
}
