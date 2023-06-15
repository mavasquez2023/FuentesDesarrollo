<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>



<div class="menu">
	<table cellspacing="0" cellpadding="0" width="100%" border="0" height="80px">
		<tbody>
		<tr>
			<td width="100%">
				<p align="center">
					<span style="font-size: 14px"><b>Menú<br>Div. Previsional</b></span>
				</p>
				<ul>
				<li>
					<html:link action="DivisionPrevisionalPage.do?step=homeDivisionPrevisional">
					<span style="font-size: 14px">Inicio</span>
					</html:link>
				</li>
				<li>
					<html:link action="Consulta1.do">
					<span style="font-size: 14px">Consultar Estado Propuesta</span>
					</html:link>
				</li>
				<li>
					<html:link action="Consulta2.do">
					<span style="font-size: 14px">Consultar Propuesta Web</span>
					</html:link>
				</li>
				<li>
					<html:link action="archivoholding.do">
					<span style="font-size: 14px">Cargar Archivo Recibido</span>
					</html:link>
				</li>
				<li>
					<html:link action="estadisticaProceso.do">
					<span style="font-size: 14px">Estadistica Proceso</span>
					</html:link>
				</li>

                 <c:if test="${sessionScope.flagAdmin == 1}">

				  <li>
                     <html:link action="/CambioTramoFecPage.do">
                     <span style="font-size: 14px">Configurar fechas de proceso</span>
                     </html:link>
                  </li>
                
                  <li>
                     <html:link action="/EstadosProcesamientosDP.do">
                     <span style="font-size: 14px">Reporte de procesamientos DP</span>
                     </html:link>
                  </li>
                
                 </c:if>
                 		
                <li>
                     <html:link action="/estadoprocesamiento.do">
                     <span style="font-size: 14px">Estados de procesamiento</span>
                     </html:link>
                </li>
				<li>
					<a href="#" onclick="window.open('<%=request.getContextPath()%>/edocs/LaAraucana_ActTramos_ManualDivPrev1.pdf', '_blank','toolbar=0,menubar=0,resizable=1,width=800,height=600');">
					<span style="font-size: 14px">Ayuda</span>
					</a>
				</li>
				<li>
					<html:link  page="/logout"> 
					<span style="font-size: 14px">Cerrar sesi&oacute;n</span>
					</html:link>
				</li>
				</ul>
			</td>
		</tr>
		</tbody>
	</table>
</div>