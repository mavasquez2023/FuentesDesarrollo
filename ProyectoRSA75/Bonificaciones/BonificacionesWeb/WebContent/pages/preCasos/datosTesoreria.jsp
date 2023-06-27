<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
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
    	    <td class="caminito"><%@ include file="/includes/camino.jsp"%>
	        <logic:equal name="caso" property="estado" value="PRECASO">
	            <html:link page="/prepareListaPreCasos.do" target="_top">Lista de Pre-Casos</html:link> &gt; Detalle Movimientos</td>
	        </logic:equal>
	        <logic:notEqual name="caso" property="estado" value="PRECASO">
	        	<html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; Caso
			</logic:notEqual>
	      </tr>
    	</table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">
          
          <br>
		  <br>
          
		  <h4 class="txtHomeSmall">Detalle de Movimiento(s)</h4>
		            
	        <table width="529" border="0" cellspacing="2" cellpadding="2">
	          <tr>
	            <td class="celdaColor1"><p class="vinculosUp">Caso Id</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Folio Tesoreria</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Fecha Movimiento</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Usuario</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Tipo Movimiento</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Rut</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Nombre</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Monto</p></td>
	            <td width="19">&nbsp;</td>
	          </tr>
	      		<logic:iterate id="register" name="listaMovimientosTesoreria" type="cl.araucana.bienestar.bonificaciones.vo.InfoMovimientoPreCasoVO">
	              <tr class="lookup01">
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="casoId"/></td>
	                <td bgcolor="#F0F0F0" nowrap><bean:write name="register" property="folioTesoreria"/></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="fecha" formatKey="format.date"/></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="usuario"/></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="tipoMovimiento"/></td>
	                <td bgcolor="#F0F0F0" nowrap><bean:write name="register" property="fullRut"/></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="nombre"/></td>
	                <td bgcolor="#F0F0F0" nowrap>$<bean:write name="register" property="monto" formatKey="format.int"/></td>
	              </tr>
	            </logic:iterate>
	            
	            <tr>
	            	<td>
	            		&nbsp;
	            	</td>
	            </tr>
	            
				<tr>
					<td colspan='8' align="center">
				        <logic:equal name="caso" property="estado" value="PRECASO">
							<p class="centrado"><html:link page='/prepareListaPreCasos.do'>Volver</html:link></p>
				        </logic:equal>
				        <logic:notEqual name="caso" property="estado" value="PRECASO">
                			<p class="centrado"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="caso" paramProperty="casoID" target="_top">Volver</html:link></p>
						</logic:notEqual>
					</td>
				</tr>
	        </table>
	        
          	<br>
			<br>
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
