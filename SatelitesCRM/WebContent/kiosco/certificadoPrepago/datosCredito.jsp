<%@ include file="../../comun/headerJsp.jsp"%>

	<logic:iterate id="lista" name="listaCreditosVigentes">
		<form target="imprimePDF" action="listadoCreditos.do" method="post">
			<tr>
				<td>
				<input type="checkbox" name="creditosParaPrepago" value="${lista.folio}"></td>
				<td>${lista.folio}</td>
				<td>${lista.desdeCuota}</td>
				<td>${lista.hastaCuota}</td>
				<td>${lista.fechaOtorgamiento}</td>
				<td><fmt:formatNumber currencyCode="$" maxFractionDigits="0">${lista.montoCuota}</fmt:formatNumber></td>
				<td>${lista.tasaImpuestoLTE}</td>
				<td><fmt:formatNumber currencyCode="$" maxFractionDigits="0">${lista.montoImpuestoLTE}</fmt:formatNumber></td>
				<td>${lista.folioForm24}</td>
				<td><fmt:formatNumber currencyCode="$" maxFractionDigits="0">${lista.saldoCapital}</fmt:formatNumber></td>
				<td><fmt:formatNumber currencyCode="$" maxFractionDigits="0">${lista.comisionPrepago}</fmt:formatNumber></td>
				<td><fmt:formatNumber currencyCode="$" maxFractionDigits="0">${lista.gravamenes}</fmt:formatNumber></td>
				<td><fmt:formatNumber currencyCode="$" maxFractionDigits="0">${lista.gastosDeCobranza}</fmt:formatNumber></td>
				<td><fmt:formatNumber currencyCode="$" maxFractionDigits="0">${lista.total}</fmt:formatNumber></td>
			</tr>
			<input type="hidden" name="rut" value="${rut}">
			<input type="hidden" name="origen" value="kiosco" >
			<input type="hidden" name="accion" value="imprimirReporte">
			<div class="botones">
				<input type="submit" value="Imprimir" id="imprimir" class="boton" />
			</div>
		</form>
		<iframe name="imprimePDF" style="display: none;" ></iframe>
	</logic:iterate>


