<%@ page language="java"%>
 
<%@ include file = "/includes/env.jsp" %>

<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head> 
 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<%@ include file = "/includes/arriba.jsp" %>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Lista de Profesionales </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top">
 
 <%@ include file = "/includes/menu.jspf"%>
 
          </td>
          <td width="77%" valign="top">
  <br>


            <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
              <tr>
                <td align="center"><table width="25%" border="0" cellspacing="1" cellpadding="0">
                  <table>
                  <tr>
			            <td colspan="2">
			            	<table>
			            		<tr>
			            			<td width="85%"><p class="derecha">Agregar </p>
	                    			</td>
	                    			<td width="15%">
				                    	<div align="center">
				                    	<html:link page="/prepareAdminProfesional.do"><html:img page="/images/botones/boton_ir.gif" border="0" /></html:link>
				                     	</div>
				                     </td>
			            		</tr>
			            	</table>
			            </td>
			        </tr>
				<!-- FOrmulario de FIltro -->
	<html:form action="/getListaProfesionales">

	                  <tr>
	                    <td width="85%"><p class="derecha">Filtrar </p>
	                    </td>
	                    <td width="15%">
	                    	<div align="center">
	                    	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
	                     	</div>
	                     </td>
	                  </tr>
                  </table>                  
                </td>
              </tr>
            </table>
            <table width="529" border="0" cellspacing="2" cellpadding="2">
              <tr bgcolor="#999999">
                <td width="226"><p><a href="#" class="vinculosUp">RUT</a></p>
                </td>
                <td><p>
                  <a href="#" class="vinculosUp">Nombre</a></p></td>
                <td>
                	<p>&nbsp;</p>
                </td>                
                </tr>
              <tr bgcolor="#F0F0F0" class="lookup01">
                <td>
                <html:text property="rutFiltro" styleClass="txtHomeSmall" size="10"/>
                </td>
                <td width="289">
	            <html:text property="nombreFiltro" styleClass="txtHomeSmall" size="10"/>
				</td>
				<td>
					&nbsp;
				</td>
                </tr>
</html:form>                

				<!-- despliegue de los Profesionales -->				
				<logic:iterate id="register" name="lista.profesionales" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Profesional">
					<bean:define id="profesional" name="register" type="cl.araucana.bienestar.bonificaciones.model.Profesional" />
	                
	                <tr bgcolor="#F0F0F0" class="lookup01">
	                	<td>
             	            <html:link page='<%="/setearProfesionalSeleccionado.do?nombre="+profesional.getNombre()+
             	            	"&rut="+profesional.getRut()+
             	            	"&dv="+profesional.getDigito()%>'> 
	                			<bean:write name="register" property="fullRut"/>
	                		</html:link>
	                	</td>
	                	<td>
	                		<bean:write name="register" property="nombre"/>
	                	</td>
	                	<td>
	                		<p><html:link page='<%="/prepareAdminProfesional.do?rut="+profesional.getRut()+
	                							 "&dv="+profesional.getDigito()+
	                							 "&nombre="+profesional.getNombre()%>'> 
	                		Mod
	                		</html:link></p>
	                	</td>
	                </tr>
				</logic:iterate>

            </table>

</td>
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
  </tr>
</table>

<%@ include file = "/includes/pie.jsp" %>

<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
