package id.edmaputra.uwati.controller.laporan;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import id.edmaputra.uwati.service.pasien.RekamMedisDiagnosaService;
import id.edmaputra.uwati.service.transaksi.PenjualanDetailService;
import id.edmaputra.uwati.support.Converter;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.THead;
import id.edmaputra.uwati.view.Table;

@Controller
@RequestMapping("/obat-diagnosa")
public class LaporanObatDiagnosaController {

	private static final Logger logger = LoggerFactory.getLogger(LaporanObatDiagnosaController.class);

	@Autowired
	private PenjualanDetailService penjualanDetailService;

	@Autowired
	private RekamMedisDiagnosaService rekamMedisDiagnosaService;
	
	List<Object[]> obat = null;
	List<Object[]> diagnosa = null;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPelanggan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("laporan-obat-diagnosa");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement daftar(
			@RequestParam(value = "tanggalAwal", defaultValue = "", required = false) String tanggalAwal,
			@RequestParam(value = "tanggalAkhir", defaultValue = "", required = false) String tanggalAkhir,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			obat = new ArrayList<>();
			diagnosa = new ArrayList<>();
			HtmlElement el = new HtmlElement();			

			if (StringUtils.isNotBlank(tanggalAwal) || StringUtils.isNotBlank(tanggalAkhir)) {
				Date from = Converter.stringToDate(tanggalAwal);
				if (StringUtils.isBlank(tanggalAkhir)) {
					obat = penjualanDetailService.top10PenjualanObat(from, from);
					diagnosa = rekamMedisDiagnosaService.top10Diagnosa(from, from);
				} else if (StringUtils.isNotBlank(tanggalAkhir)) {
					Date to = Converter.stringToDate(tanggalAkhir);
					if (from.compareTo(to) > 0) {
						obat = penjualanDetailService.top10PenjualanObat(to, from);
						diagnosa = rekamMedisDiagnosaService.top10Diagnosa(to, from);
					} else if (from.compareTo(to) < 0) {
						obat = penjualanDetailService.top10PenjualanObat(from, to);
						diagnosa = rekamMedisDiagnosaService.top10Diagnosa(from, to);
					}
				}
			}

			String tabelObatTop10 = tabelGeneratorTop10(obat, request, 0);
			String tabelDiagnosaTop10 = tabelGeneratorTop10(diagnosa, request, 1);
			el.setTabelObat(tabelObatTop10);
			el.setTabelDiagnosa(tabelDiagnosaTop10);

			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/daftar-rekap", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement rekap(HttpServletRequest request, HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();
			
			String tabelObat = tabelGenerator(obat, request, 0);
			String tabelDiagnosa = tabelGenerator(diagnosa, request, 1);
			el.setTabelObatDetail(tabelObat);
			el.setTabelDiagnosaDetail(tabelDiagnosa);

			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	private String tabelGenerator(List<Object[]> list, HttpServletRequest request, int tipe) {
		String html = "";
		String data = "";
		int index = 1;
		for (Object[] obj : list) {
			String row = "";
			row += Html.td(index + "");
			row += Html.td(Table.nullCell(obj[0].toString()));
			row += Html.td(Table.nullCell(obj[1].toString()));
			data += Html.tr(row);
			index++;
		}
		String tbody = Html.tbody(data);
		if (tipe == 0) {
			html = THead.THEAD_TOP_10_OBAT + tbody;
		} else if (tipe == 1) {
			html = THead.THEAD_TOP_10_DIAGNOSA + tbody;
		}
		return html;
	}

	private String tabelGeneratorTop10(List<Object[]> list, HttpServletRequest request, int tipe) {
		String html = "";
		String data = "";		
		int size = 10;
		int tempSize = list.size();
		if (tempSize < 10) {
			size = list.size();
		}
		for (int i = 0; i < size; i++) {
			String row = "";
			row += Html.td(new Integer(i+1).toString());
			row += Html.td(Table.nullCell(list.get(i)[0].toString()));
			row += Html.td(Table.nullCell(list.get(i)[1].toString()));
			data += Html.tr(row);			
		}
		String tbody = Html.tbody(data);
		if (tipe == 0) {
			html = THead.THEAD_TOP_10_OBAT + tbody;
		} else if (tipe == 1) {
			html = THead.THEAD_TOP_10_DIAGNOSA + tbody;
		}
		return html;
	}
}
