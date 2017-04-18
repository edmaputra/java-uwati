package id.edmaputra.uwati.entity.master.obat;

import java.math.BigDecimal;

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
@Table(name = "obat_detail", indexes = { @Index(columnList = "id_obat") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ObatDetail extends DasarEntity<Long> {

	private static final long serialVersionUID = 2605773865176568103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obat", nullable = false)
	@JsonIgnore
	private Obat obat;

	@Column(name = "harga_jual", nullable = false)
	private BigDecimal hargaJual;

	@Column(name = "harga_beli", nullable = false)
	private BigDecimal hargaBeli;

	@Column(name = "harga_diskon", nullable = true)
	private BigDecimal hargaDiskon;

	@Column(name = "harga_jual_resep", nullable = false)
	private BigDecimal hargaJualResep;

	public ObatDetail() {
	}

	public ObatDetail(Obat obat, BigDecimal hargaJual, BigDecimal hargaBeli, BigDecimal hargaDiskon,
			BigDecimal hargaJualResep) {
		this.obat = obat;
		this.hargaJual = hargaJual;
		this.hargaBeli = hargaBeli;
		this.hargaDiskon = hargaDiskon;
		this.hargaJualResep = hargaJualResep;
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

	public BigDecimal getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(BigDecimal hargaJual) {
		this.hargaJual = hargaJual;
	}

	public BigDecimal getHargaBeli() {
		return hargaBeli;
	}

	public void setHargaBeli(BigDecimal hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public BigDecimal getHargaDiskon() {
		return hargaDiskon;
	}

	public void setHargaDiskon(BigDecimal hargaDiskon) {
		this.hargaDiskon = hargaDiskon;
	}

	public BigDecimal getHargaJualResep() {
		return hargaJualResep;
	}

	public void setHargaJualResep(BigDecimal hargaJualResep) {
		this.hargaJualResep = hargaJualResep;
	}
}
