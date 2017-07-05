package id.edmaputra.uwati.view;

import java.util.Date;

public class PageParameters {
	
	private String halaman = "1";
	private String cari;
	private Date tanggal = new Date();

	public String getHalaman() {
		return halaman;
	}

	public void setHalaman(String halaman) {
		this.halaman = halaman;
	}

	public String getCari() {
		return cari;
	}

	public void setCari(String cari) {
		this.cari = cari;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

}
