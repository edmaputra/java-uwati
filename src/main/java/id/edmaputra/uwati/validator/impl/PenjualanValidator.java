package id.edmaputra.uwati.validator.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.validator.AbstractValidator;
import id.edmaputra.uwati.validator.ValidatorException;

@Component
public class PenjualanValidator extends AbstractValidator<Penjualan> {

	@Override
	protected void doValidate(Penjualan data) throws ValidatorException {
		// TODO Auto-generated method stub
		if (data == null) {
			throwValidatorException("Penjualan Tidak Boleh Null");
		} else if (data.getNomorFaktur() == null) {
			throwValidatorException("Nomor Faktur Null");
		} else if (data.getNomorFaktur().trim().isEmpty()) {
			throwValidatorException("Nomor Faktur kosong");
		} else if (data.getTotalPembelian().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException("Total Penjualan Kurang Dari 0");
		} else if (data.getPajak().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException("Pajak Penjualan Kurang Dari 0");
		} else if (data.getDiskon().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException("Diskon Penjualan Kurang Dari 0");
		} else if (data.getBayar().compareTo(BigDecimal.ZERO) < 0) {
			throwValidatorException("Pembayaran Penjualan Kurang Dari 0");
		} else if (data.getTipe() == null) {
			throwValidatorException("Tipe Penjualan Null");
		} else if (data.getWaktuTransaksi() == null) {
			throwValidatorException("Tanggal Lahir Tidak Boleh Null");
		} else if (data.getWaktuTransaksi().after(new Date())) {
			throwValidatorException("Tanggal Penjualan tidak boleh Melebihi Tanggal Hari Ini");
		}
	}

}
