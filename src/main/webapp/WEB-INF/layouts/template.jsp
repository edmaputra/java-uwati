<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%><%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%><%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<spring:url value="/static/css/bootstrap.min.css" var="bootstrapMinCss" /><spring:url value="/static/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" /><spring:url value="/static/css/uwati.css" var="uwatiCss" /> <spring:url value="/static/css/style.css" var="styleCss" />
<spring:url value="/static/css/style-responsive.css" var="styleResponsiveCss" />
<spring:url value="/static/css/jquery.gritter.css" var="gritterCss" />
<spring:url value="/static/css/bootstrap-datepicker3.min.css" var="bootstrapDatePickerCss" />
<spring:url value="/static/css/jquery.autocomplete.styles.css" var="autoCompleteThemesCss" />
<spring:url value="/static/css/font-awesome.min.css" var="fontawesomeCss" />

<spring:url
	value="/static/baru/bootstrap-modal-patch/css/bootstrap-modal-bs3patch.css"
	var="bootstrap-modal-3-patch" />
<spring:url
	value="/static/baru/bootstrap-modal-patch/css/bootstrap-modal.css"
	var="bootstrap-modal" />

<spring:url value="/static/js/jquery-2.1.1.min.js" var="jQuery2" />
<spring:url value="/static/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/static/js/uwati.js" var="uwatiJs" />
<spring:url value="/static/js/jquery-ui-1.9.2.custom.min.js"
	var="jqueryUiCustom" />
<spring:url value="/static/js/jquery.scrollTo.min.js" var="scrollTo" />
<spring:url value="/static/js/jquery.nicescroll.js" var="niceScroll" />
<spring:url value="/static/js/jquery.dcjqaccordion.2.7.js"
	var="dcjAccordion" />
<spring:url value="/static/js/jquery.ui.touch-punch.min.js"
	var="touchPunch" />
<spring:url value="/static/js/common-scripts.js" var="common" />
<spring:url value="/static/js/jquery.gritter.js" var="jqueryGritter" />
<spring:url value="/static/js/gritter-conf.js" var="jqueryGritterConf" />
<spring:url value="/static/js/jquery.validate.min.js"
	var="jqueryValidate" />
<spring:url value="/static/js/bootstrap-datepicker.min.js"
	var="bootstrapDatePicker" />
<spring:url value="/static/js/bootstrap-datepicker.id.min.js"
	var="indonesiaLocaleDatePicker" />
<spring:url value="/static/js/date.format.js" var="dateFormatJs" />
<spring:url value="/static/js/jquery.autocomplete.min.js"
	var="jqueryautocompleteJs" />
<spring:url value="/static/js/jquery.maskMoney.min.js" var="maskMoney" />
<link href="${bootstrapMinCss}" rel="stylesheet" />
<link href="${bootstrapThemeMinCss}" rel="stylesheet" />
<link href="${fontawesomeCss}" rel="stylesheet" />
<link href="${uwatiCss}" rel="stylesheet" />
<link href="${styleCss}" rel="stylesheet" />
<link href="${styleResponsiveCss}" rel="stylesheet" />
<link href="${gritterCss}" rel="stylesheet" />
<link href="${bootstrapDatePickerCss}" rel="stylesheet" />
<link href="${autoCompleteThemesCss}" rel="stylesheet" />
<link
	href="/uwati/static/baru/bootstrap-modal-patch/css/bootstrap-modal-bs3patch.css"
	rel="stylesheet" />
<link
	href="/uwati/static/baru/bootstrap-modal-patch/css/bootstrap-modal.css"
	rel="stylesheet" />
<script type="text/javascript" src="${jQuery2}"></script>
<script type="text/javascript" src="${uwatiJs}"></script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<section id="container">
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right"
					data-original-title="Toggle Navigation"></div>
			</div>
			<a href="<spring:url value="/" />" class="logo"><b>UWATI</b></a>
			<div class="nav notify-row" id="top_menu">
				<ul class="nav top-menu">
					<tiles:insertAttribute name="notifikasi" />
					<tiles:insertAttribute name="pesan" />
				</ul>
			</div>
			<div class="top-menu">
				<tiles:insertAttribute name="navigasi-top-menu" />
			</div>
		</header>
		<aside>
			<tiles:insertAttribute name="navigasi-sidebar" />
		</aside>
		<section id="main-content">
			<section class="wrapper">
				<h3>
					<i class="fa fa-angle-right"></i>
					<tiles:getAsString name="page_title" />
				</h3>
				<tiles:insertAttribute name="body" />
			</section>
		</section>
		<footer class="site-footer">
			<tiles:insertAttribute name="footer" />
		</footer>
	</section>
	<div class="modal fade" id="pesan-modal" tabindex="-1"
		style="display: none;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Pesan</h4>
		</div>
		<div class="modal-body">
			<div id="pesan" style="text-align: center;"></div>
		</div>
		<div class="modal-footer"></div>
	</div>
	<script type="text/javascript" src="${bootstrapJs}"></script>
	<script type="text/javascript" src="${jqueryUiCustom}"></script>
	<script src="${touchPunch}"></script>
	<script class="include" type="text/javascript" src="${dcjAccordion}"></script>
	<script src="${scrollTo}"></script>
	<script type="text/javascript" src="${niceScroll}"></script>
	<script src="${common}"></script>
	<script type="text/javascript" src="${jqueryGritter}"></script>
	<script type="text/javascript" src="${jqueryGritterConf}"></script>
	<script type="text/javascript" src="${jqueryValidate}"></script>
	<script type="text/javascript" src="${bootstrapDatePicker}"></script>
	<script type="text/javascript" src="${indonesiaLocaleDatePicker}"></script>
	<script type="text/javascript" src="${dateFormatJs}"></script>
	<script type="text/javascript" src="${jqueryautocompleteJs}"></script>
	<script type="text/javascript" src="${maskMoney}"></script>
	<spring:url
		value="/static/baru/bootstrap-modal-patch/js/bootstrap-modal.js"
		var="bootstrap-modal-js" />
	<spring:url
		value="/static/baru/bootstrap-modal-patch/js/bootstrap-modalmanager.js"
		var="bootstrap-modalmanager-js" />
	<script type="text/javascript"
		src="/uwati/static/baru/bootstrap-modal-patch/js/bootstrap-modal.js"></script>
	<script type="text/javascript"
		src="/uwati/static/baru/bootstrap-modal-patch/js/bootstrap-modalmanager.js"></script>
</body>
</html>