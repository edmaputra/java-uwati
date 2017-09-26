<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="tambahUrl" value="/daftarpengguna/tambah" />
<c:url var="editUrl" value="/daftarpengguna/edit" />
<c:url var="hapusUrl" value="/daftarpengguna/hapus" />

<c:url var="dapatkanUrl" value="/daftarpengguna/dapatkan" />
<c:url var="daftarUrl" value="/daftarpengguna/daftar" />
<c:url var="tersedia" value="/daftarpengguna/tersedia" />

<c:url var="roleSemuaUrl" value="/role/semua" />
<c:url var="roleByNamaUrl" value="/role/nama" />

<c:url var="suggestKaryawan" value="/karyawan/suggest" />
<c:url var="isKaryawanAda" value="/karyawan/ada" />

<div class="showback">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-2 ">
					<security:authorize access="hasAnyRole('ADMIN')">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#modal-pengguna" id="button-baru">Pengguna
							Baru</button>
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

<div class="modal container fade" id="modal-pengguna" tabindex="-1"
	style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Pengguna</h4>
	</div>

	<form class="form style-form" method="post" id="tambah-pengguna">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label>Nama:</label> <input type="text" name="nama" id="nama"
							class="form-control" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label>Karyawan:</label> <input type="text" class="form-control"
							id="karyawan" name="karyawan" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Password:</label> <input type="password" name="password"
							id="password" class="form-control" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Password Konfirmasi:</label> <input type="password"
							class="form-control" name="passwordKonfirmasi"
							id="passwordKonfirmasi" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Aktif:</label> <input type="checkbox" name="isAktif"
							class="form-control" id="isAktif" />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>First Time:</label> <input type="checkbox"
							name="isPertamaKali" class="form-control" id="isPertamaKali" />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Kesempatan:</label> <input type="number"
							name="countKesalahan" class="form-control" id="countKesalahan"
							autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Role 1:</label> <input type="text" name="role1"
							class="form-control" id="role1">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Role 2:</label> <input type="text" name="role2"
							class="form-control" id="role2">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Role 3:</label> <input type="text" name="role3"
							class="form-control" id="role3">
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="submit" class="btn btn-primary" value="Simpan" />
		</div>
	</form>
</div>

<div class="modal container fade" id="modal-pengguna-edit" tabindex="-1"
	style="display: none;">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="myModalLabel">Pengguna</h4>
	</div>

	<form class="form style-form" method="post" id="edit-pengguna">
		<div class="modal-body">
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label>Nama:</label> <input type="text" name="nama" id="namaEdit"
							class="form-control" autocomplete="off" readonly="readonly" />
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label>Karyawan:</label> <input type="text" class="form-control"
							id="karyawanEdit" name="karyawan" autocomplete="off">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Password:</label> <input type="password" name="password"
							id="passwordEdit" class="form-control" autocomplete="off" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Password Konfirmasi:</label> <input type="password"
							class="form-control" name="passwordKonfirmasi"
							id="passwordKonfirmasiEdit" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Aktif:</label> <input type="checkbox" name="isAktif"
							class="form-control" id="isAktifEdit" />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>First Time:</label> <input type="checkbox"
							name="isPertamaKali" class="form-control" id="isPertamaKaliEdit" />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Kesempatan:</label> <input type="number"
							name="countKesalahan" class="form-control"
							id="countKesalahanEdit" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label>Role 1:</label> <input type="text" name="role1"
							class="form-control" id="role1Edit">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Role 2:</label> <input type="text" name="role2"
							class="form-control" id="role2Edit">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label>Role 3:</label> <input type="text" name="role3"
							class="form-control" id="role3Edit">
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default btnKeluar"
				data-dismiss="modal">Keluar</button>
			<input type="hidden" name="id" class="form-control" id="ids" /> <input
				type="submit" class="btn btn-primary" value="Simpan" />
		</div>
	</form>
</div>


<div class="modal fade" id="modal-pengguna-hapus" tabindex="-1"
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
			<form class="form-horizontal style-form formHapus" method="post">
				<div class="modal-footer">
					<button type="button" class="btn btn-default btnKeluar"
						id="keluarModalHapus" data-dismiss="modal">Tidak</button>
					<input type="hidden" name="id" class="form-control" id="hapusId" />
					<input type="submit" class="btn btn-danger" value="Hapus" />
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
		refresh(1, '');

		$('#btnCari').click(function() {
			refresh(1, $('#stringCari').val());
		});

		$('#button-baru').click(function() {
			clear();
			clearEdit();
		});

		$('.btnEdit').click(function() {
			clear();
			clearEdit();
		});

		$("#tambah-pengguna").validate({
			rules : {
				nama : {
					required : true,
					remote : {
						url : "${tersedia}",
						type : "get",
						data : {
							nama : function() {
								return $("#nama").val();
							}
						}
					}
				},
				password : {
					minlength : 6,
					required : true
				},
				passwordKonfirmasi : {
					minlength : 6,
					equalTo : "#password"
				},
				countKesalahan : {
					number : true,
					min : 0,
					required : true
				},
				karyawan : {
					remote : {
						url : "${isKaryawanAda}",
						type : "get",
						data : {
							nama : function() {
								return $("#karyawan").val();
							}
						}
					}
				}
			},
			messages : {
				nama : {
					required : "Nama Wajib Diisi",
					remote : "User Sudah Ada"
				},
				password : {
					minlength : "Password Harus Lebih dari 5 Karakter",
					required : "Passord Wajib Diisi"
				},
				passwordKonfirmasi : {
					minlength : "Password Harus Lebih dari 5 Karakter",
					equalTo : "Password Tidak Sama"
				},
				countKesalahan : {
					number : "Harap Isi dengan Angka",
					min : "Harap Isi Angka Positif",
					required : "Harap Isi Kesempatan"
				},
				karyawan : {
					remote : "Karyawan Tidak Ada"
				}
			},
			submitHandler : function(form) {
				var data = {};
				data = setDataContent(data);
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

		$("#edit-pengguna").validate({
			rules : {
				nama : {
					required : true
				},
				passwordKonfirmasi : {
					minlength : 6,
					equalTo : "#passwordEdit"
				},
				countKesalahan : {
					number : true,
					min : 0,
					required : true
				},
				karyawan : {
					remote : {
						url : "${isKaryawanAda}",
						type : "get",
						data : {
							nama : function() {
								return $("#karyawanEdit").val();
							}
						}
					}
				}
			},
			messages : {
				nama : {
					required : "Nama Wajib Diisi"
				},
				passwordKonfirmasi : {
					minlength : "Password Harus Lebih dari 5 Karakter",
					equalTo : "Password Tidak Sama"
				},
				countKesalahan : {
					number : "Harap Isi dengan Angka",
					min : "Harap Isi Angka Positif",
					required : "Harap Isi Kesempatan"
				},
				karyawan : {
					remote : "Karyawan Tidak Ada"
				}
			},
			submitHandler : function(form) {
				var data = {};
				data = setDataEdit(data);
				data['id'] = $('#ids').val();
				$.postJSON('${editUrl}', data, function() {
					$('#gritter-edit-sukses').click();
					$('.btnKeluar').click();
					clear();
					refresh();
				}, function() {
					$('#gritter-edit-gagal').click();
				});
			}
		});

		$(".formHapus").submit(function(e) {
			e.preventDefault();
			var data = {};
			data['id'] = $('#hapusId').val();
			$.postJSON('${hapusUrl}', data, function(result) {
				refresh();
				$('#keluarModalHapus').click();
				$('#gritter-hapus-sukses').click();
			}, function(e) {
				$('#gritter-hapus-sukses').click();
				$('#keluarModalHapus').click();
				refresh();
			});
		});

		setAutoComplete('#role1', '${roleByNamaUrl}');
		setAutoComplete('#role2', '${roleByNamaUrl}');
		setAutoComplete('#role3', '${roleByNamaUrl}');
		setAutoComplete('#role1Edit', '${roleByNamaUrl}');
		setAutoComplete('#role2Edit', '${roleByNamaUrl}');
		setAutoComplete('#role3Edit', '${roleByNamaUrl}');
		setAutoComplete('#karyawan', '${suggestKaryawan}');
		setAutoComplete('#karyawanEdit', '${suggestKaryawan}');

	});

	function getData(ids) {
		var data = {
			id : ids
		};
		$.getAjax('${dapatkanUrl}', data, function(pengguna) {
			clearEdit();
			$('#ids').val(ids);
			$('#namaEdit').val(pengguna.nama);
			$('#isAktifEdit').prop('checked', pengguna.isAktif);
			$('#isPertamaKaliEdit').prop('checked', pengguna.isPertamaKali);
			$('#countKesalahanEdit').val(pengguna.countKesalahan);
			$('#karyawanEdit').val(pengguna.karyawan.nama);
			if (pengguna.roles[0].nama) {
				$('#role1Edit').val(pengguna.roles[0].nama);
			}
			if (pengguna.roles[1].nama) {
				$('#role2Edit').val(pengguna.roles[1].nama);
			}
			if (pengguna.roles[2].nama) {
				$('#role3Edit').val(pengguna.roles[2].nama);
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

	function setDataContent(data) {
		data['nama'] = $('#nama').val();
		data['kataSandi'] = $('#password').val();
		data['isAktif'] = $('#isAktif').is(":checked");
		data['isPertamaKali'] = $('#isPertamaKali').is(":checked");
		data['countKesalahan'] = $('#countKesalahan').val();
		data['role1'] = $('#role1').val();
		data['role2'] = $('#role2').val();
		data['role3'] = $('#role3').val();
		data['karyawan'] = $('#karyawan').val();
		return data;
	}

	function setDataEdit(data) {
		data['nama'] = $('#namaEdit').val();
		data['kataSandi'] = $('#passwordEdit').val();
		data['isAktif'] = $('#isAktifEdit').is(":checked");
		data['isPertamaKali'] = $('#isPertamaKaliEdit').is(":checked");
		data['countKesalahan'] = $('#countKesalahanEdit').val();
		data['role1'] = $('#role1Edit').val();
		data['role2'] = $('#role2Edit').val();
		data['role3'] = $('#role3Edit').val();
		data['karyawan'] = $('#karyawanEdit').val();
		return data;
	}

	function clear() {
		$('#nama').val('');
		$('#passwordKonfirmasi').val('');
		$('#password').val('');
		$('#isAktif').prop('checked', false);
		$('#isPertamaKali').prop('checked', false);
		$('#countKesalahan').val('0');
		$('#role1').val('');
		$('#role2').val('');
		$('#role3').val('');
		$('#karyawan').val('');
	}

	function clearEdit() {
		$('#namaEdit').val('');
		$('#passwordKonfirmasiEdit').val('');
		$('#passwordEdit').val('');
		$('#isAktifEdit').prop('checked', false);
		$('#isPertamaKaliEdit').prop('checked', false);
		$('#countKesalahanEdit').val('0');
		$('#role1Edit').val('');
		$('#role2Edit').val('');
		$('#role3Edit').val('');
		$('#karyawanEdit').val('');
	}

	//		karyawan : {					
	//		remote: {
	//			url: "${isKaryawanAda}",
	//			type: "get",
	//			data: {
	//				nama: function(){
	//					return $("#tambahKaryawan").val();
	//				}
	//			}
	//		}
	//	}
	//},
</script>