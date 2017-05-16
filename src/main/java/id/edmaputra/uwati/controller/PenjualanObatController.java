package id.edmaputra.uwati.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import id.edmaputra.uwati.support.LogSupport;

@Controller
@RequestMapping("/penjualan-obat")
public class PenjualanObatController {

	private static final Logger logger = LoggerFactory.getLogger(PenjualanObatController.class);


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView tampilkanPelanggan(Principal principal, HttpServletRequest request) {
		try {
			logger.info(LogSupport.load(principal.getName(), request));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//			listDetailTemp = new ArrayList<>();
			ModelAndView mav = new ModelAndView("penjualan-obat");
			Date tanggalHariIni = new Date();
			mav.addObject("tanggalHariIni", formatter.format(tanggalHariIni));				
			return mav;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}


}
