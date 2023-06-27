 
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
			<div align='center'><h1><bean:message key="label.afiliacion.certificado.titulo"/></h1></div>
		</td>
	</tr>
</table>
 
 
<br>
<div align="center"><bean:message key="label.afiliacion.certificado.subtitulo"/></div>
<br><br>										 
<bean:write name="nombre"/>, <bean:message key="label.afiliacion.certificado.rut"/> <bean:write name="rut"/> <bean:message key="label.afiliacion.certificado.afiliado"/> <bean:write name="tipo"/> <bean:message key="label.afiliacion.certificado.afiliado.activo"/> <bean:write name="fechaDesde"/>
  <bean:message key="label.punto"/>
<br>
<br>
<bean:message key="label.afiliacion.certificado.certificado"/>
<br>
<br>
<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td nowrap class="certificado">
		Santiago, <bean:write name="fechaHoy" formatKey="format.fullDate"/>
		</td>
		<td align='right' class="certificado">
		<img src="/AutoconsultaWeb/web/images/asignacionFamiliar/CopiadefirmaSIL.gif">
		</td>
	</tr>	
</table>
<br>
<br>

</font>
