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
		    <td align="left" valign="middle" class="titulos">Resultado Consulta Web Service Fonasa</td>
		</tr>
		<tr> 
			<td align="left" valign="top">
				<table width="400" height="170" class="t1">
					<tr><td>
		          			<table width="400" border="0" align="left" cellpadding="0" cellspacing="0">
								<tr> 
		            				<th width="10" height="27">&nbsp;</th>
					                <td width="300" align="left" valign="top"><b>Estado</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="estadoFormat"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Glosa Estado</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="gloEstado"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Apellido Paterno</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="extApellidoPat"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Apellido Materno</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="extApellidoMat"/>
									</td>
									<td align="center" valign="top" width="120">
										&nbsp;
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Nombres</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="extNombres"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Sexo</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="extSexo"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Fecha Nacimiento</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="extFechaNacimiFormat"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Codigo Estado Benficiario</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="extCodEstBen"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top"><b>Descripci&oacute;n Estado Beneficiario</b></td>
					                <td colspan="2" class="texto_chico">
										<bean:write name="resWSFonasaTo" property="extDescEstado"/>
									</td>
								</tr>
								<tr> 
		            				<th width="20" height="27">&nbsp;</th>
					                <td width="30" align="left" valign="top">
					                	<a href="formularioConsulta.do"><button class="boton_1">Volver</button></a>
					                </td>
					                <td colspan="2" class="texto_chico">
										&nbsp;
									</td>
								</tr>
				            </table>

					</td></tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
