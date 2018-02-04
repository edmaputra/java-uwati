package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.PembelianDetailTemp;
import id.edmaputra.uwati.repository.transaksi.PembelianDetailTempRepository;

@Service
public class PembelianDetailTempService {

	@Autowired
	private PembelianDetailTempRepository pembelianDetailTempRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(PembelianDetailTemp pembelianDetailTemp) {
		pembelianDetailTempRepo.save(pembelianDetailTemp);
	}

	public PembelianDetailTemp dapatkan(Long id) {
		return pembelianDetailTempRepo.findOne(id);
	}

	public Page<PembelianDetailTemp> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return pembelianDetailTempRepo.findAll(expression, request);
	}

	public List<PembelianDetailTemp> dapatkanSemua() {
		return pembelianDetailTempRepo.findAll(new Sort(Direction.ASC, "id"));
	}

	public void hapus(PembelianDetailTemp pembelianDetailTemp) {
		pembelianDetailTempRepo.delete(pembelianDetailTemp);
	}

	public List<PembelianDetailTemp> dapatkanList(BooleanExpression exp) {
		return (List<PembelianDetailTemp>) pembelianDetailTempRepo.findAll(exp);
	}

	public void hapus(Long index) {
		pembelianDetailTempRepo.delete(index);
	}

	public void hapus(String nomorFaktur, String pengguna) {
		pembelianDetailTempRepo.deleteByNomorFakturAndPengguna(nomorFaktur, pengguna);
	}

	public PembelianDetailTemp dapatkanByRandomIdAndIdObat(String randomId, Long idObat) {
		return pembelianDetailTempRepo.findByRandomIdAndIdObat(randomId, idObat);
	}

	public List<PembelianDetailTemp> dapatkanListByRandomId(String randomId) {
		return pembelianDetailTempRepo.findByRandomId(randomId);
	}

	public void hapusBatch(String randomId) {
		pembelianDetailTempRepo.deleteInBatch(dapatkanListByRandomId(randomId));
	}
}
