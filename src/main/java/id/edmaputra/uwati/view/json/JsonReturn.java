package id.edmaputra.uwati.view.json;

//@JsonPropertyOrder({"value","data"})
public class JsonReturn {

	private String value;
	private String data;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
