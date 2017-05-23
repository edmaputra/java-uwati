<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="taglib.jsp"%>

<ul class="nav pull-right top-menu">
	<li><a class="pengguna" href="<spring:url value="/pengguna" />">Pengguna</a></li>
	<li><a class="logout" href="<spring:url value="/keluar" />">Keluar</a></li>
</ul>