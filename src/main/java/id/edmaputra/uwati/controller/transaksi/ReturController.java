package id.edmaputra.uwati.controller.transaksi;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.entity.transaksi.ReturPembelian;
import id.edmaputra.uwati.entity.transaksi.ReturPembelianDetail;
import id.edmaputra.uwati.entity.transaksi.ReturPembelianDetailTemp;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.pengguna.PenggunaService;
import id.edmaputra.uwati.service.transaksi.PembelianDetailService;
import id.edmaputra.uwati.service.transaksi.PembelianService;
import id.edmaputra.uwati.service.transaksi.ReturPembelianDetailTempService;
import id.edmaputra.uwati.service.transaksi.ReturPembelianService;
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
@RequestMapping("/retur")
public class ReturController {

	private static final Logger logger = LoggerFactory.getLogger(ReturController.class);
	
	@Autowired
	private ReturPembelianService returPembelianService;
	
	@Autowired
	private PembelianService pembelianService;

	@Autowired
	private PembelianDetailService pembelianDetailService;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatStokService obatStokService;

	@Autowired
	private ReturPembelianDetailTempService tempService;
	
	@Autowired
	private PenggunaService penggunaService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPage(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("retur-pembelian");
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
			btn += Html.button("btn btn-primary btn-xs btnEdit", "modal", "#modal-detail", "onClick",
					"getData(" + p.getId() + ")", 0, "Retur Pembelian " + p.getNomorFaktur());

			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_PEMBELIAN_RETUR + tbody;
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
			ph.setDetails(tabelDetails(details));
			return ph;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar-retur", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement daftarObatRetur(@RequestParam(value = "randomId", required = true) String randomId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			List<ReturPembelianDetailTemp> list = tempService.dapatkanListByRandomId(randomId);

			String tabel = tabelReturObatGenerator(list);
			el.setTabelTerapi(tabel);
			el.setValue1(Converter.patternCurrency(totalHarga(list)));
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private String tabelReturObatGenerator(List<ReturPembelianDetailTemp> list) {
		String html = "";
		String data = "";
		for (ReturPembelianDetailTemp t : list) {
			String row = "";
			String btn = "";
			row += Html.td(t.getObat());
			row += Html.td(t.getJumlah().toString());
			row += Html.td(t.getSubTotal());
			btn += Html.aJs("<i class='fa fa-trash-o'></i>", "btn btn-danger btn-xs", "onClick",
					"batalRetur(" + t.getIdObat() + ")", "Hapus Data");
			row += Html.td(btn);
			data += Html.tr(row);
		}
		html = data;
		return html;
	}

	private BigDecimal totalHarga(List<ReturPembelianDetailTemp> list) {
		BigDecimal t = BigDecimal.ZERO;
		for (ReturPembelianDetailTemp temp : list) {
			Integer hargaTotal = Integer.valueOf(temp.getSubTotal().replaceAll("[.,]", ""));
			t = t.add(new BigDecimal(hargaTotal));
		}
		return t;
	}

	@RequestMapping(value = "/dapatkanobat", method = RequestMethod.GET)
	@ResponseBody
	public PembelianDetailHandler dapatkanObat(@RequestParam("nomorFaktur") String nomorFaktur,
			@RequestParam("id") String id, @RequestParam("supplier") String supplier) {
		try {
			PembelianDetailHandler ph = new PembelianDetailHandler();
			PembelianDetail get = pembelianDetailService.dapatkan(new Long(id), nomorFaktur, supplier);
			ph.setObat(get.getObat());
			ph.setHargaBeli(Converter.patternCurrency(get.getHargaBeli()));
			ph.setJumlah(get.getJumlah().toString());
			ph.setSubTotal(Converter.patternCurrency(get.getSubTotal()));
			ph.setIdObat(obatService.dapatkanByNama(get.getObat()).getId());
			return ph;
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
			Obat obat = obatService.dapatkan(h.getIdObat());
			List<ObatStok> listObatStok = obatStokService.temukanByObats(obat);
			PembelianDetail pembelianObat = pembelianDetailService.dapatkan(h.getObat(), h.getNomorFaktur(),
					h.getDistributor());
			Integer jumlahRetur = Integer.valueOf(h.getJumlah());
			if (jumlahRetur > listObatStok.get(0).getStok() || jumlahRetur > pembelianObat.getJumlah()) {
				String pesan = "<p>Jumlah Obat Retur lebih banyak dari Stok yang tersedia atau lebih besar dari pada jumlah pembelian sebelumnya ! </p>";
				pesan += "<p>Obat : " + h.getObat() + "</p>";
				pesan += "<p>Jumlah Retur : " + jumlahRetur + "</p>";
				pesan += "<p>Stok : " + listObatStok.get(0).getStok() + "</p>";
				pesan += "<p>Jumlah Pembelian : " + pembelianObat.getJumlah() + " </p>";
				h.setInfo(pesan);
				h.setTipe(0);
			} else {
				ReturPembelianDetailTemp temp = null;
				ReturPembelianDetailTemp tersimpan = tempService.dapatkanByRandomIdAndIdObat(h.getRandomId(),
						obat.getId());
				if (tersimpan == null) {
					temp = new ReturPembelianDetailTemp();
					BeanUtils.copyProperties(h, temp);
				} else {
					temp = tersimpan;
					int jumlahTersimpan = Integer.valueOf(tersimpan.getJumlah());
					int jumlahBaru = jumlahTersimpan + Integer.valueOf(h.getJumlah());
					BigDecimal harga = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getHargaBeli()));
					BigDecimal subTotal = harga.multiply(new BigDecimal(jumlahBaru));
					temp.setJumlah(String.valueOf(jumlahBaru));
					temp.setSubTotal(Converter.patternCurrency(subTotal));
				}

				temp.setWaktuDibuat(new Date());
				temp.setTerakhirDirubah(new Date());
				tempService.simpan(temp);
			}
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getIdObat() + "", request));
			h.setInfo(e.getMessage());
			return h;
		}
	}

	@RequestMapping(value = "/batal-retur", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailHandler hapusObat(@RequestBody PembelianDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		ReturPembelianDetailTemp tersimpan = tempService.dapatkanByRandomIdAndIdObat(h.getRandomId(), h.getIdObat());
		try {
			// updateStokObat(tersimpan.getObat(), tersimpan.getJumlah(), 1);
			tempService.hapus(tersimpan);
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

	private String tabelDetails(List<PembelianDetail> pembelianDetails) {
		String html = "";
		String data = "";
		for (PembelianDetail d : pembelianDetails) {
			String row = "";
			String btn = "";
			row += Html.td(d.getObat());
			row += Html.td(Formatter.patternCurrency(d.getHargaBeli()));
			row += Html.td(Formatter.patternCurrency(d.getJumlah()));
			row += Html.td(Formatter.patternCurrency(d.getSubTotal()));
			row += Html.td("A");
			btn += Html.button("btn btn-primary btn-xs", null, null, "onClick", "getObat('" + d.getId() + "')", 7,
					"Retur Obat : " + d.getObat(), "button-pilih-obat", "button");
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = THead.THEAD_PEMBELIAN_DETAIL_RETUR + tbody;
		return html;
	}

	@RequestMapping(value = "/retur", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailHandler jual(@RequestBody PembelianDetailHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		ReturPembelian returPembelian = new ReturPembelian();
		try {
			Pembelian pembelian = pembelianService.dapatkan(h.getNomorFaktur(), h.getDistributor());
			List<ReturPembelianDetailTemp> temps = tempService.dapatkanListByRandomId(h.getRandomId());
			List<ReturPembelianDetail> details = new ArrayList<>();
			for (ReturPembelianDetailTemp temp : temps) {
				ReturPembelianDetail detail = new ReturPembelianDetail();
				detail = copyReturPembelianDetail(detail, temp);
				details.add(detail);
			}
			
			returPembelian = copyReturPembelian(returPembelian, pembelian);			
			
			returPembelian.setPengguna(penggunaService.temukan(principal.getName()));
			returPembelian.setReturPembelianDetail(details);
			returPembelianService.simpan(returPembelian);
			
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), returPembelian.toString(), request));
			h.setInfo(e.getMessage());
			return h;
		}
	}
	
	private ReturPembelian copyReturPembelian(ReturPembelian returPembelian, Pembelian pembelian) {
		returPembelian.setDeadline(pembelian.getDeadline());
		returPembelian.setNomorFaktur(pembelian.getNomorFaktur());
		returPembelian.setSupplier(pembelian.getSupplier());		
		
		return returPembelian;
	}
	
	private ReturPembelianDetail copyReturPembelianDetail(ReturPembelianDetail detail, ReturPembelianDetailTemp temp) {
		return detail;
	}

}
