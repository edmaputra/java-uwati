package id.edmaputra.uwati.view.handler;

public class PenggunaHandler {

	private Integer id;
	private String nama;
	private String kataSandi;
	private Boolean isAktif;
	private Boolean isPertamaKali;
	private int countKesalahan;
	private String role1;
	private String role2;
	private String role3;
	private String karyawan;

	public PenggunaHandler() {

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

	public String getKataSandi() {
		return kataSandi;
	}

	public void setKataSandi(String kataSandi) {
		this.kataSandi = kataSandi;
	}

	public Boolean getIsAktif() {
		return isAktif;
	}

	public void setIsAktif(Boolean isAktif) {
		this.isAktif = isAktif;
	}

	public Boolean getIsPertamaKali() {
		return isPertamaKali;
	}

	public void setIsPertamaKali(Boolean isPertamaKali) {
		this.isPertamaKali = isPertamaKali;
	}

	public int getCountKesalahan() {
		return countKesalahan;
	}

	public void setCountKesalahan(int countKesalahan) {
		this.countKesalahan = countKesalahan;
	}

	public String getRole1() {
		return role1;
	}

	public void setRole1(String role1) {
		this.role1 = role1;
	}

	public String getRole2() {
		return role2;
	}

	public void setRole2(String role2) {
		this.role2 = role2;
	}

	public String getRole3() {
		return role3;
	}

	public void setRole3(String role3) {
		this.role3 = role3;
	}

	@Override
	public String toString() {
		return "PenggunaHandler [id=" + id + ", nama=" + nama + ", isAktif=" + isAktif + ", isPertamaKali="
				+ isPertamaKali + ", countKesalahan=" + countKesalahan + ", role1=" + role1 + ", role2=" + role2
				+ ", role3=" + role3 + "]";
	}

	public String getKaryawan() {
		return karyawan;
	}

	public void setKaryawan(String karyawan) {
		this.karyawan = karyawan;
	}

}
