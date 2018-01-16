<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="daftar" value="/utang-piutang/daftar" />
<c:url var="dapatkan" value="/utang-piutang/dapatkan" />
<c:url var="bayar" value="/utang-piutang/bayar" />

<div class="row">
	<div class="col-lg-12">
		<div class="content-panel">
			<div class="row kolom-pencarian">
				<div class="col-md-12">
					<form class="form-inline pull-right" id="formCari">
						<div class="form-group">
							<select class="form-control" name="tipe" id="tipe"
								style="width: 110px;">
								<option value="0">Utang</option>
								<option value="1">Piutang</option>
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
							<button type="button" class="btn btn-primary" id="btnCari"
								style="width: 80px">Filter</button>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-default" id="btnReset">Reset</button>
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

<div class="modal container fade" id="utang-piutang-modal" tabindex="-1"
	style="display: none;">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Pembayaran</h4>
	</div>
	<form class="form style-form" method="post" id="form-bayar">
		<div class="modal-body">
			<div class="row">
				<div class="form-group col-md-4">
					<label class="lb-sm">Nomor Faktur</label> <input type="text"
						readonly="readonly" class="form-control" id="nomor-faktur" />
				</div>
				<div class="form-group col-md-2">
					<label class="lb-sm">Tanggal</label> <input type="text"
						readonly="readonly" class="form-control" id="tanggal" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Total Belanja</label> <input type="text"
						readonly="readonly" class="form-control input-angka"
						id="total-pembelian" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Utang/Piutang</label> <input type="text"
						readonly="readonly" class="form-control input-angka"
						id="total-bayar" />
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-md-8">
					<table class="table table-striped table-advance table-hover"
						id="tabel-detail"></table>
				</div>
				<div class="col-md-4">
					<table class="table table-striped table-advance table-hover"
						id="tabel-bayar" border="1">
					</table>
				</div>
			</div>
			<hr />
			<div class="row" style="background-color: #68DFF0; margin: 2px;">
				<div class="form-group col-md-3 col-md-offset-3">
					<label class="lb-sm">Total Terbayar</label> <input type="text"
						readonly="readonly" class="form-control input-angka"
						id="total-terbayar" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">Jumlah Pembayaran</label> <input type="text"
						class="form-control input-angka" id="jumlah-pembayaran"
						name="jumlah_pembayaran" value="0" autocomplete="off" />
				</div>
				<div class="form-group col-md-3">
					<label class="lb-sm">-</label> <input type="submit"
						class="btn btn-primary form-control" value="BAYAR"> <input
						type="hidden" class="form-control" id="ids" /> <input
						type="hidden" class="form-control" id="tipe-transaksi" />

				</div>
			</div>
		</div>
		<div class="modal-footer"></div>
	</form>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>	
	var tipe = 0;
	var tanggalAwal = '';
	var tanggalAkhir = '';
	var cari = '';
	$(document).ready(function() {
		$('#tanggalAwal').val(tanggalAwal);
		refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);

		$('#btnCari').click(function() {
			tanggalAwal = $('#tanggalAwal').val();
			tanggalAkhir = $('#tanggalAkhir').val();
			cari = $('#cari').val();
			tipe = $('#tipe').val();
			refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);
		});

		$('#btnReset').click(function() {
			reset();
			refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);
		});
		
		$("#form-bayar").validate({
			rules : {
			jumlah_pembayaran : {
				required : true,
				number : true,
// 				lessEqualThan : '#totalPembelianFinal'
			},
		},
		messages : {
			jumlah_pembayaran : {
				required : "Harap Isi Jumlah Pembayaran",
				number : "Harap Isi dengan Angka",
// 				lessEqualThan : 'Jumlah Bayar Lebih Besar'
			},
		},
			submitHandler : function(form) {
				var jumlah_pembayaran = parseFloat($("#jumlah-pembayaran").val().replace(/\./g, ''));
				var total_terbayar = parseFloat($("#total-terbayar").val().replace(/\./g, ''));
				var grand_total = parseFloat($("#total-pembelian").val().replace(/\./g, ''));
				var b = jumlah_pembayaran + total_terbayar;
				if (b > grand_total){
					var p = "Total Jumlah Pembayaran Melebihi Harga Pembelian";
					$('#pesan').empty();
					$('#pesan').append(p);
					$('#pesan-modal').modal('show');
				} else {
					var data = {};
					data = setData(data);
					$.postJSON('${bayar}', data, function() {
						$('#utang-piutang-modal').modal('hide');
						reset();
						refresh(1, tipe, tanggalAwal, tanggalAkhir, cari);
						$('#gritter-tambah-sukses').click();
					}, function() {
						$('#gritter-tambah-gagal').click();
					});
				}				
			}
		});
	});

	function refresh(halaman, tipe, tanggalAwal, tanggalAkhir, cari) {
		var data = {
			hal : halaman,
			tipe : tipe,
			tanggalAwal : tanggalAwal,
			tanggalAkhir : tanggalAkhir,
			cari : cari
		};
		$.getAjax('${daftar}', data, function(result) {
			tipe = data.tipe;
			$('#tabelCoba').empty();
			$('#tabelCoba').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);			
		}, null);
	}
	
	function getData(ids) {
		$('#jumlah-pembayaran').val('0');
		var data = {
			id : ids,
			tipe : tipe
		};
		$.getAjax('${dapatkan}', data, function(result) {
			$('#ids').val(data.id);
			isiFieldFromDataResult(result);			
		}, null);
	}

	function isiFieldFromDataResult(result) {
		$('#nomor-faktur').val(result.nomorFaktur);
		$('#tanggal').val(result.tanggal);
		$('#tipe-transaksi').val(result.tipe);
		$('#total-pembelian').val(result.totalPembelianFinal);
		$('#total-terbayar').val(result.subTotal);
		$('#total-bayar').val(result.bayar);
		$('#tabel-detail').empty();
		$('#tabel-detail').append(result.details);
		$('#tabel-bayar').empty();
		$('#tabel-bayar').append(result.bayarDetails);
	}
	
	function setData(data) {
		data['id'] = $('#ids').val();
		data['bayar'] = $('#jumlah-pembayaran').val();
		data['tipe'] = $('#tipe-transaksi').val();
		return data;
	}
	
	function reset() {
		tanggalAwal = $('#tanggalAwal').val();
		tanggalAkhir = $('#tanggalAkhir').val();
		cari = $('#cari').val();
		tipe = $('#tipe').val();
		$('#jumlah-pembayaran').val('0');
	}
</script>