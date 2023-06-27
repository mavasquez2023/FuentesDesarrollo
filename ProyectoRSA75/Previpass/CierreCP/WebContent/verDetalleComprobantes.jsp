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
<TITLE></TITLE>
<script>
var path= "<%=request.getContextPath()%>";


</script>
</HEAD>
<BODY topmargin="0">
<form name="form1">
	<table border="0" align="left" cellpadding="0" cellspacing="0">
		<tr align="left" valign="top">
			<td><img src="images/logoshort.jpg" height="95" border="0"
				usemap="#Map" /></td>
		</tr>
		<tr align="center" valign="top">
			<td align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="20" align="center">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr valign="middle">
							<td height="20" align="left" valign="middle"
								class="titulos_formularios" width="49%">&nbsp;</td>
							<td height="25" align="right" valign="bottom"
								class="titulos_formularios" width="51%">
							<table height="18" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="18" align="right" valign="middle"
										class="botonera_ppalactivada">&nbsp;</td>
									<td align="right" valign="middle" nowrap="nowrap"
										class="botonera_ppalactivada"><a href="#"
										onclick="window.close();"><img src="images/ico_cerrar.gif"
										width="9" height="9" hspace="2" border="0" alt="Cerrar Ventana" />Cerrar</a>
									</td>
									<td width="10" align="right" valign="middle" nowrap="nowrap"
										class="botonera_ppalactivada">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr valign="middle">
							<td height="22" colspan="2" align="left" valign="middle"
								class="titulos_formularios">
							<div align="center"><img src="images/sombra.jpg" width="523"
								height="7" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="72%" valign="top">
				<TABLE border=0 width="100%">
					<tr><td colspan="4" class="titulos_formularios" align="center">Detalle Comprobantes</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td width="125" class="textos-formularios1">Periodo: 
						&nbsp; <%=Parametros.getInstance().getParam().getPeriodoSistema()%></td>
						<td width="125" class="textos-formularios1">Forma Pago: 
						&nbsp; ${formapago}</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">NÓMINA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CÓDIGO BARRA</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT EMPRESA</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CONVENIO</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TOTAL($)</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">N° TRABAJADORES</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FECHA PAGO</td>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ESTADO PAGO</td>
					</tr>
					<c:set var="totalMonto" value="0" />
					<c:set var="totalTrabajadores" value="0" />
					<c:forEach var="comprobante" items="${comprobantes}">
					<tr>
						<td align="center" class="textos_formularios">${comprobante.tipoNomina}</td>
						<td align="center" class="textos_formularios">${comprobante.codigoBarra}</td>
						<td align="center" class="textos_formularios">${comprobante.rutEmpresaFull}</td>
						<td align="center" class="textos_formularios">${comprobante.convenio}</td>
						<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.monto}</fmt:formatNumber></td>
						<td align="center" class="textos_formularios">${comprobante.n_trabajadores}</td>
						<td align="center" class="textos_formularios">${comprobante.fecha}</td>
						<td align="center" class="textos_formularios">
						<c:choose>
								<c:when test='${comprobante.id_estado=="4"}'>PARCIAL</c:when>
								<c:when test='${comprobante.id_estado=="5"}'>PAGADO</c:when>
								<c:otherwise>${comprobante.id_estado}</c:otherwise>
						</c:choose>
						</td>
						<c:set var="totalMonto" value="${totalMonto + comprobante.monto}"></c:set>
						<c:set var="totalTrabajadores" value="${totalTrabajadores + comprobante.n_trabajadores}"></c:set>
					</tr>
					</c:forEach>
					<tr><td colspan=4 align="right" class="textos_formularios">Total:</td>
					<td align="right" class="textos_formcolor"><b>&#36;<fmt:setLocale
										value="ES" /><fmt:formatNumber><c:out value="${totalMonto}"/></fmt:formatNumber></b></td>
					<td align="center" class="textos_formularios"><b><fmt:setLocale
										value="ES" /><fmt:formatNumber><c:out value="${totalTrabajadores}"/></fmt:formatNumber></b></td>
					<td align="center" class="textos_formularios">&nbsp;</td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</table>
</form>
</BODY>
</html>
