<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="taglib.jsp"%>

<ul class="nav pull-right top-menu">
	<li><a class="btn btn-default dropdown-toggle"
		data-toggle="dropdown" aria-haspopup="true" area-expanded="false"
		href="#"><i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i></a>
		<ul class="dropdown-menu">
			<li><a href="<spring:url value="/pengguna" />"><i class="fa fa-user fa-fw"></i>Pengguna</a></li>
			<li class="divider" role="separator"></li>
			<li><a href="<spring:url value="/keluar" />"><i class="fa fa-sign-out fa-fw"></i>Keluar</a></li>
		</ul>
	</li>	
	
</ul>

