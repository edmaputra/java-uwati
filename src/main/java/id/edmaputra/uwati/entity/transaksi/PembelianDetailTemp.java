package id.edmaputra.uwati.entity.transaksi;

public class PembelianDetailTemp{

	private Long id;
	
	private String obat;
	
	private String jumlah;
	
	private String hargaJual;
	
	private String hargaJualResep;
	
	private String hargaBeli;

	private String diskon;

	private String pajak;

	private String subTotal;

	private String tanggalKadaluarsa;
	
	private String pengguna;
	
	private String nomorFaktur;
	
	private String tanggal;
	
	private String supplier;
	
	private String pesan;

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

	@Override
	public String toString() {
		return "PembelianDetailTemp [obat=" + obat + ", jumlah=" + jumlah + ", hargaJual=" + hargaJual
				+ ", hargaJualResep=" + hargaJualResep + ", hargaBeli=" + hargaBeli + ", diskon=" + diskon + ", pajak="
				+ pajak + ", subTotal=" + subTotal + ", tanggalKadaluarsa=" + tanggalKadaluarsa + ", pengguna="
				+ pengguna + ", nomorFaktur=" + nomorFaktur + ", tanggal=" + tanggal + ", supplier=" + supplier + "]";
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}
	
	
	
	
}
