<%@ include file = "/WEB-INF/includes/headerEnv.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
<%@ include file = "/WEB-INF/includes/header.jsp"%>
<body>
<form name="form1" action="" method="post">
<input type="hidden" name="accion" value=""/>
<input type="hidden" name="rutEmpresa" value=""/>
<input type="hidden" name="rut" value=""/>

<div id="container" class="container_12">
  <%@ include file = "/WEB-INF/includes/titulo.jsp"%>
  <%@ include file = "/WEB-INF/includes/opciones.jsp"%>
  <div id="content" class="grid_10"> 
	<h1><bean:write name="editar.titulo"/> servicios del encargado</h1>
	<%@ include file = "/WEB-INF/includes/mensaje.jsp"%>
	
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableHeader">
	<tr>
	  <th colspan="2">Datos encargado de servicio</th>
	</tr>
	<tr>
	  <td class="label">Nombre Empresa</td>
	  <td><strong><bean:write name="empresa"  property="nombre"/></strong></td>
	</tr>
	<tr>
	  <td class="label">Nombre Encargado</td>
	  <td><strong><bean:write name="encargado" property="nombre"/> <bean:write name="encargado" property="apellidoPaterno"/> <bean:write name="encargado" property="apellidoMaterno"/></strong>
	  </td>
	</tr>
	</table>
	
	<br/><br/>
<logic:notEmpty name="lstServicios">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableLista">
  	<tr>
	  <th colspan="2">Lista de servicios</th>
	</tr>
  
  <logic:iterate id="srv" name="lstServicios">
      <tr>
         <logic:equal name="srv" property="habilitado" value="true">
           <td><input checked="true" type="checkbox" name="accesos" value="<bean:write name="srv" property="codigo"/>"/></td>
        </logic:equal>
        <logic:notEqual name="srv" property="habilitado" value="true">
          <td><input type="checkbox" name="accesos" value="<bean:write name="srv" property="codigo"/>"/></td>
        </logic:notEqual>
        <td>&nbsp;<bean:write name="srv" property="descripcion"/></td>
      </tr>
  </logic:iterate>
  </table>
</logic:notEmpty>
    <p id="botones">
	  <input type="button" name="enviar" value="Volver" onclick="return doVuelveLista()" class="btn"/>&nbsp; &nbsp;
	  <input type="button" name="enviar" value="Aceptar" onclick="return doGrabar()" class="btn"/>&nbsp; &nbsp;
	</p>
  </div>
</div>

</form>
</body>
</HTML>
