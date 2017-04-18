<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/daftarpengguna/tambah" />
<c:url var="editUrl" value="/daftarpengguna/edit" />
<c:url var="hapusUrl" value="/daftarpengguna/hapus" />
<c:url var="dapatkanUrl" value="/daftarpengguna/dapatkan" />
<c:url var="daftarUrl" value="/daftarpengguna/daftar" />
<c:url var="roleSemuaUrl" value="/role/semua" />
<c:url var="roleByNamaUrl" value="/role/nama" />

<div class="showback">
	<div class="row mt">
		<div class="col-md-12">
			<div class="content-panel">
				<div class="row">
					<div class="col-md-2 ">
						<security:authorize access="hasAnyRole('ADMIN')">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#daftarpengguna-modal">Pengguna Baru</button>
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

<div class="modal fade" id="daftarpengguna-modal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Pengguna Baru</h4>
			</div>

			<form:form action="${tambahUrl}" commandName="pengguna" cssClass="form style-form formTambah" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-7">
								<div class="form-group">
									<label>Nama:</label>
									<form:input path="nama" cssClass="form-control" id="tambahNama" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Password:</label>
									<form:password path="kataSandi" cssClass="form-control"
										id="tambahKataSandi" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Password Konfirmasi:</label> <input type="password"
										class="form-control" name="kataSandiKonfirmasi" id="tambahKataSandiKonfirmasi" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Aktif:</label>									
									<form:checkbox path="isAktif" cssClass="form-control"
										id="tambahIsAktif" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>First Time:</label>
									<form:checkbox path="isPertamaKali" cssClass="form-control"
										id="tambahIsPertamaKali" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Kesempatan:</label>
									<input type="number" name="countKesalahan" class="form-control" id="tambahCountKesalahan" />									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Role 1:</label> 
									<input class="form-control" id="tambahRole1">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Role 2:</label> <input class="form-control"
										id="tambahRole2">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Role 3:</label> <input class="form-control autocomplete-text"
										id="tambahRole3">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						data-dismiss="modal">Keluar</button>
					<form:hidden path="id" cssClass="form-control" id="tambahId" />
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>

			</form:form>
		</div>
	</div>
</div>

<div class="modal fade" id="daftarpengguna-modal-edit" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Edit Pengguna</h4>
			</div>
			<form:form action="${editUrl}" commandName="pengguna" cssClass="form style-form formEdit" method="post">
				<div class="form-panel">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-7">
								<div class="form-group">
									<label>Nama:</label>
									<input class="form-control" name="nama" id="editNama" disabled="disabled"/>									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Password:</label>
									<form:password path="kataSandi" cssClass="form-control" id="editKataSandi" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Password Konfirmasi:</label> <input type="password"
										class="form-control" name="kataSandiKonfirmasi" id="editKataSandiKonfirmasi" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Aktif:</label>									
									<form:checkbox path="isAktif" cssClass="form-control" id="editIsAktif" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>First Time:</label>
									<form:checkbox path="isPertamaKali" cssClass="form-control"
										id="editIsPertamaKali" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Kesempatan:</label>
									<input type="number" name="countKesalahan" class="form-control" id="editCountKesalahan" />									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>Role 1:</label> 
									<input class="form-control" id="editRole1">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Role 2:</label>
									<input class="form-control" id="editRole2">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Role 3:</label>
									<input class="form-control" id="editRole3">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar" data-dismiss="modal">Keluar</button>
					<form:hidden path="id" cssClass="form-control" id="editId" />
					<input type="submit" class="btn btn-primary" value="Simpan" />
				</div>
			</form:form>
		</div>
	</div>
</div>

<div class="modal fade" id="daftarpengguna-modal-hapus" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Hapus Pengguna</h4>
			</div>
			<div class="form-panel">
				<div class="modal-body">
					<p>Apakah Anda Yakin Ingin Menghapus ?</p>
				</div>
			</div>
			<form:form action="${hapusUrl}" commandName="pengguna"
				cssClass="form-horizontal style-form formHapus" method="post">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						id="keluarModalHapus" data-dismiss="modal">Tidak</button>
					<form:hidden path="id" cssClass="form-control" id="hapusId" />
					<input type="submit" class="btn btn-danger" value="Hapus" />
				</div>
			</form:form>
		</div>
	</div>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	function getData(ids) {
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(pengguna) {
			$('#editId').val(ids);
			$('#editNama').val(pengguna.nama);			
			$('#editIsAktif').prop('checked', pengguna.isAktif);
			$('#editIsPertamaKali').prop('checked', pengguna.isPertamaKali);
			$('#editCountKesalahan').val(pengguna.countKesalahan);
// 			console.log('Pengguna : '+pengguna.id);
// 			console.log(pengguna.roles[2].nama);
			if ("undefined" !== typeof pengguna.roles[0].nama) {
				$('#editRole1').val(pengguna.roles[0].nama);	
			}
			
			if ("undefined" !== typeof pengguna.roles[1].nama) {
				$('#editRole2').val(pengguna.roles[1].nama);	
			}
			
			if ("undefined" !== typeof pengguna.roles[2].nama) {
				$('#editRole3').val(pengguna.roles[2].nama);	
			}
			
			
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
	
	function clear(){
		$('#tambahNama').val('');
		$('#editNama').val('');
		$('#tambahKataSandi').val('');
		$('#tambahKataSandiKonfirmasi').val('');
		$('#editKataSandi').val('');
		$('#editKataSandiKonfirmasi').val('');
		$('#tambahIsAktif').prop('checked', false);
		$('#tambahIsPertamaKali').prop('checked', false);
		$('#editIsAktif').prop('checked', false);
		$('#editIsPertamaKali').prop('checked', false);
		$('#tambahCountKesalahan').val('');
		$('#editCountKesalahan').val('');
		$('#tambahRole1').val('');
		$('#editRole1').val('');
		$('#tambahRole2').val('');
		$('#editRole2').val('');
		$('#tambahRole3').val('');
		$('#editRole3').val('');
	}

	$(document).ready(function() {
		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$(".formTambah").validate({
			rules : {
				nama : {
					required : true
				},
				kataSandi : {
					minlength : 6,
					required : true
				},
				kataSandiKonfirmasi : {
					minlength : 6,
					equalTo : "#tambahKataSandi"
				},
				countKesalahan :{
					number : true,
					min : 0,
					required :true
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				kataSandi : {
					minlength : "Password Harus Lebih dari 5 Karakter",
					required : "Passord Wajib Diisi"
				},
				kataSandiKonfirmasi : {
					minlength : "Password Harus Lebih dari 5 Karakter",
					equalTo : "Password Tidak Sama"
				},
				countKesalahan :{
					number : "Harap Isi dengan Angka",
					min : "Harap Isi Angka Positif",
					required : "Harap Isi Kesempatan"
				}				
			},
			submitHandler : function(form) {
				var data = {};
				data['nama'] = $('#tambahNama').val();
				data['kataSandi'] = $('#tambahKataSandi').val();
				data['isAktif'] = $('#tambahIsAktif').is(":checked");
				data['isPertamaKali'] = $('#tambahIsPertamaKali').is(":checked");
				data['countKesalahan'] = $('#tambahCountKesalahan').val();
				data['role1'] = $('#tambahRole1').val();
				data['role2'] = $('#tambahRole2').val();
				data['role3'] = $('#tambahRole3').val();
				$.postJSON('${tambahUrl}', data, function() {
					$('#gritter-tambah-sukses').click();
					$('.btnKeluar').click();
					clear();
					refresh();
				}, function() {
					$('#gritter-tambah-gagal').click();
				});
			}
		});

		$(".formEdit").validate({
			rules : {
				nama : {
					required : true
				},
				kataSandi : {
					minlength : 6,					
				},
				kataSandiKonfirmasi : {
					minlength : 6,
					equalTo : "#tambahKataSandi"
				},
				countKesalahan :{
					number : true,
					min : 0
				}
			},
			messages : {
				nama : "Nama Wajib Diisi",
				kataSandi : {
					minlength : "Password Harus Lebih dari 5 Karakter"
				},
				kataSandiKonfirmasi : {
					minlength : "Password Harus Lebih dari 5 Karakter",
					equalTo : "Password Tidak Sama"
				},
				countKesalahan :{
					number : "Harap Isi dengan Angka",
					min : "Harap Isi Angka Positif",
					required : "Harap Isi Kesempatan"
				}				
			},
			submitHandler : function(form) {
				var data = {};
				data['id'] = $('#editId').val();
				data['nama'] = $('#editNama').val();
				data['kataSandi'] = $('#editKataSandi').val();
				data['isAktif'] = $('#editIsAktif').is(':checked');
				data['isPertamaKali'] = $('#editIsPertamaKali').is(':checked');
				data['countKesalahan'] = $('#editCountKesalahan').val();
				data['role1'] = $('#editRole1').val();
				data['role2'] = $('#editRole2').val();
				data['role3'] = $('#editRole3').val();
				$.postJSON('${editUrl}', data, function() {
					$('#gritter-edit-sukses').click();
					$('.btnKeluar').click();
					refresh();
				}, function() {
					$('#gritter-edit-gagal').click();
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
		
		setAutoComplete('#tambahRole1', '${roleByNamaUrl}');
		setAutoComplete('#tambahRole2', '${roleByNamaUrl}');
		setAutoComplete('#tambahRole3', '${roleByNamaUrl}');
		setAutoComplete('#editRole1', '${roleByNamaUrl}');
		setAutoComplete('#editRole2', '${roleByNamaUrl}');
		setAutoComplete('#editRole3', '${roleByNamaUrl}');
		
		
				
		$('.btnKeluar').click(function(){
			clear();
		});
	});
</script>