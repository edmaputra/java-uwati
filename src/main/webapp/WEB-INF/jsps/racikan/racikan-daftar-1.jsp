<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/racikan/tambah" />
<c:url var="editUrl" value="/racikan/edit" />
<c:url var="hapusUrl" value="/racikan/hapus" />
<c:url var="dapatkanUrl" value="/racikan/dapatkan" />
<c:url var="daftarUrl" value="/racikan/daftar" />
<c:url var="cariObatUrl" value="/obat/cariobat" />
<c:url var="getObatFromNamaUrl" value="/obat/nama" />

<c:url var="obatListUrl" value="/obat/obat-tindakan" />
<c:url var="tambahObatUrl" value="/racikan/tambah-obat" />
<c:url var="listObatRacikanUrl" value="/racikan/list-obat" />
<c:url var="hapusObatUrl" value="/racikan/hapus-obat" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN','APOTEK')">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#racikan-modal" id="racikan_baru">Racikan
							Baru</button>
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

<div class="modal container fade" id="racikan-modal" tabindex="-1"
	style="display: none;">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Racikan Baru</h4>
	</div>

	<form class="form style-form formTambah" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Nama Racikan:</label> <input type="text"
							class="form-control" name="nama" id="nama" autocomplete="off">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Biaya Racik</label> <input type="text" class="form-control"
							name="biayaRacik" id="biayaRacik" autocomplete="off">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label>Harga Racikan</label> <input type="text"
							class="form-control" name="hargaRacikan" id="hargaRacikan"
							readonly="readonly">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<table class="table table-striped table-advance table-hover">
						<thead style="background-color: #68DFF0;">
							<tr>
								<th>Komposisi</th>
								<th>Jumlah</th>
								<th>Biaya</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="tabel-obat">
						</tbody>
					</table>
				</div>
				<div class="col-md-6">
					<input type="text" name="cariObat" class="form-control"
						id="cariObat" placeholder="Pencarian" autocomplete="off" />
					<div id="list-obat"></div>
					<div class="btn-group btn-group-justified" id="navigasi-obat"></div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="hidden" name="ids" class="form-control" id="ids" /> <input
				type="submit" class="btn btn-primary" value="Simpan" />
		</div>
	</form>
</div>

<div class="modal fade" id="racikan-modal-hapus" tabindex="-1"
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
			<form action="${hapusUrl}"
				class="form-horizontal style-form formHapus" method="post">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						id="keluarModalHapus" data-dismiss="modal">Tidak</button>
					<input type="hidden" class="form-control" name="hapusId"
						id="hapusId" /> <input type="submit" class="btn btn-danger"
						value="Hapus" />
				</div>
			</form>
		</div>
	</div>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	var randomId = Math.floor(Math.random() * 1000000);
	var halamanObat = 1;
	var cariObat = '';
	var state = 1;

	$(document).ready(function() {

		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$('#racikan_baru').click(function(e) {
			reset();
			refreshObat(halamanObat, cariObat);
			refreshDaftarObat(randomId);
			state = 0;
		});

		$("#cariObat").keyup(function() {
			refreshObat(1, $('#cariObat').val());
		});

		$("#racikan-modal").on("hide.bs.modal", function() {
			randomId = Math.floor(Math.random() * 1000000);
			console.log(randomId);
		});

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				biayaRacik : {
					required : true
				},
				hargaRacikan : {
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				biayaRacik : "Biaya Racik Wajib Diisi",
				hargaRacikan : "Harga Total Racikan Wajib Diisi"
			},
			submitHandler : function(form) {
				var data = {};
				data = setRacikanData(data);

				if (state == 0) {
					$.postJSON('${tambahUrl}', data, function() {
						$('#gritter-tambah-sukses').click();
						$('.btnKeluar').click();
						reset();
						refresh();
						console.log(randomId);
					}, function() {
						$('#gritter-tambah-gagal').click();
					});
				} else if (state == 1) {
					$.postJSON('${editUrl}', data, function() {
						$('#gritter-edit-sukses').click();
						$('.btnKeluar').click();
						reset();
						refresh();
						console.log(randomId);
					}, function() {
						$('#gritter-tambah-gagal').click();
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
		setMaskingUang("#biayaRacik");
		setMaskingUang("#hargaRacikan");
	});

	function getData(ids) {
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#nama').val(result.racikan);
			$('#biayaRacik').val(result.biayaRacik);
			$('#hargaRacikan').val(result.hargaRacikan);
			refreshObat(halamanObat, cariObat);
			randomId = result.info
			console.log(randomId);
			refreshDaftarObat(randomId);
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
			cari : find,
			n : 15
		};

		$.getAjax('${obatListUrl}', data, function(result) {
			$('#list-obat').empty();
			$('#list-obat').append(result.button);
			$('#navigasi-obat').empty();
			$('#navigasi-obat').append(result.navigasiObat);
			halamanObat = halaman;
			cariObat = find;
		}, null);
	}

	function refreshDaftarObat(id) {
		if (id != null) {
			var data = {
				randomId : id
			};
			$.getAjax('${listObatRacikanUrl}', data, function(result) {
				$('#tabel-obat').empty();
				$('#tabel-obat').append(result.tabelTerapi);
				$('#hargaRacikan').val(result.value1);
				$('#hargaRacikan').focus();
			}, null);
		}
	}

	function tambahObat(id) {
		var data = {
			idObat : id,
			randomId : randomId
		};
		$.postJSON('${tambahObatUrl}', data, function(result) {
			console.log(randomId);
			refreshDaftarObat(randomId);
			refreshObat(halamanObat, cariObat);
		}, function() {
			console.log(result.info);
			$('#gritter-tambah-gagal').click();
		});
	}

	function hapusObat(id) {
		var data = {
			idObat : id,
			randomId : randomId
		};
		$.postJSON('${hapusObatUrl}', data, function(result) {
			refreshDaftarObat(randomId);
			console.log(result.info);
		}, function() {
			console.log(result.info);
		});
	}

	function setRacikanData(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['nama'] = $('#nama').val();
		data['biayaRacik'] = $('#biayaRacik').val();
		data['randomId'] = randomId;
		return data;
	}

	function reset() {
		$('#hargaRacikan').val('0');
		$('#nama').val('');
		$('#biayaRacik').val('0');
		$('#list-obat').empty();
		$('#tabel-obat').empty();
		halamanObat = 1;
		cariObat = '';
	}
</script>