
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>Integrador LDAP</title>
<!-- Le styles -->
<link href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="css/estilo.css" rel="stylesheet" />
<link href="css/redmond/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.corner.js"></script>
<script type="text/javascript" src="js/ldap.js"></script>
<script type="text/javascript" src="js/corev2.js"></script>
<style>
label {
	display: inline-block;
	width: 5em;
}

fieldset div {
	margin-bottom: 2em;
}

fieldset .help {
	display: inline-block;
}

.ui-tooltip {
	width: 210px;
}
</style>
<script>

		$(document).ready(function() {
			//cargarValores("empresa");
			
			$(".informacion").corner("6px");
			$(".informacion_error_login").corner("6px");

		});
				
		function login() {
			//validarRut();
			if($("#password").val()!=""){
				rut= $("#username").val();
				rut= rut.replace(/\./g, "");
				$("#username").val(rut);
				document.forms[0].submit();
			}else{
				marcarCampoError("j_password");
			}
		}

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
				document.forms[0].entrar.focus();
				login();
			}
			return;
		}
	</script>
</head>
<body>
<form id="form1" action="j_security_check" method="post">
	
	<div class="container">
	
		<div id="header">
			<div id="logo"></div>
			<div id="informacion" class="informacion"></div>
			<div id="informacion_error_login" class="informacion"></div>
		</div>

		<div id="div_login">
			<h1 align="center">Gestor de Claves</h1>
			<table class="tabla_formulario_login" cellspacing="2" align="center" >
				<tr class="tr_formulario">
					<td colspan=2><div id="observaciones" style="color: #ff0000;font-family: Arial,Verdana;font-size: 10"></div></td>
				</tr>
				
				<tr class="tr_formulario">
					<td class="titulo_campo_login">Rut Usuario</td>
					<td class="data_campo_login"><input type="text" 
							id="username" name="j_username" maxlength="12" placeholder="Ej: 12312312-3"
							onchange="validarRut();" 
							onKeyPress="keyRut();" 
							onKeyUp="formateaRut(this);" /></td>
				</tr>
				<tr class="tr_formulario">
					<td class="titulo_campo_login">Clave</td>
					<td class="data_campo_login"><input type="password"
						id="password" name="j_password" maxlength="10" onKeyPress="validarEnter();" />
					</td>
				</tr>
				<tr>
				<td> &nbsp;</td>
				
				<tr class="tr_formulario">
					<td colspan="4" align="center">
						<button type="button" class="boton_login" name="entrar"
							onclick="login();">Ingresar
						</button>
					</td>
				</tr>
			</table>
		</div>

		<script type="text/javascript">
			jQuery(document).ready(function($) {
				// $('#tabs').tab();
			});
		</script>

		<div id="footer" class="texto_footer">
			Una innovaci&oacute;n tecnol&oacute;gica de Caja De
			Compensaci&oacute;n
			<div id="logo_araucana"></div>
		</div>
	</div>
	<!-- container -->


	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<div id="cargando">


		<div id="imagen">
			<img src="img/cargando_big.gif" width="100px"></img>
		</div>
		<div id="texto_cargando"></div>

	</div>
</form>

</body>
</html>
