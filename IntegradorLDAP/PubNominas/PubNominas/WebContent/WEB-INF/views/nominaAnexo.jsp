<%--
    Document   : AnexoTrabajadores
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
        <main>
           <jsp:include page="banner.jsp" flush="true" />
           
            <div class="guia" style="margin-top: -15px;margin-bottom: -15px">
            	<c:if test="${rol=='ejecutivo' || rol=='operador'}">
            		<div class="guia__container marco"> <a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a><a class="guia__link--active guia__link" href="#">Anexo de Trabajadores</a></div>
            	</c:if>
            	<c:if test="${rol=='encargado'}">
            		<div class="guia__container marco"><a class="guia__link" href="<c:url  value='/sv_inicio.do' />">Servicios en Línea</a><a class="guia__link" href="<c:url  value='/sv_planillas.do' />">Emisión de Planillas</a><a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a><a class="guia__link--active guia__link" href="#">Anexo de Trabajadores</a> </div>
            	</c:if>
                
            </div>
            <section class="container-fluid contenido-interior">
                <div class="marco">
                    <div class="row">
                        <jsp:include page="menu.jsp" flush="true" />
                        
                        <div class="col-lg-9 col-xs-12">
                            <div class="contenido-dinamico">
                                <h1>Anexo Trabajadores</h1>
                               


                                    <form class="form" action="ArsdocQueryServlet" id="form-nomina" target="iaccion">
                                        <input type="hidden" name="_display_fields" size="100" value="Periodo,RutEmpresa:Rut Empresa,RazonSocial:Razon Social,CodigoOficina:Codigo Oficina,CodigoSucursal:Codigo Sucursal,CodigoAnexo:Codigo Anexo">
										<input type="hidden" name="_alignDisplayFields" size="100" value="Periodo:center,CodigoOficina:center,CodigoSucursal:center">
										<input type="hidden" name="_folder" value="Anexos Asfam PDF:Anexos Asfam PDF ">	
										<input type="hidden" name="_max_hits" value="200">
										<input type="hidden" name="_sort_field_order" value="Periodo:D,RutEmpresa:A,CodigoOficina:A,CodigoSucursal:A">
										<input type="hidden" name="_html" value="araucana.htm">
										<input type="hidden" name="_dataTypes" size="100" value="Periodo:date,RutEmpresa:int,RazonSocial:string,CodigoOficina:int,CodigoSucursal:int,CodigoAnexo:int">	
										<input type="hidden" name="_dateFormats" value="Periodo:yyyyMM">
										<input type="hidden" name="_sqlOperators" size="100" value="Periodo:=,RutEmpresa:in,RazonSocial:like,CodigoOficina:=,CodigoSucursal:=,CodigoAnexo:=">
										<input type="hidden" name="_applicationGroup" size="100" value="Anexos Asfam PDF2,Anexos Asfam PDF2">	
										<input type="hidden" name="_nameAdditionalField" value="CodigoAnexo">
										<input type="hidden" name="_dateAdditionalField" value="">
										<input type="hidden" name="_nameComparedField" value="">
										<input type="hidden" name="_excludedFieldsInGet" value="RazonSocial">
										<input type="hidden" name="Periodo" value="${periodo }">
										<input type="hidden" name="RutEmpresa" value="${rutEmpresas }">
										<input type="hidden" name="RazonSocial" value="">
										<input type="hidden" name="CodigoOficina" value="${oficina }">
										<input type="hidden" name="CodigoSucursal" value="${sucursal }">

                                        <div class="alerta alerta--error" id="errores_form-nominas" style="display:none"></div>
                                       
        								
                                    </form>

								<iframe name="iaccion" id="iaccion" width="100%" height="500px" style="border:none" style="margin-top: -40px"></iframe>
                                <div class="separador--big"></div>
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
        <script src="assets/js/funciones_op2_4_5.js"></script>
         <script src="assets/js/funciones.js"></script>
        
         <c:if test="${cabeceras.size() == null && mensaje==null}">
        	
        	<script type="text/javascript">
        		$(document).ready(function() {
					callNominasOndemand();
				});
        	</script>
        </c:if>
        
    </body>
</html>
