<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA DE GENERACION DE REPORTES</title>
<link href="./css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="./css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

	/*función que signa un valor al formulario.*/
	function asignaValor(a){
		//gc();
		document.MenuPrincipalForm.opcion.value = a;
	}

	/*funcion envia formulario.*/
	function enviaFormulario(a){
	
		asignaValor(a);
		document.MenuPrincipalForm.submit();
	}

</script>

</head>
<body>
<html:form action="/menuPrincipal.do">
	<input type="hidden" name="opcion" value="0">
	<div id="caja_registro">
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	<table width="1020" border="0" >
		<tr>
			<td colspan="3"  align="right" width="1020">
				<a href="#" align="right" onClick="enviaFormulario(-1);" ><B>Cerrar Sesión</B></a>
			</td>
		</tr>
	</table>
	<table width="1020" border="0" >
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="32">&nbsp;</td>
				<td align="center">
					<strong><p1><font color="#1B2935"> Menú Principal </font></p1></strong>
				</td>
			<td width="17">&nbsp;</td>
		</tr>
		<tr>
		</tr>
		<br/>
		<tr>
			<!-- <th colspan="1">Seleccione una opción</th> -->
			<td width="32">&nbsp;</td>
			<td align="center">
				<strong><p1>Seleccione una opción</p1></strong>
			</td>
			<td width="17">&nbsp;</td>
			
		</tr>
		
		<!-- Botón para acceder a menu generación de reporte -->
		<tr>
			<td width="32">&nbsp;</td>
			<td align="center" >&nbsp;<input type="submit" name="btn_GeneraReporte"
				class="btn_menu" id="btn_GeneraReporte"
				value="Generación Reportes" onClick="asignaValor(1)" /></td>
			<td width="17">&nbsp;</td>
		</tr>
		
		<!-- Botón para acceder a menu Edición de Reporte -->
		<tr>
			<td width="32">&nbsp;</td>
			<td align="center">&nbsp;<input type="submit" name="btn_EditarReporte"
				class="btn_menu" id="btn_EditarReporte"
				value="Mantenedor Reportes" onClick="asignaValor(2)"/></td>
			<td width="17">&nbsp;</td>
		</tr>
	</table>
	</div>
</html:form>
</body>
</html:html>
