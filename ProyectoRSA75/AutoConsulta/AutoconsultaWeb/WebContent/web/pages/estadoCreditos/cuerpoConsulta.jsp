<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic"  %>  
<div style="width: 100%" class="certificado">
	<h1 class="text-center">Consulta Estado Solicitud de Cr�dito</h1>
	<br><br>
	<div class="row">
		<span class="col-4"></span>
		<span class="col-4"><strong>Rut afiliado: </strong> <bean:write name="rutConsulta" scope="request" /></span>
		<span class="col-4"><strong>Fecha Emisi�n: </strong> <bean:write name="fechaHoy" scope="request" /> </span>
	</div>
	<br><br>
	<logic:equal value="persona" name="tipoConsulta" scope="session">
		<p align="justify">
			<logic:equal value="0" name="mensajeRespuesta" property="id" scope="request">
				Su cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra en <strong>Simulaci�n.</strong>
			</logic:equal>
			<logic:equal value="1" name="mensajeRespuesta" property="id" scope="request">
				Su cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra en <strong>Proceso de aprobaci�n</strong>.
			</logic:equal>
			<logic:equal value="2" name="mensajeRespuesta" property="id" scope="request">
				Su cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra en <strong>Proceso de validaci�n de antecedentes</strong>.
			</logic:equal>
			<logic:equal value="3" name="mensajeRespuesta" property="id" scope="request">
				Su cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra <strong>Rechazado</strong>, favor ac�rquese a la sucursal m�s cercana
				para revisar su caso.
				<br><br>
				<a href="http://www.laaraucana.cl/irj/portal/anonymous/sucursales" target="_blank">Ver Red de Sucursales</a>
			</logic:equal>
			<logic:equal value="4" name="mensajeRespuesta" property="id" scope="request">
				Su cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra <strong>Aprobado</strong>, favor ac�rquese a la sucursal m�s cercana.
				<br><br>
				<a href="http://www.laaraucana.cl/irj/portal/anonymous/sucursales" target="_blank">Ver Red de Sucursales</a>
			</logic:equal>
			<logic:equal value="5" name="mensajeRespuesta" property="id" scope="request">
				Usted no registra solicitudes de cr�dito en nuestros sistemas.
			</logic:equal>
		</p>
	</logic:equal>
	<logic:equal value="empresa" name="tipoConsulta" scope="session">
		<p>
			<logic:equal value="0" name="mensajeRespuesta" property="id" scope="request">
				El cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra en <strong>Simulaci�n</strong>.
			</logic:equal>
			<logic:equal value="1" name="mensajeRespuesta" property="id" scope="request">
				El cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra en <strong>Proceso de aprobaci�n</strong>.
			</logic:equal>
			<logic:equal value="2" name="mensajeRespuesta" property="id" scope="request">
				El cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra en <strong>Proceso de validaci�n de antecedentes</strong>.
			</logic:equal>
			<logic:equal value="3" name="mensajeRespuesta" property="id" scope="request">
				El cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra <strong>Rechazado</strong>, favor ind�quele al trabajador afiliado
				que se acerque a la sucursal m�s cercana para revisar su caso.
				<br><br>
				<a href="http://www.laaraucana.cl/irj/portal/anonymous/sucursales" target="_blank">Ver Red de Sucursales</a>
			</logic:equal>
			<logic:equal value="4" name="mensajeRespuesta" property="id" scope="request">
				El cr�dito folio N� <strong><bean:write name="mensajeRespuesta" property="folioCredito" scope="request"/></strong> se encuentra <strong>Aprobado</strong>, favor ind�quele al trabajador afiliado 
				que se acerque a la sucursal m�s cercana.
				<br><br>
				<a href="http://www.laaraucana.cl/irj/portal/anonymous/sucursales" target="_blank">Ver Red de Sucursales</a>
			</logic:equal>
			<logic:equal value="5" name="mensajeRespuesta" property="id" scope="request">
				El trabajador afiliado no registra solicitudes de cr�dito en nuestros sistemas.
			</logic:equal>
		</p>
	</logic:equal>
	</div>