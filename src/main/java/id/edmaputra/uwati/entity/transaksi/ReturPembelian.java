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

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarTransaksiEntity;
import id.edmaputra.uwati.entity.pengguna.Pengguna;

@Entity
@Table(name = "retur_pembelian", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReturPembelian extends DasarTransaksiEntity<Long> {


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

	private BigDecimal grandTotal;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "deadline")
	private Date deadline;
	
	@Column(name = "supplier")
	private String supplier;
	
//	@OneToMany(mappedBy="returPembelian", cascade=CascadeType.ALL, orphanRemoval=true)
//	private List<ReturPembelianDetail> returPembelianDetail;

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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	

}
