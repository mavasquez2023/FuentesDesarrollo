<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>

<table border="0" cellpadding="0" cellspacing="0" width="100%"
	height='100%'>
	<tr>
		<td width='160' valign='top' ><%@ include file = "/web/includes/opciones.jsp"%></td>
		<!-- menu de opciones -->
		<td width='1%'>&nbsp;</td>
		<td valign='top'>
		
		<logic:equal name="liqReembol" value="persona">
			<table align="center">
			<tr>
			<td>
			<br>
			<div class="tituloconsultas">
				<bean:message key='consulta.liquidacionreembolsos'/>
			</div>
			</td>
			</tr>
			</table>
		</logic:equal>
		<logic:equal name="liqReembol" value="empresa">
			<table align="center">
			<tr>
			<td>
			<br>
			<div class="tituloconsultas">
				<bean:message key='consulta.prestacionescomplementarias'/>
			</div>
			</td>
			</tr>
			</table>
		</logic:equal> 
		
		<br>
		<div class="texto"><br>
		<div class="textobold"><bean:message
			key="label.liquidacion.selec.periodo" /></div>
		<br>
		<SCRIPT language="JavaScript" type="text/javascript">
function getPeriodo(){
	document.RESUMENForm.periodo.value = document.RESUMENForm.periodos.value;
}

function valida(){     
	if (document.RESUMENForm.periodo.value=="0"){ 
		alert("Debe escoger el periodo a consultar.");
		return false; 
	}
	else 
		return true;
}
</SCRIPT> <html:form action="/getResumenMensual"
			onsubmit="return valida();">
			<html:hidden property="periodo" value="0" />
			<table border="0" cellpadding="2" cellspacing="2" width="50%">
				<tr>
					<td class="textobold"><bean:message
						key="label.liquidacion.empresa.periodo" />:</td>
					<td class="texto"><select name="periodos"
						onchange="javascript:getPeriodo();">
						<option value="0">Seleccione</option>
						<logic:iterate id="cboperiodo" name="lista.periodos"
							scope="session">
							<option
								value="<bean:write name="cboperiodo" property="rePeriodo"/>"><bean:write
								name="cboperiodo" property="descPeriodo" /></option>
						</logic:iterate>

					</select></td>
				</tr>
				<tr>
					<td class="texto" colspan='2' align='center'><html:image
						page='/images/aceptar.gif' /></td>
				</tr>
			</table>
		</html:form></div>


		<!-- End de la pagina particular --></td>
	</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>