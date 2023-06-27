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
  	<h1>Selección de empresa</h1>
  	<%@ include file = "/WEB-INF/includes/mensaje.jsp"%>
 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableHeader">
	 <tr>
        <th class="label">Error interno </th>
        <th>Favor de consultar con administracion de sistemas</th>
      </tr>
     <tr>
     </table>
    <p id="botones">
	<input type="button" name="enviar" value="Enviar" onclick="history.back()" class="btn"/>
	</p>
  </div>
  <div class="clear"></div>
</div>


</form>
</body>
</HTML>

