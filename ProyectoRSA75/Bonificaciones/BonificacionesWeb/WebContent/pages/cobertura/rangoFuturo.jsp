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
</SCRIPT>

<bean:define id="currentDate2" name="currentDate2" type="java.lang.String"/>
<bean:define id="fechaHoy" name="fechaHoy" type="java.lang.String"/>
<bean:define id="fechaInicioBienestar" name="fechaInicioBienestar" type="java.lang.String"/>
<script language="JavaScript">
	cal1.addDisabledDates(null, "<%= currentDate2%>");
</script>

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
	<tr>
		<td>
			<table width="97%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td class="caminito">
						<%@ include file="/includes/camino.jsp"%> 
						<html:link page="/getListaCoberturas.do" target="_top">Lista de Coberturas</html:link> &gt; <html:link page="/setFichaCobertura.do" target="_top">Cobertura</html:link> &gt; Mis Rangos &gt; Definir Rango Futuro
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
						<html:form action="/opcionesRangoFuturo">
							<table width="97%" border="0" cellspacing="1" cellpadding="1">
								<tr>
									<td>
										<html:select property="opcionFuturo" styleClass="menuDespl">
											<html:options name="rangosFuturos.opciones.valores" labelName="rangosFuturos.opciones"/>
										</html:select> 
										<html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/> 
									</td>
								</tr>
							</table>
							<table width="97%" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<td width="52%" valign="top">
										<table width="359" border="0" cellspacing="2" cellpadding="2">
											<tr>
												<td colspan="2">&nbsp;</td>
											</tr>
											<tr>
												<td width="195"><p><strong><bean:message key="label.rango.cuentaGasto"/>: </strong></p></td> 
												<td>
									              <bean:define id="cobertura" name="cobertura" type="cl.araucana.bienestar.bonificaciones.model.Cobertura" />
									              	<logic:iterate id="cuenta" name="rangos.cuenta.gasto" type="cl.araucana.bienestar.bonificaciones.model.Parametro">
													<% if (Long.parseLong(cuenta.getCodigo()) == cobertura.getCuentaGasto()) { %>
														<p><bean:write name="cuenta" property="descripcion" /></p>
													<% } %>	
									              </logic:iterate>
												</td>
											</tr>
											<tr>
												<td><p><strong><bean:message key="label.common.fechaInicioVig" /> <bean:message key="label.obligatorio"/>: </strong></p></td>
												<td>
													<html:text property="fechaInicioVig" styleClass="txtHomeSmall" readonly="true" size="12" value="<%= cobertura.getFechaInicioFuturo()%>"/>
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
													<html:text property="fechaFinVig" styleClass="txtHomeSmall" readonly="false" size="12" value="<%= cobertura.getFechaFinFuturo()%>"/>
													<a href="#" 
														onClick="cal1.select(document.forms[1].fechaFinVig,'anchor2','dd/MM/yyyy'); return false;" 
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
										<td bgcolor="#F0F0F0"><html:radio property="indiceFuturo" value='<%=i+""%>'/></td>
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
