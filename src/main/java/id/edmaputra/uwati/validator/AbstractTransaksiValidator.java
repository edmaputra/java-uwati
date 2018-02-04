package id.edmaputra.uwati.validator;

import id.edmaputra.uwati.entity.DasarTransaksiEntity;

public abstract class AbstractTransaksiValidator<T> implements Validator<T> {
	
	protected void throwValidatorException(String message) throws ValidatorException {
        throw new ValidatorException(message);
    }
	
	protected abstract void doValidate(T data) throws ValidatorException;

    public void validate(T data) throws ValidatorException {

        doValidate(data);

        if (data instanceof DasarTransaksiEntity<?>) {
        	DasarTransaksiEntity<?> entity = (DasarTransaksiEntity<?>) data;
            validateEntity(entity);
        }
    }

    protected void validateEntity(DasarTransaksiEntity<?> entity) throws ValidatorException {
        if (entity.getWaktuDibuat() == null) {
            throwValidatorException("Waktu dirubah tidak boleh null");
        } else if (entity.getTerakhirDirubah() == null) {
            throwValidatorException("Terakhir dirubah tidak boleh null");
        } 
    }

    
	
}
