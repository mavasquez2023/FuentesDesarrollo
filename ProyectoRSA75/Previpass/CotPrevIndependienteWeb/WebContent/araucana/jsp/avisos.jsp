<%@ include file="/html/comun/taglibs.jsp" %>
<style> 
.div-datos {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #333333;
	padding: 4px;
	background-position: top;
}
</style>
<logic:empty name="informe" property="listadoResumen">
	<br/><br/>
	<div class="div-datos" align="center"><b>No Existen Avisos</b></div>
</logic:empty>
<logic:notEmpty name="informe" property="listadoResumen">
	<div class="div-datos">
		<p><b>Independiente:</b></p>

		<b><bean:write name="informe" property="empresa.idEmpresaFmt"/> <bean:write name="informe" property="empresa.razonSocial"/> Convenio N�<bean:write name="informe" property="nomina.idConvenio"/></b> <br><br>
		En validaci&oacute;n de la informaci&oacute;n de proceso <b><logic:equal name="informe" property="tipoProceso" value="R">Remuneraci&oacute;n</logic:equal><logic:equal name="informe" property="tipoProceso" value="A">Reliquidaci&oacute;n</logic:equal><logic:equal name="informe" property="tipoProceso" value="G">Gratificaci&oacute;n</logic:equal><logic:equal name="informe" property="tipoProceso" value="D">Deposito Convenido</logic:equal></b> remitida en el archivo del <bean:write name="informe" property="nomina.recibida" format="dd/MM/yyyy"/> <bean:write name="informe" property="nomina.recibida" format="HH:mm"/> se informa a Ud. que se ha detectado la(s) siguiente(s) observaci&oacute;n(es) 
		<br/>
		<br/>
		<logic:iterate id="listado" name="informe" property="listadoResumen">
			<b>- <bean:write name="listado" property="cotizante.nombre"/> <bean:write name="listado" property="cotizante.apellidoPat"/> <bean:write name="listado" property="cotizante.apellidoMat"/>, <bean:write name="listado" property="cotizante.formatRut"/></b>
			<logic:iterate id="aviso" name="listado" property="avisos">
				<ul><li><bean:write name="aviso" /></li></ul>
			</logic:iterate>
		</logic:iterate>
		<br/>
		<br/>
		<img src="<c:url value="/img/soporte.gif" />"/>
	</div>
</logic:notEmpty>
<br/><br/>
<div align="center" class="div-datos">
	Cotizaci&oacute;n Previsional Electr&oacute;nica<br/>
	Call Center : 600 4228 100<br/>
	<a href="mailto:cp@laaraucana.cl">Enviar Mail a: Soporte Clientes</a><br/>
	www.cp.cl<br/>
</div>