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
	
<script type="text/javascript">

	/*Funcion que revisa el resultado del login.*/
	function revisaResultado(){
		
		if(document.LoginForm.resultado.value != "" && document.LoginForm.resultado.value != "null"){
			alert(document.LoginForm.resultado.value);
		}
		
		if("<%=session.getAttribute("Error")%>" != "" && "<%=session.getAttribute("Error")%>" != "null"){
			alert("<%=session.getAttribute("Error")%>");
		}
	}

	/*Funcion que limpia los campos del formulario.*/
	function limpiarFormulario(){
	
		document.LoginForm.txt_Usuario.value = "";
		document.LoginForm.txt_Clave.value = "";
	}

	/*Funcion que valida el ingreso de un analista en el formulario.*/
	function ingreso(){
	
		if(validaLogin()){
	
			document.LoginForm.submit();
		}	
	}

	/*Funcion que valida el login del analista que desea registrarse.*/
	function validaLogin(){
	
		var user = document.LoginForm.txt_Usuario.value;
		var pass = document.LoginForm.txt_Clave.value;
		
		if (user.length == 0 || pass.length == 0){
		
			alert("Debe ingresar Usuario y Clave para ingresar.");
			return false;
		}
		
		return true;
	}


</script>	
	
</head>

<body onload="revisaResultado();">
<html:form action="/login.do">
	<div id="caja_registro">
	<input type="hidden" name="resultado"
		value="<%=request.getAttribute("resultado")%>">
	<table>	
		<tr>
			<td>
				
					<strong><p1><font color="#FFFFFF"> SISTEMA TRABAJADOR INDEPENDIENTE </font></p1></strong>
					<br/>
				
			</td>
		</tr>
		<tr>
			<td>
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<strong><p1> <font color="#1B2935">CAJA DE COMPENSACIÓN LA ARAUCANA </font></p1></strong>
				
				
			</td>
		</tr>
	</table>	
	<table width="288" bgcolor="#F2F2F2">
		<tr>
			<th height="38" colspan="2"><font color="#1B2935">Autenticación</font></th>
		</tr>
		<tr>
			<td align="left" valign="middle" bordercolor="#FFFFFF"><strong>Usuario</strong></td>
			<td><input type="text" class="text" name="txt_Usuario"
				id="txt_Usuario" size="21"/></td>
		</tr>
		<tr>
			<td align="left" valign="middle" bordercolor="#FFFFFF"><strong>Clave</strong></td>
			<td><input type="password" class="text" name="txt_Clave"
				id="txt_Clave" value="" size="21"/></td>
		</tr>
		<tr>
			<td width="69"></td>
			<td width="178"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Ingresar" id="btn_Ingresar" class="btn_limp"	value="Ingresar" onClick="ingreso();"/> 
				&nbsp;&nbsp;&nbsp; 
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"	value="Cancelar" onClick="limpiarFormulario()"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:10px;" align="right">v1.32</td>
		</tr>
	</table>
	</div>
</html:form>
</body>
</html:html>
