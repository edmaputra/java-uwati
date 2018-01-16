package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.ReturPembelian;
import id.edmaputra.uwati.repository.transaksi.ReturPembelianRepository;

@Service
public class ReturPembelianService {

	@Autowired
	private ReturPembelianRepository repository;
	
	private static final int PAGE_SIZE = 25;

	public void simpan(ReturPembelian returPembelian) {		
		repository.save(returPembelian);
	}

	public ReturPembelian dapatkan(Long id) {
		return repository.findOne(id);
	}

	public Page<ReturPembelian> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return repository.findAll(expression, request);
	}
	
	public Page<ReturPembelian> muatDaftarAndPembayaran(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return repository.findAll(expression, request);
	}
	
	public List<ReturPembelian> dapatkanSemua(){
		return repository.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}	

	public void hapus(ReturPembelian returPembelian) {
		repository.delete(returPembelian);
	}

	public List<ReturPembelian> dapatkanList(BooleanExpression exp) {
		return (List<ReturPembelian>) repository.findAll(exp);
	}
	
	public ReturPembelian dapatkan(BooleanExpression expression){
		return repository.findOne(expression);
	}
}
