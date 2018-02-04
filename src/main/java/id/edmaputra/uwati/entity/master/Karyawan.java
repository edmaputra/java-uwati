package id.edmaputra.uwati.entity.master;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;
import id.edmaputra.uwati.entity.pengguna.Pengguna;

@Entity
@Table(name="karyawan", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Karyawan extends DasarEntity<Integer>{
	
	private static final long serialVersionUID = 4888650963869604003L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nama", nullable=false, length=DBConf.LENGTH_NAMA)
	private String nama;
	
	@Column(name="spesialis", length=DBConf.LENGTH_SPESIALIS)
	private String spesialis;
	
	@Column(name="sip", length=DBConf.LENGTH_SIP)
	private String sip;

	@Column(name="alamat", length=DBConf.LENGTH_ALAMAT)	
	private String alamat;
	
	@Column(name="jabatan", length=DBConf.LENGTH_JABATAN)	
	private String jabatan;
	
	@OneToOne(mappedBy = "karyawan", cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_pengguna", nullable = true)
	private Pengguna pengguna;
	
	public Karyawan(){}	

	public Karyawan(String nama, String spesialis, String sip, String alamat, String jabatan, Pengguna pengguna) {
		super();
		this.nama = nama;
		this.spesialis = spesialis;
		this.sip = sip;
		this.alamat = alamat;
		this.jabatan = jabatan;
		this.pengguna = pengguna;
	}

	public Integer getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getSpesialis() {
		return spesialis;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setSpesialis(String spesialis) {
		this.spesialis = spesialis;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getSip() {
		return sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}
}
