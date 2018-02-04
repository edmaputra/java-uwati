<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/profil/simpan" />
<c:url var="dapatkanUrl" value="/profil/dapatkan" />

<form class="form-horizontal style-form formTambah" method="POST">
	<div class="row">
		<div class="col-lg-12 col-md-12">
			<div class="form-panel">
				<input type="submit" class="btn btn-primary pull-right"
					value="Simpan" style="width: 250px"> <input type="button"
					class="btn btn-default" value="Reset" id="btnReset"
					style="width: 110px">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
			<div class="form-panel">
				<h4 class="mb">
					<i class="fa fa-angle-right"></i> APOTEK
				</h4>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Nama</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="nama" id="nama"
							value="${apotek.nama}" autocomplete="off">
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Alamat</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="3" name="alamat" id="alamat">${apotek.alamat}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Telepon</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="telepon"
							id="telepon" value="${apotek.telepon}" autocomplete="off">
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<div class="form-panel">
				<h4 class="mb">
					<i class="fa fa-angle-right"></i> Obat
				</h4>
				<div class="form-group">
					<label class="col-sm-7 col-sm-7 control-label">Notifikasi
						Obat Kadaluarsa</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="tenggat_kadaluarsa"
							id="tenggat_kadaluarsa" value="${apotek.tenggatKadaluarsa}"
							autocomplete="off">
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	function muatUlang() {
		$.getAjax('${dapatkanUrl}', null, function(result) {
			$('#nama').val(result.nama);
			$('#alamat').val(result.alamat);
			$('#telepon').val(result.telepon);
			$('#tenggat_kadaluarsa').val(result.tenggatKadaluarsa);
			// 			$('#biaya_resep').val(result.biayaResep);
		}, null);
	}

	$(document).ready(function() {
		muatUlang();
		$(".formTambah").validate(
			{
				rules : {
					nama : {
						required : true
					},
					alamat : {
						required : true
					},
					tenggat_kadaluarsa : {
						required : true,
						number : true,
						min : 0
					},
				},
				messages : {
					nama : "Silahkan Isi Nama Apotik",
					alamat : "Silahkan Isi Alamat Apotik",
					tenggat_kadaluarsa : {
						required : "Batas Hari Peringatan Obat Kadaluarsa Harus Diisi",
						number : "Isi dengan Angka",
						min : "Angka Minimal adalah 0"
					}
				},
				submitHandler : function(form) {
					var data = {};
						data['nama'] = $('#nama').val();
						data['alamat'] = $('#alamat').val();
						data['telepon'] = $('#telepon').val();
						data['tenggatKadaluarsa'] = $('#tenggat_kadaluarsa').val();												
					$.postJSON('${simpanUrl}',data,	function() {
						$('#gritter-tambah-sukses').click();
						muatUlang();
					},
					function() {$('#gritter-tambah-gagal').click();});}
			});

			$('#btnReset').click(function() {
				$('#nama').val('');
				$('#alamat').val('');
				$('#telepon').val('');
				$('#tenggat_kadaluarsa').val('');
			});


		});
</script>