<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkan" value="/obat-diagnosa/dapatkan" />
<c:url var="daftar" value="/obat-diagnosa/daftar" />

<c:url var="daftarRekap" value="/obat-diagnosa/daftar-rekap" />
<c:url var="pdfUrl" value="/obat-diagnosa/pdf" />

<div class="row">
	<div class="col-lg-10">
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
			<div class="row">
				<div class="col-md-6">
					<div style="text-align: center;"><h4>Obat</h4></div>
					<table class="table table-striped table-advance table-hover"
						id="tabel-obat">
					</table>					
				</div>
				<div class="col-md-6">
					<div style="text-align: center;"><h4>Diagnosa</h4></div>					
					<table class="table table-striped table-advance table-hover"
						id="tabel-diagnosa">
					</table>
				</div>
			</div>		
		</div>
	</div>

	<div class="col-lg-2 ds">
		<h3>Rekapitulasi</h3>
		<div class="desc">
			<div class="row">
				<div class="col-md-12">
					<button id="button_rekap" class="btn btn-primary"
						data-toggle="modal" data-target="#penjualan-rekap-modal"
						style="width: 100%;">
						<i class="fa fa-medkit" aria-hidden="true"> Obat &amp; Diagnosa</i>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal container fade" id="penjualan-rekap-modal"
	tabindex="-1" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Rekapitulasi</h4>
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
<!-- 				<div class="form-group col-md-2"> -->
<!-- 					<label class="lb-sm">-</label> -->
<!-- 					<button id="button_cetak_rekap" class="btn btn-primary form-control">-->
<!-- 						<i class="fa fa-print" aria-hidden="true"> Cetak</i> -->
<!-- 					</button> -->
<!-- 				</div>-->
			</div>
			<div class="row">
				<div class="col-md-6">
					<div style="text-align: center;"><h5>Obat</h5></div>	
					<table class="table table-striped table-advance table-hover"
						id="tabel-detail-obat" border="1"></table>
				</div>
				<div class="col-md-6">
					<div style="text-align: center;"><h5>Diagnosa</h5></div>
					<table class="table table-striped table-advance table-hover"
						id="tabel-detail-diagnosa" border="1"></table>
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
	var tanggalAwal = '';
	var tanggalAkhir = '';
	$(document).ready(function() {		
		refresh(tanggalAwal, tanggalAkhir);

		$('#btnCari').click(function() {
			tanggalAwal = $('#tanggalAwal').val();
			tanggalAkhir = $('#tanggalAkhir').val();			
			refresh(tanggalAwal, tanggalAkhir);
		});

		$('#btnReset').click(function() {
			reset();
			refresh(tanggalAwal, tanggalAkhir);
		});

		$('#button_rekap').click(function() {
			getDataRekap();
		});
	});

	function getDataRekap() {
		$('#rekap_tanggal_awal').val(tanggalAwal);
		$('#rekap_tanggal_akhir').val(tanggalAkhir);
		var data = {};
		$.getAjax('${daftarRekap}', data, function(result) {
			$('#tabel-detail-obat').empty();
			$('#tabel-detail-obat').append(result.tabelObatDetail);
			$('#tabel-detail-diagnosa').empty();
			$('#tabel-detail-diagnosa').append(result.tabelDiagnosaDetail);
		}, null);
	}
	
	function setIdUntukCetakResi(ids) {
		$('#cetakId').val(ids);
		$('#form_cetak').submit();
	}

	function refresh(tanggalAwal, tanggalAkhir) {
		var data = {		
			tanggalAwal : tanggalAwal,
			tanggalAkhir : tanggalAkhir			
		};
		$.getAjax('${daftar}', data, function(result) {
			$('#tabel-obat').empty();
			$('#tabel-obat').append(result.tabelObat);
			$('#tabel-diagnosa').empty();
			$('#tabel-diagnosa').append(result.tabelDiagnosa);
		}, null);
	}

	function reset() {
		tanggalAwal = tanggalHariIni();
		tanggalAkhir = '';				
		$('#tanggalAwal').val(tanggalAwal);
		$('#tanggalAkhir').val(tanggalAkhir);		
	}
</script>