package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import id.edmaputra.uwati.entity.master.Apotek;
import id.edmaputra.uwati.service.ApotekService;
import id.edmaputra.uwati.support.LogSupport;

@Controller
@RequestMapping("/profil")
public class ApotekController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApotekController.class);

	@Autowired
	private ApotekService apotekService;

	@ModelAttribute("apotek")
	public Apotek constructApotek() {
		return new Apotek();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView muatHalaman(Principal principal, HttpServletRequest request) {		
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			ModelAndView mav = new ModelAndView("profil");
			return mav;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			logger.info(LogSupport.loadGagal(principal.getName(), request));
			return null;
		}
	}
		
	@RequestMapping(value="/dapatkan", method = RequestMethod.GET)
	@ResponseBody
	public Apotek dapatkanApotek() {
		try {
			Apotek get = apotekService.muatDaftar(1, null).getContent().get(0);
			return get;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	@Transactional
	@RequestMapping(value="/simpan", method = RequestMethod.POST)
	@ResponseBody
	public Apotek simpan(@RequestBody Apotek apotek, BindingResult result, Principal principal, HttpServletRequest request){
		Page<Apotek> apoteks = apotekService.muatDaftar(1, null);
		Apotek saved = apoteks.getContent().get(0);
		try {			
			saved.setAlamat(apotek.getAlamat());
			saved.setNama(apotek.getNama());
			saved.setTelepon(apotek.getTelepon());
			saved.setTerakhirDirubah(new Date());
			saved.setUserEditor(principal.getName());
			apotekService.simpan(saved);
			logger.info(LogSupport.tambah(principal.getName(), saved.toString(), request));
			return saved;
		} catch (Exception e) {
			logger.info(LogSupport.tambahGagal(principal.getName(), saved.toString(), request));
			logger.info(e.getMessage());
			return null;
		}		
	}

}
