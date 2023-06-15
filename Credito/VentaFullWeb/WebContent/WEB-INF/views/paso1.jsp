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
    <link rel="stylesheet" href="./resources/css/font-awesome-6.4.0.css"/>
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
        
          <div class="row row--bg">
            <div class="col-xs-12 col-lg-5">
              <div class="separador"></div>
              <div class="info">
                <div class="info__box info__box--centrado info__box--bigtitle">
                  <h2>Simula y solicita tu cr&eacute;dito donde te encuentres</h2>
                </div>
                <div class="info__box info__box--midtitle info__box--centrado">
                  <h4>Hola</h4>
                  <h2>${nombreCompleto}</h2>
                </div>
                <div class="info__box info__box--monto" ${campagna}>
					<div class="box__minibox">
						<span><b>Tienes una oferta preaprobada de</b></span>
						<h2>$${montoCampagna}</h2>
						<p>Vigente hasta el ${fechaCampagna}</p>
						<span>Sujeto a evaluación crediticia</span>
					</div>
                </div>
                <div class="info__box info__box--lista">
                  <ul>
                    <li>
                      <a class="info__box__link" target="_blank">
                        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                          <path d="M12.5012 0C9.10634 0 8.6803 0.0148441 7.34695 0.0755219C6.01621 0.13646 5.10787 0.347139 4.31307 0.656257C3.49092 0.975531 2.79352 1.40262 2.09872 2.09768C1.4034 2.79248 0.976312 3.48988 0.655996 4.31176C0.346097 5.10682 0.135158 6.01543 0.0752611 7.34565C0.015625 8.679 0 9.1053 0 12.5001C0 15.895 0.0151046 16.3197 0.0755219 17.653C0.13672 18.9838 0.3474 19.8921 0.656257 20.6869C0.975792 21.5091 1.40288 22.2065 2.09794 22.9013C2.79248 23.5966 3.48988 24.0247 4.3115 24.344C5.10682 24.6531 6.01543 24.8638 7.34591 24.9247C8.67926 24.9854 9.10504 25.0003 12.4996 25.0003C15.8947 25.0003 16.3194 24.9854 17.6528 24.9247C18.9835 24.8638 19.8929 24.6531 20.6882 24.344C21.5101 24.0247 22.2065 23.5966 22.901 22.9013C23.5963 22.2065 24.0234 21.5091 24.3437 20.6872C24.651 19.8921 24.862 18.9835 24.9245 17.6533C24.9844 16.32 25 15.895 25 12.5001C25 9.1053 24.9844 8.67926 24.9245 7.34591C24.862 6.01517 24.651 5.10682 24.3437 4.31202C24.0234 3.48988 23.5963 2.79248 22.901 2.09768C22.2057 1.40236 21.5104 0.975271 20.6875 0.656257C19.8906 0.347139 18.9817 0.13646 17.651 0.0755219C16.3176 0.0148441 15.8931 0 12.4973 0H12.5012ZM11.3798 2.25263C11.7126 2.25211 12.084 2.25263 12.5012 2.25263C15.8387 2.25263 16.2343 2.26461 17.5523 2.3245C18.771 2.38023 19.4325 2.58388 19.8731 2.75498C20.4565 2.98154 20.8724 3.25238 21.3096 3.68988C21.7471 4.12739 22.0179 4.54406 22.245 5.1274C22.4161 5.56751 22.62 6.22897 22.6755 7.44773C22.7354 8.76546 22.7484 9.16129 22.7484 12.4973C22.7484 15.8332 22.7354 16.2291 22.6755 17.5468C22.6198 18.7656 22.4161 19.427 22.245 19.8671C22.0185 20.4505 21.7471 20.8658 21.3096 21.3031C20.8721 21.7406 20.4567 22.0114 19.8731 22.238C19.433 22.4099 18.771 22.613 17.5523 22.6687C16.2345 22.7286 15.8387 22.7416 12.5012 22.7416C9.16338 22.7416 8.7678 22.7286 7.45008 22.6687C6.23132 22.6125 5.56985 22.4088 5.12896 22.2377C4.54562 22.0112 4.12895 21.7403 3.69144 21.3028C3.25394 20.8653 2.9831 20.4497 2.75602 19.8661C2.58492 19.426 2.38101 18.7645 2.32555 17.5458C2.26565 16.228 2.25367 15.8322 2.25367 12.4941C2.25367 9.15608 2.26565 8.76233 2.32555 7.44461C2.38127 6.22585 2.58492 5.56438 2.75602 5.12375C2.98258 4.54041 3.25394 4.12374 3.69144 3.68624C4.12895 3.24873 4.54562 2.9779 5.12896 2.75081C5.56959 2.57893 6.23132 2.37581 7.45008 2.31982C8.60321 2.26773 9.05009 2.25211 11.3798 2.2495V2.25263ZM19.1736 4.32817C18.3455 4.32817 17.6736 4.99927 17.6736 5.82766C17.6736 6.6558 18.3455 7.32768 19.1736 7.32768C20.0018 7.32768 20.6737 6.6558 20.6737 5.82766C20.6737 4.99953 20.0018 4.32765 19.1736 4.32765V4.32817ZM12.5012 6.08079C8.95608 6.08079 6.08183 8.95504 6.08183 12.5001C6.08183 16.0452 8.95608 18.9182 12.5012 18.9182C16.0463 18.9182 18.9195 16.0452 18.9195 12.5001C18.9195 8.95504 16.046 6.08079 12.5009 6.08079H12.5012ZM12.5012 8.33342C14.8022 8.33342 16.6679 10.1988 16.6679 12.5001C16.6679 14.8012 14.8022 16.6668 12.5012 16.6668C10.1998 16.6668 8.33446 14.8012 8.33446 12.5001C8.33446 10.1988 10.1998 8.33342 12.5012 8.33342Z" fill="#1F7ABC"/>
                        </svg>
                        <span>@cajalaaraucana</span>
                      </a>
                    </li>
                    <li>
                      <a class="info__box__link" target="_blank">
                        <svg width="26" height="25" viewBox="0 0 26 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                          <path d="M26 12.5759C26 5.62988 20.4032 -0.000976562 13.4993 -0.000976562C6.5953 -0.000976562 0.998535 5.62988 0.998535 12.5759C0.998535 18.8534 5.56987 24.0565 11.546 25V16.2114H8.37201V12.5759H11.546V9.80506C11.546 6.65298 13.4123 4.91187 16.2677 4.91187C17.6354 4.91187 19.066 5.15751 19.066 5.15751V8.2526H17.4897C15.9368 8.2526 15.4525 9.22208 15.4525 10.2167V12.5759H18.9195L18.3653 16.2114H15.4525V25C21.4287 24.0565 26 18.8534 26 12.5759Z" fill="#1F7ABC"/>
                        </svg>
                        <span>@cajalaaraucana</span>
                      </a>
                    </li>
                    <li>
                      <a class="info__box__link" target="_blank">
                        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                          <path d="M7.85314 23.4375C17.2869 23.4375 22.449 15.6204 22.449 8.85288C22.449 8.63297 22.449 8.41305 22.439 8.19313C23.4394 7.47339 24.3097 6.56373 25 5.53411C24.0796 5.94396 23.0892 6.21386 22.0488 6.34381C23.1092 5.71404 23.9196 4.70442 24.3097 3.50486C23.3193 4.09464 22.2189 4.51449 21.0484 4.7444C20.108 3.74477 18.7775 3.125 17.3069 3.125C14.4758 3.125 12.1749 5.42415 12.1749 8.25311C12.1749 8.65296 12.2249 9.04281 12.3049 9.42267C8.04322 9.21275 4.2617 7.16351 1.73069 4.06465C1.29052 4.82437 1.04042 5.70405 1.04042 6.6437C1.04042 8.42304 1.95078 9.99246 3.32133 10.9121C2.48099 10.8821 1.69068 10.6522 1.0004 10.2724C1.0004 10.2924 1.0004 10.3123 1.0004 10.3423C1.0004 12.8214 2.77111 14.9007 5.11204 15.3705C4.68187 15.4904 4.23169 15.5504 3.7615 15.5504C3.43137 15.5504 3.11124 15.5204 2.80112 15.4604C3.45138 17.4997 5.35214 18.9791 7.59304 19.0191C5.83233 20.3986 3.62145 21.2183 1.22049 21.2183C0.810325 21.2183 0.40016 21.1983 0 21.1483C2.2609 22.5878 4.96199 23.4375 7.85314 23.4375Z" fill="#1F7ABC"/>
                        </svg>                          
                        <span>@cajalaaraucana</span>
                      </a>
                    </li>
                    <li>
                      <a class="info__box__link" target="_blank">
                        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                          <path d="M0 1.79084C0 0.802052 0.821894 0 1.8358 0H23.1642C24.1781 0 25 0.802055 25 1.79084V23.2092C25 24.1983 24.1781 25 23.1642 25L1.8358 25C0.821894 25 0 24.1983 0 23.2092V1.79084ZM7.72403 20.9276V9.63869H3.97181V20.9276H7.72403ZM5.8479 8.09738C7.15638 8.09738 7.9708 7.23052 7.9708 6.14719C7.94641 5.03948 7.15638 4.19667 5.87273 4.19667C4.5892 4.19667 3.75 5.03948 3.75 6.14719C3.75 7.23052 4.56424 8.09738 5.82345 8.09738H5.8479ZM13.5178 20.9276V14.6234C13.5178 14.286 13.5422 13.9489 13.6413 13.7078C13.9125 13.0336 14.5299 12.3355 15.5665 12.3355C16.9242 12.3355 17.4674 13.3707 17.4674 14.8883V20.9276H21.2192V14.4547C21.2192 10.9872 19.3681 9.37379 16.8994 9.37379C14.909 9.37379 14.0166 10.4678 13.5179 11.237V11.2759H13.4929C13.5011 11.263 13.5094 11.25 13.5179 11.237V9.63869H9.76573C9.81497 10.698 9.76573 20.9276 9.76573 20.9276H13.5178Z" fill="#1F7ABC"/>
                        </svg>
                        <span>@cajalaaraucana</span>
                      </a>
                    </li>
                  </ul>
                </div>
                <div class="info__link" style="margin-top: -20px">
                  <p>Conoce los <a href="img/Detalle_Condiciones.doc.pdf" class="link" target="_blank">t&eacute;rminos y condiciones</a></p>
                  <p style="font-size: small; margin-top: 30px;color:#666666;text-align: justify;">Las Cajas de Compensación son entidades de seguridad social, fiscalizadas por la Superintendencia de Seguridad Social <a href="https://www.suseso.cl" target="_blank">www.suseso.cl</a></p>
                </div>
              </div>
            </div>
            <div class="col-xs-12 col-lg-7 col-azul">
              <div class="pasos-main">
                <div class="separador hide-index"></div>
                <div class="row">
                  <div class="col-xs-12">
                    <div class="pasos">
                      <div class="pasos--uno">
                        <h4>Simulaci&oacute;n</h4>
                        <div class="pasos__box">
                          <div class="pasos__item pasos__item--uno activo">
                            <span>1</span>
                          </div>
                          <div class="pasos__item pasos__item--dos">
                            <span>2</span>
                          </div>
                          <div class="pasos__item pasos__item--tres">
                            <span>3</span>
                          </div>
                          <div class="pasos__item pasos__item--cuatro">
                            <span>4</span>
                          </div>
                          <div class="pasos__item pasos__item--cinco">
                            <span>5</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-xs-12 col-lg-12">
                    <form id="paso1" class="form" action="paso2.do" method="post">
                      <div class="box">
                        <p class="box__titulo">Monto</p>
                        <label for="valor" hidden>Monto</label>
                        <input type="text" id="valor" name="valor" value="${montoaSimular}" class="text tipo_numerico">
                        <span class="symbol">$</span>
                         
                        <div class="box__slider">
                          <div id="dataMontoMaximo" data-montomaximo=${montoMaximo}></div>
                          <div id="dataMontoMaximoLabel" data-montomaximolabel=${montoMaximoLabel}></div>
                          <div id="monto-valor"></div>                          
                          <div class="slider__numbers">
                            <span>$40.000</span>
                            <span>$${montoMaximoLabel}</span>
                          </div>
                        </div>
                        <div class="alerta alerta--error" id="less-more" style="display:none;"></div>
                      </div>
    
                      <div class="box">
                        <p class="box__titulo">N° de cuotas</p>
                        <label for="amount" hidden>N° de cuotas</label>
                        <div class="box__slide">
                          <div class="box__slide__input">
                            <input type="text" id="monto" name="monto">
                          </div>
                          <div class="box__slide__barra">
                            <div id="numero-cuotas"></div>
                            <div class="slider__numbers">
                              <span>3</span>
                              <span>60</span>
                            </div>
                          </div>
                        </div>
                        <div class="alerta alerta--error" id="min-max" style="display:none;"></div>
                      </div>
                      
                      <div class="box">
                        <div class="form__grupo" data-animacion="placeholder" style="margin-top: -30px; padding-top:18px;">
                          <i class="icono bi bi-chevron-down"></i>
                          <select name="sucursal" id="sucursal">
                            <optgroup label="sucursal" hidden="hidden">Selecciona Sucursal</optgroup>
                            <option value=""></option>
                            <c:forEach items="${sucursales}" var="suc">
                            	<option value="${suc.codigo}">${suc.descripcion}</option>
                            </c:forEach>
                          </select>
                          <label for="sucursal">Selecciona una sucursal para futuras atenciones</label>
                        </div>
                         <c:if test="${afiliado.segmento!='PENSIONADOS'}">
                        <div class="form__seleccion" style="margin-bottom: -5px; margin-top: 15px;">
                          <div class="form__checkbox form__checkbox">
                         
                            <input type="checkbox" name="seguro-cesantia" id="seguro-cesantia" >
                            <label for="seguro-cesantia">Incluir seguro de cesant&iacute;a</label>
                          </div>
                          		<p style="font-size: small; margin-top: 10px">No olvides marcar el seguro para estar protegido en contingencia.</p>
                        </div>
                         </c:if>
                      </div>
    
                      <div class="alerta alerta--error" id="errores_paso1" style="display:none"></div>
                      <div class="btn__grupo btn__grupo--der">
                        <a class="boton boton--terciario" href="#" onClick="validarPaso1(); return false;">Simular</a>
                      </div>
                      
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modulo-check">
            <i class="bi bi-chevron-right modulo-check-volver"></i>
            <h3>¿Est&aacute;s seguro(a) que deseas eliminar el seguro de cesant&iacute;a?</h3>
            <h4>En caso de cesant&iacute;a cubre <span>hasta 6 meses del cr&eacute;dito.</span></h4>
            <img src="img/icono-cesantia.svg" alt="Icono seguridad">
          </div>
        </div>
      </div>
    </main>
      <!-- Preloader -->
    <div class="modal-generico" id="procesando-credito" style="display:none;">
		<div class="modal-generico__interior preload">
			<svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
				<path d="M20.0001 11.8855C15.5186 11.8855 11.8857 15.5185 11.8857 19.9999C11.8857 24.4814 15.5186 28.1143 20.0001 28.1143C24.4815 28.1143 28.1145 24.4814 28.1145 19.9999C28.1145 15.5185 24.4815 11.8855 20.0001 11.8855ZM14.3857 19.9999C14.3857 16.8992 16.8993 14.3855 20.0001 14.3855C23.1008 14.3855 25.6145 16.8992 25.6145 19.9999C25.6145 23.1007 23.1008 25.6143 20.0001 25.6143C16.8993 25.6143 14.3857 23.1007 14.3857 19.9999Z" fill="#1F7ABC"/>
				<path d="M24.4912 3.35844C23.1713 -1.11948 16.8287 -1.11948 15.5088 3.35844L15.2742 4.15448C14.8811 5.48798 13.358 6.11888 12.1372 5.45388L11.4084 5.0569C7.30874 2.82381 2.82382 7.30875 5.0569 11.4084L5.45388 12.1372C6.11888 13.358 5.48798 14.8811 4.15448 15.2742L3.35844 15.5088C-1.11948 16.8287 -1.11948 23.1713 3.35844 24.4912L4.15448 24.7258C5.48798 25.1189 6.11888 26.642 5.45387 27.8628L5.0569 28.5916C2.82382 32.6912 7.30873 37.1762 11.4084 34.9431L12.1372 34.5461C13.358 33.8811 14.8811 34.512 15.2742 35.8455L15.5088 36.6416C16.8287 41.1195 23.1713 41.1195 24.4912 36.6416L24.7258 35.8455C25.1189 34.512 26.642 33.8811 27.8628 34.5461L28.5916 34.9431C32.6913 37.1762 37.1762 32.6913 34.9431 28.5916L34.5461 27.8628C33.8811 26.642 34.512 25.1189 35.8455 24.7258L36.6416 24.4912C41.1195 23.1713 41.1195 16.8287 36.6416 15.5088L35.8455 15.2742C34.512 14.8811 33.8811 13.358 34.5461 12.1372L34.9431 11.4084C37.1762 7.30873 32.6912 2.82382 28.5916 5.05691L27.8628 5.45388C26.642 6.11888 25.1189 5.48798 24.7258 4.15448L24.4912 3.35844ZM17.9068 4.06525C18.522 1.97825 21.478 1.97825 22.0932 4.06525L22.3278 4.86128C23.1711 7.72248 26.4392 9.07616 29.0587 7.64931L29.7875 7.25234C31.6982 6.21157 33.7884 8.30184 32.7477 10.2125L32.3507 10.9413C30.9238 13.5608 32.2775 16.8289 35.1387 17.6722L35.9348 17.9068C38.0217 18.522 38.0217 21.478 35.9348 22.0932L35.1387 22.3278C32.2775 23.1711 30.9238 26.4392 32.3507 29.0587L32.7477 29.7875C33.7884 31.6982 31.6982 33.7884 29.7875 32.7477L29.0587 32.3507C26.4392 30.9238 23.1711 32.2775 22.3278 35.1387L22.0932 35.9348C21.478 38.0217 18.522 38.0217 17.9068 35.9348L17.6722 35.1387C16.8289 32.2775 13.5608 30.9238 10.9413 32.3507L10.2125 32.7477C8.30184 33.7884 6.21157 31.6982 7.25234 29.7875L7.64931 29.0587C9.07615 26.4392 7.72249 23.1711 4.86129 22.3278L4.06525 22.0932C1.97825 21.478 1.97825 18.522 4.06524 17.9068L4.86129 17.6722C7.72249 16.8289 9.07616 13.5608 7.64931 10.9413L7.25234 10.2125C6.21158 8.30184 8.30184 6.21158 10.2125 7.25234L10.9413 7.64931C13.5608 9.07616 16.8289 7.72249 17.6722 4.86129L17.9068 4.06525Z" fill="#1F7ABC"/>
			</svg>
			<h2>Nos encontramos simulando tu Cr&eacute;dito</h2>
			<p>Espera un momento por favor</p>
		</div>
	</div>
    <script src="./assets/js/polyfill.js"></script>
    <script src="./assets/js/jquery-3.6.0.js"></script>
    <script src="./assets/js/jquery-ui.js"></script>
    <script src="./assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="./assets/js/automatizar.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/fln.js"></script>
    <script src="./assets/js/funciones.js"></script>
    <script src="./resources/js/font-awesome-6.4.0.js"></script>
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