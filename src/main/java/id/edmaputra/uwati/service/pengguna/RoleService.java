package id.edmaputra.uwati.service.pengguna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pengguna.Role;
import id.edmaputra.uwati.repository.pengguna.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	private static final int PAGE_SIZE = 25;

	public void simpan(Role role) {
		repository.save(role);
	}

	public Page<Role> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "nama");
		return repository.findAll(expression, request);
	}

	public Role dapatkan(Integer id) {
		return repository.findOne(id);
	}

	public List<Role> dapatkanSemua() {
		return repository.findAll();
	}

	public void hapus(Role role) {
		repository.delete(role);

	}

	public Role dapatkanByNama(String nama) {
		return repository.findByNama(nama);
	}
	
	public List<Role> dapatkanListByNama(BooleanExpression expression){
		return (List<Role>) repository.findAll(expression);
	}

}
