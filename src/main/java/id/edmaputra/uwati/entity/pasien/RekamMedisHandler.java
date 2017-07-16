package id.edmaputra.uwati.entity.pasien;

public class RekamMedisHandler {
	
	private String nomor;
	
	private String tanggal;
	
	private String anamnesa;
	
	private String pemeriksaan;
	
	private String diagnosa;
	
	private String tabelTerapi;
	
	private String pasien;

	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getAnamnesa() {
		return anamnesa;
	}

	public void setAnamnesa(String anamnesa) {
		this.anamnesa = anamnesa;
	}

	public String getPemeriksaan() {
		return pemeriksaan;
	}

	public void setPemeriksaan(String pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}

	public String getDiagnosa() {
		return diagnosa;
	}

	public void setDiagnosa(String diagnosa) {
		this.diagnosa = diagnosa;
	}

	@Override
	public String toString() {
		return "RekamMedisHandler [nomor=" + nomor + ", tanggal=" + tanggal + ", anamnesa=" + anamnesa
				+ ", pemeriksaan=" + pemeriksaan + ", diagnosa=" + diagnosa + "]";
	}

	public String getTabelTerapi() {
		return tabelTerapi;
	}

	public void setTabelTerapi(String terapiList) {
		this.tabelTerapi = terapiList;
	}

	public String getPasien() {
		return pasien;
	}

	public void setPasien(String pasien) {
		this.pasien = pasien;
	}

}
