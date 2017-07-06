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

import id.edmaputra.uwati.config.DBConf;
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
	
	@OneToMany(mappedBy = "rekamMedis", cascade = CascadeType.ALL)	
	private List<RekamMedisDetail> rekamMedisDetail;	
	
	@OneToOne
	@JoinColumn(name = "id_dokter")
	private Karyawan dokter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pasien", nullable = false)
	@JsonIgnore
	private Pasien pasien;

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
	
}
