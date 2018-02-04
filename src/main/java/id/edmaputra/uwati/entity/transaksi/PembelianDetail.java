package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Table(name = "pembelian_detail", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PembelianDetail extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = -8422941488327899895L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "obat")
	private String obat;

	@Column(nullable = false)	
	private Integer jumlah;
		
	private BigDecimal hargaJual;
	
	private BigDecimal hargaJualResep;
	
	private BigDecimal hargaBeli;

	@Column(columnDefinition="Decimal(19,2) default 0")
	private BigDecimal diskon;

	private BigDecimal pajak;

	private BigDecimal subTotal;

	private Date tanggalKadaluarsa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pembelian")
	@JsonIgnore
	private Pembelian pembelian;

	private Boolean isReturned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObat() {
		return obat;
	}

	public void setObat(String obat) {
		this.obat = obat;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public BigDecimal getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(BigDecimal hargaJual) {
		this.hargaJual = hargaJual;
	}

	public BigDecimal getHargaJualResep() {
		return hargaJualResep;
	}

	public void setHargaJualResep(BigDecimal hargaJualResep) {
		this.hargaJualResep = hargaJualResep;
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

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Date getTanggalKadaluarsa() {
		return tanggalKadaluarsa;
	}

	public void setTanggalKadaluarsa(Date tanggalKadaluarsa) {
		this.tanggalKadaluarsa = tanggalKadaluarsa;
	}

	public Pembelian getPembelian() {
		return pembelian;
	}

	public void setPembelian(Pembelian pembelian) {
		this.pembelian = pembelian;
	}

	public Boolean getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(Boolean isReturned) {
		this.isReturned = isReturned;
	}

	@Override
	public String toString() {
		return "PembelianDetail [id=" + id + ", obat=" + obat + ", jumlah=" + jumlah + ", hargaJual=" + hargaJual
				+ ", hargaJualResep=" + hargaJualResep + ", hargaBeli=" + hargaBeli + ", diskon=" + diskon + ", pajak="
				+ pajak + ", subTotal=" + subTotal + ", tanggalKadaluarsa=" + tanggalKadaluarsa + ", isReturned="
				+ isReturned + "]";
	}
}
