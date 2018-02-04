package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.ReturPembelianDetailTemp;
import id.edmaputra.uwati.repository.transaksi.ReturPembelianDetailTempRepository;

@Service
public class ReturPembelianDetailTempService {

	@Autowired
	private ReturPembelianDetailTempRepository returPembelianDetailTempRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(ReturPembelianDetailTemp returPembelianDetailTemp) {
		returPembelianDetailTempRepo.save(returPembelianDetailTemp);
	}

	public ReturPembelianDetailTemp dapatkan(Long id) {
		return returPembelianDetailTempRepo.findOne(id);
	}

	public Page<ReturPembelianDetailTemp> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return returPembelianDetailTempRepo.findAll(expression, request);
	}

	public List<ReturPembelianDetailTemp> dapatkanSemua() {
		return returPembelianDetailTempRepo.findAll(new Sort(Direction.ASC, "id"));
	}

	public void hapus(ReturPembelianDetailTemp returPembelianDetailTemp) {
		returPembelianDetailTempRepo.delete(returPembelianDetailTemp);
	}

	public List<ReturPembelianDetailTemp> dapatkanList(BooleanExpression exp) {
		return (List<ReturPembelianDetailTemp>) returPembelianDetailTempRepo.findAll(exp);
	}

	public void hapus(Long index) {
		returPembelianDetailTempRepo.delete(index);
	}

	public List<ReturPembelianDetailTemp> dapatkanListByRandomId(String randomId) {
		return returPembelianDetailTempRepo.findByRandomId(randomId);
	}

	public void hapusBatch(String randomId) {
		returPembelianDetailTempRepo.deleteInBatch(dapatkanListByRandomId(randomId));
	}

	public ReturPembelianDetailTemp dapatkanByRandomIdAndIdObat(String randomId, Long idObat) { 
		return returPembelianDetailTempRepo.findByRandomIdAndIdObat(randomId, idObat);
	}


}
