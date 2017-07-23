package id.edmaputra.uwati.validator.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class PenjualanDetailValidator extends AbstractValidator<PenjualanDetail> {
	
	private String entity = "Penjualan Detail :";

	@Override
	protected void doValidate(PenjualanDetail data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null) {
			throwValidatorException(entity + "Null");
		} else if (data.getPenjualan() == null) {
			throwValidatorException(entity + "Penjualan Null");
		} else if (data.getObat() == null) {
			throwValidatorException(entity + "Obat Null");
		} else if (data.getObat().trim().isEmpty()) {
			throwValidatorException(entity + "Obat Kosong");
		} else if (data.getDiskon().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException(entity + "Diskon Penjualan Kurang Dari 0");
		} else if (data.getHargaJual().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException(entity + "Harga Jual Kurang Dari 0");
		} else if (data.getHargaTotal().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException(entity + "Harga Total Kurang Dari 0");
		} else if (data.getJumlah() <= 0) {
			throwValidatorException(entity + "Jumlah <= 0");
		}
	}

}
