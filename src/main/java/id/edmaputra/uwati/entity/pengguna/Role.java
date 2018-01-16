package id.edmaputra.uwati.entity.pengguna;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import id.edmaputra.uwati.DBConf;
import id.edmaputra.uwati.entity.DasarEntity;

@Entity
@Table(name="role", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "nama") })
public class Role extends DasarEntity<Integer>{
	
	private static final long serialVersionUID = -6809398190016052683L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nama", nullable = false, length = DBConf.LENGTH_NAMA_ROLE)
	private String nama;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
//	@OneToMany(mappedBy="role", cascade=CascadeType.ALL, orphanRemoval=true)
//	private List<Menu> listMenu = new ArrayList<>();

}
