<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/satuan/tambah" />
<c:url var="editUrl" value="/satuan/edit" />
<c:url var="hapusUrl" value="/satuan/hapus" />
<c:url var="dapatkanUrl" value="/satuan/dapatkan" />
<c:url var="daftarUrl" value="/satuan/daftar" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
<!-- 			<div class="content-panel"> -->
				<div class="row">
					<div class="col-md-2 ">
						<security:authorize access="hasAnyRole('ADMIN')">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#satuan-modal">Satuan Baru</button>
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

<div class="modal fade" tabindex="-1" style="display: none;"
	id="satuan-modal" data-focus-on="input:first">
	<form class="form-horizontal style-form formTambah" method="post">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 class="modal-title">Satuan Baru</h4>
		</div>
		<div class="modal-body" style="margin: 8px;">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>Nama:</label> <input type="text" name="nama"
							class="form-control" id="tambahNama" autocomplete="off" /> <input
							type="hidden" name="id" class="form-control" id="tambahId"
							autocomplete="off" />
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

<div class="modal fade" tabindex="-1" style="display: none;"
	id="satuan-modal-edit" data-focus-on="input:first">
	<form class="form-horizontal style-form formEdit" method="post">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 class="modal-title">Edit Satuan</h4>
		</div>
		<div class="modal-body" style="margin: 8px;">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label>Nama:</label> <input type="text" name="nama"
							class="form-control" id="editNama" autocomplete="off" /> <input
							type="hidden" name="id" class="form-control" id="editId"
							autocomplete="off" />
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar" data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="Simpan" />
		</div>
	</form>
</div>

<div class="modal fade" id="satuan-modal-hapus" tabindex="-1" data-width = "300">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title">Hapus Satuan</h4>
	</div>
	<div class="modal-body" style="margin: 8px; text-align: center;">		
		<p>Apakah Anda Yakin Ingin Menghapus ?</p>
	</div>
	<form:form action="${hapusUrl}" commandName="satuan" cssClass="form-horizontal style-form formHapus" method="post">
	<div class="modal-footer">
		<button type="button" class="btn btn-default btnKeluar" id="keluarModalHapus" data-dismiss="modal">Tidak</button>
		<form:hidden path="id" cssClass="form-control" id="hapusId" />
		<input type="submit" class="btn btn-danger" value="Hapus" />
	</div>
	</form:form>
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

	$(document).ready(function() {
		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
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