<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<spring:url value="/static/css/bootstrap.min.css" var="bootstrapMinCss" />
<spring:url value="/static/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
<spring:url value="/static/css/uwati.css" var="uwatiCss" />


<spring:url value="/static/js/jquery-2.1.1.min.js" var="jQuery2" />
<spring:url value="/static/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/static/js/uwati.js" var="uwatiJs" />

<link href="${bootstrapMinCss}" rel="stylesheet" />
<link href="${bootstrapThemeMinCss}" rel="stylesheet" />
<link href="${uwatiCss}" rel="stylesheet" />

<script type="text/javascript" src="${jQuery2}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${uwatiJs}"></script>

<title><tiles:getAsString name="title" /></title>
</head>
<body>	
	<div class="container" style="min-height: 500px">
		<div class="content">
			<div class="container-fluid">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	
</body>

</html>