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
<title>Mandato Cesantía - La Araucana</title>
<link rel="stylesheet" href="assets/css/fln.css" />
<link rel="stylesheet" href="fonts/fln-icons.css" />
<link rel="stylesheet" href="assets/css/certificado.css" />
<link rel="stylesheet" href="assets/css/estilos.css" />
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css">
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
				<img alt="Copago Crédito" src="img/ayuda.png" width="155px" height="170px">

			</div>
			<div class="col xs12 lg10">
				<div class="pasos">
					<div class="row justify-center-xs justify-start-sm">
						<div class="col xs12 sm12 md4 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md">
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
									<span></span>INGRESA TU RUT
								</div>
							</div>
						</div>
						<div class="col xs12 sm12 md4 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md activo">
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
									<span>Paso 2</span>AUTORIZACIÓN
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="separador"></div>
						<div class="col xs12 lg10">
							<p>
								<b><h5>
										MANDATO ÚNICO PARA PRESENTAR DENUNCIOS DE SINIESTROS<br>
										FRENTE A SEGUROS GENERALES SURAMERICANA S.A.
									</h5>
								</b>
							</p>
						</div>
						<form id="paso2" action="aprobacion.do" method="post">

							
								<div class="col xs12">
								<div class="col xs12 lg8">
									<p style="margin-bottom: 10px">
										<b>Datos del Mandante</b>
									</p>

								</div>
								<div class="col xs12 lg6">
									<div class="form__grupo" data-animacion="placeholder">
										<input class="text requerido" type="text" id="nombre"
											name="nombre" value="${nombre}" disabled="disabled"
											style="background-color: #DCDCDC" /> <label for="nombre">Nombre</label>
									</div>
								</div>
								<div class="col xs12 lg6">
									<div class="form__grupo" data-animacion="placeholder"
										data-comentario="">
										<input class="text requerido" type="text" id="rutAfiliado"
											name="rutAfiliado" value="${rut}" disabled="disabled"
											style="background-color: #DCDCDC" /> <label for="rut">Rut</label>
									</div>

								</div>
								</div>
							<c:if test="${existe == 'NO'}">
								<div class="col xs12">
								<div class="grupo-emails">
									<div class="col xs12 lg6" style="margin-top: -10px">
										<div class="form__grupo" data-animacion="placeholder"
											data-comentario="Ej: nombre@correo.cl">
											<input class="text requerido tipo_email" type="text"
												id="email" name="email" value="${data.email}" maxlength="50" />
											<label for="email">Email</label>
										</div>
									</div>
									<div class="col xs12 lg6" style="margin-top: -10px">
										<div class="form__grupo" data-animacion="placeholder"
											data-comentario="Ej: nombre@correo.cl">
											<input class="text requerido tipo_email" type="text"
												id="confirma-email" name="confirma-email"
												value="" maxlength="50" autocomplete="off"/> <label
												for="confirma-email">Confirma Email</label>
										</div>
									</div>
								</div>

								<div class="grupo-celulares">
								<div class="col xs12 lg6">
									<div class="form__grupo" data-animacion="placeholder"
										data-comentario="Ej: +56912345678">
										<input class="text requerido tipo_fono tipo_numerico"
											type="text" id="celular" name="celular" data-prefijo="+56"
											data-prefijo-tipo="telefono" value="${data.celular}" maxlength="12" /> <label
											for="celular">Teléfono Celular</label>
									</div>
								</div>
								<div class="col xs12 lg6">
									<div class="form__grupo" data-animacion="placeholder"
										data-comentario="Ej: +56912345678">
										<input class="text requerido tipo_fono tipo_numerico"
											type="text" id="confirma-celular" name="confirma-celular" data-prefijo="+56"
											data-prefijo-tipo="telefono" value="" maxlength="12" autocomplete="off"/> <label
											for="confirma-celular">Confirmar Celular</label>
									</div>
								</div>
								</div>
								<div class="col xs12 lg6">
									<div class="form__grupo" data-animacion="placeholder"
										data-comentario="Ej: +56223456789">
										<input class="text tipo_fono tipo_numerico" type="text"
											id="telefono" name="telefono" data-prefijo="+56"
											data-prefijo-tipo="telefono" value="${data.telefono}" maxlength="12"/> <label
											for="telefono">Teléfono Fijo (Opcional)</label>
									</div>
								</div>
								</div>
								<div class="col xs12">
								<div class="col xs12 lg12">
									<div class="alerta alerta--info"
										style="margin-top: 5px; margin-bottom: 5px; padding: 0px">
										<div class="btn__grupo text-align-center-xs">

											<br> <a class="btn btn--primario" href="#"
												onClick="validarPaso2_3(); return false;">Rechazo</a>&nbsp;&nbsp;
											<a class="btn btn--primario" href="#"
												onClick="validarPaso2(); return false;">Autorizo</a>

										</div>
									</div>
								</div>
								</div>
							</c:if>
							<c:if test="${existe == 'SI'}">
								<div class="col xs12 lg12">
									<div class="separador"></div>
									<div class="btn__grupo text-align-center-xs">

										<a class="btn btn--primario" href="#"
											onClick="validarPaso2_2(); return false;" style="margin-top: -20px;margin-bottom: -5px">Descargar
											aprobación</a>

									</div>
								</div>
								<div class="col xs12">
								<div class="col xs12 lg6">
								<p>Ingresa tu correo:</p>
									<div class="form__grupo" data-animacion="placeholder"
										data-comentario="Ej: nombre@correo.cl">
										<input class="text requerido tipo_email" type="text"
											id="email" name="correo" value="" /> <label for="email">Email</label>
									</div>

								</div>
								</div>
							</c:if>
							<div class="col xs12 lg12">
									<div class="alerta alerta--error" id="errores_paso2"
										style="display: none"></div>
							</div>
						</form>
					</div>


					<div class="row">

				<div class="col xs12">
						<div class="col xs12 lg10">
							<p>&nbsp;</p>
							<p style="text-align: justify;">
								Por el presente instrumento, el "Mandante", ${nombre } Rut ${rut }, confiere e instruye a Caja de Compensación de Asignación Familiar La Araucana, en adelante también "La Araucana", mandato para presentar en su nombre y representación el denuncio de siniestro necesario para hacer efectiva la cobertura de su seguro de cesantía, en caso de tenerlo contratado y vigente en conjunto con su crédito social, con SEGUROS GENERALES SURAMERICANA S.A., en adelante "SURA".</p>
							<br />
							<p style="text-align: justify;">
								La Caja de Compensación de Asignación Familiar La Araucana, previa autorización expresa del Mandante, la cual será registrada a través de correo electrónico, autorización vía web o grabación telefónica, dejando así constancia fehaciente de su identidad y registro del consentimiento, conforme a lo aprobado por la Superintendencia de Seguridad Social en su Oficio Ordinario N° 1478 de 24 de abril de 2020, podrá hacer todas las gestiones necesarias con el mandante, o frente a su empleador y/o ex empleador, para obtener todos los antecedentes requeridos por la póliza y certificado de cobertura, y presentar el respectivo denuncio de siniestro a SURA y/o a la compañía corredora de seguros que intermedie la operación, para así dar pleno cumplimiento al objeto encargado.</p>
							<br />
							<p style="text-align: justify;">
								El presente mandato surtirá sus efectos una vez suscrito por el afiliado. El Mandante declara en este acto que será el exclusivo responsable de informar a La Araucana, en tiempo y forma, cualquier actualización o modificación que requiera el presente instrumento, liberando a La Araucana de cualquier responsabilidad en el caso eventual de surgir algún perjuicio para el Mandante por este concepto.</p>
							<br />
							<p style="text-align: justify;">
								El Mandante declara que el correo electrónico señalado en el presente documento será el mecanismo idóneo por el que La Araucana deberá notificar las comunicaciones relacionadas con el objeto del mandato y enviar cualquier otra información y antecedentes relativos a la operación que el mandato resguarda, por lo que será de su exclusiva responsabilidad informar todo cambio de éste; liberando a La Araucana de cualquier responsabilidad por la falta de recepción de una o más de cualquiera de dichas comunicaciones con la información y antecedentes remitidos , las que se entienden por notificadas una vez enviadas desde la casilla de correo electrónico de La Araucana. Además, el Mandante declara que los datos consignados son expresión fiel de la realidad, no encontrándose La Araucana obligada a verificar ni rectificar la información entregada por el Mandante.</p>
							<br />
							<p style="text-align: justify;">
								Las partes dejan expresa constancia que el presente mandato terminará por cualquiera de las causales que establece el artículo 2.163 del Código Civil, incluyendo la revocación expresa del Mandante en su portal de servicios en línea, alojado en el Sitio Web de La Araucana (www.laaraucana.cl). Además, el Mandante podrá también revocar este mandato presencialmente en cualquiera de las sucursales de La Araucana, mediante la suscripción del formulario físico dispuesto por esta última para tales efectos.</p>
							<br />
						</div>
				</div>
				</div>

				
				<form id="paso4" action="procesado.do" method="post">
					<input type="hidden" name="emailDescarga" id="emailDescarga" />
				</form>

			</div>
		</div>
	</div>
	</div>
	

	<script
		src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&amp;render=explicit"></script>
	<script src="assets/js/polyfill.js"></script>
	<script src="assets/js/jquery-3.3.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="assets/js/funciones.js"></script>
	
	<script>
	$(document).ready(function() {
			$('#rut').keyup(function(){
        		$(this).val($(this).val().toUpperCase());
    		});
    		
    		$('#email').bind("cut copy paste",function(e) { 
    				e.preventDefault(); 
    		});
    		$('#confirmar-email').bind("cut copy paste",function(e) { 
    				e.preventDefault(); 
    		});
    		$('#celular').bind("cut copy paste",function(e) { 
    				e.preventDefault(); 
    		});
    		$('#confirmar-celular').bind("cut copy paste",function(e) { 
    				e.preventDefault(); 
    		});   

    		
		});
	</script>
</body>
</html>