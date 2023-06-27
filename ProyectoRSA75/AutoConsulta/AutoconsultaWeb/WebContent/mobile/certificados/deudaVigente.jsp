<%-- 
    Document   : deudaVigente
    Created on : 10-04-2012, 07:08:15 PM
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

<strong><bean:message key="label.deuda.vigente.certificado.titulo"/></strong>
<br/><br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<bean:write name="modulo2.fechahoy"/><br/><img src="<%=getPath %>img/separador_certificados.gif" width="100%" height="7" vspace="5" /><br />
<logic:notPresent name="tieneDeuda">
	<br/>
	<bean:message key="label.deuda.vigente.certificado.no.credito"/>
	
</logic:notPresent>

<logic:present name="tieneDeuda">
<bean:message key="label.deuda.vigente.certificado.segunDetalle"/>
<bean:define id="creditosDirectos" name="deudaVigente" property="creditosDirectos"/>
<logic:notEmpty name="creditosDirectos">
	<div align='center'><b><bean:message key="label.deuda.vigente.certificado.deudor.titular"/></b></div>
	<br/>
	<table border='1' width='100%' cellspacing='0' cellpadding='0'>
	<tr>
		<td rowspan='2' align='center' valign='middle' width='75'><bean:message key="label.deuda.vigente.certificado.folio"/></td>
		<td rowspan='2' align='center' valign='middle' width='59'><bean:message key="label.deuda.vigente.certificado.vence"/></td>
		<td colspan='2' align='center' width='91'><bean:message key="label.deuda.vigente.certificado.cuotas"/></td>
		<td colspan='3' align='center'><bean:message key="label.deuda.vigente.certificado.saldos"/></td>
		<td rowspan='2' align='center'><bean:message key="label.deuda.vigente.certificado.observaciones"/></td>
	</tr>
	<tr>
		<td align='center' width='40'><bean:message key="label.deuda.vigente.certificado.cuotas.impagas"/></td>
		<td align='center' width='45'><bean:message key="label.deuda.vigente.certificado.cuotas.vigentes"/></td>
		<td align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.impagos"/></td>
		<td align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.vigentes"/></td>
		<td align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.total"/></td>
	</tr>
	<logic:iterate id="register" name="creditosDirectos" type='cl.araucana.autoconsulta.vo.InformacionCreditoVO'>
		<tr>
			<td align="center"><bean:write name="register" property="oficinaFolio"/></td>
			<td align="center"><bean:write name="register" property="vencimiento"/></td>
			<td align="center"><bean:write name="register" property="cuotasImpagas"/></td>
			<td align="center"><bean:write name="register" property="cuotasVigentes"/></td>
			<td align="right"><bean:write name="register" property="saldoImpago" formatKey="format.money"/></td>
			<td align="right"><bean:write name="register" property="saldoVigente" formatKey="format.money"/></td>
			<td align="right"><bean:write name="register" property="saldoTotal" formatKey="format.money"/></td>
			<td >
				<bean:message key="label.deuda.vigente.certificado.desde"/>:
				<bean:write name="register" property="rangoCuotasVigentes"/>
				<%
				if(register.getTipoOperacion()==4 || register.getTipoOperacion()==5 || register.getTipoOperacion()==6 || register.getTipoOperacion()==7 || register.getTipoOperacion()==8 || register.getTipoOperacion()==9) {%>
					<br/>
					<bean:message key="label.deuda.vigente.certificado.intermediado.con"/>:
					<br/>
					<bean:message key='<%="traductor.tipoOperacion.credito."+register.getTipoOperacion()%>'/>
				<% } %>
			</td>
		</tr>
	</logic:iterate>
		<tr>
			<td colspan='6' align='right'><bean:message key="label.deuda.vigente.certificado.saldo.total.titular"/></td>
			<td align="right"><bean:write name="deudaVigente" property="saldoTotalTitular" formatKey="format.money"/></td>
		</tr>
	</table>
</logic:notEmpty>

<bean:define id="creditosIndirectos" name="deudaVigente" property="creditosIndirectos"/>
<br/>
<br/>
<logic:notEmpty name="creditosIndirectos">
	<div align='center'><b><bean:message key="label.deuda.vigente.certificado.deudor.avalista"/></b></div>
	<br/>
	<table border='1' width='100%' cellspacing='0' cellpadding='0'>
	<logic:iterate id="register" name="creditosIndirectos" type='cl.araucana.autoconsulta.vo.InformacionCreditoVO'>
		<bean:define id="datosTitular" name="register" property="datosTitular"/>
		<tr>
			<td align='left' width='75' valign='top'>
				<bean:message key="label.deuda.vigente.certificado.indirecta.rut"/>
				<br/>
				<bean:write name="datosTitular" property="fullRut"/>
			</td>
			<td align='left' colspan='7' valign='top'>
				<bean:message key="label.deuda.vigente.certificado.indirecta.nombre"/>
				<br/>
				<bean:write name="datosTitular" property="fullNombre"/>
			</td>
		</tr>
		<tr>
			<td rowspan='2' align='center' valign='middle' width='75'><bean:message key="label.deuda.vigente.certificado.folio"/></td>
			<td rowspan='2' align='center' valign='middle' width='59'><bean:message key="label.deuda.vigente.certificado.vence"/></td>
			<td colspan='2' align='center' width='91'><bean:message key="label.deuda.vigente.certificado.cuotas"/></td>
			<td colspan='3' align='center'><bean:message key="label.deuda.vigente.certificado.saldos"/></td>
			<td rowspan='2' align='center'><bean:message key="label.deuda.vigente.certificado.observaciones"/></td>
		</tr>
		<tr>
			<td align='center' width='40'><bean:message key="label.deuda.vigente.certificado.cuotas.impagas"/></td>
			<td align='center' width='45'><bean:message key="label.deuda.vigente.certificado.cuotas.vigentes"/></td>
			<td align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.impagos"/></td>
			<td align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.vigentes"/></td>
			<td align='center' width='60'><bean:message key="label.deuda.vigente.certificado.saldos.total"/></td>
		</tr>
		<tr>
			<td align="center"><bean:write name="register" property="oficinaFolio"/></td>
			<td align="center"><bean:write name="register" property="vencimiento"/></td>
			<td align="center"><bean:write name="register" property="cuotasImpagas"/></td>
			<td align="center"><bean:write name="register" property="cuotasVigentes"/></td>
			<td align="right"><bean:write name="register" property="saldoImpago" formatKey="format.money"/></td>
			<td align="right"><bean:write name="register" property="saldoVigente" formatKey="format.money"/></td>
			<td align="right"><bean:write name="register" property="saldoTotal" formatKey="format.money"/></td>
			<td >
				<%
				if(register.isDeudaNominal()) {%>
					<bean:message key="label.deuda.vigente.certificado.deuda.nominal"/>:
					<br/>
				<% } %>
				<bean:message key="label.deuda.vigente.certificado.desde"/>:
				<bean:write name="register" property="rangoCuotasVigentes"/>
				<%
				if(register.getTipoOperacion()==4 || register.getTipoOperacion()==5 || register.getTipoOperacion()==6 || register.getTipoOperacion()==7 || register.getTipoOperacion()==8 || register.getTipoOperacion()==9) {%>
					<br/>
					<bean:message key="label.deuda.vigente.certificado.intermediado.con"/>:
					<br/>
					<bean:message key='<%="traductor.tipoOperacion.credito."+register.getTipoOperacion()%>'/>
				<% } %>
			</td>
		</tr>
	</logic:iterate>
	<tr>
		<td colspan='6' align='right'><bean:message key="label.deuda.vigente.certificado.saldo.total.aval"/></td>
		<td align="right"><bean:write name="deudaVigente" property="saldoTotalAval" formatKey="format.money"/></td>
	</tr>
	</table>
</logic:notEmpty>


<br/>
<br/>
<bean:message key="label.deuda.vigente.certificado.gravamenes"/>
<br/>
<br/>
<bean:message key="label.deuda.vigente.certificado.pago.anticipado"/>
</logic:present>
<br/>
<br/>
<bean:message key="label.deuda.vigente.certificado.extiende"/>
<br/>
<br/>
<br/>
<br/>

<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td >
		<bean:write name="deudaVigente" property="fechaHoy" formatKey="format.fullDate"/>
		</td>
		<td align='right'>
		<img src="/AutoconsultaWeb/modulo/images/deudaVigente/CopiadefirmaCredito.gif"/>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">
			<bean:message key="label.validador.id"/>: <bean:write name="deudaVigente" property="id"/><br/>
			<bean:message key="label.validador.mensaje"/>
		</td>
	</tr>
</table>

          <br /></td>
        </tr>
    </table>
</div>
</body>
</html>
