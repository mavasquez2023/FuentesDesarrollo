<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>Convenio Sura - La Araucana</title>
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
          wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
              '://ajax.googleapis.com/ajax/libs/webfont/1.5.18/webfont.js';
          wf.type = 'text/javascript';
          wf.async = 'true';
          var s = document.getElementsByTagName('script')[0];
          s.parentNode.insertBefore(wf, s);
      })();
    </script>
  </head>
  <body onload="report()">
    <main>
      <div class="container">
     	<div align="right">
			<a href="<c:url value='/exit.do' />"  style="text-decoration:none"><img alt="Salir" src="img/salir.png"  title="Cerrar Sesi�n" /><span class="pasos__data" style="font-size:1.6rem;color:gray;">&nbsp;Salir</span></a>
		</div>
		<div class="row">
			<div class="col xs12 lg2 text-align-center-xs">
				<svg class="svg svg--secundario" xmlns="http://www.w3.org/2000/svg" width="155" height="140" viewBox="0 0 155.3 140"><path d="M77.7 85.4c23.6 0 42.7-19.1 42.7-42.7S101.2 0 77.7 0 34.9 19.1 34.9 42.7 54.1 85.4 77.7 85.4zM77.7 7.8c19.3 0 34.9 15.6 34.9 34.9S97 77.7 77.7 77.7 42.7 62 42.7 42.7C42.7 23.4 58.4 7.8 77.7 7.8z"/><path d="M135.6 96.8L135.6 96.8c-37.2-15-78.8-15-116 0l0 0C7.7 101.7 0 112 0 123.4V140h155.3v-16.6C155.3 112 147.6 101.7 135.6 96.8zM147.5 132.2H7.8v-8.9c0-8.1 5.8-15.7 14.8-19.3 35.3-14.2 74.8-14.2 110.2 0 9 3.6 14.8 11.2 14.8 19.3V132.2z"/></svg>

				<div class="separador"></div>
			</div>
			<div class="col xs12 lg10">
				<div class="pasos">
					<div class="row justify-center-xs justify-start-sm">
						<div class="col xs12 sm12 md4 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md">
								<div class="pasos__icono">
									<svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60"
										height="60" viewBox="0 0 60 60">
										<rect class="svg__fondo" width="60" height="60" />
										<path
											d="M53.4 12.3H6.6c-2.3 0-4.1 1.8-4.1 4.1v27.2c0 1.1 0.4 2.1 1.2 2.9 0.8 0.8 1.8 1.2 2.9 1.2h46.8c2.3 0 4.1-1.8 4.1-4.1V16.4C57.5 14.2 55.7 12.3 53.4 12.3zM6.6 14.7h46.8c0.9 0 1.7 0.8 1.7 1.7v27.2c0 0.9-0.8 1.7-1.7 1.7H6.6c-0.9 0-1.7-0.8-1.7-1.7l0-27.2C4.9 15.5 5.6 14.7 6.6 14.7z" />
										<path
											d="M29.7 26h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 25.9 29.4 26 29.7 26z" />
										<path
											d="M29.7 31.2h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 31 29.4 31.2 29.7 31.2z" />
										<path
											d="M29.7 36.3h8.9c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2h-8.9c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 36.2 29.4 36.3 29.7 36.3z" />
										<path
											d="M16.3 31.3c2.6 0 4.7-2.1 4.7-4.7 0-0.2 0-0.3 0-0.5l0 0c0-0.2-0.1-0.5-0.1-0.7 -0.1-0.2-0.1-0.4-0.2-0.7 -0.7-1.7-2.4-2.9-4.3-2.9 -1.9 0-3.6 1.1-4.3 2.9 -0.1 0.2-0.2 0.4-0.2 0.7 -0.1 0.2-0.1 0.5-0.1 0.7 0 0.2 0 0.3 0 0.5C11.6 29.2 13.7 31.3 16.3 31.3zM14.4 24.3c0.1-0.1 0.3-0.2 0.5-0.3 0.1 0 0.2-0.1 0.3-0.1 0.2-0.1 0.4-0.1 0.5-0.2 0.2 0 0.4-0.1 0.6-0.1 1.6 0 2.9 1.3 2.9 2.9 0 0.2 0 0.4-0.1 0.6 0 0.2-0.1 0.4-0.2 0.5 0 0.1-0.1 0.2-0.1 0.3h0c-0.1 0.2-0.2 0.3-0.3 0.5 -0.6 0.7-1.4 1.1-2.3 1.1 -0.2 0-0.4 0-0.6-0.1 -0.2 0-0.4-0.1-0.5-0.2 -0.1 0-0.2-0.1-0.3-0.1 -0.2-0.1-0.3-0.2-0.5-0.3 -0.1-0.1-0.3-0.3-0.4-0.4 -0.1-0.1-0.2-0.3-0.3-0.5 0-0.1-0.1-0.2-0.1-0.3 -0.1-0.2-0.1-0.4-0.2-0.5 0-0.2-0.1-0.4-0.1-0.6C13.4 25.7 13.7 24.9 14.4 24.3z" />
										<path
											d="M24.4 35.6c0-0.6-0.2-1.1-0.5-1.6 -0.1-0.2-0.3-0.4-0.5-0.6 -0.1-0.1-0.1-0.1-0.2-0.2 -0.2-0.2-0.4-0.3-0.6-0.4 -0.2-0.1-0.3-0.2-0.5-0.2 -3.8-1.5-8-1.5-11.8 0 -0.2 0.1-0.3 0.1-0.5 0.2 -0.2 0.1-0.4 0.3-0.6 0.4 -0.1 0.1-0.1 0.1-0.2 0.2 -0.2 0.2-0.3 0.4-0.5 0.6 -0.3 0.5-0.5 1.1-0.5 1.6v2.1h16.2V35.6zM11.1 34.2c0.6-0.2 1.3-0.5 1.9-0.6 0.6-0.2 1.3-0.3 2-0.3 0.2 0 0.4 0 0.7 0 1.1-0.1 2.2 0 3.3 0.2 0.9 0.2 1.7 0.4 2.6 0.8 0.1 0 0.2 0.1 0.3 0.1 0.1 0 0.2 0.1 0.2 0.1 0.1 0.1 0.1 0.1 0.2 0.2 0.1 0.1 0.3 0.3 0.3 0.5 0 0 0 0.1 0 0.1 0 0.1 0 0.1 0.1 0.2 0 0.1 0 0.1 0 0.2V36H9.9v-0.4c0-0.1 0-0.1 0-0.2 0-0.1 0-0.1 0.1-0.2 0 0 0-0.1 0-0.1 0.1-0.2 0.2-0.3 0.3-0.5 0.1-0.1 0.1-0.1 0.2-0.2 0.1-0.1 0.1-0.1 0.2-0.1C10.9 34.3 11 34.3 11.1 34.2z" /></svg>
								</div>
								<div class="pasos__data">
									<span>Convenio</span>SURA
								</div>
							</div>
						</div>
						<
						
					</div>
				</div>
				<div class="separador"></div>
          </div>
          <div class="col xs12 md8 lg10">
            <div class="respuesta text-align-center-xs">
              <h4 class="respuesta__titulo color--secundario">Error en servicio!</h4>
              <div class="separador"></div>
              <p class="respuesta__texto"><span>Problemas en conexi�n con Sura: ${msgError}</span></p>
            </div>
            <div class="btn__grupo text-align-center-xs">
            <c:if test="${mandato==false }">
            	<a class="btn btn--primario" href="<c:url value='/mandato.do' />" onClick="javascript:fln.preloader(1);">Solicitar transferencia electr�nica</a>&nbsp;&nbsp;
           	</c:if>
            <a class="btn btn--primario" href="<c:url value='/exit.do' />" onClick="javascript:fln.preloader(1);">Ir al home</a>
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