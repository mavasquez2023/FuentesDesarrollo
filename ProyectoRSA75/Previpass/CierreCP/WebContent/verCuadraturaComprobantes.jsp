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
function VerCuadratura(clave){
	form1.cierreDetalle.value= "${cierreDetalle}";
	form1.seccion.value=clave;
	form1.action= path + "/VerCuadratutaComprobantes.do";
	form1.submit();
}
function Download(){
	periodo= "${periodo}";
	cierre= "${cierreDetalle}";
	clave= "${clave}";
	form1.filename.value= "Cuadratura_" + periodo + "_cierre" + cierre + "_" + clave + ".xls";
	form1.seccion.value= clave;
	form1.target= "_blank";
	form1.action= path + "/DownloadCuadratura.do";
	form1.submit();
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
					<tr><td colspan="4" class="titulos_formularios" align="center">Cuadratura Comprobantes x Entidad</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td width="150" class="textos-formularios1">Periodo: 
						&nbsp; ${periodo}</td>
						<td width="100" class="textos-formularios1">Cierre: 
						&nbsp; ${cierreDetalle}</td>
						<td class="textos-formularios">&nbsp;</td>
						<td class="text-11" align="right" width="80" >Acción:</td>
						<td class="text-11" align="right" width="40" ><a href="#" onclick="window.print();return false;"><img src="icons/print.gif" border="0" alt="Imprimir página" align="bottom"/></a></td>
						<td class="text-11" align="right" width="40" ><a href="#" onclick="Download();return false;"><img src="icons/excel.jpg" border="0" alt="Descargar Excel" align="bottom"/></a></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 width="50%" align="center">
					<c:choose>
							<c:when test='${clave=="AFP"}'>
								<c:set var="menuAFP" value="textos-formularios1a" />
							</c:when>
							<c:otherwise>
								<c:set var="menuAFP" value="textos-formularios1" />
							</c:otherwise>
					</c:choose>
					<c:choose>
							<c:when test='${clave=="ISAPRE"}'>
								<c:set var="menuISA" value="textos-formularios1a" />
							</c:when>
							<c:otherwise>
								<c:set var="menuISA" value="textos-formularios1" />
							</c:otherwise>
					</c:choose>
					<c:choose>
							<c:when test='${clave=="INP"}'>
								<c:set var="menuINP" value="textos-formularios1a" />
							</c:when>
							<c:otherwise>
								<c:set var="menuINP" value="textos-formularios1" />
							</c:otherwise>
					</c:choose>
					<c:choose>
							<c:when test='${clave=="MUTUAL"}'>
								<c:set var="menuMUT" value="textos-formularios1a" />
							</c:when>
							<c:otherwise>
								<c:set var="menuMUT" value="textos-formularios1" />
							</c:otherwise>
					</c:choose>
					<c:choose>
							<c:when test='${clave=="CCAF"}'>
								<c:set var="menuCAJ" value="textos-formularios1a" />
							</c:when>
							<c:otherwise>
								<c:set var="menuCAJ" value="textos-formularios1" />
							</c:otherwise>
					</c:choose>
					<c:choose>
							<c:when test='${clave=="APV"}'>
								<c:set var="menuAPV" value="textos-formularios1a" />
							</c:when>
							<c:otherwise>
								<c:set var="menuAPV" value="textos-formularios1" />
							</c:otherwise>
					</c:choose>
					<tr><td width="100" align= "center" class="${menuAFP}"><A href="#" onclick="VerCuadratura('AFP');return false;"><B>AFP</B></A></td>
						<td width="100" align= "center" class="${menuISA}"><A href="#" onclick="VerCuadratura('ISAPRE');return false;"><B>ISAPRE</B></A></td>
						<td width="100" align= "center" class="${menuINP}"><A href="#" onclick="VerCuadratura('INP');return false;"><B>INP</B></A></td>
						<td width="100" align= "center" class="${menuMUT}"><A href="#" onclick="VerCuadratura('MUTUAL');return false;"><B>MUTUAL</B></A></td>
						<td width="100" align= "center" class="${menuCAJ}"><A href="#" onclick="VerCuadratura('CCAF');return false;"><B>CAJA</B></A></td>
						<td width="100" align= "center" class="${menuAPV}"><A href="#" onclick="VerCuadratura('APV');return false;"><B>APV</B></A></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				
				<c:choose>
							<c:when test='${clave=="AFP"}'>
								<jsp:include page="cuadraturaAFP.jsp"/>
							</c:when>
							<c:when test='${clave=="ISAPRE"}'>
								<jsp:include page="cuadraturaISAPRE.jsp"/>
							</c:when>
							<c:when test='${clave=="INP"}'>
								<jsp:include page="cuadraturaINP.jsp"/>
							</c:when>
							<c:when test='${clave=="CCAF"}'>
								<jsp:include page="cuadraturaCCAF.jsp"/>
							</c:when>
							<c:when test='${clave=="MUTUAL"}'>
								<jsp:include page="cuadraturaMUTUAL.jsp"/>
							</c:when>
							<c:when test='${clave=="APV"}'>
								<jsp:include page="cuadraturaAPV.jsp"/>
							</c:when>
							<c:otherwise>
								<jsp:include page="cuadraturaAFP.jsp"/>
							</c:otherwise>
					</c:choose>
				
			</td>
		</tr>
	</table>
	<INPUT type="hidden" name="cierreDetalle" value="">
	<INPUT type="hidden" name="seccion" value="">
	<INPUT type="hidden" name="periodo" value="${periodo}">
	<INPUT type="hidden" name="filename" value="">
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
