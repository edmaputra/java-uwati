<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="taglib.jsp"%>

<security:authorize access="hasAnyRole('ADMIN') and isAuthenticated()">
	<li class="dropdown"><a href="<spring:url value="/profil" />"
		title="Pengaturan"> <i class="fa fa-laptop"></i>
	</a></li>
</security:authorize>