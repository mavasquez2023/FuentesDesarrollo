<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
	<head>
		<title>LA ARAUCANA C.C.A.F.</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
		<c:if test="${origen==null }">
		<!-- Google tag (gtag.js) --> 
		<script async src=https://www.googletagmanager.com/gtag/js?id=G-X15NJZQVF2></script> 
		<script> window.dataLayer = window.dataLayer || []; function gtag(){dataLayer.push(arguments);} gtag('js', new Date()); gtag('config', 'G-X15NJZQVF2'); </script>
		</c:if>
	</head>
	<body>
		<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
		<fieldset class="form-fieldset">
			<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
			<div class="contenedor">
				<div class="menu">
					<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
				</div>
				<div class="opcionmenu">
					<div class="texto">
						<b>LA ARAUCANA C.C.A.F. </b>
					</div>
					<br> <br> <br>
					<div class="texto">Bienvenido(a) al portal de pago para deudores afiliados y no afiliados.</div>
					<br> <br> <br>
				</div>
			</div>
		</fieldset>
	</body>
</html>
