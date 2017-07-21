package id.edmaputra.uwati.specification;

import java.util.Date;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.QRekamMedis;

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
					.or(QRekamMedis.rekamMedis.diagnosa.containsIgnoreCase(cari));
		} else {
			hasil = hasil.and(QRekamMedis.rekamMedis.nomor.containsIgnoreCase(cari)
					.or(QRekamMedis.rekamMedis.anamnesa.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.pemeriksaan.containsIgnoreCase(cari))
					.or(QRekamMedis.rekamMedis.diagnosa.containsIgnoreCase(cari)));
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

}
