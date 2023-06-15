<font class="certificado">
<table border='0' cellspacing='0' width='100%'>
<!--
	<tr>
		<td align='right' nowrap class="certificado">
			<img src="/AutoconsultaWeb/web/images/logoCertificado.jpg">
		</td>
	</tr>
-->

	<tr>
		<td nowrap class="certificado">
			<div align='center'><h1><bean:message key="label.consulta.cheques.titulo"/></h1></div>
		</td>
	</tr>
</table>

<br>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td class="certificado">
<bean:message key="label.nombre"/>:
</td>
<td class="certificado">
<bean:write name="nombre"/>
</td>
</tr>
<tr>
<td class="certificado">
<bean:message key="label.rut"/>:
</td>
<td class="certificado">
<bean:write name="rut"/>
</td>
</tr>
</table>

<logic:empty name="cheques">
	<br>
	<br>
	<br>
	<br>
	<bean:message key="label.consulta.cheques.no.cheques"/>
</logic:empty>

<logic:notEmpty name="cheques">
<br>
<br>
<br>
<bean:message key="label.consulta.cheques.nota"/>
<br>
<br>
<br>
	<table border='1' width='100%' cellspacing='0' cellpadding='0'>
	<tr>
		<td class="certificado"><bean:message key="label.consulta.cheques.concepto"/></td>
		<td class="certificado"><bean:message key="label.consulta.cheques.monto"/></td>
		<td class="certificado"><bean:message key="label.consulta.cheques.periodo"/></td>
		<td class="certificado"><bean:message key="label.consulta.cheques.estado.cheque"/></td>
		<td class="certificado"><bean:message key="label.consulta.cheques.forma.pago"/></td>
		<td class="certificado"><bean:message key="label.consulta.cheques.fecha.pago"/></td>
		<td class="certificado"><bean:message key="label.consulta.cheques.sucursal"/></td>
	</tr>
	<logic:iterate id="register" name="cheques" type='cl.araucana.autoconsulta.vo.ChequeVO'>
		<tr>
			<td class="certificado"><bean:write name="register" property="conceptoDespliegue"/></td>
			<td class="certificado"><bean:write name="register" property="monto" formatKey="format.money"/></td>
			<td class="certificado"><bean:write name="register" property="periodo" formatKey="format.date.periodo"/></td>
			<td class="certificado"><bean:message key='<%="traductor.estado.cheque."+register.getCodigoEstadoCheque()%>'/></td>
			<td class="certificado"><bean:message key='<%="traductor.forma.pago."+register.getCodigoFormaPago()%>'/></td>
			<td class="certificado"><bean:write name="register" property="fechaPago" formatKey="format.date"/></td>			
			<bean:define id="sucursal" name="register" property="sucursal" type='java.lang.String'/>
			<%if(sucursal!=null && sucursal.length()>0) {%>
				<td class="certificado"><bean:write name="register" property="sucursal"/></td>
			<%} else { %>
				 	<td class="certificado">&nbsp</td>
			<% } %>
			
		</tr>
	</logic:iterate>
	</table>
</logic:notEmpty>
<br>
<br>
<br>
<br>

<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td class="certificado" nowrap>
		<bean:write name="fechaHoy" formatKey="format.fullDate"/>
	</tr>
</table>


