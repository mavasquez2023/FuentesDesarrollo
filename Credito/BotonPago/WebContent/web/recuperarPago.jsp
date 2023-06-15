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
	<fieldset class="form-fieldset">
	<div class="contenedor">
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<h1>Recuperar Pago a SAP</h1>
			<br> <br>
			<div class="textofino">
				<b>Nota:</b>&nbsp;En esta opción se genera el comprobante de pago y se recupera a SAP siempre que se encuentre pagado en SPL.
				<html:errors />
			</div><br>
			<form action="regularizaPago.do?op=recupera" method="post" id="recuperarPagoForm">
			<div class="field li">
				<label>Ingrese Id de Pago en SPL:</label>
				<input class="inputCorto" type="text" name="idPago" id="idPago" maxlength="10">
			</div>
			<div class="field">
				<input class="boton" type="submit" value="Recuperar">
			</div>
			
			</form>
			<br> <br>
			<c:if test="${not empty tipo}">
				<div class="${tipo}">
					<strong>Mensaje:</strong>&nbsp; ${mensaje}
				</div>
			</c:if>
		</div>
		<br>
	</div>
	</fieldset>
</body>

<script type="text/javascript">

$(document).ready(function () {
        $("#recuperarPagoForm").validate({
            rules: {
                idPago: {required: true}
            }
        }); //fin jquery validate 
    }); //fin document ready

</script>
</html>