package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;
import id.edmaputra.uwati.entity.transaksi.NomorFaktur;
import id.edmaputra.uwati.entity.transaksi.PenjualanDetailTemp;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.obat.RacikanDetailService;
import id.edmaputra.uwati.service.obat.RacikanService;
import id.edmaputra.uwati.service.transaksi.NomorFakturService;
import id.edmaputra.uwati.service.transaksi.PenjualanDetailTempService;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;

@Controller
@RequestMapping("/nomor-faktur")
public class NomorFakturController {

	private static final Logger logger = LoggerFactory.getLogger(NomorFakturController.class);
	private final Integer PANJANG_NOMOR_URUT = 4;
	private final String KODE_TRANSAKSI_PENJUALAN = "A";
		
	@Autowired
	private NomorFakturService nomorFakturService;
	
	@Autowired
	private PenjualanDetailTempService penjualanDetailTempService;
	
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
	
	@RequestMapping(value = "/not-terpakai", method = RequestMethod.POST)
	@ResponseBody
	public NomorFaktur setKondisi(@RequestBody PenjualanDetailTemp pdt, BindingResult result, Principal principal, HttpServletRequest request) {
		try {
			if (!StringUtils.isBlank(pdt.getNomorFaktur())){				
				Integer nomorUrut = getUrutanNomorFaktur(pdt.getNomorFaktur());
				Date tanggal = Converter.stringToDate(pdt.getTanggal());
				NomorFaktur nomorFaktur = nomorFakturService.dapatkan(nomorUrut, tanggal);
				nomorFaktur.setIsTerpakai(false);
				nomorFakturService.simpan(nomorFaktur);
				
				NomorFaktur n = nomorFakturService.dapatkan(nomorUrut, tanggal, false, false);
				String nomorFakturTemp = generateNomorFakturFromTanggalAndNomorUrut(n.getTanggal(), n.getNomor());
				
				kembalikanStokObat(nomorFakturTemp);
				
				hapusTempDiPenjualanDetailTemp(nomorFakturTemp, principal.getName());
								
				return nomorFaktur;
			} else {
				return null;
			}			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pdt.toString(), request));
			return null;
		}
	}
	
	@RequestMapping(value = "/selesai", method = RequestMethod.POST)
	@ResponseBody
	public NomorFaktur setSelesai(@RequestBody PenjualanDetailTemp pdt, BindingResult result, Principal principal, HttpServletRequest request) {
		try {
			if (!StringUtils.isBlank(pdt.getNomorFaktur())){
				Integer nomorUrut = getUrutanNomorFaktur(pdt.getNomorFaktur());
				Date tanggal = Converter.stringToDate(pdt.getTanggal());
				NomorFaktur nomorFaktur = nomorFakturService.dapatkan(nomorUrut, tanggal);
				nomorFaktur.setIsSelesai(true);
				nomorFaktur.setIsTerpakai(false);
				nomorFakturService.simpan(nomorFaktur);											
								
				return nomorFaktur;
			} else {
				return null;
			}			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), pdt.toString(), request));
			return null;
		}
	}
	
	private void kembalikanStokObat(String nomorFaktur){
		List<PenjualanDetailTemp> listTemp = penjualanDetailTempService.dapatkan(nomorFaktur);
		for (PenjualanDetailTemp pdt : listTemp){
			Obat obat = getObat(pdt.getObat());
			if (obat.getTipe() == 0){
				Integer stokLama = obat.getStok().get(0).getStok();						
				Integer stokBaru = stokLama + Integer.valueOf(pdt.getJumlah()).intValue();				
				obat.getStok().get(0).setStok(stokBaru);
				obatService.simpan(obat);
			} else if (obat.getTipe() == 1){
				Racikan r = getRacikan(obat.getNama());
				for (RacikanDetail rd : r.getRacikanDetail()) {
					Obat komposisiRacikan = getObat(rd.getKomposisi().getNama());
					Integer jumlahBeli = Integer.valueOf(pdt.getJumlah()) * rd.getJumlah();
					Integer stokLama = komposisiRacikan.getStok().get(0).getStok();
					Integer stokBaru = stokLama + jumlahBeli;;				
					komposisiRacikan.getStok().get(0).setStok(stokBaru);
					obatService.simpan(komposisiRacikan);
				}
			}
		}
	}
	
	
	private void hapusTempDiPenjualanDetailTemp(String nomorFaktur, String pengguna){
		List<PenjualanDetailTemp> list = penjualanDetailTempService.dapatkan(nomorFaktur, pengguna);
		for (PenjualanDetailTemp t : list){
			penjualanDetailTempService.hapus(t);
		}		
	}
	
	private Integer getUrutanNomorFaktur(String nomorFaktur){
		nomorFaktur = nomorFaktur.substring(nomorFaktur.length() - PANJANG_NOMOR_URUT, nomorFaktur.length());
		return Integer.valueOf(nomorFaktur).intValue();
	}
	
	private String generateNomorFakturFromTanggalAndNomorUrut(Date tanggal, Integer nomorUrut) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(tanggal);
		int tahun = cal.get(Calendar.YEAR);
		int bulan = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		bulan = bulan + 1;
		String b = angkaNolBulan(bulan + "");
		String nomor = nomorUrut + "";

		nomor = generateNomorUrut(nomor);
		return KODE_TRANSAKSI_PENJUALAN + "" + tahun + "" + b + "" + day + "" + nomor;

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
