package id.edmaputra.uwati.entity.master.obat;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name = "tindakan", uniqueConstraints = { @UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "nama")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tindakan extends DasarEntity<Long>{

	private static final long serialVersionUID = -5401806277308004092L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nama", nullable = false, length = DBConf.LENGTH_NAMA)
	private String nama;
	
	@Column(name = "kode", nullable = true, length = DBConf.LENGTH_KODE_OBAT)
	private String kode;
	
	@Column(name = "tarif", nullable = false)
	private BigDecimal tarif;
	
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

	public BigDecimal getTarif() {
		return tarif;
	}

	public void setTarif(BigDecimal biaya) {
		this.tarif = biaya;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode.toUpperCase();
	}

	@Override
	public String toString() {
		return "Tindakan [id=" + id + ", nama=" + nama + ", kode=" + kode + ", tarif=" + tarif + "]";
	}
	
}
