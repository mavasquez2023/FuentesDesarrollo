
<%@ include file = "/includes/env.jsp"%>
<%@ include file = "/includes/header.jsp"%>
<%@ include file = "/includes/top.jsp"%>


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

           <p><strong><font color=red><bean:message key="message.success"/></strong></p>
           
<p>
<logic:present name="message.success"> 
<div class="texto"><bean:write name="message.success"/></div> 
</logic:present>
</p>
           
          </td>
        </tr>
      </table>      
    </td>
  </tr>
</table>

<%@ include file = "/includes/footer.jsp"%>

