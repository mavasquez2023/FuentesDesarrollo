<%-- 
    Document   : afiliacion
    Created on : 10-04-2012, 07:08:30 PM
    Author     : desajee
--%>
<%@ include file = "/modulo2/includes/headjsp.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file = "/mobile/includes/headhtml.jsp"%>
<body style="background:url(none)">
<div style="width:700px;margen:5px;">
    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td id="info"><img src="<%=getPath %>img/logo_certificados.gif" width="345" height="41" vspace="5" align="right" /><br />

<strong><bean:message key="label.afiliacion.certificado.titulo"/></strong>
<br/><br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<bean:write name="modulo2.fechahoy"/><br/><img src="<%=getPath %>img/separador_certificados.gif" width="100%" height="7" vspace="5" /><br />
<logic:present name="tipo">
<div align="center"><bean:message key="label.afiliacion.certificado.subtitulo"/></div>
<bean:write name="afiliado.nombre"/>, <bean:message key="label.afiliacion.certificado.rut"/> 
  <bean:write name="afiliado.fullRut"/> <bean:message key="label.afiliacion.certificado.afiliado"/> 
  <bean:write name="tipo"/> <bean:message key="label.afiliacion.certificado.afiliado.activo"/> 
  <bean:write name="fechaDesde"/>
  <bean:message key="label.punto"/>
<br/>
<br/>
<bean:message key="label.afiliacion.certificado.certificado"/>
<br/>
<br/>
<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td>
		Santiago, <bean:write name="fechaHoy" formatKey="format.fullDate"/>
		</td>
		<td align='right' class="certificado">
		<img src="/AutoconsultaWeb/web/images/asignacionFamiliar/CopiadefirmaSIL.gif"/>
		</td>
	</tr>	
</table>
</logic:present>
<logic:present name="message"> 
<div style="border:1px solid red; ">
<bean:write name="message"/><br/>
<bean:write name="info"/><br/>
</div>
</logic:present>

          <br /></td>
        </tr>
    </table>
</div>
</body>
</html>
