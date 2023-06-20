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
<title>Envío Digital de formularios ASFAM - La Araucana</title>
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
		<a href="<c:url value='/exit.do' />"  style="text-decoration:none"><img alt="Salir" src="img/salir.png"  title="Cerrar Sesión" /><span class="pasos__data" style="font-size:1.6rem;color:gray;">&nbsp;Salir</span></a>
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
						<div class="col xs12 sm12 md4 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md activo">
								<div class="pasos__icono">
									<svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60"
										height="60" viewBox="0 0 60 60">
										<rect class="svg__fondo" width="60" height="60" />
										<path
											d="M53.4 12.3H6.6c-2.3 0-4.1 1.8-4.1 4.1v27.2c0 1.1 0.4 2.1 1.2 2.9 0.8 0.8 1.8 1.2 2.9 1.2h46.8c2.3 0 4.1-1.8 4.1-4.1V16.4C57.5 14.2 55.7 12.3 53.4 12.3zM6.6 14.7h46.8c0.9 0 1.7 0.8 1.7 1.7v27.2c0 0.9-0.8 1.7-1.7 1.7H6.6c-0.9 0-1.7-0.8-1.7-1.7l0-27.2C4.9 15.5 5.6 14.7 6.6 14.7z" />
										<path
											d="M29.7 26h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 25.9 29.4 26 29.7 26z" />
										<path
											d="M29.7 31.2h20.7c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2H29.7c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 31 29.4 31.2 29.7 31.2z" />
										<path
											d="M29.7 36.3h8.9c0.6 0 1.2-0.5 1.2-1.2 0-0.6-0.5-1.2-1.2-1.2h-8.9c-0.6 0-1.2 0.5-1.2 1.2 0 0.3 0.1 0.6 0.3 0.8C29.1 36.2 29.4 36.3 29.7 36.3z" />
										<path
											d="M16.3 31.3c2.6 0 4.7-2.1 4.7-4.7 0-0.2 0-0.3 0-0.5l0 0c0-0.2-0.1-0.5-0.1-0.7 -0.1-0.2-0.1-0.4-0.2-0.7 -0.7-1.7-2.4-2.9-4.3-2.9 -1.9 0-3.6 1.1-4.3 2.9 -0.1 0.2-0.2 0.4-0.2 0.7 -0.1 0.2-0.1 0.5-0.1 0.7 0 0.2 0 0.3 0 0.5C11.6 29.2 13.7 31.3 16.3 31.3zM14.4 24.3c0.1-0.1 0.3-0.2 0.5-0.3 0.1 0 0.2-0.1 0.3-0.1 0.2-0.1 0.4-0.1 0.5-0.2 0.2 0 0.4-0.1 0.6-0.1 1.6 0 2.9 1.3 2.9 2.9 0 0.2 0 0.4-0.1 0.6 0 0.2-0.1 0.4-0.2 0.5 0 0.1-0.1 0.2-0.1 0.3h0c-0.1 0.2-0.2 0.3-0.3 0.5 -0.6 0.7-1.4 1.1-2.3 1.1 -0.2 0-0.4 0-0.6-0.1 -0.2 0-0.4-0.1-0.5-0.2 -0.1 0-0.2-0.1-0.3-0.1 -0.2-0.1-0.3-0.2-0.5-0.3 -0.1-0.1-0.3-0.3-0.4-0.4 -0.1-0.1-0.2-0.3-0.3-0.5 0-0.1-0.1-0.2-0.1-0.3 -0.1-0.2-0.1-0.4-0.2-0.5 0-0.2-0.1-0.4-0.1-0.6C13.4 25.7 13.7 24.9 14.4 24.3z" />
										<path
											d="M24.4 35.6c0-0.6-0.2-1.1-0.5-1.6 -0.1-0.2-0.3-0.4-0.5-0.6 -0.1-0.1-0.1-0.1-0.2-0.2 -0.2-0.2-0.4-0.3-0.6-0.4 -0.2-0.1-0.3-0.2-0.5-0.2 -3.8-1.5-8-1.5-11.8 0 -0.2 0.1-0.3 0.1-0.5 0.2 -0.2 0.1-0.4 0.3-0.6 0.4 -0.1 0.1-0.1 0.1-0.2 0.2 -0.2 0.2-0.3 0.4-0.5 0.6 -0.3 0.5-0.5 1.1-0.5 1.6v2.1h16.2V35.6zM11.1 34.2c0.6-0.2 1.3-0.5 1.9-0.6 0.6-0.2 1.3-0.3 2-0.3 0.2 0 0.4 0 0.7 0 1.1-0.1 2.2 0 3.3 0.2 0.9 0.2 1.7 0.4 2.6 0.8 0.1 0 0.2 0.1 0.3 0.1 0.1 0 0.2 0.1 0.2 0.1 0.1 0.1 0.1 0.1 0.2 0.2 0.1 0.1 0.3 0.3 0.3 0.5 0 0 0 0.1 0 0.1 0 0.1 0 0.1 0.1 0.2 0 0.1 0 0.1 0 0.2V36H9.9v-0.4c0-0.1 0-0.1 0-0.2 0-0.1 0-0.1 0.1-0.2 0 0 0-0.1 0-0.1 0.1-0.2 0.2-0.3 0.3-0.5 0.1-0.1 0.1-0.1 0.2-0.2 0.1-0.1 0.1-0.1 0.2-0.1C10.9 34.3 11 34.3 11.1 34.2z" /></svg>
								</div>
								<div class="pasos__data">
									<span>Paso 1</span>SELECCIONA OPCIÓN
								</div>
							</div>
						</div>
						<div class="col xs12 sm12 md4 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md">
								<div class="pasos__icono">
									<svg class="svg" xmlns="http://www.w3.org/2000/svg" width="60"
										height="60" viewBox="0 0 60 60">
										<rect class="svg__fondo" width="60" height="60" />
										<path
											d="M49.4 10.6C44.3 5.4 37.3 2.5 30 2.5c-7.3 0-14.3 2.9-19.4 8.1C5.4 15.7 2.5 22.7 2.5 30s2.9 14.3 8.1 19.4c5.2 5.2 12.1 8.1 19.4 8.1 7.3 0 14.3-2.9 19.4-8.1s8.1-12.1 8.1-19.4S54.6 15.7 49.4 10.6zM30 54.3C16.6 54.3 5.7 43.4 5.7 30S16.6 5.7 30 5.7 54.3 16.6 54.3 30 43.4 54.3 30 54.3z" />
										<path
											d="M40.9 21.2L26.6 35.4l-7.5-7.5c-0.6-0.6-1.6-0.6-2.3 0 -0.6 0.6-0.6 1.6 0 2.3l8.6 8.6c0.3 0.3 0.7 0.5 1.1 0.5 0.4 0 0.8-0.2 1.1-0.5l15.4-15.4c0.6-0.6 0.6-1.6 0-2.3C42.5 20.5 41.5 20.5 40.9 21.2z" /></svg>
								</div>
								<div class="pasos__data">
									<span>Paso 2</span>INGRESA TUS DATOS
								</div>
							</div>
						</div>
						<div class="col xs12 sm12 md4">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md">
								<div class="pasos__icono">
									<svg xmlns="http://www.w3.org/2000/svg" class="svg__fondo"
										width="55" height="60" viewBox="0 0 374.3 315">
										<defs>
										<rect width="374.3" height="315" /></defs>
										<clipPath>
										<use xlink:href="#SVGID_1_" /></clipPath>
										<path class="st0"
											d="M340.8 57h-73.5V30c0-16.5-13.4-30-30-30H137c-16.5 0-30 13.4-30 30v27H33.5C15 57 0 72 0 90.5v191C0 300 15 315 33.5 315h307.3c8.9 0 17.4-3.5 23.7-9.8 6.3-6.3 9.8-14.8 9.8-23.7v-191C374.3 72 359.3 57 340.8 57M245.3 30v27H129V30c0-4.4 3.6-8 8-8h100.3C241.7 22 245.3 25.6 245.3 30M352.4 90.5v25l-165.2 58.9L22 115.6V90.5c0-6.4 5.2-11.6 11.6-11.6h307.3C347.2 78.9 352.4 84.1 352.4 90.5M195.9 204.3h-17.4v-9.7l5.1 1.8c2.4 0.8 5 0.8 7.4 0l5-1.8V204.3zM22 138.9l134.5 47.8v28.5c0 6.1 4.9 11 11 11h39.4c6.1 0 11-4.9 11-11v-28.5l134.5-48v142.7c0 6.4-5.2 11.6-11.6 11.6H33.5c-6.4 0-11.6-5.2-11.6-11.6V138.9z" /></svg>
								</div>
								<div class="pasos__data">
									<span>Paso 3</span>ENVÍA TU DOCUMENTACIÓN
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="separador"></div>
				<p>Este servicio permitirá subir una solicitud de acreditación,
					renovación o extinción de asignación familiar, certificados y
					declaración jurada de ingresos de respaldo.</p>
				<div class="separador"></div>
				<form class="form" id="paso1" action="paso2.do" method="POST">
					<div class="row">

						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="">
								<input class="text requerido" type="text" id="rut" name="rut"
									value="${rut}" disabled="disabled"
									style="background-color: #DCDCDC" /> <label for="rut">RUT</label>
							</div>

						</div>

						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder">
								<input class="text requerido" type="text" id="nombre"
									name="nombre" value="${nombre}" disabled="disabled"
									style="background-color: #DCDCDC" /> <label for="nombre">Nombre</label>
							</div>
						</div>
						<div class="col xs12 lg6" style="margin-top: 20px">
						<p class="adicional adicional--dos bold" >Descargar Formularios:</p>
						<ul>
							<li><p class="adicional adicional--dos"
									style="padding-left: 3%;">
									-&nbsp;&nbsp;&nbsp;Solicitud de Asignación Familiar y Maternal 
										(en <a style="color: #2072BC"
										href="resources/pdf/Solicitud-de-Asignación-Familiar-y-Maternal.pdf"
										target="_blank">PDF</a>
										&nbsp;o&nbsp;<a style="color: #2072BC"
										href="resources/pdf/Solicitud-de-Asignación-Familiar-y-Maternal.docx"
										target="_blank">Word</a>)
								</p></li>
							<li><p class="adicional adicional--dos"
									style="padding-left: 3%;">
									-&nbsp;&nbsp;&nbsp;Solicitud de Extinción de Asignación Familiar 
										(en <a style="color: #2072BC"
										href="resources/pdf/Solicitud-de-Extinción-Asignación-Familiar.pdf"
										target="_blank">PDF</a>
										&nbsp;o&nbsp;<a style="color: #2072BC"
										href="resources/pdf/Solicitud-de-Extinsión-Asignación-Familiar-y-Maternal.docx"
										target="_blank">Word</a>)
								</p></li>
							<li><p class="adicional adicional--dos"
									style="padding-left: 3%;">
									-&nbsp;&nbsp;&nbsp;Declaración Jurada de Ingresos Para Actualización Asignación Familiar 
									(<a style="color: #2072BC"
										href="resources/pdf/Declaración-Jurada-de-Ingresos-Para-Actualización-Asignación-Familiar.pdf"
										target="_blank">descargar aquí</a>)
								</p></li>
							<li><p class="adicional adicional--dos"
									style="padding-left: 3%;">
									-&nbsp;&nbsp;&nbsp;Declaración Jurada Ingresos Nuevos Beneficiarios (<a style="color: #2072BC"
										href="resources/pdf/Declaración-Jurada-Ingresos-Nuevos-Beneficiarios.pdf"
										target="_blank">descargar aquí</a>)
								</p></li>
							<li><p class="adicional adicional--dos"
									style="padding-left: 3%; font-size: small;">
									<br>Debes imprimir, 
									completar con letra legible y adjuntarla como imagen o pdf.
								</p></li>
						</ul>

					</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="" style="position: relative">

								<p class="adicional adicional--dos">
									<br>Revisa que documentos debes presentar para actualizar cargas familiares 
									(<a style="color: #2072BC"
										href="resources/pdf/acreditacióndeASFAM-WEB.pdf"
										target="_blank">descarga aqui</a>)
								</p>

							</div>
						</div>
						
					</div>

					<div class="alerta alerta--error" id="errores_paso1"
						style="display: none"></div>
					<div class="btn__grupo text-align-right-xs">
						<a class="btn btn--primario" href="#"
							onClick="validarPaso1_2(); return false;" style="margin-top: 20px">Documentación
							pendiente</a>&nbsp;&nbsp;&nbsp;<a class="btn btn--primario" href="#"
							onClick="validarPaso1(); return false;">Ingresar nueva solicitud</a>
					</div>
					
				</form>
				<form class="form" id="documentacion" action="documentacion.do"
					method="POST"></form>
			</div>

		</div>
	</div>


	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script
		src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&amp;render=explicit"></script>
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