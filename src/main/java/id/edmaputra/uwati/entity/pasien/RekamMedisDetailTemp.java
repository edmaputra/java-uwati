package id.edmaputra.uwati.entity.pasien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="rekam_medis_detail_temp", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RekamMedisDetailTemp extends DasarEntity<Long> {
	
	private static final long serialVersionUID = 2351726938294178806L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="id_obat", nullable=true, length=DBConf.LENGTH_TRANSAKSI_NOMORFAKTUR)
	private String idObat;
	
	@Column(name="nomor", nullable=true, length=DBConf.LENGTH_TRANSAKSI_NOMORFAKTUR)
	private String nomor;
	
	@Column(name="terapi", nullable=false, length=DBConf.LENGTH_NAMA)
	private String terapi;
	
	@Column(name="harga_jual", nullable=false, length=40)
	private String hargaJual;

	@Column(name="diskon", nullable=true, length=40)
	private String diskon;
	
	@Column(name="pajak", nullable=true, length=40)
	private String pajak;
		
	@Column(name="harga_total", nullable=false)
	private String hargaTotal;
	
	@Column(name="jumlah", nullable=false, length=40)
	private String jumlah;
	
	@Column(name="tipe", nullable=true)
	private int tipe;
	
	@Column(name="tipe_penggunaan", nullable=true)
	private int tipePenggunaan;
	
	@Column(name="is_sudah_diproses", nullable=true)
	private Boolean isSudahDiproses;
	
	@Column(name="randomId", nullable=true)
	private String randomId;
	
	@Column(name="dokter_id", nullable=true)
	private Integer dokterId;

	public Integer getDokterId() {
		return dokterId;
	}

	public void setDokterId(Integer dokterId) {
		this.dokterId = dokterId;
	}

	public String getRandomId() {
		return randomId;
	}

	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

	public Boolean getIsSudahDiproses() {
		return isSudahDiproses;
	}

	public void setIsSudahDiproses(Boolean isSudahDiproses) {
		this.isSudahDiproses = isSudahDiproses;
	}

	public int getTipePenggunaan() {
		return tipePenggunaan;
	}

	public void setTipePenggunaan(int tipePenggunaan) {
		this.tipePenggunaan = tipePenggunaan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	public String getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(String hargaJual) {
		this.hargaJual = hargaJual;
	}

	public String getDiskon() {
		return diskon;
	}

	public void setDiskon(String diskon) {
		this.diskon = diskon;
	}

	public String getPajak() {
		return pajak;
	}

	public void setPajak(String pajak) {
		this.pajak = pajak;
	}

	public String getHargaTotal() {
		return hargaTotal;
	}

	public void setHargaTotal(String hargaTotal) {
		this.hargaTotal = hargaTotal;
	}

	public String getTerapi() {
		return terapi;
	}

	public void setTerapi(String terapi) {
		this.terapi = terapi;
	}

	public String getIdObat() {
		return idObat;
	}

	public void setIdObat(String idObat) {
		this.idObat = idObat;
	}

	public int getTipe() {
		return tipe;
	}

	public void setTipe(int tipe) {
		this.tipe = tipe;
	}

}
