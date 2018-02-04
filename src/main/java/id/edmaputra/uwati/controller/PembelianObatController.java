package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.master.Apotek;
import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.transaksi.BayarPembelian;
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.entity.transaksi.PembelianDetailTemp;
import id.edmaputra.uwati.reports.Struk;
import id.edmaputra.uwati.service.ApotekService;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.pengguna.PenggunaService;
import id.edmaputra.uwati.service.transaksi.BayarPembelianService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailTempService;
import id.edmaputra.uwati.service.transaksi.PembelianService;
import id.edmaputra.uwati.specification.ObatPredicateBuilder;
import id.edmaputra.uwati.specification.PembelianPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.handler.PembelianDetailHandler;
import id.edmaputra.uwati.view.json.JsonReturn;
import id.edmaputra.uwati.view.json.Suggestion;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/pembelian-obat")
public class PembelianObatController {

	private static final Logger logger = LoggerFactory.getLogger(PembelianObatController.class);

	@Autowired
	private PembelianService pembelianService;

	@Autowired
	private PembelianDetailService pembelianDetailService;
	
	@Autowired
	private PembelianDetailTempService pembelianDetailTempService;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	@Autowired
	private PenggunaService penggunaService;
	
	@Autowired
	private ApotekService apotekService;
	
	@Autowired
	private BayarPembelianService bayarPembelianService;
	
	@Autowired 
	@Qualifier("strukPembelianPdf")
	private JasperReportsPdfView strukPembelian;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPelanggan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			ModelAndView mav = new ModelAndView("pembelian-obat");
			Date tanggalHariIni = new Date();
			mav.addObject("tanggalHariIni", formatter.format(tanggalHariIni));			
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/cariobat", method = RequestMethod.GET)
	@ResponseBody
	public Suggestion dapatkanObatByNama(@RequestParam("query") String nama) {
		try {
			ObatPredicateBuilder builder = new ObatPredicateBuilder();
			if (!StringUtils.isBlank(nama)) {
				builder.nama(nama);
			}

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
	
	@RequestMapping(value = "/tambah-obat", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailHandler tambahTerapi(@RequestBody PembelianDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			PembelianDetailTemp t = null;
			PembelianDetailTemp tersimpan = pembelianDetailTempService.dapatkanByRandomIdAndIdObat(h.getRandomId(), h.getIdObat());
			if (tersimpan == null) {
				t = new PembelianDetailTemp();
				Obat obat = getObat(h.getIdObat());
				t.setRandomId(h.getRandomId());
				t.setObat(obat.getNama());
				t.setIdObat(obat.getId());
				t.setDiskon("0");
				t.setJumlah("1");
				t.setTanggalKadaluarsa(Converter.dateToStringDashSeparator(obat.getExpired().get(0).getTanggalExpired()));
				t.setHargaBeli(Converter.patternCurrency(obat.getDetail().get(0).getHargaBeli()));
				t.setSubTotal(Converter.patternCurrency(obat.getDetail().get(0).getHargaBeli()));
				t.setHargaJual(Converter.patternCurrency(obat.getDetail().get(0).getHargaJual()));
				t.setHargaJualResep(Converter.patternCurrency(obat.getDetail().get(0).getHargaJualResep()));
				t.setPajak("0");
			} else {
				t = tersimpan;
				Integer j = Integer.valueOf(tersimpan.getJumlah()).intValue() + 1;
				BigDecimal diskon = new BigDecimal(tersimpan.getDiskon());
				t.setJumlah(j.toString());
				BigDecimal harga = new BigDecimal(t.getHargaBeli().replaceAll("[.,]", ""));
				BigDecimal total = harga.multiply(new BigDecimal(j));
				total = total.subtract(diskon);
				t.setSubTotal(Converter.patternCurrency(total));
			}
			
			// updateStokObat(t.getObat(), "1", 0);
			t.setWaktuDibuat(new Date());
			t.setTerakhirDirubah(new Date());
			pembelianDetailTempService.simpan(t);
			logger.info(LogSupport.tambah(principal.getName(), t.getIdObat().toString(), request));
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getIdObat() + "", request));
			h.setInfo(e.getMessage());
			return h;
		}
	}
	
	@RequestMapping(value = "/list-obat", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement daftarObatTemp(@RequestParam(value = "randomId", required = true) String randomId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			List<PembelianDetailTemp> list = pembelianDetailTempService.dapatkanListByRandomId(randomId);

			String tabel = tabelTerapiGenerator(list);
			el.setTabelTerapi(tabel);
			el.setValue1(Converter.patternCurrency(totalHarga(list)));
			el.setValue2(Converter.patternCurrency(new BigDecimal(totalItems(list))));
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/dapatkan-obat", method = RequestMethod.GET)
	@ResponseBody
	public PembelianDetailHandler dapatkanObat(@RequestParam("idObat") String idObat,
			@RequestParam("randomId") String randomId, Principal principal) {
		PembelianDetailHandler h = new PembelianDetailHandler();
		Long id = new Long(idObat);
		h.setIdObat(id);
		h.setRandomId(randomId);
		try {
			PembelianDetailTemp tersimpan = pembelianDetailTempService.dapatkanByRandomIdAndIdObat(randomId, id);
			if (tersimpan != null) {
				h.setObat(tersimpan.getObat());
				h.setJumlah(tersimpan.getJumlah());
				h.setHargaJual(tersimpan.getHargaJual());
				h.setHargaBeli(tersimpan.getHargaBeli());
				h.setDiskon(tersimpan.getDiskon());
				h.setSubTotal(tersimpan.getSubTotal());
				h.setTanggalExpired(tersimpan.getTanggalKadaluarsa());
			}
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			h.setInfo(e.getMessage());
			return h;
		}
	}
	
	@RequestMapping(value = "/hapus-obat", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailHandler hapusObat(@RequestBody PembelianDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		PembelianDetailTemp tersimpan = pembelianDetailTempService.dapatkanByRandomIdAndIdObat(h.getRandomId(),
				h.getIdObat());
		try {
			// updateStokObat(tersimpan.getObat(), tersimpan.getJumlah(), 1);
			pembelianDetailTempService.hapus(tersimpan);
			logger.info(LogSupport.hapus(principal.getName(), tersimpan.getId() + "", request));
			h.setInfo(tersimpan.getObat() + " Terhapus");
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), tersimpan.toString(), request));
			h.setInfo(tersimpan.getObat() + " Gagal Terhapus : " + e.getMessage());
			return h;
		}
	}
	
	@RequestMapping(value = "/edit-obat", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailHandler editTerapi(@RequestBody PembelianDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			PembelianDetailTemp tersimpan = pembelianDetailTempService.dapatkanByRandomIdAndIdObat(h.getRandomId(),h.getIdObat());			
			if (tersimpan != null) {
				Integer jumlah = new Integer(Converter.hilangkanTandaTitikKoma(h.getJumlah()));				
				BigDecimal hargaBeli = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getHargaBeli()));				
				BigDecimal hargaJualResep = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getHargaJual()));
				BigDecimal hargaJual = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getHargaJual()));				
				BigDecimal diskon = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getDiskon()));				
				BigDecimal hargaTotal = hargaBeli.multiply(new BigDecimal(jumlah));
				hargaTotal = hargaTotal.subtract(diskon);
				tersimpan.setJumlah(h.getJumlah());
				tersimpan.setHargaJual(Converter.patternCurrency(hargaJual));
				tersimpan.setHargaJualResep(Converter.patternCurrency(hargaJualResep));
				tersimpan.setHargaBeli(Converter.patternCurrency(hargaBeli));
				tersimpan.setSubTotal(Converter.patternCurrency(hargaTotal));
				tersimpan.setDiskon(Converter.patternCurrency(diskon));
				tersimpan.setTanggalKadaluarsa(h.getTanggalExpired());
				tersimpan.setTerakhirDirubah(new Date());
				pembelianDetailTempService.simpan(tersimpan);
			}
			logger.info(LogSupport.edit(principal.getName(), h.getIdObat().toString(), request));
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(principal.getName(), h.getIdObat() + "", request));
			h.setInfo(e.getMessage());
			return h;
		}
	}

	@RequestMapping(value = "/beli", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailHandler simpanPembelian(@RequestBody PembelianDetailHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pembelian pembelian = new Pembelian();
		try {
			if (!isTersedia(h.getNomorFaktur(), h.getTanggal().toString())){
//				throw new Exception("Nomor Faktur Sudah Ada");
				return null;
			}
			String nomorFaktur = h.getNomorFaktur();
			pembelian.setNomorFaktur(nomorFaktur);
			pembelian.setWaktuTransaksi(Converter.stringToDate(h.getTanggal()));
			pembelian.setSupplier(h.getDistributor());
			pembelian.setPengguna(penggunaService.temukan(principal.getName()));
			pembelian.setDiskon(new BigDecimal(Formatter.hilangkanTitik(h.getDiskon())));
			pembelian.setPajak(new BigDecimal(Formatter.hilangkanTitik(h.getPajak())));
			pembelian.setTotal(new BigDecimal(Formatter.hilangkanTitik(h.getTotalPembelian())));
			pembelian.setGrandTotal(new BigDecimal(Formatter.hilangkanTitik(h.getTotalPembelianFinal())));
			
			pembelian.setWaktuDibuat(new Date());
			pembelian.setTerakhirDirubah(new Date());
			
			List<PembelianDetail> listPembelianDetail = new ArrayList<>();
			
			List<PembelianDetailTemp> list = pembelianDetailTempService.dapatkanListByRandomId(h.getRandomId());
			for (PembelianDetailTemp t : list) {
				PembelianDetail pembelianDetail = new PembelianDetail();
				pembelianDetail = setDetailContent(pembelianDetail, t);
				pembelianDetail.setPembelian(pembelian);
				listPembelianDetail.add(pembelianDetail);
				Obat obat = getObat(pembelianDetail.getObat());
				updateObat(obat, pembelianDetail, principal.getName(), request);				
			}
			
			BigDecimal grandTotal = pembelian.getGrandTotal();
			BigDecimal jumlahDibayar = new BigDecimal(Formatter.hilangkanTitik(h.getBayar()));
			
			List<BayarPembelian> bayarPembelians = new ArrayList<>();
			BayarPembelian bayarPembelian = new BayarPembelian();
			bayarPembelian.setPembelian(pembelian);
			bayarPembelian.setWaktuTransaksi(Converter.stringToDate(h.getTanggal()));
			bayarPembelian.setJumlahBayar(jumlahDibayar);
			bayarPembelians.add(bayarPembelian);
			if (grandTotal.compareTo(jumlahDibayar) > 0) {
				pembelian.setLunas(false);
			} else if (grandTotal.compareTo(jumlahDibayar) <= 0) {
				pembelian.setLunas(true);
			}			
			pembelian.setBayarPembelian(bayarPembelians);
			pembelian.setPembelianDetail(listPembelianDetail);			
			pembelianService.simpan(pembelian);
			pembelianDetailTempService.hapusBatch(h.getRandomId());
			
			h.setId(pembelian.getId().toString());
			logger.info(LogSupport.tambah(principal.getName(), pembelian.toString(), request));
			return h;			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pembelian.toString(), request));
			h.setInfo(e.getMessage());
			return h;
		}
	}
	
	@RequestMapping(value = "/tersedia")
	@ResponseBody
	public String tersedia(@RequestParam("nomorFaktur") String nomorFaktur, @RequestParam("tahun") String tahun) {		
		return isTersedia(nomorFaktur, tahun).toString();		
	}
	
	private Boolean isTersedia(String nomorFaktur, String tahun){
		PembelianPredicateBuilder builder = new PembelianPredicateBuilder();
		if (!StringUtils.isBlank(nomorFaktur)) {
			builder.nomorFaktur(nomorFaktur);
		}
		tahun = tahun.substring(tahun.length() - 4, tahun.length());
		if (!StringUtils.isBlank(tahun)) {
			builder.tahun(tahun);
		}
		
		BooleanExpression exp = builder.getExpression();
		Boolean tersedia = pembelianService.dapatkan(exp) == null;
		
		return tersedia;
	}
	
	@RequestMapping(value = "/cetak", method = RequestMethod.POST)
	public ModelAndView buatResiDanCetak(ModelAndView mav, @RequestParam("id") String id, Principal principal){
		try {
			List<Struk> struks = new ArrayList<>();
			Map<String, Object> parameterMap = new HashMap<String, Object>();						
			Apotek apotek = apotekService.semua().get(0);			
			BigDecimal grandTotal = new BigDecimal(0);
			
			Pembelian pembelian = getPembelian(Long.valueOf(id).longValue());
			for (PembelianDetail d : pembelian.getPembelianDetail()){
				Struk struk = new Struk();
				struk.setStrukNamaApotek(apotek.getNama());
				struk.setStrukAlamatApotek(apotek.getAlamat()+"\n"+apotek.getTelepon());	
				struk.setStrukTanggal(Converter.dateToString(pembelian.getWaktuTransaksi()));
				struk.setStrukNomorFaktur(pembelian.getNomorFaktur());
				struk.setStrukOperator(principal.getName());
				
//				BigDecimal pengurang = d.getDiskon().add(d.getPajak());
//				BigDecimal temp = d.getHargaJual().multiply(new BigDecimal(d.getJumlah()));
//				BigDecimal netto = temp.subtract(pengurang);
				
				struk.setStrukNamaObat(d.getObat());
				struk.setStrukJumlahObat(d.getJumlah().toString() + " x");
				struk.setStrukHargaObat(Converter.patternCurrency(d.getHargaBeli()));
				BigDecimal subTotal = d.getSubTotal();
				struk.setStrukSubTotalObat(Converter.patternCurrency(subTotal));				
				grandTotal = grandTotal.add(subTotal);
				struk.setStrukGrandTotal(Converter.patternCurrency(grandTotal));
				struks.add(struk);
			}
			
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(struks);
			
			parameterMap.put("datasource", JRdataSource);
			
			mav = new ModelAndView(strukPembelian, parameterMap);
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}		
	}

	private PembelianDetail setDetailContent(PembelianDetail pembelianDetail, PembelianDetailTemp temp) {
		if (temp.getDiskon() != null) {
			pembelianDetail.setDiskon(new BigDecimal(temp.getDiskon().replaceAll("[.,]", "")));
		}
		if (temp.getHargaBeli() != null) {
			pembelianDetail.setHargaBeli(new BigDecimal(temp.getHargaBeli().replaceAll("[.,]", "")));
		}
		if (temp.getHargaJual() != null) {
			pembelianDetail.setHargaJual(new BigDecimal(temp.getHargaJual().replaceAll("[.,]", "")));
		}
		if (temp.getHargaJualResep() != null) {
			pembelianDetail.setHargaJualResep(new BigDecimal(temp.getHargaJualResep().replaceAll("[.,]", "")));
		}
		if (temp.getJumlah() != null) {
			pembelianDetail.setJumlah(Integer.valueOf(temp.getJumlah()));
		}
		if (temp.getObat() != null) {
			pembelianDetail.setObat(getObat(temp.getObat()).getNama());
		}
		if (temp.getPajak() != null) {
			pembelianDetail.setPajak(new BigDecimal(temp.getPajak().replaceAll("[.,]", "")));
		}
		if (temp.getSubTotal() != null) {
			pembelianDetail.setSubTotal(new BigDecimal(temp.getSubTotal().replaceAll("[.,]", "")));
		}
		if (temp.getTanggalKadaluarsa() != null) {
			pembelianDetail.setTanggalKadaluarsa(Converter.stringToDate(temp.getTanggalKadaluarsa()));
		}
		pembelianDetail.setTerakhirDirubah(new Date());		
		pembelianDetail.setWaktuDibuat(new Date());
		return pembelianDetail;
	}
	
	private void updateObat(Obat obat, PembelianDetail detail, String user, HttpServletRequest request){		
		try {			
			obat.getDetail().get(0).setHargaBeli(detail.getHargaBeli());
			obat.getDetail().get(0).setHargaJual(detail.getHargaJual());
			obat.getDetail().get(0).setHargaJualResep(detail.getHargaJualResep());
			Integer stokLama = obat.getStok().get(0).getStok();
			Integer stokBaru = stokLama + detail.getJumlah();
			obat.getStok().get(0).setStok(stokBaru);
			obat.getExpired().get(0).setTanggalExpired(detail.getTanggalKadaluarsa());
			obatService.simpan(obat);
			logger.info(LogSupport.edit(user, obat.toString(), request));
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(user, obat.toString(), request));
		}
	}

//	@Transactional
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
	
	private Pembelian getPembelian(Long id){
		Pembelian pembelian  = pembelianService.dapatkan(id);
		
		List<PembelianDetail> pembelianDetails = pembelianDetailService.dapatkanByPembelian(pembelian);
		pembelian.setPembelianDetail(pembelianDetails);
		Hibernate.initialize(pembelian.getPembelianDetail());
		
		return pembelian;
	}
	
	private BigDecimal totalHarga(List<PembelianDetailTemp> list) {
		BigDecimal t = BigDecimal.ZERO;
		for (PembelianDetailTemp temp : list) {
			Integer hargaTotal = Integer.valueOf(temp.getSubTotal().replaceAll("[.,]", ""));
			t = t.add(new BigDecimal(hargaTotal));
		}
		return t;
	}

	private Integer totalItems(List<PembelianDetailTemp> list) {
		Integer t = 0;
		for (PembelianDetailTemp temp : list) {
			Integer totalItems = Integer.valueOf(temp.getJumlah().replaceAll("[.,]", ""));
			t = t + totalItems;
		}
		return t;
	}
	
	private String tabelTerapiGenerator(List<PembelianDetailTemp> list) {
		String html = "";
		String data = "";
		for (PembelianDetailTemp t : list) {
			String row = "";
			String btn = "";
			row += Html.td(t.getObat());
			row += Html.td(t.getJumlah().toString());
			row += Html.td(t.getSubTotal());
			btn += Html.aJs("<i class='fa fa-pencil'></i>", "btn btn-primary btn-xs", "onClick",
					"editObat(" + t.getIdObat() + ")", "Edit Data", "modal", "#edit-obat-modal");
			btn += Html.aJs("<i class='fa fa-trash-o'></i>", "btn btn-danger btn-xs", "onClick",
					"hapusObat(" + t.getIdObat() + ")", "Hapus Data");
			row += Html.td(btn);
			data += Html.tr(row);
		}
		html = data;
		// System.out.println(html);
		return html;
	}

}
