<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				<TABLE border=0 align="center">
					<tr>
						<td height="22" width=120 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ENTIDAD</td>
						<td height="22" width=100 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT EMPRESA</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CONVENIO</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TIPO NOMINA</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">PENSION</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">FONASA</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ACCIDENTE</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">DESAHUCIO</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TOTAL PAGOS</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">ASFAM</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Ley 15386</td>
						<td height="22" width=80 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TOTAL REBAJAS</td>
						<td height="22" width=100 align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">TOTAL</td>
					</tr>
					<c:set var="entidad_old" value="Ninguna" />
					<c:set var="totalPension" value="0"></c:set>
					<c:set var="totalFonasa" value="0"></c:set>
					<c:set var="totalAccidente" value="0"></c:set>
					<c:set var="totalDesahucio" value="0"></c:set>
					<c:set var="totalTotalPagos" value="0"></c:set>
					<c:set var="totalAsfam" value="0"></c:set>
					<c:set var="totalLey15386" value="0"></c:set>
					<c:set var="totalTotalRebajas" value="0"></c:set>
					<c:set var="totalTotal" value="0"></c:set>
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
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM4}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM5}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM6}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM7}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM8}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM9}</fmt:formatNumber></td>
						<td align="right" class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.totalM10}</fmt:formatNumber></td>
					</tr>
					<tr id="${comprobante.nombreEntidad}" style="display:none">
									<td colspan="13">
										<table border="0" cellpadding="1" cellspacing="1" width="100%">
					</c:if>
					
					<!-- Se inserta detalle Entidad -->
					<tr>
						<td align="left" width=120 class="textos_formularios">&nbsp;</td>
						<td align="right" width=100 class="textos_formularios">${comprobante.rutEmpresa}</td>
						<td align="center" width=80 class="textos_formularios">${comprobante.convenio}</td>
						<td align="center" width=80 class="textos_formularios">${comprobante.tipoNomina}</td>
						
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m2}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m3}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m4}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m5}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m6}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m7}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m8}</fmt:formatNumber></td>
						<td align="right" width=80 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m9}</fmt:formatNumber></td>
						<td align="right" width=100 class="textos_formularios"><fmt:setLocale value="ES" /><fmt:formatNumber>${comprobante.m10}</fmt:formatNumber></td>
					</tr>
					<c:set var="entidad_old" value="${comprobante.nombreEntidad}"/>
					<c:set var="totalPension" value="${totalPension + comprobante.m2}"/>
					<c:set var="totalFonasa" value="${totalFonasa + comprobante.m3}"/>
					<c:set var="totalAccidente" value="${totalAccidente + comprobante.m4}"/>
					<c:set var="totalDesahucio" value="${totalDesahucio + comprobante.m5}"/>
					<c:set var="totalTotalPagos" value="${totalTotalPagos + comprobante.m6}"/>
					<c:set var="totalAsfam" value="${totalAsfam + comprobante.m7}"/>
					<c:set var="totalLey15386" value="${totalLey15386 + comprobante.m8}"/>
					<c:set var="totalTotalRebajas" value="${totalTotalRebajas + comprobante.m9}"/>
					<c:set var="totalTotal" value="${totalTotal + comprobante.m10}"/>
					
					</c:forEach>
					<!-- se cierra sección final -->
					</table></td></tr>
					<tr><td align="right" colspan=4 class="textos_formcolor">Totales:</td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalPension}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalFonasa}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalAccidente}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalDesahucio}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalTotalPagos}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalAsfam}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalLey15386}"/></fmt:formatNumber></td>
					<td align="right" width=80 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalTotalRebajas}"/></fmt:formatNumber></td>
					<td align="right" width=100 class="textos_formcolor">$<fmt:setLocale value="ES" /><fmt:formatNumber><c:out value="${totalTotal}"/></fmt:formatNumber></td>
					</tr>
				</TABLE>
			