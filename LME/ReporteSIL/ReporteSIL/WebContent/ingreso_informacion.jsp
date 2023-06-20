<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>>Reporte SIL La Araucana</title>
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
      
      
      function capitalize(str,force){
  		str=force ? str.toLowerCase() : str;  
  		return str.replace(/(\b)([a-zA-Z])/g,
           function(firstLetter){
              return   firstLetter.toUpperCase();
           });       
	}
	function paso2(){
      	 window.location="<%=request.getContextPath()%>/seleccion.jsp";
      }
    </script>
    <style type="text/css">
		#direccion {
    		text-transform:capitalize;
		}
	</style>
  </head>
  <body onsubmit="showLoading();">
    <main>
      <div class="container">
        <div class="separador"></div>
        <div class="row">
          <div class="col xs12 lg2 text-align-center-xs">
          <a href="index.jsp">
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
</a>
            <div class="separador--big"></div>
          </div>
          <div class="col xs12 lg10">
            <div class="pasos">
              <div class="row justify-center-xs justify-start-sm">
                <div class="col xs12 sm6 md4">
                  <div class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-sm">
                    <div class="pasos__icono"><svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60" height="60" viewBox="0 0 60 60"><rect class="svg__fondo" width="60" height="60"/><path d="M53.4 12.3H6.6c-2.3 0-4.1 1.8-4.1 4.1v27.2c0 1.1 0.4 2.1 1.2 2.9 0.8 0.8 1.8 1.2 2.9 1.2h46.8c2.3 0 4.1-1.8 4.1-4.1V16.4C57.5 14.2 55.7 12.3 53.4 12.3zM6.6 14.7h46.8c0.9 0 1.7 0.8 1.7 1.7v27.2c0 0.9-0.8 1.7-1.7 1.7H6.6c-0.9 0-1.7-0.8-1.7-1.7l0-27.2C4.9 15.5 5.6 14.7 6.6 14.7z"/><path d="M29.7 26h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 25.9 29.4 26 29.7 26z"/><path d="M29.7 31.2h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 31 29.4 31.2 29.7 31.2z"/><path d="M29.7 36.3h8.9c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2h-8.9c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 36.2 29.4 36.3 29.7 36.3z"/><path d="M16.3 31.3c2.6 0 4.7-2.1 4.7-4.7 0-0.2 0-0.3 0-0.5l0 0c0-0.2-0.1-0.5-0.1-0.7 -0.1-0.2-0.1-0.4-0.2-0.7 -0.7-1.7-2.4-2.9-4.3-2.9 -1.9 0-3.6 1.1-4.3 2.9 -0.1 0.2-0.2 0.4-0.2 0.7 -0.1 0.2-0.1 0.5-0.1 0.7 0 0.2 0 0.3 0 0.5C11.6 29.2 13.7 31.3 16.3 31.3zM14.4 24.3c0.1-0.1 0.3-0.2 0.5-0.3 0.1 0 0.2-0.1 0.3-0.1 0.2-0.1 0.4-0.1 0.5-0.2 0.2 0 0.4-0.1 0.6-0.1 1.6 0 2.9 1.3 2.9 2.9 0 0.2 0 0.4-0.1 0.6 0 0.2-0.1 0.4-0.2 0.5 0 0.1-0.1 0.2-0.1 0.3h0c-0.1 0.2-0.2 0.3-0.3 0.5 -0.6 0.7-1.4 1.1-2.3 1.1 -0.2 0-0.4 0-0.6-0.1 -0.2 0-0.4-0.1-0.5-0.2 -0.1 0-0.2-0.1-0.3-0.1 -0.2-0.1-0.3-0.2-0.5-0.3 -0.1-0.1-0.3-0.3-0.4-0.4 -0.1-0.1-0.2-0.3-0.3-0.5 0-0.1-0.1-0.2-0.1-0.3 -0.1-0.2-0.1-0.4-0.2-0.5 0-0.2-0.1-0.4-0.1-0.6C13.4 25.7 13.7 24.9 14.4 24.3z"/><path d="M24.4 35.6c0-0.6-0.2-1.1-0.5-1.6 -0.1-0.2-0.3-0.4-0.5-0.6 -0.1-0.1-0.1-0.1-0.2-0.2 -0.2-0.2-0.4-0.3-0.6-0.4 -0.2-0.1-0.3-0.2-0.5-0.2 -3.8-1.5-8-1.5-11.8 0 -0.2 0.1-0.3 0.1-0.5 0.2 -0.2 0.1-0.4 0.3-0.6 0.4 -0.1 0.1-0.1 0.1-0.2 0.2 -0.2 0.2-0.3 0.4-0.5 0.6 -0.3 0.5-0.5 1.1-0.5 1.6v2.1h16.2V35.6zM11.1 34.2c0.6-0.2 1.3-0.5 1.9-0.6 0.6-0.2 1.3-0.3 2-0.3 0.2 0 0.4 0 0.7 0 1.1-0.1 2.2 0 3.3 0.2 0.9 0.2 1.7 0.4 2.6 0.8 0.1 0 0.2 0.1 0.3 0.1 0.1 0 0.2 0.1 0.2 0.1 0.1 0.1 0.1 0.1 0.2 0.2 0.1 0.1 0.3 0.3 0.3 0.5 0 0 0 0.1 0 0.1 0 0.1 0 0.1 0.1 0.2 0 0.1 0 0.1 0 0.2V36H9.9v-0.4c0-0.1 0-0.1 0-0.2 0-0.1 0-0.1 0.1-0.2 0 0 0-0.1 0-0.1 0.1-0.2 0.2-0.3 0.3-0.5 0.1-0.1 0.1-0.1 0.2-0.2 0.1-0.1 0.1-0.1 0.2-0.1C10.9 34.3 11 34.3 11.1 34.2z"/></svg></div>
                    <div class="pasos__data"><span>Paso 1</span>Ingresa RUT</div>
                  </div>
                </div>
                <div class="col xs12 sm6 md4 oculto-xs block-sm">
                  <div class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-sm">
                    <div class="pasos__icono"><svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60" height="60" viewBox="0 0 60 60"><rect class="svg__fondo" width="60" height="60"/><path d="M46.2 12.5L46.2 12.5c0-0.3-0.1-0.5-0.3-0.7l-9-9c-0.2-0.2-0.5-0.3-0.7-0.3H11.1c-1.3 0-2.3 1-2.3 2.3v45.5c0 1.2 1 2.3 2.3 2.3H44c1.2 0 2.3-1 2.3-2.3L46.2 12.5 46.2 12.5zM37 5.8l5.8 5.7h-5.4c-0.2 0-0.4-0.2-0.4-0.4V5.8zM44.3 50.2c0 0.2-0.2 0.4-0.4 0.4H11.1c-0.2 0-0.4-0.2-0.4-0.4V4.8c0-0.2 0.2-0.4 0.4-0.4h24.2v6.8c0 1.2 1 2.3 2.3 2.3h6.8v36.7H44.3z"/><path d="M38.7 18.9c-0.2-0.2-0.5-0.3-0.7-0.3H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h20.9c0.6 0 1-0.5 1-1C39 19.3 38.9 19.1 38.7 18.9z"/><path d="M38.7 26c-0.2-0.2-0.5-0.3-0.7-0.3H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h20.9c0.6 0 1-0.5 1-1C39 26.5 38.9 26.2 38.7 26z"/><path d="M38.7 33.1c-0.2-0.2-0.5-0.3-0.7-0.3H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h20.9c0.6 0 1-0.5 1-1C39 33.6 38.9 33.3 38.7 33.1z"/><path d="M27.5 40H17c-0.6 0-1 0.5-1 1s0.5 1 1 1h10.5c0.6 0 1-0.5 1-1S28.1 40 27.5 40z"/></svg></div>
                    <div class="pasos__data"><span>Paso 2</span>Selecciona licencia</div>
                  </div>
                </div>
                <div class="col xs12 sm6 md4 oculto-xs block-sm">
                  <div class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-sm activo">
                    <div class="pasos__icono"><svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60" height="60" viewBox="0 0 60 60"><rect class="svg__fondo" width="60" height="60"/><path d="M49.4 10.6C44.3 5.4 37.3 2.5 30 2.5c-7.3 0-14.3 2.9-19.4 8.1C5.4 15.7 2.5 22.7 2.5 30s2.9 14.3 8.1 19.4c5.2 5.2 12.1 8.1 19.4 8.1 7.3 0 14.3-2.9 19.4-8.1s8.1-12.1 8.1-19.4S54.6 15.7 49.4 10.6zM30 54.3C16.6 54.3 5.7 43.4 5.7 30S16.6 5.7 30 5.7 54.3 16.6 54.3 30 43.4 54.3 30 54.3z"/><path d="M40.9 21.2L26.6 35.4l-7.5-7.5c-0.6-0.6-1.6-0.6-2.3 0 -0.6 0.6-0.6 1.6 0 2.3l8.6 8.6c0.3 0.3 0.7 0.5 1.1 0.5 0.4 0 0.8-0.2 1.1-0.5l15.4-15.4c0.6-0.6 0.6-1.6 0-2.3C42.5 20.5 41.5 20.5 40.9 21.2z"/></svg></div>
                    <div class="pasos__data"><span>Paso 3</span>Ingresa datos adicionales</div>
                  </div>
                </div>
              </div>
            </div>
            <div class="separador"></div>
            <form class="form" id="paso2" action="datosLicencia.do">
            <p><strong>Información del Afiliado</strong></p>
            
              <div class="row">
              <div class="col xs12 lg4">
                  <div class="form__grupo" data-animacion="placeholder">
                    <input class="text" type="text" id="rut" name="rut" value="${rut}" disabled="disabled"/>
                    <label for="rut">RUT</label>
                  </div>
                </div>
                <div class="col xs12 lg4">
                  <div class="form__grupo" data-animacion="placeholder">
                    <input class="text" type="text" id="nombre" name="nombre" value="${nombre }" disabled="disabled"/>
                    <label for="nombre">Nombre</label>
                  </div>
                </div>
                <div class="col xs12 lg4">
                  <div class="form__grupo" data-animacion="placeholder">
                    <input class="text" type="text" id="estado" name="estado" value="${estado}" disabled="disabled"/>
                    <label for="estado">Estado Afiliacion</label>
                  </div>
                </div>
               
              </div>
              <p><strong>Información Licencia</strong></p>
              <div class="row">
              	<div class="col xs12 lg2">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text" type="text" id="numlic" name="numlic" value="${licencia.licencia}" disabled="disabled"/>
                    <label for="numlic">Nro. Licencia</label>
                  </div>
                </div>
                <div class="col xs12 lg2">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text" type="text" id="fecini" name="fecini" value="${licencia.fechaDesdeStr}" disabled="disabled"/>
                    <label for="fecini">Fecha Inicio</label>
                  </div>
                </div>
                <div class="col xs12 lg2">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text" type="text" id="fecter" name="fecter" value="${licencia.fechaHastaStr}" disabled="disabled"/>
                    <label for="fecter">Fecha Fin</label>
                  </div>
                </div>
               <div class="col xs12 lg2">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text" type="text" id="numdia" name="numdia" value="${licencia.dias}" disabled="disabled"/>
                    <label for="numdia">Nro. Días</label>
                  </div>
                </div>
                <div class="col xs12 lg2">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text" type="text" id="estadolic" name="estadolic" value="${licencia.estado}" disabled="disabled"/>
                    <label for="estadolic">Estado</label>
                  </div>
                </div>
              </div>
             <div class="separador"></div>
             <p><strong>Ingreso Información</strong></p>
              <div class="row">
              	<div class="col xs12 lg6">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text tipo_email" type="text" id="email" name="email" value="${licencia.email}" maxlength="50"/>
                    <label for="email">Correo Afiliado</label>
                  </div>
                </div>
                <div class="col xs12 lg6">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text tipo_fono tipo_numerico" type="text" id="celular" name="celular" value="${licencia.celular}" data-prefijo="+569" data-prefijo-tipo="celular"/>
                    <label for="celular">Celular Afiliado</label>
                  </div>
                </div>
                <div class="col xs12 lg6">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text requerido" type="text" id="direccion" name="direccion" value="${licencia.direccion}" maxlength="50" />
                    <label for="direccion">Dirección</label>
                  </div>
                </div>
               <div class="col xs12 lg6">
                  <div class="form__grupo" data-animacion="placeholder">
                  <input class="text" type="text" id="observacion" name="observacion" value="${licencia.observacion}" maxlength="100" />
                    <label for="observacion">Observación</label>
                  </div>
                </div>
              </div>
              <div class="separador"></div>
              <div class="alerta alerta--error" id="errores_paso2" style="display:none"></div>

               
              <div class="col xs12 lg4">
              		<div class="btn__grupo text-align-center-xs"><a class="btn btn--primario btn--borde" href="#" onClick="paso2(); return false;">Volver</a>
                  	</div>
              	</div>
               <div class="col xs12 lg4">
                	<div class="btn__grupo text-align-center-xs"><a class="btn btn--primario" href="#" onClick="validarPaso2(); return false;">
                	<c:choose>
                	<c:when test='${licencia.estado=="AUTORIZADA" }'>CONTINUAR</c:when>
                	<c:otherwise>ACEPTAR</c:otherwise>
                	</c:choose>
                	</a>
          			</div>
          		</div>	

          </form>
        </div>
      </div>
    </main>
    <div id="loading" style="position:absolute; top:30%; left:47%; display:none; z-index: 1" >
		<img src="img/loading.gif">
	</div>
    <script src="assets/js/polyfill.js"></script>
    <script src="assets/js/jquery-3.3.1.js"></script>
    <script src="assets/js/jquery-ui.js"></script>
    <script src="assets/js/automatizar.js"></script>
    <script src="assets/js/fln.js"></script>
    <script src="assets/js/funciones.js"></script>
    <script type="text/javascript">
      $(document).ready(function(){
        $("#direccion").blur(function() {
        	$(this).val(capitalize(this.value, true));
        });
        });
	</script>
  </body>
</html>