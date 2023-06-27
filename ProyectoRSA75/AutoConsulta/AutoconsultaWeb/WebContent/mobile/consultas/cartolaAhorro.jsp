<%-- 
    Document   : cartolaAhorro
    Created on : 11-04-2012, 01:04:22 PM
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
<strong><bean:message key="label.cartola.titulo"/></strong>
<br/><br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<bean:write name="modulo2.fechahoy"/><br/><img src="img/separador_certificados.gif" width="97%" height="7" vspace="5" /><br />


<logic:notPresent name="cartola"  property="fullNumeroCuenta">
<div class="alerta">
  <bean:message key="label.cartola.sin.cuentas"/>
</div>
</logic:notPresent>

<logic:present name="cartola"  property="fullNumeroCuenta">

<table border='1' cellpadding='0' cellspacing='0' width='97%'>
<tbody>
	<tr>
		<td>
			<b><bean:message key="label.nombre"/></b>
			<br>
			<bean:write name="afiliado.nombre"/>
		</td>
		<td>
			<b><bean:message key="label.rut"/><br/></b>
			<bean:write name="afiliado.rut"/>
		</td>
		<td>
			<b><bean:message key="label.cartola.numero.cuenta"/></b>
			<br>
			<bean:write name="cartola" property="fullNumeroCuenta"/>
		</td>
	</tr>
</tbody>
</table>

<br>



<table border='1' cellpadding='0' cellspacing='0' width='97%'>
	<tr>
		<td>
			<b><bean:message key="label.cartola.direccion"/></b>
			<br>
			<bean:write name="cartola" property="direccion"/>

		</td>
		<td>
			<b><bean:message key="label.cartola.comuna"/></b>
			<br>
			<bean:write name="cartola" property="comuna"/>
		</td>
		<td>
			<b><bean:message key="label.cartola.ciudad"/></b>
			<br>
			<bean:write name="cartola" property="ciudad"/>
		</td>
	</tr>
</table>

<br>
<b><bean:message key="label.cartola.anteror"/></b>
<bean:define id= "fechaUltCartola" name="cartola" property="fechaUltCartola" type='java.lang.String'/>
			
<table border='1' cellpadding='0' cellspacing='0' width='97%'>
	<tr>
	<% System.out.println("3"); %>
		<td>
			<b><bean:message key="label.cartola.fecha"/></b>
			<br>
			<%= fechaUltCartola.substring(6,8) + "/" + fechaUltCartola.substring(4,6) + "/" + fechaUltCartola.substring(0,4)%>
		</td>
		<td class="certificado" width='151'>
			<B>
			<% System.out.println("4"); %>
			<bean:message key="label.cartola.saldo.cuotas"/>
			<br>
			</B>
			<% System.out.println("5"); %>
			<bean:write name="cartola" property="ultSaldoCuotas" format='<%= float5FormatPatt %>'/>
		</td>
		<td class="certificado" width='143'>
					<% System.out.println("51"); %>
			<B><bean:message key="label.cartola.valor.cuota"/></B>
			<br>
						<% System.out.println("52"); %>
			<bean:write name="cartola" property="ultValorCuota" format='<%= float3FormatPatt %>'/>
		</td>
		<td class="certificado" width='143'>
					<% System.out.println("53"); %>
			<B><bean:message key="label.cartola.saldo.pesos"/></B>
			<br>
			<% System.out.println("6"); %>
			<bean:write name="cartola" property="ultSaldoPesos" formatKey="format.money"/>
		</td>
	</tr>
</table>

<bean:define id="detalles" name="cartola" property="detalles"/>

<br>
<B><bean:message key="label.cartola.movimientos.periodo"/></B>

<table border='1' cellpadding='0' cellspacing='0' width='97%'>
	<tr>
		<td class="certificado" align='center' width='239'>
			<B><bean:message key="label.cartola.detalle"/></B>
		</td>
		<td class="certificado" align='center' width='71'>
			<B><bean:message key="label.cartola.fecha"/></B>
		</td>
		<td class="certificado" align='center' width='101'>
			<B><bean:message key="label.cartola.cuotas"/></B>
		</td>
		<td class="certificado" align='center' width='99'>
			<B><bean:message key="label.cartola.depositos"/></B>
		</td>
		<td class="certificado" align='center' width='99'>
			<B><bean:message key="label.cartola.cargos"/></B>
		</td>
	</tr>
	<logic:notEmpty name="detalles">
	<logic:iterate id="register" name="detalles">
	<tr>
		<td>
			<bean:write name="register" property="descripcionDetalle"/>
		</td>
		<td class="certificado" align='center'>
			<bean:write name="register" property="fechaDetalle"/>
		</td>
		<td align='right'>
			<bean:write name="register" property="cuotaDespliegue" format='<%= float5FormatPatt %>'/>
		</td>
		<td align='right' nowrap>
			<bean:define id="deposito" name="register" property="deposito" type="java.lang.Integer"/>
			<% System.out.println("7"); %>
			<%
			if(deposito.intValue()>0) {%>
				<bean:write name="register" property="deposito" formatKey="format.money"/>
			<%} else { %>
			 	&nbsp
			<% } %>
		</td>
		<td align='right' nowrap>
			<bean:define id="cargo" name="register" property="cargo" type="java.lang.Integer"/>
			<%
			if(cargo.intValue()>0) {%>
				<bean:write name="register" property="cargo" formatKey="format.money"/>
			<%} else { %>
			 	&nbsp
			<% } %>
		</td>
		
	</tr>	
	</logic:iterate>
	</logic:notEmpty>
	<tr>
		<td colspan='2' align='center'>
			<B><bean:message key="label.cartola.subtotal.periodo"/></B>
		</td>
		<td width='101'>
			<table border='0' width='90' cellspacing='0' cellpadding='0'>		
				<tr>
					<td align='right'>
						<bean:write name="cartola" property="subtotalCuotas" format='<%= float5FormatPatt %>'/>
					</td>
				</tr>
			</table>
		</td>
		<td width='99'>
			<table border='0' width='92' cellspacing='0' cellpadding='0'>		
				<tr>
					<td align='right' nowrap>
						<bean:write name="cartola" property="subtotalDepositos" formatKey="format.money"/>
					</td>
				</tr>
			</table>
		</td>
		<td width='82'>
			<table border='0' width='92' cellpadding='0'>		
				<tr>
					<td align='right' nowrap>
						<bean:write name="cartola" property="subtotalCargos" formatKey="format.money"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan='4' align='center'>
			<B><bean:message key="label.cartola.rentabilidad.periodo"/></B>
		</td>
		<td width='82'>
			<table border='0' width='92' cellspacing='0' cellpadding='0'>		
				<tr>
					<td align='right'>
						<bean:write name="cartola" property="rentabilidadPeriodo" formatKey="format.money"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<br>
<B><bean:message key="label.cartola.resumen.movimientos.saldos"/></B>

<table border='1' cellpadding='0' cellspacing='0' width='97%'>
	<tr>
		<td width='151'>
			<B><bean:message key="label.cartola.fecha"/></B>
			<br>
			<bean:write name="cartola" property="fechaBDUltValorCuota"/>
		</td>
		<td width='151'>
			<B><bean:message key="label.cartola.saldo.cuotas"/></B>
			<br>
			<bean:write name="cartola" property="saldoCuotas" format='<%= float5FormatPatt %>'/>
		</td>
		<td width='151'>
			<B><bean:message key="label.cartola.valor.cuota"/></B>
			<br>
			<bean:write name="cartola" property="valorCuotaActual" format='<%= float3FormatPatt %>'/>
		</td>
		<td width='151'>
			<B><bean:message key="label.cartola.saldo.pesos"/></B>
			<br>
			<bean:write name="cartola" property="saldoPesos" formatKey="format.money"/>
		</td>
	</tr>
</table>

<br>
<B><bean:message key="label.cartola.movimientos.valorizar"/></B>
<table border='1' cellpadding='0' cellspacing='0'>
	<tr>
		<td width='300' colspan='2'>
			<B><bean:message key="label.cartola.depositos"/></B>
			<br>
			<bean:write name="cartola" property="depositosValorizar" formatKey="format.money"/>
		</td>
		<td width='300' colspan='2'>
			<B><bean:message key="label.cartola.giros"/></B>
			<br>
			<bean:write name="cartola" property="girosValorizar" formatKey="format.money"/>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: none;" align="left" width='300' colspan='2' height='20' valign='middle'>
			<B>
				<bean:message key="label.cartola.total.saldo.contable"/>
				<bean:write name="cartola" property="fecha" formatKey="format.date"/>
			</B>
		</td>
		<td style="border-bottom: none;" align="left" width='300' colspan='2' height='20' valign='middle'>
			<B><bean:message key="label.cartola.saldo.maximo.giro"/></B>
		</td>
	</tr>
	<tr>
		<td style="border-top: none;" align="right" width='300' colspan='2' height='20' valign='middle'>
		 	<bean:write name="cartola" property="totalSaldoContable" formatKey="format.money"/>
		</td>
		<td style="border-top: none;" align="right" width='300' colspan='2' height='20' valign='middle'>
			<bean:write name="cartola" property="saldoMaximoGiro" formatKey="format.money"/>
		</td>
	</tr>
</table>
</logic:present>

          <br /></td>
        </tr>
    </table>
  </div>
  <div style="clear:left;"></div>
</div>
</body>
</html>
