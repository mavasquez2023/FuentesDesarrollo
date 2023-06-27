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

function NuevaBoleta(action){
	window.location= path + "/GenerarBoleta.do";
}

function EliminaBoleta(id, monto){
	if(confirm("¿Confirma la eliminación de la boleta por $" + monto + " ?")){
		window.location= path + "/EliminarBoleta.do?id=" + id;
	}
}

function AsignarComprobantes(rutEmpresa, montoboleta, idboleta){
	rutEmpresa= quitaFormato(rutEmpresa, '.');
	window.location= path + "/VerComprobantesBoleta.do?rutEmpresa=" + rutEmpresa + "&montoboleta=" + montoboleta + "&idboleta=" + idboleta;
}

function VerErrores(){
	document.getElementById("errores").style.visibility= "visible";
}

</script>
</HEAD>
<BODY topmargin="0">
<form name="form1">
	<table border="0" align="left" cellpadding="0" cellspacing="0">
		<tr align="center" valign="top">
			<td align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr><td colspan="3" class="titulos_formularios" align="center">Gestión Boletas</td></tr>
				<tr>
						<td class="text-11" align="left" ><a href="#" onclick="NuevaBoleta();return false;"><img src="icons/nueva.gif" border="0" align="middle" alt="Nueva Boleta" >Nueva Boleta</a></td>
						<td class="text-11" >&nbsp;</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="72%" valign="top">
				<TABLE border=0 width="100%">
					<tr><td>&nbsp;</td></tr>
					<tr><td width="125" class="textos-formularios1">Periodo: 
						&nbsp; <%=Parametros.getInstance().getParam().getPeriodoSistema()%></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">PERIODO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT EMPRESA</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">BANCO</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CUENTA</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">MONTO($)</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ESTADO</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ACCION</td>
					</tr>
					<c:set var="totalMonto" value="0" />
					<c:forEach var="boleta" items="${boletas}">
					<tr>
						<td align="center" class="textos_formularios">${boleta.periodo}</td>
						<td align="center" class="textos_formularios">${boleta.rutEmpresa}</td>
						<td align="center" class="textos_formularios">${boleta.idBanco}</td>
						<td align="center" class="textos_formularios">${boleta.idCuenta}</td>
						<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${boleta.monto}</fmt:formatNumber></td>
						<td align="center" class="textos_formularios">
							<c:choose>
								<c:when test='${boleta.estado=="0"}'>NUEVA</c:when>
								<c:when test='${boleta.estado=="1"}'>INCOMPLETA</c:when>
								<c:when test='${boleta.estado=="2"}'>PAGADA</c:when>
							</c:choose>
						</td>
						<td align="center" class="textos_formularios">
							<c:if test='${boleta.estado=="0"}'>
								<a href="#" onclick="EliminaBoleta('${boleta.idBoleta}', '${boleta.monto}')">
									<img src="icons/action_delete.png" border="0" align="middle" alt="Eliminar Boleta" />
								</a>
								<a href="#" onclick="AsignarComprobantes('${boleta.rutEmpresa}', '${boleta.monto}', '${boleta.idBoleta}')">
									<img src="icons/alert.png" border="0" align="middle" alt="Asociar Comprobante(s)" />
								</a>
							</c:if>
							<c:if test='${error==boleta.idBoleta}'>
									<img src="icons/alert-error.png" border="0" align="middle" alt="Ver Errores" />
							</c:if>
							<c:if test='${boleta.estado=="1"}'>
								<img src="icons/alert-error.png" border="0" align="middle" alt="Libro Banco No Registrado" />
							</c:if>
							<c:if test='${boleta.estado=="2"}'>
								<img src="icons/ok-icon.png" border="0" align="middle" alt="Boleta OK" />
							</c:if>
						</td>
						<c:set var="totalMonto" value="${totalMonto + boleta.monto}"></c:set>
					</tr>
					</c:forEach>
					<tr><td colspan=4 align="right" class="textos_formularios">Total:</td>
					<td align="right" class="textos_formcolor"><b>&#36;<fmt:setLocale
										value="ES" /><fmt:formatNumber><c:out value="${totalMonto}"/></fmt:formatNumber></b></td>
					<td colspan=2 align="center" class="textos_formularios">&nbsp;</td>
					</tr>
				</TABLE>
				<c:if test='${error>0}'>
				<div id="errores">
				<TABLE border=0 align="center">
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><img src="icons/alert-error.png" border="0" align="middle" />&nbsp;Mensajes de Error</td>
					</tr>
					<c:forEach var="mensaje" items="${mensajes}">
					<tr>
						<td align="center" class="textos_formularios">${mensaje}</td>
					</tr>
					</c:forEach>
				</TABLE>
				</div>
				</c:if>	
			</td>
		</tr>
	</table>
</form>
</BODY>
</html>
