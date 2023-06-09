<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>Cr&eacute;dito Digital - La Araucana</title>
    <link rel="icon" href="./assets/favicon.ico">
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="./assets/css/jquery-ui.css"/>
		<link rel="stylesheet" href="./assets/css/base.css"/>
    <link rel="stylesheet" href="./assets/css/estilos.css"/>
    <script>
      WebFontConfig = {
          google: {
              families: ['Open Sans:300,400,500,700']
          }
      };
      (function () {
          var wf = document.createElement('script');
          wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
              '://ajax.googleapis.com/ajax/libs/webfont/1.5.18/webfont.js';
          wf.type = 'text/javascript';
          wf.async = 'true';
          var s = document.getElementsByTagName('script')[0];
          s.parentNode.insertBefore(wf, s);
      })();
    </script>
  </head>
  <body>
     <main>
      <div class="container container-flujo">
        <div class="marco">
          <div class="row">
            <div class="col-xs-12 col-lg-12">
              <div class="separador--big"></div>
              <div class="respuesta-positiva">
				<svg width="100" height="100" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
					<circle cx="10" cy="10" r="9.5" fill="white" stroke="#1F7ABC"/>
					<path d="M10.4141 7.20312V15.7656H9.11719V7.20312H10.4141ZM9.78125 4C9.99479 4 10.1771 4.07031 10.3281 4.21094C10.4844 4.34635 10.5625 4.5599 10.5625 4.85156C10.5625 5.13802 10.4844 5.35156 10.3281 5.49219C10.1771 5.63281 9.99479 5.70312 9.78125 5.70312C9.55729 5.70312 9.36979 5.63281 9.21875 5.49219C9.07292 5.35156 9 5.13802 9 4.85156C9 4.5599 9.07292 4.34635 9.21875 4.21094C9.36979 4.07031 9.55729 4 9.78125 4Z" fill="#1F7ABC"/>
				</svg>
				<h3>Servicio no disponible, intente nuevamente.</h3>
				<div class="separador"></div>
				<div class="botonera">
					<a href="<c:url value='<%=cl.laaraucana.ventafullweb.util.Configuraciones.getConfig("url.salir.portal")%>' />" target="_top" class="boton boton--borde terciario">Volver al inicio</a>
				</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <script src="./assets/js/polyfill.js"></script>
    <script src="./assets/js/jquery-3.6.0.js"></script>
    <script src="./assets/js/jquery-ui.js"></script>
    <script src="./assets/js/automatizar.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/popper.min.js"></script>
    <script src="./assets/js/tippy-bundle.umd.js"></script>
    <script src="./assets/js/fln.js"></script>
    <script src="./assets/js/funciones.js"></script>
  </body>
</html>