package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.entity.transaksi.PembelianDetailTemp;
import id.edmaputra.uwati.reports.Struk;
import id.edmaputra.uwati.service.ApotekService;
import id.edmaputra.uwati.service.ObatDetailService;
import id.edmaputra.uwati.service.ObatExpiredService;
import id.edmaputra.uwati.service.ObatService;
import id.edmaputra.uwati.service.ObatStokService;
import id.edmaputra.uwati.service.PenggunaService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailTempService;
import id.edmaputra.uwati.service.transaksi.PembelianService;
import id.edmaputra.uwati.specification.ObatPredicateBuilder;
import id.edmaputra.uwati.specification.PembelianDetailTempPredicateBuilder;
import id.edmaputra.uwati.specification.PembelianPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
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
	private PembelianDetailTempService pdtService;
	
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

//	@Transactional
	@RequestMapping(value = "/tambahTemp", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailTemp tambahObat(@RequestBody PembelianDetailTemp pembelianDetailTemp, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			pembelianDetailTemp.setPengguna(principal.getName());
			pembelianDetailTemp.setWaktuDibuat(new Date());
			pembelianDetailTemp.setTerakhirDirubah(new Date());
			pdtService.simpan(pembelianDetailTemp);
			logger.info(LogSupport.tambah(principal.getName(), pembelianDetailTemp.toString(), request));
			return pembelianDetailTemp;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pembelianDetailTemp.toString(), request));
			return null;
		}
	}

	@RequestMapping(value = "/daftarTemp", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarObat(@RequestParam(value = "s", required = false) String supplier,
			@RequestParam(value = "t", required = false) String tanggal,
			@RequestParam(value = "n", required = false) String nomorFaktur, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			
			PembelianDetailTempPredicateBuilder builder = new PembelianDetailTempPredicateBuilder();			
			builder.find(nomorFaktur, principal.getName());
			
			BooleanExpression exp = builder.getExpression();			
			List<PembelianDetailTemp> listTemp = pdtService.dapatkanList(exp);
			
			String tabel = tabelGenerator(listTemp, request);			
			HtmlElement el = new HtmlElement();
			el.setTabel(tabel);
			
			Integer tot = 0;
			for (PembelianDetailTemp te : listTemp){
				Integer subTotal = Integer.valueOf(te.getSubTotal().replaceAll("[.,]", ""));
				tot = tot + subTotal;
			}						
			String total = NumberFormat.getNumberInstance(new Locale("id", "ID")).format(tot);			
			el.setGrandTotal(total);
			// logger.info(LogSupport.);
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println("GET OBAT GAGAL");
			return null;
		}
	}

	@RequestMapping(value = "/hapusTemp", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailTemp hapusObat(@RequestBody PembelianDetailTemp pembelianDetailTemp, BindingResult result,
			Principal principal, HttpServletRequest request) {
		PembelianDetailTemp t = pembelianDetailTemp;
		try {			
			Long index = pembelianDetailTemp.getId();
			pdtService.hapus(index);
			
			return t;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}

//	@Transactional
	@RequestMapping(value = "/beli", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailTemp simpanPembelian(@RequestBody PembelianDetailTemp temp, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pembelian pembelian = new Pembelian();
		try {
			if (!isTersedia(temp.getNomorFaktur(), temp.getTanggal().toString())){
//				throw new Exception("Nomor Faktur Sudah Ada");
				return null;
			}
			String nomorFaktur = temp.getNomorFaktur();
			pembelian.setNomorFaktur(nomorFaktur);
			pembelian.setWaktuTransaksi(Converter.stringToDate(temp.getTanggal()));
			pembelian.setSupplier(temp.getSupplier());
			pembelian.setPengguna(penggunaService.temukan(principal.getName()));
			pembelian.setWaktuDibuat(new Date());
			pembelian.setTerakhirDirubah(new Date());

			BigDecimal grandTotal = new BigDecimal(0);
			PembelianDetailTempPredicateBuilder builder = new PembelianDetailTempPredicateBuilder();
			if (!StringUtils.isBlank(nomorFaktur)) {
				builder.find(nomorFaktur, principal.getName());
			}
			BooleanExpression exp = builder.getExpression();			
			List<PembelianDetailTemp> listTemp = pdtService.dapatkanList(exp);
			
			List<PembelianDetail> listPembelianDetail = new ArrayList<>();
			for (PembelianDetailTemp t : listTemp) {
				PembelianDetail pembelianDetail = new PembelianDetail();
				pembelianDetail = setDetailContent(pembelianDetail, t);
				pembelianDetail.setPembelian(pembelian);
				grandTotal = grandTotal.add(new BigDecimal(t.getSubTotal().replaceAll("[.,]", "")));
				listPembelianDetail.add(pembelianDetail);
				Obat obat = getObat(pembelianDetail.getObat());
				updateObat(obat, pembelianDetail, principal.getName(), request);				
			}
			pembelian.setGrandTotal(grandTotal);
			pembelian.setPembelianDetail(listPembelianDetail);
			
			pembelianService.simpan(pembelian);
			
			for (PembelianDetailTemp t : listTemp) {
				pdtService.hapus(t);
			}
			
			
			temp.setId(pembelian.getId());
			logger.info(LogSupport.tambah(principal.getName(), pembelian.toString(), request));
			return temp;			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pembelian.toString(), request));
			temp.setPesan(e.getMessage());
			return temp;
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

	private String tabelGenerator(List<PembelianDetailTemp> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		for (PembelianDetailTemp pdt : list) {
			String row = "";
			String btn = "";
			row += Html.td(pdt.getObat());
			row += Html.td(pdt.getJumlah());
			row += Html.td(pdt.getTanggalKadaluarsa());
			row += Html.td(pdt.getHargaBeli());
			row += Html.td(pdt.getSubTotal());
			// row += Html.td(pdt.getHargaJual());
			// row += Html.td(pdt.getHargaJualResep());
			btn += Html.button("btn btn-danger btn-xs", null, null, "onClick", "hapus(" + pdt.getId() + ")", 1);
			row += Html.td(btn);
			data += Html.tr(row);			
		}
		html = data;
		return html;
	}

//	@Transactional
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
	
	private Pembelian getPembelian(Long id){
		Pembelian pembelian  = pembelianService.dapatkan(id);
		
		List<PembelianDetail> pembelianDetails = pembelianDetailService.dapatkanByPembelian(pembelian);
		pembelian.setPembelianDetail(pembelianDetails);
		Hibernate.initialize(pembelian.getPembelianDetail());
		
		return pembelian;
	}

}
