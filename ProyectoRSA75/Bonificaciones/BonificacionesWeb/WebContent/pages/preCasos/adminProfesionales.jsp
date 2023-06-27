<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head>
 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/prepareListaPreCasos.do" target="_top">Lista de Pre-Casos</html:link></td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">


	    <html:form action="/registrarProfesional">
     	  <table>
          	<tr>
                  <td>
		       		&nbsp;
		       	</td>
		    </tr>     	  
     	  <tr>
                 <td colspan="6"><p class="derecha">
		       		Registrar <html:image page="/images/botones/boton_ir.gif" border="0" /></p>
		       	</td>
		    </tr>
          	<tr>
                  <td colspan="6">
		       		&nbsp;
   		           	<html:errors/>
		       	</td>
		    </tr>
		    <tr>
		        <td>
		        	<p><strong>
		          		<bean:message key="label.rut"/> <bean:message key="label.obligatorio"/>:
		          	</strong></p>
		        </td>
                <td>
                   	<p>
	                   <html:text property="rut" styleClass="txtHomeSmall" maxlength="8" size="12"/>  
	                   <html:text property="dv" styleClass="txtHomeSmall" maxlength="1" size="2"/>
    	             </p>
                </td>
		    </tr>
		    <tr>
		      	<td>
		       		<p><strong>
		       			<bean:message key="label.socio.nombre"/> <bean:message key="label.obligatorio"/>:
		       		</strong></p>
		       	</td>
                <td>
                  	<p>
	                   <html:text property="nombre" styleClass="txtHomeSmall" maxlength="50" size="50"/>
    	            </p>
                </td>
		    </tr>
     	  	</table>
       	  </html:form>
          </td>          
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
  </tr>
</table>
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>