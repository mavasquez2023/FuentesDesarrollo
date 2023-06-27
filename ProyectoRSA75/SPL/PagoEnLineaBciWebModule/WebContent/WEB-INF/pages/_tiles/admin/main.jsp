<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<%   // Objeto de Permisos de la Aplicación
 
   cl.araucana.spl.action.roles.UserInformation userinformation = (cl.araucana.spl.action.roles.UserInformation)session.getAttribute("application.userinformation");
   if (userinformation==null) userinformation = new cl.araucana.spl.action.roles.UserInformation();
 
 %>
<table width="97%" border="0" cellpadding="0" cellspacing="0">
<tr> 
  <td class="titulos_formularios">
   <% if(userinformation.getAcciones() != null) { %>
 	 Seleccione una opci&oacute;n del men&uacute;.
   <% } %>
    <% if(userinformation.getAcciones() == null) { %>
 	 Usted no tiene asignado, opciones de men&uacute; para este sistema.
   <% } %>
  </td>
</tr>
</table>