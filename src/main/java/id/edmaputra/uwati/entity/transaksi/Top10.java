package id.edmaputra.uwati.entity.transaksi;

public class Top10 {
	
	private Long id;
	
	private String obat;

	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getObat() {
		return obat;
	}

	public void setObat(String obatTop10) {
		this.obat = obatTop10;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
