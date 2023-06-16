
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<link href="theme/Interna_Araucana.css" rel="stylesheet" type="text/css" />

<title>La Araucana C.C.A.F. - Proceso de Cierre Cotizacion</title>
<script language="JavaScript 1.2" type="text/javascript">
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";

function Redirect(action){
	//window.location= "http://" + serverport + action;
	window.location= path + action;
}

</script>

</head>
<body topmargin="0" leftmargin="0">
	<table width="520 border="0"  align="left" cellpadding="1" cellspacing="1">
		
		<tr height="200" >
			<td align="center" class="tit-13">Se ha producido un error en ${accion}, favor vuelva a intentar o comuníquese a Sistemas.
			</td>
		</tr>
		<tr>
			<td align="center"><input class="btn_mini" type="button" name="Cerrar" value="Volver" onclick="Redirect('/GenerarPropuesta.do');"/></td>
		</tr>
	</table>
</body>
</html>


