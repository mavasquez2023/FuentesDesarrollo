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
<TITLE>Propuesta Pago - CierreCP</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<style>
<c:forEach var="forma" varStatus="vs" items="${listFormasPago}">
	<c:choose>
	<c:when test='${vs.index==0}'>
		#F${vs.index}{position:absolute;top:75px;left:420px;visibility: visible;}
	</c:when>
	<c:otherwise>
		#F${vs.index}{position:absolute;top:75px;left:420px;visibility: hidden;}
	</c:otherwise>
	</c:choose>
	
</c:forEach>
</style>
<script language="JavaScript 1.2" type="text/javascript">
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";

function Init(){
	//form1= window.document.forms[0];
	if (form1.numFormas.value==0){
		form1.generar.disabled="disabled";
	}
}
function OptionCierre(pos){
	if (pos>0){
		veroption.style.visibility= "visible";
		form1.optioncierre.value="del";
	}else{
		veroption.style.visibility= "hidden";
		form1.optioncierre.value="";
	}	
}
function Generar(){	
	nroformas= form1.numFormas.value;
	var fila=0;
	var forma="";
	var deposito="";
	var numempchecked=0;
	var numindchecked=0;
	if (form1.numFormas.value>1){
		for (var i = 0; i < nroformas; i++) {
			//alert("opcion pago " + i + ":" + form1.opcionpago[i].checked);
			if (form1.opcionpago[i].checked==true){
				fila= i;
				forma=form1.opcionpago[i].value;
				//alert("forma pago=" + forma);
				if(forma==1 || forma==2){
					numempchecked++;
				}else if(forma==3 || forma==4){
					numindchecked++;
				}
			}
		}
	}else{
		forma= form1.opcionpago.value;
		form1.opcionpago.checked=true;
		numempchecked=1;
	}
	totalcierre= Number(form1.totalcierre.value);
	//alert("numempchecked=" + numempchecked + ", numindchecked=" + numindchecked);
	if(totalcierre==0 && numempchecked==0 && numindchecked==0){
		alert('Seleccione los Comprobantes a incluir en el cierre.');
		return;
	}else{
		if(numempchecked>1){
			forma="1,2";
		}
		if(numindchecked>1){
			forma="3,4";
		}
		form1.formapago.value= forma;
		deposito= form1.deposito.value;
		if(deposito=="1"){
			deposito="TRANSFERENCIA";	
		}else if(deposito=="2"){
			deposito="CHEQUE";
		}
	}
	//alert('numempresas=' + numempchecked + ", numindepend" + numindchecked);
	cierrereal=0;
	if(numempchecked>0){
		if(form1.cierre_manual.value>0){
			cierrereal= form1.cierre_manual.value;
		}else{
			cierrereal= form1.cierre.value;
		}
	}else{
		if(form1.cierreind_manual.value>0){
			cierrereal= form1.cierreind_manual.value;
		}else{
			cierrereal= form1.cierreind.value;
		}
		
	}
	
	if(confirm("¿Confirma la generación de la propuesta del cierre " + cierrereal + ", con depósito de "  + deposito + " ?")){
			form1.submit();
		}
}
function totalizar(formapago, estado, monto, cantidad){
	if(formapago==1 || formapago==2){
			tipoemp="EMPRESA";
	}else if(formapago==3 || formapago==4){
			tipoemp="INDEPENDIENTE";
	}
	tipoold= form1.tipoEmpresa.value;
	totalcierre= Number(form1.totalcierre.value);
	totalcantidad= Number(form1.totalcantidad.value);
	if(tipoold == tipoemp || tipoold=="" || totalcierre==0){
		if(estado.checked){
			form1.totalcierre.value= totalcierre + monto;
			form1.totalcantidad.value= totalcantidad + cantidad; 
		}else{
			form1.totalcierre.value= totalcierre - monto;
			form1.totalcantidad.value= totalcantidad - cantidad; 
		}
		form1.tipoEmpresa.value= tipoemp;
	}else if(tipoold != tipoemp){
		alert("No se puede generar en un cierre Propuesta Empresa e Independiente a la vez.");
		estado.checked= false;
		return;
	}
}

function VerDescuadratura(formapago){
	openWindow("_blank", "POPUP",  550, 600)
	form1.target= "POPUP";
	form1.formapago.value= formapago;
	form1.action= path + "/VerDescuadraturaFormaPago.do?formapago=" + formapago;
	form1.submit();
	
}

function VerComprobantes(formapago){
	openWindow("_blank", "POPUP2",  700, 600)
	form1.target= "POPUP2";
	form1.formapago.value= formapago;
	form1.action= path + "/VerComprobantesFormaPago.do?formapago=" + formapago;
	form1.submit();	
}

function validaCierre(cierreManual){
	cierreManual= "," + cierreManual + ",";
	if(form1.listCierre.value.indexOf(cierreManual)>-1){
		alert("El cierre indicado ya se encuentra utilizado, ingrese otro valor.");
		form1.cierre_manual.value="";
		return false;
	}
	return;
}

function validaCierreIndep(cierreManual){
	cierreManual= "," + cierreManual + ",";
	if(form1.listCierreIndep.value.indexOf(cierreManual)>-1){
		alert("El cierre indicado ya se encuentra utilizado, ingrese otro valor.");
		form1.cierreind_manual.value="";
		return false;
	}
	return;
}

</script>
</HEAD>
<BODY topmargin="0" onload="Init();">
<form action= "GenerarPropuesta.do" name="form1">
				<TABLE border=0 align="left" cellpadding="0" cellspacing="0">
					<tr><td width="540" class="titulos_formularios" align="center" colspan=2>Generar Propuesta de Pago a Entidades</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td class="textos-formularios1" width="546">Período Empresa: <B><%=Parametros.getInstance().getParam().getPeriodoSistema()%></B>
					| Período Independiente: <B><%=Parametros.getInstance().getParam().getPeriodoSistemaIndependiente()%></B>
					</td>
						<td class="textos-formularios1" align="right">
							<TABLE border=0 align="right" cellpadding="1" cellspacing="1">
							<tr>
								<td class="text-11" align="right" >Acción:</td>
								<td class="text-11" align="right" ><a href="#" onclick="window.print();return false;"><img src="icons/print.gif" border="0" alt="Imprimir página" align="bottom"/></a></td>
							</tr>
							</TABLE>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td colspan=2>
						<c:set var="numdynp" value="false"></c:set>
						<c:forEach var="forma" varStatus="vs" items="${listFormasPago}">
							<c:if test='${forma.numDYNP>0}'><c:set var="numdynp" value="true"></c:set>
							</c:if>
						</c:forEach>
						<table align="center">
														
							<!-- ***********************************EMPRESAS********** -->
							<c:set var="empresas" value="false"></c:set>
							<c:set var="sumempresas" value="0"></c:set>
							<c:set var="desempresas" value="0"></c:set>
							<c:set var="numcolumnas" value="5"></c:set>
							<c:forEach var="forma" varStatus="vs" items="${listFormasPago}">
								<c:set var="numFormas" value="${vs.index+1}"></c:set>
									<c:if test='${forma.formaPago=="1" || forma.formaPago=="2"}'>
										<c:if test='${empresas=="false"}'>
											<c:if test='${numdynp}'>
													<c:set var="numcolumnas" value="6"></c:set>
											</c:if>
											<tr><td height="22" align="center" valign="middle" class="textos_formcolor" colspan=${numcolumnas}>Comprobantes EMPRESAS</td></tr>
											<tr>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">
												</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">FORMA PAGO</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">N° COMPROBANTES</td>
												<c:if test='${numdynp}'>
													<td height="22" align="center" valign="middle"
													class="textos_formcolor">CON DYNP</td>
												</c:if>
												<td height="22" width="90" align="center" valign="middle"
												class="textos_formcolor">TOTAL CP</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">N° DESCUADRATURAS</td>
											</tr>
											<c:set var="empresas" value="true"></c:set>
										</c:if>
										<tr>
												<td class="textos_formularios" align="center">
												<input type="checkbox" name="opcionpago"  value="${forma.formaPago}" onclick="totalizar(${forma.formaPago}, this, ${forma.totalComprobantes}, ${forma.numComprobantes})">
												</td>
												<td class="textos_formularios">
												<c:choose>
													<c:when test='${forma.formaPago=="1"}'>SPL-EMPRESA</c:when>
													<c:when test='${forma.formaPago=="2"}'>MIXTA-EMPRESA</c:when>
												</c:choose>
												</td>
												<td class="textos_formularios" align="center"><a href="#" onClick="VerComprobantes('${forma.formaPago}');" title="Ver Comprobantes">${forma.numComprobantes}</a></td>
												<c:if test='${numdynp}'>
													<td class="textos_formularios2" align="center">${forma.numDYNP}</td>
												</c:if>
												<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${forma.totalComprobantes}</fmt:formatNumber></td>
												<td align="right" class="textos_formularios">&nbsp;</td>
												<c:set var="desempresas" value="${forma.numDescuadraturas}"></c:set>
												<c:set var="sumempresas" value="${forma.totalComprobantes + sumempresas}"></c:set>
										</tr>
									</c:if>
							</c:forEach>
										<c:if test='${sumempresas>0}'>
										<tr>
												<td height="22" align="center" valign="middle" colspan="3"
												class="textos_formcolor">&nbsp;</td>
												<c:if test='${numdynp}'>
													<td height="22" align="center" valign="middle"
													class="textos_formcolor">&nbsp;</td>
												</c:if>
												<td height="22" align="right" valign="middle"
												class="textos_formcolor">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${sumempresas}</fmt:formatNumber></td>
												<c:choose>
													<c:when test='${desempresas>0}'><td class="textos_formcolor" height="22" valign="middle" align="center"><a href="#" onClick="VerDescuadratura('1');" title="Ver Descuadratura">${desempresas}</a></td></c:when>
													<c:otherwise><td class="textos_formcolor" align="center">${desempresas}</td></c:otherwise>
												</c:choose>
												
										</tr>
										</c:if>
										<tr><td>&nbsp;</td></tr>	
										
							<!-- *************************************INDEPENDIENTES******** -->
							<c:set var="independientes" value="false"></c:set>
							<c:set var="sumindependientes" value="0"></c:set>
							<c:set var="desindependientes" value="0"></c:set>
							<c:forEach var="forma" varStatus="vs" items="${listFormasPago}">
								<c:set var="numFormas" value="${vs.index+1}"></c:set>
									<c:if test='${forma.formaPago=="3" || forma.formaPago=="4"}'>
										
										<c:if test='${independientes=="false"}'>
											<tr><td height="22" align="center" valign="middle" class="textos_formcolor" colspan=5>Comprobantes INDEPENDIENTES</td></tr>
											<tr>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">
												</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">FORMA PAGO</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">N° COMPROBANTES</td>
												<c:if test='${numdynp}'>
													<td height="22" align="center" valign="middle"
													class="textos_formcolor">CON DYNP</td>
												</c:if>
												<td height="22" width="90" align="center" valign="middle"
												class="textos_formcolor">TOTAL CP</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">N° DESCUADRATURAS</td>
											</tr>
											<c:set var="independientes" value="true"></c:set>
										</c:if>
										<tr>
												<td class="textos_formularios" align="center">
												<input type="checkbox" name="opcionpago"  value="${forma.formaPago}" onclick="totalizar(${forma.formaPago}, this, ${forma.totalComprobantes}, ${forma.numComprobantes})">
												</td>
												<td class="textos_formularios">
												<c:choose>
													<c:when test='${forma.formaPago=="3"}'>MIXTA-INDEPENDIENTE</c:when>
													<c:when test='${forma.formaPago=="4"}'>SPL-INDEPENDIENTE</c:when>
												</c:choose>
												</td>
												<td class="textos_formularios" align="center"><a href="#" onClick="VerComprobantes('${forma.formaPago}');" title="Ver Comprobantes">${forma.numComprobantes}</a></td>
												<c:if test='${numdynp}'>
													<td class="textos_formularios2" align="center">${forma.numDYNP}</td>
												</c:if>
												<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${forma.totalComprobantes}</fmt:formatNumber></td>
												<td align="right" class="textos_formularios">&nbsp;</td>
												<c:set var="desindependientes" value="${forma.numDescuadraturas}"></c:set>
												<c:set var="sumindependientes" value="${forma.totalComprobantes + sumindependientes}"></c:set>
										</tr>
									</c:if>
							</c:forEach>
										<c:if test='${sumindependientes>0}'>
										<tr>
												<td height="22" align="center" valign="middle" colspan="3"
												class="textos_formcolor">&nbsp;</td>
												<c:if test='${numdynp}'>
													<td height="22" align="center" valign="middle"
													class="textos_formcolor">&nbsp;</td>
												</c:if>
												<td height="22" align="right" valign="middle"
												class="textos_formcolor">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${sumindependientes}</fmt:formatNumber></td>
												<c:choose>
													<c:when test='${desindependientes>0}'><td class="textos_formcolor" height="22" valign="middle" align="center"><a href="#" onClick="VerDescuadratura('3');" title="Ver Descuadratura">${desindependientes}</a></td></c:when>
													<c:otherwise><td class="textos_formcolor" align="center">${desindependientes}</td></c:otherwise>
												</c:choose>
												
										</tr>
										</c:if>
										
								<!-- *************************************OTROS******** -->
							<c:set var="otros" value="false"></c:set>
							<c:set var="sumotros" value="0"></c:set>
							<c:set var="desotros" value="0"></c:set>
							<c:forEach var="forma" varStatus="vs" items="${listFormasPago}">
								<c:set var="numFormas" value="${vs.index+1}"></c:set>
									<c:if test='${forma.formaPago=="0"}'>
										
										<c:if test='${otros=="false"}'>
											<tr><td height="22" align="center" valign="middle" class="textos_formcolor" colspan=5>Comprobantes Otros</td></tr>
											<tr>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">
												</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">FORMA PAGO</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">N° COMPROBANTES</td>
												<c:if test='${numdynp}'>
													<td height="22" align="center" valign="middle"
													class="textos_formcolor">CON DYNP</td>
												</c:if>
												<td height="22" width="90" align="center" valign="middle"
												class="textos_formcolor">TOTAL CP</td>
												<td height="22" align="center" valign="middle"
												class="textos_formcolor">N° DESCUADRATURAS</td>
											</tr>
											<c:set var="otros" value="true"></c:set>
										</c:if>
										<tr>
												<td class="textos_formularios" align="center">
												&nbsp;
												</td>
												<td class="textos_formularios">
												<c:choose>
													<c:when test='${forma.formaPago=="0"}'>OTROS</c:when>
												</c:choose>
												</td>
												<td class="textos_formularios" align="center"><a href="#" onClick="VerComprobantes('${forma.formaPago}');" title="Ver Comprobantes">${forma.numComprobantes}</a></td>
												<c:if test='${numdynp}'>
													<td class="textos_formularios2" align="center">${forma.numDYNP}</td>
												</c:if>
												<td align="right" class="textos_formularios">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${forma.totalComprobantes}</fmt:formatNumber></td>
												<td align="right" class="textos_formularios">&nbsp;</td>
												<c:set var="desotros" value="${forma.numDescuadraturas}"></c:set>
												<c:set var="sumotros" value="${forma.totalComprobantes + sumotros}"></c:set>
										</tr>
									</c:if>
							</c:forEach>
										<c:if test='${sumotros>0}'>
										<tr>
												<td height="22" align="center" valign="middle" colspan="3"
												class="textos_formcolor">&nbsp;</td>
												<c:if test='${numdynp}'>
													<td height="22" align="center" valign="middle"
													class="textos_formcolor">&nbsp;</td>
												</c:if>
												<td height="22" align="right" valign="middle"
												class="textos_formcolor">&#36;<fmt:setLocale value="ES" /><fmt:formatNumber>${sumotros}</fmt:formatNumber></td>
												<c:choose>
													<c:when test='${desotros>0}'><td class="textos_formcolor" height="22" valign="middle" align="center"><a href="#" onClick="VerDescuadratura('3');" title="Ver Descuadratura">${desotros}</a></td></c:when>
													<c:otherwise><td class="textos_formcolor" align="center">${desotros}</td></c:otherwise>
												</c:choose>
												
										</tr>
										</c:if>		
										
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=6>Información Propuesta Cierre</td>
							</tr>
							<tr>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=2>DEPÓSITO</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=1>TOTAL$</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=1>CANTIDAD COMPR.</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=1>N° CIERRE EMP.</td>
								<td height="22" align="center" valign="middle"
									class="textos_formcolor" colspan=1>N° CIERRE INDEP.</td>
							</tr>
							<tr>
								<td class="textos_formularios" align="center" colspan=2>
									<select name="deposito" class="text-11">
										<option value="CHEQUE" selected="selected">CHEQUE</option>
										<option value="TRANSFERENCIA" >TRANSFERENCIA</option>
									</select>
								</td>
								<td class="textos_formularios" align="center" colspan=1>$<INPUT type="text" name="totalcierre" value="0" disabled=disabled/></td>
								<td class="textos_formularios" align="center" colspan=1><INPUT type="text" name="totalcantidad" value="0" disabled=disabled/></td>
								<td class="textos_formularios" align="center">
									<select name="cierre" class="text-11" onchange="OptionCierre(this.selectedIndex);">
										<option value="${nextCierre}" selected="selected">${nextCierre}</option>
										<c:if test='${cierrePendiente!= 0}'>
											<option value="${cierrePendiente}">${cierrePendiente}</option>
										</c:if>
									</select>
									<INPUT type="text" name="cierre_manual" class="text-11" value="" size="2" maxlength="3" onblur="validaCierre(this.value);" onKeyPress="keyNum()" />
								</td>
								<td class="textos_formularios" align="center" >
									<select name="cierreind" class="text-11" onchange="OptionCierre(this.selectedIndex);">
										<option value="${nextCierreIndependiente}" selected="selected">${nextCierreIndependiente}</option>
										<c:if test='${cierrePendiente!= 0}'>
											<option value="${cierrePendiente}">${cierrePendiente}</option>
										</c:if>
									</select>
									<INPUT type="text" name="cierreind_manual" class="text-11" value="" size="2" maxlength="3" onblur="validaCierreIndep(this.value);" onKeyPress="keyNum()" />
								</td>
							</tr>
							<INPUT type="hidden" name="listCierre" value="${listCierre}" />
							<INPUT type="hidden" name="listCierreIndep" value="${listCierreIndep}" />
							<INPUT type="hidden" name="numFormas" value="${numFormas}" />
							<INPUT type="hidden" name="tipoEmpresa" value="" />
						</table>
					</td></tr>
					
					<tr><td class="text-11" align="center" colspan=2>
					<DIV id="veroption" style="visibility:hidden;width:650" >
						<UL>Esta opción eliminará propuesta anterior y generará una nueva que incluirá los nuevos comprobantes. <br>¿Confirma selección? 
						</UL>
					</DIV>
					<INPUT type="hidden" name="optioncierre" value=""/>
					</td></tr>
					<tr><td align="center" colspan=2>
						<INPUT type="button" name="generar" class="btn2"  value="Generar" onclick="Generar();" >
					</td></tr>
				</TABLE>
			<INPUT type="hidden" name="formapago" value=""/>
</form>
</BODY>
</html>
