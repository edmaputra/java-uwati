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

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.service.KategoriService;
import id.edmaputra.uwati.service.SatuanService;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.specification.ObatPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.handler.ObatHandler;
import id.edmaputra.uwati.view.json.JsonReturn;
import id.edmaputra.uwati.view.json.Suggestion;

@Controller
@RequestMapping("/obat")
public class ObatController {

	private static final Logger logger = LoggerFactory.getLogger(ObatController.class);

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	@Autowired
	private SatuanService satuanService;

	@Autowired
	private KategoriService kategoriService;

	@ModelAttribute("obat")
	public ObatHandler constructSatuan() {
		return new ObatHandler();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanObat(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("obat-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.loadGagal(principal.getName(), request));
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarObat(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			ObatPredicateBuilder builder = new ObatPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			builder.tipe(0);

			BooleanExpression exp = builder.getExpression();
			Page<Obat> page = obatService.muatDaftar(halaman, exp);

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
	public Obat dapatkanObat(@RequestParam("id") String obat) {
		try {
			Obat get = getObat(Long.valueOf(obat));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/nama", method = RequestMethod.GET)
	@ResponseBody
	public Obat dapatkanObatByNama(@RequestParam("nama") String nama) {
		try {
			Obat get = getObat(nama);
			return get;
		} catch (Exception e) {
			return null;
		}
	}

	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Obat tambahObat(@RequestBody ObatHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			Obat obat = new Obat();
			obat = setObatContent(obat, h);
			obat.setUserInput(principal.getName());
			obat.setWaktuDibuat(new Date());

			List<ObatDetail> lOD = new ArrayList<>();
			ObatDetail obatDetail = new ObatDetail();
			obatDetail = setObatDetailContent(obat, obatDetail, h);
			obatDetail.setUserInput(principal.getName());
			obatDetail.setWaktuDibuat(new Date());
			lOD.add(obatDetail);

			List<ObatStok> lStok = new ArrayList<>();
			ObatStok stok = new ObatStok();
			stok = setObatStokDetailContent(obat, stok, h);
			stok.setUserInput(principal.getName());
			stok.setWaktuDibuat(new Date());
			lStok.add(stok);

			List<ObatExpired> lOE = new ArrayList<>();
			ObatExpired expired = new ObatExpired();
			expired = setObatExpiredContent(obat, expired, h);
			expired.setUserInput(principal.getName());
			expired.setWaktuDibuat(new Date());
			lOE.add(expired);

			obat.setDetail(lOD);
			obat.setStok(lStok);
			obat.setExpired(lOE);

			obatService.simpan(obat);

			logger.info(LogSupport.tambah(principal.getName(), h.toString(), request));
			return obat;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.toString(), request));
			System.out.println(e.getMessage());
			return null;
		}
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Obat editObat(@RequestBody ObatHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Obat edit = getObat(h.getId());
		String entity = edit.toString();
		try {
			int i = 0;
			edit = setObatContent(edit, h);
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());

			for (ObatDetail od : edit.getDetail()) {
				od = setObatDetailContent(edit, od, h);
				od.setUserEditor(principal.getName());
				od.setTerakhirDirubah(new Date());
				edit.getDetail().set(i, od);
				i++;
			}
			i = 0;

			for (ObatStok stok : edit.getStok()) {
				stok = setObatStokDetailContent(edit, stok, h);
				stok.setUserEditor(principal.getName());
				stok.setTerakhirDirubah(new Date());
				edit.getStok().set(i, stok);
				i++;
			}
			i = 0;

			for (ObatExpired expired : edit.getExpired()) {
				expired = setObatExpiredContent(edit, expired, h);
				expired.setUserEditor(principal.getName());
				expired.setTerakhirDirubah(new Date());
				edit.getExpired().set(i, expired);
				i++;
			}

			obatService.simpan(edit);
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
	public Obat hapusObat(@RequestBody ObatHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Obat hapus = getObat(h.getId());
		String entity = hapus.toString();
		try {
			obatService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return null;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	@RequestMapping(value = "/cariobat", method = RequestMethod.GET)
	@ResponseBody
	public Suggestion suggestionObatByNama(@RequestParam("query") String nama) {
		try {
			ObatPredicateBuilder builder = new ObatPredicateBuilder();
			if (!StringUtils.isBlank(nama)) {
				builder.nama(nama);
			}

			builder.tipe(0);

			BooleanExpression exp = builder.getExpression();
			List<Obat> l = obatService.dapatkanListByNama(exp);

			List<JsonReturn> listReturn = new ArrayList<>();
			for (Obat o : l) {
				JsonReturn jr = new JsonReturn();
				jr.setData(o.getNama());
				jr.setValue(o.getNama());
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

	@RequestMapping(value = "/obat-tindakan", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarForTransaksi(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "n", defaultValue = "", required = false) String n,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();			
			
			Page<Obat> page = getListObatAndTindakan(halaman, cari, Integer.valueOf(n), -1);

			String button = buttonListGenerator(page, 2, request);
			el.setButton(button);

			if (page.hasContent()) {
				if (page.getTotalPages() > 1) {
					int current = page.getNumber() + 1;
					int next = current + 1;
					int prev = current - 1;

					String h = navigasiHalamanGenerator(prev, current, next, page.getTotalPages(), cari);
					el.setNavigasiObat(h);
				}
			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/obat", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarObatForTransaksi(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "n", defaultValue = "", required = false) String n,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();			
			
			Page<Obat> page = getListObatAndTindakan(halaman, cari, Integer.valueOf(n), 0);

			String button = buttonListGenerator(page, 2, request);
			el.setButton(button);

			if (page.hasContent()) {
				if (page.getTotalPages() > 1) {
					int current = page.getNumber() + 1;
					int next = current + 1;
					int prev = current - 1;

					String h = navigasiHalamanGenerator(prev, current, next, page.getTotalPages(), cari);
					el.setNavigasiObat(h);
				}
			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private Page<Obat> getListObatAndTindakan(Integer halaman, String nama, Integer n, int tipe) {
		ObatPredicateBuilder builder = new ObatPredicateBuilder();
		if (!StringUtils.isBlank(nama)) {
			builder.nama(nama);
		}		
		
		if (tipe != -1){
			builder.tipe(tipe);
		}
//		builder.stok(1);

		BooleanExpression exp = builder.getExpression();
		return obatService.muatDaftar(halaman, exp, n);
	}

	private String tabelGenerator(Page<Obat> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>" + "<th>Obat</th>" + "<th>Kode</th>" + "<th>Satuan</th>"
				+ "<th>Kategori</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Obat o : list.getContent()) {
			String row = "";
			String btn = "";
			
			row += Html.td(o.getNama());
			row += Html.td(o.getKode());
			row += Html.td(o.getSatuan().getNama());
			row += Html.td(o.getKategori().getNama());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(o.getUserInput());
				row += Html.td(Formatter.formatTanggal(o.getWaktuDibuat()));
				row += Html.td(o.getUserEditor());
				row += Html.td(Formatter.formatTanggal(o.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#obat-modal", "onClick",
						"getData(" + o.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#obat-modal-hapus", "onClick",
						"setIdUntukHapus(" + o.getId() + ")", 1);
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

	private String navigasiHalamanGenerator(int prev, int current, int next, int totalPage, String cari) {
		String html = "";

		if (current == 1) {
			html += Html.aJs("&gt;", "btn btn-primary", "onClick", "refreshObat(" + next + ",\"" + cari + "\")");
		} else if (current == totalPage) {
			html += Html.aJs("&lt;", "btn btn-primary", "onClick", "refreshObat(" + prev + ",\"" + cari + "\")");
		} else if (current > 1 && current < totalPage) {
			html += Html.aJs("&lt;", "btn btn-primary", "onClick", "refreshObat(" + prev + ",\"" + cari + "\")");
			html += Html.aJs("&gt;", "btn btn-primary", "onClick", "refreshObat(" + next + ",\"" + cari + "\")");
		}
		// System.out.println(html);
		return html;
	}

	private String buttonListGenerator(Page<Obat> list, int jumlahKolom, HttpServletRequest request) {
		String button = "";
		String isi = "";
		int size = list.getContent().size();
		for (int i = 0; i < size; i++) {
			isi += Html.aJs(list.getContent().get(i).getNama(), "btn btn-default btn-wrapword", "onClick", "tambahObat(" + list.getContent().get(i).getId() + ")");
			if ((i + 1) % jumlahKolom == 0 || (i + 1) == size) {
				button += Html.div(isi, "btn-group btn-group-justified");
				isi = "";
			}
		}

		return button;
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

	private Obat setObatContent(Obat obat, ObatHandler h) {
		obat.setNama(h.getNama());
		obat.setKode(h.getKode());
		obat.setBarcode(h.getBarcode());
		obat.setBatch(h.getBatch());
		obat.setTipe(0);
		obat.setStokMinimal(Integer.valueOf(h.getStokMinimal()));
		obat.setSatuan(satuanService.dapatkanByNama(h.getSatuan()));
		obat.setKategori(kategoriService.dapatkanByNama(h.getKategori()));
		obat.setTerakhirDirubah(new Date());
		return obat;
	}

	private ObatDetail setObatDetailContent(Obat obat, ObatDetail obatDetail, ObatHandler h) {
		obatDetail.setObat(obat);
		h = hilangkanTitik(h);
		obatDetail.setHargaBeli(new BigDecimal(h.getHargaBeli()));
		obatDetail.setHargaJual(new BigDecimal(h.getHargaJual()));
		obatDetail.setHargaJualResep(new BigDecimal(h.getHargaJualResep()));
		obatDetail.setHargaDiskon(new BigDecimal(h.getHargaDiskon()));
		obatDetail.setTerakhirDirubah(new Date());
		return obatDetail;
	}

	private ObatStok setObatStokDetailContent(Obat obat, ObatStok stok, ObatHandler h) {
		stok.setObat(obat);
		stok.setStok(h.getStok());
		stok.setTerakhirDirubah(new Date());
		return stok;
	}

	private ObatExpired setObatExpiredContent(Obat obat, ObatExpired expired, ObatHandler h) {
		expired.setObat(obat);
		expired.setTanggalExpired(Converter.stringToDate(h.getTanggalExpired()));
		expired.setTerakhirDirubah(new Date());
		return expired;
	}

	private ObatHandler hilangkanTitik(ObatHandler h) {
		try {
			h.setHargaBeli(h.getHargaBeli().replaceAll("[.]", ""));
			h.setHargaJual(h.getHargaJual().replaceAll("[.]", ""));
			h.setHargaJualResep(h.getHargaJualResep().replaceAll("[.]", ""));
			h.setHargaDiskon(h.getHargaDiskon().replaceAll("[.]", ""));
			return h;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
