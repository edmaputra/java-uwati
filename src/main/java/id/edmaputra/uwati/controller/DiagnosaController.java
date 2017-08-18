package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.util.Date;

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

import id.edmaputra.uwati.entity.master.obat.Diagnosa;
import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.service.obat.DiagnosaService;
import id.edmaputra.uwati.specification.DiagnosaPredicateBuilder;
import id.edmaputra.uwati.specification.ObatPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;

@Controller
@RequestMapping("/diagnosa")
public class DiagnosaController {

	private static final Logger logger = LoggerFactory.getLogger(DiagnosaController.class);

	@Autowired
	private DiagnosaService diagnosaService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPage(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("diagnosa-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.loadGagal(principal.getName(), request));
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement tampilkanDaftar(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			DiagnosaPredicateBuilder builder = new DiagnosaPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Diagnosa> page = diagnosaService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()) {
				int current = page.getNumber() + 1;
				int next = current + 1;
				int prev = current - 1;
				int first = Math.max(1, current - 5);
				int last = Math.min(first + 10, page.getTotalPages());

				String h = Html.navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(), cari);
				el.setNavigasiHalaman(h);
			}			
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public Diagnosa dapatkan(@RequestParam("id") String id) {
		try {
			Diagnosa get = diagnosaService.dapatkan(new Long(id));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/ada", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAda(@RequestParam("nama") String nama) {
		return diagnosaService.dapatkanByNama(nama) != null;
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Diagnosa tambah(@RequestBody Diagnosa d, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			d.setWaktuDibuat(new Date());
			d.setTerakhirDirubah(new Date());
			d.setUserInput(principal.getName());
			
			diagnosaService.simpan(d);
			d.setInfo(d.getNama()+" Tersimpan");
			logger.info(LogSupport.tambah(principal.getName(), d.toString(), request));
			return d;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), d.getNama(), request));
			d.setInfo(d.getNama()+" Gagal Tersimpan");
			return d;
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Diagnosa edit(@RequestBody Diagnosa d, BindingResult result, Principal principal, HttpServletRequest request) {
		Diagnosa get = diagnosaService.dapatkan(d.getId());		
		try {
			get.setNama(d.getNama());
			get.setKode(d.getKode());
			diagnosaService.simpan(get);
			logger.info(LogSupport.edit(principal.getName(), get.getNama(), request));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(principal.getName(), d.getNama(), request));
			return d;
		}
	}
	
	@RequestMapping(value = "/hapus", method = RequestMethod.POST)
	@ResponseBody
	public Diagnosa hapus(@RequestBody Diagnosa d, BindingResult result, Principal principal, HttpServletRequest request) {
		Diagnosa hapus = diagnosaService.dapatkan(d.getId());
		try {
			diagnosaService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), d.getNama(), request));
			return d;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), d.getNama(), request));
			return d;
		}
	}
	
	@RequestMapping(value = "/b", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarForTransaksi(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "n", defaultValue = "", required = false) String n,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();			
			
			Page<Diagnosa> page = getListDiagnosa(halaman, cari, Integer.valueOf(n));

			String button = buttonListGenerator(page, 1, request);
			el.setButton(button);

			if (page.hasContent()) {
				if (page.getTotalPages() > 1) {
					int current = page.getNumber() + 1;
					int next = current + 1;
					int prev = current - 1;

					String h = navigasiDiagnosa(prev, current, next, page.getTotalPages(), cari);
					el.setNavigasiObat(h);
				}
			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	private Page<Diagnosa> getListDiagnosa(Integer halaman, String nama, Integer n) {
		DiagnosaPredicateBuilder builder = new DiagnosaPredicateBuilder();
		if (!StringUtils.isBlank(nama)) {
			builder.nama(nama);
		}

		BooleanExpression exp = builder.getExpression();
		return diagnosaService.muatDaftar(halaman, exp, n);
	}
	
	private String buttonListGenerator(Page<Diagnosa> list, int jumlahKolom, HttpServletRequest request) {
		String button = "";
		String isi = "";
		int size = list.getContent().size();
		for (int i = 0; i < size; i++) {
			isi += Html.aJs(list.getContent().get(i).getNama(), "btn btn-default btn-wrapword", "onClick", "tambahDiagnosa(" + list.getContent().get(i).getId() + ")");
			if ((i + 1) % jumlahKolom == 0 || (i + 1) == size) {
				button += Html.div(isi, "btn-group btn-group-justified");
				isi = "";
			}
		}
		return button;
	}
	
	private String navigasiDiagnosa(int prev, int current, int next, int totalPage, String cari) {
		String html = "";

		if (current == 1) {
			html += Html.aJs("&gt;", "btn btn-primary", "onClick", "refreshDiagnosa(" + next + ",\"" + cari + "\")");
		} else if (current == totalPage) {
			html += Html.aJs("&lt;", "btn btn-primary", "onClick", "refreshDiagnosa(" + prev + ",\"" + cari + "\")");
		} else if (current > 1 && current < totalPage) {
			html += Html.aJs("&lt;", "btn btn-primary", "onClick", "refreshDiagnosa(" + prev + ",\"" + cari + "\")");
			html += Html.aJs("&gt;", "btn btn-primary", "onClick", "refreshDiagnosa(" + next + ",\"" + cari + "\")");
		}
		return html;
	}

	private String tabelGenerator(Page<Diagnosa> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr><th>Diagnosa</th><th>Kode</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Diagnosa o : list.getContent()) {
			String row = "";
			String btn = "";			
			row += Html.td(o.getNama());
			row += Html.td(o.getKode());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(o.getUserInput());
				row += Html.td(Formatter.formatTanggal(o.getWaktuDibuat()));
				row += Html.td(o.getUserEditor());
				row += Html.td(Formatter.formatTanggal(o.getTerakhirDirubah()));

				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#diagnosa-modal", "onClick",
						"getData(" + o.getId() + ")", 0);

				btn += Html.button("btn btn-danger btn-xs", "modal", "#diagnosa-modal-hapus", "onClick",
						"setIdUntukHapus(" + o.getId() + ")", 1);
			}
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}
}
