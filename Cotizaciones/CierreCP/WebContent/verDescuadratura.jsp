<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
function Redirect(action){
	window.location= path + action;
}
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
								class="titulos_formularios" width="49%">&nbsp;&nbsp;&nbsp;&nbsp;Ver
							 Descuadraturas Cierre</td>
							<td height="25" align="right" valign="bottom"
								class="titulos_formularios" width="51%">
							<table height="18" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="18" align="right" valign="middle"
										class="botonera_ppalactivada"><a href="#"
										onclick="window.print();"><img src="icons/printer.gif"
										width="13" height="12" hspace="2" border="0" alt="Cerrar Ventana" />Imprimir</a></td>
									<td width="10" align="right" valign="middle" nowrap="nowrap"
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
				<TABLE border=0>
					<tr><td>&nbsp;</td></tr>
					<tr><td colspan="3" class="textos-formularios1">Forma Pago: 
						<c:choose>
							<c:when test='${formapago=="1"}'>SPL</c:when>
							<c:when test='${formapago=="2"}'>MIXTO</c:when>
							<c:otherwise>${formapago}</c:otherwise>
						</c:choose>
					</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td colspan=4 class="text-11" height="25">Comprobantes con descuadratura:</td></tr>
				</table>
				
					<TABLE border=0 align="center">
					<tr>

						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CÓDIGO BARRA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FOLIO INGRESO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">MONTO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FECHA PAGO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">HORA</td>
					</tr>
					<c:set var="proceso_old" value="0" />
					<c:forEach var="descuadratura" items="${descuadraturas}">
							<tr>
								<c:if test='${descuadratura.proceso != proceso_old}'>
									<tr><td class="textos_formularios2" colspan="5" >
										<c:choose>
											<c:when test='${descuadratura.proceso=="CP"}'>CP y NO TESORERÍA</c:when>
											<c:when test='${descuadratura.proceso=="TE"}'>TESORERÍA y NO CP</c:when>
											<c:otherwise>${descuadratura.proceso}</c:otherwise>
										</c:choose>
										</td>
									</tr>
								</c:if>
								<td class="textos_formularios">${descuadratura.codigoBarra}</td>
								<td class="textos_formularios" align="center">${descuadratura.folioIngreso}</td>
								<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${descuadratura.monto}</fmt:formatNumber></td>
								<td align="center" class="textos_formularios">${descuadratura.fecha}</td>
								<td align="center" class="textos_formularios">
									<c:choose>
											<c:when test='${descuadratura.proceso=="CP"}'>-</c:when>
											<c:when test='${descuadratura.proceso=="TE"}'>${descuadratura.time}</c:when>
											<c:otherwise>&nbsp;</c:otherwise>
										</c:choose>
								</td>
							</tr>
							<c:set var="proceso_old" value="${descuadratura.proceso}" />
					</c:forEach>
							<tr><td class="text-11">&nbsp;</td></tr>
							<tr><td class="text-11" colspan="5">Observación: Si un comprobante aparece en ambas partes, es una decuadratura en el Total</td>
							</tr>
					</TABLE>
			</td>
		</tr>
	</table>
	
</form>
</BODY>
</html>
