package id.edmaputra.uwati.entity.master.obat;

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
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.config.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;
import id.edmaputra.uwati.entity.master.Kategori;
import id.edmaputra.uwati.entity.master.Satuan;

@Entity
@Table(name = "obat", uniqueConstraints = { @UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "nama"), @UniqueConstraint(columnNames = "kode") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Obat extends DasarEntity<Long> {

	private static final long serialVersionUID = -1724188467187289522L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nama", nullable = false, length = DBConf.LENGTH_NAMA)
	private String nama;

	@Column(name = "kode", nullable = false, length = DBConf.LENGTH_KODE_OBAT)
	private String kode;

	@ManyToOne
	@JoinColumn(name = "id_satuan", nullable = false)
	private Satuan satuan;

	@ManyToOne
	@JoinColumn(name = "id_kategori", nullable = false)
	private Kategori kategori;

	@Column(name = "batch", nullable = true, length = DBConf.LENGTH_KODE_OBAT)
	private String batch;

	@Column(name = "barcode", nullable = true, length = DBConf.LENGTH_BARCODE)
	private String barcode;

	@OneToMany(mappedBy = "obat", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonSerialize(using = ObatDetailListSerializer.class)
	private List<ObatDetail> obatDetail;

	@OneToMany(mappedBy = "obat", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
	private List<ObatStok> stok;
	
	@OneToMany(mappedBy = "obat", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
	private List<ObatExpired> expired;

	@Column(name = "stok_minimal", nullable = false)
	private Integer stokMinimal;

	@Column(name = "tipe", nullable = false)
	private Integer tipe;

	public Obat() {
	}

	public Obat(String nama, String kode, Satuan satuan, Kategori kategori, String batch, String barcode,
			List<ObatDetail> obatDetail, List<ObatStok> stok, Integer stokMinimal, Integer tipe) {
		this.nama = nama;
		this.kode = kode;
		this.satuan = satuan;
		this.kategori = kategori;
		this.batch = batch;
		this.barcode = barcode;
		this.obatDetail = obatDetail;
		this.stok = stok;
		this.stokMinimal = stokMinimal;
		this.tipe = tipe;
	}
	
	public void addObatDetail(ObatDetail od){
		obatDetail.add(od);
		od.setObat(this);
	}
	
	public void deleteObatDetail(ObatDetail od){
		obatDetail.remove(od);
		od.setObat(null);
	}
	
	public void addStok(ObatStok s){
		stok.add(s);
		s.setObat(this);
	}
	
	public void deleteStok(ObatStok s){
		stok.remove(s);
		s.setObat(null);
	}
	
	public void addExpired(ObatExpired e){
		expired.add(e);
		e.setObat(this);
	}
	
	public void deleteExpired(ObatExpired e){
		expired.remove(e);
		e.setObat(null);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public Satuan getSatuan() {
		return satuan;
	}

	public void setSatuan(Satuan satuan) {
		this.satuan = satuan;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public List<ObatDetail> getObatDetail() {
		return obatDetail;
	}

	public void setObatDetail(List<ObatDetail> obatDetail) {
		this.obatDetail = obatDetail;
	}

	public List<ObatStok> getStok() {
		return stok;
	}

	public void setStok(List<ObatStok> stok) {
		this.stok = stok;
	}

	public Integer getStokMinimal() {
		return stokMinimal;
	}

	public void setStokMinimal(Integer stokMinimal) {
		this.stokMinimal = stokMinimal;
	}

	public Integer getTipe() {
		return tipe;
	}

	public void setTipe(Integer tipe) {
		this.tipe = tipe;
	}

	public List<ObatExpired> getExpired() {
		return expired;
	}

	public void setExpired(List<ObatExpired> expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Obat [id=" + id + ", nama=" + nama + ", kode=" + kode + ", satuan=" + satuan + ", kategori=" + kategori
				+ ", batch=" + batch + ", barcode=" + barcode + ", obatDetail=" + obatDetail + ", stok=" + stok
				+ ", expired=" + expired + ", stokMinimal=" + stokMinimal + ", tipe=" + tipe + "]";
	}

}
