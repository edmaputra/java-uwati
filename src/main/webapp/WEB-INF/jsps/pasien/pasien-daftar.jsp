<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambah" value="/pasien/tambah" />
<c:url var="edit" value="/pasien/edit" />
<c:url var="hapus" value="/pasien/hapus" />
<c:url var="dapatkan" value="/pasien/dapatkan" />
<c:url var="daftar" value="/pasien/daftar" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<div class="content-panel">
				<div class="row">
					<div class="col-md-2 ">
						<security:authorize access="hasAnyRole('ADMIN','MEDIS')">
							<button class="btn btn-primary btnTambah" data-toggle="modal"
								data-target="#pasien-modal">Pasien Baru</button>
							<!-- 							<a  href="#" data-toggle="modal" data-target="#pasien-modal-rm">Coba</a> -->
							<a href='#' data-toggle='modal' data-target='#pasien-modal-rm'>Rekam
								Medis</a>

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
</div>


<div class="modal fade" id="pasien-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Pasien</h4>
			</div>

			<form class="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Nama:</label> <input type="text" name="nama"
										class="form-control" id="nama" />

								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Nomor Identitas:</label> <input type="text"
										name="identitas" class="form-control" id="identitas" />

								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Tanggal Lahir:</label> <input type="text"
										name="tanggalLahir" class="form-control datePicker"
										id="tanggalLahir" />

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
										class="form-control" id="agama" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Pekerjaan:</label> <input type="text" name="pekerjaan"
										class="form-control" id="pekerjaan" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-9">
								<div class="form-group">
									<label>Alamat:</label> <input type="text" name="alamat"
										class="form-control" id="alamat" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Telepon:</label> <input type="text" name="kontak"
										class="form-control" id="kontak" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Jaminan:</label> <input type="text"
										name="jaminanKesehatan" class="form-control"
										id="jaminanKesehatan" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Nomor Jaminan:</label> <input type="text"
										name="nomorJaminan" class="form-control" id="nomorJaminan" />
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

<div class="modal fade" id="pasien-modal-rm" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Detail dan Rekam
					Medis Pasien</h4>
			</div>
			<div class="modal-body">
				<div class="form-panel">
					<h4 class="mb">
						<i class="fa fa-angle-right"></i> Pasien
					</h4>
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label" style="text-align: right;">Nama
								:</label> <label class="col-sm-6 control-label"><strong>Diar
									Resti Andari Winangsih</strong></label>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="text-align: right;">Jenis
								Kelamin :</label> <label class="col-sm-2 control-label"><strong>Perempuan</strong></label>
							<label class="col-sm-2 control-label" style="text-align: right;">Usia
								:</label> <label class="col-sm-2 control-label"><strong>31
									Th 2 bln</strong></label>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="text-align: right;">Alamat
								:</label> <label class="col-sm-10 control-label"><strong>Jalan
									Kol. Soetadji No. 36 Tanjung Selor, Kabupaten Bulungan,
									Kalimantan Utaraaaaaaaaaaaaaaaa</strong></label>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="text-align: right;">Jaminan
								:</label> <label class="col-sm-3 control-label"><Strong>BPJS</Strong></label>
							<label class="col-sm-2 control-label" style="text-align: right;">Nomor
								:</label> <label class="col-sm-3 control-label"><strong>1234567890123456</strong></label>
						</div>
					</form>

				</div>

				<div class="form-panel">
					<h4 class="mb">
						<i class="fa fa-angle-right"></i> Rekam Medis
					</h4>
					<div class="row">
						<div class="col-md-2 ">
							<security:authorize access="hasAnyRole('ADMIN','MEDIS')">
								<button class="btn btn-primary btn-rekam-medis" data-toggle="modal" data-target="#pasien-modal-hapus">Rekam Medis Baru</button>
							</security:authorize>
						</div>
					</div>
					
					<table class="table table-striped table-advance table-hover"
						id="tabel-rekam-medis">
					</table>
					<div id="nav-rekam-medis"></div>
				</div>
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
	</div>
</div>

<div class="modal fade" id="pasien-modal-hapus" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Pasien</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Yakin Ingin Menghapus ?</p>
				</div>
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
	</div>
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

		$('.btnEdit').click(function() {
			state = 1;
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
					required : true
				},
				tanggalLahir : {
					required : true
				},
				jenisKelamin : {
					valueNotEquals : "default"
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				identitas : "Identitas Wajib Diisi",
				tanggalLahir : "Harap Isi Tanggal Lahir",
				jenisKelamin : "Harap Pilih Jenis Kelamin"
			},
			submitHandler : function(form) {
				var data = {};
				data = setDataContent(data);
				if (state == 0) {
					$.postJSON('${tambah}', data, function(result) {
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
				state = 1;
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
			isiFieldFromDataResult(result);
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
		$('#nomorJaminan').val(result.nomorJaminan);
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
	}
</script>