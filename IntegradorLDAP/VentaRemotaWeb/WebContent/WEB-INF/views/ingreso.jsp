<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, viewport-fit=cover" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta name="format-detection" content="telephone=no" />
<title>Venta remota - La Araucana</title>
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
		<div align="right">&nbsp;</div>
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


				<div class="separador"></div>
				<p style="color: #5C5BFF">
					<b>APROBACIÓN CRÉDITO SOCIAL</b>
				</p>
				<br />
				<p>Seleccione cómo prefiere ingresar y presione el botón
					"Continuar":</p>
				<div class="separador"></div>
				<form class="form" id="paso0" action="init.do" method="GET">
					<div class="row">
						<div class="col xs12 lg8">

							<div class="form">
								<p class="adicional--dos" style="font-size:large;">
									<input type="radio" id="opcion1" name="opcion" value="0"	/>
									&nbsp;&nbsp;&nbsp;&nbsp;Con su Clave de la Araucana.
								</p>
							</div>

						</div>
						<div class="col xs12 lg8">

							<div class="form">
								<p class="adicional--dos" style="font-size:large;">
									<input type="radio" id="opcion2" name="opcion" value="1" />
									&nbsp;&nbsp;&nbsp;&nbsp;Con su Cédula de Identidad.
								</p>
							</div>

						</div>



						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="" style="position: relative"></div>
						</div>
					</div>
					<!--  <div class="captcha" id="captcha"></div>-->
					<div class="alerta alerta--error" id="errores_paso1"
						style="display: none"></div>
					<div class="btn__grupo text-align-center-xs">
						<a class="btn btn--primario" href="#"
							onClick="validarPaso0(); return false;">Continuar</a>
					</div>

				</form>

			</div>

		</div>
	</div>


	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display: none"></div>

	<script src="assets/js/polyfill.js"></script>
	<script src="assets/js/jquery-3.3.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="assets/js/funciones.js"></script>
	<script src="assets/js/jquery.Rut.min.js"></script>

	<script>
		$(document).ready(function() {
			$('#rut').keyup(function() {
				$(this).val($(this).val().toUpperCase());
			});
		});
	</script>

</body>

</html>