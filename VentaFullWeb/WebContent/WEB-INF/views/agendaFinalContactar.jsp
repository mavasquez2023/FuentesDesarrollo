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
              <div class="respuesta-positiva">
				<svg width="100" height="100" viewBox="0 0 80 80" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M56 46C55.7156 50 52.16 58 40.2133 58C28.2667 58 24.4267 50 24 46" stroke="#1F7ABC" stroke-width="3" stroke-linecap="round"/>
                  <circle cx="40" cy="40" r="38.5" stroke="#1F7ABC" stroke-width="3"/>
                  <circle cx="28" cy="27.9998" r="6" fill="#1F7ABC"/>
                  <circle cx="49.9999" cy="27.9998" r="6" fill="#1F7ABC"/>
                </svg>                 
				<h3>Mantente atento a nuestro llamado, nos contactaremos pronto al n&uacute;mero +${telefono}</h3>				
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
    <script>
	    function resizeIframe(e){
	        //console.log('resize');
	        var medidafija = e;
	        var alto = 0;
	        setTimeout(function(){
	            if(medidafija > 0){
	                alto = medidafija;
	                window.scrollTo(0, 0);
	            }else{
	                alto = $('body').innerHeight();
	            }
	            let message = { height: alto, width: '100%' };  
	        
	            // window.top refers to parent window
	            window.top.postMessage(message, "*");
	     
	        }, 200)
	    } resizeIframe(); 
    </script>
  </body>
</html>