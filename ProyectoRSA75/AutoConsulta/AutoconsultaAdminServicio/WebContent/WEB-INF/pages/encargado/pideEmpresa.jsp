<%@ include file = "/WEB-INF/includes/headerEnv.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
<%@ include file = "/WEB-INF/includes/header.jsp"%>
<body>
<form name="form1" action="" method="post" >
<input type="hidden" name="accion" value=""/>
<input type="hidden" name="rutEmpresa" value=""/>
<input type="hidden" name="hRutNum" value=""/>
<input type="hidden" name="hRutDig" value=""/>

<div id="container" class="container_12">
  <%@ include file = "/WEB-INF/includes/titulo.jsp"%>
  <%@ include file = "/WEB-INF/includes/opciones.jsp"%>
  <div id="content" class="grid_10"> 
  	<h1>Selecci�n de empresa</h1>
  	<%@ include file = "/WEB-INF/includes/mensaje.jsp"%>
 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableHeader">
	 <tr>
        <th class="label">Ingrese rut de empresa</th>
        <th>&nbsp;</th>
      </tr>
     <tr>
     <tr>
       <td class="label">Rut empresa</td>
       <td><input type="text" name="rut" value="" /> (ejemplo: 111111-1)</td>
     </tr>
     </table>
    <p id="botones">
	<input type="button" name="enviar" value="Enviar" onclick="return validaForm() && doEmpresa()" class="btn"/>
	</p>
  </div>
  <div class="clear"></div>
</div>


</form>
</body>
</HTML>

