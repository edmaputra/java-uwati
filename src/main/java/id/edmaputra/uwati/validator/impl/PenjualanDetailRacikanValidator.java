package id.edmaputra.uwati.validator.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailRacikan;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class PenjualanDetailRacikanValidator extends AbstractValidator<PenjualanDetailRacikan> {

	private String entity = "Penjualan Detail Racikan :";

	@Override
	protected void doValidate(PenjualanDetailRacikan data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null) {
			throwValidatorException(entity + "Null");
		} else if (data.getPenjualanDetail() == null) {
			throwValidatorException(entity + "Penjualan Detail Null");
		} else if (data.getKomposisi() == null) {
			throwValidatorException(entity + "Komposisi Null");
		} else if (data.getKomposisi().trim().isEmpty()) {
			throwValidatorException(entity + "Komposisi Kosong");
		} else if (data.getHargaJualPerKomposisi().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException(entity + "Harga Jual < 0");
		} else if (data.getJumlah() <= 0) {
			throwValidatorException(entity + "Jumlah <= 0");
		}
	}

}
