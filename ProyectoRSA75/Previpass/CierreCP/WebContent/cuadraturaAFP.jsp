<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				<TABLE border=0 align="center">
					<tr>
						<td height="22" width=150 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ENTIDAD</td>
						<td height="22" width=100 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT EMPRESA</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CONVENIO</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TIPO NOMINA</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">OBLIGATORIO</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">AHORRO</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">SIS</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">AFC TRA</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">AFC EMP</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TP</td>
						<td height="22" width=100 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TOTAL</td>
					</tr>
					<c:set var="entidad_old" value="Ninguna" />
					<c:set var="totalObligatorio" value="0"></c:set>
					<c:set var="totalAhorro" value="0"></c:set>
					<c:set var="totalSIS" value="0"></c:set>
					<c:set var="totalAFCTra" value="0"></c:set>
					<c:set var="totalAFCEmp" value="0"></c:set>
					<c:set var="totalTP" value="0"></c:set>
					<c:set var="total" value="0"></c:set>
					<c:forEach var="comprobante" items="${cuadratura}">
					<!-- Se agrupa por tipo Entidad -->
					<c:if test='${comprobante.nombreEntidad != entidad_old}'>
						<!-- se cierra seccion anterior -->
						<c:if test='${entidad_old != "Ninguna"}'></table></td></tr></c:if>
					<tr>
						<td align="left" class="textos_formularios">
						<A href="#" onclick="swapDetalle('${comprobante.nombreEntidad}');return false;">
												<img id="img${comprobante.nombreEntidad}" src="icons/ico_mas.gif" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
						</A> ${comprobante.nombreEntidad}</td>
						<td align="left" class="textos_formularios">&nbsp;</td>
						<td align="left" class="textos_formularios">&nbsp;</td>
						<td align="left" class="textos_formularios">&nbsp;</td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM2}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM3}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM12}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM6}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM7}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM9}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM11}</fmt:formatNumber></td>
					</tr>
					<tr id="${comprobante.nombreEntidad}" style="display:none">
									<td colspan="11">
										<table border="0" cellpadding="1" cellspacing="1" width="100%">
					</c:if>
					
					<!-- Se inserta detalle Entidad -->
					<tr>
						<td align="left" width=150 class="textos_formularios">&nbsp;</td>
						<td align="right" width=80 class="textos_formularios">${comprobante.rutEmpresa}</td>
						<td align="center" width=80 class="textos_formularios">${comprobante.convenio}</td>
						<td align="center" width=80 class="textos_formularios">${comprobante.tipoNomina}</td>
						
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m2}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m3}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m12}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m6}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m7}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m9}</fmt:formatNumber></td>
						<td align="right" width=100 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m11}</fmt:formatNumber></td>
					</tr>
					<c:set var="entidad_old" value="${comprobante.nombreEntidad}"/>
					<c:set var="totalObligatorio" value="${totalObligatorio + comprobante.m2}"/>
					<c:set var="totalAhorro" value="${totalAhorro + comprobante.m3}"/>
					<c:set var="totalSIS" value="${totalSIS + comprobante.m12}"/>
					<c:set var="totalAFCTra" value="${totalAFCTra + comprobante.m6}"/>
					<c:set var="totalAFCEmp" value="${totalAFCEmp + comprobante.m7}"/>
					<c:set var="totalTP" value="${totalTP + comprobante.m9}"/>
					<c:set var="total" value="${total + comprobante.m11}"/>
					
					</c:forEach>
					<!-- se cierra sección final -->
					</table></td></tr>
					<tr><td align="right" colspan=4 class="textos_formcolor">Totales:</td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalObligatorio}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalAhorro}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalSIS}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalAFCTra}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalAFCEmp}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalTP}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${total}"/></fmt:formatNumber></td>
					</tr>
				</TABLE>
			
