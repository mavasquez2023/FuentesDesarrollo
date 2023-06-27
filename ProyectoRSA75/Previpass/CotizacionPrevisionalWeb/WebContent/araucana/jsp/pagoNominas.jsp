<%@ include file="/html/comun/taglibs.jsp"%>
<html:form action="/PagarComprobantes" styleId="formulario">
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="procesos" />
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="pagoNominas" />
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
      	<html:messages id="msg" message="true">
      		<br />
			<div class="msgExito">${msg}</div>
		</html:messages>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<nested:notPresent property="consulta">
	        	  	<tr align="center">
		        		<td height="30" bgcolor="#FFFFFF" class="titulo">
		        			<div align="center">
		        				<strong>No hay n&oacute;minas por pagar</strong>
		        			</div>
		        		</td>
			        </tr>
				</nested:notPresent>
				<nested:present property="consulta">
					<logic:notEmpty name="PagarActionForm" property="consulta">
					<tr align="center">
						<td height="16" valign="top">
							<table width="100%" border="0" cellpadding="0" cellspacing="1">
								<tr valign="bottom">
									<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>N&oacute;minas a Pagar</strong>
								</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
								<tr class="subtitulos_tablas">
									<td width="90" align="center" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
									<td width="300" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
									<td width="120" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
									<td width="80" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Total</td>
								</tr>
								<nested:iterate id="filaConsulta" property="consulta" indexId="nFila" type="cl.araucana.cp.presentation.struts.javaBeans.LineaComprobantesAPagar">
									<c:choose>
							   		    <c:when test="${nFila % 2 == 0}">
							   		    	<c:set var="estilo" value="textos_formularios"/>
						   		    		<c:set var="estilo2" value="tablaClaroBordes"/>
						   		    	</c:when>
				   						<c:otherwise>
				   							<c:set var="estilo" value="textos-formcolor2"/>
				   							<c:set var="estilo2" value="tablaOscuroBordes"/>
				   						</c:otherwise>
									</c:choose>
									<tr>
										<td height="20" align="left" valign="middle" class="${estilo}">
											<nested:hidden property="codigoBarra" />
											<nested:write property="rutFmt" />
										</td>
										<td class="${estilo}">
											<nested:write property="razonSocial" />
										</td>
										<td class="${estilo}">
											<nested:write property="nombreConvenio" />
										</td>
										<td class="${estilo}">
											<div align="right">
												<nested:write property="total" format="$ ###,###,###,###" />
											</div>
										</td>
									</tr>
								</nested:iterate>
							</table>
							<input type="hidden" name="nNominas" value="6" />
						</td>
					</tr>
					<tr>
						<td>
							<br />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="center" valign="middle">
			                      		<html:submit property="operacion" value="${pagoCaja}" styleClass="btn4" onclick="javascript:pagarEnCaja();"/>
			                      		<html:submit property="operacion" value="${pagoLinea}" styleClass="btn4" onclick="javascript:pagarEnLinea();"/>
									</td>
								</tr>
							</table>
							<br />
						</td>
					</tr>
					</logic:notEmpty>
				</nested:present>
			</table>
		</td>
	</tr>
	<tr>
		<td width="170">&nbsp;</td>
	</tr>
</table>
</html:form>
<script language="javascript">

function pagarEnCaja(){
	formu = document.getElementById("formulario");
	formu.target = "_top";
	return true;

}
function pagarEnLinea(){
	formu = document.getElementById("formulario");
	formu.target = "popup";
	return true;

}
</script>
