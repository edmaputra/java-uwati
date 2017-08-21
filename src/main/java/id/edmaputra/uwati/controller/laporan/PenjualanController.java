package id.edmaputra.uwati.controller.laporan;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
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

@Controller
@RequestMapping("/laporan/penjualan")
public class PenjualanController {

	private static final Logger logger = LoggerFactory.getLogger(PenjualanController.class);

	@Autowired
	private PenjualanService penjualanService;

	@Autowired
	private PenjualanDetailService penjualanDetailService;

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
			btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#penjualan-modal", "onClick",
					"getData(" + p.getId() + ")", 0, "Detail Penjualan " + p.getNomorFaktur());
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
}
