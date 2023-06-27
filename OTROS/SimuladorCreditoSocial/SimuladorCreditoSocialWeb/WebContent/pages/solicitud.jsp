<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html:html>
<head>
    <title><bean:message key="welcome.title"/></title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>La Araucana - Simulador de crédito social</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">

	<link rel="stylesheet" href="css/fln-icons.css">
	<link rel="stylesheet" href="css/fln.css">
	<link rel="stylesheet" href="css/estilos.css">

	<script>
		WebFontConfig = {
			google: {
				families: ['Open Sans:300,400,600,700']
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
	<div class="container-fluid">
		<div class="respuesta">
			<div class="respuesta__item respuesta__item--exito xs-text-center" id="respuesta-exito" style="display:none">
				<div class="respuesta__header">
					<span class="respuesta__icono fln-alerta-exito"></span>
				</div>
				<div class="respuesta__contenido">
					<h3 class="respuesta__titulo">Solicitud enviada exitósamente</h3>
					<p class="respuesta__parrafo">Estimado Cliente su solicitud quedó registrada y será próximamente contactado</p>
					<div class="separador"></div>
					<a href="#" onclick="window.location.reload(); return false;" class="btn btn--secundario btn--borde">Volver a
						simular</a>
				</div>
			</div>
			<div class="respuesta__item respuesta__item--error xs-text-center" id="respuesta-error" style="display:none">
				<div class="respuesta__header">
					<span class="respuesta__icono fln-alerta-error"></span>
				</div>
				<div class="respuesta__contenido">
					<h3 class="respuesta__titulo">¡Oh no!</h3>
					<p class="respuesta__parrafo">Ocurrió un problema al enviar su solicitud, pruebe volver a simular.</p>
					<div class="separador"></div>
					<a href="#" onclick="window.location.reload(); return false;" class="btn btn--secundario btn--borde">Volver a
						simular</a>
				</div>
			</div>
		</div>
		<div class="simulador">
			<div class="simulador__container">
				<header class="simulador__header flex-xs bottom-xs">
					<div class="simulador__header__icon">
						<svg xmlns="http://www.w3.org/2000/svg" width="150" height="150" viewBox="0 0 150 150">
							<path d="M98.6 99.2c-1.3 0-2.4 1.1-2.4 2.4 0 1.3 1.1 2.4 2.4 2.4 1.3 0 2.4-1.1 2.4-2.4C101 100.3 99.9 99.2 98.6 99.2zM58.5 96.3c-2 0-3.5-1.6-3.5-3.5 0-2 1.6-3.5 3.5-3.5 1.2 0 2.4 0.6 3.6 1.7 0.5 0.4 1.1 0.7 1.7 0.7 0.6 0 1.2-0.3 1.7-0.7 0.4-0.5 0.7-1.1 0.7-1.7 0-0.6-0.3-1.2-0.7-1.7 -1.3-1.2-2.7-2.1-4.1-2.6l-0.4-0.1v-3.9c0-1.3-1.1-2.4-2.4-2.4s-2.4 1.1-2.4 2.4v3.9l-0.4 0.1c-3.3 1.2-5.5 4.3-5.5 7.8 0 4.6 3.7 8.3 8.3 8.3 2 0 3.5 1.6 3.5 3.5 0 2-1.6 3.5-3.5 3.5 -1.4 0-3-0.9-4.3-2.4 -0.4-0.5-1-0.8-1.6-0.8 -0.1 0-0.1 0-0.2 0 -0.6 0-1.1 0.2-1.5 0.6 -1 0.8-1.1 2.3-0.3 3.3 1.5 1.8 3.3 3 5.1 3.7l0.4 0.1v3.9c0 1.3 1.1 2.4 2.4 2.4s2.4-1.1 2.4-2.4v-3.9l0.4-0.1c3.3-1.2 5.5-4.3 5.5-7.8C66.7 100 63 96.3 58.5 96.3zM34.8 47.8c1.3 0 2.4-1.1 2.4-2.4V33.7c0-1.3-1.1-2.4-2.4-2.4s-2.4 1.1-2.4 2.4v11.8C32.5 46.8 33.5 47.8 34.8 47.8zM138.8 87.4h-0.6v-13h0.6c3.6 0 6.5 2.9 6.5 6.5S142.4 87.4 138.8 87.4zM145.3 107.5c0 2-1.6 3.5-3.5 3.5h-49c-2 0-3.5-1.6-3.5-3.5V95.7c0-2 1.6-3.5 3.5-3.5h46.1c2 0 3.9-0.5 5.6-1.5l0.9-0.5V107.5zM133.5 124c0 5.2-4.2 9.4-9.4 9.4h-5.3v-17.7h14.8V124zM114 135.8c0 5.2-4.2 9.4-9.4 9.4H14.2c-5.2 0-9.4-4.2-9.4-9.4V55.9l0.9 0.7c2.6 1.9 5.9 3 9.1 3h89.7c5.2 0 9.4 4.2 9.4 9.4v18.3H92.7c-4.6 0-8.3 3.7-8.3 8.3v11.8c0 4.6 3.7 8.3 8.3 8.3H114V135.8zM14.2 36h3.5v18.9h-2.9c-5 0-10.1-3.5-10.1-9.4C4.7 40.3 9 36 14.2 36zM22.4 27.8c0-2 1.6-3.5 3.5-3.5h21.3v30.7H22.4V27.8zM65 54.9H52V24.2h13V54.9zM82.7 27.8v27.2h-13V24.2h9.4C81.1 24.2 82.7 25.8 82.7 27.8zM40.2 8.3c0-2 1.6-3.5 3.5-3.5h54.9c2 0 3.5 1.6 3.5 3.5v46.7H87.4V27.8c0-4.6-3.7-8.3-8.3-8.3h-39V8.3zM106.9 36H124c5.2 0 9.4 4.2 9.4 9.4v41.9h-14.8V69.1c0-6.7-4.8-12.6-11.3-13.9l-0.5-0.1V36zM138.8 69.7h-0.6V45.5c0-7.8-6.4-14.2-14.2-14.2h-17.1v-23c0-4.6-3.7-8.3-8.3-8.3H43.7c-4.6 0-8.3 3.7-8.3 8.3v11.2H26c-4.6 0-8.3 3.7-8.3 8.3v3.5h-3.5C6.4 31.3 0 37.7 0 45.5v90.4c0 7.8 6.4 14.2 14.2 14.2h90.4c6.7 0 12.5-4.8 13.9-11.3l0.1-0.5h5.5c7.8 0 14.2-6.4 14.2-14.2v-8.3h3.5c4.6 0 8.3-3.7 8.3-8.3V80.9C150 74.7 145 69.7 138.8 69.7zM124 99.2h-13.6c-1.3 0-2.4 1.1-2.4 2.4 0 1.3 1.1 2.4 2.4 2.4H124c1.3 0 2.4-1.1 2.4-2.4C126.4 100.3 125.3 99.2 124 99.2z" /></svg>
					</div>
					<div class="simulador__nav flex-xs between-xs">
						<div class="simulador__nav__item activo" data-paso="1">
							<div class="simulador__nav__icon">
								<svg xmlns="http://www.w3.org/2000/svg" width="41" height="55" viewBox="0 0 41.2 55">
									<path d="M41.2 11.1c0-0.3-0.1-0.7-0.4-0.9L31 0.4c0 0 0 0 0 0C30.8 0.1 30.5 0 30.1 0H2.7C1.2 0 0 1.2 0 2.7v49.6C0 53.8 1.2 55 2.7 55h35.8c1.5 0 2.7-1.2 2.7-2.7L41.2 11.1C41.2 11.1 41.2 11.1 41.2 11.1zM31.4 4.4l5.5 5.5h-5.3c-0.1 0-0.1-0.1-0.1-0.1L31.4 4.4 31.4 4.4zM38.7 52.3c0 0.1-0.1 0.1-0.1 0.1H2.7c-0.1 0-0.1-0.1-0.1-0.1V2.7c0-0.1 0.1-0.1 0.1-0.1h26.1v7.1c0 1.5 1.2 2.7 2.7 2.7h7.1L38.7 52.3 38.7 52.3z" />
									<path d="M32 17.6H9.2c-0.7 0-1.3 0.6-1.3 1.3 0 0.7 0.6 1.3 1.3 1.3H32c0.7 0 1.3-0.6 1.3-1.3C33.3 18.2 32.7 17.6 32 17.6z" />
									<path d="M32 25.4H9.2c-0.7 0-1.3 0.6-1.3 1.3 0 0.7 0.6 1.3 1.3 1.3H32c0.7 0 1.3-0.6 1.3-1.3C33.3 26 32.7 25.4 32 25.4z" />
									<path d="M32 33.2H9.2c-0.7 0-1.3 0.6-1.3 1.3 0 0.7 0.6 1.3 1.3 1.3H32c0.7 0 1.3-0.6 1.3-1.3C33.3 33.7 32.7 33.2 32 33.2z" />
									<path d="M20.6 40.9H9.2c-0.7 0-1.3 0.6-1.3 1.3 0 0.7 0.6 1.3 1.3 1.3h11.4c0.7 0 1.3-0.6 1.3-1.3S21.3 40.9 20.6 40.9z" /></svg>
							</div>
							<div class="simulador__nav__label">
								<div class="simulador__nav__paso">Paso 1</div>
								<div class="simulador__nav__name">Datos simulación</div>
							</div>
						</div>
						<div class="simulador__nav__item" data-paso="2">
							<div class="simulador__nav__icon">
								<svg xmlns="http://www.w3.org/2000/svg" width="55" height="55" viewBox="0 0 55 55">
									<path d="M46.9 8.1C41.8 2.9 34.8 0 27.5 0 20.2 0 13.2 2.9 8.1 8.1 2.9 13.2 0 20.2 0 27.5s2.9 14.3 8.1 19.4S20.2 55 27.5 55c7.3 0 14.3-2.9 19.4-8.1S55 34.8 55 27.5 52.1 13.2 46.9 8.1zM27.5 51.8c-13.4 0-24.3-10.9-24.3-24.3S14.1 3.2 27.5 3.2s24.3 10.9 24.3 24.3S40.9 51.8 27.5 51.8z" />
									<path d="M40.6 18.7C40 18 39 18 38.4 18.7L24.1 32.9l-7.5-7.5c-0.6-0.6-1.6-0.6-2.3 0s-0.6 1.6 0 2.3l8.6 8.6c0.3 0.3 0.7 0.5 1.1 0.5s0.8-0.2 1.1-0.5L40.6 21C41.3 20.3 41.3 19.3 40.6 18.7z" /></svg>
							</div>
							<div class="simulador__nav__label">
								<div class="simulador__nav__paso">Paso 2</div>
								<div class="simulador__nav__name">Resultado simulación</div>
							</div>
						</div>
						<div class="simulador__nav__item" data-paso="3">
							<div class="simulador__nav__icon">
								<svg xmlns="http://www.w3.org/2000/svg" width="55" height="55" viewBox="0 0 55 55">
									<path class="st0" d="M31.2 26.2c-0.7-0.4-1.7-0.9-2.8-1.4 -1.3-0.6-2.2-1.1-2.6-1.5 -0.4-0.4-0.7-1-0.7-1.6 0-0.7 0.3-1.3 0.8-1.8 0.5-0.5 1.2-0.7 2-0.7 1.3 0 2.4 0.7 3.2 2.2l0 0c0.3 0.4 0.7 0.6 1.2 0.6 0.9 0 1.6-0.7 1.6-1.6 0-0.2 0-0.4-0.1-0.6l0 0c0-0.1-0.1-0.1-0.1-0.2 0 0 0-0.1-0.1-0.1 -0.6-1-1.2-1.7-1.8-2.2 -0.7-0.5-1.6-0.9-2.7-1.1V14h0c0-0.7-0.5-1.2-1.2-1.2 -0.7 0-1.2 0.5-1.2 1.2 0 0 0 0.1 0 0.1v2.1c-0.7 0.2-1.4 0.5-2 0.8 -0.6 0.3-1.1 0.7-1.5 1.2 -0.4 0.5-0.7 1-0.9 1.6 -0.2 0.6-0.3 1.2-0.3 1.9 0 1.4 0.4 2.6 1.3 3.6 0.6 0.7 1.7 1.4 3.5 2.2 0.9 0.4 1.6 0.8 2.1 1.1 0.6 0.3 1 0.6 1.3 0.9 0.6 0.6 0.8 1.3 0.8 2.2 0 1-0.3 1.9-1 2.5 -0.7 0.7-1.5 1-2.5 1 -1 0-1.9-0.3-2.5-0.9 -0.6-0.6-1.1-1.6-1.5-3l0 0c-0.2-0.6-0.8-1.1-1.5-1.1 -0.9 0-1.6 0.7-1.6 1.6 0 0.1 0 0.1 0 0.2l0 0c0.5 2 1.2 3.5 2.1 4.4 1 1 2.3 1.6 4.1 1.9V41v0 0 0h0c0 0.7 0.6 1.2 1.2 1.2 0.7 0 1.2-0.5 1.2-1.2h0v0 0 0 -2.8c1.6-0.4 2.8-1.2 3.7-2.4 0.9-1.2 1.4-2.6 1.4-4.2 0-1.6-0.5-3-1.6-4.1C32.5 27.1 31.9 26.7 31.2 26.2z" />
									<path class="st0" d="M46.9 8.1C41.8 2.9 34.8 0 27.5 0 20.2 0 13.2 2.9 8.1 8.1 2.9 13.2 0 20.2 0 27.5s2.9 14.3 8.1 19.4c5.2 5.2 12.1 8.1 19.4 8.1 7.3 0 14.3-2.9 19.4-8.1 5.2-5.2 8.1-12.1 8.1-19.4S52.1 13.2 46.9 8.1zM27.5 51.8c-13.4 0-24.3-10.9-24.3-24.3S14.1 3.2 27.5 3.2s24.3 10.9 24.3 24.3S40.9 51.8 27.5 51.8z" /></svg>
							</div>
							<div class="simulador__nav__label">
								<div class="simulador__nav__paso">Paso 3</div>
								<div class="simulador__nav__name">Solicitar crédito</div>
							</div>
						</div>
					</div>
				</header>
				<div class="simulador__item" id="paso-1">
					<html:form action="/solicitudCredito" styleClass="form" styleId="solicitud-credito">
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder">
									<span class="icono fln-abajo"></span>
									<select name="tipo-afiliado" id="tipo-afiliado" class="requerido">
										<option value="" hidden> </option>
										<option value="1">Trabajador</option>
										<option value="2">Pensionado</option>
									</select>
									<label for="tipo-afiliado">Tipo de afiliado</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder" data-comentario="Sin puntos ni guión. Ej 12345678k">
									<input type="text" class="text tipo_rut requerido" id="rut" name="rut">
									<label for="rut">RUT</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder" data-comentario="Mayor o igual a $20.000">
									<input type="text" class="text tipo_numerico formatear-montos" id="monto" name="monto">
									<label for="monto">Monto</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder">
									<span class="icono fln-abajo"></span>
									<select name="cuotas" id="cuotas" class="requerido">
										<option value="" hidden> </option>
										 <% for(int i = 3; i < 61; i+=1) { %>
                                           <option value="<%=i%>"><%=i%></option>
                                         <% } %>
									</select>
									<label for="cuotas">N° Cuotas</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder">
									<span class="icono fln-abajo"></span>
									<select name="sucursal" id="sucursal" class="requerido">
										<option value="" hidden> </option>
										<option value="001">Ancud</option>
										<option value="O 50000185">Angol</option>
										<option value="O 50000297">Antofagasta</option>
										<option value="O 50000386">Arica</option>
										<option value="O 50000081">Calama</option>
										<option value="O 50000204">Calbuco</option>
										<option value="O 50000119">Casablanca</option>
										<option value="O 50000203">Castro</option>
										<option value="O 50000180">Cañete</option>
										<option value="O 50000152">Centro Cívico</option>
										<option value="O 50000175">Chillán</option>
										<option value="O 50000184">Concepción</option>
										<option value="O 50000168">Constitución</option>
										<option value="O 50000109">Copiapó</option>
										<option value="O 50000110">Coquimbo</option>
										<option value="O 50000181">Coronel</option>
										<option value="O 50000208">Coyhaique</option>
										<option value="O 50000167">Curicó</option>
										<option value="O 50000144">Estación Central</option>
										<option value="O 50000140">Gran Avenida</option>
										<option value="O 50000148">Huechuraba</option>
										<option value="O 50000158">Huérfanos</option>
										<option value="O 50000114">Illapel</option>
										<option value="O 50000149">Independencia</option>
										<option value="O 50000091">Iquique</option>
										<option value="O 50000123">La Calera</option>
										<option value="O 50000147">La Florida</option>
										<option value="O 50000115">La Serena</option>
										<option value="O 50000197">La Unión</option>
										<option value="O 50000151">Las Condes</option>
										<option value="O 50000171">Linares</option>
										<option value="O 50000125">Los Andes</option>
										<option value="O 50000178">Los Ángeles</option>
										<option value="O 50000141">Maipú</option>
										<option value="O 50000082">María Elena</option>
										<option value="O 50000128">Melipilla</option>
										<option value="O 50003931">Metropolitana</option>
										<option value="O 50000176">Mulchén</option>
										<option value="O 50000199">Osorno</option>
										<option value="O 50000112">Ovalle</option>
										<option value="O 50000155">Providencia</option>
										<option value="O 50000142">Puente Alto</option>
										<option value="O 50000209">Puerto Aysén</option>
										<option value="O 50000210">Puerto Montt</option>
										<option value="O 50000211">Puerto Natales</option>
										<option value="O 50000201">Puerto Varas</option>
										<option value="O 50000213">Punta Arenas</option>
										<option value="O 50000207">Quellón</option>
										<option value="O 50000154">Quilicura</option>
										<option value="O 50000122">Quillota</option>
										<option value="O 50000121">Quilpué</option>
										<option value="O 50000134">Rancagua</option>
										<option value="O 50000143">San Bernardo</option>
										<option value="O 50000174">San Carlos</option>
										<option value="O 50000131">San Fernando</option>
										<option value="O 50000129">Sucural San Antonio</option>
										<option value="O 50000127">Talagante</option>
										<option value="O 50000173">Talca</option>
										<option value="O 50000179">Talcahuano</option>
										<option value="O 50000190">Temuco</option>
										<option value="O 50000079">Tocopilla</option>
										<option value="O 50000194">Valdivia</option>
										<option value="O 50000106">Vallenar</option>
										<option value="O 50000126">Valparaíso</option>
										<option value="O 50000187">Victoria</option>
										<option value="O 50000111">Vicuña</option>
										<option value="O 50000150">Vitacura</option>
										<option value="O 50000120">Viña del Mar</option>
										<option value="O 50000145">Ñuñoa</option>
									</select>
									<label for="sucursal">Sucursal</label>
								</div>
							</div>
							<div class="col-xs-12">
								<div class="form__seleccion">
									<div class="form__checkbox">
										<input type="checkbox" name="seguro-cesantia" id="seguro-cesantia">
										<label for="seguro-cesantia">Incluir Seguro de Cesantía</label>
									</div>
								</div>
							</div>
							<div class="col-xs-12">
								<div class="separador"></div>
								<div class="alerta alerta--error" id="errores_simulador-datos" style="display:none"></div>
							</div>
							<div class="col-xs-12 xs-text-right">
								<a href="#" class="btn btn--secundario btn--block-xs btn--inline-md" onclick="validarDatosCredito(); return false;"><input
									 type="submit" value="">Simular Crédito</a>
							</div>
						</div>
					</html:form>
					<div class="separador--big"></div>
					<div class="simulador__notas contenido-dinamico">
						<ul>
							<li>
								El Crédito Social se encuentra regulado en la <a href="pdf/01_SUSESO_Circular_2052.pdf" target="_blank" class="link-externo">Circular N° 2052</a> de la Superintendencia de Seguridad Social.
							</li>
							<li>
								El Crédito Social es exclusivo para trabajadores y pensionados afiliados a la C.C.A.F. La Araucana.
							</li>
							<li>
								Los montos están sujetos a evaluación y aprobación crediticia conforme a los antecedentes y políticas de Crédito de La Caja y a la normativa vigente.
							</li>
							<li>
								El monto a simular debe ser mayor o igual a $20.000.
							</li>
							<li>
								En caso de consultas o reclamo favor acercarse a algunas de nuestras sucursales o bien ingresar a nuestra página web <a href="http://www.laaraucana.cl" target="_blank" class="link-externo">www.laaraucana.cl</a> en la sección “Contáctenos”
							</li>
						</ul>
					</div>
				</div>
			<!-- <div class="simulador__item" id="paso-2" style="display:none">
					<div class="row">
						<div class="col-xs-12 xs-text-right">
							<div class="simulador__fecha">
								<div class="simulador__fecha__top">Fecha y hora de simulación</div>
								<div class="simulador__fecha__data">
									08 de Octubre de 2018 | 21:00
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="tabla tabla--zebra tabla--full tabla--small">
								<table>
									<tbody>
										<tr>
											<td>
												<div class="text-may">
													RUT
												</div>
											</td>
											<td class="xs-text-right"><strong>11.111.111-1</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Sucursal
												</div>
											</td>
											<td class="xs-text-right"><strong>Santiago centro</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Tipo de afiliado
												</div>
											</td>
											<td class="xs-text-right"><strong>Trabajador</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													N° de cuotas
												</div>
											</td>
											<td class="xs-text-right"><strong>24</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Incluye seguro de cesantía
												</div>
											</td>
											<td class="xs-text-right"><strong>Si</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Incluye seguro de desgravamen
												</div>
											</td>
											<td class="xs-text-right"><strong>Si</strong></td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td>Monto</td>
											<td class="xs-text-right"><strong>$3.500.000</strong></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="tabla tabla--zebra tabla--full tabla--small">
								<table>
									<tbody>
										<tr>
											<td>
												<div class="text-may">
													Impuestos
												</div>
											</td>
											<td class="xs-text-right"><strong>$21.004</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Gasto notarial
												</div>
											</td>
											<td class="xs-text-right"><strong>$210</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Valor cuota mensual
												</div>
											</td>
											<td class="xs-text-right"><strong>$210.000</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													tasa interés mensual
												</div>
											</td>
											<td class="xs-text-right"><strong>2,11%</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Pago primera cuota
												</div>
											</td>
											<td class="xs-text-right"><strong>01/06/2019</strong></td>
										</tr>
										<tr>
											<td>
												<div class="text-may">
													Carga anual equivalente (CAE)
												</div>
											</td>
											<td class="xs-text-right"><strong>42,55%</strong></td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td>
												<div class="text-may">Costo total del crédito (CTC)</div>
											</td>
											<td class="xs-text-right"><strong>$3.216.117</strong></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<div class="col-xs-12">
							<div class="btn__grupo xs-text-right flex-xs column-reverse-xs block-md">
								<a href="#" class="btn btn--secundario btn--borde btn--block-xs btn--inline-md" onclick="simulador.sgte(1); return false;">Volver
									a simular</a>
								<a href="#" class="btn btn--secundario btn--block-xs btn--inline-md" onclick="simulador.sgte(3); return false;">Solicitar
									Crédito</a>
							</div>
						</div>
					</div>
					<div class="separador--big"></div>
					<div class="tabla tabla--zebra tabla--big tabla--full">
						<table>
							<tbody>
								<tr>
									<td>Resultado de la simulación</td>
									<td class="xs-text-right"><a href="pdf/02_Resultado_Simulacion_Credito.pdf" target="_blank" class="tabla__icono fln-pdf"></a></td>
								</tr>
								<tr>
									<td>Información del Crédito o Detalle de Condiciones</td>
									<td class="xs-text-right"><a href="pdf/03_Informacion_Detalle_Credito.pdf" target="_blank" class="tabla__icono fln-pdf"></a></td>
								</tr>
								<tr>
									<td>Información Avalistas, Fiadores o Codeudores Solidarios</td>
									<td class="xs-text-right"><a href="pdf/04_Informacion_Avalistas.pdf" target="_blank" class="tabla__icono fln-pdf"></a></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="separador--big"></div>
					<div class="fln-accordion">
						<div class="fln-accordion__header">Ver detalles de cuotas</div>
						<div class="fln-accordion__contenido">
							<div class="separador"></div>
							<div class="tabla tabla--zebra tabla--full tabla--responsive tabla--paginada">
								<table data-filas="12">
									<thead>
										<tr>
											<th class="sorter-false filter-false">N° Cuota</th>
											<th class="sorter-false filter-false">Vencimiento</th>
											<th class="sorter-false filter-false">Monto Capital $</th>
											<th class="sorter-false filter-false">Monto Interés $</th>
											<th class="sorter-false filter-false">Seguro Desgravamen $</th>
											<th class="sorter-false filter-false">Seguro de Cesantía $</th>
											<th class="sorter-false filter-false">Valor Cuota $</th>
										</tr>
									</thead>
									<tbody>
                                        <logic:iterate id="testDataId" name="testData">
                                        <tr>
                                            <td data-th="N° Cuota" class="md-text-center"><bean:write name="testDataId" property="numeroCuota"/></td>
                                            <td data-th="Vencimiento" class="md-text-center"><bean:write name="testDataId" property="fechaVencimiento"/></td>
                                            <td data-th="Monto Capital $" class="md-text-center"><bean:write name="testDataId" property="montoCapital"/></td>
                                            <td data-th="Monto Interés $" class="md-text-center"><bean:write name="testDataId" property="montoInteres"/></td>
                                            <td data-th="Seguro Desgravamen $" class="md-text-center"><bean:write name="testDataId" property="seguroDesgravamen"/></td>
                                            <td data-th="Seguro Cesantía $" class="md-text-center"><bean:write name="testDataId" property="seguroCesantia"/></td>
                                            <td data-th="Valor Cuota $" class="md-text-center"><bean:write name="testDataId" property="valorCuota"/></td>
                                        </tr>
                                        </logic:iterate>
									</tbody>
								</table>
								<div class="paginador">
									<div class="paginador__nav">
										<span class="paginador__item paginador__flecha primero deshabilitado"><i class="fln-izquierda-doble"></i></span>
										<span class="paginador__item paginador__flecha anterior deshabilitado"><i class="fln-izquierda"></i></span>
										<span class="paginador__item salida">
											<span class="visibles">0 </span>
											<span class="total">de 0</span>
										</span>
										<span class="paginador__item total"></span>
										<span class="paginador__item paginador__flecha siguiente"><i class="fln-derecha"></i></span>
										<span class="paginador__item paginador__flecha ultimo"><i class="fln-derecha-doble"></i></span>
									</div>
									<div class="paginador__filas">
										<span class="paginador__item texto">Cantidad de filas</span>
										<span class="paginador__item paginador__select">
											<div class="paginador__input">
												<select name="paginador__select" class="paginador__select select filas">
													<option value="all">Todas</option>
													<option value="12" selected>12</option>
													<option value="24">24</option>
													<option value="36">36</option>
													<option value="48">48</option>
												</select>
												<i class="fln-abajo"></i>
											</div>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>-->
					<div class="separador--big"></div>
					<div class="simulador__notas contenido-dinamico">
						<ul>
							<li>
								El Crédito Social es exclusivo para trabajadores y pensionados afiliados a la C.C.A.F. La Araucana.
							</li>
							<li>
								Los montos están sujetos a evaluación y aprobación crediticia conforme a los antecedentes y políticas de
								Crédito de La Caja y a la normativa vigente.
							</li>
							<li>
								El Crédito Social se encuentra regulado en la <a href="pdf/01_SUSESO_Circular_2052.pdf" target="_blank" class="link-externo">Circular N° 2052</a> de la
								Superintendencia de Seguridad Social.
							</li>
							<li>
								La contratación del seguro de cesantía es voluntaria. Esta simulación de crédito considera plan de hasta 4
								cuotas de cobertura.
							</li>
							<li>
								<strong>Valor cuota:</strong> El valor cuota considera todos los intereses, amortizaciones, gastos propios del Crédito Social
								(impuestos y gastos notariales) y gastos o cargos por productos o servicios voluntariamente contratados.
							</li>
							<li>
								<strong>Carga Anual Equivalente (CAE):</strong> Carga Anual Equivalente, indicador que expresado en forma de porcentaje (%)
								revela el costo de un crédito en un período anual, cualquiera que sea el plazo pactado para el pago de la
								obligación. Este indicador contempla el tipo de interés, todos los gastos asociados al crédito y el plazo de éste.
							</li>
							<li>
								<strong>Costo Total del Crédito (CTC):</strong> Corresponde a la suma de todas las cuotas en función del plazo del Crédito Social.
							</li>
							<li>
								La presente sólo tendrá el carácter de simulación no vinculante o meramente referencial, hasta la aprobación de la evaluación de riesgo comercial correspondiente.
							</li>
						</ul>
					</div>
				</div>
				<div class="simulador__item" id="paso-3" style="display:none">
					<form action="" class="form" id="simulador-solicitar">
						<div class="simulador__resumen">
							<div class="row">
								<div class="col-xs-12 col-md-4">
									<div class="simulador__resumen__item">
										<div class="simulador__resumen__texto">Estas solicitando un Crédito por</div>
										<div class="simulador__resumen__valor">$3.500.000</div>
									</div>
								</div>
								<div class="col-xs-12 col-md-4">
									<div class="simulador__resumen__item">
										<div class="simulador__resumen__texto">Con una cuota mensual de</div>
										<div class="simulador__resumen__valor">$210.000</div>
									</div>
								</div>
								<div class="col-xs-12 col-md-4">
									<div class="simulador__resumen__item">
										<div class="simulador__resumen__texto">Costo total del crédito (CTC)</div>
										<div class="simulador__resumen__valor">$3.650.088</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo">
									<input type="text" class="text requerido" id="nombre" name="nombre" value="Victoria Díaz Harris" disabled>
									<label for="nombre">Nombre</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo">
									<input type="text" class="text requerido" id="rut-usuario" name="rut-usuario" value="11.111.111-1" disabled>
									<label for="rut-usuario">RUT</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder" data-comentario="EJ: +56991234567">
									<input type="text" class="text tipo_telefono requerido" id="celular" name="celular">
									<label for="celular">Celular</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder" data-comentario="EJ: +56221234567">
									<input type="text" class="text tipo_telefono requerido" id="telefono" name="telefono">
									<label for="telefono">Teléfono</label>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form__grupo" data-animacion="placeholder" data-comentario="EJ: nombre@correo.cl">
									<input type="text" class="text requerido tipo_email" id="email" name="email">
									<label for="email">Email</label>
								</div>
							</div>
							<div class="col-xs-12">
								<div class="separador"></div>
								<div class="alerta alerta--error" id="errores_simulador-solicitar" style="display:none"></div>
								<div class="btn__grupo xs-text-right flex-xs column-reverse-xs block-md">
									<a href="#" class="btn btn--secundario btn--borde btn--block-xs btn--inline-md" onclick="simulador.sgte(2); return false;">Volver</a>
									<a href="#" class="btn btn--secundario btn--block-xs btn--inline-md" onclick="validarSolicitud(); return false;">Solicitar</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="preloader-general" id="preloader-general" data-tipo="pill" data-posicion="bottom" style="display: none"></div>

	<script type="text/javascript" src="js/polyfill.min.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/automatizar.min.js"></script>
	<script type="text/javascript" src="js/fln.js"></script>
	<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
	<script type="text/javascript" src="js/jquery.tablesorter.widgets.js"></script>
	<script type="text/javascript" src="js/funciones.js"></script>
	<!-- <script type="text/javascript" src="js/modernizr.js"></script>
	<script type="text/javascript" src="js/tabsOrAccordion.min.js"></script>
	<script type="text/javascript" src="js/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript" src="js/jquery.nav.js"></script>
	<script type="text/javascript" src="js/prism.js"></script>
	<script type="text/javascript" src="js/fln.min.js"></script>
	<script type="text/javascript" src="js/funciones.js"></script> -->
</body>


</html:html>
