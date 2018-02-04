package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.PenjualanDetailTemp;
import id.edmaputra.uwati.repository.transaksi.PenjualanDetailTempRepository;

@Service
public class PenjualanDetailTempService {

	@Autowired
	private PenjualanDetailTempRepository penjualanDetailTempRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(PenjualanDetailTemp penjualanDetailTemp) {		
		penjualanDetailTempRepo.save(penjualanDetailTemp);
	}

	public PenjualanDetailTemp dapatkan(Long id) {
		return penjualanDetailTempRepo.findOne(id);
	}
	
	public PenjualanDetailTemp dapatkan(Long id, String nomorFaktur) {
		return penjualanDetailTempRepo.findByIdAndNomorFaktur(id, nomorFaktur);
	}
	
	public PenjualanDetailTemp dapatkanByObatAndNomorFaktur(String obat, String nomorFaktur) {
		return penjualanDetailTempRepo.findByObatAndNomorFaktur(obat, nomorFaktur);
	}

	public Page<PenjualanDetailTemp> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return penjualanDetailTempRepo.findAll(expression, request);
	}
	
	public List<PenjualanDetailTemp> dapatkanSemua(){
		return penjualanDetailTempRepo.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(PenjualanDetailTemp penjualanDetailTemp) {
		penjualanDetailTempRepo.delete(penjualanDetailTemp);
	}

	public List<PenjualanDetailTemp> dapatkanList(BooleanExpression exp) {
		return (List<PenjualanDetailTemp>) penjualanDetailTempRepo.findAll(exp);
	}

	public void hapus(Long index) {
		penjualanDetailTempRepo.delete(index);		
	}
	
	public void hapus(String nomorFaktur, String pengguna){
		penjualanDetailTempRepo.deleteByNomorFakturAndPengguna(nomorFaktur, pengguna);
	}

	public List<PenjualanDetailTemp> dapatkan(String nomorFakturTemp) {
		return penjualanDetailTempRepo.findByNomorFaktur(nomorFakturTemp);
	}

	public List<PenjualanDetailTemp> dapatkan(String nomorFaktur, String pengguna) {
		return penjualanDetailTempRepo.findByNomorFakturAndPengguna(nomorFaktur, pengguna);
	}
	
	public PenjualanDetailTemp dapatkanByRandomIdAndIdObat(String randomId, String idObat){
		return penjualanDetailTempRepo.findByRandomIdAndIdObat(randomId, idObat);
	}

	public List<PenjualanDetailTemp> dapatkanListByRandomId(String randomId) {
		return penjualanDetailTempRepo.findByRandomId(randomId);
	}

	public void hapusBatch(String randomId) {
		penjualanDetailTempRepo.deleteInBatch(dapatkanListByRandomId(randomId));		
	}
}
