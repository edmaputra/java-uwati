<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/profil/simpan" />
<c:url var="dapatkanUrl" value="/profil/dapatkan" />

<div class="row mt">
	<div class="col-lg-12">
		<div class="form-panel">
			<form:form commandName="apotek"
				cssClass="form-horizontal style-form formTambah" method="POST"
				action="${simpanUrl}">
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Nama</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="nama"
							id="tambahNama" value="${apotek.nama}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Alamat</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="alamat"
							id="tambahAlamat" value="${apotek.alamat}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Telepon</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="telepon"
							id="tambahTelepon" value="${apotek.telepon}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<input type="submit" class="btn btn-primary" value="Simpan" style="width: 250px">
						<input type="button" class="btn btn-default" value="Reset" id="btnReset" style="width: 110px">
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>

<script>
	function muatUlang() {
		$.getAjax('${dapatkanUrl}', null, function(result) {
			$('#tambahNama').val(result.nama);
			$('#tambahAlamat').val(result.alamat);
			$('#tambahTelepon').val(result.telepon);			
		}, null);
	}

	$(document).ready(function() {
		
		muatUlang();

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				alamat : {
					required : true
				},
			},
			messages : {
				nama : "Silahkan Isi Nama Apotik",
				alamat : "Silahkan Isi Alamat Apotik"
			},
			submitHandler : function(form) {
				var data = {};
				data['nama'] = $('#tambahNama').val();
				data['alamat'] = $('#tambahAlamat').val();
				data['telepon'] = $('#tambahTelepon').val();
				$.postJSON('${simpanUrl}', data, function() {
					$('#gritter-tambah-sukses').click();					
					muatUlang();
				}, function() {
					$('#gritter-tambah-gagal').click();
				});
			}
		});
		
		$('#btnReset').click(function(){
			$('#tambahNama').val('');
			$('#tambahAlamat').val('');
			$('#tambahTelepon').val('');
		});
		
	});
</script>