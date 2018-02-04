<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/penjualan-obat/tambah" />
<c:url var="cariObatUrl" value="/obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="tambahKeListObatUrl" value="/penjualan-obat/tambahTemp" />
<c:url var="hapusObatUrl" value="/penjualan-obat/hapusTemp" />
<c:url var="daftarTempUrl" value="/penjualan-obat/daftarTemp" />

<c:url var="tersediaUrl" value="/penjualan-obat/tersedia" />
<c:url var="cetakUrl" value="/penjualan-obat/cetak" />
<c:url var="racikanListUrl" value="/racikan/list" />
<c:url var="racikanDetailUrl" value="/racikan/detail" />
<c:url var="tambahRacikanKeListJual" value="/penjualan-obat/racikanTemp" />
<c:url var="nomorFakturNotTerpakai" value="/nomor-faktur/not-terpakai" />

<c:url var="obatListUrl" value="/obat/obat-tindakan" />
<c:url var="tambahObatUrl" value="/penjualan-obat/tambah-obat" />
<c:url var="listObatTerpilihUrl" value="/penjualan-obat/list-obat" />
<c:url var="editObatTerpilihUrl" value="/penjualan-obat/edit-obat" />
<c:url var="hapusObatTerpilihUrl" value="/penjualan-obat/hapus-obat" />
<c:url var="dapatkanObatTerpilihUrl"
	value="/penjualan-obat/dapatkan-obat" />
<c:url var="cekStokListObatUrl" value="/penjualan-obat/cek-stok" />
<c:url var="penjualanUrl" value="/penjualan-obat/jual" />

<div class="row">
	<div class="col-md-12">
		<div class="form-panel">
			<form class="form formTambah" id="formObat" method="POST">
				<div class="row">
					<div class="col-md-2">
						<div class="form-group">
							<input name="tanggal" type="text" id="tanggal"
								class="form-control datePicker" placeholder="Tanggal Transaksi"
								value="${tanggalHariIni}" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<input name="pelanggan" type="text" id="pelanggan"
								class="form-control" placeholder="Pelanggan" autocomplete="off" />
						</div>
					</div>
					<div class="col-md-7">
						<input type="text" name="cariObat" class="form-control"
							id="cariObat" placeholder="Pencarian" autocomplete="off" />
					</div>
				</div>

				<div class="row">
					<div class="col-md-5">
						<table
							class="table table-striped table-advance table-hover fullwidth"
							id="tabel">
							<thead>
								<tr>
									<th>Obat</th>
									<th>Jumlah</th>
									<th>Sub Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="tabel-obat">
							</tbody>
						</table>
						<br />

					</div>
					<div class="col-md-7">
						<div id="list-obat"></div>
						<div class="btn-group btn-group-justified" id="navigasi-obat"></div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-5">
						<table class="kolom-keterangan tabel-keterangan"
							style="width: 100%">
							<tr>
								<td>Barang</td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="barang" value="0"
									style="background-color: green;"></td>
								<td>Total</td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="total" value="0"
									style="background-color: green;"></td>
							</tr>
							<tr>
								<td><a href="#" data-toggle="modal"
									data-target="#diskon-modal" style="color: yellow;">Diskon</a></td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="diskon" value="0"
									style="background-color: green;"></td>
								<td><a href="#" data-toggle="modal"
									data-target="#pajak-modal" style="color: yellow;">Pajak</a></td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="pajak" value="0"
									style="background-color: green;"></td>
							</tr>
							<tr>
								<td colspan="2">Total Bayar</td>
								<td colspan="2"><input type="text" readonly="readonly"
									class="form-control" id="totalBayar" value="0"
									style="background-color: green;"></td>
							</tr>
						</table>
					</div>
					<div class="col-md-7">
						<div class="form-group">
							<table style="width: 100%; height: 100%;">
								<tr>
									<td
										style="position: relative; height: 100px; font-size: 16pt; text-align: center;">
										<button
											style="position: absolute; top: 0; bottom: 0; border-radius: 0; right: 0; width: 100%; background-color: blue; color: white;"
											id="button-bayar">Bayar</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="edit-obat-modal" tabindex="-1"
	style="display: none;" data-width="600">
	<form class="form style-form formEdit" method="post">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Edit Obat</h4>
		</div>

		<div class="modal-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Obat:</label> <input type="text" class="form-control"
							name="editObat" id="editObat" readonly="readonly"
							autocomplete="off">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label>Jumlah:</label> <input type="text"
							class="form-control input-angka" name="editJumlah"
							id="editJumlah" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Harga Satuan:</label> <input type="text"
							class="form-control input-angka" name="editHarga" id="editHarga"
							autocomplete="off">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label>Harga Total:</label> <input type="text"
							class="form-control input-angka" name="editHargaTotal"
							id="editHargaTotal" readonly="readonly" autocomplete="off">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Diskon:</label> <input type="text"
							class="form-control input-angka" name="editDiskon"
							id="editDiskon" autocomplete="off">
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<input type="hidden" class="form-control" name="editId" id="editId" />
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="UPDATE"
				id="update-obat" />
		</div>
	</form>
</div>

<div class="modal fade" id="pajak-modal" tabindex="-1" data-width="300"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Pajak</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label>Pajak:</label> <input type="text"
				class="form-control input-angka" id="input-pajak" value="0"
				autocomplete="off" />
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-primary btnKeluar"
			data-dismiss="modal" id="btnPajak">UPDATE</button>
	</div>
</div>

<div class="modal fade" id="diskon-modal" tabindex="-1" data-width="300"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Diskon</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label>Diskon:</label> <input type="text"
				class="form-control input-angka" id="input-diskon" value="0"
				autocomplete="off" />
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-primary btnKeluar"
			data-dismiss="modal" id="btnDiskon">UPDATE</button>
	</div>
</div>

<div class="modal fade" id="bayar-modal" tabindex="-1" data-width="700"
	style="display: none;">
	<form class="form-horizontal form-bayar" method="POST">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Bayar</h4>
		</div>
		<div class="modal-body">
			<div class="form-group">
				<label class="col-sm-4 control-label">Total Pembelian</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-lg input-angka"
						id="totalPembelianFinal" readonly="readonly" value="0"
						name="totalPembelianFinal">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Jumlah Pembelian</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-lg input-angka"
						id="jumlahPembelianFinal" readonly="readonly" value="0"
						name="jumlahPembelianFinal">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Total Pembayaran</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-lg input-angka"
						id="totalPembayaran" value="0" name="totalPembayaran"
						autocomplete="off">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Kembalian</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-lg input-angka"
						id="kembalian" readonly="readonly" value="0" name="kembalian"
						autocomplete="off">
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="JUAL"
				id="button-jual" style="width: 65%" />
		</div>
	</form>
</div>

<div class="modal fade" id="cetak-modal" tabindex="-1" data-width="400"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Pesan</h4>
	</div>
	<div class="modal-body" style="text-align: center;">
		<div id="pesan-penjualan"></div>
	</div>
	<form action="${cetakUrl}" class="form-horizontal style-form formCetak"
		method="POST" target="_blank">
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="keluarModalHapus" data-dismiss="modal">Tidak</button>
			<input type="hidden" name="id" class="form-control" id="penjualanId" />
			<input type="submit" class="btn btn-danger" id="button-cetak"
				value="Cetak" />
		</div>
	</form>
</div>

<div class="modal fade" id="utang-modal" tabindex="-1" data-width="400" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Pesan</h4>
	</div>
	<div class="modal-body" style="text-align: center;">
		<div id="pesan-utang"></div>
	</div>	
	<div class="modal-footer">
		<button type="button" class="btn btn-default btnKeluar" id="keluar-modal" data-dismiss="utang-modal">Tidak</button>
		<button type="button" class="btn btn-primary" id="button-lanjut-transaksi">Lanjut</button>		
	</div>	
</div>


<button class="btn btn-primary btnHide" data-toggle="modal"
	data-target="#pesanModal" id="pesanButton">-</button>

<form action="${cetakUrl}" method="POST">
	<button class="btn btn-primary btnHide" id="cetakButton">-</button>
</form>

<script>
	var randomId = Math.floor(Math.random() * 1000000);
	var halamanObat = 1;
	var cariObat = '';
	var diskonTotal = "0";
	var pajakTotal = "0";

	$(document).ready(
			function() {
				refreshObat(halamanObat, cariObat);

				$("#cariObat").keyup(function() {
					refreshObat(1, $('#cariObat').val());
				});

				$("#btnPajak").click(function() {
					hitungPajakAtauDiskon("#input-pajak", 0);
				});

				$("#btnDiskon").click(function() {
					hitungPajakAtauDiskon("#input-diskon", 1);
				});

				$("#button-bayar").click(function(e) {
					e.preventDefault();
					cekStokPerObat(randomId);
				});
				
				$("#button-jual").click(function(e) {
					e.preventDefault();
					var total_pembayaran = parseFloat($("#totalPembayaran").val().replace(/\./g, ''));
					var total_pembelian = parseFloat($("#totalPembelianFinal").val().replace(/\./g, ''));
					var i = 0;
					if (total_pembayaran < total_pembelian){
						console.log('Pesan Utang');
						var p = "Total Pembayaran lebih kurang dari total Belanja. Transaksi ini akan tercatat sebagai Piutang. <p>Lanjutkan Transaksi ?</p>";
						$("#pesan-utang").empty();
						$("#pesan-utang").append(p);
						$("#utang-modal").modal('show');						
						$('#button-lanjut-transaksi').click(function (e) {
							i = i+1;
							if (i == 1){
//	 							e.preventDefault();
								$("#utang-modal").modal('hide');
								console.log('Bayar utang');
								$(".form-bayar").submit();	
							}
														
						});
					} else {
						console.log('Bayar non utang');
						$(".form-bayar").submit();
					}
					i = 0;
				});

				$("#button-cetak").click(function() {
					$("#cetak-modal").modal('hide');
				});
				
				$("#keluar-modal").click(function() {
					$("#utang-modal").modal('hide');
				});

				hitungHargaTotalOnKeyUp("#editJumlah", "#editHargaTotal");
				hitungHargaTotalOnKeyUp("#editHarga", "#editHargaTotal");
				hitungHargaTotalOnKeyUp("#editDiskon", "#editHargaTotal");

				$("#totalPembayaran").keyup(
						function() {
							$("#kembalian").val(
									hitungKembalian($("#totalPembelianFinal"),
											$("#totalPembayaran")));
						});

				$(".formEdit").validate({
					rules : {
						editObat : {
							required : true
						},
						editJumlah : {
							required : true,
							min : 1
						},
						editHarga : {
							required : true
						},
						editHargaTotal : {
							required : true
						},
						editDiskon : {
							required : true,
						}
					},
					messages : {
						editObat : {
							required : "Nama Wajib Diisi",
						},
						editJumlah : {
							required : "Jumlah Wajib Diisi",
							min : "Isi Jumlah Dengan Benar"
						},
						editHarga : {
							required : "Harga Wajib Diisi"
						},
						editHargaTotal : {
							required : "Harga Total Wajib Terisi"
						},
						editDiskon : {
							required : "Diskon Wajib Diisi"
						}
					},
					submitHandler : function(form) {
						var data = {};
						data = setObat(data);
						$.postJSON('${editObatTerpilihUrl}', data, function() {
							$('.btnKeluar').click();
							refreshDaftarObat(randomId);
							refreshObat(halamanObat, cariObat);
							resetEditObatData();
						}, function() {
							$('#gritter-edit-gagal').click();
						});
					}
				});

				$(".form-bayar").validate({
					rules : {
						totalPembelianFinal : {
							required : true,
							min : 1,
							number : true
						},
						jumlahPembelianFinal : {
							required : true,
							min : 1,
							number : true
						},
						totalPembayaran : {
							required : true,
							min : 0,
							number : true,
// 							greaterEqualThan : '#totalPembelianFinal'
						},
						kembalian : {
							required : true,
							number : true
						}
					},
					messages : {
						totalPembelianFinal : {
							required : "Total Pembelian Wajib Terisi",
							min : "Total Pembelian Tidak Boleh 0",
							number : "Masukkan Angka"
						},
						jumlahPembelianFinal : {
							required : "Jumlah Pembelian Wajib Diisi",
							min : "Jumlah Pembelian Tidak Boleh 0",
							number : "Masukkan Angka"
						},
						totalPembayaran : {
							required : "Harga Wajib Diisi",
							min : "Total Pembayaran Tidak Boleh Kurang dari 0",
							number : "Masukkan Angka",
// 							greaterEqualThan : "Jumlah Bayar Kurang"
						},
						kembalian : {
							required : "Kembalian Wajib Terisi",
							number : "Masukkan Angka"
						}
					},
					submitHandler : function(form) {						
						var data = {};
						data = setPenjualanData(data);
						$.postJSON('${penjualanUrl}', data, function(result) {
							$('.btnKeluar').click();
							$('#penjualanId').val(result.id);
							$('#pesan-penjualan').empty();
							$('#pesan-penjualan').append(result.info);
							console.log(result.info);
							$('#cetak-modal').modal('show');
							resetEditObatData();
							resetAll();
							randomId = Math.floor(Math.random() * 1000000);
						}, function(result) {
							$('#gritter-edit-gagal').click();
							console.log(result.info);
						});
					}
				});

				setMaskingUang("#input-diskon");
				setMaskingUang("#input-pajak");
				setMaskingUang("#editJumlah");
				setMaskingUang("#editHarga");
				setMaskingUang("#editHargaTotal");
				setMaskingUang("#editDiskon");
			});

	function tambahObat(id) {
		var data = {
			idObat : id,
			randomId : randomId
		};
		$.postJSON('${tambahObatUrl}', data, function(result) {
			refreshDaftarObat(randomId);
			refreshObat(halamanObat, cariObat);
		}, function() {
			console.log(result.info);
			$('#gritter-tambah-gagal').click();
		});
	}

	function refreshDaftarObat(id) {
		if (id != null) {
			var data = {
				randomId : id
			};
			$.getAjax('${listObatTerpilihUrl}', data, function(result) {
				$('#tabel-obat').empty();
				$('#tabel-obat').append(result.tabelTerapi);
				$('#barang').empty();
				$('#barang').val(result.value2);
				$('#total').empty();
				$('#total').val(result.value1);
				$('#pajak').val(pajakTotal);
				$('#diskon').val(diskonTotal);
				$('#totalBayar').val(
						tambahTitik(hitungHargaTotal(result.value1, pajakTotal,
								diskonTotal)));
			}, null);
		}
	}

	function refreshObat(halaman, find) {
		var data = {
			hal : halaman,
			cari : find,
			n : 14
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

	function editObat(id) {
		resetEditObatData();
		var data = {
			idObat : id,
			randomId : randomId
		};
		$.getAjax('${dapatkanObatTerpilihUrl}', data, function(result) {
			$('#editObat').val(result.obat);
			$('#editJumlah').val(result.jumlah);
			$('#editHarga').val(result.hargaJual);
			$('#editHargaTotal').val(result.subTotal);
			$('#editDiskon').val(result.diskon);
			$('#editId').val(result.idObat);
		}, null);
	}

	function cekStokPerObat(rid) {
		var data = {
			randomId : rid
		};
		$.getAjax('${cekStokListObatUrl}', data, function(result) {
			if (result.info != "OKE") {
				$('#pesan').empty();
				$('#pesan').append(result.info);
				console.log(result.info);
				$('#pesan-modal').modal('show');
			} else {
				$("#totalPembelianFinal").val(
						$("#totalBayar").val().replace(/\./g, ''));
				$("#jumlahPembelianFinal").val(
						$("#barang").val().replace(/\./g, ''));
				$('#bayar-modal').modal('show');
				$('#totalPembayaran').focus();
			}
		}, null);
	}

	function hapusObat(id) {
		var data = {
			idObat : id,
			randomId : randomId
		};
		$.postJSON('${hapusObatTerpilihUrl}', data, function(result) {
			refreshDaftarObat(randomId);
			console.log(result.info);
		}, function() {
			console.log(result.info);
		});
	}

	function hitungHargaTotalOnKeyUp(origin, hargaTotal) {
		$(origin).keyup(
				function() {
					$(hargaTotal).val(
							hitungHargaTotalPerObat("#editHarga",
									"#editDiskon", "#editJumlah"));
				});
	}

	function hitungHargaTotalPerObat(hrg, dsk, jum) {
		if ($(hrg).val() != '' && $(dsk).val() != '' && $(jum).val() != '') {
			var hargaBeli = parseFloat($(hrg).val().replace(/\./g, ''));
			var diskon = parseFloat($(dsk).val().replace(/\./g, ''));
			var jumlah = parseInt($(jum).val().replace(/\./g, ''), 10);
			var hargaTotal = hargaBeli * jumlah;
			hargaTotal = hargaTotal - diskon;
			return hargaTotal;
		}
	}

	function hitungHargaTotal(hrg, pjk, dsk) {
		if (hrg != '' && pjk != '' && dsk != '') {
			var hargaBeli = parseFloat(hrg.replace(/\./g, ''));
			var pajak = parseFloat(pjk.replace(/\./g, ''));
			var diskon = parseFloat(dsk.replace(/\./g, ''));
			var hargaTotal = hargaBeli - diskon;
			hargaTotal = hargaTotal + pajak;
			return hargaTotal;
		}
	}

	function hitungPajakAtauDiskon(inputan, n) {
		if ($("#total").val() != "0") {
			if (n == 0) {
				pajakTotal = $(inputan).val();
			} else if (n == 1) {
				diskonTotal = $(inputan).val();
			}
			refreshDaftarObat(randomId);
		}
	}

	function hitungKembalian(beli, bayar) {
		var totalBeli = $(beli).val().replace(/\./g, '');
		var totalBayar = $(bayar).val().replace(/\./g, '');
		var kembali = totalBayar - totalBeli;
		return kembali;
	}

	function setObat(data) {
		data['idObat'] = $('#editId').val();
		data['obat'] = $('#editObat').val();
		data['jumlah'] = $('#editJumlah').val();
		data['hargaJual'] = $('#editHarga').val();
		data['subTotal'] = $('#editHargaTotal').val();
		data['diskon'] = $('#editDiskon').val();
		data['randomId'] = randomId;
		return data;
	}

	function setPenjualanData(data) {
		data['randomId'] = randomId;
		data['tanggal'] = $('#tanggal').val();
		data['pelanggan'] = $('#pelanggan').val();
		data['tanggal'] = $('#tanggal').val();
		data['totalPembelian'] = $('#total').val();
		data['pajak'] = $('#pajak').val();
		data['diskon'] = $('#diskon').val();
		data['totalPembelianFinal'] = $('#totalPembelianFinal').val();
		data['totalPembayaran'] = $('#totalPembayaran').val();
		return data;
	}

	function resetEditObatData() {
		$('#editId').val('');
		$('#editObat').val('');
		$('#editJumlah').val('0');
		$('#editHarga').val('0');
		$('#editHargaTotal').val('0');
		$('#editDiskon').val('0');
	}

	function resetAll() {
		$('#pelanggan').val('');
		halamanObat = 1;
		cariObat = '';
		diskonTotal = "0";
		pajakTotal = "0";
		refreshObat(halamanObat, cariObat);
		$('#tabel-obat').empty();
		$('#barang').val('0');
		$('#total').val('0');
		$('#diskon').val('0');
		$('#pajak').val('0');
		$('#input-diskon').val('0');
		$('#input-pajak').val('0');
		$('#totalBayar').val('0')
		$('#totalPembayaran').val('0');
		$('#totalPembelian').val('0');
		$('#jumlahPembelian').val('0');
		$('#kembalian').val('0');
		$('#cariObat').val('');
	}
</script>