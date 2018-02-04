<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<label>Obat Sudah Kadaluarsa</label>
						<h4 style="color: red;">${obatSudahKadaluarsa}</h4>
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<label>Obat Akan Kadaluarsa</label>
						<h4 style="color: red;">${obatAkanKadaluarsa}</h4>
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<label>Stok Obat Akan Habis</label>
						<h4 style="color: red;">${obatAkanHabis}</h4>
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<label>&nbsp;</label>
						<a href="<spring:url value="/notifikasi" />" class="btn btn-default" style="width: 100%;">Rincian</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<security:authorize
	access="hasAnyRole('ADMIN','APOTIK') and isAuthenticated()">
	<div class="row">
		<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 mb">
			<a href="<spring:url value="/penjualan-obat" />">
				<div class="jualObat pn">
					<i class="fa fa-shopping-cart fa-4x"></i>
					<h2>PENJUALAN</h2>
					<h4>OBAT</h4>
				</div>
			</a>
		</div>
</security:authorize>
<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 mb">
		<a href="<spring:url value="/pembelian-obat" />">
			<div class="beli-obat pn">
				<i class="fa fa-shopping-cart fa-4x"></i>
				<h2>PEMBELIAN</h2>
				<h4>OBAT</h4>
			</div>
		</a>
	</div>

	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 mb">
		<a href="<spring:url value="/retur" />">
			<div class="retur-obat pn">
				<i class="fa fa-shopping-cart fa-4x"></i>
				<h2>RETUR PEMBELIAN</h2>
				<h4>OBAT</h4>
			</div>
		</a>
	</div>
</div>
</security:authorize>

<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
	<div class="row">
		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 mb">
			<a href="<spring:url value="/laporan/penjualan" />">
				<div class="laporan pn">
					<i class="fa fa-wpforms fa-4x"></i>
					<h2>LAPORAN</h2>
					<h4>PENJUALAN</h4>
				</div>
			</a>
		</div>

		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 mb">
			<a href="<spring:url value="/laporan/pembelian" />">
				<div class="laporan pn">
					<i class="fa fa-wpforms fa-4x"></i>
					<h2>LAPORAN</h2>
					<h4>PEMBELIAN</h4>
				</div>
			</a>
		</div>

		<!-- 		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 mb"> -->
		<%-- 		<a href="<spring:url value="/obat-diagnosa" />"> --%>
		<!-- 			<div class="jualObat pn"> -->
		<!-- 				<i class="fa fa-copy fa-4x"></i> -->
		<!-- 				<h2>LAPORAN</h2> -->
		<!-- 				<h4>OBAT-DIAGNOSA</h4> -->
		<!-- 			</div> -->
		<!-- 		</a> -->
		<!-- 		</div> -->

		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 mb">
			<a href="<spring:url value="/laporan/pasien" />">
				<div class="laporan pn">
					<i class="fa fa-wpforms fa-4x"></i>
					<h2>LAPORAN</h2>
					<h4>PASIEN</h4>
				</div>
			</a>
		</div>

		<!-- 	<div class="col-md-4 col-sm-4 mb"> -->
		<%-- 		<a href="<spring:url value="#" />"> --%>
		<!-- 			<div class="jualObat pn"> -->
		<!-- 				<i class="fa fa-cloud fa-4x"></i> -->
		<!-- 				<h2>LAPORAN</h2> -->
		<!-- 				<h4>PEMBELIAN</h4> -->
		<!-- 			</div> -->
		<!-- 		</a> -->
		<!-- 	</div> -->
	</div>
</security:authorize>
