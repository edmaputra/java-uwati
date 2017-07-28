package id.edmaputra.uwati.controller.pengguna;

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
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysema.query.types.expr.BooleanExpression;

import id.edmaputra.uwati.entity.pengguna.Pengguna;
import id.edmaputra.uwati.entity.pengguna.Role;
import id.edmaputra.uwati.service.KaryawanService;
import id.edmaputra.uwati.service.pengguna.PenggunaService;
import id.edmaputra.uwati.service.pengguna.RoleService;
import id.edmaputra.uwati.specification.PenggunaPredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.handler.PasswordHandler;
import id.edmaputra.uwati.view.handler.PenggunaHandler;

@Controller
public class PenggunaController {

	private static final Logger logger = LoggerFactory.getLogger(PenggunaController.class);

	@Autowired
	private PenggunaService penggunaService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private KaryawanService karyawanService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@ModelAttribute("pengguna")
	public Pengguna constructPengguna() {
		return new Pengguna();
	}

	@RequestMapping(value = "/pengguna")
	public ModelAndView tampilkanProfilPengguna(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("pengguna");			
			mav.addObject("pengguna", principal.getName());
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/pengguna/simpan", method = RequestMethod.POST)
	@ResponseBody
	public Pengguna simpan(@RequestBody PasswordHandler ph, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pengguna p = penggunaService.temukan(principal.getName());
		try {
			// String passwordLama = encoder.encode(ph.getPasswordLama());
			if (encoder.matches(ph.getPasswordLama(), p.getKataSandi())){
				p.setKataSandi(encoder.encode(ph.getPasswordBaru()));
				p.setTerakhirDirubah(new Date());
				p.setUserEditor(principal.getName());
				penggunaService.simpan(p);
				logger.info(LogSupport.edit(principal.getName(), p.getNama(), request));				
			} else {
				p.setInfo("Password Lama Tidak Valid, Harap Isi Password Lama dengan Benar");				
			}
			return p;
		} catch (Exception e) {
			logger.info(LogSupport.editGagal(principal.getName(), p.getNama(), request));
			logger.info(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/daftarpengguna")
	public ModelAndView tampilkanDaftarPengguna(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("pengguna-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftarpengguna/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarPengguna(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "isPertamaKali", required = false) Boolean isPertamaKali,
			@RequestParam(value = "isAktif", required = false) Boolean isAktif, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			PenggunaPredicateBuilder builder = new PenggunaPredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			if (isAktif != null) {
				builder.aktif(isAktif);
			}

			if (isPertamaKali != null) {
				builder.aktif(isPertamaKali);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Pengguna> page = penggunaService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()) {
				int current = page.getNumber() + 1;
				int next = current + 1;
				int prev = current - 1;
				int first = Math.max(1, current - 5);
				int last = Math.min(first + 10, page.getTotalPages());

				String h = navigasiHalamanGenerator(first, prev, current, next, last, page.getTotalPages(), cari);
				el.setNavigasiHalaman(h);
			}
			return el;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftarpengguna/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public Pengguna dapatkanPengguna(@RequestParam("id") String id) {
		try {
			Pengguna get = penggunaService.dapatkan(Integer.valueOf(id));
//			Karyawan karyawan = karyawanService.dapatkan(get.getKaryawan().getNama());
//			get.setKaryawan(karyawan);
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/daftarpengguna/tersedia", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isAda(@RequestParam("nama") String nama) {
		return penggunaService.temukan(nama) == null;
	}

	@RequestMapping(value = "/daftarpengguna/semua", method = RequestMethod.GET)
	@ResponseBody
	public List<Pengguna> dapatkanSemua() {
		try {
			List<Pengguna> l = penggunaService.dapatkanSemua();
			return l;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	
	@RequestMapping(value = "/daftarpengguna/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Pengguna tambahPengguna(@RequestBody PenggunaHandler ph, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			Pengguna pengguna = new Pengguna();
			pengguna = setPenggunaContent(pengguna, ph);
			List<Role> list = new ArrayList<>();
			pengguna.setRoles(setListRolesContent(list, ph));
			pengguna.setUserInput(principal.getName());
			pengguna.setWaktuDibuat(new Date());
			pengguna.setTerakhirDirubah(new Date());
			pengguna.setKaryawan(karyawanService.dapatkan(ph.getKaryawan()));
			penggunaService.simpan(pengguna);
			logger.info(LogSupport.tambah(principal.getName(), pengguna.toString(), request));
			return pengguna;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), ph.toString(), request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/daftarpengguna/edit", method = RequestMethod.POST)
	@ResponseBody
	public Pengguna editPengguna(@RequestBody PenggunaHandler ph, BindingResult result, Principal principal,
			HttpServletRequest request) {		
		Pengguna edit = getPengguna(ph.getId());
		String entity = edit.toString();
		try {
			edit = setPenggunaContent(edit, ph);
			edit.getRoles().clear();
			List<Role> editRoles = new ArrayList<>();
			editRoles = setListRolesContent(editRoles, ph);
			edit.setRoles(editRoles);
			edit.setKaryawan(karyawanService.dapatkan(ph.getKaryawan()));
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			penggunaService.simpan(edit);
			logger.info(LogSupport.edit(principal.getName(), entity, request));
			return edit;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(principal.getName(), entity, request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/daftarpengguna/hapus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Pengguna hapusPengguna(@RequestBody Pengguna pengguna, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Pengguna hapus = penggunaService.dapatkan(pengguna.getId());
		String entity = hapus.toString();
		try {
			penggunaService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return hapus;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}
	
	private Pengguna getPengguna(Integer id){
		Pengguna get = penggunaService.dapatkan(id);
		
		return get;
	}

	private String tabelGenerator(Page<Pengguna> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr>" + "<th>Pengguna</th>" + "<th>Aktif</th>" + "<th>First Time</th>"
				+ "<th>Kesempatan</th>" + "<th>Role</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Pengguna p : list.getContent()) {
			String row = "";
			String btn = "";			
			row += Html.td(p.getNama());
			row += Html.td(p.getIsAktif() + "");
			row += Html.td(p.getIsPertamaKali() + "");
			row += Html.td(p.getCountKesalahan() + "");
			row += Html.td(roleParser(p.getRoles()));
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(p.getUserInput());
				row += Html.td(Formatter.formatTanggal(p.getWaktuDibuat()));
				row += Html.td(p.getUserEditor());
				row += Html.td(Formatter.formatTanggal(p.getTerakhirDirubah()));
				btn = Html.button("btn btn-primary btn-xs btnEdit", "modal", "#modal-pengguna-edit", "onClick",
						"getData(" + p.getId() + ")", 0, "Edit "+p.getNama());
				btn += Html.button("btn btn-danger btn-xs", "modal", "#modal-pengguna-hapus", "onClick",
						"setIdUntukHapus(" + p.getId() + ")", 1, "Hapus "+p.getNama());
			}
			row += Html.td(btn);
			data += Html.tr(row);
		}
		String tbody = Html.tbody(data);
		html = thead + tbody;
		return html;
	}

	private String navigasiHalamanGenerator(int first, int prev, int current, int next, int last, int totalPage,
			String cari) {
		String html = "";

		if (current == 1) {
			html += Html.li(Html.aJs("&lt;&lt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&lt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&lt;&lt;", null, "onClick", "refresh(" + first + ",\"" + cari + "\")"), null,
					null, null);
			html += Html.li(Html.aJs("&lt;", null, "onClick", "refresh(" + prev + ",\"" + cari + "\")"), null, null,
					null);
		}

		for (int i = first; i <= last; i++) {
			if (i == current) {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + cari + "\")"), "active",
						null, null);
			} else {
				html += Html.li(Html.aJs(i + "", null, "onClick", "refresh(" + i + ",\"" + cari + "\")"), null, null,
						null);
			}
		}

		if (current == totalPage) {
			html += Html.li(Html.aJs("&gt;", null, null, null), "disabled", null, null);
			html += Html.li(Html.aJs("&gt;&gt;", null, null, null), "disabled", null, null);
		} else {
			html += Html.li(Html.aJs("&gt;", null, "onClick", "refresh(" + next + ",\"" + cari + "\")"), null, null,
					null);
			html += Html.li(Html.aJs("&gt;&gt;", null, "onClick", "refresh(" + last + ",\"" + cari + "\")"), null, null,
					null);
		}

		String nav = Html.nav(Html.ul(html, "pagination"));

		return nav;
	}

	private Pengguna setPenggunaContent(Pengguna p, PenggunaHandler ph) {
		p.setNama(ph.getNama());
		if (StringUtils.isNotBlank(ph.getKataSandi()) || !StringUtils.isNotBlank(ph.getKaryawan())){
			p.setKataSandi(encoder.encode(ph.getKataSandi()));
		}		
		p.setCountKesalahan(ph.getCountKesalahan());
		p.setIsAktif(ph.getIsAktif());
		p.setIsPertamaKali(ph.getIsPertamaKali());
		return p;
	}

	private List<Role> setListRolesContent(List<Role> list, PenggunaHandler ph) {
		if (StringUtils.isNotEmpty(ph.getRole1())) {
			Role role = roleService.dapatkanByNama(ph.getRole1());
			list.add(role);
		}
		if (StringUtils.isNotEmpty(ph.getRole2())) {
			Role role = roleService.dapatkanByNama(ph.getRole2());
			list.add(role);
		}
		if (StringUtils.isNotEmpty(ph.getRole3())) {
			Role role = roleService.dapatkanByNama(ph.getRole3());
			list.add(role);
		}
		return list;
	}

	private String roleParser(List<Role> roles) {
		String r = "";
		try {
			List<String> listNama = new ArrayList<>();
			for (Role role : roles) {
				role.setNama(role.getNama().replaceAll("ROLE_", ""));
				listNama.add(role.getNama());
			}
			r = StringUtils.join(listNama, ",");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return r;
	}

}
