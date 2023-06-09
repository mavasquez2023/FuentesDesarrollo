<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>Crédito Digital - La Araucana</title>
    <link rel="icon" href="/favicon.ico">
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
        	<!--  
	        <div id="mySidenav" class="sidenav">	        	      	
				<a href="#" id="contact" onClick="paso2Contacto(); return false;">
					<div class="row justify-content-start">
						<div class="col-3" style="margin-top:8px; margin-left:5px;">
							<i class="fa-solid fa-phone-volume fa-beat-fade fa-2xl"></i>					
						</div>
						<div class="col-7">
							¿Necesitas asistencia telef&oacute;nica?						
						</div>
					</div>
				</a>	        	
			</div>
			-->
          <div class="row">
            <div class="col-xs-12 col-lg-12">
              <div class="respuesta-negativa">
				<svg width="80" height="80" viewBox="0 0 80 80" fill="none" xmlns="http://www.w3.org/2000/svg">
					<path d="M24 58C24.2844 54 27.84 46 39.7867 46C51.7333 46 55.5733 54 56 58" stroke="#1F7ABC" stroke-width="3" stroke-linecap="round"/>
					<circle cx="40" cy="40" r="38.5" stroke="#1F7ABC" stroke-width="3"/>
					<circle cx="28" cy="27.9998" r="6" fill="#1F7ABC"/>
					<circle cx="49.9999" cy="27.9998" r="6" fill="#1F7ABC"/>
				</svg>
				<h3>De acuerdo a la pol&iacute;tica de la Araucana, no es posible continuar con el cr&eacute;dito.</h3>
				<div class="separador"></div>
				<div class="botonera">
					<a href="<c:url value='<%=cl.laaraucana.ventafullweb.util.Configuraciones.getConfig("url.salir.portal")%>' />" class="boton boton--borde terciario">Ir al home</a>
				</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <div class="modal-generico" id="procesando-credito" style="display:none;">
			<div class="modal-generico__interior">
				<svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
					<path d="M20.0001 11.8855C15.5186 11.8855 11.8857 15.5185 11.8857 19.9999C11.8857 24.4814 15.5186 28.1143 20.0001 28.1143C24.4815 28.1143 28.1145 24.4814 28.1145 19.9999C28.1145 15.5185 24.4815 11.8855 20.0001 11.8855ZM14.3857 19.9999C14.3857 16.8992 16.8993 14.3855 20.0001 14.3855C23.1008 14.3855 25.6145 16.8992 25.6145 19.9999C25.6145 23.1007 23.1008 25.6143 20.0001 25.6143C16.8993 25.6143 14.3857 23.1007 14.3857 19.9999Z" fill="#1F7ABC"/>
					<path d="M24.4912 3.35844C23.1713 -1.11948 16.8287 -1.11948 15.5088 3.35844L15.2742 4.15448C14.8811 5.48798 13.358 6.11888 12.1372 5.45388L11.4084 5.0569C7.30874 2.82381 2.82382 7.30875 5.0569 11.4084L5.45388 12.1372C6.11888 13.358 5.48798 14.8811 4.15448 15.2742L3.35844 15.5088C-1.11948 16.8287 -1.11948 23.1713 3.35844 24.4912L4.15448 24.7258C5.48798 25.1189 6.11888 26.642 5.45387 27.8628L5.0569 28.5916C2.82382 32.6912 7.30873 37.1762 11.4084 34.9431L12.1372 34.5461C13.358 33.8811 14.8811 34.512 15.2742 35.8455L15.5088 36.6416C16.8287 41.1195 23.1713 41.1195 24.4912 36.6416L24.7258 35.8455C25.1189 34.512 26.642 33.8811 27.8628 34.5461L28.5916 34.9431C32.6913 37.1762 37.1762 32.6913 34.9431 28.5916L34.5461 27.8628C33.8811 26.642 34.512 25.1189 35.8455 24.7258L36.6416 24.4912C41.1195 23.1713 41.1195 16.8287 36.6416 15.5088L35.8455 15.2742C34.512 14.8811 33.8811 13.358 34.5461 12.1372L34.9431 11.4084C37.1762 7.30873 32.6912 2.82382 28.5916 5.05691L27.8628 5.45388C26.642 6.11888 25.1189 5.48798 24.7258 4.15448L24.4912 3.35844ZM17.9068 4.06525C18.522 1.97825 21.478 1.97825 22.0932 4.06525L22.3278 4.86128C23.1711 7.72248 26.4392 9.07616 29.0587 7.64931L29.7875 7.25234C31.6982 6.21157 33.7884 8.30184 32.7477 10.2125L32.3507 10.9413C30.9238 13.5608 32.2775 16.8289 35.1387 17.6722L35.9348 17.9068C38.0217 18.522 38.0217 21.478 35.9348 22.0932L35.1387 22.3278C32.2775 23.1711 30.9238 26.4392 32.3507 29.0587L32.7477 29.7875C33.7884 31.6982 31.6982 33.7884 29.7875 32.7477L29.0587 32.3507C26.4392 30.9238 23.1711 32.2775 22.3278 35.1387L22.0932 35.9348C21.478 38.0217 18.522 38.0217 17.9068 35.9348L17.6722 35.1387C16.8289 32.2775 13.5608 30.9238 10.9413 32.3507L10.2125 32.7477C8.30184 33.7884 6.21157 31.6982 7.25234 29.7875L7.64931 29.0587C9.07615 26.4392 7.72249 23.1711 4.86129 22.3278L4.06525 22.0932C1.97825 21.478 1.97825 18.522 4.06524 17.9068L4.86129 17.6722C7.72249 16.8289 9.07616 13.5608 7.64931 10.9413L7.25234 10.2125C6.21158 8.30184 8.30184 6.21158 10.2125 7.25234L10.9413 7.64931C13.5608 9.07616 16.8289 7.72249 17.6722 4.86129L17.9068 4.06525Z" fill="#1F7ABC"/>
				</svg>
				<h2>Nos encontramos evaluando tu Crédito</h2>
				<p>Espere un momento por favor</p>
			</div>
		</div>
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