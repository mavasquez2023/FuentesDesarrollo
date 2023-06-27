<%@ include file="/html/comun/taglibs.jsp"%>

<script language="JavaScript1.2">

	/*
	 *  CP global and user variables.
	 */
	var period = "${periodo}";
	
	var MAX_NOMINAS_SELECCIONABLES = 10;
	
	${tipoNominasCode}
	
	var enumTipoNominas = "${enumTipoNominas}";
	
	${conveniosCode}
	${empresasCode}
</script>

<script language="JavaScript1.2" src="js/adapted/nozipped.js"></script>

<c:if test="${empresasSelect != ''}">
	<table border="0" width="100%" align="left">
		<tr>
		    <td align="left">
				<form name="senderForm" action="receiver.ado" enctype="multipart/form-data" accept="text/plain" method="post">

					<input type="hidden" id="accion"    name="accion"    value="nominas">
					<input type="hidden" id="subAccion" name="subAccion" value="envio">
			
					<input type="hidden" name="sendMethod" value="COMPRESSION_NONE">
					<input type="hidden" name="nFiles" value="8">
		
					<table border="0" width="100%" align="center" cellspacing="0" cellpadding="0">
		
						<tr>
							<td colspan="2" align="left" class="textos_formularios">
								<br>
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Este formulario le permite enviar sus n&oacute;minas de cotizaciones a <b>CP.CL</b>
							    para el per&iacute;odo de cotizaci&oacute;n <b><font color="blue">${periodo}</font></b>.
								Para cada n&oacute;mina a enviar, seleccione su empresa y adjunte la n&oacute;mina de cotizaciones correspondiente:
								<br>
								<br>
							</td>
						</tr>

						<tr><td colspan="2">&nbsp;</td></tr>

						<tr>
							<td colspan="2">
								<table border="0" align="center">
									<tr>
										<th align="left" class="barra_titulos">
										    <code>&nbsp;</code>Empresa
										</th>
																			
										<th align="left" class="barra_titulos">
										    <code>&nbsp;&nbsp;&nbsp;&nbsp;</code>N&oacute;mina
										<th>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina1" value="" tabindex="1">
											
											<select name="empresa1" tabindex="2" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;1:</code> <input type="file" name="nomina1" tabindex="3" size="15">
										</td>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina2" value="" tabindex="4">
											
											<select name="empresa2" tabindex="5" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;2:</code> <input type="file" name="nomina2" tabindex="6" size="15">
										</td>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina3" value="" tabindex="7">
											
											<select name="empresa3" tabindex="8" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;3:</code> <input type="file" name="nomina3" tabindex="9" size="15">
										</td>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina4" value="" tabindex="10">
											
											<select name="empresa4" tabindex="11" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;4:</code> <input type="file" name="nomina4" tabindex="12" size="15">
										</td>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina5" value="" tabindex="13">
											
											<select name="empresa5" tabindex="14" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;5:</code> <input type="file" name="nomina5" tabindex="15" size="15">
										</td>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina6" value="" tabindex="16">
											
											<select name="empresa6" tabindex="17" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;6:</code> <input type="file" name="nomina6" tabindex="18" size="15">
										</td>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina7" value="" tabindex="19">
											
											<select name="empresa7" tabindex="20" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;7:</code> <input type="file" name="nomina7" tabindex="21" size="15">
										</td>
									</tr>

									<tr>
										<td align="left">
											<input type="hidden" name="idNomina8" value="" tabindex="22">
											
											<select name="empresa8" tabindex="23" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;8:</code> <input type="file" name="nomina8" tabindex="24" size="15">
										</td>
									</tr>
									
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina9" value="" tabindex="22">
											
											<select name="empresa9" tabindex="23" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;&nbsp;9:</code> <input type="file" name="nomina9" tabindex="24" size="15">
										</td>
									</tr>
									<tr>
										<td align="left">
											<input type="hidden" name="idNomina10" value="" tabindex="22">
											
											<select name="empresa10" tabindex="23" class="campos">
												<%= request.getAttribute("empresasSelect") %>
											</select>
										</td>
			
										<td align="left">
											<code>&nbsp;10:</code> <input type="file" name="nomina10" tabindex="24" size="15">
										</td>
									</tr>
								</table>
							</td>
						</tr>
		
						<tr>
							<td colspan="2" align="center">
								<br>
								
								<input type="submit" value="Enviar" onclick="return AR_checkForm();" tabindex="25" class="btn-destacado">
								<input type="submit" value="Cancelar" onclick="return AR_cancelForm();" tabindex="26" class="btn-destacado">
							</td>
						</tr>
					
						<tr>
							<td colspan="2" align="left">
								<br>
							
								<table border="0" width="85%" align="center" cellspacing="0" cellpadding="0">
									<tr align="center">
										<td valign="top" align="right" width="10%" class="textos_formularios">
											<b>Nota:</b>
										</td>
										
										<td class="textos_formularios">
											Los archivos adjuntos deben tener extensi&oacute;n <b>.&lt;tipo&gt;&lt;convenio&gt;</b>,
											en donde:
											
											<br>
											<br>
											<table border="0" align="center" cellspacing="0" cellpadding="0">
												<tr>
													<td width="10%" class="textos_formularios" nowrap>
														<b>&lt;tipo&gt;</b>
													</td>
	
													<td class="textos_formularios" nowrap>
														Es el tipo de n&oacute;mina (${enumTipoNominas})
													</td>
												</tr>
												
												<tr>
													<td width="10%" class="textos_formularios" nowrap>
														<b>&lt;convenio&gt;</b>
													</td>
	
													<td class="textos_formularios" nowrap>
														Es el n&uacute;mero de convenio de cotizaci&oacute;n (2 d&iacute;gitos <b>siempre</b>)</td>
												</tr>
											</table>
											
											<br>
											Ejemplo: La extensi&oacute;n <b>.R01</b> indica <u>n&oacute;mina de remuneraciones</u>
											del <u>convenio 1</u>.
											<br>
										</td>
									</tr>
								</table>
							</td>
						</tr>	
					</table>
				</form>
		    </td>
		</tr>
	</table>
</c:if>

<c:if test="${empresasSelect == ''}">
	<br>
	
	<p class="text-11">
		<b>Usted no est&aacute; autorizado para enviar n&oacute;minas de cotizaciones.</b>
	</p>
</c:if>
