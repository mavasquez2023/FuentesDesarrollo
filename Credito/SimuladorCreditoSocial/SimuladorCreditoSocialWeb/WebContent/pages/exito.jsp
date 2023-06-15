<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html:html>
<head>
<title><bean:message key="welcome.title" /></title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>La Araucana - Simulador de crédito social</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, viewport-fit=cover">

<link href="<c:url value="css/fln-icons.css" />" rel="stylesheet">
<link href="<c:url value="css/fln.css" />" rel="stylesheet">
<link href="<c:url value="css/estilos.css" />" rel="stylesheet">

<script src="<c:url value="js/polyfill.min.js" />"></script>
<script src="<c:url value="js/jquery.min.js" />"></script>
<script src="<c:url value="js/automatizar.min.js" />"></script>
<script src="<c:url value="js/fln.js" />"></script>
<script src="<c:url value="js/funciones.js" />"></script>
<script src="<c:url value="js/jquery.tablesorter.js" />"></script>
<script src="<c:url value="js/jquery.tablesorter.pager.js" />"></script>
<script src="<c:url value="js/jquery.tablesorter.widgets.js" />"></script>
<script src="<c:url value="js/utils.js" />"></script>
<script>
	WebFontConfig = {
		google : {
			families : [ 'Open Sans:300,400,600,700' ]
		}
	};
	(function() {
		var wf = document.createElement('script');
		wf.src = ('https:' == document.location.protocol ? 'https' : 'http')
				+ '://ajax.googleapis.com/ajax/libs/webfont/1.5.18/webfont.js';
		wf.type = 'text/javascript';
		wf.async = 'true';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(wf, s);
	})();
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="respuesta">
			<div class="respuesta__item respuesta__item--exito xs-text-center"
				id="respuesta-exito" >
				<div class="respuesta__header">
					<span class="respuesta__icono fln-alerta-exito"></span>
				</div>
				<div class="respuesta__contenido">
					<h3 class="respuesta__titulo">Solicitud enviada exitósamente</h3>
					<p class="respuesta__parrafo">Estimado Cliente su solicitud
						quedó registrada y será próximamente contactado</p>
					<div class="separador"></div>
					<a href="<c:url value="http://www.laaraucana.cl"/>"
						class="btn btn--secundario btn--borde">Aceptar</a>
				</div>
			</div>
			<div class="respuesta__item respuesta__item--error xs-text-center"
				id="respuesta-error" style="display: none">
				<div class="respuesta__header">
					<span class="respuesta__icono fln-alerta-error"></span>
				</div>
				<div class="respuesta__contenido">
					<h3 class="respuesta__titulo">¡Oh no!</h3>
					<p class="respuesta__parrafo">Ocurrió un problema al enviar su
						solicitud, pruebe volver a simular.</p>
					<div class="separador"></div>
					<a href="#" onclick="window.history.back();"
						class="btn btn--secundario btn--borde">Volver a simular</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html:html>