package id.edmaputra.uwati.entity.master.obat;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name = "racikan")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Racikan extends DasarEntity<Long> {

	private static final long serialVersionUID = 2605773865176568103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nama", nullable = false, length = DBConf.LENGTH_NAMA)	
	private String nama;	
	
	@OneToMany(mappedBy="racikan", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<RacikanDetail> racikanDetail;	
		
	@Column(name = "harga_jual", nullable = false)
	private BigDecimal hargaJual;
	
	@Column(name = "biaya_racik", nullable = false)
	private BigDecimal biayaRacik;
	
	public Racikan(){
		
	}

	public Racikan(String nama, BigDecimal hargaJual, BigDecimal biayaRacik) {		
		this.nama = nama;
		this.hargaJual = hargaJual;
		this.biayaRacik = biayaRacik;
	}

	public Long getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public List<RacikanDetail> getRacikanDetail() {
		return racikanDetail;
	}

	public void setRacikanDetail(List<RacikanDetail> racikanDetail) {
		this.racikanDetail = racikanDetail;
	}

	public BigDecimal getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(BigDecimal hargaJual) {
		this.hargaJual = hargaJual;
	}

	public BigDecimal getBiayaRacik() {
		return biayaRacik;
	}

	public void setBiayaRacik(BigDecimal biayaRacik) {
		this.biayaRacik = biayaRacik;
	}

	@Override
	public String toString() {
		return "Racikan [nama=" + nama + ", racikanDetail=" + racikanDetail + ", hargaJual=" + hargaJual
				+ ", biayaRacik=" + biayaRacik + "]";
	}
	
}
