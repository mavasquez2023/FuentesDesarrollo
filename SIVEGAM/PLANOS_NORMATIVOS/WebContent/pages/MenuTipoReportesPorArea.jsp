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
		document.MenuTipRepPorAreaForm.opcion.value = a;
	}

	/*funcion envia formulario.*/
	function enviaFormulario(a){
	
		asignaValor(a);
		document.MenuTipRepPorAreaForm.submit();
	}

</script>

</head>
<body>
<html:form action="/MenuTipoReportePorArea.do">
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
			<td colspan="3"  align="right">
				<a href="#" align="right" onclick="enviaFormulario(0);"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" ><B>Cerrar Sesión</B></a>
			</td>
		</tr>
		<tr>
		</tr>
	  	<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="32">&nbsp;</td>
   				<td align="center">
					<strong><p1><font color="#1B2935"> Menú Tipo de Reportes por Área. </font></p1></strong>
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
		
		<tr>
			<td width="32">&nbsp;</td>
   			<td align="center">
				<input type="submit" name="btn_Sivegam"
				class="btn_menu" id="btn_Sivegam"
				value="Sivegam" onClick="asignaValor(1)" /></td>
			<td width="17">&nbsp;</td>
		</tr>
		
		<tr>
			<td width="32">&nbsp;</td>
   			<td align="center">
				<input type="submit" name="btn_Afc"
				class="btn_menu" id="btn_Afc"
				value="AFC" onClick="asignaValor(2)"/></td>
			<td width="17">&nbsp;</td>
		</tr>
		
		<tr>
			<td width="32">&nbsp;</td>
   			<td align="center">
				<input type="submit" name="btn_Cesantia"
				class="btn_menu" id="btn_Cesantia"
				value="Cesantía" onClick="asignaValor(3)"/></td>
			<td width="17">&nbsp;</td>
		</tr>		
	</table>
	</div>
</html:form>
</body>
</html:html>
