<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.salir {
	font-family: Verdana, Arial, sans-serif; 
	font-size: 10px; 
	font-weight: bold; 
	color: yellow;
}
</style>

<script language="JavaScript"> 
function abrir(url){ 
	if (window.open("<%=request.getContextPath()%>" + "/pages/demo/" + url, '', 'fullscreen')){ 
	} 
} 
</script>

<table border="0" style="width : 100%; height: 60px;" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td>
				<table style="width : 160px;" cellpadding="0" cellspacing="0">
					<tr>
						<td rowspan="2" valign="top">
							<html:img page="/img/EA.jpg"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
			
		<tr>
			<td>
				<table cellpadding="0" cellspacing="0" style="width : 100%;" border="0">
					<tr>
						<td style="height: 21px; vertical-align: bottom;">

							<!-- Main Link -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td class="bbg" style="height: 21px; vertical-align: top" align="center">
										<!--
											<html:link href="http://www.cp.cl" styleClass="mainlink">Inicio</html:link> 
											<span class="divider">&nbsp;|&nbsp;</span>
										-->
											<html:link forward="home" styleClass="mainlink">Servicios</html:link>
											<span class="divider">&nbsp;|&nbsp;</span> 
											<html:link forward="faq" styleClass="mainlink" >Preguntas Frecuentes</html:link>
											<span class="divider">&nbsp;|&nbsp;</span> 
											<html:link href=" http://www.laaraucana.cl/?seccion=159&nom=" styleClass="mainlink" >Cont&aacute;ctenos</html:link> 	
											<span class="divider">&nbsp;|&nbsp;</span>
											<a href="javascript:abrir('full_sistema.htm')" class="mainlink">Demo</a> 
											<logic:present name="ea_user_profile" scope="session">
												<span class="divider">&nbsp;|&nbsp;</span> 
												<span>
													<html:link forward="logout" styleClass="salir">
														Salir
													</html:link>
												</span>
											</logic:present>
										</td>
									</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</tbody>
</table>

