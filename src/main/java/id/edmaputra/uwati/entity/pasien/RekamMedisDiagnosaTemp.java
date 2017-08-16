package id.edmaputra.uwati.entity.pasien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.config.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="rekam_medis_diagnosa_temp", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RekamMedisDiagnosaTemp extends DasarEntity<Long> {
	
	private static final long serialVersionUID = 2351726938294178806L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="id_diagnosa", nullable=true, length=DBConf.LENGTH_TRANSAKSI_NOMORFAKTUR)
	private String idDiagnosa;
	
	@Column(name="diagnosa", nullable=true, length=DBConf.LENGTH_TRANSAKSI_NOMORFAKTUR)
	private String diagnosa;
	
	@Column(name="randomId", nullable=true)
	private String randomId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdDiagnosa() {
		return idDiagnosa;
	}

	public void setIdDiagnosa(String idDiagnosa) {
		this.idDiagnosa = idDiagnosa;
	}

	public String getDiagnosa() {
		return diagnosa;
	}

	public void setDiagnosa(String diagnosa) {
		this.diagnosa = diagnosa;
	}

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

}
