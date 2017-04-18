package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.util.Date;

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

import id.edmaputra.uwati.entity.master.Dokter;
import id.edmaputra.uwati.service.DokterService;
import id.edmaputra.uwati.specification.DokterPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;

@Controller
@RequestMapping("/dokter")
public class DokterController {

	private static final Logger logger = LoggerFactory.getLogger(DokterController.class);

	@Autowired
	private DokterService dokterService;

	@ModelAttribute("dokter")
	public Dokter constructDokter() {
		return new Dokter();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanDokter(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("dokter-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarDokter(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			DokterPredicateBuilder builder = new DokterPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Dokter> page = dokterService.muatDaftar(halaman, exp);

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
	public Dokter dapatkanDokter(@RequestParam("id") String dokter) {
		try {
			Dokter get = dokterService.dapatkan(Integer.valueOf(dokter));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Dokter tambahDokter(@RequestBody Dokter dokter, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			dokter.setUserInput(principal.getName());
			dokter.setWaktuDibuat(new Date());
			dokter.setTerakhirDirubah(new Date());
			dokterService.simpan(dokter);
			logger.info(LogSupport.tambah(principal.getName(), dokter.toString(), request));
			return dokter;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), dokter.toString(), request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Dokter editDokter(@RequestBody Dokter dokter, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Dokter edit = dokterService.dapatkan(dokter.getId());
		String entity = edit.toString();
		try {
			edit.setNama(dokter.getNama());
			edit.setSpesialis(dokter.getSpesialis());
			edit.setSip(dokter.getSip());
			edit.setAlamat(dokter.getAlamat());
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			dokterService.simpan(edit);
			logger.info(LogSupport.edit(principal.getName(), entity, request));
			return edit;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(principal.getName(), entity, request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/hapus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String hapusDokter(@RequestBody Dokter dokter, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Dokter hapus = dokterService.dapatkan(dokter.getId());
		String entity = hapus.toString();
		try {
			dokterService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return "HAPUS OK";
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	private String tabelGenerator(Page<Dokter> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>"
				+ "<th>Id</th>"
				+ "<th>Dokter</th>"
				+ "<th>Spesialis</th>"
				+ "<th>SIP</th>"
				+ "<th>Alamat</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" 
					+ "<th>Waktu Dibuat</th>" 
					+ "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Dokter d : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(d.getId().toString());
			row += Html.td(d.getNama());
			row += Html.td(d.getSpesialis());
			row += Html.td(d.getSip());
			row += Html.td(d.getAlamat());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(d.getUserInput());
				row += Html.td(Formatter.formatTanggal(d.getWaktuDibuat()));
				row += Html.td(d.getUserEditor());
				row += Html.td(Formatter.formatTanggal(d.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#dokter-modal-edit", "onClick",
						"getData(" + d.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#dokter-modal-hapus", "onClick",
						"setIdUntukHapus(" + d.getId() + ")", 1);
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
