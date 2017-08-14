package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;

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
@Table(name = "penjualan_detail_temp", uniqueConstraints = { @UniqueConstraint(columnNames = "id")},
		indexes = {@Index(name = "iPenjualanDetailTemp_nomorFaktur_pengguna", columnList = "nomorFaktur,pengguna")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PenjualanDetailTemp extends DasarTransaksiEntity<Long>{

	private static final long serialVersionUID = -2093256738213786669L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 30, name = "nomorFaktur")
	private String nomorFaktur;
	
	@Column(length = 12)
	private String tanggal;	
	
	@Column
	private Integer tipe;
	
	@Column(length = 150)
	private String dokter;
	
	@Column(length = 150)
	private String pelanggan;
	
	@Column(length = 15)
	private String idObat;

	@Column(length = 150)
	private String obat;
	
	@Column(length = 6)
	private String jumlah;
	
	@Column(length = 15)
	private String hargaJual;		

	@Column(length = 15)
	private String diskon;

	@Column(length = 15)
	private String pajak;

	@Column(length = 15)
	private String subTotal;
	
	@Column(length = 50)
	private String pengguna;
	
	@Column(length = 50)
	private String info;
	
	@Column(length = 20)
	private String total;
	
	@Column(length = 20)
	private String grandTotal;
	
	@Column(length = 20)
	private String bayar;
	
	@Column(length = 20)
	private String kembali;
	
	@Column(length = 20)
	private String randomId;	

	
	public String getIdObat() {
		return idObat;
	}

	public void setIdObat(String idObat) {
		this.idObat = idObat;
	}
	
	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
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

	public Integer getTipe() {
		return tipe;
	}

	public void setTipe(Integer tipe) {
		this.tipe = tipe;
	}

	public String getDokter() {
		return dokter;
	}

	public void setDokter(String dokter) {
		this.dokter = dokter;
	}

	public String getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(String pelanggan) {
		this.pelanggan = pelanggan;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getBayar() {
		return bayar;
	}

	public void setBayar(String bayar) {
		this.bayar = bayar;
	}

	public String getKembali() {
		return kembali;
	}

	public void setKembali(String kembali) {
		this.kembali = kembali;
	}
	
}
