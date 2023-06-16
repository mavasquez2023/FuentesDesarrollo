<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<body>
	<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
	<div class="contenedor">
		<div style="margin:30px ; width: 90%;">
			<div class="texto">
				<b>LA ARAUCANA C.C.A.F. </b>
			</div>
			<br> <br> <br>
			<div class="${tipo}"><b>${titulo}:</b>&nbsp;${mensaje}<html:errors/></div>
			<br>
			<div style=" text-align: right;">
			 <input  class="boton" name="button" type="button" onclick="window.close();" value="Cerrar esta ventana" /> 
			</div>
			<br> <br>
		</div>
	</div>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
</body>
</html>