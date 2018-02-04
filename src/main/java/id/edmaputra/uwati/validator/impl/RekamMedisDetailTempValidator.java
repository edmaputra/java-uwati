package id.edmaputra.uwati.validator.impl;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.master.Apotek;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetailTemp;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class RekamMedisDetailTempValidator extends AbstractValidator<RekamMedisDetailTemp> {

	@Override
	protected void doValidate(RekamMedisDetailTemp data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null) {
			throwValidatorException("Terapi Tidak Boleh Null");
		} else if (data.getTerapi() == null) {
			throwValidatorException("Nama Terapi Tidak Boleh Null");
		} else if (data.getTerapi().trim().isEmpty()) {
			throwValidatorException("Nama Terapi tidak boleh kosong");
		} else if (data.getNomor() == null) {
			throwValidatorException("Nomor Rekam Medis Tidak Boleh Null");
		} else if (data.getNomor().trim().isEmpty()) {
			throwValidatorException("Nomor Rekam Medis Tidak Boleh Kosong");
		} else if (data.getJumlah() == null) {
			throwValidatorException("Jumlah Terapi Tidak Boleh Null");
		} else if (data.getJumlah().trim().isEmpty()) {
			throwValidatorException("Jumlah Terapi Tidak Boleh Kosong");
		} else if (data.getHargaJual() == null) {
			throwValidatorException("Harga Terapi Tidak Boleh Null");
		} else if (data.getHargaJual().trim().isEmpty()) {
			throwValidatorException("Harga Terapi Tidak Boleh Kosong");
		}
	}

}
