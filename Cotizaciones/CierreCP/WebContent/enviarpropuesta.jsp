
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

<title>Previpass - Enviar Propuesta</title>

<script language="JavaScript 1.2" type="text/javascript">
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";

function EnviarPropuesta(){
	periodo= <%=request.getParameter("periodo")%>;
	cierre= <%=request.getParameter("cierre")%>;
	if(form1.deposito[0].checked){
		deposito= form1.deposito[0].value;
	}else{
		deposito= form1.deposito[1].value;
	}
	filename= "Propuesta_" + deposito + "_periodo" + periodo + "_cierre" + cierre + ".xls";
	form1.action= path + "/EnviarPropuesta.do?filename=" + filename + "&periodo=" + periodo + "&cierre=" + cierre;
	form1.submit();
}

</script>
</head>
<body topmargin="0" leftmargin="0">
<form name="form1" method="post">
	<table border="0"  align="center" cellpadding="1" cellspacing="1">
		<tr height="40" >
			<td align="center" class="tit-13" valign="middle">Confirme el correo del destinatario:
		</td>
		</tr><tr height="40" >
			<td align="center" class="tit-13">
		<input type="text" size="40" name="mail" value="cparra@laaraucana.cl" /></td>
		</tr>
		<tr height="30" >
			<td align="left" class="tit-13" valign="bottom">Informar:
		</td>
		<tr height="44">
			<td align="center" valign="middle" class="text-11">
			<input type="radio" name="deposito" value="CHEQUE" />CHEQUES
			<input type="radio" name="deposito" value="TRANSF." checked />TRANSFERENCIAS
			</td>
		</tr>
		<tr height="44">
			<td align="center"><input class="btn_mini" type="button" name="Enviar" value="Enviar" onclick="EnviarPropuesta();"/></td>
		</tr>
	</table>
</form>
</body>
</html>


