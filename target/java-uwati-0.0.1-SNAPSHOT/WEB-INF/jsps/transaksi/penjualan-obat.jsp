<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/penjualan-obat/tambah" />
<c:url var="cariObatUrl" value="/obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="tambahKeListObatUrl" value="/penjualan-obat/tambahTemp" />
<c:url var="hapusObatUrl" value="/penjualan-obat/hapusTemp" />
<c:url var="daftarTempUrl" value="/penjualan-obat/daftarTemp" />
<c:url var="beliUrl" value="/penjualan-obat/beli" />
<c:url var="tersediaUrl" value="/penjualan-obat/tersedia" />
<c:url var="cetakUrl" value="/penjualan-obat/cetak" />

<div class="row mt">
	<div class="col-md-12">
		<div class="form-panel">
			<form class="form formTambah" id="formObat" method="POST">
				<div class="row">
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Nomor Faktur</label> 
							<input name="nomorFaktur" type="text" id="nomorFaktur" class="form-control" readonly="readonly"/>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Tanggal Beli</label> 
							<input name="tanggal" type="text" id="tanggal" class="form-control datePicker" value="${tanggalHariIni}" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Jenis</label> <select class="form-control"
								name="jenis-jual" id="jenis">
								<option value="default"></option>
								<option value="0">Umum</option>
								<option value="1">Dokter</option>
								<option value="2">Resep</option>
							</select>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Pelanggan/Pasien</label> <input
								name="pelanggan" type="text" id="pelanggan" class="form-control"
								readonly="readonly" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Dokter</label> <input name="dokter"
								type="text" id="dokter" class="form-control" readonly="readonly" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="lb-sm">Obat</label> 
							<input name="obat" type="text" id="obat" class="form-control" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Harga Jual</label> <input name="hargaJual"
								type="text" id="hargaJual" class="form-control" />
						</div>
					</div>

					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Diskon</label> <input name="diskon"
								type="text" id="diskon" class="form-control" />
						</div>
					</div>

					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">%</label> <input name="persenDiskon"
								type="text" id="persenDiskon" class="form-control" />
						</div>
					</div>



				</div>

				<div class="row">
					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">Stok</label> <input name="stok" type="text"
								id="stok" class="form-control" readonly="readonly" />
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">Jumlah</label> <input name="jumlah"
								type="text" id="jumlah" class="form-control" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Satuan</label> <input name="satuan"
								type="text" id="satuan" class="form-control" readonly="readonly" />
						</div>
					</div>

					<div class="col-md-2 col-md-offset-2">
						<div class="form-group">
							<label class="lb-sm">Pajak</label> <input name="pajak"
								type="text" id="pajak" class="form-control" />
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">%</label> <input name="persenPajak"
								type="text" id="persenPajak" class="form-control" />
						</div>
					</div>

					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Subtotal</label> <input name="subtotal"
								type="text" id="subTotal" class="form-control"
								readonly="readonly" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Racikan</label>
							<button class="form-control btn btn-default" id="racikan"
								data-toggle="modal" data-target="#tambahRacikanModal">Tambah
								Racikan</button>
						</div>
					</div>
					<div class="col-md-4 col-md-offset-5">
						<div class="form-group">
							<label class="lb-sm">Tambah Obat</label> <input type="submit"
								class="form-control btn btn-primary" id="tambah"
								value="Tambahkan">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<table class="table table-striped table-advance table-hover"
							id="tabel">
							<thead>
								<tr>
									<th>Obat</th>
									<th>Jumlah</th>
									<th>Harga Satuan</th>
									<th>Pajak</th>
									<th>Diskon</th>
									<th>Sub Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="body"></tbody>
						</table>
					</div>
					<div class="col-md-4">
						<div class="form-panel">
							<h1 id="grandTotal" class="text-right">0</h1>
						</div>
					</div>
				</div>

			</form>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<button class="form-control btn btn-primary" id="jual">Jual
							dan Simpan</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="tambahRacikanModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Tambah Racikan</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default btnKeluar"
					id="keluarModal" data-dismiss="modal">Batal</button>
				<input type="submit" class="btn btn-primary" value="Tambah" />
			</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		$('#obat').blur(function() {
			var val = $('#obat').val();
			isiDataObat(val);
		});
		
		$('#jumlah').blur(function() {
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#jumlah'));			
		});
		
		$('#hargaJual').blur(function() {
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#jumlah'));
		});

		$("#obat").keyup(function(event) {
			if (event.keyCode == 13) {
				var val = $('#obat').val();
				isiDataObat(val);
			}
		});

		$("#racikan").click(function(event) {
			event.preventDefault();
		});

		tabKey('#obat', '#jumlah');
		tabKey('#jumlah', '#hargaJual');
		tabKey('#hargaJual', '#diskon');
		tabKey('#diskon', '#persenDiskon');
		tabKey('#persenDiskon', '#pajak');
		tabKey('#pajak', '#persenPajak');
		tabKey('#persenPajak', '#tambah');

		$('#jenis').on('change', function(e) {
			var optionSelected = $("option:selected", this);
			var valueSelected = this.value;
			if (valueSelected == 'umum') {
				$('#pelanggan').val('');
				document.getElementById('pelanggan').readOnly = true;
				$('#dokter').val('');
				document.getElementById('dokter').readOnly = true;
			} else if (valueSelected == 'dokter') {
				document.getElementById('pelanggan').readOnly = false;
				document.getElementById('dokter').readOnly = false;
			} else if (valueSelected == 'resep') {
				document.getElementById('pelanggan').readOnly = false;
				$('#dokter').val('');
				document.getElementById('dokter').readOnly = true;
			}
		});

// 		$('#tambah').click(function() {
// 			console.log('Tambah');
			$(".formTambah").validate({
				rules : {
					tanggal : {
						required : true
					},
					jenis : {
						valueNotEquals : "default"
					},
					obat : {
						required : true
					},
					stok : {
						min : 1,
						required : true,						
					},
					jumlah : {
						min : 1,
						required : true,
						lessEqualThan: '#stok'
					},
					satuan : {
						required : true
					},
					hargaJual : {
						required : true
					},
// 					subtotal : {
// 						required : true
// 					}
				},
				messages : {
					tanggal : {
						required : "Harap Pilih Tanggal"
					},
					jenis : {
						valueNotEquals : "Pilih Jenis Penjualan"
					},
					obat : {
						required : "Masukan Nama Obat"
					},
					stok : {
						min : "Minimal 1",
						required : "Stok Kosong, Harap Diisi",
					},
					jumlah : {
						min : "Jumlah Pembelian Minimal 1",
						required : "Harap Isi Jumlah Pembelian",
						lessEqualThan: 'Jumlah Beli Melebihi Stok'
					},
					satuan : {
						required : "Isi Satuan"
					},
					hargaJual : {
						required : "Isi Harga Jual"
					},
// 					subtotal : {
// 						required : "Subtotal Kosong"
// 					}
				},
				submitHandler : function(form) {
					$('#subtotal').val(hitungSubTotal('#hargaJual', '#jumlah'));					
					var data = setDataObat();					
					$.postJSON('${tambahKeListObatUrl}', data, function(r) {
						clean();
						$('#nomorFaktur').val(r.nomorFaktur);
						reloadListObat();
						document.getElementById('tanggal').disabled = true;						
					}, function() {
						$('#gritter-tambah-gagal').click();						
					});
// 					clean();
				}
			});
// 			event.preventDefault();
// 		});

		setAutoComplete('#obat', '${cariObatUrl}');
		setMaskingUang("#hargaJual");
		setMaskingUang("#diskon");
		setMaskingUang("#pajak");
		setMaskingUang("#subTotal");

	});

	function isiDataObat(val) {
		var data = {
			nama : val
		};
		$.getAjax('${getObatUrl}', data, function(obat) {
			$('#hargaJual').val(obat.detail[0].hargaJual);
			$('#stok').val(obat.stok[0].stok);
			$('#satuan').val(obat.satuan.nama);
		}, null);
	}
	
	function hitungSubTotal(hrg, jum){
		var hargaBeli = $(hrg).val();			
		var hargaBeli = hargaBeli.replace(/\./g, '');			
		var jumlah = $(jum).val();		
		var subTotal = hargaBeli * jumlah;		
		return subTotal;				
	}
	
	function setDataObat(){
		var data = {};		
		data['tanggal'] = $('#tanggal').val();
		data['nomorFaktur'] = $('#nomorFaktur').val();
		data['dokter'] = $('#dokter').val();
		data['pelanggan'] = $('#pelanggan').val();
		data['obat'] = $('#obat').val();
		data['jumlah'] = $('#jumlah').val();		
		data['hargaJual'] = $('#hargaJual').val();
		data['diskon'] = $('#diskon').val();
		data['pajak'] = $('#pajak').val();
		data['subTotal'] = $('#subTotal').val();		
		return data;
	}
	
	function setDataFromRacikan(){
		
	}
	
	function reloadListObat() {
		var nomorFaktur = $('#nomorFaktur').val();
		console.log(nomorFaktur);
		var data = {
			n : nomorFaktur
		};
		$.getAjax('${daftarTempUrl}', data, function(result) {			
			$('#body').empty();
			$('#body').append(result.tabel);
			$('#grandTotal').empty();
			$('#grandTotal').append(result.grandTotal);
		}, console.log(''));
	}
	
	function hapus(index){
		var data = {};
		data['id'] = index;
		data['nomorFaktur'] = $('#nomorFaktur').val();
		$.postJSON('${hapusObatUrl}', data, function() {
			reloadListObat();			
		}, function(e) {
			console.log('GAGAL HAPUS');
		});
	}

	function tabKey(origin, destination) {
		$(".formTambah").on('keydown', origin, function(e) {
			var keyCode = e.keyCode || e.which;
			if (keyCode == 9) {
				e.preventDefault();
				$(destination).focus();
			}
		});
	}

	function clean() {
		$('#obat').val('');
		$('#hargaJual').val('');
		$('#diskon').val('');
		$('#personDiskon').val('');
		$('#pajak').val('');
		$('#persenPajak').val('');
		$('#stok').val('');
		$('#satuan').val('');
		$('#jumlah').val('');
		$('#subTotal').val('');
	}

	function cleanAll() {
		$('#obat').val('');
		$('#hargaJual').val('');
		$('#diskon').val('');
		$('#personDiskon').val('');
		$('#pajak').val('');
		$('#persenPajak').val('');
		$('#stok').val('');
		$('#satuan').val('');
		$('#jumlah').val('');
		$('#tanggal').val('');
		$('#jenis').val('default');
		$('#dokter').val('');
		$('#pelanggan').val('');
		$('#subTotal').val('');
	}
</script>