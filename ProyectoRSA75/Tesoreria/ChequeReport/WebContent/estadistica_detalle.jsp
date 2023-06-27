<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

function ReinyectarPDF(id){
	if(confirm("¿Desea Reinyectar PDF?")){
		window.location= path + "/Reinyectar?id="+ id;
	}
}

</script>
</HEAD>
<BODY topmargin="0">
<form name="form1">
	<table border="0" align="left" cellpadding="0" cellspacing="0">
		<tr align="left" valign="top">
			<td><img src="images/logo.jpg" height="95" border="0"
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
							 Estadísticas Cheques PDF</td>
							<td height="25" align="right" valign="bottom"
								class="titulos_formularios" width="51%">
							<table height="18" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="18" align="right" valign="middle"
										class="botonera_ppalactivada">&nbsp;</td>
									<td align="right" valign="middle" nowrap="nowrap"
										class="botonera_ppalactivada"><a href="#"
										onclick="window.close();"><img src="icons/cerrar.gif"
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
					<tr><td colspan="1" class="textos-formularios1">Detalle Estadísticas</td><td colspan="2" class="textos-formularios1">${ruta}</td></tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">AREA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">OFICINA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">USUARIO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FOLIO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">MONTO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ESTADO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">COLA</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">DISPOSITIVO</td>
						
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FECHA_CRE.</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FECHA_MOD.</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">IPREMOTA</td>
						<c:if test="${estado=='0'}">
							<td height="22" align="center" valign="middle"
								bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">MENSAJE</td>
							<td height="22" align="center" valign="middle"
								bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ACCION</td>
						</c:if>
					</tr>
					<c:set var="totalMonto" value="0" />
					<c:forEach var="estadistica" items="${estadisticas}">
					<tr>
						<td align="center" class="textos_formularios">${estadistica.area}</td>
						<td align="center" class="textos_formularios">${estadistica.oficina}</td>
						<td align="center" class="textos_formularios">${estadistica.usuario}</td>
						<td align="center" class="textos_formularios">${estadistica.folio}</td>
						<td align="right" class="textos_formularios">${estadistica.monto}</td>
						<td class="textos_formularios" align="center">
						<c:choose>
							<c:when test="${estadistica.estado=='1'}">OK</c:when>
							<c:otherwise><font color='red'>ERROR</font></c:otherwise>
						</c:choose>
						</td>
						<td align="center" class="textos_formularios">${estadistica.cola}</td>
						<td align="center" class="textos_formularios">${estadistica.dispositivo}</td>
						<td align="center" class="textos_formularios">${estadistica.fecha}<br>${estadistica.hora}</td>
						<td align="center" class="textos_formularios">${estadistica.fechamod}<br>${estadistica.horamod}</td>
						<td align="center" class="textos_formularios">${estadistica.ipremota}<br>${estadistica.username}</td>
						<c:if test="${estado=='0'}">
							<td align="center" class="textos_formularios">&nbsp;${estadistica.mensaje}</td>
							<td align="center" class="textos_formularios"><a href="#" onclick="ReinyectarPDF('${estadistica.id}');return false;"><img src="icons/reinyectar.png" border="0" align="middle" alt="Reinyectar PDF" ></a></td>
						</c:if>
					</tr>
					</c:forEach>
					<tr><td>&nbsp;</td></tr>
					<tr><td align="center" colspan=11>
						<INPUT type="button" name="cerrar" class="btn2"  value="Cerrar" onclick="window.close();" >
					</td></tr>
				</TABLE>
			</td>
		</tr>
	</table>
</form>
</BODY>
</html>
