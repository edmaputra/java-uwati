<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkanUrl" value="/resep/dapatkan" />
<c:url var="daftarUrl" value="/resep/daftar" />

<c:url var="jualUrl" value="/penjualan-obat/resep/jual" />
<c:url var="cetakResiUrl" value="/penjualan-obat/resep/cetak" />

<c:url var="terapiUrl" value="/obat/obat-tindakan" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<div class="content-panel">
				<div class="row">
					<div class="col-md-12">
						<form class="form-inline pull-right" id="formCari">
							<div class="form-group">
								<input type="text" id="stringTanggalCari"
									class="form-control datePicker" placeholder="Tanggal"
									style="width: 100px" />
							</div>
							<div class="form-group">
								<input type="text" id="stringCari" class="form-control"
									placeholder="Pencarian" style="width: 250px" />
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-primary" id="btnCari">Cari</button>
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-default" id="btnReset"
									onclick="refresh(1, '', '')">Reset</button>
							</div>
						</form>
					</div>
				</div>
				<br />

				<table class="table table-striped table-advance table-hover"
					id="tabel">
				</table>
				<div id="nav"></div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="resep-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Resep</h4>
			</div>
			<form class="form-horizontal style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-1 control-label">ID</label>
							<div class="col-sm-1">
								<input type="text" class="form-control" id="pasienId"
									readonly="readonly">
							</div>
							<label class="col-sm-1 control-label">Nama</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" readonly="readonly"
									id="nama">
							</div>
							<label class="col-sm-1 control-label" style="text-align: right;">Gender</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly"
									id="gender">
							</div>
							<label class="col-sm-1 control-label" style="text-align: right;">Usia</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" readonly="readonly"
									id="usia">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Asuransi Kesehatan</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="jaminan"
									readonly="readonly">
							</div>
							<label class="col-sm-1 control-label">Nomor</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="nomor-jaminan"
									readonly="readonly">
							</div>
						</div>

						<div class="row">
							<div class="col-md-7">
								<table class="table table-striped table-advance table-hover">
									<thead style="background-color: #68DFF0;">
										<tr>
											<th>Terapi</th>
											<th>Jumlah</th>
											<th>Biaya</th>
										</tr>
									</thead>
									<tbody id="tabel-terapi">
									</tbody>
								</table>
							</div>
							<div class="col-md-5">
								<div class="row">
									<div class="col-md-7">
										<div class="form-group">
											<label class="lb-sm">Total Pembelian</label> <input
												name="totalpembelian" type="text" id="totalPembelian"
												class="form-control input-lg" readonly="readonly" value="0">
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<label class="lb-sm">Diskon</label> <input name="diskon"
												type="text" id="diskon" class="form-control input-lg"
												value="0">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="lb-sm">Total Pembayaran</label> <input
										name="totalbayar" type="text" id="totalBayar"
										class="form-control input-lg" value="0" readonly="readonly">
								</div>
								<div class="row">
									<div class="col-md-7">
										<div class="form-group">
											<label class="lb-sm">Bayar</label> <input name="bayar"
												type="text" id="bayar" class="form-control input-lg"
												value="0">
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<label class="lb-sm">Kembali</label> <input name="kembali"
												type="text" id="kembali" class="form-control input-lg"
												value="0" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="form-group">
									<input type="hidden" name="id" class="form-control" id="ids" />
									<input type="hidden" name="dokterId" class="form-control"
										id="dokterId" /> <input type="submit"
										class="btn btn-primary col-sm-12" value="Bayar" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade" id="resep-modal-cetak" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Resep Terproses</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Ingin Mencetak Resi ?</p>
				</div>
			</div>
			<form action="${cetakResiUrl}"
				class="form-horizontal style-form formCetak" method="POST"
				target="_blank">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						id="keluarModalHapus" data-dismiss="modal">Tidak</button>
					<input type="hidden" name="id" class="form-control"
						id="penjualanId" /> <input type="submit" class="btn btn-danger"
						value="Cetak" />
				</div>
			</form>
		</div>
	</div>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	$(document).ready(function() {
				refresh(1, '', '');

				$('#btnCari').click(function() {
					refresh(1, $('#stringCari').val(), $('#stringTanggalCari').val());
				});

				$('#diskon').keyup(function() {
					$('#totalBayar').val(hitungGrandTotalBayar('#totalPembelian','#diskon'));
				});

				$('#bayar').keyup(function() {
					$('#kembali').val(hitungKembalian('#totalBayar', '#bayar'));
				});

				$(".formTambah").validate({
					rules : {
						totalpembelian : {
							required : true,
							min : 0
						},
						diskon : {
							required : true,
							min : 0
						},
						totalbayar : {
							required : true,
							min : 0
						},
						bayar : {
							required : true,
							min : 0,
							greaterEqualThan : '#totalBayar'
						},
						kembali : {
							required : true,
							min : 0
						}
					},
					messages : {
						totalpembelian : {
							required : "Total Pembelian Harus Diisi",
							min : "Isi Total Pembelian dengan Benar"
						},
						diskon : {
							required : "Diskon Harus Diisi",
							min : "Isi Diskon dengan Benar"
						},
						totalbayar : {
							required : "Total Pembayaran Harus Terisi",
							min : "Isi Total Pembelian dengan Benar"
						},
						bayar : {
							required : "Pembayaran Harus Diisi",
							min : "Isi Pembayaran dengan Benar",
							greaterEqualThan : 'Pembayaran Kurang'
						},
						kembali : {
							required : "Kembalian Harus Terisi",
							min : "Isi Kembalian Tidak Tepat"
						}
					},
					submitHandler : function(form) {
						var data = {};
						data = setResep(data);
						$.postJSON('${jualUrl}', data, function(result) {
							reset();
							refresh();
							$('#penjualanId').val(result.penjualanId);
							$('#resep-modal').modal('hide');
							$('#resep-modal-cetak').modal('show');
						}, function() {
							$('#gritter-tambah-gagal').click();
						});

					}
				});

// 				setMaskingUang("#totalPembelian");
// 				setMaskingUang("#diskon");
// 				setMaskingUang("#totalBayar");
// 				setMaskingUang("#bayar");
// 				setMaskingUang("#kembali");
			});

	function getData(ids, s) {
		reset();
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#pasienId').val(result.pasienId);
			$('#dokterId').val(result.dokterId);
			$('#nama').val(result.pasien);
			$('#gender').val(result.gender);
			$('#usia').val(result.usia);
			$('#jaminan').val(result.jaminan);
			$('#nomor-jaminan').val(result.nomorJaminan);
			$('#totalPembelian').val(result.totalPembelian);
			$('#totalBayar').val(result.totalPembelian);
			$('#tabel-terapi').append(result.tabelTerapi);
			$('#ids').val(ids);
		}, null);
	}

	// 	function setIdUntukHapus(ids) {
	// 		$('#hapusId').val(ids);
	// 	}

	function refresh(halaman, find, tanggal) {
		var data = {
			hal : halaman,
			cari : find,
			tgl : tanggal
		};
		$.getAjax('${daftarUrl}', data, function(result) {
			$('#tabel').empty();
			$('#tabel').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function setResep(data) {
		if ($('#ids').val() != null && $('#ids').val() != '') {
			data['id'] = $('#ids').val();
		}
		data['pasienId'] = $('#pasienId').val();
		data['dokterId'] = $('#dokterId').val();
		data['totalPembelian'] = $('#totalPembelian').val();
		data['diskon'] = $('#diskon').val();
		data['totalBayar'] = $('#totalBayar').val();
		data['bayar'] = $('#bayar').val();
		data['kembali'] = $('#kembali').val();
		return data;
	}

	function hitungGrandTotalBayar(t, d) {
		var total = parseFloat($(t).val().replace(/\./g, ''), 10);
		var diskon = parseFloat($(d).val().replace(/\./g, ''), 10);
		var grandTotal = total - diskon;
		return grandTotal;
	}

	function hitungKembalian(gt, b) {
		var grandTotal = $(gt).val().replace(/\./g, '');
		var bayar = $(b).val().replace(/\./g, '');
		var kembali = bayar - grandTotal;
		return kembali;
	}

	function reset() {
		$('#ids').val('');
		$('#pasienId').val('');
		$('#dokterId').val('');
		$('#nama').val(tanggalHariIni());
		$('#gender').val('');
		$('#usia').val('');
		$('#jaminan').val('');
		$('#nomor-jaminan').val('');
		$('#totalPembelian').val('0');
		$('#diskon').val('0');
		$('#totalBayar').val('0');
		$('#bayar').val('0');
		$('#kembali').val('0');
		$('#tabel-terapi').empty();
	}
</script>