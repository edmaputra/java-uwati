package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import id.edmaputra.uwati.service.pasien.PasienService;
import id.edmaputra.uwati.service.pasien.RekamMedisService;
import id.edmaputra.uwati.specification.RekamMedisPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.handler.PasienHandler;

@Controller
public class RekamMedisController {

	private static final Logger logger = LoggerFactory.getLogger(RekamMedisController.class);

	@Autowired
	private PasienService pasienService;
	
	private Pasien pasien;
	
	@Autowired
	private RekamMedisService rekamMedisService;
//	
//	@Autowired
//	private KaryawanService karyawanService;
//	
//	@Autowired
//	private RekamMedisDetailService rekamMedisDetailService;
//
	@RequestMapping(value ="/rekam-medis/{id}", method = RequestMethod.GET) 
	public ModelAndView tampilkanPage(@PathVariable("id") String id, Principal principal, HttpServletRequest request){
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("rekam-medis-daftar");;
			pasien = pasienService.dapatkan(new Long(id));
			if (pasien.getJenisKelamin() == 0){
				pasien.setInfo("Perempuan");
			} else if (pasien.getJenisKelamin() == 1){
				pasien.setInfo("Laki-laki");
			}
			Instant instant = pasien.getTanggalLahir().toInstant();
			LocalDate tanggalLahir = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			Long usia = ChronoUnit.YEARS.between(tanggalLahir, LocalDate.now());
			pasien.setJenisKelamin(Long.valueOf(usia).intValue());
			mav.addObject("pasien", pasien);
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.loadGagal(principal.getName(), request));
			return null;
		}
	}

	@RequestMapping(value = "/rekammedis/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement tampilkanDaftar(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "tgl", defaultValue = "", required = false) String tanggal,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			RekamMedisPredicateBuilder builder = new RekamMedisPredicateBuilder();
			
			builder.pasien(pasien.getId());
			
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}
			
			if (!StringUtils.isBlank(tanggal)) {
				builder.tanggal(Converter.stringToDate(tanggal));
			}

			BooleanExpression exp = builder.getExpression();
			Page<RekamMedis> page = rekamMedisService.muatDaftar(halaman, exp);

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
//
//	@RequestMapping(value = "/rekammedis/dapatkan", method = RequestMethod.GET)
//	@ResponseBody
//	public Pasien dapatkanEntity(@RequestParam("id") String pasien) {
//		try {
//			Pasien get = getPasien(Long.valueOf(pasien));
//			return get;
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return null;
//		}
//	}
//
//	@RequestMapping(value = "/ada", method = RequestMethod.GET)
//	@ResponseBody
//	public Boolean isAda(@RequestParam("identitas") String identitas) {
//		return pasienService.dapatkanByIdentitas(identitas) != null;
//	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Pasien tambah(@RequestBody PasienHandler h, BindingResult result, Principal principal, HttpServletRequest request) {
		try {		
			Pasien pasien = new Pasien();
//			pasien = setPasien(pasien, h);
			
			pasien.setUserInput(principal.getName());
			pasien.setWaktuDibuat(new Date());	
			pasien.setTerakhirDirubah(new Date());
			
			pasienService.simpan(pasien);
			logger.info(LogSupport.tambah(principal.getName(), pasien.toString(), request));
			pasien.setInfo("Pasien " + pasien.getNama()+" Berhasil Ditambah");
			return pasien;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getNama(), request));
			return null;
		}
	}
//
	@RequestMapping(value = "/rekammedis/baru", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedis tambah(@RequestBody RekamMedis rm, BindingResult result, Principal principal, HttpServletRequest request) {
		try {			
			rm.setUserInput(principal.getName());
			rm.setWaktuDibuat(new Date());	
			rm.setTerakhirDirubah(new Date());
			rm.setInfo("0");
			rekamMedisService.simpan(rm);
			logger.info(LogSupport.tambah(principal.getName(), pasien.toString(), request));			
			return rm;
		} catch (Exception e) {
			logger.info(e.getMessage());
//			logger.info(LogSupport.tambahGagal(principal.getName(), rm, request));
			return null;
		}
	}
	
	private void updateNomorRekamMedis(RekamMedis rm){
		String nomor = "RM";
		
	}
////
////	@Transactional
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	@ResponseBody
//	public Pasien edit(@RequestBody PasienHandler h, BindingResult result, Principal principal,
//			HttpServletRequest request) {
//		Pasien edit = getPasien(h.getId());
//		String entity = edit.toString();
//		try {
//			edit = setPasien(edit, h);
//			
//			edit.setUserEditor(principal.getName());
//			edit.setTerakhirDirubah(new Date());
//			
//			pasienService.simpan(edit);
//			logger.info(LogSupport.edit(principal.getName(), entity, request));
//			edit.setInfo("Pasien "+edit.getNama()+" Berhasil Diubah");
//			return edit;
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			logger.info(LogSupport.editGagal(principal.getName(), entity, request));
//			return null;
//		}
//	}
////
////	@Transactional
//	@RequestMapping(value = "/hapus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public Pasien hapus(@RequestBody PasienHandler p, BindingResult result, Principal principal,
//			HttpServletRequest request) {
//		Pasien hapus = getPasien(p.getId());
//		String entity = hapus.toString();
//		try {
//			pasienService.hapus(hapus);
//			logger.info(LogSupport.hapus(principal.getName(), entity, request));
//			hapus.setInfo("Hapus Berhasil");
//			return hapus;
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
//			return null;
//		}
//	}
//	
//	@RequestMapping(value = "/{id}/rekam-medis", method = RequestMethod.GET)
//	@ResponseBody
//	public Pasien rekamMedis(@PathVariable("id") PasienHandler p, BindingResult result, Principal principal, HttpServletRequest request) {
//		Pasien pasien = getPasien(p.getId());
//		return pasien;
//	}
//	
	private String tabelGenerator(Page<RekamMedis> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>"
				+ "<th>Id</th>"
				+ "<th>Nomor</th>"
				+ "<th>Anamnesa</th>"
				+ "<th>Pemeriksaan</th>"
				+ "<th>Diagnosa</th>"
				+ "<th>Terapi</th>";		
		thead += "<th></th></tr></thead>";
		String data = "";
		for (RekamMedis t : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(t.getId().toString());
			row += Html.td(t.getNomor());
			row += Html.td(t.getAnamnesa());
			row += Html.td(t.getPemeriksaan());
			row += Html.td(t.getDiagnosa());
			btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#pasien-modal", "onClick", "getData(" + t.getId() + ")", 0, "Edit Data");
//			btn += Html.button("btn btn-danger btn-xs", "modal", "#pasien-modal-hapus", "onClick","setIdUntukHapus(" + t.getId() + ")", 1, "Hapus Data");			
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}
//	
//	private String tabelRekamMedisGenerator(Page<RekamMedis> list, HttpServletRequest request) {
//		String html = "";
//		String thead = "<thead><tr>"
//				+ "<th>Id</th>"
//				+ "<th>Nomor</th>"
//				+ "<th>Tanggal</th>"
//				+ "<th>Anamnesa</th>"
//				+ "<th>Pemeriksaan</th>"
//				+ "<th>Diagnosa</th>"
//				+ "<th>Dokter</th>";	
//		thead += "<th></th></tr></thead>";
//		String data = "";
//		for (RekamMedis t : list.getContent()) {
//			String row = "";
//			String btn = "";
//			row += Html.td(t.getId().toString());
//			row += Html.td(t.getNomor());
//			row += Html.td(Converter.dateToStringIndonesianLocale(t.getTanggal()));
//			row += Html.td(t.getAnamnesa());
//			row += Html.td(t.getPemeriksaan());
//			row += Html.td(t.getDiagnosa());
//			row += Html.td(t.getDokter().getNama());			
//			if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_MEDIS")) {
//
//				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#pasien-modal", "onClick",
//						"getData(" + t.getId() + ")", 0, "Edit Data");
//
//				btn += Html.button("btn btn-danger btn-xs", "modal", "#pasien-modal-hapus", "onClick",
//						"setIdUntukHapus(" + t.getId() + ")", 1, "Hapus Data");
//			}
//			row += Html.td(btn);
//			data += Html.tr(row);
//		}
//		String tbody = Html.tbody(data);
//		html = thead + tbody;
//		return html;
//	}
//
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
//	
//	private Pasien setPasien(Pasien p, PasienHandler h){		
//		p.setAgama(h.getAgama());
//		p.setAlamat(h.getAlamat());
//		p.setIdentitas(h.getIdentitas());
//		p.setJaminanKesehatan(h.getJaminanKesehatan());
//		p.setJenisKelamin(h.getJenisKelamin());
//		p.setKontak(h.getKontak());
//		p.setNama(h.getNama());
//		p.setNomorJaminan(h.getNomorJaminan());
//		p.setPekerjaan(h.getPekerjaan());
//		p.setTanggalLahir(Converter.stringToDate(h.getTanggalLahir()));
//		return p;
//	}
//	
//	private Pasien getPasien(Long id){
//		Pasien get = pasienService.dapatkan(id);
//
//		List<RekamMedis> rekamMedis = rekamMedisService.temukanByPasien(get);
//		get.setRekamMedis(rekamMedis);
//		Hibernate.initialize(get.getRekamMedis());
//
//		return get;
//	}
//	
//	private RekamMedis getRekamMedis(Long id){
//		RekamMedis get = rekamMedisService.dapatkan(id);
//
//		List<RekamMedisDetail> rekamMedisDetails = rekamMedisDetailService.dapatkan(get);
//		get.setRekamMedisDetail(rekamMedisDetails);
//		Hibernate.initialize(get.getRekamMedisDetail());
//
//		return get;
//	}
//	

}
