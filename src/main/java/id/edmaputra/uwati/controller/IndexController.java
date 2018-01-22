package id.edmaputra.uwati.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.edmaputra.uwati.entity.master.Apotek;
import id.edmaputra.uwati.service.ApotekService;
import id.edmaputra.uwati.service.obat.ObatService;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	 @Autowired
	 private ApotekService apotekService;
	 
	 @Autowired
	 private ObatService obatService;

	@RequestMapping(value = "/keluar", method = RequestMethod.GET)
	public String keluar(HttpServletRequest request, HttpServletResponse response) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			logger.info("LOGOUT");
			return "redirect:masuk";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = {"/","/masuk"})
	public String home(Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				model.addAttribute("obatAkanKadaluarsa", obatService.countObatAkanKadaluarsa());
				model.addAttribute("obatSudahKadaluarsa", obatService.countObatSudahKadaluarsa());
				model.addAttribute("obatAkanHabis", obatService.countObatAkanHabis());
				return "beranda";
			}
			Page<Apotek> apoteks = apotekService.muatDaftar(1);
			model.addAttribute("apotek", apoteks.getContent().get(0));
			return "masuk";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
}
