<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>MUVU - La Araucana</title>
    <link rel="stylesheet" href="assets/css/fln.css"/>
    <link rel="stylesheet" href="fonts/fln-icons.css"/>
    <link rel="stylesheet" href="assets/css/estilos.css"/>
    <script>
      WebFontConfig = {
          google: {
              families: ['Open Sans:300,400,500,700']
          }
      };
      (function () {
          var wf = document.createElement('script');
          wf.type = 'text/javascript';
          wf.async = 'true';
          var s = document.getElementsByTagName('script')[0];
          s.parentNode.insertBefore(wf, s);
      })();
    </script>
  </head>
  <body >
    <main>
      <div class="container">
		<div class="row">
			<jsp:include page="banner.jsp" />
          <div class="col xs12 md8 lg10">
            <div class="respuesta text-align-center-xs">
              <p class="respuesta__titulo color--secundario">UPS!, tu incorporación al programa no está completa</p>
              <br>
               <p class="respuesta__subtitulo color--secundario"><font  style="font-size: medium;"></font>${data.causa } </p>
              <div class="separador"></div>
            </div>
            <div class="btn__grupo text-align-center-xs">

             <a class="btn btn--primario" href="<c:url value='https://muvu.cl' />" onClick="javascript:fln.preloader(1);">Ir a MUVU</a>
            </div>
          </div>
        </div>
      </div>
    </main>
     <div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
    <script src="assets/js/polyfill.js"></script>
    <script src="assets/js/jquery-3.3.1.js"></script>
    <script src="assets/js/jquery-ui.js"></script>
    <script src="assets/js/automatizar.js"></script>
    <script src="assets/js/fln.js"></script>
    <script src="assets/js/funciones.js"></script>
  </body>
</html>