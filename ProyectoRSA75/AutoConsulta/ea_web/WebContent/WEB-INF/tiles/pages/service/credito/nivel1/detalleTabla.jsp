<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script>
function imprimir(dest){
	// verificar si ingreso la fecha
	
    alert("Recuerde desactivar los encabezados y pies de página, además de ajustar el tamaño del texto, en su Explorador de Internet, para así obtener un mejor resultado en la impresión.");
	var ventana;
	contextRoot = "<%=request.getContextPath()%>";
	//args = '<%=request.getAttribute("args")%>';
	
	ff = document.forms[1].fechaFiniquito.value;
	dd = ff.slice(0, 2);
	mm = ff.slice(3, 5);
	yyyy = ff.slice(6, 10);
	
	args="rutAfiliado=" + document.forms[1].rutAfiliado.value + "&fechaFiniquito=" + yyyy + mm + dd;
	url = contextRoot + dest + "?" + args;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open(url,"print",prop);
	ventana.print();
}

function validaFecha(yyyy, mm, dd) {
	//((year%4 == 0) && ((year%100 != 0) || (year%400 == 0)))
}
function checkDate(sDate) {
	if(trim(sDate) == "") {
		alert("Seleccione o ingrese la Fecha Finiquito.");
		return false;
	};
	
	re = /(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/(19[7-9][0-9]|20[0-3][0-9])/gi;
	if(sDate.length != 10 || !sDate.match(re)) {
		alert("La forma de la fecha (dd/mm/aaaa) no es correcto: " + sDate);
		return false;
	}

	var today = new Date();
	
	dd = sDate.slice(0, 2);
	mm = Number(sDate.slice(3, 5))-1;
	yyyy = sDate.slice(6, 10);
	var selectedDate = new Date(yyyy, mm, dd);

	if(selectedDate - today < 0) {
		alert("La Fecha Finiquito debe ser posterior a hoy.");
		return false;
	}
	return true;
}

function ltrim(value) {
	var pattern=new RegExp("^\\s+", "g")
	return value.replace(pattern, "");
}

function rtrim(value) {
	var pattern=new RegExp("\\s+$", "g")
	return value.replace(pattern, "");
}

function trim(value) {
	return rtrim(ltrim(value));
}
</script>
<logic:notEmpty name="detalles" scope="request">
<!-- Detalles -->
<table style="width :100%; border : 0px;" cellpadding="0" cellspacing="0" >
	<tbody>
		<tr style="height : 18px;">
			<td class=smallfontreverse >
				<b>
					<bean:message key="deudaCreditoN1.table.header" />&nbsp;
					<bean:message key="global.text.unidadMonetaria" />
				</b>
			</td>
		</tr>
		<tr>
			<td>
				<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
					<tbody>
						<tr style="background-color : #cccccc;">
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 15%;" rowspan="2"><bean:message key="deudaCreditoN1.table.header.folio" /></td>
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 5%;" rowspan="2">(*)</td>
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 26%;" colspan="2"><bean:message key="deudaCreditoN1.table.header.saldo" /></td>
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 54%;" colspan="3"><bean:message key="deudaCreditoN1.table.header.aval" /></td>
						</tr>
						<tr style="background-color : #cccccc;">
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 13%;"><bean:message key="deudaCreditoN1.table.header.vigente" /></td>
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 13%"><bean:message key="deudaCreditoN1.table.header.moroso" /></td>
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 17%;"><bean:message key="deudaCreditoN1.table.header.rut" /></td>
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 37%"><bean:message key="deudaCreditoN1.table.header.nombre" /></td>
							<td class=smallprompt style="font-weight : bold; text-align : center; width : 5%"><html:img page="/img/left.gif" style="border : 0px; width : 10px; height : 10px;" /></td>
							
						</tr>
						<logic:iterate id="detalle" name="detalles" scope="request">
							<tr>
								<td class="smallprompt" style="vertical-align: top;">
									<html:link page="/deudaCreditoN2.do" name="detalle" property="params">
										<bean:write name="detalle" property="codigoOficinaProceso" />-
										<bean:write name="detalle" property="folio" />
									</html:link>
								</td>
								<td class="smallprompt" style="vertical-align: top;">
									<html:link action="/tablaDesarrolloCredito" name="detalle" property="params">
										ver
									</html:link>
								</td>
								<td class="smallcurrency" style="vertical-align: top;">
									<bean:write name="detalle" property="saldoVigente" filter="true" formatKey="global.monto" ignore="true" />
								</td>																										
								<td class="smallcurrency" style="vertical-align: top;">
									<bean:write name="detalle" property="saldoMoroso" filter="true" formatKey="global.monto" ignore="true" />
								</td>																										
								<td class="smallprompt" style="vertical-align: top;">										
									<logic:notEmpty name="detalle" property="aval1" scope="page">
										<bean:write name="detalle" property="aval1.rut.formattedRut" scope="page" />-
										<bean:write name="detalle" property="aval1.rut.dv" scope="page" />
									</logic:notEmpty>
								</td>
								<td class="smallprompt">
									<logic:notEmpty name="detalle" property="aval1" scope="page">
										<bean:write name="detalle" property="aval1.apellidoPaterno" />&nbsp;
										<bean:write name="detalle" property="aval1.apellidoMaterno" />&nbsp;
										<bean:write name="detalle" property="aval1.nombre" />
									</logic:notEmpty>	
								</td>
								<td class="smallprompt" style="vertical-align: top;">
									<logic:notEmpty name="detalle" property="aval1" scope="page">
										<logic:notEmpty name="detalle" property="aval1.params" scope="page">
											<html:link page="/deudaCreditoN1.do" name="detalle" property="aval1.params" scope="page">
												ver
											</html:link>
										</logic:notEmpty>
									</logic:notEmpty>	
								</td>
							</tr>
							<logic:notEmpty name="detalle" property="aval2" scope="page">
							<tr>
								<td class="smallprompt">
								</td>
								<td class="smallprompt">
								</td>
								<td class="smallcurrency">
								</td>																										
								<td class="smallcurrency">
								</td>																										
								<td class="smallprompt" style="vertical-align: top;">										
									<logic:notEmpty name="detalle" property="aval2" scope="page">
										<bean:write name="detalle" property="aval2.rut.formattedRut" scope="page" />-
										<bean:write name="detalle" property="aval2.rut.dv" scope="page" />
									</logic:notEmpty>
								</td>
								<td class="smallprompt">
									<logic:notEmpty name="detalle" property="aval2" scope="page">
										<bean:write name="detalle" property="aval2.apellidoPaterno" />&nbsp;
										<bean:write name="detalle" property="aval2.apellidoMaterno" />&nbsp;
										<bean:write name="detalle" property="aval2.nombre" />
									</logic:notEmpty>	
								</td>
								<td class="smallprompt" style="vertical-align: top;">
									<logic:notEmpty name="detalle" property="aval2" scope="page">
										<logic:notEmpty name="detalle" property="aval2.params" scope="page">
											<html:link page="/deudaCreditoN1.do" name="detalle" property="aval2.params" scope="page">
												ver
											</html:link>
										</logic:notEmpty>
									</logic:notEmpty>
								</td>
							</tr>
							</logic:notEmpty>
						</logic:iterate>
						<tr>
							<td colspan="6" style="height : 1px; background-color : #006666;"></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td style="font-family: verdana; font-size: 10px; color: red;">(*) Ver tabla de desarrollo.
			</td>
		</tr>

		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>

		<tr>
			<td>
				<table width="430px">
					<tr>
						<td class="smallprompt" >
							Si desea imprimir <b>Certificado Saldo Deuda Capital</b>, 
							seleccione la fecha finiquito, luego haga clic al bot&oacute;n <b>Obtener Certificado</b>.
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr><td>&nbsp;</td></tr>

		<html:form action="/certificadoSaldoDeudaCapital" method="post">
		<tr>
			<td>
				<table width="430px">
					<tr>
						<td class=smallprompt style="font-weight : bold; width: 110px;">Fecha Finiquito: </td>
						<td>
							<html:text property="fechaFiniquito" styleClass="smallprompt" size="10" maxlength="10" />
							<html:img page="/img/calendario.gif" onclick="opencalendar(1, 'fechaFiniquito'); return false;" />
							(dd/mm/aaaa)
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span class="smallprompt">
							<html:button property="bObtenerCertificado" value="Obtener Certificado" 
									onclick="if(checkDate(document.forms[1].fechaFiniquito.value)) {imprimir('/certificadoSaldoDeudaCapital.do'); }"/>
							</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<html:hidden property="rutAfiliado" />
		</html:form>
	</tbody>
</table>
</logic:notEmpty>															