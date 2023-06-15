<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="comun/tld.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include flush="true" page="comun/inports.jsp"></jsp:include>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>Pago de Crédito en Línea - La Araucana</title>
<script>				

		function validarRut() {
			if ($("#username").val().length == 0)
				return;
			var estructuraRut = /^0*(\d{1,3}(\.?\d{3})*)\-([\dkK])$/;
			valor = $("#username").val();
			if (valor.match(estructuraRut) == null) {
				mostrarInfoErrorLogin("El Rut ingresado no es v&aacute;lido.");
				marcarCampoError("username");
				return;
			} else if ($("#username").val().length < 6) {
				mostrarInfoErrorLogin("El largo del Rut no es v&aacute;lido");
				marcarCampoError("username");
				return;
			}

			var tmp = $("#username").val().split('-');
			var digv = tmp[1];
			var rut = tmp[0];
			if (digv == 'K') {
				digv = 'k';
			}
			var digesto = dv(rut);
			if (digesto == digv) {
				return true;
			} else {
				mostrarInfoErrorLogin("El Rut ingresado no es v&aacute;lido.");
				marcarCampoError("username");
				return;

			}
		}

		function dv(T) {
			var M = 0, S = 1;
			for (; T; T = Math.floor(T / 10)) {
				S = (S + T % 10 * (9 - M++ % 6)) % 11;
			}
			return S ? S - 1 : 'k';
		}
		
		function validarEnter(){
			if(validaEnter() == false){
				document.forms[0].filtro.focus();
				login();
			}
			return;
		}
	</script>
</head>
<body class="corp">
<jsp:include flush="true" page="comun/header.jsp"></jsp:include>
<fieldset class="form-fieldset">
<div id="content">
  <div class="grid_12">
  <br/><br/>
    <h1>PAGO DE CRÉDITO EN LÍNEA</h1>
    <div class="mensaje bg_gris">Por favor ingrese su <strong>RUT</strong> y <strong>Clave</strong> de acceso</div>
    <div class="grid_4 alpha borde4" style="height:293px;">
      <div class="pad_caja"><br />
      <form action="j_security_check" method="post" name="loginForm" id="loginForm">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th valign="top"><label for="username">RUT:</label></th>
            <td valign="top"><input type="text" name="username" id="uname" maxlength="12" placeholder="Ej: 12312312-3" onkeypress="keyRut()" /> <input type="hidden" name="j_username" id="name" />
            </td>
              <br />
            
          </tr>
          <tr>
            <th valign="top"><label for="pswd">Clave:</label></th>
            <td valign="top"><input type="password"
						id="pswd" name="j_password" maxlength="10" onkeypress="validarEnter();" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input  class="boton" name="action" id="submiter" 
                        type="submit" value="Ingresar"/></td>
          </tr>
          <tr><td colspan=2><div id="mensajesError"></div></td></tr>
        </table>
        </form>
        <br> <br>
		<div class="textofino">
			¿Olvidó su clave? La puede recuperar &nbsp;<a href="<c:url value='<%=cl.laaraucana.botonpago.web.utils.Configuraciones.getConfig("url.recuperar.clave.persona")%>' />">aquí</a>
		</div>
		<div class="textofino">
			Si no tiene clave la puede solicitar &nbsp;<a href="<c:url value='<%=cl.laaraucana.botonpago.web.utils.Configuraciones.getConfig("url.clave.persona")%>' />">aquí</a>
		</div>
</div>
</div>
    <div class="grid_8 omega"> <img src="img/imagen_login.jpg" width="646" height="295" /></div>
</div>

</div>
</fieldset>
<script type="text/javascript">
	
	$(document).ready(function() {
	
		if (window.opener) {
			window.opener.location.reload();
			window.close();
		}
		$("#uname").focus();
		
		$("#loginForm").validate({
			rules : {
				username : {
					required : true,
					rut : true
				},
				j_password : {
					required : true
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo("#mensajesError");
			},
			messages : {
				"username" : {
					required : "* El Rut es requerido."
				},
				"j_password" : {
					required : "* La contraseña es requerida."
				},
			}
		}); //fin jquery validate

		$("#uname").Rut({
			format_on : 'keyup'
		});

		$("#submiter").click(function() {
			var value = $("#uname").val();
			value = value.split(".").join("");
			$("#name").val(value);
			
		});
	}); //fin document ready
</script>

</body>
</html>
