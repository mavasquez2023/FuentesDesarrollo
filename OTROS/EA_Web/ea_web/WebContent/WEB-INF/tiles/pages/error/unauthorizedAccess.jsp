<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<title>La Araucana C.C.A.F</title>

<script>
function goto(url) {
	location.href="<%=request.getContextPath()%>" + url;
}
</script>
</head>
<body>
<table>
	<tbody>
		<tr>
			<td style="font-family: verdana; font-size: medium;">
				<p>Estimado(a) Cliente:</p>
			</td>			
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>			
		</tr>
		<tr>
			<td>
				Usted no está autorizado para acceder a este sistema.
			</td>			
		</tr>
		<tr>
			<td>
				Para mayor información, comuníquese con el administrador en
				<a href="mailto:ctorres@laaraucana.cl">ctorres@laaraucana.cl</a>.
				Gracias.
			</td>			
		</tr>
		<tr>
	</tbody>
</table>
</body>
</html>
