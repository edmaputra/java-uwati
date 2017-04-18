package id.edmaputra.uwati.entity.transaksi;

import java.math.BigDecimal;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.entity.DasarTransaksiEntity;
import id.edmaputra.uwati.entity.master.obat.Racikan;

@Entity
@Table(name = "penjualan_racikan", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PenjualanRacikan extends DasarTransaksiEntity<Long> {

	private static final long serialVersionUID = 2605773865176568103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_racikan", nullable = false)
	private Racikan racikan;
	
	@OneToMany(mappedBy="penjualanRacikan", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<PenjualanDetailRacikan> racikanDetail;	
		
	@Column(name = "harga_jual", nullable = false)
	private BigDecimal hargaJual;
	
	@ManyToOne
	@JoinColumn(name="id_penjualan")
	@JsonIgnore
	private Penjualan penjualan;

	public Long getId() {
		return id;
	}

	public Racikan getRacikan() {
		return racikan;
	}

	public void setRacikan(Racikan racikan) {
		this.racikan = racikan;
	}

	public List<PenjualanDetailRacikan> getRacikanDetail() {
		return racikanDetail;
	}

	public void setRacikanDetail(List<PenjualanDetailRacikan> racikanDetail) {
		this.racikanDetail = racikanDetail;
	}

	public Penjualan getPenjualan() {
		return penjualan;
	}

	public void setPenjualan(Penjualan penjualan) {
		this.penjualan = penjualan;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(BigDecimal hargaJual) {
		this.hargaJual = hargaJual;
	}
	
}
