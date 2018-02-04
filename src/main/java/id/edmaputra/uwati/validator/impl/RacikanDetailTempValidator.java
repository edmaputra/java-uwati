package id.edmaputra.uwati.validator.impl;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.master.Apotek;
import id.edmaputra.uwati.entity.master.obat.RacikanDetailTemporary;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetailTemp;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class RacikanDetailTempValidator extends AbstractValidator<RacikanDetailTemporary> {

	@Override
	protected void doValidate(RacikanDetailTemporary data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null) {
			throwValidatorException("Terapi Tidak Boleh Null");
		} else if (data.getKomposisi() == null) {
			throwValidatorException("Komposisi Tidak Boleh Null");
		} else if (data.getKomposisi().trim().isEmpty()) {
			throwValidatorException("Komposisi tidak boleh kosong");
		} else if (data.getJumlah() == null) {
			throwValidatorException("Jumlah Komposisi Medis Tidak Boleh Null");
		} else if (data.getJumlah().trim().isEmpty()) {
			throwValidatorException("Jumlah Komposisi Tidak Boleh Kosong");
		} else if (Integer.valueOf(data.getJumlah()) <= 0 ) {
			throwValidatorException("Jumlah Tidak Boleh Kurang dari Sama dengan 0");
		}
	}

}
