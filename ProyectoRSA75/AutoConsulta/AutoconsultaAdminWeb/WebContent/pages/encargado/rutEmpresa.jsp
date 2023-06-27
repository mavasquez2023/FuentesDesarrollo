<%@ include file = "/includes/env.jsp"%>
<%@ include file = "/includes/header.jsp"%>
<%@ include file = "/includes/top.jsp"%>

<%
   session.setAttribute("back_url","/AutoconsultaAdminWeb/ManageEncargados.do?command=rutEmpresa");
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

          <html:form action='<%= "/ManageEncargados.do?command=details&touch="+Math.random() %>'>
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
              <html:hidden property="validate" value="true"/> 
              <tr>
                <td><p><strong>Ingrese RUT de la empresa para gestionar sus Encargados:</strong></p>
                </td>
               </tr>
               <tr>
                <td><p><html:text property="rut" styleClass="txtHomeSmall" maxlength="8" size="10"/>
                <html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/>
                </p>
                
                </td>
              </tr>
          </table>
          </html:form>
          </td>
        </tr>
      </table>      
    </td>
  </tr>
</table>

<%@ include file = "/includes/footer.jsp"%>