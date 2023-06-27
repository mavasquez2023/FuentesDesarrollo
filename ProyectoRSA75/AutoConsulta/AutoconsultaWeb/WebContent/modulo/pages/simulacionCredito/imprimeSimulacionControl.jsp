<%@ include file = "/modulo/includes/env.jsp"%> 
<% isPrinting = true; %>
<%@ include file = "/modulo/includes/header.jsp"%>
 
<div align='left'>

<table border="0" cellpadding="0" cellspacing="0" width="80%">

	<tr>
		<td align='right' nowrap class="certificado">
			<img src="/AutoconsultaWeb/modulo/images/logoCertificado.jpg">
		</td>
	</tr>

	<tr>
		<td align="left">
			<%@ include file = "/modulo/pages/simulacionCredito/cuerpoSimulacion.jsp"%>
			<br>
		</td>
	<tr>
		<td class="certificado" align="right">
			<bean:write name="fechaHoy" formatKey="format.fullDate"/>
		</td>
	</tr>
</table>
</div>