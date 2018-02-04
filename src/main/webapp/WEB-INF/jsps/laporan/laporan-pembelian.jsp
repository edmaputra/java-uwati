<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkan" value="/laporan/pembelian/dapatkan" />
<c:url var="daftar" value="/laporan/pembelian/daftar" />
<c:url var="batal" value="/laporan/pembelian/batal" />
<c:url var="daftarRekap" value="/laporan/pembelian/dapatkan-rekap" />

<div class="row">
	<div class="col-lg-9">
		<div class="content-panel">
			<div class="row kolom-pencarian">
				<div class="col-md-12">
					<form class="form-inline pull-right" id="formCari">

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

	<div class="col-lg-3 ds">
		<h3>Rekapitulasi</h3>
		<div class="desc">
			<div class="row">
				<div class="col-md-12 pull-right">
					<label>Pembelian</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h2 id="rekapitulasi" class="pull-right">0</h2>
				</div>
			</div>
		</div>
		<div class="desc">
			<div class="row">
				<div class="col-md-12">
					<button id="button_rekap" class="btn btn-primary"
						data-toggle="modal" data-target="#pembelian-rekap-modal"
						style="width: 100%;">
						<i class="fa fa-medkit" aria-hidden="true"> Obat</i>
					</button>
				</div>
			</div>
		</div>
		<div class="desc">
			<div class="row">
<!-- 				<div class="col-md-6"> -->
<!-- 					<a href="#" class="btn btn-primary" style="width: 100%;"><i -->
<!-- 						class="fa fa-file-excel-o fa-6" aria-hidden="true"> excel</i></a> -->
<!-- 				</div> -->
<!-- 				<div class="col-md-6"> -->
<!-- 					<a href="#" class="btn btn-danger" style="width: 100%;"><i -->
<!-- 						class="fa fa-file-pdf-o fa-6" aria-hidden="true"> pdf</i></a> -->
<!-- 				</div> -->
			</div>
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

<div class="modal container fade" id="pembelian-rekap-modal"
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

<div class="modal fade" id="batal-modal" tabindex="-1" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Batalkan Pembelian</h4>
	</div>
	
	<form class="form style-form form-batal" method="post">
	<div class="modal-body">
		<div class="form-group">
			<label class="lb-sm">Isi Alasan Pembatalan :</label> 
			<input type="text" class="form-control" id="info_batal" name="info_batal" />
		</div>
	</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="keluarModalHapus" data-dismiss="modal">Tidak</button>
			<input type="hidden" name="id" class="form-control" id="hapusId" />
			<input type="submit" class="btn btn-danger" value="Batal" />
		</div>
	</form>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	var state = 1;
	var tanggalAwal = tanggalHariIni();
	var tanggalAkhir = '';
	var cari = '';
	$(document).ready(function() {
		$('#tanggalAwal').val(tanggalAwal);
		refresh(1, tanggalAwal, tanggalAkhir, cari);

		$('#btnCari').click(function() {
			tanggalAwal = $('#tanggalAwal').val();
			tanggalAkhir = $('#tanggalAkhir').val();
			cari = $('#cari').val();
			refresh(1, tanggalAwal, tanggalAkhir, cari);
		});

		$('#btnReset').click(function() {
			reset();
			refresh(1, tanggalAwal, tanggalAkhir, cari);
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
					refresh(1, tanggalAwal, tanggalAkhir, cari);
				}, function(e) {
					$('#keluarModalHapus').click();
					$('#gritter-hapus-sukses').click();
					reset();
					refresh(1, tanggalAwal, tanggalAkhir, cari);
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
		};
		$.getAjax('${daftarRekap}', data, function(result) {
			$('#rekap_total_obat').val(result.jumlah);
			$('#rekap_tanggal_awal').val(data.tanggalAwal);
			$('#rekap_tanggal_akhir').val(data.tanggalAkhir);
			$('#tabel_detail_rekap').empty();
			$('#tabel_detail_rekap').append(result.details);
		}, null);
	}

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, tanggalAwal, tanggalAkhir, cari) {
		var data = {
			hal : halaman,
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
		$('#supplier').val(result.distributor);
		$('#tanggal').val(result.tanggal);
		$('#pembelian').val(result.totalPembelian);
		$('#diskon').val(result.diskon);
		$('#pajak').val(result.pajak);
		$('#pembelian_final').val(result.totalPembelianFinal);
		$('#tabel_detail').empty();
		$('#tabel_detail').append(result.details);
	}

	function reset() {
		tanggalAwal = tanggalHariIni();
		tanggalAkhir = '';
		cari = '';
		$('#tanggalAwal').val(tanggalAwal);
		$('#tanggalAkhir').val(tanggalAkhir);
		$('#cari').val(cari);
	}
	
	function resetBatal(){
		$('#info_batal').val('');
	}
</script>