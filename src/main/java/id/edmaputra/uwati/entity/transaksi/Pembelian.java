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
import id.edmaputra.uwati.view.Formatter;

@Entity
@Table(name = "pembelian", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pembelian extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 7769140999910692567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "waktu_transaksi")
	private Date waktuTransaksi;
	
	@ManyToOne
    @JoinColumn(name = "id_pengguna")
	private Pengguna pengguna;
	
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
	
	@OneToMany(mappedBy="pembelian", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
	private List<PembelianDetail> pembelianDetail;
	
	@OneToMany(mappedBy = "pembelian", fetch = FetchType.LAZY, orphanRemoval = true, cascade=CascadeType.ALL)
	private List<BayarPembelian> bayarPembelian;
	
	@Column(name="status_lunas", nullable = true)
	private Boolean lunas;
	
	public boolean sisaUtangAvailable() {
		BigDecimal sisaUtang = new BigDecimal(0);
		for (BayarPembelian bayar : this.bayarPembelian) {
			sisaUtang.add(bayar.getJumlahBayar());
		}
		if (this.grandTotal.compareTo(sisaUtang) > 0) {
			return true;
		}
		return false;		
	}
	
	public BigDecimal sisaUtang() {
		BigDecimal totalDibayar = new BigDecimal(0);
		for (BayarPembelian bayar : this.bayarPembelian) {
			totalDibayar.add(bayar.getJumlahBayar());
		}
		return this.grandTotal.subtract(totalDibayar);
	}
	
	public String sisaUtangString() {		
		return Formatter.patternCurrency(sisaUtang());
	}

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

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public String getNomorFaktur() {
		return nomorFaktur;
	}

	public void setNomorFaktur(String nomorFaktur) {
		this.nomorFaktur = nomorFaktur;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	
	public String getGrandTotalString() {
		return Formatter.patternCurrency(grandTotal);
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Date getWaktuBerakhir() {
		return deadline;
	}

	public void setWaktuBerakhir(Date waktuBerakhir) {
		this.deadline = waktuBerakhir;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public List<PembelianDetail> getPembelianDetail() {
		return pembelianDetail;
	}

	public void setPembelianDetail(List<PembelianDetail> pembelianDxetail) {
		this.pembelianDetail = pembelianDxetail;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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

	public BigDecimal getTotal() {
		return total;
	}
	
	public String getTotalString() {
		return Formatter.patternCurrency(this.total);
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<BayarPembelian> getBayarPembelian() {
		return bayarPembelian;
	}

	public void setBayarPembelian(List<BayarPembelian> bayarPembelian) {
		this.bayarPembelian = bayarPembelian;
	}

	public Boolean getLunas() {
		return lunas;
	}

	public void setLunas(Boolean lunas) {
		this.lunas = lunas;
	}
}
