<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/kategori-pasien/tambah" />
<c:url var="editUrl" value="/kategori-pasien/edit" />
<c:url var="hapusUrl" value="/kategori-pasien/hapus" />
<c:url var="dapatkanUrl" value="/kategori-pasien/dapatkan" />
<c:url var="daftarUrl" value="/kategori-pasien/daftar" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
			<!-- 			<div class="content-panel"> -->
			<div class="row">
				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN')">
						<button class="btn btn-primary btnTambah" data-toggle="modal"
							data-target="#kategori-modal">Kategori Baru</button>
					</security:authorize>
				</div>

				<div class="col-md-10">
					<form class="form-inline pull-right" id="formCari">
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
				id="tabelCoba">
			</table>
			<div id="nav"></div>
			<!-- 			</div> -->
		</div>
	</div>
</div>

<div class="modal fade" id="kategori-modal" tabindex="-1"
	style="display: none;" data-focus-on="input:first">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Kategori Baru</h4>
	</div>
	<form class="form-horizontal style-form formTambah" method="post">
		<div class="modal-body" style="margin: 8px;">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>Nama:</label> <input type="text" class="form-control"
							id="tambahNama" autocomplete="off" name="tambahNama" /> <input
							type="hidden" class="form-control" id="tambahId" />
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="Simpan" />
		</div>
	</form>
</div>

<div class="modal fade" id="kategori-modal-edit" tabindex="-1"
	style="display: none;" data-focus-on="input:first">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Edit Kategori</h4>
	</div>
	<form class="form-horizontal style-form formEdit" method="post">
		<div class="modal-body" style="margin: 8px;">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>Nama:</label> <input type="text" class="form-control"
							id="editNama" autocomplete="off" name="editNama" /> <input
							type="hidden" class="form-control" id="editId" autocomplete="off" />
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="Simpan" />
		</div>
	</form>
</div>

<div class="modal fade" id="kategori-modal-hapus" tabindex="-1"
	style="display: none;" data-width="300">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Hapus Kategori</h4>
	</div>
	<div class="modal-body" style="text-align: center;">
		<p>Apakah Anda Yakin Ingin Menghapus ?</p>
	</div>
	<form class="form-horizontal style-form formHapus" method="post">
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="keluarModalHapus" data-dismiss="modal">Tidak</button>
			<input type="hidden" class="form-control" id="hapusId" /> <input
				type="submit" class="btn btn-danger" value="Hapus" />
		</div>
	</form>

</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	$(document).ready(function() {
		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$('.btnTambah').click(function() {
			reset();
		});

		$('.btnEdit').click(function() {
			reset();
		});

		$(".formTambah").validate({
			rules : {
				tambahNama : {
					required : true
				}
			},
			messages : {
				tambahNama : "Nama Wajib Diisi"
			},
			submitHandler : function(form) {
				var data = {};
				data['nama'] = $('#tambahNama').val();
				$.postJSON('${tambahUrl}', data, function() {
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
				editNama : {
					required : true
				}
			},
			messages : {
				editNama : "Nama Wajib Diisi"
			},
			submitHandler : function(form) {
				var data = {};
				data['nama'] = $('#editNama').val();
				data['id'] = $('#editId').val();
				$.postJSON('${editUrl}', data, function() {
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

	function refresh(halaman, find) {
		var data = {
			hal : halaman,
			cari : find
		};

		$.getAjax('${daftarUrl}', data, function(result) {
			$('#tabelCoba').empty();
			$('#tabelCoba').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function reset() {
		$('#tambahNama').val('');
		$('#editNama').val('');
		$('#editId').val('');
	}
</script>