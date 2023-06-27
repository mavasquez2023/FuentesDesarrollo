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
function Home(){
	window.location= path ;
}
function verFechas(mes, oficina, estado){
	window.location= path + "/Estadistica?mes="+ mes+ "&oficina=" + oficina + "&estado=" + estado + "&action=FEC";
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
									<!--   align="right" valign="middle" nowrap="nowrap"
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
								class="titulos_formularios">Estadísticas Cheques PDF</td><td><a href="#" onclick="Home();"><img src="icons/home.gif" border="0" align="middle" alt="Estadísticas x Mes" ></a>&nbsp;<a href="#" onclick="location.reload();"><img src="icons/refrescar.gif" border="0" align="middle" alt="Refescar página" ></a></td></tr>
					<tr><td width="150" class="textos-formularios1">Estadísticas x USUARIO</td><td colspan="2" class="textos-formularios1">${ruta}</td></tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">USUARIO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ESTADO</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CANTIDAD</td>
					<c:if test="${hidecol!='1'}">
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FECHAS</td>
					</c:if>
					</tr>
					<c:set var="totalMonto" value="0" />
					<c:forEach var="estadistica" items="${estadisticas}">
					<tr><td align="center" class="textos_formularios">${estadistica.usuario}</td>
						<td class="textos_formularios" align="center">
						<c:choose>
							<c:when test="${estadistica.estado=='1'}">OK</c:when>
							<c:otherwise><font color='red'>ERROR</font></c:otherwise>
						</c:choose>
						</td>
						<td class="textos_formularios" align="right"><a href="#" title="Ver Detalle Cheques" onclick="verDetalle('${estadistica.mes}', '${estadistica.fecha}', '${estadistica.oficina}', '${estadistica.estado}');">${estadistica.cantidad}</a></td>
						<c:if test="${hidecol!='1'}">
							<td class="textos_formularios" align="right"><a href="#" onclick="verFechas('${estadistica.mes}', '${estadistica.oficina}', '${estadistica.estado}');">Ver</a></td>
						</c:if>
					</tr>
					</c:forEach>
					<tr><td>&nbsp;</td></tr>
					<tr><td align="center" colspan=4>
						<INPUT type="button" name="volver" class="btn2"  value="Volver" onclick="history.back();" >
					</td></tr>
				</TABLE>
			</td>
		</tr>
	</table>
</form>
</BODY>
</html>
