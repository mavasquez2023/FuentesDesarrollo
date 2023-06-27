<%@ include file = "/WEB-INF/includes/headerEnv.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
<%@ include file = "/WEB-INF/includes/header.jsp"%>
<body>
<form name="form1" action="" method="post">
<input type="hidden" name="accion" value=""/>
<input type="hidden" name="rutEmpresa" value=""/>
<input type="hidden" name="rutEncargado" value=""/>
<input type="hidden" name="rut" value=""/>

<div id="container" class="container_12">
  <%@ include file = "/WEB-INF/includes/titulo.jsp"%>
  <%@ include file = "/WEB-INF/includes/opciones.jsp"%>
  <div id="content" class="grid_10"> 
    <h1>Lista de encargados</h1>
    <%@ include file = "/WEB-INF/includes/mensaje.jsp"%>
    
 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableHeader">
	 <tr>
        <th class="label">Datos de empresa</th>
        <th>&nbsp;</th>
      </tr>
     <tr>
     <tr>
       <td class="label">Nombre empresa</td>
       <td><strong><bean:write name="empresa"  property="nombre"/></strong></td>
     </tr>
     </table>
	<br/>

<logic:notEmpty name="usuarios">
	 <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableLista">
	 <tr>
        <th>Encargados de servicios</th>
        <th>&nbsp;</th>
      </tr>
	<logic:iterate id="register" name="usuarios">
		<tr>
			<td><bean:write name="register" property="nombre"/> <bean:write name="register" property="apellidoPaterno"/> <bean:write name="register" property="apellidoMaterno"/>&nbsp;</td>
			<td>
  			   <a href="javascript:doModificar(<bean:write name="register" property="rutEncargado"/>)">Modificar</a> &nbsp; &nbsp;
			</td>
		</tr>
	</logic:iterate>
	</table>
</logic:notEmpty>
<logic:empty name="usuarios">
No hay encargados asociados a la empresa.<br/>
</logic:empty>
	<p id="botones">
	<input type="button" name="enviar" value="Volver" onclick="return doEmpresa()"/>&nbsp; &nbsp;
<%
//	<input type="button" name="enviar" value="Agregar encargado" onclick="return doNuevo()"/>&nbsp; &nbsp;
%>
	</p>
  </div>
  <div class="clear"></div>
</div>

</form>
</body>
</HTML>
