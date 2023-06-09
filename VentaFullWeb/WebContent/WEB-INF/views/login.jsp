<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<meta name="format-detection" content="telephone=no" />
	<title>Cr&eacute;dito Digital - La Araucana</title>
	<link rel="icon" href="./assets/favicon.ico">
	<link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<link rel="stylesheet" href="./assets/css/jquery-ui.css" />
	<link rel="stylesheet" href="./assets/css/base.css" />
	<link rel="stylesheet" href="./assets/css/estilos.css" />
	<!-- Recaptcha v3 -->
	<!--  <script src="https://www.google.com/recaptcha/api.js?render=6LcvecIlAAAAAFueQRNIu94KsrrIQVMpl1rsEBHj"></script> -->
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
		<div class="solicita-credito-social">
			<div class="contenedor-fluido">
				<div class="marco marco--login">
					<div class="row" style="justify-content: center;">
						<div class="col-xs-12 col-lg-11">
							<div class="info info--login">
				                <div class="info__box info__box--bigtitle info__box--login">
				                  <h2>Simula y solicita tu cr&eacute;dito donde te encuentres</h2>
				                 
				                </div>
             				 </div>
						</div>
						<div class="col-xs-12 col-lg-11">
							<form id="form-rut-credito-social" class="form" target="_top" action="validaAfiliado.do" method="post" style="margin-bottom:0px">
								<!--  <form id="form-rut-credito-social" class="form" style="margin-bottom:0px"> -->
									<div class="row" style="margin-bottom:-1.7rem;">
									
										<div class="col-xs-12 col-lg-3">
											<div class="form__captcha">											
												<div class="form__grupo form__grupo--cel" style="position:relative;">
													<input type="text" class="text tipo_telefono requerido celular" name="celular-login" id="celular-login" data-prefijo="+569" data-prefijo-tipo="celular" placeholder="Ingresa tu celular" data-minlength="12" maxlength="12" onblur="validarPhone(this);" autocomplete="off">
													<label for="celular-login" hidden>Celular</label>
	
													<span class="tippy ayuda-cel" data-tippy-content="Este dato se requiere para contactarlo en caso de no concretar su solicitud">
														<svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
															<circle cx="10" cy="10" r="9.5" fill="white" stroke="#1F7ABC"/>
															<path d="M10.4141 7.20312V15.7656H9.11719V7.20312H10.4141ZM9.78125 4C9.99479 4 10.1771 4.07031 10.3281 4.21094C10.4844 4.34635 10.5625 4.5599 10.5625 4.85156C10.5625 5.13802 10.4844 5.35156 10.3281 5.49219C10.1771 5.63281 9.99479 5.70312 9.78125 5.70312C9.55729 5.70312 9.36979 5.63281 9.21875 5.49219C9.07292 5.35156 9 5.13802 9 4.85156C9 4.5599 9.07292 4.34635 9.21875 4.21094C9.36979 4.07031 9.55729 4 9.78125 4Z" fill="#1F7ABC"/>
														</svg>
													</span>
												</div>
											</div>
										</div>
										
										<div class="col-xs-12 col-lg-3">
											<div class="form__captcha">
												<div class="form__grupo" style="margin-bottom: 0;">
													<input type="text" class="text tipo_rut rut requerido" name="rut-credito-social" id="rut-credito-social" maxlength="12" placeholder="Ingresa tu RUT" autocomplete="off">
													<label for="rut-credito-social" hidden>Rut</label>
												</div>
											
											</div>
										</div>											
										
										<div class="col-xs-12 col-lg-3">
											<div class="form__captcha">												
												<div class="form__grupo form__grupo--serie" style="margin-bottom: 10px;position:relative;">
													<input class="text requerido tipo_serie" type="text" id="serie" name="serie" maxlength="10" placeholder="Ingresa tu n&uacute;mero de serie" autocomplete="off">
													<label for="serie" hidden>N&uacute;mero de serie</label>
	
													<div class="ayuda ayuda--form">
														<span>?</span>
														<div class="ayuda__container">
															<p>Ac&aacute; puedes encontrar el n&uacute;mero de serie de tu c&eacute;dula
																de identidad.</p>
															<div class="separador--small"></div>
															<a href="img/numero-serie.png" target="_blank"><img src="img/numero-serie.png" alt="N&uacute;mero serie"></a>
														</div>
													</div>
												</div>
												<div id="captcha" class="captcha"></div>
												<!-- Recaptcha v3 
												<div class="col-xs-12 col-lg-3">
													<div class="form__captcha">
														
														<input type="hidden" id="g-recaptcha-response" name="g-recaptcha-response">
    													<input type="hidden" name="action" value="validate_captcha">										
													</div>
												</div>
												-->												
											</div>
										</div>
										
										<div class="col-xs-12 col-lg-3">
											<div class="botonera-login form__grupo">
												<a href="#" id="btn-solicitar" class="solicitar" onclick="validarLogin();">Solicitar</a>
											</div>
										</div>
										
										<div class="col-xs-8 col-lg-12">
											<div class="alerta alerta--error" id="errores_form-rut-credito-social" style="display:none;"></div>
											
											<c:if test='${not empty mensajeError}'>
												<div class='alerta alerta--error error-validacion' id='errores_form-rut-credito-social'>${mensajeError}</div>												
											</c:if>
											<!-- <div class="respuesta__item--error" id="error-rut-credito-social" style="display:none"></div> --> 
											
										</div>
										
									</div>
									
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script>
		window.onload = function (){
			$('#celular-login').focus(function() {
				$('#celular-login').val("+569");
				$('#celular-login').unbind();
			});
			
			$('#celular-login').blur(function() {
				$('#celular-login').val("+569");
				$('#celular-login').unbind();
			});
			loginGenesys();
			
		};
		
		var _captcha = document.getElementById('captcha');
		var onloadCallback = function () {
			if (_captcha) {
				grecaptcha.render(_captcha, {
					'sitekey': '<%=cl.laaraucana.ventafullweb.util.Configuraciones.getConfig("servicios.recaptcha.key")%>',
					'callback': recaptchaCallback,
				})
			}
		}
		var recaptchaCallback = function recaptchaCallback(response) {
			$('.captcha').removeClass('invalido');
			$('.captcha').addClass('valido');
		};
		
		/* ReCaptcha v3
		grecaptcha.ready(function() {
		    // do request for recaptcha token
		    // response is promise with passed token
	        grecaptcha.execute('6LcvecIlAAAAAFueQRNIu94KsrrIQVMpl1rsEBHj', {action:'validate_captcha'})
				.then(function(token) {
					// add token value to form
					document.getElementById('g-recaptcha-response').value = token;
        	});
    	});
		*/
			
				
	</script>	
	<!-- Comentar la siguiente linea para recaptcha v3 -->
	<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&amp;render=explicit"></script>
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
		<c:if test='${not empty mensajeError}'>
			setTimeout(function () {
	      		$('.error-validacion').fadeOut('fast');
		    }, 5000);
		</c:if>
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