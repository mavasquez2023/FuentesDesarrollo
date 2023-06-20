<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>Consulta Saldo a Favor - La Araucana</title>
    <link rel="stylesheet" href="./assets/css/fln.css"/>
    <link rel="stylesheet" href="./fonts/fln-icons.css"/>
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
      <div class="container">
        <div class="separador"></div>
        <div class="row">
          <div class="col xs12 lg12 xl2 text-align-center-xs"><svg xmlns="http://www.w3.org/2000/svg" class="home_icon" width="150" height="135" viewBox="0 0 150 134.9"><path class="st0" d="M148.8 63.9c1.2-0.8 1.5-2.4 0.7-3.6 -0.8-1.2-2.3-1.5-3.5-0.8l-30.9 19.7v-6.7L148.8 51c1.2-0.8 1.5-2.4 0.7-3.6 -0.8-1.2-2.3-1.5-3.5-0.8l-30.9 19.7v-8.9l33.7-21.5c1.2-0.8 1.6-2.4 0.8-3.6 -0.2-0.4-0.5-0.7-0.9-0.9l-55.2-31c-0.8-0.5-1.8-0.4-2.7 0.1L1.2 56.2c-1.2 0.8-1.6 2.4-0.8 3.6 0.2 0.3 0.5 0.6 0.8 0.8l53.8 33.2c0.9 0.5 1.9 0.5 2.8 0l32.8-20.9v8.9l-34.2 21.8L4 71.3c-1.2-0.8-2.8-0.4-3.6 0.8C-0.4 73.3 0 75 1.2 75.7l53.8 33.2c0.9 0.5 1.9 0.5 2.8 0L90.7 88v6.7l-34.2 21.8L4 84.2c-1.2-0.8-2.8-0.4-3.6 0.8 -0.8 1.2-0.4 2.8 0.9 3.6l53.8 33.2c0.9 0.5 1.9 0.5 2.8 0l32.8-20.9v6.4l-34.2 21.8L4 96.8c-1.2-0.8-2.8-0.4-3.6 0.8 -0.8 1.2-0.4 2.8 0.9 3.6l53.8 33.2c0.9 0.5 1.9 0.5 2.8 0L91.5 113c0.9 0.8 2.2 0.9 3.2 0.3l19.2-12.5c0.7-0.5 1.2-1.3 1.2-2.2V98l33.7-21.5c1.2-0.8 1.5-2.4 0.7-3.6 -0.8-1.2-2.3-1.5-3.5-0.8l-30.9 19.7v-6.4L148.8 63.9zM56.4 88.5L7.6 58.4l30.8-19.2 49.8 29.1L56.4 88.5zM109.9 69.6v27.7l-14 9.1V68.3c0-1-0.5-1.8-1.4-2.3l0 0L43.7 36.3l13.7-8.8L109.9 58 109.9 69.6zM112 53.1L62.3 24.3l30-18.7 50.1 28.2L112 53.1z"/></svg>
            <div class="separador--big block-xl oculto-xs"></div>
          </div>
          <div class="separador--big block-xl oculto-lg"></div>
          <div class="col xs12 lg10">
            <div class="row">
              <div class="col xs12 lg12 xl6">
                <form class="form" id="validar-rut" action="consulta.do" method="POST">
                  <div class="form__grupo" data-animacion="placeholder" data-comentario="Sin puntos ni guión. Ej: 12345678k">
                    <input class="text requerido tipo_rut" type="text" id="rut" name="rut" maxlength="12"/>
                    <label for="rut">Ingresa tu RUT</label>
                  </div>
                   <div class="separador"></div>
                  <div class="captcha" id="captcha"></div>
                  <div class="separador"></div>
                  <div class="alerta alerta--aviso alerta--rut" style="display:none">Debes ingresar un RUT válido.</div>
                  <div class="alerta alerta--error" id="errores_validar-rut" style="display:none"></div>
                  <div class="btn__grupo text-align-right-md text-align-center-xs"><a class="btn btn--primario" href="#" onClick="validarPaso1(); return false;">Consultar</a>
                  </div>
                </form>
              </div>
              <jsp:include page="leyenda.jsp"></jsp:include>
            </div>
          </div>
        </div>
      </div>
    </main>
    <div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
    <script>
		var _captcha = document.getElementById('captcha')
		var key = document.getElementById('keyCaptcha');

		var onloadCallback = function() {
			if (_captcha) {
				grecaptcha.render(_captcha, {
					'sitekey' : '<%=cl.laaraucana.saldoafavor.utils.Configuraciones.getConfig("servicios.recaptcha.key")%>',
					'callback' : recaptchaCallback,
				})
			}
		}
		
	</script>
	
	<script
		src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&amp;render=explicit"></script>
    <script src="./assets/js/polyfill.js"></script>
    <script src="./assets/js/jquery-3.4.1.js"></script>
    <script src="./assets/js/jquery.Rut.js"></script>
    <script src="./assets/js/jquery-ui.js"></script>
    <script src="./assets/js/automatizar.js"></script>
    <script src="./assets/js/fln.js"></script>
    <script src="./assets/js/funciones.js"></script>
    <script>
	$(document).ready(function() {
			$('#rut').keyup(function(){
        		$(this).val($(this).val().toUpperCase());
    		});
		});
	</script>
  </body>
</html>