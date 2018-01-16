package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarTransaksiEntity;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;

@Entity
@Table(name = "retur_pembelian_detail", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReturPembelianDetail extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = -8422941488327899895L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "obat")
	private String obat;

	@Column(nullable = false)
	private Integer jumlah;

	private BigDecimal hargaBeli;

	private BigDecimal diskon;

	private BigDecimal pajak;

	private BigDecimal hargaTotal;

	private Date tanggalKadaluarsa;

	@ManyToOne
	@JoinColumn(name = "id_retur_pembelian")
	@JsonIgnore
	private ReturPembelian returPembelian;
	
	private Date tanggal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public BigDecimal getHargaBeli() {
		return hargaBeli;
	}

	public void setHargaBeli(BigDecimal hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public BigDecimal getDiskon() {
		return diskon;
	}

	public void setDiskon(BigDecimal diskon) {
		this.diskon = diskon;
	}

	public BigDecimal getPajak() {
		return pajak;
	}

	public void setPajak(BigDecimal pajak) {
		this.pajak = pajak;
	}

	public BigDecimal getHargaTotal() {
		return hargaTotal;
	}

	public void setHargaTotal(BigDecimal hargaTotal) {
		this.hargaTotal = hargaTotal;
	}

	public Date getTanggalKadaluarsa() {
		return tanggalKadaluarsa;
	}

	public void setTanggalKadaluarsa(Date tanggalKadaluarsa) {
		this.tanggalKadaluarsa = tanggalKadaluarsa;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getObat() {
		return obat;
	}

	public void setObat(String obat) {
		this.obat = obat;
	}

	public ReturPembelian getReturPembelian() {
		return returPembelian;
	}

	public void setReturPembelian(ReturPembelian returPembelian) {
		this.returPembelian = returPembelian;
	}

	@Override
	public String toString() {
		return "ReturPembelianDetail [obat=" + obat + ", jumlah=" + jumlah + ", hargaBeli=" + hargaBeli + ", diskon="
				+ diskon + ", pajak=" + pajak + ", hargaTotal=" + hargaTotal + ", tanggalKadaluarsa="
				+ tanggalKadaluarsa + ", returPembelian=" + returPembelian + ", tanggal=" + tanggal + "]";
	}

}
