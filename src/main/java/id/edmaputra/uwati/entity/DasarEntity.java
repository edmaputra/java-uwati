package id.edmaputra.uwati.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.view.Table;

@MappedSuperclass
public abstract class DasarEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "creator", nullable = true, length = DBConf.LENGTH_NAMA_PENGGUNA) 
	private String userInput;

	@Column(name = "editor", nullable = true, length = DBConf.LENGTH_NAMA_PENGGUNA)
	private String userEditor;

	@Column(name = "waktu_dibuat", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date waktuDibuat;

	@Column(name = "terakhir_dirubah", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date terakhirDirubah;
	
	@Column(name = "info", nullable = true, length = DBConf.LENGTH_NAMA)
	private String info;

	public String getUserInput() {		
		return Table.nullCell(userInput);
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public String getUserEditor() {
		return Table.nullCell(userEditor);
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

	public String getInfo() {
		return Table.nullCell(info);
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
