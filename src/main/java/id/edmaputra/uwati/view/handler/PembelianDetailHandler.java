package id.edmaputra.uwati.view.handler;

public class PembelianDetailHandler {

	private String id;

	private Long idObat;

	private String randomId;

	private String nomorFaktur;

	private String tanggal;

	private Integer tipe;

	private String dokter;

	private String pelanggan;

	private String obat;

	private String jumlah;

	private String hargaJual;

	private String diskon;

	private String pajak;

	private String subTotal;

	private String pengguna;

	private String info;

	private String totalPembelian;

	private String totalPembelianFinal;

	private String totalPembayaran;

	private String kembali;
	
	private String hargaBeli;
	
	private String hargaJualResep;
	
	private String distributor;
	
	private String details;
	
	private String tanggalExpired;
	
	private String bayar;
	
	private String bayarDetails;
	
	private String idPembelian;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIdObat() {
		return idObat;
	}

	public void setIdObat(Long idObat) {
		this.idObat = idObat;
	}

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTotalPembelian() {
		return totalPembelian;
	}

	public void setTotalPembelian(String totalPembelian) {
		this.totalPembelian = totalPembelian;
	}

	public String getTotalPembelianFinal() {
		return totalPembelianFinal;
	}

	public void setTotalPembelianFinal(String totalPembelianFinal) {
		this.totalPembelianFinal = totalPembelianFinal;
	}

	public String getTotalPembayaran() {
		return totalPembayaran;
	}

	public void setTotalPembayaran(String bayar) {
		this.totalPembayaran = bayar;
	}

	public String getKembali() {
		return kembali;
	}

	public void setKembali(String kembali) {
		this.kembali = kembali;
	}

	public String getHargaBeli() {
		return hargaBeli;
	}

	public void setHargaBeli(String hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public String getHargaJualResep() {
		return hargaJualResep;
	}

	public void setHargaJualResep(String hargaJualResep) {
		this.hargaJualResep = hargaJualResep;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTanggalExpired() {
		return tanggalExpired;
	}

	public void setTanggalExpired(String tanggalExpired) {
		this.tanggalExpired = tanggalExpired;
	}

	public String getBayar() {
		return bayar;
	}

	public void setBayar(String bayar) {
		this.bayar = bayar;
	}

	public String getBayarDetails() {
		return bayarDetails;
	}

	public void setBayarDetails(String bayarDetails) {
		this.bayarDetails = bayarDetails;
	}

	@Override
	public String toString() {
		return "PembelianDetailHandler [id=" + id + ", idObat=" + idObat + ", randomId=" + randomId + ", nomorFaktur="
				+ nomorFaktur + ", tanggal=" + tanggal + ", tipe=" + tipe + ", dokter=" + dokter + ", pelanggan="
				+ pelanggan + ", obat=" + obat + ", jumlah=" + jumlah + ", hargaJual=" + hargaJual + ", diskon="
				+ diskon + ", pajak=" + pajak + ", subTotal=" + subTotal + ", pengguna=" + pengguna + ", info=" + info
				+ ", totalPembelian=" + totalPembelian + ", totalPembelianFinal=" + totalPembelianFinal
				+ ", totalPembayaran=" + totalPembayaran + ", kembali=" + kembali + ", hargaBeli=" + hargaBeli
				+ ", hargaJualResep=" + hargaJualResep + ", distributor=" + distributor + ", details=" + details
				+ ", tanggalExpired=" + tanggalExpired + ", bayar=" + bayar + ", bayarDetails=" + bayarDetails + "]";
	}

	public String getIdPembelian() {
		return idPembelian;
	}

	public void setIdPembelian(String idPembelian) {
		this.idPembelian = idPembelian;
	}

}
