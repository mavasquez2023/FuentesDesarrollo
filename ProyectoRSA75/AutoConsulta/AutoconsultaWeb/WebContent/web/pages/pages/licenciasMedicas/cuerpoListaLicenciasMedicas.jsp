<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td align='right' nowrap class="certificado">
 			<img src="/AutoconsultaWeb/web/images/logoCertificado.jpg">
		</td>
	</tr>
	<tr> 
		<td nowrap class="certificado">
			<div align='center'><h1><bean:message key="label.licencia"/></h1></div>
		</td>
	</tr>
</table>

<font class="certificado">


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
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>  
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>

	<logic:notEmpty name="lista.empleadores">
		<logic:iterate id="register" name="lista.empleadores">
			<tr>
				<td class="certificado"><b><bean:message key="label.empleador"/>:</b></td>
				<td class="certificado">[<bean:write name="register" property="fullRut"/>] <bean:write name="register" property="nombre"/></td>
			<tr>
		</logic:iterate>
	</logic:notEmpty>
</table>

<br>
<br>
<br>

<logic:empty name="lista.licencias">
	<br>
	<br>
	<bean:message key="label.licencia.rut.sin.licencias"/>
</logic:empty>


<logic:notEmpty name="lista.licencias">
<table width='50%' border='1' cellspacing='0' align = 'center'>
	<tr>
		<td class="certificado"><bean:message key="label.licencia.fecha.desde"/></td>
		<td class="certificado"><bean:message key="label.licencia.fecha.hasta"/></td>
		<td class="certificado"><bean:message key="label.licencia.dias"/></td>
		<td class="certificado"><bean:message key="label.licencia.estado"/></td>
		<td class="certificado"><bean:message key="label.licencia.fecha.pago"/></td>
		<td class="certificado"><bean:message key="label.licencia.oficina.pago"/></td>
		<td class="certificado"><bean:message key="label.licencia.dias.pago"/></td>
		<td class="certificado"><bean:message key="label.licencia.monto.pagar"/></td>
		<td class="certificado"><bean:message key="label.licencia.forma.pago"/></td>
	</tr>

	<logic:iterate id="register" name="lista.licencias" type='cl.araucana.autoconsulta.vo.LicenciaMedicaVO'>
		<tr>
			<td class="certificado" valign="top"><bean:write name="register" property="fechaDesde" formatKey="format.date"/></td>
			<td class="certificado" valign="top"><bean:write name="register" property="fechaHasta" formatKey="format.date"/></td>
			<td class="certificado" valign="top" align='right' nowrap><bean:write name="register" property="diasLicencia"/></td>
			<%if(register.getVisada()!=2){%>
				<td class="certificado" valign="top"><bean:message key='<%="traductor.estado.licenciaMedica."+register.getCodigoEstadoLicencia()%>'/></td>
			<%}else{%>
				<td class="certificado" valign="top"><bean:message key='<%="traductor.estado.licenciaMedicaCompinOcc."+register.getCodigoEstadoLicencia()%>'/></td>
			<%}%>
			<%if(register.getVisada()!=1) {%>
				<td class="certificado" colspan=5>
					<%if(register.getVisada()==2){%>
						<%if(Integer.parseInt(register.getCodigoEstadoLicencia())>=1 && Integer.parseInt(register.getCodigoEstadoLicencia())<=3){%>					
							<bean:message key='<%="traductor.estado.obsLicMedComOcc."+register.getCodigoEstadoLicencia()+".1"%>'/>
						<%}%>	
						<%if(Integer.parseInt(register.getCodigoEstadoLicencia())==5 || Integer.parseInt(register.getCodigoEstadoLicencia())==6){%>
							<bean:message key='<%="traductor.estado.obsLicMedComOcc."+register.getCodigoEstadoLicencia()%>'/>
						<%}%>
						<%if(Integer.parseInt(register.getCodigoEstadoLicencia())==7){%>
							<%if(register.getCodigoSubEstadoLicencia()==4){%>
								<bean:message key='<%="traductor.estado.obsLicMedComOccSub."+register.getCodigoSubEstadoLicencia()%>'/>
								<bean:write name="register" property="obsIsapre"/>					
							<%}%>
							<%if(register.getCodigoSubEstadoLicencia()==5){%>
								<bean:message key='<%="traductor.estado.obsLicMedComOccSub."+register.getCodigoSubEstadoLicencia()%>'/>
							<%}%>
						<%}%>
					<%}else{%>
						<bean:message key="label.licencia.en.compin"/>
					<%}%>
				</td>				
				<logic:iterate id="observacionCompin" name="register" property="listaObservacionesCompin" type='cl.araucana.autoconsulta.vo.StringVO'>
					<tr>
					<%if(!observacionCompin.getTexto().equals(" ")) {%>
						<td class="certificado" colspan=4>
						   <bean:message key="label.licencia.observacion.compin" />
						</td>
						<td class="certificado" colspan=5>
							<bean:write name="observacionCompin" property="texto"/><br>
						</td>
					<%}%>
					</tr>
				</logic:iterate>
				
			<%
				} else { 
					if(register.getCodigoEstadoLicencia().equals("8")){%>
						<td class="certificado" colspan=5>
							<bean:message key="traductor.estado.obsLicMedComOcc.8"/>
						</td>	
					<%}else	if(!register.getCodigoEstadoLicencia().equals("1")  
							&& !register.getCodigoEstadoLicencia().equals("8")){ %>
						<td class="certificado" colspan=5>
							<bean:write name="register" property="observacion1"/><br>
							<bean:write name="register" property="observacion2"/><br>
							<bean:write name="register" property="observacion3"/>
							<logic:iterate id="observacionCompin" name="register" property="listaObservacionesCompin" type='cl.araucana.autoconsulta.vo.StringVO'>
								<br>
								<bean:write name="observacionCompin" property="texto"/><br>
							</logic:iterate>
						</td>
					<%} else { %>
		 				<td class="certificado"><bean:write name="register" property="fechaDePago" formatKey="format.date"/></td>
						<td class="certificado"><bean:write name="register" property="oficinaPago"/></td>
						<td class="certificado" align='right' nowrap><bean:write name="register" property="diasDePago"/></td>
						<td class="certificado" align='right' nowrap><bean:write name="register" property="montoAPagar" formatKey="format.money"/></td>
						<td class="certificado"><bean:message key='<%="traductor.formaPago.licencia."+register.getCodigoFormaDePago()%>'/></td>
					<%} %>
			<%} %>
			<%if(register.getCodigoObservacion1()==17) {%>
				<tr>
				<td class="certificado" colspan=4>
				   <bean:message key="label.licencia.observacion.compin" />
				<td class="certificado" colspan=5>
					<bean:write name="register" property="observacion1"/>
				</td>
				</tr>
			<%} %>	

			<%if(register.getCodigoObservacion2()==17) {%>					
				<tr>
				<td class="certificado" colspan=4>
				   <bean:message key="label.licencia.observacion.compin" />
				<td class="certificado" colspan=5>
						<bean:write name="register" property="observacion2"/>
				</td>
				</tr>
			<%} %>	

			<%if(register.getCodigoObservacion3()==17) {%>					
				<tr>
				<td class="certificado" colspan=4>
				   <bean:message key="label.licencia.observacion.compin" />
				<td class="certificado" colspan=5>
						<bean:write name="register" property="observacion3"/>
				</td>
				</tr>
			<%} %>	

			
		<tr>
	</logic:iterate>
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