package id.edmaputra.uwati.view.handler;

public class PasswordHandler {
	
	private Integer id;
	private String nama;
	private String passwordLama;
	private String passwordBaru;
	private String konfirmasiPasswordBaru;

	public String getPasswordLama() {
		return passwordLama;
	}

	public void setPasswordLama(String passwordLama) {
		this.passwordLama = passwordLama;
	}

	public String getPasswordBaru() {
		return passwordBaru;
	}

	public void setPasswordBaru(String passwordBaru) {
		this.passwordBaru = passwordBaru;
	}

	public String getKonfirmasiPasswordBaru() {
		return konfirmasiPasswordBaru;
	}

	public void setKonfirmasiPasswordBaru(String konfirmasiPasswordBaru) {
		this.konfirmasiPasswordBaru = konfirmasiPasswordBaru;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}
