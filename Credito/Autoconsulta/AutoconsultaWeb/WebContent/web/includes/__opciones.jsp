<% String indent="&nbsp&nbsp&nbsp"; %>
<%  
String isAdminCosal=(String)request.getSession().getAttribute("isAdminCosal");
%>           
<div align='center'>
<!-- Begin Opciones del menu -->
<script>
function logoff(){

	document.logout.submit(); 
}
function vuelveAPerfiles(){	 
	document.welcome.submit();  
}      
function popUpCredEdu(url){ 
		var ventana;  
		var prop="toolbar=no,scrollbars=yes,location=no,statusbar=no,menubar=no,resizable=no,width=805,height=630";
		ventana=window.open(url,"pubcrededu",prop);
}   
</script>        
<logic:present name="servicios">     
   
<br>

<table border="0" cellpadding="0" cellspacing="0" width="90%">
	<tr>
		<td align="center">
			<form method="post" 

action="/AutoconsultaWeb/web/logout.do" name="logout">
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
		
	
		
			<tr>
				<td>
					
					
					
						<a class="subopcion" href="#" onClick="popUpCredEdu('http://api.laaraucana.cl/sv/router.do?service=CREDES');">
						Simulación Crédito Educacional						</a>				</td>
			</tr>
		
	
		
	
		
	

	<tr>
		<td>&nbsp;		</td>
	</tr>	
</table>
<table border="0" cellpadding="0" cellspacing="0" width="90%">
	<tr>
		<td nowrap align="center">
			<form method="post" 

action="<%=request.getContextPath().toString()%>/web/logout.do" name="logout">
				<html:link styleClass="grupoopcion" href="javascript:logoff()"><font 

color="yellow">Salir</font></html:link>
			</form>	
			<%if(request.getSession(true).getAttribute("nombreEmpresas")!=null) {%>
			<form method="post" action="<%=request.getContextPath().toString()%>/web/Welcome.do" name="welcome">
				<html:link styleClass="grupoopcion" href="javascript:vuelveAPerfiles()">       
					<font color="yellow">Volver</font>
				</html:link>
			</form>
			<%}%>		
		</td>
	</tr>

<%-- DESHABILITACION DEL CAMBIO DE CLAVE (REQ4369, gpavez@hotmail.com. 16/11/2007).
    <% if (session.getAttribute("encargadoEmpresa")==null) { %>
	
	<tr>
		<td nowrap>
			<html:link styleClass="subopcion" page="/changeClave.do">
			<font color="white"><bean:message key="opcion.cambio.clave"/></font>
			</html:link>
		</td>
	</tr>
	
	<% } %>
	
	<tr>
		<td nowrap>
			&nbsp;
		</td>
	</tr>	
--%>

	<tr>
		<td nowrap>
			*&nbsp;
		</td>
	</tr> 

	<tr>
		<td nowrap class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.certificados'/></td>
	</tr>	

	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==1) {%>
			<tr>
				<td nowrap>
				    &nbsp;&nbsp;
					<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
						<bean:write name="opcion" property="nombre"/>
					</html:link>
				</td>
			</tr>
		<%}%>
	</logic:iterate>

	<tr>
		<td nowrap>
			&nbsp;
		</td>
	</tr>

	<tr>
		<td nowrap class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.consultas'/></td>
	</tr>
	
	<% if(isAdminCosal.equalsIgnoreCase("false")) {%>
		<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
			<%if(opcion.getTipo()==0) {%>
				<tr>
					<td nowrap>
						&nbsp;&nbsp;
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
					<td nowrap>
						&nbsp;&nbsp;
						<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
							<bean:write name="opcion" property="nombre"/>
						</html:link>
					</td>
				</tr>
			<%}	%>
			
			<%if(opcion.getTipo()==3) {%>
				<tr>
					<td nowrap>
						&nbsp;&nbsp;
						<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
							<bean:write name="opcion" property="nombre"/>
						</html:link>
					</td>
				</tr>
			<%}%>	
		</logic:iterate>	
	<%}%>
		
	<tr>
		<td nowrap>
			&nbsp;
		</td>
	</tr>

	<tr>
		<td nowrap class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.simulacion'/></td>
	</tr>
    
	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==2) {%>
			<tr>
				<td nowrap>
					&nbsp;&nbsp;
					<% int pos= opcion.getAction().indexOf("http:");%>
					<%if(pos==-1) {%>
						<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
						<bean:write name="opcion" property="nombre"/>
						</html:link>
					<%}else{%>
						<a class="subopcion" href="#" onclick="popUpCredEdu('<%= opcion.getAction() %>');">
						<bean:write name="opcion" property="nombre"/>
						</a>
					<%}%>
				</td>
			</tr>
		<%}%>
	</logic:iterate>

	<tr>
		<td nowrap>
			&nbsp;
		</td>
	</tr>	
</table>

</logic:present>

<!-- End Opciones del menu -->

</div>
