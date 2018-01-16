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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarTransaksiEntity;

@Entity
@Table(name = "batal_penjualan_detail_racikan", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BatalPenjualanDetailRacikan extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 2605773865176568103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_racikan", nullable = false)
	private BatalPenjualanDetail batalPenjualanDetail;
	
	@Column(name="komposisi", nullable=false, length = DBConf.LENGTH_NAMA)
	private String komposisi;
	
	@Column(name="jumlah", nullable=false)
	private Integer jumlah;
	
	@Column(nullable=false)
	private BigDecimal hargaJualPerKomposisi;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BatalPenjualanDetail getBatalPenjualanDetail() {
		return batalPenjualanDetail;
	}

	public void setBatalPenjualanDetail(BatalPenjualanDetail penjualanDetailBatal) {
		this.batalPenjualanDetail = penjualanDetailBatal;
	}

	public String getKomposisi() {
		return komposisi;
	}

	public void setKomposisi(String komposisi) {
		this.komposisi = komposisi;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public BigDecimal getHargaJualPerKomposisi() {
		return hargaJualPerKomposisi;
	}

	public void setHargaJualPerKomposisi(BigDecimal hargaJualPerKomposisi) {
		this.hargaJualPerKomposisi = hargaJualPerKomposisi;
	}

	


}
