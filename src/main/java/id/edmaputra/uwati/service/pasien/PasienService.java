package id.edmaputra.uwati.service.pasien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.Pasien;
import id.edmaputra.uwati.repository.pasien.PasienRepository;

@Service
public class PasienService {

	@Autowired
	private PasienRepository pasienRepository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Pasien pasien) {
		pasienRepository.save(pasien);
	}

	public Pasien dapatkan(Long id) {
		return pasienRepository.findOne(id);
	}

	public Page<Pasien> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return pasienRepository.findAll(expression, request);
	}

	public Page<Pasien> muatDaftar(Integer halaman, Integer size, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, size, Sort.Direction.ASC, "nama");
		return pasienRepository.findAll(expression, request);
	}

	public void hapus(Pasien pasien) {
		pasienRepository.delete(pasien);
	}

	public Pasien dapatkan(String nama) {
		return pasienRepository.findByNama(nama);
	}

	public Pasien dapatkanByIdentitas(String identitas) {
		return pasienRepository.findByIdentitas(identitas);
	}

	public List<Pasien> dapatkanSemua() {
		return pasienRepository.findAll();
	}
}
