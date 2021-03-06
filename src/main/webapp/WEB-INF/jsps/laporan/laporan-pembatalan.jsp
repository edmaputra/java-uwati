<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkan" value="/laporan/pembatalan/dapatkan" />
<c:url var="daftar" value="/laporan/pembatalan/daftar" />

<c:url var="pdfUrl" value="/laporan/penjualan/pdf" />
<c:url var="excel" value="/laporan/penjualan/excel" />

<div class="row">
	<div class="col-lg-12">
		<div class="content-panel">
			<div class="row kolom-pencarian">
				<div class="col-md-12">
					<form class="form-inline pull-right" id="formCari">
						<div class="form-group">
							<select class="form-control" name="tipe" id="tipe"
								style="width: 165px;">
								<option value="0">Penjualan</option>
								<option value="1">Pembelian</option>
								<option value="2">Retur Pembelian</option>
							</select>
						</div>

						<div class="form-group">
							<input type="text" id="tanggalAwal"
								class="form-control datePicker" placeholder="Tanggal Awal"
								style="width: 110px" />
						</div>
						<div class="form-group">
							<input type="text" id="tanggalAkhir"
								class="form-control datePicker" placeholder="Tanggal Akhir"
								style="width: 110px" />
						</div>
						<div class="form-group">
							<input type="text" id="cari" class="form-control"
								placeholder="Pencarian" style="width: 150px" />
						</div>
						<div class="form-group">
							<a class="btn btn-primary" id="btnCari" style="width: 80px;">Filter</a>
						</div>
						<div class="form-group">
							<a class="btn btn-default" id="btnReset">Reset</a>							
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

<div class="modal container fade" id="penjualan-modal" tabindex="-1"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Laporan</h4>
	</div>
	<form class="form style-form" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="form-group col-md-5">
					<label class="lb-sm">Nomor Faktur</label> <input type="text"
						readonly="readonly" class="form-control" id="nomor_faktur" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Tanggal</label> <input type="text"
						readonly="readonly" class="form-control" id="tanggal" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-4">
					<label class="lb-sm">Kasir</label> <input type="text"
						readonly="readonly" class="form-control" id="kasir" />
				</div>
				<div class="form-group col-md-4">
					<label class="lb-sm">Pasien/Pelanggan</label> <input type="text"
						readonly="readonly" class="form-control" id="pelanggan" />
				</div>
				<div class="form-group col-md-4">
					<label class="lb-sm">Dokter</label> <input type="text"
						readonly="readonly" class="form-control" id="dokter" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-2">
					<label class="lb-sm">Pembelian</label> <input type="text"
						readonly="readonly" class="form-control input-angka"
						id="pembelian" />
				</div>
				<div class="form-group col-md-2">
					<label class="lb-sm">Diskon</label> <input type="text"
						readonly="readonly" class="form-control input-angka" id="diskon" />
				</div>
				<div class="form-group col-md-2">
					<label class="lb-sm">Pajak</label> <input type="text"
						readonly="readonly" class="form-control input-angka" id="pajak" />
				</div>
				<div class="form-group col-md-2">
					<label class="lb-sm">Total</label> <input type="text"
						readonly="readonly" class="form-control input-angka"
						id="pembelian_final" />
				</div>
				<div class="form-group col-md-2">
					<label class="lb-sm">Pembayaran</label> <input type="text"
						readonly="readonly" class="form-control input-angka" id="bayar" />
				</div>
				<div class="form-group col-md-2">
					<label class="lb-sm">Kembali</label> <input type="text"
						readonly="readonly" class="form-control input-angka" id="kembali" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped table-advance table-hover"
						id="tabel_detail" border="1"></table>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="hidden" name="id" class="form-control" id="ids" />
		</div>
	</form>
</div>

<div class="modal container fade" id="pembelian-modal" tabindex="-1"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Laporan</h4>
	</div>
	<form class="form style-form" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="form-group col-md-5">
					<label class="lb-sm">Nomor Faktur</label> <input type="text"
						readonly="readonly" class="form-control" id="nomor_faktur" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Tanggal</label> <input type="text"
						readonly="readonly" class="form-control" id="tanggal" />
				</div>
				<div class="form-group col-md-4">
					<label class="lb-sm">Supplier/Distributor</label> <input
						type="text" readonly="readonly" class="form-control" id="supplier" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-3">
					<label class="lb-sm">Pembelian</label> <input type="text"
						readonly="readonly" class="form-control input-angka"
						id="pembelian" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Diskon</label> <input type="text"
						readonly="readonly" class="form-control input-angka" id="diskon" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Pajak</label> <input type="text"
						readonly="readonly" class="form-control input-angka" id="pajak" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Total</label> <input type="text"
						readonly="readonly" class="form-control input-angka"
						id="pembelian_final" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped table-advance table-hover"
						id="tabel_detail" border="1"></table>
				</div>
			</div>
		</div>
		<div class="modal-footer"></div>
	</form>
</div>

<div class="modal container fade" id="penjualan-rekap-modal"
	tabindex="-1" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Laporan Rekap</h4>
	</div>
	<form class="form style-form" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="form-group col-md-5">
					<label class="lb-sm">Tanggal Awal</label> <input type="text"
						readonly="readonly" class="form-control" id="rekap_tanggal_awal" />
				</div>
				<div class="form-group col-md-5">
					<label class="lb-sm">Tanggal Akhir</label> <input type="text"
						readonly="readonly" class="form-control" id="rekap_tanggal_akhir" />
				</div>
				<!-- 							<div class="form-group col-md-2"> -->
				<!-- 								<label class="lb-sm">Total Penjualan Obat</label> -->
				<!-- 								<input type="text" readonly="readonly" class="form-control" id="rekap_total_obat"/> -->
				<!-- 							</div> -->
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped table-advance table-hover"
						id="tabel_detail_rekap" border="1"></table>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="hidden" name="id" class="form-control" id="ids" />
		</div>
	</form>
</div>



<form action="${cetakResi}" class="form-horizontal style-form" id="form_cetak" method="POST" target="_blank" style="visibility: hidden;">
		<div class="modal-footer">
			<input type="hidden" name="id" class="form-control" id="cetakId" />			
		</div>
</form>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	var state = 1;
	var tipe = -1;
	var tanggalAwal = tanggalHariIni();
	var tanggalAkhir = '';
	var cari = '';
	$(document).ready(function() {
		$('#tanggalAwal').val(tanggalAwal);
		refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);

		$('#btnCari').click(function() {
			tipe = $('#tipe').val();
			tanggalAwal = $('#tanggalAwal').val();
			tanggalAkhir = $('#tanggalAkhir').val();
			cari = $('#cari').val();
			refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);
		});

		$('#btnReset').click(function() {
			reset();
			refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);
		});

		$('#button_rekap').click(function() {
			getDataRekap();
		});
		
		$(".form-batal").validate({
			rules : {
				info_batal : {
					required : true
				}
			},
			messages : {
				info_batal : {
					required : "Harap Isi Alasan Pembatalan"
				}
			},
			submitHandler : function(form) {
				var data = {};
				data['id'] = $('#hapusId').val();
				data['info'] = $('#info_batal').val();				
				resetBatal();
				$.postJSON('${batal}', data, function(result) {					
					$('#keluarModalHapus').click();
					$('#gritter-hapus-sukses').click();
					reset();
					refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);
				}, function(e) {
					$('#keluarModalHapus').click();
					$('#gritter-hapus-sukses').click();
					reset();
					refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);
				});
			}
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

	function getDataRekap() {
		var data = {
			tanggalAwal : $('#tanggalAwal').val(),
			tanggalAkhir : $('#tanggalAkhir').val(),
			tipe : $('#tipe').val()
		};
		$.getAjax('${daftarRekap}', data, function(result) {
			$('#rekap_total_obat').val(result.jumlah);
			$('#rekap_tanggal_awal').val(data.tanggalAwal);
			$('#rekap_tanggal_akhir').val(data.tanggalAkhir);
			$('#tabel_detail_rekap').empty();
			$('#tabel_detail_rekap').append(result.details);
		}, null);
	}
	
	function setIdUntukCetakResi(ids) {
		$('#cetakId').val(ids);
		$('#form_cetak').submit();
	}

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, tipe, tanggalAwal, tanggalAkhir, cari) {
		var data = {
			hal : halaman,
			tipe : tipe,
			tanggalAwal : tanggalAwal,
			tanggalAkhir : tanggalAkhir,
			cari : cari
		};
		$.getAjax('${daftar}', data, function(result) {
			$('#tabelCoba').empty();
			$('#tabelCoba').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
			$('#rekapitulasi').empty();
			$('#rekapitulasi').append(result.grandTotal);
		}, null);
	}

	function isiFieldFromDataResult(result) {
		$('#ids').val(result.id);
		$('#nomor_faktur').val(result.nomorFaktur);
		$('#pelanggan').val(result.pelanggan);
		$('#kasir').val(result.pengguna);
		$('#dokter').val(result.dokter);
		$('#tanggal').val(dateFormat(result.waktuTransaksi, 'dd-mm-yyyy'));
		$('#pembelian').val(result.totalPembelian);
		$('#diskon').val(result.diskon);
		$('#pajak').val(result.pajak);
		$('#pembelian_final').val(result.grandTotal);
		$('#bayar').val(result.bayar);
		$('#kembali').val(result.kembali);
		$('#tabel_detail').empty();
		$('#tabel_detail').append(result.details);
	}

	function reset() {
		tipe = -1;
		tanggalAwal = tanggalHariIni();
		tanggalAkhir = '';
		cari = '';
		$('#tipe').val(tipe);
		$('#tanggalAwal').val(tanggalAwal);
		$('#tanggalAkhir').val(tanggalAkhir);
		$('#cari').val(cari);
	}
	
	function resetBatal(){
		$('#info_batal').val('');
	}
</script>