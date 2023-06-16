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

function Actualizar(){
	window.location= path + "/VerProcesosActivos.do";
}
function VerLog(){
	window.location= path + "/VerLogProcesos.do";
}
function SuprimirProceso(proceso, cierre){
if(confirm("¿Confirma la eliminación del Proceso " + proceso + " asociado al Cierre " + cierre + " ?")){
	var clave= proceso + ":" + cierre;
	form1.clave.value= clave;
	form1.action= path + "/VerProcesosActivos.do?" ;
	form1.submit();
}
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
					<tr><td colspan="4" class="titulos_formularios" align="center">Procesos Activos</td></tr>
					<tr><td>&nbsp;</td>
					<td><TABLE border=0 align="right" cellpadding="1" cellspacing="1">
							<tr>
								<td class="text-11" align="right" >Ver Log:</td>
								<td class="text-11" align="right" ><a href="#" onclick="VerLog();"><img src="icons/verlog.gif" border="0" alt="ver Log Procesos Ejecutados" align="bottom"/></a></td>
							</tr>
							</TABLE>
					</td>
					</tr>
					<tr><td width="150" class="textos-formularios1">Periodo: 
						&nbsp; ${periodo}</td>
						<td align="right" class="textos-formularios1">
							<a href="#" onclick="Actualizar();"><img border="0" src="icons/refrescar.gif"
							alt="Actualizar" height="16" width="16" />Actualizar</a>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
				<c:choose>
				<c:when test='${sinprocesos==null}'>
					<tr>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">PROCESO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CIERRE</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FECHA/HORA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ACCION</td>
					</tr>
					<c:forEach var="proceso" items="${activos}">
						<tr>
							<td align="center" class="textos_formularios">${proceso.clave}</td>
							<td align="center" class="textos_formularios">${proceso.cierre}</td>
							<td align="center" class="textos_formularios">${proceso.fechahora}</td>
							<td align="center" class="textos_formularios"><a href="#"
								onclick="SuprimirProceso('${proceso.clave}', '${proceso.cierre}');"><img
								src="icons/error.gif" border="0" align="middle" alt="Suprimir"></a></td>

						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td align="center" class="textos_formularios">${sinprocesos}</td>
					</tr>
				</c:otherwise>
			</c:choose>

				
				</TABLE>
			</td>
		</tr>
	</table>
	<INPUT type="hidden" name="clave" value="">
</form>
</BODY>
</html>
