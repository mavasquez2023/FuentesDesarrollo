<%@page contentType="text/html"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
    

<!-- Begin de la pagina particular -->

<% java.util.Hashtable nombres = (java.util.Hashtable)session.getAttribute("nombreEmpresas"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>La Araucana</title>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<script>
function doBack() {
  document.formBack.submit();
}
</script>
</head>


<body>
<div class="header">
  <div class="logo"><a href="http://www.laaraucana.cl"><img src="img/logo_araucana_ss.gif" alt="laaraucana.cl" width="300" height="50"></a></div>
  <h1>Auto Consulta</h1>
</div>
<div class="content">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" valign="top" style="padding-top:15px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="31%" valign="top" style="font-size:26px; color:#004686"><strong>Bienvenido(a)</strong></td>
              <td width="69%" align="right" valign="top" style="font-size:26px;color:#666;">
                 <logic:notEmpty name="empresa.nombre"><bean:write name="usuario.nombre"/></logic:notEmpty><br />
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
	        <html:link styleClass="texto" page='<%= "/Welcome.do?accion=4&empresaACargo="+(-1) %>'>
			<b><bean:write name="datosUsuario" property="fullRut"/>&nbsp;<bean:write name="datosUsuario" property="nombre"/></b> 
	       	</html:link>
			</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>
		
<logic:notEmpty name="empresasACargo">

 
		<tr>
			<td>
				<strong><bean:message key="label.empresaacargo.seleccion.empresas"/></strong>
			</td>
		</tr>



<logic:iterate id="register" name="empresasACargo" type="cl.araucana.autoconsulta.vo.EmpresaACargoVO">
		<tr>
			<td height="40">
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <html:link styleClass="texto" page='<%= "/Welcome.do?accion=4&empresaACargo="+register.getRut() %>'>
			<b><%= register.getRut()+"-"+register.getDv()+" "+(nombres==null || nombres.get(String.valueOf(register.getRut()))==null ? " -- sin información --" : (String)nombres.get(String.valueOf(register.getRut()))) %></b>        
	       	</html:link>
			</td>
		</tr>
</logic:iterate>  
</logic:notEmpty>
</table>

    </div>
    <div class="grid_12"><a href="#" onclick='doBack();'/>Volver</a></div>
</div>
<form name="formBack" id="formBack" method="post" action="m.ingreso.jsp">
</form>
</body>
</html>
