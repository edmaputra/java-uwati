package id.edmaputra.uwati.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class DasarTransaksiEntity<T> implements Serializable {
	
	private static final long serialVersionUID = -6271581964552598034L;

	@Column(name = "waktu_dibuat", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)    
	private Date waktuDibuat;

    @Column(name = "terakhir_dirubah", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date terakhirDirubah;

    @Lob
    @Column(name = "info")
    private String info;

    public abstract T getId();

    public abstract void setId(T id);

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getTerakhirDirubah() {
        return terakhirDirubah;
    }

    public void setTerakhirDirubah(Date terakhirDirubah) {
        this.terakhirDirubah = terakhirDirubah;
    }

    public Date getWaktuDibuat() {
        return waktuDibuat;
    }

    public void setWaktuDibuat(Date waktuDibuat) {
        this.waktuDibuat = waktuDibuat;
    }

	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        final DasarTransaksiEntity<T> other = (DasarTransaksiEntity<T>) obj;
        if (this.getId() != other.getId() && (this.getId() == null
                || !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

}
