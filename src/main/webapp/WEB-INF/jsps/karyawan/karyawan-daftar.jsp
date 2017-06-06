<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/karyawan/tambah" />
<c:url var="editUrl" value="/karyawan/edit" />
<c:url var="hapusUrl" value="/karyawan/hapus" />
<c:url var="dapatkanUrl" value="/karyawan/dapatkan" />
<c:url var="daftarUrl" value="/karyawan/daftar" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<div class="content-panel">
				<div class="row">
					<div class="col-md-2 ">
						<security:authorize access="hasAnyRole('ADMIN')">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#dokter-modal">Karyawan Baru</button>
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

<div class="modal fade" id="dokter-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Karyawan Baru</h4>
			</div>

			<form:form action="${tambahUrl}" commandName="karyawan"
				cssClass="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label>Nama:</label>
									<form:input path="nama" cssClass="form-control" id="tambahNama" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<div class="form-group">
									<label>Jabatan:</label>
									<form:input path="jabatan" cssClass="form-control" id="tambahJabatan" />									
								</div>
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<label>Spesialis:</label>
									<form:input path="spesialis" cssClass="form-control" id="tambahSpesialis" />										
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>SIP:</label>
									<form:input path="sip" cssClass="form-control" id="tambahSip" />										
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Alamat:</label>
									<form:input path="alamat" cssClass="form-control" id="tambahAlamat" />										
								</div>
								<form:hidden path="id" cssClass="form-control" id="tambahId" />
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>

			</form:form>
		</div>
	</div>
</div>

<div class="modal fade" id="dokter-modal-edit" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Edit Karyawan</h4>
			</div>
			<form:form action="${editUrl}" commandName="karyawan" cssClass="form style-form formEdit" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label>Nama:</label>
									<form:input path="nama" cssClass="form-control" id="editNama" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5">
								<div class="form-group">
									<label>Jabatan:</label>
									<form:input path="jabatan" cssClass="form-control" id="editJabatan" />									
								</div>
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<label>Spesialis:</label>
									<form:input path="spesialis" cssClass="form-control" id="editSpesialis" />									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>SIP:</label>
									<form:input path="sip" cssClass="form-control" id="editSip" />									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Alamat:</label>
									<form:input path="alamat" cssClass="form-control" id="editAlamat" />									
								</div>
								<form:hidden path="id" cssClass="form-control" id="editId" />
							</div>
						</div>						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form:form>
		</div>
	</div>
</div>

<div class="modal fade" id="dokter-modal-hapus" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Karyawan</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Yakin Ingin Menghapus ?</p>
				</div>
			</div>
			<form:form action="${hapusUrl}" commandName="karyawan"
				cssClass="form-horizontal style-form formHapus" method="post">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						id="keluarModalHapus" data-dismiss="modal">Tidak</button>
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
		var data = {id:ids};		
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#editNama').val(result.nama);
			$('#editSpesialis').val(result.spesialis);
			$('#editSip').val(result.sip);
			$('#editAlamat').val(result.alamat);
			$('#editJabatan').val(result.jabatan);
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
				data['nama'] = $('#tambahNama').val();
				data['spesialis'] = $('#tambahSpesialis').val();
				data['sip'] = $('#tambahSip').val();
				data['alamat'] = $('#tambahAlamat').val();
				data['jabatan'] = $('#tambahJabatan').val();
				$.postJSON('${tambahUrl}', data, function() {
					$('#gritter-tambah-sukses').click();
					$('.btnKeluar').click();
					$('#tambahNama').val('');
					$('#tambahSpesialis').val('');
					$('#tambahSip').val('');
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
				data['id'] = $('#editId').val();
				data['spesialis'] = $('#editSpesialis').val();
				data['nama'] = $('#editNama').val();
				data['sip'] = $('#editSip').val();
				data['alamat'] = $('#editAlamat').val();
				data['jabatan'] = $('#editJabatan').val();
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
</script>