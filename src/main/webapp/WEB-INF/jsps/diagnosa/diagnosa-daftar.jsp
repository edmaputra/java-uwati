<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambah" value="/diagnosa/tambah" />
<c:url var="edit" value="/diagnosa/edit" />
<c:url var="hapus" value="/diagnosa/hapus" />
<c:url var="dapatkan" value="/diagnosa/dapatkan" />
<c:url var="daftar" value="/diagnosa/daftar" />

<div class="row">
	<div class="col-lg-12">
		<div class="content-panel">
			<div class="row kolom-pencarian">
				<div class="col-md-2">
					<button class="btn btn-primary btnTambah" data-toggle="modal"
						data-target="#diagnosa-modal">Diagnosa Baru</button>
				</div>
				<div class="col-md-10">
					<form class="form-inline pull-right" id="formCari">
						<div class="form-group">
							<input type="text" id="stringCari" class="form-control"
								placeholder="Pencarian" style="width: 150px" />
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-primary" id="btnCari"
								style="width: 80px">Cari</button>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-default" id="btnReset">Reset</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="content-panel">

			<table class="table table-striped table-advance table-hover"
				id="tabel">
			</table>
			<div id="nav"></div>
		</div>
	</div>
</div>


<div class="modal fade" id="diagnosa-modal" tabindex="-1"
	style="display: none;" data-focus-on="input:first">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Diagnosa</h4>
	</div>

	<form class="form style-form formTambah" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="form-group col-md-3">
					<label>Kode:</label> <input type="text" name="kode"
						class="form-control" id="kode" autocomplete="off" />
				</div>
				<div class="form-group col-md-9">
					<label>Nama:</label> <input type="text" name="nama"
						class="form-control" id="nama" autocomplete="off" />
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

<div class="modal fade" id="diagnosa-modal-hapus" tabindex="-1"
	style="display: none;" data-width="300">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Hapus Tindakan</h4>
	</div>
	<div class="modal-body" style="text-align: center;">
		<p>Apakah Anda Yakin Ingin Menghapus ?</p>
	</div>
	<form class="form-horizontal style-form formHapus" method="post">
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="keluarModalHapus" data-dismiss="modal">Tidak</button>
			<input type="hidden" name="id" class="form-control" id="hapusId" />
			<input type="submit" class="btn btn-danger" value="Hapus" />
		</div>
	</form>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	var state = 1;
	$(document).ready(function() {
		refresh(1, '');

		$('#stringCari').on('keypress', function(e) {
			if (e.which === 13) {
				$('#btnCari').click();
			}
		});

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$('#btnReset').click(function() {
			refresh(1, '');
		});

		$('.btnTambah').click(function() {
			state = 0;
		});

		$('.btnKeluar').click(function() {
			reset();
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
				var data = {};
				data = setData(data);
				if (state == 0) {
					$.postJSON('${tambah}', data, function() {
						$('#gritter-tambah-sukses').click();
						$('.btnKeluar').click();
						refresh();
					}, function() {
						$('#gritter-tambah-gagal').click();
					});
				} else if (state == 1) {
					$.postJSON('${edit}', data, function() {
						$('#gritter-edit-sukses').click();
						$('.btnKeluar').click();
						refresh();
					}, function() {
						$('#gritter-edit-gagal').click();
					});
				}
			}
		});

		$(".formHapus").submit(function(e) {
			e.preventDefault();
			var data = {};
			data['id'] = $('#hapusId').val();
			$.postJSON('${hapus}', data, function(result) {
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
		$.getAjax('${dapatkan}', data, function(result) {
			$('#nama').val(result.nama);
			$('#kode').val(result.kode);
			$('#ids').val(ids);
			state = 1;
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

		$.getAjax('${daftar}', data, function(result) {
			$('#tabel').empty();
			$('#tabel').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function setData(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['nama'] = $('#nama').val();
		data['kode'] = $('#kode').val();
		return data;
	}

	function reset() {
		$('#ids').val('');
		$('#nama').val('');
		$('#kode').val('');
	}
</script>