package id.edmaputra.uwati.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.edmaputra.uwati.config.DBConf;
import id.edmaputra.uwati.view.Table;

@MappedSuperclass
public abstract class Bayar<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "waktu_bayar", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date waktuBayar;


}
