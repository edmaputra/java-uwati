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
@Table(name = "batal_penjualan_detail", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BatalPenjualanDetail extends DasarTransaksiEntity<Long> {

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
	
	@OneToMany(mappedBy="batalPenjualanDetail", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
	private List<BatalPenjualanDetailRacikan> batalPenjualanDetailRacikan;		

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_penjualan")
	@JsonIgnore
	private BatalPenjualan batalPenjualan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<BatalPenjualanDetailRacikan> getBatalPenjualanDetailRacikan() {
		return batalPenjualanDetailRacikan;
	}

	public void setBatalPenjualanDetailRacikan(List<BatalPenjualanDetailRacikan> racikanDetailBatal) {
		this.batalPenjualanDetailRacikan = racikanDetailBatal;
	}

	public BatalPenjualan getBatalPenjualan() {
		return batalPenjualan;
	}

	public void setBatalPenjualan(BatalPenjualan penjualanBatal) {
		this.batalPenjualan = penjualanBatal;
	}

}
