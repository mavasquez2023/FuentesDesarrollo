    
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
			<div align='center'><h1><bean:message key="label.cartola.titulo"/></h1></div>
		</td>
	</tr>
</table>

<br>

<table border='0' cellpadding='0' cellspacing='0' width='607'>
	<tr>
		<td  class="certificado" align=right>
			<b>
			<bean:write name="cartola" property="fecha" formatKey="format.fullDate"/>
			</b>
		</td>
	</tr>
</table>

<br>

<table border='1' cellpadding='0' cellspacing='0' width='607'>
<tbody>
	<tr>
		<td class="certificado" width='313'>
			<b><bean:message key="label.nombre"/></b>
			<br>
			<bean:write name="nombre"/>
		</td>
		<td class="certificado" width='143'>
			<b><bean:message key="label.rut"/><br></b>
			<bean:write name="rut"/>
		</td>
		<td class="certificado" width='151'>
			<b><bean:message key="label.cartola.numero.cuenta"/></b>
			<br>
			<bean:write name="cartola" property="fullNumeroCuenta"/>
		</td>
	</tr>
</tbody>
</table>

<br>

<table border='1' cellpadding='0' cellspacing='0' width='607'>
	<tr>
		<td class="certificado" width='313'>
			<b><bean:message key="label.cartola.direccion"/></b>
			<br>
			<bean:write name="cartola" property="direccion"/>

		</td>
		<td class="certificado" width='143'>
			<b><bean:message key="label.cartola.comuna"/></b>
			<br>
			<bean:write name="cartola" property="comuna"/>
		</td>
		<td class="certificado" width='151' >
			<b><bean:message key="label.cartola.ciudad"/></b>
			<br>
			<bean:write name="cartola" property="ciudad"/>
		</td>
	</tr>
</table>

<br>
<b><bean:message key="label.cartola.anteror"/></b>
<bean:define id= "fechaUltCartola" name="cartola" property="fechaUltCartola" type='java.lang.String'/>
			
<table border='1' width='607' cellpadding='0' cellspacing='0'>
	<tr>
	<% System.out.println("3"); %>
		<td class="certificado" width='151'>
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

<table border='1' width='607' cellpadding='0' cellspacing='0'>
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
		<td class="certificado">
			<bean:write name="register" property="descripcionDetalle"/>
		</td>
		<td class="certificado" align='center'>
			<bean:write name="register" property="fechaDetalle"/>
		</td>
		<td class="certificado" align='right'>
			<bean:write name="register" property="cuotaDespliegue" format='<%= float5FormatPatt %>'/>
		</td>
		<td class="certificado" align='right' nowrap>
			<bean:define id="deposito" name="register" property="deposito" type="java.lang.Integer"/>
			<% System.out.println("7"); %>
			<%
			if(deposito.intValue()>0) {%>
				<bean:write name="register" property="deposito" formatKey="format.money"/>
			<%} else { %>
			 	&nbsp
			<% } %>
		</td>
		<td class="certificado" align='right' nowrap>
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
		<td class="certificado" colspan='2' align='center'>
			<B><bean:message key="label.cartola.subtotal.periodo"/></B>
		</td>
		<td class="certificado" width='101'>
			<table border='0' width='90' cellspacing='0' cellpadding='0'>		
				<tr>
					<td class="certificado" align='right'>
						<bean:write name="cartola" property="subtotalCuotas" format='<%= float5FormatPatt %>'/>
					</td>
				</tr>
			</table>
		</td>
		<td class="certificado" width='99'>
			<table border='0' width='92' cellspacing='0' cellpadding='0'>		
				<tr>
					<td class="certificado" align='right' nowrap>
						<bean:write name="cartola" property="subtotalDepositos" formatKey="format.money"/>
					</td>
				</tr>
			</table>
		</td>
		<td class="certificado" width='82'>
			<table border='0' width='92' cellpadding='0'>		
				<tr>
					<td class="certificado" align='right' nowrap>
						<bean:write name="cartola" property="subtotalCargos" formatKey="format.money"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="certificado" colspan='4' align='center'>
			<B><bean:message key="label.cartola.rentabilidad.periodo"/></B>
		</td>
		<td class="certificado" width='82'>
			<table border='0' width='92' cellspacing='0' cellpadding='0'>		
				<tr>
					<td class="certificado" align='right'>
						<bean:write name="cartola" property="rentabilidadPeriodo" formatKey="format.money"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<br>
<B><bean:message key="label.cartola.resumen.movimientos.saldos"/></B>

<table border='1' width='607' cellpadding='0' cellspacing='0'>
	<tr>
		<td class="certificado" width='151'>
			<B><bean:message key="label.cartola.fecha"/></B>
			<br>
			<bean:write name="cartola" property="fechaBDUltValorCuota"/>
		</td>
		<td class="certificado" width='151'>
			<B><bean:message key="label.cartola.saldo.cuotas"/></B>
			<br>
			<bean:write name="cartola" property="saldoCuotas" format='<%= float5FormatPatt %>'/>
		</td>
		<td class="certificado" width='151'>
			<B><bean:message key="label.cartola.valor.cuota"/></B>
			<br>
			<bean:write name="cartola" property="valorCuotaActual" format='<%= float3FormatPatt %>'/>
		</td>
		<td class="certificado" width='151'>
			<B><bean:message key="label.cartola.saldo.pesos"/></B>
			<br>
			<bean:write name="cartola" property="saldoPesos" formatKey="format.money"/>
		</td>
	</tr>
</table>

<br>
<B><bean:message key="label.cartola.movimientos.valorizar"/></B>
<table border='1' width='607' cellpadding='0' cellspacing='0'>
	<tr>
		<td class="certificado" width='300' colspan='2'>
			<B><bean:message key="label.cartola.depositos"/></B>
			<br>
			<bean:write name="cartola" property="depositosValorizar" formatKey="format.money"/>
		</td>
		<td class="certificado" width='300' colspan='2'>
			<B><bean:message key="label.cartola.giros"/></B>
			<br>
			<bean:write name="cartola" property="girosValorizar" formatKey="format.money"/>
		</td>
	</tr>
	<tr>
		<td class="certificado" style="border-bottom: none;" align="left" width='300' colspan='2' height='20' valign='middle'>
			<B>
				<bean:message key="label.cartola.total.saldo.contable"/>
				<bean:write name="cartola" property="fecha" formatKey="format.date"/>
			</B>
		</td>
		<td class="certificado" style="border-bottom: none;" align="left" width='300' colspan='2' height='20' valign='middle'>
			<B><bean:message key="label.cartola.saldo.maximo.giro"/></B>
		</td>
	</tr>
	<tr>
		<td class="certificado" style="border-top: none;" align="right" width='300' colspan='2' height='20' valign='middle'>
		 	<bean:write name="cartola" property="totalSaldoContable" formatKey="format.money"/>
		</td>
		<td class="certificado" style="border-top: none;" align="right" width='300' colspan='2' height='20' valign='middle'>
			<bean:write name="cartola" property="saldoMaximoGiro" formatKey="format.money"/>
		</td>
	</tr>
</table>
<div align='right'>
</div>
</font>
