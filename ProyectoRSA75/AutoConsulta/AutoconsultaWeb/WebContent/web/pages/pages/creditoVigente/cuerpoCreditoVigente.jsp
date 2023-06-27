<font class="certificado">
  
<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td align='right' nowrap class="certificado">
			<img src="/AutoconsultaWeb/web/images/logoCertificado.jpg">
		</td>
	</tr>
	<tr>
		<td nowrap class="certificado">
			<div align='center'><font size='3'><b><bean:message key="label.consulta.creditos.titulo"/></b></font></div>
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
<logic:notEmpty name="empleadores">
	<tr>
		<td valign="top" class="certificado"><bean:message key="label.empleador"/>:</td>
		<td class="certificado">
		<logic:iterate id="register" name="empleadores">
			<bean:write name="register" property="nombreRut"/><br>
		</logic:iterate>
		</td>
	</tr>
</logic:notEmpty>

</table>

<br>
<br>

<logic:empty name="creditosVigentes">
	<br>
	<br>
	<bean:message key="label.consulta.creditos.noCreditosVigentes"/>
	
	<logic:present name="comercial">
		<script language="Javascript">
     		window.open( 
				"/AutoconsultaWeb/web/pages/deudaVigente/comercialCredito2.html?"+
				"/AutoconsultaWeb/web/images/deudaVigente/comercialCredito.jpg", "", 
				"resizable=1,HEIGHT=200,WIDTH=200");
		</script>
	</logic:present>
</logic:empty>

<logic:notEmpty name="creditosVigentes">
	<table border='1' width='100%' cellspacing='0' cellpadding='0'>
	<tr>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.calidadDeudor"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.otorgado"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.montoDescontar"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.numeroCuotas"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.primerDescuento"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.ultimoDescuento"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.cuotasPagadas"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.cuotasVigentes"/></td>
		<td class="certificado" align='center'><bean:message key="label.consulta.creditos.estado"/></td>		
	</tr>
	<logic:iterate id="register" name="creditosVigentes" type='cl.araucana.autoconsulta.vo.ConsultaCreditoVO'>
		<tr>
			<td class="certificado" align="center">
				<bean:message key='<%="traductor.credito.titularidad."+register.getTitularidad()%>'/>
			</td>
			<td class="certificado" align="center"><bean:write name="register" property="fechaOtorgamiento" formatKey="format.date"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="montoCuota" formatKey="format.money"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="numeroCuotas"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="primerDescuento" formatKey="format.date"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="ultimoDescuento" formatKey="format.date"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="cuotasPagadas"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="cuotasImpagas"/></td>
			<td class="certificado" align="center">
				<bean:message key='<%="traductor.estado.credito."+register.getCodigoEstadoPrestamo()%>'/>
			</td>
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