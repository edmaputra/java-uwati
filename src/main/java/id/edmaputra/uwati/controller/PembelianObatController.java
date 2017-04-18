package id.edmaputra.uwati.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.transaksi.Pembelian;
import id.edmaputra.uwati.entity.transaksi.PembelianDetail;
import id.edmaputra.uwati.entity.transaksi.PembelianDetailTemp;
import id.edmaputra.uwati.service.ObatDetailService;
import id.edmaputra.uwati.service.ObatExpiredService;
import id.edmaputra.uwati.service.ObatService;
import id.edmaputra.uwati.service.ObatStokService;
import id.edmaputra.uwati.service.PembelianDetailService;
import id.edmaputra.uwati.service.PembelianService;
import id.edmaputra.uwati.service.PenggunaService;
import id.edmaputra.uwati.specification.ObatPredicateBuilder;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.json.JsonReturn;
import id.edmaputra.uwati.view.json.Suggestion;

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

	private List<PembelianDetailTemp> listDetailTemp = new ArrayList<>();;

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

	@Transactional
	@RequestMapping(value = "/tambahTemp", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailTemp tambahObat(@RequestBody PembelianDetailTemp pembelianDetailTemp, BindingResult result,
			Principal principal, HttpServletRequest request) {
		try {
			listDetailTemp.add(pembelianDetailTemp);
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
			HtmlElement el = new HtmlElement();
			// List<PembelianDetailTemp> list = new ArrayList<>();
			// for (PembelianDetailTemp p : listDetailTemp){
			// if (p.getPengguna() == principal.getName() && p.getSupplier() ==
			// supplier && p.getTanggal() == Converter.stringToDate(tanggal)){
			// list.add(p);
			// }
			// }
			System.out.println("size " + listDetailTemp.size());
			String tabel = tabelGenerator(listDetailTemp, request);
			el.setTabel(tabel);
			Integer tot = 0;
			for (PembelianDetailTemp te : listDetailTemp){
				Integer subTotal = Integer.valueOf(te.getSubTotal().replaceAll("[.,]", ""));
				tot = tot + subTotal;
			}			
			System.out.println(tot);
			String total = NumberFormat.getNumberInstance(new Locale("id", "ID")).format(tot);
			System.out.println(total);
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
			// t.setId(Long.valueOf(index));
//			System.out.println("Index : " + pembelianDetailTemp.getId());
			
			Integer index = pembelianDetailTemp.getId();			
			
			ListIterator<PembelianDetailTemp> iter = listDetailTemp.listIterator();
			while(iter.hasNext()){
			    if(iter.next().getId().equals(index)){
			    	System.out.println("hpapus");
			        iter.remove();
			    }
			}
			
//			listDetailTemp.remove(index);
			System.out.println("size after Delete : " + listDetailTemp.size());
			return t;
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/beli", method = RequestMethod.POST)
	@ResponseBody
	public PembelianDetailTemp simpanPembelian(@RequestBody PembelianDetailTemp temp, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pembelian pembelian = new Pembelian();
		try {
			pembelian.setNomorFaktur(temp.getNomorFaktur());
			pembelian.setWaktuTransaksi(Converter.stringToDate(temp.getTanggal()));
			pembelian.setSupplier(temp.getSupplier());
			pembelian.setPengguna(penggunaService.temukan(principal.getName()));
			pembelian.setWaktuDibuat(new Date());
			pembelian.setTerakhirDirubah(new Date());

			BigDecimal grandTotal = new BigDecimal(0);
			List<PembelianDetail> listPembelianDetail = new ArrayList<>();
			for (PembelianDetailTemp t : listDetailTemp) {
				PembelianDetail pembelianDetail = new PembelianDetail();
				pembelianDetail = setDetailContent(pembelianDetail, t);
				pembelianDetail.setPembelian(pembelian);
				grandTotal = grandTotal.add(new BigDecimal(t.getSubTotal().replaceAll("[.,]", "")));
				listPembelianDetail.add(pembelianDetail);
				Obat obat = pembelianDetail.getObat();
				updateObat(obat, pembelianDetail, principal.getName(), request);				
			}
			pembelian.setGrandTotal(grandTotal);
			pembelian.setPembelianDetail(listPembelianDetail);

			pembelianService.simpan(pembelian);
			logger.info(LogSupport.tambah(principal.getName(), pembelian.toString(), request));
			listDetailTemp.removeAll(listDetailTemp);
			listDetailTemp = new ArrayList<>();
			return temp;			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pembelian.toString(), request));
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
			pembelianDetail.setObat(getObat(temp.getObat()));
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
			obat.getObatDetail().get(0).setHargaBeli(detail.getHargaBeli());
			obat.getObatDetail().get(0).setHargaJual(detail.getHargaJual());
			obat.getObatDetail().get(0).setHargaJualResep(detail.getHargaJualResep());
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

	// @RequestMapping(value = "/daftar", method = RequestMethod.GET)
	// @ResponseBody
	// public HtmlElement dapatkanDaftarPelanggan(
	// @RequestParam(value = "hal", defaultValue = "1", required = false)
	// Integer halaman,
	// @RequestParam(value = "cari", defaultValue = "", required = false) String
	// cari, HttpServletRequest request,
	// HttpServletResponse response) {
	// try {
	// HtmlElement el = new HtmlElement();
	//
	// PelangganPredicateBuilder builder = new PelangganPredicateBuilder();
	// if (!StringUtils.isBlank(cari)) {
	// builder.cari(cari);
	// }
	//
	// BooleanExpression exp = builder.getExpression();
	// Page<Pelanggan> page = pelangganService.muatDaftar(halaman, exp);
	//
	// String tabel = tabelGenerator(page, request);
	// el.setTabel(tabel);
	//
	// if (page.hasContent()){
	// int current = page.getNumber() + 1;
	// int next = current + 1;
	// int prev = current - 1;
	// int first = Math.max(1, current - 5);
	// int last = Math.min(first + 10, page.getTotalPages());
	//
	// String h = navigasiHalamanGenerator(first, prev, current, next, last,
	// page.getTotalPages(), cari);
	// el.setNavigasiHalaman(h);
	// }
	// return el;
	// } catch (Exception e) {
	// logger.info(e.getMessage());
	// return null;
	// }
	// }
	//
	// @Transactional
	// @RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	// @ResponseBody
	// public Pelanggan dapatkanPelanggan(@RequestParam("id") String dokter) {
	// try {
	// Pelanggan get = pelangganService.dapatkan(Integer.valueOf(dokter));
	// return get;
	// } catch (Exception e) {
	// logger.info(e.getMessage());
	// return null;
	// }
	// }
	//
	// @Transactional
	// @RequestMapping(value = "/tambah", method = RequestMethod.POST)
	// @ResponseBody
	// public Pelanggan tambahPelanggan(@RequestBody Pelanggan pelanggan,
	// BindingResult result, Principal principal,
	// HttpServletRequest request) {
	// try {
	// pelanggan.setUserInput(principal.getName());
	// pelanggan.setWaktuDibuat(new Date());
	// pelanggan.setTerakhirDirubah(new Date());
	// pelangganService.simpan(pelanggan);
	// logger.info(LogSupport.tambah(principal.getName(), pelanggan.toString(),
	// request));
	// return pelanggan;
	// } catch (Exception e) {
	// logger.info(e.getMessage());
	// logger.info(LogSupport.tambahGagal(principal.getName(),
	// pelanggan.toString(), request));
	// return null;
	// }
	// }
	//
	// @Transactional
	// @RequestMapping(value = "/edit", method = RequestMethod.POST)
	// @ResponseBody
	// public Pelanggan editPelanggan(@RequestBody Pelanggan pelanggan,
	// BindingResult result, Principal principal,
	// HttpServletRequest request) {
	// Pelanggan edit = pelangganService.dapatkan(pelanggan.getId());
	// String entity = edit.toString();
	// try {
	// edit.setNama(pelanggan.getNama());
	// edit.setKontak(pelanggan.getKontak());
	// edit.setKode(pelanggan.getKode());
	// edit.setAlamat(pelanggan.getAlamat());
	// edit.setUserEditor(principal.getName());
	// edit.setTerakhirDirubah(new Date());
	// pelangganService.simpan(edit);
	// logger.info(LogSupport.edit(principal.getName(), entity, request));
	// return edit;
	// } catch (Exception e) {
	// logger.info(e.getMessage());
	// logger.info(LogSupport.editGagal(principal.getName(), entity, request));
	// return null;
	// }
	// }
	//
	// @RequestMapping(value = "/hapus", method = RequestMethod.POST, produces =
	// "application/json; charset=utf-8")
	// @ResponseBody
	// public String hapusPelanggan(@RequestBody Pelanggan pelanggan,
	// BindingResult result, Principal principal,
	// HttpServletRequest request) {
	// Pelanggan hapus = pelangganService.dapatkan(pelanggan.getId());
	// String entity = hapus.toString();
	// try {
	// pelangganService.hapus(hapus);
	// logger.info(LogSupport.hapus(principal.getName(), entity, request));
	// return "HAPUS OK";
	// } catch (Exception e) {
	// logger.info(e.getMessage());
	// logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
	// return null;
	// }
	// }
	//
	private String tabelGenerator(List<PembelianDetailTemp> list, HttpServletRequest request) {
		String html = "";
		String data = "";
		int i = 0;
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
			btn += Html.button("btn btn-danger btn-xs", null, null, "onClick", "hapus(" + i + ")", 1);
			row += Html.td(btn);
			data += Html.tr(row);
			i++;
		}
		html = data;
		return html;
	}

	private Obat getObat(String nama) {
		Obat get = obatService.dapatkanByNama(nama);

		List<ObatDetail> lObatDetail = obatDetailService.temukanByObats(get);
		get.setObatDetail(lObatDetail);
		Hibernate.initialize(get.getObatDetail());

		List<ObatStok> lObatStok = obatStokService.temukanByObats(get);
		get.setStok(lObatStok);
		Hibernate.initialize(get.getStok());

		List<ObatExpired> lObatExpired = obatExpiredService.temukanByObats(get);
		get.setExpired(lObatExpired);
		Hibernate.initialize(get.getExpired());
		return get;
	}

}
