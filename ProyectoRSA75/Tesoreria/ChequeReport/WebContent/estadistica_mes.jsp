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

function verOficinas(mes, estado){
	window.location= path + "/Estadistica?mes="+ mes+ "&estado=" + estado + "&action=OFI";
}

function verMes(mes, estado){
	window.location= path + "/Estadistica?mes="+ mes + "&estado=" + estado + "&action=FEC";
}
function verDetalle(mes, fecha, oficina, estado){
	if(mes!='0'){
		mes= "&mes=" + mes;
	}else{
		mes="";
	}
	if(fecha!=''){
		fecha= "&fecha=" + fecha;
	}
	if(oficina!=''){
		oficina= "&oficina=" + oficina;
	}
	
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, width=800, height=600, top=50, left=50";
		pagina= path + "/Estadistica?action=DET" + mes+ fecha+ oficina + "&estado="+ estado;
		window.open(pagina,"DET",opciones);
	
}

function Buscar(){
	var folio= document.getElementById("buscar").value;
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, width=800, height=600, top=50, left=50";
		pagina= path + "/Estadistica?action=FOL&folio="+ folio;
		window.open(pagina,"DET",opciones);
	
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
							<td height="25" align="right" valign="bottom"
								class="titulos_formularios" width="51%">
							<table height="18" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="18" align="right" valign="middle"
										class="botonera_ppalactivada">&nbsp;</td>
									<!--  td align="right" valign="middle" nowrap="nowrap"
										class="botonera_ppalactivada"><a href="#"
										onclick="window.close();"><img src="images/ico_cerrar.gif"
										width="9" height="9" hspace="2" border="0" alt="Cerrar Ventana" />Cerrar</a>
									</td-->
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
				<TABLE border=0 width="85%">
					<tr><td>&nbsp;</td><td colspan="1" height="20" align="center" valign="middle"
								class="titulos_formularios">Estadísticas Cheques PDF</td><td><a href="#" onclick="location.reload();"><img src="icons/refrescar.gif" border="0" align="middle" alt="Refescar página" ></a></td></tr>
					<tr><td width="150" class="textos-formularios1" align="left">Estadísticas x MES</td><td colspan="2">&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td height="22" align="center" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="titulos_formularios">Buscar Folio:</td>
						<td colspan="2" > <input type="text" name="buscar" id="buscar" maxlength="8" size="16"/></td>
						<td><a href="#" onclick="Buscar();"><img src="images/buscar.png" border="0" align="middle" alt="Buscar" ></a> </td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">PERIODO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ESTADO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CANTIDAD</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">OFICINAS</td>
					</tr>
					<c:set var="totalMonto" value="0" />
					<c:forEach var="estadistica" items="${estadisticas}">
					<tr><td align="center" class="textos_formularios"><a href="#" title="Ver Detalle por Día" onclick="verMes('${estadistica.mes}', '${estadistica.estado}');">${estadistica.mes}</a></td>
						<td class="textos_formularios" align="center">
						<c:choose>
							<c:when test="${estadistica.estado=='1'}">OK</c:when>
							<c:otherwise><font color='red'>ERROR</font></c:otherwise>
						</c:choose>
						</td>
						<td class="textos_formularios" align="right">
						<c:choose>
							<c:when test="${estadistica.estado=='1'}">${estadistica.cantidad}</c:when>
							<c:otherwise><a href="#" onclick="verDetalle('${estadistica.mes}', '${estadistica.fecha}', '${estadistica.oficina}', '${estadistica.estado}');">${estadistica.cantidad}</a></c:otherwise>
						</c:choose>
						</td>
						<td class="textos_formularios" align="center"><a href="#" title="Ver Detalle por Oficina" onclick="verOficinas('${estadistica.mes}', '${estadistica.estado}');">Ver</a></td>
					</tr>
					</c:forEach>
				</TABLE>
			</td>
		</tr>
	</table>
</form>
</BODY>
</html>
