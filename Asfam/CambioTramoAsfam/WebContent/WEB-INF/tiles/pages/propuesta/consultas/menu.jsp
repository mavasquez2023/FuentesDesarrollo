<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div class="menu">
	<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
			<tr>
				<td width="100%">
					<p align="center">
						<b>Menu</b>
					</p>
					<ul>
						<li>
							<html:link action="DivisionPrevisionalPage.do?step=homeDivisionPrevisional">
								Inicio
							</html:link>
						</li>
						<li>
							<html:link action="DivisionPrevisionalPage.do?step=consultarEstadoAfiliados">
								Consultar Estado Propuesta
							</html:link>
						</li>
						<li>
							<html:link action="DivisionPrevisionalPage.do?step=consultarPropuestaWeb">
								Consultar Propuesta Web
							</html:link>
						</li>
						<li>
							<html:link action="archivoholding.do">
								Cargar Archivo Recibido
							</html:link>
						</li>
						<li>
							<html:link action="logout.do">
								Cerrar Sesi&oacute;n
							</html:link>
						</li>
					</ul>
				</td>
			</tr>
		</tbody>
	</table>
</div>