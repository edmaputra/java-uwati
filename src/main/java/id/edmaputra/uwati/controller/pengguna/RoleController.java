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

import id.edmaputra.uwati.entity.pengguna.Role;
import id.edmaputra.uwati.service.pengguna.RoleService;
import id.edmaputra.uwati.specification.RolePredicateBuilder;
import id.edmaputra.uwati.support.LogSupport;
import id.edmaputra.uwati.view.Formatter;
import id.edmaputra.uwati.view.Html;
import id.edmaputra.uwati.view.HtmlElement;
import id.edmaputra.uwati.view.json.JsonReturn;
import id.edmaputra.uwati.view.json.Suggestion;

@Controller
@RequestMapping("/role")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@ModelAttribute("role")
	public Role constructRole() {
		return new Role();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanRole(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("role-daftar");
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/daftar", method = RequestMethod.GET)
	@ResponseBody
	public HtmlElement dapatkanDaftarRole(
			@RequestParam(value = "hal", defaultValue = "1", required = false) Integer halaman,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari, 
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HtmlElement el = new HtmlElement();

			RolePredicateBuilder builder = new RolePredicateBuilder();
			if (!StringUtils.isBlank(cari)) {
				builder.cari(cari);
			}

			BooleanExpression exp = builder.getExpression();
			Page<Role> page = roleService.muatDaftar(halaman, exp);

			String tabel = tabelGenerator(page, request);
			el.setTabel(tabel);

			if (page.hasContent()){
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

	@RequestMapping(value = "/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public Role dapatkanRole(@RequestParam("id") String role) {
		try {
			Role get = roleService.dapatkan(Integer.valueOf(role));
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/nama", method = RequestMethod.GET)
	@ResponseBody
	public Suggestion dapatkanRoleByNama(@RequestParam("query") String nama) {
		try {
			RolePredicateBuilder builder = new RolePredicateBuilder();
			if (!StringUtils.isBlank(nama)) {
				builder.cari(nama);
			}
			BooleanExpression exp = builder.getExpression();
			List<Role> l = roleService.dapatkanListByNama(exp);
						
			List<JsonReturn> listReturn = new ArrayList<>();
			for(Role role:l){
				JsonReturn jr = new JsonReturn();
				jr.setData(role.getNama());
				jr.setValue(role.getNama());
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
	
	@RequestMapping(value = "/semua", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> dapatkanSemua(){
		try {
			List<Role> l = roleService.dapatkanSemua();
			return l;
		} catch (Exception e) {
			logger.info(e.getMessage());			
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	@ResponseBody
	public Role tambahRole(@RequestBody Role role, BindingResult result, Principal principal,
			HttpServletRequest request) {
		try {
			role.setUserInput(principal.getName());
			role.setWaktuDibuat(new Date());
			role.setTerakhirDirubah(new Date());
			roleService.simpan(role);
			logger.info(LogSupport.tambah(principal.getName(), role.toString(), request));
			return role;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.tambahGagal(principal.getName(), role.toString(), request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Role editRole(@RequestBody Role role, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Role edit = roleService.dapatkan(role.getId());
		String entity = edit.toString();
		try {
			edit.setNama(role.getNama());
			edit.setUserEditor(principal.getName());
			edit.setTerakhirDirubah(new Date());
			roleService.simpan(edit);
			logger.info(LogSupport.edit(principal.getName(), entity, request));
			return edit;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.editGagal(principal.getName(), entity, request));
			return null;
		}
	}

	@Transactional
	@RequestMapping(value = "/hapus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String hapusRole(@RequestBody Role role, BindingResult result, Principal principal,
			HttpServletRequest request) {
		Role hapus = roleService.dapatkan(role.getId());
		String entity = hapus.toString();
		try {
			roleService.hapus(hapus);
			logger.info(LogSupport.hapus(principal.getName(), entity, request));
			return "HAPUS OK";
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(LogSupport.hapusGagal(principal.getName(), entity, request));
			return null;
		}
	}

	private String tabelGenerator(Page<Role> list, HttpServletRequest request) {
		String html = "";
		String thead = "<thead><tr><th>Role</th>";
		if (request.isUserInRole("ROLE_ADMIN")) {
			thead += "<th>User Input</th>" + "<th>Waktu Dibuat</th>" + "<th>User Editor</th>"
					+ "<th>Terakhir Diubah</th>";
		}
		thead += "<th></th></tr></thead>";
		String data = "";
		for (Role r : list.getContent()) {
			String row = "";
			String btn = "";			
			row += Html.td(r.getNama());
			if (request.isUserInRole("ROLE_ADMIN")) {
				row += Html.td(r.getUserInput());
				row += Html.td(Formatter.formatTanggal(r.getWaktuDibuat()));
				row += Html.td(r.getUserEditor());
				row += Html.td(Formatter.formatTanggal(r.getTerakhirDirubah()));
				btn= Html.button("btn btn-primary btn-xs btnEdit", "modal", "#role-modal-edit", "onClick",
						"getData(" + r.getId() + ")", 0);
				btn += Html.button("btn btn-danger btn-xs", "modal", "#role-modal-hapus", "onClick",
						"setIdUntukHapus(" + r.getId() + ")", 1);
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

}
