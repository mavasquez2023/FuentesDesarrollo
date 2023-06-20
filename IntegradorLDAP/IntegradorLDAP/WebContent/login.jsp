<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>Gestor de Claves - La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/ldap.js"></script>
<script type="text/javascript" src="js/corev2.js"></script>
<script>				
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
				document.forms[0].filtro.focus();
				login();
			}
			return;
		}
	</script>
</head>
<body class="corp">


<div id="content" class="container_12">
  <div class="grid_12">
  <img src="images/cabecera_claves.jpg" /> <br/><br/>
    <h1>Gestor de Claves</h1>
    <div class="mensaje bg_gris">Por favor ingrese su <strong>RUT</strong> y <strong>Clave</strong> de acceso</div>
    <div class="grid_4 alpha borde4" style="height:293px;">
      <div class="pad_caja"><br />
      <form action="j_security_check" method="post" name="frmlogin">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th valign="top">RUT</th>
            <td valign="top"><input type="text" 
							id="username" name="j_username" maxlength="12" placeholder="Ej: 12312312-3"
							onchange="validarRut();" 
							onKeyPress="keyRut();" 
							onKeyUp="formateaRut(this);" />
              <br />
            
          </tr>
          <tr>
            <th valign="top">Clave</th>
            <td valign="top"><input type="password"
						id="password" name="j_password" maxlength="10" onkeypress="validarEnter();" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" 
                        class="boton" id="filtro"  
                        type="submit" value="Ingresar" name="filtro" onclick="login();"/></td>
          </tr>
        </table>
        </form>
</div>
</div>
    <div class="grid_8 omega"> <img src="images/imagen_login.jpg" width="646" height="295" /></div>
</div>
</div>



<script>
	document.frmlogin.j_username.focus();
</script>

</body>
</html>
