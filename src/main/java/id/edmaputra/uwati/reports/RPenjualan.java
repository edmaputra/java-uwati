package id.edmaputra.uwati.reports;

import java.util.List;

public class RPenjualan {
	
	private String nomorFaktur;
	
	private String waktuTransaksi;
	
	private List<RPenjualanDetail> details;

	public String getNomorFaktur() {
		return nomorFaktur;
	}

	public void setNomorFaktur(String nomorFaktur) {
		this.nomorFaktur = nomorFaktur;
	}

	public String getWaktuTransaksi() {
		return waktuTransaksi;
	}

	public void setWaktuTransaksi(String waktuTransaksi) {
		this.waktuTransaksi = waktuTransaksi;
	}

	public List<RPenjualanDetail> getDetails() {
		return details;
	}

	public void setDetails(List<RPenjualanDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "RPenjualan [nomorFaktur=" + nomorFaktur + ", waktuTransaksi=" + waktuTransaksi + "]";
	}

}
