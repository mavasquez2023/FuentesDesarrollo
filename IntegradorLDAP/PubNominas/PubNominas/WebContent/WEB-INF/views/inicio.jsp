<%--
    Document   : NominaDeCredito
    Created on : 19-jul-2022
    Author     : J-Factory
--%>
<%@page import="cl.laaraucana.pubnominas.utils.AvisoSingleton"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="iso-8859-1"/>
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
			<c:if test="${rol=='ejecutivo' || rol=='operador'}">
            	<div class="guia__container marco"> <a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a></div>
            </c:if>
            <c:if test="${rol=='encargado'}">
            	<div class="guia__container marco"><a class="guia__link" href="<c:url  value='/sv_inicio.do' />">Servicios en Línea</a><a class="guia__link" href="<c:url  value='/sv_planillas.do' />">Emisión de Planillas</a><a class="guia__link" href="<c:url  value='/init.do' />">Inicio</a></div>
            </c:if>
		
	</div>
	<section class="container-fluid contenido-interior">
		<div class="marco">
			<div class="row">
				<jsp:include page="menu.jsp" flush="true" />
				<div class="col-lg-9 col-xs-12">
					<div class="contenido-dinamico">
						<h1>Impresión de Nóminas en PDF <span id="periodo" class="color--gris80 ">(${periodo })</span></h1>
						

						<p>Aquí podrá imprimir documentos para cumplir con la obligación de cotizar
						 y pagar sus compromisos con La Araucana.</p>
						<c:if test="${rol=='encargado'}">
						<%if(AvisoSingleton.getInstance().isActivo()){ %>
						<div class="desplegable" style="margin-bottom: 20px">
                                    <span class="desplegable__cabecera desplegable__cabecera--aviso">Aviso importante</span>
                                    <div class="desplegable__contenido">
                                        <div class="bloque bloque--aviso" >
                                            <section class="bloque__section" >
                                                <p style="margin-top: -30px"><%=AvisoSingleton.getInstance().getParrafo1()%></p>
                                                <%if(AvisoSingleton.getInstance().getParrafo1() !=null && !AvisoSingleton.getInstance().getParrafo1().trim().equals("")){ %>
                                                <p style="margin-bottom: -15px"><%=AvisoSingleton.getInstance().getParrafo2() %></p>
                                                <%} %>
                                            </section>
                                            <div class="bloque__subnota"><%=AvisoSingleton.getInstance().getNota() %></div>
                                        </div>
                                    </div>
                                </div>
                          <%} %>
                          </c:if>
                                    <form class="form" action="" id="form-nominas">
                                        <div class="row">
                                        	<c:if test="${rol=='ejecutivo' || rol=='operador'}">
											<div class="col-xs-12 col-md-6 col-lg-4">

												 	 <div class="form__grupo js-rut-empresa " data-animacion="placeholder"><span class="form__grupo--help"></span>
                                               		<input class="text tipo_rut rut requerido" type="text" name="rut-empresa" id="rut-empresa" value="${rutEmpresa }" onchange="setRutEmpresa(this.value);" />
                                                    <label for="rut-empresa">RUT Empresa</label>
                                                    </div>
                                            </div>
												<div class="col-xs-12 col-md-6 col-lg-6" >
													<div class="form__grupo js-rut-empresa " data-animacion="placeholder">
                                                    <input class="text tipo_text" type="text" name="razonSocial" id="razonSocial" value="${razonSocial} " disabled="disabled"/>
                                                   	<label for="razonSocial">Razón Social</label>
                                                   	</div>
                                           		</div>
                                            </div>
                                            <div class="row">
                                            <div class="col-xs-12 col-md-4 col-lg-10" style="margin-bottom: -20px">
                                               		<div style="text-align: right;"><a class="btn btn--secundario btn--block-xs btn--inline-md btn--relevante" href="#" onclick="limpiarRUT();">Limpiar</a>
                                            		</div>
                                            </div>
                                            </div>
                                            <div class="row">
                                            </c:if>
                                            <div class="col-xs-12 col-md-6 col-lg-4" >
                                                <div class="form__grupo js-periodo " data-animacion="placeholder"><span class="icono fln-abajo"></span>
                                                    <select name="periodo" id="periodo" onchange="mostrarContenido('.js-periodo-personalizado', this.value === '0');setPeriodo(this.value, '');">
                                                        <option value="${periodo_actual }" <c:if test="${periodo==periodo_actual }">selected='selected'</c:if> >Mes actual</option>
                                                        <option value="${periodo_anterior }" <c:if test="${periodo==periodo_anterior }">selected='selected'</c:if> >Mes anterior</option>
                                                        <option value="0" <c:if test="${periodo!=periodo_actual && periodo!=periodo_anterior}">selected='selected'</c:if>>Período personalizado</option>
                                                    </select>
                                                    <label for="periodo">Seleccione un periodo</label>
                                                </div>
                                            </div>
                                         </div>
                                        <div class="js-periodo-personalizado" <c:if test="${periodo==periodo_actual || periodo==periodo_anterior}">style='display:none'</c:if>>
                                            
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6 col-lg-3">
                                                    <div class="form__grupo js-mes-inicio " data-animacion="placeholder"><span class="icono fln-abajo"></span>
                                                        <select class="requerido" name="mes-inicio" id="mes-inicio" onchange="setPeriodo(this.value, 'mes');">
                                                            <option value="" hidden="hidden"></option>
                                                            <option value="01" <c:if test="${mes=='01'}">selected='selected'</c:if>>Enero</option>
                                                            <option value="02" <c:if test="${mes=='02'}">selected='selected'</c:if>>Febrero</option>
                                                            <option value="03" <c:if test="${mes=='03'}">selected='selected'</c:if>>Marzo</option>
                                                            <option value="04" <c:if test="${mes=='04'}">selected='selected'</c:if>>Abril</option>
                                                            <option value="05" <c:if test="${mes=='05'}">selected='selected'</c:if>>Mayo</option>
                                                            <option value="06" <c:if test="${mes=='06'}">selected='selected'</c:if>>Junio</option>
                                                            <option value="07" <c:if test="${mes=='07'}">selected='selected'</c:if>>Julio</option>
                                                            <option value="08" <c:if test="${mes=='08'}">selected='selected'</c:if>>Agosto</option>
                                                            <option value="09" <c:if test="${mes=='09'}">selected='selected'</c:if>>Septiembre</option>
                                                            <option value="10" <c:if test="${mes=='10'}">selected='selected'</c:if>>Octubre</option>
                                                            <option value="11" <c:if test="${mes=='11'}">selected='selected'</c:if>>Noviembre</option>
                                                            <option value="12" <c:if test="${mes=='12'}">selected='selected'</c:if>>Diciembre</option>
                                                        </select>
                                                        <label for="mes-inicio">Mes</label>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 col-md-6 col-lg-3">
                                                    <div class="form__grupo js-ano-inicio " data-animacion="placeholder"><span class="icono fln-abajo"></span>
                                                        <select class="requerido" name="ano-inicio" id="ano-inicio" onchange="setPeriodo(this.value, 'ano');">
                                                            <option value="" hidden="hidden"></option>
                                                            <c:forEach var="year" items="${Anios }">
                                                            	<option value='${year }' <c:if test="${ano==year}">selected='selected'</c:if>>${year }</option>
                                                            </c:forEach>
                                                        </select>
                                                        <label for="ano-inicio">Año</label>
                                                    </div>
                                                </div>
                                                 <c:if test="${ rol=='operador'}">
                                                 	<div class="col-xs-12 col-md-6 col-lg-2">

												 	 	<div class="form__grupo js-rut-empresa " data-animacion="placeholder"><span class="form__grupo--help"></span>
                                               			<input class="text tipo_int " type="text" name="cantidad_anios" id="cantidad_anios" value="${cantidad_anios }" autocomplete="off" onchange="setAnios(this.value);" />
                                                    	<label for="cantidad_anios">Cantidad Años</label>
                                                    	</div>
                                            		</div>
                                                 </c:if>
                                               
                                            </div>
                                        </div>
                                        <!--  
                                        <c:if test="${rol=='ejecutivo' || rol=='operador'}">
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
                                                <div class="col-xs-12 col-md-6 col-lg-3">
                                                    <div class="form__grupo js-codigo-sucursal " data-animacion="placeholder"><span class="form__grupo--help"></span>
                                                        <input class="text requerido" type="text" name="codigo-nomina" id="codigo-nomina"/>
                                                        <label for="codigo-sucursal">Código Nomina</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                            
                            </c:if>
                            -->
                            <c:if test="${ rol=='operador'}">
						
						<div class="row" >
                                            <div class="col-xs-12 col-md-6 col-lg-4">
                                               
                                                <div class="form__seleccion js-opciones-avanzadas">
                                                    <div class="form__toggle">
                                                        <input type="checkbox" name="opciones-aviso" id="opciones-aviso" onchange="activarAviso(this.checked);" 
                                                        <%if(AvisoSingleton.getInstance().isActivo()){ %>
                                                        	checked='checked'
                                                        <%} %>/>
                                                        
                                                        <label for="opciones-aviso">Aviso activo</label>
                                                    </div>
                                                </div>
                                            </div>
                         </div>
						<div class="desplegable">
                                    <span class="desplegable__cabecera--aviso">Aviso importante</span>
                                    <div class="desplegable__contenido">
                                        <div class="bloque bloque--aviso" >
                                            <section class="bloque__section" >
                                                <p style="margin-top: -30px"><input type="text" size="90" name="parrafo1" id="parrafo1" style="margin-top: -30px" value="<%=AvisoSingleton.getInstance().getParrafo1()%>" /></p>
                                                <p style="margin-top: -15px"><input type="text" size="90" name="parrafo1" id="parrafo2" style="margin-top: -30px" value="<%=AvisoSingleton.getInstance().getParrafo2()%>" /></p>
                                            </section>
                                            <p><input type="text" size="90" name="nota" id="nota" value="<%=AvisoSingleton.getInstance().getNota() %>" /></p>
                                        </div>
                                    </div>
                                </div>
                          </c:if>
                          	<div class="alerta alerta--info" id="aviso" style="display:none"></div>
                                    </form>
                            
							<c:if test="${rol=='encargado' }">
							<div class="row" style="margin-top: -50px">
								<div class="col-xs-12 col-md-6 col-lg-6">
									<c:if test="${filtro=='0' || filtro=='-1'}">
										<h4>Encargado de Empresas:</h4>
									</c:if>
									<c:if test="${filtro=='1'}">
										<h4>Empresas a Descargar:</h4>
									</c:if>
								</div>
								<div class="col-xs-12 col-md-6 col-lg-3" >
									
										<c:if test="${filtro=='0' }">
										<div class="text-align-left-xs" style="margin-top: 20px;margin-right: 10px">
										<a 
											class="btn btn--small btn--secundario btn--block-xs btn--inline-md btn--relevante"
											href="<c:url  value='/seleccion.do' />">Seleccionar empresas</a>
										</div>
										</c:if>
										<c:if test="${filtro=='1' }">
										<div class="text-align-left-xs" style="margin-top: 20px;margin-left: 15px">
										<a 
											class="btn btn--small btn--secundario btn--block-xs btn--inline-md btn--relevante"
											href="<c:url  value='/limpiarFiltro.do' />">Limpiar selección</a>
										</div>
										</c:if>
									
								</div>
							</div>
						
							<div class="tabla tabla--full tabla--borde tabla--responsive">
								<table style="width: 73%">
									<thead>
										<tr>
											<th>Rut empresa</th>
											<th>Nombre empresa</th>
										</tr>
									</thead>
									<tbody id="nombreColumna">
										<c:forEach var="empresa" items="${empresasFiltradas }">
											<tr>
												<td>${empresa.ID }</td>
												<td>${empresa.name}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</c:if>
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
	
	<script>
	$(document).ready(function() {
			$('#rut-empresa').keyup(function(){
        		$(this).val($(this).val().toUpperCase());
    		});
    		<c:if test="${aviso=='1' && (rol=='ejecutivo' || rol=='operador')}">
    			avisar('Debe ingresar el Rut de la Empresa.', 3000);
    		</c:if>
    		//$('#aviso').slideUp	();
		});
	</script>
</body>
</html>
