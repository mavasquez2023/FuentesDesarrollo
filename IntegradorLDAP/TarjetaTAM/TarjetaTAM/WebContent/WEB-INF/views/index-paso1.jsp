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
<title>Tarjeta Adulto Mayor - La Araucana</title>
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
			<div class="col xs12 lg3 text-align-center-xs">
			
				<jsp:include page="menuServices.jsp" flush="true" />

				<div class="separador--big"></div>
			</div>
			<div class="col xs12 lg9">
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
									<span>Paso 1</span>DATOS AFILIADO
								</div>
							</div>
						</div>
						<div class="col xs12 sm12 md4 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md">
								<div class="pasos__icono">
									<svg width="59.999940559276474" height="59.999940559276474" xmlns="http://www.w3.org/2000/svg" class="svg">

 										<g>
  											<title></title>
  											<rect height="402" width="582" y="-1" x="-1" fill="none" id="canvas_background"/>
 										</g>
 										<g>
  											<title></title>
  											<rect height="60" width="60" y="0.002493" x="-0.012496" id="svg_1" class="svg__fondo"/>
  											<path d="m53.400002,12.3l-46.800002,0c-2.3,0 -4.1,1.8 -4.1,4.099999l0,27.199999c0,1.100002 0.4,2.100002 1.2,2.900002c0.8,0.799999 1.8,1.200001 2.9,1.200001l46.800002,0c2.299999,0 4.099998,-1.799999 4.099998,-4.100002l0,-27.199999c0,-2.2 -1.799999,-4.099999 -4.099998,-4.099999zm-46.800002,2.4l46.800002,0c0.899998,0 1.699997,0.8 1.699997,1.7l0,27.199999c0,0.900002 -0.799999,1.700001 -1.699997,1.700001l-46.800002,0c-0.9,0 -1.7,-0.799999 -1.7,-1.700001l0,-27.199999c0,-0.9 0.7,-1.7 1.7,-1.7z" id="svg_2"/>
  											<path d="m29.700001,26l20.700001,0c0.599998,0 1.199997,-0.5 1.199997,-1.200001c0,-0.599998 -0.5,-1.199999 -1.199997,-1.199999l-20.700001,0c-0.6,0 -1.200001,0.5 -1.200001,1.199999c0,0.300001 0.1,0.6 0.299999,0.800001c0.300001,0.299999 0.6,0.4 0.900002,0.4z" id="svg_3"/>
  											<path d="m29.700001,36.949921l20.700001,0c0.599998,0 1.199997,-0.499996 1.199997,-1.199993c0,-0.599995 -0.5,-1.199993 -1.199997,-1.199993l-20.700001,0c-0.6,0 -1.200001,0.499996 -1.200001,1.199993c0,0.299995 0.1,0.599995 0.299999,0.799992c0.300001,0.200001 0.6,0.400002 0.900002,0.400002z" id="svg_4"/>
  											<path d="m16.299999,28.800018c2.6,0 4.700001,-2.099998 4.700001,-4.699999c0,-0.200001 0,-0.300001 0,-0.5l0,0c0,-0.200001 -0.1,-0.5 -0.1,-0.700001c-0.1,-0.199999 -0.1,-0.4 -0.199999,-0.699999c-0.700001,-1.700001 -2.400002,-2.900002 -4.300001,-2.900002c-1.9,0 -3.599999,1.1 -4.299999,2.900002c-0.1,0.199999 -0.200001,0.4 -0.200001,0.699999c-0.099999,0.200001 -0.099999,0.5 -0.099999,0.700001c0,0.199999 0,0.299999 0,0.5c-0.2,2.6 1.9,4.699999 4.499999,4.699999zm-1.9,-7c0.1,-0.099998 0.3,-0.199999 0.5,-0.299999c0.1,0 0.200001,-0.1 0.3,-0.1c0.2,-0.1 0.400001,-0.1 0.5,-0.199999c0.2,0 0.400001,-0.1 0.599999,-0.1c1.6,0 2.900002,1.299999 2.900002,2.9c0,0.200001 0,0.4 -0.1,0.6c0,0.199999 -0.1,0.4 -0.200001,0.5c0,0.1 -0.1,0.199999 -0.1,0.299999l0,0c-0.099998,0.200001 -0.199999,0.300001 -0.299999,0.5c-0.6,0.700001 -1.4,1.1 -2.299999,1.1c-0.200001,0 -0.400001,0 -0.6,-0.1c-0.200001,0 -0.400001,-0.1 -0.5,-0.199999c-0.1,0 -0.200001,-0.1 -0.3,-0.1c-0.2,-0.1 -0.3,-0.200001 -0.5,-0.300001c-0.1,-0.099998 -0.3,-0.299999 -0.400001,-0.4c-0.099999,-0.1 -0.2,-0.299999 -0.299999,-0.5c0,-0.1 -0.1,-0.199999 -0.1,-0.299999c-0.1,-0.200001 -0.1,-0.4 -0.2,-0.5c0,-0.200001 -0.1,-0.4 -0.1,-0.6c0.2,-0.799999 0.5,-1.6 1.2,-2.200001z" id="svg_6"/>
  											<path d="m16.299999,40.21999c2.6,0 4.700001,-2.099998 4.700001,-4.700001c0,-0.199997 0,-0.299999 0,-0.5l0,0c0,-0.199997 -0.1,-0.5 -0.1,-0.699997c-0.1,-0.200001 -0.1,-0.400002 -0.199999,-0.700001c-0.700001,-1.699999 -2.400002,-2.899998 -4.300001,-2.899998c-1.9,0 -3.599999,1.1 -4.299999,2.899998c-0.1,0.200001 -0.200001,0.399998 -0.200001,0.700001c-0.099999,0.199997 -0.099999,0.5 -0.099999,0.699997c0,0.200001 0,0.300003 0,0.5c-0.2,2.600002 1.9,4.700001 4.499999,4.700001zm-1.9,-7c0.1,-0.099998 0.3,-0.200001 0.5,-0.299999c0.1,0 0.200001,-0.099998 0.3,-0.099998c0.2,-0.100002 0.400001,-0.100002 0.5,-0.200001c0.2,0 0.400001,-0.100002 0.599999,-0.100002c1.6,0 2.900002,1.300003 2.900002,2.900002c0,0.200001 0,0.400002 -0.1,0.599998c0,0.200001 -0.1,0.400002 -0.200001,0.5c0,0.100002 -0.1,0.200001 -0.1,0.300003l0,0c-0.099998,0.199997 -0.199999,0.299999 -0.299999,0.5c-0.6,0.699997 -1.4,1.099998 -2.299999,1.099998c-0.200001,0 -0.400001,0 -0.6,-0.099998c-0.200001,0 -0.400001,-0.100002 -0.5,-0.200001c-0.1,0 -0.200001,-0.100002 -0.3,-0.100002c-0.2,-0.099998 -0.3,-0.199997 -0.5,-0.299999c-0.1,-0.099998 -0.3,-0.299999 -0.400001,-0.399998c-0.099999,-0.100002 -0.2,-0.300003 -0.299999,-0.5c0,-0.100002 -0.1,-0.200001 -0.1,-0.300003c-0.1,-0.199997 -0.1,-0.399998 -0.2,-0.5c0,-0.199997 -0.1,-0.399998 -0.1,-0.599998c0.2,-0.799999 0.5,-1.599998 1.2,-2.200001z" id="svg_8"/>
 										</g>
									</svg>
								</div>
								<div class="pasos__data">
									<span>Paso 2</span>DESPACHO TARJETA
								</div>
							</div>
						</div>
						<div class="col xs12 sm12 md4">
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
									<span>Paso 3</span>CONFIRMACIÓN DATOS
								</div>
							</div>
						</div>
					</div>
				</div>

				<p class="alerta alerta--info"><b>REGISTRAR SOLICITUD TARJETA</b></p>
				<form class="form" id="paso1" action="paso1.do" method="POST">
					<div class="row">
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="">
								<input class="text requerido" type="text" id="rut" name="rut"
									value="" /> <label for="rut">RUT</label>
							</div>

						</div>

						<div class="btn__grupo text-align-left-xs">
							<a id="botonIL" style="margin-left: 20px; margin-top: 15px"
								class="btn btn--primario btn--alto" href="#"
								onClick="validarPaso1(''); return false;">Buscar</a>
						</div>


						<div class="separador"></div>

						<div class="col xs12 lg12">
							<div class="alerta alerta--error" id="errores_paso1"
								style="display: none"></div>
						</div>
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