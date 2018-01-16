package id.edmaputra.uwati.entity.master.obat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name = "cek_stok")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CekStok extends DasarEntity<Integer> {

	private static final long serialVersionUID = 4762936026977759007L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "idObat", nullable = true, length = DBConf.LENGTH_NAMA_ROLE)
	private String idObat;

	@Column(name = "randomId", nullable = true, length = DBConf.LENGTH_NAMA_ROLE)
	private String randomId;

	@Column(name = "obat", nullable = true, length = DBConf.LENGTH_NAMA)
	private String obat;

	@Column(name = "jumlah", nullable = true)
	private Integer jumlah;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdObat() {
		return idObat;
	}

	public void setIdObat(String idObat) {
		this.idObat = idObat;
	}

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

	public String getObat() {
		return obat;
	}

	public void setObat(String obat) {
		this.obat = obat;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer stok) {
		this.jumlah = stok;
	}

}
