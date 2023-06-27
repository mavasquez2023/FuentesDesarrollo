<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- <META name="links-collection-enabled" content="false"> -->
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">

<STYLE>
body {
	background-attachment: scroll;
	background-color: #FFFFFF;
	background-image:  none;
	background-repeat: repeat-x;

}
</STYLE>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Socio</strong></p></td>
              </tr>
            </table>            
            
            <logic:notEmpty name="reporte" property="socio">
            	<bean:define id="socio" name="reporte" property="socio" type="cl.araucana.bienestar.bonificaciones.model.Socio"/>
			</logic:notEmpty>
            <table width="530" border="0" cellpadding="1" cellspacing="1">
              <tr bgcolor="#F0F0F0">
                <td width="125"><p class="textoHeader1"><strong>RUT:</strong></p>
                </td>
                <td width="401"><p class="textoHeader1">
            <logic:notEmpty name="reporte" property="socio">
                <bean:write name="socio" property="fullRut"/>
            </logic:notEmpty>
             
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
            </table>
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
                <td><p><html:link page="/setFichaVale.do" paramId="codigoVale" paramName="register" paramProperty="codigoVale" target="_top"><bean:write name="register" property="codigoVale"/></html:link></p>
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
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Pr&eacute;stamos
                      Activos</strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr class="celdaColor2">
                <td width="119"><p class="vinculosUp">Monto</p>
                </td>
                <td width="108"><p class="vinculosUp"> Cuotas</p>
                </td>
                <td width="102"><p class="vinculosUp">Cuotas Cobradas</p>
                </td>
                <td width="123"><p class="vinculosUp">&Uacute;ltimo
                      Cobro</p>
                </td>
              </tr>

            <logic:notEmpty name="reporte" property="socio">
       		<logic:iterate id="register" name="reporte" property="prestamos" indexId="i">
              <tr>
                <td bgcolor="#F0F0F0"><p>$<bean:write name="register" property="monto" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="numeroCuotasTotales" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="cuotaActual" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fecha" formatKey="format.date"/></p>
                </td>
              </tr>

       		</logic:iterate>
            </logic:notEmpty>

            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Descuentos
                      Pendientes</strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr class="celdaColor2">
                <td width="115"><p class="vinculosUp">Monto</p>
                </td>
                <td width="210"><p class="vinculosUp"> Fecha Descuento</p>
                </td>
                <td width="118"><p class="vinculosUp">Estado</p></td>
                <td width="61"><p class="vinculosUp">Caso ID</p></td>
              </tr>
              <tr>
                <td bgcolor="#F0F0F0"><p>$ 100.000</p>
                </td>
                <td bgcolor="#F0F0F0"><p>8 de septiembre de 2003</p>
                </td>
                <td bgcolor="#F0F0F0"><p>Activo</p>
                </td>
                <td bgcolor="#F0F0F0"><p><a href="../../caso/caso.shtml">543525</a></p>
                </td>
              </tr>
              <tr bgcolor="#FFFFFF">
                <td><p>$ 3.500</p>
                </td>
                <td><p>7 de octubre de 2003</p>
                </td>
                <td><p>Activo</p>
                </td>
                <td><p><a href="../../caso/caso.shtml">678678</a></p>
                </td>
              </tr>
              <tr>
                <td bgcolor="#F0F0F0"><p>$ 8.000</p>
                </td>
                <td bgcolor="#F0F0F0"><p>18 de noviembre de 2003</p>
                </td>
                <td bgcolor="#F0F0F0"><p>Activo</p>
                </td>
                <td bgcolor="#F0F0F0"><p><a href="../../caso/caso.shtml">234234</a></p>
                </td>
              </tr>
              <tr bgcolor="#FFFFFF">
                <td><p>$ 9.300</p>
                </td>
                <td><p>21 de diciembre de 2003</p>
                </td>
                <td><p>Activo</p>
                </td>
                <td><p><a href="../../caso/caso.shtml">234652</a></p>
                </td>
              </tr>
            </table>
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
                <td width="208"><p class="vinculosUp">Fecha Reembolso</p></td>
                <td width="119"><p class="vinculosUp">Estado</p></td>
                <td width="58"><p class="vinculosUp">Caso ID</p>
                </td>
              </tr>

            <logic:notEmpty name="reporte" property="reembolsos">
       		<logic:iterate id="register" name="reporte" property="reembolsos" indexId="i">

              <tr>
                <td bgcolor="#F0F0F0"><p>$<bean:write name="register" property="montoReembolso" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fechaEstado" formatKey="format.date"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="estado" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoId" target="_top"><bean:write name="register" property="casoId"/></html:link></p>
                </td>
              </tr>

       		</logic:iterate>
            </logic:notEmpty>
			
            </table>

</body>
</html>
