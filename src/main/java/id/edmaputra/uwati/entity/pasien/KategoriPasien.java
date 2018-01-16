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

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="kategori_pasien", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "nama") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class KategoriPasien extends DasarEntity<Integer> {

	private static final long serialVersionUID = 3092154796857130317L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nama", nullable = false, length = DBConf.LENGTH_NAMA_KATEGORI)
	private String nama;		
	
	public KategoriPasien(){};

	public KategoriPasien(String nama) {
		this.nama = nama;
	}

	public Integer getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Override
	public String toString() {
		return "Kategori Pasien [id=" + id + ", nama=" + nama + "]";
	}
	

}
