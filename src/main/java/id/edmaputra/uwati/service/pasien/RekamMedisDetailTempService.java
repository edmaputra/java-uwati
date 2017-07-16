package id.edmaputra.uwati.service.pasien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetailTemp;
import id.edmaputra.uwati.repository.pasien.RekamMedisDetailTempRepository;

@Service
public class RekamMedisDetailTempService {

	@Autowired
	private RekamMedisDetailTempRepository rekamMedisDetailTempRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(RekamMedisDetailTemp rekamMedisDetailTemp) {
		rekamMedisDetailTempRepository.save(rekamMedisDetailTemp);
	}

	public RekamMedisDetailTemp dapatkan(Long id) {
		return rekamMedisDetailTempRepository.findOne(id);
	}

	public Page<RekamMedisDetailTemp> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return rekamMedisDetailTempRepository.findAll(expression, request);
	}

	public Page<RekamMedisDetailTemp> muatDaftar(Integer halaman, Integer size, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return rekamMedisDetailTempRepository.findAll(expression, request);
	}

	public void hapus(RekamMedisDetailTemp rekamMedisDetailTemp) {
		rekamMedisDetailTempRepository.delete(rekamMedisDetailTemp);
	}
	
	public List<RekamMedisDetailTemp> muatDaftar(String nomor){
		return rekamMedisDetailTempRepository.findByNomor(nomor);
	}
	
	public RekamMedisDetailTemp dapatkan(String nomor, String idObat){
		return rekamMedisDetailTempRepository.findByNomorAndIdObat(nomor, idObat);
	}
	
	public void hapus(String nomor, String idObat){
		rekamMedisDetailTempRepository.deleteByNomorAndIdObat(nomor, idObat);
	}

	public void hapus(String nomor) {
		rekamMedisDetailTempRepository.deleteByNomor(nomor);		
	}
	
	public void hapusBatch(String nomor) {
		rekamMedisDetailTempRepository.deleteInBatch(muatDaftar(nomor));		
	}
}
