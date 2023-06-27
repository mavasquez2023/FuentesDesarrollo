<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
 
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head> 
 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<jsp:include page="/includes/calendario.js" />

<SCRIPT LANGUAGE="JavaScript">
var cal1 = new CalendarPopup();
cal1.showYearNavigation();
cal1.showYearNavigationInput();
cal1.setMonthNames("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
cal1.setDayHeaders("D","L","M","M","J","V","S");

var cal2 = new CalendarPopup();
cal2.showYearNavigation();
cal2.showYearNavigationInput();
cal2.setMonthNames("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
cal2.setDayHeaders("D","L","M","M","J","V","S");
</SCRIPT>

<bean:define id="currentDate" name="currentDate" type="java.lang.String"/>
<bean:define id="fechaHoy" name="fechaHoy" type="java.lang.String"/>
<bean:define id="fechaInicioBienestar" name="fechaInicioBienestar" type="java.lang.String"/>
<script language="JavaScript">
	cal1.addDisabledDates("<%= currentDate%>",null);
	cal1.addDisabledDates(null, "12/31/2004");

	cal2.addDisabledDates(null, "12/31/2004");
</script>

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
	<tr>
		<td>
			<table width="97%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td class="caminito">
						<%@ include file="/includes/camino.jsp"%> 
						<html:link page="/getListaCoberturas.do" target="_top">Lista de Coberturas</html:link> &gt; <html:link page="/setFichaCobertura.do" target="_top">Cobertura</html:link> &gt; Mis Rangos
					</td>
				</tr>
			</table>
			<table width="97%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %></td>
					<td valign="top">
						<!-- Header Cobertura -->
						<%@ include file="/includes/headerCobertura.jsp" %>
						<br>
						<html:errors/>
						<html:form action="/opcionesRangos">
							<table width="97%" border="0" cellspacing="1" cellpadding="1">
								<tr>
									<td>
										<html:select property="opcion" styleClass="menuDespl">
											<html:options name="rangos.opciones.valores" labelName="rangos.opciones"/>
										</html:select> 
										<html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/> 
									</td>
									<td width="27%" align="right" valign="top">
										<table width="172" border="0" cellspacing="3" cellpadding="1">
											<% if (userinformation.hasAccess("cobRango")) { %>
												<tr>
													<td width="184" align="right" class="opciones">
														<html:link page="/getListaRangosHistoricos.do" target="_top">
															<html:img page="/images/botones/cobertura/rangosHistoricos.gif" alt="Rangos Historicos" width="160" height="21" border="0"/>
														</html:link>
													</td>
												</tr>
											<% } %>
											<% if (userinformation.hasAccess("cobRango")) { %>
												<tr>
													<td align="right" class="opciones">
														<html:link page="/getListaRangoFuturo.do" target="_top">
															<html:img page="/images/botones/cobertura/rangosFuturos.gif" alt="Rangos Futuros" width="160" height="21" border="0"/>
														</html:link>
													</td>
												</tr>
											<% } %>
										</table>
									</td>
								</tr>
							</table>
							<bean:define id="cobertura" name="cobertura" type="cl.araucana.bienestar.bonificaciones.model.Cobertura" />
							<table width="97%" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<td width="52%" valign="top">
										<table width="359" border="0" cellspacing="2" cellpadding="2">
											<tr>
												<td colspan="2">&nbsp;</td>
											</tr>
											<tr>
												<td width="195"><p><strong><bean:message key="label.rango.cuentaGasto"/> <bean:message key="label.obligatorio"/>: </strong></p></td> 
												<td>
													<html:select property="cuentaGasto" styleClass="menuDespl">
														<html:options collection="rangos.cuenta.gasto" property="codigo" labelProperty="descripcion"/>
													</html:select>
												</td>
											</tr>
											<tr>
												<td><p><strong><bean:message key="label.common.fechaInicioVig" /> <bean:message key="label.obligatorio"/>: </strong></p></td>
												<td>
													<html:text property="fechaInicioVig" styleClass="txtHomeSmall" readonly="true" size="12" value="<%= cobertura.getFechaInicioVigente()%>"/>
													<a href="#" 
														onClick="cal1.select(document.forms[1].fechaInicioVig,'anchor1','dd/MM/yyyy'); return false;" 
														title="Eliga la fecha de inicio de vigencia" 
														name="anchor1" id="anchor1"> 
														<html:img page='/images/botones/boton_look_up.gif' border="0"/> 
													</a>
												</td>
											</tr>
											<tr>
												<td><p><strong><bean:message key="label.common.fechaFinVig" /> <bean:message key="label.obligatorio"/>: </strong></p></td>
												<td>
													<html:text property="fechaFinVig" styleClass="txtHomeSmall" readonly="false" size="12" value="<%= cobertura.getFechaFinVigente()%>"/>
													<a href="#" 
														onClick="cal2.select(document.forms[1].fechaFinVig,'anchor2','dd/MM/yyyy'); return false;" 
														title="Eliga la fecha de fin de vigencia" 
														name="anchor2" id="anchor2"> 
														<html:img page='/images/botones/boton_look_up.gif' border="0"/> 
													</a>
												</td>
											</tr>
											<tr>
												<td colspan="2"><hr></td>
											</tr>
											<tr>
												<td width="195"><p><strong><bean:message key="label.rango.inicio"/> <bean:message key="label.obligatorio"/>: </strong></p></td>
												<td width="150"><html:text property="inicioRango" styleClass="txtHomeSmall" size="18"/></td>
											</tr>
											<tr>
												<td><p><strong><bean:message key="label.rango.fin"/> <bean:message key="label.obligatorio"/>: </strong></p></td>
												<td><p><html:text property="finRango" styleClass="txtHomeSmall" size="18"/></p></td>
											</tr>
											<tr>
												<td><p><strong><bean:message key="label.rango.porcentajeCobertura"/> <bean:message key="label.obligatorio"/>:</strong></p></td>
												<td><p><html:text property="porcentajeCobertura" styleClass="txtHomeSmall" size="18"/></p></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table width="529" border="0" cellspacing="2" cellpadding="2">
								<tr>
									<td width="56" class="celdaColor1">&nbsp;</td>
									<td width="170" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.inicio"/></p></td>
									<td width="161" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.fin"/></p></td>
									<td width="94" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.porcentajeCobertura"/></p></td>
									<td width="16">&nbsp;</td>
								</tr>
								<logic:iterate id="register" name="lista.rangos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Rango">
									<tr>
										<td bgcolor="#F0F0F0"><html:radio property="indice" value='<%=i+""%>'/></td>
										<td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="rangoInicio" formatKey="format.int"/></p></td>
										<td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="rangoFin" formatKey="format.int"/></p></td>
										<td bgcolor="#F0F0F0"><p><bean:write name="register" property="rangoPorcentajeCobertura" formatKey="format.int"/>%</p></td>
									</tr>
								</logic:iterate>
							</table>
						</html:form>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
