package id.edmaputra.uwati.entity.master;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="apotek", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Apotek extends DasarEntity<Integer>{
	
	private static final long serialVersionUID = 4888650963869604003L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nama", length=DBConf.LENGTH_NAMA)
	private String nama;
	
	@Column(name="alamat", length=DBConf.LENGTH_ALAMAT)
	private String alamat;

	@Column(name="telepon", length=DBConf.LENGTH_TELEPON)	
	private String telepon;
	
	@Column(name="biaya_resep", nullable = false)	
	private BigDecimal biayaResep;
	
	@Column(name="tenggat_kadaluarsa")
	private Integer tenggatKadaluarsa;
	
	public BigDecimal getBiayaResep() {
		return biayaResep;
	}

	public void setBiayaResep(BigDecimal biayaResep) {
		this.biayaResep = biayaResep;
	}

	public Apotek(){
		
	}

	public Apotek(String nama, String alamat, String telepon) {
		super();
		this.nama = nama;
		this.alamat = alamat;
		this.telepon = telepon;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTelepon() {
		return telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public Integer getTenggatKadaluarsa() {
		return tenggatKadaluarsa;
	}

	public void setTenggatKadaluarsa(Integer tenggatExpire) {
		this.tenggatKadaluarsa = tenggatExpire;
	}
}
