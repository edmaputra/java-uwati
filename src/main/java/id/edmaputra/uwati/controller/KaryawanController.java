package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
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

import id.edmaputra.uwati.entity.master.Karyawan;
import id.edmaputra.uwati.entity.pengguna.Pengguna;
import id.edmaputra.uwati.service.KaryawanService;
import id.edmaputra.uwati.service.pengguna.PenggunaService;
import id.edmaputra.uwati.specification.KaryawanPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.json.JsonReturn;
import id.edmaputra.uwati.view.json.Suggestion;

@Controller
@RequestMapping("/karyawan")
public class KaryawanController {

	private static final Logger logger = LoggerFactory.getLogger(KaryawanController.class);

	@Autowired
	private KaryawanService karyawanService;
	
	@Autowired
	private PenggunaService penggunaService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanHalamanListKaryawan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("karyawan-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement tampilkanListKaryawan(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			KaryawanPredicateBuilder builder = new KaryawanPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Karyawan> page = karyawanService.muatDaftar(halaman, exp);

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
	public Karyawan dapatkanKaryawan(@RequestParam("id") String karyawan) {
		try {
			Karyawan get = getKaryawan(karyawan);
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/ada", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAda(@RequestParam("nama") String nama) {
		return karyawanService.dapatkan(nama) != null;
	}
	
	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	@ResponseBody
	public Suggestion dapatkanKaryawanByNama(@RequestParam("query") String nama) {
		try {
			KaryawanPredicateBuilder builder = new KaryawanPredicateBuilder();
			if (!StringUtils.isBlank(nama)) {
				builder.nama(nama);
			}

			BooleanExpression exp = builder.getExpression();
			List<Karyawan> l = karyawanService.dapatkanListByNama(exp);

			List<JsonReturn> listReturn = new ArrayList<>();
			for (Karyawan k : l) {
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
	public Karyawan tambahKaryawan(@RequestBody Karyawan karyawan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			karyawan.setJabatan(karyawan.getJabatan().toUpperCase());
			karyawan.setUserInput(principal.getName());
			karyawan.setWaktuDibuat(new Date());
			karyawan.setTerakhirDirubah(new Date());
			karyawanService.simpan(karyawan);
			logger.info(LogSupport.tambah(principal.getName(), karyawan.toString(), request));
			return karyawan;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), karyawan.toString(), request));
			return null;
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Karyawan editKaryawan(@RequestBody Karyawan karyawan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Karyawan edit = getKaryawan(karyawan.getId());
		String entity = edit.toString();
		try {
			edit.setNama(karyawan.getNama());
			edit.setSpesialis(karyawan.getSpesialis());
			edit.setSip(karyawan.getSip());
			edit.setAlamat(karyawan.getAlamat());
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			edit.setJabatan(karyawan.getJabatan().toUpperCase());
			karyawanService.simpan(edit);
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
	public String hapusKaryawan(@RequestBody Karyawan karyawan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Karyawan hapus = karyawanService.dapatkan(karyawan.getId());
		String entity = hapus.toString();
		try {
			karyawanService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return "HAPUS OK";
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}
	
	private Karyawan getKaryawan(String id){
		Karyawan get = karyawanService.dapatkan(Integer.valueOf(id));

		Pengguna listPengguna = penggunaService.temukanByKaryawan(get);
		get.setPengguna(listPengguna);
		Hibernate.initialize(get.getPengguna());

		return get;
	}
	
	private Karyawan getKaryawan(Integer id){
		Karyawan get = karyawanService.dapatkan(id);

		Pengguna listPengguna = penggunaService.temukanByKaryawan(get);
		get.setPengguna(listPengguna);
		Hibernate.initialize(get.getPengguna());

		return get;
	}

	private String tabelGenerator(Page<Karyawan> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>"
				+ "<th>Nama</th>"
				+ "<th>Jabatan</th>"
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
		for (Karyawan d : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(d.getNama());
			row += Html.td(d.getJabatan());
			row += Html.td(d.getSpesialis());
			row += Html.td(d.getSip());
			row += Html.td(d.getAlamat());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(d.getUserInput());
				row += Html.td(Formatter.formatTanggal(d.getWaktuDibuat()));
				row += Html.td(d.getUserEditor());
				row += Html.td(Formatter.formatTanggal(d.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#dokter-modal", "onClick",
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
