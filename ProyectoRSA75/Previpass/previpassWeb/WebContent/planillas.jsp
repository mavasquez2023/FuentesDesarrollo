<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${! perfil.planillas}"><c:redirect url="menu.jsp"/></c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PreviPass</title>
<link href="css/grid_960.css" rel="stylesheet" type="text/css" />
<link href="css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="css/estilo_formularios.css" rel="stylesheet" type="text/css" />
<link href="css/collapsible_menu.css" rel="stylesheet" type="text/css" />
</head>

<body style="background: url(img/bg.jpg) repeat-x #CCCCCC;">
<div class="container_4" style="margin-top:30px;">
	<div class="grid_4" id="menu">
    	<div id="logo">
         <div class="default-logo"><a href="menu.jsp" class="default-logo"></a></div>
        </div>
        <c:if test="${perfil.pagoAtrasado}"><div class="ppal"><a href="pago_atrasado.jsp"><img src="img/btn_pagoatrasado_off.jpg" alt="Pago Atrasado" width="179" height="58" border="0" onmouseover="src='img/btn_pagoatrasado_on.jpg'" onmouseout="src='img/btn_pagoatrasado_off.jpg'"/></a></div></c:if>
        <div class="ppal"><a href="#"><img src="img/btn_planillas_on.jpg" alt="Planillas y Certificados" width="179" height="58" border="0" /></a></div>
        <c:if test="${perfil.pagoCotizaciones}"><div class="ppal"><a href="cotizaciones.jsp"><img src="img/btn_pagocoti_off.jpg" alt="Pago de Cotizaciones" width="178" height="58" border="0" onmouseover="src='img/btn_pagocoti_on.jpg'" onmouseout="src='img/btn_pagocoti_off.jpg'"/></a></div></c:if>
    	<jsp:include page="links.jsp"/>
    </div>
	<div class="grid_4 fondo">
	  <div id="titulo_ppal">
		<table width="100%"><tr><td width="99%" valign="middle">Planillas y Certificados</td><td nowrap="nowrap" valign="middle"><img src="img/phone.gif" border="0" width="30" height="30"/></td><td nowrap="nowrap" valign="middle">600-4228100</td></tr></table>
      </div>
 <div id="container">
  <iframe name="contenido" class="iframecapana" src="/Publicacion/cotizacionJaas.jsp" height="700" frameborder="0" scrolling="yes"></iframe>
</div>
    </div>
  <jsp:include page="piedepagina.jsp"/>
</div>
</body>
</html>
