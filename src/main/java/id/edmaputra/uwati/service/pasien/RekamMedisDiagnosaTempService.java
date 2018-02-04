package id.edmaputra.uwati.service.pasien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.RekamMedisDetailTemp;
import id.edmaputra.uwati.entity.pasien.RekamMedisDiagnosaTemp;
import id.edmaputra.uwati.repository.pasien.RekamMedisDiagnosaTempRepository;

@Service
public class RekamMedisDiagnosaTempService {

	@Autowired
	private RekamMedisDiagnosaTempRepository rekamMedisDiagnosaTempRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(RekamMedisDiagnosaTemp rekamMedisDiagnosaTemp) {
		rekamMedisDiagnosaTempRepository.save(rekamMedisDiagnosaTemp);
	}

	public RekamMedisDiagnosaTemp dapatkan(Long id) {
		return rekamMedisDiagnosaTempRepository.findOne(id);
	}

	public Page<RekamMedisDiagnosaTemp> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return rekamMedisDiagnosaTempRepository.findAll(expression, request);
	}

	public Page<RekamMedisDiagnosaTemp> muatDaftar(Integer halaman, Integer size, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return rekamMedisDiagnosaTempRepository.findAll(expression, request);
	}

	public void hapus(RekamMedisDiagnosaTemp rekamMedisDiagnosaTemp) {
		rekamMedisDiagnosaTempRepository.delete(rekamMedisDiagnosaTemp);
	}
		
	public RekamMedisDiagnosaTemp dapatkanByRandomIdAndIdDiagnosa(String randomId, String idDiagnosa){
		return rekamMedisDiagnosaTempRepository.findByRandomIdAndIdDiagnosa(randomId, idDiagnosa);
	}
	
	public void hapusBatchByRandomId(String randomId) {
		rekamMedisDiagnosaTempRepository.deleteInBatch(muatDaftarByRandomId(randomId));		
	}

	private List<RekamMedisDiagnosaTemp> muatDaftarByRandomId(String randomId) {
		// TODO Auto-generated method stub
		return rekamMedisDiagnosaTempRepository.findByRandomId(randomId);
	}
	
	public void hapus(String nomor, String idDiagnosa){
		rekamMedisDiagnosaTempRepository.deleteByNomorAndIdDiagnosa(nomor, idDiagnosa);
	}

	public void hapus(String nomor) {
		rekamMedisDiagnosaTempRepository.deleteByNomor(nomor);		
	}
	
	public void hapusBatchByNomor(String nomor) {
		rekamMedisDiagnosaTempRepository.deleteInBatch(muatDaftarByNomor(nomor));		
	}
	
	public RekamMedisDiagnosaTemp dapatkanByNomorAndIdDiagnosa(String nomor, String idDiagnosa){
		return rekamMedisDiagnosaTempRepository.findByNomorAndIdDiagnosa(nomor, idDiagnosa);
	}
	
	public List<RekamMedisDiagnosaTemp> muatDaftarByNomor(String nomor){
		return rekamMedisDiagnosaTempRepository.findByNomor(nomor);
	}
}
