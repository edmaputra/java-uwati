<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/pembelian-obat/tambah" />
<c:url var="cariObatUrl" value="/pembelian-obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="tambahObatUrl" value="/pembelian-obat/tambahTemp" />
<c:url var="hapusObatUrl" value="/pembelian-obat/hapusTemp" />
<c:url var="daftarTempUrl" value="/pembelian-obat/daftarTemp" />
<c:url var="beliUrl" value="/pembelian-obat/beli" />
<c:url var="tersediaUrl" value="/pembelian-obat/tersedia" />
<c:url var="cetakUrl" value="/pembelian-obat/cetak" />


<div class="row mt">
	<div class="col-md-12">
		<div class="form-panel">
		<form class="form formTambah" id="formObat" action="${tambahObatUrl}" method="POST">
			<h5 class="mb">
				<i class="fa fa-angle-right"></i> Rincian Pembelian
			</h5>
			
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Tanggal Beli</label> <input
								name="tanggal" type="text" id="tanggal"
								class="form-control datePicker" value="${tanggalHariIni}" />
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="lb-sm">Obat</label> <input name="namaObat"
								type="text" id="namaObat" class="form-control" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Harga Beli</label> <input name="hargaBeli"
								type="text" id="hargaBeli" class="form-control" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Nomor Faktur</label> <input
								name="nomorFaktur" type="text" id="nomorFaktur"
								class="form-control" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Kode Obat</label> <input name="kodeObat"
								type="text" id="kodeObat" class="form-control"
								readonly="readonly" />
						</div>
					</div>
					<div class="col-md-2 col-md-offset-1">
						<div class="form-group">
							<label class="lb-sm">Harga Jual</label> <input name="hargaJual"
								type="text" id="hargaJual" class="form-control" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Supplier</label> <input type="text"
								id="supplier" name="supplier" class="form-control" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label class="lb-sm">Jumlah Beli</label> <input name="jumlah"
								type="number" id="jumlah" class="form-control" />
						</div>
					</div>
					<div class="col-md-2 col-md-offset-2">
						<div class="form-group">
							<label class="lb-sm">Harga Jual Resep</label> <input
								name="hargaJualResep" type="text" id="hargaJualResep"
								class="form-control" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3 col-md-offset-3">
						<div class="form-group">
							<label class="lb-sm">Kadaluarsa</label> <input
								name="tanggalKadaluarsa" type="text" id="tanggalKadaluarsa"
								class="form-control datePicker" />
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="lb-sm">Total</label> <input name="subTotal"
								type="text" id="subTotal" class="form-control" readonly="readonly"
								style="font-size: 20px;" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="text-right">							
							<input type="submit" class="btn btn-default" value="Tambahkan" id="tambah"/>
						</div>
					</div>
				</div>
				</form>
		</div>

		<div class="form-panel">
		<div class="row">
			<div class="col-md-7">
				<table class="table table-striped table-advance table-hover"
				id="tabel">
				<thead>
					<tr>
						<th>Obat</th>
						<th>Jumlah</th>
						<th>Tanggal Kadaluarsa</th>
						<th>Harga Beli</th>
						<th>Sub Total</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="body"></tbody>
			</table>
			</div>
			<div class="col-md-5">
				<div class="form-panel">
				<h1 id="grandTotal" class="text-right">0</h1>
				</div>
			</div>
		</div>
			
		</div>
		
		<div class="form-panel">
			<div class="row">
				<div class="col-md-6 pull-right">
					<div class="text-right">
						<input type="button" id="simpan" class="btn btn-primary"
							value="Simpan" style="width: 50%;" />
					</div>
				</div>
			</div>
		</div>
	
	</div>
</div>
<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<div class="modal fade" id="cetakModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">Pembelian Tersimpan</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Ingin Mencetak Bukti Pembelian ?</p>
				</div>
			</div>
			<form action="${cetakUrl}" class="form-horizontal style-form formCetak" method="POST" target="_blank">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar" id="keluarModal" data-dismiss="modal" onclick="muatUlangHalaman()">Tidak</button>
					<input type="hidden" name="id" class="form-control" id="beliId" />
					<input type="submit" id="cetak" class="btn btn-primary" value="CETAK" />
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		setAutoComplete('#namaObat', '${cariObatUrl}');

		$('#namaObat').blur(function() {
			var val = $('#namaObat').val();
			isiDataObat(val);
		});

		$("#namaObat").keyup(function(event) {
			if (event.keyCode == 13) {
				var val = $('#namaObat').val();
				isiDataObat(val);
			}
		});
		
		tabKey('#tanggal', '#nomorFaktur');
		tabKey('#nomorFaktur', '#supplier');
		tabKey('#supplier', '#namaObat');
		tabKey('#namaObat', '#jumlah');
		tabKey('#jumlah', '#tanggalKadaluarsa');
		tabKey('#tanggalKadaluarsa', '#hargaBeli');
		tabKey('#hargaBeli', '#hargaJual');
		tabKey('#hargaJual', '#hargaJualResep');
		tabKey('#hargaJualResep', '#tambah');
		
		$('#jumlah').blur(function() {			
			hitungSubTotal('#jumlah');
		});		
		
		$('#hargaBeli').blur(function() {			
			hitungSubTotal('#hargaBeli');
		});
		
		$("#jumlah").keyup(function(event) {
			if (event.keyCode == 13) {
				hitungSubTotal("#jumlah");
			}
		});
		
		$('#tambah').click(function(){
			hitungSubTotal('#tambah');
			$(".formTambah").validate({
				rules : {
					tanggal : {
						required : true
					},
					supplier : {
						required : true
					},
					nomorFaktur : {
						required : true,
						remote: {
							url: "${tersediaUrl}",
							type: "get",
							data: {
								nomorFaktur: function(){
									return $("#nomorFaktur").val();
								},
								tahun: function(){
									return $("#tanggal").val();
								}
							}
						},
					},
					namaObat : {
						required : true
					},
					kodeObat : {
						required : true
					},
					jumlah : {
						required : true,
						number : true,
						min : 1
					},
					tanggalKadaluarsa : {
						required : true
					},				
					hargaJual : {
						required : true
					},
					hargaJualResep : {
						required : true
					},
					hargaBeli : {
						required : true
					}				
				},
				messages : {
					tanggal : "Isi Tanggal Pembelian",
					supplier : "Isi Nama Supplier",
					nomorFaktur : {
						required : "Isi Nomor Faktur",
						remote : "Nomor Faktur Sudah Ada"
					},
					namaObat : "Masukkan Nama Obat",
					kodeObat : "Masukkan Kode Obat",
					tanggalKadaluarsa : "Tentukan Tanggal Expire Obat",
					jumlah : {
						required : "Masukkan Jumlah",
						number : "Masukkan Angka dengan Benar",
						min : "Masukkan Lebih dari 0"
					},
					hargaJual : {
						required : "Masukkan Harga Jual Obat"					
					},
					hargaJualResep : {
						required : "Masukkan Harga Jual Resep Obat",					
					},
					hargaBeli : {
						required : "Masukkan Harga Beli Obat",					
					}				
				},
				submitHandler : function(form) {
					document.getElementById('nomorFaktur').readOnly = true;
					var data = {};
					data['obat'] = $('#namaObat').val();
					data['tanggal'] = $('#tanggal').val();
					data['supplier'] = $('#supplier').val();
					data['nomorFaktur'] = $('#nomorFaktur').val();
					data['jumlah'] = $('#jumlah').val();
					data['tanggalKadaluarsa'] = $('#tanggalKadaluarsa').val();
					data['hargaJual'] = $('#hargaJual').val();
					data['hargaJualResep'] = $('#hargaJualResep').val();
					data['hargaBeli'] = $('#hargaBeli').val();
					data['subTotal'] = $('#subTotal').val();
					$.postJSON('${tambahObatUrl}', data, function() {
						clean();
						refresh();
					}, function() {
						$('#gritter-tambah-gagal').click();						
					});
				}
			});
		});
		
		$('#simpan').click(function(e){
			var tanggal = $('#tanggal').val();
			var supplier = $('#supplier').val();
			var nomorFaktur = $('#nomorFaktur').val();
			if (tanggal==null || tanggal=="",supplier==null || supplier =="", nomorFaktur == null || nomorFaktur==""){
				alert('Harap Isi Tanggal, Nomor Faktur dan Supplier');
			} else {			
				var data = {};
				data['tanggal'] = tanggal;
				data['supplier'] = supplier;
				data['nomorFaktur'] = nomorFaktur;					
				$.postJSON('${beliUrl}', data, function(result) {
					cleanAll();
					refresh();
					$('#beliId').val(result.id);
					$('#cetakModal').modal('show');
					$('#gritter-tambah-sukses').click();
				}, function() {
					$('#gritter-tambah-gagal').click();
				});
				e.preventDefault();
			}
		});
		
		
// 		$(".formCetak").submit(function(e) {
// 			e.preventDefault();
// 			var data = {};
// 			data['id'] = $('#beliId').val();
// 			$.postJSON('${cetakUrl}', data, function(result) {
// 				refresh();
// 				$('#keluarModalHapus').click();
// 				$('#gritter-hapus-sukses').click();
// 			}, function(e) {
// 				$('#gritter-hapus-sukses').click();
// 				$('#keluarModalHapus').click();
// 				refresh();
// 			});
// 		});
		
		setMaskingUang("#hargaJual");
		setMaskingUang("#hargaJualResep");
		setMaskingUang("#hargaBeli");
		setMaskingUang("#subTotal");
	});

	function isiDataObat(val) {
		var data = {
			nama : val
		};
		$.getAjax('${getObatUrl}', data, function(obat) {
			$('#kodeObat').val(obat.kode);
			$('#hargaJual').val(obat.detail[0].hargaJual);
			$('#hargaJualResep').val(obat.detail[0].hargaJualResep);
			$('#hargaBeli').val(obat.detail[0].hargaBeli);
			$('#tanggalKadaluarsa').val(
					dateFormat(obat.expired[0].tanggalExpired, 'dd-mm-yyyy'));			
		}, null);
	}
	
	function hitungSubTotal(origin){
		var hargaBeli = $('#hargaBeli').val();			
		var hargaBeli = hargaBeli.replace(/\./g, '');			
		var jumlah = $('#jumlah').val();		
		var subTotal = hargaBeli * jumlah;		
		$('#subTotal').val(subTotal);		
	}
	
	function refresh() {
		var nomorFaktur = $('#nomorFaktur').val();
		var data = {
// 			s : supplier,
// 			t : tanggal
			n : nomorFaktur
		};

		$.getAjax('${daftarTempUrl}', data, function(result) {			
			$('#body').empty();
			$('#body').append(result.tabel);
			$('#grandTotal').empty();
			$('#grandTotal').append(result.grandTotal);
		}, console.log(''));
	}
	
	function hapus(index){
		var data = {};
		data['id'] = index;
		$.postJSON('${hapusObatUrl}', data, function() {
			refresh();			
		}, function(e) {
			console.log('GAGAL HAPUS');
		});
	}
	
	function tabKey(origin, destination){
		$(".formTambah").on('keydown', origin, function(e) { 
			  var keyCode = e.keyCode || e.which; 
			  if (keyCode == 9) { 
			    e.preventDefault(); 
			    $(destination).focus();
			  } 
		});
	}
	
	function muatUlangHalaman(){
		 window.location.reload();
	}
	
	function clean(){
		$('#kodeObat').val('');
		$('#hargaJual').val('');		
		$('#hargaJualResep').val('');		
		$('#hargaBeli').val('');		
		$('#tanggalKadaluarsa').val('');
		$('#jumlah').val('');
		$('#subTotal').val('');
		$('#namaObat').val('');
		$('#namaObat').focus();
	}
	
	function cleanAll(){
		$('#kodeObat').val('');
		$('#hargaJual').val('');		
		$('#hargaJualResep').val('');		
		$('#hargaBeli').val('');		
		$('#tanggalKadaluarsa').val('');
		$('#jumlah').val('');
		$('#subTotal').val('');
		$('#namaObat').val('');
		$('#supplier').val('');		
		$('#nomorFaktur').val('');
		document.getElementById('nomorFaktur').readOnly = false;
	}
</script>