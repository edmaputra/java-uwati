package id.edmaputra.uwati.validator;

public interface Validator<T> {
	
	void validate(T data) throws ValidatorException;

}
