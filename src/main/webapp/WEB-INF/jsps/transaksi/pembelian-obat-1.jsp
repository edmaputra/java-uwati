<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="cariObatUrl" value="/pembelian-obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="beliUrl" value="/pembelian-obat/beli" />
<c:url var="tersediaUrl" value="/pembelian-obat/tersedia" />
<c:url var="cetakUrl" value="/pembelian-obat/cetak" />

<c:url var="obatListUrl" value="/obat/obat" />
<c:url var="tambahObatUrl" value="/pembelian-obat/tambah-obat" />
<c:url var="listObatTerpilihUrl" value="/pembelian-obat/list-obat" />
<c:url var="editObatTerpilihUrl" value="/pembelian-obat/edit-obat" />
<c:url var="hapusObatTerpilihUrl" value="/pembelian-obat/hapus-obat" />
<c:url var="dapatkanObatTerpilihUrl"
	value="/pembelian-obat/dapatkan-obat" />
<c:url var="cekStokListObatUrl" value="/pembelian-obat/cek-stok" />
<c:url var="penjualanUrl" value="/pembelian-obat/jual" />


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
							<input name="distributor" type="text" id="distributor"
								class="form-control" placeholder="Distributor" />
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-md-5 form-group">
						<input name="nomor_faktur" type="text" id="nomor_faktur"
							class="form-control" placeholder="Nomor Faktur"
							autocomplete="off" />
					</div>
					<div class="col-md-7 form-group">
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
						<table class="keterangan-beli tabel-beli" style="width: 100%">
							<tr>
								<td>Barang</td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="barang" value="0"
									style="background-color: #f57900;"></td>
								<td>Total</td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="total" value="0"
									style="background-color: #f57900;"></td>
							</tr>
							<tr>
								<td><a href="#" data-toggle="modal"
									data-target="#diskon-modal" style="color: black;">Diskon</a></td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="diskon" value="0"
									style="background-color: #f57900;"></td>
								<td><a href="#" data-toggle="modal"
									data-target="#pajak-modal" style="color: black;">Pajak</a></td>
								<td><input type="text" readonly="readonly"
									class="form-control" id="pajak" value="0"
									style="background-color: #f57900;"></td>
							</tr>
							<tr>
								<td colspan="2">Total Bayar</td>
								<td colspan="2"><input type="text" readonly="readonly"
									class="form-control" id="totalBayar" value="0"
									style="background-color: #f57900;"></td>
							</tr>
						</table>
					</div>
					<div class="col-md-7">
						<div class="form-group">
							<table style="width: 100%; height: 100%;">
								<tr>
									<td style="position: relative; height: 100px; font-size: 16pt; text-align: center;">
										<button
											style="position: absolute; top: 0; bottom: 0; border-radius: 0; right: 0; width: 100%; background-color: blue; color: white;" id="button-bayar">Beli</button>
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
<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<div class="modal fade" id="cetak-modal" tabindex="-1"
	style="display: none;">

	<div class="modal-header">
		<h4 class="modal-title" id="myModalLabel">Pembelian Tersimpan</h4>
	</div>
	<div class="modal-body">
		<div id="pesan-cetak-modal" style="text-align: center;"></div>
		<div style="text-align: center;">Apakah Anda Ingin Mencetak Bukti Pembelian ?</div>
	</div>
	<form action="${cetakUrl}" class="form-horizontal style-form formCetak"
		method="POST" target="_blank">
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="keluarModal" data-dismiss="modal">Tidak</button>
			<input type="hidden" name="id" class="form-control" id="beli_id" />
			<input type="submit" id="cetak" class="btn btn-primary" value="CETAK" />
		</div>
	</form>
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
							name="edit_obat" id="edit_obat" readonly="readonly"
							autocomplete="off">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label>Jumlah:</label> <input type="number" class="form-control"
							name="edit_jumlah" id="edit_jumlah" autocomplete="off"
							style="text-align: right;">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Harga Beli:</label> <input type="text"
							class="form-control input-angka" name="edit_harga_beli"
							id="edit_harga_beli" autocomplete="off">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label>Harga Total:</label> <input type="text"
							class="form-control input-angka" name="edit_harga_total"
							id="edit_harga_total" readonly="readonly" autocomplete="off">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Diskon:</label> <input type="text"
							class="form-control input-angka" name="edit_diskon"
							id="edit_diskon" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 form-group">
					<label>Harga Jual:</label> <input type="text"
						class="form-control input-angka" name="edit_harga_jual"
						id="edit_harga_jual" autocomplete="off">
				</div>
				<div class="col-md-4 form-group">
					<label>Tanggal Kadaluarsa:</label> <input type="text"
						class="form-control datePicker" name="edit_tanggal_kadaluarsa"
						id="edit_tanggal_kadaluarsa" autocomplete="off">
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<input type="hidden" class="form-control" name="edit_id" id="edit_id" />
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="UPDATE"
				id="update-obat" />
		</div>
	</form>
</div>

<div class="modal fade" id="pajak-modal" tabindex="-1"
	style="display: none;" data-width="300">
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

<div class="modal fade" id="diskon-modal" tabindex="-1"
	style="display: none;" data-width="300">
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
				<label class="col-sm-4 control-label">Total Pembayaran</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-lg input-angka"
						id="bayar" value="0" name="bayar" autocomplete="off">
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar" data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="BELI" id="button-beli" style="width: 65%" />
		</div>
	</form>
</div>

<script>
	$(document).ready(function() {
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
			cek("#tanggal", "#distributor", "#nomor_faktur");
		});
		
		$("#cetak").click(function() {
			$('#bayar-modal').modal('show');
		});


		hitungHargaTotalOnKeyUp("#edit_jumlah", "#edit_harga_total");
		hitungHargaTotalOnKeyUp("#edit_harga_beli", "#edit_harga_total");
		hitungHargaTotalOnKeyUp("#edit_diskon", "#edit_harga_total");

		$(".formEdit").validate({
			rules : {
				edit_obat : {
					required : true
				},
				edit_jumlah : {
					required : true,
					min : 1
				},
				edit_harga_beli : {
					required : true
				},
				edit_harga_total : {
					required : true
				},
				edit_diskon : {
					required : true,
				},
				edit_harga_jual : {
					required : true,
				},
				edit_tanggal_kadaluarsa : {
					required : true,
				}
			},
			messages : {
				edit_obat : {
					required : "Obat Wajib Diisi",
				},
				edit_jumlah : {
					required : "Jumlah Wajib Diisi",
					min : "Isi Jumlah Dengan Benar"
				},
				edit_harga : {
					required : "Harga Wajib Diisi"
				},
				edit_harga_total : {
					required : "Harga Total Wajib Terisi"
				},
				edit_diskon : {
					required : "Diskon Wajib Diisi"
				},
				edit_harga_jual : {
					required : "Harga Jual Wajib Diisi"
				},
				edit_tanggal_kadaluarsa : {
					required : "Tanggal Kadaluarsa Wajib Diisi"
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
				bayar : {
					required : true,
					number : true,
					min : 0,
					lessEqualThan : '#totalPembelianFinal'
				},
			},
			messages : {
				bayar : {
					required : "Harap Isi Jumlah Pembayaran",
					number : "Harap Isi dengan Angka",
					min : "Minimal Isi Dengan Angka 0",
					lessEqualThan : 'Jumlah Bayar Lebih Besar'
				},
			},
			submitHandler : function(form) {
				var data = {};
				data = setPembelian(data);
				$.postJSON('${beliUrl}', data, function(result) {
					var p = "Pembelian Tersimpan, Stok Update Diperbarui.";
					$('#pesan-cetak-modal').empty();
					$('#pesan-cetak-modal').append(p);
					if (data['totalPembelianFinal'] >  data['bayar']){
						var utang = data['totalPembelianFinal'] - data['bayar'];
						var u = " Pembayaran Pembelian menyisakan utang sebesar "+ utang;
						$('#pesan-cetak-modal').append($('<p>').text(u));
					}
										
					$('#beli_id').val(result.id);
					$('#cetak-modal').modal('show');
					resetAll();
					randomId = Math.floor(Math.random() * 1000000);
					refreshObat(halamanObat, cariObat);					
				}, function() {
					$('#gritter-tambah-gagal').click();
				});
			}
		});

		setMaskingUang("#input-diskon");
		setMaskingUang("#input-pajak");
		// 		setMaskingUang("#edit_jumlah");
		setMaskingUang("#edit_harga_beli");
		setMaskingUang("#edit_harga_total");
		setMaskingUang("#edit_diskon");
		setMaskingUang("#edit_harga_jual");
	});

	var randomId = Math.floor(Math.random() * 1000000);
	var halamanObat = 1;
	var cariObat = '';
	var diskonTotal = "0";
	var pajakTotal = "0";

	function cek(tanggal, distributor, nomor_faktur) {
		if ($(tanggal).val() != '' && $(distributor).val() != '' && $(nomor_faktur).val() != '') {
			$('#totalPembelianFinal').val($('#totalBayar').val().replace(/\./g, ''));
			$('#bayar-modal').modal('show');
		} else {
			var p = "Harap Isi Tanggal, Distributor dan Nomor Faktur Pembelian";
			$('#pesan').empty();
			$('#pesan').append(p);
			$('#pesan-modal').modal('show');
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

	function editObat(id) {
		resetEditObatData();
		var data = {
			idObat : id,
			randomId : randomId
		};
		$.getAjax('${dapatkanObatTerpilihUrl}', data, function(result) {
			$('#edit_obat').val(result.obat);
			$('#edit_jumlah').val(result.jumlah);
			$('#edit_harga_beli').val(result.hargaBeli);
			$('#edit_harga_total').val(result.subTotal);
			$('#edit_diskon').val(result.diskon);
			$('#edit_harga_jual').val(result.hargaJual);
			$('#edit_tanggal_kadaluarsa').val(result.tanggalExpired);
			$('#edit_id').val(result.idObat);
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
							hitungHargaTotalPerObat("#edit_harga_beli",
									"#edit_diskon", "#edit_jumlah"));
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

	function resetEditObatData() {
		$('#edit_id').val('');
		$('#edit_obat').val('');
		$('#edit_jumlah').val('0');
		$('#edit_harga_beli').val('0');
		$('#edit_harga_jual').val('0');
		$('#edit_harga_total').val('0');
		$('#edit_diskon').val('0');
		$('#edit_tanggal_kadaluarsa').val('');
	}

	function setObat(data) {
		data['idObat'] = $('#edit_id').val();
		data['obat'] = $('#edit_obat').val();
		data['jumlah'] = $('#edit_jumlah').val();
		data['hargaJual'] = $('#edit_harga_jual').val();
		data['hargaBeli'] = $('#edit_harga_beli').val();
		data['subTotal'] = $('#edit_harga_total').val();
		data['diskon'] = $('#edit_diskon').val();
		data['tanggalExpired'] = $('#edit_tanggal_kadaluarsa').val();
		data['randomId'] = randomId;
		return data;
	}

	function setPembelian(data) {
		data['tanggal'] = $('#tanggal').val();
		data['distributor'] = $('#distributor').val();
		data['nomorFaktur'] = $('#nomor_faktur').val();
		data['pajak'] = $('#pajak').val();
		data['diskon'] = $('#diskon').val();
		data['pajak'] = $('#pajak').val();
		data['totalPembelian'] = $('#total').val();
		data['totalPembelianFinal'] = $('#totalBayar').val().replace(/\./g, '');;
		data['bayar'] = $('#bayar').val();
		data['randomId'] = randomId;
		return data;
	}

	function resetAll() {
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
		$('#cariObat').val('');
		$('#totalBayar').val('0')
		$('#distributor').val('');
		$('#nomor_faktur').val('');
		$('#totalPembelianFinal').val('0');
		$('#bayar').val('0');		
	}
</script>