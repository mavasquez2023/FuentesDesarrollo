<%--
    Document   : NominaDeCredito
    Created on : 19-jul-2022
    Author     : J-Factory
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
        <meta name="format-detection" content="telephone=no"/>
        <title>La Araucana - Sucursal Virtual - Personas</title>
        <link rel="icon" href="favicon.ico"/>
        <link rel="stylesheet" href="assets/css/fln.css"/>
        <link rel="stylesheet" href="fonts/fln-icons.css"/>
        <link rel="stylesheet" href="assets/css/estilos.css"/>
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
    </head>
<body>
	<main> <jsp:include page="banner.jsp" flush="true" />

	<div class="guia" style="margin-top: -15px;margin-bottom: -15px">
		<div class="guia__container marco">
			<a class="guia__link" href="<c:url  value='/sv_inicio.do' />">Servicios en Línea</a><a class="guia__link" href="<c:url  value='/sv_planillas.do' />">Emisión de Planillas</a><a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a><a class="guia__link--active guia__link" href="#">Filtrar</a>
		</div>
	</div>
	<section class="container-fluid contenido-interior">
		<div class="marco">
			<div class="row">
				<jsp:include page="menu.jsp" flush="true" />
				<div class="col-lg-9 col-xs-12">
					<div class="contenido-dinamico">
						<h1>Impresión de Documentos</h1>
	
                            <form class="form" action="filtrar.do" id="form-filtrar" method="post">
                            <!--  
                               <div class="row" >
                                            <div class="col-xs-12 col-md-6 col-lg-4">
                                               
                                                <div class="form__seleccion js-opciones-avanzadas">
                                                    <div class="form__toggle">
                                                        <input type="checkbox" name="opciones-avanzadas" id="opciones-avanzadas" onchange="mostrarContenido('.js-avanzadas', this.checked);"/>
                                                        <label for="opciones-avanzadas">Opciones avanzadas</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="js-avanzadas" style="display:none">
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-3">
                                                    <div class="form__grupo js-codigo-oficina " data-animacion="placeholder"><span class="form__grupo--help"></span>
                                                        <input class="text requerido" type="text" name="codigo-oficina" id="codigo-oficina"/>
                                                        <label for="codigo-oficina">Código oficina</label>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-3">
                                                    <div class="form__grupo js-codigo-sucursal " data-animacion="placeholder"><span class="form__grupo--help"></span>
                                                        <input class="text requerido" type="text" name="codigo-sucursal" id="codigo-sucursal"/>
                                                        <label for="codigo-sucursal">Código Sucursal</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>  
                                  -->  
                           
								<p><h5>Seleccione las empresas a filtrar las consultas:</h5></p>
								
							<div class="row">
								<div class="col-xs-12 col-md-6 col-lg-6">
									<h4>Encargado de Empresas:</h4>
								</div>
								<div class="col-xs-12 col-md-6 col-lg-3" >
									<div class="text-align-left-xs" style="margin-top: 20px;margin-right: 10px">
										<a 
											class="btn btn--small btn--secundario btn--block-xs btn--inline-md btn--relevante"
											href="#" onclick="validarFiltrar();">Guardar selección</a>
									</div>
								</div>
							</div>
						
							<div class="tabla tabla--full tabla--borde tabla--responsive">
								<table style="width: 72%">
									<thead>
										<tr>
											<th>Rut empresa</th>
											<th>Nombre empresa</th>
											<th>Seleccionar</th>
										</tr>
									</thead>
									<tbody id="nombreColumna">
										<c:forEach var="empresa" items="${empresasAutorizadas }">
											<tr>
												<td>${empresa.ID }</td>
												<td>${empresa.name}</td>
												 
												<td > 
													<div class="row">
														
                                                   		<div class="form__toggle" style="margin-left: 40px">
                                                        	<input type="checkbox" value="${empresa.ID }" name="empresa_filtrar" id="empresa_filtrar" />
                                                    	</div>
                                                    
                                            		</div>
												</td>
												
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
							</form>
						</div>
					</div>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" flush="true" /> </main>
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
	<script src="assets/js/funciones_op2_4_5.js"></script>
	<script src="assets/js/funciones.js"></script>
</body>
</html>
