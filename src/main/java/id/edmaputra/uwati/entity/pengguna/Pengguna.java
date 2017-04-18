package id.edmaputra.uwati.entity.pengguna;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import id.edmaputra.uwati.config.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="pengguna", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "nama") })
public class Pengguna extends DasarEntity<Integer>{

	private static final long serialVersionUID = 5813445391302629664L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nama", nullable=false, length=DBConf.LENGTH_NAMA_PENGGUNA)
	private String nama;
	
	@Column(name="kata_sandi", nullable=false)
	private String kataSandi;
	
	@Column(name="aktif", nullable=false)
	private Boolean isAktif;
	
	@Column(name="pertama_kali", nullable=false)
	private Boolean isPertamaKali;
	
	@Column(name="kesalahan", nullable=false)
	private int countKesalahan;
		
	@ManyToMany(fetch=FetchType.EAGER)	
	@JoinTable(name="pengguna_role",
			joinColumns = {@JoinColumn(name = "pengguna_id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")})
	private List<Role> roles = new ArrayList<>();
	
	public Pengguna(){
		
	}
	
	public Pengguna(String nama, Boolean isAktif, Boolean isPertamaKali, int countKesalahan, List<Role> roles) {	
		this.nama = nama;
		this.isAktif = isAktif;
		this.isPertamaKali = isPertamaKali;
		this.countKesalahan = countKesalahan;
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	@Override
	public String toString() {
		return "Pengguna [id=" + id + ", nama=" + nama + ", isAktif=" + isAktif
				+ ", isPertamaKali=" + isPertamaKali + ", countKesalahan=" + countKesalahan + ", roles=" + roles + "]";
	}

}
