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
    <style>
    .changecolor:hover {background-color: #00ccFF}
    </style>
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
              <div class="separador"></div>
              <div class="respuesta-positiva">
				<svg width="100" height="100" viewBox="0 0 80 80" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M56 46C55.7156 50 52.16 58 40.2133 58C28.2667 58 24.4267 50 24 46" stroke="#1F7ABC" stroke-width="3" stroke-linecap="round"/>
                  <circle cx="40" cy="40" r="38.5" stroke="#1F7ABC" stroke-width="3"/>
                  <circle cx="28" cy="27.9998" r="6" fill="#1F7ABC"/>
                  <circle cx="49.9999" cy="27.9998" r="6" fill="#1F7ABC"/>
                </svg>
				<h3>${mensaje}</h3>
				<!--  <p>+${telefono}</p> -->
				<div class="botonera mt-2">
					<a href="#" class="boton boton--terciario" onclick="paso2Contacto();" ${contactar}>Contactar</a>
				</div>
				<h2>${fecha}</h2>
				<table>
					<tr>
						<c:if test='${not empty paginacion}'>
						<td><h3><a href="paginacion.do?ultimaFecha="><i class="bi bi-chevron-bar-left"></i></a></h3></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						</c:if>
						<c:forEach items="${agenda}" var="agend" begin = "0" end = "4" varStatus="id">
							<td class="changecolor" id="dia${id.count}" >${agend.nombreDia} <h3>${agend.numDia}</h3></td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</c:forEach>
						<td><h3><a href="paginacion.do?ultimaFecha=${ultimaFecha}"><i class="bi bi-chevron-right"></i></a></h3></td>
					</tr>
				</table>
				<form id="form-agenda" action="agendar.do">
					<div class="botonera text-center">		
						<br><br>
						<input type="radio" id="horario" name="horario" value="AM">
						<label for="html">AM (9:00 - 12:00)</label><br><br>
						<input type="radio" id="horario" name="horario" value="PM">
						<label for="css">PM (13:00 - 18:00)</label><br>
					</div>
					<div class="col-xs-12">
						<div class="alerta alerta--error" id="errores_form-agenda" style="display:none;"></div>
					</div>
					<div class="separador"></div>
					<!--  
					<div class="botonera">
						<a href="#" class="boton boton--borde terciario" onclick="validarAgenda();">Agendar</a>
					</div>
					-->
					<div class="btn__grupo btn__grupo--der btn__grupo--flex">
                        <a href="#" class="boton boton--terciario" onclick="validarAgenda();">Agendar</a>
                        
                       	<a href="<c:url value='<%=cl.laaraucana.ventafullweb.util.Configuraciones.getConfig("url.salir.portal")%>' />" target="_top" class="boton boton--borde terciario">Volver al inicio</a>
                        
                 	</div>
					<input name="diaAgenda" id="diaAgenda" type="hidden" value="" />
					<input name="primerDiaAgenda" id="primerDiaAgenda" type="hidden" value="${primeraFecha}" />
					<input name="periodoAgenda" id="periodoAgenda" type="hidden" value="" />
				</form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <!-- Preloader -->
	<div class="modal-generico" id="procesando-credito" style="display: none;">
		<div class="modal-generico__interior preload">
			<svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
				<path d="M20.0001 11.8855C15.5186 11.8855 11.8857 15.5185 11.8857 19.9999C11.8857 24.4814 15.5186 28.1143 20.0001 28.1143C24.4815 28.1143 28.1145 24.4814 28.1145 19.9999C28.1145 15.5185 24.4815 11.8855 20.0001 11.8855ZM14.3857 19.9999C14.3857 16.8992 16.8993 14.3855 20.0001 14.3855C23.1008 14.3855 25.6145 16.8992 25.6145 19.9999C25.6145 23.1007 23.1008 25.6143 20.0001 25.6143C16.8993 25.6143 14.3857 23.1007 14.3857 19.9999Z" fill="#1F7ABC"/>
				<path d="M24.4912 3.35844C23.1713 -1.11948 16.8287 -1.11948 15.5088 3.35844L15.2742 4.15448C14.8811 5.48798 13.358 6.11888 12.1372 5.45388L11.4084 5.0569C7.30874 2.82381 2.82382 7.30875 5.0569 11.4084L5.45388 12.1372C6.11888 13.358 5.48798 14.8811 4.15448 15.2742L3.35844 15.5088C-1.11948 16.8287 -1.11948 23.1713 3.35844 24.4912L4.15448 24.7258C5.48798 25.1189 6.11888 26.642 5.45387 27.8628L5.0569 28.5916C2.82382 32.6912 7.30873 37.1762 11.4084 34.9431L12.1372 34.5461C13.358 33.8811 14.8811 34.512 15.2742 35.8455L15.5088 36.6416C16.8287 41.1195 23.1713 41.1195 24.4912 36.6416L24.7258 35.8455C25.1189 34.512 26.642 33.8811 27.8628 34.5461L28.5916 34.9431C32.6913 37.1762 37.1762 32.6913 34.9431 28.5916L34.5461 27.8628C33.8811 26.642 34.512 25.1189 35.8455 24.7258L36.6416 24.4912C41.1195 23.1713 41.1195 16.8287 36.6416 15.5088L35.8455 15.2742C34.512 14.8811 33.8811 13.358 34.5461 12.1372L34.9431 11.4084C37.1762 7.30873 32.6912 2.82382 28.5916 5.05691L27.8628 5.45388C26.642 6.11888 25.1189 5.48798 24.7258 4.15448L24.4912 3.35844ZM17.9068 4.06525C18.522 1.97825 21.478 1.97825 22.0932 4.06525L22.3278 4.86128C23.1711 7.72248 26.4392 9.07616 29.0587 7.64931L29.7875 7.25234C31.6982 6.21157 33.7884 8.30184 32.7477 10.2125L32.3507 10.9413C30.9238 13.5608 32.2775 16.8289 35.1387 17.6722L35.9348 17.9068C38.0217 18.522 38.0217 21.478 35.9348 22.0932L35.1387 22.3278C32.2775 23.1711 30.9238 26.4392 32.3507 29.0587L32.7477 29.7875C33.7884 31.6982 31.6982 33.7884 29.7875 32.7477L29.0587 32.3507C26.4392 30.9238 23.1711 32.2775 22.3278 35.1387L22.0932 35.9348C21.478 38.0217 18.522 38.0217 17.9068 35.9348L17.6722 35.1387C16.8289 32.2775 13.5608 30.9238 10.9413 32.3507L10.2125 32.7477C8.30184 33.7884 6.21157 31.6982 7.25234 29.7875L7.64931 29.0587C9.07615 26.4392 7.72249 23.1711 4.86129 22.3278L4.06525 22.0932C1.97825 21.478 1.97825 18.522 4.06524 17.9068L4.86129 17.6722C7.72249 16.8289 9.07616 13.5608 7.64931 10.9413L7.25234 10.2125C6.21158 8.30184 8.30184 6.21158 10.2125 7.25234L10.9413 7.64931C13.5608 9.07616 16.8289 7.72249 17.6722 4.86129L17.9068 4.06525Z" fill="#1F7ABC"/>
			</svg>
			<h2>Estamos configurando la agenda</h2>
			<p>Espera un momento por favor</p>
		</div>
	</div>
	
	<!-- Preloader buscando ejecutivo -->
		<div class="modal-generico" id="buscando-ejecutivo" style="display: none;">
			<div class="modal-generico__interior preload">
				<svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
					<path d="M20.0001 11.8855C15.5186 11.8855 11.8857 15.5185 11.8857 19.9999C11.8857 24.4814 15.5186 28.1143 20.0001 28.1143C24.4815 28.1143 28.1145 24.4814 28.1145 19.9999C28.1145 15.5185 24.4815 11.8855 20.0001 11.8855ZM14.3857 19.9999C14.3857 16.8992 16.8993 14.3855 20.0001 14.3855C23.1008 14.3855 25.6145 16.8992 25.6145 19.9999C25.6145 23.1007 23.1008 25.6143 20.0001 25.6143C16.8993 25.6143 14.3857 23.1007 14.3857 19.9999Z" fill="#1F7ABC"/>
					<path d="M24.4912 3.35844C23.1713 -1.11948 16.8287 -1.11948 15.5088 3.35844L15.2742 4.15448C14.8811 5.48798 13.358 6.11888 12.1372 5.45388L11.4084 5.0569C7.30874 2.82381 2.82382 7.30875 5.0569 11.4084L5.45388 12.1372C6.11888 13.358 5.48798 14.8811 4.15448 15.2742L3.35844 15.5088C-1.11948 16.8287 -1.11948 23.1713 3.35844 24.4912L4.15448 24.7258C5.48798 25.1189 6.11888 26.642 5.45387 27.8628L5.0569 28.5916C2.82382 32.6912 7.30873 37.1762 11.4084 34.9431L12.1372 34.5461C13.358 33.8811 14.8811 34.512 15.2742 35.8455L15.5088 36.6416C16.8287 41.1195 23.1713 41.1195 24.4912 36.6416L24.7258 35.8455C25.1189 34.512 26.642 33.8811 27.8628 34.5461L28.5916 34.9431C32.6913 37.1762 37.1762 32.6913 34.9431 28.5916L34.5461 27.8628C33.8811 26.642 34.512 25.1189 35.8455 24.7258L36.6416 24.4912C41.1195 23.1713 41.1195 16.8287 36.6416 15.5088L35.8455 15.2742C34.512 14.8811 33.8811 13.358 34.5461 12.1372L34.9431 11.4084C37.1762 7.30873 32.6912 2.82382 28.5916 5.05691L27.8628 5.45388C26.642 6.11888 25.1189 5.48798 24.7258 4.15448L24.4912 3.35844ZM17.9068 4.06525C18.522 1.97825 21.478 1.97825 22.0932 4.06525L22.3278 4.86128C23.1711 7.72248 26.4392 9.07616 29.0587 7.64931L29.7875 7.25234C31.6982 6.21157 33.7884 8.30184 32.7477 10.2125L32.3507 10.9413C30.9238 13.5608 32.2775 16.8289 35.1387 17.6722L35.9348 17.9068C38.0217 18.522 38.0217 21.478 35.9348 22.0932L35.1387 22.3278C32.2775 23.1711 30.9238 26.4392 32.3507 29.0587L32.7477 29.7875C33.7884 31.6982 31.6982 33.7884 29.7875 32.7477L29.0587 32.3507C26.4392 30.9238 23.1711 32.2775 22.3278 35.1387L22.0932 35.9348C21.478 38.0217 18.522 38.0217 17.9068 35.9348L17.6722 35.1387C16.8289 32.2775 13.5608 30.9238 10.9413 32.3507L10.2125 32.7477C8.30184 33.7884 6.21157 31.6982 7.25234 29.7875L7.64931 29.0587C9.07615 26.4392 7.72249 23.1711 4.86129 22.3278L4.06525 22.0932C1.97825 21.478 1.97825 18.522 4.06524 17.9068L4.86129 17.6722C7.72249 16.8289 9.07616 13.5608 7.64931 10.9413L7.25234 10.2125C6.21158 8.30184 8.30184 6.21158 10.2125 7.25234L10.9413 7.64931C13.5608 9.07616 16.8289 7.72249 17.6722 4.86129L17.9068 4.06525Z" fill="#1F7ABC"/>
				</svg>
				<h2>Estamos buscando un ejecutivo</h2>
				<p>Espera un momento por favor</p>
			</div>
		</div>
	
	<!-- Modal contactar -->
	<div class="modal-generico" id="modal-contactanos" style="display: none;">
		<div class="modal-generico__interior">
			<svg width="100" height="100" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
				<circle cx="10" cy="10" r="9.5" fill="white" stroke="#1F7ABC"/>
				<path d="M10.4141 7.20312V15.7656H9.11719V7.20312H10.4141ZM9.78125 4C9.99479 4 10.1771 4.07031 10.3281 4.21094C10.4844 4.34635 10.5625 4.5599 10.5625 4.85156C10.5625 5.13802 10.4844 5.35156 10.3281 5.49219C10.1771 5.63281 9.99479 5.70312 9.78125 5.70312C9.55729 5.70312 9.36979 5.63281 9.21875 5.49219C9.07292 5.35156 9 5.13802 9 4.85156C9 4.5599 9.07292 4.34635 9.21875 4.21094C9.36979 4.07031 9.55729 4 9.78125 4Z" fill="#1F7ABC"/>
			</svg>
			<h2>Muchas gracias por contactarnos</h2>
			<p id="texto1"></p>
			<p id="texto2"></p>
			
			<div class="separador"></div>
			<div class="botonera">
				<a href="<c:url value='<%=cl.laaraucana.ventafullweb.util.Configuraciones.getConfig("url.salir.portal")%>' />" target="_top" class="boton boton--borde terciario">Volver al inicio</a>
			</div>
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
	$(document).ready(function() {
			$('.changecolor').click(function(){
				$('#dia1').css("background-color", "white");
				$('#dia2').css("background-color", "white");
				$('#dia3').css("background-color", "white");
				$('#dia4').css("background-color", "white");
				$('#dia5').css("background-color", "white");
				$(this).css("background-color", "#cccccc");
    		});
			$('#dia1').click(function(){
				$('#diaAgenda').val("dia1");
    		});
			$('#dia2').click(function(){
				$('#diaAgenda').val("dia2");
    		});
			$('#dia3').click(function(){
				$('#diaAgenda').val("dia3");
    		});
			$('#dia4').click(function(){
				$('#diaAgenda').val("dia4");
    		});
			$('#dia5').click(function(){
				$('#diaAgenda').val("dia5");
    		});
			/*
			$('.changecolor').hover(	
			function(){
				$(this).css("background-color", "#00ccFF");
			}, function(){
				$(this).css("background-color", "white");
			});
    		*/
		});
	</script>
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