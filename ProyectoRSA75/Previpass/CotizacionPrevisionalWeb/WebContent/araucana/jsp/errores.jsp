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
	<div class="div-datos" align="center"><b>No Existen Errores</b></div>	
</logic:empty>
<logic:notEmpty name="informe" property="listadoResumen">
<div class="div-datos">
<p><b>Empresa:</b></p>

<b><bean:write name="informe" property="empresa.idEmpresaFmt"/> <bean:write name="informe" property="empresa.razonSocial"/></b> <br><br>
En validaci&oacute;n de la informaci&oacute;n de proceso <b><logic:equal name="informe" property="tipoProceso" value="R">Remuneraci&oacute;n</logic:equal><logic:equal name="informe" property="tipoProceso" value="A">Reliquidaci&oacute;n</logic:equal><logic:equal name="informe" property="tipoProceso" value="G">Gratificaci&oacute;n</logic:equal><logic:equal name="informe" property="tipoProceso" value="D">Deposito Convenido</logic:equal></b> remitida en el archivo del <bean:write name="informe" property="nomina.recibida" format="dd/MM/yyyy"/> <bean:write name="informe" property="nomina.recibida" format="HH:mm"/> se informa a Ud. que se ha detectado la(s) siguiente(s) observaci&oacute;n(es) 
que impiden la creaci&oacute;n de su comprobante:
<br/><br/>
<logic:iterate id="listado" name="informe" property="listadoResumen">
	<b>- <bean:write name="listado" property="cotizantePendiente.nombre"/> <bean:write name="listado" property="cotizantePendiente.apellidoPat"/> <bean:write name="listado" property="cotizantePendiente.apellidoMat"/>, <bean:write name="listado" property="cotizantePendiente.idCotizante"/><logic:equal name="informe" property="tipoProceso" value="A">, Per&iacute;odo:<bean:write name="listado" property="cotizantePendiente.periodo"/></logic:equal></b>
	<logic:iterate id="error" name="listado" property="errores">
	<ul>
		<li><bean:write name="error" /></li>
	</ul>
	</logic:iterate>
</logic:iterate>
Consecuente con lo anterior, <b>no es posible procesar esta informaci&oacute;n</b>, por lo que es necesario corregir y reenviar dentro de los horarios establecidos, o bien modifique en l&iacute;nea conect&aacute;ndose a su sitio <a href="http://www.cp.cl" >www.cp.cl</a>
<br/><br/>
<img src="<c:url value="/img/soporte.gif" />"/>
</div></logic:notEmpty>
<br/><br/>
<div align="center" class="div-datos">
	Cotizaci&oacute;n Previsional Electr&oacute;nica<br/>
	Call Center : 600 4228 100<br/>
	<a href="mailto:cp@laaraucana.cl">Enviar Mail a: Soporte Clientes</a><br/>
	www.cp.cl<br/>
</div>
