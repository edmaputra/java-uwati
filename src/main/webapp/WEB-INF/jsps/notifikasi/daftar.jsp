<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="daftarUrl" value="/notifikasi/daftar" />

<div class="showback">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<table class="table table-striped table-advance table-hover"
				id="tabel_obat_sudah_kadaluarsa">
			</table>
			<div id="nav_obat_sudah_kadaluarsa"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<table class="table table-striped table-advance table-hover"
				id="tabel_obat_akan_kadaluarsa">
			</table>
			<div id="nav_obat_akan_kadaluarsa"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<table class="table table-striped table-advance table-hover"
				id="tabel_obat_akan_habis">
			</table>
			<div id="nav_obat_akan_habis"></div>
		</div>
	</div>
</div>

<div>
	<%@ include file="../../layouts/gritter.jsp"%>
</div>


<script>
	function refresh(halaman, tabel, navigasi) {
		var data = {
			hal : halaman,
			tabel : tabel,
			navigasi : navigasi
		};
		$.getAjax('${daftarUrl}', data, function(result) {
			$(tabel).empty();
			$(tabel).append(result.tabel);
			$(navigasi).empty();
			$(navigasi).append(result.navigasiHalaman);
		}, null);
	}

	$(document).ready(function() {
		refresh(1, '#tabel_obat_sudah_kadaluarsa', '#nav_obat_sudah_kadaluarsa');
		refresh(1, '#tabel_obat_akan_kadaluarsa', '#nav_obat_akan_kadaluarsa');
		refresh(1, '#tabel_obat_akan_habis', '#nav_obat_akan_habis');
		
	});
</script>