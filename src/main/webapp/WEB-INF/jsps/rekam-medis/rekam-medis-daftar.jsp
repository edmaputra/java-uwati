<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="baruUrl" value="/rekammedis/baru" />
<c:url var="tambahUrl" value="/rekammedis/tambah" />
<c:url var="editUrl" value="/rekammedis/edit" />
<c:url var="hapusUrl" value="/rekammedis/hapus" />
<c:url var="dapatkanUrl" value="/rekammedis/dapatkan" />
<c:url var="daftarUrl" value="/rekammedis/daftar" />
<c:url var="terapiUrl" value="/obat/obat-tindakan" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<form class="form-horizontal style-form" method="get">
				<div class="form-group">
					<input type="hidden" class="form-control" value="${pasien.id}"
						id="pasienId"> <label class="col-sm-1 control-label">Nama</label>
					<div class="col-sm-5">
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
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Rekam Medis Baru</h4>
			</div>
			<form class="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Nomor:</label> <input type="text" name="nomor"
										class="form-control" id="nomor" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Tanggal:</label> <input type="text" name="tanggal"
										class="form-control datePicker" id="tanggal"
										style="width: 300px" />
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
								<table class="table table-striped table-advance table-hover"
									id="tabel-terapi">
									<thead style="background-color: #68DFF0;">
										<tr>
											<th>Terapi</th>
											<th>Jumlah</th>
											<th>Biaya</th>
											<th>x</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="col-md-6">
								<input type="text" name="cari-terapi" class="form-control" id="cari-terapi" placeholder="Pencarian"/>
								<div id="list-terapi">
									
								</div>
								<div class="btn-group btn-group-justified" id="navigasi-obat">
								
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<input type="hidden" name="id" class="form-control" id="ids" /> <input
						type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="role-modal-edit" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Edit Role</h4>
			</div>

			<form class="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="form-group">
							<label>Anamnesa</label>
							<textarea class="form-control" rows="5" name="anamnesa"
								id="anamnesa"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="role-modal-hapus" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Yakin Ingin Menghapus ?</p>
				</div>
			</div>

		</div>
	</div>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	function getData(ids) {
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#editNama').val(result.nama);
			$('#editId').val(ids);
		}, null);
	}

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, find, tanggal) {
		var data = {
			hal : halaman,
			cari : find,
			tgl : tanggal
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
	
	function tambahObat(id){
		console.log('ID Obat : '+id);
	}

	$(document).ready(function() {	
		refresh(1, '', '');
		refreshObat(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val(), $('#stringTanggalCari').val());
		});
		
		$("#cari-terapi").keyup(function(){
			refreshObat(1, $('#cari-terapi').val());
	    });
		
		$('#rm-baru').click(function() {
			data['anamnesa'] = 'a';
			$.postJSON('${baruUrl}', data, function() {
			}, function() {
				$('#gritter-tambah-gagal').click();
			});
		});

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi"
			},
			submitHandler : function(form) {
				var str = {};
				str['nama'] = $('#tambahNama').val();
				$.postJSON('${tambahUrl}', str, function() {
					$('#gritter-tambah-sukses').click();
					$('.btnKeluar').click();
					$('#tambahNama').val('');
					refresh();
				}, function() {
					$('#gritter-tambah-gagal').click();
				});
			}
		});

		$(".formEdit").validate({
			rules : {
				nama : {
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi"
			},
			submitHandler : function(form) {
				var str = {};
				str['nama'] = $('#editNama').val();
				str['id'] = $('#editId').val();
				$.postJSON('${editUrl}', str, function() {
					$('#gritter-edit-sukses').click();
					$('.btnKeluar').click();
					refresh();
				}, function() {
					$('#gritter-edit-gagal').click();
				});
			}
		});

		$(".formHapus").submit(function(e) {
			e.preventDefault();
			var str = {};
			str['id'] = $('#hapusId').val();
			$.postJSON('${hapusUrl}', str, function(result) {
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
</script>