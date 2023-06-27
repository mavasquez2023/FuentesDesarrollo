<%-- 
    Document   : liquidacionReembolsos
    Created on : 11-04-2012, 01:04:56 PM
    Author     : desajee
--%>

<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file = "/mobile/includes/headhtml.jsp"%>
<body style="background:url(none)">
<div class="container_12">
  <div class="grid_12 omega">
    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td id="info"><img src="img/logo_certificados.gif" width="345" height="41" vspace="5" align="right" /><br />
<strong><bean:message key="label.liquidacion.empresa.titulo1"/></strong>
<br/><br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<bean:write name="modulo2.fechahoy"/><br/><img src="img/separador_certificados.gif" width="932" height="7" vspace="5" /><br />


<logic:empty name="lista.liquidaciones">
	<bean:message key="label.liquidacion.sin.lquidacion"/>
</logic:empty>
<logic:notEmpty name="lista.liquidaciones">
<table border="0" cellpadding="2" cellspacing="2" width="80%">
		<tr>
			<td nowrap> 
				<bean:message key="label.liquidacion.seleccion"/>:
			</td>
			<td>
			  	<html:select property="nroliq" styleClass="combo">
    			    <html:options collection="lista.liquidaciones" labelProperty="fechaLiquidacion"  property="nroLiquidacion"/>
		        </html:select>
			</td>
			<td colspan='2' align='center'>
				<html:image page='/images/aceptar.gif'/>
			</td>
		</tr>
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
