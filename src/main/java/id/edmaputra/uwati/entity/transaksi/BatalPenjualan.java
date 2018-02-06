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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarTransaksiEntity;

@Entity
@Table(name = "batal_penjualan", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BatalPenjualan extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 3092154796857130317L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "waktu_transaksi")
	private Date waktuTransaksi;
	
	@Column(name="kasir", length = DBConf.LENGTH_NAMA)
	private String pengguna;
	
	@Column(name="pelanggan", length = DBConf.LENGTH_NAMA)
	private String pelanggan;
	
	@Column(name="dokter", length = DBConf.LENGTH_NAMA)
	private String dokter;
	
	@Column(name="nomor_faktur", nullable=false, length=DBConf.LENGTH_TRANSAKSI_NOMORFAKTUR)	
	private String nomorFaktur;
		
	private BigDecimal totalPembelian;
	
	private BigDecimal diskon;
	
	private BigDecimal grandTotal;
	
	private BigDecimal bayar;
	
	private BigDecimal kembali;
	
	private BigDecimal pajak;

	@Column(name="tipe", nullable = false)
	private Integer tipe;
	
	@Column(length=DBConf.LENGTH_TRANSAKSI_NOMORRESEP, nullable = true)
	private String nomorResep;
		
	@OneToMany(mappedBy="batalPenjualan", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
	private List<BatalPenjualanDetail> batalPenjualanDetail;
	
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

	public String getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(String pelanggan) {
		this.pelanggan = pelanggan;
	}

	public String getDokter() {
		return dokter;
	}

	public void setDokter(String dokter) {
		this.dokter = dokter;
	}

	public String getNomorFaktur() {
		return nomorFaktur;
	}

	public void setNomorFaktur(String nomorFaktur) {
		this.nomorFaktur = nomorFaktur;
	}

	public BigDecimal getTotalPembelian() {
		return totalPembelian;
	}

	public void setTotalPembelian(BigDecimal totalPembelian) {
		this.totalPembelian = totalPembelian;
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

	public List<BatalPenjualanDetail> getBatalPenjualanDetail() {
		return batalPenjualanDetail;
	}

	public void setBatalPenjualanDetail(List<BatalPenjualanDetail> penjualanDetailBatal) {
		this.batalPenjualanDetail = penjualanDetailBatal;
	}

	public Date getWaktuPembatalan() {
		return waktuPembatalan;
	}

	public void setWaktuPembatalan(Date waktuPembatalan) {
		this.waktuPembatalan = waktuPembatalan;
	}
	
	

}
