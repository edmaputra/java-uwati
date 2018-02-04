package id.edmaputra.uwati.validator.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class RekamMedisValidator extends AbstractValidator<RekamMedis> {

	@Override
	protected void doValidate(RekamMedis data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null) {
			throwValidatorException("Rekam Medis Tidak Boleh Null");
		} else if (data.getRekamMedisDetail() == null){
			throwValidatorException("Rekam Medis Detail Tidak Boleh Null");
		} else if (data.getNomor() == null) {
			throwValidatorException("Nomor Rekam Medis Tidak Boleh Null");
		} else if (data.getNomor().trim().isEmpty()) {
			throwValidatorException("Nomor Rekam Medis tidak boleh kosong");
		} else if (data.getTanggal() == null) {
			throwValidatorException("Tanggal Rekam Medis Tidak Boleh Null");
		} else if (data.getTanggal().after(new Date())) {
			throwValidatorException("Tanggal Rekam Medis Melebihi Tanggal Hari Ini");
		} else if (data.getPasien() == null) {
			throwValidatorException("Pasien Tidak Boleh Null");
		} else if (data.getDokter() == null) {
			throwValidatorException("Dokter Tidak Boleh Null");
		} else if (data.getIsResepSudahDiproses() == null) {
			throwValidatorException("Status Proses Tidak Boleh Null");
		} else if (data.getIsSudahDisimpan() == null) {
			throwValidatorException("Status Simpan Tidak Boleh Null");
		}
	}

}
