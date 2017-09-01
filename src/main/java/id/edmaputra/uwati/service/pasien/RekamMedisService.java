package id.edmaputra.uwati.service.pasien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.Pasien;
import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.repository.pasien.RekamMedisRepository;


@Service
public class RekamMedisService {

	@Autowired
	private RekamMedisRepository rekamMedisRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(RekamMedis rekamMedis) {
		rekamMedisRepository.save(rekamMedis);
	}

	public RekamMedis dapatkan(Long id) {
		return rekamMedisRepository.findOne(id);
	}

	public Page<RekamMedis> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuDibuat");
		return rekamMedisRepository.findAll(expression, request);
	}
	
	public Page<RekamMedis> muatDaftar(Integer halaman, Integer size, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "id");
		return rekamMedisRepository.findAll(expression, request);
	}

	public void hapus(RekamMedis rekamMedis) {
		rekamMedisRepository.delete(rekamMedis);
	}

	public RekamMedis dapatkan(String nomor) {
		return rekamMedisRepository.findByNomor(nomor);
	}

	public List<RekamMedis> temukanByPasien(Pasien pasien) {
		return rekamMedisRepository.findByPasien(pasien);
	}

	public Page<RekamMedis> muatDaftarResep(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.DESC, "waktuDibuat");
		return rekamMedisRepository.findAll(expression, request);
	}

	public List<RekamMedis> dapatkanList(BooleanExpression exp) {
		return (List<RekamMedis>) rekamMedisRepository.findAll(exp);
	}
}
