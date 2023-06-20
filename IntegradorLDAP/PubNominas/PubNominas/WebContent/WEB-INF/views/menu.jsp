<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <div class="col-lg-3 oculto-xs block-lg">
                            <div class="menu-lateral menu-desplegable">
                                <ul>
                                	<c:if test="${menu=='inicio'}">
                                    	<li><a class="activo" href="#">Filtro de búsqueda</a></li>
                                    </c:if>
                                    <c:if test="${menu!='inicio'}">
                                    	<a href="init.do">Filtro de búsqueda</a>
                                    </c:if>
                                    <c:if test="${menu=='cotizacion'}">
                                    	<li><a class="activo" href="#">Declaración y pago cotizaciones</a></li>
                                    </c:if>
                                    <c:if test="${menu!='cotizacion'}">
                                    	<a href="initcot.do">Declaración y pago cotizaciones</a>
                                    </c:if>
                                    <c:if test="${menu=='credito'}">
                                    	<li><a class="activo" href="#">Nóminas de crédito</a></li>
                                    </c:if>
                                    <c:if test="${menu!='credito'}">
                                    	<a href="initcre.do">Nóminas de crédito</a>
                                    </c:if>
                                    <c:if test="${menu=='pex'}">
                                    	<li><a class="activo" href="#">Saldo a favor</a></li>
                                    </c:if>
                                    <c:if test="${menu!='pex'}">
                                    	<a href="initpex.do">Saldo a favor</a>
                                    </c:if>
                                    <c:if test="${menu=='leasing'}">
                                    	<li><a class="activo" href="#">Nóminas ahorro y leasing</a></li>
                                    </c:if>
                                    <c:if test="${menu!='leasing'}">
                                    	<a href="initlea.do">Nóminas ahorro y leasing</a>
                                    </c:if>
                                    <c:if test="${menu=='anexo'}">
                                    	<li><a class="activo" href="#">Anexos trabajadores</a></li>
                                    </c:if>
                                    <c:if test="${menu!='anexo'}">
                                    	<a href="initanx.do">Anexos trabajadores</a>
                                    </c:if>
                                     <c:if test="${menu=='autorizacion'}">
                                    	<li><a class="activo" href="#">Autorizaciones cargas del mes</a></li>
                                    </c:if>
                                    <c:if test="${menu!='autorizacion'}">
                                    	<a href="initaut.do">Autorizaciones cargas del mes</a>
                                    </c:if>
                                    <c:if test="${menu=='estadisticas' && rol=='operador'}">
                                    	<a class="activo" href="#">Estadísticas de descarga</a>
                                    </c:if>
                                    <c:if test="${menu!='estadisticas' && rol=='operador'}">
                                    	<a href="estadisticas.do">Estadísticas de descarga</a>
                                    </c:if>
                                    <c:if test="${menu=='soporte'}">
                                    	<a class="activo" href="<c:url value='<%=cl.laaraucana.pubnominas.utils.Configuraciones.getConfig("url.soporte.nominas")%>' />">Soporte nóminas</a>
                                    </c:if>
                                    <c:if test="${menu!='soporte'}">
                                    	<a target="_blank" href="<c:url value='<%=cl.laaraucana.pubnominas.utils.Configuraciones.getConfig("url.soporte.nominas")%>' />">Soporte nóminas</a>
                                    </c:if>
                                    <!--
                                    <li><a href="CartaDeRenovacionCargasFamiliares">Cartas de Renovación Cargas Familiares</a></li>
                                     -->
                                </ul>
                            </div>
 </div>