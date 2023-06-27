<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
	<title>Formulario de Consulta Web Service Fonasa</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="GENERATOR" content="Rational Software Architect">
	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estilo.css" />
	<script language="javascript">
		var nav4 = window.Event ? true : false;
		function onlyRutKey(evt){
			// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
			var key = nav4 ? evt.which : evt.keyCode;
			return (key <= 13 || (key >= 48 && key <= 57) || key ==107 || key==75 || key = 45);
		}
	</script>
</head>
<body>
	<table width="739" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td>
				<img src="${pageContext.request.contextPath}/images/logo_araucana.jpg" border="0" />
			</td>
		</tr>
		<tr> 
			<td align="left" valign="middle">
				<img src="${pageContext.request.contextPath}/images/sombra.jpg" width="739" height="7" />
			</td>
		</tr>
   		<tr> 
		    <td align="left" valign="middle">&nbsp;</td>
		</tr>
		<tr> 
		    <td align="left" valign="middle" class="titulos">Consulta Web Service Fonasa</td>
		</tr>
		<tr> 
		    <td align="left" height="40" valign="top" class="texto">
		    	<table border="0" cellpadding="0" cellspacing="0">
		    	<tr>
		    		<td rowspan="3"><img src="${pageContext.request.contextPath}/images/exclamacion.jpg" width="40" height="40" /></td>
		    		<td>&nbsp;</td>
		    	</tr>
		    	<tr>
		    		<td class="texto">		    	
				    	&nbsp;&nbsp;Ingrese el <b>Rut</b> de la persona a consultar
				    </td>
		    	</tr>
		    	<tr>
		    		<td>&nbsp;</td>
		    	</tr>		
		    	</table>    	

		    </td>
		</tr>
		<tr> 
			<td align="left" valign="top">
				<table width="400" height="170" class="t1">
					<tr><td>
						<html:form action="/consultarFonasa">
		          			<table width="350" border="0" align="left" cellpadding="0" cellspacing="0">
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Rut</b></td>
					                <td colspan="2" class="texto_chico">
					                	<html:text property="rut" size="15" maxlength="10" onkeypress="return onlyRutKey(event);" onblur="this.value = this.value.toUpperCase();"/>
										</ br>(incluir guión y dígito verificador)
									</td>
									<td align="center" valign="top" width="120">
										<html:submit styleClass="boton_1">Consultar</html:submit>
									</td>
								</tr>
				            </table>
						</html:form>
					</td></tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
