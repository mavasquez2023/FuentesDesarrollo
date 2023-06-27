<!-- TEST APP -->
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
			<div align='center'><h1><bean:message key="label.deuda.vigente.certificado.titulo"/></h1></div>
		</td>
	</tr>
</table>

<br>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td valign='top' width="25%">
<bean:message key="label.nombre"/>:
</td>
<td>
<bean:write name="nombre"/>
</td>
</tr>
<tr>
<td valign='top'>
<bean:message key="label.rut"/>:
</td>
<td>
<bean:write name="rut"/>
</td>
</tr>
</table>

<logic:notPresent name="tieneDeuda">
	<br>
	<br>
	<br>
	<br>
	<bean:message key="label.deuda.vigente.certificado.no.credito"/>
	
<logic:present name="comercial">
<script language="Javascript">
     window.open( 
"/AutoconsultaWeb/web/pages/deudaVigente/comercialCredito2.html?"+
"/AutoconsultaWeb/web/images/deudaVigente/comercialCredito.jpg", "", 
"resizable=1,HEIGHT=200,WIDTH=200");
</script>
</logic:present>
</logic:notPresent>

<logic:present name="tieneDeuda">
<br>
<br>
<br>
<bean:message key="label.deuda.vigente.certificado.segunDetalle"/>
<bean:define id="creditosDirectos" name="deudaVigente" property="creditosDirectos"/>
<br>
<br>
<logic:notEmpty name="creditosDirectos">
	<div align='center'><b><bean:message key="label.deuda.vigente.certificado.deudor.titular"/></b></div>
	<br>
	<table border='1' width='100%' cellspacing='0' cellpadding='0'>
	<tr>
		<td class="certificado" rowspan='2' align='center' valign='middle' width='75'><bean:message key="label.deuda.vigente.certificado.folio"/></td>
		<td class="certificado" rowspan='2' align='center' valign='middle' width='59'><bean:message key="label.deuda.vigente.certificado.vence"/></td>
		<td class="certificado" colspan='2' align='center' width='91'><bean:message key="label.deuda.vigente.certificado.cuotas"/></td>
		<td class="certificado" colspan='3' align='center'><bean:message key="label.deuda.vigente.certificado.saldos"/></td>
		<td class="certificado" rowspan='2' align='center'><bean:message key="label.deuda.vigente.certificado.observaciones"/></td>
	</tr>
	<tr>
		<td class="certificado" align='center' width='40'><bean:message key="label.deuda.vigente.certificado.cuotas.impagas"/></td>
		<td class="certificado" align='center' width='45'><bean:message key="label.deuda.vigente.certificado.cuotas.vigentes"/></td>
		<td class="certificado" align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.impagos"/></td>
		<td class="certificado" align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.vigentes"/></td>
		<td class="certificado" align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.total"/></td>
	<tr>
	<logic:iterate id="register" name="creditosDirectos" type='cl.araucana.autoconsulta.vo.InformacionCreditoVO'>
		<tr>
			<td class="certificado" align="center"><bean:write name="register" property="oficinaFolio"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="vencimiento"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="cuotasImpagas"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="cuotasVigentes"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="saldoImpago" formatKey="format.money"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="saldoVigente" formatKey="format.money"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="saldoTotal" formatKey="format.money"/></td>
			<td class="certificado" nowrap>
				<bean:message key="label.deuda.vigente.certificado.desde"/>:
				<bean:write name="register" property="rangoCuotasVigentes"/>
				<%
				if(register.getTipoOperacion()==4 || register.getTipoOperacion()==5 || register.getTipoOperacion()==6 || register.getTipoOperacion()==7 || register.getTipoOperacion()==8 || register.getTipoOperacion()==9) {%>
					<br>
					<bean:message key="label.deuda.vigente.certificado.intermediado.con"/>:
					<br>
					<bean:message key='<%="traductor.tipoOperacion.credito."+register.getTipoOperacion()%>'/>
				<% } %>
			</td>
		</tr>
	</logic:iterate>
		<tr>
			<td class="certificado" colspan='6' align='right'><bean:message key="label.deuda.vigente.certificado.saldo.total.titular"/></td>
			<td class="certificado" align="right"><bean:write name="deudaVigente" property="saldoTotalTitular" formatKey="format.money"/></td>
		</tr>
	</table>
</logic:notEmpty>

<bean:define id="creditosIndirectos" name="deudaVigente" property="creditosIndirectos"/>
<br>
<br>
<logic:notEmpty name="creditosIndirectos">
	<div align='center'><b><bean:message key="label.deuda.vigente.certificado.deudor.avalista"/></b></div>
	<br>
	<table border='1' width='100%' cellspacing='0' cellpadding='0'>
	<logic:iterate id="register" name="creditosIndirectos" type='cl.araucana.autoconsulta.vo.InformacionCreditoVO'>
		<bean:define id="datosTitular" name="register" property="datosTitular"/>
		<tr>
			<td class="certificado" align='left' width='75' valign='top'>
				<bean:message key="label.deuda.vigente.certificado.indirecta.rut"/>
				<br>
				<bean:write name="datosTitular" property="fullRut"/>
			</td>
			<td class="certificado" align='left' colspan='7' valign='top'>
				<bean:message key="label.deuda.vigente.certificado.indirecta.nombre"/>
				<br>
				<bean:write name="datosTitular" property="fullNombre"/>
			</td>
		</tr>
		<tr>
			<td class="certificado" rowspan='2' align='center' valign='middle' width='75'><bean:message key="label.deuda.vigente.certificado.folio"/></td>
			<td class="certificado" rowspan='2' align='center' valign='middle' width='59'><bean:message key="label.deuda.vigente.certificado.vence"/></td>
			<td class="certificado" colspan='2' align='center' width='91'><bean:message key="label.deuda.vigente.certificado.cuotas"/></td>
			<td class="certificado" colspan='3' align='center'><bean:message key="label.deuda.vigente.certificado.saldos"/></td>
			<td class="certificado" rowspan='2' align='center'><bean:message key="label.deuda.vigente.certificado.observaciones"/></td>
		</tr>
		<tr>
			<td class="certificado" align='center' width='40'><bean:message key="label.deuda.vigente.certificado.cuotas.impagas"/></td>
			<td class="certificado" align='center' width='45'><bean:message key="label.deuda.vigente.certificado.cuotas.vigentes"/></td>
			<td class="certificado" align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.impagos"/></td>
			<td class="certificado" align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.vigentes"/></td>
			<td class="certificado" align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.total"/></td>
		<tr>
		<tr>
			<td class="certificado" align="center"><bean:write name="register" property="oficinaFolio"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="vencimiento"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="cuotasImpagas"/></td>
			<td class="certificado" align="center"><bean:write name="register" property="cuotasVigentes"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="saldoImpago" formatKey="format.money"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="saldoVigente" formatKey="format.money"/></td>
			<td class="certificado" align="right"><bean:write name="register" property="saldoTotal" formatKey="format.money"/></td>
			<td class="certificado" nowrap>
				<%
				if(register.isDeudaNominal()) {%>
					<bean:message key="label.deuda.vigente.certificado.deuda.nominal"/>:
					<br>
				<% } %>
				<bean:message key="label.deuda.vigente.certificado.desde"/>:
				<bean:write name="register" property="rangoCuotasVigentes"/>
				<%
				if(register.getTipoOperacion()==4 || register.getTipoOperacion()==5 || register.getTipoOperacion()==6 || register.getTipoOperacion()==7 || register.getTipoOperacion()==8 || register.getTipoOperacion()==9) {%>
					<br>
					<bean:message key="label.deuda.vigente.certificado.intermediado.con"/>:
					<br>
					<bean:message key='<%="traductor.tipoOperacion.credito."+register.getTipoOperacion()%>'/>
				<% } %>
			</td>
		<tr>
	</logic:iterate>
	<tr>
		<td class="certificado" colspan='6' align='right'><bean:message key="label.deuda.vigente.certificado.saldo.total.aval"/></td>
		<td class="certificado" align="right"><bean:write name="deudaVigente" property="saldoTotalAval" formatKey="format.money"/></td>
	</tr>
	</table>
</logic:notEmpty>


<br>
<br>
<bean:message key="label.deuda.vigente.certificado.gravamenes"/>
<br>
<br>
<bean:message key="label.deuda.vigente.certificado.pago.anticipado"/>
</logic:present>
<br>
<br>
<bean:message key="label.deuda.vigente.certificado.extiende"/>
<br>
<br>
<br>
<br>

<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td class="certificado" nowrap>
		<bean:write name="deudaVigente" property="fechaHoy" formatKey="format.fullDate"/>
		</td>
		<td class="certificado" align='right'>
		<img src="/AutoconsultaWeb/web/images/deudaVigente/CopiadefirmaCredito.gif">
		</td>
	</tr>
	<tr>
		<td>&nbsp</td>
	</tr>
	<tr>
		<td class="certificado" colspan="2">
			<bean:message key="label.validador.id"/>: <bean:write name="deudaVigente" property="id"/><br>
			<bean:message key="label.validador.mensaje"/>
		</td>
	</tr>
</table>
<br>
<br>

</font>