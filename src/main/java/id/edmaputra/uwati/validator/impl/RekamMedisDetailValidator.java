package id.edmaputra.uwati.validator.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.pasien.RekamMedisDetail;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class RekamMedisDetailValidator extends AbstractValidator<RekamMedisDetail> {

	@Override
	protected void doValidate(RekamMedisDetail data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null) {
			throwValidatorException("Terapi Tidak Boleh Null");
		} else if (data.getRekamMedis() == null){
			throwValidatorException("Rekam Medis Tidak Boleh Null");
		} else if (data.getTerapi() == null) {
			throwValidatorException("Nama Terapi Tidak Boleh Null");
		} else if (data.getTerapi().trim().isEmpty()) {
			throwValidatorException("Nama Terapi tidak boleh kosong");
		} else if (data.getJumlah() == null) {
			throwValidatorException("Jumlah Terapi Tidak Boleh Null");
		} else if (data.getJumlah() <= 0) {
			throwValidatorException("Jumlah Tidak Boleh 0 atau lebih kurang");
		} else if (data.getHargaJual() == null) {
			throwValidatorException("Harga Terapi Tidak Boleh Null");
		} else if (data.getHargaJual().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException("Harga Terapi Tidak Boleh Kurang Dari 0");
		} else if (data.getHargaTotal() == null) {
			throwValidatorException("Harga Terapi Tidak Boleh Null");
		} else if (data.getHargaTotal().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException("Harga Total Tidak Boleh Kurang Dari 0");
		}
	}

}
