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

		document.AgregarSif017Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.AgregarSif017Form.submit();
	}
	
	function validacionDePeriodos1(){
		
		var fechaEmision = document.AgregarSif017Form.txt_FechaEmision.value;
		var mesInformado = document.AgregarSif017Form.txt_MesProceso.value;
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y ao.
		var diaEmision = parseInt(fechaEmision.substring(0,2),10);
		var mesEmision = parseInt(fechaEmision.substring(3,5),10);
		var anioEmision = parseInt(fechaEmision.substring(6,10),10);

		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioInformado = parseInt(fechaSistema.substring(6,10),10);
		
		//solo se debe cumplir esto. lo dems es falso.
		if(((mesInformado < mesEmision) && (anioInformado == anioEmision)) || (anioInformado < anioEmision)){
			alert("La Fecha de Emision informada no puede ser mayor al mes del periodo procesado.");
			document.getElementById("txt_FechaEmision").value = "";
			return false;
		}
		
		return true;

	}
	
	function validacionDePeriodos2(){
		
		var fechaEmision = document.AgregarSif017Form.txt_FechaRendicion.value;
		var mesInformado = document.AgregarSif017Form.txt_MesProceso.value;
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y ao.
		var diaEmision = parseInt(fechaEmision.substring(0,2),10);
		var mesEmision = parseInt(fechaEmision.substring(3,5),10);
		var anioEmision = parseInt(fechaEmision.substring(6,10),10);

		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioInformado = parseInt(fechaSistema.substring(6,10),10);
		
		//solo se debe cumplir esto. lo dems es falso.
		if(((mesInformado < mesEmision) && (anioInformado == anioEmision)) || (anioInformado < anioEmision)){
			alert("La Fecha de Rendicion informada no puede ser mayor al mes del periodo procesado.");
			document.getElementById("txt_FechaEmision").value = "";
			return false;
		}
		
		return true;

	}
	
	function validacionperiodos(){
		var key;
		key = validacionDePeriodos2();
		if(key == validacionDePeriodos1())
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	function validaIngresoDeCampos(){
		
		if( Trim(document.AgregarSif017Form.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut Empresa.");
			return false;
		}	
		if( Trim(document.AgregarSif017Form.txt_NumVerif.value) == "" ){
			alert("Falta ingresar el campo Digito Verificador Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.txt_NombreEmpresa.value) == "" ){
			alert("Falta ingresar el campo Nombre Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.dbx_TipoEgreso.value) == 0 ){
			alert("Falta ingresar el campo Tipo Egreso.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.dbx_ModPago.value) == 0 ){
			alert("Falta ingresar el campo Modalidad de Pago.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.txt_MontoDocumento.value) == "" ){
			alert("Falta ingresar el campo Monto Documento.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.txt_NumeroSerie.value) == "" ){
			alert("Falta ingresar el campo Numero Serie.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.txt_NumeroDocumento.value) == "" ){
			alert("Falta ingresar el campo Numero Documento.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.txt_FechaEmision.value) == "" ){
			alert("Falta ingresar el campo Fecha Emision.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.dbx_CodBanco.value) == 0 ){
			alert("Falta ingresar el campo Codigo Banco.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.txt_NumeroCartola.value) == "" ){
			alert("Falta ingresar el campo Numero Cartola.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.dbx_EstDocumento.value) == 0 ){
			alert("Falta ingresar el campo Estado Documento.");
			return false;
		}
		if( Trim(document.AgregarSif017Form.txt_FechaRendicion.value) == ""){
			alert("Falta ingresar el campo Fecha Rendicion.");
			return false;
		}

			return true;
		
	}

	/**Funcion que limpia los campos del formulario una vez que se han ingresado en el formulario.*/
	function limpiarCamposFormulario(){

		document.AgregarSif017Form.txt_Rut.value   = "" ;
		document.AgregarSif017Form.txt_NumVerif.value   = "" ;
		document.AgregarSif017Form.txt_NombreEmpresa.value   = "" ;
		document.AgregarSif017Form.dbx_TipoEgreso.disabled = true ;
		document.AgregarSif017Form.dbx_ModPago.disabled = true ;
		document.AgregarSif017Form.dbx_TipoEgreso.value   = 1 ;
		document.AgregarSif017Form.dbx_ModPago.value   = 2 ;
		document.AgregarSif017Form.txt_MontoDocumento.value   = "" ;
		document.AgregarSif017Form.txt_NumeroSerie.value   = "" ;
		document.AgregarSif017Form.txt_NumeroDocumento.value   = "" ;
		document.AgregarSif017Form.txt_FechaEmision.value   = "" ;
		document.AgregarSif017Form.dbx_CodBanco.value   = 0 ;
		document.AgregarSif017Form.txt_NumeroCartola.value   = "" ;
		document.AgregarSif017Form.dbx_EstDocumento.value   = 0 ;
		document.AgregarSif017Form.txt_FechaRendicion.value  = "" ;
	}
			
	function agregarRegistroSif017(){
		if(validaIngresoDeCampos()){
			var rutEmpresa = document.AgregarSif017Form.txt_Rut.value;
			var dvEmpresa = document.AgregarSif017Form.txt_NumVerif.value;
			var nombreEmpresa = document.AgregarSif017Form.txt_NombreEmpresa.value;
			var codTipoEgreso = document.AgregarSif017Form.dbx_TipoEgreso.value;
			var modalidadPago = document.AgregarSif017Form.dbx_ModPago.value;
			var montoDocumento = document.AgregarSif017Form.txt_MontoDocumento.value;
			var numeroSerie = document.AgregarSif017Form.txt_NumeroSerie.value;
			var numeroDocumento = document.AgregarSif017Form.txt_NumeroDocumento.value;
			var fechaEmision = document.AgregarSif017Form.txt_FechaEmision.value;
			var codigoBanco = document.AgregarSif017Form.dbx_CodBanco.value;
			var numeroCartola = document.AgregarSif017Form.txt_NumeroCartola.value;
			var estadoDocumento = document.AgregarSif017Form.dbx_EstDocumento.value;
			var fechaRendicion = document.AgregarSif017Form.txt_FechaRendicion.value;
			var validador = true;
			
			validador = validacionperiodos();
			
			if(validador == true){
				document.getElementById("pantallaDeCarga").style.visibility = "visible";
				document.AgregarSif017Form.btn_Agregar.disabled=true;
				document.AgregarSif017Form.btn_Cancelar.disabled=true;
				
				AgregarRegistroCotizacionesDWR.insertSif017(rutEmpresa,dvEmpresa,nombreEmpresa,codTipoEgreso,modalidadPago,montoDocumento,numeroSerie,
															numeroDocumento,fechaEmision,codigoBanco,numeroCartola,estadoDocumento,fechaRendicion,function(data){
					
					var resp = null;
					resp = data.codRespuesta;
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					document.AgregarSif017Form.btn_Agregar.disabled=false;
					document.AgregarSif017Form.btn_Cancelar.disabled=false;
					if(resp == 0){
						alert("Los datos se han agregado exitosamente.");
						limpiarCamposFormulario();
					}else{
						alert("Los datos no se han agregado.");
					}	
				});
				
			}else{
				
				//alert("No es posible ingresar el registro, debido a que faltan campos por ingresar.");	
				return false;
			}	
		}
	}
	
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.AgregarSif017Form.txt_Rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.AgregarSif017Form.txt_NumVerif.value = digitoVerificador;
	}

	function cargarMesInformado(){
		
		var idTipoReporte = 17;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.AgregarSif017Form.txt_MesProceso.value = data.periodoProceso;
			}else{
				document.AgregarSif017Form.txt_MesProceso.value = "";
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
<html:form action="/agregarSif017.do">
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
			AGREGAR REGISTROS REPORTE DIVISI&Oacute;N RECUPERACI&Oacute;N COTIZACIONES SIF017 </p1></strong></td>
			
		</tr>
		<tr></tr>
		<tr>
			<td><br/>
			<p><p2>1. Rendici&oacute;n egresos por saldo a favor empleador (SAFEM)</p2></p>
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
					<td  height="40">
						<strong>RUT Empresa </strong>
					</td>
					<td >
						<input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autoCompletarDigitoVerificador();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerif" id="txt_NumVerif" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">
					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Nombre Empresa</strong></td>
					<td>
						<input type="text" name="txt_NombreEmpresa" id="txt_NombreEmpresa" size="50" maxlength="80" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Tipo de Egreso</strong>
					</td>
					<td colspan="4">
						<html:select property="dbx_TipoEgreso" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoEgreso" property="id_tipo_Egreso" labelProperty="desc_tipo_egreso"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Modalidad de pago</strong>
					</td>
					<td colspan="4">
						<html:select property="dbx_ModPago" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListModalidadPago" property="id_modalidad_pago" labelProperty="desc_modalidad_pago"/>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td  height="40">
						<strong>Monto Documento</strong>
					</td>
					<td colspan="4">
						<input type="text" name="txt_MontoDocumento" id="txt_MontoDocumento" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" />
					</td>
				</tr>
				<tr>
					<td height="40"><strong>N&uacute;mero de Serie</strong></td>
					<td >
						<input type="text" name="txt_NumeroSerie" id="txt_NumeroSerie" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>N&uacute;mero  Documento</strong></td>
					<td >
						<input type="text" name="txt_NumeroDocumento" id="txt_NumeroDocumento" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>	
				</tr>
				<tr>	
					<td height="40">
						<strong>Fecha Emisi&oacute;n Documento</strong></td>
					<td colspan="4">
						<input type="text" name="txt_FechaEmision" id="txt_FechaEmision" size="20" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true" >
						<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaEmision);validacionDePeriodos1();"/>
					</td>
				</tr>
				<tr>
					<td height="40"><strong>C&oacute;digo de Banco</strong></td>
					<td colspan="4">
						<html:select property="dbx_CodBanco" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCodigoBanco" property="cod_banco_normativa" labelProperty="desc_cod_banco_normativa"/>
						</html:select>					
					</td>
				</tr>
				<tr>
					<td height="40">
						<strong>N&uacute;mero Cartola</strong>
					</td>
					<td colspan="4">
						<input type="text" name="txt_NumeroCartola" id="txt_NumeroCartola" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"/></td>
				</tr>
				<tr height="40">
					<td><strong>Estado Documento</strong></td>
					<td colspan="4">
						<html:select property="dbx_EstDocumento" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListEstadoDocumento" property="id_estado_documento" labelProperty="desc_estado_documento"/>
						</html:select>					
					</td>
				</tr>
				<tr>	
					<td height="40">
						<strong>Fecha Rendici&oacute;n</strong></td>
					<td colspan="4">
						<input type="text" name="txt_FechaRendicion" id="txt_FechaRendicion" size="20" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">
						<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaRendicion);validacionDePeriodos2();"/>
					</td>
				</tr>	
			</table>
			</td>
		</tr>
		<tr>
			<td height="37" align="right" width="80%">
				<input type="button" name="btn_Agregar" id="btn_Agregar" class="btn_limp" value="Agregar" onclick="agregarRegistroSif017();"/>
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


