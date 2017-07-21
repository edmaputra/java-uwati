<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/penjualan-obat/tambah" />
<c:url var="cariObatUrl" value="/obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="tambahKeListObatUrl" value="/penjualan-obat/tambahTemp" />
<c:url var="hapusObatUrl" value="/penjualan-obat/hapusTemp" />
<c:url var="daftarTempUrl" value="/penjualan-obat/daftarTemp" />
<c:url var="jualUrl" value="/penjualan-obat/jual" />
<c:url var="tersediaUrl" value="/penjualan-obat/tersedia" />
<c:url var="cetakUrl" value="/penjualan-obat/cetak" />
<c:url var="racikanListUrl" value="/racikan/list" />
<c:url var="racikanDetailUrl" value="/racikan/detail" />
<c:url var="tambahRacikanKeListJual" value="/penjualan-obat/racikanTemp" />
<c:url var="nomorFakturNotTerpakai" value="/nomor-faktur/not-terpakai" />

<c:url var="obatListUrl" value="/obat/obat-tindakan" />


<div class="row mt">
	<div class="col-md-12">
		<div class="form-panel">
			<form class="form formTambah" id="formObat" method="POST">
				<div class="row">
					<div class="col-md-2">
						<div class="form-group">
							<input name="tanggal" type="text" id="tanggal"
								class="form-control datePicker" placeholder="Tanggal Transaksi" value="${tanggalHariIni}"/>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<input name="pelanggan" type="text" id="pelanggan"
								class="form-control" placeholder="Pelanggan" />
						</div>
					</div>
					<div class="col-md-7">
						<input type="text" name="cariObat" class="form-control"
							id="cariObat" placeholder="Pencarian" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-5">
						<table class="table table-striped table-advance table-hover"
							id="tabel">
							<thead>
								<tr>
									<th>Obat</th>
									<th>Jumlah</th>
									<th>Sub Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="body"></tbody>
						</table>
					</div>
					<div class="col-md-7">
						<div id="list-obat"></div>
						<div class="btn-group btn-group-justified" id="navigasi-obat"></div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<button class="form-control btn btn-primary" id="btnBayar">B
								A Y A R</button>
						</div>
					</div>
				</div>
			</form>
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
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
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
										onclick="refreshRacikan(1,'')">Reset</button>
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<table class="table table-striped table-advance table-hover"
								id="tabelRacikan">
							</table>
							<div id="nav"></div>
						</div>
						<div class="col-md-4">
							<div class="form-panel">
								<h4 id="namaRacikan"></h4>
								<table class="table" id="racikanDetail">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-md-2">
						<div class="form-grup">
							<input type="text" class="form-control" id="hargaSatuanRacikan"
								readonly="readonly" placeholder="Harga Racikan">
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-grup">
							<input type="text" class="form-control" id="jumlahBeliRacikan"
								placeholder="Jumlah">
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-grup">
							<input type="text" class="form-control" id="totalRacikan"
								readonly="readonly" placeholder="Total">
						</div>
					</div>
					<div class="col-md-2 col-md-offset-1">
						<div class="form-group">
							<button type="button"
								class="form-control btn btn-default btnKeluar"
								data-dismiss="modal">Keluar</button>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<input type="submit" class="form-control btn btn-primary"
								value="Tambah" id="tambahRacikan" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="pesanModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Pesan</h4>
			</div>
			<div class="modal-body">
				<div id="pesan"></div>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="bayarModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form class="form formBayar" method="POST">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Bayar</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="lb-sm">Total Pembelian</label> <input name="total"
									type="text" id="total" class="form-control input-lg" value="0">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="lb-sm">Pajak</label> <input name="pajakBayar"
									type="text" id="pajakBayar" class="form-control input-lg"
									value="0">
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label class="lb-sm">%</label> <input name="persenPajakBayar"
									type="text" id="persenPajakBayar" class="form-control input-lg"
									value="0">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-md-offset-6">
							<div class="form-group">
								<label class="lb-sm">Diskon</label> <input name="diskonBayar"
									type="text" id="diskonBayar" class="form-control input-lg"
									value="0">
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label class="lb-sm">%</label> <input name="persenDiskonBayar"
									type="text" id="persenDiskonBayar"
									class="form-control input-lg" value="0">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="lb-sm">Grand Total</label> <input
									name="grandTotalBayar" type="text" id="grandTotalBayar"
									class="form-control input-lg" readonly="readonly" value="0" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="lb-sm">Bayar</label> <input name="bayar"
									type="text" id="bayar" class="form-control input-lg" value="0" />
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="lb-sm">Kembali</label> <input name="kembali"
									type="text" id="kembali" class="form-control input-lg"
									readonly="readonly" value="0">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<input type="submit" class="btn btn-primary" value="JUAL" id="jual" />
				</div>
			</div>
		</form>
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
	$(document).ready(function() {
		refreshObat(halamanObat, cariObat);		
		$("#cariObat").keyup(function() {
			refreshObat(1, $('#cariObat').val());
		});
	});

	function refreshObat(halaman, find) {
		var data = {
			hal : halaman,
			cari : find
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
</script>