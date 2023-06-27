<%@ include file = "/includes/env.jsp"%>
<%@ include file = "/includes/header.jsp"%>
<%@ include file = "/includes/top.jsp"%>


<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="0" cellpadding="0">
      <tr valign="top">
        <td width="80%"><table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
          </tr>
        </table>          
          <h1>Sistema de Autoconsulta - Administración</h1>
          <p>Escoja una opci&oacute;n de las siguientes:        </p><table width="694" border="0" cellspacing="0" cellpadding="2">
          <!--DWLayoutTable-->
          
          <tr align="center">
            <td width="90">
            <% if (userinformation.hasAccess("encargados")) { %>
          	    <html:link page="/ManageEncargados.do?command=rutEmpresa" target="_top">
				<html:img page="/images/botones_home/mngEncargados.gif" alt="Encargados de Empresas" border="0"/>
            	</html:link>
            <% } %>
		    </td>
            <td width="91">
  		    </td>
            <td width="94">            
  			</td>
            <td width="96">
			</td>
            <td width="96">
			</td>
            <td width="94">
			</td>
            <td width="105">
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




