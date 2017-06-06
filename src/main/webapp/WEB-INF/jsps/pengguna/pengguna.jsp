<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/pengguna/simpan" />
<c:url var="dapatkanUrl" value="/pengguna/dapatkan" />


<div class="row mt">
	<div class="col-lg-12">
		<div class="form-panel">
			<h3><i class="fa fa-angle-right"></i>Pengguna : ${pengguna}</h3>			
			<form:form commandName="pengguna" cssClass="form-horizontal style-form formEdit" method="POST" action="${simpanUrl}">
				<div class="form-group">
					<label class="col-sm-3 col-sm-3 control-label">Password Lama</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" name="passwordLama" id="passwordLama">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 col-sm-3 control-label">Password Baru</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" name="passwordBaru" id="passwordBaru">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 col-sm-3 control-label">Konfirmasi Password Baru</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" name="confirmPasswordBaru"
							id="confirmPasswordBaru">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<input type="submit" class="btn btn-primary" value="Simpan"
							style="width: 250px"> <input type="button"
							class="btn btn-default" value="Reset" id="btnReset"
							style="width: 110px">
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
	$(document).ready(function() {
		$(".formEdit").validate({
			rules : {
				passwordLama : {
					required : true
				},
				passwordBaru : {
					minlength : 6,
					required : true
				},
				confirmPasswordBaru : {
					minlength : 6,
					equalTo : "#passwordBaru"
				},
			},
			messages : {
				passwordLama : "Harap Isi Password Lama",
				passwordBaru : {
					required : "Harap Isi Password Baru",
					minlength : "Password Harus Lebih dari 6 Karakter"
				},
				confirmPasswordBaru : {
					minlength : "Password Harus Lebih dari 6 Karakter",
					equalTo : "Password Tidak Sama"
				}
			},
			submitHandler : function(form) {
				var data = {};
				data['passwordLama'] = $('#passwordLama').val();
				data['passwordBaru'] = $('#passwordBaru').val();
				data['confirmPasswordBaru'] = $('#confirmPasswordBaru').val();
				$.postJSON('${simpanUrl}', data, function() {
					$('#gritter-edit-sukses').click();
					reset();
				}, function() {
					$('#gritter-edit-gagal').click();
					console.log(p.info);
				});
			}
		});

		$('#btnReset').click(function() {
			reset();
		});
		
		

	});
	
	function reset(){
		$('#passwordLama').val('');
		$('#passwordBaru').val('');
		$('#confirmPasswordBaru').val('');
	}
</script>