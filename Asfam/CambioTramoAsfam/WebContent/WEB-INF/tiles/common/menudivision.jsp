<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="menu">
	<table cellspacing="0" cellpadding="0" width="100%" border="0" height="80px">
		<tbody>
			<tr>
				<td width="100%">
					<p align="center">
						<b>Menu</b>
					</p>
					<ul>
						
						<li>
							<html:link action="DivisionPrevisionalPage.do?step=homeDivisionPrevisional" ><span style="font-size: 14px">Inicio</span></html:link>
						</li>
						<li><html:link action="/DescargaPropuestas.do" ><span style="font-size: 14px">
							<c:if test="${parametros.tipoDescarga=='PROPUESTA' }">
								Descargar Propuesta
							</c:if>
							<c:if test="${parametros.tipoDescarga=='INFORME' }">
								Descargar Informe
							</c:if>
							</span></html:link>
						</li>
						<c:if test="${parametros.tipoDescarga=='PROPUESTA' }">
						<li>
							<html:link action="/AceptaPropuesta.do"><span style="font-size: 14px">Enviar Declaración de Renta</span></html:link>
						</li>
						<li>
							<html:link action="/estadisticaProceso.do"><span style="font-size: 14px">Estadistica Proceso</span></html:link>
						</li>
						</c:if>
						<!-- 
						<li>
							<html:link action="DivisionPrevisionalPage.do?step=homeDivisionPrevisional">
								Inicio
							</html:link>
						</li> 
						<li>
							<html:link action="Consulta1.do">
								Consultar Estado Propuesta
							</html:link>
						</li>
						<li>
							<html:link action="Consulta2.do">
								Consultar Propuesta Web
							</html:link>
						</li>
						<li>
							<html:link action="archivoholding.do">
								Cargar Archivo Recibido
							</html:link>
						</li>
						-->
						<li>
						
							<html:link action="logout.do">
								<span style="font-size: 14px;color:blue">Cerrar Sesi&oacute;n</span>
							</html:link>
						</li>
					</ul>
				</td>
			</tr>
		</tbody>
	</table>
</div>