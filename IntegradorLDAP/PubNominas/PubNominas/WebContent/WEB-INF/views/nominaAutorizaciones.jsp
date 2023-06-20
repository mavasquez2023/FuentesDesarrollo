<%--
    Document   : NominaAutorizaciones
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
        <link rel="stylesheet" href="assets/css/certificado.css"/>
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
        <main>
            <jsp:include page="banner.jsp" flush="true" />
            
            <div class="guia" style="margin-top: -15px;margin-bottom: -15px">
            	<c:if test="${rol=='ejecutivo' || rol=='operador'}">
            		<div class="guia__container marco"> <a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a><a class="guia__link--active guia__link" href="#">Autorizaciones Cargas del Mes</a></div>
            	</c:if>
            	<c:if test="${rol=='encargado'}">
            		<div class="guia__container marco"><a class="guia__link" href="<c:url  value='/sv_inicio.do' />">Servicios en Línea</a><a class="guia__link" href="<c:url  value='/sv_planillas.do' />">Emisión de Planillas</a><a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a><a class="guia__link--active guia__link" href="#">Autorizaciones Cargas del Mes</a> </div>
            	</c:if>
            
            </div>
            <section class="container-fluid contenido-interior">
                <div class="marco">
                    <div class="row">
                        <jsp:include page="menu.jsp" flush="true" />
                        <div class="col-lg-9 col-xs-12">
                            <div class="contenido-dinamico" >
                                <h1>Autorizaciones Cargas del Mes</h1>
                                <div class="bloque" style="margin-top: -30px;">
                                    <form class="form" action="autorizaciones.do" id="form-nomina" method="post" >
                                    	 <div class="row">
                                            <div class="col-xs-12 col-md-6 col-lg-4">
                                                <div class="form__grupo js-periodo " data-animacion="placeholder"><span class="icono fln-abajo"></span>
                                                    <select class="requerido" name="tipomov" id="tipomov" onchange="callNominas();">
                                                        <option value="AUT" selected="selected">Autorizaciones Asfam</option>
                                                        <option value="MOD">Nómina Modificaciones Asfam</option>
                                                    </select>
                                                    <label for="tipo-autorizacion"> Tipo Autorización</label>
                                                </div>
                                            </div>
                                        </div>
                                       
                                        <div class="alerta alerta--error" id="errores_form-autorizaciones" style="display:none"></div>
                                        
                                        <input type="hidden" id="rutEmpresa" name="rutEmpresa" value="${rutEmpresas }" />
                                        <input type="hidden" id="periodo" name="periodo" value="${periodo }" />
                                        <input type="hidden" id="oficina" name="oficina" value="${oficina }" />
                                        <input type="hidden" id="sucursal" name="sucursal" value="${sucursal }" />
                                    </form>
                                </div>
                                <c:if test="${mensaje != null }"> <h3>${mensaje} </h3>
								</c:if>
								<c:if test="${cabeceras.size() > 0 }">
                                <div class="tabla tabla--full tabla--borde tabla--responsive" style="margin-top: -50px;">
                                    <table class="nomina">
                                        <thead>
                                            <tr class="nomina">
											<th class="nomina">Periodo</th>
											<th class="nomina nomina_rut">Rut Empresa</th>
											<th class="nomina">Razón social</th>
											<th class="nomina nomina_rut">Rut Afiliado</th>
											<th class="nomina">Nombre Afiliado</th>
											<th class="nomina">Oficina</th>
											<th class="nomina">Sucursal</th>
											<th class="nomina">Descargar</th>
										</tr>
                                        </thead>
									<tbody id="nombreColumna">
										<c:forEach items="${cabeceras}" var="cab" varStatus="numreg">
											<tr class="nomina">
												<td class="nomina" >${cab.periodo_actual}</td>
												<td class="nomina" >${cab.rutEmpresa}-${cab.dvEmpresa}</td>
												<td class="nomina" >${cab.razonSocial}</td>
												<td class="nomina" >${cab.rutAfiliado}-${cab.dvAfiliado}</td>
												<td class="nomina" >${cab.nombreAfiliado}</td>
												<td class="nomina" >${cab.oficina}</td>
												<td class="nomina" >${cab.sucursal}</td>
												<td class="nomina" >
												<!-- a  href="<c:url value='/reporteAUT.do?rutemp=${cab.rutEmpresa}&periodo=${cab.periodo}&rutafi=${cab.rutAfiliado}'/>">
													<button id="reg${numreg.count }" type="button" class="boton" onclick="lock(this, '${numreg.count}');">descargar</button>
												</a-->
												
												<a id="reg${numreg.count }" class="tabla__icon fln-pdf-light" href="<c:url value='/reporteAUT.do?rutemp=${cab.rutEmpresa}&periodo=${cab.periodo}&rutafi=${cab.rutAfiliado}'/>" onclick="lock(this, '${numreg.count}');"></a>
												 
												</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
                                </div>
                                </c:if>
                                <div id="resultado"></div>
                                <div class="separador--big oculto-xs block-lg"></div>
                            </div>
                        </div>
                    </div>
            </section>
            <jsp:include page="footer.jsp" flush="true" />
        </main>
        
        <div id="loading" style="position:fixed; top:35%; display:none; left:52%;  z-index: auto" >
			<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
		</div>
        <div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
        <script src="assets/js/polyfill.js"></script>
        <script src="assets/js/jquery-3.4.1.js"></script>
        <script src="assets/js/jquery-ui.js"></script>
        <script src="assets/js/jquery.Rut.js"></script>
        <script src="assets/js/automatizar.js"></script>
        <script src="assets/js/lozad.js"></script>
        <script src="assets/js/jquery.fancybox.js"></script>
        <script src="assets/js/owl.carousel.js"></script>
        <script src="assets/js/fln.js"></script>
        <script src="assets/js/funciones.js"></script>
        
        <c:if test="${cabeceras.size() == null && mensaje==null}">
        	
        	<script type="text/javascript">
        		$(document).ready(function() {
					callNominas();
				});
        	</script>
        </c:if>
    </body>
</html>
