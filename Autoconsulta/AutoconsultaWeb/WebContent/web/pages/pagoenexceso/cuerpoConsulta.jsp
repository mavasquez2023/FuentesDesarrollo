<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic"  %>
<div style="width: 100%" class="certificado">
	<h1 class="text-center">Consulta Devolución de pagos en exceso</h1>
	<br><br>  
	<div class="row">
		<span class="col-4"></span>
		<span class="col-4"><strong>Rut afiliado: </strong> <bean:write name="rutConsulta" scope="request" /></span>
		<span class="col-4"><strong>Fecha Emisión: </strong> <bean:write name="fechaHoy" scope="request" /> </span>
	</div>  
	<br><br>
	<logic:equal value="3" name="mensajeRespuesta" property="codRespuesta" scope="request">
		<p align="justify">
			<logic:equal value="persona" name="tipoConsulta" scope="session">
				Usted registra con fecha 
				<bean:write name="mensajeRespuesta" property="pagoEnExceso.fechaCreacion" scope="request" />
				en caja un saldo a favor de <strong>$<bean:write name="mensajeRespuesta" property="pagoEnExceso.monto" scope="request" format="###,###.##" /></strong>, 
				valor correspondiente a pagos en exceso de cuotas de crédito. </br></br>
				Para efectuar el cobro del monto señalado, acérquese a cualquiera de 
				nuestras oficinas a lo largo de todo el país, presentando su cédula
				de identidad vigente. Si el cobro lo efectúa una tercera persona,
				debe presentar un poder notarial y fotocopia de identidad del titular.</br></br>
				Usted tiene un plazo de 5 años contado desde la fecha indicada para solicitar 
				la restitución.
			</logic:equal>
			<logic:equal value="empresa" name="tipoConsulta" scope="session">
				El trabajador afiliado registra con fecha 
				<bean:write name="mensajeRespuesta" property="pagoEnExceso.fechaCreacion" scope="request" />
				en caja un saldo a favor de <strong>$<bean:write name="mensajeRespuesta" property="pagoEnExceso.monto" scope="request" format="###,###.##" /></strong>, 
				valor correspondiente a pagos en exceso de cuotas de crédito. </br></br>
				Para efectuar el cobro del monto señalado, debe acercarse a cualquiera de 
				nuestras oficinas a lo largo de todo el país, presentando su cédula
				de identidad vigente. Si el cobro lo efectúa una tercera persona,
				debe presentar un poder notarial y fotocopia de identidad del titular.</br></br>
				Tiene un plazo de 5 años contado desde la fecha indicada para solicitar 
				la restitución.
			</logic:equal>
	
		</p>
	</logic:equal>
	<logic:equal value="1" name="mensajeRespuesta" property="codRespuesta" scope="request">
		<p>
		<logic:equal value="persona" name="tipoConsulta" scope="session">
			Usted no registra devoluciones de pago en exceso.
		</logic:equal>
		<logic:equal value="empresa" name="tipoConsulta" scope="session">
			El trabajador afiliado
		</logic:equal>
		 no registra devoluciones de pago en exceso.</p>
	</logic:equal>
	<logic:equal value="5" name="mensajeRespuesta" property="codRespuesta" scope="request">
		<p> Error al verificar datos del afiliado.
		</p>
	</logic:equal>
</div>
</br></br>