<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="cl.araucana.cierrecpe.business.Parametros"%>
<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<TITLE>Gestión Boletas</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<script>
var path= "<%=request.getContextPath()%>";

function NuevoBanco(){
	window.location= path + "/GestionBancos.do?accion=NEW";
}

function EliminaBanco(id){
	if(confirm("¿Confirma la eliminación del Banco código " + id + " ?")){
		window.location= path + "/GestionBancos.do?accion=DELETE&idbanco=" + id;
	}
}

function EditarBanco(id){
	window.location= path + "/GestionBancos.do?accion=EDIT&idbanco=" + id;
}

function VerErrores(){
	document.getElementById("errores").style.visibility= "visible";
}

function Volver(){
	window.location= path + "/GenerarBoleta.do";
}

</script>
</HEAD>
<BODY topmargin="0">
<form name="form1">
	<table border="0" align="left" cellpadding="0" cellspacing="0">
		<tr align="center" valign="top">
			<td align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr><td colspan="3" class="titulos_formularios" align="center">Gestión Bancos</td></tr>
				<tr>
						<td class="text-11" align="left" ><a href="#" onclick="NuevoBanco();return false;"><img src="icons/nueva.gif" border="0" align="middle" alt="Nuevo Banco" >Nuevo Banco</a></td>
						<td class="text-11" >&nbsp;</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="72%" valign="top">
				<TABLE border=0 align="center">
					<tr>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">NOMBRE</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">COD. BANCO</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CUENTA CORRIENTE</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ACCION</td>
					</tr>
					<c:forEach var="banco" items="${bancos}">
					<tr>
						<td align="center" class="textos_formularios">${banco.nombre}</td>
						<td align="center" class="textos_formularios">${banco.idBanco}</td>
						<td align="center" class="textos_formularios">${banco.idCuenta}</td>
						<td align="center" class="textos_formularios">
							<a href="#" onclick="EliminaBanco('${banco.idBanco}')">
								<img src="icons/action_delete.png" border="0" align="middle" alt="Eliminar Banco" />
							</a>
							<a href="#" onclick="EditarBanco('${banco.idBanco}')">
								<img src="icons/edit.gif" border="0" align="middle" alt="Editar Banco" />
							</a>
						</td>
					</tr>
					</c:forEach>
					<tr><td colspan=4>&nbsp;</td></tr>
					<tr><td align="center" colspan=4>
							<INPUT type="button" name="volver" class="btn2"  value="Volver" onclick="Volver();" >
						</td>
					</tr>
				</TABLE>	
			</td>
		</tr>
	</table>
</form>
</BODY>
</html>
