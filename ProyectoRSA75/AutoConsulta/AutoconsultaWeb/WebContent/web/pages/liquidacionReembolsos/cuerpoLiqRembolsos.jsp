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
			<div align='center'>
			   <h1><bean:message key="label.liquidacion.titulo.linea2"/></h1>
			</div>
		</td>
	</tr>
</table>
<BR>
<font class="certificado">
<logic:empty name="consulta.detallada" > 
<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td nowrap class="certificado">
			<div align='center'>
				<font size='2'>
					<b>
						<bean:message key="label.liquidacion.sin.liquidaciones"/><BR>
					</b>
				</font>
			</div>
		</td>
	</tr>
	
</table>
</logic:empty>

<logic:notEmpty name="consulta.detallada" > 
	<table width='100%' border="1" cellspacing='0' align = 'center'>
		<tr  bgcolor="#cccccc">
			<td class="certificado" colspan="2" align="center"><font size="2"><B><bean:message
				key="label.liquidacion.titulo.linea3" /></B></font></td>
		</tr>

		<bean:define id="trabajador" name="consulta.detallada" property="trabajador" type='cl.araucana.autoconsulta.vo.DatosTrabajadoresLiquidacionesVO'/>
		
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.convenio"/></B></font></td>
			<td class="certificado" width='60%'><bean:write name="trabajador" property="nombreEmpresa" /></td>
		</tr>
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.nombre.funcionario"/></B></font></td>
			<td class="certificado" width='60%'><bean:write name="trabajador" property="fullNombre" /></td>
		</tr>
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.rut.funcionario"/></B></font></td>
			<td class="certificado" width='60%'><bean:write name="trabajador" property="fullRutTra" /></td>
		</tr>
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.banco.ctacte"/></B></font></td>
			<td class="certificado" width='60%'><bean:write name="trabajador" property="nombreBanco" />&nbsp;-&nbsp;<bean:write name="trabajador" property="ctaCte" /></td>
		</tr>
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.email"/></B></font></td>
			<td class="certificado" width='60%'><bean:write name="trabajador" property="mail" /></td>
		</tr>
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.empresa"/></B></font></td>
			<td class="certificado" width='60%'><bean:write name="trabajador" property="nombreEmpresa" /></td>
		</tr>
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.rut.empresa"/></B></font></td>
			<td class="certificado" width='60%'><bean:write name="trabajador" property="fullRutEmpresa" /></td>
		</tr>
	
		<bean:define id="liquidacion" name="consulta.detallada" property="liquidacion" type='cl.araucana.autoconsulta.vo.LiquidacionesVO'/>
		<tr>
			<td class="certificado" width='40%'><font size="1"><B><bean:message key="label.liquidacion.fecha.liquidacion"/></B></font>
				<bean:write name="liquidacion" property="fechaLiquidacion" /></td>
			<td class="certificado" width='60%'><font size="1"><B><bean:message key="label.liquidacion.numero.liquidacion"/></B></font>
				<bean:write name="liquidacion" property="nroLiquidacion" />
			</td>
		</tr>
	</table> 			 
	<br>
	<table width='100%' border="1" cellspacing='0' align = 'center'>
		<tr>
			<td class="certificado" width='60%'><font size="1"><B><bean:message key="label.liquidacion.saldo.disponible"/></B></font></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="saldoPrevioLiquidacion" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	
		<tr>
			<td class="certificado" width='60%'><font size="1"><B><bean:message key="label.liquidacion.comision"/></B></font></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="comision" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	

		<tr>
			<td class="certificado" width='60%'><font size="1"><B><bean:message key="label.liquidacion.reajuste"/></B></font></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="reajuste" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	

		<tr>
			<td class="certificado" width='60%'><B><font size="1"><bean:message key="label.liquidacion.valor.total.solicitado"/></font></B></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="totalSolicitadoPesos" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	
		<tr>
			<td class="certificado" width='60%'><B><font size="1"><bean:message key="label.liquidacion.valor.pendiente.de.bonificar"/></font></B></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="totalPendientePesos" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	
		<tr>
			<td class="certificado" width='60%'><B><font size="1"><bean:message key="label.liquidacion.valor.rechazado.por.tope"/></font></B></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="totalRechazadoPesos" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	
		
		<tr>
			<td class="certificado" width='60%'><B><font size="1"><bean:message key="label.liquidacion.rebaja.solitada.empresa"/></font></B></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="rebajaSolicitadaEmpresa" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	
		<tr>
			<td class="certificado" width='60%'><font size="1"><B><bean:message key="label.liquidacion.monto.a.rebajar.fondo"/></B></font></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="totalBonificadoPesos" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	

		<tr>
			<td class="certificado" width='60%'><B><font size="1"><bean:message key="label.liquidacion.saldo.disponible.despues.de.liquidacion"/>
							&nbsp;<bean:write name="liquidacion" property="fechaLiquidacion" />:</font></B></td>
			<td class="certificado" width='10%' align="left" style="border-right: 0">$</td>
			<td class="certificado" width='10%' align="right" style="border-right: 0;border-left: 0"><bean:write name="liquidacion" property="saldoPosterior" formatKey="format.int"/></td>
			<td class="certificado" width='20%' align="left" style="border-left: 0">&nbsp;</td>
		</tr>	
	</table>
		<br>	
		<table  width='100%' border="1" cellspacing='0' align = 'center'>
		<tr bgcolor="#cccccc">
			<td class="certificado" colspan="8" align="center"><font size="2"><B><bean:message key="label.liquidacion.titulo.linea4"/></B></font></td>
		</tr>
		<tr>  
			<td class="certificado"><font size="1"><B> <bean:message key="label.liquidacion.solicitud"/></B></font></td>
			<td class="certificado"><font size="1"><B> <bean:message key="label.liquidacion.item"/></B></font></td>
			<td class="certificado"><font size="1"><B> <bean:message key="label.liquidacion.numero.carga"/></B></font></td>
			<td class="certificado"><font size="1"><B> <bean:message key="label.liquidacion.concepto"/></B></font></td>
			<td class="certificado"><font size="1"><B> <bean:message key="label.liquidacion.solicitado"/></B></font></td>
			<td class="certificado"><font size="1"><B> <bean:message key="label.liquidacion.bonificado"/></B></font></td>
			<td class="certificado"><font size="1"><B> <bean:message key="label.liquidacion.pendiente"/></B></font></td>
			<td class="certificado"><font size="1"><B><bean:message key="label.liquidacion.rechazado"/></B></font></td>
		</tr>	
		<logic:iterate id="movimientos" name="consulta.detallada" property="movimientos" type='cl.araucana.autoconsulta.vo.MovimientosLiquidacionVO'>
			<tr style="border-bottom: 1px;">
				<td class="certificado"><bean:write name="movimientos" property="numeroDocumento" /></td>
				<td class="certificado"><bean:write name="movimientos" property="codigoPrestacion" /></td>
				<td class="certificado" align="center"><bean:write name="movimientos" property="numeroCarga" /></td>
				<td class="certificado"><bean:write name="movimientos" property="descripcionPrestacion" /></td>
				<td class="certificado" align="right"><bean:write name="movimientos" property="montoBase" formatKey="format.int"/></td>
				<td class="certificado" align="right"><bean:write name="movimientos" property="montoBonificado" formatKey="format.int"/></td>
				<td class="certificado" align="right"><bean:write name="movimientos" property="montoPendiente" formatKey="format.int"/></td>
				<td class="certificado" align="right"><bean:write name="movimientos" property="montoRechazado" formatKey="format.int"/></td>
			</tr>
		</logic:iterate>
		<tr>
			<bean:define id="totales" name="totales" type='cl.araucana.autoconsulta.vo.TotalesLiquidacionVO'/>
			<td class="certificado" colspan="4"><font size="1"><B> <bean:message key="label.liquidacion.totales"/></B></font></td>		
			<td class="certificado" align="right"><bean:write name="totales" property="totalBase" formatKey="format.int"/></td>
			<td class="certificado" align="right"><bean:write name="totales" property="totalBonificado" formatKey="format.int"/></td>
			<td class="certificado" align="right"><bean:write name="totales" property="totalPendiente" formatKey="format.int"/></td>
			<td class="certificado" align="right"><bean:write name="totales" property="totalRechazado" formatKey="format.int"/></td>
		</tr>
		<tr>
			<td class="certificado" colspan="4"><font size="1"><B> <bean:message key="label.liquidacion.documentos"/></B></font></td>		
			<td class="certificado" colspan="4"><bean:write name="totales" property="totalDocumentos" formatKey="format.int"/></td>
		</tr>
		 
		</table>
		<br>
		<table  width='100%' border="1" cellspacing='0' align = 'center'>
			<tr bgcolor="#cccccc">	
			<td class="certificado" align="center"><font size="2"><B> <bean:message key="label.liquidacion.monto.a.depositar"/></B></font>&nbsp;
			<bean:write name="liquidacion" property="totalBonificadoPesos" formatKey="format.money"/></td>		
			</tr>
		</table>
	</logic:notEmpty>
		<BR>
		<logic:notEmpty  name="publicidad">
		<table  width='100%' border="1" cellspacing='0' align = 'center'>
		<tr>
			<bean:define id="publicidad" name="publicidad" type='cl.araucana.autoconsulta.vo.PublicidadVO'/>
			<td class="certificado" align="center"><font size="2"><B> <bean:write name="publicidad" property="mensaje" /></B></font>
			</td>		
		</tr>
		</table>
		</logic:notEmpty>
<br>
<br>
<br>
<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td nowrap class="certificado">
			<bean:write name="fechaHoy" formatKey="format.fullDate"/>
		</td>
	</tr>
</table>
</font>