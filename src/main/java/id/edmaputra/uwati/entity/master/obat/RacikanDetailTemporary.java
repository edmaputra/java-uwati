package id.edmaputra.uwati.entity.master.obat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name = "racikan_detail_temp")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RacikanDetailTemporary extends DasarEntity<Long> {

	private static final long serialVersionUID = 2605773865176568103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "random_id", nullable = true)
	private String randomId;

	@Column(name = "racikan", nullable = true)
	private String racikan;

	@Column(name = "komposisi", nullable = false)
	private String komposisi;

	@Column(name = "jumlah", nullable = false)
	private String jumlah;

	@Column(name = "harga_satuan", nullable = false)
	private String hargaSatuan;

	@Column(name = "harga_total", nullable = false)
	private String hargaTotal;

	@Column(name = "is_sudah_diproses", nullable = true)
	private Boolean isSudahDiproses;

	@Column(name = "id_obat", nullable = false)
	private String idObat;

	public Boolean getIsSudahDiproses() {
		return isSudahDiproses;
	}

	public void setIsSudahDiproses(Boolean isSudahDiproses) {
		this.isSudahDiproses = isSudahDiproses;
	}

	public String getHargaTotal() {
		return hargaTotal;
	}

	public void setHargaTotal(String hargaTotal) {
		this.hargaTotal = hargaTotal;
	}

	public String getIdObat() {
		return idObat;
	}

	public void setIdObat(String idObat) {
		this.idObat = idObat;
	}

	public RacikanDetailTemporary() {
	}

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRacikan() {
		return racikan;
	}

	public void setRacikan(String racikan) {
		this.racikan = racikan;
	}

	public String getKomposisi() {
		return komposisi;
	}

	public void setKomposisi(String komposisi) {
		this.komposisi = komposisi;
	}

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	public String getHargaSatuan() {
		return hargaSatuan;
	}

	public void setHargaSatuan(String hargaSatuan) {
		this.hargaSatuan = hargaSatuan;
	}

	@Override
	public String toString() {
		return "RacikanDetail [racikan=" + racikan + ", komposisi=" + komposisi + ", jumlah=" + jumlah
				+ ", hargaSatuan=" + hargaSatuan + "]";
	}

}
