<%-- 
    Document   : asignacionFamiliar
    Created on : 10-04-2012, 03:58:56 PM
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

<strong><bean:message key="label.asignacion.familiar.certificado.titulo"/></strong>
<br/><br/>
<strong>Nombre: </strong><bean:write name="afiliado.nombre"/> <br/>
<strong>RUT: </strong><bean:write name="afiliado.fullRut"/><br /><br />
<bean:write name="modulo2.fechahoy"/><br/><img src="img/separador_certificados.gif" width="932" height="7" vspace="5" /><br />


<bean:define id="cargas" name="asignacionFamiliar" property="cargas"/>
<bean:define id="codigoEstadoEmpresa" name="asignacionFamiliar" property="codigoEstadoEmpresa" type='java.lang.String'/>
<bean:define id="fechaAfiliacion" name="asignacionFamiliar" property="fechaAfiliacion" type='java.lang.String'/>
<bean:define id="nombreEmpresa" name="asignacionFamiliar" property="nombreEmpresa" type='java.lang.String'/>

<br>
 <bean:message key="label.asignacion.familiar.certificado.se.encuentra"/>
 <bean:message key='<%="traductor.estado.empresa."+codigoEstadoEmpresa%>'/>
 <bean:message key="label.asignacion.familiar.certificado.acontar"/>
 <%= fechaAfiliacion.substring(8,10) + "/" + fechaAfiliacion.substring(5,7) + "/" + fechaAfiliacion.substring(0,4)%>
 <%if(!nombreEmpresa.equals("")) {%> 
 	<bean:message key="label.asignacion.familiar.certificado.empresa"/>
 <% } %>
 <bean:write name="asignacionFamiliar" property="nombreEmpresa"/>
 <bean:message key="label.punto"/>

<logic:notEmpty name="cargas">
	<bean:message key="label.asignacion.familiar.certificado.registrando"/>
	<bean:write name="asignacionFamiliar" property="cantidadCargas"/>
	<logic:present name="activa">
		<bean:message key="label.asignacion.familiar.certificado.asignaciones.autorizadas"/>
	</logic:present>
	<logic:notPresent name="activa">
		<bean:message key="label.asignacion.familiar.certificado.asignaciones.suspendidas"/>
	</logic:notPresent>
	<br>
	<br>
	<table>
		<tr>
			<td class="certificado"><bean:message key="label.asignacion.familiar.certificado.tramo.vigente"/></td>
			<td class="certificado"><bean:write name="asignacionFamiliar" property="descripcionTramoVigente"/></td>
			<td class="certificado">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
			<td class="certificado">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
			<td class="certificado">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
			<td class="certificado"><bean:message key="label.asignacion.familiar.certificado.valor.carga"/>:</td>
			<td class="certificado"><bean:write name="asignacionFamiliar" property="valorCarga" formatKey="format.money"/></td>
		</tr>
	</table>
	<br>
	<table width='50%' border='1' cellspacing='0' align = 'center'>
		<tr align='center'>
			<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.rut.causante"/></td>
			<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.nombre.causante"/></td>
			<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.parentesco"/></td>
			<logic:present name="activa">
				<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.fecha.nacimiento"/></td>
				<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.fecha.vencimiento"/></td>
			</logic:present>
			<logic:notPresent name="activa">
				<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.fecha.autorizada.desde"/></td>
				<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.fecha.suspendida.desde"/></td>
			</logic:notPresent>
			<td nowrap class="certificado"><bean:message key="label.asignacion.familiar.certificado.asignacion.invalidez"/></td>
		</tr>
		<logic:iterate id="register" name="cargas" type='cl.araucana.autoconsulta.vo.CargaFamiliarVO'>
			<tr>
				<td nowrap class="certificado"><bean:write name="register" property="fullRut"/></td>
				<td nowrap class="certificado"><bean:write name="register" property="fullNombre"/></td>
				<td nowrap class="certificado"><bean:message key='<%="traductor.parentezco."+register.getCodigoParentezco()%>'/></td>
				<logic:present name="activa">
					<%if(register.getFechaNacimiento()!=null) {%>
						<td nowrap class="certificado"><bean:write name="register" property="fechaNacimiento" formatKey="format.date"/></td>
					<%} else { %>
					 	<td class="certificado">&nbsp</td>
					<% } %>				
					<%if(register.getFechaVencimiento()!=null) {%>
						<td nowrap class="certificado"><bean:write name="register" property="fechaVencimiento" formatKey="format.date"/></td>
					<%} else { %>
					 	<td class="certificado">&nbsp</td>
					<% } %>
				</logic:present>
				<logic:notPresent name="activa">
					<%if(register.getFechaAutorizacion()!=null) {%>
						<td nowrap class="certificado"><bean:write name="register" property="fechaAutorizacion" formatKey="format.date"/></td>
					<%} else { %>
					 	<td class="certificado">&nbsp</td>
					<% } %>					

					<%if(register.getFechaAnulacion()!=null) {%>
						<td nowrap class="certificado"><bean:write name="register" property="fechaAnulacion" formatKey="format.date"/></td>
					<%} else { %>
					 	<td class="certificado">&nbsp</td>
					<% } %>	
				</logic:notPresent>
				<td nowrap class="certificado"><bean:message key='<%="traductor.condicion.invalidez."+register.getCodigoCondicionInvalidez()%>'/></td>
			</tr>
		</logic:iterate>
	</table>
	<logic:present name="fechaDesde">
		<br>
		<br>
		<bean:message key="label.asignacion.familiar.certificado.sin.autorizacion"/>:
		<bean:write name="fechaDesde" formatKey="format.date"/>
	</logic:present>
</logic:notEmpty>

<br>
<br>

<logic:empty name="cargas">
	<bean:message key="label.asignacion.familiar.certificado.no.cargas"/>
</logic:empty>
<br>
<br>
<bean:message key="label.asignacion.familiar.certificado.extiende"/>
<br>
<br>
<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td nowrap class="certificado">
		<bean:write name="asignacionFamiliar" property="fechaHoy" formatKey="format.fullDate"/>
		</td>
		<td align='right' class="certificado">
		<img src="/AutoconsultaWeb/web/images/asignacionFamiliar/CopiadefirmaSIL.gif">
		</td>
	</tr>
	<tr>
		<td>&nbsp</td>
	</tr>
	<tr>
		<td class="certificado" colspan="2">
			<bean:message key="label.validador.id"/>: <bean:write name="asignacionFamiliar" property="id"/><br>
			<bean:message key="label.validador.mensaje"/>
		</td>
	</tr>
</table>

          <br /></td>
        </tr>
    </table>
  </div>
  <div style="clear:left;"></div>
</div>
</body>
</html>
