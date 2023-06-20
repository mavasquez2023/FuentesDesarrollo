<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<!-- Le styles -->
<link href="dist/css/bootstrap.css" rel="stylesheet">
<link href="css/estilo.css" rel="stylesheet">

<link href="css/redmond/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.corner.js"></script>
<script type="text/javascript" src="js/ie.js"></script>
<script type="text/javascript" src="js/comun.js"></script>
<script type="text/javascript" src="js/cargar_datos.js"></script>
<script type="text/javascript" src="js/ldap.js"></script>
<script type="text/javascript" src="js/corev2.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
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
</head>
<body> 
<form  id="fupload" action="UploadServlet" name="fupload" method="post" enctype="multipart/form-data">
		<table align="center" class="tabla_formulario" cellspacing="2">
		<tr class="tr_formulario"><td class="titulo_campo" align="center" colspan="2">Empresas Autorizadas</td><td class="titulo_campo_check" align="center">Acción</td></tr>

					<c:forEach var="empresa" varStatus="vs" items="${usuarios_registrados}">
						<tr class="tr_formulario"><td class="data_campo">${empresa.key}</td><td class="data_campo">${empresa.value}</td><td class="data_campo_check"><a href="#" title="Eliminar Empresa" onclick="addEmpresaRolUsu('${empresa.key}','E');"><span class="glyphicon glyphicon-remove-circle"></span></td></tr>
					</c:forEach>
						<tr class="tr_formulario"><td class="data_campo"><input type="text" name="newempuser" id="newempuser" maxlength="13" onKeyPress="keyRut();" onKeyUp="formateaRut(this)" /></td><td class="data_campo">&nbsp;</td>
						<td class="data_campo_check"><a href="#" title="Agregar Empresa" onclick="addEmpresaRolUsu('', 'N');"><span class="glyphicon glyphicon-ok-circle"></span></a>
						&nbsp;&nbsp;<a href="#" title="Subir Lista" onclick="cargarArchivoU();"><span class="glyphicon glyphicon-list-alt"></span></a></td></tr>
						<tr class="tr_formulario"><td colspan="2" class="data_campo"><div id="upfile_u" style="visibility: hidden"><input type="file" name="listemp_u" id="listemp_u" value="" />&nbsp;<a href="#" onclick="ayuda();"><img src="images/help.jpg" title="Formato archivo"></a></div></td>
						<td class="data_campo_check"><div id="upfile_u2" style="visibility: hidden"><input type="submit" name="Subir" value="Subir" onclick="submitFileU();" /></div></td></tr>
						</table>
		<input type="hidden" name="userid" id="userid" value="" />
		<input type="hidden" name="rutemp" id="rutemp" value="" />
		<input type="hidden" name="estado" id="estado" value="" />
</form>
<script>
	$(document).ready(function()
	{
 		$('#fupload').submit(function(e) {
 			e.preventDefault();
 			uploadFile('fupload');
    	});
    	$('#fupload_rol').submit(function(e) {
 			e.preventDefault();
 			uploadFile('fupload_rol');
    	});
    	
 
	});
</script>
</body>
</html>