<%@ page language="java"%>
 
<%@ include file = "/includes/env.jsp" %>
<jsp:include page="/includes/calendario.js" flush="true"/>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head> 

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td>
    <table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito">
        <%@ include file="/includes/camino.jsp"%>
        <logic:notEqual name="caso" property="estado" value="PRECASO">        
        	<html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; Caso
		</logic:notEqual>
		</td>
      </tr>
 	</table>

	<table width="97%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
	    <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %></td>
	    <td valign="top">
	    		<html:form action="/saldoDeudaTotalCaso">
	          	<h1>&nbsp;</h1>
				<h1 class="centrado">�Esta seguro que desea saldar la deuda total del caso?</h1>
				<p><b>Notar que esta acci�n si es realizada ocurrir� lo siguiente:
				<br/>
				<br/>1. Si el Socio esta ACTIVO generar� un comprobante de ingreso en tesorer�a de bienestar por el total de las cuotas impagas y se cerrar� en el proximo pago de convenios.
				<br/>2. Si el Socio esta INACTIVO y el caso tiene cuotas INTERNAS se cerrar� automaticamente.
				<br/>3. Si el Socio esta INACTIVO y el caso tiene cuotas EXTERNAS se cerrar� en el proximo pago de convenios.
				</b></p>
	          	<br/>
	          	<table border="0" align="center" cellpadding="4" cellspacing="4">
	          		<tr>
	          			<td><html:submit value="Aceptar"></html:submit></td>
	          			<td><html:button property="" value="Cancelar" onclick="javascript:history.go(-1);"></html:button></td>
	          		</tr>
	          	</table>
	 			</html:form>     
	    </td>
	   </tr>
	 </table>      
    </td>
  </tr>
</table>

<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
