<%@ include file = "/includes/env.jsp"%>
<%@ include file = "/includes/header.jsp"%>
<%@ include file = "/includes/top.jsp"%>

<script language="JavaScript">

function cambiarCajas(){
	var cajaPrincipal = document.forms[0].cajaPrincipal;
	var cajas = document.forms[0].indice;

	cajas.checked = cajaPrincipal.checked ;

	for (i = 0; i < cajas.length; i++){
		cajas[i].checked = cajaPrincipal.checked ;
	}	
}

</script>

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

		  <html:form action="/registrarEncargado.do">

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
					  <table border="0" cellspacing="1" cellpadding="1">
					  <br>
			              <tr>
			                <td><p><strong>Rut Encargado: </strong></p></td>
			                <td><p>
			                	<html:text property="rut" styleClass="txtHomeSmall" maxlength="8" size="10" readonly="true"/>
			                </p>
			                </td>
			                <td><p><strong>Nombre: </strong></p></td>
			                <td><p>
			                	<input type="text" name="nombre" value="<bean:write name="encargado" property="nombre"/>" class="txtHomeSmall" maxlength="20" size="20">
			                	</p>
			                </td>
			              </tr>
			              <tr>
			              	<td><p><strong>Apellido paterno: </strong></p></td>
			                <td><p>
			                	<input type="text" name="apellidop" value="<bean:write name="encargado" property="apellidoPaterno"/>" Class="txtHomeSmall" maxlength="15" size="15"/>
			                </p>
			                </td>
			                <td><p><strong>Apellido materno: </strong></p></td>
			                <td><p>
			                	<input type="text" name="apellidom" value="<bean:write name="encargado" property="apellidoMaterno"/>" Class="txtHomeSmall" maxlength="15" size="15"/>
			                </p>
			                </td>
			              </tr>
			          </table>
					 
                </td>
              </tr>
              
          </table>

          <table width="97%" border="0" cellspacing="1" cellpadding="1">
              <tr class="menuDespl">
                <td width="25%" class="menuDespl"><p><strong>Lista de Oficina y Sucursales de esta Empresa</strong></p></td>
              </tr>
          </table>

          <table width="97%" border="0" cellspacing="1" cellpadding="1">
           <tr>
				<td  class="menuDespl" width="10%" align="center">
					&nbsp;
				</td>           
                <td class="menuDespl">OFICINA
				</td>
                <td class="menuDespl">SUCURSAL</td>
              </tr>

		  <logic:iterate id="detalle" name="listaOficinasSucursalesEncargado" indexId="i" type ="cl.araucana.autoconsulta.vo.OficinasSucursalesVO">
              <tr>
                <td bgcolor="#F0F0F0" class="lookup01" width="10%" align="center">
                	<input type="checkbox" name="indice" value="<%=i%>" <%if(detalle.isACargo()){%>checked<%}%>>
                </td>
                <td><p><bean:write name="detalle" property="oficina"/></p></td>
                <td><p><bean:write name="detalle" property="sucursal"/></p></td>
              </tr>
		  </logic:iterate>
          </table>
		  <br>
          <table>
			<tr>
            <td>
            	<p><strong>Aceptar</strong></p>
            </td>
            <td>
            	<p><html:image page="/images/botones/boton_ir.gif" alt="Aceptar" border="0"/></p>
            </td>
       </html:form>
            <td>
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
                   
            <bean:define id="empresa" name="empresa" type="cl.araucana.autoconsulta.vo.EmpresaVO" />
            <td>
            	<p><strong>Volver</strong></p>
            </td>
            <td>
            	<p>
	        	<html:link page='<%= "/ManageEncargados.do?command=details&rut="+empresa.getRut() %>'>
	        	<html:img page="/images/botones/boton_ir.gif" alt="Volver" border="0"/>
	        	</html:link>
	            </p>
            </td>
          </tr>
          </table>
         
          </td>
        </tr>
      </table>   
      
      
      
         
    </td>
  </tr>
</table>

          


<%@ include file = "/includes/footer.jsp"%>