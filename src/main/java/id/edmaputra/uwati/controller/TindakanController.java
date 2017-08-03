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
import id.edmaputra.uwati.view.handler.TindakanHandler;

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

	@Autowired
	private SatuanService satuanService;

	@Autowired
	private KategoriService kategoriService;

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

				String h = Html.navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(), cari);
				el.setNavigasiHalaman(h);
			}
			
			System.out.println(el.getTabel());
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
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Obat tambah(@RequestBody TindakanHandler h, BindingResult result, Principal principal,
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
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getNama(), request));
			return null;
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Obat edit(@RequestBody TindakanHandler tindakan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Obat edit = getObat(Long.valueOf(tindakan.getId()));
		String entity = edit.toString();
		try {
			int i = 0;
			edit = setObatContent(edit, tindakan);
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());

			for (ObatDetail od : edit.getDetail()) {
				od = setObatDetailContent(edit, od, tindakan);
				od.setUserEditor(principal.getName());
				od.setTerakhirDirubah(new Date());
				edit.getDetail().set(i, od);
				i++;
			}
			i = 0;

			for (ObatStok stok : edit.getStok()) {
				stok = setObatStokDetailContent(edit, stok, tindakan);
				stok.setUserEditor(principal.getName());
				stok.setTerakhirDirubah(new Date());
				edit.getStok().set(i, stok);
				i++;
			}
			i = 0;

			for (ObatExpired expired : edit.getExpired()) {
				expired = setObatExpiredContent(edit, expired, tindakan);
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
	public Obat hapus(@RequestBody TindakanHandler tindakan, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Obat hapus = getObat(Long.valueOf(tindakan.getId()));
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

	private String tabelGenerator(Page<Obat> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>" +  "<th>Tindakan</th>" + "<th>Kode</th>" + "<th>Harga/Tarif</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Obat o : list.getContent()) {
			String row = "";
			String btn = "";
			Obat obat = getObatDetail(o.getId());
			row += Html.td(obat.getNama());
			row += Html.td(obat.getKode());
			row += Html.td(Converter.patternCurrency(obat.getDetail().get(0).getHargaJualResep()));
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(o.getUserInput());
				row += Html.td(Formatter.formatTanggal(o.getWaktuDibuat()));
				row += Html.td(o.getUserEditor());
				row += Html.td(Formatter.formatTanggal(o.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#tindakan-modal", "onClick",
						"getData(" + o.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#tindakan-modal-hapus", "onClick",
						"setIdUntukHapus(" + o.getId() + ")", 1);
			}
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}

	// private String tabelGenerator(Page<Tindakan> list, HttpServletRequest
	// request) {
	// String html = "";
	// String thead = "<thead><tr>"
	// + "<th>Id</th>"
	// + "<th>Tindakan</th>"
	// + "<th>Kode</th>"
	// + "<th>Tarif</th>"
	// + "<th>Info</th>";
	// if (request.isUserInRole("ROLE_ADMIN")) {
	// thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User
	// Editor</th>"
	// + "<th>Terakhir Diubah</th>";
	// }
	// thead += "<th></th></tr></thead>";
	// String data = "";
	// for (Tindakan t : list.getContent()) {
	// String row = "";
	// String btn = "";
	// row += Html.td(t.getId().toString());
	// row += Html.td(t.getNama());
	// row += Html.td(t.getKode());
	// row += Html.td(Formatter.patternCurrency(t.getTarif()));
	// row += Html.td(t.getInfo());
	// if (request.isUserInRole("ROLE_ADMIN")) {
	// row += Html.td(t.getUserInput());
	// row += Html.td(Formatter.formatTanggal(t.getWaktuDibuat()));
	// row += Html.td(t.getUserEditor());
	// row += Html.td(Formatter.formatTanggal(t.getTerakhirDirubah()));
	//
	// btn = Html.button("btn btn-primary btn-xs btnEdit", "modal",
	// "#tindakan-modal", "onClick",
	// "getData(" + t.getId() + ")", 0);
	//
	// btn += Html.button("btn btn-danger btn-xs", "modal",
	// "#tindakan-modal-hapus", "onClick",
	// "setIdUntukHapus(" + t.getId() + ")", 1);
	// }
	// row += Html.td(btn);
	// data += Html.tr(row);
	// }
	// String tbody = Html.tbody(data);
	// html = thead + tbody;
	// return html;
	// }
	
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
	
	private Obat getObatDetail(Long id){
		Obat get = obatService.dapatkan(id);

		List<ObatDetail> lObatDetail = obatDetailService.temukanByObat(get);
		get.setDetail(lObatDetail);
		Hibernate.initialize(get.getDetail());
		
		return get;
	}

	private Obat setObatContent(Obat obat, TindakanHandler h) {
		obat.setNama(h.getNama());
		obat.setKode(h.getKode());
		obat.setBarcode("");
		obat.setBatch("");
		obat.setTipe(2);
		obat.setStokMinimal(9999);
		obat.setSatuan(satuanService.dapatkanSemua().get(0));
		obat.setKategori(kategoriService.dapatkanSemua().get(0));
		obat.setTerakhirDirubah(new Date());
		return obat;
	}

	private ObatDetail setObatDetailContent(Obat obat, ObatDetail obatDetail, TindakanHandler h) {
		obatDetail.setObat(obat);
		obatDetail.setHargaBeli(BigDecimal.ZERO);
		obatDetail.setHargaJual(new BigDecimal(h.getTarif().replaceAll("[.,]", "")));
		obatDetail.setHargaJualResep(new BigDecimal(h.getTarif().replaceAll("[.,]", "")));
		obatDetail.setHargaDiskon(BigDecimal.ZERO);
		obatDetail.setTerakhirDirubah(new Date());
		return obatDetail;
	}

	private ObatStok setObatStokDetailContent(Obat obat, ObatStok stok, TindakanHandler h) {
		stok.setObat(obat);
		stok.setStok(9999999);
		stok.setTerakhirDirubah(new Date());
		return stok;
	}

	private ObatExpired setObatExpiredContent(Obat obat, ObatExpired expired, TindakanHandler h) {
		expired.setObat(obat);
		expired.setTanggalExpired(new Date());
		expired.setTerakhirDirubah(new Date());
		return expired;
	}

}
