<%@ include file="../layout/headerJsp.jsp"%>
				<fieldset class="form-fieldset">
						<c:choose>
							<c:when test="${tipoMensaje=='3'}">
									<div class="field full-width">
										<strong>Periodo seleccionado: ${periodo}</strong><br><br>
										<strong>Estado proceso de Carga: ${estCarga.desEstado}</strong><br><br>
										<strong>Estado proceso de Validación: ${estValid.desEstado}</strong><br><br>
										<strong>Estado de creación de pase contable: ${estPase.desEstado}</strong><br><br>
										<input class="boton" type="button" value='Consultar Estado Procesos' onclick="recargarEstadosProcesos();"/>									
									</div>
									<br>
							</c:when>
							<c:otherwise>
								Se produjo un error al consultar por el estado de los procesos, causa: ${mensaje}. Por favor intente nuevamente.
								
							</c:otherwise>
						</c:choose>
				</fieldset><br>
