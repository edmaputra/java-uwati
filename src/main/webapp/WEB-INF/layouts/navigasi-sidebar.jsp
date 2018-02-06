<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<div id="sidebar" class="nav-collapse ">
	<ul class="sidebar-menu" id="nav-accordion">

		<!-- 		<p class="centered"> -->
		<!-- 			<a href="profile.html"><img src="#" class="img-circle" width="60"></a> -->
		<!-- 		</p> -->

<!-- 		<h5 class="centered">Apotik Nur Jannah</h5> -->

		<li class="sub-menu"><a href="<spring:url value="/" />"> <i
				class="fa fa-home"></i> <span>Beranda</span>
		</a></li>

		<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-cubes"></i><span>Master</span>
			</a>
				<ul class="sub">
					<li><a href="<spring:url value="/satuan" />">Satuan</a></li>
					<li><a href="<spring:url value="/kategori" />">Kategori</a></li>
					<li><a href="<spring:url value="/karyawan" />">Karyawan</a></li>
					<%-- 				<li><a href="<spring:url value="/pelanggan" />">Pelanggan</a></li>							 --%>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-medkit"></i><span>Obat</span>
			</a>
				<ul class="sub">
					<li><a href="<spring:url value="/obat" />">Obat</a></li>
					<li><a href="<spring:url value="/racikan" />">Racikan</a></li>
					<li><a href="<spring:url value="/tindakan" />">Tindakan</a></li>
					<li><a href="<spring:url value="/diagnosa" />">Diagnosa</a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-user"></i> <span>Pengguna</span>
			</a>
				<ul class="sub">
					<li><a href="<spring:url value="/daftarpengguna" />">Pengguna</a></li>
					<%--<li><a href="<spring:url value="/role" />">Role</a></li> --%>
				</ul></li>
		</security:authorize>

		<security:authorize
			access="hasAnyRole('ADMIN','MEDIS') and isAuthenticated()">
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-users"></i> <span>Pasien</span>
			</a>
				<ul class="sub">
					<li><a href="<spring:url value="/pasien" />">Pasien</a></li>
					<security:authorize
						access="hasAnyRole('ADMIN') and isAuthenticated()">
						<li><a href="<spring:url value="/kategori-pasien" />">Kategori
								Pasien</a></li>
					</security:authorize>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
			<li class="sub-menu"><a href="javascript:;"> <i
					class="fa fa-book"></i> <span>Laporan</span>
			</a>
				<ul class="sub">
					<li><a href="<spring:url value="/laporan/penjualan" />">Penjualan</a></li>
					<li><a href="<spring:url value="/laporan/pembelian" />">Pembelian</a></li>
					<li><a href="<spring:url value="/laporan/pasien" />">Pasien</a></li>
					<li><a href="<spring:url value="/obat-diagnosa" />">Obat &amp; Diagnosa</a></li>
					<li><a href="<spring:url value="/laporan/retur" />">Retur Obat</a></li>
					<li><a href="<spring:url value="/laporan/pembatalan" />">Pembatalan Transaksi</a></li>
				</ul></li>
		</security:authorize>

		<li class="sub-menu"><a href="javascript:;"> <i
				class="fa fa-shopping-cart"></i> <span>Transaksi</span>
		</a>
			<ul class="sub">
				<security:authorize
					access="hasAnyRole('ADMIN','APOTIK') and isAuthenticated()">
					<li><a href="<spring:url value="/penjualan-obat" />">Penjualan</a></li>
				</security:authorize>
				<security:authorize
					access="hasAnyRole('ADMIN','APOTIK') and isAuthenticated()">
					<li><a href="<spring:url value="/resep" />">Resep</a></li>
				</security:authorize>
				<security:authorize
					access="hasAnyRole('ADMIN') and isAuthenticated()">
					<li><a href="<spring:url value="/pembelian-obat" />">Pembelian</a></li>
				</security:authorize>
				<security:authorize
					access="hasAnyRole('ADMIN') and isAuthenticated()">
					<li><a href="<spring:url value="/utang-piutang" />">Utang &amp; Piutang</a></li>
				</security:authorize>				
				<security:authorize
					access="hasAnyRole('ADMIN') and isAuthenticated()">
					<li><a href="<spring:url value="/retur" />">Retur</a></li>
				</security:authorize>
			</ul></li>

	</ul>
	<!-- sidebar menu end-->
</div>
