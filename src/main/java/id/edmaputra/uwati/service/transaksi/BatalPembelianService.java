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
import id.edmaputra.uwati.repository.transaksi.BatalPembelianRepository;

@Service
public class BatalPembelianService {

	@Autowired
	private BatalPembelianRepository batalPembelianRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(BatalPembelian batalPembelian) {		
		batalPembelianRepository.save(batalPembelian);
	}

	public BatalPembelian dapatkan(Long id) {
		return batalPembelianRepository.findOne(id);
	}

	public Page<BatalPembelian> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return batalPembelianRepository.findAll(expression, request);
	}
	
	public List<BatalPembelian> dapatkanSemua(){
		return batalPembelianRepository.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}
	
	public BatalPembelian dapatkanByNomorFaktur(String nomorFaktur){
		return batalPembelianRepository.findByNomorFaktur(nomorFaktur);
	}

	public void hapus(BatalPembelian batalPembelian) {
		batalPembelianRepository.delete(batalPembelian);
	}

	public List<BatalPembelian> dapatkanList(BooleanExpression exp) {
		return (List<BatalPembelian>) batalPembelianRepository.findAll(exp);
	}
	
	public BatalPembelian dapatkan(BooleanExpression expression){
		return batalPembelianRepository.findOne(expression);
	}
}
