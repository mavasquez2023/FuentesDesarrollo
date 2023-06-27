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
<TITLE>Ver Seccion Entidades</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<script>
var path= "<%=request.getContextPath()%>";

function GenerarArchivoEntidades(){
	if(confirm("¿Confirma la generación de Archivo Entidades periodo " + form1.periodo.value + " ?")){
		form1.action= path + "/GenerarArchivosEntidades.do";
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
								class="titulos_formularios" width="49%">&nbsp;&nbsp;&nbsp;&nbsp;
							 Archivo Entidades x Período</td>
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
					<tr><td class="textos-formularios1">Periodo: &nbsp; ${periodo}
					</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td class="text-11" height="25">Seleccione las entidades a generar:</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td height="22" align="center" valign="middle"
									class="textos_formcolor"><input type="checkbox" name="allSeccion" title="Uso exclusivo AE" onclick="selectAll(form1.tipoSeccion, this.checked);selectAll(form1.entidadCAJA, this.checked);selectAll(form1.entidadAPV, this.checked);"></td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">SECCIÓN</td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">NOMBRE ENTIDAD</td>
					</tr>
					<c:set var="seccion_old" value="Ninguna" />
					<c:forEach var="seccion" items="${secciones}">
					<!-- se define si cierre es par o impar para definir un stylesheet -->
								
								<!-- Se agrupa por tipo sección -->
								<c:if test='${seccion.tipoSeccion != seccion_old}'>
									<!-- se cierra seccion anterior -->
									<c:if test='${seccion_old != "Ninguna"}'></table></td></tr></c:if>
									<!-- Se inserta cabecera de la seccion -->
									<tr>
										<!-- se define si cierre es dintinto del anterior para solo colocar el valor en la primera fila -->
										<td align="center" class="textos_formularios" >
											<c:set var="numsecciones" value="${numsecciones + 1}"></c:set>
											<input type="checkbox" name="tipoSeccion" checked='checked' title="Uso exclusivo AE" value="${seccion.tipoSeccion}"
											onclick="unSelect(form1.allSeccion, this.checked); selectAll(form1.entidad${seccion.tipoSeccion}, this.checked);">
										</td>
										<td align="left" class="textos_formularios">
											<c:if test="${seccion.tipoSeccion=='APV' || seccion.tipoSeccion=='CAJA'}">
											<A href="#" onclick="swapDetalle('${seccion.tipoSeccion}');return false;">
												<img id="img${seccion.tipoSeccion}" src="icons/ico_mas.gif" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
											</A>
											</c:if>
											${seccion.tipoSeccion}
										</td>
										<td align="center" class="textos_formularios">&nbsp;</td>
									</tr>
									<tr id="${seccion.tipoSeccion}" style="display:none">
									<td colspan="3">
										<table border="0" cellpadding="1" cellspacing="1" width="100%">
								</c:if>
								<!-- Se inserta detalle sección -->
								<tr>
									<!-- style="background-color: #ff9900" -->
									
									<c:set var="seccion_old" value="${seccion.tipoSeccion}" />
									<td align="center" class="textos_formularios2" width="50">&nbsp;</td>
									<td align="left" class="textos_formularios2" >
									<c:choose>
										<c:when test="${seccion.tipoSeccion=='APV'}">
											<input type="checkbox" name="entidadAPV" value="${seccion.nombreEntidad}" checked="checked" />
										</c:when>
										<c:when test="${seccion.tipoSeccion=='CAJA' && seccion.nombreEntidad!=oldCCAF}">
											<input type="checkbox" name="entidadCAJA" value="${seccion.nombreEntidad}" checked="checked" />
											<c:set var="oldCCAF" value="${seccion.nombreEntidad}"></c:set>
										</c:when>
									</c:choose>
									&nbsp;${seccion.nombreEntidad}
									</td>
					
								</tr>
								<c:set var="seccion_old" value="${seccion.tipoSeccion}" />
								
					</c:forEach>
					
				</TABLE>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="text-11" height="60" align="center" valign="middle" colspan="3">
				<INPUT type="button" id="generarAE" class="salir" value="GENERAR AE" title="Generar Archivo Entidades x Periodo" onclick="GenerarArchivoEntidades();">
			</td>
		</tr>
	</table>
	<INPUT type="hidden" name="periodo" value="${periodo}">
	<INPUT type="hidden" name="cierre" value="0">
	<INPUT type="hidden" name="formaPago" value="2"><!-- Mixto -->
</form>
<script type="text/javascript">
<!--
function swapDetalle(id){
	obj = document.getElementById(id);
	img = document.getElementById('img'+id);

    if ( obj.style.display=='' )
    {
		obj.style.display='none';
		img.src = 'icons/ico_mas.gif';
		img.alt = "Expandir";
		img.title = "Expandir";
	} else	
	{
		obj.style.display='';
		img.src = 'icons/ico_menos.gif';
		img.alt = "Contraer";
		img.title = "Contraer";
	}
}
// -->
</script>
</BODY>
</html>
