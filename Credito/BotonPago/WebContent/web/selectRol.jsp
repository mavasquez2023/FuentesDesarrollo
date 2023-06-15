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
<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
<body>
<fieldset class="form-fieldset">
	<div align="center">
		<br>
		<h1>Ingreso al sistema</h1>
		<br> <br>
		<p class="texto">Seleccione el perfil con el que desea ingresar.</p>
		<form id="selectRol" action="/BotonPago/web/selectRol.do?parameter=selecciona" method="post">
			<div class="centraTabla">
				<table class="formV">
					<tbody>
						<c:forEach var="r" items="${roles}">
							<tr>
								<td><label>${r.label}</label>
								</td>
								<td><input id="rol" type="radio" name="rol" value="${r.rol}" >
								</td>
							</tr>
						</c:forEach>
						<tr>
								<td colspan="2"><label for="rol" class="error">
								<html:errors property="rol" />
								</label></td>
								
							</tr>
							<tr>
								<td></td>
								<td> <input class="boton" type="submit" value="Continuar">
								</td>
							</tr>
					</tbody>
				</table>
			</div>
			
		</form>
	</div>
	<br>
	<br>
	</fieldset>
</body>
<script type="text/javascript"> 
	$(document).ready(function () {
	        $("#selectRol").validate({
	        	errorLabelContainer: "#errorPagoMinino",
	            rules: {
	            	rol: {required: true}
	            },
	            messages: {
	            	rol: {
	                  required: "* Debe seleccionar un rol.",
	                 }
	              }
	        }); //fin jquery validate
	    }); //fin document ready
</script>
</html>
