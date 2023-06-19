<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, viewport-fit=cover" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta name="format-detection" content="telephone=no" />
<title>Contrato Canales Remotos - La Araucana</title>
<link rel="stylesheet" href="assets/css/fln.css" />
<link rel="stylesheet" href="fonts/fln-icons.css" />
<link rel="stylesheet" href="assets/css/estilos.css" />
<script>
	WebFontConfig = {
		google : {
			families : [ 'Open Sans:300,400,500,700' ]
		}
	};
	(function() {
		var wf = document.createElement('script');
		wf.src = ('https:' == document.location.protocol ? 'https' : 'http')
				+ '://ajax.googleapis.com/ajax/libs/webfont/1.5.18/webfont.js';
		wf.type = 'text/javascript';
		wf.async = 'true';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(wf, s);
	})();
</script>

</head>
<body>


	<div class="container">
	<div align="right">
		&nbsp;
	</div>
		<div class="row">
			<div class="col xs12 lg2 text-align-center-xs">
				<svg class="svg svg--secundario" xmlns="http://www.w3.org/2000/svg"
					width="155" height="140" viewBox="0 0 155.3 140">
					<path
						d="M77.7 85.4c23.6 0 42.7-19.1 42.7-42.7S101.2 0 77.7 0 34.9 19.1 34.9 42.7 54.1 85.4 77.7 85.4zM77.7 7.8c19.3 0 34.9 15.6 34.9 34.9S97 77.7 77.7 77.7 42.7 62 42.7 42.7C42.7 23.4 58.4 7.8 77.7 7.8z" />
					<path
						d="M135.6 96.8L135.6 96.8c-37.2-15-78.8-15-116 0l0 0C7.7 101.7 0 112 0 123.4V140h155.3v-16.6C155.3 112 147.6 101.7 135.6 96.8zM147.5 132.2H7.8v-8.9c0-8.1 5.8-15.7 14.8-19.3 35.3-14.2 74.8-14.2 110.2 0 9 3.6 14.8 11.2 14.8 19.3V132.2z" /></svg>

				<div class="separador--big"></div>
			</div>
			<div class="col xs12 lg10">
				<div class="pasos">
					<div class="row justify-center-xs justify-start-sm">
						<div class="col xs12 sm12 md8 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md activo">
								
								<div class="pasos__data">
									<span>APROBACIÓN CONTRATO DE USO DE CANALES REMOTOS</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="separador"></div>
				<p><b>Le solicitamos autenticarse y presionar el botón "Continuar" para descargar el contrato y autorizarlo.</b></p>
				<div class="separador"></div>
				<p>Ingresa tu RUT y número de serie de tu carnet de identidad.</p>
				<div class="separador"></div>
				<form class="form" id="paso1" action="validaCedula.do" method="POST">
					<div class="row">

						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Sin puntos ni guión. Ej: 12345678k">
								<input class="text requerido tipo_rut" type="text" id="rut" name="rut"
									value="${rutLdap}" maxlength="12" /> <label for="rut">RUT</label>
							</div>
							<c:if test='${errorMsg=="rut_sininfo" }'>
								<div class="alerta alerta--error">
									No existe información asociada al Rut, favor puedes llamar al Call Center de La ARAUCANA 600 4228 100
								</div>
							</c:if>
							<c:if test='${errorMsg=="serie_error" }'>
								<div class="alerta alerta--error">
									Rut o Número de serie ingresado no es válido, intenta nuevamente.
								</div>
							</c:if>
							<c:if test='${errorMsg=="operacion_error" }'>
								<div class="alerta alerta--error">
									Eror interno del servicio, favor intenta nuevamente o llama al Call Center de La ARAUCANA 600 4228 100
								</div>
							</c:if>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="" style="position: relative">
								<input class="text" type="hidden" id="serie1" name="serie1"
									value="" /> <label for="serie1"></label>


							</div>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Ej 123456XXX o A021065XXX"
								style="position: relative">
								<input class="text requerido" type="text" id="serie" name="serie"
									value="" /> <label for="serie">Número serie</label>
								<div class="ayuda ayuda--form">
									<span style="color: red">?</span>
									<div class="ayuda__container">
										<p>Acá puedes encontrar el número de serie de tu cédula de
											identidad.</p>
										<div class="separador--small"></div>
										<a href="img/numero-serie.png" target="_blank"><img
											src="img/numero-serie.png" alt="Número serie" /></a>
									</div>
								</div>

							</div>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario=""
								style="position: relative">
								 

							</div>
						</div>
					</div>

					 <div class="alerta alerta--error" id="errores_paso1" style="display:none"></div>
					<div class="btn__grupo text-align-right-xs">
						<a class="btn btn--primario" href="#"
							onClick="validarPaso1(); return false;">Continuar</a>
					</div>
					 
				</form>
				 	
			</div>

		</div>
	</div>

	 
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/polyfill.js"></script>
	<script src="assets/js/jquery-3.3.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="assets/js/funciones.js"></script>
	<script src="assets/js/jquery.Rut.min.js"></script>
	
	<script>
	$(document).ready(function() {
			$('#rut').keyup(function(){
        		$(this).val($(this).val().toUpperCase());
    		});
		});
	</script>

</body>

</html>