<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<jsp:include page="/includes/calendario.js" flush="true"/>
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
	ventana=window.open('<%= contextRoot+"/imprimeReporte.do?destino=situacionSocio"%>',"print",prop);
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
<%java.lang.String rutSocio = "";%>
<logic:notEmpty name="reporte" property="socio">
    <bean:define id="socio" name="reporte" property="socio" type="cl.araucana.bienestar.bonificaciones.model.Socio"/>
	<%rutSocio = socio.getRut();%>
</logic:notEmpty>
<SCRIPT LANGUAGE="JavaScript">
var cal1 = new CalendarPopup();
cal1.showYearNavigation();
cal1.showYearNavigationInput();
cal1.setMonthNames("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
cal1.setDayHeaders("D","L","M","M","J","V","S");

//cal1.addDisabledDates("08/13/2008",null);

function submitForm(){
	document.forms[1].action = "getReporteSituacionSocio.do?rut=<%=rutSocio%>";
	document.forms[1].submit();
}
</SCRIPT>


</head>

<bean:define id="currentDate" name="currentDate" type="java.lang.String"/>
<bean:define id="fechaHoy" name="fechaHoy" type="java.lang.String"/>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<logic:empty name="print">

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Situaci&oacute;n
          Socio</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
 </logic:empty>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Socio</strong></p></td>
              </tr>
            </table>            
            
            
            <table width="530" border="0" cellpadding="1" cellspacing="1">
              <tr bgcolor="#F0F0F0">
                <td width="125"><p class="textoHeader1"><strong>RUT:</strong></p>
                </td>
                <td width="401"><p class="textoHeader1">
            <logic:notEmpty name="reporte" property="socio">
                <bean:write name="socio" property="fullRut"/>
            </logic:notEmpty>
            <logic:empty name="print">
                <html:link page="/getListaSocios.do?source=reporte"><html:img page="/images/botones/boton_look_up.gif" alt="Socio" width="14" height="14" border="0"/></html:link>
            </logic:empty>
                </p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Nombre:</strong></p>
                </td>
                <td><p class="textoHeader1">            
            <logic:notEmpty name="reporte" property="socio">
                <bean:write name="socio" property="nombre"/>&nbsp;<bean:write name="socio" property="apePat"/>&nbsp;<bean:write name="socio" property="apeMat"/>
            </logic:notEmpty> </p>
                </td>
              </tr>
              <tr bgcolor="#F0F0F0">
                <td><p class="textoHeader1"><strong>Estado:</strong></p>
                </td>
                <td><p class="textoHeader1">
                <logic:notEmpty name="reporte" property="socio">
  					<bean:write name="socio" property="estado"/>
  				</logic:notEmpty> 
  				</p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Fecha de Ingreso:</strong></p>
                </td>
                <td><p class="textoHeader1">
            <logic:notEmpty name="reporte" property="socio">
  				<bean:write name="socio" property="fecIng" formatKey="format.date"/>
  				</logic:notEmpty> 
  				</p>
                </td>
              </tr>
              <tr bgcolor="#F0F0F0">
                <td><p class="textoHeader1"><strong>Oficina:</strong></p>
                </td>
                <td><p class="textoHeader1">
            <logic:notEmpty name="reporte" property="socio">
                <bean:write name="socio" property="oficina"/>
                </logic:notEmpty> 
                </p>
                </td>
              </tr>
              <html:form action="/getReporteSituacionSocio" >
 			 <tr>
                <td><p class="textoHeader1"><strong>Fecha de valor UF: </strong></p>
                </td>
                <td><p class="textoHeader1">
            
  				
               			<html:text property="fechaUF" styleClass="txtHomeSmall" readonly="true" size="12"/>
               			<A href="#" 
							onClick="cal1.select(document.forms[1].fechaUF,'anchor1','dd/MM/yyyy'); return false;"
							TITLE="Fecha de calculo UF" 
							NAME="anchor1" ID="anchor1"><html:img page='/images/botones/boton_look_up.gif' border="0"/> 
						</A>   	 
						<html:button value="cambiar valor" property="cambiaValor" onclick="submitForm()"></html:button>
					
  				
  				</p>
                </td>
              </tr>              
              <!--
              <tr bgcolor="#F0F0F0">
                <td><p class="textoHeader1"><strong>valor UF:</strong></p>
                </td>
                <td><p class="textoHeader1">

                	<%
                	//String valorUF = (String) request.getAttribute("valorUF");
                	%>
                	
                	<%//=valorUF%>
                </p>
                </td>
              </tr>-->
              </html:form>
            </table>
            <br>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Cargas
                      Familiares</strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="112" class="celdaColor2"><p class="vinculosUp">RUT</p>
                </td>
                <td width="181" class="celdaColor2"><p class="vinculosUp"> Nombre</p>
                </td>
                <td width="117" bgcolor="#F0F0F0" class="celdaColor2"><p class="vinculosUp"> Tipo
                    de Carga</p>
                </td>
                <td width="100" bgcolor="#F0F0F0" class="celdaColor2"><p class="vinculosUp"> Estado</p>
                </td>
                </tr>
            <logic:notEmpty name="reporte" property="socio">
       		<logic:iterate id="register" name="reporte" property="cargas" indexId="i">

              <tr>
                <td><p><bean:write name="register" property="fullRutCarga"/></p>
                </td>
                <td><p><bean:write name="register" property="nombreCarga"/>&nbsp;<bean:write name="register" property="apePatCarga"/>&nbsp;<bean:write name="register" property="apeMatCarga"/></p>
                </td>
                <td><p><bean:write name="register" property="tipoCarga"/></p>
                </td>
                <td><p><bean:write name="register" property="estadoCarga"/></p>
                </td>
                </tr>
       		</logic:iterate>
            </logic:notEmpty>

            </table>
            <br>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Vales Entregados
                      y No Utilizados</strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="113" class="celdaColor2"><p class="vinculosUp">Vale</p>
                </td>
                <td width="181" class="celdaColor2"><p class="vinculosUp">Nombre Convenio</p>
                </td>
                <td width="216" class="celdaColor2"><p class="vinculosUp">Fecha Entrega</p>
                </td>
                </tr>

            <logic:notEmpty name="reporte" property="socio">
       		<logic:iterate id="register" name="reporte" property="vales" indexId="i">
              <tr>
                <td><p>
            <logic:empty name="print">           
                <html:link page="/setFichaVale.do" paramId="codigoVale" paramName="register" paramProperty="codigoVale" target="_top"><bean:write name="register" property="codigoVale"/></html:link>
			</logic:empty>
            <logic:notEmpty name="print">           
                <bean:write name="register" property="codigoVale"/>
			</logic:notEmpty>
                </p>
                </td>
                <td><p><bean:write name="register" property="nombreConvenio"/>
                </p>
                </td>
                <td><p><bean:write name="register" property="fechaEntrega" formatKey="format.date"/></p>
                </td>
                </tr>
       		</logic:iterate>
            </logic:notEmpty>

            </table>
            <br>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Pr&eacute;stamos
                      Activos</strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr class="celdaColor2">
                <td width="74"><p class="vinculosUp">Monto cuota</p>
                </td>
                <td width="53"><p class="vinculosUp"> Cuotas</p>
                </td>
                <td width="105"><p class="vinculosUp">Cuotas Cobradas</p>
                </td>
                <td width="93"><p class="vinculosUp">&Uacute;ltimo
                      Cobro</p>
                </td>
                 <td width="67"><p class="vinculosUp">saldo total</p>
                </td>
                <!-- <td width="128"><p class="vinculosUp">fecha UF</p>
                </td>-->
              </tr>

            <logic:notEmpty name="reporte" property="socio">
       		<logic:iterate id="register" name="reporte" property="prestamos" indexId="i">
              <tr>
                <td width="74"><p>$<bean:write name="register" property="monto" formatKey="format.int"/></p>
                </td>
                <td width="53"><p><bean:write name="register" property="numeroCuotasTotales" formatKey="format.int"/></p>
                </td>
                <td width="105"><p><bean:write name="register" property="cuotaActual" formatKey="format.int"/></p>
                </td>
                <td width="93"><p><bean:write name="register" property="fecha" formatKey="format.date"/></p>
                </td>
                 <td width="67"><p>$<bean:write name="register" property="saldoTotal" formatKey="format.int"/></p>
                </td>
                 <!--<td width="128"><p>
                 
                 </p>
                </td>-->
              </tr>

       		</logic:iterate>
            </logic:notEmpty>

            </table>
            <br>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Descuentos
                      Pendientes</strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr class="celdaColor2">
                <td width="115"><p class="vinculosUp">Saldo Pendiente</p>
                </td>
                <td width="64"><p class="vinculosUp"> Cuotas</p>
                </td>
                <td width="95"><p class="vinculosUp">Cuotas Cobradas</p>
                </td>
                <td width="94"><p class="vinculosUp">&Uacute;ltimo
                      Cobro</p>
                </td>
                <td width="78"><p class="vinculosUp">Tipo Caso</p></td>
                <td width="73"><p class="vinculosUp">Caso ID</p></td>
              </tr>
            <logic:notEmpty name="reporte" property="socio">
       		<logic:iterate id="register" name="reporte" property="descuentos" indexId="i">
              <tr>
                <td><p>$ <bean:write name="register" property="saldoPendiente" formatKey="format.int"/></p>
                </td>
                <td width="64"><p><bean:write name="register" property="cuotas" formatKey="format.int"/></p>
                </td>
                <td width="95"><p><bean:write name="register" property="cuotaCobrada" formatKey="format.int"/></p>
                </td>
                <td width="94"><p><bean:write name="register" property="fechaUltimoCobro" formatKey="format.date"/></p>
                </td>
                <td width="78"><p>
                	<logic:equal name="register" property="tipoCaso" value="<%=cl.araucana.bienestar.bonificaciones.model.Caso.STD_PRECASO%>">
                		<bean:write name="register" property="tipoCaso"/></p>
                	</logic:equal>
                	<logic:notEqual name="register" property="tipoCaso" value="<%=cl.araucana.bienestar.bonificaciones.model.Caso.STD_PRECASO%>">
                		CASO
                	</logic:notEqual>
                </td>
                <td width="73"><p>
                <logic:empty name="print">
                <html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoId" target="_top"><bean:write name="register" property="casoId"/></html:link>
                </logic:empty>
                <logic:notEmpty name="print">
                	<bean:write name="register" property="casoId"/>
                </logic:notEmpty>
                </p>

                </td>
              </tr>
       		</logic:iterate>
            </logic:notEmpty>
            </table>
            <br>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Reembolsos
                      Pendientes</strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellpadding="2" cellspacing="2">
              <tr class="celdaColor2">
                <td width="117"><p class="vinculosUp"> Monto</p>
                </td>
                <td width="208"><p class="vinculosUp">Fecha Ingreso</p></td>
                <td width="119"><p class="vinculosUp">Estado</p></td>
                <td width="58"><p class="vinculosUp">Caso ID</p>
                </td>
              </tr>

            <logic:notEmpty name="reporte" property="reembolsos">
       		<logic:iterate id="register" name="reporte" property="reembolsos" indexId="i">

              <tr>
                <td><p>$<bean:write name="register" property="montoReembolso" formatKey="format.int"/></p>
                </td>
                <td><p><bean:write name="register" property="fechaEstado" formatKey="format.date"/></p>
                </td>
                <td><p><bean:write name="register" property="estado" formatKey="format.int"/></p>
                </td>
                <td><p><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoId" target="_top"><bean:write name="register" property="casoId"/></html:link></p>
                </td>
              </tr>

       		</logic:iterate>
            </logic:notEmpty>
            </table>
<logic:empty name="print">
<br>
	            <html:link href="javascript:printInforme();"><html:img page="/images/printer.gif" border="0" /></html:link>
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
</logic:empty>
</body>
</html>
