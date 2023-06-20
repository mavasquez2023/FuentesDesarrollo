
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>Reporte SIL La Araucana</title>
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

      function paso2(){
      	 window.location="<%=request.getContextPath()%>/seleccion.jsp";
      }
	</script>
  </head>
  <body>
    <main>
      <div class="container">
        <div class="separador"></div>
        <div class="row">
          <div class="col xs12 lg2 text-align-center-xs">
          <svg width="155" height="200" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg">
 <!-- Created with SVG-edit - http://svg-edit.googlecode.com/ -->
 <g>
  <title>Reporte SIL</title>
  <path id="svg_14" d="m6.81416,-268.18584l3.185841,0l0,3.185841l-3.185841,0l0,-3.185841zm0.371681,-2.81416l0,2.442478l2.442478,0l0,-2.442478l-2.442478,0z" stroke-linecap="null" stroke-linejoin="null" stroke-dasharray="null" stroke-width="2" stroke="#000000" fill="#0000ff"/>
  <g id="svg_21">
   <path fill-opacity="0" id="svg_16" d="m28.414989,52.768409l21.763424,-21.763424l87.23658,0l0,109.000004l-109.000004,0l0,-87.23658z" stroke-linecap="null" stroke-linejoin="null" stroke-dasharray="null" stroke-width="6" stroke="#003f7f" fill="#000000"/>
   <line stroke="#003f7f" id="svg_17" y2="60" x2="120" y1="60" x1="56" stroke-linecap="null" stroke-linejoin="null" stroke-dasharray="null" stroke-width="5" fill="none"/>
   <line id="svg_18" stroke="#003f7f" y2="80.5" x2="121" y1="80.5" x1="57" stroke-linecap="null" stroke-linejoin="null" stroke-dasharray="null" stroke-width="5" fill="none"/>
   <line id="svg_19" stroke="#003f7f" y2="99.5" x2="122" y1="99.5" x1="58" stroke-linecap="null" stroke-linejoin="null" stroke-dasharray="null" stroke-width="5" fill="none"/>
   <text stroke="#003f7f" transform="matrix(3.4442861563765934,0,0,2.0447679395653884,-394.7044206094649,-269.09630682566683) " xml:space="preserve" text-anchor="middle" font-family="Open Sans" font-size="24" id="svg_20" y="227" x="140" stroke-linecap="null" stroke-linejoin="null" stroke-dasharray="null" stroke-width="0" fill="#003f7f">SIL</text>
  </g>
 </g>
</svg>
            <div class="separador--big"></div>
          </div>
          <div class="col xs12 lg10">
            <div class="pasos">
              <div class="row justify-center-xs justify-start-sm">
                <div class="col xs12 sm6 md4">
                  <div class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-sm activo">
                    <div class="pasos__icono"><svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60" height="60" viewBox="0 0 60 60"><rect class="svg__fondo" width="60" height="60"/><path d="M46.2 12.5L46.2 12.5c0-0.3-0.1-0.5-0.3-0.7l-9-9c-0.2-0.2-0.5-0.3-0.7-0.3H11.1c-1.3 0-2.3 1-2.3 2.3v45.5c0 1.2 1 2.3 2.3 2.3H44c1.2 0 2.3-1 2.3-2.3L46.2 12.5 46.2 12.5zM37 5.8l5.8 5.7h-5.4c-0.2 0-0.4-0.2-0.4-0.4V5.8zM44.3 50.2c0 0.2-0.2 0.4-0.4 0.4H11.1c-0.2 0-0.4-0.2-0.4-0.4V4.8c0-0.2 0.2-0.4 0.4-0.4h24.2v6.8c0 1.2 1 2.3 2.3 2.3h6.8v36.7H44.3z"/><path d="M38.7 18.9c-0.2-0.2-0.5-0.3-0.7-0.3H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h20.9c0.6 0 1-0.5 1-1C39 19.3 38.9 19.1 38.7 18.9z"/><path d="M38.7 26c-0.2-0.2-0.5-0.3-0.7-0.3H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h20.9c0.6 0 1-0.5 1-1C39 26.5 38.9 26.2 38.7 26z"/><path d="M38.7 33.1c-0.2-0.2-0.5-0.3-0.7-0.3H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h20.9c0.6 0 1-0.5 1-1C39 33.6 38.9 33.3 38.7 33.1z"/><path d="M27.5 40H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h10.5c0.6 0 1-0.5 1-1S28.1 40 27.5 40z"/></svg></div>
                    <div class="pasos__data"><span>Paso 4</span>Información del Pago</div>
                  </div>
                </div>
              </div>
            </div>
            <div class="separador"></div>
            <div class="separador"></div>
            <div class="separador"></div>
            <p><strong>Licencia AUTORIZADA pero sin fecha de pago definida aún.</strong></p>
            <div class="separador"></div>
            <div class="separador"></div>
            <div class="row">
              <div class="col xs12 lg6">
                <form class="form" id="paso4" action="inicio.do">
                
                  <c:if test='${errorMsg=="pdf_error" }'>
                  <div class="alerta alerta--error">No se ha podido generar el Reporte. Por favor, intente nuevamente o comuníquese a Sistemas</div>
                  </c:if>
                  <div class="separador"></div>
                  <div class="alerta alerta--error" id="errores_paso1" style="display:none"></div>
                  
               
                </form>
              </div>
              </div>
              <div class="separador"></div>
              <div class="row">
              	<div class="col xs12 lg2">
              		<div class="btn__grupo text-align-right-xs"><a class="btn btn--primario btn--borde" href="#" onClick="paso2(); return false;">Paso 2</a>
                  	</div>
              	</div>
				<div class="col xs12 lg2">
              	 	<div class="btn__grupo text-align-right-xs"><a class="btn btn--primario" href="#" onClick="finalizar(); return false;">Finalizar</a>
                  	</div>
             	 </div>
              </div>
            
            
          </div>
        </div>
      </div>
    </main>
    <script src="assets/js/polyfill.js"></script>
    <script src="assets/js/jquery-3.3.1.js"></script>
    <script src="assets/js/jquery-ui.js"></script>
    <script src="assets/js/automatizar.js"></script>
    <script src="assets/js/fln.js"></script>
    <script src="assets/js/funciones.js"></script>
  </body>
</html>