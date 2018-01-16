package id.edmaputra.uwati.entity.pasien;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="rekam_medis_detail", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RekamMedisDetail extends DasarEntity<Long>{

	private static final long serialVersionUID = -483614842386945638L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rekam_medis", nullable = true)
	@JsonIgnore
	private RekamMedis rekamMedis;
		
	@Column(name="terapi", nullable=false, length=DBConf.LENGTH_NAMA)
	private String terapi;
	
	@Column(name="jumlah", nullable = false)	
	private Integer jumlah;
	
	@Column(name="harga", nullable=false)
	private BigDecimal hargaJual;
	
	@Column(name="diskon", nullable=true)
	private BigDecimal diskon;
	
	@Column(name="pajak", nullable=true)
	private BigDecimal pajak;
		
	@Column(name="harga_total", nullable=false)
	private BigDecimal hargaTotal;
	
	@Column(name="tipe", nullable=true)
	private int tipe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RekamMedis getRekamMedis() {
		return rekamMedis;
	}

	public void setRekamMedis(RekamMedis rekamMedis) {
		this.rekamMedis = rekamMedis;
	}

	public String getTerapi() {
		return terapi;
	}

	public void setTerapi(String terapi) {
		this.terapi = terapi;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public BigDecimal getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(BigDecimal hargaJual) {
		this.hargaJual = hargaJual;
	}

	public BigDecimal getDiskon() {
		return diskon;
	}

	public void setDiskon(BigDecimal diskon) {
		this.diskon = diskon;
	}

	public BigDecimal getPajak() {
		return pajak;
	}

	public void setPajak(BigDecimal pajak) {
		this.pajak = pajak;
	}

	public BigDecimal getHargaTotal() {
		return hargaTotal;
	}

	public void setHargaTotal(BigDecimal hargaTotal) {
		this.hargaTotal = hargaTotal;
	}

	public int getTipe() {
		return tipe;
	}

	public void setTipe(int tipe) {
		this.tipe = tipe;
	}	
	
}
