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
<div class="row mt">
	<div class="col-md-12">
		<div class="content-panel">
			<div class="row">
				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN')">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#obat-modal">Obat Baru</button>
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
</div>

<div class="modal fade" id="obat-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Obat Baru</h4>
			</div>
			<form:form action="${tambahUrl}" commandName="obat"
				cssClass="form style-form formTambah" method="post">
				<div class="modal-body">
					<div class="form-panel">
						<h4 class="mb">
							<i class="fa fa-angle-right"></i> Obat
						</h4>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Nama:</label>
									<form:input path="nama" cssClass="form-control" id="tambahNama" />
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Kode:</label>
									<form:input path="kode" cssClass="form-control" id="tambahKode" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Barcode:</label>
									<form:input path="barcode" cssClass="form-control"
										id="tambahBarcode" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Batch:</label>
									<form:input path="batch" cssClass="form-control"
										id="tambahBatch" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Satuan:</label>
									<form:input path="satuan" cssClass="form-control"
										id="tambahSatuan" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Kategori:</label>
									<form:input path="kategori" cssClass="form-control"
										id="tambahKategori" />
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Stok Minimal:</label> <input type="number"
										class="form-control" id="tambahStokMinimal" name="stokMinimal" />
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
									<label>Harga Jual:</label>
									<form:input path="hargaJual" cssClass="form-control"
										id="tambahHargaJual" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Harga Jual Resep:</label>
									<form:input path="hargaJualResep" cssClass="form-control"
										id="tambahHargaJualResep" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Harga Beli:</label>
									<form:input path="hargaBeli" cssClass="form-control"
										id="tambahHargaBeli" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Harga Diskon:</label>
									<form:input path="hargaDiskon" cssClass="form-control"
										id="tambahHargaDiskon" />
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
										class="form-control" id="tambahStok" disabled="disabled"
										value="0">
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
										id="tambahTanggalExpired">
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<form:hidden path="id" cssClass="form-control" id="tambahId" />
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>

			</form:form>
		</div>
	</div>
</div>

<div class="modal fade" id="obat-modal-edit" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Edit Obat</h4>
			</div>
			<form:form action="${editUrl}" commandName="obat"
				cssClass="form style-form formEdit" method="post">
				<div class="modal-body">
					<div class="form-panel">
						<h4 class="mb">
							<i class="fa fa-angle-right"></i> Obat
						</h4>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Nama:</label>
									<form:input path="nama" cssClass="form-control" id="editNama" />
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Kode:</label>
									<form:input path="kode" cssClass="form-control" id="editKode" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Barcode:</label>
									<form:input path="barcode" cssClass="form-control"
										id="editBarcode" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Batch:</label>
									<form:input path="batch" cssClass="form-control" id="editBatch" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Satuan:</label>
									<form:input path="satuan" cssClass="form-control"
										id="editSatuan" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Kategori:</label>
									<form:input path="kategori" cssClass="form-control"
										id="editKategori" />
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Stok Minimal:</label> <input type="number"
										class="form-control" id="editStokMinimal" name="stokMinimal" />
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
									<label>Harga Jual:</label>
									<form:input path="hargaJual" cssClass="form-control"
										id="editHargaJual" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Harga Jual Resep:</label>
									<form:input path="hargaJualResep" cssClass="form-control"
										id="editHargaJualResep" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Harga Beli:</label>
									<form:input path="hargaBeli" cssClass="form-control"
										id="editHargaBeli" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Harga Diskon:</label>
									<form:input path="hargaDiskon" cssClass="form-control"
										id="editHargaDiskon" />
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
										class="form-control" id="editStok" disabled="disabled">
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
										id="editTanggalExpired">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<form:hidden path="id" cssClass="form-control" id="editId" />
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form:form>
		</div>
	</div>
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
			<form:form action="${hapusUrl}" commandName="obat"
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
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(obat) {
			$('#editNama').val(obat.nama);
			$('#editBarcode').val(obat.barcode);
			$('#editBatch').val(obat.batch);
			$('#editKode').val(obat.kode);
			$('#editSatuan').val(obat.satuan.nama);
			$('#editKategori').val(obat.kategori.nama);
			$('#editStokMinimal').val(obat.stokMinimal);
			$('#editHargaJual').val(obat.detail[0].hargaJual);
			$('#editHargaJualResep').val(obat.detail[0].hargaJualResep);
			$('#editHargaBeli').val(obat.detail[0].hargaBeli);
			$('#editHargaDiskon').val(obat.detail[0].hargaDiskon);
			$('#editStok').val(obat.stok[0].stok);
			$('#editTanggalExpired').val(
					dateFormat(obat.expired[0].tanggalExpired, 'dd-mm-yyyy'));
			$('#editId').val(ids);
			$("#editHargaJual").maskMoney('mask');
			$("#editHargaJualResep").maskMoney('mask');
			$("#editHargaBeli").maskMoney('mask');
			$("#editHargaDiskon").maskMoney('mask');
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
								return $("#tambahSatuan").val();
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
								return $("#tambahKategori").val();
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
				data['nama'] = $('#tambahNama').val();
				data['kode'] = $('#tambahKode').val();
				data['barcode'] = $('#tambahBarcode').val();
				data['batch'] = $('#tambahBatch').val();
				data['satuan'] = $('#tambahSatuan').val();
				data['kategori'] = $('#tambahKategori').val();
				data['stokMinimal'] = $('#tambahStokMinimal').val();
				data['hargaJual'] = $('#tambahHargaJual').val();
				data['hargaJualResep'] = $('#tambahHargaJualResep').val();
				data['hargaBeli'] = $('#tambahHargaBeli').val();
				data['hargaDiskon'] = $('#tambahHargaDiskon').val();
				data['stok'] = $('#tambahStok').val();
				data['tanggalExpired'] = $('#tambahTanggalExpired').val();
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
								return $("#editSatuan").val();
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
								return $("#editKategori").val();
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
				data['nama'] = $('#editNama').val();
				data['kode'] = $('#editKode').val();
				data['barcode'] = $('#editBarcode').val();
				data['batch'] = $('#editBatch').val();
				data['satuan'] = $('#editSatuan').val();
				data['kategori'] = $('#editKategori').val();
				data['stokMinimal'] = $('#editStokMinimal').val();
				data['hargaJual'] = $('#editHargaJual').val();
				data['hargaJualResep'] = $('#editHargaJualResep').val();
				data['hargaBeli'] = $('#editHargaBeli').val();
				data['hargaDiskon'] = $('#editHargaDiskon').val();
				data['stok'] = $('#editStok').val();
				data['tanggalExpired'] = $('#editTanggalExpired').val();
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

		setAutoComplete('#tambahKategori', '${kategoriSemua}');
		setAutoComplete('#tambahSatuan', '${satuanSemua}');
		setAutoComplete('#editKategori', '${kategoriSemua}');
		setAutoComplete('#editSatuan', '${satuanSemua}');

		setMaskingUang("#tambahHargaJual");
		setMaskingUang("#tambahHargaJualResep");
		setMaskingUang("#tambahHargaBeli");
		setMaskingUang("#tambahHargaDiskon");

		setMaskingUang("#editHargaJual");
		setMaskingUang("#editHargaJualResep");
		setMaskingUang("#editHargaBeli");
		setMaskingUang("#editHargaDiskon");
	});
</script>