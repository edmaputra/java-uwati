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
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.repository.transaksi.PembelianDetailRepository;

@Service
public class PembelianDetailService {

	@Autowired
	private PembelianDetailRepository repository;

	private static final int PAGE_SIZE = 25;

	public void simpan(PembelianDetail pembelianDetail) {		
		repository.save(pembelianDetail);
	}

	public PembelianDetail dapatkan(Long id) {
		return repository.findOne(id);
	}
	
	public PembelianDetail dapatkan(Long id, String nomorFaktur, String supplier) {
		return repository.findByIdAndNomorFakturAndSupplier(id, nomorFaktur, supplier);
	}
	
	public PembelianDetail dapatkan(Long id, Pembelian pembelian) {
		return repository.findByIdAndPembelian(id, pembelian);
	}
	
	public PembelianDetail dapatkan(String obat, Pembelian pembelian) {
		return repository.findByObatAndPembelian(obat, pembelian);
	}
	
	public PembelianDetail dapatkan(String obat, String nomorFaktur, String supplier) {
		return repository.findByObatAndNomorFakturAndSupplier(obat, nomorFaktur, supplier);
	}

	public Page<PembelianDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return repository.findAll(expression, request);
	}
	
	public List<PembelianDetail> dapatkanSemua(){
		return repository.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(PembelianDetail pembelianDetail) {
		repository.delete(pembelianDetail);
	}

	public List<PembelianDetail> dapatkanList(BooleanExpression exp) {
		return (List<PembelianDetail>) repository.findAll(exp);
	}

	public List<PembelianDetail> dapatkanByPembelian(Pembelian pembelian) {
		return repository.findByPembelian(pembelian);
	}
	
	public void setStatusReturObat(String obat, String nomorFaktur, String supplier, int status) {
		repository.setStatusReturObat(obat, nomorFaktur, supplier, status);
	}
}
