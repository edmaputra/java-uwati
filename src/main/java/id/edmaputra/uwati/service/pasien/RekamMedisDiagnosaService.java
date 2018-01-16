package id.edmaputra.uwati.service.pasien;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDiagnosa;
import id.edmaputra.uwati.repository.pasien.RekamMedisDiagnosaRepository;

@Service
public class RekamMedisDiagnosaService {

	@Autowired
	private RekamMedisDiagnosaRepository rekamMedisDiagnosaRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(RekamMedisDiagnosa rekamMedisDiagnosa) {
		rekamMedisDiagnosaRepository.save(rekamMedisDiagnosa);
	}

	public RekamMedisDiagnosa dapatkan(Long id) {
		return rekamMedisDiagnosaRepository.findOne(id);
	}

	public Page<RekamMedisDiagnosa> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return rekamMedisDiagnosaRepository.findAll(expression, request);
	}

	public Page<RekamMedisDiagnosa> muatDaftar(Integer halaman, Integer size, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return rekamMedisDiagnosaRepository.findAll(expression, request);
	}

	public void hapus(RekamMedisDiagnosa rekamMedisDiagnosa) {
		rekamMedisDiagnosaRepository.delete(rekamMedisDiagnosa);
	}
	
	public void hapus(RekamMedis rekamMedis) {
		rekamMedisDiagnosaRepository.deleteByRekamMedis(rekamMedis);
	}
	
	public void hapusBatch(RekamMedis rekamMedis){
		rekamMedisDiagnosaRepository.deleteInBatch(dapatkan(rekamMedis));
	}

	public List<RekamMedisDiagnosa> dapatkan(RekamMedis rekamMedis) {
		return rekamMedisDiagnosaRepository.findByRekamMedis(rekamMedis);
	}
	
	public List<Object[]> top10Diagnosa(Date from, Date to){
		return rekamMedisDiagnosaRepository.top10Diagnosa(from, to);
	}
}
