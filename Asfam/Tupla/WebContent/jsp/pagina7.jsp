 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<title>pagina7</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="tupla version 3.3">
<meta name="GENERATOR" content="Rational Software Architect">
<link rel="stylesheet" type="text/css" href="css/cambiotramo.css" />
<link rel="stylesheet" type="text/css" href="css/main.styles.css" />
</head>
<body>
<table width="1000px" style="margin-left: 30px;"> 
<tr>



<td height="100" colspan="2">

<img  src="img/BONO.jpg" width="1000px" height="100"">



</td>
</tr>
<tr><td valign="top" width="300px" >
<jsp:include  page="/jsp/menu.jsp">
</jsp:include>
</td><td width="700px"><font class="text">
Presione el bot&oacute;n Procesar para iniciar el proceso</font><hr>
<html:form action="validacion" method="post">
<table width="700px" border="0">
<tr><td><font class="blueText">Esquema Origen</font></td><td><input type="text" name="esquemaorigen" >
</td><td><font class="blueText">Esquema Destino</font></td><td><input type="text" name="esquemadestino" ></td></tr>
<tr><td><font class="blueText">Tabla Origen</font></td><td><input type="text" name="tablaorigen"></td><td></td><td></td></tr>
<tr><td><font class="blueText">Table Destino</font></td><td><input type="text" name="tabladestino"></td><td></td><td></td></tr>
<tr><td><font class="blueText">ID Mayor a</font></td><td><input type="text" name="maxid"   size="6" value="0"></td><td></td><td></td></tr> 
<tr><td></td><td align="center" colspan="4">
<input type="submit" value="Procesar" class="boton" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" />
</td><td></td><td></td></tr>
</table>
</html:form>
</td></tr>
</table>
</body>
</html:html>
 