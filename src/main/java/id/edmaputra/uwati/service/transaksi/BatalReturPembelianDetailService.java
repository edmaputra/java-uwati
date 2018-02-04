package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.BatalReturPembelianDetail;
import id.edmaputra.uwati.repository.transaksi.BatalReturPembelianDetailRepository;

@Service
public class BatalReturPembelianDetailService {

	@Autowired
	private BatalReturPembelianDetailRepository repository;
	
	private static final int PAGE_SIZE = 25;

	public void simpan(BatalReturPembelianDetail batalReturPembelianDetail) {		
		repository.save(batalReturPembelianDetail);
	}

	public BatalReturPembelianDetail dapatkan(Long id) {
		return repository.findOne(id);
	}

	public Page<BatalReturPembelianDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return repository.findAll(expression, request);
	}
	
	public Page<BatalReturPembelianDetail> muatDaftarAndPembayaran(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return repository.findAll(expression, request);
	}
	
	public List<BatalReturPembelianDetail> dapatkanSemua(){
		return repository.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}	

	public void hapus(BatalReturPembelianDetail batalReturPembelianDetail) {
		repository.delete(batalReturPembelianDetail);
	}

	public List<BatalReturPembelianDetail> dapatkanList(BooleanExpression exp) {
		return (List<BatalReturPembelianDetail>) repository.findAll(exp);
	}
	
	public BatalReturPembelianDetail dapatkan(BooleanExpression expression){
		return repository.findOne(expression);
	}
}
