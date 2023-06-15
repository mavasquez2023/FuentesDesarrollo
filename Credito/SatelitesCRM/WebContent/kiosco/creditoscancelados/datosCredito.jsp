<%@ include file="../../comun/headerJsp.jsp"%>
<logic:equal value="0" name="opcion">
	<logic:iterate id="lista" name="listaCreditosCancelados">
		<tr>
			<td>
			<a href="listadoCreditos/seleccionCredito.do?folio=${lista.folio}&accion=cargarCredito">Seleccionar</a>
			</td>
			<td class="folio">${lista.folio}</td>
			<td>
			<c:choose>
			<c:when test="${lista.tipoMoneda == 'UF'}">
				UF <fmt:formatNumber maxFractionDigits="0"
							value="${lista.montoSolicitado}" />
				</c:when>
				<c:otherwise>
					$ <fmt:formatNumber maxFractionDigits="0"
							value="${lista.montoSolicitado}" />
				</c:otherwise>
			</c:choose>
			</td>
			<td>${lista.fechaOtorgamiento}</td>
			<td>${lista.fechaCancelacion}</td>
			<td>
			<c:choose>
					<c:when test="${lista.tipoMoneda == 'UF'}">
					UF <fmt:formatNumber maxFractionDigits="0" value="${lista.montoCuota}"/>
					</c:when>
					<c:otherwise>
					$ <fmt:formatNumber maxFractionDigits="0" value="${lista.montoCuota}"/>
					</c:otherwise>
				</c:choose>
			</td>
			<td>${lista.plazo}</td>
		</tr>
	</logic:iterate>
		
		<logic:empty name="listaCreditosCancelados">
			<tr>
			<c:choose>
				<c:when test="${codError=='1'}">
					<td colspan="8"> <div id="msgError">${msg}</div></td>

					<script>$("#imprimir").attr("disabled","disabled");</script>
				</c:when>
				<c:otherwise>
					<td colspan="8"> <div id="msgError">El cliente no tiene créditos cancelados.</div></td>
				</c:otherwise>
			</c:choose>
			</tr>
		</logic:empty>
</logic:equal>
<logic:equal value="1" name="opcion">
	<logic:iterate id="lista" name="listaCuotas">
		<tr>
			<td>${lista.nCuota}</td>
			<td>${lista.fecVencimiento}</td>
			<td><fmt:formatNumber maxFractionDigits="0" value="${lista.nCuota}"/></td>
			<td>${lista.fecPago}</td>
			<td>${lista.oficina}</td>
			<td>${lista.docPago}</td>
			<td>
				<c:choose>
					<c:when test="${lista.tipoMoneda == 'UF'}">
					UF <fmt:formatNumber maxFractionDigits="5" value="${lista.monto}" />
					</c:when>
					<c:otherwise>
					$<fmt:formatNumber maxFractionDigits="0" value="${lista.monto}" />
					</c:otherwise>
				</c:choose>
				</td>
			<td>${lista.estPago}</td>
		</tr>
	</logic:iterate>
	
	<logic:empty name="listaCuotas">
		<c:choose>
			<c:when test="${codError=='1'}">
				<td colspan="8"> <div id="msgError">${msg}</div></td>
				<script>$("#imprimir").attr("disabled","disabled");</script>
			</c:when>
			<c:otherwise>
				<td colspan="8"> <div id="msgError">El crédito seleccionado no tiene cuotas canceladas.</div></td>
			</c:otherwise>
		</c:choose>
	</logic:empty>

</logic:equal>