
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
<script type="text/javascript" src="resources/js/corev2.js"></script>
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
	<main>
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
									<span>Paso 1</span>DATOS AFILIADO
								</div>
							</div>
						</div>
						<div class="col xs12 sm12 md4 oculto-xs block-md">
							<div
								class="pasos__item flex-xs align-middle-xs justify-center-xs justify-start-md activo">
								<div class="pasos__icono">
									<svg width="60.00000911898385" height="60.00000911898385" xmlns="http://www.w3.org/2000/svg" class="svg">

 										<g>
  											<title></title>
  											<rect height="402" width="582" y="-1" x="-1" fill="none" id="canvas_background"/>
 										</g>
 										<g>
  											<title></title>
  											<rect height="60" width="60" y="-0.790002" x="74.7375" id="svg_1" class="svg__fondo"/>
  											<path d="m53.400002,12.3l-46.800002,0c-2.3,0 -4.1,1.8 -4.1,4.099999l0,27.199999c0,1.100002 0.4,2.100002 1.2,2.900002c0.8,0.799999 1.8,1.200001 2.9,1.200001l46.800002,0c2.299999,0 4.099998,-1.799999 4.099998,-4.100002l0,-27.199999c0,-2.2 -1.799999,-4.099999 -4.099998,-4.099999zm-46.800002,2.4l46.800002,0c0.899998,0 1.699997,0.8 1.699997,1.7l0,27.199999c0,0.900002 -0.799999,1.700001 -1.699997,1.700001l-46.800002,0c-0.9,0 -1.7,-0.799999 -1.7,-1.700001l0,-27.199999c0,-0.9 0.7,-1.7 1.7,-1.7z" id="svg_2"/>
  											<path d="m29.700001,25.750004l20.700001,0c0.599998,0 1.199997,-0.5 1.199997,-1.200001c0,-0.599998 -0.5,-1.199999 -1.199997,-1.199999l-20.700001,0c-0.6,0 -1.200001,0.5 -1.200001,1.199999c0,0.300001 0.1,0.6 0.299999,0.800001c0.300001,0.299999 0.6,0.4 0.900002,0.4z" id="svg_3"/>
  											<path d="m30.224995,38.082512l20.700001,0c0.599998,0 1.199997,-0.5 1.199997,-1.200001c0,-0.599998 -0.5,-1.200001 -1.199997,-1.200001l-20.700001,0c-0.6,0 -1.200001,0.5 -1.200001,1.200001c0,0.299999 0.1,0.599998 0.299999,0.799999c0.300001,0.200001 0.6,0.400002 0.900002,0.400002z" id="svg_4"/>
  											<path d="m16.299999,29.800022c2.6,0 4.700001,-2.099998 4.700001,-4.699999c0,-0.200001 0,-0.300001 0,-0.5l0,0c0,-0.200001 -0.1,-0.5 -0.1,-0.700001c-0.1,-0.199999 -0.1,-0.4 -0.199999,-0.699999c-0.700001,-1.700001 -2.400002,-2.900002 -4.300001,-2.900002c-1.9,0 -3.599999,1.1 -4.299999,2.900002c-0.1,0.199999 -0.200001,0.4 -0.200001,0.699999c-0.099999,0.200001 -0.099999,0.5 -0.099999,0.700001c0,0.199999 0,0.299999 0,0.5c-0.2,2.6 1.9,4.699999 4.499999,4.699999zm-1.9,-7c0.1,-0.099998 0.3,-0.199999 0.5,-0.299999c0.1,0 0.200001,-0.1 0.3,-0.1c0.2,-0.1 0.400001,-0.1 0.5,-0.199999c0.2,0 0.400001,-0.1 0.599999,-0.1c1.6,0 2.900002,1.299999 2.900002,2.9c0,0.200001 0,0.4 -0.1,0.6c0,0.199999 -0.1,0.4 -0.200001,0.5c0,0.1 -0.1,0.199999 -0.1,0.299999l0,0c-0.099998,0.200001 -0.199999,0.300001 -0.299999,0.5c-0.6,0.700001 -1.4,1.1 -2.299999,1.1c-0.200001,0 -0.400001,0 -0.6,-0.1c-0.200001,0 -0.400001,-0.1 -0.5,-0.199999c-0.1,0 -0.200001,-0.1 -0.3,-0.1c-0.2,-0.1 -0.3,-0.200001 -0.5,-0.300001c-0.1,-0.099998 -0.3,-0.299999 -0.400001,-0.4c-0.099999,-0.1 -0.2,-0.299999 -0.299999,-0.5c0,-0.1 -0.1,-0.199999 -0.1,-0.299999c-0.1,-0.200001 -0.1,-0.4 -0.2,-0.5c0,-0.200001 -0.1,-0.4 -0.1,-0.6c0.2,-0.799999 0.5,-1.6 1.2,-2.200001z" id="svg_6"/>
  											<path d="m16.562496,41.337509c2.6,0 4.700001,-2.099998 4.700001,-4.700001c0,-0.199997 0,-0.299999 0,-0.5l0,0c0,-0.199997 -0.1,-0.5 -0.1,-0.699997c-0.1,-0.200001 -0.1,-0.400002 -0.199999,-0.700001c-0.700001,-1.700001 -2.400002,-2.900002 -4.300001,-2.900002c-1.9,0 -3.599999,1.100002 -4.299999,2.900002c-0.1,0.200001 -0.200001,0.399998 -0.200001,0.700001c-0.099999,0.199997 -0.099999,0.5 -0.099999,0.699997c0,0.200001 0,0.300003 0,0.5c-0.2,2.600002 1.9,4.700001 4.499999,4.700001zm-1.9,-7c0.1,-0.099998 0.3,-0.200001 0.5,-0.299999c0.1,0 0.200001,-0.099998 0.3,-0.099998c0.2,-0.100002 0.400001,-0.100002 0.5,-0.200001c0.2,0 0.400001,-0.100002 0.599999,-0.100002c1.6,0 2.900002,1.300003 2.900002,2.900002c0,0.200001 0,0.400002 -0.1,0.599998c0,0.200001 -0.1,0.400002 -0.200001,0.5c0,0.100002 -0.1,0.200001 -0.1,0.300003l0,0c-0.099998,0.199997 -0.199999,0.299999 -0.299999,0.5c-0.6,0.699997 -1.4,1.099998 -2.299999,1.099998c-0.200001,0 -0.400002,0 -0.6,-0.099998c-0.200001,0 -0.400001,-0.100002 -0.5,-0.200001c-0.1,0 -0.200001,-0.100002 -0.3,-0.100002c-0.2,-0.099998 -0.3,-0.199997 -0.5,-0.299999c-0.1,-0.099998 -0.3,-0.299999 -0.400001,-0.399998c-0.099999,-0.100002 -0.2,-0.300003 -0.299999,-0.5c0,-0.100002 -0.1,-0.200001 -0.1,-0.300003c-0.1,-0.199997 -0.1,-0.399998 -0.2,-0.5c0,-0.199997 -0.1,-0.399998 -0.1,-0.599998c0.2,-0.799999 0.5,-1.599998 1.2,-2.200001z" id="svg_8"/>
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

				<form class="form" id="paso2" action="paso3.do" method="POST">
					<div class="row">
					<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="">
								<input class="text requerido" type="text" id="rutAfiliado" name="rutAfiliado"
									value="${rutAfiliado}" disabled="disabled"
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
						
						<div class="col xs12 lg12">
						<p>Selecciona forma de despacho: &nbsp;
								<input type="radio" name="tipoDespacho" id="tipoDespacho1" value="O" checked="checked"/>
									&nbsp;Oficina&nbsp;&nbsp;&nbsp;
								<input  type="radio" name="tipoDespacho" id="tipoDespacho2" value="C"/>
									&nbsp;Correos de Chile
						</p>
						</div>
						<div id="oficina">
							<div class="col xs12 lg12">
										<div class="form__grupo bold" data-animacion="placeholder"
											data-comentario="Oficina La Araucana">
											
											<select class="requerido" id="oficinaDespacho" name="oficinaDespacho">
											<option value="000">Seleccione una oficina</option>
												<c:forEach items="${oficinas}" var="ofi">
												<c:choose>
													<c:when test="${ofi.codigoOficina == oficina}">
														<option value="${ofi.codigoOficina}" selected="selected">${ofi.descripcion}</option>
													</c:when>
													<c:otherwise>
														<option value="${ofi.codigoOficina}">${ofi.descripcion}</option>
													</c:otherwise>
												</c:choose>
												</c:forEach>
											</select> <label for="oficina">&nbsp;</label>

										</div>
								</div>
						</div>
						<div id="correos" style="display:none">
						<div class="separador"></div>
						<c:if test="${comuna_habilitada=='1' }">
							<div class="col xs12 md12">
								<div class="form__seleccion">
									<div class="form__checkbox">
										<input type="checkbox" name="repetirDireccion"
											id="repetirDireccion" style="margin-top: 5px" value="0" /> <label
											for="terminos">Utilizar dirección particular</label>
									</div>
								</div>
							</div>
						</c:if>
							<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Seleccione región particular">
								<select class="requerido" id="regionDespacho" name="regionDespacho">
									<c:forEach items="${regiones}" var="re">
										<c:choose>
											<c:when test="${re.id == region}">
												<option value="${re.id}" selected="selected">${re.descripcion}</option>
											</c:when>
											<c:otherwise>
												<option value="${re.id}">${re.descripcion}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <label for="region">&nbsp;</label>

							</div>
						</div>

						<div class="col xs12 lg6">
							<div class="form__grupo"
								data-comentario="Selecione comuna particular">
								<select class="text requerido" id="comunaDespacho" name="comunaDespacho">
									<option value="0">Seleccione una comuna</option>
									<c:forEach items="${comunas}" var="item">
										<c:choose>
											<c:when test="${item.id == comuna}">
												<option value="${item.id}" selected="selected">${item.descripcion}</option>
											</c:when>
											<c:otherwise>
												<option value="${item.id}">${item.descripcion}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <label for="comuna">&nbsp;</label>
							</div>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Ej: Merced">
								<input class="text requerido tipo_text"
									type="text" id="direccionDespacho" name="direccionDespacho"  value="${datos.direccion}" maxlength="50" /> <label
									for="direccion">Dirección</label>
							</div>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Ej: 4279">
								<input class="text requerido tipo_numerico"
									type="text" id="numeroDespacho" name="numeroDespacho"  value="${datos.numero}" maxlength="6" /> <label
									for="numero">Número</label>
							</div>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Ej: Block A Dpto 123">
								<input class="text requerido tipo_text"
									type="text" id="deptoDespacho" name="deptoDespacho"  value="${datos.bloque}" maxlength="30"/> <label
									for="bloque">Dpto/Casa/Oficina)</label>
							</div>
						</div>
						<div class="col xs12 lg6">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Ej: 4279">
								<input class="text requerido tipo_text"
									type="text" id="referenciaDespacho" name="referenciaDespacho"  value="${datos.referencia}" maxlength="50"/> <label
									for="referencia">Referencia</label>
							</div>
						</div>
						</div>

					</div>
					<div class="col xs12 lg12">
							<div class="alerta alerta--error" id="errores_paso1"
								style="display: none"></div>
					</div>
					<div class="btn__grupo text-align-right-xs">
					<a id="botonIL" style="margin-top: 15px"
								class="btn btn--secundario btn--alto" href="<c:url value='/init.do' />"
								>Volver</a>
							<a id="botonIL" style="margin-top: 15px"
								class="btn btn--primario btn--alto" href="#"
								onClick="validarPaso2(); return false;">Continuar</a>
						</div>
				</form>
			</div>
		</div>
	</div>
	</main>

	<script src="assets/js/polyfill.js"></script>
	<script src="assets/js/jquery-3.3.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="assets/js/funciones.js"></script>
	<script type="text/javascript">
		$('#regionDespacho').change(
				function() {
					$.getJSON('comunas.do', {
						regionNombre : $(this).val(),
						ajax : 'true'
					}, function(data) {
						var html = '';
						var len = data.length;
						for (var i = 0; i < len; i++) {

							html += '<option value="' + data[i].id + '">'
									+ data[i].descripcion + '</option>';
						}
						html += '</option>';
						$('#comunaDespacho	').html(html);
					});
				});
	</script>
	<script>
	$(document).ready(function() {
			$('#tipoDespacho1').click(function(){
				$('#correos').hide();
        		$('#oficina').show();
    		});
    		$('#tipoDespacho2').click(function(){
				$('#oficina').hide();
        		$('#correos').show();
    		});
    		$('#repetirDireccion').click(function(){
				if ($('#repetirDireccion').is(':checked')) {
					$('#repetirDireccion').val("1");
					$('#regionDespacho').val("13");
					$('#comunaDespacho').val("000");
					$('#direccionDespacho').val("");
					$('#numeroDespacho').val("");
					$('#deptoDespacho').val("");
					$('#referenciaDespacho').val("");
					
					$('#regionDespacho').prop("disabled", true);
					$('#comunaDespacho').prop("disabled", true);
					$('#direccionDespacho').prop("disabled", true);
					$('#numeroDespacho').prop("disabled", true);
					$('#deptoDespacho').prop("disabled", true);
					$('#referenciaDespacho').prop("disabled", true);
				}else{
					$('#repetirDireccion').val("0");
					$('#regionDespacho').prop("disabled", false);
					$('#comunaDespacho').prop("disabled", false);
					$('#direccionDespacho').prop("disabled", false);
					$('#numeroDespacho').prop("disabled", false);
					$('#deptoDespacho').prop("disabled", false);
					$('#referenciaDespacho').prop("disabled", false);
				}
			});

		});
	</script>
</body>
</html>