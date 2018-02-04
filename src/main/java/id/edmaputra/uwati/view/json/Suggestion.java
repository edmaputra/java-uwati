package id.edmaputra.uwati.view.json;

import java.util.List;

//@JsonPropertyOrder({"suggestions"})
public class Suggestion {
	
	private List<JsonReturn> suggestions;

	public List<JsonReturn> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<JsonReturn> suggestions) {
		this.suggestions = suggestions;
	}
}
