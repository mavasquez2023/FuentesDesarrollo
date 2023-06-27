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

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
	<tr>
		<td>
			<table width="97%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td class="caminito">
						<%@ include file="/includes/camino.jsp"%> 
						<html:link page="/getListaCoberturas.do" target="_top">Lista de Coberturas</html:link> &gt; <html:link page="/setFichaCobertura.do" target="_top">Cobertura</html:link> &gt; Mis Rangos &gt; Hist&oacute;rico de Rangos de Cobertura
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
						<logic:empty name="cobertura" property="rangosHistoricos">
							<h1 class="centrado">No existen rangos historicos</h1>
						</logic:empty>
						<logic:notEmpty name="cobertura" property="rangosHistoricos">
							<logic:iterate id="grupoRangos" name="cobertura" property="rangosHistoricos" type="cl.araucana.bienestar.bonificaciones.model.VigenciaRango">
								<table width="529" border="0" cellspacing="2" cellpadding="2">
									<tr>
										<th><p><strong><bean:message key="label.common.fechaInicioVig" /></strong></p></th>
										<td><p><bean:write name="grupoRangos" property="inicioVigencia" formatKey="format.date" /></p></td>
									</tr>
									<tr>
										<th><p><strong><bean:message key="label.common.fechaFinVig" /></strong></p></th>
										<td><p><bean:write name="grupoRangos" property="finVigencia" formatKey="format.date" /></p></td>
									</tr>
								</table>
								<table width="529" border="0" cellspacing="2" cellpadding="2">
									<tr>
										<td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.inicio"/></p></td>
										<td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.fin"/></p></td>
										<td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.porcentajeCobertura"/></p></td>
									</tr>
									<logic:iterate id="register" name="grupoRangos" property="rangos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Rango">
										<tr>
											<td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="rangoInicio" formatKey="format.int"/></p></td>
											<td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="rangoFin" formatKey="format.int"/></p></td>
											<td bgcolor="#F0F0F0"><p><bean:write name="register" property="rangoPorcentajeCobertura" formatKey="format.int"/>%</p></td>
										</tr>
									</logic:iterate>
								</table>
								<br>
							</logic:iterate>
						</logic:notEmpty>
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
