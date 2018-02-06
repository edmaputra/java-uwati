package id.edmaputra.uwati.controller.laporan;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualan;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetailRacikan;
import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailRacikan;
import id.edmaputra.uwati.reports.RPenjualan;
import id.edmaputra.uwati.reports.RPenjualanDetail;
import id.edmaputra.uwati.reports.Struk;
import id.edmaputra.uwati.service.ApotekService;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.obat.RacikanDetailService;
import id.edmaputra.uwati.service.obat.RacikanService;
import id.edmaputra.uwati.service.transaksi.BatalPenjualanService;
import id.edmaputra.uwati.service.transaksi.PenjualanDetailService;
import id.edmaputra.uwati.service.transaksi.PenjualanService;
import id.edmaputra.uwati.specification.PenjualanPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.THead;
import id.edmaputra.uwati.view.Table;
import id.edmaputra.uwati.view.handler.PenjualanHandler;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/laporan/penjualan")
public class LaporanPenjualanController {

	private static final Logger logger = LoggerFactory.getLogger(LaporanPenjualanController.class);

	@Autowired
	private ApotekService apotekService;
	
	@Autowired
	private PenjualanService penjualanService;

	@Autowired
	private PenjualanDetailService penjualanDetailService;
	
	@Autowired
	private BatalPenjualanService batalPenjualanService;
	
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
	
	@Autowired
	@Qualifier("strukPenjualanPdf")
	private JasperReportsPdfView strukPenjualan;
	
//	@Autowired 
//	@Qualifier("laporanPenjualanPdf")
//	private JasperReportsPdfView pdfPenjualan;
	
	private List<Penjualan> penjualans;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPelanggan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("laporan-penjualan");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement daftar(@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "tipe", defaultValue = "1", required = false) Integer tipe,
			@RequestParam(value = "tanggalAwal", defaultValue = "", required = false) String tanggalAwal,
			@RequestParam(value = "tanggalAkhir", defaultValue = "", required = false) String tanggalAkhir,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			PenjualanPredicateBuilder builder = new PenjualanPredicateBuilder();

			if (StringUtils.isNotBlank(tanggalAwal) || StringUtils.isNotBlank(tanggalAkhir)) {
				Date awal = Converter.stringToDate(tanggalAwal);
				if (StringUtils.isBlank(tanggalAkhir)) {
					builder.tanggal(awal, awal);
				} else if (StringUtils.isNotBlank(tanggalAkhir)) {
					Date akhir = Converter.stringToDate(tanggalAkhir);
					if (awal.compareTo(akhir) > 0) {
						builder.tanggal(akhir, awal);
					} else if (awal.compareTo(akhir) < 0) {
						builder.tanggal(awal, akhir);
					}
				}
			}

			if (tipe != -1) {
				builder.tipe(tipe);
			}

			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Penjualan> page = penjualanService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()) {
				int current = page.getNumber() + 1;
				int next = current + 1;
				int prev = current - 1;
				int first = Math.max(1, current - 5);
				int last = Math.min(first + 10, page.getTotalPages());

				String h = Html.navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(), tipe,
						tanggalAwal, tanggalAkhir, cari);
				el.setNavigasiHalaman(h);
			}

			List<Penjualan> list = penjualanService.dapatkanList(exp);
			penjualans = new ArrayList<>();
			penjualans = list;
			BigDecimal rekap = BigDecimal.ZERO;
			for (Penjualan p : list) {
				rekap = rekap.add(p.getGrandTotal());
			}

			el.setGrandTotal(Formatter.patternCurrency(rekap));

			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/excel-all", method = RequestMethod.GET)	
	public ModelAndView excelAll() {
		try {
			List<Penjualan> penjualans = penjualanService.dapatkanSemua();
			return new ModelAndView("penjualanExcel","penjualanList", penjualans);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.GET)	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response) {
		try {
			for (int i = 0; i < this.penjualans.size(); i++) {
				List<PenjualanDetail> penjualanDetails = penjualanDetailService.dapatkanByPenjualan(penjualans.get(i));
				penjualans.get(i).setPenjualanDetail(penjualanDetails);
				this.penjualans.set(i, penjualans.get(i));
			}
			return new ModelAndView(new LaporanPenjualanExcelReportView(),"penjualans", this.penjualans);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private String tabelGenerator(Page<Penjualan> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		for (Penjualan p : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(Converter.dateToString(p.getWaktuTransaksi()));
			row += Html.td(Table.nullCell(p.getNomorFaktur()));
			row += Html.td(Table.nullCell(p.getPelanggan()));
			row += Html.td(Table.nullCell(p.getPengguna()));
			row += Html.td(Table.nullCell(p.getDokter()));
			row += Html.td(Formatter.patternCurrency(p.getGrandTotal()));			
			btn += Html.button("btn btn-primary btn-xs btnEdit", "modal", "#penjualan-modal", "onClick",
					"getData(" + p.getId() + ")", 0, "Detail Penjualan " + p.getNomorFaktur());
			btn += Html.button("btn btn-default btn-xs", null, null, "onClick",
					"setIdUntukCetakResi(" + p.getId() + ")", 4, "Cetak Resi Penjualan " + p.getNomorFaktur());
			btn += Html.td(Html.button("btn btn-danger btn-xs btnEdit", "modal", "#batal-modal", "onClick",
					"setIdUntukHapus(" + p.getId() + ")", 1, "Batal Penjualan " + p.getNomorFaktur()));
			// btn += Html.button("btn btn-danger btn-xs", "modal",
			// "#penjualan-modal-hapus", "onClick", "setIdUntukHapus(" +
			// p.getId() + ")", 1);

			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_PENJUALAN + tbody;
		return html;
	}

	@RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public PenjualanHandler dapatkan(@RequestParam("id") String id) {
		try {
			PenjualanHandler ph = new PenjualanHandler();
			Penjualan get = penjualanService.dapatkan(new Long(id));
			List<PenjualanDetail> details = penjualanDetailService.dapatkanByPenjualan(get);

			ph = setContent(get, ph);

			ph.setDetails(tabelDetails(details, 0));

			return ph;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/batal", method = RequestMethod.POST)
	@ResponseBody
	public BatalPenjualan batal(@RequestBody PenjualanHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			BatalPenjualan batal = new BatalPenjualan();
			List<BatalPenjualanDetail> listBatalDetail = new ArrayList<>();
			List<BatalPenjualanDetailRacikan> listBatalDetailRacikan = new ArrayList<>();
			
			Penjualan get = penjualanService.dapatkan(h.getId());
			List<PenjualanDetail> details = penjualanDetailService.dapatkanByPenjualan(get);
						
			batal = setBatalContent(get, batal);
			
			for (PenjualanDetail d : details){
				BatalPenjualanDetail bd = new BatalPenjualanDetail();
				bd.setDiskon(d.getDiskon());
				bd.setHargaJual(d.getHargaJual());
				bd.setHargaTotal(d.getHargaTotal());
				bd.setJumlah(d.getJumlah());
				bd.setObat(d.getObat());
				bd.setPajak(d.getPajak());
				bd.setInfo(d.getInfo());
				bd.setBatalPenjualan(batal);
				bd.setTerakhirDirubah(new Date());
				bd.setWaktuDibuat(new Date());				
				if (!d.getIsRacikan()){
					bd.setIsRacikan(false);
				} else {
					bd.setIsRacikan(true);
					for (PenjualanDetailRacikan r : d.getRacikanDetail()){
						BatalPenjualanDetailRacikan dr = new BatalPenjualanDetailRacikan();
						dr.setBatalPenjualanDetail(bd);
						dr.setHargaJualPerKomposisi(r.getHargaJualPerKomposisi());
						dr.setInfo(r.getInfo());
						dr.setJumlah(r.getJumlah());
						dr.setKomposisi(r.getKomposisi());
						dr.setTerakhirDirubah(new Date());
						dr.setWaktuDibuat(new Date());
						listBatalDetailRacikan.add(dr);
					}
					bd.setBatalPenjualanDetailRacikan(listBatalDetailRacikan);
				}				
				listBatalDetail.add(bd);
				
			}			
			batal.setBatalPenjualanDetail(listBatalDetail);
			batal.setInfo(h.getInfo());
			batal.setWaktuPembatalan(new Date());
			batalPenjualanService.simpan(batal);
			for (BatalPenjualanDetail batalDetail : listBatalDetail){
				Obat obat = getObat(batalDetail.getObat());
				if (obat.getTipe() == 0 || obat.getTipe() == 1) {
					updateStokObat(obat, batalDetail.getJumlah(), 1);
				}
			}						
			penjualanService.hapus(get);
			
			batal.setInfo("Penjualan Nomor Faktur "+batal.getNomorFaktur()+" Dibatalkan");

			return batal;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/resi", method = RequestMethod.POST)
	public ModelAndView buatResiDanCetakPenjualan(@RequestParam("id") String id, ModelAndView mav,
			Principal principal) {
		try {
			List<Struk> struks = new ArrayList<>();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			Apotek apotek = apotekService.semua().get(0);
			BigDecimal grandTotal = new BigDecimal(0);
			Penjualan penjualan = penjualanService.dapatkan(new Long(id));
			List<PenjualanDetail> list = penjualanDetailService.dapatkanByPenjualan(penjualan);

			for (PenjualanDetail d : list) {
				Struk struk = new Struk();
				struk.setStrukNamaApotek(apotek.getNama());
				struk.setStrukAlamatApotek(apotek.getAlamat() + "\n" + apotek.getTelepon());
				struk.setStrukTanggal(Converter.dateToString(penjualan.getWaktuTransaksi()));
				struk.setStrukNomorFaktur(penjualan.getNomorFaktur());
				struk.setStrukOperator(penjualan.getPengguna());
				struk.setStrukPelanggan(penjualan.getPelanggan());

				struk.setStrukNamaObat(d.getObat());
				struk.setStrukJumlahObat(d.getJumlah().toString() + " x");
				struk.setStrukHargaObat(Converter.patternCurrency(d.getHargaJual()));
				BigDecimal subTotal = d.getHargaTotal();
				struk.setStrukSubTotalObat(Converter.patternCurrency(subTotal));
				grandTotal = grandTotal.add(subTotal);

				BigDecimal total = penjualan.getGrandTotal().add(penjualan.getDiskon());
				total = total.subtract(penjualan.getPajak());

				struk.setStrukTotal(Converter.patternCurrency(total));

				BigDecimal p = penjualan.getPajak();
				BigDecimal di = penjualan.getDiskon();
				BigDecimal t = grandTotal.add(p);
				t = t.subtract(di);

				struk.setStrukPajak(Converter.patternCurrency(p));
				struk.setStrukDiskon(Converter.patternCurrency(di));

				struk.setStrukGrandTotal(Converter.patternCurrency(t));
				struk.setStrukBayar(Converter.patternCurrency(penjualan.getBayar()));
				struk.setStrukKembali(Converter.patternCurrency(penjualan.getKembali()));

				struks.add(struk);
			}

			JRDataSource JRdataSource = new JRBeanCollectionDataSource(struks);			

			parameterMap.put("datasource", JRdataSource);

			mav = new ModelAndView(strukPenjualan, parameterMap);
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}
	

	private BatalPenjualan setBatalContent(Penjualan get, BatalPenjualan batal) {
		batal.setBayar(get.getBayar());
		batal.setDiskon(get.getDiskon());
		batal.setDokter(get.getDokter());
		batal.setGrandTotal(get.getGrandTotal());
		batal.setKembali(get.getKembali());
		batal.setNomorFaktur(get.getNomorFaktur());
		batal.setNomorResep(get.getNomorResep());
		batal.setPajak(get.getPajak());
		batal.setPelanggan(get.getPelanggan());
		batal.setPengguna(get.getPengguna());
		batal.setTipe(get.getTipe());
		batal.setTotalPembelian(get.getTotalPembelian());
		batal.setWaktuTransaksi(get.getWaktuTransaksi());
		batal.setWaktuDibuat(new Date());
		batal.setTerakhirDirubah(new Date());
		return batal;
	}

	@RequestMapping(value = "/dapatkan-rekap", method = RequestMethod.GET)
	@ResponseBody
	public PenjualanHandler dapatkanRekap(
			@RequestParam(value = "tipe", defaultValue = "1", required = false) Integer tipe,
			@RequestParam("tanggalAwal") String tanggalAwal, 
			@RequestParam("tanggalAkhir") String tanggalAkhir) {
		try {
			PenjualanHandler ph = new PenjualanHandler();
			PenjualanPredicateBuilder builder = new PenjualanPredicateBuilder();

			if (StringUtils.isNotBlank(tanggalAwal) || StringUtils.isNotBlank(tanggalAkhir)) {
				Date awal = Converter.stringToDate(tanggalAwal);
				if (StringUtils.isBlank(tanggalAkhir)) {
					builder.tanggal(awal, awal);
				} else if (StringUtils.isNotBlank(tanggalAkhir)) {
					Date akhir = Converter.stringToDate(tanggalAkhir);
					if (awal.compareTo(akhir) > 0) {
						builder.tanggal(akhir, awal);
					} else if (awal.compareTo(akhir) < 0) {
						builder.tanggal(awal, akhir);
					} else if (awal.compareTo(akhir) == 0){
						builder.tanggal(awal, awal);
					}
				}
			}

			if (tipe != -1) {
				builder.tipe(tipe);
			}

			BooleanExpression exp = builder.getExpression();
			List<Penjualan> list = penjualanService.dapatkanList(exp);
			for (Penjualan p : list) {
				p.setPenjualanDetail(penjualanDetailService.dapatkanByPenjualan(p));
			}

			List<PenjualanDetail> d = new ArrayList<>();
			List<String> obat = new ArrayList<>();

			for (Penjualan p : list) {
				for (PenjualanDetail detail : p.getPenjualanDetail()) {
					detail.setPenjualan(p);
					d.add(detail);
					obat.add(detail.getObat());
				}
			}

			Set<String> hs = new HashSet<>();
			hs.addAll(obat);
			obat.clear();
			obat.addAll(hs);

			ph.setJumlah(obat.size());

			ph.setDetails(tabelDetails(d, 1));

			return ph;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
//	@RequestMapping(value = "/pdf", method = RequestMethod.POST)
//	public ModelAndView pdf(ModelAndView mav, @RequestParam("id") String id, Principal principal){
//	public ModelAndView pdf(ModelAndView mav, Principal principal){
//		try {
//			
//			String tanggalAwal = "01-07-2017";
//			String tanggalAkhir = "02-07-2017";
//			
//			PenjualanPredicateBuilder builder = new PenjualanPredicateBuilder();
//
//			if (StringUtils.isNotBlank(tanggalAwal) || StringUtils.isNotBlank(tanggalAkhir)) {
//				Date awal = Converter.stringToDate(tanggalAwal);
//				if (StringUtils.isBlank(tanggalAkhir)) {
//					builder.tanggal(awal, awal);
//				} else if (StringUtils.isNotBlank(tanggalAkhir)) {
//					Date akhir = Converter.stringToDate(tanggalAkhir);
//					if (awal.compareTo(akhir) > 0) {
//						builder.tanggal(akhir, awal);
//					} else if (awal.compareTo(akhir) < 0) {
//						builder.tanggal(awal, akhir);
//					} else if (awal.compareTo(akhir) == 0){
//						builder.tanggal(awal, awal);
//					}
//				}
//			}
//			
//			BooleanExpression exp = builder.getExpression();
//			List<Penjualan> lists = penjualanService.dapatkanList(exp);
//			List<RPenjualan> laporan = new ArrayList<>();
//			for (Penjualan p : lists){
//				RPenjualan rp = new RPenjualan();
//				rp.setNomorFaktur(p.getNomorFaktur());
//				rp.setWaktuTransaksi(Converter.dateToString(p.getWaktuTransaksi()));				
//				List<RPenjualanDetail> listRpd = new ArrayList<>();				
//				List<PenjualanDetail> details = penjualanDetailService.dapatkanByPenjualan(p);
//				for (PenjualanDetail d : details){
//					RPenjualanDetail rpd = new RPenjualanDetail();
//					rpd.setObat(d.getObat());
//					rpd.setHargaJual(d.getHargaJual().toString());
//					listRpd.add(rpd);
//				}
//				rp.setDetails(listRpd);
//				laporan.add(rp);
//			}
//								
//			Map<String, Object> parameterMap = new HashMap<String, Object>();
//			JRDataSource JRdataSource = new JRBeanCollectionDataSource(laporan);			
//			parameterMap.put("datasource", JRdataSource);			
//			mav = new ModelAndView(pdfPenjualan, parameterMap);			
//			return mav;
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			System.out.println(e.getMessage());
//			return null;
//		}		
//	}

	private PenjualanHandler setContent(Penjualan p, PenjualanHandler ph) {
		ph.setNomorFaktur(p.getNomorFaktur());
		ph.setPengguna(p.getPengguna());
		ph.setDokter(p.getDokter());
		ph.setPelanggan(p.getPelanggan());
		ph.setWaktuTransaksi(p.getWaktuTransaksi());
		ph.setTotalPembelian(Formatter.patternCurrency(p.getTotalPembelian()));
		ph.setDiskon(Formatter.patternCurrency(p.getDiskon()));
		ph.setPajak(Formatter.patternCurrency(p.getPajak()));
		ph.setGrandTotal(Formatter.patternCurrency(p.getGrandTotal()));
		ph.setBayar(Formatter.patternCurrency(p.getBayar()));
		ph.setKembali(Formatter.patternCurrency(p.getKembali()));
		return ph;
	}

	private String tabelDetails(List<PenjualanDetail> penjualanDetails, Integer n) {
		String html = "";
		String data = "";
		for (PenjualanDetail d : penjualanDetails) {
			String row = "";
			row += Html.td(d.getObat());
			if (n == 1) {
				row += Html.td(Converter.dateToString(d.getPenjualan().getWaktuTransaksi()));
			}
			row += Html.td(Formatter.patternCurrency(d.getHargaJual()));
			row += Html.td(Formatter.patternCurrency(d.getJumlah()));
			row += Html.td(Formatter.patternCurrency(d.getHargaTotal()));
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		if (n == 1) {
			html = THead.THEAD_PENJUALAN_DETAIL_TANGGAL + tbody;
		} else {
			html = THead.THEAD_PENJUALAN_DETAIL + tbody;
		}
		return html;
	}
	
	private void updateStokObat(Obat o, Integer jumlah, int operasi) {
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
			Racikan r = getRacikan(o.getNama());
			for (RacikanDetail rd : r.getRacikanDetail()) {
				Obat racikanDetail = getObat(rd.getKomposisi().getNama());
				Integer jumlahBeli = Integer.valueOf(jumlah) * rd.getJumlah();
				Integer stokLama = racikanDetail.getStok().get(0).getStok();
				Integer stokBaru = null;
				// pengurangan stok
				if (operasi == 0) {
					stokBaru = stokLama - jumlahBeli;
				}
				// penambahan stok
				else if (operasi == 1) {
					stokBaru = stokLama + jumlahBeli;
				}
				racikanDetail.getStok().get(0).setStok(stokBaru);
				obatService.simpan(racikanDetail);
			}
		}
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
