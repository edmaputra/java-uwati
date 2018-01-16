package id.edmaputra.uwati.entity.pasien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="rekam_medis_diagnosa", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RekamMedisDiagnosa extends DasarEntity<Long>{

	private static final long serialVersionUID = -483614842386945638L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rekam_medis", nullable = true)
	@JsonIgnore
	private RekamMedis rekamMedis;
		
	@Column(name="diagnosa", nullable=false, length=200)
	private String Diagnosa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RekamMedis getRekamMedis() {
		return rekamMedis;
	}

	public void setRekamMedis(RekamMedis rekamMedis) {
		this.rekamMedis = rekamMedis;
	}

	public String getDiagnosa() {
		return Diagnosa;
	}

	public void setDiagnosa(String diagnosa) {
		Diagnosa = diagnosa;
	}
	
	
}
