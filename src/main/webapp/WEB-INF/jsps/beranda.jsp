<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>


<security:authorize access="hasAnyRole('ADMIN','APOTIK') and isAuthenticated()">
<div class="row">
	<div class="col-md-4 col-sm-4 mb">
		<a href="<spring:url value="/penjualan-obat" />">
			<div class="jualObat pn">
				<i class="fa fa-shopping-cart fa-4x"></i>
				<h2>PENJUALAN</h2>
				<h4>OBAT</h4>
			</div>
		</a>
	</div>
	
	<div class="col-md-4 col-sm-4 mb">
		<a href="<spring:url value="/pembelian-obat" />">
			<div class="jualObat pn">
				<i class="fa fa-shopping-cart fa-4x"></i>
				<h2>PEMBELIAN</h2>
				<h4>OBAT</h4>
			</div>
		</a>
	</div>
	
<!-- 	<div class="col-md-4 col-sm-4 mb"> -->
<%-- 		<a href="<spring:url value="#" />"> --%>
<!-- 			<div class="jualObat pn"> -->
<!-- 				<i class="fa fa-cloud fa-4x"></i> -->
<!-- 				<h2>RETUR PEMBELIAN</h2> -->
<!-- 				<h4>OBAT</h4> -->
<!-- 			</div> -->
<!-- 		</a> -->
<!-- 	</div> -->
</div>
</security:authorize>

<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
<div class="row">
<!-- 	<div class="col-md-4 col-sm-4 mb"> -->
<%-- 		<a href="<spring:url value="#" />"> --%>
<!-- 			<div class="jualObat pn"> -->
<!-- 				<i class="fa fa-cloud fa-4x"></i> -->
<!-- 				<h2>LAPORAN</h2> -->
<!-- 				<h4>PENJUALAN</h4> -->
<!-- 			</div> -->
<!-- 		</a> -->
<!-- 	</div> -->
	
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
