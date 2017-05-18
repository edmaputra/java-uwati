package id.edmaputra.uwati.entity.master.obat;

import java.util.List;

public class RacikanTemp {
	
	private Long id;
	
	private String nama;
	
	private String biayaRacik;
	
	private List<RacikanDetailTemp> komposisi;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getBiayaRacik() {
		return biayaRacik;
	}

	public void setBiayaRacik(String biayaRacik) {
		this.biayaRacik = biayaRacik;
	}

	public List<RacikanDetailTemp> getKomposisi() {
		return komposisi;
	}

	public void setKomposisi(List<RacikanDetailTemp> komposisi) {
		this.komposisi = komposisi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
