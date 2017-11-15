<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambah" value="/tindakan/tambah" />
<c:url var="edit" value="/tindakan/edit" />
<c:url var="hapus" value="/tindakan/hapus" />
<c:url var="dapatkan" value="/tindakan/dapatkan" />
<c:url var="daftar" value="/tindakan/daftar" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN')">
						<button class="btn btn-primary btnTambah" data-toggle="modal"
							data-target="#tindakan-modal">Tindakan Baru</button>
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
		</div>
	</div>
</div>


<div class="modal fade" id="tindakan-modal" tabindex="-1"
	style="display: none;" data-focus-on="input:first">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Tindakan</h4>
	</div>

	<form class="form style-form formTambah" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-9">
					<div class="form-group">
						<label>Nama:</label> <input type="text" name="nama"
							class="form-control" id="nama" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Kode:</label> <input type="text" name="kode"
							class="form-control" id="kode" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label>Tarif/Harga:</label> <input type="text" name="tarif"
							class="form-control" id="tarif" value="0" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>Info/Keterangan:</label> <input type="text" name="info"
							class="form-control" id="info" autocomplete="off" />
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

<div class="modal fade" id="tindakan-modal-hapus" tabindex="-1"
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

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$('.btnTambah').click(function() {
			state = 0;
		});

		$('.btnKeluar').click(function() {
			reset();
		});

		setMaskingUang("#tarif");

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				Kode : {
					required : true
				},
				tarif : {
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				kode : "Kode Wajib Diisi",
				tarif : "Tarif Wajib Diisi"
			},
			submitHandler : function(form) {
				var data = {};
				data = setTindakan(data);
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
		var data = {
			id : ids
		};
		$.getAjax('${dapatkan}', data, function(result) {
			$('#nama').val(result.nama);
			$('#kode').val(result.kode);
			$('#tarif').val(result.detail[0].hargaJual);
			$('#info').val(result.info);
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
			$('#tabelCoba').empty();
			$('#tabelCoba').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function setTindakan(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['nama'] = $('#nama').val();
		data['kode'] = $('#kode').val();
		data['tarif'] = $('#tarif').val();
		data['info'] = $('#info').val();
		return data;
	}

	function reset() {
		$('#ids').val('');
		$('#nama').val('');
		$('#kode').val('');
		$('#tarif').val('0');
		$('#info').val('');
	}
</script>