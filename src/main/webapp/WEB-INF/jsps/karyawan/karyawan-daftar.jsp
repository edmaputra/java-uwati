<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/karyawan/tambah" />
<c:url var="editUrl" value="/karyawan/edit" />
<c:url var="hapusUrl" value="/karyawan/hapus" />
<c:url var="dapatkanUrl" value="/karyawan/dapatkan" />
<c:url var="daftarUrl" value="/karyawan/daftar" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN')">
						<button class="btn btn-primary btnTambah" data-toggle="modal"
							data-target="#dokter-modal">Karyawan Baru</button>
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
				id="tabel">
			</table>
			<div id="nav"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="dokter-modal" tabindex="-1"
	style="display: none;" data-width="700" data-focus-on="input:first">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Karyawan</h4>
	</div>

	<form class="form style-form formTambah" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-8">
					<div class="form-group">
						<label>Nama:</label> <input type="text" name="nama"
							class="form-control" id="nama" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label>Jabatan:</label> <input type="text" name="jabatan"
							class="form-control" id="jabatan" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-7">
					<div class="form-group">
						<label>Spesialis:</label> <input type="text" name="spesialis"
							class="form-control" id="spesialis" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>SIP:</label> <input type="text" name="sip"
							class="form-control" id="sip" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>Alamat:</label> <input type="text" name="alamat"
							class="form-control" id="alamat" autocomplete="off" />
					</div>
					<input type="hidden" name="id" class="form-control" id="ids"
						autocomplete="off" />
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

<div class="modal fade" id="dokter-modal-hapus" tabindex="-1"
	style="display: none;" data-width="300">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Hapus Karyawan</h4>
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
	var state = 1;
	$(document).ready(function() {
		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$('.btnTambah').click(function() {
			reset();
			state = 0;
		});

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				jabatan : {
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				jabatan : "Jabatan Wajib Diisi"
			},
			submitHandler : function(form) {
				var data = {};
				data = setContent(data);
				if (state == 0) {
					$.postJSON('${tambahUrl}', data, function() {
						$('#gritter-tambah-sukses').click();
						$('.btnKeluar').click();
						reset();
						refresh();
					}, function() {
						$('#gritter-tambah-gagal').click();
					});
				} else if (state == 1) {
					$.postJSON('${editUrl}', data, function() {
						$('#gritter-edit-sukses').click();
						$('.btnKeluar').click();
						reset();
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
		state = 1;
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#nama').val(result.nama);
			$('#spesialis').val(result.spesialis);
			$('#sip').val(result.sip);
			$('#alamat').val(result.alamat);
			$('#jabatan').val(result.jabatan);
			$('#ids').val(ids);
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
			$('#tabel').empty();
			$('#tabel').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function setContent(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['nama'] = $('#nama').val();
		data['spesialis'] = $('#spesialis').val();
		data['sip'] = $('#sip').val();
		data['alamat'] = $('#alamat').val();
		data['jabatan'] = $('#jabatan').val();
		return data;
	}

	function reset() {
		$('#nama').val('');
		$('#spesialis').val('');
		$('#sip').val('');
		$('#jabatan').val('');
		$('#ids').val('');
		$('#alamat').val('');
	}
</script>