<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <div class="col-lg-3 oculto-xs block-lg">
                            <div class="menu-lateral menu-desplegable">
                                <ul>
                                	<c:if test="${menu=='inicio'}">
                                    	<li><a class="activo" href="#">Filtro de b�squeda</a></li>
                                    </c:if>
                                    <c:if test="${menu!='inicio'}">
                                    	<a href="init.do">Filtro de b�squeda</a>
                                    </c:if>
                                    <c:if test="${menu=='cotizacion'}">
                                    	<li><a class="activo" href="#">Declaraci�n y pago cotizaciones</a></li>
                                    </c:if>
                                    <c:if test="${menu!='cotizacion'}">
                                    	<a href="initcot.do">Declaraci�n y pago cotizaciones</a>
                                    </c:if>
                                    <c:if test="${menu=='credito'}">
                                    	<li><a class="activo" href="#">N�minas de cr�dito</a></li>
                                    </c:if>
                                    <c:if test="${menu!='credito'}">
                                    	<a href="initcre.do">N�minas de cr�dito</a>
                                    </c:if>
                                    <c:if test="${menu=='pex'}">
                                    	<li><a class="activo" href="#">Saldo a favor</a></li>
                                    </c:if>
                                    <c:if test="${menu!='pex'}">
                                    	<a href="initpex.do">Saldo a favor</a>
                                    </c:if>
                                    <c:if test="${menu=='leasing'}">
                                    	<li><a class="activo" href="#">N�minas ahorro y leasing</a></li>
                                    </c:if>
                                    <c:if test="${menu!='leasing'}">
                                    	<a href="initlea.do">N�minas ahorro y leasing</a>
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
                                    	<a class="activo" href="#">Estad�sticas de descarga</a>
                                    </c:if>
                                    <c:if test="${menu!='estadisticas' && rol=='operador'}">
                                    	<a href="estadisticas.do">Estad�sticas de descarga</a>
                                    </c:if>
                                    <c:if test="${menu=='soporte'}">
                                    	<a class="activo" href="<c:url value='<%=cl.laaraucana.pubnominas.utils.Configuraciones.getConfig("url.soporte.nominas")%>' />">Soporte n�minas</a>
                                    </c:if>
                                    <c:if test="${menu!='soporte'}">
                                    	<a target="_blank" href="<c:url value='<%=cl.laaraucana.pubnominas.utils.Configuraciones.getConfig("url.soporte.nominas")%>' />">Soporte n�minas</a>
                                    </c:if>
                                    <!--
                                    <li><a href="CartaDeRenovacionCargasFamiliares">Cartas de Renovaci�n Cargas Familiares</a></li>
                                     -->
                                </ul>
                            </div>
 </div>