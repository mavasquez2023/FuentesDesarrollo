<%@page contentType="text/html"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
    

<!-- Begin de la pagina particular -->

<% 
java.util.Hashtable nombres = (java.util.Hashtable)session.getAttribute("nombreEmpresas"); 
session.setAttribute("chkUser",((cl.araucana.autoconsulta.vo.UsuarioVO)session.getAttribute("datosUsuario")).getFullRut());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>La Araucana</title>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link href="css/grid.css" rel="stylesheet" type="text/css" />
<link href="css/ar-paginacion.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="js/ar-paging.js"></SCRIPT>
<script>
function doBack() {
  document.formBack.submit();
}
</script>
</head>


<body>
<div class="container_12 altura-contenedor">
  <div class="grid_5">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td width="95%">&nbsp;</td>
        </tr>
	    <tr>
	      <td valign="top"><img src="img/logo_araucana_interior.jpg" width="311" height="36" /></td>
        </tr>
	    <tr>
	      <td align="right"></td>
        </tr>
      </table>
  </div>
  <div class="grid_7" style="height:142px;">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" valign="top" style="padding-top:15px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="31%" valign="top" style="font-size:26px; color:#004686"><strong>Bienvenido(a)</strong></td>
              <td width="69%" align="right" valign="top" style="font-size:26px;color:#666;">
                 <logic:notEmpty name="usuario.nombre"><bean:write name="usuario.nombre"/></logic:notEmpty><br />
              </td>
            </tr>
            <tr>
              <td valign="top" style="font-size:21px; color:#004686">&nbsp;</td>
              <td align="right" valign="top" style="font-size:21px;color:#666;">
   <logic:notEmpty name="empresa.nombre">
	/ <bean:write name="empresa.nombre"/>
   </logic:notEmpty>&nbsp;
              </td>
            </tr>
          </table></td>
        </tr>
      </table>
    </div>


	<div class="grid_11">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <td align="center">

<table border="0" cellpadding="2" cellspacing="2" width="70%">
<logic:empty name="esSoloEncargado">
		<tr>
			<td>
				<strong><bean:message key="label.empresaacargo.seleccion"/></strong>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>
				<strong><bean:message key="label.empresaacargo.seleccion.personal"/></strong>
			</td>
		</tr>

		<tr>
			<td>
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <html:link styleClass="texto" page='<%= "/loginHuella.do?accion=4&empresaACargo="+(-1) %>'>
			<b><bean:write name="datosUsuario" property="fullRut"/>&nbsp;<bean:write name="datosUsuario" property="nombre"/></b> 
	       	</html:link>
			</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>
</logic:empty>&nbsp;





</table>
<logic:notEmpty name="empresasACargo">
<form action="" method="get" enctype="application/x-www-form-urlencoded">
<table border="0" cellpadding="2" cellspacing="2" width="70%" id="results">
<tr>
  <td><strong><bean:message key="label.empresaacargo.seleccion.empresas"/></strong></td>
</tr>
<logic:iterate id="register" name="empresasACargo" type="cl.araucana.autoconsulta.vo.EmpresaACargoVO">
		<tr>
			<td height="40">
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <html:link styleClass="texto" page='<%= "/loginHuella.do?accion=4&empresaACargo="+register.getRut() %>'>
			<b><%= register.getRut()+"-"+register.getDv()+" "+(nombres==null || nombres.get(String.valueOf(register.getRut()))==null ? " -- sin información --" : (String)nombres.get(String.valueOf(register.getRut()))) %></b>        
	       	</html:link>
			</td>
		</tr>
</logic:iterate>  
</table>
<div id="pageNavPosition"></div>
</form>

</logic:notEmpty>

    </div>
    <div class="grid_12"><img src="img/btn_volver.jpg" width="152" height="50" align="left"  onclick='doBack();'/></div>
</div>
<form name="formBack" id="formBack" method="post" action="ingreso.jsp">
</form>
<script type="text/javascript"><!--
    var pager = new Pager('results', 7); 
    pager.init(); 
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
//--></script>

</body>
</html>
