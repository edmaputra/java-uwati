package id.edmaputra.uwati.specification;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.QPasien;

public class PasienPredicateBuilder {
	
	private BooleanExpression hasil = null;
	
	public BooleanExpression getExpression(){
		return hasil;
	}
	
	public void cari(String cari){
		if (hasil == null){
			hasil = QPasien.pasien.nama.containsIgnoreCase(cari)
					.or(QPasien.pasien.identitas.containsIgnoreCase(cari))
					.or(QPasien.pasien.alamat.containsIgnoreCase(cari))
					.or(QPasien.pasien.kontak.containsIgnoreCase(cari))
					.or(QPasien.pasien.jaminanKesehatan.containsIgnoreCase(cari))
					.or(QPasien.pasien.nomorJaminanKesehatan.containsIgnoreCase(cari))
					.or(QPasien.pasien.pekerjaan.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QPasien.pasien.nama.containsIgnoreCase(cari)
					.or(QPasien.pasien.identitas.containsIgnoreCase(cari))
					.or(QPasien.pasien.alamat.containsIgnoreCase(cari))
					.or(QPasien.pasien.kontak.containsIgnoreCase(cari))
					.or(QPasien.pasien.jaminanKesehatan.containsIgnoreCase(cari))
					.or(QPasien.pasien.nomorJaminanKesehatan.containsIgnoreCase(cari))
					.or(QPasien.pasien.pekerjaan.containsIgnoreCase(cari)));
		}
	}
	
	public void kategori(Integer id){
		if (hasil == null){
			hasil = QPasien.pasien.kategoriPasien.id.eq(id);					
		} else {
			hasil = hasil.and(QPasien.pasien.kategoriPasien.id.eq(id));
		}
	}
	
	public void id(Long id){
		if (hasil == null){
			hasil = QPasien.pasien.id.eq(id);					
		} else {
			hasil = hasil.and(QPasien.pasien.id.eq(id));
		}
	}
	
	public void identitas(String cari){
		if (hasil == null){
			hasil = QPasien.pasien.identitas.containsIgnoreCase(cari);
		} else {
			hasil = hasil.and(QPasien.pasien.identitas.containsIgnoreCase(cari));
		}
	}
	
	public void nomorJaminanKesehatan(String cari){
		if (hasil == null){
			hasil = QPasien.pasien.nomorJaminanKesehatan.containsIgnoreCase(cari);
		} else {
			hasil = hasil.and(QPasien.pasien.nomorJaminanKesehatan.containsIgnoreCase(cari));
		}
	}
	
	public void kontak(String cari){
		if (hasil == null){
			hasil = QPasien.pasien.kontak.containsIgnoreCase(cari);
		} else {
			hasil = hasil.and(QPasien.pasien.kontak.containsIgnoreCase(cari));
		}
	}

}
