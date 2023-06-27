<%@ include file = "/includes/env.jsp"%>
<%@ include file = "/includes/header.jsp"%>
<%@ include file = "/includes/top.jsp"%>

<%
   session.setAttribute("back_url","/AutoconsultaAdminWeb/ManageEncargados.do?command=rutEmpresa");
%>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td width="21%" valign="top"><%@ include file="/includes/opciones.jsp" %>
    </td>
    <td valign="top"> 

<table width="79%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <span class="tituloconsultas"><h1>Empresa / Usuario asigna permisos</h1></span>
    </td>
  </tr>
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
	      <td><p><strong>Empresa:</strong></p></td>
	      <td><p><bean:write name="empresa" property="fullRut"/> - <bean:write name="empresa" property="nombre"/></p></td>
      </tr>
      <tr>
	      <td><p><strong>Usuario:</strong></p></td>
	      <td><p><bean:write name="encargado" property="rutEncargado"/> - <bean:write name="encargado" property="nombre"/> <bean:write name="encargado" property="apellidoPaterno"/> <bean:write name="encargado" property="apellidoMaterno"/></p></td>
      </tr>
    </table>
<!-- inicio asignacion -->
<logic:present name="validation.message">
<div class="botongris">
<br/><strong><bean:write name="validation.message"/></strong><br/><br/>

</logic:present>
<html:form action='/asignarServicios' method="POST">
<html:hidden property="validar" value="true"/>
<html:hidden property="accion" value="Asignar"/>
<br/>
  <div class="table3">
    <table width="90%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="6" class="celeste" align="left"> Servicios del usuario </td>
      </tr>
    </table>
<br/>
<table border="0" cellpadding="2" cellspacing="2" width="90%">
<logic:notEmpty name="lstServicios">
  <logic:iterate id="srv" name="lstServicios">
      <tr>
         <logic:equal name="srv" property="habilitado" value="true">
           <td align="left" class="c11azul"><input checked="true" type="checkbox" name="accesos" value="<bean:write name="srv" property="codigo"/>"/></td>
        </logic:equal>
        <logic:notEqual name="srv" property="habilitado" value="true">
          <td align="left" class="c11azul"><input type="checkbox" name="accesos" value="<bean:write name="srv" property="codigo"/>"/></td>
        </logic:notEqual>
        <td align="left" class="c11azul">&nbsp;<bean:write name="srv" property="descripcion"/></td>
      </tr>
  </logic:iterate>
</logic:notEmpty>

	<tr>
		<td class="texto" align='center'>
		  <p>
           		Volver
           		<bean:define id="rutEncargado" name="encargado" property="rutEncargado"/>
	        	<html:link page='<%="/prepareDetalleEncargado.do?rutEncargado=" + rutEncargado %>'>
	        	<html:img page="/images/botones/boton_ir.gif" alt="Volver" border="0"/>
	        	</html:link>
	       </p>
		</td>
		<td class="texto" align='center'>
		  <p>
           		Aceptar
	        	<html:image src="/AutoconsultaAdminWeb/images/botones/boton_ir.gif" value="Asignar" border="0" onclick="this.disabled='true'"/>
	       </p>
		</td>
	</tr>
</table>
</div>
</html:form>
</div>
<!-- frin asignacion -->
    </td>
  </tr>
</table>
		</td>
	</tr>
</table>

<%@ include file = "/includes/footer.jsp"%>
