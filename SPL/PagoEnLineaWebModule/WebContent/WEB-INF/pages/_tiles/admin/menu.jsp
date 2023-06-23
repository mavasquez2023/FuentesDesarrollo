<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<%@ page language="java"%>

<style>
a:hover {
	color: #333333;
	text-decoration: none;
}
a:active {
	color: #333333;
	text-decoration: underline;
}
a:visited {
	color: #333333;
	text-decoration: underline;
}
a {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333333;
	text-decoration: underline;
}
</style>

<script type="text/javascript">
function ocultamenu(elemento){
  var menu = document.getElementById(elemento);
  menu.style.display = "none";
}
function despliega(elemento){
  var menu = document.getElementById(elemento);
    if(menu.style.display == "none"){
      menu.style.display = "block";
    }
    else{
      menu.style.display = "none";
    }
}
</script> 
<%   // Objeto de Permisos de la Aplicación
 
   cl.araucana.spl.action.roles.UserInformation userinformation = (cl.araucana.spl.action.roles.UserInformation)session.getAttribute("application.userinformation");
   if (userinformation==null) userinformation = new cl.araucana.spl.action.roles.UserInformation();
 
 %>
<table width="170" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td height="0" valign="top"><a href="#" onclick="despliega('admin');return false;">
      <table width="170" border="0" cellspacing="0" cellpadding="0">
  		<tr>
          <td class="fondomenu">Administraci&oacute;n</td>
  		</tr>
	  </table>
	  </a>
      <table width="146" border="0" cellspacing="0" cellpadding="0" id="admin">
        <tr> 
          <td valign="top" class="vde-menu"><table width="100%" border="0" cellspacing="3" cellpadding="0">
             <% if (userinformation.hasAccess("impRendicion")) { %>
	              <tr> 
	                <td class="menusegmentos"><a href="<c:url value="/admin/importarRendicion/frm.do" />">Importar Rendici&oacute;n</a></td>
	              </tr>
              <% } %>
              <% if (userinformation.hasAccess("adminInconsistencias")) { %>
	              <tr> 
	                <td class="menusegmentos"><a href="<c:url value="/admin/listarInconsistencias.do" />">Admin. Inconsistencias</a></td>
	              </tr>
               <% } %>
                <% if (userinformation.hasAccess("concluirPagos")) { %>
	              <tr> 
	                <td class="menusegmentos"><a href="<c:url value="/admin/listarPagos.do" />">Concluir Pagos</a></td>
	              </tr>
              <% } %>
            </table></td>
        </tr>
        <tr> 
          <td><img src="<c:url value="/images/vde_bas.gif"/>" width="146" height="7" /></td>
        </tr>
      </table>
    </td>
</tr>
</table>