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
           <p><html:errors/></p> 

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