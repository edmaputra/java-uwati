package id.edmaputra.uwati.entity.master.obat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name = "obat_expired", indexes = { @Index(columnList = "id_obat") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ObatExpired extends DasarEntity<Long> {

	private static final long serialVersionUID = 2605773865176568103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obat", nullable = false)
	@JsonIgnore
	private Obat obat;

	@Temporal(TemporalType.DATE)
	private Date tanggalExpired;

	public ObatExpired() {

	}

	public ObatExpired(Obat obat, Date tanggalExpired) {
		super();
		this.obat = obat;
		this.tanggalExpired = tanggalExpired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Obat getObat() {
		return obat;
	}

	public void setObat(Obat obat) {
		this.obat = obat;
	}

	public Date getTanggalExpired() {
		return tanggalExpired;
	}

	public void setTanggalExpired(Date tanggalExpired) {
		this.tanggalExpired = tanggalExpired;
	}
}
