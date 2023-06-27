<font class="certificado">

<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td align='right' nowrap class="certificado">
			<img src="/AutoconsultaWeb/modulo/images/logoCertificado.jpg">
		</td>
	</tr>   
	<tr>
		<td nowrap class="certificado">
			<div align='center'><font size='3'><b><bean:message key="label.licencia.certificado.titulo"/></b></font></div>
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

<logic:notPresent name="tieneLicencias">
	<br>
	<br>
	<br>
	<br>
	<bean:message key="label.licencia.certificado.sin.licencias"/>
</logic:notPresent>

<logic:present name="tieneLicencias">
<br>
<br>
<bean:message key="label.licencia.certificado.segunDetalle"/>
<br>
<br>
<bean:define id="licencias" name="certificado" property="licencias"/>
<br>


<table border='1' cellspacing='0' height='4'>
<logic:notEmpty name="licencias">
	<tr>
		<td rowspan='2' align='center' class="certificado"><bean:message key="label.licencia.certificado.desde.hasta"/></td>
		<td rowspan='2' align='center' class="certificado"><bean:message key="label.licencia.certificado.dias"/></td>
		<td align='center' rowspan='2' width='59' class="certificado"><bean:message key="label.licencia.certificado.remuneracion.imponible"/></td>
		<td align='center' rowspan='2' width='59' class="certificado"><bean:message key="label.licencia.certificado.subsidio.pagado"/></td>
		<td align='center' valign='top' colspan='4' height='14' class="certificado"><bean:message key="label.licencia.certificado.cotizacion"/></td>
		<td width='184' rowspan='2' class="certificado"><bean:message key="label.licencia.certificado.cotizacion.afp"/></td>
	</tr>
	<tr>
		<td align='center' height='24' width='41' class="certificado"><bean:message key="label.licencia.certificado.cotizacion.mes"/></td>
		<td align='center' height='24' width='59' class="certificado"><bean:message key="label.licencia.certificado.cotizacion.salud"/></td>
		<td align='center' valign='top' height='24' width='59' class="certificado"><bean:message key="label.licencia.certificado.cotizacion.cesantia"/></td>
		<td align='center' valign='top' height='24' class="certificado"><bean:message key="label.licencia.certificado.cotizacion.pension"/></td>
	<tr>

	<logic:iterate id="register" name="licencias">
		<tr>
			<td class="certificado">
				<bean:write name="register" property="fechaDesde"/>
				<bean:write name="register" property="fechaHasta"/>
			</td>
			<td class="certificado" align='right' nowrap><bean:write name="register" property="diasLicencia"/></td>
			<td class="certificado" align='right' nowrap><bean:write name="register" property="rentaImponibleCotizacion" formatKey="format.money"/></td>
			<td class="certificado" align='right' nowrap><bean:write name="register" property="subsidioPagado" formatKey="format.money"/></td>
			<td class="certificado" align='right' nowrap><bean:write name="register" property="fechaMesCotizacion"/></td>
			<td class="certificado" align='right' nowrap><bean:write name="register" property="cotizacionSalud" formatKey="format.money"/></td>
			<td class="certificado" align='right' nowrap><bean:write name="register" property="cotizacionCesantia" formatKey="format.money"/></td>
			<td class="certificado" align='right' nowrap><bean:write name="register" property="cotizacionPension" formatKey="format.money"/></td>
			<bean:define id="nomInstitutoPrevisional" name="register" property="nomInstitutoPrevisional" type='java.lang.String'/>
			<%if(nomInstitutoPrevisional!=null && nomInstitutoPrevisional.length()>0) {%>
				<td class="certificado"><bean:write name="register" property="nomInstitutoPrevisional"/></td>
			<%} else { %>
				 	<td class="certificado">&nbsp</td>
			<% } %>
		<tr>
	</logic:iterate>
</logic:notEmpty>
</table>

<br>

<table>
	<tr>
		<td class="certificado" width='25%' nowrap><bean:message key="label.licencia.certificado.total.subsidio.pagado"/>:</td>
		<td class="certificado" width='25%' align='right' nowrap><bean:write name="certificado" property="totalSubsidioPagado" formatKey="format.money"/></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td class="certificado" nowrap><bean:message key="label.licencia.certificado.total.cotizacion.pension"/>:</td>
		<td class="certificado" align='right' nowrap><bean:write name="certificado" property="totalCotizacionPension" formatKey="format.money"/></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td class="certificado" nowrap><bean:message key="label.licencia.certificado.total.cotizacion.salud"/>:</td>
		<td class="certificado" align='right' nowrap><bean:write name="certificado" property="totalSalud" formatKey="format.money"/></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td class="certificado" nowrap><bean:message key="label.licencia.certificado.total.cotizacion.seguro.cesantia"/>:</td>
		<td class="certificado" align='right' nowrap><bean:write name="certificado" property="totalSubsidioCesantia" formatKey="format.money"/></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td class="certificado" nowrap></td>
		<td class="certificado" align='right' nowrap><bean:message key="label.licencia.certificado.total.linea"/></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td class="certificado" nowrap><bean:message key="label.licencia.certificado.total"/>:</td>
		<td class="certificado" align='right' nowrap><bean:write name="certificado" property="sumaTotal" formatKey="format.money"/></td>
		<td></td>
		<td></td>
	</tr>
</table>
</logic:present>
<br>
<br>
<bean:message key="label.licencia.certificado.extiende"/>
<br>
<br>

<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td nowrap class="certificado">
			<bean:write name="certificado" property="fechaHoy" formatKey="format.fullDate"/>
		</td>
		<td align='right' class="certificado">
<img src="/AutoconsultaWeb/modulo/images/licenciasMedicas/CopiadefirmaSIL.gif">
		</td>
	</tr>
	<tr>
		<td>&nbsp</td>
	</tr>
	<tr>
		<td class="certificado" colspan="2">
			<bean:message key="label.validador.id"/>: <bean:write name="certificado" property="id"/><br>
			<bean:message key="label.validador.mensaje"/>
		</td>
	</tr>
</table>
<br>
<br>

</font>


