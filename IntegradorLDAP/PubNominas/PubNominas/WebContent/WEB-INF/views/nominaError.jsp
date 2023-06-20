<%--
    Document   : Nomina Cotizaciones y Aporte
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
        
            <div class="guia">
            <div class="guia__container marco"><a class="guia__link" href="<c:url  value='/sv_inicio.do' />">Servicios en Línea</a><a class="guia__link" href="<c:url  value='/sv_planillas.do' />">Emisión de Planillas</a><a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a><a class="guia__link--active guia__link" href="#">Declaración y Pago Cotizaciones</a>
            </div>
            </div>
            <section class="container-fluid contenido-interior">
                <div class="marco">
                    <div class="row">
                        <jsp:include page="menu.jsp" flush="true" />
                        
                        <div class="col-lg-9 col-xs-12">
                            <div class="contenido-dinamico">
                                <h1>Ups, ha ocurrido un error</h1>
                               	<div class="bloque" style="margin-top: -30px;">
                                    <p>Favor intenta nuevamente</p>
                                </div>
                                <c:if test="${mensaje != null }"> <h3>${mensaje} </h3>
								</c:if>
								
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
        <script src="assets/js/funciones_op1_3.js"></script>
        <script src="assets/js/funciones.js"></script>
        
    </body>
</html>
