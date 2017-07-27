package id.edmaputra.uwati.service.obat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.CekStok;
import id.edmaputra.uwati.repository.obat.CekStokRepository;

@Service
public class CekStokService {

	@Autowired
	private CekStokRepository cekStokRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(CekStok cekStok) {
		cekStokRepository.save(cekStok);
	}

	public CekStok dapatkan(Integer id) {
		return cekStokRepository.findOne(id);
	}

	public Page<CekStok> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return cekStokRepository.findAll(expression, request);
	}

	public void hapus(CekStok cekStok) {
		cekStokRepository.delete(cekStok);
	}

	public List<CekStok> dapatkanListByNama(BooleanExpression exp) {
		return (List<CekStok>) cekStokRepository.findAll(exp);
	}
	
	public List<CekStok> dapatkanListByRandomId(String randomId){
		return cekStokRepository.findByRandomId(randomId);
	}
	
	public void hapusBatch(String randomId){
		cekStokRepository.deleteInBatch(dapatkanListByRandomId(randomId));
	}
	
	public CekStok dapatkanByRandomIdAndIdObat(String randomId, String idObat){
		return cekStokRepository.findByRandomIdAndIdObat(randomId, idObat);
	}
	
	public CekStok dapatkanByRandomIdAndNamaObat(String randomId, String obat){
		return cekStokRepository.findByRandomIdAndObat(randomId, obat);
	}
}
