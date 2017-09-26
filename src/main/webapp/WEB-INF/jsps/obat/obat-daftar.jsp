<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/obat/tambah" />
<c:url var="editUrl" value="/obat/edit" />
<c:url var="hapusUrl" value="/obat/hapus" />
<c:url var="dapatkanUrl" value="/obat/dapatkan" />
<c:url var="daftarUrl" value="/obat/daftar" />

<c:url var="satuanSemua" value="/satuan/nama" />
<c:url var="kategoriSemua" value="/kategori/nama" />

<c:url var="kategoriAda" value="/kategori/ada" />
<c:url var="satuanAda" value="/satuan/ada" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN')">
						<button class="btn btn-primary btnTambah" data-toggle="modal"
							data-target="#obat-modal" id="btnBaru">Obat Baru</button>
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

<div class="modal container fade" id="obat-modal" tabindex="-1"
	role="dialog" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Obat</h4>
	</div>
	<form class="form style-form formTambah" method="post">
		<div class="modal-body">
			<div class="form-panel">
				<h4 class="mb">
					<i class="fa fa-angle-right"></i> Obat
				</h4>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label>Nama:</label> <input type="text" name="nama"
								class="form-control" id="nama" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label>Kode:</label> <input type="text" name="kode"
								class="form-control" id="kode" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Barcode:</label> <input type="text" name="barcode"
								class="form-control" id="barcode" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Batch:</label> <input type="text" name="batch"
								class="form-control" id="batch" autocomplete="off" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label>Satuan:</label> <input type="text" name="satuan"
								class="form-control" id="satuan" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Kategori:</label> <input type="text" name="kategori"
								class="form-control" id="kategori" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label>Stok Minimal:</label> <input type="number"
								name="stokMinimal" class="form-control" id="stokMinimal"
								autocomplete="off" value="0" />
						</div>
					</div>
				</div>
			</div>
			<div class="form-panel">
				<h4 class="mb">
					<i class="fa fa-angle-right"></i> Detail Obat
				</h4>
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label>Harga Jual:</label> <input type="text" name="hargaJual"
								class="form-control" id="hargaJual" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Harga Jual Resep:</label> <input type="text"
								name="hargaJualResep" class="form-control" id="hargaJualResep"
								autocomplete="off" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Harga Beli:</label> <input type="text" name="hargaBeli"
								class="form-control" id="hargaBeli" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label>Harga Diskon:</label> <input type="text"
								name="hargaDiskon" class="form-control" id="hargaDiskon"
								autocomplete="off" />
						</div>
					</div>
				</div>
			</div>
			<div class="form-panel">
				<h4 class="mb">
					<i class="fa fa-angle-right"></i> Stok Obat
				</h4>
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label>Stok:</label> <input type="number" name="stok"
								class="form-control" id="stok" disabled="disabled" value="0">
						</div>
					</div>
				</div>
			</div>

			<div class="form-panel">
				<h4 class="mb">
					<i class="fa fa-angle-right"></i> Tanggal Kadaluarsa Obat
				</h4>
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label>Tanggal Kadaluarsa:</label> <input type="text"
								name="tanggalExpired" class="form-control datePicker"
								id="tanggalExpired" autocomplete="off">
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


<div class="modal fade" id="obat-modal-hapus" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Obat</h4>
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
					<input type="hidden" class="form-control" id="hapusId" name="id" />
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
			reset();
		});

		$('.btnEdit').click(function() {
			// 			state = 1;
			// 			console.log(state);
		});

		$('.btnKeluar').click(function() {
			reset();
		});

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				kode : {
					required : true
				},
				satuan : {
					required : true,
					remote : {
						url : "${satuanAda}",
						type : "get",
						data : {
							nama : function() {
								return $("#satuan").val();
							}
						}
					},
				},
				kategori : {
					required : true,
					remote : {
						url : "${kategoriAda}",
						type : "get",
						data : {
							nama : function() {
								return $("#kategori").val();
							}
						}
					},
				},
				stokMinimal : {
					required : true,
					number : true,
					min : 1
				},
				hargaJual : {
					required : true
				},
				hargaJualResep : {
					required : true
				},
				hargaBeli : {
					required : true
				},
				tanggalExpired : {
					required : true
				},
				stok : {
					required : true,
					number : true
				},
			},
			messages : {
				nama : "Masukkan Nama",
				kode : "Masukkan Kode",
				satuan : {
					required : "Pilih Satuan",
					remote : "Satuan Tidak Ada, tambahkan pada Data Master"
				},
				kategori : {
					required : "Pilih Kategori",
					remote : "Kategori Tidak Ada, tambahkan pada Data Master"
				},
				stokMinimal : {
					required : "Masukkan Stok Minimal",
					number : "Masukkan Angka dengan Benar",
					min : "Masukkan Lebih dari 0"
				},
				hargaJual : {
					required : "Masukkan Harga Jual Obat",
					number : "Masukkan Angka dengan Benar",
					min : "Masukkan Lebih dari 0"
				},
				hargaJualResep : {
					required : "Masukkan Harga Jual Resep Obat",
					number : "Masukkan Angka dengan Benar",
					min : "Masukkan Lebih dari 0"
				},
				hargaBeli : {
					required : "Masukkan Harga Beli Obat",
					number : "Masukkan Angka dengan Benar",
					min : "Masukkan Lebih dari 0"
				},
				stok : {
					required : "Stok Obat Tidak Boleh Kosong",
					number : "Masukkan Angka dengan Benar"
				},
				tanggalExpired : "Tentukan Tanggal Expire Obat",
			},
			submitHandler : function(form) {
				var data = {};
				data = setObatData(data);
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
						$('#gritter-tambah-gagal').click();
					});
				}
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

		setAutoComplete('#kategori', '${kategoriSemua}');
		setAutoComplete('#satuan', '${satuanSemua}');
		// 		setAutoComplete('#editKategori', '${kategoriSemua}');
		// 		setAutoComplete('#editSatuan', '${satuanSemua}');

		setMaskingUang("#hargaJual");
		setMaskingUang("#hargaJualResep");
		setMaskingUang("#hargaBeli");
		setMaskingUang("#hargaDiskon");

		// 		setMaskingUang("#editHargaJual");
		// 		setMaskingUang("#editHargaJualResep");
		// 		setMaskingUang("#editHargaBeli");
		// 		setMaskingUang("#editHargaDiskon");
	});

	function getData(ids) {
		state = 1;
		console.log(state);
		reset();
		var data = {
			id : ids
		};

		$.getAjax('${dapatkanUrl}', data, function(obat) {
			$('#ids').val(ids);
			$('#nama').val(obat.nama);
			$('#barcode').val(obat.barcode);
			$('#batch').val(obat.batch);
			$('#kode').val(obat.kode);
			$('#satuan').val(obat.satuan.nama);
			$('#kategori').val(obat.kategori.nama);
			$('#stokMinimal').val(obat.stokMinimal);
			$('#hargaJual').val(obat.detail[0].hargaJual);
			$('#hargaJualResep').val(obat.detail[0].hargaJualResep);
			$('#hargaBeli').val(obat.detail[0].hargaBeli);
			$('#hargaDiskon').val(obat.detail[0].hargaDiskon);
			$('#stok').val(obat.stok[0].stok);
			$('#tanggalExpired').val(
					dateFormat(obat.expired[0].tanggalExpired, 'dd-mm-yyyy'));

			$("#hargaJual").maskMoney('mask');
			$("#hargaJualResep").maskMoney('mask');
			$("#hargaBeli").maskMoney('mask');
			$("#hargaDiskon").maskMoney('mask');
		}, null);
		console.log($('#ids').val());
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

	function setObatData(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['nama'] = $('#nama').val();
		data['kode'] = $('#kode').val();
		data['barcode'] = $('#barcode').val();
		data['batch'] = $('#batch').val();
		data['satuan'] = $('#satuan').val();
		data['kategori'] = $('#kategori').val();
		data['stokMinimal'] = $('#stokMinimal').val();
		data['hargaJual'] = $('#hargaJual').val();
		data['hargaJualResep'] = $('#hargaJualResep').val();
		data['hargaBeli'] = $('#hargaBeli').val();
		data['hargaDiskon'] = $('#gargaDiskon').val();
		data['stok'] = $('#stok').val();
		data['tanggalExpired'] = $('#tanggalExpired').val();
		return data;
	}

	function reset() {
		$('#nama').val('');
		$('#kode').val('');
		$('#barcode').val('');
		$('#batch').val('');
		$('#satuan').val('');
		$('#kategori').val('');
		$('#stokMinimal').val('0');
		$('#hargaJual').val('0');
		$('#hargaJualResep').val('0');
		$('#hargaBeli').val('0');
		$('#hargaDiskon').val('0');
		$('#stok').val('0');
		$('#tanggalExpired').val('');
		$('#ids').val('');
	}
</script>