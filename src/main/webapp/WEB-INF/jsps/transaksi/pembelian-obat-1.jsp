<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/pembelian-obat/tambah" />
<c:url var="cariObatUrl" value="/pembelian-obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="tambahObatUrl" value="/pembelian-obat/tambahTemp" />
<c:url var="hapusObatUrl" value="/pembelian-obat/hapusTemp" />
<c:url var="daftarTempUrl" value="/pembelian-obat/daftarTemp" />
<c:url var="beliUrl" value="/pembelian-obat/beli" />
<c:url var="tersediaUrl" value="/pembelian-obat/tersedia" />
<c:url var="cetakUrl" value="/pembelian-obat/cetak" />

<c:url var="obatListUrl" value="/obat/obat" />
<c:url var="tambahObatUrl" value="/pembelian-obat/tambah-obat" />
<c:url var="listObatTerpilihUrl" value="/pembelian-obat/list-obat" />
<c:url var="editObatTerpilihUrl" value="/pembelian-obat/edit-obat" />
<c:url var="hapusObatTerpilihUrl" value="/pembelian-obat/hapus-obat" />
<c:url var="dapatkanObatTerpilihUrl" value="/pembelian-obat/dapatkan-obat" />
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
								class="form-control" placeholder="Distributor"  />
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-md-5 form-group">
						<input name="nomor_faktur" type="text" id="nomor_faktur"
								class="form-control" placeholder="Nomor Faktur" autocomplete="off" />
					</div>
					<div class="col-md-7 form-group">
						<input type="text" name="cariObat" class="form-control"
							id="cariObat" placeholder="Pencarian" autocomplete="off"/>
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
						<table class="keterangan-beli tabel-beli"
							style="width: 100%">
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
					<div class="col-md-6">
						<div class="form-group">							
							<a href="#" class="form control btn btn-primary" style="width: 100%; height: 100%;"  id="button-bayar">BAYAR</a>
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
<!-- 							<a href="#" class="form control btn btn-default" style="height: 100%;" id="button-batal" onclick="resetAll()">Batal</a>							 -->
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

<div class="modal fade" id="cetakModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Pembelian Tersimpan</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Ingin Mencetak Bukti Pembelian ?</p>
				</div>
			</div>
			<form action="${cetakUrl}" class="form-horizontal style-form formCetak" method="POST" target="_blank">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar" id="keluarModal" data-dismiss="modal" onclick="muatUlangHalaman()">Tidak</button>
					<input type="hidden" name="id" class="form-control" id="beliId" />
					<input type="submit" id="cetak" class="btn btn-primary" value="CETAK" />
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="edit-obat-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form class="form style-form formEdit" method="post">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Edit Obat</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>Obat:</label> 
								<input type="text" class="form-control"	name="edit_obat" id="edit_obat" readonly="readonly" autocomplete="off">
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label>Jumlah:</label> 
								<input type="text" class="form-control input-angka" name="edit_jumlah" id="edit_jumlah" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label>Harga Beli:</label> 
								<input type="text"class="form-control input-angka" name="edit_harga_beli" id="edit_harga_beli" autocomplete="off">
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label>Harga Total:</label> 
								<input type="text" class="form-control input-angka" name="edit_harga_total" id="edit_harga_total" readonly="readonly" autocomplete="off">
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Diskon:</label> 
								<input type="text" class="form-control input-angka" name="edit_diskon" id="edit_diskon" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 form-group">							
							<label>Harga Jual:</label> 
							<input type="text" class="form-control input-angka" name="edit_harga_jual" id="edit_harga_jual" autocomplete="off">
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
			</div>
		</form>
	</div>
</div>

<div class="modal fade" id="pajak-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Pajak</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Pajak:</label> <input type="text"
						class="form-control input-angka" id="input-pajak" value="0" autocomplete="off"/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btnKeluar"
					data-dismiss="modal" id="btnPajak">UPDATE</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="diskon-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Diskon</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Diskon:</label> <input type="text"
						class="form-control input-angka" id="input-diskon" value="0" autocomplete="off"/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btnKeluar"
					data-dismiss="modal" id="btnDiskon">UPDATE</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		refreshObat(halamanObat, cariObat);
		
		$("#cariObat").keyup(function() {
			refreshObat(1, $('#cariObat').val());
		});
		
		setMaskingUang("#input-diskon");
		setMaskingUang("#input-pajak");
		setMaskingUang("#edit_jumlah");
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
</script>