<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PreviPass</title>
<link href="css/grid_960.css" rel="stylesheet" type="text/css" />
<link href="css/estilos_interna.css" rel="stylesheet" type="text/css" />

<script language="JavaScript" type="text/javascript"> 

function gestionMP() {
		var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, width=770, height=520, top=50, left=50";
		pagina = "cp_movper.jsp";
		window.open(pagina,"",opciones);
}
</script>
<script type="text/javascript">
	document.oncontextmenu = function(){
	return false;
	}
</script>
</head>

<body style="background: url(img/bg.jpg) repeat-x">
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/publico.jsp">
</form>
<div class="container_4" style="margin-top:30px;">
	<div class="grid_4"><img src="img/header.gif" width="957" height="118" /></div>
	<c:set var="tienePermiso" value="false"/>
	<c:if test="${perfil.administradorCP || perfil.administradorCCAF || perfil.pagoCotizaciones || perfil.planillas || perfil.pagoAtrasado}">
		<c:set var="tienePermiso" value="true" />
	</c:if>
	<c:choose>
	<c:when test="${tienePermiso==true}">
		<div align="right"><c:if test="${perfil.administradorCP}">
			<a href="/AdminCotPrevWEB" target="_top"><img
				src="img/ico_home.gif" border="0" width="11" height="12" hspace="3" /><font
				size="2"> Administraci&oacute;n CP</font></a>&nbsp;
			</c:if> <c:if test="${perfil.administradorCP}">
			<a href="/CierreCP" target="_blank"><img src="img/ico_home.gif"
				border="0" width="11" height="12" hspace="3" /><font size="2">
			CierreCP</font></a>&nbsp;
			</c:if> <c:if test="${perfil.administradorCCAF}">
			<a href="/adminCCAFWeb" target="_top"><img src="img/ico_home.gif"
				border="0" width="11" height="12" hspace="3" /><font size="2">
			Administraci&oacute;n CCAF</font></a>&nbsp;
			</c:if> <img src="img/ico_cerrar.gif" border="0" width="11" height="12"
			hspace="3" /><a
			href="javascript:document.getElementById('logoutForm').submit();">
		<font size="2">Cerrar Sesi&oacute;n</font>&nbsp;</a></div>

		<div class="grid_4 fondo">
		<div class="grid_1" align="right" style="margin-top:30px"><img
			src="img/servicios_linea.jpg" width="223" height="468" /></div>
		<div class="caja-1">
		<div class="menu"><c:choose>
			<c:when test="${perfil.pagoCotizaciones}">
				<a href="cotizaciones.jsp"><img src="img/pago_cot.jpg"
					width="209" height="227" border="0"
					onmouseover="src='img/pago_cot_off.jpg'"
					onmouseout="src='img/pago_cot.jpg'" /></a>
			</c:when>
			<c:otherwise>
				<img src="img/pago_cot_off.jpg" width="209" height="227" border="0" />
			</c:otherwise>
		</c:choose></div>
		<div class="menu"><c:choose>
			<c:when test="${perfil.planillas}">
				<a href="planillas.jsp"><img src="img/planillas.jpg" width="209"
					height="227" border="0" onmouseover="src='img/planillas_off.jpg'"
					onmouseout="src='img/planillas.jpg'" /></a>
			</c:when>
			<c:otherwise>
				<img src="img/planillas_off.jpg" width="209" height="227" border="0" />
			</c:otherwise>
		</c:choose></div>
		<div class="menu"><c:choose>
			<c:when test="${perfil.pagoAtrasado}">
				<a href="pago_atrasado.jsp"><img src="img/pago_atras.jpg"
					width="209" height="227" border="0"
					onmouseover="src='img/pago_atras_off.jpg'"
					onmouseout="src='img/pago_atras.jpg'" /></a>
			</c:when>
			<c:otherwise>
				<img src="img/pago_atras_off.jpg" width="209" height="227"
					border="0" />
			</c:otherwise>
		</c:choose></div>
		<div class="menu"><c:choose>
			<c:when test="${perfil.pagoCotizaciones}">
				<a href="#" onclick="gestionMP();"><img src="img/personal.jpg"
					width="209" height="227" border="0"
					onmouseover="src='img/personal_off.jpg'"
					onmouseout="src='img/personal.jpg'" /></a>
			</c:when>
			<c:otherwise>
				<img src="img/personal_off.jpg" width="209" height="227" border="0" />
			</c:otherwise>
		</c:choose></div>
		<div class="menu"><a
			href="http://www.misplanillas.cl/ea/logon.jsp" target="_blank"><img
			src="img/cta_cte.jpg" width="209" height="227" border="0"
			onmouseover="src='img/cta_cte_off.jpg'"
			onmouseout="src='img/cta_cte.jpg'" /></a></div>
		<div class="menu"><a
			href="http://rasw.laaraucana.cl/sv/router.do?service=EPC"
			target="_blank"><img src="img/nominas_autorizadas.jpg"
			width="209" height="227" border="0"
			onmouseover="src='img/nominas_autorizadas_off.jpg'"
			onmouseout="src='img/nominas_autorizadas.jpg'" /></a></div>
		</div>
		</div>
	</c:when>
	<c:otherwise>
		<div align="center">&nbsp;<br />
		Usted no se encuentra registrado en el sistema, por favor contáctese
		con un Administrador de La Araucana<br />
		<a href="javascript:document.getElementById('logoutForm').submit();"><font
			size="2">Cerrar Sesi&oacute;n</font>&nbsp;</a> <br />
		&nbsp;</div>
	</c:otherwise>
</c:choose>
	<div style="clear:left"></div>
  	<jsp:include page="piedepagina.jsp"/>
</div>
</body>
</html>