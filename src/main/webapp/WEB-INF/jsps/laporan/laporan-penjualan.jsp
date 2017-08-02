<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkan" value="/laporan/penjualan/dapatkan" />
<c:url var="daftar" value="/laporan/penjualan/daftar" />

<div class="row">
	<div class="col-lg-9">
		<div class="content-panel">
			<div class="row kolom-pencarian">
				<div class="col-md-12">
					<form class="form-inline pull-right" id="formCari">					
						<div class="form-group">							
							<select class="form-control" name="tipe" id="tipe" style="width: 110px;">
								<option value="-1">SEMUA</option>
								<option value="0">Umum</option>
								<option value="2">Resep</option>
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
							<button type="button" class="btn btn-primary" id="btnCari" style="width: 80px">Filter</button>
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
		<h3>Detail</h3>
		<div class="desc">			
			<div class="thumb">
<!-- 				<span class="badge bg-theme"><h4>1</h4></span> -->			
			</div>
			<div class="details">
			<h5>Albothyl</h5>			
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="penjualan-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Laporan</h4>
			</div>
			<form class="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<input type="hidden" name="id" class="form-control" id="ids" /> <input
						type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="penjualan-modal-hapus" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Pasien</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Yakin Ingin Menghapus ?</p>
				</div>
			</div>
			<form class="form-horizontal style-form formHapus" method="post">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						id="keluarModalHapus" data-dismiss="modal">Tidak</button>
					<input type="hidden" name="id" class="form-control" id="hapusId" />
					<input type="submit" class="btn btn-danger" value="Hapus" />
				</div>
			</form>
		</div>
	</div>
</div>

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

// 		$('.btnTambah').click(function() {
// 			state = 0;
// 		});

// 		$('.btnEdit').click(function() {
// 			state = 1;
// 		});

		$('.btnKeluar').click(function() {
			reset();
		});

		$(".formHapus").submit(function(e) {
			e.preventDefault();
			var data = {};
			data['id'] = $('#hapusId').val();
			$.postJSON('${hapus}', data, function(result) {
				refresh();
				$('#keluarModalHapus').click();
				$('#gritter-hapus-sukses').click();
			}, function(e) {
				$('#gritter-hapus-sukses').click();
				$('#keluarModalHapus').click();
				refresh();
			});
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
		}, null);
	}

	function isiFieldFromDataResult(result) {
		$('#ids').val(result.id);
		$('#nama').val(result.nama);
		$('#identitas').val(result.identitas);
		$('#tanggalLahir').val(result.tanggalLahir);
		$('#tanggalLahir').val(dateFormat(result.tanggalLahir, 'dd-mm-yyyy'));
		$('#jenisKelamin').val(result.jenisKelamin);
		$('#agama').val(result.agama);
		$('#pekerjaan').val(result.pekerjaan);
		$('#alamat').val(result.alamat);
		$('#kontak').val(result.kontak);
		$('#jaminanKesehatan').val(result.jaminanKesehatan);
		$('#nomorJaminan').val(result.nomorJaminan);
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
</script>