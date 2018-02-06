package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarTransaksiEntity;
import id.edmaputra.uwati.entity.pengguna.Pengguna;

@Entity
@Table(name = "batal_pembelian", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BatalPembelian extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 7769140999910692567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "waktu_transaksi")
	private Date waktuTransaksi;
	
	@Column(name="pengguna", nullable=false, length=DBConf.LENGTH_NAMA_PENGGUNA)
	private String pengguna;
	
	@Column(name="nomor_faktur", nullable=false, length=DBConf.LENGTH_TRANSAKSI_NOMORFAKTUR)	
	private String nomorFaktur;
	
	private BigDecimal pajak;
	
	private BigDecimal diskon;
	
	private BigDecimal grandTotal;
	
	private BigDecimal total;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline")    
	private Date deadline;
	
	@Column(name = "supplier")
	private String supplier;
	
	@OneToMany(mappedBy="batalPembelian", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
	private List<BatalPembelianDetail> batalPembelianDetail;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "waktu_pembatalan")
	private Date waktuPembatalan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getWaktuTransaksi() {
		return waktuTransaksi;
	}

	public void setWaktuTransaksi(Date waktuTransaksi) {
		this.waktuTransaksi = waktuTransaksi;
	}

	public String getPengguna() {
		return pengguna;
	}

	public void setPengguna(String pengguna) {
		this.pengguna = pengguna;
	}

	public String getNomorFaktur() {
		return nomorFaktur;
	}

	public void setNomorFaktur(String nomorFaktur) {
		this.nomorFaktur = nomorFaktur;
	}

	public BigDecimal getPajak() {
		return pajak;
	}

	public void setPajak(BigDecimal pajak) {
		this.pajak = pajak;
	}

	public BigDecimal getDiskon() {
		return diskon;
	}

	public void setDiskon(BigDecimal diskon) {
		this.diskon = diskon;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public List<BatalPembelianDetail> getBatalPembelianDetail() {
		return batalPembelianDetail;
	}

	public void setBatalPembelianDetail(List<BatalPembelianDetail> batalPembelianDetail) {
		this.batalPembelianDetail = batalPembelianDetail;
	}

	public Date getWaktuPembatalan() {
		return waktuPembatalan;
	}

	public void setWaktuPembatalan(Date waktuPembatalan) {
		this.waktuPembatalan = waktuPembatalan;
	}

}
