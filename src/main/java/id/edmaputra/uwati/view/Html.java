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

	public static String div(String content, String clazz) {
		String ul = "<div ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			ul += "class = \"" + clazz + "\" ";
		}
		ul += ">" + content + "</div>";
		return ul;
	}
	
	public static String snap(String content, String clazz) {
		String ul = "<snap ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			ul += "class = \"" + clazz + "\" ";
		}
		ul += ">" + content + "</snap>";
		return ul;
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

	public static String aJs(String content, String clazz, String evt, String eventName, String title) {
		String a = "<a href=\"javascript:;\" ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			a += "class = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(evt) || evt != null) {
			a += evt + " = '" + eventName + "' ";
		}
		if (!StringUtils.isEmpty(title) || title != null) {
			a += "title = '" + title + "' ";
		}
		a += ">" + content + "</a>";
		return a;
	}

	public static String aJs(String content, String clazz, String evt, String eventName, String title,
			String dataToggle, String dataTarget) {
		String a = "<a href=\"javascript:;\" ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			a += "class = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(evt) || evt != null) {
			a += evt + " = '" + eventName + "' ";
		}
		if (!StringUtils.isEmpty(title) || title != null) {
			a += "title = '" + title + "' ";
		}
		if (!StringUtils.isEmpty(dataToggle) || dataToggle != null) {
			a += "data-toggle = '" + dataToggle + "' ";
		}
		if (!StringUtils.isEmpty(dataTarget) || dataTarget != null) {
			a += "data-target = '" + dataTarget + "' ";
		}
		a += ">" + content + "</a>";
		return a;
	}

	public static String a(String content, String clazz, String evt, String eventName, String href, String dataToggle,
			String dataTarget) {
		String a = "<a ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			a += "class = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(evt) || evt != null) {
			a += evt + " = '" + eventName + "' ";
		}
		if (!StringUtils.isEmpty(href) || href != null) {
			a += "href = '" + href + "' ";
		}
		if (!StringUtils.isEmpty(dataToggle) || dataToggle != null) {
			a += "data-toggle = '" + dataToggle + "' ";
		}
		if (!StringUtils.isEmpty(dataTarget) || dataTarget != null) {
			a += "data-target = '" + dataTarget + "' ";
		}
		a += ">" + content + "</a>";
		return a;
	}

	public static String a(String content, String clazz, String evt, String eventName, String href, String dataToggle,
			String dataTarget, String title) {
		String a = "<a ";
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			a += "class = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(evt) || evt != null) {
			a += evt + " = '" + eventName + "' ";
		}
		if (!StringUtils.isEmpty(href) || href != null) {
			a += "href = '" + href + "' ";
		}
		if (!StringUtils.isEmpty(dataToggle) || dataToggle != null) {
			a += "data-toggle = '" + dataToggle + "' ";
		}
		if (!StringUtils.isEmpty(dataTarget) || dataTarget != null) {
			a += "data-target = '" + dataTarget + "' ";
		}
		if (!StringUtils.isEmpty(title) || title != null) {
			a += "title = '" + title + "' ";
		}
		a += ">" + content + "</a>";
		return a;
	}

	public static String button(String clazz, String dataToggle, String dataTarget, String evt, String eventName,
			int tipe) {
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

	public static String button(String clazz, String dataToggle, String dataTarget, String evt, String eventName,
			int tipe, String title) {
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
		if (!StringUtils.isEmpty(title) || title != null) {
			btn += "title = '" + title + "' ";
		}
		btn += ">";
		if (tipe == 0) {
			btn += "<i class='fa fa-pencil'></i>";
		} else if (tipe == 1) {
			btn += "<i class='fa fa-trash-o'></i>";
		} else if (tipe == 2) {
			btn += "<i class='fa fa-check-square-o'></i>";
		} else if (tipe == 3) {
			btn += "<i class='fa fa-cart-plus'></i>";
		} else if (tipe == 4) {
			btn += "<i class='fa fa-print'></i>";
		} else if (tipe == 5) {
			btn += "<i class='fa fa-file-o'></i>";
		} else if (tipe == 6) {
			btn += "<i class='fa fa-usd'></i>";
		} else if (tipe == 7) {
			btn += "<i class='fa fa-arrow-down'></i>";
		}
		btn += "</button>";
		return btn;
	}
	
	public static String button(String clazz, String dataToggle, String dataTarget, String evt, String eventName,
			int tipe, String title, String id, String type) {
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
			btn += evt + " = \"" + eventName + "\" ";
		}
		if (!StringUtils.isEmpty(title) || title != null) {
			btn += "title = '" + title + "' ";
		}
		if (!StringUtils.isEmpty(id) || id != null) {
			btn += "id = '" + id + "' ";
		}
		if (!StringUtils.isEmpty(type) || type != null) {
			btn += "type = '" + type + "' ";
		}
		btn += ">";
		if (tipe == 0) {
			btn += "<i class='fa fa-pencil'></i>";
		} else if (tipe == 1) {
			btn += "<i class='fa fa-trash-o'></i>";
		} else if (tipe == 2) {
			btn += "<i class='fa fa-check-square-o'></i>";
		} else if (tipe == 3) {
			btn += "<i class='fa fa-cart-plus'></i>";
		} else if (tipe == 4) {
			btn += "<i class='fa fa-print'></i>";
		} else if (tipe == 5) {
			btn += "<i class='fa fa-file-o'></i>";
		} else if (tipe == 6) {
			btn += "<i class='fa fa-usd'></i>";
		} else if (tipe == 7) {
			btn += "<i class='fa fa-arrow-down'></i>";
		}
		btn += "</button>";
		return btn;
	}
		
//	<input type="checkbox" disabled="disabled" checked="checked"/>
	public static String checkbox(String id, String clazz, boolean isChecked, boolean isReadonly, boolean isEnabled) {
		String chkbox = "<input type='checkbox'";
		if (!StringUtils.isEmpty(id) || id != null) {
			chkbox += "id = '" + clazz + "' ";
		}
		if (!StringUtils.isEmpty(clazz) || clazz != null) {
			chkbox += "class = '" + clazz + "' ";
		}
		if (isChecked) {
			chkbox += "checked = 'checked' ";
		}
		if (isReadonly) {
			chkbox += "readonly = 'readonly' ";
		}
		if (!isEnabled) {
			chkbox += "disabled = 'disabled' ";
		}
		chkbox += "/>";
		return chkbox;
	}
	
	public static String desc(int urutan, String namaObat){
		String desc = "";
		String thumb = snap("<h4>"+urutan+"</h4>", "badge bg-theme");
		String details = div("<h5>"+namaObat+"</h5>", "details");
		desc += div(thumb+details, "desc");
		return desc;
	}

	public static String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage,
			String cari) {
		String html = "";

		if (current == 1) {
			html += Html.li(Html.aJs("&lt;&lt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&lt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&lt;&lt;", null, "onClick", "refresh(" + first + ",\"" + cari + "\")"), null,
					null, null);
			html += Html.li(Html.aJs("&lt;", null, "onClick", "refresh(" + prev + ",\"" + cari + "\")"), null, null,
					null);
		}

		for (int i = first; i <= last; i++) {
			if (i == current) {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + cari + "\")"), "active",
						null, null);
			} else {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + cari + "\")"), null, null,
						null);
			}
		}

		if (current == totalPage) {
			html += Html.li(Html.aJs("&gt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&gt;", null, "onClick", "refresh(" + next + ",\"" + cari + "\")"), null, null,
					null);
			html += Html.li(Html.aJs("&gt;&gt;", null, "onClick", "refresh(" + last + ",\"" + cari + "\")"), null, null,
					null);
		}

		String nav = Html.nav(Html.ul(html, "pagination"));

		return nav;
	}

	public static String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage,
			int tipe, String tanggalAwal, String tanggalAkhir, String cari) {
		String html = "";

		if (current == 1) {
			html += Html.li(Html.aJs("&lt;&lt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&lt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&lt;&lt;", null, "onClick", "refresh(" + first + "," + tipe + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null,
					null, null);
			html += Html.li(Html.aJs("&lt;", null, "onClick", "refresh(" + prev + "," + tipe + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null, null,
					null);
		}

		for (int i = first; i <= last; i++) {
			if (i == current) {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + "," + tipe + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), "active",
						null, null);
			} else {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + "," + tipe + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null, null,
						null);
			}
		}

		if (current == totalPage) {
			html += Html.li(Html.aJs("&gt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&gt;", null, "onClick",
					"refresh(" + next + "," + tipe + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"),
					null, null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, "onClick", "refresh(" + last + "," + tipe + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null, null,
					null);
		}
		
		String nav = Html.nav(Html.ul(html, "pagination"));
		return nav;
	}
	
	public static String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage, String tanggalAwal, String tanggalAkhir, String cari) {
		String html = "";

		if (current == 1) {
			html += Html.li(Html.aJs("&lt;&lt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&lt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&lt;&lt;", null, "onClick", "refresh(" + first + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null,
					null, null);
			html += Html.li(Html.aJs("&lt;", null, "onClick", "refresh(" + prev + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null, null,
					null);
		}

		for (int i = first; i <= last; i++) {
			if (i == current) {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), "active",
						null, null);
			} else {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null, null,
						null);
			}
		}

		if (current == totalPage) {
			html += Html.li(Html.aJs("&gt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&gt;", null, "onClick",
					"refresh(" + next + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"),
					null, null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, "onClick", "refresh(" + last + ",\"" + tanggalAwal + "\",\"" + tanggalAkhir + "\",\"" + cari + "\")"), null, null,
					null);
		}
		
		String nav = Html.nav(Html.ul(html, "pagination"));
		return nav;
	}
}
