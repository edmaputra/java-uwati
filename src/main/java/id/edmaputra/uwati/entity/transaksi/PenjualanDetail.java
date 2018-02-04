package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarTransaksiEntity;

@Entity
@Table(name = "penjualan_detail", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PenjualanDetail extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 3548899586680273181L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(nullable=false, name = "obat", length = DBConf.LENGTH_NAMA) 
	private String obat;
	
	@Column(nullable=false)	
	private Integer jumlah;
		
	private BigDecimal hargaJual;
	
	private BigDecimal diskon;
	
	private BigDecimal pajak;
		
	private BigDecimal hargaTotal;
	
	private Boolean isRacikan;
	
	@OneToMany(mappedBy="penjualanDetail", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
	private List<PenjualanDetailRacikan> racikanDetail;	
	
	public List<PenjualanDetailRacikan> getRacikanDetail() {
		return racikanDetail;
	}

	public void setRacikanDetail(List<PenjualanDetailRacikan> racikanDetail) {
		this.racikanDetail = racikanDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_penjualan")
	@JsonIgnore
	private Penjualan penjualan;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObat() {
		return obat;
	}

	public void setObat(String obatDetail) {
		this.obat = obatDetail;
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

	public Boolean getIsRacikan() {
		return isRacikan;
	}

	public void setIsRacikan(Boolean isRacikan) {
		this.isRacikan = isRacikan;
	}

	public Penjualan getPenjualan() {
		return penjualan;
	}

	public void setPenjualan(Penjualan penjualan) {
		this.penjualan = penjualan;
	}

	@Override
	public String toString() {
		return "PenjualanDetail [id=" + id + ", obat=" + obat + ", jumlah=" + jumlah + ", hargaJual=" + hargaJual
				+ ", diskon=" + diskon + ", pajak=" + pajak + ", hargaTotal=" + hargaTotal + ", isRacikan=" + isRacikan
				+ ", racikanDetail=" + racikanDetail + "]";
	} 

}
