<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/pelanggan/tambah" />
<c:url var="editUrl" value="/pelanggan/edit" />
<c:url var="hapusUrl" value="/pelanggan/hapus" />
<c:url var="dapatkanUrl" value="/pelanggan/dapatkan" />
<c:url var="daftarUrl" value="/pelanggan/daftar" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<div class="content-panel">
				<div class="row">
					<div class="col-md-2 ">
						<security:authorize access="hasAnyRole('ADMIN')">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#pelanggan-modal">Pelanggan Baru</button>
						</security:authorize>
					</div>

					<div class="col-md-10">
						<form class="form-inline pull-right" id="formCari">
							<div class="form-group">
								<input type="text" id="stringCari" class="form-control" placeholder="Pencarian" style="width: 250px" />
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-primary" id="btnCari">Cari</button>
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-default" id="btnReset" onclick="refresh(1,'')">Reset</button>
							</div>						
						</form>
					</div>
				</div>
				<br />

				<table class="table table-striped table-advance table-hover" id="tabel">
				</table>
				<div id="nav"></div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="pelanggan-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Pelanggan Baru</h4>
			</div>

			<form class="form-horizontal style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Kode:</label>
									<input type="text" name="kode" class="form-control" id="tambahKode" autocomplete="off" />									
								</div>
							</div>
							<div class="col-md-8">
								<div class="form-group">
									<label>Nama:</label>
									<input type="text" name="nama" class="form-control" id="tambahNama" autocomplete="off" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-7">
								<div class="form-group">
									<label>Alamat:</label>
									<input type="text" name="alamat" class="form-control" id="tambahAlamat" autocomplete="off" />
								</div>
								
							</div>
							<div class="col-md-5">
								<div class="form-group">
									<label>Kontak:</label>
									<input type="text" name="kontak" class="form-control" id="tambahKontak" autocomplete="off" />
								</div>
							</div>							
						</div>	
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar" data-dismiss="modal">Keluar</button>
					<input type="hidden" name="id" class="form-control" id="tambahId" autocomplete="off" />					
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="pelanggan-modal-edit" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Edit Pelanggan</h4>
			</div>
			<form class="form-horizontal style-form formEdit" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Kode:</label>
									<input type="text" name="kode" class="form-control" id="editKode" autocomplete="off" />									
								</div>
							</div>
							<div class="col-md-8">
								<div class="form-group">
									<label>Nama:</label>
									<input type="text" name="nama" class="form-control" id="editNama" autocomplete="off" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-7">
								<div class="form-group">
									<label>Alamat:</label>
									<input type="text" name="alamat" class="form-control" id="editAlamat" autocomplete="off" />
								</div>
								
							</div>
							<div class="col-md-5">
								<div class="form-group">
									<label>Kontak:</label>
									<input type="text" name="kontak" class="form-control" id="editKontak" autocomplete="off" />									
								</div>
							</div>							
						</div>					
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar" data-dismiss="modal">Keluar</button>
					<input type="hidden" name="id" class="form-control" id="editId" autocomplete="off" />					
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="pelanggan-modal-hapus" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Pelanggan</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Yakin Ingin Menghapus ?</p>
				</div>
			</div>
			<form  class="form-horizontal style-form formHapus" method="post">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						id="keluarModalHapus" data-dismiss="modal">Tidak</button>
					<input type="hidden" name="id" class="form-control" id="hapusId" autocomplete="off" />					
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
	function getData(ids) {
		var data = {id:ids};		
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#editNama').val(result.nama);
			$('#editKontak').val(result.kontak);
			$('#editKode').val(result.kode);
			$('#editAlamat').val(result.alamat);
			$('#editId').val(ids);
		}, null);
	}

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, find) {
		var data = { hal : halaman, cari : find };

		$.getAjax('${daftarUrl}', data, function(result) {
			$('#tabel').empty();
			$('#tabel').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	$(document).ready(function() {
		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				kode:{
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				kode : "Kode Wajib Diisi"
			},
			submitHandler : function(form) {
				var str = {};
				str['nama'] = $('#tambahNama').val();
				str['kode'] = $('#tambahKode').val();
				str['kontak'] = $('#tambahKontak').val();
				str['alamat'] = $('#tambahAlamat').val();
				$.postJSON('${tambahUrl}', str, function() {
					$('#gritter-tambah-sukses').click();
					$('.btnKeluar').click();
					$('#tambahNama').val('');
					$('#tambahKontak').val('');
					$('#tambahKode').val('');
					$('#tambahAlamat').val('');
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
				},
				spesialis:{
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				spesialis : "Spesialis Wajib Diisi"
			},
			submitHandler : function(form) {
				var str = {};
				str['id'] = $('#editId').val();
				str['kode'] = $('#editKode').val();
				str['nama'] = $('#editNama').val();
				str['kontak'] = $('#editKontak').val();
				str['alamat'] = $('#editAlamat').val();
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