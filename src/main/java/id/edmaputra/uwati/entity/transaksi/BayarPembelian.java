package id.edmaputra.uwati.entity.transaksi;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import id.edmaputra.uwati.entity.Bayar;

@Entity
@Table(name = "bayar_pembelian", uniqueConstraints = { @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BayarPembelian extends Bayar<Long> {

	private static final long serialVersionUID = 7769140999910692567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pembelian")
	@JsonIgnore
	private Pembelian pembelian;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pembelian getPembelian() {
		return pembelian;
	}

	public void setPembelian(Pembelian pembelian) {
		this.pembelian = pembelian;
	}

	@Override
	public String toString() {
		return "BayarPembelian [id=" + id + ", pembelian=" + pembelian + ", getJumlahBayar()=" + getJumlahBayar() + "]";
	}
	
	
	
}
