package id.edmaputra.uwati.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="pelanggan", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "kode") })
public class Pelanggan extends DasarEntity<Integer>{

	private static final long serialVersionUID = 2272001067197118594L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nama", nullable=false, length=DBConf.LENGTH_NAMA)
	private String nama;
	
	@Column(name="Kode", nullable = false, length=DBConf.LENGTH_KODE_PELANGGAN)
	private String kode;

	@Column(name="alamat", length=DBConf.LENGTH_ALAMAT)	
	private String alamat;
	
	@Column(name="kontak", length=DBConf.LENGTH_KONTAK)
	private String kontak;
	
	public Pelanggan(){}
	
	public Pelanggan(String nama, String kode, String alamat, String kontak) {		
		this.nama = nama;
		this.kode = kode;
		this.alamat = alamat;
		this.kontak = kontak;
	}
	
	public Integer getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getKode() {
		return kode;
	}

	public String getAlamat() {
		return alamat;
	}

	public String getKontak() {
		return kontak;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public void setKontak(String kontak) {
		this.kontak = kontak;
	}

	@Override
	public String toString() {
		return "Pelanggan [id=" + id + ", nama=" + nama + ", kode=" + kode + ", alamat=" + alamat + ", kontak=" + kontak
				+ "]";
	}
	
	

}
