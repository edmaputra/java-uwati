<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkan" value="/laporan/retur/dapatkan" />
<c:url var="daftar" value="/laporan/retur/daftar" />
<c:url var="batal" value="/laporan/retur/batal" />

<c:url var="pdfUrl" value="/laporan/penjualan/pdf" />
<c:url var="excel" value="/laporan/penjualan/excel" />

<div class="row">
	<div class="col-lg-12">
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

<div class="modal fade" id="batal-modal" tabindex="-1" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Batalkan Penjualan</h4>
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
					refresh(1,  tanggalAwal, tanggalAkhir, cari);
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

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, tanggalAwal, tanggalAkhir, cari) {
		var data = {			
			tanggalAwal : tanggalAwal,
			tanggalAkhir : tanggalAkhir,
			cari : cari
		};
		$.getAjax('${daftar}', data, function(result) {
			$('#tabelCoba').empty();
			$('#tabelCoba').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
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