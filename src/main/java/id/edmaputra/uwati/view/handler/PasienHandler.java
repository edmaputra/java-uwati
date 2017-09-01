package id.edmaputra.uwati.view.handler;

public class PasienHandler {

	private Long id;
	private String nama;
	private String identitas;
	private String alamat;
	private String tanggalLahir;
	private int jenisKelamin;
	private String agama;
	private String kontak;
	private String pekerjaan;
	private String jaminanKesehatan;
	private String nomorJaminan;
	private Integer kategori;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getIdentitas() {
		return identitas;
	}

	public void setIdentitas(String identitas) {
		this.identitas = identitas;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(String tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public int getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(int jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getAgama() {
		return agama;
	}

	public void setAgama(String agama) {
		this.agama = agama;
	}

	public String getKontak() {
		return kontak;
	}

	public void setKontak(String kontak) {
		this.kontak = kontak;
	}

	public String getPekerjaan() {
		return pekerjaan;
	}

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public String getJaminanKesehatan() {
		return jaminanKesehatan;
	}

	public void setJaminanKesehatan(String jaminanKesehatan) {
		this.jaminanKesehatan = jaminanKesehatan;
	}

	public String getNomorJaminan() {
		return nomorJaminan;
	}

	public void setNomorJaminan(String nomorJaminan) {
		this.nomorJaminan = nomorJaminan;
	}

	public Integer getKategori() {
		return kategori;
	}

	public void setKategori(Integer kategori) {
		this.kategori = kategori;
	}

}
