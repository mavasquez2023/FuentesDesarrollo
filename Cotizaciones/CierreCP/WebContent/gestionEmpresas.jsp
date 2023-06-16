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
<script language="JavaScript1.2" src="js/core.js"></script>
<script src="js/jquery-1.2.6.js"></script>
<style>
#Ayuda{position:absolute;top:248px;left:482px;}	
</style>
<script>
$(document).ready(function(){	
	$("a.Ayuda").mouseover(
		function (){
			$("#Ayuda").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("a.Ayuda").mouseout(
		function (){
			$("#Ayuda").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
	$("#accionPLA").click(
		function (){
			$("#centralizar").animate({"height": "toggle", "opacity": "toggle"}, "slow");
			$("#fpago").animate({"height": "toggle", "opacity": "toggle"}, "slow");
		}
	);
});
</script> 
<script>
var path= "<%=request.getContextPath()%>";

function VerCierre(){
	form1.target= "";
	form1.action= path + "/VerResumen.do";
	form1.submit();
}

function GenerarPlanillas(){
	if(form1.optioncentral[0].checked){
		mensaje= "¿Confirma la generación de Planillas para el cierre " + form1.cierre.value + " \n" + "y Fecha de Pago " + form1.fechaPago.value + "?";
	}else{
		mensaje= "¿Confirma la generación de Planillas y Centralización para el cierre " + form1.cierre.value + " \n" + "y Fecha de Pago " + form1.fechaPago.value + "?"
	}
	if(confirm(mensaje)){
		form1.action= path + "/GenerarPlanillas.do";
		form1.submit();
	}
}

function GenerarTGR(){
	if(confirm("¿Confirma la generación de TGR para el cierre " + form1.cierre.value + " ?")){
		form1.action= path + "/GenerarTesoreriaGeneral.do";
		form1.submit();
	}
	
}

function GenerarAIA(){
	if(confirm("¿Confirma la generación de Archivos de Impresión para el cierre " + form1.cierre.value + " ?")){
		form1.action= path + "/GenerarArchivosImpresion.do";
		form1.submit();
	}
	
}

function GenerarCEN(){
	if(confirm("¿Confirma la generación de Centralización para el cierre " + form1.cierre.value + " ?")){
		form1.action= path + "/CentralizacionBibliotecaCierre.do";
		form1.submit();
	}
	
}
function VerDetalle(cierre, tipoNomina, recurso){
	openWindow("_blank", "POPUP",  680, 600)
	form1.target= "POPUP";
	form1.cierreDetalle.value= cierre;
	form1.tipoNomina.value= tipoNomina;
	form1.recurso.value= recurso;
	form1.action= path + "/VerComprobantes.do";
	form1.submit();
}

function Estadisticas(){
	periodo= form1.periodo.value;
	filename= "EstadisticasCP_Periodo" + periodo + ".xls";
	form1.target= "_blank";
	form1.action= path + "/DownloadEstadisticas.do?filename=" + filename;
	form1.submit();
}

function OpenPDFService(){
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=no, width=830, height=500, top=50, left=100";
	pagina = "${urlPDFServices}";
	window.open(pagina,"",opciones);
}

function VerCuadratura(cierre){
	openWindow("_blank", "CUADRA",  800, 600)
	form1.target= "CUADRA";
	form1.cierreDetalle.value= cierre;
	form1.seccion.value="AFP";
	form1.action= path + "/VerCuadratutaComprobantes.do";
	form1.submit();
}

function Download(opcion){
	form1.recurso.value= opcion;
	form1.target= "_blank";
	form1.action= path + "/DownloadInformes.do";
	form1.submit();
}

function cerrarSesion(){
		top.location="logout.jsp";
}
</script>
</HEAD>
<BODY topmargin="0">
<form name="form1" method="get">
<TABLE border=0 align="left" cellpadding="0" cellspacing="0">
	<tr><td>
		<TABLE border=0 align="left" cellpadding="0" cellspacing="0">
				<tr><td colspan="3" class="titulos_formularios" align="center">Gestión Empresas</td></tr>
				<tr>
						<td class="text-11" align="left" ><a href="#" onclick="Estadisticas();return false;"><img src="icons/estadisticas.gif" border="0" align="middle" alt="Ver Estadisticas de Pago" >Estadísticas</a></td>
						<td class="text-11" >&nbsp;</td>
						<td class="text-11" align="right" ><a href="#" onclick="OpenPDFService();return false;">Publicador<img src="icons/pdf.png" border="0" align="middle" alt="Abrir PDF Service" ></a></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td class="textos-formularios1" width="200">Período: 
						<select	name="periodo" class="text-11" onchange="form1.cambiaPeriodo.value='1';VerCierre();">
							<c:forEach var="numper" items="${listPeriodos}">
								<option value="${numper}"
									<c:if test='${periodo==numper}'>selected</c:if>>${numper}</option>
							</c:forEach>
						</select>
						</td>
					<!-- td class="textos-formularios1" width="200">Período sistema: <B>${periodoSistema}</B><input type="hidden" name="periodo" value="${periodoSistema}" /></td-->
						<td class="textos-formularios1" align="left" width="200">Mostrar cierre: 
							<select name="cierre" class="text-11" onchange="VerCierre();">
							<option value="0">Todos</option>
							<c:forEach var="numcierre" items="${listCierres}">
								<option value="${numcierre}" <c:if test='${numcierre==cierre}'>selected</c:if>>${numcierre}</option>
							</c:forEach>
							</select>
						</td>
						<td class="textos-formularios1" align="right" width="150">
							<TABLE border=0 align="right" cellpadding="1" cellspacing="1">
							<tr>
								<td class="text-11" align="right" >Acción:</td>
								<td class="text-11" align="right" ><a href="#" onclick="window.print();return false;"><img src="icons/print.gif" border="0" alt="Imprimir Resumen" align="bottom"/></a></td>
								<c:if test="${cierre==0}">
									<td class="text-11" align="right" ><a href="#" onclick="Download('1');return false;"><img src="icons/excel_N.jpg" border="0" alt="Empresas Nuevas" align="bottom"/></a></td>
									<td class="text-11" align="right" ><a href="#" onclick="Download('2');return false;"><img src="icons/excel_NP.jpg" border="0" alt="Empresas No Pagaron" align="bottom"/></a></td>
									<td class="text-11" align="right" ><a href="#" onclick="Download('3');return false;"><img src="icons/excel_A.jpg" border="0" alt="Empresas Activas" align="bottom"/></a></td>
									<td class="text-11" align="right" ><a href="#" onclick="Download('4');return false;"><img src="icons/excel_FP.jpg" border="0" alt="Formas De Pago" align="bottom"/></a></td>
								</c:if>
								<c:if test="${cierre>0 && (periodo==periodoSistema || periodo==periodoSistemaIndependiente )}">
									<td class="text-11" align="right" ><INPUT type="button" id="accionPLA" class="btn_mini" value="PLA" title="Generar Planillas" disabled='disabled' ></td>
									<!-- td class="text-11" align="right" ><INPUT type="button" id="accionTGR" class="btn_mini" value="TGR" title="Generar Tesorería General de la República" disabled='disabled' onclick="GenerarTGR();"></td -->
									<!-- td class="text-11" align="right" ><INPUT type="button" id="accionAIA" class="btn_mini" value="AIA" title="Generar Archivos Impresión ASICOM" disabled='disabled' onclick="GenerarAIA();"></td -->
								</c:if>
								<c:if test="${cierre>0}">
									<td class="text-11" align="right" ><INPUT type="button" id="accionCEN" class="btn_mini" value="CEN" title="Centralizar Información Cierre" disabled='disabled' onclick="GenerarCEN();"></td>
								</c:if>
							</tr>
							</TABLE>
						</td>
				</tr>
				<tr><td class="text-11" colspan="3" align="right">
						<div id="centralizar" style="display:none" align="right">Centralización Trabajadores: 
							<input type="radio" name="optioncentral" value="none" title="NO agregar registros a la tabla" />*none
							<input type="radio" name="optioncentral" value="add" checked="checked" title="Agregar registros a la tabla" />*add
							<input type="radio" name="optioncentral" value="del" title="Elimina si existe y luego agrega registros a la tabla" />*replace
						</div>
					</td>
				</tr>
				<tr><td class="text-11" colspan="3" align="right">
						<div id="fpago" style="display:none" align="right">
							Fecha Pago: 
							<input type="text" class="text-11" name="fechaPago" value="${fechaPago}" size="10" maxlength="10"><BR>
							<INPUT type="button" class="btn_mini" value="Generar" title="Generar Planillas" onclick="GenerarPlanillas();">
						</div>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
		</TABLE>
	</td>
	</tr>
	<tr><td>
		<TABLE border=1 align="center" cellpadding="0" cellspacing="0">
			<tr><td colspan="11" class="textos_formcolor" align="center" valign="middle">Resumen Comprobantes por Cierre</td></tr>
			<tr><td class="textos_formcolor" align="center" valign="middle" width="80">Cierre</td>
				<td class="textos_formcolor" align="center" valign="middle" width="80">Nómina</td>
				<td class="textos_formcolor" align="center" valign="middle">Cantidad</td>
				<c:if test="${ocultarB1 == 'false' && cierre>0 }">
				<td class="textos_formcolor" align="center" valign="middle">Sin Planillas</td>
				</c:if>
				<c:if test="${ocultarB2 == 'false' && cierre>0 }">
				<td class="textos_formcolor" align="center" valign="middle">Sin TGR</td>
				</c:if>
				<td class="textos_formcolor" align="center" valign="middle">Total($)</td>
				<td class="textos_formcolor" align="center" valign="middle">N° Trabajadores</td>
				<td height="22" align="center" valign="middle"
					class="textos_formcolor"><div title="Planillas">PLA</div></td>
				<td height="22" align="center" valign="middle"
					class="textos_formcolor"><div title="Centralización Información Cierre">CEN</div></td>
				<td height="22" align="center" valign="middle"
					class="textos_formcolor"><div title="Tes. Gral. de la República">TGR</div></td>
				<!-- td height="22" align="center" valign="middle"
					class="textos_formcolor"><div title="Archivos Impresión ASICOM">AIA</div></td -->
				
			</tr>
			<c:set var="totalCierres" value="0"></c:set>
			<c:set var="totalTrabajadores" value="0"></c:set>
			<c:set var="totalComprobantes" value="0"></c:set>
			<c:set var="cierre_old" value="0" />
			<c:forEach var="numcierre" items="${listPlanillas}">
				<c:if test="${numcierre.cierre==cierre || cierre==0}">
				<tr>
					<c:choose>
						<c:when test='${cierre_old!=numcierre.cierre}'>
							<c:choose>
								<c:when test='${periodo==periodoSistema || periodo== null}'>
									<td class="text-11" align="center"><A href="#" onclick="VerCuadratura(${numcierre.cierre});return false;"><b>${numcierre.cierre}</b></A></td>
								</c:when>
								<c:otherwise>
									<td class="text-11" align="center"><b>${numcierre.cierre}</b></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<td align="center" class="text-11">&nbsp;</td>
						</c:otherwise>
					</c:choose>
					
					<td class="text-11" align="center">${numcierre.tipoNomina}</td>
					<td class="text-11" align="center">
						<A href="#" onclick="VerDetalle(${numcierre.cierre}, '${numcierre.tipoNomina}', 'COMPROBANTES');return false;">${numcierre.numeroComprobantes}</A> 
					</td>
					<c:if test="${ocultarB1 == 'false' && cierre>0 }">
					<td class="text-11" align="center">
						<c:choose>
							<c:when test="${numcierre.sinPlanillas>0}"><A href="#" onclick="VerDetalle(${numcierre.cierre}, '${numcierre.tipoNomina}', 'PLANILLAS');return false;">${numcierre.sinPlanillas}</A></c:when>
							<c:otherwise>${numcierre.sinPlanillas}</c:otherwise>
						</c:choose> 
					</td>
					</c:if>
					<c:if test="${ocultarB2 == 'false' && cierre>0 }">
					<td class="text-11" align="center">
						<c:choose>
							<c:when test="${numcierre.sinTGR>0}"><A href="#" title="${numcierre.sinTGR} de ${numcierre.numeroComprobantesTGR }" onclick="VerDetalle(${numcierre.cierre}, '${numcierre.tipoNomina}', 'TGR');return false;">${numcierre.sinTGR}</A></c:when>
							<c:otherwise><div id="sinTGR" title="${numcierre.sinTGR} de ${numcierre.numeroComprobantesTGR }">${numcierre.sinTGR}</div></c:otherwise>
						</c:choose> 
					</td>
					</c:if>
					<td align="right" class="text-11" width="110">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${numcierre.total}</fmt:formatNumber></td>
					<td align="center" class="text-11"><fmt:setLocale value="ES" /><fmt:formatNumber>${numcierre.numTrabajadores}</fmt:formatNumber></td>
				
						<td class="text-11" align="center">
							<c:choose>
								<c:when test='${numcierre.sinPlanillas>0 && numcierre.sinPlanillas==numcierre.numeroComprobantes}'><img src="icons/none.gif" border="0" align="middle" alt="${numcierre.numeroComprobantes-numcierre.sinPlanillas } de ${numcierre.numeroComprobantes }" ></c:when>
								<c:when test='${numcierre.sinPlanillas>0 && numcierre.sinPlanillas<numcierre.numeroComprobantes}'><img src="icons/error.gif" border="0" align="middle" alt="${numcierre.numeroComprobantes-numcierre.sinPlanillas } de ${numcierre.numeroComprobantes }" ></c:when>
								<c:otherwise><img src="icons/ok.gif" border="0" align="middle" alt="OK" ></c:otherwise>
							</c:choose>
						</td>
						<td class="text-11" align="center">
							<c:choose>
								<c:when test='${numcierre.sinCEN>0 && numcierre.sinCEN==numcierre.numeroComprobantesCEN}'><img src="icons/none.gif" border="0" align="middle" alt="${numcierre.numeroComprobantesCEN-numcierre.sinCEN } de ${numcierre.numeroComprobantesCEN }" ></c:when>
								<c:when test='${numcierre.sinCEN>0 && numcierre.sinCEN<numcierre.numeroComprobantesCEN}'><img src="icons/error.gif" border="0" align="middle" alt="${numcierre.numeroComprobantesCEN-numcierre.sinCEN } de ${numcierre.numeroComprobantesCEN }" ></c:when>
								<c:when test='${numcierre.numeroComprobantesCEN==0}'><img src="icons/vacio.jpg" border="0" align="middle" alt="Sin Información para el Proceso" ></c:when>
								<c:otherwise><img src="icons/ok.gif" border="0" align="middle" alt="OK(${numcierre.numeroComprobantesCEN })" ></c:otherwise>
							</c:choose>
						</td>
						<td class="text-11" align="center">
							<c:choose>
								<c:when test='${numcierre.sinTGR>0 && numcierre.sinTGR==numcierre.numeroComprobantesTGR}'><img src="icons/none.gif" border="0" align="middle" alt="${numcierre.numeroComprobantesTGR-numcierre.sinTGR } de ${numcierre.numeroComprobantesTGR }" ></c:when>
								<c:when test='${numcierre.sinTGR>0 && numcierre.sinTGR<numcierre.numeroComprobantesTGR}'><img src="icons/error.gif" border="0" align="middle" alt="${numcierre.numeroComprobantesTGR-numcierre.sinTGR } de ${numcierre.numeroComprobantesTGR }" ></c:when>
								<c:when test='${numcierre.numeroComprobantesTGR==0}'><img src="icons/vacio.jpg" border="0" align="middle" alt="Sin Información para el Proceso" ></c:when>
								<c:otherwise><img src="icons/ok.gif" border="0" align="middle" alt="OK(${numcierre.numeroComprobantesTGR })" ></c:otherwise>
							</c:choose>
						</td>
						<!--  td class="text-11" align="center">
							<c:choose>
								<c:when test='${numcierre.sinAIA>0 && numcierre.sinAIA==numcierre.numeroComprobantesAIA}'><img src="icons/none.gif" border="0" align="middle" alt="${numcierre.numeroComprobantesAIA-numcierre.sinAIA } de ${numcierre.numeroComprobantesAIA }" ></c:when>
								<c:when test='${numcierre.sinAIA>0 && numcierre.sinAIA<numcierre.numeroComprobantesAIA}'><img src="icons/error.gif" border="0" align="middle" alt="${numcierre.numeroComprobantesAIA-numcierre.sinAIA } de ${numcierre.numeroComprobantesAIA }" ></c:when>
								<c:when test='${numcierre.numeroComprobantesAIA==0}'><img src="icons/vacio.jpg" border="0" align="middle" alt="Sin Información para el Proceso" ></c:when>
								<c:otherwise><img src="icons/ok.gif" border="0" align="middle" alt="OK(${numcierre.numeroComprobantesAIA })" ></c:otherwise>
							</c:choose>
						</td-->
						
					
				</tr>
				
				<c:set var="cierre_old" value="${numcierre.cierre}" />
				<c:set var="totalCierres" value="${totalCierres + numcierre.total}"/>
				<c:set var="totalTrabajadores" value="${totalTrabajadores + numcierre.numTrabajadores}"/>
				<c:set var="totalComprobantes" value="${totalComprobantes + numcierre.numeroComprobantes}"/>
				</c:if>
			</c:forEach>
			<c:if test='${cierre==0 && totalCierres>0}'>
			<tr><td align="right" colspan="2" class="textos_formcolor">Totales:</td>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalComprobantes}"/></fmt:formatNumber></td>
				<td align="right" class="textos_formcolor">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalCierres}"/></fmt:formatNumber></td>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalTrabajadores}"/></fmt:formatNumber></td>
				<td colspan="4" class="textos_formcolor">&nbsp;</td>
			</tr>
			</c:if>
			<c:if test='${cierre>0 && totalCierres>0}'>
			<tr><td align="right" colspan="2" class="textos_formcolor">Totales:</td>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalComprobantes}"/></fmt:formatNumber></td>
				<c:if test="${ocultarB1 == 'false' }">
					<td class="textos_formcolor">&nbsp;</td>
				</c:if>
				<c:if test="${ocultarB2 == 'false' }">
					<td class="textos_formcolor">&nbsp;</td>
				</c:if>
				<td align="right" class="textos_formcolor">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalCierres}"/></fmt:formatNumber></td>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalTrabajadores}"/></fmt:formatNumber></td>
				<td colspan="4" class="textos_formcolor">&nbsp;</td>
			</tr>
			</c:if>	
		</TABLE>
	</td></tr>
</TABLE>
<INPUT type="hidden" name="cierreDetalle" value="">
<INPUT type="hidden" name="recurso" value="">
<INPUT type="hidden" name="seccion" value="">
<INPUT type="hidden" name="tipoNomina" value="">
<INPUT type="hidden" name="cambiaPeriodo" value="">
<INPUT type="hidden" name="rutEmpresa" value="0">
<INPUT type="hidden" name="formapago" value="${formapago}" />
</form>

<script type="text/javascript">
	<c:if test='${cierre>0 && (periodo==periodoSistema || periodo==periodoSistemaIndependiente )}'>
		//Se habilita acciones para el cierre según condiciones
		accion1 = document.getElementById('accionPLA');
		//accion2 = document.getElementById('accionTGR');
		//accion3 = document.getElementById('accionAIA');
		accion1.disabled=${ocultarB1};
		//accion2.disabled=${ocultarB2};
		//accion3.disabled=${ocultarB3};
		
	</c:if>
	<c:if test='${cierre>0}'>
		accion4 = document.getElementById('accionCEN');
		accion4.disabled=${ocultarB4};
	</c:if>
</script>
</BODY>
</html>
