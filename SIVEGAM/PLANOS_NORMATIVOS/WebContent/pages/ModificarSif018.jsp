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
<link href="./css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="./css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="./js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteCotizacionesDWR.js"></script>

<script type="text/javascript">

	function asignaValor(a){

		document.AgregarSif018Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.AgregarSif018Form.submit();
	}

	function validacionDePeriodos(){
		
		var fechaEmision = document.AgregarSif018Form.txt_FechaEmision.value;
		var mesInformado = document.AgregarSif018Form.txt_MesProceso.value;
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y ao.
		var diaEmision = parseInt(fechaEmision.substring(0,2),10);
		var mesEmision = parseInt(fechaEmision.substring(3,5),10);
		var anioEmision = parseInt(fechaEmision.substring(6,10),10);

		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioInformado = parseInt(fechaSistema.substring(6,10),10);
		
		//solo se debe cumplir esto. lo dems es falso.
		if(((mesInformado < mesEmision) && (anioInformado == anioEmision)) || (anioInformado < anioEmision)){
			alert("La Fecha de Emisin informada no puede ser mayor al mes del periodo procesado.");
			document.getElementById("txt_FechaEmision").value = "";
			return false;
		}
		
		return true;

	}

	function validaIngresoDeCampos(){
		
		if( Trim(document.AgregarSif018Form.dbx_CodArchivo.value) == 0 ){
			alert("Falta ingresar el campo Cdigo Archivo.");
			return false;
		}	
		if( Trim(document.AgregarSif018Form.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.txt_NumVerif.value) == ""){
			alert("Falta ingresar el campo Dgito Verificador Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.txt_NombreEmpresa.value) == "" ){
			alert("Falta ingresar el campo Nombre Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.dbx_ModPago.value) == 0 ){
			alert("Falta ingresar el campo Modalidad de Pago.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.txt_MontoDocumento.value) == "" ){
			alert("Falta ingresar el campo Monto Documento.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.txt_NumeroSerie.value) == "" ){
			alert("Falta ingresar el campo Nmero Serie.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.txt_NumeroDocumento.value) == "" ){
			alert("Falta ingresar el campo Nmero Documento.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.txt_FechaEmision.value) == "" ){
			alert("Falta ingresar el campo Fecha Emisin.");
			return false;
		}
		if( Trim(document.AgregarSif018Form.dbx_CodBanco.value) == 0 ){
			alert("Falta ingresar el campo Cdigo Banco.");
			return false;
		}
		
			
		return true;
		
	}

	/**Funcion que limpia los campos del formulario una vez que se han ingresado en el formulario.*/
	function limpiarCamposFormulario(){
		document.AgregarSif018Form.dbx_ModPago.disabled = true ;
		document.AgregarSif018Form.dbx_CodArchivo.disabled = true ;
		document.AgregarSif018Form.dbx_CodArchivo.value   = 18 ;
		document.AgregarSif018Form.txt_Rut.value   = "" ;
		document.AgregarSif018Form.txt_NumVerif.value   = "" ;
		document.AgregarSif018Form.txt_NombreEmpresa.value   = "" ;
		document.AgregarSif018Form.dbx_ModPago.value   = 2 ;
		document.AgregarSif018Form.txt_MontoDocumento.value   = "" ;
		document.AgregarSif018Form.txt_NumeroSerie.value   = "" ;
		document.AgregarSif018Form.txt_NumeroDocumento.value   = "" ;
		document.AgregarSif018Form.txt_FechaEmision.value   = "" ;
		document.AgregarSif018Form.dbx_CodBanco.value   = 0 ;
		document.AgregarSif018Form.txt_FechaCobro.value  = "" ;

	}
		
	function modificarRegistroSif018(){
		
		
		var idSif018_glob = document.AgregarSif018Form.idSif018_glob.value;
		var codArchivo = document.AgregarSif018Form.dbx_CodArchivo.value;
		var rutEmpresa = document.AgregarSif018Form.txt_Rut.value;
		var dvEmpresa = document.AgregarSif018Form.txt_NumVerif.value;
		var nombreEmpresa = document.AgregarSif018Form.txt_NombreEmpresa.value;
		var modalidadPago = document.AgregarSif018Form.dbx_ModPago.value;
		var montoDocumento = document.AgregarSif018Form.txt_MontoDocumento.value;
		var numeroSerie = document.AgregarSif018Form.txt_NumeroSerie.value;
		var numeroDocumento = document.AgregarSif018Form.txt_NumeroDocumento.value;
		var fechaEmision = document.AgregarSif018Form.txt_FechaEmision.value;
		var codigoBanco = document.AgregarSif018Form.dbx_CodBanco.value;
		var fechaCobro = document.AgregarSif018Form.txt_FechaCobro.value;
					
		if((validaIngresoDeCampos() == true) && (validacionDePeriodos() == true)){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
			document.AgregarSif018Form.btn_Modificar.disabled=true;
			document.AgregarSif018Form.btn_Cancelar.disabled=true;
			
			EditarReporteCotizacionesDWR.updateSif018(idSif018_glob,'18',rutEmpresa,dvEmpresa,nombreEmpresa,modalidadPago,montoDocumento,numeroSerie,numeroDocumento,fechaEmision,codigoBanco,fechaCobro,function(data){
				
				var resp = null;
				resp = data.codResultado;
				
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				document.AgregarSif018Form.btn_Modificar.disabled=false;
				document.AgregarSif018Form.btn_Cancelar.disabled=false;
				if(resp == 0){
					alert("Los datos se han agregado exitosamente.");
					enviaFormulario(1);
				}else{
					alert("Error al momento de guardar los datos, intentelo nuevamente.");
				}	
			});
		}else{
			
			//alert("No es posible ingresar el registro, debido a que faltan campos por ingresar.");	
			return false;
		}	
	}
	
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.AgregarSif018Form.txt_Rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.AgregarSif018Form.txt_NumVerif.value = digitoVerificador;
	}

	function cargarMesInformado(){
		
		var idTipoReporte = 18;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.AgregarSif018Form.txt_MesProceso.value = data.periodoProceso;
			}else{
				document.AgregarSif018Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
		limpiarCamposFormulario();
	}
	
	function cerrarVentana(){
		window.close();
	}	
	function cargarModificar(){
		
		var id = '<%=session.getAttribute("idSelectedItem")%>';
		correlativoRequest = id;
		if(id == "" || id == "null")
		{
			return false;
		}else{	
			obtenerDataCruzada(id);
		}
	}	

	function obtenerDataCruzada(correlativo){

		document.AgregarSif018Form.idSelectedItem.value = '<%=session.getAttribute("idSelectedItem")%>';
		document.AgregarSif018Form.idSif018_glob.value = '<%=session.getAttribute("idSif018_glob")%>';
		document.AgregarSif018Form.rutSearch.value = '<%=session.getAttribute("rutSearch")%>';
		var idSelectedItem = '2'; 
		var idSif018_glob = document.AgregarSif018Form.idSif018_glob.value;
		var rutSearch = document.AgregarSif018Form.rutSearch.value;		
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerDatosSif018ParaEditar(rutSearch,idSif018_glob,idSelectedItem, function(data){

			var sif018 = null;
			sif018 = data.listSif018[0];
			
			if(sif018 != null)
			{
			
				document.AgregarSif018Form.txt_Rut.value = sif018.rut_empleador;
				document.AgregarSif018Form.txt_NumVerif.value = sif018.dv_empleador;
				document.AgregarSif018Form.txt_NombreEmpresa.value = Trim(sif018.nombre_empleador);
				document.AgregarSif018Form.dbx_ModPago.value = sif018.mod_pago;
				document.AgregarSif018Form.txt_MontoDocumento.value = sif018.monto_documento;
				document.AgregarSif018Form.txt_NumeroDocumento.value = Trim(sif018.numero_documento);
				document.AgregarSif018Form.txt_NumeroSerie.value = Trim(sif018.numero_serie);
				document.AgregarSif018Form.dbx_CodBanco.value = sif018.codigo_banco;
				document.AgregarSif018Form.txt_FechaCobro.value = sif018.fechaRendicionDate;
				document.AgregarSif018Form.txt_FechaEmision.value = sif018.fechaEmisionDocumentoDate;					
			}
		});
		
		DWREngine.setAsync(true);	
	}	

</script>
</head>
<body onload="cargarMesInformado();cargarModificar();">
<html:form action="/agregarSif018.do">
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
	<input type="hidden" name="idSelectedItem">
	<input type="hidden" name="idSif018_glob">
	<input type="hidden" name="rutSearch">

	<table width="1100" border="0">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="enviaFormulario(1);"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp;
				<!-- <a href="#" align="right" onClick="enviaFormulario(-1);" ><B>Cerrar Sesin</B></a> -->
			</td>
		</tr>
		<tr>
			<td height="25"><strong><p1>
			MODIFICAR REGISTROS REPORTE DIVISI&Oacute;N RECUPERACI&Oacute;N COTIZACIONES SIF018 </p1></strong></td>
		</tr>
		<tr></tr>
		<tr>
			<td><br/>
			<p><p2>1. Identificaci&oacute;n de campos para Informe de Documentos Safem Emitidos en Reemplazo de Documentos Caducados</p2></p>
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
					<td height="40">
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
						<strong>Modalidad de pago</strong>
					</td>
					<td colspan="40">
						<!-- <input type="text" name="txt_ModPAgo" id="txt_ModPAgo" size="19" maxlength="2" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" /> -->
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
					<td colspan="40">
						<input type="text" name="txt_MontoDocumento" id="txt_MontoDocumento" size="19" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" />
					</td>
				</tr>
				<tr>
					<td height="40"><strong>N&uacute;mero de Serie</strong></td>
					<td >
						<input type="text" name="txt_NumeroSerie" id="txt_NumeroSerie" size="19" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>
					<td >&nbsp;</td>
					<td>
						<strong>N&uacute;mero  Documento</strong></td>
					<td >
						<input type="text" name="txt_NumeroDocumento" id="txt_NumeroDocumento" size="19" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>	
				</tr>
				<tr>	
					<td height="40">
						<strong>Fecha Emisi&oacute;n</strong></td>
					<td >
						<input type="text" name="txt_FechaEmision" id="txt_FechaEmision" size="19" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">
						<IMG style="cursor:hand" border="0" src="./images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaEmision);validacionDePeriodos();"/>
					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Fecha de Cobro</strong></td>
					<td >
						<input type="text" name="txt_FechaCobro" id="txt_FechaCobro" size="19" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
						<IMG style="cursor:hand" border="0" src="./images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaCobro);validacionDePeriodos();"/>
					</td>
				</tr>			
				<tr>
					<td height="40"><strong>C&oacute;digo de Banco</strong></td>
					<td colspan="40">
						<!-- <input type="text" name="txt_CodBanco" id="txt_CodBanco" size="19" maxlength="5" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"> -->
						<html:select property="dbx_CodBanco" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCodigoBanco" property="cod_banco_normativa" labelProperty="desc_cod_banco_normativa"/>
						</html:select>					
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<html:select property="dbx_CodArchivo" styleClass="dbx_codArchivo" value="0" style="visibility:hidden">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCodigoArchivo" property="id_codigo_archivo" labelProperty="desc_codigo_archivo"/>
						</html:select>
				</tr>	
			</table>
			</td>
		</tr>
		<tr>
			<td height="37" align="right" width="80%">
				<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="modificarRegistroSif018();"/>
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);"/> 
				<!--<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cerrar" onclick="javascript:closeSesion()" />-->
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
					<IMG border="0" src="./images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	</div>
</div>
</html:form>
</body>	
</html:html>

