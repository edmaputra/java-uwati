package id.edmaputra.uwati.entity.transaksi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarTransaksiEntity;

@Entity
@Table(name = "retur_pembelian_detail_temp", uniqueConstraints = { @UniqueConstraint(columnNames = "id")},
		indexes = {@Index(name = "iPembelianDetailTemp_nomorFaktur_pengguna", columnList = "nomorFaktur,pengguna")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReturPembelianDetailTemp extends DasarTransaksiEntity<Long>{

	private static final long serialVersionUID = -2093256738213786669L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 150)
	private String obat;
	
	@Column(length = 6)
	private String jumlah;
	
	@Column(length = 15)
	private String hargaJual;
	
	@Column(length = 15)
	private String hargaJualResep;
	
	@Column(length = 15)
	private String hargaBeli;

	@Column(length = 15)
	private String diskon;

	@Column(length = 15)
	private String pajak;

	@Column(length = 15)
	private String subTotal;

	@Column(length = 12)
	private String tanggalKadaluarsa;
	
	@Column(length = 50)
	private String pengguna;
	
	@Column(length = 30, name = "nomorFaktur")
	private String nomorFaktur;
	
	@Column(length = 12)
	private String tanggal;
	
	@Column(length = 70)
	private String supplier;
	
	@Column(length = 100)
	private String pesan;
	
	@Column(length = 15)
	private String randomId;
		
	private Long idObat;
		
	private Long idPembelian;

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

	public Long getIdObat() {
		return idObat;
	}

	public void setIdObat(Long idObat) {
		this.idObat = idObat;
	}

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

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	public String getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(String hargaJual) {
		this.hargaJual = hargaJual;
	}

	public String getHargaJualResep() {
		return hargaJualResep;
	}

	public void setHargaJualResep(String hargaJualResep) {
		this.hargaJualResep = hargaJualResep;
	}

	public String getHargaBeli() {
		return hargaBeli;
	}

	public void setHargaBeli(String hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public String getDiskon() {
		return diskon;
	}

	public void setDiskon(String diskon) {
		this.diskon = diskon;
	}

	public String getPajak() {
		return pajak;
	}

	public void setPajak(String pajak) {
		this.pajak = pajak;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getTanggalKadaluarsa() {
		return tanggalKadaluarsa;
	}

	public void setTanggalKadaluarsa(String tanggalKadaluarsa) {
		this.tanggalKadaluarsa = tanggalKadaluarsa;
	}

	public String getPengguna() {
		return pengguna;
	}

	public void setPengguna(String pengguna) {
		this.pengguna = pengguna;
	}

	public String getNomorFaktur() {
		return nomorFaktur;
	}

	public void setNomorFaktur(String nomorFaktur) {
		this.nomorFaktur = nomorFaktur;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}

	@Override
	public String toString() {
		return "ReturPembelianDetailTemp [id=" + id + ", obat=" + obat + ", jumlah=" + jumlah + ", hargaJual="
				+ hargaJual + ", hargaJualResep=" + hargaJualResep + ", hargaBeli=" + hargaBeli + ", diskon=" + diskon
				+ ", pajak=" + pajak + ", subTotal=" + subTotal + ", tanggalKadaluarsa=" + tanggalKadaluarsa
				+ ", pengguna=" + pengguna + ", nomorFaktur=" + nomorFaktur + ", tanggal=" + tanggal + ", supplier="
				+ supplier + ", pesan=" + pesan + ", randomId=" + randomId + ", idObat=" + idObat + "]";
	}

	public Long getIdPembelian() {
		return idPembelian;
	}

	public void setIdPembelian(Long idPembelian) {
		this.idPembelian = idPembelian;
	}
	
	
	
	
}
