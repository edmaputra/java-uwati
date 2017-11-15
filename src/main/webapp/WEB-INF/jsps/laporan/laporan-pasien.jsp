<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkan" value="/laporan/pasien/dapatkan" />
<c:url var="daftar" value="/laporan/pasien/daftar" />

<div class="row">
	<div class="col-lg-9">
		<div class="content-panel">
			<div class="row kolom-pencarian">
				<div class="col-md-12">
					<form class="form-inline pull-right" id="formCari">
						<div class="form-group">
							<select class="form-control" id="kategori_cari">
								<option value="-1"></option>
								<c:forEach items="${kategoris}" var="kategoris" varStatus="loop">
									<option value="${kategoris.id}"><c:out
											value="${kategoris.nama}" /></option>
								</c:forEach>
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

	<div class="col-lg-3 ds">
		<h3>Rekapitulasi</h3>
		<div class="desc">
			<div class="row">
				<div class="col-md-12 pull-right">
					<label>Jumlah Pasien</label>
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
				<div class="col-md-6">
					<a href="#" class="btn btn-primary" style="width: 100%;"><i class="fa fa-file-excel-o fa-6" aria-hidden="true"> excel</i></a>
				</div>
				<div class="col-md-6">					
					<a href="#" class="btn btn-danger" style="width: 100%;"><i class="fa fa-file-pdf-o fa-6" aria-hidden="true"> pdf</i></a>
				</div>
			</div>
		</div>		
	</div>	
</div>



<div class="modal fade" id="penjualan-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Laporan</h4>
			</div>
			<form class="form style-form" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="form-group col-md-5">
								<label class="lb-sm">Nomor Faktur</label>
								<input type="text" readonly="readonly" class="form-control" id="nomor_faktur"/>
							</div>
							<div class="form-group col-md-3">
								<label class="lb-sm">Tanggal</label>
								<input type="text" readonly="readonly" class="form-control" id="tanggal"/>
							</div>							
						</div>
						<div class="row">
							<div class="form-group col-md-4">
								<label class="lb-sm">Kasir</label>
								<input type="text" readonly="readonly" class="form-control" id="kasir"/>
							</div>
							<div class="form-group col-md-4">
								<label class="lb-sm">Pasien/Pelanggan</label>
								<input type="text" readonly="readonly" class="form-control" id="pelanggan"/>
							</div>							
							<div class="form-group col-md-4">
								<label class="lb-sm">Dokter</label>
								<input type="text" readonly="readonly" class="form-control" id="dokter"/>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-2">
								<label class="lb-sm">Pembelian</label>
								<input type="text" readonly="readonly" class="form-control input-angka" id="pembelian"/>
							</div>
							<div class="form-group col-md-2">
								<label class="lb-sm">Diskon</label>
								<input type="text" readonly="readonly" class="form-control input-angka" id="diskon"/>
							</div>
							<div class="form-group col-md-2">
								<label class="lb-sm">Pajak</label>
								<input type="text" readonly="readonly" class="form-control input-angka" id="pajak"/>
							</div>
							<div class="form-group col-md-2">
								<label class="lb-sm">Total</label>
								<input type="text" readonly="readonly" class="form-control input-angka" id="pembelian_final"/>
							</div>
							<div class="form-group col-md-2">
								<label class="lb-sm">Pembayaran</label>
								<input type="text" readonly="readonly" class="form-control input-angka" id="bayar"/>
							</div>
							<div class="form-group col-md-2">
								<label class="lb-sm">Kembali</label>
								<input type="text" readonly="readonly" class="form-control input-angka" id="kembali"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<table class="table table-striped table-advance table-hover" id="tabel_detail" border="1"></table>
							</div>
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
</script>