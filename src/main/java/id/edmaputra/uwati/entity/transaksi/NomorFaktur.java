package id.edmaputra.uwati.entity.transaksi;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarTransaksiEntity;

@Entity
@Table(name = "nomorFaktur")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class NomorFaktur extends DasarTransaksiEntity<Long>{

	private static final long serialVersionUID = -2093256738213786669L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Integer nomor;
	
	@Column(nullable = false)
	private Boolean isSelesai;
	
	@Column(nullable = false)
	private Boolean isTerpakai;
	
	@Column(name = "tanggal", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNomor() {
		return nomor;
	}

	public void setNomor(Integer nomor) {
		this.nomor = nomor;
	}

	public Boolean getIsSelesai() {
		return isSelesai;
	}

	public void setIsSelesai(Boolean isDone) {
		this.isSelesai = isDone;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public Boolean getIsTerpakai() {
		return isTerpakai;
	}

	public void setIsTerpakai(Boolean isTerpakai) {
		this.isTerpakai = isTerpakai;
	}
	
}
