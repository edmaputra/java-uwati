package id.edmaputra.uwati.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.edmaputra.uwati.config.DBConf;

@MappedSuperclass
public abstract class DasarEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_input", nullable = true, length = DBConf.LENGTH_NAMA_PENGGUNA) 
	private String userInput;

	@Column(name = "user_editor", nullable = true, length = DBConf.LENGTH_NAMA_PENGGUNA)
	private String userEditor;

	@Column(name = "waktu_dibuat", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date waktuDibuat;

	@Column(name = "terakhir_dirubah", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date terakhirDirubah;

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public String getUserEditor() {
		return userEditor;
	}

	public void setUserEditor(String userEditor) {
		this.userEditor = userEditor;
	}

	public Date getWaktuDibuat() {
		return waktuDibuat;
	}

	public void setWaktuDibuat(Date waktuDibuat) {
		this.waktuDibuat = waktuDibuat;
	}

	public Date getTerakhirDirubah() {
		return terakhirDirubah;
	}

	public void setTerakhirDirubah(Date terakhirDirubah) {
		this.terakhirDirubah = terakhirDirubah;
	}

}
