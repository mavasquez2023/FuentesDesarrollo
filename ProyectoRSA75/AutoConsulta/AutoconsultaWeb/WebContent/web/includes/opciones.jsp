<% String indent="&nbsp&nbsp&nbsp"; %>
<%  
String isAdminCosal=(String)request.getSession().getAttribute("isAdminCosal");
%>           
<div  class="menu" align="center">

<!-- Begin Opciones del menu  -->
<script>
	function llogoff(){

		document.logout.submit(); 
	}

function vvuelveAPerfiles(){	 
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
<table border="0" cellpadding="0" cellspacing="0" width="95%">
<!--
	<tr>
		<td nowrap align="center">
			<form method="post" 

action="<%=request.getContextPath().toString()%>/web/logout.do" name="logout">
				<html:link styleClass="grupoopcion" href="javascript:logoff();"><font 

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
-->
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
		<td nowrap class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.certificados'/></td>
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
	
	
	<!-- Certificados de satelites -->
	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==4) {%>
			<tr>
				<td>
					<a href="<%=request.getContextPath() %><bean:write name="opcion" property="action"/>" class="subopcion"><bean:write name="opcion" property="nombre"/></a>						
				</td>
			</tr>
			
		<%}%>
	</logic:iterate>  
	<!-- Fin certificados de satelites -->
	
	<tr>
		<td >&nbsp;
			
		</td>
	</tr>

	<tr>
		<td  class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.consultas'/></td>
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
	
	<!-- Compromiso total -->
	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==6) {%>
			<tr>
				<td>
					<a href="<%=request.getContextPath() %><bean:write name="opcion" property="action"/>" class="subopcion"><bean:write name="opcion" property="nombre"/></a>				
				</td>
			</tr>
		<%}%>
	</logic:iterate>  
	<!-- Compromiso total -->

	<% if(isAdminCosal.equalsIgnoreCase("true")) {%>	
		<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
			<%if(opcion.getTipo()==0 && !opcion.getAction().equalsIgnoreCase("/getListaLiquidaciones.do")) {%>
				<tr>
					<td >
						
						<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
							<bean:write name="opcion" property="nombre"/>
						</html:link>
					</td>
				</tr>
			<%}	%>
			
			<%if(opcion.getTipo()==3) {%>
				<tr>
					<td >
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
		<td >&nbsp;
			
		</td>
		
	</tr>

	<tr>
		<td  class='grupoopcion'>&nbsp;<bean:message key='opcion.grupo.simulacion'/></td>
	</tr>
    
	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==2) {%>
			<tr>
				<td >
					
					<% int pos= opcion.getAction().indexOf("http:");%>
					<%if(pos==-1) {%>
					<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
						<bean:write name="opcion" property="nombre"/>
					</html:link>					
					<%}else{%>
						
					<%}%>
				</td>
			</tr>
		<%}%>
	</logic:iterate>

<!-- Simuladores de satelites -->
	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
		<%if(opcion.getTipo()==5) {%>
			<tr>
				<td>
					<a href="<%=request.getContextPath() %><bean:write name="opcion" property="action"/>" class="subopcion"><bean:write name="opcion" property="nombre"/></a>	
				</td>
			</tr>
		<%}%>
	</logic:iterate>  
	<!-- Fin Simuladores de satelites -->  

</table>

</logic:present>

<!-- End Opciones del menu -->

</div>
