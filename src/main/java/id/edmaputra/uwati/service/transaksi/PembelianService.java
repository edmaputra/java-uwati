package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.repository.transaksi.PembelianRepository;

@Service
public class PembelianService {

	@Autowired
	private PembelianRepository pembelianRepo;
	
	private static final int PAGE_SIZE = 25;

	public void simpan(Pembelian pembelian) {		
		pembelianRepo.save(pembelian);
	}

	public Pembelian dapatkan(Long id) {
		return pembelianRepo.findOne(id);
	}

	public Page<Pembelian> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return pembelianRepo.findAll(expression, request);
	}
	
	public Page<Pembelian> muatDaftarAndPembayaran(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuTransaksi");
		return pembelianRepo.findAll(expression, request);
	}
	
	public List<Pembelian> dapatkanSemua(){
		return pembelianRepo.findAll(new Sort(Direction.ASC, "waktuTransaksi"));
	}
	
	public Pembelian dapatkanByNomorFaktur(String nomorFaktur){
		return pembelianRepo.findByNomorFaktur(nomorFaktur);
	}

	public void hapus(Pembelian pembelian) {
		pembelianRepo.delete(pembelian);
	}

	public List<Pembelian> dapatkanList(BooleanExpression exp) {
		return (List<Pembelian>) pembelianRepo.findAll(exp);
	}
	
	public Pembelian dapatkan(BooleanExpression expression){
		return pembelianRepo.findOne(expression);
	}

	public Pembelian dapatkan(String nomorFaktur, String supplier) {
		return pembelianRepo.findByNomorFakturAndSupplier(nomorFaktur, supplier);
	}
}
