package id.edmaputra.uwati.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import id.edmaputra.uwati.config.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="dokter", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Dokter extends DasarEntity<Integer>{
	
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
	
	public Dokter(){}
	
	public Dokter(String nama, String spesialis, String sip, String alamat) {		
		this.nama = nama;
		this.spesialis = spesialis;
		this.sip = sip;
		this.alamat = alamat;
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

	@Override
	public String toString() {
		return "Dokter [id=" + id + ", nama=" + nama + ", spesialis=" + spesialis + ", sip=" + sip + ", alamat="
				+ alamat + "]";
	}
}
