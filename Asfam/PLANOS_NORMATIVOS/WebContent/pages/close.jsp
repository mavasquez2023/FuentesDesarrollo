<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERROR</title>

<link href="./css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="./css/grid_960.css" rel="stylesheet" type="text/css" />
	
	<div id="caja_registro">
	<input type="hidden" name="resultado"
		value="<%=request.getAttribute("resultado")%>">
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	<table align="center" width="1020">
		<tr>
			<td align="center">
				<strong><p1><font color="#0B3B39">INTERFAZ DE CIERRE</font></p1></strong>
				<br/>		
			</td>
		</tr>
	</table>
	<br>
	<table width="1020">
		<tr><td>
			<table width="288" bgcolor="#F2F2F2" align="center">
				<tr>
					<th height="38" colspan="2"><font color="#1B2935">Se est&aacute; cerrando la interfaz gr&aacute;fica, 
						espere un momento por favor.</font></th>
				</tr>
			</table>
		</td></tr>
	</table>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
    		window.close();
    	});
	</script>
	
</body>
</html:html>