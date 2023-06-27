<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<script>
function imprimir(dest){
    alert("Recuerde desactivar los encabezados y pies de página, además de ajustar el tamaño del texto, en su Explorador de Internet, para así obtener un mejor resultado en la impresión.");
	var ventana;
	contextRoot = "<%=request.getContextPath()%>";
	args = '<%=request.getAttribute("args")%>';	
	url = contextRoot + dest + "?" + args;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open(url,"print",prop);
	ventana.print();
}
</script>

<table>
	<tbody>
		<tr>
			<td>
				<table>
					<tbody>
						<tr>
							<td style="vertical-align: top;"><html:img page="/img/vwicn069.gif" style="border : 0px; width : 10px; height : 10px;" /></td>
							<td style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									<html:link action="/ctaCteN1" name="paramsHomeCtaCte" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
										<bean:message key="global.text.paginaInicioCuentaCorriente" />
									</html:link>
							</td>
						</tr>
						<tr>
							<td><html:img page="/img/prevview.gif" style="border : 0px; width : 10px; height : 10px;" /></td>
							<td style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
								<html:link onclick="history.go(-1); return false;" href="" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									<bean:message key="global.text.paginaAnterior" />
								</html:link>
							</td>
						</tr>			
					</tbody>
				</table>
			</td>
		</tr>
		
		<tr>
			<td><html:img page="/img/horiz_gray.gif" style="border : 0; width : 142px; height : 6px;" alt=""/></td>										
		</tr>

		<tr>
			<td>
				<table>
					<tbody>
						<tr>										
							<td style="text-decoration: none; color: #000000; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: normal;">
							</td>
						</tr>
						<tr>
							<td></td>
							<td style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
								<html:link onclick="imprimir('/ctaCte/402/listadoNominaDiferenciaAsignacionFamiliar.do'); return false;" href="" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									Hága clic aquí para obtener certificado.
								</html:link>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>