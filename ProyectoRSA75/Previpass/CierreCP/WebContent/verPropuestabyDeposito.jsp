<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="cl.araucana.cierrecpe.entidades.business.ChequeEntidades"%>
<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<TITLE>Gesti�n Propuesta de Pagos</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<script src="js/jquery-1.2.6.js"></script>
<style>
<c:forEach var="cierre" items="${listCierres}">
	#C${cierre}{position:relative;top:20px;left:20px;visibility: hidden;}
</c:forEach>
#Ayuda{position:absolute;top:50px;left:340px;}
#Procesos{position:absolute;top:130px;left:270px;}	
</style>
<script>
$(document).ready(function(){
	$("a.Ayuda").click(
		function (){
			$("#Ayuda").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("#Cerrar").click(
		function (){
			$("#Ayuda").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("a.Procesos").click(
		function (){
			$("#Procesos").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("#CerrarPro").click(
		function (){
			$("#Procesos").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("#accionIC").click(
		function (){
			$("#optionclean").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("#optionclean").click(
		function (){
			$("#optionclean").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("#accionAE").click(
		function (){
			$("#fdeposito").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("#accionFULL").click(
		function (){
			$("#fdeposito").animate({"height": "toggle", "opacity": "toggle"}, "slow");
			$("#fpago").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
});
</script> 
<script>
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";

function VerCheques(){
	form1.target= "";
	form1.action= path + "/VerPropuesta.do";
	form1.submit();
}

function VerChequesSort(){
	form1.order.value="SECCION";
	form1.target= "";
	form1.action= path + "/VerPropuesta.do";
	form1.submit();
}

function VerDetalle(folio){
	openWindow("_blank", "POPUP",  550, 600)
	form1.target= "POPUP";
	form1.folio.value= folio;
	form1.action= path + "/VerDetallePropuesta.do?folio=" + folio;
	form1.submit();
}

function VerSecciones(){
	openWindow("_blank", "POPUP",  550, 600)
	periodo= form1.periodo.value;
	form1.target= "POPUP";
	form1.action= path + "/VerSecciones.do?periodo=" + periodo;
	form1.submit();
}

function Download(){
	periodo= form1.periodo.value;
	cierre= form1.cierre.value;
	if (cierre=="0"){
		cierre="Todos";
	}
	filename= "Propuesta_Cheques_" + periodo + "_cierre" + cierre + ".xls";
	form1.target= "_blank";
	form1.action= path + "/DownloadPropuesta.do?filename=" + filename;
	form1.submit();
}

function Enviar(){
	periodo= form1.periodo.value;
	cierre= form1.cierre.value;
	pagina= serverport + path + "/enviarpropuesta.jsp?periodo=" + periodo + "&cierre=" + cierre;
	openWindow(pagina, "POPUP",  500, 300);
}

function Estadisticas(){
	periodo= form1.periodo.value;
	filename= "Estadisticas_Entidades_Periodo" + periodo + ".xls";
	form1.target= "_blank";
	form1.action= path + "/DownloadEstadisticasEnt.do?filename=" + filename;
	form1.submit();
}
function PreFull(){
	document.getElementById('okfd').disabled="true";
}
function GenerarCierreFull(){
	cierre= form1.cierre.value;
	forma= form1.formapago.value;
	if(forma=="1"){
		forma="SPL-EMPRESA";	
	}else if(forma=="2"){
		forma="MIXTA-EMPRESA";
	}else if(forma=="3"){
		forma="SPL-INDEPENDIENTE";	
	}else if(forma=="4"){
		forma="MIXTA-INDEPENDIENTE";
	}
	if(confirm("�Confirma la generaci�n completa del cierre " + cierre + ", asociado a la forma de pago "  + forma + " ?")){
			form1.action= path + "/GenerarCierreFull.do";
			form1.submit();
	}
}

function GenerarCheques(){
	if(confirm("�Confirma la carga a Tesorer�a del cierre " + form1.cierre.value + " ?")){
		form1.action= path + "/GenerarCheques.do";
		form1.submit();
	}
}

function GenerarInforme(){
	if(confirm("�Confirma la generaci�n del Informe Contable del periodo " + form1.periodo.value + " ?")){
		form1.action= path + "/GenerarInformeContable.do";
		form1.submit();
	}else{
		form1.clean[0].checked= false;
		form1.clean[1].checked= false;
	}
}

function GenerarArchivos(opcion){
	if(opcion==0){
		if(!confirm("�Confirma la generaci�n de Archivo de Entidades del periodo " + form1.periodo.value + " \n" + "y Fecha Dep�sito " + form1.fechaDeposito.value + " ?")){
			return false;
		}
	}else{
		if(!confirm("�Confirma la generaci�n de Archivo de Entidades del cierre " + form1.cierre.value + " ?")){
			return false;	
		}
	}
	form1.action= path + "/GenerarArchivosEntidades.do";
	form1.submit();
}

function FechaCreacion(cierre, estado){
switch(cierre){
 <c:forEach var="cierre" items="${listCierres}">
	case '${cierre}': if (estado){
			C${cierre}.style.visibility= "visible";
		}else{
			C${cierre}.style.visibility= "hidden";
		}
		break;
</c:forEach>	
} 
}



</script>
</HEAD>

<BODY topmargin="0">
<form name="form1" method="post">
				<TABLE border=0 align="left" cellpadding="0" cellspacing="0">
					<tr><td colspan="3" class="titulos_formularios" align="center">Gesti�n Entidades</td></tr>
					<tr>
						<td class="text-11" align="left" colspan="1"><a href="#" onclick="Estadisticas();return false;"><img src="icons/estadisticas.gif" border="0" align="middle" alt="Ver Estadisticas de Pago" >Estad�sticas</a></td>
						<td class="text-11" align="right" colspan="2"><a href="#" class="Ayuda">? Ayuda</a>
						<div id="Ayuda" style="display:none"><jsp:include page="estados.html" /></div></td>
					</tr>
					<tr><td class="text-11">&nbsp;</td></tr>
					<tr>
						<td class="textos-formularios1">Per�odo Propuesta: 
						<select	name="periodo" class="text-11" onchange="form1.cambiaPeriodo.value='1';VerCheques();">
							<c:forEach var="numper" items="${listPeriodos}">
								<option value="${numper}"
									<c:if test='${periodo==numper}'>selected</c:if>>${numper}</option>
							</c:forEach>
						</select></td>
						<td class="textos-formularios1" align="left">Mostrar Cierre: 
							<select name="cierre" class="text-11" onchange="VerCheques();">
							<option value="0">Todos</option>
							<c:forEach var="numcierre" items="${listCierres}">
								<option value="${numcierre}" <c:if test='${numcierre==cierre}'>selected</c:if>>${numcierre}</option>
							</c:forEach>
							</select>
						</td>
						<td class="textos-formularios1" align="right">
							<TABLE border=0 align="right" cellpadding="1" cellspacing="1">
							<tr>
								<td class="text-11" align="right" >Acci�n:</td>
								<td class="text-11" align="right" ><a href="#" onclick="window.print();return false;"><img src="icons/print.gif" border="0" alt="Imprimir p�gina" align="bottom"/></a></td>
								<td class="text-11" align="right" ><a href="#" onclick="Download();return false;"><img src="icons/excel.jpg" border="0" alt="Descargar Excel" align="bottom"/></a></td>
								<td class="text-11" align="right" ><a href="#" onclick="Enviar();return false;"><img src="icons/enviar.gif" border="0" alt="Enviar Propuesta" align="bottom"/></a></td>
								<c:if test="${cierre>0}">
								<td class="text-11" align="right" ><INPUT type="button" id="accionTE" class="btn_mini" value="TE" title="Generar Cheques Tesorer�a" disabled='disabled' onclick="GenerarCheques();"></td>
								</c:if>
								<c:if test="${cierre==0}">
								<td class="text-11" align="right" ><INPUT type="button" id="accionIC" class="btn_mini" value="IC" title="Generar Informe Contable" ></td>
								</c:if>
							</tr>
							</TABLE>
						</td>
					</tr>
					<tr><td class="text-11" colspan="3" align="right">
						<div id="optionclean" style="display:none" align="right">
							<input type="radio" name="clean" value="add" title="Agregar registros a la tabla" onclick="GenerarInforme();" />*add
							<input type="radio" name="clean" value="del" title="Elimina y luego agrega registros a la tabla" onclick="GenerarInforme();" />*replace
						</div>
					</td></tr>
					<tr><td class="text-11" colspan="3" align="right">
						<div id="fdeposito" style="display:none" align="right">
							Fecha Dep�sito: 
							<input type="text" class="text-11" name="fechaDeposito" value="${fechaDeposito}" size="10" maxlength="10">
							<INPUT type="button" class="btn_mini" id="okfd" value="OK" title="Generar Archivo Entidades" onclick="GenerarArchivos('${cierre}')">
						</div>
					</td></tr>
					<tr><td class="text-11" colspan="3" align="right">
						<div id="fpago" style="display:none" align="right">
							Fecha Pago: 
							<input type="text" class="text-11" name="fechaPago" value="${fechaPago}" size="10" maxlength="10">
							<INPUT type="button" class="btn_mini" value="OK" title="Generar Cierre Full" onclick="GenerarCierreFull();">
						</div>
					</td></tr>
					<tr><td class="text-11">&nbsp;</td></tr>
					<tr>
						<td colspan="3">
						
						<table border=0>
							<tr><td colspan="12" class="textos_formcolor" align="center" valign="middle">Resumen Propuestas por Cierre</td></tr>
							<tr>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor">CIERRE</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor">ORIGEN</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor">DEP�SITO <a href="#" onclick="VerChequesSort();"><img id="imgorder" src="icons/ascsort.gif" border="0" alt="Order by Dep�sito" title="Order" /></a></td>
								<td height="22" width="100" align="center" valign="middle"
									class="textos_formcolor">SECCION</td>
								<td height="22" width="190" align="center" valign="middle"
									class="textos_formcolor">ENTIDAD</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor">NOMINA</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor">FOLIO EGRESO</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor">MONTO</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor">ESTADO</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor"><div title="Cheques Tesorer�a">TE</div></td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor"><div title="Archivo Entidades">AE</div></td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor"><div title="Informe Contable">IC</div></td>
							</tr>
							<c:set var="cierre_old" value="0" />
							<c:set var="seccion_old" value="Ninguna" />
							<c:set var="deposito_old" value="" />
							<c:set var="finseccion" value="</table></td></tr>" />
							<!-- 
							B1=Tesorer�a
							B2=Archivo Entidades
							B3=Informe Contable
							B4=Archivo Entidades Periodo
							 -->
							<c:set var="ocultarB1" value="true"></c:set>
							<c:set var="ocultarB2" value="true"></c:set>
							<c:set var="ocultarB3" value="false"></c:set>
							<c:set var="ocultarB4" value="false"></c:set>
							<c:set var="numchecked" value="0"></c:set>
							<c:set var="numsecciones" value="0"></c:set>
							<c:set var="totalCierre" value="0"></c:set>
							<c:set var="oldCCAF" value="0"></c:set>

							<!-- se itera sobre una collection de cheques -->
							<c:forEach var="cheque" items="${cheques}">
								<!-- se define si cierre es par o impar para definir un stylesheet -->
								<c:choose>
									<c:when test='${cheque.cierre%2>0}'>
										<c:set var="classtext" value="textos_formularios" />
										<c:set var="classtext2" value="textos_formularios2" />
									</c:when>
									<c:otherwise>
										<c:set var="classtext" value="textos_formularios2" />
										<c:set var="classtext2" value="textos_formularios" />
									</c:otherwise>
								</c:choose>
								<!-- Se agrupa por tipo secci�n -->
								<c:if test='${cheque.descripcionSeccion != seccion_old}'>
									<!-- se cierra seccion anterior -->
									<c:if test='${seccion_old != "Ninguna"}'></table></td></tr></c:if>
									<!-- Se inserta cabecera de la seccion -->
									<tr>
										<!-- se define si cierre es dintinto del anterior para solo colocar el valor en la primera fila -->
										<c:choose>
											<c:when test='${cierre_old!=cheque.cierre}'>
												<td align="center" class="${classtext}"><a href="#" title="${cheque.fechaCreacion}"><b>${cheque.cierre}</b></a></td>
												<td align="center" class="${classtext}">${cheque.origen}</td>
											</c:when>
											<c:otherwise>
												<td align="center" class="${classtext}">&nbsp;</td>
												<td align="center" class="${classtext}">&nbsp;</td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test='${cierre_old!=cheque.cierre || deposito_old!= cheque.deposito}'>
												<td align="center" class="${classtext}">${cheque.deposito}</td>
											</c:when>
											<c:otherwise>
												<td align="center" class="${classtext}">&nbsp;</td>
											</c:otherwise>
										</c:choose>
										<td align="left" class="${classtext}">
											<A href="#" onclick="swapDetalle('${cheque.cierre}${cheque.deposito}${cheque.descripcionSeccion}');return false;">
												<img id="img${cheque.cierre}${cheque.deposito}${cheque.descripcionSeccion}" src="icons/ico_mas.gif" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
											</A>
											${cheque.descripcionSeccion}&nbsp;(${cheque.cantidadSeccion})</td>
										<td align="left" class="${classtext}" colspan="3">&nbsp;</td>
										<td align="right" class="${classtext}" width="90">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${cheque.totalSeccion}</fmt:formatNumber></td>
										<td align="center" class="${classtext}">&nbsp;</td>
										<td align="center" class="${classtext}">
											<c:choose>
												<c:when test='${cheque.estadoStr=="G"}'><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:when>
												<c:otherwise><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:otherwise>
											</c:choose>
										</td>
										<td align="center" class="${classtext}">
											<c:choose>
												<c:when test='${cheque.estadoStr=="A" || cheque.estadoStr=="I"}'><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:when>
												<c:otherwise><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:otherwise>
											</c:choose>
										</td>
										<td align="center" class="${classtext}">
											<c:choose>
												<c:when test='${cheque.estadoStr=="I"}'><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:when>
												<c:otherwise><img src="icons/none.gif" border="0" align="middle" alt="No Generado" ></c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr id="${cheque.cierre}${cheque.deposito}${cheque.descripcionSeccion}" style="display:none">
									<td colspan="11">
										<table border="0" cellpadding="1" cellspacing="1" width="95%">
								</c:if>
								<!-- Se inserta detalle secci�n -->
								<tr>
									<!-- style="background-color: #ff9900" -->
									
									<c:set var="cierre_old" value="${cheque.cierre}" />
									<c:set var="seccion_old" value="${cheque.descripcionSeccion}" />
									<td align="center" class="${classtext2}" width="150">&nbsp;</td>
									<td align="left" class="${classtext2}" width="240">${cheque.razonSocial}</td>
									<td align="center" class="${classtext2}" width="40">${cheque.tipoNomina}</td>
									<td align="center" class="${classtext2}" width="70">
										<A href="#" onclick="VerDetalle(${cheque.folioEgreso});return false;">${cheque.folioEgreso}</A>
										<c:if test="${cheque.estadoStr == 'G' && cheque.generaEgreso}">*</c:if>
									</td>
									<td align="right" class="${classtext2}" width="80">&#36;<fmt:setLocale
										value="ES" /><fmt:formatNumber>${cheque.montoTotal}</fmt:formatNumber></td>

									<td align="center" class="${classtext2}" width="40">${cheque.estado}</td>
									<!-- se evaluan condiciones de acciones -->
									<c:if test="${cheque.estadoStr == 'G'}"><c:set var="ocultarB1" value="false"></c:set><c:set var="ocultarB3" value="true"></c:set></c:if>
									<c:if test="${cheque.estadoStr != 'G' }"><c:set var="ocultarB2" value="false"></c:set></c:if>
								</tr>
								<c:set var="totalCierre" value="${totalCierre + cheque.montoTotal}"/>
								<c:set var="seccion_old" value="${cheque.descripcionSeccion}" />
								<c:set var="deposito_old" value="${cheque.deposito}" />
							</c:forEach>
							<!-- se evalua condicion de planillas generadas -->
							<c:if test="${conPlanillas=='false'}"><c:set var="ocultarB2" value="true"></c:set><c:set var="ocultarB4" value="true"></c:set></c:if>
							<!-- se cierra secci�n final -->
							<c:if test='${true}'>
								</table></td></tr>
							</c:if>
							<!-- Se agrega total al final -->
							<tr>	<td align="right" 
									<c:choose>
										<c:when test='${cierre>0}'>colspan="7"</c:when>
										<c:otherwise>colspan="7"</c:otherwise>
									</c:choose>
									class="textos_formcolor">Total:</td>
									<td align="right" class="textos_formcolor">&#36;<fmt:setLocale
										value="ES" /><fmt:formatNumber><c:out value="${totalCierre}"/></fmt:formatNumber></td>
									<td class="textos_formcolor" colspan="4">&nbsp;</td>
							</tr>
							<c:if test='${!ocultarB1}'>
								<tr>
									<td align="left" colspan=3 class="text-11">*: indica folio temporal</td>
								</tr>
							</c:if>
							<c:if test="${cierre>0}">
							<tr>
								<td class="text-11" height="60" align="center" valign="middle" colspan="13"><INPUT type="button" id="accionFULL" class="btn2" value="GENERAR CIERRE FULL" title="Generar Cierre Full" onClick="PreFull();">
								<a href="#" class="Procesos"><img src="icons/ayuda.jpg" border="0" align="middle"  alt="Procesos a Ejecutar..." ></a><div id="Procesos" style="display:none"><jsp:include page="procesos.html" /></div></td>
							</tr>
							</c:if>
						</table>
						</td>
					</tr>
				</TABLE>
<INPUT type="hidden" name="folio" value="">
<INPUT type="hidden" name="cambiaPeriodo" value="">
<INPUT type="hidden" name="optioncentral" value="del">
<INPUT type="hidden" name="order" value="SECCION">
</form>
<script type="text/javascript">
	<c:if test='${cierre>0}'>
		numchecked=<c:out value="${numchecked}"/>;
		numsecciones=<c:out value="${numsecciones}"/>;
		//Se habilita acciones para el cierre seg�n condiciones
		accion1 = document.getElementById('accionTE');
		accion3 = document.getElementById('accionFULL');
		accion4 = document.getElementById('fpago');
		accion5 = document.getElementById('space');
		accion1.disabled=<c:out value="${ocultarB1}"/>;
		accion3.disabled=<c:out value="${ocultarB1}"/>;
	</c:if>
	//Se habilita acciones para el periodo seg�n condiciones
	<c:if test='${cierre==0}'>
		accion3 = document.getElementById('accionIC');
		accion3.disabled=<c:out value="${ocultarB3}"/> ;
	</c:if>
</script>
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
