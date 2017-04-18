package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import id.edmaputra.uwati.config.DBConf;
import id.edmaputra.uwati.entity.DasarTransaksiEntity;
import id.edmaputra.uwati.entity.master.Dokter;
import id.edmaputra.uwati.entity.master.Pelanggan;
import id.edmaputra.uwati.entity.pengguna.Pengguna;

@Entity
@Table(name = "penjualan", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Penjualan extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 3092154796857130317L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "waktu_transaksi")
	private Date waktuTransaksi;
	
	@ManyToOne
    @JoinColumn(name = "id_pengguna")
	private Pengguna pengguna;
	
	@ManyToOne
    @JoinColumn(name = "id_pelanggan", nullable = true)
	private Pelanggan pelanggan;
	
	@Column(name="nomor_faktur", nullable=false, length=DBConf.LENGTH_TRANSAKSI_NOMORFAKTUR)	
	private String nomorFaktur;
		
	private BigDecimal totalPembelian;
	
	private BigDecimal diskon;
	
	private BigDecimal grandTotal;
	
	private BigDecimal bayar;
	
	private BigDecimal kembali;
	
	private BigDecimal pajak;
		
	private Integer tipe;
	
	@Column(length=DBConf.LENGTH_TRANSAKSI_NOMORRESEP, nullable = true)
	private String nomorResep;
	
	@ManyToOne
    @JoinColumn(name = "id_dokter", nullable = true)
	private Dokter dokter;
	
	@OneToMany(mappedBy="penjualan", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<PenjualanDetail> penjualanDetail;
	
	@OneToMany(mappedBy="penjualan", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<PenjualanRacikan> penjualanRacikan;
	
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

	public Pelanggan getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(Pelanggan pelanggan) {
		this.pelanggan = pelanggan;
	}

	public String getNomorFaktur() {
		return nomorFaktur;
	}

	public void setNomorFaktur(String nomorFaktur) {
		this.nomorFaktur = nomorFaktur;
	}

	public Integer getTipe() {
		return tipe;
	}

	public void setTipe(Integer tipe) {
		this.tipe = tipe;
	}

	public String getNomorResep() {
		return nomorResep;
	}

	public void setNomorResep(String nomorResep) {
		this.nomorResep = nomorResep;
	}

	public Dokter getDokter() {
		return dokter;
	}

	public void setDokter(Dokter dokter) {
		this.dokter = dokter;
	}

	public List<PenjualanDetail> getPenjualanDetail() {
		return penjualanDetail;
	}

	public void setPenjualanDetail(List<PenjualanDetail> penjualanDetail) {
		this.penjualanDetail = penjualanDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public BigDecimal getTotalPembelian() {
		return totalPembelian;
	}

	public void setTotalPembelian(BigDecimal subTotal) {
		this.totalPembelian = subTotal;
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

	public BigDecimal getBayar() {
		return bayar;
	}

	public void setBayar(BigDecimal bayar) {
		this.bayar = bayar;
	}

	public BigDecimal getKembali() {
		return kembali;
	}

	public void setKembali(BigDecimal kembali) {
		this.kembali = kembali;
	}

	public BigDecimal getPajak() {
		return pajak;
	}

	public void setPajak(BigDecimal pajak) {
		this.pajak = pajak;
	}

	public List<PenjualanRacikan> getPenjualanRacikan() {
		return penjualanRacikan;
	}

	public void setPenjualanRacikan(List<PenjualanRacikan> penjualanRacikan) {
		this.penjualanRacikan = penjualanRacikan;
	}

}
