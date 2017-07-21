package id.edmaputra.uwati.entity.master.obat;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name = "racikan_detail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RacikanDetail extends DasarEntity<Long> {

	private static final long serialVersionUID = 2605773865176568103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_racikan", nullable = false)
	@JsonIgnore
	private Racikan racikan;

	@ManyToOne
	private Obat komposisi;

	@Column(name = "jumlah", nullable = false)
	private Integer jumlah;

	@Column(name = "harga_satuan", nullable = false)
	private BigDecimal hargaSatuan;

	@Column(name = "harga_total", nullable = false)
	private BigDecimal hargaTotal;

	public BigDecimal getHargaTotal() {
		return hargaTotal;
	}

	public void setHargaTotal(BigDecimal hargaTotal) {
		this.hargaTotal = hargaTotal;
	}

	public RacikanDetail() {
	}

	public RacikanDetail(Racikan racikan, Obat komposisi, Integer jumlah, BigDecimal hargaSatuan) {
		this.racikan = racikan;
		this.komposisi = komposisi;
		this.jumlah = jumlah;
		this.hargaSatuan = hargaSatuan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Racikan getRacikan() {
		return racikan;
	}

	public void setRacikan(Racikan racikan) {
		this.racikan = racikan;
	}

	public Obat getKomposisi() {
		return komposisi;
	}

	public void setKomposisi(Obat komposisi) {
		this.komposisi = komposisi;
	}

	public BigDecimal getHargaSatuan() {
		return hargaSatuan;
	}

	public void setHargaSatuan(BigDecimal hargaJualPerKomposisi) {
		this.hargaSatuan = hargaJualPerKomposisi;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	@Override
	public String toString() {
		return "RacikanDetail [racikan=" + racikan + ", komposisi=" + komposisi + ", jumlah=" + jumlah
				+ ", hargaSatuan=" + hargaSatuan + "]";
	}

}
