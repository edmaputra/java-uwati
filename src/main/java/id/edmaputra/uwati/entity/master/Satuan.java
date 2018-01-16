package id.edmaputra.uwati.entity.master;

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
@Table(name="satuan", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "nama") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Satuan extends DasarEntity<Integer> {

	private static final long serialVersionUID = 4762936026977759007L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;

	@Column(name = "nama", nullable = false, length = DBConf.LENGTH_NAMA_ROLE)	
	private String nama;	

	public Satuan(){
		
	}
	
	public Satuan(String nama) {				
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
		return "Satuan [id=" + id + ", nama=" + nama + "]";
	}

}
