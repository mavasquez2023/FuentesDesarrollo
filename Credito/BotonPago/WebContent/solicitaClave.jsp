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
			Por favor, ingrese su Rut y su correo electrónico para generar una nueva clave. <br>La clave será enviada al correo
			electrónico indicado.
		</p>
		<br>
		<form action="solicitaClave.do" id="solicitarClaveForm" method="post">
			<div id="solicitar-clave-form">
				<div class="field li">
					<label class="span2-8">Rut:</label>
					<input type="text" class="inputCorto" name="rutForm" id="rutForm" size="30" maxlength="12">
					<input type="hidden" name="rut" id="rut">
					<html:errors property="rut" />
				</div>
				<div class="field li">
					<label class="span2-8">Correo electrónico:</label>
					<input class="inputCorto"  type="text" name="email" id="email" size="30" maxlength="50">
					<html:errors property="email" />
				</div>
				<div class="field li">
					<label class="span2-8">Repetir correo electrónico:</label>
					<input class="inputCorto"  type="text" name="email2" id="email2" size="30" maxlength="50">
					<html:errors property="email2" />
				</div>
				<div class="right">
					<input type="button" class="boton" name="volver" value="Volver" onclick="window.location.href='<%=request.getContextPath()%>/login.jsp'">
					<input type="submit" id="submiter" class="boton" value="Enviar">
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("#solicitarClaveForm").validate({
            rules: {
            	rutForm: {required: true, rut:true},
                email: {required: true, email: true},
        		email2: {equalTo:"#email"}
            }
        }); //fin jquery validate

        $( "#email2" ).rules( "add", {
            messages: {
            	equalTo: "* Los correos no coinciden"
        	  }
        });
        
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
