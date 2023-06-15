<%@ include file="../../comun/headerJsp.jsp"%>

<logic:equal value="0" name="opcion">
	<logic:iterate id="lista" name="listaCreditosVigentes">
		<tr>
			<td>
				<!-- a href="listadoCreditos/seleccionCredito.do?folio=${lista.folio}&accion=cargarCredito">Seleccionar</a-->
				<a href="listadoCreditos/seleccionCredito/detalleCredito.do?folio_contrato=${lista.folio}&fecha_otorgamiento=${lista.fechaOtorgamiento}&accion=cargarDetalle&uc=${uc}">Detalle</a>
			</td>
			<td class="folio">${lista.folio}</td>
			<td>
				<c:choose>
				<c:when test="${lista.tipoMoneda == 'UF'}">
					UF <fmt:formatNumber maxFractionDigits="0" value="${lista.montoSolicitado}" />
				</c:when>
				<c:otherwise>
					$<fmt:formatNumber maxFractionDigits="0" value="${lista.montoSolicitado}" />
				</c:otherwise>
			</c:choose>
			</td>
			<td>${lista.fechaOtorgamiento}</td>
			<td>
			<c:choose>
				<c:when test="${lista.tipoMoneda == 'UF'}">
					UF <fmt:formatNumber maxFractionDigits="5">${lista.montoCuota}</fmt:formatNumber>
				</c:when>
				<c:otherwise>
					$<fmt:formatNumber maxFractionDigits="0">${lista.montoCuota}</fmt:formatNumber>
				</c:otherwise>
			</c:choose>
			
				
			</td>
			<td><fmt:formatNumber maxFractionDigits="0" value="${lista.plazo}" /></td>		
			<td>
				<c:if test="${not empty lista.gastosCobranza}">
					$<fmt:formatNumber maxFractionDigits="0" value="${lista.gastosCobranza.trim()}" />
				</c:if>			
			</td>
			<td>
				${lista.rolAsociado}
			</td>
		</tr>
	</logic:iterate>
	
	<logic:empty name="listaCreditosVigentes">
		<tr>
		<c:choose>
			<c:when test="${codError=='1'}">
				<td colspan="8"> <div id="msgError">${msg}</div></td>
				<script>$("#imprimir").attr("disabled","disabled");</script>
			</c:when>
			<c:otherwise>
				<td colspan="8"> <div id="msgError">El cliente no tiene créditos vigentes.</div></td>
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
			<td><fmt:formatNumber maxFractionDigits="0" value="${lista.nCuota}" />
			</td>
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
				<td colspan="8"> <div id="msgError">${msg}</div>
				</td>
				<script>$("#imprimir").attr("disabled","disabled");</script>
			</c:when>
			<c:otherwise>
				<td colspan="8"> <div id="msgError">El crédito seleccionado no tiene cuotas vigentes.</div></td>
			</c:otherwise>
		</c:choose>
	</logic:empty>
</logic:equal>