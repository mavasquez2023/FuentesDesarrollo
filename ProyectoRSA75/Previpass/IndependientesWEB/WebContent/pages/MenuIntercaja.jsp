<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA DE AFILIACION TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet"
	type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript">
	
	/*función que signa un valor al formulario.*/
	function asignaValor(a){

		document.MenuIntercForm.opcion.value = a;
	}

	/*funcion envia formulario.*/
	function enviaFormulario(a){
	
		asignaValor(a);
		document.MenuIntercForm.submit();
	}
	
</script>
</head>
<body onload="">
<html:form action="/menuInterc.do">
	<input type="hidden" name="opcion" value="0">
	<div id="caja_registro">
	<table width="970" border="0" >
		<tr>
			<td colspan="3"  align="right">
				<a href="#" align="right" onclick="enviaFormulario(0);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td width="32">&nbsp;</td>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<strong><p1><font color="#1B2935"> Menú de Intercaja</font></p1></strong>
				</td>
			<td width="17">&nbsp;</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<!-- <th colspan="1">Seleccione una opción</th> -->
			<td width="32">&nbsp;</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<strong><p1>Seleccione una opción</p1></strong>
			</td>
			<td width="17">&nbsp;</td>
			
		</tr>

		<tr>
			<td width="32">&nbsp;</td>
			<td width="216"><input type="submit" name="btn_GeneraArchSalida"
				class="btn_menu" id="btn_GeneraArchSalida"
				value="Generación Archivo Salida" onClick="asignaValor(1)" /></td>
			<td width="17">&nbsp;</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ProcesarArchEntrada"
				class="btn_menu" id="btn_ProcesarArchEntrada"
				value="Procesamiento Archivo de Entrada" onClick="asignaValor(2)" />
			</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ConsultaCasosPend"
				class="btn_menu" id="btn_ConsultaCasosPend"
				value="Consulta Casos Pendientes" onClick="asignaValor(3)" /></td>
			<td>&nbsp;</td>
		</tr>	
	</table>
	</div>
</html:form>
</body>
</html:html>
