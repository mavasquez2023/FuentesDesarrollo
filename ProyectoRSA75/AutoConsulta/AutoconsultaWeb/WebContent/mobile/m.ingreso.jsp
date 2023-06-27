<%
session.invalidate();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<title>laaraucana.cl</title> 
<meta content='width=device-width; initial-scale=1.0' name='viewport'>
<link rel="shortcut icon" href="http://www.laaraucana.cl/favicon.ico">
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<script>
function unFormat() {
var sRut = document.form1.j_username.value;
    sRut = sRut.replace("-","").replace(".","").replace(",","");
    if (sRut.length > 2) {
       sRut = sRut.substr( 0 , sRut.length-1) + "-" + sRut.charAt(sRut.length-1);
    }
    document.form1.j_username.value = sRut;
}
</script>
</head>
<body>
<div class="header">
  <div class="logo"><a href="http://www.laaraucana.cl"><img src="img/logo_araucana_ss.gif" alt="laaraucana.cl" width="300" height="50"></a></div>
  <h1>Auto Consulta</h1>
</div>
<div class="content">
  <h2>Bienvenido</h2>
  <p>
     Por favor ingrese su rut y contraseña
  </p>

     <form id="form1" name="form1" method="post" action="j_security_check">
	    <label>Rut :</label><input value="" id="j_username" size="15" name="j_username" type="text" onblur="unFormat()"> 
	    <br/>Ejemplo: 10100200-2<br/><br/>

	    <label>Clave :</label><input value="" id="j_password" size="15" name="j_password" type="password"> 
	    <br/>
	    <br/><input name="enviar" name="enviar" type="submit"> 
     </form>
</div>
<div class="footer"><a href="http://www.laaraucana.cl">La Araucana.cl</a></div>
</body>
</html>
