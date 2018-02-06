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
import id.edmaputra.uwati.entity.transaksi.BatalPembelian;
import id.edmaputra.uwati.entity.transaksi.BatalPembelianDetail;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.BatalPenjualanDetailRacikan;
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailRacikan;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.transaksi.BatalPembelianService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailService;
import id.edmaputra.uwati.service.transaksi.PembelianService;
import id.edmaputra.uwati.specification.PembelianPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.THead;
import id.edmaputra.uwati.view.Table;
import id.edmaputra.uwati.view.handler.PembelianDetailHandler;

@Controller
@RequestMapping("/laporan/pembelian")
public class LaporanPembelianController {

	private static final Logger logger = LoggerFactory.getLogger(LaporanPembelianController.class);

	@Autowired
	private PembelianService pembelianService;

	@Autowired
	private PembelianDetailService pembelianDetailService;

	@Autowired
	private BatalPembelianService batalPembelianService;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPelanggan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("laporan-pembelian");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement daftar(@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "tanggalAwal", defaultValue = "", required = false) String tanggalAwal,
			@RequestParam(value = "tanggalAkhir", defaultValue = "", required = false) String tanggalAkhir,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

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

			BooleanExpression exp = builder.getExpression();
			Page<Pembelian> page = pembelianService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()) {
				int current = page.getNumber() + 1;
				int next = current + 1;
				int prev = current - 1;
				int first = Math.max(1, current - 5);
				int last = Math.min(first + 10, page.getTotalPages());

				String h = Html.navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(),
						tanggalAwal, tanggalAkhir, cari);
				el.setNavigasiHalaman(h);
			}

			List<Pembelian> list = pembelianService.dapatkanList(exp);
			BigDecimal rekap = BigDecimal.ZERO;
			for (Pembelian p : list) {
				rekap = rekap.add(p.getGrandTotal());
			}

			el.setGrandTotal(Formatter.patternCurrency(rekap));

			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/dapatkan-rekap", method = RequestMethod.GET)
	@ResponseBody
	public PembelianDetailHandler dapatkanRekap(
			@RequestParam(value = "tipe", defaultValue = "1", required = false) Integer tipe,
			@RequestParam("tanggalAwal") String tanggalAwal, @RequestParam("tanggalAkhir") String tanggalAkhir) {
		try {
			PembelianDetailHandler ph = new PembelianDetailHandler();
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

			BooleanExpression exp = builder.getExpression();
			List<Pembelian> list = pembelianService.dapatkanList(exp);
			for (Pembelian p : list) {
				p.setPembelianDetail(pembelianDetailService.dapatkanByPembelian(p));
			}

			List<PembelianDetail> d = new ArrayList<>();
			List<String> obat = new ArrayList<>();

			for (Pembelian p : list) {
				for (PembelianDetail detail : p.getPembelianDetail()) {
					detail.setPembelian(p);
					d.add(detail);
					obat.add(detail.getObat());
				}
			}

			Set<String> hs = new HashSet<>();
			hs.addAll(obat);
			obat.clear();
			obat.addAll(hs);

			ph.setJumlah(String.valueOf(obat.size()));

			ph.setDetails(tabelDetails(d, 1));

			return ph;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private String tabelGenerator(Page<Pembelian> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		for (Pembelian p : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(Converter.dateToString(p.getWaktuTransaksi()));
			row += Html.td(Table.nullCell(p.getSupplier()));
			row += Html.td(Table.nullCell(p.getNomorFaktur()));
			row += Html.td(Formatter.patternCurrency(p.getGrandTotal()));
			btn += Html.button("btn btn-primary btn-xs btnEdit", "modal", "#penjualan-modal", "onClick",
					"getData(" + p.getId() + ")", 0, "Detail Penjualan " + p.getNomorFaktur());
			btn += Html.td(Html.button("btn btn-danger btn-xs btnEdit", "modal", "#batal-modal", "onClick",
					"setIdUntukHapus(" + p.getId() + ")", 1, "Batal Pembelian " + p.getNomorFaktur()));

			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_PEMBELIAN + tbody;
		return html;
	}

	@RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public PembelianDetailHandler dapatkan(@RequestParam("id") String id) {
		try {
			PembelianDetailHandler ph = new PembelianDetailHandler();
			Pembelian get = pembelianService.dapatkan(new Long(id));
			List<PembelianDetail> details = pembelianDetailService.dapatkanByPembelian(get);
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
	public BatalPembelian batal(@RequestBody PembelianDetailHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			BatalPembelian batal = new BatalPembelian();
			List<BatalPembelianDetail> listBatalDetail = new ArrayList<>();

			Pembelian get = pembelianService.dapatkan(new Long(h.getId()));
			List<PembelianDetail> details = pembelianDetailService.dapatkanByPembelian(get);
			batal = setBatalContent(get, batal);

			for (PembelianDetail d : details) {
				BatalPembelianDetail bd = new BatalPembelianDetail();
				bd.setBatalPembelian(batal);
				bd.setDiskon(d.getDiskon());
				bd.setHargaBeli(d.getHargaBeli());
				bd.setHargaJual(d.getHargaJual());
				bd.setHargaJualResep(d.getHargaJualResep());
				bd.setInfo(d.getInfo());
				bd.setIsReturned(d.getIsReturned());
				bd.setJumlah(d.getJumlah());
				bd.setObat(d.getObat());
				bd.setPajak(d.getPajak());
				bd.setSubTotal(d.getSubTotal());
				bd.setTanggalKadaluarsa(d.getTanggalKadaluarsa());
				bd.setTerakhirDirubah(new Date());
				bd.setWaktuDibuat(new Date());
				listBatalDetail.add(bd);
			}
			batal.setBatalPembelianDetail(listBatalDetail);
			batal.setInfo(h.getInfo());
			batal.setWaktuPembatalan(new Date());
			batalPembelianService.simpan(batal);
			for (BatalPembelianDetail batalDetail : listBatalDetail) {
				Obat obat = getObat(batalDetail.getObat());
				if (obat.getTipe() == 0) {
					updateStokObat(obat, batalDetail.getJumlah(), 0);
				}
			}
			pembelianService.hapus(get);

			batal.setInfo("Pembelian Nomor Faktur " + batal.getNomorFaktur() + " Dibatalkan");

			return batal;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private BatalPembelian setBatalContent(Pembelian get, BatalPembelian batal) {
		batal.setDeadline(get.getDeadline());
		batal.setDiskon(get.getDiskon());
		batal.setGrandTotal(get.getGrandTotal());
		batal.setNomorFaktur(get.getNomorFaktur());
		batal.setPajak(get.getPajak());
		batal.setPengguna(get.getPengguna().getNama());
		batal.setSupplier(get.getSupplier());
		batal.setTotal(get.getTotal());
		batal.setWaktuTransaksi(get.getWaktuTransaksi());
		batal.setWaktuDibuat(new Date());
		batal.setTerakhirDirubah(new Date());
		return batal;
	}

	private PembelianDetailHandler setContent(Pembelian p, PembelianDetailHandler ph) {
		ph.setNomorFaktur(p.getNomorFaktur());
		ph.setDistributor(p.getSupplier());
		ph.setTanggal(Converter.dateToStringDashSeparator(p.getWaktuTransaksi()));
		ph.setTotalPembelian(Formatter.patternCurrency(p.getTotal()));
		ph.setDiskon(Formatter.patternCurrency(p.getDiskon()));
		ph.setPajak(Formatter.patternCurrency(p.getPajak()));
		ph.setTotalPembelianFinal(Formatter.patternCurrency(p.getGrandTotal()));
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
			row += Html.td(Formatter.patternCurrency(d.getHargaJual()));
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		if (n == 1) {
			html = THead.THEAD_PEMBELIAN_DETAIL_TANGGAL + tbody;
		} else {
			html = THead.THEAD_PEMBELIAN_DETAIL + tbody;
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
		}
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
