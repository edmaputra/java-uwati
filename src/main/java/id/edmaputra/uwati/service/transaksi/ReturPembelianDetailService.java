package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.ReturPembelianDetail;
import id.edmaputra.uwati.repository.transaksi.ReturPembelianDetailRepository;

@Service
public class ReturPembelianDetailService {

	@Autowired
	private ReturPembelianDetailRepository repository;
	
	private static final int PAGE_SIZE = 25;

	public void simpan(ReturPembelianDetail returPembelianDetail) {		
		repository.save(returPembelianDetail);
	}

	public ReturPembelianDetail dapatkan(Long id) {
		return repository.findOne(id);
	}

	public Page<ReturPembelianDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "tanggal");
		return repository.findAll(expression, request);
	}
	
	public Page<ReturPembelianDetail> muatDaftarAndPembayaran(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "tanggal");
		return repository.findAll(expression, request);
	}
	
	public List<ReturPembelianDetail> dapatkanSemua(){
		return repository.findAll(new Sort(Direction.ASC, "tanggal"));
	}	

	public void hapus(ReturPembelianDetail returPembelianDetail) {
		repository.delete(returPembelianDetail);
	}

	public List<ReturPembelianDetail> dapatkanList(BooleanExpression exp) {
		return (List<ReturPembelianDetail>) repository.findAll(exp);
	}
	
	public ReturPembelianDetail dapatkan(BooleanExpression expression){
		return repository.findOne(expression);
	}
}
