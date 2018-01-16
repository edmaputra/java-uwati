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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;
import id.edmaputra.uwati.entity.master.Karyawan;

@Entity
@Table(name="rekam_medis", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "nomor") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RekamMedis extends DasarEntity<Long>{

	private static final long serialVersionUID = 2272001067197118594L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tanggal", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tanggal;
		
	@Column(name="nomor", nullable=true, length=DBConf.LENGTH_TRANSAKSI_NOMORREKAMMEDIS)
	private String nomor;
	
	@Lob
	@Column(name="anamnesa", nullable=true, length = 512)
	private String anamnesa;
	
	@Lob
	@Column(name="pemeriksaan", nullable=true, length = 512)
	private String pemeriksaan;
	
	@Lob
	@Column(name="diagnosa", nullable=true, length = 512)
	private String diagnosa;
	
	@Column(name="status_resep_diproses", nullable = true)
	private Boolean isResepSudahDiproses;
	
	@Column(name="status_simpan", nullable = true)
	private Boolean isSudahDisimpan;
	
	@Column(name="status_masuk_list_resep", nullable = true)
	private Boolean isMasukListResep;
	
	@Column(name="kunjungan", nullable = false)
	private Integer kunjungan;
	
	@OneToMany(mappedBy = "rekamMedis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)	
	private List<RekamMedisDetail> rekamMedisDetail;
	
	@OneToMany(mappedBy = "rekamMedis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)	
	private List<RekamMedisDiagnosa> rekamMedisDiagnosa;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dokter", nullable = true)
	@JsonIgnore
	private Karyawan dokter;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pasien", nullable = true)
	@JsonIgnore
	private Pasien pasien;
	
	@Column(name="pernafasan", nullable=true, length = 30)
	private String pernafasan;
	
	@Column(name="tekanan_darah", nullable=true, length = 30)
	private String tekananDarah;
	
	@Column(name="nadi", nullable=true, length = 30)
	private String nadi;
	
	@Column(name="suhu_tubuh", nullable=true, length = 30)
	private String suhuTubuh;
	
	@Column(name="tinggi_badan", nullable=true, length = 30)
	private String tinggiBadan;
	
	@Column(name="berat_badan", nullable=true, length = 30)
	private String beratBadan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	public String getAnamnesa() {
		return anamnesa;
	}

	public void setAnamnesa(String anamnesa) {
		this.anamnesa = anamnesa;
	}

	public String getPemeriksaan() {
		return pemeriksaan;
	}

	public void setPemeriksaan(String pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}

	public String getDiagnosa() {
		return diagnosa;
	}

	public void setDiagnosa(String diagnosa) {
		this.diagnosa = diagnosa;
	}

	public List<RekamMedisDetail> getRekamMedisDetail() {
		return rekamMedisDetail;
	}

	public void setRekamMedisDetail(List<RekamMedisDetail> terapi) {
		this.rekamMedisDetail = terapi;
	}

	public Karyawan getDokter() {
		return dokter;
	}

	public void setDokter(Karyawan dokter) {
		this.dokter = dokter;
	}

	public Boolean getIsResepSudahDiproses() {
		return isResepSudahDiproses;
	}

	public void setIsResepSudahDiproses(Boolean isResepSudahDiproses) {
		this.isResepSudahDiproses = isResepSudahDiproses;
	}

	public Boolean getIsSudahDisimpan() {
		return isSudahDisimpan;
	}

	public void setIsSudahDisimpan(Boolean isSudahDisimpan) {
		this.isSudahDisimpan = isSudahDisimpan;
	}

	public Pasien getPasien() {
		return pasien;
	}

	public void setPasien(Pasien pasien) {
		this.pasien = pasien;
	}

	public Boolean isMasukListResep() {
		return isMasukListResep;
	}

	public void setIsMasukListResep(Boolean isSudahTerjual) {
		this.isMasukListResep = isSudahTerjual;
	}

	public Integer getKunjungan() {
		return kunjungan;
	}

	public void setKunjungan(Integer kunjungan) {
		this.kunjungan = kunjungan;
	}
	
	public List<RekamMedisDiagnosa> getRekamMedisDiagnosa() {
		return rekamMedisDiagnosa;
	}

	public void setRekamMedisDiagnosa(List<RekamMedisDiagnosa> rekamMedisDiagnosa) {
		this.rekamMedisDiagnosa = rekamMedisDiagnosa;
	}
	
	public Boolean getIsMasukListResep() {
		return isMasukListResep;
	}

	public String getPernafasan() {
		return pernafasan;
	}

	public void setPernafasan(String pernafasan) {
		this.pernafasan = pernafasan;
	}

	public String getTekananDarah() {
		return tekananDarah;
	}

	public void setTekananDarah(String tekananDarah) {
		this.tekananDarah = tekananDarah;
	}

	public String getNadi() {
		return nadi;
	}

	public void setNadi(String nadi) {
		this.nadi = nadi;
	}

	public String getSuhuTubuh() {
		return suhuTubuh;
	}

	public void setSuhuTubuh(String suhuTubuh) {
		this.suhuTubuh = suhuTubuh;
	}

	public String getTinggiBadan() {
		return tinggiBadan;
	}

	public void setTinggiBadan(String tinggiBadan) {
		this.tinggiBadan = tinggiBadan;
	}

	public String getBeratBadan() {
		return beratBadan;
	}

	public void setBeratBadan(String beratBadan) {
		this.beratBadan = beratBadan;
	}
	
}
