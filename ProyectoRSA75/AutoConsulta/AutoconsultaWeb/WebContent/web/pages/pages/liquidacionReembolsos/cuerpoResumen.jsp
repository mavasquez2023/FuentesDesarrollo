<table border='0' cellspacing='0' width='100%'>
	<tr>  
		<td align='right' nowrap class="certificado">
			<img src="/AutoconsultaWeb/web/images/logoCertificado.jpg">
		</td>
	</tr>
	<tr bgcolor="#cccccc">
		<td nowrap class="certificado">
			<div align='center'>
				<font size='2'>
					<b>
						<bean:message key="label.liquidacion.empresa.titulo1"/><BR>
					</b>
				</font>
			</div>
		</td>
	</tr>
	
</table>
<BR>
<font class="certificado">
<logic:notEmpty name="lista.resumen" > 
		<table  width="100%" border="0" cellspacing="0" align = "center" >
				<bean:define id="header" name="empresa.convenio" type='cl.araucana.autoconsulta.vo.ConvenioEmpresaVO'/>
			
				<tr>
					<td class="certificado" width='20%'><font size="1"><B> <bean:message key="label.liquidacion.periodo.informado"/></B></font></td>
					<td class="certificado" width='80%'><font size="1"><bean:write name="periodo" formatKey="format.date.periodo2" /></font></td>			  
				</tr>
				<tr> 
					<td class="certificado" width='20%'><font size="1"><B> <bean:message key="label.liquidacion.fondo"/></B></font></td>
					<td class="certificado" width='80%'><font size="1"><bean:write name="header" property="fondo" /></font></td>			
				</tr>
				<!--<logic:notEmpty name="tips" > 
					<logic:iterate id="tips" name="tips" >
						<tr>
							<td class="certificado" width='20%'><font size="1"><B> <bean:message key="label.liquidacion.TIP.anual"/></B></font></td>
							<td class="certificado" width='80%'><font size="1"><bean:write name="tips" property="tipValor" /></font></td>			
						</tr>
						<tr>
							<td class="certificado" width='20%'><font size="1"><B> <bean:message key="label.liquidacion.TIP.mensual"/></B></font></td>
							<td class="certificado" width='80%'><font size="1"><bean:write name="tips" property="tipPropia" /></font></td>			
						</tr>
					</logic:iterate>
				</logic:notEmpty>-->
				<tr>
					<td class="certificado" width='20%'><font size="1"><B> <bean:message key="label.liquidacion.empresa"/></B></font></td>
					<td class="certificado" width='80%'><font size="1"><bean:write name="header" property="nombreEmpresa" /></font></td>			
				</tr>
				<tr>
					<td class="certificado" width='20%'><font size="1"><B> <bean:message key="label.liquidacion.rut"/></B></font></td>
					<td class="certificado" width='80%'><font size="1"><bean:write name="header" property="rutEmpresaFull"/></font></td>			
				</tr>
			
		</table>
		<br> 
		<table width="100%" border="1" cellspacing="0" align = "center">		
		 <tr>
			<td class="certificado" colspan="2" ><font size="1"><B><bean:message key="label.liquidacion.titulo.trabajador"/></B></font></td>
			
			<td class="certificado" align="center" ><font size="1"><B><bean:message key="label.liquidacion.titulo.1"/><BR><bean:write name="fecha1"/></B></font></td>
			<logic:equal name="c1"  value="si">
				<td class="certificado"  align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.3"/><BR><bean:write name="fecha2"/></B></font></td>
			</logic:equal>
			<logic:equal name="c2"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.3"/><BR><bean:write name="fecha3"/></B></font></td>
			</logic:equal>
			<logic:equal name="c3"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.3"/><BR><bean:write name="fecha4"/></B></font></td>
			</logic:equal>
			<logic:equal name="c4"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.3"/><BR><bean:write name="fecha5"/></B></font></td>
			</logic:equal>
			<logic:equal name="c5"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.5"/><BR><bean:write name="fecha6"/></B></font></td>
			</logic:equal>
			<logic:equal name="c6"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.5"/><BR><bean:write name="fecha7"/></B></font></td>
			</logic:equal>
			<logic:equal name="c7"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.5"/><BR><bean:write name="fecha8"/></B></font></td>
			</logic:equal>
			<logic:equal name="c8"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.5"/><BR><bean:write name="fecha9"/></B></font></td>
			</logic:equal>
			<logic:equal name="c9"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.7"/><BR><bean:write name="fecha10"/></B></font></td>
			</logic:equal>
			<logic:equal name="c10"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.7"/><BR><bean:write name="fecha11"/></B></font></td>
			</logic:equal>
			<logic:equal name="c11"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.7"/><BR><bean:write name="fecha12"/></B></font></td>
			</logic:equal>
			<logic:equal name="c12"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.7"/><BR><bean:write name="fecha13"/></B></font></td>
			</logic:equal>
			<!--<logic:equal name="c13"  value="si">			
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.9"/><BR><bean:write name="fecha14"/></B></font></td>
			</logic:equal>
			<logic:equal name="c14"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.9"/><BR><bean:write name="fecha15"/></B></font></td>
			</logic:equal>
			<logic:equal name="c15"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.9"/><BR><bean:write name="fecha16"/></B></font></td>
			</logic:equal>
			<logic:equal name="c16"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.9"/><BR><bean:write name="fecha17"/></B></font></td>
			</logic:equal>-->
			
			<logic:equal name="c18"  value="si">  
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.13"/><BR><bean:write name="fecha18"/></B></font></td>
			</logic:equal>
			<logic:equal name="c19"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.13"/><BR><bean:write name="fecha19"/></B></font></td>
			</logic:equal>
			<logic:equal name="c20"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.13"/><BR><bean:write name="fecha20"/></B></font></td>
			</logic:equal>
			<logic:equal name="c21"  value="si">
				<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.13"/><BR><bean:write name="fecha21"/></B></font></td>
			</logic:equal>
			
			  
			<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.10"/><BR><bean:write name="fechaCierre"/></B></font></td>
			<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.11"/><BR><bean:write name="fechaCierre"/></B></font></td>
			<td class="certificado" align="center" ><font size="1"><B> <bean:message key="label.liquidacion.titulo.12"/><BR><bean:write name="fechaCierre"/></B></font></td>
		</tr>
		<logic:iterate id="movimientos" name="lista.resumen" >
			<tr style="border-bottom: 1px;">  
				<td class="certificado" nowrap><font size="1"><bean:write name="movimientos" property="nombreTrabajador" />&nbsp;</font></td>
				<td class="certificado" nowrap><font size="1"><bean:write name="movimientos" property="rutTrabajador" /></font></td>
				<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="fondoInicial"  formatKey="format.int"/></font></td>
				<logic:equal name="c1" value="si">    
					<td class="certificado" nowrap align="right"><font size="1" ><bean:write name="movimientos" property="montoAumentoInicial1" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c2" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoAumentoInicial2" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c3" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoAumentoInicial3" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c4" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoAumentoInicial4" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c5" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoAumentoComision1"formatKey="format.int" /></font></td>
				</logic:equal>
				<logic:equal name="c6" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoAumentoComision2" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c7" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoAumentoComision3" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c8" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoAumentoComision4" formatKey="format.int"/></font></td>
				</logic:equal>  
				<logic:equal name="c9" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoValorPagado1" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c10" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoValorPagado2" formatKey="format.int"/></font></td>
				</logic:equal>
				<logic:equal name="c11" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoValorPagado3" formatKey="format.int"/></font></td> 
				</logic:equal>
				<logic:equal name="c12" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoValorPagado4" formatKey="format.int"/></font></td>
				</logic:equal>
				<!--<logic:equal name="c13" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoActualizado1" formatKey="format.int" /></font></td>
				</logic:equal>
				<logic:equal name="c14" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoActualizado2" formatKey="format.int" /></font></td>
				</logic:equal>
				<logic:equal name="c15" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoActualizado3" formatKey="format.int" /></font></td>
				</logic:equal>
				<logic:equal name="c16" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoActualizado4" formatKey="format.int" /></font></td>
				</logic:equal>-->
				<logic:equal name="c18" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoRetiroSolEmp1" formatKey="format.int" /></font></td>
				</logic:equal>
				<logic:equal name="c19" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoRetiroSolEmp2" formatKey="format.int" /></font></td>
				</logic:equal>
				<logic:equal name="c20" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoRetiroSolEmp3" formatKey="format.int" /></font></td>
				</logic:equal>	
				<logic:equal name="c21" value="si">
					<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoRetiroSolEmp4" formatKey="format.int" /></font></td>
				</logic:equal>							
				<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoInteres" formatKey="format.int"/></font></td>
				<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoReajuste" formatKey="format.int"/></font></td>
				<td class="certificado" nowrap align="right"><font size="1"><bean:write name="movimientos" property="montoFondoFinal" formatKey="format.int" /></font></td>
			
			</tr>
			
		</logic:iterate>
		
		<bean:define id="totales" name="totales" type='cl.araucana.autoconsulta.vo.TotalesPorEmpresaVO'/>
		<tr bgcolor="#cccccc"> 
			<td class="certificado" colspan="2"><font size="1"><B> <bean:message key="label.liquidacion.titulo.total.empresa"/></B></font></td>		
			<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalFondoInicial" formatKey="format.int" /><B></font></td>
			<logic:equal name="c1" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoAumentoInicial1" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c2" value="si">
			<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoAumentoInicial2" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c3" value="si">
				<td class="certificado"  align="right" ><font size="1"><B><bean:write name="totales" property="totalMontoAumentoInicial3" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c4" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoAumentoInicial4" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c5" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoAumentoComision1" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c6" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoAumentoComision2" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c7" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoAumentoComision3" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c8" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoAumentoComision4" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c9" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoValorPagado1" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c10" value="si">     
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoValorPagado2" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c11" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoValorPagado3" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c12" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoValorPagado4" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<!--<logic:equal name="c13" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoActualizado1" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c14" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoActualizado2" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c15" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoActualizado3" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c16" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoActualizado4" formatKey="format.int" /><B></font></td>
			</logic:equal>-->
		
			<logic:equal name="c18" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoRetSolicitud1" formatKey="format.int" /><B></font></td>
			</logic:equal>	
			<logic:equal name="c19" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoRetSolicitud2" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c20" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoRetSolicitud3" formatKey="format.int" /><B></font></td>
			</logic:equal>
			<logic:equal name="c21" value="si">
				<td class="certificado"  align="right"><font size="1"><B><bean:write name="totales" property="totalMontoRetSolicitud4" formatKey="format.int" /><B></font></td>
			</logic:equal>											
			<td class="certificado" align="right"><font size="1"><B><bean:write name="totales" property="totalMontoInteres" formatKey="format.int" /><B></font></td>
			<td class="certificado" align="right"><font size="1"><B><bean:write name="totales" property="totalMontoReajuste" formatKey="format.int" /><B></font></td>
			<td class="certificado" align="right"><font size="1"><B><bean:write name="totales" property="totalMontoFondoFinal" formatKey="format.int" /><B></font></td>
		</tr>
	</table> 
</logic:notEmpty>

</font>

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


