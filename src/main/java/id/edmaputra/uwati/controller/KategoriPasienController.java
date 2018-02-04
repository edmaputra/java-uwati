package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Kategori;
import id.edmaputra.uwati.entity.pasien.KategoriPasien;
import id.edmaputra.uwati.service.KategoriService;
import id.edmaputra.uwati.service.pasien.KategoriPasienService;
import id.edmaputra.uwati.specification.KategoriPasienPredicateBuilder;
import id.edmaputra.uwati.specification.KategoriPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.json.JsonReturn;
import id.edmaputra.uwati.view.json.Suggestion;

@Controller
@RequestMapping("/kategori-pasien")
public class KategoriPasienController {

	private static final Logger logger = LoggerFactory.getLogger(KategoriPasienController.class);

	@Autowired
	private KategoriPasienService kategoriPasienService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanKategori(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("kategori-pasien-daftar");
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
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			KategoriPasienPredicateBuilder builder = new KategoriPasienPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<KategoriPasien> page = kategoriPasienService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()){
			int current = page.getNumber() + 1;
			int next = current + 1;
			int prev = current - 1;
			int first = Math.max(1, current - 5);
			int last = Math.min(first + 10, page.getTotalPages());

			String h = navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(), cari);
			el.setNavigasiHalaman(h);
			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public KategoriPasien dapatkanKategori(@RequestParam("id") String satuan) {
		try {
			KategoriPasien get = kategoriPasienService.dapatkan(Integer.valueOf(satuan));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/ada", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAda(@RequestParam("nama") String nama) {
		return kategoriPasienService.dapatkanByNama(nama) != null;
	}
	
	@RequestMapping(value = "/semua", method = RequestMethod.GET)
	@ResponseBody
	public List<KategoriPasien> dapatkanSemua(){
		try {
			List<KategoriPasien> l = kategoriPasienService.dapatkanSemua();
			return l;
		} catch (Exception e) {
			logger.info(e.getMessage());			
			return null;
		}
	}
	
	@RequestMapping(value = "/nama", method = RequestMethod.GET)
	@ResponseBody
	public Suggestion dapatkanKategoriByNama(@RequestParam("query") String nama) {
		try {
			KategoriPredicateBuilder builder = new KategoriPredicateBuilder();
			if (!StringUtils.isBlank(nama)) {
				builder.cari(nama);
			}

			BooleanExpression exp = builder.getExpression();
			List<KategoriPasien> l = kategoriPasienService.dapatkanListByNama(exp);
						
			List<JsonReturn> listReturn = new ArrayList<>();
			for(KategoriPasien k:l){
				JsonReturn jr = new JsonReturn();
				jr.setData(k.getNama());
				jr.setValue(k.getNama());
				listReturn.add(jr);
			}			
			Suggestion r = new Suggestion();
			r.setSuggestions(listReturn);
			return r;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public KategoriPasien tambahKategori(@RequestBody KategoriPasien kategori, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			kategori.setUserInput(principal.getName());
			kategori.setWaktuDibuat(new Date());
			kategori.setTerakhirDirubah(new Date());
			kategoriPasienService.simpan(kategori);
			logger.info(LogSupport.tambah(principal.getName(), kategori.toString(), request));
			return kategori;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), kategori.toString(), request));
			return null;
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public KategoriPasien editKategori(@RequestBody KategoriPasien kategori, BindingResult result, Principal principal,
			HttpServletRequest request) {
		KategoriPasien edit = kategoriPasienService.dapatkan(kategori.getId());
		String entity = edit.toString();
		try {
			edit.setNama(kategori.getNama());
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			kategoriPasienService.simpan(edit);
			logger.info(LogSupport.edit(principal.getName(), entity, request));
			return edit;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(principal.getName(), entity, request));
			return null;
		}
	}

	@RequestMapping(value = "/hapus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String hapusKategori(@RequestBody KategoriPasien kategori, BindingResult result, Principal principal,
			HttpServletRequest request) {
		KategoriPasien hapus = kategoriPasienService.dapatkan(kategori.getId());
		String entity = hapus.toString();
		try {
			kategoriPasienService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return "HAPUS OK";
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	private String tabelGenerator(Page<KategoriPasien> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr><th>Kategori</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (KategoriPasien k : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(k.getNama());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(k.getUserInput());
				row += Html.td(Formatter.formatTanggal(k.getWaktuDibuat()));
				row += Html.td(k.getUserEditor());
				row += Html.td(Formatter.formatTanggal(k.getTerakhirDirubah()));
				btn= Html.button("btn btn-primary btn-xs btnEdit", "modal", "#kategori-modal-edit", "onClick",
						"getData(" + k.getId() + ")", 0);
				btn += Html.button("btn btn-danger btn-xs", "modal", "#kategori-modal-hapus", "onClick",
						"setIdUntukHapus(" + k.getId() + ")", 1);
			}
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}

	private String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage,
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

}
