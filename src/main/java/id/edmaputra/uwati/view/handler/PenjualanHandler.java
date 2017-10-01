package id.edmaputra.uwati.view.handler;

import java.util.Date;

public class PenjualanHandler {
	
	private Long id;
		
	private Date waktuTransaksi;
		
	private String pengguna;
		
	private String pelanggan;
	
	private String dokter;
	
	private String nomorFaktur;
		
	private String totalPembelian;
	
	private String diskon;
	
	private String grandTotal;
	
	private String bayar;
	
	private String kembali;
	
	private String pajak;
	
	private Integer tipe;
	
	private String nomorResep;
	
	private String details;
	
	private Integer jumlah;
	
	private String info;

	public Date getWaktuTransaksi() {
		return waktuTransaksi;
	}

	public void setWaktuTransaksi(Date waktuTransaksi) {
		this.waktuTransaksi = waktuTransaksi;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPengguna() {
		return pengguna;
	}

	public void setPengguna(String pengguna) {
		this.pengguna = pengguna;
	}

	public String getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(String pelanggan) {
		this.pelanggan = pelanggan;
	}

	public String getNomorFaktur() {
		return nomorFaktur;
	}

	public void setNomorFaktur(String nomorFaktur) {
		this.nomorFaktur = nomorFaktur;
	}

	public Integer getTipe() {
		return tipe;
	}

	public void setTipe(Integer tipe) {
		this.tipe = tipe;
	}

	public String getNomorResep() {
		return nomorResep;
	}

	public void setNomorResep(String nomorResep) {
		this.nomorResep = nomorResep;
	}

	public String getDokter() {
		return dokter;
	}

	public void setDokter(String dokter) {
		this.dokter = dokter;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTotalPembelian() {
		return totalPembelian;
	}

	public void setTotalPembelian(String totalPembelian) {
		this.totalPembelian = totalPembelian;
	}

	public String getDiskon() {
		return diskon;
	}

	public void setDiskon(String diskon) {
		this.diskon = diskon;
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

	public String getPajak() {
		return pajak;
	}

	public void setPajak(String pajak) {
		this.pajak = pajak;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
