<table align="center" class="tabla-creditos">
					<tr>
						<th>RUT EMPRESA</th>
						<th>NOMBRE EMPRESA</th>
						<th>&nbsp;<!-- input class="boton" id="sel" type="checkbox" value="Todas" /--></th>
					</tr>
					<c:set var="total" value="0"></c:set>
					<c:forEach var="entry" varStatus="vs" items="${empresas}">
							<tr>
								<td class="certificadoLeft">${entry.key}</td>
								<td class="certificadoLeft">${entry.value}</td>
								<td class="certificadoCenter"><input class="boton" id="rut" name="rut" type="checkbox" value="${entry.key}" onclick="validaSeleccion(this)" /></td>
							</tr>
					</c:forEach>
</table>