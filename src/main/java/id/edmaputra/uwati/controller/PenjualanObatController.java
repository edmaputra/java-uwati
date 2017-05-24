package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import id.edmaputra.uwati.entity.transaksi.NomorFaktur;
import id.edmaputra.uwati.entity.transaksi.PembelianDetailTemp;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailTemp;
import id.edmaputra.uwati.service.ObatDetailService;
import id.edmaputra.uwati.service.ObatExpiredService;
import id.edmaputra.uwati.service.ObatService;
import id.edmaputra.uwati.service.ObatStokService;
import id.edmaputra.uwati.service.RacikanDetailService;
import id.edmaputra.uwati.service.RacikanService;
import id.edmaputra.uwati.service.transaksi.NomorFakturService;
import id.edmaputra.uwati.service.transaksi.PenjualanDetailTempService;
import id.edmaputra.uwati.specification.NomorFakturPredicateBuilder;
import id.edmaputra.uwati.specification.PenjualanDetailTempPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;

@Controller
@RequestMapping("/penjualan-obat")
public class PenjualanObatController {

	private static final Logger logger = LoggerFactory.getLogger(PenjualanObatController.class);

	@Autowired
	private PenjualanDetailTempService pdtService;

	@Autowired
	private NomorFakturService nomorFakturService;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ObatExpiredService obatExpiredService;
	
	@Autowired
	private RacikanService racikanService;
	
	@Autowired
	private RacikanDetailService racikanDetailService;
	
	private final String KODE_TRANSAKSI = "A";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPelanggan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			// listDetailTemp = new ArrayList<>();
			ModelAndView mav = new ModelAndView("penjualan-obat");
			Date tanggalHariIni = new Date();
			mav.addObject("tanggalHariIni", formatter.format(tanggalHariIni));
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/tambahTemp", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailTemp tambahObat(@RequestBody PenjualanDetailTemp pdt, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			pdt.setPengguna(principal.getName());
			pdt.setWaktuDibuat(new Date());
			pdt.setTerakhirDirubah(new Date());
			String nomorFaktur = pdt.getNomorFaktur();
			if (nomorFaktur == null || StringUtils.isBlank(nomorFaktur)) {
				String nF = generateNomorFaktur(pdt.getTanggal());				
				pdt.setNomorFaktur(nF);
				NomorFaktur newNF = new NomorFaktur();				
				newNF.setNomor(setNomorUrutFaktur(nF));
				newNF.setIsDone(false);
				newNF.setTanggal(Converter.stringToDate(pdt.getTanggal()));
				newNF.setWaktuDibuat(new Date());
				newNF.setTerakhirDirubah(new Date());				
				nomorFakturService.simpan(newNF);
			}
			pdtService.simpan(pdt);
			updateStokObat(pdt.getObat(), pdt.getJumlah(), 0);
			logger.info(LogSupport.tambah(principal.getName(), pdt.toString(), request));
			return pdt;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pdt.toString(), request));
			System.out.println(e);
			return null;
		}
	}
	
	@RequestMapping(value = "/racikanTemp", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailTemp tambahRacikanTemp(@RequestBody PenjualanDetailTemp pdt, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			
			pdt.setPengguna(principal.getName());			
			pdt.setWaktuDibuat(new Date());			
			pdt.setTerakhirDirubah(new Date());			
			String nomorFaktur = pdt.getNomorFaktur();			
			if (nomorFaktur == null || StringUtils.isBlank(nomorFaktur)) {
				String nF = generateNomorFaktur(pdt.getTanggal());				
				pdt.setNomorFaktur(nF);				
				NomorFaktur newNF = new NomorFaktur();				
				newNF.setNomor(setNomorUrutFaktur(nF));				
				newNF.setIsDone(false);				
				newNF.setTanggal(Converter.stringToDate(pdt.getTanggal()));				
				newNF.setWaktuDibuat(new Date());				
				newNF.setTerakhirDirubah(new Date());				
				nomorFakturService.simpan(newNF);				
			}		
			pdtService.simpan(pdt);			
//			updateStokObat(pdt.getObat(), pdt.getJumlah(), 0);			
			logger.info(LogSupport.tambah(principal.getName(), pdt.toString(), request));
			return pdt;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pdt.toString(), request));
			System.out.println(e);
			return null;
		}
	}

	@RequestMapping(value = "/daftarTemp", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarObat(@RequestParam(value = "t", required = false) String tanggal,
			@RequestParam(value = "n", required = false) String nomorFaktur, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			PenjualanDetailTempPredicateBuilder builder = new PenjualanDetailTempPredicateBuilder();
			builder.find(nomorFaktur, principal.getName());
			BooleanExpression exp = builder.getExpression();
			List<PenjualanDetailTemp> listTemp = pdtService.dapatkanList(exp);
			String tabel = tabelGenerator(listTemp, request);
			HtmlElement el = new HtmlElement();
			el.setTabel(tabel);
			Integer tot = 0;
			for (PenjualanDetailTemp te : listTemp) {
				Integer subTotal = Integer.valueOf(te.getSubTotal().replaceAll("[.,]", ""));
				tot = tot + subTotal;
			}
			String total = NumberFormat.getNumberInstance(new Locale("id", "ID")).format(tot);
			el.setGrandTotal(total);
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println(e);
			return null;
		}
	}

	@RequestMapping(value = "/hapusTemp", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailTemp hapusObat(@RequestBody PenjualanDetailTemp pdt, BindingResult result,
			Principal principal, HttpServletRequest request) {
		PenjualanDetailTemp t = pdt;
		try {			
			PenjualanDetailTemp data = pdtService.dapatkan(pdt.getId(), pdt.getNomorFaktur());
			updateStokObat(data.getObat(), data.getJumlah(), 1);			
			pdtService.hapus(data);
			return t;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/tambahRacikanTemp", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailTemp tambahRacikan(@RequestBody PenjualanDetailTemp pdt, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			pdt.setPengguna(principal.getName());
			pdt.setWaktuDibuat(new Date());
			pdt.setTerakhirDirubah(new Date());
			String nomorFaktur = pdt.getNomorFaktur();
			if (nomorFaktur == null || StringUtils.isBlank(nomorFaktur)) {
				String nF = generateNomorFaktur(pdt.getTanggal());				
				pdt.setNomorFaktur(nF);
				NomorFaktur newNF = new NomorFaktur();				
				newNF.setNomor(setNomorUrutFaktur(nF));
				newNF.setIsDone(false);
				newNF.setTanggal(Converter.stringToDate(pdt.getTanggal()));
				newNF.setWaktuDibuat(new Date());
				newNF.setTerakhirDirubah(new Date());				
				nomorFakturService.simpan(newNF);
			}
			pdtService.simpan(pdt);
			updateStokObat(pdt.getObat(), pdt.getJumlah(), 0);
			logger.info(LogSupport.tambah(principal.getName(), pdt.toString(), request));
			return pdt;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pdt.toString(), request));
			System.out.println(e);
			return null;
		}
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
//			penambahan stok
			else if (operasi == 1){
				stokBaru = stokLama + Integer.valueOf(jumlah).intValue();
			}
			o.getStok().get(0).setStok(stokBaru);
			obatService.simpan(o);
		} else if (o.getTipe() == 1){
			
		}
	}

	private String generateNomorFaktur(String tanggal) {
		String nomorFaktur = "";
		Date date = Converter.stringToDate(tanggal);
		List<NomorFaktur> list = dapatkanNomorFaktur(date);
		if (!list.isEmpty()) {
			NomorFaktur n = list.get(list.size() - 1);
			nomorFaktur = setUrutanNomorFaktur(date, n.getNomor());
		} else {
			nomorFaktur = setUrutanNomorFaktur(date, 0);
		}
		return nomorFaktur;
	}

	private List<NomorFaktur> dapatkanNomorFaktur(Date tanggal) {
		NomorFakturPredicateBuilder builder = new NomorFakturPredicateBuilder();
		if (tanggal != null) {
			builder.tanggal(tanggal);
		}

		BooleanExpression exp = builder.getExpression();
		Page<NomorFaktur> page = nomorFakturService.muatDaftar(1, exp);

		return page.getContent();
	}

	private String setUrutanNomorFaktur(Date tanggal, Integer nomorTerakhir) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(tanggal);
		int tahun = cal.get(Calendar.YEAR);
		int bulan = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		bulan = bulan + 1;
		String b = angkaNolBulan(bulan + "");

		nomorTerakhir++;
		String nomor = nomorTerakhir + "";

		nomor = generateNomorUrut(nomor);
		return KODE_TRANSAKSI+""+tahun + "" + b + "" + day + "" + nomor;

	}

	private String angkaNolBulan(String nomor) {
		String[] nol = new String[2];
		nol[0] = "";
		nol[1] = "0";
		nomor = nol[2 - nomor.length()] + nomor;
		return nomor;
	}

	private String generateNomorUrut(String nomor) {
		String[] nol = new String[4];
		nol[0] = "";
		nol[1] = "0";
		nol[2] = "00";
		nol[3] = "000";
		nomor = nol[4 - nomor.length()] + nomor;
		return nomor;
	}
	
	private Integer setNomorUrutFaktur(String nomorFaktur){
		String nomor = nomorFaktur.substring(nomorFaktur.length() - 4, nomorFaktur.length());
		Integer returnValue = Integer.valueOf(nomor).intValue();
		return returnValue;
	}
	
	private String cekStokObatFromRacikan(PembelianDetailTemp pdt){
		String kondisi = "OK";
		Racikan r = getRacikan(pdt.getObat());
		for (RacikanDetail rd : r.getRacikanDetail()){
			String temp = "";
			Obat obat = getObat(rd.getKomposisi().getNama());
			Integer jumlahBeli = Integer.valueOf(pdt.getJumlah()) * rd.getJumlah();
			Integer stok = obat.getStok().get(0).getStok();
			if (jumlahBeli > stok){
				kondisi = ""; 
			} 
		}
		return kondisi;
	}
	
	private Racikan getRacikan(String nama) {
		Racikan racikan = racikanService.dapatkanByNama(nama);
		
		List<RacikanDetail> listRacikanDetail = racikanDetailService.dapatkanByRacikan(racikan);
		racikan.setRacikanDetail(listRacikanDetail);
		Hibernate.initialize(racikan.getRacikanDetail());
		
		return racikan;
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

	private String tabelGenerator(List<PenjualanDetailTemp> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		for (PenjualanDetailTemp pdt : list) {
			String row = "";
			String btn = "";
			row += Html.td(pdt.getObat());
			row += Html.td(pdt.getJumlah());
			row += Html.td(pdt.getHargaJual());
			row += Html.td(pdt.getPajak());
			row += Html.td(pdt.getDiskon());
			row += Html.td(pdt.getSubTotal());
			btn += Html.button("btn btn-danger btn-xs", null, null, "onClick", "hapus(" + pdt.getId() + ")", 1);
			row += Html.td(btn);
			data += Html.tr(row);
		}
		html = data;
		return html;
	}
}
