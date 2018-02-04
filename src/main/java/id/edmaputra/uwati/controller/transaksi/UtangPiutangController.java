package id.edmaputra.uwati.controller.transaksi;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import id.edmaputra.uwati.entity.transaksi.BayarPembelian;
import id.edmaputra.uwati.entity.transaksi.BayarPenjualan;
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.service.transaksi.BayarPembelianService;
import id.edmaputra.uwati.service.transaksi.BayarPenjualanService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailService;
import id.edmaputra.uwati.service.transaksi.PembelianService;
import id.edmaputra.uwati.service.transaksi.PenjualanDetailService;
import id.edmaputra.uwati.service.transaksi.PenjualanService;
import id.edmaputra.uwati.specification.PembelianPredicateBuilder;
import id.edmaputra.uwati.specification.PenjualanPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.THead;
import id.edmaputra.uwati.view.Table;
import id.edmaputra.uwati.view.handler.PembelianDetailHandler;

@Controller
@RequestMapping("/utang-piutang")
public class UtangPiutangController {

	private static final Logger logger = LoggerFactory.getLogger(UtangPiutangController.class);

	@Autowired
	private BayarPembelianService bayarPembelianService;
	
	@Autowired
	private BayarPenjualanService bayarPenjualanService;

	@Autowired
	private PembelianService pembelianService;
	
	@Autowired
	private PenjualanDetailService penjualanDetailService;

	@Autowired
	private PembelianDetailService pembelianDetailService;

	@Autowired
	private PenjualanService penjualanService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPageUtangPiutang(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("utang-piutang-daftar");
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
			if (tipe == 0) {
				PembelianPredicateBuilder builder = new PembelianPredicateBuilder();
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

				if (!StringUtils.isBlank(cari)) {
					builder.cari(cari);
				}

				builder.lunas(false);

				BooleanExpression exp = builder.getExpression();
				Page<Pembelian> page = pembelianService.muatDaftar(halaman, exp);

				String tabel = pembelianTabelGenerator(page, request);
				el.setTabel(tabel);

				if (page.hasContent()) {
					int current = page.getNumber() + 1;
					int next = current + 1;
					int prev = current - 1;
					int first = Math.max(1, current - 5);
					int last = Math.min(first + 10, page.getTotalPages());

					String h = Html.navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(),
							tipe, tanggalAwal, tanggalAkhir, cari);
					el.setNavigasiHalaman(h);
				}

			} else if (tipe == 1) {
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

				if (!StringUtils.isBlank(cari)) {
					builder.cari(cari);
				}
				
				builder.lunas(false);

				BooleanExpression exp = builder.getExpression();
				Page<Penjualan> page = penjualanService.muatDaftar(halaman, exp);

				String tabel = penjualanTabelGenerator(page, request);
				el.setTabel(tabel);

				if (page.hasContent()) {
					int current = page.getNumber() + 1;
					int next = current + 1;
					int prev = current - 1;
					int first = Math.max(1, current - 5);
					int last = Math.min(first + 10, page.getTotalPages());

					String h = Html.navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(),
							tipe, tanggalAwal, tanggalAkhir, cari);
					el.setNavigasiHalaman(h);
				}

			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public PembelianDetailHandler dapatkan(@RequestParam("id") String id, @RequestParam("tipe") int tipe) {
		try {
			PembelianDetailHandler ph = new PembelianDetailHandler();
			if (tipe == 0) {
				Pembelian get = pembelianService.dapatkan(new Long(id));
				List<PembelianDetail> details = pembelianDetailService.dapatkanByPembelian(get);
				List<BayarPembelian> bayarPembelians = bayarPembelianService.dapatkanByPembelian(get);
				ph = setContent(get, ph);
				ph.setDetails(tabelDetails(details, 0));
				ph.setBayarDetails(tabelBayarDetails(bayarPembelians, 0));
			} else if (tipe == 1) {
				Penjualan get = penjualanService.dapatkan(new Long(id));
				List<PenjualanDetail> details = penjualanDetailService.dapatkanByPenjualan(get);
				List<BayarPenjualan> bayarPenjualan = bayarPenjualanService.dapatkanByPenjualan(get);
				ph = setContent(get, ph);
				ph.setDetails(tabelPenjualanDetails(details, 0));
				ph.setBayarDetails(tabelBayarPenjualanDetails(bayarPenjualan, 0));
			}			
			return ph;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/bayar", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailHandler simpanPembelian(@RequestBody PembelianDetailHandler h, BindingResult result, Principal principal, HttpServletRequest request) {		
		try {
			if (h.getTipe() == 0) {
				Pembelian pembelian = pembelianService.dapatkan(new Long(h.getId()));

				BigDecimal bayar = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getBayar()));
				
				List<BayarPembelian> bayarPembelians = bayarPembelianService.dapatkanByPembelian(pembelian);
				BayarPembelian bayarPembelian = new BayarPembelian();
				bayarPembelian.setPembelian(pembelian);
				bayarPembelian.setWaktuTransaksi(new Date());
				bayarPembelian.setJumlahBayar(bayar);
				bayarPembelians.add(bayarPembelian);
				
				BigDecimal grandTotal = pembelian.getGrandTotal();
				BigDecimal jumlahTerbayar = getTotalTerbayar(bayarPembelians);
				
				System.out.println("Grand Total : "+grandTotal+" == Jumlah Bayar :"+jumlahTerbayar);
				
				if (grandTotal.compareTo(jumlahTerbayar) > 0) {
					pembelian.setLunas(false);
				} else if (grandTotal.compareTo(jumlahTerbayar) <= 0) {
					pembelian.setLunas(true);
				}			
				pembelian.setBayarPembelian(bayarPembelians);			
				pembelianService.simpan(pembelian);
				
				h.setId(pembelian.getId().toString());
				logger.info(LogSupport.tambah(principal.getName(), pembelian.toString(), request));
			} else if (h.getTipe() == 1) {
				Penjualan penjualan = penjualanService.dapatkan(new Long(h.getId()));

				BigDecimal bayar = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getBayar()));
				
				List<BayarPenjualan> bayarPenjualans = bayarPenjualanService.dapatkanByPenjualan(penjualan);
				BayarPenjualan bayarPenjualan = new BayarPenjualan();
				bayarPenjualan.setPenjualan(penjualan);
				bayarPenjualan.setWaktuTransaksi(new Date());
				bayarPenjualan.setJumlahBayar(bayar);
				bayarPenjualans.add(bayarPenjualan);
				
				BigDecimal grandTotal = penjualan.getGrandTotal();
				BigDecimal jumlahTerbayar = getTotalTerbayarPenjualan(bayarPenjualans);
				
				System.out.println("Grand Total : "+grandTotal+" == Jumlah Bayar :"+jumlahTerbayar);
				if (grandTotal.compareTo(jumlahTerbayar) > 0) {
					penjualan.setLunas(false);
				} else if (grandTotal.compareTo(jumlahTerbayar) <= 0) {
					penjualan.setLunas(true);
				}			
				penjualan.setPembayaran(bayarPenjualans);			
				penjualanService.simpan(penjualan);
				
				h.setId(penjualan.getId().toString());
				logger.info(LogSupport.tambah(principal.getName(), penjualan.toString(), request));
			}			
			return h;			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), null, request));
			h.setInfo(e.getMessage());
			return h;
		}
	}

	private String penjualanTabelGenerator(Page<Penjualan> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		for (Penjualan p : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(Converter.dateToString(p.getWaktuTransaksi()));
			row += Html.td(Table.nullCell(p.getNomorFaktur()));
			row += Html.td(Table.nullCell(p.getPelanggan()));
			row += Html.td(Formatter.patternCurrency(p.getGrandTotal()));
			row += Html.td(Table.nullCell(getSisaPiutang(p)));
			btn += Html.button("btn btn-primary btn-xs", "modal", "#utang-piutang-modal", "onClick",
					"getData(" + p.getId() + ")", 6, "Bayar Piutang " + p.getNomorFaktur());
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_PIUTANG + tbody;
		return html;
	}

	private String pembelianTabelGenerator(Page<Pembelian> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		for (Pembelian p : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(Converter.dateToString(p.getWaktuTransaksi()));
			row += Html.td(Table.nullCell(p.getNomorFaktur()));
			row += Html.td(Table.nullCell(p.getSupplier()));
			row += Html.td(Table.nullCell(p.getGrandTotalString()));
			row += Html.td(Table.nullCell(getSisaUtang(p)));
			btn += Html.button("btn btn-primary btn-xs btn-utang", "modal", "#utang-piutang-modal", "onClick",
					"getData(" + p.getId() + ")", 6, "Bayar Utang " + p.getNomorFaktur());
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_UTANG + tbody;
		return html;
	}

	private String getSisaPiutang(Penjualan p) {
		List<BayarPenjualan> bayarPembelians = bayarPenjualanService.dapatkanByPenjualan(p);
		BigDecimal totalDibayar = new BigDecimal(0);
		for (BayarPenjualan bayar : bayarPembelians) {
			totalDibayar = totalDibayar.add(bayar.getJumlahBayar());
		}
		BigDecimal piutang = p.getGrandTotal().subtract(totalDibayar);
		return Converter.patternCurrency(piutang);
	}
	
	private String getSisaUtang(Pembelian p) {
		List<BayarPembelian> bayarPembelians = bayarPembelianService.dapatkanByPembelian(p);
		BigDecimal totalDibayar = new BigDecimal(0);
		for (BayarPembelian bayar : bayarPembelians) {
			totalDibayar = totalDibayar.add(bayar.getJumlahBayar());
		}
		BigDecimal utang = p.getGrandTotal().subtract(totalDibayar);
		return Converter.patternCurrency(utang);
	}
	
	private String getSisaUtang(Penjualan p) {
		List<BayarPenjualan> bayarPenjualans = bayarPenjualanService.dapatkanByPenjualan(p);
		BigDecimal totalDibayar = new BigDecimal(0);
		for (BayarPenjualan bayar : bayarPenjualans) {
			totalDibayar = totalDibayar.add(bayar.getJumlahBayar());
		}
		BigDecimal utang = p.getGrandTotal().subtract(totalDibayar);
		return Converter.patternCurrency(utang);
	}
	
	private BigDecimal getTotalTerbayar(List<BayarPembelian> bayarPembelians) {		 
		BigDecimal totalDibayar = new BigDecimal(0);
		for (BayarPembelian bayar : bayarPembelians) {
			totalDibayar = totalDibayar.add(bayar.getJumlahBayar());
		}
		return totalDibayar;
	}
	
	private BigDecimal getTotalTerbayarPenjualan(List<BayarPenjualan> bayarPenjualans) {		 
		BigDecimal totalDibayar = new BigDecimal(0);
		for (BayarPenjualan bayar : bayarPenjualans) {
			totalDibayar = totalDibayar.add(bayar.getJumlahBayar());
		}
		return totalDibayar;
	}

	private PembelianDetailHandler setContent(Pembelian p, PembelianDetailHandler ph) {
		ph.setNomorFaktur(p.getNomorFaktur());		
		ph.setTanggal(Converter.dateToStringDashSeparator(p.getWaktuTransaksi()));
		ph.setTotalPembelianFinal(Formatter.patternCurrency(p.getGrandTotal()));
		ph.setBayar(getSisaUtang(p));
		ph.setSubTotal(Formatter.patternCurrency(p.getGrandTotal().subtract(new BigDecimal(Converter.hilangkanTandaTitikKoma(ph.getBayar())))));
		ph.setTipe(0);
		return ph;
	}
	
	private PembelianDetailHandler setContent(Penjualan p, PembelianDetailHandler ph) {
		ph.setNomorFaktur(p.getNomorFaktur());		
		ph.setTanggal(Converter.dateToStringDashSeparator(p.getWaktuTransaksi()));
		ph.setTotalPembelianFinal(Formatter.patternCurrency(p.getGrandTotal()));
		ph.setBayar(getSisaUtang(p));
		ph.setSubTotal(Formatter.patternCurrency(p.getGrandTotal().subtract(new BigDecimal(Converter.hilangkanTandaTitikKoma(ph.getBayar())))));
		ph.setTipe(1);
		return ph;
	}

	private String tabelDetails(List<PembelianDetail> pembelianDetails, Integer n) {
		String html = "";
		String data = "";
		for (PembelianDetail d : pembelianDetails) {
			String row = "";
			row += Html.td(d.getObat());
			if (n == 1) {
				row += Html.td(Converter.dateToString(d.getPembelian().getWaktuTransaksi()));
			}
			row += Html.td(Formatter.patternCurrency(d.getHargaBeli()));
			row += Html.td(Formatter.patternCurrency(d.getJumlah()));
			row += Html.td(Formatter.patternCurrency(d.getSubTotal()));
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_PEMBELIAN_DETAIL_ONUTANG + tbody;
		return html;
	}
	
	private String tabelPenjualanDetails(List<PenjualanDetail> penjualanDetails, Integer n) {
		String html = "";
		String data = "";
		for (PenjualanDetail d : penjualanDetails) {
			String row = "";
			row += Html.td(d.getObat());
			row += Html.td(Formatter.patternCurrency(d.getHargaJual()));
			row += Html.td(Formatter.patternCurrency(d.getJumlah()));
			row += Html.td(Formatter.patternCurrency(d.getHargaTotal()));
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_PENJUALAN_DETAIL_ONUTANG + tbody;
		return html;
	}

	private String tabelBayarDetails(List<BayarPembelian> pembayaran, Integer n) {
		String html = "";
		String data = "";
		for (BayarPembelian d : pembayaran) {
			String row = "";
			row += Html.td(Table.nullCell(Converter.dateToString(d.getWaktuTransaksi())));
			row += Html.td(Formatter.patternCurrency(d.getJumlahBayar()));
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_BAYAR_PEMBELIAN_DETAIL + tbody;
		return html;
	}
	
	private String tabelBayarPenjualanDetails(List<BayarPenjualan> pembayaran, Integer n) {
		String html = "";
		String data = "";
		for (BayarPenjualan d : pembayaran) {
			String row = "";
			row += Html.td(Table.nullCell(Converter.dateToString(d.getWaktuTransaksi())));
			row += Html.td(Formatter.patternCurrency(d.getJumlahBayar()));
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_BAYAR_PEMBELIAN_DETAIL + tbody;
		return html;
	}

}
