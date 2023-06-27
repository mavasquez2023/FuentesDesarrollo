<%@ include file="/html/comun/taglibs.jsp" %>
<table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
  <tr>
    <td colspan="2"><div align="center"><b>Informaci&oacute;n N&oacute;mina </b></div></td>
  </tr>
  <tr>
    <td><b>Fecha Envio</b></td>
    <td><bean:write name="infoNomina" property="fechaEnvio" format="dd/MM/yyyy"/> <bean:write name="infoNomina" property="fechaEnvio" format="HH:mm"/></td>
  </tr>
  <tr>
    <td><b>Fecha Recibida</b></td>
    <td><bean:write name="infoNomina" property="fechaRecibido" format="dd/MM/yyyy"/> <bean:write name="infoNomina" property="fechaRecibido" format="HH:mm"/></td>
  </tr>
  <tr>
    <td><b>Usuario</b></td>
    <td><bean:write name="infoNomina" property="formatRutEncargado"/></td>
  </tr>
  <tr>
    <td><b>Tama&ntilde;o</b></td>
    <td><b>Normal :</b> <bean:write name="infoNomina" property="normalSize"/> Bytes - <b>Comprimido :</b> <bean:write name="infoNomina" property="comprimidoSize"/> Bytes</td>
  </tr>
  <tr>
    <td><b>Cantidad Trabajadores con Errores</b></td>
    <td><bean:write name="infoNomina" property="totalErroneos"/></td>
  </tr>
  <tr>
    <td><b>Total Trabajadores </b></td>
    <td><bean:write name="infoNomina" property="totalTrabajadores"/></td>
  </tr>
  <tr>
	<td colspan="2">&nbsp;</td>
  </tr>	  
  <tr> 
	<td height="4" colspan="2" bgcolor="#85b4be"></td>
  </tr>
</table>