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
			<h1>Crear usuario</h1>
			<br> <br> 
			<div class="centraTabla">
				<c:if test="${not empty tipo}">
					<div class="${tipo}">
						<strong>Mensaje:</strong>&nbsp;${mensaje}
					</div>
				</c:if>
			</div>
			<div class="textofino">
				<b>Nota:</b>&nbsp;En esta opción usted puede ingresar un nuevo usuario para la aplicación, los datos del usuario se guardan en el directorio LDAP.
				<html:errors />
			</div>
			<br>
				<form id="formulario" action="crearUser.do" method="post" class="centrar">
				<fieldset class="form-fieldset">
					<div class="padding-m">
					
						<h2>Ingrese los datos del usuario</h2>
	                    <div class="hr"></div>
	                    
	                    <div class="field li">
	                        <label class="span2-8">Rut:</label>
	                        <input class="inputCorto" type="text" maxlength="13" id="id"name="id">
	                        <html:errors property="id" />
	                    </div>
	                    
	                    <div class="field li">
	                        <label class="span2-8">Nombre:</label>
	                        <input class="inputCorto" type="text" name="nombre" maxlength="20">
	                        <html:errors property="nombre" />
	                    </div>
	                    
	                    <div class="field li">
	                        <label class="span2-8">Apellido paterno:</label>
	                        <input class="inputCorto" type="text" name="apellidoPaterno" maxlength="15">
	                        <html:errors property="apellidoPaterno" />
	                    </div>
	                    
	                    <div class="field li">
	                        <label class="span2-8">Apellido materno:</label>
	                        <input class="inputCorto" type="text" name="apellidoMaterno" maxlength="15">
	                        <html:errors property="apellidoMaterno" />
	                    </div>
	                    
	                    
	                    <div class="field li">
	                        <label class="span2-8">Email:</label>
	                        <input class="inputCorto" type="text" name="email" maxlength="50">
	                        <html:errors property="email" />
	                    </div>
	                    
	                    <div class="field li">
	                        <label class="span2-8">Fono:</label>
	                        <input class="inputCorto" type="text" name="fono" id="fono" maxlength="10">
	                        <html:errors property="fono" />
	                    </div>
	                    
	                    <div class="field li">
	                        <label class="span2-8">Sexo:</label>
	                        <select name="sexo" class="inputCorto">
								<option value="">Seleccione sexo</option>
								<option value="F">Femenino</option>
								<option value="M">Masculino</option>
							</select>
	                        <html:errors property="sexo" />
	                    </div>
	                    
	                    <div class="field li">
	                        <label class="span2-8">Perfil:</label>
	                        <select multiple="multiple" name="perfil" class="inputCorto">
								<option value="admin">Administrador</option>
								<option value="deudor">Deudor</option>
								<option value="ejecutivo">Ejecutivo</option>
							</select>
	                        <html:errors property="perfil" />
	                    </div>
	                    <br />
	                    <div class="right">
                        	<input id="boton" class="boton" type="submit" name="ingresar" value="Ingresar">
                    	</div>
	                 </div>
				 </fieldset>
				</form>
			
			<div align="right"></div>
		</div>
		<br>
	</div>
		<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
</body>

<script type="text/javascript">
    $(document).ready(function () {
        $("#formulario").validate({
            rules: {
                id: {required: true, rut:true},
                nombre: "required",
                apellidoPaterno: "required",
                apellidoMaterno: "required",
                email: {required: true, email: true},
                fono: {required: true},
                sexo: "required",
                perfil: "required"
            }
        }); //fin jquery validate
        $("#id").Rut({
			format_on : 'keyup'
		}); 
		$("#id").Rut({
			format_on : 'load'
		});
        jQuery(function ($) {
	         $("#fono").autoNumeric("init", {
	             aSep: '',
	             aDec: ',',
	             mDec: '0',
	             vMax: '9999999999'
	         });
        });
    }); //fin document ready
</script>

</html>