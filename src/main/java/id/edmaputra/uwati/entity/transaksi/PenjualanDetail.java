package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import id.edmaputra.uwati.entity.DasarEntity;
import id.edmaputra.uwati.entity.DasarTransaksiEntity;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;

@Entity
@Table(name = "penjualan_detail", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PenjualanDetail extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 3548899586680273181L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_obat_detail", nullable = false)    
	private ObatDetail obatDetail;
	
	@Column(nullable=false)	
	private Integer jumlah;
		
	private BigDecimal hargaJual;
	
	private BigDecimal diskon;
	
	private BigDecimal pajak;
		
	private BigDecimal hargaTotal;
	
	private Boolean isRacikan;
	
	@ManyToOne
	@JoinColumn(name="id_penjualan")
	@JsonIgnore
	private Penjualan penjualan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ObatDetail getObatDetail() {
		return obatDetail;
	}

	public void setObatDetail(ObatDetail obatDetail) {
		this.obatDetail = obatDetail;
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

}
