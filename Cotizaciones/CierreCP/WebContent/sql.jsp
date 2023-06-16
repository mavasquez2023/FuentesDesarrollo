
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

function Ejecutar(){
	form1.action= path + "/SQL.do";
	form1.submit();
}


</script>
</head>
<body topmargin="0" leftmargin="0">

<form name="form1" method="post">	
<table>
<tr><td class="textos_formcolor" colspan=2>Conexión DS</td></tr>
<tr><td class="textos_formcolor">Clave:</td><td class="textos_formularios"><input type="password" name="clave" value="" /></td></tr>
<tr><td >&nbsp;</td></tr>
</table>
<table>
<tr><td class="textos_formcolor">Ingrese SQL:</td></tr>
<tr><td class="textos_formularios"><textarea name="sql" cols=90 rows=10 >
<%	Object result= request.getAttribute("resultado");
	if(result == null)
		out.println("--Ingrese sentencias SQL;");
%>
<c:forEach var="sqlin" items="${consulta}">
${sqlin}
</c:forEach>
</textarea></td></tr>
<tr><td align="center"><INPUT type="button" class="salir" value="Ejecutar" onClick="Ejecutar();">&nbsp; <INPUT type="reset" class="salir" value="Limpiar"></td></tr>
<tr><td >&nbsp;</td></tr>
<tr><td class="textos_formcolor">RESULTADO</td></tr>
<c:forEach var="sql" items="${resultado}">
<tr><td class="textos_formularios">
${sql}
</td><tr>
</c:forEach>
</table>
</form>
</body>
</html>


