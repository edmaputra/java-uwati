package id.edmaputra.uwati.validator.impl;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.master.Apotek;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class ApotekValidator extends AbstractValidator<Apotek>{

	@Override
	protected void doValidate(Apotek data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data.getNama() == null){
			throwValidatorException("Nama Apotek Tidak Boleh Null");
		} else if (data.getNama().trim().isEmpty()){
			throwValidatorException("Nama Apotek tidak boleh kosong");
		} else if (data.getAlamat() == null){
			throwValidatorException("Alamat Apotek Tidak Boleh Null");
		} else if (data.getAlamat().trim().isEmpty()){
			throwValidatorException("Alamat Apotek tidak boleh kosong");
		}
	}

}
