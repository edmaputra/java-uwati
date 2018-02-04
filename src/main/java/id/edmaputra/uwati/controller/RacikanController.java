package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

import id.edmaputra.uwati.controller.support.ObatControllerSupport;
import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;
import id.edmaputra.uwati.entity.master.obat.RacikanDetailTemporary;
import id.edmaputra.uwati.entity.pasien.Pasien;
import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetail;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetailTemp;
import id.edmaputra.uwati.service.KategoriService;
import id.edmaputra.uwati.service.SatuanService;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.obat.RacikanDetailService;
import id.edmaputra.uwati.service.obat.RacikanDetailTempService;
import id.edmaputra.uwati.service.obat.RacikanService;
import id.edmaputra.uwati.specification.RacikanPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.handler.RacikanDetailHandler;
import id.edmaputra.uwati.view.handler.RacikanHandler;
import id.edmaputra.uwati.view.handler.RekamMedisHandler;

@Controller
@RequestMapping("/racikan")
public class RacikanController {

	private static final Logger logger = LoggerFactory.getLogger(RacikanController.class);

	@Autowired
	private RacikanService racikanService;
	
	@Autowired
	private RacikanDetailService racikanDetailService;
	
	@Autowired
	private RacikanDetailTempService racikanDetailTempService;

	@Autowired
	private ObatService obatService;
	
	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	@Autowired
	private KategoriService kategoriService;

	@Autowired
	private SatuanService satuanService;

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
			BigDecimal hargaJual = r.getBiayaRacik().add(r.getHargaJual());
			el.setGrandTotal(Formatter.patternCurrency(hargaJual));
			el.setValue1(r.getNama());
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public RacikanDetailHandler dapatkanRacikan(@RequestParam("id") String id, Principal principal) {
		try {
			RacikanDetailHandler h = new RacikanDetailHandler();
			Racikan r = getRacikan(Long.valueOf(id));
			List<RacikanDetail> list = r.getRacikanDetail();
			List<RacikanDetailTemporary> listTemporary = new ArrayList<>();
			racikanDetailTempService.hapusBatch(String.valueOf(r.getId()));
			
			BigDecimal total = new BigDecimal(0);
			for (RacikanDetail d : list){
				RacikanDetailTemporary temp = new RacikanDetailTemporary();
				temp.setRandomId(r.getId().toString());
				temp.setKomposisi(d.getKomposisi().getNama());
				temp.setIdObat(d.getKomposisi().getId().toString());
				BigDecimal hargaSatuan = d.getHargaSatuan();
				Integer jumlah = d.getJumlah();
				BigDecimal hargaTotal = d.getHargaTotal();
				temp.setHargaSatuan(Converter.patternCurrency(hargaSatuan));
				temp.setHargaTotal(Converter.patternCurrency(hargaTotal));
				temp.setJumlah(jumlah.toString());
				temp.setWaktuDibuat(new Date());
				temp.setTerakhirDirubah(new Date());
				temp.setUserInput(principal.getName());
				racikanDetailTempService.simpan(temp);
				listTemporary.add(temp);
				total = total.add(d.getHargaTotal());
			}
			
			String tabel = tabelTerapiGenerator(listTemporary);
			h.setRacikan(r.getNama());
			h.setBiayaRacik(Converter.patternCurrency(r.getBiayaRacik()));
			h.setHargaRacikan(String.valueOf(total));
			h.setTabel(tabel);
			h.setInfo(String.valueOf(r.getId()));
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public RacikanHandler tambahRacikan(@RequestBody RacikanHandler r, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			Obat obat = new Obat();
			obat = setObatContent(obat, r, principal);

			Racikan racikan = new Racikan();
			racikan = setRacikanContent(racikan, r, principal);

			BigDecimal harga = new BigDecimal(0);

			List<RacikanDetail> l = new ArrayList<>();
			List<RacikanDetailTemporary> list = racikanDetailTempService.dapatkanListByRandomId(r.getRandomId());
			for (RacikanDetailTemporary temp : list){
				RacikanDetail rd = new RacikanDetail();
				rd = setRacikanDetailContent(rd, temp, racikan, principal);
				l.add(rd);
				harga = harga.add(hitungSubTotal(rd.getHargaSatuan(), rd.getJumlah()));				
			}			
			racikan.setHargaJual(harga);
			racikan.setRacikanDetail(l);

			obatService.simpan(obat);
			racikanService.simpan(racikan);
			
			logger.info(LogSupport.tambah(principal.getName(), r.toString(), request));
			logger.info(LogSupport.tambah(principal.getName(), obat.getNama(), request));
			racikanDetailTempService.hapusBatch(r.getRandomId());
			return r;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), r.toString(), request));
			return null;
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public RacikanHandler edit(@RequestBody RacikanHandler h, BindingResult result, Principal principal, HttpServletRequest request) {		
		Racikan r = getRacikan(Long.valueOf(h.getId()));
		Obat obat = getObat(h.getNama());
		List<RacikanDetail> l = new ArrayList<>();
		try {
			obat = setObatContent(obat, h, principal);			
			r = setRacikanContent(r, h, principal);
			
			BigDecimal harga = new BigDecimal(0);
		
			racikanDetailService.hapusBatch(r);
			
			List<RacikanDetailTemporary> list = racikanDetailTempService.dapatkanListByRandomId(h.getRandomId());
			for (RacikanDetailTemporary temp : list){
				RacikanDetail rd = new RacikanDetail();
				rd = setRacikanDetailContent(rd, temp, r, principal);
				l.add(rd);
				harga = harga.add(hitungSubTotal(rd.getHargaSatuan(), rd.getJumlah()));				
			}			
			r.setHargaJual(harga);
			r.setRacikanDetail(l);	
			
			obatService.simpan(obat);
			racikanService.simpan(r);
			
			logger.info(LogSupport.edit(principal.getName(), r.getId().toString(), request));
			logger.info(LogSupport.edit(principal.getName(), obat.getNama(), request));
			racikanDetailTempService.hapusBatch(h.getRandomId());
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), r.getId() + "", request));
			h.setInfo(e.getMessage());
			return h;
		}
	}
	
	@RequestMapping(value = "/hapus", method = RequestMethod.POST)
	@ResponseBody
	public RacikanHandler hapus(@RequestBody RacikanHandler temp, BindingResult result, Principal principal,HttpServletRequest request) {
		Racikan racikan = getRacikan(Long.valueOf(temp.getId()));		
		try {
			racikanService.hapus(racikan);
			obatService.hapus(getObat(racikan.getNama()));
			logger.info(LogSupport.hapus(principal.getName(), temp.getId()+"", request));
			temp.setInfo(temp.getId()+" Terhapus");						
			return temp;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), temp.toString(), request));
			temp.setInfo(temp.getId()+" Gagal Terhapus : "+e.getMessage());
			return temp;
		}
	}
	
	@RequestMapping(value = "/tambah-obat", method = RequestMethod.POST)
	@ResponseBody
	public RacikanDetailHandler tambahTerapi(@RequestBody RacikanDetailHandler temp, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			RacikanDetailTemporary t = null;
			RacikanDetailTemporary tersimpan = racikanDetailTempService.dapatkan(temp.getRandomId(), temp.getIdObat()); 
			if (tersimpan == null){
				t = new RacikanDetailTemporary();
				Obat obat = getObat(Long.valueOf(temp.getIdObat()).longValue());
				t.setRandomId(temp.getRandomId());
				t.setKomposisi(obat.getNama());
				t.setIdObat(obat.getId().toString());
				t.setHargaSatuan(Converter.patternCurrency(obat.getDetail().get(0).getHargaJual()));
				t.setHargaTotal(Converter.patternCurrency(obat.getDetail().get(0).getHargaJual()));
				t.setJumlah("1");
			} else {
				t = tersimpan;
				Integer j = Integer.valueOf(tersimpan.getJumlah()).intValue() + 1;				
				t.setJumlah(j.toString());
				BigDecimal h = new BigDecimal(t.getHargaSatuan().replaceAll("[.,]", ""));
				BigDecimal total = h.multiply(new BigDecimal(j));
				t.setHargaTotal(Converter.patternCurrency(total));
			}			
			t.setIsSudahDiproses(false);
			t.setUserInput(principal.getName());			
			t.setWaktuDibuat(new Date());
			t.setTerakhirDirubah(new Date());
//			rekamMedisDetailTempValidator.validate(t);
			racikanDetailTempService.simpan(t);
			logger.info(LogSupport.tambah(principal.getName(), temp.toString(), request));
			return temp;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), temp.getRandomId() + "", request));
			temp.setInfo(e.getMessage());
			return temp;
		}
	}
	
	@RequestMapping(value = "/hapus-obat", method = RequestMethod.POST)
	@ResponseBody
	public RacikanDetailTemporary hapusObat(@RequestBody RacikanDetailHandler temp, BindingResult result, Principal principal,HttpServletRequest request) {
		RacikanDetailTemporary tersimpan = racikanDetailTempService.dapatkan(temp.getRandomId(), temp.getIdObat());		
		try {			
			racikanDetailTempService.hapus(tersimpan);
			logger.info(LogSupport.hapus(principal.getName(), tersimpan.getId()+"", request));
			tersimpan.setInfo(tersimpan.getKomposisi()+" Terhapus");						
			return tersimpan;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), tersimpan.toString(), request));
			tersimpan.setInfo(tersimpan.getKomposisi()+" Gagal Terhapus : "+e.getMessage());
			return tersimpan;
		}
	}	
	
	@RequestMapping(value = "/list-obat", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement daftarObatTemp(@RequestParam(value = "randomId", required = true) String randomId, HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();
			
			List<RacikanDetailTemporary> list = racikanDetailTempService.dapatkanListByRandomId(randomId);

			String tabel = tabelTerapiGenerator(list);
			el.setTabelTerapi(tabel);
			el.setValue1(hargaRacikan(list));
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	private String hargaRacikan(List<RacikanDetailTemporary> list){
		Integer t = 0;
		for (RacikanDetailTemporary r : list){
			Integer hargaTotal = Integer.valueOf(r.getHargaTotal().replaceAll("[.,]", ""));
			t = t + hargaTotal;
		}
		return t.toString();
	}

	private String tabelGenerator(Page<Racikan> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr><th>Nama</th>";
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
			row += Html.td(s.getNama());
			row += Html.td(Formatter.patternCurrency(s.getHargaJual()));
			row += Html.td(Formatter.patternCurrency(s.getBiayaRacik()));
			if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_APOTEK")) {
				row += Html.td(s.getUserInput());
				row += Html.td(Formatter.formatTanggal(s.getWaktuDibuat()));
				row += Html.td(s.getUserEditor());
				row += Html.td(Formatter.formatTanggal(s.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#racikan-modal", "onClick",
						"getData(" + s.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#racikan-modal-hapus", "onClick",
						"setIdUntukHapus(" + s.getId() + ")", 1);
			}
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}
	
	private String tabelTerapiGenerator(List<RacikanDetailTemporary> list) {
		String html = "";
		String data = "";
		for (RacikanDetailTemporary t : list) {
			String row = "";
			String btn = "";
			row += Html.td(t.getKomposisi());
			row += Html.td(t.getJumlah().toString());
			row += Html.td(t.getHargaTotal());
			btn += Html.aJs("<i class='fa fa-trash-o'></i>", "btn btn-danger btn-xs", "onClick", "hapusObat(" + t.getIdObat() + ")", "Hapus Data");			
			row += Html.td(btn);
			data += Html.tr(row);
		}
		html = data;		 	
//		System.out.println(html);
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
			row += Html.td(Formatter.patternCurrency(s.getHargaJual()));
			row += Html.td(Formatter.patternCurrency(s.getBiayaRacik()));
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
			row += Html.td(Formatter.patternCurrency(s.getHargaSatuan().multiply(new BigDecimal(s.getJumlah()))));						
			data += Html.tr(row);
		}
		String row = "";
		row += Html.td("Biaya Racik");
		row += Html.td("1");			
		row += Html.td(Formatter.patternCurrency(list.get(0).getRacikan().getBiayaRacik()));
		data += Html.tr(row);
		
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

	private Obat setObatContent(Obat obat, RacikanHandler r, Principal principal) {
		obat.setNama(r.getNama());
		obat.setTipe(1);
		obat.setKode("R" + obat.getNama());
		obat.setStokMinimal(99999);
		obat.setKategori(kategoriService.dapatkanSemua().get(0));
		obat.setSatuan(satuanService.dapatkanSemua().get(0));
		obat.setWaktuDibuat(new Date());
		obat.setTerakhirDirubah(new Date());
		obat.setUserInput(principal.getName());
		return obat;
	}

	private Racikan setRacikanContent(Racikan racikan, RacikanHandler r, Principal principal) {
		racikan.setNama(r.getNama());
		racikan.setBiayaRacik(new BigDecimal(r.getBiayaRacik().replaceAll("[.,]", "")));
		racikan.setUserInput(principal.getName());
		racikan.setWaktuDibuat(new Date());
		racikan.setTerakhirDirubah(new Date());
		return racikan;
	}

	private RacikanDetail setRacikanDetailContent(RacikanDetail rd, RacikanDetailHandler rdt, Racikan r,
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
	
	private RacikanDetail setRacikanDetailContent(RacikanDetail rd, RacikanDetailTemporary rdt, Racikan r,
			Principal principal) {
		rd.setKomposisi(getObat(rdt.getKomposisi()));				
		rd.setJumlah(Integer.valueOf(rdt.getJumlah()));
		rd.setHargaSatuan(new BigDecimal(rdt.getHargaSatuan().replaceAll("[,.]", "")));
		rd.setHargaTotal(rd.getHargaSatuan().multiply(new BigDecimal(rd.getJumlah())));
		
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
	
	private Racikan getRacikan(String nama) {
		Racikan racikan = racikanService.dapatkanByNama(nama);

		List<RacikanDetail> listRacikanDetail = racikanDetailService.dapatkanByRacikan(racikan);
		racikan.setRacikanDetail(listRacikanDetail);
		Hibernate.initialize(racikan.getRacikanDetail());

		return racikan;
	}
	
	private Racikan getRacikan(Long id) {
		Racikan racikan = racikanService.dapatkan(id);

		List<RacikanDetail> listRacikanDetail = racikanDetailService.dapatkanByRacikan(racikan);
		racikan.setRacikanDetail(listRacikanDetail);
		Hibernate.initialize(racikan.getRacikanDetail());

		return racikan;
	}

	private Obat getObat(String nama) {
		Obat get = obatService.dapatkanByNama(nama);

		List<ObatDetail> lObatDetail = obatDetailService.temukanByObat(get);
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

	private Obat getObat(Long id) {
		Obat get = obatService.dapatkan(id);

		List<ObatDetail> lObatDetail = obatDetailService.temukanByObat(get);
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

	private void updateStokObat(String obat, String jumlah, int operasi) {
		Obat o = getObat(obat);
		if (o.getTipe() == 0) {
			Integer stokLama = o.getStok().get(0).getStok();
			Integer stokBaru = null;
			// pengurangan stok
			if (operasi == 0) {
				stokBaru = stokLama - Integer.valueOf(jumlah).intValue();
			}
			// penambahan stok
			else if (operasi == 1) {
				stokBaru = stokLama + Integer.valueOf(jumlah).intValue();
			}
			o.getStok().get(0).setStok(stokBaru);
			obatService.simpan(o);
		} else if (o.getTipe() == 1) {
			Racikan r = getRacikan(obat);
			for (RacikanDetail rd : r.getRacikanDetail()) {
				Obat or = getObat(rd.getKomposisi().getNama());
				Integer jumlahBeli = Integer.valueOf(jumlah) * rd.getJumlah();
				Integer stokLama = or.getStok().get(0).getStok();
				Integer stokBaru = null;
				// pengurangan stok
				if (operasi == 0) {
					stokBaru = stokLama - jumlahBeli;
				}
				// penambahan stok
				else if (operasi == 1) {
					stokBaru = stokLama + jumlahBeli;
				}
				or.getStok().get(0).setStok(stokBaru);
				obatService.simpan(or);
			}
		}
	}

}
