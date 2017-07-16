<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="baruUrl" value="/rekammedis/baru" />
<c:url var="tambahUrl" value="/rekammedis/tambah" />
<c:url var="editUrl" value="/rekammedis/edit" />
<c:url var="hapusUrl" value="/rekammedis/hapus" />

<c:url var="dapatkanUrl" value="/rekammedis/dapatkan" />
<c:url var="daftarUrl" value="/rekammedis/daftar" />

<c:url var="tambahTerapiUrl" value="/rekammedis/tambah-terapi" />
<c:url var="daftarTerapiUrl" value="/rekammedis/daftar-terapi" />
<c:url var="hapusTerapiUrl" value="/rekammedis/hapus-terapi" />

<c:url var="terapiUrl" value="/obat/obat-tindakan" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<form class="form-horizontal style-form" method="get">
				<div class="form-group">
					<label class="col-sm-1 control-label">ID</label>
					<div class="col-sm-1">
						<input type="text" class="form-control" value="${pasien.id}" id="pasienId" readonly="readonly">
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
						<input type="text" class="form-control"
							value="${pasien.jenisKelamin}" readonly="readonly">
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
							value="${pasien.nomorJaminan}" readonly="readonly">
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
									onclick="refresh(1,'')">Reset</button>
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

<div class="modal fade" id="rm-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Rekam Medis Baru</h4>
			</div>
			<form class="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Nomor:</label> <input type="text" name="nomor"
										class="form-control" id="nomor" readonly="readonly" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Tanggal:</label> <input type="text" name="tanggal"
										class="form-control datePicker" id="tanggal"
										style="width: 300px" value="${tanggalHariIni}" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label>Anamnesa :</label>
							<textarea class="form-control" rows="3" name="anamnesa"
								id="anamnesa"></textarea>
						</div>
						<div class="form-group">
							<label>Pemeriksaan :</label>
							<textarea class="form-control" rows="3" name="pemeriksaan"
								id="pemeriksaan"></textarea>
						</div>
						<div class="form-group">
							<label>Diagnosa :</label>
							<textarea class="form-control" rows="3" name="diagnosa"
								id="diagnosa"></textarea>
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
											<th>x</th>
										</tr>
									</thead>
									<tbody id="tabel-terapi">
									</tbody>
								</table>
							</div>
							<div class="col-md-6">
								<input type="text" name="cari-terapi" class="form-control"
									id="cari-terapi" placeholder="Pencarian" />
								<div id="list-terapi"></div>
								<div class="btn-group btn-group-justified" id="navigasi-obat">

								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<input type="hidden" name="id" class="form-control" id="ids" /> 
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="rm-modal-hapus" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">				
				<h4 class="modal-title" id="myModalLabel">Hapus Rekam Medis</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Yakin Ingin Menghapus ?</p>
				</div>
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
	</div>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	var nomorRM = null;
	var state = 1;
	$(document).ready(function() {
		refresh(1, '', '');		

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val(), $('#stringTanggalCari').val());
		});
		
		$('.btnKeluar').click(function() {
			state = 1;
		});

		$('.btnEdit').click(function() {
			state = 1;			
		});

		$("#cari-terapi").keyup(function() {
			refreshObat(1, $('#cari-terapi').val());
		});

		$('#rm-baru').click(function() {
			rekamMedisBaru();
			state = 0;
			reset();
			refreshObat(1, '');
		});

		$(".formTambah").validate({
			rules : {
				nomor : {
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
				},
				diagnosa : {
					required : true
				}
			},
			messages : {
				nomor : "Nomor Wajib Diisi",
				tanggal : "Tanggal Wajib Diisi",
				anamnesa : "Anamnesa Wajib Diisi",
				pemeriksaan : "Pemeriksaan Wajib Diisi",
				diagnosa : "Diagnosa Wajib Diisi"
			},
			submitHandler : function(form) {
				var data = {};
				data = setRekamMedis(data);
				if (state == 0){
					$.postJSON('${tambahUrl}', data, function() {
						$('#gritter-tambah-sukses').click();
						$('.btnKeluar').click();
						reset();
						refresh();
					}, function() {
						$('#gritter-tambah-gagal').click();
					});
				} else if (state == 1){
					$.postJSON('${editUrl}', data, function() {
						$('#gritter-edit-sukses').click();
						reset();
						$('.btnKeluar').click();
						refresh();
					}, function() {
						$('#gritter-edit-gagal').click();
					});
				}				
				state = 1;				
			}
		});

		$(".formHapus").submit(function(e) {
			e.preventDefault();
			var data = {};
			data['id'] = $('#hapusId').val();
			$.postJSON('${hapusUrl}', data, function(result) {
				refresh();
				$('#keluarModalHapus').click();
				$('#gritter-hapus-sukses').click();
			}, function(e) {
				$('#gritter-hapus-sukses').click();
				$('#keluarModalHapus').click();
				refresh();
			});
		});
	});
	
	function getData(ids) {
		reset();
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#anamnesa').val(result.anamnesa);
			$('#diagnosa').val(result.diagnosa);
			$('#pemeriksaan').val(result.pemeriksaan);
			$('#nomor').val(result.nomor);
			$('#tanggal').val(result.tanggal);
			$('#tabel-terapi').append(result.tabelTerapi);
			$('#ids').val(ids);			
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
			cari : find
		};

		$.getAjax('${terapiUrl}', data, function(result) {
			$('#list-terapi').empty();
			$('#list-terapi').append(result.button);
			$('#navigasi-obat').empty();
			$('#navigasi-obat').append(result.navigasiObat);
		}, null);
	}

	function refreshDaftarTerapi(n) {
		if (nomorRM != null) {
			var data = {
				nomor : n
			};

			$.getAjax('${daftarTerapiUrl}', data, function(result) {
				$('#tabel-terapi').empty();
				$('#tabel-terapi').append(result.tabelTerapi);
			}, null);
		}

	}

	function tambahObat(id) {
		var data = {
			idObat : id,
			nomor : nomorRM
		};
		$.postJSON('${tambahTerapiUrl}', data, function(result) {
			// 			console.log(result.nomor);
			refreshDaftarTerapi(nomorRM);
		}, function() {
			console.log(result.info);
			$('#gritter-tambah-gagal').click();
		});
	}

	function hapusObat(id) {
		var data = {
			idObat : id,
			nomor : nomorRM
		};
		$.postJSON('${hapusTerapiUrl}', data, function(result) {
			refreshDaftarTerapi(nomorRM);
			console.log(result.info);
		}, function() {
			console.log(result.info);
		});
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
	
	function setRekamMedis(data){
		if ($('#ids').val() != null && $('#ids').val() != ''){
			data['id'] = $('#ids').val();	
		}		
		data['nomor'] = $('#nomor').val();
		data['tanggal'] = $('#tanggal').val();
		data['anamnesa'] = $('#anamnesa').val();
		data['pemeriksaan'] = $('#pemeriksaan').val();
		data['diagnosa'] = $('#diagnosa').val();
		data['pasien'] = $('#pasienId').val();
		return data;
	}
	
	function reset(){
		$('#nomor').val('');
		$('#tanggal').val(tanggalHariIni());
		$('#anamnesa').val('');
		$('#pemeriksaan').val('');
		$('#diagnosa').val('');
		$('#tabel-terapi').empty();
	}
</script>