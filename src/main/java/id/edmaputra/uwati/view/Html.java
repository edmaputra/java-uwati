package id.edmaputra.uwati.view;

import org.apache.commons.lang3.StringUtils;

public class Html {
	
	public static String tbody(String content) {
		return "<tbody>" + content + "</tbody>";
	}

	public static String tr(String content) {
		return "<tr>" + content + "</tr>";
	}

	public static String td(String content) {
		return "<td>" + content + "</td>";
	}
	
	public static String nav(String content) {
		return "<nav>" + content + "</nav>";
	}
	
	public static String ul(String content, String clazz) {
		String ul = "<ul ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			ul += "class = \"" + clazz + "\" ";
		} 
		ul += ">" + content + "</ul>";
		return ul;
	}
	
	public static String li(String content, String clazz, String evt, String eventName) {
		String li = "<li ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			li += "class = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(evt) || evt != null) {
			li += evt + " = '" + eventName + "' ";
		}
		li += ">" + content + "</li>";
		return li;
	}
	
	public static String aJs(String content, String clazz, String evt, String eventName) {
		String a = "<a href=\"javascript:;\" ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			a += "class = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(evt) || evt != null) {
			a += evt + " = '" + eventName + "' ";
		}
		a += ">" + content + "</a>";
		return a;
	}

	public static String button(String clazz, String dataToggle, String dataTarget, String evt, String eventName, int tipe) {
		String btn = "<button ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			btn += "class = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(dataToggle) || dataToggle != null) {
			btn += "data-toggle = '" + dataToggle + "' ";
		}
		if (!StringUtils.isEmpty(dataTarget) || dataTarget != null) {
			btn += "data-target = '" + dataTarget + "' ";
		}
		if (!StringUtils.isEmpty(evt) || evt != null) {
			btn += evt + " = '" + eventName + "' ";
		}
		btn += ">";
		if (tipe == 0) {
			btn += "<i class='fa fa-pencil'></i>";
		} else if (tipe == 1) {
			btn += "<i class='fa fa-trash-o'></i>";
		} else if (tipe == 2) {
			btn += "<i class='fa fa-check-square-o'></i>";
		}
		btn += "</button>";
		return btn;
	}

}
