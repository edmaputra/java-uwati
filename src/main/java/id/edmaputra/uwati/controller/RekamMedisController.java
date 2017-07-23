package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PathVariable;
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
import id.edmaputra.uwati.entity.pasien.Pasien;
import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetail;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetailTemp;
import id.edmaputra.uwati.entity.pasien.RekamMedisHandler;
import id.edmaputra.uwati.entity.pengguna.Pengguna;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.obat.RacikanDetailService;
import id.edmaputra.uwati.service.obat.RacikanService;
import id.edmaputra.uwati.service.pasien.PasienService;
import id.edmaputra.uwati.service.pasien.RekamMedisDetailService;
import id.edmaputra.uwati.service.pasien.RekamMedisDetailTempService;
import id.edmaputra.uwati.service.pasien.RekamMedisService;
import id.edmaputra.uwati.service.pengguna.PenggunaService;
import id.edmaputra.uwati.specification.RekamMedisPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.validator.impl.RekamMedisDetailTempValidator;
import id.edmaputra.uwati.validator.impl.RekamMedisDetailValidator;
import id.edmaputra.uwati.validator.impl.RekamMedisValidator;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.THead;

@Controller
public class RekamMedisController {

	private static final Logger logger = LoggerFactory.getLogger(RekamMedisController.class);
	
	private final int LENGTH_TEXT = 15;
	private final int STAT_REKAMMEDIS_BARU = 0;
	private final int STAT_PENJUALAN_RESEP = 1;

	@Autowired
	private PasienService pasienService;
	
	@Autowired
	private PenggunaService penggunaService;

	@Autowired
	private RekamMedisService rekamMedisService;
	
	@Autowired
	private RekamMedisValidator rekamMedisValidator;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	@Autowired
	private RekamMedisDetailService rekamMedisDetailService;
	
	@Autowired
	private RekamMedisDetailValidator rekamMedisDetailValidator;
	
	@Autowired
	private RekamMedisDetailTempService rekamMedisDetailTempService;
	
	@Autowired
	private RekamMedisDetailTempValidator rekamMedisDetailTempValidator;
	
	@Autowired
	private RacikanService racikanService;

	@Autowired
	private RacikanDetailService racikanDetailService;

	//
	@RequestMapping(value = "/rekam-medis/{id}", method = RequestMethod.GET)
	public ModelAndView tampilkanPage(@PathVariable("id") String id, Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("rekam-medis-daftar");			
			Pasien pasien = pasienService.dapatkan(new Long(id));
			if (pasien.getJenisKelamin() == 0) {
				pasien.setInfo("Perempuan");
			} else if (pasien.getJenisKelamin() == 1) {
				pasien.setInfo("Laki-laki");
			}
			Instant instant = pasien.getTanggalLahir().toInstant();
			LocalDate tanggalLahir = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			Long usia = ChronoUnit.YEARS.between(tanggalLahir, LocalDate.now());
			pasien.setJenisKelamin(Long.valueOf(usia).intValue());
			mav.addObject("pasien", pasien);

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date tanggalHariIni = new Date();
			mav.addObject("tanggalHariIni", formatter.format(tanggalHariIni));
			
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.loadGagal(principal.getName(), request));
			return null;
		}
	}
	
	@RequestMapping(value = "/resep", method = RequestMethod.GET)
	public ModelAndView tampilkanResep(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("resep-daftar");			
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
			@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			RekamMedisPredicateBuilder builder = new RekamMedisPredicateBuilder();
			Pasien p = pasienService.dapatkan(Long.valueOf(id).longValue());

			builder.pasien(p.getId());
			builder.saved(true);

			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			if (!StringUtils.isBlank(tanggal)) {
				builder.tanggal(Converter.stringToDate(tanggal));
			}

			BooleanExpression exp = builder.getExpression();
			Page<RekamMedis> page = rekamMedisService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request, THead.THEAD_REKAMMEDIS);
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
	
	@RequestMapping(value = "/resep/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement tampilkanDaftarResep(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "tgl", defaultValue = "", required = false) String tanggal,			
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			RekamMedisPredicateBuilder builder = new RekamMedisPredicateBuilder();			

			builder.saved(true);
//			builder.sudahDiproses(false);

			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			if (!StringUtils.isBlank(tanggal)) {
				builder.tanggal(Converter.stringToDate(tanggal));
			}

			BooleanExpression exp = builder.getExpression();
			Page<RekamMedis> page = rekamMedisService.muatDaftarResep(halaman, exp);

			String tabel = tabelResepGenerator(page, request, THead.THEAD_RESEP);
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
	
	@RequestMapping(value = "/rekammedis/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public RekamMedisHandler dapatkan(@RequestParam("id") String id, @RequestParam("status") String status, Principal principal) {
		try {
			int stat = Integer.valueOf(status).intValue();
			RekamMedisHandler h = new RekamMedisHandler();
			RekamMedis rekamMedis = getRekamMedis(Long.valueOf(id));
			h.setNomor(rekamMedis.getNomor());
			h.setTanggal(Converter.dateToStringDashSeparator(rekamMedis.getTanggal()));
			h.setAnamnesa(rekamMedis.getAnamnesa());
			h.setDiagnosa(rekamMedis.getDiagnosa());
			h.setPemeriksaan(rekamMedis.getPemeriksaan());
			
			rekamMedisDetailTempService.hapusBatch(rekamMedis.getNomor());
			
			for(RekamMedisDetail rmd : rekamMedis.getRekamMedisDetail()){
				RekamMedisDetailTemp temp = new RekamMedisDetailTemp();
				temp.setNomor(rekamMedis.getNomor());
				temp.setIdObat(obatService.dapatkanByNama(rmd.getTerapi()).getId()+"");
				temp.setTerapi(rmd.getTerapi());
				temp.setHargaJual(Converter.patternCurrency(rmd.getHargaJual()));
				temp.setJumlah(rmd.getJumlah()+"");
				temp.setHargaTotal(Converter.patternCurrency(rmd.getHargaTotal()));
				temp.setDiskon(rmd.getDiskon()+"");				
				temp.setPajak(rmd.getPajak()+"");
				temp.setTipe(rmd.getTipe());
				temp.setTipePenggunaan(stat);
				temp.setUserInput(principal.getName());
				temp.setWaktuDibuat(new Date());
				temp.setTerakhirDirubah(new Date());
				rekamMedisDetailTempValidator.validate(temp);
				rekamMedisDetailTempService.simpan(temp);
			}
			List<RekamMedisDetailTemp> list = rekamMedisDetailTempService.muatDaftar(rekamMedis.getNomor(), stat);
			String tabel = tabelTerapiGenerator(list);
			
			h.setTabelTerapi(tabel);
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/resep/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public RekamMedisHandler dapatkanResep(@RequestParam("id") String id, Principal principal) {
		try {
			RekamMedisHandler h = new RekamMedisHandler();
			RekamMedis rekamMedis = getRekamMedis(Long.valueOf(id));
			Pasien p = rekamMedis.getPasien();
			h.setPasienId(p.getId().toString());
			h.setPasien(p.getNama());
			if (p.getJenisKelamin() == 0) {
				h.setGender("Perempuan");
			} else if (p.getJenisKelamin() == 1) {
				h.setGender("Laki-laki");
			}
			Instant instant = p.getTanggalLahir().toInstant();
			LocalDate tanggalLahir = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			Long usia = ChronoUnit.YEARS.between(tanggalLahir, LocalDate.now());
			h.setUmur(usia.toString());
			h.setJaminan(p.getJaminanKesehatan());
			h.setNomorJaminan(p.getNomorJaminanKesehatan());
			h.setDokterId(rekamMedis.getDokter().getId().toString());
			List<RekamMedisDetail> list = rekamMedis.getRekamMedisDetail();
			String tabel = tabelResep(list);
			BigDecimal total = new BigDecimal(0);
			for (RekamMedisDetail d : list){
				total = total.add(d.getHargaTotal());
			}
			
			h.setTotalPembelian(Converter.patternCurrency(total));
			h.setTabelTerapi(tabel);
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/rekammedis/baru", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedis baru(@RequestBody RekamMedis rm, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			rm.setTanggal(new Date());
			Pasien p = pasienService.dapatkan(Long.valueOf(rm.getInfo()).longValue());
			rm.setPasien(p);
			rm.setUserInput(principal.getName());
			rm.setWaktuDibuat(new Date());
			rm.setTerakhirDirubah(new Date());
			rm.setInfo("0");			
			rekamMedisService.simpan(rm);			
			rm.setNomor(generateNomorRekamMedis(rm, p));
//			rekamMedisValidator.validate(rm);
			rekamMedisService.simpan(rm);
			logger.info(LogSupport.tambah(principal.getName(), rm.toString(), request));
			return rm;
		} catch (Exception e) {
			logger.info(e.getMessage());
			// logger.info(LogSupport.tambahGagal(principal.getName(), rm,
			// request));
			return null;
		}
	}	
	
	@RequestMapping(value = "/rekammedis/tambah", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedis tambah(@RequestBody RekamMedisHandler h, BindingResult result, Principal principal, HttpServletRequest request) {		
		RekamMedis rm = rekamMedisService.dapatkan(h.getNomor());		
		List<RekamMedisDetail> list = new ArrayList<>();
		try {
			rm = setRekamMedisContent(rm, h);
			
			rm.setIsResepSudahDiproses(false);		
			rm.setIsSudahDisimpan(true);
			
			Pengguna p = penggunaService.temukan(principal.getName());
			rm.setDokter(p.getKaryawan());
			rm.setPasien(pasienService.dapatkan(Long.valueOf(h.getPasien())));
						
			rm.setWaktuDibuat(new Date());		
			rm.setUserInput(principal.getName());	
						
			List<RekamMedisDetailTemp> l = rekamMedisDetailTempService.muatDaftar(h.getNomor(), STAT_REKAMMEDIS_BARU);			
			for (RekamMedisDetailTemp t : l){
				RekamMedisDetail rmd = new RekamMedisDetail();
				rmd = setRekamMedisDetailContent(rmd, t);
				rmd.setRekamMedis(rm);
				rmd.setWaktuDibuat(new Date());
				rmd.setUserInput(principal.getName());
				rekamMedisDetailValidator.validate(rmd);
				list.add(rmd);
			}
			
			rm.setRekamMedisDetail(list);
			rekamMedisValidator.validate(rm);
			rekamMedisService.simpan(rm);
			rekamMedisDetailTempService.hapusBatch(rm.getNomor());
			logger.info(LogSupport.tambah(principal.getName(), rm.toString(), request));
			return rm;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), rm.toString() + "", request));
			rm.setInfo(e.getMessage());
			return rm;
		}
	}
	
	@RequestMapping(value = "/rekammedis/edit", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedis edit(@RequestBody RekamMedisHandler h, BindingResult result, Principal principal, HttpServletRequest request) {		
		RekamMedis rm = getRekamMedis(h.getNomor());		
		List<RekamMedisDetail> listEdit = new ArrayList<>();
		try {						
			rm = setRekamMedisContent(rm, h);
			
			rm.setUserEditor(principal.getName());
			
			rekamMedisDetailService.hapusBatch(rm);
			
			List<RekamMedisDetailTemp> l = rekamMedisDetailTempService.muatDaftar(rm.getNomor(), STAT_REKAMMEDIS_BARU);
			
			for (RekamMedisDetailTemp t : l){
				RekamMedisDetail rmd = new RekamMedisDetail();
				rmd = setRekamMedisDetailContent(rmd, t);
				rmd.setRekamMedis(rm);
				rmd.setWaktuDibuat(new Date());
				rmd.setUserInput(principal.getName());
				rekamMedisDetailValidator.validate(rmd);
				listEdit.add(rmd);
			}
			
			rm.setRekamMedisDetail(listEdit);
			rekamMedisValidator.validate(rm);
			rekamMedisService.simpan(rm);
			rekamMedisDetailTempService.hapusBatch(rm.getNomor());
			logger.info(LogSupport.tambah(principal.getName(), rm.toString(), request));
			return rm;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), rm.toString() + "", request));
			rm.setInfo(e.getMessage());
			return rm;
		}
	}
	
	@RequestMapping(value = "/rekammedis/hapus", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedis hapus(@RequestBody RekamMedis temp, BindingResult result, Principal principal,HttpServletRequest request) {
		RekamMedis rm = rekamMedisService.dapatkan(temp.getId());		
		try {
			rekamMedisService.hapus(rm);
			logger.info(LogSupport.hapus(principal.getName(), temp.getId()+"", request));
			rm.setInfo(rm.getNomor()+" Terhapus");						
			return rm;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), rm.toString(), request));
			rm.setInfo(rm.getNomor()+" Gagal Terhapus : "+e.getMessage());
			return rm;
		}
	}

	@RequestMapping(value = "/rekammedis/daftar-terapi", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement daftarTerapi(@RequestParam(value = "nomor", required = true) String nomor,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();
			List<RekamMedisDetailTemp> list = rekamMedisDetailTempService.muatDaftar(nomor, STAT_REKAMMEDIS_BARU);

			String tabel = tabelTerapiGenerator(list);
			el.setTabelTerapi(tabel);
		
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/rekammedis/tambah-terapi", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedisDetailTemp tambahTerapi(@RequestBody RekamMedisDetailTemp temp, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			RekamMedisDetailTemp t = null;
			RekamMedisDetailTemp tersimpan = rekamMedisDetailTempService.dapatkan(temp.getNomor(), temp.getIdObat()); 
			if (tersimpan == null){
				t = new RekamMedisDetailTemp();
				Obat obat = getObat(Long.valueOf(temp.getIdObat()).longValue());
				t.setNomor(temp.getNomor());
				t.setIdObat(temp.getIdObat());
				t.setTerapi(obat.getNama());				
				t.setDiskon("0");
				t.setPajak("0");				
				t.setJumlah("1");
				t.setTipe(obat.getTipe());
				if (obat.getTipe() == 1) {
					Racikan r = racikanService.dapatkanByNama(obat.getNama());
					t.setHargaJual(Converter.patternCurrency(r.getBiayaRacik().add(r.getHargaJual())));
					t.setHargaTotal(Converter.patternCurrency(r.getBiayaRacik().add(r.getHargaJual())));
				} else {
					t.setHargaJual(Converter.patternCurrency(obat.getDetail().get(0).getHargaJual()));
					t.setHargaTotal(Converter.patternCurrency(obat.getDetail().get(0).getHargaJual()));
				}
				t.setTipePenggunaan(0);
			} else {
				t = tersimpan;
				Integer j = Integer.valueOf(tersimpan.getJumlah()).intValue() + 1;				
				t.setJumlah(j.toString());
				BigDecimal h = new BigDecimal(t.getHargaJual().replaceAll("[.,]", ""));
				BigDecimal total = h.multiply(new BigDecimal(j));
				t.setHargaTotal(Converter.patternCurrency(total));
			}			
			
			t.setUserInput(principal.getName());			
			t.setWaktuDibuat(new Date());
			t.setTerakhirDirubah(new Date());
			rekamMedisDetailTempValidator.validate(t);
			rekamMedisDetailTempService.simpan(t);
			logger.info(LogSupport.tambah(principal.getName(), temp.toString(), request));
			return temp;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), temp.getId() + "", request));
			temp.setInfo(e.getMessage());
			return temp;
		}
	}
	
	@RequestMapping(value = "/rekammedis/hapus-terapi", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedisDetailTemp hapusTerapi(@RequestBody RekamMedisDetailTemp temp, BindingResult result, Principal principal,HttpServletRequest request) {
		RekamMedisDetailTemp t = rekamMedisDetailTempService.dapatkan(temp.getNomor(), temp.getIdObat());		
		try {			
			rekamMedisDetailTempService.hapus(t);
			logger.info(LogSupport.hapus(principal.getName(), temp.getId()+"", request));
			t.setInfo(t.getTerapi()+" Terhapus");						
			return t;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), t.toString(), request));
			t.setInfo(t.getTerapi()+" Gagal Terhapus : "+e.getMessage());
			return t;
		}
	}
	
	
	private String generateNomorRekamMedis(RekamMedis rm, Pasien pasien) {
		String nomor = "RM-";
		nomor += pasien.getId() + "-";
		nomor += generateNomorUrut(rm.getId() + "");
		return nomor;
	}

	private String generateNomorUrut(String nomor) {
		String[] nol = new String[6];
		nol[0] = "";
		nol[1] = "0";
		nol[2] = "00";
		nol[3] = "000";
		nol[4] = "0000";
		nol[5] = "00000";
		nomor = nol[6 - nomor.length()] + nomor;
		return nomor;
	}

	private String tabelGenerator(Page<RekamMedis> list, HttpServletRequest request, String tHead) {
		String html = "";		
		String data = "";
		for (RekamMedis rm : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(rm.getId().toString());
			row += Html.td(rm.getNomor());
			row += Html.td(ringkas(rm.getAnamnesa(), LENGTH_TEXT));
			row += Html.td(ringkas(rm.getPemeriksaan(), LENGTH_TEXT));
			row += Html.td(ringkas(rm.getDiagnosa(), LENGTH_TEXT));		
			row += Html.td(Html.aJs("Detail", "btn btn-primary btn-xs", null, null));
			btn += Html.button("btn btn-primary btn-xs btnEdit", "modal", "#rm-modal", "onClick", "getData(" + rm.getId() + ","+STAT_REKAMMEDIS_BARU+")", 0, "Edit Data");
			btn += Html.button("btn btn-danger btn-xs", "modal", "#rm-modal-hapus", "onClick","setIdUntukHapus(" + rm.getId() +")", 1, "Hapus Data");
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = tHead + tbody;
		return html;
	}
	
	private String tabelResepGenerator(Page<RekamMedis> list, HttpServletRequest request, String tHead) {
		String html = "";		
		String data = "";
		for (RekamMedis rm : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(rm.getId().toString());
			row += Html.td(rm.getNomor());
			row += Html.td(rm.getPasien().getNama());
			row += Html.td(rm.getDokter().getNama());		
			
			if (rm.getIsResepSudahDiproses() == false){
				btn += Html.button("btn btn-primary btn-xs btnEdit", "modal", "#resep-modal", "onClick", "getData(" + rm.getId() + ","+STAT_PENJUALAN_RESEP+")", 3, "Proses Pembayaran");
			}
			btn += Html.button("btn btn-default btn-xs", null, null, "onClick", "cetakResep(" + rm.getId() +")", 4, "Cetak Resep");
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = tHead + tbody;
		return html;
	}

	private String tabelTerapiGenerator(List<RekamMedisDetailTemp> list) {
		String html = "";
		String data = "";
		for (RekamMedisDetailTemp t : list) {
			String row = "";
			String btn = "";
			row += Html.td(t.getTerapi());
			row += Html.td(t.getJumlah() + "");
			row += Html.td(t.getHargaTotal());
			btn += Html.aJs("<i class='fa fa-trash-o'></i>", "btn btn-danger btn-xs", "onClick", "hapusObat(" + t.getIdObat() + ")", "Hapus Data");			
			row += Html.td(btn);
			data += Html.tr(row);
		}
		html = data;		 	
//		System.out.println(html);
		return html;
	}
	
	private String tabelResep(List<RekamMedisDetail> list) {
		String html = "";
		String data = "";
		for (RekamMedisDetail t : list) {
			String row = "";
			row += Html.td(t.getTerapi());
			row += Html.td(t.getJumlah().toString());
			row += Html.td(Converter.patternCurrency(t.getHargaTotal()));								
			data += Html.tr(row);
		}
		html = data;		 	
//		System.out.println(html);
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
	
	private RekamMedis setRekamMedisContent(RekamMedis rm, RekamMedisHandler h){
		rm.setNomor(h.getNomor());
		rm.setAnamnesa(h.getAnamnesa());			
		rm.setDiagnosa(h.getDiagnosa());	
		rm.setPemeriksaan(h.getPemeriksaan());					
		rm.setTanggal(Converter.stringToDate(h.getTanggal()));
		rm.setTerakhirDirubah(new Date());
		return rm;
	}
	
	private RekamMedisDetail setRekamMedisDetailContent(RekamMedisDetail rmd, RekamMedisDetailTemp t){
		rmd.setDiskon(new BigDecimal(t.getDiskon()));
		BigDecimal hargaJual = new BigDecimal(Converter.hilangkanTandaTitikKoma(t.getHargaJual()));
		Integer jumlah = new Integer(t.getJumlah());
		BigDecimal hargaTotal = hargaJual.multiply(new BigDecimal(jumlah));
		rmd.setPajak(new BigDecimal(t.getPajak()));
		rmd.setHargaJual(hargaJual);
		rmd.setJumlah(jumlah);
		rmd.setHargaTotal(hargaTotal);
		rmd.setTipe(t.getTipe());
		rmd.setTerapi(t.getTerapi());
		rmd.setTerakhirDirubah(new Date());		
		return rmd;
	}
	
	private RekamMedis getRekamMedis(Long id) {
		RekamMedis get = rekamMedisService.dapatkan(id);
		
		List<RekamMedisDetail> listDetail = rekamMedisDetailService.dapatkan(get);
		get.setRekamMedisDetail(listDetail);
		Hibernate.initialize(get.getRekamMedisDetail());
		
		return get;
	}
	
	private RekamMedis getRekamMedis(String nomor) {
		RekamMedis get = rekamMedisService.dapatkan(nomor);
		
		List<RekamMedisDetail> listDetail = rekamMedisDetailService.dapatkan(get);
		get.setRekamMedisDetail(listDetail);
		Hibernate.initialize(get.getRekamMedisDetail());
		
		return get;
	}
	
	private RekamMedis inisialisasiRekamMedis(RekamMedis rekamMedis){
//		rekamMedis.setPasien(pasien);
		return rekamMedis;
	}
	
	private String ringkas(String s, int length){
		String text = "";
		if (s.length() > length){
			text += s.substring(0, length);
			text += " ... ";
		} else {
			text = s;
		}
		return text;
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

}
