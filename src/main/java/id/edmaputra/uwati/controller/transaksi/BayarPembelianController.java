package id.edmaputra.uwati.controller.transaksi;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import id.edmaputra.uwati.service.transaksi.BayarPembelianService;
import id.edmaputra.uwati.view.handler.PenjualanDetailHandler;

@Controller
@RequestMapping("/bayar-beli")
public class BayarPembelianController {
	
	private static final Logger logger = LoggerFactory.getLogger(BayarPembelianController.class);

	@Autowired
	private BayarPembelianService bayarPembelianService;

	@RequestMapping(value = "/bayar", method = RequestMethod.POST)
	@ResponseBody
	public void jual(@RequestBody PenjualanDetailHandler h, BindingResult result, Principal principal,
			HttpServletRequest request) {
	}
	
	
}
