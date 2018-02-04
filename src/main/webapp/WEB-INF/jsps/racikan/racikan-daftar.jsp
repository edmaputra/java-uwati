<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/racikan/tambah" />
<c:url var="editUrl" value="/racikan/edit" />
<c:url var="hapusUrl" value="/racikan/hapus" />
<c:url var="dapatkanUrl" value="/racikan/dapatkan" />
<c:url var="daftarUrl" value="/racikan/daftar" />
<c:url var="cariObatUrl" value="/obat/cariobat" />
<c:url var="getObatFromNamaUrl" value="/obat/nama" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<div class="content-panel">
				<div class="row">
					<div class="col-md-2 ">
						<security:authorize access="hasAnyRole('ADMIN','APOTEK')">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#racikan-modal">Racikan Baru</button>
						</security:authorize>
					</div>

					<div class="col-md-10">
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
									onclick="refresh(1,'')">Reset</button>
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

<div class="modal fade" id="racikan-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Racikan Baru</h4>
			</div>

			<form:form action="${tambahUrl}" commandName="racikan"
				cssClass="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Nama Racikan:</label>
									<form:input path="nama" cssClass="form-control" id="tambahNama" />
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Jumlah Komposisi:</label>									
									<input type="text" class="form-control" id="tambahJumlahKomposisi">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Biaya Racik</label>									
									<input type="text" class="form-control" name="biayaRacik" id="biayaRacik">
								</div>
							</div>	
							<div class="col-md-2">
								<div class="form-group">
									<label>-</label>
									<button class="form-control btn btn-default" id="setKomposisi">Set</button>
								</div>
							</div>
						</div>
						<div id="komposisi"></div>								
					</div>
				</div>
				<div class="modal-footer">
					<div class="row">
						<div class="col-md-4">
							<div class="form-grup">
<!-- 								<label>Total:</label>									 -->
								<input type="text" class="form-control" id="total" readonly="readonly" placeholder="TOTAL">
<!-- 								<h3 id="grandTotal" class="text-right">0</h3> -->
							</div>
						</div>
						<div class="col-md-2 col-md-offset-4">
							<div class="form-group">
							<button type="button" class="form-control btn btn-default btnKeluar" id="k" data-dismiss="modal">Keluar</button>
							</div>
						</div>
						<div class="col-md-2">
						<div class="form-group">
							<input type="submit" class="form-control btn btn-primary" value="Simpan" id="simpan" />
							</div>
						</div>
					</div>
				</div>

			</form:form>
		</div>
	</div>
</div>

<!-- <div class="modal fade" id="pelanggan-modal-edit" tabindex="-1" -->
<!-- 	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!-- 	<div class="modal-dialog "> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-header"> -->
<!-- 				<button type="button" class="close" data-dismiss="modal" -->
<!-- 					aria-hidden="true">&times;</button> -->
<!-- 				<h4 class="modal-title" id="myModalLabel">Edit Pelanggan</h4> -->
<!-- 			</div> -->
<%-- 			<form:form action="${editUrl}" commandName="pelanggan" --%>
<%-- 				cssClass="form-horizontal style-form formEdit" method="post"> --%>
<!-- 				<div class="form-panel"> -->
<!-- 					<div class="modal-body"> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label>Kode:</label> -->
<%-- 									<form:input path="kode" cssClass="form-control" id="editKode" /> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label>Nama:</label> -->
<%-- 									<form:input path="nama" cssClass="form-control" id="editNama" />																		 --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-7"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label>Alamat:</label> -->
<%-- 									<form:input path="alamat" cssClass="form-control" id="editAlamat" />									 --%>
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 							<div class="col-md-5"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label>Kontak:</label> -->
<%-- 									<form:input path="kontak" cssClass="form-control" id="editKontak" />									 --%>
<!-- 								</div> -->
<!-- 							</div>							 -->
<!-- 						</div>					 -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="modal-footer"> -->
<!-- 					<button type="button" class="btn btn-default btnKeluar" data-dismiss="modal">Keluar</button> -->
<%-- 					<form:hidden path="id" cssC<label class="lb-sm">Tanggal Beli</label>lass="form-control" id="editId" /> --%>
<!-- 					<input type="submit" class="btn btn-primary" value="Simpan" /> -->
<!-- 				</div> -->
<%-- 			</form:form> --%>
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<!-- <div class="modal fade" id="pelanggan-modal-hapus" tabindex="-1" -->
<!-- 	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!-- 	<div class="modal-dialog modal-sm"> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-header"> -->
<!-- 				<button type="button" class="close" data-dismiss="modal" -->
<!-- 					aria-hidden="true">&times;</button> -->
<!-- 				<h4 class="modal-title" id="myModalLabel">Hapus Pelanggan</h4> -->
<!-- 			</div> -->
<!-- 			<div class="form-panel"> -->
<!-- 				<div class="modal-body"> -->
<!-- 					<p>Apakah Anda Yakin Ingin Menghapus ?</p> -->
<!-- 				</div> -->
<!-- 			</div> -->
<%-- 			<form:form action="${hapusUrl}" commandName="pelanggan" --%>
<%-- 				cssClass="form-horizontal style-form formHapus" method="post"> --%>
<!-- 				<div class="modal-footer"> -->
<!-- 					<button type="button" class="btn btn-default btnKeluar" -->
<!-- 						id="keluarModalHapus" data-dismiss="modal">Tidak</button> -->
<%-- 					<form:hidden path="id" cssClass="form-control" id="hapusId" /> --%>
<!-- 					<input type="submit" class="btn btn-danger" value="Hapus" /> -->
<!-- 				</div> -->
<%-- 			</form:form> --%>
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	var obatRacikan;
	var obatRacikanJson;
	$(document).ready(function() {
		setMaskingUang("#biayaRacik");
		setMaskingUang("#total");
		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});
		
		$('#setKomposisi').click(function(e) {			
			setJumlahInputKomposisi($('#tambahJumlahKomposisi').val());
			e.preventDefault();
		});
		
		$('#k').click(function(e) {			
			reset();
		});
		
		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				biayaRacik : {
					required : true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				biayaRacik : "Biaya Racik Wajib Diisi"
			},
			submitHandler : function(form) {				
				bacaIsinya($('#tambahJumlahKomposisi').val());				
				var data = {};
				data['nama'] = $('#tambahNama').val();
				data['biayaRacik'] = $('#biayaRacik').val();
				data['komposisi'] = obatRacikanJson;
				$.postJSON('${tambahUrl}', data, function() {
					$('#gritter-tambah-sukses').click();
					$('.btnKeluar').click();
					reset();
					refresh();
				}, function() {
					$('#gritter-tambah-gagal').click();
				});
			}
		});


		$(".formHapus").submit(function(e) {
			e.preventDefault();
			var str = {};
			str['id'] = $('#hapusId').val();
			$.postJSON('${hapusUrl}', str, function(result) {
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
		$.getAjax('${dapatkanUrl}', data, function(result) {
			$('#editNama').val(result.nama);
			$('#editKontak').val(result.kontak);
			$('#editKode').val(result.kode);
			$('#editAlamat').val(result.alamat);
			$('#editId').val(ids);
		}, null);
	}

	function setIdUntukHapus(ids) {
		$('#hapusId').val(ids);
	}

	function refresh(halaman, find) {
		var data = {
			hal : halaman,
			cari : find
		};
		$.getAjax('${daftarUrl}', data, function(result) {
			$('#tabel').empty();
			$('#tabel').append(result.tabel);
			$('#nav').empty();
			$('#nav').append(result.navigasiHalaman);
		}, null);
	}
	
	function bacaIsinya(jumlah){
		var arr = new Array();
		var num = parseInt(jumlah, 10);
		for (var i = 1; i <= num; i++) {
			obatRacikan = new Object();
			var idObat = '#obat' + i;
			var idHarga = '#harga' + i;
			var idJumlah = '#jumlah' + i;
			var idSubtotal = '#subtotal' + i;
			obatRacikan.obat = $(idObat).val();
			obatRacikan.harga = $(idHarga).val();
			obatRacikan.jumlah = $(idJumlah).val();
			obatRacikan.subtotal = $(idSubtotal).val();
			arr.push(obatRacikan);
		}
		obatRacikanJson = JSON.parse(JSON.stringify(arr));
	}

	function setJumlahInputKomposisi(jumlah) {
		var num = parseInt(jumlah, 10);
		var container = $('<div />', {class : "form-panel"});
		for (var i = 1; i <= num; i++) {
			var row = $('<div />', {class : "row"});
			var column1 = $('<div />', {class : "col-md-3"});
			var column2 = $('<div />', {class : "col-md-2"});
			var column3 = $('<div />', {class : "col-md-3"});
			var column4 = $('<div />', {class : "col-md-4"});
			var formGroup1 = $('<div />', {class : "form-group"});
			var formGroup2 = $('<div />', {class : "form-group"});
			var formGroup3 = $('<div />', {class : "form-group"});
			var formGroup4 = $('<div />', {class : "form-group"});
			var inputObat = $('<input />', {id : "obat" + i, name : "obat" + i, class : "form-control", placeholder : "Obat"});
			var inputJumlah = $('<input />', {id : "jumlah" + i, name : "jumlah" + i, class : "form-control", placeholder : "Jumlah", value : "0"});
			var inputHarga = $('<input />', {id : "harga" + i, name : "harga" + i, class : "form-control", placeholder : "Harga", value : "0"});
			var inputSubtotal = $('<input />', {id : "subtotal" + i, name : "subtotal" + i, class : "form-control", value : "0", placeholder : "Subtotal", readonly : "readonly"});

			inputObat.appendTo(formGroup1);
			formGroup1.appendTo(column1);
			inputJumlah.appendTo(formGroup2);
			formGroup2.appendTo(column2);
			inputHarga.appendTo(formGroup3);
			formGroup3.appendTo(column3);
			inputSubtotal.appendTo(formGroup4);
			formGroup4.appendTo(column4);
			
			column1.appendTo(row);
			column2.appendTo(row);
			column3.appendTo(row);
			column4.appendTo(row);
		
			row.appendTo(container);
		}

		$('#komposisi').html(container);
		
		for (var i = 1; i <= num; i++) {
			var idObat = '#obat' + i;
			var idHarga = '#harga' + i;
			var idJumlah = '#jumlah' + i;
			var idSubtotal = '#subtotal' + i;
			setAutoComplete(idObat, '${cariObatUrl}');
			setMaskingUang(idHarga);
			setBlur(idObat, idHarga);
			setHitungBlur(idHarga, idJumlah, idSubtotal);
			setMaskingUang(idSubtotal);
			setHitungTotalBlur(idSubtotal, num, '#total');
		}		
	}
	
	function hitungSubTotal(hrg, jum, result){
		var hargaBeli = $(hrg).val();			
		var hargaBeli = hargaBeli.replace(/\./g, '');			
		var jumlah = $(jum).val();		
		var subTotal = hargaBeli * jumlah;		
		$(result).val(subTotal);		
	}
	
	function hitungTotal(jumlah, result){
		var num = parseInt(jumlah, 10);
		var total = parseInt(0, 10);
		for (var i = 1; i <= num; i++) {
			var idSubtotal = '#subtotal' + i;
			var subTotal = $(idSubtotal).val().replace(/\./g, '');
			subTotal = parseInt(subTotal, 10);
			total = total + subTotal;
		}
		$(result).val(total);		
	}
	
	function setBlur(obat, harga){
		$(obat).blur(function() {
			var val = $(obat).val();
			isiDataObat(val, harga);
		});
	}
	
	function setHitungBlur(harga, jumlah, result){
		$(harga).blur(function() {
			hitungSubTotal(harga, jumlah, result);			
		});
	}
	
	function setHitungTotalBlur(origin, jumlah, result){
		$(origin).blur(function() {
			hitungTotal(jumlah, result);			
		});
	}
	
	function isiDataObat(val, harga) {
		var data = {
			nama : val
		};
		$.getAjax('${getObatFromNamaUrl}', data, function(obat) {
			$(harga).val(obat.detail[0].hargaJual);						
		}, null);
	}
	
	function reset(){
		$('#tambahJumlahKomposisi').val('');
		$('#tambahNama').val('');
		$('#biayaRacik').val('');
		$('#komposisi').html('');
	}
</script>