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
function cambiarCajas(){
	var cajaPrincipal = document.forms[1].cajaPrincipal;
	var cajas = document.forms[1].indices;

	for (i = 0; i < cajas.length; i++){
		cajas[i].checked = cajaPrincipal.checked ;
	}
}
</script>
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

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Generar
          Reembolso </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
          <html:form action="/generaPagoReembolso">
 	        <html:select property="opcion" styleClass="menuDespl">
              	<html:options name="opciones.valores" labelName="opciones"/>
	        </html:select>
	        <html:image page="/images/botones/boton_ir.gif" border="0" />

          <br>
            <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
              <tr>
                <td align="center"><table width="25%" border="0" cellspacing="1" cellpadding="0">
                    <tr>
                      <td width="59%"><p class="derecha">Filtrar </p>
                      </td>
                      <td width="41%"><div align="center">
                      	<html:image value="_filtrar" property="_filtrar" page="/images/botones/boton_ir.gif" border="0" />
                      	</div>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>

		<table width="537" border="0" cellspacing="2" cellpadding="2">
			<tr class="lookup01" bgcolor="#F0F0F0">
				<td width="50%"><bean:message key="label.common.fechaOcurrenciaHasta" /></td>
				<td width="50%">
					<% String fechaOcurrencia = request.getParameter("fechaOcurrencia");
						if (fechaOcurrencia == null || fechaOcurrencia.equals("")) {
							fechaOcurrencia = fechaHoy;
						}
					%>
					<html:text property="fechaOcurrencia" styleClass="txtHomeSmall" readonly="true" size="12" value="<%= fechaOcurrencia%>"/>
					<a href="#" 
						onClick="cal1.select(document.forms[1].fechaOcurrencia,'anchor1','dd/MM/yyyy'); return false;" 
						title="Eliga la fecha limite de ocurrencia" 
						name="anchor1" id="anchor1"> 
							<html:img page='/images/botones/boton_look_up.gif' border="0"/> 
					</a>
				</td>
			</tr>
		</table>
          <br>
           <table width="530" border="0" cellpadding="2" cellspacing="2">
              <tr>
                <td class="celdaColor1"><input type="checkbox" name="cajaPrincipal" checked="checked" onclick="javascript:cambiarCajas()"></td>
                <td class="celdaColor1"><p class="vinculosUp">Caso ID</p>
                </td>
                <td class="celdaColor1"><p> <a href="#" class="vinculosUp">Fecha<br>Ocurrencia</a></p>
                </td>                                
                <td class="celdaColor1"><p class="vinculosUp">C&oacute;digo Oficina</p>
                </td>
                <td class="celdaColor1"><p class="vinculosUp">RUT</p></td>
                <td class="celdaColor1"><p class="vinculosUp">Monto<br>del Reembolso</p></td>
              </tr>
              
 		<logic:iterate id="register" name="lista.reembolsos" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.ReembolsoVO">
              
              <tr>
                <td bgcolor="#F0F0F0" class="lookup01"><input type="checkbox" name="indices" value="<%=i%>" checked></td>
                <td bgcolor="#F0F0F0" class="lookup01"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoId" target="_top"><bean:write name="register" property="casoId"/></html:link></td>
                <td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="fechaOcurrencia" formatKey="format.date"/></td>                
                <td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="oficina"/></td>
                <td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="fullRut"/></td>
                <td bgcolor="#F0F0F0" class="lookup01">$<bean:write name="register" property="montoReembolso" formatKey="format.int"/></td>
              </tr>
         </logic:iterate>

             </table>
            </html:form></td>
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
