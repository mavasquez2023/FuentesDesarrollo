<style>
.salir {
	font-family: Verdana, Arial, sans-serif; 
	font-size: 10px; 
	font-weight: bold; 
	color: yellow;
}
</style>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>		
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="bbg" style="height: 21px; width: 640px; vertical-align: top">
									&nbsp;&nbsp;&nbsp; 
									<html:link page="/index.jsp" styleClass="mainlink">Inicio</html:link> 
									<span class="divider">&nbsp;|&nbsp;</span>
									<span class="selectedmainlink">Servicios</span>
									<span class="divider">&nbsp;|&nbsp;</span> 
									<html:link page="/deprecated/uc.html" styleClass="mainlink">Registrarse</html:link> 
									<span class="divider">&nbsp;|&nbsp;</span> 
									<html:link page="/deprecated/uc.html" styleClass="mainlink" >Preguntas Frecuntas</html:link>
									<span class="divider">&nbsp;|&nbsp;</span> 
									<html:link page="/deprecated/uc.html" styleClass="mainlink" >Cont&aacute;ctenos</html:link> 
									<span class="divider">&nbsp;|&nbsp;</span> 
									<span>
										<html:link forward="logout" styleClass="salir">
											Salir
										</html:link>
									</span>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>


</body>
</html>
