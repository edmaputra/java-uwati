package id.edmaputra.uwati.validator.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.pasien.Pasien;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class PasienValidator extends AbstractValidator<Pasien>{

	@Override
	protected void doValidate(Pasien data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null){
			throwValidatorException("Pasien Tidak Boleh Null");
		} else if (data.getNama() == null){
			throwValidatorException("Nama Pasien Tidak Boleh Null");
		} else if (data.getNama().trim().isEmpty()){
			throwValidatorException("Nama Pasien tidak boleh kosong");
		} else if (data.getIdentitas() == null){
			throwValidatorException("Identitas Tidak Boleh Null");
		} else if (data.getIdentitas().trim().isEmpty()){
			throwValidatorException("Identitas Apotek tidak boleh kosong");
		} else if (data.getTanggalLahir() == null){
			throwValidatorException("Tanggal Lahir Tidak Boleh Null");
		} else if (data.getTanggalLahir().after(new Date())){
			throwValidatorException("Tanggal Lahir tidak boleh Melebihi Tanggal Hari Ini");
		}
	}

}
