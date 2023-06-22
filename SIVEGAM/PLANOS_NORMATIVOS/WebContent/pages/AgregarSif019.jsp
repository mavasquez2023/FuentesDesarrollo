<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA DE GENERACION DE REPORTES</title>
<link href="../css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="../css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="../js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/interface/AgregarRegistroCotizacionesDWR.js"></script>


<script type="text/javascript">

	function asignaValor(a){

		document.AgregarSif019Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.AgregarSif019Form.submit();
	}

	function validacionDePeriodos1(){
		
		var fechaEmision = document.AgregarSif019Form.txt_FechaEmisionOrigen.value;
		var mesInformado = document.AgregarSif019Form.numeroMes.value;
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y ao.
		var diaEmision = parseInt(fechaEmision.substring(0,2),10);
		var mesEmision = parseInt(fechaEmision.substring(3,5),10);
		var anioEmision = parseInt(fechaEmision.substring(6,10),10);

		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioInformado = parseInt(fechaSistema.substring(6,10),10);
		
		//solo se debe cumplir esto. lo dems es falso.
		if(((mesInformado < mesEmision) && (anioInformado == anioEmision)) || (anioInformado < anioEmision)){
			alert("La Fecha de Emision Origen informada no puede ser mayor al mes del periodo procesado.");
			document.getElementById("txt_FechaEmision").value = "";
			return false;
		}
		
		return true;

	}
	
	function validacionDePeriodos2(){
		
		var fechaEmision = document.AgregarSif019Form.txt_FechaEmisionNuevo.value;
		var mesInformado = document.AgregarSif019Form.txt_MesProceso.value;
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y ao.
		var diaEmision = parseInt(fechaEmision.substring(0,2),10);
		var mesEmision = parseInt(fechaEmision.substring(3,5),10);
		var anioEmision = parseInt(fechaEmision.substring(6,10),10);

		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioInformado = parseInt(fechaSistema.substring(6,10),10);
		
		//solo se debe cumplir esto. lo dems es falso.
		if(((mesInformado < mesEmision) && (anioInformado == anioEmision)) || (anioInformado < anioEmision)){
			alert("La Fecha de Emision Nueva informada no puede ser mayor al mes del periodo procesado.");
			document.getElementById("txt_FechaEmision").value = "";
			return false;
		}
		
		return true;

	}

	function validaIngresoDeCampos(){
		
		if( Trim(document.AgregarSif019Form.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut Empresa.");
			return false;
		}	
		if( Trim(document.AgregarSif019Form.txt_NumVerif.value) == "" ){
			alert("Falta ingresar el campo Digito Verificador Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_NombreEmpresa.value) == "" ){
			alert("Falta ingresar el campo Nombre Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_meOrigenGasto.value) == "" ){
			alert("Falta ingresar el campo Mes Origen Gasto.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.dbx_ModPagoOrigen.value) == 0 ){
			alert("Falta ingresar el campo Modalidad de Pago Origen.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_MontoDocumentoOrigen.value) == "" ){
			alert("Falta ingresar el campo Monto Documento Origen.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_NumeroSerieOrigen.value) == "" ){
			alert("Falta ingresar el campo Numero Serie Origen.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_NumeroDocumentoOrigen.value) == "" ){
			alert("Falta ingresar el campo Numero Documento Origen.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_FechaEmisionOrigen.value) == "" ){
			alert("Falta ingresar el campo Fecha Emision Origen.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.dbx_CodBancoOrigen.value) == 0 ){
			alert("Falta ingresar el campo Codigo Banco Origen.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.dbx_ModPagoNuevo.value) == 0 ){
			alert("Falta ingresar el campo Modalidad de Pago Nuevo.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_MontoDocumentoNuevo.value) == "" ){
			alert("Falta ingresar el campo Monto Documento Nuevo.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_NumeroSerieNuevo.value) == "" ){
			alert("Falta ingresar el campo Numero Serie Nuevo.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_NumeroDocumentoNuevo.value) == "" ){
			alert("Falta ingresar el campo Numero Documento Nuevo.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.txt_FechaEmisionNuevo.value) == "" ){
			alert("Falta ingresar el campo Fecha Emision Nuevo.");
			return false;
		}
		if( Trim(document.AgregarSif019Form.dbx_CodBancoNuevo.value) == 0){
			alert("Falta ingresar el campo Codigo Banco Nuevo.");
			return false;
		}
			return true;
		
	}

	/**Funcion que limpia los campos del formulario una vez que se han ingresado en el formulario.*/
	function limpiarCamposFormulario(){
		document.AgregarSif019Form.txt_Rut.value   = "" ;
		document.AgregarSif019Form.txt_NumVerif.value   = "" ;
		document.AgregarSif019Form.txt_NombreEmpresa.value   = "" ;
		document.AgregarSif019Form.txt_meOrigenGasto.value   = "" ;
		document.AgregarSif019Form.dbx_ModPagoOrigen.value   = 0 ;
		document.AgregarSif019Form.txt_MontoDocumentoOrigen.value   = "" ;
		document.AgregarSif019Form.txt_NumeroSerieOrigen.value   = "" ;
		document.AgregarSif019Form.txt_NumeroDocumentoOrigen.value   = "" ;
		document.AgregarSif019Form.txt_FechaEmisionOrigen.value   = "" ;
		document.AgregarSif019Form.dbx_CodBancoOrigen.value   = 0 ;
		document.AgregarSif019Form.dbx_ModPagoNuevo.value   = 0 ;
		document.AgregarSif019Form.txt_MontoDocumentoNuevo.value   = "" ;
		document.AgregarSif019Form.txt_NumeroSerieNuevo.value   = "" ;
		document.AgregarSif019Form.txt_NumeroDocumentoNuevo.value   = "" ;
		document.AgregarSif019Form.txt_FechaEmisionNuevo.value   = "" ;
		document.AgregarSif019Form.dbx_CodBancoNuevo.value  = 0 ;	
		document.AgregarSif019Form.dbx_EstDocumento.disabled = true ;
		document.AgregarSif019Form.dbx_EstDocumento.value = 102;
	}
	
	function agregarRegistroSif019(){
		
		
		var rutEmpresa = document.AgregarSif019Form.txt_Rut.value;
		var dvEmpresa = document.AgregarSif019Form.txt_NumVerif.value;
		var nombreEmpresa = document.AgregarSif019Form.txt_NombreEmpresa.value;
		var mesOrigenGasto = document.AgregarSif019Form.txt_meOrigenGasto.value;
		
		var modalidadPagoOrigen = document.AgregarSif019Form.dbx_ModPagoOrigen.value;
		var montoDocumentoOrigen = document.AgregarSif019Form.txt_MontoDocumentoOrigen.value;
		var numeroSerieOrigen = document.AgregarSif019Form.txt_NumeroSerieOrigen.value;
		var numeroDocumentoOrigen = document.AgregarSif019Form.txt_NumeroDocumentoOrigen.value;
		var fechaEmisionOrigen = document.AgregarSif019Form.txt_FechaEmisionOrigen.value;
		var codigoBancoOrigen = document.AgregarSif019Form.dbx_CodBancoOrigen.value;

		var modalidadPagoNuevo = document.AgregarSif019Form.dbx_ModPagoNuevo.value;
		var montoDocumentoNuevo = document.AgregarSif019Form.txt_MontoDocumentoNuevo.value;
		var numeroSerieNuevo = document.AgregarSif019Form.txt_NumeroSerieNuevo.value;
		var numeroDocumentoNuevo = document.AgregarSif019Form.txt_NumeroDocumentoNuevo.value;
		var fechaEmisionNuevo = document.AgregarSif019Form.txt_FechaEmisionNuevo.value;
		var codigoBancoNuevo = document.AgregarSif019Form.dbx_CodBancoNuevo.value;
		
		if(validaIngresoDeCampos() == true){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
			document.AgregarSif019Form.btn_Agregar.disabled=true;
			document.AgregarSif019Form.btn_Cancelar.disabled=true;
			
			AgregarRegistroCotizacionesDWR.insertSif019(rutEmpresa,dvEmpresa,nombreEmpresa,mesOrigenGasto,modalidadPagoOrigen,montoDocumentoOrigen,
														numeroSerieOrigen,numeroDocumentoOrigen,fechaEmisionOrigen,codigoBancoOrigen,
														modalidadPagoNuevo,montoDocumentoNuevo,numeroSerieNuevo,numeroDocumentoNuevo,
														fechaEmisionNuevo,codigoBancoNuevo,function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				document.AgregarSif019Form.btn_Agregar.disabled=false;
				document.AgregarSif019Form.btn_Cancelar.disabled=false;
				
				if(resp == 0){
					alert("Los datos se han agregado exitosamente.");
					limpiarCamposFormulario();
				}	
			});
			
		}else{
			
			//alert("No es posible ingresar el registro, debido a que faltan campos por ingresar.");	
			return false;
		}	
	}
	
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.AgregarSif019Form.txt_Rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.AgregarSif019Form.txt_NumVerif.value = digitoVerificador;
	}

	function cargarMesInformado(){
		
		var idTipoReporte = 19;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.AgregarSif019Form.txt_MesProceso.value = data.periodoProceso;
			}else{
				document.AgregarSif019Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
		limpiarCamposFormulario();
	}
	
	function cerrarVentana(){
		window.close();
	}	
</script>
</head>
<body onload="cargarMesInformado()">
<html:form action="/agregarSif019.do">
<div id="caja_registro">
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>

	<input type="hidden" name="opcion" value="0">

	<table width="1100" border="0">
		<tr>
			<td align="right">
				<!-- <a href="#" align="right" onclick="enviaFormulario(1);"><B>Volver</B></a> -->
				<a href="#" align="right" onclick="cerrarVentana();"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp;
				<!-- <a href="#" align="right" onClick="enviaFormulario(-1);" ><B>Cerrar Sesin</B></a> -->
			</td>
		</tr>
		<tr>
			<td height="25"><strong><p1>
			AGREGAR REGISTROS REPORTE DIVISI&Oacute;N RECUPERACI&Oacute;N COTIZACIONES SIF019 </p1></strong></td>
		</tr>
		<tr></tr>
		<tr>
			<td><br/>
			<p><p2>1. Detalle de documentos caducados incluidos en los documentos del archivo n 18</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="200"></td>
					<td width="312"></td>
					<td></td>
					<td width="200"></td>
					<td width="312"></td>
				</tr>
				<tr>
					<td height="40"><strong>Mes Informado</strong></td>
					<td colspan="4"><input type="text" name="txt_MesProceso" id="txt_MesProceso" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')" disabled="true" /></td>
				</tr>
				<tr>
					<td height="40"><strong>RUT Empresa </strong></td>
					<td>
						<input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autoCompletarDigitoVerificador();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerif" id="txt_NumVerif" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"/>
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Nombre Empresa</strong>
					</td>
					<td>
						<input type="text" name="txt_NombreEmpresa" id="txt_NombreEmpresa" size="50" maxlength="80" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"/>
					</td>
				</tr>
				<tr>
					<td height="40"><strong>Mes Origen Gasto</strong></td>
					<td>
						<input type="text" name="txt_meOrigenGasto" id="txt_meOrigenGasto" size="19" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true"/>
						<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_meOrigenGasto);"/>
					</td>
					<td>&nbsp;</td>
					<td><strong>Estado Documento Origen</strong></td>
					<td >
						<html:select property="dbx_EstDocumento" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListEstadoDocumento" property="id_estado_documento" labelProperty="desc_estado_documento"/>
						</html:select>					
					</td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Modo Pago Origen</strong>
					</td>
					<td colspan="4">
						<!-- <input type="text" name="txt_ModPAgo" id="txt_ModPAgo" size="19" maxlength="2" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" /> -->
						<html:select property="dbx_ModPagoOrigen" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListModalidadPago" property="id_modalidad_pago" labelProperty="desc_modalidad_pago"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="40"><strong>N&uacute;mero de Serie Origen</strong></td>
					<td >
						<input type="text" name="txt_NumeroSerieOrigen" id="txt_NumeroSerieOrigen" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>N&uacute;mero Documento Origen</strong>
					</td>
					<td >
						<input type="text" name="txt_NumeroDocumentoOrigen" id="txt_NumeroDocumentoOrigen" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>	
				</tr>
				<tr>
					<td height="40" >
						<strong>Monto Documento Origen</strong>
					</td>
					<td >
						<input type="text" name="txt_MontoDocumentoOrigen" id="txt_MontoDocumentoOrigen" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" />
					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Fecha Emisi&oacute;n Origen</strong>
					</td>
					<td >
						<input type="text" name="txt_FechaEmisionOrigen" id="txt_FechaEmisionOrigen" size="19" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">
						<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaEmisionOrigen);validacionDePeriodos1();"/>
					</td>
				</tr>
				<tr>
					<td height="40"><strong>C&oacute;digo de Banco Origen</strong></td>
					<td colspan="4">
						<!-- <input type="text" name="txt_CodBanco" id="txt_CodBanco" size="19" maxlength="5" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"> -->
						<html:select property="dbx_CodBancoOrigen" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCodigoBanco" property="cod_banco_normativa" labelProperty="desc_cod_banco_normativa"/>
						</html:select>					
					</td>
				</tr>
			<!-- </table>
			<table width="100%" border="1" rules="groups">	 -->
				<tr>
					<td  height="40">
						<strong>Modo Pago Nuevo</strong>
					</td>
					<td colspan="4">
						<!-- <input type="text" name="txt_ModPAgo" id="txt_ModPAgo" size="19" maxlength="2" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" /> -->
						<html:select property="dbx_ModPagoNuevo" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListModalidadPago" property="id_modalidad_pago" labelProperty="desc_modalidad_pago"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="40"><strong>N&uacute;mero de Serie Nuevo</strong></td>
					<td >
						<input type="text" name="txt_NumeroSerieNuevo" id="txt_NumeroSerieNuevo" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>N&uacute;mero Documento Nuevo</strong></td>
					<td >
						<input type="text" name="txt_NumeroDocumentoNuevo" id="txt_NumeroDocumentoNuevo" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>	
				</tr>
				<tr>
					<td height="40" >
						<strong>Monto Documento Nuevo</strong>
					</td>
					<td >
						<input type="text" name="txt_MontoDocumentoNuevo" id="txt_MontoDocumentoNuevo" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" />
					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Fecha Emisi&oacute;n Nuevo</strong></td>
					<td >
						<input type="text" name="txt_FechaEmisionNuevo" id="txt_FechaEmisionNuevo" size="19" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">
						<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaEmisionNuevo);validacionDePeriodos2();"/>
					</td>
				</tr>
				<tr>
					<td height="40"><strong>C&oacute;digo de Banco Nuevo</strong></td>
					<td colspan="4">
						<!-- <input type="text" name="txt_CodBanco" id="txt_CodBanco" size="19" maxlength="5" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"> -->
						<html:select property="dbx_CodBancoNuevo" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCodigoBanco" property="cod_banco_normativa" labelProperty="desc_cod_banco_normativa"/>
						</html:select>					
					</td>
				</tr>	
			</table>
			</td>
		</tr>
		<tr>
			<td height="37" align="right" width="80%">
				<input type="button" name="btn_Agregar" id="btn_Agregar" class="btn_limp" value="Agregar" onclick="agregarRegistroSif019();"/>
				&nbsp;&nbsp;&nbsp;
				<!-- <input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);"/> -->
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="cerrarVentana();"/>
				
			</td>
		</tr>
	</table>
	<div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1400px; height: 900px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
		<table width="100%">
			<tr>
				<td height="200">
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<IMG border="0" src="../images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	</div>
</div>
</html:form>
</body>
</html:html>

