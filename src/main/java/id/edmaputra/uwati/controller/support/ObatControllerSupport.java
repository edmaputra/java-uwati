package id.edmaputra.uwati.controller.support;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import id.edmaputra.uwati.entity.master.obat.Obat;
import id.edmaputra.uwati.entity.master.obat.ObatDetail;
import id.edmaputra.uwati.entity.master.obat.ObatExpired;
import id.edmaputra.uwati.entity.master.obat.ObatStok;
import id.edmaputra.uwati.entity.master.obat.Racikan;
import id.edmaputra.uwati.entity.master.obat.RacikanDetail;
import id.edmaputra.uwati.service.obat.ObatDetailService;
import id.edmaputra.uwati.service.obat.ObatExpiredService;
import id.edmaputra.uwati.service.obat.ObatService;
import id.edmaputra.uwati.service.obat.ObatStokService;
import id.edmaputra.uwati.service.obat.RacikanDetailService;
import id.edmaputra.uwati.service.obat.RacikanService;

@Component
public class ObatControllerSupport {

	@Autowired	
	private RacikanService racikanService;

	@Autowired
	private RacikanDetailService racikanDetailService;

	@Autowired
	private ObatService obatService;

	@Autowired
	private ObatExpiredService obatExpiredService;

	@Autowired
	private ObatDetailService obatDetailService;

	@Autowired
	private ObatStokService obatStokService;

	private Racikan getRacikan(String nama) {
		Racikan racikan = racikanService.dapatkanByNama(nama);

		List<RacikanDetail> listRacikanDetail = racikanDetailService.dapatkanByRacikan(racikan);
		racikan.setRacikanDetail(listRacikanDetail);
		Hibernate.initialize(racikan.getRacikanDetail());

		return racikan;
	}
	
	private Racikan getRacikan(Long id) {
		Racikan racikan = racikanService.dapatkan(id);

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

	private Obat getObat(Long id) {
		Obat get = obatService.dapatkan(id);

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

	private void updateStokObat(String obat, String jumlah, int operasi) {
		Obat o = getObat(obat);
		if (o.getTipe() == 0) {
			Integer stokLama = o.getStok().get(0).getStok();
			Integer stokBaru = null;
			// pengurangan stok
			if (operasi == 0) {
				stokBaru = stokLama - Integer.valueOf(jumlah).intValue();
			}
			// penambahan stok
			else if (operasi == 1) {
				stokBaru = stokLama + Integer.valueOf(jumlah).intValue();
			}
			o.getStok().get(0).setStok(stokBaru);
			obatService.simpan(o);
		} else if (o.getTipe() == 1) {
			Racikan r = getRacikan(obat);
			for (RacikanDetail rd : r.getRacikanDetail()) {
				Obat or = getObat(rd.getKomposisi().getNama());
				Integer jumlahBeli = Integer.valueOf(jumlah) * rd.getJumlah();
				Integer stokLama = or.getStok().get(0).getStok();
				Integer stokBaru = null;
				// pengurangan stok
				if (operasi == 0) {
					stokBaru = stokLama - jumlahBeli;
				}
				// penambahan stok
				else if (operasi == 1) {
					stokBaru = stokLama + jumlahBeli;
				}
				or.getStok().get(0).setStok(stokBaru);
				obatService.simpan(or);
			}
		}
	}
}
