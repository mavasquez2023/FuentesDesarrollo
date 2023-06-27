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
							 Detalle de Egreso</td>
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
				<TABLE border=0>
					<tr><td>&nbsp;</td></tr>
					<tr><td colspan="3" class="textos-formularios1">Folio Egreso: 
					&nbsp; ${folioEgreso}
					</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td colspan=4 class="text-11" height="25">Comprobantes de Ingreso asociados al Egreso:</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FOLIO INGRESO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT EMPRESA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">MONTO</td>
					</tr>
					<c:set var="totalMonto" value="0" />
					<c:forEach var="comprobante" items="${folios}">
					<tr><td align="center" class="textos_formularios">${comprobante.folioIngreso}</td>
						<td class="textos_formularios">${comprobante.rutEmpresa}</td>
						<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.monto}</fmt:formatNumber></td>
					</tr>
					<c:set var="totalMonto" value="${totalMonto + comprobante.monto}"></c:set>
					</c:forEach>
					<tr><td colspan=2 align="right" class="textos_formularios">Total:</td>
					<td align="right" class="textos_formcolor"><b>&#36;<fmt:setLocale
										value="ES" /><fmt:formatNumber><c:out value="${totalMonto}"/></fmt:formatNumber></b></td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</table>
	<input type="hidden" name="codigoBarra" value="">
</form>
</BODY>
</html>
