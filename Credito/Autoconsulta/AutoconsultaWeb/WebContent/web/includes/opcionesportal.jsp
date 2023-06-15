<% String indent="&nbsp&nbsp&nbsp";%>
<%  
String isAdminCosal=(String)request.getSession().getAttribute("isAdminCosal");
%>           
<div align='center'  class='menu'>

<!-- Begin Opciones del menu -->
<script>
	function logoff() {

		document.logout.submit(); 
	}

function vuelveAPerfiles(){	 
	document.welcome.submit();  
}      


</script>        
<logic:present name="servicios">     
   
<br>
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
	<tr>
		<td  align="center">
			<%if(request.getSession(true).getAttribute("nombreEmpresas")!=null) {%>
			<form method="post" action="<%=request.getContextPath().toString()%>/web/Welcome.do" name="welcome">
				<html:link styleClass="grupoopcion" href="javascript:vuelveAPerfiles()">       
					<font color="magenta">Volver</font>
				</html:link>
			</form>
			<%}%>		
		</td>
	</tr>
	<tr>
		<td >
			&nbsp;
		</td>
	</tr> 

	<tr>
		<th  class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.certificados'/></th>
	</tr>	

	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==1) {%>
			<tr>
				<td >
					<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
						<bean:write name="opcion" property="nombre"/>
					</html:link>
				</td>
			</tr>
		<%}%>
	</logic:iterate>

	<tr>
		<td >
			&nbsp;
		</td>
	</tr>

	<tr>
		<th  class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.consultas'/></th>
	</tr>
	
	<% if(isAdminCosal.equalsIgnoreCase("false")) {%>
		<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
			<%if(opcion.getTipo()==0) {%>
				<tr>
					<td >
						
						<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
							<bean:write name="opcion" property="nombre"/>
						</html:link>
					</td>
				</tr>
			<%}%>	
		</logic:iterate>	
	<%}%>	

	<% if(isAdminCosal.equalsIgnoreCase("true")) {%>	
		<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
			<%if(opcion.getTipo()==0 && !opcion.getAction().equalsIgnoreCase("/getListaLiquidaciones.do")) {%>
				<tr>
					<td >
						&nbsp;&nbsp;
						<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
							<bean:write name="opcion" property="nombre"/>
						</html:link>
					</td>
				</tr>
			<%}	%>
			
			<%if(opcion.getTipo()==3) {%>
				<tr>
					<td >
						
						<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
							<bean:write name="opcion" property="nombre"/>
						</html:link>
					</td>
				</tr>
			<%}%>	
		</logic:iterate>	
	<%}%>
		
	<tr>
		<td >
			&nbsp;
		</td>
	</tr>

	
    
	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==2) {%>
			<tr>
				<th  class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.simulacion'/></th>
    		</tr>
			<tr>
				<td >
					
					<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
						<bean:write name="opcion" property="nombre"/>
					</html:link>
				</td>
			</tr>
		<%}%>
	</logic:iterate>

	<tr>
		<td >
			&nbsp;
		</td>
	</tr>	
</table>

</logic:present>

<table border="0" cellpadding="0" cellspacing="0" width="90%">
	<tr>
		<td align="center">
			<form method="post" action="/AutoconsultaWeb/web/logout.do" name="logout">
				<a href="javascript:logoff()" class="grupoopcion">Salir</a>
			</form>		</td>
	</tr> 

	<tr>
		<tH class='grupoopcion'>&nbsp;Certificados</tH>
	</tr>	
			<tr>
				<td>
				    
					<a href="/AutoconsultaWeb/web/getAsignacionFamiliar.do" class="subopcion">Asignación Familiar</a>				</td>
			</tr>
			<tr>
				<td>
				    
					<a href="/AutoconsultaWeb/web/getCertificadoLicenciasMedicas.do" class="subopcion">Subsidio por Incapacidad Laboral</a>				</td>
			</tr>
			<tr>
				<td>
				    
					<a href="/AutoconsultaWeb/web/getCertificadoAfiliacion.do" class="subopcion">Certificado Afiliación</a>				</td>
			</tr>
			<tr>
				<td>
				    
					<a href="/AutoconsultaWeb/web/getDeudaVigente.do" class="subopcion">Deuda Vigente de Crédito</a>				</td>
			</tr>
	<tr>
		<td>&nbsp;		</td>
	</tr>

	<tr>
		<td class='grupoopcion'>&nbsp;Consultas</td>
	</tr>
				<tr>
					<td>
						
						<a href="/AutoconsultaWeb/web/getListaLicenciasMedicas.do" class="subopcion">Estado de Licencias Médicas</a>					</td>
				</tr>
				<tr>
					<td>
						
						<a href="/AutoconsultaWeb/web/getCartolaAhorro.do" class="subopcion">Cartola de Ahorro</a>					</td>
				</tr>
				<tr>
					<td>
						
						<a href="/AutoconsultaWeb/web/getCreditosVigentes.do" class="subopcion">Créditos Vigentes</a>					</td>
				</tr>
				<tr>
					<td>
						
						<a href="/AutoconsultaWeb/web/getListaLiquidaciones.do" class="subopcion">Liquidación de Reembolsos</a>					</td>
				</tr>
	<tr>
		<td>&nbsp;		</td>
	</tr>

	
	<tr>
		<td class='grupoopcion'>&nbsp;Simuladores</td>
	</tr>
			<tr>
				<td>
						<a href="/AutoconsultaWeb/web/prepareSimulacionCreditoWeb.do" class="subopcion">Simulación de Crédito</a>				</td>
			</tr>
			<!--
			<tr>
				<td>
						<a class="subopcion" href="#" onClick="popUpCredEdu('http://api.laaraucana.cl/sv/router.do?service=CREDES');">
						Simulación Crédito Educacional						</a>				</td>
			</tr> -->
	<tr>
		<td>&nbsp;OTRA  DE TESTING	</td> 
	</tr>	
	
</table>


<!-- End Opciones del menu -->

</div>
