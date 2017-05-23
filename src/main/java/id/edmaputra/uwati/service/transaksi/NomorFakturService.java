package id.edmaputra.uwati.service.transaksi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.NomorFaktur;
import id.edmaputra.uwati.repository.transaksi.NomorFakturRepository;
import id.edmaputra.uwati.repository.transaksi.PenjualanDetailTempRepository;

@Service
public class NomorFakturService {

	@Autowired
	private NomorFakturRepository nomorFakturRepository;

	private static final int PAGE_SIZE = 2000;

	public void simpan(NomorFaktur nomorFaktur) {		
		nomorFakturRepository.save(nomorFaktur);
	}

	public NomorFaktur dapatkan(Long id) {
		return nomorFakturRepository.findOne(id);
	}

	public Page<NomorFaktur> muatDaftar(Integer halaman, BooleanExpression expression) {
		PageRequest request = new PageRequest(halaman - 1, PAGE_SIZE, Sort.Direction.ASC, "waktuDibuat");
		return nomorFakturRepository.findAll(expression, request);
	}
	
	public List<NomorFaktur> dapatkanSemua(){
		return nomorFakturRepository.findAll(new Sort(Direction.ASC, "id"));
	}
	
	public void hapus(NomorFaktur nomorFaktur) {
		nomorFakturRepository.delete(nomorFaktur);
	}

	public List<NomorFaktur> dapatkanList(BooleanExpression exp) {
		return (List<NomorFaktur>) nomorFakturRepository.findAll(exp);
	}

	public void hapus(Long index) {
		nomorFakturRepository.delete(index);		
	}	
}
