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

import id.edmaputra.uwati.entity.master.Pelanggan;
import id.edmaputra.uwati.service.PelangganService;
import id.edmaputra.uwati.specification.PelangganPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;

@Controller
@RequestMapping("/pelanggan")
public class PelangganController {

	private static final Logger logger = LoggerFactory.getLogger(PelangganController.class);

	@Autowired
	private PelangganService pelangganService;

	@ModelAttribute("pelanggan")
	public Pelanggan constructPelanggan() {
		return new Pelanggan();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPelanggan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("pelanggan-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarPelanggan(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			PelangganPredicateBuilder builder = new PelangganPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Pelanggan> page = pelangganService.muatDaftar(halaman, exp);

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
	public Pelanggan dapatkanPelanggan(@RequestParam("id") String dokter) {
		try {
			Pelanggan get = pelangganService.dapatkan(Integer.valueOf(dokter));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Pelanggan tambahPelanggan(@RequestBody Pelanggan pelanggan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			pelanggan.setUserInput(principal.getName());
			pelanggan.setWaktuDibuat(new Date());
			pelanggan.setTerakhirDirubah(new Date());
			pelangganService.simpan(pelanggan);
			logger.info(LogSupport.tambah(principal.getName(), pelanggan.toString(), request));
			return pelanggan;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pelanggan.toString(), request));
			return null;
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Pelanggan editPelanggan(@RequestBody Pelanggan pelanggan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pelanggan edit = pelangganService.dapatkan(pelanggan.getId());
		String entity = edit.toString();
		try {
			edit.setNama(pelanggan.getNama());
			edit.setKontak(pelanggan.getKontak());
			edit.setKode(pelanggan.getKode());
			edit.setAlamat(pelanggan.getAlamat());
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			pelangganService.simpan(edit);
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
	public String hapusPelanggan(@RequestBody Pelanggan pelanggan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pelanggan hapus = pelangganService.dapatkan(pelanggan.getId());
		String entity = hapus.toString();
		try {
			pelangganService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return "HAPUS OK";
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	private String tabelGenerator(Page<Pelanggan> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>"
				+ "<th>Kode</th>"
				+ "<th>Pelanggan</th>"
				+ "<th>Alamat</th>"
				+ "<th>Kontak</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" 
					+ "<th>Waktu Dibuat</th>" 
					+ "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Pelanggan p : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(p.getKode());
			row += Html.td(p.getNama());
			row += Html.td(p.getAlamat());
			row += Html.td(p.getKontak());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(p.getUserInput());
				row += Html.td(Formatter.formatTanggal(p.getWaktuDibuat()));
				row += Html.td(p.getUserEditor());
				row += Html.td(Formatter.formatTanggal(p.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#pelanggan-modal-edit", "onClick",
						"getData(" + p.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#pelanggan-modal-hapus", "onClick",
						"setIdUntukHapus(" + p.getId() + ")", 1);
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
