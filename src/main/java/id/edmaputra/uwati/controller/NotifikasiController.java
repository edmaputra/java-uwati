package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.Table;

@Controller
@RequestMapping("/notifikasi")
public class NotifikasiController {

	private static final Logger logger = LoggerFactory.getLogger(NotifikasiController.class);

	private final int TOTAL_HALAMAN = 10;

	@Autowired
	private ObatService obatService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanHalaman(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("notifikasi");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarKategori(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "tabel") String tabel, @RequestParam(value = "navigasi") String navigasi,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();
			List<Object[]> daftar = null;
			String htmlTabel = "";
			int totalDaftar = 0;

			if (tabel.equals("#tabel_obat_sudah_kadaluarsa")) {
				totalDaftar = obatService.countObatSudahKadaluarsa();
				daftar = obatService.obatSudahKadaluarsa(limitPertama(halaman), limitKedua(halaman));
				htmlTabel = tabelGenerator(daftar, request, 0);
			} else if (tabel.equals("#tabel_obat_akan_kadaluarsa")) {
				totalDaftar = obatService.countObatAkanKadaluarsa();
				daftar = obatService.obatAkanKadaluarsa(limitPertama(halaman), limitKedua(halaman));
				htmlTabel = tabelGenerator(daftar, request, 1);
			} else if (tabel.equals("#tabel_obat_akan_habis")) {
				totalDaftar = obatService.countObatAkanHabis();
				daftar = obatService.obatAkanHabis(limitPertama(halaman), limitKedua(halaman));
				htmlTabel = tabelGenerator(daftar, request, 2);
			}
			
			el.setTabel(htmlTabel);

			if (!daftar.isEmpty()) {
				int total = (totalDaftar / TOTAL_HALAMAN) + 1; 
				int current = halaman;
				int next = current + 1;
				int prev = current - 1;
				int first = Math.max(1, current - 5);
				int last = Math.min(first + 10, total);

//				System.out.println(first + " " + prev + " " + current + " " + next + " " + last + " " + total);
				String h = navigasiHalamanGenerator(first, prev, current, next, last, total, tabel, navigasi);
				el.setNavigasiHalaman(h);
			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private String tabelGenerator(List<Object[]> list, HttpServletRequest request, int i) {
		String html = "";
		String thead = "";
		if (i == 0) {
			thead = "<thead><tr><th>Obat</th><th>Tanggal Kadaluarsa</th></tr></thead>";
		} else if (i == 1) {
			thead = "<thead><tr><th>Obat</th><th>Tanggal Kadaluarsa</th><th>Sisa Hari</th></tr></thead>";
		} else if (i == 2) {
			thead = "<thead><tr><th>Obat</th><th>Sisa Stok</th></tr></thead>";
		}
		String data = "";
		for (int k = 0; k < list.size(); k++) {
			String row = "";
			row += Html.td(Table.nullCell(list.get(k)[0].toString()));
			row += Html.td(Table.nullCell(list.get(k)[1].toString()));
			if (i == 1) {
				row += Html.td(Table.nullCell(list.get(k)[2].toString()));
			}
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}

	private String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage,
			String tabel, String navigasi) {
		String html = "";

		if (current == 1) {
			html += Html.li(Html.aJs("&lt;&lt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&lt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&lt;&lt;", null, "onClick",
					"refresh(" + first + ",\"" + tabel + "\",\"" + navigasi + "\")"), null, null, null);
			html += Html.li(
					Html.aJs("&lt;", null, "onClick", "refresh(" + first + ",\"" + tabel + "\",\"" + navigasi + "\")"),
					null, null, null);
		}

		for (int i = first; i <= last; i++) {
			if (i == current) {
				html += Html.li(
						Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + tabel + "\",\"" + navigasi + "\")"),
						"active", null, null);
			} else {
				html += Html.li(
						Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + tabel + "\",\"" + navigasi + "\")"),
						null, null, null);
			}
		}

		if (current == totalPage) {
			html += Html.li(Html.aJs("&gt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(
					Html.aJs("&gt;", null, "onClick", "refresh(" + next + ",\"" + tabel + "\",\"" + navigasi + "\")"),
					null, null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, "onClick",
					"refresh(" + last + ",\"" + tabel + "\",\"" + navigasi + "\")"), null, null, null);
		}
		String nav = Html.nav(Html.ul(html, "pagination"));
		return nav;
	}

	private int limitPertama(int halaman) {
		int h = 0;
		if (halaman > 1) {
			int a = halaman - 1;
			h = (a*TOTAL_HALAMAN);
		}
		return h;
	}

	private int limitKedua(int halaman) {
		int h = TOTAL_HALAMAN - 1;		
		return h;
	}
}
