<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/penjualan-obat/tambah" />
<c:url var="cariObatUrl" value="/obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="tambahKeListObatUrl" value="/penjualan-obat/tambahTemp" />
<c:url var="hapusObatUrl" value="/penjualan-obat/hapusTemp" />
<c:url var="daftarTempUrl" value="/penjualan-obat/daftarTemp" />
<c:url var="jualUrl" value="/penjualan-obat/jual" />
<c:url var="tersediaUrl" value="/penjualan-obat/tersedia" />
<c:url var="cetakUrl" value="/penjualan-obat/cetak" />
<c:url var="racikanListUrl" value="/racikan/list" />
<c:url var="racikanDetailUrl" value="/racikan/detail" />
<c:url var="tambahRacikanKeListJual" value="/penjualan-obat/racikanTemp" />
<c:url var="nomorFakturNotTerpakai" value="/nomor-faktur/not-terpakai" />


<div class="row mt">
	<div class="col-md-12">
		<div class="form-panel">
			<form class="form formTambah" id="formObat" method="POST">
				<div class="row">
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Nomor Faktur</label> <input
								name="nomorFaktur" type="text" id="nomorFaktur"
								class="form-control" readonly="readonly" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Tanggal Beli</label> <input name="tanggal"
								type="text" id="tanggal" class="form-control datePicker"
								value="${tanggalHariIni}" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Jenis</label> <select class="form-control"
								name="jenis" id="jenis">
								<option value="default"></option>
								<option value="0">Umum</option>
								<option value="1">Dokter</option>
								<option value="2">Resep</option>
							</select>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Pelanggan/Pasien</label> <input
								name="pelanggan" type="text" id="pelanggan" class="form-control"
								readonly="readonly" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Dokter</label> <input name="dokter"
								type="text" id="dokter" class="form-control" readonly="readonly" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="lb-sm">Obat</label> <input name="obat" type="text"
								id="obat" class="form-control" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Harga Jual</label> <input name="hargaJual"
								type="text" id="hargaJual" class="form-control" value="0"/>
						</div>
					</div>

					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Diskon</label> 
							<input name="diskon" type="text" id="diskon" class="form-control" value="0"/>
						</div>
					</div>

					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">%</label> <input name="persenDiskon"
								type="text" id="persenDiskon" class="form-control" value="0"/>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">Stok</label> <input name="stok" type="text"
								id="stok" class="form-control" readonly="readonly" />
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">Jumlah</label> <input name="jumlah"
								type="text" id="jumlah" class="form-control" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Satuan</label> <input name="satuan"
								type="text" id="satuan" class="form-control" readonly="readonly" />
						</div>
					</div>

					<div class="col-md-2 col-md-offset-2">
						<div class="form-group">
							<label class="lb-sm">Pajak</label> <input name="pajak"
								type="text" id="pajak" class="form-control" value="0"/>
						</div>
					</div>
					<div class="col-md-1">
						<div class="form-group">
							<label class="lb-sm">%</label> <input name="persenPajak"
								type="text" id="persenPajak" class="form-control" value="0"/>
						</div>
					</div>

					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Subtotal</label> <input name="subtotal"
								type="text" id="subTotal" class="form-control"
								readonly="readonly" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Racikan</label>
							<button class="form-control btn btn-default" id="racikan"
								data-toggle="modal" data-target="#tambahRacikanModal">Tambah
								Racikan</button>
						</div>
					</div>
					<div class="col-md-4 col-md-offset-5">
						<div class="form-group">
							<label class="lb-sm">Tambah Obat</label> <input type="submit"
								class="form-control btn btn-primary" id="tambah"
								value="Tambahkan">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<table class="table table-striped table-advance table-hover"
							id="tabel">
							<thead>
								<tr>
									<th>Obat</th>
									<th>Jumlah</th>
									<th>Harga Satuan</th>
									<th>Pajak</th>
									<th>Diskon</th>
									<th>Sub Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="body"></tbody>
						</table>
					</div>
					<div class="col-md-4">
						<div class="form-panel">
							<h1 id="grandTotal" class="text-right">0</h1>
						</div>
					</div>
				</div>

			</form>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<button class="form-control btn btn-primary" id="btnBayar">B A Y A R</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="tambahRacikanModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Tambah Racikan</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-inline pull-right" id="formCari">
								<div class="form-group">
									<input type="text" id="stringCari" class="form-control"
										placeholder="Pencarian" style="width: 250px" />
								</div>
								<div class="form-group">
									<button type="button" class="btn btn-primary" id="btnCari">Cari</button>
								</div>
								<div class="form-group">
									<button type="button" class="btn btn-default" id="btnReset"
										onclick="refreshRacikan(1,'')">Reset</button>
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<table class="table table-striped table-advance table-hover"
								id="tabelRacikan">
							</table>
							<div id="nav"></div>
						</div>
						<div class="col-md-4">
							<div class="form-panel">
								<h4 id="namaRacikan"></h4>
								<table class="table" id="racikanDetail">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-md-2">
						<div class="form-grup">
							<input type="text" class="form-control" id="hargaSatuanRacikan"
								readonly="readonly" placeholder="Harga Racikan">
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-grup">
							<input type="text" class="form-control" id="jumlahBeliRacikan"
								placeholder="Jumlah">
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-grup">
							<input type="text" class="form-control" id="totalRacikan"
								readonly="readonly" placeholder="Total">
						</div>
					</div>
					<div class="col-md-2 col-md-offset-1">
						<div class="form-group">
							<button type="button"
								class="form-control btn btn-default btnKeluar"
								data-dismiss="modal">Keluar</button>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<input type="submit" class="form-control btn btn-primary"
								value="Tambah" id="tambahRacikan" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="pesanModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Pesan</h4>
			</div>
			<div class="modal-body">
				<div id="pesan"></div>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="bayarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	<form class="form formBayar" method="POST">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Bayar</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="lb-sm">Total Pembelian</label> 
							<input name="total" type="text" id="total" class="form-control input-lg" value="0">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="lb-sm">Pajak</label> 
							<input name="pajakBayar" type="text" id="pajakBayar" class="form-control input-lg" value="0">
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">%</label> 
							<input name="persenPajakBayar" type="text" id="persenPajakBayar" class="form-control input-lg" value="0">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-md-offset-6">
						<div class="form-group">
							<label class="lb-sm">Diskon</label> 
							<input name="diskonBayar" type="text" id="diskonBayar" class="form-control input-lg" value="0">
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">%</label> 
							<input name="persenDiskonBayar" type="text" id="persenDiskonBayar" class="form-control input-lg" value="0">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="lb-sm">Grand Total</label> 
							<input name="grandTotalBayar" type="text" id="grandTotalBayar" class="form-control input-lg" readonly="readonly" value="0"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-7">
						<div class="form-group">
							<label class="lb-sm">Bayar</label> 
							<input name="bayar" type="text" id="bayar" class="form-control input-lg" value="0"/>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="lb-sm">Kembali</label> 
							<input name="kembali" type="text" id="kembali" class="form-control input-lg" readonly="readonly" value="0">
						</div>
					</div>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default btnKeluar" data-dismiss="modal">Keluar</button>
				<input type="submit" class="btn btn-primary" value="JUAL" id="jual"/>
			</div>
		</div>
		</form>
	</div>
</div>


<button class="btn btn-primary btnHide" data-toggle="modal"
	data-target="#pesanModal" id="pesanButton">-</button>

<form action="${cetakUrl}" method="POST">	
	<button class="btn btn-primary btnHide" id="cetakButton">-</button>
</form>

<script>
	var nmrFaktur;
	var tgl;
	
	$(document).ready(function() {
		refreshRacikan(1, '');
		$('#jenis').focus();
		$('#jenis').on('change', function(e) {			
			var optionSelected = $("option:selected", this);
			var valueSelected = this.value;
			if (valueSelected == 0) {				
				$('#pelanggan').val('');
				document.getElementById('pelanggan').readOnly = true;
				$('#dokter').val('');
				document.getElementById('dokter').readOnly = true;				
			} else if (valueSelected == 1) {
				document.getElementById('pelanggan').readOnly = false;
				document.getElementById('dokter').readOnly = false;
			} else if (valueSelected == 2) {
				document.getElementById('pelanggan').readOnly = false;
				$('#dokter').val('');
				document.getElementById('dokter').readOnly = true;
			} else {
				$('#pelanggan').val('');
				document.getElementById('pelanggan').readOnly = true;
				$('#dokter').val('');
				document.getElementById('dokter').readOnly = true;
			}
		});
		
		$('#btnCari').click(function() {
			refreshRacikan(1, $('#stringCari').val());
		});
		
		$('#obat').blur(function() {
			var val = $('#obat').val();
			isiDataObat(val);
		});
		
		$('#jumlah').blur(function() {
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak', '#diskon', '#jumlah'));			
		});
		
		$('#jumlah').keyup(function() {
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak', '#diskon', '#jumlah'));			
		});
		
		$('#hargaJual').blur(function() {
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak','#diskon', '#jumlah'));
		});
		
		$('#hargaJual').keyup(function() {
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak','#diskon', '#jumlah'));
		});
		
		$('#pajak').blur(function() {
			$("#persenPajak").val(hitungPersen('#hargaJual', '#jumlah', '#pajak'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak','#diskon', '#jumlah'));
		});
		
		$('#pajak').keyup(function() {
			$("#persenPajak").val(hitungPersen('#hargaJual', '#jumlah', '#pajak'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak','#diskon', '#jumlah'));
		});
		
		$('#persenPajak').blur(function() {
			$("#pajak").val(hitungFromPersen('#hargaJual', '#jumlah', '#persenPajak'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak', '#diskon', '#jumlah'));
		});
		
		$('#persenPajak').keyup(function() {
			$("#pajak").val(hitungFromPersen('#hargaJual', '#jumlah', '#persenPajak'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak', '#diskon', '#jumlah'));
		});
		
		$('#diskon').blur(function() {
			$("#persenDiskon").val(hitungPersen('#hargaJual', '#jumlah', '#diskon'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak','#diskon', '#jumlah'));
		});
		
		$('#diskon').keyup(function() {
			$("#persenDiskon").val(hitungPersen('#hargaJual', '#jumlah', '#diskon'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak','#diskon', '#jumlah'));
		});
		
		$('#persenDiskon').keyup(function() {
			$("#diskon").val(hitungFromPersen('#hargaJual', '#jumlah', '#persenDiskon'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak', '#diskon', '#jumlah'));
		});
		
		$('#persenDiskon').blur(function() {
			$("#diskon").val(hitungFromPersen('#hargaJual', '#jumlah', '#persenDiskon'));
			$('#subTotal').val(hitungSubTotal('#hargaJual', '#pajak', '#diskon', '#jumlah'));
		});
		
		$('#jumlahBeliRacikan').blur(function() {
			$('#totalRacikan').val(hitungSubTotalRacikan('#hargaSatuanRacikan', '#jumlahBeliRacikan'));
		});

		$("#obat").keyup(function(event) {
			if (event.keyCode == 13) {
				var val = $('#obat').val();
				isiDataObat(val);
			}
		});
		
		setKeyUpForHitungGrandTotal('#diskonBayar');
		setKeyUpForHitungGrandTotal('#pajakBayar');

		$("#racikan").click(function(event) {
			event.preventDefault();
		});
		
		$(".btn-xs").click(function(event) {
			event.preventDefault();
		});
		
		$("#tambahRacikan").click(function(event) {
			tambahRacikan();
			event.preventDefault();		
		});
		
		$('#btnBayar').click(function() {
			if ($('#nomorFaktur').val() == '' || $('#nomorFaktur') == null){
				alert("Harap Pilih Obat Terlebih Dulu");
			} else {				
				$('#bayarModal').modal('show');
				$('#total').val($("#grandTotal").text());
				$('#grandTotalBayar').val($("#grandTotal").text());
				$('#grandTotalBayar').focus();
			}
		});
		
		$(window).bind('beforeunload', function(){
			nmrFaktur = $('#nomorFaktur').val();
			tgl = $('#tanggal').val();
			return "Transaksi akan dibatalkan. Anda Yakin?";
		});
		
		$(window).on('unload', function(e) {			
			data = {nomorFaktur : nmrFaktur, tanggal : tgl};
			$.postAjaxAsyncFalse('${nomorFakturNotTerpakai}', data, function(r) {
				null;
			}, function() {
				$('#gritter-edit-gagal').click();						
			});		
		});
		
		setKeyUpForHitungGrandTotal('#diskonBayar');
		setKeyUpForHitungGrandTotal('#pajakBayar');
		setKeyUpForHitungGrandTotal('#persenPajakBayar');
		setKeyUpForHitungGrandTotal('#persenDiskonBayar');
		
		setKeyUpForKembalian('#bayar');

		tabKey('#obat', '#jumlah');
		tabKey('#jumlah', '#hargaJual');
		tabKey('#hargaJual', '#diskon');
		tabKey('#diskon', '#persenDiskon');
		tabKey('#persenDiskon', '#pajak');
		tabKey('#pajak', '#persenPajak');
		tabKey('#persenPajak', '#tambah');
		tabKey('#jumlahBeliRacikan', '#tambahRacikan');
		tabKey('#persenDiskonBayar', '#bayar');
		tabKey('#bayar', '#jual');

		$(".formTambah").validate({
			rules : {
				tanggal : {
					required : true
				},
				jenis : {
					valueNotEquals : "default"
				},
				obat : {
					required : true
				},
				stok : {
					min : 1,
					required : true,						
				},
				jumlah : {
					min : 1,
					required : true,
					lessEqualThan: '#stok'
				},
				satuan : {
					required : true
				},
				hargaJual : {
					required : true
				},
// 					subtotal : {
// 						required : true
// 					}
				},
			messages : {
				tanggal : {
					required : "Harap Pilih Tanggal"
				},
				jenis : {
					valueNotEquals : "Pilih Jenis Penjualan"
				},
				obat : {
					required : "Masukan Nama Obat"
				},
				stok : {
					min : "Minimal 1",
					required : "Stok Kosong, Harap Diisi",
				},
				jumlah : {
					min : "Jumlah Pembelian Minimal 1",
					required : "Harap Isi Jumlah Pembelian",
					lessEqualThan: 'Jumlah Beli Melebihi Stok'
				},
				satuan : {
					required : "Isi Satuan"
				},
				hargaJual : {
					required : "Isi Harga Jual"
				},
// 					subtotal : {
// 						required : "Subtotal Kosong"
// 					}
				},
			submitHandler : function(form) {
				$("#pajak").val(hitungFromPersen('#hargaJual', '#jumlah', '#persenPajak'));
				$("#persenPajak").val(hitungPersen('#hargaJual', '#jumlah', '#pajak'));
				$("#diskon").val(hitungFromPersen('#hargaJual', '#jumlah', '#persenDiskon'));
				$("#persenDiskon").val(hitungPersen('#hargaJual', '#jumlah', '#diskon'));
				$('#subtotal').val(hitungSubTotal('#hargaJual', '#pajak', '#diskon', '#jumlah'));					
				var data = setDataObat();					
				$.postJSON('${tambahKeListObatUrl}', data, function(r) {
					clean();
					$('#nomorFaktur').val(r.nomorFaktur);
					reloadListObat();
					document.getElementById('tanggal').disabled = true;						
				}, function() {
					$('#gritter-tambah-gagal').click();						
				});
			}
		});
		
		$('#pajakBayar').keyup(function() {
			$("#persenPajakBayar").val(hitungPersen('#total', null, '#pajakBayar'));
			$('#grandTotalBayar').val(hitungGrandTotalBayar('#total', '#diskonBayar','#pajakBayar'));
		});
		
		$('#persenPajakBayar').keyup(function() {
			$("#pajakBayar").val(hitungFromPersen('#total', null, '#persenPajakBayar'));
			$('#grandTotalBayar').val(hitungGrandTotalBayar('#total', '#diskonBayar','#pajakBayar'));
		});
		
		$('#diskonBayar').keyup(function() {
			$("#persenDiskonBayar").val(hitungPersen('#total', null, '#diskonBayar'));
			$('#grandTotalBayar').val(hitungGrandTotalBayar('#total', '#diskonBayar','#pajakBayar'));
		});
		
		$('#persenDiskonBayar').keyup(function() {
			$("#diskonBayar").val(hitungFromPersen('#total', null, '#persenDiskonBayar'));
			$('#grandTotalBayar').val(hitungGrandTotalBayar('#total', '#diskonBayar','#pajakBayar'));
		});
		
		$(".formBayar").validate({
			rules : {
				total : {
					required : true
				},
				grandTotalBayar : {
					min : 1,
					required : true
				},
				bayar : {
					min : 1,
					required : true
				},
				kembali : {
					min : 0,
					required : true
				}
			},
			messages : {
				total : {
					required : "Total Harga Tidak Ada"
				},
				grandTotalBayar : {
					min : "Grand Total Salah",
					required : "Grand Total Tidak Ada",					
				},
				bayar : {
					min : "Input Pembayaran dengan Benar",
					required : "Input Pembayaran dengan Benar"
				},
				kembali : {
					min : "Kembalian Tidak Benar",
					required : "Kembalian Tidak Benar"
				}
			},
			submitHandler : function(form) {
				if (bayarValid('#bayar', '#grandTotalBayar')){
					var data = {};
					data = setDataPenjualan();
					$.postJSON('${jualUrl}', data, function(r) {
						$('.btnKeluar').click();
						cleanAll();
						$('#cetakButton').click();
					}, function() {
						$('#gritter-tambah-gagal').click();						
					});
				} else {
					alert('Uang Pembayaran Tidak Cukup');
				}				
			}
		});

		setAutoComplete('#obat', '${cariObatUrl}');
		setMaskingUang("#hargaJual");
		setMaskingUang("#diskon");
		setMaskingUang("#pajak");
		setMaskingUang("#subTotal");
		setMaskingUang("#hargaSatuanRacikan");
		setMaskingUang("#totalRacikan");
		setMaskingUang("#total");
		setMaskingUang("#pajakBayar");
		setMaskingUang("#diskonBayar");
		setMaskingUang("#grandTotalBayar");
		setMaskingUang("#bayar");
		setMaskingUang("#kembali");
	});
	
	function setKeyUpForHitungGrandTotal(selector){
		$(selector).keyup(function(event) {			
			var grandTotalBayar = hitungGrandTotalBayar('#total', '#diskonBayar', '#pajakBayar');			
			$('#grandTotalBayar').val(grandTotalBayar);
		});	
	}
	
	function setKeyUpForKembalian(selector){
		$(selector).keyup(function(event) {			
			var kembalian = hitungKembalian('#grandTotalBayar', '#bayar');			
			$('#kembali').val(kembalian);
		});	
	}

	function isiDataObat(val) {
		if (val != ''){
			var data = {nama : val};
			$.getAjax('${getObatUrl}', data, function(obat) {
				$('#hargaJual').val(obat.detail[0].hargaJual);
				$('#stok').val(obat.stok[0].stok);
				$('#satuan').val(obat.satuan.nama);
			}, null);	
		}		
	}
	
	function hitungSubTotalRacikan(hrg, jum){
		if ($(hrg).val() != '' && $(jum).val() != ''){
			var hargaBeli = $(hrg).val().replace(/\./g, '');
			hargaBeli = parseFloat(hargaBeli);
			var jumlah = $(jum).val();
			jumlah = parseInt(jumlah, 10);
			var subTotal = hargaBeli * jumlah;		
			return subTotal;
		}
	}
	
	function hitungSubTotal(hrg, pjk, dsk, jum){
		if ($(hrg).val() != '' && $(pjk).val() != '' && $(dsk).val() != '' && $(jum).val() != ''){
			var hargaBeli = parseFloat($(hrg).val().replace(/\./g, ''));			
			var pajak = parseFloat($(pjk).val().replace(/\./g, ''));			
			var d = parseFloat($(dsk).val().replace(/\./g, ''));		
			var jumlah = parseInt($(jum).val(), 10);			
			var subTotal = hargaBeli * jumlah;
			subTotal = subTotal + pajak;
			subTotal = subTotal - d;
			return subTotal;
		}						
	}
	
	function hitungFromPersen(h, j, pd){
		if ($(h).val() != '' && $(j).val() != '' && $(pd).val() != ''){
			var hargaBeli = parseFloat($(h).val().replace(/\./g, ''));			
			var persen= parseFloat($(pd).val().replace(/\./g, ''));
			var jumlah;
			if (j == null) {
				jumlah = 1;
			} else {
				jumlah = parseInt($(j).val(), 10);	
			}
			var t = hargaBeli * jumlah;
			var r = (t * persen) / 100; 
			return r;
		}
	}
	
	function hitungPersen(h, j ,pd){
		if ($(h).val() != '' && $(j).val() != '' && $(pd).val() != ''){			
			var hargaBeli = parseFloat($(h).val().replace(/\./g, ''));			
			var persen= parseFloat($(pd).val().replace(/\./g, ''));			
			var jumlah;
			if (j == null) {
				jumlah = 1;
			} else {
				jumlah = parseInt($(j).val(), 10);	
			}
			var t = hargaBeli * jumlah;
			var r = (persen * 100) / t;
			return r;
		}
	}
	
	function hitungGrandTotalBayar(t, d, p){	
		var total = parseFloat($(t).val().replace(/\./g, ''), 10);		
		var pajak = parseFloat($(p).val().replace(/\./g, ''), 10);
		var diskon = parseFloat($(d).val().replace(/\./g, ''), 10);		
		var grandTotal = total - diskon;
		grandTotal = grandTotal + pajak;
		return grandTotal;				
	}
	
	function hitungKembalian(gt, b){
		var grandTotal = $(gt).val().replace(/\./g, '');
		var bayar = $(b).val().replace(/\./g, '');
		var kembali = bayar - grandTotal;
		return kembali;
	}
	
	function bayarValid(b, t){		
		var bayar = parseFloat($(b).val().replace(/\./g, ''));
		var total = parseFloat($(t).val().replace(/\./g, ''));
		if (bayar >= total){
			return true;
		} else {
			return false;
		}
	}
	
	function tambahRacikan(){
		var h = $('#hargaSatuanRacikan').val();
		if (h != null){
	 		var racik = $("#namaRacikan").text();
	 		var data = setDataRacikan();
	 		data['obat'] = racik;
			$.postJSON('${tambahRacikanKeListJual}', data, function(r) {
				if (r.info == 'OK'){
					clean();
					$('#nomorFaktur').val(r.nomorFaktur);
					reloadListObat();
					document.getElementById('tanggal').disabled = true;	
					$('.btnKeluar').click();	
				} else {
					$('#pesan').empty();
					$('#pesanButton').click();
					$('#pesan').append(r.info);
				}				
			}, function() {
				$('#gritter-tambah-gagal').click();						
			});
		}
	}
	
	function setDataPenjualan(){
		var data = {};		
		data['tanggal'] = $('#tanggal').val();
		data['nomorFaktur'] = $('#nomorFaktur').val();
		data['dokter'] = $('#dokter').val();
		data['pelanggan'] = $('#pelanggan').val();
		data['tipe'] = $('#jenis').val();
		data['total'] = $('#total').val();
		data['pajak'] = $('#pajakBayar').val();
		data['diskon'] = $('#diskonBayar').val();
		data['grandTotal'] = $('#grandTotalBayar').val();
		data['bayar'] = $('#bayar').val();
		data['kembali'] = $('#kembali').val();
		return data;
	}
	
	function setDataObat(){
		var data = {};		
		data['tanggal'] = $('#tanggal').val();
		data['nomorFaktur'] = $('#nomorFaktur').val();
		data['dokter'] = $('#dokter').val();
		data['pelanggan'] = $('#pelanggan').val();
		data['obat'] = $('#obat').val();
		data['jumlah'] = $('#jumlah').val();		
		data['hargaJual'] = $('#hargaJual').val();
		data['diskon'] = $('#diskon').val();
		data['pajak'] = $('#pajak').val();
		data['subTotal'] = $('#subTotal').val();		
		return data;
	}
	
	function setDataRacikan(){
		var data = {};		
		data['tanggal'] = $('#tanggal').val();
		data['nomorFaktur'] = $('#nomorFaktur').val();
		data['dokter'] = $('#dokter').val();
		data['pelanggan'] = $('#pelanggan').val();		
		data['jumlah'] = $('#jumlahBeliRacikan').val();		
		data['hargaJual'] = $('#hargaSatuanRacikan').val();
		data['diskon'] = $('#diskon').val();
		data['pajak'] = $('#pajak').val();
		data['subTotal'] = $('#totalRacikan').val();		
		return data;
	}
	
	function reloadListObat() {
		var nomorFaktur = $('#nomorFaktur').val();		
		var data = {
			n : nomorFaktur
		};
		$.getAjax('${daftarTempUrl}', data, function(result) {			
			$('#body').empty();
			$('#body').append(result.tabel);
			$('#grandTotal').empty();
			$('#grandTotal').append(result.grandTotal);
		}, null);
	}
	
	function refreshRacikan(halaman, find) {
		var data = { hal : halaman, cari : find };

		$.getAjax('${racikanListUrl}', data, function(result) {
			$('#tabelRacikan').empty();
			$('#tabelRacikan').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}
	
	function getDataRacikanPenjualan(idx) {
		var data = {id : idx};

		$.getAjax('${racikanDetailUrl}', data, function(result) {
			$('#namaRacikan').empty();
			$('#namaRacikan').append(result.value1);
			$('#racikanDetail').empty();
			$('#racikanDetail').append(result.tabel);			
			$('#hargaSatuanRacikan').val('');
			$('#hargaSatuanRacikan').val(result.grandTotal);
			$('#hargaSatuanRacikan').focus();
			$('#jumlahBeliRacikan').val('');
			$('#jumlahBeliRacikan').focus();
		}, null);
	}
	
	function hapus(index){
		var data = {};
		data['id'] = index;
		data['nomorFaktur'] = $('#nomorFaktur').val();
		$.postJSON('${hapusObatUrl}', data, function() {
			reloadListObat();			
		}, function(e) {
			console.log('GAGAL HAPUS');
		});
	}

	function tabKey(origin, destination) {
		$(".formTambah").on('keydown', origin, function(e) {
			var keyCode = e.keyCode || e.which;
			if (keyCode == 9) {
				e.preventDefault();
				$(destination).focus();
			}
		});
	}

	function clean() {
		$('#obat').val('');
		$('#hargaJual').val('');
		$('#diskon').val('0');
		$('#personDiskon').val('0');
		$('#pajak').val('0');
		$('#persenPajak').val('0');
		$('#stok').val('');
		$('#satuan').val('');
		$('#jumlah').val('');
		$('#subTotal').val('');
		$('#hargaSatuanRacikan').val('');
		$('#jumlahBeliRacikan').val('');
		$('#totalRacikan').val('');
		$('#namaRacikan').empty();
		$('#racikanDetail').empty();
	}

	function cleanAll() {
		$('#obat').val('');
		$('#hargaJual').val('');
		$('#diskon').val('0');
		$('#personDiskon').val('0');
		$('#pajak').val('0');
		$('#persenPajak').val('0');
		$('#stok').val('');
		$('#satuan').val('');
		$('#jumlah').val('');
		$('#jenis').val('default');
		$('#dokter').val('');
		$('#pelanggan').val('');
		$('#subTotal').val('');
		$('#hargaSatuanRacikan').val('');
		$('#jumlahBeliRacikan').val('');
		$('#totalRacikan').val('');
		$('#namaRacikan').empty();
		$('#racikanDetail').empty();
		$('#nomorFaktur').val('');
		$('#grandTotal').text('0');
		$('#pajakBayar').text('0');
		$('#persenPajakBayar').text('0');
		$('#diskonBayar').text('0');
		$('#persenDiskonBayar').text('0');
		$('#body').empty();
		document.getElementById('tanggal').disabled = false;
	}
</script>