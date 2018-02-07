<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambah" value="/pasien/tambah" />
<c:url var="edit" value="/pasien/edit" />
<c:url var="hapus" value="/pasien/hapus" />
<c:url var="dapatkan" value="/pasien/dapatkan" />
<c:url var="daftar" value="/pasien/daftar" />
<c:url var="isIdentitasAda" value="/pasien/tersedia" />


<div class="row">
	<div class="col-lg-12">
		<div class="content-panel">
			<div class="row kolom-pencarian">

				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN','MEDIS')">
						<button class="btn btn-primary btnTambah" data-toggle="modal"
							data-target="#pasien-modal">Pasien Baru</button>
					</security:authorize>
				</div>

				<div class="col-md-10">
					<form class="form-inline pull-right" id="formCari">
						<div class="form-group">
							<select class="form-control" id="kategori_cari">
								<option value="-1"></option>
								<c:forEach items="${kategoris}" var="kategoris" varStatus="loop">
									<option value="${kategoris.id}"><c:out
											value="${kategoris.nama}" /></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<input type="text" id="id_cari" class="form-control"
								placeholder="ID" style="width: 70px" />
						</div>
						<div class="form-group">
							<input type="text" id="stringCari" class="form-control"
								placeholder="Pencarian" style="width: 150px" />
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-primary" id="btnCari">Cari</button>
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
				id="tabelCoba">
			</table>
			<div id="nav"></div>
		</div>
	</div>
</div>


<div class="modal container fade" id="pasien-modal" tabindex="-1"
	style="display: none;" data-focus-on="input:first">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Pasien</h4>
	</div>

	<form class="form style-form formTambah" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Nama:</label> <input type="text" name="nama"
							class="form-control" id="nama" autocomplete="off" />

					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Nomor Identitas:</label> <input type="text"
							name="identitas" class="form-control" id="identitas"
							autocomplete="off" />

					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label>Tanggal Lahir:</label> <input type="text"
							name="tanggalLahir" class="form-control datePicker"
							id="tanggalLahir" autocomplete="off" />

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label>Jenis Kelamin:</label> <select class="form-control"
							name="jenisKelamin" id="jenisKelamin">
							<option value="default"></option>
							<option value="0">Perempuan</option>
							<option value="1">Laki-laki</option>
						</select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Agama:</label> <input type="text" name="agama"
							class="form-control" id="agama" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Pekerjaan:</label> <input type="text" name="pekerjaan"
							class="form-control" id="pekerjaan" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-3 form-group">
					<label>Kategori:</label> <select class="form-control"
						name="kategori" id="kategori">
						<option value="default"></option>
						<c:forEach items="${kategoris}" var="kategoris" varStatus="loop">
							<option value="${kategoris.id}"><c:out
									value="${kategoris.nama}" /></option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="col-md-9">
					<div class="form-group">
						<label>Alamat:</label> <input type="text" name="alamat"
							class="form-control" id="alamat" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Telepon:</label> <input type="text" name="kontak"
							class="form-control" id="kontak" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Jaminan:</label> <input type="text" name="jaminanKesehatan"
							class="form-control" id="jaminanKesehatan" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Nomor Jaminan:</label> <input type="text"
							name="nomorJaminan" class="form-control" id="nomorJaminan"
							autocomplete="off" />
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

<div class="modal fade" id="pasien-modal-hapus" tabindex="-1"
	style="display: none;" data-width="300">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Hapus Pasien</h4>
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

	var c = '';
	var k = -1;
	var i = '';
	$(document).ready(function() {
		refresh(1, c, k, i);

		$('#btnCari').click(function() {
			c = $('#stringCari').val();
			k = $('#kategori_cari').val();
			i = $('#id_cari').val();
			refresh(1, c, k, i);
		});

		$('#btnReset').click(function() {
			c = '';
			k = -1;
			i = '';
			$('#stringCari').val(c);
			$('#kategori_cari').val(k);
			i = $('#id_cari').val(i);
			refresh(1, c, k, i);
		});

		$('.btnTambah').click(function() {
			reset();
			state = 0;
		});

		$('.btnKeluar').click(function() {
			reset();
		});

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				identitas : {
					required : true,
				// 					remote: {
				// 						url: "${isIdentitasAda}",
				// 						type: "get",
				// 						data: {
				// 							identitas: function(){
				// 								return $("#identitas").val();
				// 							}
				// 						}
				// 					},
				},
				tanggalLahir : {
					required : true
				},
				jenisKelamin : {
					valueNotEquals : "default"
				},
				kategori : {
					valueNotEquals : "default"
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				identitas : {
					required : "Identitas Wajib Diisi",
					remote : "Identitas sudah Ada"
				},
				tanggalLahir : "Harap Isi Tanggal Lahir",
				jenisKelamin : "Harap Pilih Jenis Kelamin",
				kategori : "Harap Pilih Kategori Pasien"
			},
			submitHandler : function(form) {
				var data = {};
				data = setDataContent(data);
				if (state == 0) {
					$.postJSON('${tambah}', data, function(result) {
						$('#gritter-tambah-sukses').click();
						$('.btnKeluar').click();
						refresh(1, c, k);
					}, function() {
						$('#gritter-tambah-gagal').click();
					});
				} else if (state == 1) {
					$.postJSON('${edit}', data, function() {
						$('#gritter-edit-sukses').click();
						$('.btnKeluar').click();
						refresh(1, c, k);
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
				refresh(1, c, k);
				;
				$('#keluarModalHapus').click();
				$('#gritter-hapus-sukses').click();
			}, function(e) {
				$('#gritter-hapus-sukses').click();
				$('#keluarModalHapus').click();
				refresh(1, c, k);
			});
		});
	});

	function getData(ids) {
		reset();
		state = 1;
		var data = {
			id : ids
		};
		$.getAjax('${dapatkan}', data, function(result) {
			isiFieldFromDataResult(result);
		}, null);
	}

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, find, kategori, i) {
		var data = {
			hal : halaman,
			cari : find,
			k : kategori,
			i : i
		};

		$.getAjax('${daftar}', data, function(result) {
			$('#tabelCoba').empty();
			$('#tabelCoba').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function isiFieldFromDataResult(result) {
		$('#ids').val(result.id);
		$('#nama').val(result.nama);
		$('#identitas').val(result.identitas);
		$('#tanggalLahir').val(result.tanggalLahir);
		$('#tanggalLahir').val(dateFormat(result.tanggalLahir, 'dd-mm-yyyy'));
		$('#jenisKelamin').val(result.jenisKelamin);
		$('#agama').val(result.agama);
		$('#pekerjaan').val(result.pekerjaan);
		$('#alamat').val(result.alamat);
		$('#kontak').val(result.kontak);
		$('#jaminanKesehatan').val(result.jaminanKesehatan);
		$('#nomorJaminan').val(result.nomorJaminanKesehatan);
		$('#kategori').val(result.kategoriPasien.id);
	}

	function setDataContent(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['nama'] = $('#nama').val();
		data['identitas'] = $('#identitas').val();
		data['tanggalLahir'] = $('#tanggalLahir').val();
		data['jenisKelamin'] = $('#jenisKelamin').val();
		data['agama'] = $('#agama').val();
		data['pekerjaan'] = $('#pekerjaan').val();
		data['alamat'] = $('#alamat').val();
		data['kontak'] = $('#kontak').val();
		data['jaminanKesehatan'] = $('#jaminanKesehatan').val();
		data['nomorJaminan'] = $('#nomorJaminan').val();
		data['kategori'] = $('#kategori').val();
		return data;
	}

	function reset() {
		$('#ids').val('');
		$('#nama').val('');
		$('#identitas').val('');
		$('#tanggalLahir').val('');
		$('#jenisKelamin').val('default');
		$('#agama').val('');
		$('#pekerjaan').val('');
		$('#alamat').val('');
		$('#kontak').val('');
		$('#jaminanKesehatan').val('');
		$('#nomorJaminan').val('');
		$('#kategori').val('default');
	}
</script>