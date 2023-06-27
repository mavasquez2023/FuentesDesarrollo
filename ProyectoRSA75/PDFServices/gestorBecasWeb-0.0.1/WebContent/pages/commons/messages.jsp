<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<center>

	<div id="zona_mensajes" style="display:none;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_transparente">
			<tr> 
		        <td valign="top" align="left">
					<html:errors/>
				
					<!--  mensajes -->
					<logic:present name="message.normal">
						<div align="center" class="mensaje" id="mensajeBox" style="display:none;">
							<b><bean:message key='<%= (String)request.getAttribute("message.normal") %>'/></b>
							<logic:present name="message.normal.info"><br/><bean:write name="message.normal.info"/></logic:present>
						</div>
						
						<script language="javascript">
							$(document).ready(function(){
								 show( "mensajeBox" );
								 desplegar( "zona_mensajes" );
								}
							);
						</script>
																
					</logic:present>
						
					<!--  alertas  -->
					<logic:present name="message.alert">
						<div  align="center" class="alerta" id="alertaBox" style="display:none;">
							<b><bean:message key='<%= (String)request.getAttribute("message.alert") %>'/></b>
							<logic:present name="message.alert.info"><br/><bean:write name="message.alert.info"/></logic:present>
						</div>
						
						<script language="javascript">
							$(document).ready(function(){
								 show( "zona_mensajes" );
								 desplegar( "alertaBox" );
								}
							);
						</script>
										
					</logic:present>
					
					<!-- errores -->
					<logic:present name="message.error">
						<div  align="center" class="error" id="errorBox" style="display:none;">
							<b><bean:message key="message.error"/><br/></b>
							<bean:message key='<%= (String)request.getAttribute("message.error") %>'/><br/>
							<logic:present name="message.error.info"><br/><bean:write name="message.error.info"/></logic:present>
							<logic:present name="message.error.infolist">
								<logic:iterate id="item" name="message.error.infolist">
									<li><bean:write name="item"/></li>
								</logic:iterate>
							</logic:present>
						</div>
						
						<script language="javascript">
							$(document).ready(function(){
								 show( "zona_mensajes" );
								 desplegar( "errorBox" );
								}
							);
						</script>
										
					</logic:present>
					
					<!-- errores Ajax -->
					<div align="center" class="error" id="ajaxErrorBox" style="display:none;">
						<b><bean:message key="message.error"/><br/></b>
						<div id="ajaxErrorMessages"></div>
					</div>
					
					<!-- Errores modales -->
					<div align="center" class="errorModal" id="modalErrorBox" style="display:none;">
							<b><bean:message key="message.error"/></b>
							<div id="modalErrorMessages"></div>
							<br/>
							<div align="center"><a href="#" id="botonModalErrorBox" class="links"><bean:message key="button.aceptar"/></a></div>
					</div>
				</td>
			</tr>
		</table>
	</div>

</center>


