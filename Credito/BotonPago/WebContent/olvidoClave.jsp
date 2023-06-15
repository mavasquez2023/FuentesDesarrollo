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
	<br>
	<h1>Solicitud de clave</h1>
	<br>
	<br>
	<div class="centraTabla">
		<p class="texto">
			Por favor, ingrese su Rut para generar una nueva clave. <br>La clave será enviada al correo electrónico.
		</p>
		<br>
		<form action="olvidoClave.do" id="olvidoClaveForm" method="post">
			<div id="solicitar-clave-form">
				<div class="field li">
					<label class="span2-8">Rut:</label>
					<input type="text" class="inputCorto" name="rutForm" id="rutForm" size="30" maxlength="12">
					<input type="hidden" name="rut" id="rut">
					<html:errors property="rut" />
				</div>
				<div class="right">
					<input type="button" class="boton" name="volver" value="Volver" onclick="window.location.href='<%=request.getContextPath()%>/login.jsp'">
					<input type="submit" id="submiter" class="boton" value="Enviar">
				</div>
			</div>
		</form>
	</div>
	<br>
	<br>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("#olvidoClaveForm").validate({
            rules: {
            	rutForm: {required: true, rut:true}
            }
        }); //fin jquery validate
        
		$("#rutForm").Rut({
			format_on : 'keyup'
		});
	
		$("#submiter").click(function () {
	        var value = $("#rutForm").val();
			value = value.split(".").join("");
	        $("#rut").val(value);
	    });
    }); //fin document ready
</script>
</html>
