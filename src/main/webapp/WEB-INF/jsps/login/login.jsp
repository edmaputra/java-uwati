<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../layouts/taglib.jsp"%>
<c:url var="loginUrl" value="/login" />

<div class="halamanlogin">
	<div class="judul" id="judul">
		<h1>${apotek.nama}</h1>
		<span>${apotek.alamat} - ${apotek.telepon}</span>
	</div>

	<div class="loginform-modul">
		<div class="loginform">
			<c:if test="${param.error == true}">
				<div id="error">
					Gagal
				</div>
			</c:if>
			<form name="f" role="form" action="${loginUrl}" method="post">
				<input type="text" placeholder="Username" name="username"
					id="username" /> <input type="password" placeholder="Password"
					name="password" id="passowrd" /> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
					class="tombol" type="submit" value="Masuk" onclick="validate()">
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	function validate() {
		if (document.f.username.value == "" && document.f.password.value == "") {
			alert("Username and password are required");
			document.f.username.focus();
			return false;
		}
		if (document.f.username.value == "") {
			alert("Username is required");
			document.f.username.focus();
			return false;
		}
		if (document.f.password.value == "") {
			alert("Password is required");
			document.f.password.focus();
			return false;
		}
	}
</script>