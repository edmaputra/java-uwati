package id.edmaputra.uwati.view.handler;

import java.util.List;

public class RacikanHandler {
	
	private Long id;
	
	private String nama;
	
	private String biayaRacik;
	
	private String randomId;
	
	private String info;
	
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
