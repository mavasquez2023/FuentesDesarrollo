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
<title>Autorizaci�n de Diferimiento Especial - La Araucana</title>
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
			<a href="<c:url value='/exit.do' />" style="text-decoration:none"><img alt="Salir" src="img/salir.png"  title="Cerrar Sesi�n" /><span class="pasos__data" style="font-size:1.6rem;color:gray;">&nbsp;Salir</span></a>
		</div>
		<div class="row">
			<div class="col xs12 lg2 text-align-center-xs">
				<svg width="170.00003423920123" height="150.0000007999813" xmlns="http://www.w3.org/2000/svg">
 <!-- Created with Method Draw - http://github.com/duopixel/Method-Draw/ -->

 <g>
  <title>background</title>
 </g>
 <g>
  <title>Layer 1</title>
  <text xml:space="preserve" y="127.13" x="117.24" transform="matrix(4.15431 0 0 2.93442 -411.921 -256.368)" text-anchor="start" stroke-width="0" stroke="#3c75ad" fill="#000000" font-size="24" font-family="Helvetica, Arial, sans-serif" id="svg_13"/>
  <line y2="146.782" x2="8.52326" y1="3.88919" x1="8.25826" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_1"/>
  <line y2="7.31163" x2="129.309" y1="7.04663" x1="4.31081" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_2"/>
  <line y2="148.361" x2="127.466" y1="5.20916" x1="126.941" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_3"/>
  <line y2="143.887" x2="4.57331" y1="144.677" x1="131.414" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_4"/>
  <line y2="33.0987" x2="103.521" y1="4.67668" x1="103.259" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_5"/>
  <line y2="31.2588" x2="129.042" y1="31.2588" x1="99.5715" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_6"/>
  <line y2="29.9413" x2="126.152" y1="6.78664" x1="104.834" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_7"/>
  <line y2="34.4137" x2="89.5719" y1="34.4137" x1="35.3603" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_9"/>
  <line y2="49.1534" x2="89.0444" y1="49.1534" x1="35.0953" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_10"/>
  <line y2="64.1532" x2="86.4119" y1="64.1532" x1="34.5728" stroke-width="8" stroke="#4c4c4c" fill="none" id="svg_21"/>
  <ellipse ry="36.9806" rx="38.4241" cy="96.3241" cx="68.2963" stroke-width="8" stroke="#3c75ad" fill="#ffffff" id="svg_8"/>
  <text xml:space="preserve" y="113.9829" x="96.1197" transform="matrix(2.83478 0 0 2.40614 -215.54 -166.74)" text-anchor="start" stroke="#007f00" fill="#000000" font-size="16" font-family="Helvetica, Arial, sans-serif" id="svg_14">$</text>
  <line y2="85.6971" x2="120.824" y1="100.702" x1="105.818" stroke-width="8" stroke="#3c75ad" fill="none" id="svg_12"/>
  <line y2="101.866" x2="108.056" y1="86.7745" x1="90.9622" stroke-width="8" stroke="#3c75ad" fill="none" id="svg_11"/>
  <line y2="34.9388" x2="141.416" y1="-0.585735" x1="108.261" stroke-width="10" stroke="#ffffff" fill="#ffffff" id="svg_17"/>
  <line y2="21.7789" x2="141.153" y1="-3.48319" x1="119.311" stroke-width="10" stroke="#ffffff" fill="none" id="svg_19"/>
  <rect height="8.90268" width="8.42486" y="30.5109" x="19.4406" stroke-width="3" stroke="#4c4c4c" fill="none" id="svg_23"/>
  <rect height="8.62001" width="8.15737" y="45.2684" x="19" stroke-width="3" stroke="#4c4c4c" fill="none" id="svg_24"/>
  <rect height="8.90004" width="8.42236" y="59.7231" x="19" stroke-width="3" stroke="#4c4c4c" fill="none" id="svg_25"/>
 </g>
</svg>
				

				<div class="separador--big"></div>
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
									<span>Paso 1</span>INGRESA TU RUT
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
									<span>Paso 2</span>VALIDACI�N
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
									<span>Paso 3</span>AUTORIZACI�N
								</div>
							</div>
						</div>
						<div class="separador"></div>
						
					</div>
				</div>
				<div class="container">
					<div class="separador"></div>
					<p>Para validar tu identidad, ingresa tu celular y el c�digo de verificaci�n</p>
					<div class="separador"></div>
				

				<form class="form" id="paso2" action="paso3.do" method="POST">
					<div class="row">
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="">
								<input class="text requerido" type="text" id="rut" name="rut"
									value="${data.rut}" disabled="disabled"
									style="background-color: #DCDCDC" /> <label for="rut">RUT</label>
							</div>

						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder">
								<input class="text requerido" type="text" id="nombre"
									name="nombre" value="${data.nombre}" disabled="disabled"
									style="background-color: #DCDCDC" /> 
									<label for="nombre">Nombre</label>
							</div>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Ej: 912345678 (Ingresa n�mero de 9 d�gitos)">
								<input class="text requerido tipo_fono tipo_numerico"
									type="text" id="celular" name="celular" data-prefijo=""
									data-prefijo-tipo="telefono" value="${data.celular}" maxlength="9"/> 
									<label for="celular">Celular</label>
							</div>
						</div>
						<div class="col xs12 lg2">
							<div class="form__grupo">
								<a class="btn btn--primario" href="#"
								onClick="sendSMS(); return false;">Enviar SMS</a> 
									<label for="enviar">&nbsp;</label>
							</div>
						</div>
						<div class="col xs12 lg8">
							<div class="alerta alerta--exito"  id="aviso" style="width: 74%; margin-top: 10px; margin-bottom: 0px; display: none;"></div>
							<div class="alerta alerta--error"  id="aviso2" style="width: 74%; margin-top: 10px; margin-bottom: 0px; display: none;"></div>
						</div>	
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="C�digo de 6 d�gitos">
								<input class="text requerido tipo_fono tipo_numerico"
									type="text" id="codigosms" name="codigosms" data-prefijo=""
									data-prefijo-tipo="" value="" maxlength="6	"/> 
									<label for="codigosms">C�digo de verificaci�n v�a SMS</label>
							</div>
						</div>
					</div>
					<div class="alerta alerta--error" id="errores_paso2"
						style="display: none"></div>
					<div class="btn__grupo text-align-right-xs">
						<a class="btn btn--primario" href="#"
							onClick="validarPaso2(); return false;">Continuar</a>
					</div>
					
					<input class="text" type="hidden" id="lista_prefijos_telefonos" name="" value="${prefijostel }" /> 
					<input class="text" type="hidden" id="lista_prefijos_celulares" name="" value="${prefijoscel}" />
				</form>

				</div>
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
	
<script type="text/javascript">
function sendSMS() {
  $.ajax({
  	method: 'POST',
    url: 'enviarSMS.do',
    data: {celular: $('#celular').val()},
    beforeSend: function beforeSend() {
       fln.preloader(1);
    },
    
     success: function success(data) {
     	 fln.preloader(0);
         if (data == 'exito') {
        	  	avisar('<b><h5>Mensaje SMS enviado, revise su celular</h5></b>'
					, 5000);
         } else {
            	avisar2('<b><h5>Error al enviar SMS, intente nuevamente'
				, 5000);
         }
         
     },
    error: function() {
      alert=("ERROR");
    }
  });
}
	<c:if test='${error!=""}'>
		avisar2('<b><h5>Error c�digo verificaci�n no valido'
				, 5000);
	</c:if>
	
	</script>
</body>
</html>