package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.QRekamMedis;
import id.edmaputra.uwati.entity.transaksi.QPenjualan;

public class RekamMedisPredicateBuilder {

	private BooleanExpression hasil = null;

	public BooleanExpression getExpression() {
		return hasil;
	}

	public void cari(String cari) {
		if (hasil == null) {
			hasil = QRekamMedis.rekamMedis.nomor.containsIgnoreCase(cari)
					.or(QRekamMedis.rekamMedis.anamnesa.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.pemeriksaan.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.diagnosa.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.dokter.nama.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.pasien.nama.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.nomor.containsIgnoreCase(cari)
					.or(QRekamMedis.rekamMedis.anamnesa.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.pemeriksaan.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.diagnosa.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.dokter.nama.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.pasien.nama.containsIgnoreCase(cari)));
		}
	}

	public void pasien(Long id) {
		if (hasil == null) {
			hasil = QRekamMedis.rekamMedis.pasien.id.eq(id);
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.pasien.id.eq(id));
		}
	}

	public void tanggal(Date tanggal) {
		if (hasil == null) {
			hasil = QRekamMedis.rekamMedis.tanggal.eq(tanggal);
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.tanggal.eq(tanggal));
		}
	}
	
	public void tanggalBetween(Date tanggalAwal, Date tanggalAkhir){
		if (hasil == null) {			
			hasil = QRekamMedis.rekamMedis.tanggal.between(tanggalAwal, tanggalAkhir);
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.tanggal.between(tanggalAwal, tanggalAkhir));
		}
	}
	
	public void saved(Boolean b){
		if (hasil == null) {
			hasil = QRekamMedis.rekamMedis.isSudahDisimpan.eq(b);
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.isSudahDisimpan.eq(b));
		}
	}
	
	public void sudahDiproses(Boolean b){
		if (hasil == null) {
			hasil = QRekamMedis.rekamMedis.isResepSudahDiproses.eq(b);
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.isResepSudahDiproses.eq(b));
		}
	}
	
	public void resepList(Boolean b){
		if (hasil == null) {
			hasil = QRekamMedis.rekamMedis.isMasukListResep.eq(b);
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.isMasukListResep.eq(b));
		}
	}
	
	public void kategori(Integer kategori){
		if (hasil == null) {
			hasil = QRekamMedis.rekamMedis.pasien.kategoriPasien.id.eq(kategori);
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.pasien.kategoriPasien.id.eq(kategori));
		}
	}
	
//	public void kunjunganMax(){
//		if (hasil == null) {
//			hasil = QRekamMedis.rekamMedis.kunjungan.max();
//		} else {
//			hasil = hasil.and(QRekamMedis.rekamMedis.isResepSudahDiproses.eq(b));
//		}
//	}

}
