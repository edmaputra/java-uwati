package id.edmaputra.uwati.controller.laporan;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.transaksi.BatalReturPembelianDetail;
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.entity.transaksi.ReturPembelianDetail;
import id.edmaputra.uwati.service.ApotekService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.transaksi.BatalReturPembelianDetailService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailService;
import id.edmaputra.uwati.service.transaksi.PembelianService;
import id.edmaputra.uwati.service.transaksi.ReturPembelianDetailService;
import id.edmaputra.uwati.specification.ReturPembelianDetailPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.THead;
import id.edmaputra.uwati.view.Table;
import id.edmaputra.uwati.view.handler.PembelianDetailHandler;

@Controller
@RequestMapping("/laporan/retur")
public class LaporanReturPembelianController {

	private static final Logger logger = LoggerFactory.getLogger(LaporanReturPembelianController.class);

	@Autowired
	private ApotekService apotekService;

	@Autowired
	private PembelianDetailService pembelianDetailService;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private PembelianService pembelianService;

	@Autowired
	private BatalReturPembelianDetailService batalService;

	@Autowired
	private ReturPembelianDetailService returPembelianDetailService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPage(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("laporan-retur-pembelian");
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

			ReturPembelianDetailPredicateBuilder builder = new ReturPembelianDetailPredicateBuilder();

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
			Page<ReturPembelianDetail> page = returPembelianDetailService.muatDaftar(halaman, exp);			
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
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private String tabelGenerator(Page<ReturPembelianDetail> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		for (ReturPembelianDetail detail : list.getContent()) {
			String row = "";
			String btn = "";
			row += Html.td(Table.nullCell(detail.getNomorFaktur()));
			row += Html.td(Converter.dateToString(detail.getTanggal()));
			row += Html.td(Table.nullCell(detail.getSupplier()));
			row += Html.td(Table.nullCell(detail.getObat()));
			row += Html.td(Table.nullCell(Converter.patternCurrency(detail.getHargaBeli())));
			row += Html.td(Table.nullCell(detail.getJumlah().toString()));
			row += Html.td(Table.nullCell(Converter.patternCurrency(detail.getHargaTotal())));
			row += Html.td(Converter.dateToString(detail.getTanggalPembelian()));
			row += Html.td(Table.nullCell(detail.getPengguna()));
			btn += Html.td(Html.button("btn btn-danger btn-xs btnEdit", "modal", "#batal-modal", "onClick",
					"setIdUntukHapus(" + detail.getId() + ")", 1, "Batal Retur " + detail.getObat()));

			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_RETUR_PEMBELIAN + tbody;
		return html;
	}

	@RequestMapping(value = "/batal", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public BatalReturPembelianDetail batal(@RequestBody PembelianDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			BatalReturPembelianDetail batal = new BatalReturPembelianDetail();
			ReturPembelianDetail detail = returPembelianDetailService.dapatkan(new Long(h.getId()));
			BeanUtils.copyProperties(detail, batal);
			batal.setInfo(h.getInfo());
			batal.setWaktuPembatalan(new Date());

			batalService.simpan(batal);

			setStatusObatRetur(detail.getObat(), detail.getIdPembelian());
			updateStokObat(batal.getObat(), batal.getJumlah());
			returPembelianDetailService.hapus(detail);

			batal.setInfo("Penjualan Nomor Faktur " + batal.getNomorFaktur() + " Dibatalkan");

			return batal;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private void updateStokObat(String nama, Integer jumlah) {
		Obat obat = obatService.dapatkanByNama(nama);
		List<ObatStok> stokObat = obatStokService.temukanByObats(obat);
		obat.setStok(stokObat);
		Hibernate.initialize(obat.getStok());
		Integer stokBaru = obat.getStok().get(0).getStok() + jumlah;
		System.out.println("Stokbaru "+stokBaru);
		obat.getStok().get(0).setStok(stokBaru);
		obatService.simpan(obat);
	}

	private void setStatusObatRetur(String obat, Long idPembelian) {
		Pembelian pembelian = pembelianService.dapatkan(idPembelian);
		PembelianDetail detail = pembelianDetailService.dapatkan(obat, pembelian);
		detail.setIsReturned(false);
		pembelianDetailService.simpan(detail);
	}
}
