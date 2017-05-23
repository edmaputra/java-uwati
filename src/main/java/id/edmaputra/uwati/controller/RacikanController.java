package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;
import id.edmaputra.uwati.entity.master.obat.RacikanDetailTemp;
import id.edmaputra.uwati.entity.master.obat.RacikanTemp;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailTemp;
import id.edmaputra.uwati.service.KategoriService;
import id.edmaputra.uwati.service.ObatDetailService;
import id.edmaputra.uwati.service.ObatExpiredService;
import id.edmaputra.uwati.service.ObatService;
import id.edmaputra.uwati.service.ObatStokService;
import id.edmaputra.uwati.service.RacikanDetailService;
import id.edmaputra.uwati.service.RacikanService;
import id.edmaputra.uwati.service.SatuanService;
import id.edmaputra.uwati.specification.RacikanPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;

@Controller
@RequestMapping("/racikan")
public class RacikanController {

	private static final Logger logger = LoggerFactory.getLogger(RacikanController.class);

	@Autowired
	private RacikanService racikanService;
	
	@Autowired
	private RacikanDetailService racikanDetailService;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private KategoriService kategoriService;

	@Autowired
	private SatuanService satuanService;

	@ModelAttribute("racikan")
	public Racikan constructRacikan() {
		return new Racikan();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanSatuan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("racikan-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.loadGagal(principal.getName(), request));
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarSatuan(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			RacikanPredicateBuilder builder = new RacikanPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Racikan> page = racikanService.muatDaftar(halaman, exp);

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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanRacikanUntukDijual(
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			RacikanPredicateBuilder builder = new RacikanPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Racikan> page = racikanService.muatDaftar(halaman, exp, 6);

			String tabel = tabelRacikanPenjualan(page, request);
			el.setTabel(tabel);

			if (page.hasContent()) {
				int current = page.getNumber() + 1;
				int next = current + 1;
				int prev = current - 1;
				int first = Math.max(1, current - 5);
				int last = Math.min(first + 10, page.getTotalPages());

				String h = navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(), cari, "refreshRacikan");
				el.setNavigasiHalaman(h);
			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanRacikan(@RequestParam("id") String idx) {
		try {
			HtmlElement el = new HtmlElement();
			Racikan r = getRacikan(Long.valueOf(idx).longValue());
			String tabel = tabelRacikanDetailPenjualan(r.getRacikanDetail());
			el.setTabel(tabel);
			el.setGrandTotal(r.getHargaJual() + "");
			el.setValue1(r.getNama());
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public RacikanTemp tambahRacikan(@RequestBody RacikanTemp r, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			Obat obat = new Obat();
			obat = setObatContent(obat, r, principal);

			Racikan racikan = new Racikan();
			racikan = setRacikanContent(racikan, r, principal);

			BigDecimal harga = new BigDecimal(0);

			List<RacikanDetail> l = new ArrayList<>();
			for (RacikanDetailTemp rdt : r.getKomposisi()) {
				RacikanDetail rd = new RacikanDetail();
				rd = setRacikanDetailContent(rd, rdt, racikan, principal);
				l.add(rd);
				harga = harga.add(hitungSubTotal(rd.getHargaSatuan(), rd.getJumlah()));
			}
			racikan.setHargaJual(harga);
			racikan.setRacikanDetail(l);

			obatService.simpan(obat);
			racikanService.simpan(racikan);

			logger.info(LogSupport.tambah(principal.getName(), r.toString(), request));
			return r;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), r.toString(), request));
			return null;
		}
	}

	@RequestMapping(value = "/hapus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String hapusRacikan(@RequestBody RacikanTemp r, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Racikan hapus = racikanService.dapatkan(r.getId());
		String entity = hapus.toString();
		try {
			racikanService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return "HAPUS OK";
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	private String tabelGenerator(Page<Racikan> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr><th>Id</th><th>Nama</th>";
		thead += "<th>Harga Racikan</th>";
		thead += "<th>Biaya Racik</th>";
		if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_APOTEK")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Racikan s : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(s.getId().toString());
			row += Html.td(s.getNama());
			row += Html.td(s.getHargaJual() + "");
			row += Html.td(s.getBiayaRacik() + "");
			if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_APOTEK")) {
				row += Html.td(s.getUserInput());
				row += Html.td(Formatter.formatTanggal(s.getWaktuDibuat()));
				row += Html.td(s.getUserEditor());
				row += Html.td(Formatter.formatTanggal(s.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#satuan-modal-edit", "onClick",
						"getData(" + s.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#satuan-modal-hapus", "onClick",
						"setIdUntukHapus(" + s.getId() + ")", 1);
			}
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}

	private String tabelRacikanPenjualan(Page<Racikan> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr><th>Id</th><th>Racikan</th>";
		thead += "<th>Harga Racikan</th>";
		thead += "<th>Biaya Racik</th>";
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Racikan s : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(s.getId().toString());
			row += Html.td(s.getNama());
			row += Html.td(s.getHargaJual() + "");
			row += Html.td(s.getBiayaRacik() + "");
			btn = Html.button("btn btn-primary btn-xs btnEdit", null, null, "onClick",
					"getDataRacikanPenjualan(" + s.getId() + ")", 2);
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}
	
	private String tabelRacikanDetailPenjualan(List<RacikanDetail> list) {
		String html = "";
		String thead = "<thead><tr><th>Obat</th>";
		thead += "<th>Jumlah</th>";
		thead += "<th>Harga</th>";
		thead += "<th></th></tr></thead>";
		String data = "";
		for (RacikanDetail s : list) {
			String row = "";
			row += Html.td(s.getKomposisi().getNama());
			row += Html.td(s.getJumlah() + "");
			row += Html.td(s.getHargaSatuan() + "");						
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
	
	private String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage,
			String cari, String method) {
		String html = "";

		if (current == 1) {
			html += Html.li(Html.aJs("&lt;&lt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&lt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&lt;&lt;", null, "onClick", method + "(" + first + ",\"" + cari + "\")"), null,
					null, null);
			html += Html.li(Html.aJs("&lt;", null, "onClick", method + "(" + prev + ",\"" + cari + "\")"), null, null,
					null);
		}

		for (int i = first; i <= last; i++) {
			if (i == current) {
				html += Html.li(Html.aJs(i + "", null, "onClick", method + "(" + i + ",\"" + cari + "\")"), "active",
						null, null);
			} else {
				html += Html.li(Html.aJs(i + "", null, "onClick", method + "(" + i + ",\"" + cari + "\")"), null, null,
						null);
			}
		}

		if (current == totalPage) {
			html += Html.li(Html.aJs("&gt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&gt;", null, "onClick", method + "(" + next + ",\"" + cari + "\")"), null, null,
					null);
			html += Html.li(Html.aJs("&gt;&gt;", null, "onClick", method + "(" + last + ",\"" + cari + "\")"), null, null,
					null);
		}

		String nav = Html.nav(Html.ul(html, "pagination"));

		return nav;
	}

	private Obat setObatContent(Obat obat, RacikanTemp r, Principal principal) {
		obat.setNama(r.getNama());
		obat.setTipe(1);
		obat.setKode("R" + obat.getNama());
		obat.setStokMinimal(99999);
		obat.setKategori(kategoriService.dapatkanByNama("LAIN-LAIN"));
		obat.setSatuan(satuanService.dapatkanByNama("PCS"));
		obat.setWaktuDibuat(new Date());
		obat.setTerakhirDirubah(new Date());
		obat.setUserInput(principal.getName());
		return obat;
	}

	private Racikan setRacikanContent(Racikan racikan, RacikanTemp r, Principal principal) {
		racikan.setNama(r.getNama());
		racikan.setBiayaRacik(new BigDecimal(r.getBiayaRacik().replaceAll("[.,]", "")));
		racikan.setUserInput(principal.getName());
		racikan.setWaktuDibuat(new Date());
		racikan.setTerakhirDirubah(new Date());
		return racikan;
	}

	private RacikanDetail setRacikanDetailContent(RacikanDetail rd, RacikanDetailTemp rdt, Racikan r,
			Principal principal) {
		rd.setKomposisi(getObat(rdt.getObat()));
		rd.setJumlah(Integer.valueOf(rdt.getJumlah()));
		rd.setHargaSatuan(new BigDecimal(rdt.getHarga().replaceAll("[,.]", "")));
		rd.setRacikan(r);
		rd.setTerakhirDirubah(new Date());
		rd.setWaktuDibuat(new Date());
		rd.setUserInput(principal.getName());
		return rd;
	}

	private BigDecimal hitungSubTotal(BigDecimal harga, Integer jumlah) {
		BigDecimal jml = new BigDecimal(jumlah);
		BigDecimal subTotal = harga.multiply(jml);
		return subTotal;
	}

	private Obat getObat(String nama) {
		Obat get = obatService.dapatkanByNama(nama);

		List<ObatDetail> lObatDetail = obatDetailService.temukanByObats(get);
		get.setDetail(lObatDetail);
		Hibernate.initialize(get.getDetail());

		List<ObatStok> lObatStok = obatStokService.temukanByObats(get);
		get.setStok(lObatStok);
		Hibernate.initialize(get.getStok());

		List<ObatExpired> lObatExpired = obatExpiredService.temukanByObats(get);
		get.setExpired(lObatExpired);
		Hibernate.initialize(get.getExpired());
		return get;
	}
	
	private Racikan getRacikan(Long id) {
		Racikan racikan = racikanService.dapatkan(id);
		
		List<RacikanDetail> listRacikanDetail = racikanDetailService.dapatkanByRacikan(racikan);
		racikan.setRacikanDetail(listRacikanDetail);
		Hibernate.initialize(racikan.getRacikanDetail());
		
		return racikan;
	}

}
