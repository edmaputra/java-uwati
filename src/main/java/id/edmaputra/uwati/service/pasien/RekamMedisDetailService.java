package id.edmaputra.uwati.service.pasien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetail;
import id.edmaputra.uwati.repository.pasien.RekamMedisDetailRepository;

@Service
public class RekamMedisDetailService {

	@Autowired
	private RekamMedisDetailRepository rekamMedisDetailRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(RekamMedisDetail rekamMedisDetail) {
		rekamMedisDetailRepository.save(rekamMedisDetail);
	}

	public RekamMedisDetail dapatkan(Long id) {
		return rekamMedisDetailRepository.findOne(id);
	}

	public Page<RekamMedisDetail> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return rekamMedisDetailRepository.findAll(expression, request);
	}

	public Page<RekamMedisDetail> muatDaftar(Integer halaman, Integer size, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return rekamMedisDetailRepository.findAll(expression, request);
	}

	public void hapus(RekamMedisDetail rekamMedisDetail) {
		rekamMedisDetailRepository.delete(rekamMedisDetail);
	}
	
	public void hapus(RekamMedis rekamMedis) {
		rekamMedisDetailRepository.deleteByRekamMedis(rekamMedis);
	}
	
	public void hapusBatch(RekamMedis rekamMedis){
		rekamMedisDetailRepository.deleteInBatch(dapatkan(rekamMedis));
	}

	public List<RekamMedisDetail> dapatkan(RekamMedis rekamMedis) {
		return rekamMedisDetailRepository.findByRekamMedis(rekamMedis);
	}
	
	public List<RekamMedisDetail> dapatkanBiaya(RekamMedis rekamMedis, Integer tipe){
		return rekamMedisDetailRepository.findByRekamMedisAndTipe(rekamMedis, tipe);
	}
	
	public RekamMedisDetail dapatkanBiayaResep(RekamMedis rekamMedis, String terapi){
		return rekamMedisDetailRepository.findByRekamMedisAndTerapi(rekamMedis, terapi);
	}
	
	public RekamMedisDetail dapatkanBiayaRekamMedisDanTerapi(RekamMedis rekamMedis, String terapi){
		return rekamMedisDetailRepository.findByRekamMedisAndTerapi(rekamMedis, terapi);
	}


	public RekamMedisDetail dapatkan(String nama) {
		return rekamMedisDetailRepository.findByTerapi(nama);
	}

	public void hapusBatch(List<RekamMedisDetail> list) {
		rekamMedisDetailRepository.deleteInBatch(list);	
	}
}
