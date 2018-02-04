package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import id.edmaputra.uwati.entity.master.obat.CekStok;
import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;
import id.edmaputra.uwati.entity.pasien.RekamMedis;
import id.edmaputra.uwati.entity.pasien.RekamMedisDetail;
import id.edmaputra.uwati.entity.transaksi.BayarPenjualan;
import id.edmaputra.uwati.entity.transaksi.NomorFaktur;
import id.edmaputra.uwati.entity.transaksi.Penjualan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetail;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailRacikan;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailTemp;
import id.edmaputra.uwati.reports.Struk;
import id.edmaputra.uwati.service.ApotekService;
import id.edmaputra.uwati.service.KaryawanService;
import id.edmaputra.uwati.service.obat.CekStokService;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.obat.RacikanDetailService;
import id.edmaputra.uwati.service.obat.RacikanService;
import id.edmaputra.uwati.service.pasien.PasienService;
import id.edmaputra.uwati.service.pasien.RekamMedisDetailService;
import id.edmaputra.uwati.service.pasien.RekamMedisService;
import id.edmaputra.uwati.service.transaksi.NomorFakturService;
import id.edmaputra.uwati.service.transaksi.PenjualanDetailService;
import id.edmaputra.uwati.service.transaksi.PenjualanDetailTempService;
import id.edmaputra.uwati.service.transaksi.PenjualanService;
import id.edmaputra.uwati.specification.NomorFakturPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.validator.impl.PenjualanDetailRacikanValidator;
import id.edmaputra.uwati.validator.impl.PenjualanDetailValidator;
import id.edmaputra.uwati.validator.impl.PenjualanValidator;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.handler.PenjualanDetailHandler;
import id.edmaputra.uwati.view.handler.RekamMedisHandler;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/penjualan-obat")
public class PenjualanObatController {

	private static final Logger logger = LoggerFactory.getLogger(PenjualanObatController.class);

	@Autowired
	private PenjualanDetailTempService penjualanDetailTempService;

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

	@Autowired
	private ApotekService apotekService;

	@Autowired
	private PenjualanService penjualanService;

	@Autowired
	private PenjualanDetailService penjualanDetailService;

	@Autowired
	private KaryawanService karyawanService;

	@Autowired
	private PasienService pasienService;

	@Autowired
	private RekamMedisService rekamMedisService;

	@Autowired
	private RekamMedisDetailService rekamMedisDetailService;

	@Autowired
	private CekStokService cekStokService;

	@Autowired
	private PenjualanValidator penjualanValidator;

	@Autowired
	private PenjualanDetailValidator penjualanDetailValidator;

	@Autowired
	private PenjualanDetailRacikanValidator penjualanDetailRacikanValidator;

	@Autowired
	@Qualifier("strukPenjualanPdf")
	private JasperReportsPdfView strukPenjualan;

	private final String KODE_TRANSAKSI_JUAL = "A";
	private final String KODE_TRANSAKSI_JUAL_RESEP = "B";

	private List<PenjualanDetail> listPenjualanDetail;
	private List<PenjualanDetailRacikan> listPenjualanDetailRacikan;

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

	@RequestMapping(value = "/jual", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailHandler jual(@RequestBody PenjualanDetailHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Penjualan penjualan = new Penjualan();
		try {
			String nomorFaktur = nomorFakturGenerator(h.getTanggal(), KODE_TRANSAKSI_JUAL);
			penjualan.setNomorFaktur(nomorFaktur);
			penjualan.setWaktuTransaksi(Converter.stringToDate(h.getTanggal()));
			penjualan.setTipe(0);
			penjualan.setDokter(h.getDokter());
			penjualan.setPelanggan(h.getPelanggan());
			penjualan.setPengguna(principal.getName());
			penjualan.setTotalPembelian(new BigDecimal(Formatter.hilangkanTitik(h.getTotalPembelian())));
			penjualan.setDiskon(new BigDecimal(Formatter.hilangkanTitik(h.getDiskon())));
			penjualan.setPajak(new BigDecimal(Formatter.hilangkanTitik(h.getPajak())));
			BigDecimal totalPembelianFinal = new BigDecimal(Formatter.hilangkanTitik(h.getTotalPembelianFinal()));
			BigDecimal totalPembayaran = new BigDecimal(Formatter.hilangkanTitik(h.getTotalPembayaran()));
			if (totalPembayaran.compareTo(totalPembelianFinal) >= 0) {
				penjualan.setLunas(true);
			} else if (totalPembayaran.compareTo(totalPembelianFinal) < 0) {
				penjualan.setLunas(false);
			}
			penjualan.setGrandTotal(totalPembelianFinal);
			penjualan.setBayar(totalPembayaran);
			penjualan.setKembali(totalPembayaran.subtract(totalPembelianFinal));
			penjualan.setWaktuDibuat(new Date());
			penjualan.setTerakhirDirubah(new Date());

			listPenjualanDetail = new ArrayList<>();
			listPenjualanDetailRacikan = new ArrayList<>();

			List<PenjualanDetailTemp> listJualDetailTemp = penjualanDetailTempService
					.dapatkanListByRandomId(h.getRandomId());
			for (PenjualanDetailTemp pdt : listJualDetailTemp) {
				PenjualanDetail penjualanDetail = new PenjualanDetail();
				Obat obat = getObat(pdt.getObat());
				penjualanDetail.setObat(obat.getNama());

				if (obat.getTipe() == 0) {
					penjualanDetail.setIsRacikan(false);
				} else if (obat.getTipe() == 1) {
					penjualanDetail.setIsRacikan(true);
					Racikan r = getRacikan(obat.getNama());
					for (RacikanDetail rd : r.getRacikanDetail()) {
						PenjualanDetailRacikan pdr = new PenjualanDetailRacikan();
						pdr.setKomposisi(rd.getKomposisi().getNama());
						pdr.setHargaJualPerKomposisi(rd.getHargaSatuan());
						pdr.setJumlah(rd.getJumlah());
						pdr.setTerakhirDirubah(new Date());
						pdr.setWaktuDibuat(new Date());
						pdr.setPenjualanDetail(penjualanDetail);
						penjualanDetailRacikanValidator.validate(pdr);

						listPenjualanDetailRacikan.add(pdr);
					}
					PenjualanDetailRacikan biayaRacik = new PenjualanDetailRacikan();
					biayaRacik.setKomposisi("Biaya Racik");
					biayaRacik.setHargaJualPerKomposisi(r.getBiayaRacik());
					biayaRacik.setJumlah(1);
					biayaRacik.setTerakhirDirubah(new Date());
					biayaRacik.setWaktuDibuat(new Date());
					biayaRacik.setPenjualanDetail(penjualanDetail);
					penjualanDetailRacikanValidator.validate(biayaRacik);
					listPenjualanDetailRacikan.add(biayaRacik);

					penjualanDetail.setRacikanDetail(listPenjualanDetailRacikan);
				}

				BigDecimal hargaJual = new BigDecimal(Formatter.hilangkanTitik(pdt.getHargaJual()));
				Integer jumlah = new Integer(Formatter.hilangkanTitik(pdt.getJumlah()));
				BigDecimal diskon = new BigDecimal(Formatter.hilangkanTitik(pdt.getDiskon()));
				BigDecimal hargaTotal = hargaJual.multiply(new BigDecimal(jumlah));
				hargaTotal = hargaTotal.subtract(diskon);

				penjualanDetail.setHargaJual(hargaJual);
				penjualanDetail.setJumlah(jumlah);
				penjualanDetail.setDiskon(diskon);
				penjualanDetail.setHargaTotal(hargaTotal);

				penjualanDetail.setWaktuDibuat(new Date());
				penjualanDetail.setTerakhirDirubah(new Date());
				penjualanDetail.setPenjualan(penjualan);

				penjualanDetailValidator.validate(penjualanDetail);
				listPenjualanDetail.add(penjualanDetail);
				if (obat.getTipe() == 0 || obat.getTipe() == 1) {
					updateStokObat(obat, jumlah, 0);
				}
			}
			
			List<BayarPenjualan> pembayarans = new ArrayList<>();
			BayarPenjualan bayarPenjualan = new BayarPenjualan();
			bayarPenjualan.setPenjualan(penjualan);
			bayarPenjualan.setWaktuTransaksi(penjualan.getWaktuTransaksi());
			bayarPenjualan.setJumlahBayar(totalPembayaran);
			pembayarans.add(bayarPenjualan);
			
			penjualan.setPenjualanDetail(listPenjualanDetail);
			penjualan.setPembayaran(pembayarans);
			
			penjualanValidator.validate(penjualan);
			penjualanService.simpan(penjualan);

			penjualanDetailTempService.hapusBatch(h.getRandomId());

			logger.info(LogSupport.tambah(principal.getName(), penjualan.toString(), request));
			String pesan = "<p>Penjualan Tersimpan dengan Nomor Faktur " + penjualan.getNomorFaktur()
					+ "</p><p>Klik Tombol CETAK untuk Mencetak Resi Penjualan.</p>";
			h.setInfo(pesan);
			h.setId(penjualan.getNomorFaktur());

			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), penjualan.toString(), request));
			h.setInfo(e.getMessage());
			return h;
		}
	}

	@RequestMapping(value = "/resep/jual", method = RequestMethod.POST)
	@ResponseBody
	public RekamMedisHandler jualResep(@RequestBody RekamMedisHandler temp, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Penjualan penjualan = new Penjualan();
		try {			
			String nomorFaktur = nomorFakturGenerator(Converter.dateToStringDashSeparator(new Date()),KODE_TRANSAKSI_JUAL_RESEP);
			penjualan.setNomorFaktur(nomorFaktur);
			penjualan.setWaktuTransaksi(Converter.stringToDate(temp.getTanggal()));
			penjualan.setTipe(Integer.valueOf(2));
			penjualan.setDokter(karyawanService.dapatkan(Integer.valueOf(temp.getDokterId()).intValue()).getNama());
			penjualan.setPelanggan(pasienService.dapatkan(Long.valueOf(temp.getPasienId())).getNama());
			penjualan.setPengguna(principal.getName());
			penjualan.setTotalPembelian(new BigDecimal(Formatter.hilangkanTitik(temp.getTotalPembelian())));
			penjualan.setDiskon(new BigDecimal(Formatter.hilangkanTitik(temp.getDiskon())));
			penjualan.setPajak(new BigDecimal(Formatter.hilangkanTitik(temp.getPajak())));
			BigDecimal grandTotal = new BigDecimal(Formatter.hilangkanTitik(temp.getGrandTotal()));
			BigDecimal bayar = new BigDecimal(Formatter.hilangkanTitik(temp.getBayar()));
			penjualan.setGrandTotal(grandTotal);
			penjualan.setBayar(bayar);
			penjualan.setKembali(bayar.subtract(grandTotal));
			penjualan.setWaktuDibuat(new Date());
			penjualan.setTerakhirDirubah(new Date());

			listPenjualanDetail = new ArrayList<>();
			listPenjualanDetailRacikan = new ArrayList<>();

			RekamMedis rm = rekamMedisService.dapatkan(Long.valueOf(temp.getId()));
			List<RekamMedisDetail> list = rekamMedisDetailService.dapatkan(rm);

			for (RekamMedisDetail rmd : list) {				
				if (rmd.getTipe() != 100) {
					PenjualanDetail penjualanDetail = new PenjualanDetail();
					Obat obat = getObat(rmd.getTerapi());
					penjualanDetail.setObat(obat.getNama());

					if (obat.getTipe() == 1) {
						penjualanDetail.setIsRacikan(true);
						Racikan r = getRacikan(obat.getNama());
						for (RacikanDetail rd : r.getRacikanDetail()) {
							PenjualanDetailRacikan pdr = new PenjualanDetailRacikan();
							pdr.setKomposisi(rd.getKomposisi().getNama());
							pdr.setHargaJualPerKomposisi(rd.getHargaSatuan());
							pdr.setJumlah(rd.getJumlah());
							pdr.setTerakhirDirubah(new Date());
							pdr.setWaktuDibuat(new Date());
							pdr.setPenjualanDetail(penjualanDetail);
							penjualanDetailRacikanValidator.validate(pdr);
							listPenjualanDetailRacikan.add(pdr);
						}
						PenjualanDetailRacikan biayaRacik = new PenjualanDetailRacikan();

						biayaRacik.setKomposisi("Biaya Racik");
						biayaRacik.setHargaJualPerKomposisi(r.getBiayaRacik());
						biayaRacik.setJumlah(1);
						biayaRacik.setTerakhirDirubah(new Date());
						biayaRacik.setWaktuDibuat(new Date());
						biayaRacik.setPenjualanDetail(penjualanDetail);
						penjualanDetailRacikanValidator.validate(biayaRacik);
						listPenjualanDetailRacikan.add(biayaRacik);

						penjualanDetail.setRacikanDetail(listPenjualanDetailRacikan);
					} else {
						penjualanDetail.setIsRacikan(false);
					}
					BigDecimal hargaJual = rmd.getHargaJual();
					Integer jumlah = rmd.getJumlah();
					BigDecimal diskon = rmd.getDiskon();
					BigDecimal hargaTotal = hargaJual.multiply(new BigDecimal(jumlah));
					hargaTotal = hargaTotal.subtract(diskon);
					penjualanDetail.setHargaJual(hargaJual);
					penjualanDetail.setJumlah(jumlah);
					penjualanDetail.setDiskon(diskon);
					penjualanDetail.setHargaTotal(hargaTotal);
					penjualanDetail.setWaktuDibuat(new Date());
					penjualanDetail.setTerakhirDirubah(new Date());
					penjualanDetail.setPenjualan(penjualan);

					penjualanDetailValidator.validate(penjualanDetail);
					listPenjualanDetail.add(penjualanDetail);
					if (obat.getTipe() == 0 || obat.getTipe() == 1) {
						updateStokObat(obat, jumlah, 0);
					}
				} else if (rmd.getTipe() == 100) {
					PenjualanDetail biayaRacik = new PenjualanDetail();
					biayaRacik.setObat(rmd.getTerapi());
					
					String terapi = rmd.getTerapi();
					int s = terapi.indexOf("-") + 1;
					String t = terapi.substring(s, terapi.length());
					
					Racikan r = getRacikan(t);
					biayaRacik.setHargaJual(r.getBiayaRacik());
					biayaRacik.setJumlah(rmd.getJumlah());
					biayaRacik.setDiskon(BigDecimal.ZERO);
					biayaRacik.setPajak(BigDecimal.ZERO);
					biayaRacik.setHargaTotal(r.getBiayaRacik().multiply(new BigDecimal(rmd.getJumlah())));
					biayaRacik.setWaktuDibuat(new Date());
					biayaRacik.setTerakhirDirubah(new Date());
					biayaRacik.setPenjualan(penjualan);
					biayaRacik.setIsRacikan(true);
					listPenjualanDetail.add(biayaRacik);
				}
			}

			penjualan.setPenjualanDetail(listPenjualanDetail);
			penjualanValidator.validate(penjualan);
			penjualanService.simpan(penjualan);
			rm.setIsResepSudahDiproses(true);
			rm.setUserEditor(principal.getName());
			rm.setTerakhirDirubah(new Date());
			rekamMedisService.simpan(rm);
			logger.info(LogSupport.tambah(principal.getName(), penjualan.toString(), request));
			temp.setPenjualanId(penjualan.getNomorFaktur());
			return temp;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), penjualan.toString(), request));
			return temp;
		}
	}

	@RequestMapping(value = "/cetak", method = RequestMethod.POST)
	public ModelAndView buatResiDanCetakPenjualan(@RequestParam("id") String id, ModelAndView mav,
			Principal principal) {
		try {
			List<Struk> struks = new ArrayList<>();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			Apotek apotek = apotekService.semua().get(0);
			BigDecimal grandTotal = new BigDecimal(0);
			Penjualan penjualan = penjualanService.dapatkanByNomorFaktur(id);
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
				struk.setStrukHargaObat(Converter.patternCurrency(d.getHargaJual().subtract(d.getDiskon())));
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

	@RequestMapping(value = "/resep/cetak", method = RequestMethod.POST)
	public ModelAndView buatResiDanCetakResep(@RequestParam("id") String id, ModelAndView mav, Principal principal) {
		try {
			List<Struk> struks = new ArrayList<>();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			Apotek apotek = apotekService.semua().get(0);
			BigDecimal grandTotal = new BigDecimal(0);
			Penjualan penjualan = penjualanService.dapatkanByNomorFaktur(id);

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

	@RequestMapping(value = "/tambah-obat", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailHandler tambahTerapi(@RequestBody PenjualanDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			PenjualanDetailTemp t = null;
			PenjualanDetailTemp tersimpan = penjualanDetailTempService.dapatkanByRandomIdAndIdObat(h.getRandomId(),
					h.getIdObat());
			if (tersimpan == null) {
				t = new PenjualanDetailTemp();
				Obat obat = getObat(Long.valueOf(h.getIdObat()).longValue());
				t.setRandomId(h.getRandomId());
				t.setObat(obat.getNama());
				t.setIdObat(obat.getId().toString());
				t.setDiskon("0");
				t.setJumlah("1");
				if (obat.getTipe() == 1) {
					Racikan r = racikanService.dapatkanByNama(obat.getNama());
					t.setHargaJual(Converter.patternCurrency(r.getBiayaRacik().add(r.getHargaJual())));
					t.setSubTotal(Converter.patternCurrency(r.getBiayaRacik().add(r.getHargaJual())));
				} else {
					t.setHargaJual(Converter.patternCurrency(obat.getDetail().get(0).getHargaJual()));
					t.setSubTotal(Converter.patternCurrency(obat.getDetail().get(0).getHargaJual()));
				}
			} else {
				t = tersimpan;
				Integer j = Integer.valueOf(tersimpan.getJumlah()).intValue() + 1;
				BigDecimal diskon = new BigDecimal(tersimpan.getDiskon());
				t.setJumlah(j.toString());
				BigDecimal harga = new BigDecimal(t.getHargaJual().replaceAll("[.,]", ""));
				BigDecimal total = harga.multiply(new BigDecimal(j));
				total = total.subtract(diskon);
				t.setSubTotal(Converter.patternCurrency(total));
			}			
			t.setWaktuDibuat(new Date());
			t.setTerakhirDirubah(new Date());
			penjualanDetailTempService.simpan(t);
			logger.info(LogSupport.tambah(principal.getName(), t.getIdObat(), request));
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getIdObat() + "", request));
			h.setInfo(e.getMessage());
			return h;
		}
	}

	@RequestMapping(value = "/edit-obat", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailHandler editTerapi(@RequestBody PenjualanDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			PenjualanDetailTemp tersimpan = penjualanDetailTempService.dapatkanByRandomIdAndIdObat(h.getRandomId(),
					h.getIdObat());
			if (tersimpan != null) {
				// Integer jumlahLama = new
				// Integer(Converter.hilangkanTandaTitikKoma(tersimpan.getJumlah()));
				Integer jumlah = new Integer(Converter.hilangkanTandaTitikKoma(h.getJumlah()));
				BigDecimal harga = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getHargaJual()));
				BigDecimal diskon = new BigDecimal(Converter.hilangkanTandaTitikKoma(h.getDiskon()));
				BigDecimal hargaTotal = harga.multiply(new BigDecimal(jumlah));
				hargaTotal = hargaTotal.subtract(diskon);
				tersimpan.setJumlah(h.getJumlah());
				tersimpan.setHargaJual(Converter.patternCurrency(harga));
				tersimpan.setSubTotal(Converter.patternCurrency(hargaTotal));
				tersimpan.setDiskon(Converter.patternCurrency(diskon));
				tersimpan.setTerakhirDirubah(new Date());
				penjualanDetailTempService.simpan(tersimpan);
			}

			logger.info(LogSupport.tambah(principal.getName(), h.getIdObat(), request));
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), h.getIdObat() + "", request));
			h.setInfo(e.getMessage());
			return h;
		}
	}

	@RequestMapping(value = "/hapus-obat", method = RequestMethod.POST)
	@ResponseBody
	public PenjualanDetailHandler hapusObat(@RequestBody PenjualanDetailHandler h, BindingResult result,
			Principal principal, HttpServletRequest request) {
		PenjualanDetailTemp tersimpan = penjualanDetailTempService.dapatkanByRandomIdAndIdObat(h.getRandomId(),
				h.getIdObat());
		try {
			penjualanDetailTempService.hapus(tersimpan);
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

	@RequestMapping(value = "/dapatkan-obat", method = RequestMethod.GET)
	@ResponseBody
	public PenjualanDetailHandler dapatkanObat(@RequestParam("idObat") String idObat,
			@RequestParam("randomId") String randomId, Principal principal) {
		PenjualanDetailHandler h = new PenjualanDetailHandler();
		h.setIdObat(idObat);
		h.setRandomId(randomId);
		try {
			PenjualanDetailTemp tersimpan = penjualanDetailTempService.dapatkanByRandomIdAndIdObat(randomId, idObat);
			if (tersimpan != null) {
				h.setObat(tersimpan.getObat());
				h.setJumlah(tersimpan.getJumlah());
				h.setHargaJual(tersimpan.getHargaJual());
				h.setDiskon(tersimpan.getDiskon());
				h.setSubTotal(tersimpan.getSubTotal());
			}
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
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

			List<PenjualanDetailTemp> list = penjualanDetailTempService.dapatkanListByRandomId(randomId);

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

	@RequestMapping(value = "/cek-stok", method = RequestMethod.GET)
	@ResponseBody
	private PenjualanDetailHandler cekStokObat(@RequestParam(value = "randomId", required = true) String randomId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			PenjualanDetailHandler h = new PenjualanDetailHandler();
			List<PenjualanDetailTemp> list = penjualanDetailTempService.dapatkanListByRandomId(randomId);
			String returnStatusStok = "OKE";
			String statusStokPerObat = "";
			Integer marker = 0;
			CekStok cekStok = null;
			for (PenjualanDetailTemp temp : list) {
				Obat obat = getObat(Long.valueOf(temp.getIdObat()));
				if (obat.getTipe() == 1) {
					Racikan r = getRacikan(obat.getNama());
					for (RacikanDetail rd : r.getRacikanDetail()) {
						Obat detail = getObat(rd.getKomposisi().getNama());
						Integer jumlahBeli = new Integer(Converter.hilangkanTandaTitikKoma(temp.getJumlah()));
						jumlahBeli = jumlahBeli * rd.getJumlah();
						cekStok = periksaEntityCekStok(cekStok, detail, temp, jumlahBeli);
						cekStokService.simpan(cekStok);
					}
				} else if (obat.getTipe() == 0) {
					cekStok = periksaEntityCekStok(cekStok, obat, temp,
							new Integer(Formatter.hilangkanTitikKoma(temp.getJumlah())));
					cekStokService.simpan(cekStok);
				}
			}

			List<CekStok> cekStoks = cekStokService.dapatkanListByRandomId(randomId);
			for (CekStok c : cekStoks) {
				Obat obat = getObat(new Long(c.getIdObat()));
				Integer stokTersimpan = obat.getStok().get(0).getStok();
				if (c.getJumlah() > stokTersimpan) {
					marker = 1;
					statusStokPerObat += "<p>" + obat.getNama() + " :: Stok Tersisa " + stokTersimpan + "</p>";
				}
			}

			if (marker == 1) {
				returnStatusStok = "<p>Jumlah Beli Melebihi Stok Obat yang Ada : </p>";
				returnStatusStok = returnStatusStok + statusStokPerObat;
			}
			h.setInfo(returnStatusStok);

			cekStokService.hapusBatch(randomId);
			return h;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	private CekStok periksaEntityCekStok(CekStok cekStok, Obat obat, PenjualanDetailTemp temp, Integer jumlah) {
		cekStok = cekStokService.dapatkanByRandomIdAndIdObat(temp.getRandomId(), obat.getId().toString());
		if (cekStok == null) {
			cekStok = new CekStok();
			cekStok.setRandomId(temp.getRandomId());
			cekStok.setIdObat(obat.getId().toString());
			cekStok.setObat(obat.getNama());
			cekStok.setJumlah(jumlah);
		} else {
			Integer jumlahTersimpan = cekStok.getJumlah();
			Integer jumlahBaru = jumlahTersimpan + jumlah;
			cekStok.setJumlah(jumlahBaru);
		}
		cekStok.setWaktuDibuat(new Date());
		cekStok.setTerakhirDirubah(new Date());
		cekStok.setUserInput("");
		System.out.println("Cek Stok : " + cekStok.getObat() + ". Jumlah :" + cekStok.getJumlah());
		return cekStok;
	}

	// @RequestMapping(value = "/cek-stok", method = RequestMethod.GET)
	// @ResponseBody
	// private PenjualanDetailHandler cekStokObat(@RequestParam(value =
	// "randomId", required = true) String randomId,
	// HttpServletRequest request, HttpServletResponse response) {
	// try {
	// PenjualanDetailHandler h = new PenjualanDetailHandler();
	// List<PenjualanDetailTemp> list =
	// penjualanDetailTempService.dapatkanListByRandomId(randomId);
	// String returnStatusStok = "OKE";
	// String statusStokPerObat = "";
	// Integer marker = 0;
	// for (PenjualanDetailTemp temp : list) {
	// Obat obat = getObat(Long.valueOf(temp.getIdObat()));
	// if (obat.getTipe() == 1){
	// Racikan r = getRacikan(obat.getNama());
	// for (RacikanDetail rd : r.getRacikanDetail()){
	// Obat detail = getObat(rd.getKomposisi().getNama());
	// Integer stokTersimpan = detail.getStok().get(0).getStok();
	// Integer jumlahBeli = new
	// Integer(Converter.hilangkanTandaTitikKoma(temp.getJumlah()));
	// jumlahBeli = jumlahBeli * rd.getJumlah();
	// if (jumlahBeli > stokTersimpan) {
	// marker = 1;
	// statusStokPerObat += "<p>" + rd.getKomposisi().getNama() + " : " +
	// stokTersimpan + "</p>";
	// }
	// }
	// } else {
	// Integer stokTersimpan = obat.getStok().get(0).getStok();
	// Integer jumlahBeli = new
	// Integer(Converter.hilangkanTandaTitikKoma(temp.getJumlah()));
	// if (jumlahBeli > stokTersimpan) {
	// marker = 1;
	// statusStokPerObat += "<p>" + temp.getObat() + " : " + stokTersimpan +
	// "</p>";
	// }
	// }
	// }
	//
	// if (marker == 1) {
	// returnStatusStok = "<p>Jumlah Beli Melebihi Stok Obat yang Ada : </p>";
	// returnStatusStok = returnStatusStok + statusStokPerObat;
	// }
	// h.setInfo(returnStatusStok);
	// return h;
	// } catch (Exception e) {
	// logger.info(e.getMessage());
	// return null;
	// }
	// }

	private String tabelTerapiGenerator(List<PenjualanDetailTemp> list) {
		String html = "";
		String data = "";
		for (PenjualanDetailTemp t : list) {
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

	private BigDecimal totalHarga(List<PenjualanDetailTemp> list) {
		BigDecimal t = BigDecimal.ZERO;
		for (PenjualanDetailTemp temp : list) {
			Integer hargaTotal = Integer.valueOf(temp.getSubTotal().replaceAll("[.,]", ""));
			t = t.add(new BigDecimal(hargaTotal));
		}
		return t;
	}

	private Integer totalItems(List<PenjualanDetailTemp> list) {
		Integer t = 0;
		for (PenjualanDetailTemp temp : list) {
			Integer totalItems = Integer.valueOf(temp.getJumlah().replaceAll("[.,]", ""));
			t = t + totalItems;
		}
		return t;
	}

	private String nomorFakturGenerator(String tanggal, String kodeTransaksi) {
		String nF = generateNomorFaktur(tanggal, kodeTransaksi);
		NomorFaktur newNF = new NomorFaktur();
		newNF.setNomor(setNomorUrutFaktur(nF));
		newNF.setIsSelesai(false);
		newNF.setIsTerpakai(true);
		newNF.setTanggal(Converter.stringToDate(tanggal));
		newNF.setWaktuDibuat(new Date());
		newNF.setTerakhirDirubah(new Date());
		nomorFakturService.simpan(newNF);
		return nF;
	}

	private String generateNomorFaktur(String tanggal, String kode) {
		String nomorFaktur = "";
		Date date = Converter.stringToDate(tanggal);
		List<NomorFaktur> list = dapatkanNomorFaktur(date);
		if (!list.isEmpty()) {
			NomorFaktur n = list.get(list.size() - 1);
			nomorFaktur = setUrutanNomorFaktur(date, n.getNomor(), kode);
		} else {
			nomorFaktur = setUrutanNomorFaktur(date, 0, kode);
		}
		return nomorFaktur;
	}

	private List<NomorFaktur> dapatkanNomorFaktur(Date tanggal) {
		NomorFakturPredicateBuilder builder = new NomorFakturPredicateBuilder();
		if (tanggal != null) {
			builder.tanggal(tanggal);
		}

		BooleanExpression exp = builder.getExpression();
		List<NomorFaktur> list = nomorFakturService.dapatkanSemua(exp);

		return list;
	}

	private String setUrutanNomorFaktur(Date tanggal, Integer nomorTerakhir, String kode) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(tanggal);
		int tahun = cal.get(Calendar.YEAR);
		int bulan = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		bulan = bulan + 1;
		String b = angkaNolBulan(bulan + "");
		String d = angkaNolBulan(day + "");

		nomorTerakhir++;
		String nomor = nomorTerakhir + "";

		nomor = generateNomorUrut(nomor);
		return kode + "" + tahun + "" + b + "" + d + "" + nomor;

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

	private Integer setNomorUrutFaktur(String nomorFaktur) {
		String nomor = nomorFaktur.substring(nomorFaktur.length() - 4, nomorFaktur.length());
		Integer returnValue = Integer.valueOf(nomor).intValue();
		return returnValue;
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
}
