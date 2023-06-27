<%-- 
    Document   : estadoLicencia
    Created on : 11-04-2012, 01:03:32 PM
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

<strong><bean:message key="label.licencia.certificado.titulo"/></strong>
<br/><br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<bean:write name="modulo2.fechahoy"/><br/><img src="img/separador_certificados.gif" width="97%" height="7" vspace="5" /><br />


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

          <br /></td>
        </tr>
    </table>
  </div>
  <div style="clear:left;"></div>
</div>
</body>
</html>
