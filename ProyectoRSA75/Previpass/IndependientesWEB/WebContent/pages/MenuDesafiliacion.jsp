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
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript">
	
	var arregloPerfiles = null;
	
	/*función que signa un valor al formulario.*/
	function asignaValor(a){

		document.MenuDesafiliacionForm.opcion.value = a;
	}

	/*funcion envia formulario.*/
	function enviaFormulario(a){
	
		asignaValor(a);
		document.MenuDesafiliacionForm.submit();
	}
	
	
	/*funcion que bloquea los campos dependiendo del perfil del analista.*/
	function bloqueaCampos(){
	
		var botonIng = null;
		var botonMod = null;
		
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		
		//if ('<%=session.getAttribute("Perfil")%>' == "4"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO

		if (validarPerfiles(arregloPerfiles, "1")){
			//perfil full puede acceder a pantalla de beneficios en programa.
			
			botonIng = document.getElementById("btn_SolDesafiliacion");
			botonIng.disabled = false;			
			
			botonIng = document.getElementById("btn_ConsDesafiliacion");
			botonIng.disabled = false;			
			
			botonIng = document.getElementById("btn_NominaDesafiliacion");
			botonIng.disabled = false;		
			
		}
		if (validarPerfiles(arregloPerfiles, "2")){
			//perfil full puede acceder a pantalla de beneficios en programa.
			botonIng = document.getElementById("btn_SolDesafiliacion");
			botonIng.disabled = false;			
			
			botonIng = document.getElementById("btn_ConsDesafiliacion");
			botonIng.disabled = false;			
			
			botonIng = document.getElementById("btn_NominaDesafiliacion");
			botonIng.disabled = false;			
		}
		if (validarPerfiles(arregloPerfiles, "4")){
			//perfil beneficios en programa puede acceder solo a pantalla de beneficios en programa.
			botonIng = document.getElementById("btn_SolDesafiliacion");
			botonIng.disabled = false;			
			
			botonIng = document.getElementById("btn_ConsDesafiliacion");
			botonIng.disabled = false;			
			
			botonIng = document.getElementById("btn_NominaDesafiliacion");
			botonIng.disabled = false;			
		}
		
		$( ".btn_menu" ).each(function() {
			  if($( this ).prop( "disabled" )){
				  $( this ).css( "background", "none" );
				  $( this ).css( "background-color", "#666666" );
			  }
		});
	}
	
</script>

</head>
<body onload="bloqueaCampos();">
<html:form action="/menuDesaf.do">
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
					<strong><p1><font color="#1B2935"> Menú Desafiliación</font></p1></strong>
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
			<td width="216"><input type="submit" name="btn_SolDesafiliacion"
				class="btn_menu" id="btn_SolDesafiliacion"
				value="Generar Nueva Solicitud" onClick="asignaValor(1)" disabled="true"/></td>
			<td width="17">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ConsDesafiliacion"
				class="btn_menu" id="btn_ConsDesafiliacion"
				value="Consulta/Modificacion Solicitud"  onClick="asignaValor(2)" disabled="true"/>
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_NominaDesafiliacion"
				class="btn_menu" id="btn_NominaDesafiliacion"
				value="Nomina de Solicitudes de Desafiliacion"  onClick="asignaValor(3)" disabled="true" />
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</div>
</html:form>
</body>



</html:html>
