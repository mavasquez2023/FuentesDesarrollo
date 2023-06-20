<%--
    Document   : Estadisticas
    Created on : 19-jul-2022
    Author     : J-Factory
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, viewport-fit=cover" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta name="format-detection" content="telephone=no" />
<title>La Araucana - Sucursal Virtual - Personas</title>
<link rel="icon" href="favicon.ico" />
<link rel="stylesheet" href="assets/css/fln.css" />
<link rel="stylesheet" href="fonts/fln-icons.css" />
<link rel="stylesheet" href="assets/css/estilos.css" />
<link rel="stylesheet" href="assets/css/certificado.css" />
<script>
            WebFontConfig = {
                google: {
                    families: ["Open+Sans:300,400,600,700"]
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
         <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
<!--


google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = new google.visualization.DataTable();
      data.addColumn('string', 'Periodo');
      data.addColumn('number', 'Nominas');

      data.addRows([
        <c:forEach var="reg" varStatus="vs" items="${periodos}">
		['${reg.periodo}', ${reg.cantidad}]<c:if test="${!vs.last }">,</c:if>
							
		</c:forEach>
      ]);

      var options = {
      title: 'Descargas de Nominas por Periodo',
        hAxis: {
          title: 'Periodos'
        },
        vAxis: {
          title: 'Nominas'
        }
      };

      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    }
   //-->
</script>
</head>
<body>
	<main> <jsp:include page="banner.jsp" flush="true" />

	<div class="guia" style="margin-top: -15px; margin-bottom: -15px">
		<div class="guia__container marco">
			<a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a><a class="guia__link--active guia__link" href="#">Estadísticas</a>
		</div>

	</div>
	<section class="container-fluid contenido-interior">
		<div class="marco">
			<div class="row">
				<jsp:include page="menu.jsp" flush="true" />

				<div class="col-lg-9 col-xs-12">
					<div class="contenido-dinamico">
						<h1>Estadísticas de Descarga</h1>
						<div class="bloque" style="margin-top: -30px;">
							<form class="form" id="paso1" action="" method="get">
								<div class="row">

									<div class="col xs12 lg10">
										<div class="form__grupo" data-animacion="placeholder"
											data-comentario="">
											<script type="text/javascript"
												src="https://www.gstatic.com/charts/loader.js"></script>
											<div id="chart_div"
												style="width: 900px; height: 400px; margin-top: -30px"></div>
										</div>
									</div>


									<div class="col xs12 lg6">
										<div class="btn__grupo text-align-right-xs">
											<a id="botonIL" style="margin-left: 20px"
												class="btn btn--primario" href="<c:url value='/estadisticas2.do' />">Siguiente</a>
										</div>
									</div>
									<input type="hidden" name="opcion" id="opcion" value="" />
								</div>
							</form>
						</div>

						<div class="separador--big oculto-xs block-lg"></div>

					</div>
				</div>
			</div>
	</section>

	<jsp:include page="footer.jsp" flush="true" /> </main>
	<div id="loading"
		style="position: fixed; top: 35%; display: none; left: 52%; z-index: auto">
		<img src="<%=request.getContextPath()%>/img/3d-loader.gif">
	</div>
	<div class="preloader-general" id="preloader-general"
		data-tipo="screen" style="display: none"></div>
	<script src="assets/js/polyfill.js"></script>
	<script src="assets/js/jquery-3.4.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/jquery.Rut.js"></script>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/lozad.js"></script>
	<script src="assets/js/jquery.fancybox.js"></script>
	<script src="assets/js/owl.carousel.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="assets/js/funciones_op1_3.js"></script>
	<script src="assets/js/funciones.js"></script>

</body>
</html>
