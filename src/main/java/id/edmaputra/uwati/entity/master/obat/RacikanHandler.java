package id.edmaputra.uwati.entity.master.obat;

import java.util.List;

public class RacikanHandler {
	
	private Long id;
	
	private String nama;
	
	private String biayaRacik;
	
	private String randomId;
	
	private List<RacikanDetailHandler> komposisi;

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

	public List<RacikanDetailHandler> getKomposisi() {
		return komposisi;
	}

	public void setKomposisi(List<RacikanDetailHandler> komposisi) {
		this.komposisi = komposisi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

}
