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
<TITLE>Boleta PAC - CierreCP</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<script language="JavaScript 1.2" type="text/javascript">
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";
var cuentas= ['' 
<c:forEach var="banco" items="${bancos}">
, '${banco.idCuenta}'
</c:forEach>
];

function Guardar(){	
	if(form1.idbanco.value==""){
		alert('Ingrese Código Banco.');
		return;
	}else if(form1.nombre.value==""){
		alert('Ingrese Nombre Banco.');
		return;
	}else if(form1.idcuenta.value==""){
		alert('Ingrese Cuenta Corriente Banco.');
		return;
	}
	form1.idbanco.disabled=false;
	form1.submit();
}

function Volver(){
	window.location= path + "/VerBancos.do"
}


</script>
</HEAD>
<BODY topmargin="0" >
<form action= "GestionBancos.do" name="form1">
				<TABLE border=0 align="left" cellpadding="0" cellspacing="0">
					<tr><td colspan=3 class="titulos_formularios" align="center">
					<c:choose>
						<c:when test='${accion=="UPDATE"}'>Modificar</c:when>
						<c:otherwise>Nuevo</c:otherwise>
					</c:choose>
					&nbsp;Banco Boleta</td></tr>
					<tr><td width="180">&nbsp;</td></tr>
					
					<tr>
							<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=3>Ingrese Datos Banco</td>
					</tr>
					<tr>
							<td height="22" align="center" valign="middle"
								class="textos_formcolor" width="140">CÓD. BANCO</td>
							<td height="22" align="center" valign="middle"
								class="textos_formcolor" width="210">NOMBRE BANCO</td>
							<td height="22" align="center" valign="middle"
								class="textos_formcolor" width="180">CUENTA</td>
					</tr>
					<tr>
							<td class="textos_formularios" align="center" ><INPUT type="text" name="idbanco" id="idbanco" value="${banco.idBanco}" onKeyPress="keyNum()" <c:if test='${accion=="UPDATE"}'> disabled='disabled'</c:if> /></td>
							<td class="textos_formularios" align="center" ><INPUT type="text" name="nombre" id="nombre" value="${banco.nombre}" /></td>
							<td class="textos_formularios" align="center" ><INPUT type="text" name="idcuenta" id="idcuenta" value="${banco.idCuenta}" /></td>
					</tr>
					<tr><td colspan=3>&nbsp;</td></tr>
					<tr><td align="center" colspan=3>
							<INPUT type="button" name="guardar" class="btn2"  value="Guardar" onclick="Guardar();" >
							&nbsp;&nbsp;&nbsp;<INPUT type="button" name="volver" class="btn2"  value="Volver" onclick="Volver();" >
						</td>
						
					</tr>
					<tr><td colspan=3>&nbsp;</td></tr>
					<tr><td colspan=3 class="titulos_formularios" align="center" style="color: red">${mensaje}</td></tr>
				</TABLE>
				<input type="hidden" name="accion" value="${accion}">
				<input type="hidden" name="idcuentaold" value="${banco.idCuenta}">
</form>
</BODY>
</html>
