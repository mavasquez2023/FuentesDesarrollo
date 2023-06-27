<%-- 
    Document   : creditosVigente
    Created on : 11-04-2012, 01:04:33 PM
    Author     : desajee
--%>

<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file = "/modulo2/includes/headhtml.jsp"%>
<body style="background:url(none)">
<div class="container_12">
  <div class="grid_12 omega">
    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td id="info"><img src="img/logo_certificados.gif" width="345" height="41" vspace="5" align="right" /><br />
<strong><bean:message key="label.consulta.creditos.titulo"/></strong>
<br/><br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<bean:write name="modulo2.fechahoy"/><br/><img src="img/separador_certificados.gif" width="97%" height="7" vspace="5" /><br />
<% /* inicio  info */ %>


<logic:empty name="creditosVigentes">
	<br>
	<bean:message key="label.consulta.creditos.noCreditosVigentes"/>	
</logic:empty>

<logic:notEmpty name="creditosVigentes">
	<table border='1' width='97%' cellspacing='0' cellpadding='0'>
	<tr>
		<td align='center'><bean:message key="label.consulta.creditos.calidadDeudor"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.otorgado"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.montoDescontar"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.numeroCuotas"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.primerDescuento"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.ultimoDescuento"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.cuotasPagadas"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.cuotasVigentes"/></td>
		<td align='center'><bean:message key="label.consulta.creditos.estado"/></td>		
	</tr>
	<logic:iterate id="register" name="creditosVigentes" type='cl.araucana.autoconsulta.vo.ConsultaCreditoVO'>
		<tr>
			<td align="center">
				<bean:message key='<%="traductor.credito.titularidad."+register.getTitularidad()%>'/>
			</td>
			<td align="center"><bean:write name="register" property="fechaOtorgamiento" formatKey="format.date"/></td>
			<td align="center"><bean:write name="register" property="montoCuota" formatKey="format.money"/></td>
			<td align="center"><bean:write name="register" property="numeroCuotas"/></td>
			<td align="center"><bean:write name="register" property="primerDescuento" formatKey="format.date"/></td>
			<td align="right"><bean:write name="register" property="ultimoDescuento" formatKey="format.date"/></td>
			<td align="right"><bean:write name="register" property="cuotasPagadas"/></td>
			<td align="right"><bean:write name="register" property="cuotasImpagas"/></td>
			<td align="center">
				<bean:message key='<%="traductor.estado.credito."+register.getCodigoEstadoPrestamo()%>'/>
			</td>
		</tr>
	</logic:iterate>
	</table>
</logic:notEmpty>

          <br /></td>
        </tr>
    </table>
  </div>
  <div style="clear:left;"></div>
</div>
</body>
</html>
