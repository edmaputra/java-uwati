package id.edmaputra.uwati.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.view.Table;

@MappedSuperclass
public abstract class Bayar<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "waktu_transaksi", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date waktuTransaksi;
	
	@Column(name = "jumlah_bayar", nullable = false)	
	private BigDecimal jumlahBayar;

	public Date getWaktuTransaksi() {
		return waktuTransaksi;
	}

	public void setWaktuTransaksi(Date waktuTransaksi) {
		this.waktuTransaksi = waktuTransaksi;
	}

	public BigDecimal getJumlahBayar() {
		return jumlahBayar;
	}

	public void setJumlahBayar(BigDecimal jumlahBayar) {
		this.jumlahBayar = jumlahBayar;
	}
	
	


}
