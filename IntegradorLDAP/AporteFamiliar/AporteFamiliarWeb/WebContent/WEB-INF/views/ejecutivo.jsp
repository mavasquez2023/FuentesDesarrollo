<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="iso-8559-1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, viewport-fit=cover" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta name="format-detection" content="telephone=no" />
<title>Aporte Familiar - La Araucana</title>
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
		<div class="row">
			<jsp:include page="banner.jsp" />
			<div class="col xs12 md8 lg10">
				<div class="row justify-center-xs" style="margin-top: -40px">
					<div class="col xs12 lg8">
						<div class="respuesta text-align-center-xs">
							<p class="respuesta__titulo color--secundario">
								Certificado Aporte Familiar Permanente ${year }
							</p>

						</div>
					</div>
				</div>
				<form class="form" id="paso2" action="buscarAfiliado.do" method="POST" style="margin-top: -30px">
					<div class="row justify-center-xs" >
						<div class="col xs12 lg4">
							
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Sin puntos ni guión. Ej: 12345678K">
								<input class="text requerido tipo_rut" type="text" id="rut"
									name="rut" value="" maxlength="12" /> <label for="rut">RUT AFILIADO</label>
							</div>
							
						</div>
						<div class="col xs12 lg2">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="">
									
								<a id="buscar"
									class="btn btn--secundario" href="#" onclick="validarPaso2();">Buscar</a><label for="buscar">&nbsp;</label>
							</div>
						</div>
						
					
					</div>
					<div class="row justify-center-xs" >
						<div class="col xs12 lg6">
							<div class="alerta alerta--error" id="errores_paso2"
								style="display: none"></div>
						</div>
					</div>
					
				</form>
				<form class="form" id="paso1" action="descargar.do" method="POST" style="margin-top: -20px">
					<c:if test="${data!=null }">
						<div id="campos">
							<div class="row justify-center-xs" id="descarga">
								<div class="col xs12 lg4">
								<p>Afiliado: ${data.nombreAfiliado }</p>
									<div class="form__grupo" data-animacion="placeholder"
										data-comentario="Descarga en formato PDF">
										<a id="descargar" class="btn btn--secundario" href="#"
											onclick="validarPaso1('descargar');">Descargar</a> <br>
									</div>
								</div>
							</div>

							<div class="row justify-center-xs" style="margin-top: 20px">
								<div class="col xs12 lg4">
									<p>O si prefiere envía el certificado a su correo:</p>
									<div class="form__grupo" data-animacion="placeholder"
										data-comentario="ejp: nombre.apellido@gmail.com"
										style="margin-top: 10px">
										<input class="text requerido tipo_email" type="text"
											id="email" name="email" value="" /> <label for="email">Email</label>

									</div>
									<div class="form__grupo" style="margin-top: 20px">

										<a id="enviar" class="btn btn--secundario" href="#"
											onclick="validarPaso1('email');">Enviar</a>
									</div>
								</div>
							</div>



						</div>

					</c:if>

					<div class="row justify-center-xs" >
					<div class="col xs12 lg6">
						<div class="alerta alerta--error" id="errores_paso1"
						style="display: none"></div>
					</div>
					<div class="alerta alerta--error" id="aviso" style="display: none"></div>
				</div>
				<div class="row justify-center-xs" style="margin-top: 20px;">
						<div class="col xs12 lg6">
					
							<div class="btn__grupo text-align-center-xs">
								<a id="continuar"
									class="btn btn--primario" href="<c:url value='/exit.do'/>" >Finalizar</a>
								<br>
							</div>
						</div>
					</div>
					<input type="hidden" name="opcion" id="opcion" value="" />
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
			$('#rut').keyup(function() {
				$(this).val($(this).val().toUpperCase());
			});
		});
	</script>
	<script>
		<c:if test='${errorMsg=="rut_error" }'>
		avisar('El RUT ingresado no presenta el beneficio, se debe consultar en www.aportefamiliar.cl, ingresando run y fecha de nacimiento', 8000);
		</c:if>

	</script>

</body>

</html>