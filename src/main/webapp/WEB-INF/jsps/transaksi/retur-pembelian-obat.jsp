<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="daftar" value="/retur/daftar" />
<c:url var="dapatkan" value="/retur/dapatkan" />
<c:url var="dapatkanObat" value="/retur/dapatkanobat" />
<c:url var="tambahObat" value="/retur/tambah-obat" />
<c:url var="listObatRetur" value="/retur/daftar-retur" />
<c:url var="batalkanretur" value="/retur/batal-retur" />
<c:url var="retur" value="/retur/retur" />

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="content-panel">
			<div class="row kolom-pencarian">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
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
				id="tabel">
			</table>
			<div id="nav"></div>
		</div>
	</div>
	<div class="modal container fade" id="modal-detail" tabindex="-1"
		style="display: none;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Detail Pembelian Obat</h4>
		</div>
		<form class="form style-form" method="post" id="retur-form">
			<div class="modal-body">
				<div class="row">
					<div class="form-group col-xs-5 col-sm-5 col-md-5 col-lg-5">
						<label class="lb-sm">Nomor Faktur</label> <input type="text"
							readonly="readonly" class="form-control" id="nomor_faktur" />
					</div>
					<div class="form-group col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<label class="lb-sm">Tanggal</label> <input type="text"
							readonly="readonly" class="form-control" id="tanggal" />
					</div>
					<div class="form-group col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<label class="lb-sm">Supplier/Distributor</label> <input
							type="text" readonly="readonly" class="form-control"
							id="supplier" /> <input type="hidden" class="form-control"
							id="id-pembelian" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
						<table class="table table-striped table-advance table-hover"
							id="tabel_detail">
						</table>
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<label class="lb-sm">Obat</label> <input type="text"
							readonly="readonly" class="form-control" id="retur-obat" />
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<label class="lb-sm">Harga Beli</label> <input type="text"
							class="form-control" readonly="readonly" id="retur-harga-beli"
							value="0" />
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<label class="lb-sm">Jumlah Retur</label> <input type="number"
							class="form-control" id="retur-jumlah" value="0" />
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<label class="lb-sm">Subtotal</label> <input type="text"
							readonly="readonly" class="form-control" id="retur-subtotal"
							value="0" />

					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<label class="lb-sm">-</label> <input type="hidden"
							id="retur-id-obat" />
						<button type="button" class="form-control btn btn-primary"
							id="retur-button">Tambah</button>
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<h4>Data Retur Obat</h4>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"
						style="text-align: right;">
						<label class="lb-sm">SubTotal</label>
					</div>
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<input id="retur-total" type="text" readonly="readonly"
							style="text-align: right;" value="0" class="form-control" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table
							class="table table-striped table-advance table-hover fullwidth"
							id="tabel">
							<thead>
								<tr>
									<th>Obat</th>
									<th>Jumlah</th>
									<th>Sub Total</th>
									<th></th>
								</tr>
							</thead>

							<tbody id="tabel-obat">
							</tbody>
						</table>
						<br />

					</div>
				</div>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-default" value="Batal"
					id="retur-batal" style="width: 15%;" /> <input type="submit"
					class="btn btn-primary" value="RETUR" id="retur" style="width: 30%" />
			</div>
		</form>
	</div>
</div>



<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	var state = 1;
	var tanggalAwal = tanggalHariIni();
	var tanggalAkhir = '';
	var cari = '';
	var randomId = Math.floor(Math.random() * 1000000);
	var isi = 0;

	$(document).ready(
			function() {
				$('#tanggalAwal').val(tanggalAwal);
				refresh(1, tanggalAwal, tanggalAkhir, cari);

				$('#btnCari').click(function() {
					tanggalAwal = $('#tanggalAwal').val();
					tanggalAkhir = $('#tanggalAkhir').val();
					cari = $('#cari').val();
					refresh(1, tanggalAwal, tanggalAkhir, cari);
				});

				hitungHargaTotalOnKeyUp("#retur-harga-beli", "#retur-jumlah",
						"#retur-subtotal");

				$("#retur-button").click(function() {
					returObat();
					resetFieldReturObat();
				});

				$("#retur-batal").click(function() {
					$('#modal-detail').modal('hide');
					resetAll();
				});

				$("#retur-jumlah").keyup(function(event) {
					if (event.keyCode == 13) {
						$('#retur-button').click();
					}
				});

				setMaskingUang("#retur-subtotal");

				$("#retur-form").validate({
					submitHandler : function(form) {
						if (isi == 0) {
							var p = "Harap Pilih Obat Dulu";
							$('#pesan').empty();
							$('#pesan').append(p);
							$('#pesan-modal').modal('show');
						} else {
							var data = {
								randomId : randomId,
								idPembelian : $("#id-pembelian").val()
							};
							$.postJSON('${retur}', data, function() {
								$('#retur-batal').click();
								$('#gritter-tambah-sukses').click();
								randomId = Math.floor(Math.random() * 1000000);
							}, function() {
								$('#gritter-tambah-gagal').click();
							});
						}
					}
				});
			});

	function hitungHargaTotalOnKeyUp(harga, jumlah, hargaTotal) {
		$(jumlah).keyup(function() {
			$(hargaTotal).val(hitungHargaTotalPerObat(harga, jumlah));
		});
	}

	function hitungHargaTotalPerObat(hrg, jum) {
		if ($(hrg).val() != '' && $(jum).val() != '') {
			var harga = parseFloat($(hrg).val().replace(/\./g, ''));
			var jumlah = parseInt($(jum).val().replace(/\./g, ''), 10);
			var hargaTotal = harga * jumlah;
			return hargaTotal;
		} else {
			return "0";
		}
	}

	function refresh(halaman, tanggalAwal, tanggalAkhir, cari) {
		var data = {
			hal : halaman,
			tanggalAwal : tanggalAwal,
			tanggalAkhir : tanggalAkhir,
			cari : cari
		};
		$.getAjax('${daftar}', data, function(result) {
			$('#tabel').empty();
			$('#tabel').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}

	function refreshDaftarObatRetur(id) {
		if (id != null) {
			var data = {
				randomId : id
			};
			$.getAjax('${listObatRetur}', data, function(result) {
				$('#tabel-obat').empty();
				$('#tabel-obat').append(result.tabelTerapi);
				$('#retur-total').val(result.value1);
			}, null);
		}
	}

	function getData(ids) {
		resetAll();
		randomId = Math.floor(Math.random() * 1000000);
		var data = {
			id : ids
		};
		$.getAjax('${dapatkan}', data, function(result) {
			isiFieldFromDataResult(result);
			$('#id-pembelian').val(ids);
		}, null);
	}

	function getObat(ids) {
		var data = {
			id : ids,
			idPembelian : $('#id-pembelian').val()
		};
		$.getAjax('${dapatkanObat}', data, function(result) {
			isiFieldReturObat(result);
			$('#retur-jumlah').focus();
			$('#retur-jumlah').select();
		}, null);
	}

	function returObat() {
		var data = {
			obat : $('#retur-obat').val(),
			hargaBeli : $('#retur-harga-beli').val(),
			jumlah : $('#retur-jumlah').val(),
			subTotal : $('#retur-subtotal').val(),
			nomorFaktur : $('#nomor_faktur').val(),
			distributor : $('#supplier').val(),
			idObat : $('#retur-id-obat').val(),
			idPembelian : $('#id-pembelian').val(),
			randomId : randomId
		};
		$.postJSON('${tambahObat}', data, function(result) {
			if (result.tipe == 0) {
				var p = result.info;
				$('#pesan').empty();
				$('#pesan').append(p);
				$('#pesan-modal').modal('show');
			} else {
				refreshDaftarObatRetur(randomId);
				resetFieldReturObat();
				isi = 1;
			}
		}, null);
	}

	function batalRetur(id) {
		var data = {
			idObat : id,
			randomId : randomId
		};
		$.postJSON('${batalkanretur}', data, function(result) {
			refreshDaftarObatRetur(randomId);
		}, null);
	}

	function isiFieldReturObat(result) {
		$('#retur-obat').val(result.obat);
		$('#retur-harga-beli').val(result.hargaBeli);
		$('#retur-jumlah').val(result.jumlah);
		$('#retur-subtotal').val(result.subTotal);
		$('#retur-id-obat').val(result.idObat);
	}

	function isiFieldFromDataResult(result) {
		$('#nomor_faktur').val(result.nomorFaktur);
		$('#supplier').val(result.distributor);
		$('#tanggal').val(result.tanggal);
		$('#tabel_detail').empty();
		$('#tabel_detail').append(result.details);
	}

	function resetFieldReturObat() {
		$('#retur-obat').val('');
		$('#retur-harga-beli').val('0');
		$('#retur-jumlah').val('0');
		$('#retur-subtotal').val('0');
		$('#retur-id-obat').val(null);
	}

	function resetFieldPembelian() {
		$('#ids').val(null);
		$('#nomor_faktur').val('');
		$('#supplier').val('');
		$('#tanggal').val('');
		$('#tabel_detail').empty();
		$('#id-pembelian').val('');
	}

	function resetDaftarObatRetur() {
		$('#retur-total').val('0');
		$('#tabel-obat').empty();
		isi = 0;
	}

	function resetAll() {
		resetFieldReturObat();
		resetFieldPembelian();
		resetDaftarObatRetur();
	}
</script>
