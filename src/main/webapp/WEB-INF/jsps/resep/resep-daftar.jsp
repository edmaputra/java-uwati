<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="dapatkanUrl" value="/resep/dapatkan" />
<c:url var="daftarUrl" value="/resep/daftar" />

<c:url var="jualUrl" value="/penjualan-obat/resep/jual" />
<c:url var="cetakResiUrl" value="/penjualan-obat/resep/cetak" />

<c:url var="terapiUrl" value="/obat/obat-tindakan" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
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

<div class="modal container fade" id="resep-modal" tabindex="-1"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Resep</h4>
	</div>
	<form class="form style-form formTambah" method="post">
		<div class="modal-body">
			<div class="row">
				<div class="form-group">
					<div class="col-sm-1">
						<label class="form-control" style="border: none;">ID:</label>
					</div>
					<div class="col-sm-1 col-lg-1">
						<input type="text" class="form-control" id="pasienId"
							readonly="readonly">
					</div>
					<div class="col-sm-1 col-lg-1">
						<label class="form-control" style="border: none;">Nama:</label>
					</div>
					<div class="col-sm-3 col-lg-3">
						<input type="text" class="form-control" readonly="readonly"
							id="nama">
					</div>
					<div class="col-sm-1 col-lg-1">
						<label class="form-control" style="border: none;">Gender:</label>
					</div>
					<div class="col-sm-2 col-lg-2">
						<input type="text" class="form-control" readonly="readonly"
							id="gender">
					</div>
					<div class="col-sm-1 col-lg-1">
						<label class="form-control" style="border: none;">Usia:</label>
					</div>
					<div class="col-sm-2 col-lg-2">
						<input type="text" class="form-control" readonly="readonly"
							id="usia">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-lg-2">
						<label class="form-control" style="border: none;">Jaminan:</label>
					</div>
					<div class="col-sm-4 col-lg-4">
						<input type="text" class="form-control" readonly="readonly"
							id="jaminan">
					</div>
					<div class="col-sm-1 col-lg-1">
						<label class="form-control" style="border: none;">Nomor:</label>
					</div>
					<div class="col-sm-5 col-lg-5">
						<input type="text" class="form-control" readonly="readonly"
							id="nomor-jaminan">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-lg-2">
						<label class="form-control" style="border: none;">Tanggal:</label>
					</div>
					<div class="col-sm-2 col-lg-2">
						<input type="text" class="form-control datePicker" name="tanggal"
							id="tanggal" autocomplete="off">
					</div>
				</div>
			</div>
			<br />

			<div class="row">
				<div class="col-md-6">
					<table class="table table-striped table-advance table-hover">
						<thead style="background-color: #68DFF0;">
							<tr>
								<th>Terapi</th>
								<th>Jumlah</th>
								<th>Biaya</th>
								<th>Info</th>
							</tr>
						</thead>
						<tbody id="tabel-terapi">
						</tbody>
					</table>
				</div>
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label class="lb-sm">Total Obat</label> <input name="total_obat"
									type="text" id="total_obat" class="form-control input-angka"
									readonly="readonly" value="0">
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="lb-sm">Biaya-biaya</label> <input name="biaya"
								type="text" id="biaya" class="form-control input-angka"
								value="0" readonly="readonly">

						</div>
						<div class="col-md-4 form-group">
							<label class="lb-sm">PPN Obat(10%)</label> <input name="pajak"
								type="text" id="pajak" class="form-control input-angka"
								value="0">
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label class="lb-sm">Total (Obat + Biaya)</label> <input
									name="total_obat_biaya" type="text" id="total_obat_biaya"
									class="form-control input-angka" value="0" autocomplete="off"
									readonly="readonly">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="lb-sm">Total (Setelah Pajak)</label> <input
									name="total_pajak" type="text" id="total_pajak"
									class="form-control input-angka" value="0" autocomplete="off"
									readonly="readonly">
							</div>
						</div>
						<div class="col-md-4 form-group">
							<label class="lb-sm">Diskon</label> <input name="diskon"
								type="text" id="diskon" class="form-control input-angka"
								value="0" autocomplete="off">
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 col-md-offset-4 form-group">
							<label class="lb-sm">Total Pembayaran (Setelah Diskon)</label> <input
								name="total_bayar" type="text" id="total_bayar"
								class="form-control input-angka input-lg" value="0"
								readonly="readonly">
						</div>
					</div>

					<div class="row">
						<div class="col-md-7">
							<div class="form-group">
								<label class="lb-sm">Bayar</label> <input name="bayar"
									type="number" id="bayar"
									class="form-control input-lg input-angka" value="0"
									autocomplete="off">
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="lb-sm">Kembali</label> <input name="kembali"
									type="text" id="kembali"
									class="form-control input-lg input-angka" value="0"
									readonly="readonly" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="hidden" name="id" class="form-control" id="ids" /> <input
							type="hidden" name="dokterId" class="form-control" id="dokterId" />
						<input type="submit" class="btn btn-primary col-sm-12"
							value="Bayar" />
					</div>
				</div>
			</div>
		</div>

	</form>
</div>

<div class="modal fade" id="resep-modal-cetak" tabindex="-1"
	style="display: none;">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Resep Terproses</h4>
	</div>

	<div class="modal-body" style="text-align: center;">
		<p>Apakah Anda Ingin Mencetak Resi ?</p>
	</div>

	<form action="${cetakResiUrl}"
		class="form-horizontal style-form formCetak" method="POST"
		target="_blank">
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				id="keluarModalHapus" data-dismiss="modal">Tidak</button>
			<input type="hidden" name="id" class="form-control" id="penjualanId" />
			<input type="submit" class="btn btn-danger" value="Cetak"
				id="btnCetak" />
		</div>
	</form>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	$(document).ready(
			function() {
				refresh(1, '', '');

				$('#btnCari').click(
						function() {
							refresh(1, $('#stringCari').val(), $(
									'#stringTanggalCari').val());
						});

				$('#pajak').keyup(
						function() {
							$('#total_pajak').val(
									hitungTotalAfterPajak('#total_obat_biaya',
											'#pajak'));
							$('#total_bayar').val(
									hitungTotalPembayaran('#total_pajak',
											'#diskon'));
						});

				$('#diskon').keyup(
						function() {
							$('#total_bayar').val(
									hitungTotalPembayaran('#total_pajak',
											'#diskon'));
						});

				$('#bayar').keyup(
						function() {
							$('#kembali').val(
									hitungKembalian('#total_bayar', '#bayar'));
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
						tanggal : {
							required : true,
						},
						pajak : {
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
							greaterEqualThan : '#total_bayar'
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
						pajak : {
							required : "Pajak Harus Diisi",
							min : "Pajak Diskon dengan Benar"
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

				setMaskingUang("#pajak");
				setMaskingUang("#diskon");
				// 				setMaskingUang("#bayar");
				setMaskingUang("#kembali");
			});

	function getData(ids, s) {
		reset();
		var data = {
			id : ids
		};
		$
				.getAjax(
						'${dapatkanUrl}',
						data,
						function(result) {
							if (result.isMasukResepList == false) {
								$('#pesan').empty();
								$('#pesan')
										.append(
												"Resep Tidak Dapat Diproses karena Revisi, Hubungi Dokter Bersangkutan");
								$('#pesan-modal').modal('show');
								$('#resep-modal').modal('hide');
								reset();
							} else {
								$('#pasienId').val(result.pasienId);
								$('#dokterId').val(result.dokterId);
								$('#tanggal').val(result.tanggal);
								$('#nama').val(result.pasien);
								$('#gender').val(result.gender);
								$('#usia').val(result.umur);
								$('#jaminan').val(result.jaminan);
								$('#nomor-jaminan').val(result.nomorJaminan);

								$('#total_obat').val(result.totalObat);
								$('#biaya').val(result.totalBiaya);
								$('#pajak').val(result.pajak);
								$('#total_obat_biaya').val(
										result.totalObatDanBiaya);
								$('#total_pajak').val(result.totalAfterPajak);
								$('#total_bayar').val(result.totalPembelian);
								$('#tabel-terapi').append(result.tabelTerapi);
								$('#ids').val(ids);
							}
						}, null);
	}

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
		data['tanggal'] = $('#tanggal').val();
		data['pasienId'] = $('#pasienId').val();
		data['dokterId'] = $('#dokterId').val();
		data['totalPembelian'] = $('#total_obat_biaya').val();
		data['grandTotal'] = $('#total_bayar').val();
		data['diskon'] = $('#diskon').val();
		data['pajak'] = $('#pajak').val();
		data['bayar'] = $('#bayar').val();
		data['kembali'] = $('#kembali').val();
		return data;
	}

	function hitungTotalAfterPajak(obat_biaya, pajak) {
		var p = parseFloat($(pajak).val().replace(/\./g, ''), 10);
		var o = parseFloat($(obat_biaya).val().replace(/\./g, ''), 10);
		var total = o + p;
		return total;
	}

	function hitungTotalPembayaran(totalAfterPajak, diskon) {
		var t = parseFloat($(totalAfterPajak).val().replace(/\./g, ''), 10);
		var d = parseFloat($(diskon).val().replace(/\./g, ''), 10);
		var grandTotal = t - d;
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
		$('#tanggal').val('');
		$('#jaminan').val('');
		$('#nomor-jaminan').val('');
		$('#totalPembelian').val('0');
		$('#diskon').val('0');
		$('#totalBayar').val('0');
		$('#bayar').val('0');
		$('#kembali').val('0');
		$('#pajak').val('0');
		$('#tabel-terapi').empty();
	}
</script>