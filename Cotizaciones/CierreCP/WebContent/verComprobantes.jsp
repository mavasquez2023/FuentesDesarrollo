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
					<tr><td width="150" class="textos-formularios1">Periodo: 
						&nbsp; ${periodo}</td>
						<td width="100" class="textos-formularios1">Cierre: 
						&nbsp; ${cierreDetalle}</td>
						<td width="150" class="textos-formularios1">Tipo Nómina: 
						&nbsp; ${tipoNomina}</td>
						<td class="textos-formularios">&nbsp;</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CÓDIGO BARRA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TOTAL($)</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">N° TRABAJADORES</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FORMA PAGO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FECHA PAGO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><div id="pla" title="Planilla">PLA</div></td>
						<!-- td rowspan=2 height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><div id="aia" title="Archivo Impresión ASICOM">AIA</div></td-->
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><div id="cen" title="Centralización Información Cierre">CEN</div></td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><div id="tgr" title="Tesorería General de la República">TGR</div></td>
					</tr>
					<!-- tr>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><div id="tgr" title="Tesorería General de la República">TRA</div></td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><div id="tgr" title="Nuevo Convenio Recaudación">NCR</div></td>
					</tr -->
					<c:set var="totalComprobantes" value="0"></c:set>
					<c:set var="totalTrabajadores" value="0"></c:set>
					<c:forEach var="comprobante" items="${detalle}">
					<tr>
						<td align="center" class="textos_formularios">${comprobante.codigoBarra}</td>
						<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.total}</fmt:formatNumber></td>
						<td align="center" class="textos_formularios">${comprobante.numeroTrabajadores}</td>
						<td align="center" class="textos_formularios">${comprobante.formaPago}</td>
						<td align="center" class="textos_formularios">${comprobante.fechaPago}</td>
						<td class="textos_formularios" align="center">
							<c:choose>
								<c:when test='${comprobante.pwf==1}'><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:when>
								<c:otherwise><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:otherwise>
							</c:choose>
						</td>
						<!-- td class="textos_formularios" align="center">
							<c:choose>
								<c:when test='${comprobante.ncr==1}'><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:when>
								<c:when test='${comprobante.ncr==0}'><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:when>
								<c:otherwise><img src="icons/vacio.jpg" border="0" align="middle" alt="Sin Información para el Proceso" ></c:otherwise>
							</c:choose>
						</td -->
						<!--  td class="textos_formularios" align="center">
							<c:choose>
								<c:when test='${comprobante.aia==1}'><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:when>
								<c:when test='${comprobante.aia==0}'><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:when>
								<c:otherwise><img src="icons/vacio.jpg" border="0" align="middle" alt="Sin Información para el Proceso" ></c:otherwise>
							</c:choose>
						</td-->
						<td class="textos_formularios" align="center">
							<c:choose>
								<c:when test='${comprobante.cen==1}'><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:when>
								<c:when test='${comprobante.cen==0}'><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:when>
								<c:otherwise><img src="icons/vacio.jpg" border="0" align="middle" alt="Sin Información para el Proceso" ></c:otherwise>
							</c:choose>
						</td>
						<td class="textos_formularios" align="center">
							<c:choose>
								<c:when test='${comprobante.tgr==1}'><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:when>
								<c:when test='${comprobante.tgr==0}'><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:when>
								<c:otherwise><img src="icons/vacio.jpg" border="0" align="middle" alt="Sin Información para el Proceso" ></c:otherwise>
							</c:choose>
						</td>
					</tr>
					<c:set var="totalComprobantes" value="${totalComprobantes + comprobante.total}"/>
					<c:set var="totalTrabajadores" value="${totalTrabajadores + comprobante.numeroTrabajadores}"/>
					</c:forEach>
					<tr><td align="right" class="textos_formcolor">Total:</td>
					<td align="center" class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalComprobantes}"/></fmt:formatNumber></td>
					<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalTrabajadores}"/></fmt:formatNumber></td>
					<td colspan="7" class="textos_formcolor">&nbsp;</td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</table>
</form>
</BODY>
</html>
