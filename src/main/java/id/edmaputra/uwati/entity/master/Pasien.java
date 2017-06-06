package id.edmaputra.uwati.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import id.edmaputra.uwati.config.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="pasien", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "identitas") })
public class Pasien extends DasarEntity<Integer>{

	private static final long serialVersionUID = 2272001067197118594L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
		
	@Column(name="nama", nullable=false, length=DBConf.LENGTH_NAMA)
	private String nama;
	
	@Column(name="identitas", length=DBConf.LENGTH_IDENTITAS, nullable = false)	
	private String identitas;

	//
	@Column(name="ibu", nullable=true, length=DBConf.LENGTH_NAMA)	
	private String ibu;
	
	@Column(name="alamat", nullable=true, length=DBConf.LENGTH_ALAMAT)
	private String alamat;
	
	@Column(name="jenis_kelamin", nullable=true)
	private int jenisKelamin;
	
	@Column(name="agama", nullable=true)
	private int agama;
	
	@Column(name="kontak", nullable=true, length = DBConf.LENGTH_KONTAK)
	private String kontak;
	
	@Column(name="pekerjaan", nullable=true, length = DBConf.LENGTH_PEKERJAAN)
	private String pekerjaan;
	
	@Column(name="jaminan_kesehatan", nullable=true, length = DBConf.LENGTH_JAMINAN)
	private String jaminanKesehatan;
	
	@Column(name="nomor_jaminan", nullable=true, length = DBConf.LENGTH_JAMINAN)
	private String nomorJaminan;
	
	@OneToOne
	@JoinColumn(name = "id_pelanggan")
	private Pelanggan pelanggan;

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

	public String getIdentitas() {
		return identitas;
	}

	public void setIdentitas(String identitas) {
		this.identitas = identitas;
	}

	public String getIbu() {
		return ibu;
	}

	public void setIbu(String ibu) {
		this.ibu = ibu;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public int getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(int jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public int getAgama() {
		return agama;
	}

	public void setAgama(int agama) {
		this.agama = agama;
	}

	public String getKontak() {
		return kontak;
	}

	public void setKontak(String kontak) {
		this.kontak = kontak;
	}

	public String getPekerjaan() {
		return pekerjaan;
	}

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public String getJaminanKesehatan() {
		return jaminanKesehatan;
	}

	public void setJaminanKesehatan(String jaminanKesehatan) {
		this.jaminanKesehatan = jaminanKesehatan;
	}

	public String getNomorJaminan() {
		return nomorJaminan;
	}

	public void setNomorJaminan(String nomorJaminan) {
		this.nomorJaminan = nomorJaminan;
	}

	public Pelanggan getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(Pelanggan pelanggan) {
		this.pelanggan = pelanggan;
	}
	
}
