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
<title>Subir Licencia - La Araucana</title>
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
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
<!--




google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawStacked);

function drawStacked() {
      var data = new google.visualization.DataTable();
      data.addColumn('string', 'Licencias');
      data.addColumn('number', 'Afiliados');
      data.addColumn('number', 'Empresa');

      data.addRows([
		<c:forEach var="reg" varStatus="vs" items="${licenciasxOficina}">
		['${reg.sucursal}', ${reg.cantidadAfiliado},  ${reg.cantidadEmpresa}]<c:if test="${!vs.last }">,</c:if>
							
		</c:forEach>

      ]);

      var options = {
        title: 'Cantidad de Licencias Recibidas por Oficina para el Periodo ${periodoActual}',
        isStacked: true,
        hAxis: {
          title: 'Oficinas'
        },
        vAxis: {
          title: 'Licencias x Vía Ingreso'
        }
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }
   //-->
</script>
</head>
<body>


	<div class="container">
	<div align="right">
		<a href="<c:url value='/exit.do' />"  style="text-decoration:none"><img alt="Salir" src="img/salir.png"  title="Cerrar Sesión" /><span class="pasos__data" style="font-size:1.6rem;color:gray;">&nbsp;Salir</span></a>
	</div>
		<div class="row">
			<div class="col xs12 lg2 text-align-center-xs">
				<svg width="155.30017318187797" height="139.99975464130628" xmlns="http://www.w3.org/2000/svg" class="svg svg--secundario">

 <g>
  <title>Estadísticas Licencia - La Araucana</title>
  <rect height="402" width="582" y="-1" x="-1" fill="none" id="canvas_background"/>
 </g>
 <g>
  <title>Estadísticas Licencias - La Araucana</title>
  <path d="m77.699997,85.400002c23.599998,0 42.700005,-19.099998 42.700005,-42.700001s-19.200005,-42.700001 -42.700005,-42.700001s-42.799995,19.1 -42.799995,42.700001s19.199997,42.700001 42.799995,42.700001zm0,-77.600001c19.299995,0 34.900002,15.600001 34.900002,34.900001s-15.599998,34.999996 -34.900002,34.999996s-34.999996,-15.699997 -34.999996,-34.999996c0,-19.300001 15.700001,-34.900001 34.999996,-34.900001z" id="svg_1"/>
  <path d="m135.600006,96.800003l0,0c-37.200005,-15 -78.800003,-15 -116,0l0,0c-11.900006,4.899994 -19.600006,15.199997 -19.600006,26.599998l0,16.599998l155.300003,0l0,-16.599998c0,-11.400002 -7.699997,-21.700005 -19.699997,-26.599998zm11.899994,35.399994l-139.7,0l0,-8.900002c0,-8.099998 5.8,-15.699997 14.8,-19.299995c35.300001,-14.200005 74.800001,-14.200005 110.200003,0c9,3.599998 14.799988,11.199997 14.799988,19.299995l0,8.900002l-0.099991,0z" id="svg_2"/>
  <line y2="127.31" x2="106.795" y1="105.73" x1="106.795" stroke-width="8" stroke="#bf0000" fill="none" id="svg_5"/>
  <line y2="116.255" x2="117.825" y1="116.255" x1="95.7204" stroke-width="8" stroke="#bf0000" fill="none" id="svg_6"/>
  <polyline points="74.6904,26.7804 74.6904,26.7804" stroke-width="7" stroke-linecap="round" fill="none" id="svg_10"/>
  <polyline points="49.4304,21.5154 49.4304,21.5154" stroke-width="7" stroke-linecap="round" stroke="#000a0a" fill="none" id="svg_20"/>
  <ellipse ry="20.5249" rx="20.5275" cy="20.9649" cx="48.9029" stroke-width="9" fill="#0f0f00" id="svg_34"/>
  <ellipse ry="9.24249" rx="8.96572" cy="20.6879" cx="48.9012" stroke-width="8" stroke="#f2f7f7" fill="none" id="svg_22"/>
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
											d="M49.4 10.6C44.3 5.4 37.3 2.5 30 2.5c-7.3 0-14.3 2.9-19.4 8.1C5.4 15.7 2.5 22.7 2.5 30s2.9 14.3 8.1 19.4c5.2 5.2 12.1 8.1 19.4 8.1 7.3 0 14.3-2.9 19.4-8.1s8.1-12.1 8.1-19.4S54.6 15.7 49.4 10.6zM30 54.3C16.6 54.3 5.7 43.4 5.7 30S16.6 5.7 30 5.7 54.3 16.6 54.3 30 43.4 54.3 30 54.3z" />
										<path
											d="M40.9 21.2L26.6 35.4l-7.5-7.5c-0.6-0.6-1.6-0.6-2.3 0 -0.6 0.6-0.6 1.6 0 2.3l8.6 8.6c0.3 0.3 0.7 0.5 1.1 0.5 0.4 0 0.8-0.2 1.1-0.5l15.4-15.4c0.6-0.6 0.6-1.6 0-2.3C42.5 20.5 41.5 20.5 40.9 21.2z" /></svg>
								</div>
								<div class="pasos__data">
									<span>Paso 1</span>LICENCIAS X PERIODO
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
									<span>Paso 2</span>LICENCIAS x OFICINA
								</div>
							</div>
						</div>
					</div>
				</div>

				<p style="margin-top: 10px">Cantidad de Licencias Recibidas por Oficina y Vía de Ingreso</p>

				<form class="form" id="paso2" action="paso2.do" method="GET" style="margin-top: 10px">
						
						<div class="col xs12 lg3">
							<p class="adicional" style="font-size: medium;">Selecciona
								otro Periodo:</p>
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="">
								<select name="periodo" id="periodo" onChange="validarPaso2();">

									<c:forEach var="entry" varStatus="vs" items="${periodos}">
										<option value="${entry.periodo }"
											<c:if test="${entry.periodo==periodoActual }"> selected="selected" </c:if>>${entry.periodo}
											</option>
									</c:forEach>
								</select>
							</div>
						</div>
				</form>
				<form class="form" id="paso2DN" action="paso2DN.do" method="GET" style="margin-top: -50px">
						<div class="col xs12 lg6">
							<p class="adicional" style="font-size: medium;">Selecciona
								tipo Grafico:
							</p>
							<p style="margin-top: 10px">
								<input type="radio" name="tipografico" id="tipografico" value="F" checked="checked">&nbsp;Frecuencia
								&nbsp;&nbsp;<input type="radio" name="tipografico" id="tipografico" value="N" onClick="validarPaso2DN();">&nbsp;Distribución Normal
							</p>
						</div>
						<div class="col xs12 lg10">
							<div class="form__grupo" data-animacion="placeholder" data-comentario="">
								<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 								 <div id="chart_div" style="width: 900px; height: 400px; margin-top: -40px""></div>
							</div>
						</div>
						
						<div class="col xs12 lg6">
							<div class="btn__grupo text-align-right-xs" style="margin-top: -20px">
								<a id="botonIL" style="margin-left: 20px"
									class="btn btn--secundario" href="<c:url value='/init.do' />"
									>Volver</a>
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
	
</body>

</html>