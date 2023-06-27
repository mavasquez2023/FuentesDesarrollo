<%@ page isErrorPage="true" %>

<%@ include file = "/includes/env.jsp"%>
<%@ include file = "/includes/header.jsp"%>
<%@ include file = "/includes/top.jsp"%>


<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
      </tr>
    </table>
    
    <%  // Captura el Mensaje de Error
    String code=(String)request.getAttribute("error.code");
    String msg=(String)request.getAttribute("error.message");
    String info=(String)request.getAttribute("error.info");

    // Por prioridad: Exception JSP
    if (exception!=null) {
		code=null;
		msg=exception.getMessage();
    }

   %>
    
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/opciones.jsp" %></td>
          <td valign="top"> 

           <p><strong>La Operación no pudo ser completada</strong></p>
    
<p>           
<font size='3' color='red'>

<%   if (code!=null) { %>
     
     <bean:message key='<%= code %>'/>
     
<%   } else {
        if (msg!=null) { %> 

     <bean:write name="error.message"/> 

<%      } else {  %>

          Hubo Error de Procedimiento

<%      }
     }  %>

</font>

<br>
<font size='2' color='black'><%= info!=null ? info : "" %></font>
</p>


<div align='left'>
        <a href='<%= session.getAttribute("back_url") %>'>
        <html:img page="/images/botones/boton_volver.gif" border="0" alt="Volver"/>
        </a>
</div>
           
          </td>
        </tr>
      </table>      
    </td>
  </tr>
</table>




<%@ include file = "/includes/footer.jsp"%>

