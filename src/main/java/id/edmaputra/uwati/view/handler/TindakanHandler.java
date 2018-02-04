package id.edmaputra.uwati.view.handler;

public class TindakanHandler{

	private String id;

	private String nama;
		
	private String kode;
	
	private String tarif;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getTarif() {
		return tarif;
	}

	public void setTarif(String tarif) {
		this.tarif = tarif;
	}

	@Override
	public String toString() {
		return "Tindakan [id=" + id + ", nama=" + nama + ", kode=" + kode + ", tarif=" + tarif + "]";
	}
	
}
