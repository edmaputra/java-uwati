package id.edmaputra.uwati.view.handler;

public class ObatHandler {

	private Long id;
	private String nama;
	private String kode;
	private String satuan;
	private String kategori;
	private String batch;
	private String barcode;
	private String hargaJual = "0";
	private String hargaJualResep = "0";
	private String hargaBeli = "0";
	private String hargaDiskon = "0";
	private Integer stok = 0;
	private Integer stokMinimal = 0;
	private Integer tipe = 0;
	private String tanggalExpired;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getSatuan() {
		return satuan;
	}

	public void setSatuan(String satuan) {
		this.satuan = satuan;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getHargaDiskon() {
		return hargaDiskon;
	}

	public void setHargaDiskon(String hargaDiskon) {
		this.hargaDiskon = hargaDiskon;
	}

	public Integer getStok() {
		return stok;
	}

	public void setStok(Integer stok) {
		this.stok = stok;
	}

	public Integer getStokMinimal() {
		return stokMinimal;
	}

	public void setStokMinimal(Integer stokMinimal) {
		this.stokMinimal = stokMinimal;
	}

	public Integer getTipe() {
		return tipe;
	}

	public void setTipe(Integer tipe) {
		this.tipe = tipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTanggalExpired() {
		return tanggalExpired;
	}

	public void setTanggalExpired(String tanggalExpired) {
		this.tanggalExpired = tanggalExpired;
	}

}
