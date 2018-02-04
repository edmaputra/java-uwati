package id.edmaputra.uwati.entity.pasien;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;
import id.edmaputra.uwati.entity.master.Pelanggan;

@Entity
@Table(name="pasien", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "identitas") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pasien extends DasarEntity<Long>{

	private static final long serialVersionUID = 2272001067197118594L;

	@Id	
	private Long id;
		
	@Column(name="nama", nullable=false, length=DBConf.LENGTH_NAMA)
	private String nama;
	
	@Column(name="identitas", length=DBConf.LENGTH_IDENTITAS, nullable = false)	
	private String identitas;
	
	@Column(name="alamat", nullable=true, length=DBConf.LENGTH_ALAMAT)
	private String alamat;
	
	@Column(name = "tanggal_lahir", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tanggalLahir;
	
	@Column(name="jenis_kelamin", nullable=false)
	private int jenisKelamin;
	
	@Column(name="agama", nullable=true)
	private String agama;
	
	@Column(name="kontak", nullable=true, length = DBConf.LENGTH_KONTAK)
	private String kontak;
	
	@Column(name="pekerjaan", nullable=true, length = DBConf.LENGTH_PEKERJAAN)
	private String pekerjaan;
	
	@Column(name="jaminan_kesehatan", nullable=true, length = DBConf.LENGTH_JAMINAN)
	private String jaminanKesehatan;
	
	@Column(name="nomor_jaminan", nullable=true, length = DBConf.LENGTH_JAMINAN)
	private String nomorJaminanKesehatan;
	
	@OneToOne
	@JoinColumn(name = "id_pelanggan")
	@JsonIgnore
	private Pelanggan pelanggan;
	
	@OneToMany(mappedBy = "pasien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<RekamMedis> rekamMedis;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kategori", nullable = true)
	private KategoriPasien kategoriPasien;

	public KategoriPasien getKategoriPasien() {
		return kategoriPasien;
	}

	public void setKategoriPasien(KategoriPasien kategoriPasien) {
		this.kategoriPasien = kategoriPasien;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAgama() {
		return agama;
	}

	public void setAgama(String agama) {
		this.agama = agama.toUpperCase();
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
		this.jaminanKesehatan = jaminanKesehatan.toUpperCase();
	}

	public String getNomorJaminanKesehatan() {
		return nomorJaminanKesehatan;
	}

	public void setNomorJaminanKesehatan(String nomorJaminan) {
		this.nomorJaminanKesehatan = nomorJaminan;
	}

	public Pelanggan getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(Pelanggan pelanggan) {
		this.pelanggan = pelanggan;
	}

	public List<RekamMedis> getRekamMedis() {
		return rekamMedis;
	}

	public void setRekamMedis(List<RekamMedis> rekamMedis) {
		this.rekamMedis = rekamMedis;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}
	
}
