<%@ include file="../comun/tld.jsp"%>
<%	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<logic:equal name="opcion" value="consumo">
	<logic:equal name="error" value="ok">
		<logic:iterate id="contrato" name="listaContratos">
			<tr>
				<td><bean:write name="contrato" property="origen" />
				</td>
				<td><bean:write name="contrato" property="idContrato" />
				</td>
				<td><bean:write name="contrato" property="lineaComercial" />
				</td>
				<td><fmt:formatNumber maxFractionDigits="3">
						<bean:write name="contrato" property="tasaInteres" />
					</fmt:formatNumber>
				</td>
				<td><fmt:formatNumber maxFractionDigits="0">
						<bean:write name="contrato" property="montoAdeudado" />
					</fmt:formatNumber>
				</td>
				<td><fmt:formatNumber maxFractionDigits="0">
						<bean:write name="contrato" property="montoCuota" />
					</fmt:formatNumber>
				</td>
				<td><bean:write name="contrato" property="estadoCredito" />
				</td>
				<td><bean:write name="contrato" property="repactacion" />
				</td>
				<td><bean:write name="contrato" property="reprogramacion" />
				</td>
				<td><bean:write name="contrato" property="titular" />
				</td>
				<td><bean:write name="contrato" property="rolPagador" />
				</td>
				<td><fmt:formatNumber maxFractionDigits="0">
						<bean:write name="contrato" property="montoSolicitado" />
					</fmt:formatNumber></td>
				<td><bean:write name="contrato" property="tipoAfiliado" />
				</td>
				
				<td><bean:write name="contrato" property="bpAnexo" />
				</td>
				<td><bean:write name="contrato" property="nroInscripcion" />
				</td>
				<td><bean:write name="contrato" property="rutEmpresa" />
				</td>
				
				<td><bean:write name="contrato" property="plazo" />
				</td>
				<td><bean:write name="contrato" property="fechaOtorgamiento"
						format="dd-MM-yyyy" />
				</td>
				<td class="aDetalle"><logic:equal name="contrato"
						property="origen" value="B">
						<a
							href="../compTotal/detalleContratoBanking.do?id=<bean:write name="contrato" property="idOriginal" />&rut=<bean:write name="rut"/>">Detalle</a>
					</logic:equal> <logic:equal name="contrato" property="origen" value="A">
						<a
							href="../compTotal/detalleContratoAs400.do?folioCredito=<bean:write name="contrato" property="idOriginal" />&tipo=<bean:write name="contrato" property="lineaComercial" />">Detalle</a>
					</logic:equal>
				</td>
				<td class="aDetalle">
					<logic:equal name="contrato"
						property="lineaComercial" value="COSAL DENTAL">
						<a
							href="../compTotal/detalleContratoPresupuestoBanking.do?id=<bean:write name="contrato" property="idOriginal" />&rut=<bean:write name="rut"/>">Detalle</a>
					</logic:equal> 
					<logic:equal name="contrato"
						property="lineaComercial" value="42">
						<a
							href="../compTotal/detalleContratoPresupuestoBanking.do?id=<bean:write name="contrato" property="idOriginal" />&rut=<bean:write name="rut"/>">Detalle</a>
					</logic:equal> 
				</td>
			</tr>
		</logic:iterate>
	</logic:equal>
	<logic:equal name="error" value="vacio">
		<tr>
			<td colspan="20">
				<div class="notice_message">
					<bean:message key="error.tabla.vacia" />
				</div></td>
		</tr>
	</logic:equal>
	<logic:equal name="error" value="session">
		<tr>
			<td colspan="20"><div class="error_message">
					<bean:message key="error.session" />
				</div></td>
		</tr>
	</logic:equal>
	<logic:notEqual name="mensajeSAP" value="0">
		<tr>
			<td colspan="20">
				<div class="error_message">
					<bean:write name="mensajeSAP" />
				</div></td>
		</tr>

	</logic:notEqual>
</logic:equal>

<logic:equal name="opcion" value="hipotecario">
	<logic:equal name="error" value="0">

		<c:forEach var="listContratoAsicom" items="${listaAsicom}">
			<tr>
				<td>${listContratoAsicom.oficina_originadora}</td>
				<td>${listContratoAsicom.folio_credito}</td>
				<td>${listContratoAsicom.tasa_interes}</td>
				<td>${listContratoAsicom.monto_adeudado}</td>
				<td>${listContratoAsicom.monto_cuota}</td>
				<td>${listContratoAsicom.estado_credito}</td>
				<td>${listContratoAsicom.indicador_reprogramacion}</td>
				<td>${listContratoAsicom.rol_asociado_relacion}</td>
				<td>${listContratoAsicom.rol_pagador}</td>
				<td>${listContratoAsicom.cantidad_cuotas}</td>

				<td class="aDetalle"><a
					href="../compTotal/detalleContratoAsicom.do?id=${listContratoAsicom.folio_credito}&rut=<bean:write name="rut"/>">Detalle</a>
				</td>
				
			</tr>
		</c:forEach>

	</logic:equal>
	<logic:equal name="error" value="1">
		<tr>
			<td colspan="13">
				<div class="notice_message">
					<bean:message key="error.tabla.vacia" />
				</div></td>
		</tr>

	</logic:equal>
	<logic:equal name="error" value="session">
		<tr>
			<td colspan="13"><div class="error_message">
					<bean:message key="error.session" />
				</div></td>
		</tr>

	</logic:equal>
	<logic:notEqual name="mensajeASICOM" value="0">
		<tr>
			<td colspan="13">
				<div class="error_message">
					<bean:write name="mensajeASICOM" />
				</div></td>

		</tr>
	</logic:notEqual>
</logic:equal>

<logic:equal name="opcion" value="intercaja">
	<logic:equal name="error" value="ok">

		<c:forEach var="contrato" items="${listaIntercaja}">
			<tr>
				<td>${contrato.periodo}</td> 
				<td>${contrato.codigoCajaOrigen}</td>
				<td>${contrato.codigoCajaDestino}</td>
				<td>${contrato.numeroPagare}</td>
				<td>${contrato.identEmpresa}</td>
				<td>${contrato.identDeudor}</td>
				<td>${contrato.nombreDeudor}</td>
				<td>${contrato.identAval}</td>
				<td>${contrato.sujetoDescuento}</td>
				<td><fmt:formatNumber maxFractionDigits="0">${contrato.montoOfertaCompra}</fmt:formatNumber>
				<td><fmt:formatNumber maxFractionDigits="0">${contrato.montoCuotaDeudor}</fmt:formatNumber>
				</td>
			</tr>
		</c:forEach>

	</logic:equal>
	<logic:equal name="error" value="vacio">
		<tr>
			<td colspan="11">
				<div class="notice_message">
					<bean:message key="error.tabla.vacia" />
				</div></td>
		</tr>

	</logic:equal>
	<logic:equal name="error" value="session">
		<tr>
			<td colspan="11">
				<div class="error_message">
					<bean:message key="error.session" />
				</div>
			</td>
		</tr>

	</logic:equal>
	<logic:notEqual name="mensajeDAO" value="0">
		<tr>
			<td colspan="11">
				<div class="error_message">
					<bean:write name="mensajeDAO" />
				</div>
			</td>

		</tr>

	</logic:notEqual>
</logic:equal>

