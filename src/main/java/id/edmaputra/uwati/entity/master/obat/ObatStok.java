package id.edmaputra.uwati.entity.master.obat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name = "obat_stok", indexes = { @Index(columnList = "id_obat") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ObatStok extends DasarEntity<Long> {

	private static final long serialVersionUID = 3092154796857130317L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obat", nullable = false)
	@JsonIgnore
	private Obat obat;

	@Column(name = "stok", nullable = false)
	private Integer stok;

	public ObatStok() {

	}

	public ObatStok(Obat obat, Integer stok) {
		super();
		this.obat = obat;
		this.stok = stok;
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

	public Integer getStok() {
		return stok;
	}

	public void setStok(Integer stok) {
		this.stok = stok;
	}

}
