<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<script>
function imprimir(dest){
    alert("Recuerde desactivar los encabezados y pies de página, además de ajustar el tamaño del texto, en su Explorador de Internet, para así obtener un mejor resultado en la impresión.");
	var ventana;
	contextRoot = "<%=request.getContextPath()%>";
	args = '<%=request.getAttribute("args")%>';	
	//args="rutAfiliado=14559885&fechaFiniquito=20051130";
	url = contextRoot + dest + "?" + args;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open(url,"print",prop);
	ventana.print();
}

function openWindow(dest) {
	contextRoot = "<%=request.getContextPath()%>";
	args = '<%=request.getAttribute("args")%>';	
	windowName = "certificado";
	//windowFeatures = "alwaysRaised=yes, dependent=yes, hotkeys=no, " +
	//				 "menubar=no, personalbar=no, resizable=no, scrollBar=no, status=no, titleBar=no, toolbar=no, " +
	//				 "innerHeight=400, innerWidth=600, outerHeight=400, outerWidth=600, height=400, width=600, scerrnX=200, screenY=200, z-lock=yes";
	windowFeatures = "";
	url = contextRoot + dest + "?" + args;
	window.open(url, windowName, windowFeatures);
	return false;
}

function goTo(dest) {
	contextRoot = "<%=request.getContextPath()%>";
	args = '<%=request.getAttribute("args")%>';
	location.href = contextRoot + dest + "?" + args;
	return true;
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
								<html:link action="/deudaCreditoN1" name="paramsHome" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									<bean:message key="global.text.paginaInicioCredito" />
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

		<!--
		<tr>
			<td>
				<table>
					<tbody>
						<tr>										
							<td style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
								<html:link action="/tablaDesarrolloCredito" name="params" scope="request" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									Hága clic aquí para obtener certificado.
								</html:link>
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		-->
		
		<tr>
			<td><html:img page="/img/horiz_gray.gif" style="border : 0; width : 142px; height : 6px;" alt=""/></td>										
		</tr>

		<tr>
			<td>
				<table>
					<tbody>
						<tr>										
							<td style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
								<html:link onclick="imprimir('/tablaDesarrolloCredito.do'); return false;" href="" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									Hága clic aquí para obtener certificado.
								</html:link>
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
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
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>