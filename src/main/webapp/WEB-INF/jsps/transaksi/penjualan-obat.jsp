<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>

<c:url var="simpanUrl" value="/penjualan-obat/tambah" />
<c:url var="cariObatUrl" value="/penjualan-obat/cariobat" />
<c:url var="getObatUrl" value="/obat/nama" />
<c:url var="tambahObatUrl" value="/penjualan-obat/tambahTemp" />
<c:url var="hapusObatUrl" value="/penjualan-obat/hapusTemp" />
<c:url var="daftarTempUrl" value="/penjualan-obat/daftarTemp" />
<c:url var="beliUrl" value="/penjualan-obat/beli" />
<c:url var="tersediaUrl" value="/penjualan-obat/tersedia" />
<c:url var="cetakUrl" value="/penjualan-obat/cetak" />

<div class="row mt">
	<div class="col-md-12">
		<div class="form-panel">
			<form class="form formTambah" id="formObat" action="${tambahObatUrl}"
				method="POST">
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="lb-sm">Tanggal Beli</label> <input name="tanggal"
								type="text" id="tanggal" class="form-control datePicker"
								value="${tanggalHariIni}" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>


<script>
	
</script>