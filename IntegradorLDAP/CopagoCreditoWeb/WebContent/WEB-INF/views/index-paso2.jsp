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
<title>Copago - La Araucana</title>
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
								<b><h5>TOMA DE CONOCIMIENTO DEL PROGRAMA DE AYUDA DE COPAGO</h5></b>
							</p>
						</div>
					<form id="paso2" action="aprobacion.do" method="post">
						
								<c:if test="${existe == 'NO'}">
								<div class="col xs12 lg8">
									<div class="alerta alerta--info">
									Acepto o rechazo reembolso de cuota de crédito social de
											acuerdo a las condiciones expresadas a continuación.<br>
										<div class="btn__grupo text-align-center-xs">
											
											<br> <a class="btn btn--primario" href="#"
												onClick="validarPaso2_3(); return false;">Rechazo</a>&nbsp;&nbsp;
											<a class="btn btn--primario" href="#"
												onClick="validarPaso2(); return false;">Toma
												Conocimiento</a>

										</div>
									</div>
								</div>
								</c:if>
								<c:if test="${existe == 'SI'}">
								<div class="col xs12 lg12">
									<div class="separador"></div>
									<div class="btn__grupo text-align-center-xs">

										<a class="btn btn--primario" href="#"
											onClick="validarPaso2_2(); return false;">Descargar
											aprobación</a>

									</div>
								</div>
								</c:if>
							
							<div class="col xs12 lg8">
								<p>Ingresa tu correo:</p>

							</div>
							<div class="col xs12 lg8">
								<div class="form__grupo" data-animacion="placeholder"
									data-comentario="Ej: nombre@correo.cl">
									<input class="text requerido tipo_email" type="text" id="email"
										name="correo" value="" /> <label for="email">Email</label>
								</div>
								<div class="alerta alerta--error" id="errores_paso2"
								style="display: none"></div>
							</div>
							

				</form>		

						<c:if test="${mandato==false && existe == 'SI'}">
							
						<div class="col xs12 lg8" id="aviso_mandato">
							<div class="alerta alerta--info" id="aviso">
								<b><h3>Solicita transferencia electrónica:</h3></b>'
								<ul><li><img src="img/check.png" style="width:18px;height:18px;" />&nbsp;Recibe el beneficio de tu Copago en tu cuenta rut o cuenta corriente</li>
								<li><img src="img/check.png" style="width:18px;height:18px;" />&nbsp;Fácil y rápido</li>
								</ul>
								<p align="center">
								<a id="botonMD" style="margin-left: 20px" class="btn btn--terciario" href="<c:url value="/mandatoCopago.do" />" onClick="javascript:fln.preloader(1);">Solicitar</a>
								&nbsp;&nbsp;<a id="botonCLS" style="margin-left: 20px" class="btn btn--terciario" href="#">Cerrar</a>
								</p>
							</div>
						</div>
					</c:if>
						<div class="separador--big"></div>
						<div class="col xs12 lg8">
							<p style="text-align: justify;">

								El afiliado señor(a) ${nombre} , cédula de identidad N° ${rut},
								por este medio toma conocimiento que reúne los siguientes
								requisitos para optar al beneficio financiero incluido en el <i>"Programa
									de ayuda para trabajadores vulnerables con créditos vigentes en
									La Araucana", </i>el que consiste en el copago de tres meses de
								cuota de su crédito vigente. El financiamiento se le entregará
								de forma mensual dentro de los primeros 5 días hábiles del mes
								siguiente de la recepción efectiva de la cuota del crédito
								social y durante tres meses por un monto equivalente al 50% del
								valor cuota, con tope de $30.000 por evento, conforme lo dispone
								el Programa de Prestaciones Adicionales de La Araucana C.C.A.F.
								para el año 2020.<br> <br>Requisitos para acceder al
								beneficio: <br>
							</p>
							<table style="width: 100%; border: 0em;">
								<tr>
									<td style="text-align: left; width: 5%; border: 0"><p>1.</p></td>
									<td style="text-align: left; border: 0"><p>Ser
											afiliado a la CCAF La Araucana.</p></td>
								</tr>

								<tr>
									<td style="text-align: left; vertical-align: top; border: 0"><p>2.</p></td>
									<td style="text-align: left; border: 0">
										<p>Mantener un crédito social vigente al día  con la CCAF
											La Araucana;</p>
									</td>
								</tr>
								<tr>
									<td style="text-align: left; vertical-align: top; border: 0"><p>3.</p></td>
									<td style="text-align: left; border: 0"><p
											style="text-align: justify;">Tener una renta imponible
											hasta los $400.000 o trabajadores con una renta imponible
											hasta $700.000 que hayan tenido una disminución igual o
											superior al 20% de su renta.</p></td>
								</tr>
								<tr>
									<td style="text-align: left; vertical-align: top;; border: 0"><p>4.</p></td>
									<td style="text-align: left; border: 0"><p
											style="text-align: justify;">La evaluación de la
											disminución de la renta será la correspondiente a los meses
											de julio, agosto, septiembre del 2019 o bien, en caso de no
											poder acreditar renta en ese periodo, serán analizados los
											meses de enero, febrero y marzo del 2020.</p></td>
								</tr>
								<tr>
									<td style="text-align: left; vertical-align: top; border: 0"><p>5.</p></td>
									<td style="text-align: left; border: 0"><p
											style="text-align: justify;">Los trabajadores que
											soliciten el beneficio no podrán estar acogidos a la ley de
											protección del empleo.</p></td>
								</tr>
							</table>
							<p style="text-align: justify;">
								Si alguna de estas exigencias pierde su vigencia, el beneficio
								igualmente lo perderá.<br>
			
							</p>

						</div>


					</div>

				
				<div class="row">

					

						<br />

						<div class="col xs12 lg8">
						<br />
							<p style="text-align: justify;">
							La (s) cuota (s) afectas a este beneficio, son las que se
								detallan a continuación:
							</p>
							<table style="margin-left: 15%" class="tabla-creditos">
								<thead>
									<tr>

										<th class="certificadoLeft">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;N°&nbsp;de&nbsp;Crédito&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
										<th class="certificadoLeft">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Valor&nbsp;Cuota&nbsp;($)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
										<th class="certificadoLeft">&nbsp;&nbsp;Monto&nbsp;del&nbsp;Beneficio&nbsp;($)&nbsp;&nbsp;</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${credito}" var="cre">
										<tr>
											<td class="certificadoCenter">${cre.nCredito}</td>
											<td class="certificadoRight">${cre.valorCuota}</td>
											<td class="certificadoRight">${cre.montoBeneficio}</td>

										</tr>

									</c:forEach>


								</tbody>

							</table>
						</div>
						<div class="col xs12 lg8">
							<p>&nbsp;</p>
							<p style="text-align: justify;">En
								consecuencia, el afiliado antes individualizado declara que
								conoce inequívocamente los términos, condiciones y efectos que
								produce este beneficio, especialmente aquello que dice relación
								con los requisitos para ser beneficiario del mismo y los casos
								en que se pierde dicha calidad, y además que La Araucana
								C.C.A.F. ha dado íntegro cumplimiento a las normas que establece
								la Ley N° 19.496, sobre protección a los derechos de los
								consumidores.</p>
							<br />
							<br />
							
						</div>

				</div>

				
				<form id="paso4" action="procesado.do" method="post">
					<input type="hidden" name="emailDescarga" id="emailDescarga" />
				</form>
				<form id="paso3" action="rechazo.do" method="post">
					<input type="hidden" id="correo" name="emailRechazo">
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
    		$('#botonCLS').click(function(){
        		$('#aviso_mandato').slideUp	();
    		});
		});
	</script>
</body>
</html>