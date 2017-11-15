<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="baruUrl" value="/rekammedis/baru" />
<c:url var="tambahUrl" value="/rekammedis/tambah" />
<c:url var="editUrl" value="/rekammedis/edit" />
<c:url var="hapusUrl" value="/rekammedis/hapus" />

<c:url var="dapatkanUrl" value="/rekammedis/dapatkan" />
<c:url var="detailUrl" value="/rekammedis/detail" />
<c:url var="kunjunganUrl" value="/rekammedis/kunjungan" />
<c:url var="daftarUrl" value="/rekammedis/daftar" />

<c:url var="dapatkanTerapiUrl" value="/rekammedis/dapatkan-terapi" />
<c:url var="tambahTerapiUrl" value="/rekammedis/tambah-terapi" />
<c:url var="editTerapiUrl" value="/rekammedis/edit-terapi" />
<c:url var="daftarTerapiUrl" value="/rekammedis/daftar-terapi" />
<c:url var="hapusTerapiUrl" value="/rekammedis/hapus-terapi" />
<c:url var="hapusTempUrl" value="/rekammedis/hapus-temp" />

<c:url var="tambahDiagnosaUrl" value="/rekammedis/tambah-diagnosa" />
<c:url var="daftarDiagnosaUrl" value="/rekammedis/daftar-diagnosa" />
<c:url var="hapusDiagnosaUrl" value="/rekammedis/hapus-diagnosa" />

<c:url var="prosesResepUrl" value="/rekammedis/resep-kirim" />
<c:url var="batalProsesResepUrl" value="/rekammedis/resep-batal" />

<c:url var="cekStokListObatUrl" value="/rekammedis/cek-stok" />

<c:url var="terapiUrl" value="/obat/obat-tindakan" />
<c:url var="diagnosaUrl" value="/diagnosa/b" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
			<form class="form-horizontal style-form" method="get">
				<div class="form-group">
					<label class="col-sm-1 control-label">ID</label>
					<div class="col-sm-1">
						<input type="text" class="form-control" value="${pasien.id}"
							id="pasienId" readonly="readonly">
					</div>
					<label class="col-sm-1 control-label">Nama</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" value="${pasien.nama}"
							readonly="readonly">
					</div>
					<label class="col-sm-1 control-label" style="text-align: right;">Gender</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${pasien.info}"
							readonly="readonly">
					</div>
					<label class="col-sm-1 control-label" style="text-align: right;">Usia</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="${pasien.kontak}"
							readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">ALamat</label>
					<div class="col-sm-11">
						<input type="text" class="form-control" value="${pasien.alamat}"
							readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Asuransi Kesehatan</label>
					<div class="col-sm-4">
						<input type="text" class="form-control"
							value="${pasien.jaminanKesehatan}" readonly="readonly">
					</div>
					<label class="col-sm-1 control-label">Nomor</label>
					<div class="col-sm-5">
						<input type="text" class="form-control"
							value="${pasien.nomorJaminanKesehatan}" readonly="readonly">
					</div>
				</div>
			</form>
			<div class="content-panel">
				<div class="row">
					<div class="col-md-2 ">
						<security:authorize access="hasAnyRole('ADMIN','MEDIS')">
							<button id="rm-baru" class="btn btn-primary" data-toggle="modal"
								data-target="#rm-modal">Rekam Medis Baru</button>
						</security:authorize>
					</div>

					<div class="col-md-10">
						<form class="form-inline pull-right" id="formCari">
							<div class="form-group">
								<input type="text" id="stringTanggalCari"
									class="form-control datePicker" placeholder="Tanggal"
									style="width: 100px" />
							</div>
							<div class="form-group">
								<input type="text" id="stringCari" class="form-control"
									placeholder="Pencarian" style="width: 250px" />
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-primary" id="btnCari">Cari</button>
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-default" id="btnReset"
									onclick="refresh(1, '', '')">Reset</button>
							</div>
						</form>
					</div>
				</div>
				<br />

				<table class="table table-striped table-advance table-hover"
					id="tabel">
				</table>
				<div id="nav"></div>
			</div>
		</div>
	</div>
</div>

<div class="modal container fade" id="rm-modal" tabindex="-1"
	style="display: none;">
	<div class="modal-header">
		<h4 class="modal-title" id="myModalLabel">Rekam Medis</h4>
	</div>
	<form class="form style-form formTambah" method="post"
		id="formRekamMedis">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-3 form-group">
					<label>Kunjungan:</label> <input type="text" name="kunjungan"
						class="form-control" id="kunjungan" readonly="readonly" />
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Tanggal:</label> <input type="text" name="tanggal"
							class="form-control datePicker" id="tanggal" autocomplete="off" />
					</div>
				</div>

				<div class="col-md-3">
					<div class="form-group">
						<label>Nomor:</label> <input type="text" name="nomor"
							class="form-control" id="nomor" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label>Anamnesa :</label>
				<textarea class="form-control" rows="3" name="anamnesa"
					id="anamnesa"></textarea>
			</div>
			
			<div class="row">
				<div class="form-group col-md-2">
					<label>Tekanan Darah (mmHg):</label>
					<input type="text" name="tekanan_darah"	class="form-control" id="tekanan_darah" autocomplete="off"/>
				</div>
				<div class="form-group col-md-2">
					<label>Denyut Nadi (kali/menit):</label>
					<input type="text" name="nadi" class="form-control" id="nadi" autocomplete="off"/>
				</div>
				<div class="form-group col-md-2">
					<label>Pernafasan (kali/menit):</label>
					<input type="text" name="pernafasan" class="form-control" id="pernafasan" autocomplete="off"/>
				</div>
				<div class="form-group col-md-2">
					<label>Suhu Tubuh (celcius):</label>
					<input type="text" name="suhu_tubuh" class="form-control" id="suhu_tubuh" autocomplete="off"/>
				</div>
				<div class="form-group col-md-2">
					<label>Tinggi Badan (cm):</label>
					<input type="text" name="tinggi_badan"	class="form-control" id="tinggi_badan" autocomplete="off"/>
				</div>
				<div class="form-group col-md-2">
					<label>Berat Badan (kg):</label>
					<input type="text" name="berat_badan" class="form-control" id="berat_badan" autocomplete="off"/>
				</div>
			</div>
			
			<div class="form-group">
				<label>Pemeriksaan :</label>
				<textarea class="form-control" rows="3" name="pemeriksaan"
					id="pemeriksaan"></textarea>
			</div>
			<div class="form-group">
				<label>Diagnosa :</label>
				<div class="row">
					<div class="col-md-6">
						<table class="table table-striped table-advance table-hover">
							<thead style="background-color: #68DFF0;">
								<tr>
									<th>Diagnosa</th>
									<th>x</th>
								</tr>
							</thead>
							<tbody id="tabel-diagnosa">
							</tbody>
						</table>
					</div>
					<div class="col-md-6">
						<input type="text" name="cari_diagnosa" class="form-control"
							id="cari_diagnosa" placeholder="Pencarian Diagnosa"
							autocomplete="off" />
						<div id="button-diagnosa"></div>
						<div class="btn-group btn-group-justified" id="navigasi-diagnosa"></div>
					</div>
				</div>
			</div>
			<label>Terapi :</label>
			<div class="row">
				<div class="col-md-6">
					<table class="table table-striped table-advance table-hover">
						<thead style="background-color: #68DFF0;">
							<tr>
								<th>Terapi</th>
								<th>Jumlah</th>
								<th>Biaya</th>
								<th>Info</th>
								<th>-</th>
							</tr>
						</thead>
						<tbody id="tabel-terapi">
						</tbody>
					</table>
				</div>
				<div class="col-md-6">
					<input type="text" name="cari-terapi" class="form-control"
						id="cari-terapi" placeholder="Pencarian" autocomplete="off" />
					<div id="button-terapi"></div>
					<div class="btn-group btn-group-justified" id="navigasi-obat">

					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="button-keluar-modal" data-dismiss="modal">Keluar</button>
			<input type="hidden" name="id" class="form-control" id="ids" /> <a
				href="#" class="btn btn-primary" id="button-cek-stok">Simpan</a> <input
				type="submit" class="btn btn-primary btnHide" id="button-simpan"
				value="Simpan" />
		</div>
	</form>
</div>

<div class="modal container fade" id="detail-modal" tabindex="-1"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Detail Rekam Medis</h4>
	</div>
	<form class="form style-form">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>Kunjungan:</label> <input type="text" name="kunjungan"
							class="form-control" id="kunjungan-detail" readonly="readonly" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Tanggal:</label> <input type="text" name="tanggal"
							class="form-control datePicker" id="tanggal-detail"
							autocomplete="off" readonly="readonly" />
					</div>
				</div>

				<div class="col-md-3">
					<div class="form-group">
						<label>Nomor:</label> <input type="text" name="nomor"
							class="form-control" id="nomor-detail" readonly="readonly" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label>Anamnesa :</label>
				<textarea class="form-control" rows="3" name="anamnesa"
					id="anamnesa-detail" readonly="readonly"></textarea>
			</div>
			
			<div class="row">
				<div class="form-group col-md-2">
					<label>Tekanan Darah (mmHg):</label>
					<input type="text" name="tekanan_darah"	class="form-control" id="tekanan_darah_detail" autocomplete="off" readonly="readonly"/>
				</div>
				<div class="form-group col-md-2">
					<label>Denyut Nadi (kali/menit):</label>
					<input type="text" name="nadi" class="form-control" id="nadi_detail" autocomplete="off" readonly="readonly"/>
				</div>
				<div class="form-group col-md-2">
					<label>Pernafasan (kali/menit):</label>
					<input type="text" name="pernafasan" class="form-control" id="pernafasan_detail" autocomplete="off" readonly="readonly"/>
				</div>
				<div class="form-group col-md-2">
					<label>Suhu Tubuh (celcius):</label>
					<input type="text" name="suhu_tubuh" class="form-control" id="suhu_tubuh_detail" autocomplete="off" readonly="readonly"/>
				</div>
				<div class="form-group col-md-2">
					<label>Tinggi Badan (cm):</label>
					<input type="text" name="tinggi_badan"	class="form-control" id="tinggi_badan_detail" autocomplete="off" readonly="readonly"/>
				</div>
				<div class="form-group col-md-2">
					<label>Berat Badan (kg):</label>
					<input type="text" name="berat_badan" class="form-control" id="berat_badan_detail" autocomplete="off" readonly="readonly"/>
				</div>
			</div>
			
			<div class="form-group">
				<label>Pemeriksaan :</label>
				<textarea class="form-control" rows="3" name="pemeriksaan"
					id="pemeriksaan-detail" readonly="readonly"></textarea>
			</div>
			<div class="form-group">
				<label>Diagnosa :</label>
				<table class="table table-striped table-advance table-hover">
					<thead style="background-color: #68DFF0;">
						<tr>
							<th>Diagnosa</th>
						</tr>
					</thead>
					<tbody id="tabel-diagnosa-detail">
					</tbody>
				</table>
			</div>
			<label>Terapi :</label>
			<table class="table table-striped table-advance table-hover">
				<thead style="background-color: #FFD777;">
					<tr>
						<th>Terapi</th>
						<th>Jumlah</th>
						<th>Info</th>
					</tr>
				</thead>
				<tbody id="tabel-terapi-detail">
				</tbody>
			</table>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="button-keluar-modal" data-dismiss="modal">Keluar</button>
		</div>
	</form>
</div>

<div class="modal fade" id="edit-terapi-modal" tabindex="-1"
	style="display: none;" data-width="600">
	<form class="form style-form formEdit" method="post">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel">Edit Terapi</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Obat:</label> <input type="text" class="form-control"
							name="edit_obat" id="edit_obat" readonly="readonly"
							autocomplete="off">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-2">
					<label>Jumlah:</label> <input type="text"
						class="form-control input-angka" name="edit_jumlah"
						id="edit_jumlah" autocomplete="off">
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label>Harga:</label> <input type="text"
							class="form-control input-angka" name="edit_harga"
							id="edit_harga" autocomplete="off">
					</div>
				</div>

				<div class="col-md-5">
					<div class="form-group">
						<label>Total:</label> <input type="text"
							class="form-control input-angka" name="edit_total"
							id="edit_total" readonly="readonly" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label>Info:</label> <input type="text" class="form-control"
						name="edit_info" id="edit_info" autocomplete="off">
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<input type="hidden" class="form-control" name="edit_id" id="edit_id" />
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal" id="edit_keluar">Keluar</button>
			<input type="submit" class="btn btn-primary" value="UPDATE"
				id="update-obat" />
		</div>
	</form>
</div>

<div class="modal fade" id="rm-modal-hapus" tabindex="-1"
	style="display: none;" data-width="300">

	<div class="modal-header">
		<h4 class="modal-title" id="myModalLabel">Hapus Rekam Medis</h4>
	</div>
	<div class="modal-body" style="text-align: center;">
		<p>Apakah Anda Yakin Ingin Menghapus ?</p>
	</div>
	<form class="form style-form formHapus" method="post">
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="keluarModalHapus" data-dismiss="modal">Tidak</button>
			<form:hidden path="id" cssClass="form-control" id="hapusId" />
			<input type="submit" class="btn btn-danger" value="Hapus" />
		</div>
	</form>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	var randomId = Math.floor(Math.random() * 1000000);
	var nomorRM = null;
	var state = 1;
	var findObat = '';
	var halamanObat = 1;

	$(document).ready(function() {
		refresh(1, '', '');

		$('#tanggal').val("${tanggalHariIni}");

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val(), $('#stringTanggalCari').val());
		});

		$('.btnEdit').click(function() {
			state = 1;
		});

		$('#button-cek-stok').click(function() {
			cekStokPerObat($("#nomor").val());
		});

		$("#cari-terapi").keyup(function() {
			refreshObat(1, $('#cari-terapi').val());
		});

		$("#cari_diagnosa").keyup(function() {
			refreshDiagnosa(1, $('#cari_diagnosa').val());
		});

		$('#rm-baru').click(function() {
			// 			rekamMedisBaru();
			var idPasien = $('#pasienId').val();
			getKunjungan(idPasien);
			state = 0;
			reset();
			refreshObat(1, '');
			refreshDiagnosa(1, '');
			refreshDaftarTerapi($('#nomor').val());
			refreshDaftarDiagnosa($('#nomor').val());
		});

		$('#button-keluar-modal').click(function() {
			hapusTempObat($('#nomor').val());
			reset();
		});

		$('#edit_keluar').click(function() {
		});

		$(".formTambah").validate({
			rules : {
				nomor : {
					required : true
				},
				kunjungan : {
					required : true
				},
				tanggal : {
					required : true
				},
				anamnesa : {
					required : true
				},
				pemeriksaan : {
					required : true
				}
			},
			messages : {
				nomor : "Nomor Wajib Diisi",
				kunjungan : "Kunjungan Wajib Diisi",
				tanggal : "Tanggal Wajib Diisi",
				anamnesa : "Anamnesa Wajib Diisi",
				pemeriksaan : "Pemeriksaan Wajib Diisi"
			},
			submitHandler : function(form) {
				var data = {};
				data = setRekamMedis(data);
				if (state == 0) {
					$.postJSON('${tambahUrl}', data, function() {
						$('#gritter-tambah-sukses').click();
						$('.btnKeluar').click();
						reset();
						refresh();
						randomId = Math.floor(Math.random() * 1000000);
					}, function() {
						$('#gritter-tambah-gagal').click();
					});
				} else if (state == 1) {
					$.postJSON('${editUrl}', data, function() {
						$('#gritter-edit-sukses').click();
						reset();
						$('.btnKeluar').click();
						refresh();
					}, function() {
						$('#gritter-edit-gagal').click();
					});
				}
			}
		});

		$(".formEdit").validate({
			rules : {
				edit_id : {
					required : true
				},
				edit_obat : {
					required : true
				},
				edit_jumlah : {
					required : true
				},
				edit_harga : {
					required : true
				},
				edit_total : {
					required : true
				}
			},
			messages : {
				edit_id : {
					required : "ID Wajib Diisi"
				},
				edit_obat : {
					required : "Obat Wajib Diisi"
				},
				edit_jumlah : {
					required : "Jumlah Wajib Diisi"
				},
				edit_harga : {
					required : "Harga Wajib Diisi"
				},
				edit_total : {
					required : "Total Wajib Diisi"
				}
			},
			submitHandler : function(form) {
				var data = {};
				data = setEditTerapi(data);
				$.postJSON('${editTerapiUrl}', data, function() {
					$('#edit_keluar').click();
					refreshDaftarTerapi($('#nomor').val());
				}, function() {
				});
			}
		});

		$(".formHapus").submit(function(e) {
			e.preventDefault();
			var data = {};
			data['id'] = $('#hapusId').val();
			$.postJSON('${hapusUrl}', data, function(result) {
				refresh();
				console.log(result.info);
				$('#keluarModalHapus').click();
				$('#gritter-hapus-sukses').click();
			}, function(e) {
				console.log(result.info);
				$('#gritter-hapus-sukses').click();
				$('#keluarModalHapus').click();
				refresh();
			});
		});

		setMaskingUang("#edit_jumlah");
		setMaskingUang("#edit_harga");
		setMaskingUang("#edit_total");
		hitungHargaTotalOnKeyUp("#edit_jumlah", "#edit_total");
		hitungHargaTotalOnKeyUp("#edit_harga", "#edit_total");
	});

	function getData(ids, s) {
		reset();
		refreshObat(1, '');
		refreshDiagnosa(1, '');
		var data = {
			id : ids,
			status : s
		};
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#anamnesa').val(result.anamnesa);
			$('#tekanan_darah').val(result.tekananDarah);
			$('#nadi').val(result.nadi);
			$('#pernafasan').val(result.pernafasan);
			$('#suhu_tubuh').val(result.suhuTubuh);
			$('#tinggi_badan').val(result.tinggiBadan);
			$('#berat_badan').val(result.beratBadan);
			$('#pemeriksaan').val(result.pemeriksaan);
			$('#nomor').val(result.nomor);
			$('#tanggal').val(result.tanggal);
			$('#kunjungan').val(result.kunjungan);
			$('#tabel-terapi').append(result.tabelTerapi);
			$('#tabel-diagnosa').append(result.tabelDiagnosa);
			$('#ids').val(ids);
			state = 1;
		}, null);
	}

	function getDetail(ids) {
		resetDetail();
		var data = {
			id : ids
		};
		$.getAjax('${detailUrl}', data, function(result) {
			$('#anamnesa-detail').val(result.anamnesa);
			$('#pemeriksaan-detail').val(result.pemeriksaan);
			$('#tekanan_darah_detail').val(result.tekananDarah);
			$('#nadi_detail').val(result.nadi);
			$('#pernafasan_detail').val(result.pernafasan);
			$('#suhu_tubuh_detail').val(result.suhuTubuh);
			$('#tinggi_badan_detail').val(result.tinggiBadan);
			$('#berat_badan_detail').val(result.beratBadan);
			$('#nomor-detail').val(result.nomor);
			$('#tanggal-detail').val(result.tanggal);
			$('#kunjungan-detail').val(result.kunjungan);
			$('#tabel-terapi-detail').append(result.tabelTerapi);
			$('#tabel-diagnosa-detail').append(result.tabelDiagnosa);
		}, null);
	}

	function getKunjungan(ids) {
		var data = {
			id : ids
		};
		$.getAjax('${kunjunganUrl}', data, function(result) {
			$('#kunjungan').val(result.kunjungan);
			$('#nomor').val(result.nomor);
		}, null);
	}

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, find, tanggal) {
		ids = $('#pasienId').val();
		var data = {
			hal : halaman,
			cari : find,
			tgl : tanggal,
			id : ids
		};

		$.getAjax('${daftarUrl}', data, function(result) {
			$('#tabel').empty();
			$('#tabel').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function refreshObat(halaman, find) {
		var data = {
			hal : halaman,
			cari : find,
			n : 15
		};

		$.getAjax('${terapiUrl}', data, function(result) {
			$('#button-terapi').empty();
			$('#button-terapi').append(result.button);
			$('#navigasi-obat').empty();
			$('#navigasi-obat').append(result.navigasiObat);
		}, null);
	}

	function refreshDiagnosa(halaman, find) {
		var data = {
			hal : halaman,
			cari : find,
			n : 7
		};

		$.getAjax('${diagnosaUrl}', data, function(result) {
			$('#button-diagnosa').empty();
			$('#button-diagnosa').append(result.button);
			$('#navigasi-diagnosa').empty();
			$('#navigasi-diagnosa').append(result.navigasiObat);
		}, null);
	}

	function refreshDaftarTerapi(n) {
		if (n != null) {
			var data = {
				nomor : n
			};
			$.getAjax('${daftarTerapiUrl}', data, function(result) {
				$('#tabel-terapi').empty();
				$('#tabel-terapi').append(result.tabelTerapi);
			}, null);
		}
	}

	function refreshDaftarDiagnosa(n) {
		if (n != null) {
			var data = {
				nomor : n
			};
			$.getAjax('${daftarDiagnosaUrl}', data, function(result) {
				$('#tabel-diagnosa').empty();
				$('#tabel-diagnosa').append(result.tabelTerapi);
			}, null);
		}
	}

	function tambahObat(id) {
		var data = {
			idObat : id,
			nomor : $("#nomor").val(),
			randomId : randomId
		};
		$.postJSON('${tambahTerapiUrl}', data, function(result) {
			refreshDaftarTerapi($('#nomor').val());
		}, function() {
			console.log(result.info);
			$('#gritter-tambah-gagal').click();
		});
	}

	function editTerapi(id) {
		resetEditObatData();
		var data = {
			idObat : id,
			nomor : $("#nomor").val()
		};
		$.getAjax('${dapatkanTerapiUrl}', data, function(result) {
			$('#edit_obat').val(result.terapi);
			$('#edit_jumlah').val(result.jumlah);
			$('#edit_harga').val(result.hargaJual);
			$('#edit_total').val(result.hargaTotal);
			$('#edit_info').val(result.info);
			$('#edit_id').val(result.idObat);
		}, null);
	}

	function tambahDiagnosa(id) {
		var data = {
			idDiagnosa : id,
			nomor : $("#nomor").val(),
			randomId : randomId
		};
		$.postJSON('${tambahDiagnosaUrl}', data, function(result) {
			// 			console.log(result.nomor);
			refreshDaftarDiagnosa($('#nomor').val());
		}, function() {
			console.log(result.info);
			$('#gritter-tambah-gagal').click();
		});
	}

	function hapusTempObat(nomor) {
		var data = {
			nomor : nomor
		};
		$.postJSON('${hapusTempUrl}', data, function(result) {
		}, function() {
			console.log(result.info);
			$('#gritter-tambah-gagal').click();
		});
	}

	function hapusObat(id) {
		var data = {
			idObat : id,
			nomor : $('#nomor').val()
		};
		$.postJSON('${hapusTerapiUrl}', data, function(result) {
			refreshDaftarTerapi(data.nomor);
			console.log(result.info);
		}, function() {
			console.log(result.info);
		});
	}

	function hapusDiagnosa(id) {
		var data = {
			idDiagnosa : id,
			nomor : $('#nomor').val()
		};
		$.postJSON('${hapusDiagnosaUrl}', data, function(result) {
			refreshDaftarDiagnosa(data.nomor);
			console.log(result.info);
		}, function() {
			console.log(result.info);
		});
	}

	function cekStokPerObat(rid) {
		var data = {
			nomor : rid
		};
		$.getAjax('${cekStokListObatUrl}', data, function(result) {
			if (result.info != "OKE") {
				$('#pesan').empty();
				$('#pesan').append(result.info);
				console.log(result.info);
				$('#pesan-modal').modal('show');
			} else {
				// 				document.getElementById('formRekamMedis').submit();
				$("#button-simpan").click();
			}
		}, null);
	}

	function rekamMedisBaru() {
		var data = {
			info : $('#pasienId').val()
		};
		$.postJSON('${baruUrl}', data, function(result) {
			$('#nomor').val(result.nomor);
			nomorRM = result.nomor;
		}, function() {
			$('#gritter-tambah-gagal').click();
		});
	}

	function prosesResep(n) {
		var data = {
			id : n
		};
		$.postJSON('${prosesResepUrl}', data, function(result) {
			refresh(1, '', '');
		}, function() {
			refresh(1, '', '');
		});
	}

	function batalProsesResep(n) {
		var data = {
			id : n
		};
		$.postJSON('${batalProsesResepUrl}', data, function(result) {
			refresh(1, '', '');
		}, function() {
			refresh(1, '', '');
		});
	}

	function setRekamMedis(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['nomor'] = $('#nomor').val();
		data['kunjungan'] = $('#kunjungan').val();
		data['tanggal'] = $('#tanggal').val();
		data['anamnesa'] = $('#anamnesa').val();
		data['tekananDarah'] = $('#tekanan_darah').val();
		data['pernafasan'] = $('#pernafasan').val();
		data['nadi'] = $('#nadi').val();
		data['suhuTubuh'] = $('#suhu_tubuh').val();
		data['tinggiBadan'] = $('#tinggi_badan').val();
		data['beratBadan'] = $('#berat_badan').val();
		data['pemeriksaan'] = $('#pemeriksaan').val();
		data['pasien'] = $('#pasienId').val();
		return data;
	}

	function setEditTerapi(data) {
		data['nomor'] = $('#nomor').val();
		data['idObat'] = $('#edit_id').val();
		data['terapi'] = $('#edit_obat').val();
		data['jumlah'] = $('#edit_jumlah').val();
		data['info'] = $('#edit_info').val();
		data['hargaJual'] = $('#edit_harga').val();
		return data;
	}

	function resetEditObatData() {
		$('#edit_obat').val('');
		$('#edit_jumlah').val('0');
		$('#edit_harga').val('0');
		$('#edit_total').val('0');
		$('#edit_info').val('');
		$('#edit_id').val('');
	}

	function reset() {
		$('#nomor').val('');
		$('#kunjungan').val('');
		$('#tanggal').val(tanggalHariIni());
		$('#anamnesa').val('');
		$('#tekanan_darah').val('');
		$('#pernafasan').val('');
		$('#nadi').val('');
		$('#suhu_tubuh').val('');
		$('#tinggi_badan').val('');
		$('#berat_badan').val('');
		$('#pemeriksaan').val('');
		$('#tabel-terapi').empty();
		$('#tabel-diagnosa').empty();
	}

	function resetDetail() {
		$('#nomor-detail').val('');
		$('#kunjungan-detail').val('');
		$('#tanggal-detail').val(tanggalHariIni());
		$('#anamnesa-detail').val('');
		$('#pemeriksaan-detail').val('');
		$('#tabel-terapi-detail').empty();
		$('#tabel-diagnosa-detail').empty();
	}

	function hitungHargaTotalOnKeyUp(origin, hargaTotal) {
		$(origin).keyup(
				function() {
					$(hargaTotal).val(
							hitungHargaTotalPerObat("#edit_harga",
									"#edit_jumlah"));
				});
	}

	function hitungHargaTotalPerObat(hrg, jum) {
		if ($(hrg).val() != '' && $(jum).val() != '') {
			var hargaBeli = parseFloat($(hrg).val().replace(/\./g, ''));
			var jumlah = parseInt($(jum).val().replace(/\./g, ''), 10);
			var hargaTotal = hargaBeli * jumlah;
			return hargaTotal;
		}
	}
</script>