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
	private PembelianDetailRepository pembelianDetailRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(PembelianDetail pembelianDetail) {		
		pembelianDetailRepo.save(pembelianDetail);
	}

	public PembelianDetail dapatkan(Long id) {
		return pembelianDetailRepo.findOne(id);
	}
	
	public PembelianDetail dapatkan(Long id, String nomorFaktur, String supplier) {
		return pembelianDetailRepo.findByIdAndNomorFakturAndSupplier(id, nomorFaktur, supplier);
	}
	
	public PembelianDetail dapatkan(String obat, String nomorFaktur, String supplier) {
		return pembelianDetailRepo.findByObatAndNomorFakturAndSupplier(obat, nomorFaktur, supplier);
	}

	public Page<PembelianDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return pembelianDetailRepo.findAll(expression, request);
	}
	
	public List<PembelianDetail> dapatkanSemua(){
		return pembelianDetailRepo.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(PembelianDetail pembelianDetail) {
		pembelianDetailRepo.delete(pembelianDetail);
	}

	public List<PembelianDetail> dapatkanList(BooleanExpression exp) {
		return (List<PembelianDetail>) pembelianDetailRepo.findAll(exp);
	}

	public List<PembelianDetail> dapatkanByPembelian(Pembelian pembelian) {
		return pembelianDetailRepo.findByPembelian(pembelian);
	}
}
