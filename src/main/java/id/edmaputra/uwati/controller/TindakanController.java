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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Satuan;
import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatHandler;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.specification.ObatPredicateBuilder;
import id.edmaputra.uwati.specification.SatuanPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.json.JsonReturn;
import id.edmaputra.uwati.view.json.Suggestion;

@Controller
@RequestMapping("/tindakan")
public class TindakanController {

	private static final Logger logger = LoggerFactory.getLogger(TindakanController.class);

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	// @ModelAttribute("tindakan")
	// public Satuan constructSatuan() {
	// return new Satuan();
	// }

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

			ObatPredicateBuilder builder = new ObatPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			builder.tipe(2);

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
	public Obat dapatkanEntity(@RequestParam("id") String tindakan) {
		try {
			Obat get = getObat(Long.valueOf(tindakan));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/ada", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAda(@RequestParam("nama") String nama) {
		return obatService.dapatkanByNama(nama) != null;
	}

//	@Transactional
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Obat tambah(@RequestBody ObatHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			Obat obat = new Obat();
			obat.setKode(h.getKode());
			obat.setNama(h.getNama());
			obat.setTipe(2);
			obat.setStokMinimal(999);
			
			List<ObatDetail> details = new ArrayList<>();
			ObatDetail obatDetail = new ObatDetail();
			obatDetail = setObatDetailContent(obat, obatDetail, h);	
			obatDetail.setUserInput(principal.getName());
			obatDetail.setWaktuDibuat(new Date());					
			details.add(obatDetail);
			
			List<ObatStok> stoks = new ArrayList<>();
			ObatStok stok = new ObatStok();
			stok = setObatStokDetailContent(obat, stok, h);
			stok.setUserInput(principal.getName());
			stok.setWaktuDibuat(new Date());
			stoks.add(stok);

			List<ObatExpired> expires = new ArrayList<>();
			ObatExpired expired = new ObatExpired();
			expired = setObatExpiredContent(obat, expired, h);
			expired.setUserInput(principal.getName());
			expired.setWaktuDibuat(new Date());
			expires.add(expired);
			
			obat.setDetail(details);
			obat.setStok(stoks);
			obat.setExpired(expires);
			
			obat.setUserInput(principal.getName());
			obat.setWaktuDibuat(new Date());
			obat.setTerakhirDirubah(new Date());
			obatService.simpan(obat);
			logger.info(LogSupport.tambah(principal.getName(), obat.toString(), request));
			return obat;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getNama(), request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Obat edit(@RequestBody ObatHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Obat edit = getObat(h.getId());
		String entity = edit.toString();
		try {
			edit.setNama(h.getNama());
			edit.setNama(h.getKode());
			edit.getDetail().get(0).setHargaJual(new BigDecimal(h.getHargaJual().replaceAll("[.]", "")));
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			obatService.simpan(edit);
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
	public Obat hapus(@RequestBody ObatHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Obat hapus = getObat(h.getId());
		String entity = hapus.toString();
		try {
			obatService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			hapus.setInfo("Hapus Berhasil");
			return hapus;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	private String tabelGenerator(Page<Obat> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>"
				+ "<th>Id</th>"
				+ "<th>Tindakan</th>"
				+ "<th>Kode</th>"
				+ "<th>Tarif</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Obat t : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(t.getId().toString());
			row += Html.td(t.getNama());
			row += Html.td(t.getKode());
			List<ObatDetail> detail = obatDetailService.temukanByObat(t);
			row += Html.td(Formatter.patternCurrency(detail.get(0).getHargaJual()));
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
	
	private Obat getObat(Long id){
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
	
	private ObatDetail setObatDetailContent(Obat obat, ObatDetail obatDetail, ObatHandler h){
		obatDetail.setObat(obat);
		obatDetail.setHargaBeli(BigDecimal.ZERO);		
		obatDetail.setHargaJual(new BigDecimal(h.getHargaJual().replaceAll("[.]", "")));
		obatDetail.setHargaJualResep(BigDecimal.ZERO);
		obatDetail.setHargaDiskon(BigDecimal.ZERO);
		obatDetail.setTerakhirDirubah(new Date());
		return obatDetail;
	}
	
	private ObatStok setObatStokDetailContent(Obat obat, ObatStok stok, ObatHandler h){
		stok.setObat(obat);
		stok.setStok(9999);
		stok.setTerakhirDirubah(new Date());
		return stok;
	}
	
	private ObatExpired setObatExpiredContent(Obat obat, ObatExpired expired, ObatHandler h){	
		expired.setObat(obat);
		expired.setTanggalExpired(new Date());		
		expired.setTerakhirDirubah(new Date());
		return expired;
	}

}
