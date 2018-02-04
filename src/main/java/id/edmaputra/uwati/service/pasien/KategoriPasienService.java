package id.edmaputra.uwati.service.pasien;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.KategoriPasien;
import id.edmaputra.uwati.repository.pasien.KategoriPasienRepository;

@Service
public class KategoriPasienService {

	@Autowired
	private KategoriPasienRepository kategoriRepo;

	private static final int PAGE_SIZE = 25;

	public void simpan(KategoriPasien kategoriPasien) {
		kategoriRepo.save(kategoriPasien);
	}

	public KategoriPasien dapatkan(Integer id) {
		return kategoriRepo.findOne(id);
	}
	
	public void hapus(KategoriPasien kategoriPasien) {
		kategoriRepo.delete(kategoriPasien);
	}

	public Page<KategoriPasien> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return kategoriRepo.findAll(expression, request);
	}
	
	public List<KategoriPasien> dapatkanSemua(){
		return kategoriRepo.findAll(new Sort(Direction.ASC, "nama"));
	}
	
	public KategoriPasien dapatkanByNama(String nama){
		return kategoriRepo.findByNama(nama);
	}

	public List<KategoriPasien> dapatkanListByNama(BooleanExpression exp) {
		return (List<KategoriPasien>) kategoriRepo.findAll(exp);
	}

	
}
