package id.edmaputra.uwati.controller;

import java.security.Principal;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pasien.Pasien;
import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetail;
import id.edmaputra.uwati.service.pasien.KategoriPasienService;
import id.edmaputra.uwati.service.pasien.PasienService;
import id.edmaputra.uwati.service.pasien.RekamMedisDetailService;
import id.edmaputra.uwati.service.pasien.RekamMedisService;
import id.edmaputra.uwati.specification.PasienPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.validator.impl.PasienValidator;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.Table;
import id.edmaputra.uwati.view.handler.PasienHandler;

@Controller
@RequestMapping("/pasien")
public class PasienController {

	private static final Logger logger = LoggerFactory.getLogger(PasienController.class);

	@Autowired
	private PasienService pasienService;

	@Autowired
	private RekamMedisService rekamMedisService;

	@Autowired
	private PasienValidator pasienValidator;

	@Autowired
	private RekamMedisDetailService rekamMedisDetailService;

	@Autowired
	private KategoriPasienService kategoriPasienService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPage(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("pasien-daftar");
			mav.addObject("kategoris", kategoriPasienService.dapatkanSemua());
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
			@RequestParam(value = "k", defaultValue = "-1", required = false) Integer kategori,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "i", defaultValue = "", required = false) String id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			PasienPredicateBuilder builder = new PasienPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				// if (StringUtils.isNumeric(cari)){
				// System.out.println("Numeric "+cari);
				// builder.kontak(cari);
				// builder.nomorJaminanKesehatan(cari);
				// builder.identitas(cari);
				// builder.id(new Long(cari));
				// } else {
				// System.out.println("No Numeric");
				builder.cari(cari);
				// }
			}

			if (!StringUtils.isBlank(id)) {
				builder.id(new Long(id));
			}

			if (kategori != -1) {
				builder.kategori(kategori);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Pasien> page = pasienService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()) {
				int current = page.getNumber() + 1;
				int next = current + 1;
				int prev = current - 1;
				int first = Math.max(1, current - 5);
				int last = Math.min(first + 10, page.getTotalPages());

				String h = navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(), cari, kategori.toString(), id);
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
	public Pasien dapatkanEntity(@RequestParam("id") String pasien) {
		try {
			Pasien get = getPasien(Long.valueOf(pasien));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/tersedia", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAda(@RequestParam("identitas") String identitas) {
		return pasienService.dapatkanByIdentitas(identitas) == null;
	}

	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Pasien tambah(@RequestBody PasienHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pasien pasien = null;
		try {
			pasien = new Pasien();
			pasien = setPasien(pasien, h);

			pasien.setUserInput(principal.getName());
			pasien.setWaktuDibuat(new Date());
			pasien.setTerakhirDirubah(new Date());

			pasien.setId(idGenerator(pasienService.dapatkanSemua()));

			pasienValidator.validate(pasien);
			pasienService.simpan(pasien);
			logger.info(LogSupport.tambah(principal.getName(), pasien.toString(), request));
			pasien.setInfo("Pasien " + pasien.getNama() + " Berhasil Ditambah");
			return pasien;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getNama(), request));
			pasien.setInfo(e.getMessage());
			return pasien;
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Pasien edit(@RequestBody PasienHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pasien edit = getPasien(h.getId());
		String entity = edit.toString();
		try {
			edit = setPasien(edit, h);

			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());

			pasienValidator.validate(edit);
			pasienService.simpan(edit);
			logger.info(LogSupport.edit(principal.getName(), entity, request));
			edit.setInfo("Pasien " + edit.getNama() + " Berhasil Diubah");
			return edit;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(principal.getName(), entity, request));
			edit.setInfo(e.getMessage());
			return edit;
		}
	}

	@RequestMapping(value = "/hapus", method = RequestMethod.POST)
	@ResponseBody
	public Pasien hapus(@RequestBody PasienHandler p, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pasien hapus = getPasien(p.getId());
		String entity = hapus.toString();
		try {
			pasienService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			hapus.setInfo("Hapus Berhasil");
			return hapus;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	@RequestMapping(value = "/{id}/rekam-medis", method = RequestMethod.GET)
	@ResponseBody
	public Pasien rekamMedis(@PathVariable("id") PasienHandler p, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pasien pasien = getPasien(p.getId());
		return pasien;
	}

	private String tabelGenerator(Page<Pasien> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>" + "<th>Pasien</th>" + "<th>ID</th>" + "<th>Usia</th>" + "<th>Jaminan</th>"
				+ "<th>Nomor</th>" + "<th>Kategori</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th>Rekam Medis</th>";
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Pasien t : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(t.getNama());
			row += Html.td(t.getId().toString());
			row += Html.td(Formatter.hitungUsia(t.getTanggalLahir(), new Date()));
			row += Html.td(Table.nullCell(t.getJaminanKesehatan()));
			row += Html.td(Table.nullCell(t.getNomorJaminanKesehatan()));
			row += Html.td(Table.nullCell(t.getKategoriPasien().getNama()));
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(t.getUserInput());
				row += Html.td(Formatter.formatTanggal(t.getWaktuDibuat()));
				row += Html.td(t.getUserEditor());
				row += Html.td(Formatter.formatTanggal(t.getTerakhirDirubah()));
			}
			if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_MEDIS")) {
				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#pasien-modal", "onClick",
						"getData(" + t.getId() + ")", 0, "Edit Data");

				btn += Html.button("btn btn-danger btn-xs", "modal", "#pasien-modal-hapus", "onClick",
						"setIdUntukHapus(" + t.getId() + ")", 1, "Hapus Data");
			}
			row += Html.td(Html.a("<i class='fa fa-book'></i>", ", btn btn-primary btn-xs", null, null,
					"/uwati/rekam-medis/" + t.getId(), null, null));
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}

	private String tabelRekamMedisGenerator(Page<RekamMedis> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>" + "<th>Id</th>" + "<th>Nomor</th>" + "<th>Tanggal</th>" + "<th>Anamnesa</th>"
				+ "<th>Pemeriksaan</th>" + "<th>Diagnosa</th>" + "<th>Dokter</th>";
		thead += "<th></th></tr></thead>";
		String data = "";
		for (RekamMedis t : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(t.getId().toString());
			row += Html.td(t.getNomor());
			row += Html.td(Converter.dateToStringIndonesianLocale(t.getTanggal()));
			row += Html.td(t.getAnamnesa());
			row += Html.td(t.getPemeriksaan());
			row += Html.td(t.getDiagnosa());
			row += Html.td(t.getDokter().getNama());
			if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_MEDIS")) {

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#pasien-modal", "onClick",
						"getData(" + t.getId() + ")", 0, "Edit Data");

				btn += Html.button("btn btn-danger btn-xs", "modal", "#pasien-modal-hapus", "onClick",
						"setIdUntukHapus(" + t.getId() + ")", 1, "Hapus Data");
			}
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}

	private String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage,
			String cari, String k, String id) {
		String html = "";

		if (current == 1) {
			html += Html.li(Html.aJs("&lt;&lt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&lt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(
					Html.aJs("&lt;&lt;", null, "onClick", "refresh(" + first + ",\"" + cari + "\",\"" + k + "\",\"" + id + ")"),
					null, null, null);
			html += Html.li(Html.aJs("&lt;", null, "onClick", "refresh(" + prev + ",\"" + cari + "\",\"" + k + "\",\"" + id + ")"),
					null, null, null);
		}

		for (int i = first; i <= last; i++) {
			if (i == current) {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + cari + "\",\"" + k + "\",\"" + id + ")"),
						"active", null, null);
			} else {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + cari + "\",\"" + k + "\",\"" + id + ")"),
						null, null, null);
			}
		}

		if (current == totalPage) {
			html += Html.li(Html.aJs("&gt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&gt;", null, "onClick",
					"refresh(" + next + ",\"" + cari + "\",\"" + k + "\",\"" + id + ")"), null, null, null);
			html += Html.li(
					Html.aJs("&gt;&gt;", null, "onClick", "refresh(" + last + ",\"" + cari + "\",\"" + k + "\",\"" + id + ")"), null,
					null, null);
		}

		String nav = Html.nav(Html.ul(html, "pagination"));

		return nav;
	}

	private Pasien setPasien(Pasien p, PasienHandler h) {
		p.setAgama(h.getAgama());
		p.setAlamat(h.getAlamat());
		p.setIdentitas(h.getIdentitas());
		p.setJaminanKesehatan(h.getJaminanKesehatan());
		p.setJenisKelamin(h.getJenisKelamin());
		p.setKontak(h.getKontak());
		p.setNama(h.getNama());
		p.setNomorJaminanKesehatan(h.getNomorJaminan());
		p.setPekerjaan(h.getPekerjaan());
		p.setTanggalLahir(Converter.stringToDate(h.getTanggalLahir()));
		p.setKategoriPasien(kategoriPasienService.dapatkan(h.getKategori()));
		return p;
	}

	private Pasien getPasien(Long id) {
		Pasien get = pasienService.dapatkan(id);

		List<RekamMedis> rekamMedis = rekamMedisService.temukanByPasien(get);
		get.setRekamMedis(rekamMedis);
		Hibernate.initialize(get.getRekamMedis());

		return get;
	}

	private RekamMedis getRekamMedis(Long id) {
		RekamMedis get = rekamMedisService.dapatkan(id);

		List<RekamMedisDetail> rekamMedisDetails = rekamMedisDetailService.dapatkan(get);
		get.setRekamMedisDetail(rekamMedisDetails);
		Hibernate.initialize(get.getRekamMedisDetail());

		return get;
	}

	private Long idGenerator(List<Pasien> pasiens) {
		Long id = new Long(0);
		for (Pasien p : pasiens) {
			if (p.getId() > id) {
				id = p.getId();
			}
		}
		return id + 1;
	}

}
