<%@ include file = "/includes/env.jsp"%>
<%@ include file = "/includes/header.jsp"%>
<%@ include file = "/includes/top.jsp"%>

<%
   session.setAttribute("back_url","/AutoconsultaAdminWeb/ManageEncargados.do?command=details&rut="+request.getParameter("rut"));
%>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
      </tr>
    </table>
    
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/opciones.jsp" %>
          </td>
          <td valign="top">  

          <table width="97%" border="0" cellspacing="1" cellpadding="1">
              <tr> 
                <td width="25%"><p><b>Rut Empresa:</b></p></td>
                <td><p><bean:write name="empresa" property="fullRut"/></p></td>
              </tr>
              <tr>
                <td><p><b>Nombre:</b></p></td>
                <td><p><bean:write name="empresa" property="nombre"/></p></td>
              </tr>
              <tr>
                <td colspan="2">
                	 <html:form action="/prepareDetalleEncargado.do">
					  <html:hidden property="validate" value="true"/>
					  <html:hidden property="checkAll" value=""/>
					  
					  <table border="0" cellspacing="1" cellpadding="1">
					  <br>
					  <br>
			              <tr>
			                <td colspan="2"><p><strong>Agregar Encargado: </strong></p></td>
			                <td></td>
			              </tr>
			              <tr>
			                <td><p><strong>Rut: </strong></p></td>
			                <td><p>
			                	<html:text property="rut" styleClass="txtHomeSmall" maxlength="8" size="10"/>
			                	<html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/>
			                </p>
			                </td>
			              </tr>
			          <br>
			          <br>
			          </table>
					</html:form> 
                </td>
              </tr>
              
          </table>

		
		         
          
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
              <tr class="menuDespl">
                <td width="25%" class="menuDespl"><p><strong>Lista Actual de Encargados de esta Empresa</strong></p></td>
              </tr>
          </table>

          <table width="97%" border="0" cellspacing="1" cellpadding="1">
           <tr>
           		<td class="menuDespl"></td>
                <td class="menuDespl">NOMBRE
				</td>           		
                <td class="menuDespl">RUT
				</td>
                <td class="menuDespl">TODAS LAS OFICINAS Y SUCURSALES</td>
              </tr>
		  <logic:iterate id="register" name="encargados" type ="cl.araucana.autoconsulta.vo.EmpresaACargoVO">
              <tr>
                <td width="10%">
          	    <html:link page='<%= "/ManageEncargados.do?command=delete&touch="+Math.random()+"&rut="+register.getRutEncargado() %>'>
				<html:img page="/images/goma.gif" alt="Eliminar este Encargado" border="0" onclick="return confirm('Desea realmente eliminar el encargado');"/>
            	</html:link>
				</td>
                <td>
                	<p><bean:write name="register" property="fullNombre"/></p>
                </td>				
                <td><p><html:link page='<%="/prepareDetalleEncargado.do?rutEncargado="+register.getRutEncargado()%>'><bean:write name="register" property="rutEncargado"/></p></html:link></td>
                <td>
                	<p><bean:write name="register" property="allOficinasSucursales"/></p>
                </td>
              </tr>
		  </logic:iterate>
          </table>

         
          </td>
        </tr>
      </table>   
      
      
      
         
    </td>
  </tr>
</table>

          


<%@ include file = "/includes/footer.jsp"%>