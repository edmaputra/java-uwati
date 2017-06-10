package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Tindakan;
import id.edmaputra.uwati.service.obat.TindakanService;
import id.edmaputra.uwati.specification.TindakanPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;

@Controller
@RequestMapping("/tindakan")
public class TindakanController {

	private static final Logger logger = LoggerFactory.getLogger(TindakanController.class);

	@Autowired
	private TindakanService tindakanService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPage(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("tindakan-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.loadGagal(principal.getName(), request));
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement tampilkanDaftar(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			TindakanPredicateBuilder builder = new TindakanPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Tindakan> page = tindakanService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()) {
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
	public Tindakan dapatkanEntity(@RequestParam("id") String tindakan) {
		try {
			Tindakan get = tindakanService.dapatkan(Long.valueOf(tindakan));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/ada", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAda(@RequestParam("nama") String nama) {
		return tindakanService.dapatkanByNama(nama) != null;
	}

	@Transactional
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Tindakan tambah(@RequestBody Tindakan tindakan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			String tarif = String.valueOf(tindakan.getTarif()).replaceAll("[.]", "");
			tindakan.setTarif(new BigDecimal(tarif));
			tindakan.setUserInput(principal.getName());
			tindakan.setWaktuDibuat(new Date());
			tindakan.setTerakhirDirubah(new Date());		
			tindakanService.simpan(tindakan);
			logger.info(LogSupport.tambah(principal.getName(), tindakan.toString(), request));
			return tindakan;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), tindakan.getNama(), request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Tindakan edit(@RequestBody Tindakan tindakan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Tindakan edit = tindakanService.dapatkan(tindakan.getId());
		String entity = edit.toString();
		try {
			edit.setNama(tindakan.getNama());
			edit.setKode(tindakan.getKode());
			String tarif = String.valueOf(tindakan.getTarif()).replaceAll("[.]", "");
			edit.setTarif(new BigDecimal(tarif));
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			edit.setInfo(tindakan.getInfo());
			tindakanService.simpan(edit);
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
	public Tindakan hapus(@RequestBody Tindakan tindakan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Tindakan hapus = tindakanService.dapatkan(tindakan.getId());
		String entity = hapus.toString();
		try {
			tindakanService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			hapus.setInfo("Hapus Berhasil");
			return hapus;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	private String tabelGenerator(Page<Tindakan> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>"
				+ "<th>Id</th>"
				+ "<th>Tindakan</th>"
				+ "<th>Kode</th>"
				+ "<th>Tarif</th>"
				+ "<th>Info</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Tindakan t : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(t.getId().toString());
			row += Html.td(t.getNama());
			row += Html.td(t.getKode());
			row += Html.td(Formatter.patternCurrency(t.getTarif()));
			row += Html.td(t.getInfo());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(t.getUserInput());
				row += Html.td(Formatter.formatTanggal(t.getWaktuDibuat()));
				row += Html.td(t.getUserEditor());
				row += Html.td(Formatter.formatTanggal(t.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#tindakan-modal", "onClick",
						"getData(" + t.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#tindakan-modal-hapus", "onClick",
						"setIdUntukHapus(" + t.getId() + ")", 1);
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
