<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet"
	type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript">
	
	var arregloPerfiles = null;
	
	/*función que signa un valor al formulario.*/
	function asignaValor(a){

		document.MenuAporteForm.opcion.value = a;
	}

	/*funcion envia formulario.*/
	function enviaFormulario(a){
	
		asignaValor(a);
		document.MenuAporteForm.submit();
	}
	
	
	/*funcion que bloquea los campos dependiendo del perfil del analista.*/
	function bloqueaCampos(){

	}
	
</script>

</head>
<body onload="bloqueaCampos();">
<html:form action="/menuAporte.do">
	<input type="hidden" name="opcion" value="0">
	<div id="caja_registro">
	<table width="970" border="0" >
		<tr>
			<td colspan="3"  align="right">
				<a href="#" align="right" onClick="enviaFormulario(0);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);">Cerrar Sesión</a>
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
					<strong><p1><font color="#1B2935"> Menú Aporte</font></p1></strong>
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
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_RepNominaApoAfi"
				class="btn_menu" id="btn_RepNominaApoAfi"
				value="Consulta Histórica Pago Aporte" onClick="asignaValor(1)"/></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ConsMasivaApo"
				class="btn_menu" id="btn_ConsMasivaApo"
				value="Consulta Masiva Aportes" onClick="asignaValor(2)"/></td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</div>
</html:form>
</body>
</html:html>
