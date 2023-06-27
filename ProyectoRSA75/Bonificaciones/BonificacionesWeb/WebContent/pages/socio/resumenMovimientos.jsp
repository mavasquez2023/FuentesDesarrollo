<%@ page language="java"%>
 
 
<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<script language="JavaScript">
function printInforme(){
	var ventana;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open('<%= contextRoot+"/imprimeSocio.do?destino=resumenMovimientos"%>',"print",prop);
	ventana.print();
}
</script>
<logic:notEmpty name="print">
<STYLE>
body {
	background-attachment: scroll;
	background-color: #FFFFFF;
	background-image:  none;
	background-repeat: repeat-x;

}
</STYLE>
</logic:notEmpty> 
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

<bean:define id="currentDate" name="currentDate" type="java.lang.String"/>
<bean:define id="fechaHoy" name="fechaHoy" type="java.lang.String"/>
<bean:define id="fechaInicioBienestar" name="fechaInicioBienestar" type="java.lang.String"/>
<script language="JavaScript">
	cal1.addDisabledDates("<%= currentDate%>",null);
	cal1.addDisabledDates(null, "12/31/2004");
</script>

<logic:empty name="print">

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> 
        <html:link page="/getListaSocios.do" target="_top">Lista de Socios</html:link> &gt;
        <html:link page="/setFichaSocio.do" target="_top">Socio</html:link> &gt; Resumen
            Movimientos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">

</logic:empty>

		<logic:empty name="header.carga">
		<%@ include file="/includes/headerSocio.jsp" %>
		</logic:empty>
		<logic:notEmpty name="header.carga">
		<%@ include file="/includes/headerCarga.jsp" %>
		</logic:notEmpty>

        <html:form action="/getResumenMovimientoBeneficiario">
         <html:hidden property="source"/>

<logic:empty name="print">
		<br>
		<div align="center">
			<html:link href="javascript:printInforme();"><html:img page="/images/printer.gif" border="0" /></html:link>
		</div>

          <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
            <tr>
              <td align="center"><table width="25%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <td width="59%"><p class="derecha">Filtrar </p>
                    </td>
                    <td width="41%"><div align="center"><html:image page="/images/botones/boton_ir.gif" alt="Filtrar" border="0"/></div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>

</logic:empty>
         <html:errors/>

		<table width="537" border="0" cellspacing="2" cellpadding="2">
			<tr class="lookup01" bgcolor="#F0F0F0">
				<td width="25%"><bean:message key="label.common.fechaInicial" /></td>
				<td width="25%">
					<% String fechaInicial = request.getParameter("fechaInicio");
						if (fechaInicial == null || fechaInicial.equals("")) {
							fechaInicial = fechaInicioBienestar;
						}
					%>
					<html:text property="fechaInicio" styleClass="txtHomeSmall" readonly="true" size="12" value="<%= fechaInicial%>"/>
					<a href="#" 
						onClick="cal1.select(document.forms[1].fechaInicio,'anchor1','dd/MM/yyyy'); return false;" 
						title="Eliga el inicio del periodo de consulta" 
						name="anchor1" id="anchor1"> 
							<html:img page='/images/botones/boton_look_up.gif' border="0"/> 
					</a>
				</td>
				<td width="25%"><bean:message key="label.common.fechaFinal" /></td>
				<td width="25%">
					<% String fechaFinal = request.getParameter("fechaFin");
						if (fechaFinal == null || fechaFinal.equals("")) {
							fechaFinal = fechaHoy;
						}
					%>
					<html:text property="fechaFin" styleClass="txtHomeSmall" readonly="true" size="12" value="<%= fechaFinal%>"/>
					<a href="#" 
						onClick="cal1.select(document.forms[1].fechaFin,'anchor2','dd/MM/yyyy'); return false;" 
						title="Eliga el inicio del periodo de consulta" 
						name="anchor2" id="anchor2"> 
							<html:img page='/images/botones/boton_look_up.gif' border="0"/> 
					</a>
				</td>
			</tr>
		</table>

          <table width="537" border="0" cellspacing="2" cellpadding="2">
            <tr class="celdaColor1">
              <td width="71"><p class="vinculosUp">Codigo Producto</p></td>
              <td width="120"><p class="vinculosUp">Producto - Bonificaci&oacute;n </p></td>
              <td width="108"><p class="vinculosUp"> Tipo de Tope</p>
              </td>
              <td width="71"><p class="vinculosUp">Tope</p></td>
              <td width="71"><p class="vinculosUp">Valor Referencial</p></td>
              <td width="71"><p class="vinculosUp">Cupo Utilizado</p></td>
              <td width="71"><p class="vinculosUp">Cupo Disponible</p></td>
            </tr>
            <tr class="lookup01">
              <td bgcolor="#F0F0F0">&nbsp;</td>
              <td bgcolor="#F0F0F0"><html:text property="producto" styleClass="txtHomeSmall" size="20"/>
                </td>
              <td bgcolor="#F0F0F0">
              	<html:select property="tipoTope" styleClass="menuDespl">
              		<html:option value="">Todos</html:option>
              		<html:option value="ANUALBENEF">ANUALBENEF</html:option>
              		<html:option value="ANUALFAMIL">ANUALFAMIL</html:option>
              		<html:option value="MENSUALFAM">MENSUALFAM</html:option>
              	</html:select>
              </td>
              <td bgcolor="#F0F0F0">&nbsp;</td>
              <td bgcolor="#F0F0F0">&nbsp;</td>
              <td bgcolor="#F0F0F0">&nbsp;</td>
              <td bgcolor="#F0F0F0">&nbsp;</td>
            </tr>
            
		<logic:iterate id="register" name="lista.movimientos" indexId="i">
             
            <tr class="lookup01">
              <td bgcolor="#F0F0F0"><html:link page="/setFichaCobertura.do" paramId="codigo" paramName="register" paramProperty="codigoCobertura"><bean:write name="register" property="codigoCobertura"/></html:link></td>
              <td bgcolor="#F0F0F0"><bean:write name="register" property="nombreCobertura"/></td>
              <td bgcolor="#F0F0F0"><bean:write name="register" property="tipoTope"/></td>
              <td bgcolor="#F0F0F0">$<bean:write name="register" property="tope" formatKey="format.int"/></td>
              <td bgcolor="#F0F0F0">$<bean:write name="register" property="valorReferencial" formatKey="format.int"/></td>
              <td bgcolor="#F0F0F0">$<bean:write name="register" property="aporteBienestarAcumulado" formatKey="format.int"/></td>
              <td bgcolor="#F0F0F0">$<bean:write name="register" property="disponible" formatKey="format.int"/></td>
            </tr>
        </logic:iterate>    
          </table>
          </html:form></td>
        </tr> 
      </table>      
    </td>
  </tr>
</table>

<logic:empty name="print">

<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>

</logic:empty>

</body>
</html>
