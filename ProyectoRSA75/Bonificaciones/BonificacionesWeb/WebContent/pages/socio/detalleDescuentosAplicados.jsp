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
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Detalle Descuentos Socio</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">

			<%@ include file="/includes/headerSocio.jsp" %>
			<br>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="150" class="celdaColor1"><p class="vinculosUp">Descuento</p>
                </td>
                <td width="115" class="celdaColor1"><p class="vinculosUp">Cuota Actual</p>
                </td>
                <td width="115" class="celdaColor1"><p class="vinculosUp">Total Cuotas</p>
                </td>
                <td width="115" class="celdaColor1"><p class="vinculosUp">Monto</p>
                </td>
                </tr>

              <logic:iterate id="detalle" name="reporte" property="casosDescontados" indexId="t">
				<tr>
					<td><p>
					<logic:empty name="print">
						<html:link page="/setFichaCaso.do" paramId="codigo" paramName="detalle" paramProperty="casoId">Caso <bean:write name="detalle" property="casoId"/></html:link>
					</logic:empty>
					<logic:notEmpty name="print">
						Caso <bean:write name="detalle" property="casoId"/>
					</logic:notEmpty>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="cuotaActual" formatKey="format.int"/></html:link>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="numeroCuotas" formatKey="format.int"/>
						</p>
					</td>
					<td><p>
						$<bean:write name="detalle" property="montoDescuento" formatKey="format.int"/>
						</p>
					</td>
				</tr>
			</logic:iterate>

              <logic:iterate id="detalle" name="reporte" property="cuotasPrestamos" indexId="t" >
				<tr>
					<td><p>
						<bean:write name="detalle" property="descripcionPrestamo"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="cuotaActual" formatKey="format.int"/></html:link>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="numeroCuotasTotales" formatKey="format.int"/>
						</p>
					</td>
					<td><p>
						$<bean:write name="detalle" property="monto" formatKey="format.int"/>
						</p>
					</td>
				</tr>
			</logic:iterate>

                <tr>
                	<td>
                		
                	</td>
                	<td>
                	</td>
                	<td>
                		<p><strong>Total de Descuentos: </strong></p>
                	</td> 
                	<td>
						<P><strong>$<bean:write name="reporte" property="montoDescuentoTotal" formatKey="format.int"/></strong></p>
                	</td> 
                </tr>

            </table>

			</logic:iterate>
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
