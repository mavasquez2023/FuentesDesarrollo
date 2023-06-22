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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroCotizacionesDWR.js"></script>

<script type="text/javascript">
	
	/*Variables globales*/
	var datos = new Array();
	var datosEdit = new Array();
	var modalidadPagoOrigen = new Array();
	var modalidadPagoNuevo = new Array();
	var codBancoOrigen = new Array();
	var codBancoNuevo = new Array();
	var estadoDocumento = new Array();
	
	/*variables globales para manejo de registros en grilla y modificacion de los mismos*/
	var arrayCheck = new Array();
	var idSelectedItem;
	var idSif019_glob = 0;
	var rangoPrimero = 0;
	var rangoSegundo = 0;
	var rutEmpresa_Glob = 0;
	
	var itemArray = 0;
	var itemBanco = 0;
	var itemDocumento = 0;
	var itemPagoNuevo = 0;
	var itemCodigoBancoNuevo = 0;
	
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	/*Objeto solicitud para llenar el arreglo.*/
	function ObjSolicitud(id_sif019, rut_empresa, flagCheck){
		this.id_sif019 = id_sif019;
		this.rut_empresa = rut_empresa;
		this.flagCheck = flagCheck;
	}

	/*Funcion que sera llamada por el onChange del ckechbox*/
	function cambiaFlagCheck(f){

		for(i=0; i<arrayCheck.length; i++)
		{
			arrayCheck[i].flagCheck = 0;
		}
		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif019 == f)
			{
				if(arrayCheck[i].flagCheck == 0)
				{
					arrayCheck[i].flagCheck = 1;
				}else{
					arrayCheck[i].flagCheck = 0;
				}
			}
		}		
	}
	
	/*actualiza el checkbox*/
	function actualizarFlagCheck(f){

		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif019 == f)
			{
				if(arrayCheck[i].flagCheck == 0)
				{
					arrayCheck[i].flagCheck = 1;
				}else{
					arrayCheck[i].flagCheck = 0;
				}
			}
		}		
	}	
	/*Funciones que cargan combobox para ser usadas en la funcionalidad modificar*/
	/*Declaracion de objeto modalidad de pago*/
	function ModalidadDePagoVO(id_modalidad_pago,desc_modalidad_pago){
		
		this.id_modalidad_pago = id_modalidad_pago;
		this.desc_modalidad_pago = desc_modalidad_pago;
	}
	
	/*Declaracion de objeto codigo de banco*/
	function CodigoBancoVO(cod_banco_normativa,desc_cod_banco_normativa){
		this.cod_banco_normativa = cod_banco_normativa;
		this.desc_cod_banco_normativa = desc_cod_banco_normativa;
	}
	
	/*Declaracion de objeto estado del documento*/
	function EstadoDelDocumentoVO(id_estado_documento,desc_estado_documento){
		
		this.id_estado_documento = id_estado_documento;
		this.desc_estado_documento = desc_estado_documento;
	}
		
	/*Funcion que carga el arreglo codigo de banco*/
	function cargarArregloCodigoBanco(){
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerDataCodigoBanco("CodigoBanco", function(data){
			var codigoBanco = null;
			codigoBanco = data;
			
			for(var i=0; i<codigoBanco.length; i++){
				codBancoOrigen[i] = new CodigoBancoVO(codigoBanco[i].cod_banco_normativa,codigoBanco[i].desc_cod_banco_normativa);
				codBancoNuevo[i] = new CodigoBancoVO(codigoBanco[i].cod_banco_normativa,codigoBanco[i].desc_cod_banco_normativa);
			}
		});
		DWREngine.setAsync(true);
	}
	
		
	/*Funcion que carga el arreglo modalidad de pago*/
	function cargarArregloModalidadPago(){
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerDataModalidadPago("ModalidadPago", function(data){
			var modoPago = null;
			modoPago = data;
			
			for(var i=0; i<modoPago.length; i++){
				modalidadPagoOrigen[i] = new ModalidadDePagoVO(modoPago[i].id_modalidad_pago,modoPago[i].desc_modalidad_pago);
				modalidadPagoNuevo[i] = new ModalidadDePagoVO(modoPago[i].id_modalidad_pago,modoPago[i].desc_modalidad_pago);
			}
		});
		DWREngine.setAsync(true);	
	}

	/*funcion que carga el arreglo estado del documento*/
	function cargarArregloEstadoDocumento(){
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerDataEstadoDocumento("EstadoDocumento", function(data){
			var estDocumento = null;
			estDocumento = data;
			
			for(var i=0; i<estDocumento.length; i++){
				estadoDocumento[i] = new EstadoDelDocumentoVO(estDocumento[i].id_estado_documento,estDocumento[i].desc_estado_documento);
			}
		});
		DWREngine.setAsync(true);
	}	
	
	/*Funcion que carga el combobox con el arreglo codigo de banco (Codigo banco origen)*/
	function obtenerComboCodigoBancoOrigen(){
		
		var cmb = document.getElementById("dbx_CodigoBancoOrigen");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<codBancoOrigen.length; j++){
			var item = null;
			item = codBancoOrigen[j];
			
			cod = item.cod_banco_normativa;
			txt = item.desc_cod_banco_normativa;
			
			cmb.options[j+1] = new Option(txt,cod);
		}
	}

	/*Funcion que carga el combobox con el arreglo codigo de banco (Codigo banco nuevo)*/
	function obtenerComboCodigoBancoNuevo(){
		
		var cmb = document.getElementById("dbx_CodigoBancoNuevo");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<codBancoNuevo.length; j++){
			var item = null;
			item = codBancoNuevo[j];
			
			cod = item.cod_banco_normativa;
			txt = item.desc_cod_banco_normativa;
			
			cmb.options[j+1] = new Option(txt,cod);
		}
	}
		
	/*funcion que carga el combobox con el arreglo modalidad de pago (modalidad de pago origen)*/
	function obtenerComboModalidadPagoOrigen(){
		
		var cmb = document.getElementById("dbx_ModoPagoOrigen");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<modalidadPagoOrigen.length; j++){
			
			var item = null;
			item = modalidadPagoOrigen[j];
			
			cod = item.id_modalidad_pago;
			txt = item.desc_modalidad_pago;
			
			cmb.options[j+1] = new Option(txt, cod);
		}
	}

	/*funcion que carga el combobox con el arreglo modalidad de pago (modalidad de pago nuevo)*/
	function obtenerComboModalidadPagoNuevo(){
		
		var cmb = document.getElementById("dbx_ModoPagoNuevo");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<modalidadPagoNuevo.length; j++){
			
			var item = null;
			item = modalidadPagoNuevo[j];
			
			cod = item.id_modalidad_pago;
			txt = item.desc_modalidad_pago;
			
			cmb.options[j+1] = new Option(txt, cod);
		}
	}
	
	/*Funcion que carga el combobox con el arreglo de los tipos de estados del documento*/
	function obtenerComboEstadoDocumento(){
		
		var cmb = document.getElementById("dbx_EstadoDocumento");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<estadoDocumento.length; j++){
			var item = null;
			item = estadoDocumento[j];
			
			cod = item.id_estado_documento;
			txt = item.desc_estado_documento;
			
			cmb.options[j+1] = new Option(txt,cod);
		}
	}
	
	/*funcion que carga el valor predeterminado del combobox de modalidad de pago*/
	function selectedItemCombo(){
		
		document.ModifSif019Form.dbx_ModoPagoOrigen.value = itemArray;
		document.ModifSif019Form.dbx_CodigoBancoOrigen.value = itemBanco;
		document.ModifSif019Form.dbx_EstadoDocumento.value = itemDocumento;
		document.ModifSif019Form.dbx_ModoPagoNuevo.value = itemPagoNuevo;
		document.ModifSif019Form.dbx_CodigoBancoNuevo.value = itemCodigoBancoNuevo;
	}
	
	/*funcion que llama a la funcion cargar arreglo modalidad de pago para cargar dicho arreglo.
	Esta funcion es llamada al incio desde el onload.*/
	function cargarArregloParametricas(){
		
		cargarArregloModalidadPago();
		//cargarArregloModalidadPagoNuevo();
		cargarArregloCodigoBanco();
		//cargarArregloCodigoBancoNuevo();
		cargarArregloEstadoDocumento();
	}
	
	/*Construccion pantalla para campos modificables*/
	function obtenerHeaderEstadistica(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center" style="color: blue"><b>EDICIÓN REPORTE COTIZACIÓN SIF018</b></p>' + 
						'<p align="left" style="color: blue"> 1.- Detalle de campos</p>'+
					'</td>'+
				'</tr>';
	}
	
	function esconderDiv()
	{
		actualizarFlagCheck(idSif019_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}
	
	function obtenerHeaderCancelarEstadistico()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="updateCamposSif019();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivCamposModificables();"/>'+
					'</td>'+
				'</tr>';
	}
	
	var nombre_empresa = "";
	var mes_origen_gasto = "";
	var numero_serie_origen = "";
	var numero_documento_origen = "";
	var monto_documento_origen = "";
	var numero_serie_nuevo = "";
	var numero_documento_nuevo = "";
	var monto_documento_nuevo = "";
	var fecha_emision_origen = "";
	var fecha_emision_nuevo = "";
	
	function obtenerFilaTablaEstadistica(datoEdit)
	{
		itemDocumento = datoEdit.estado_doc_orig;
		itemArray = datoEdit.modo_pago_orig;
		itemBanco = datoEdit.codigo_banco_orig;
		itemPagoNuevo = datoEdit.modo_pago_nuevo;
		itemCodigoBancoNuevo = datoEdit.codigo_banco_nuevo;
		nombre_empresa = datoEdit.nombre_empresa;
		mes_origen_gasto = datoEdit.mes_origen_gasto;
		numero_serie_origen = datoEdit.num_serie_orig;
		numero_documento_origen = datoEdit.num_docum_orig;
		monto_documento_origen = datoEdit.monto_docum_orig;
		numero_serie_nuevo = datoEdit.num_serie_nuevo;
		numero_documento_nuevo = datoEdit.num_docum_nuevo;
		monto_documento_nuevo = datoEdit.monto_docum_nuevo;
		fecha_emision_origen = datoEdit.fechaEmisionOrigenDate;
		fecha_emision_nuevo = datoEdit.fechaEmisionNuevoDate;
	
		var texto = "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Rut Empresa" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutEmpresa' id='txt_rutEmpresa' value='" + datoEdit.rut_empresa + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvEmpresa' id='txt_dvEmpresa' value='" + datoEdit.dv_empresa + "' disabled='true' size='2'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Nombre Empresa" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreEmpresa' id='txt_nombreEmpresa' value='" + datoEdit.nombre_empresa + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Mes Origen Gasto" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_mesOrigenGasto' id='txt_mesOrigenGasto' value='" + datoEdit.mes_origen_gasto + "' size='15' maxlength='8' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Estado Documento Origen" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_EstadoDocumento' id='dbx_EstadoDocumento' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Modalidad de Pago Origen" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_ModoPagoOrigen' id='dbx_ModoPagoOrigen' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Número Serie origen" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroSerieOrigen' id='txt_numeroSerieOrigen' value='" + datoEdit.num_serie_orig + "' size='15' maxlength='20' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Número Documento Origen" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroDocumentoOrigen' id='txt_numeroDocumentoOrigen' value='" + datoEdit.num_docum_orig + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Monto Documento Origen" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_montoDocumentoOrigen' id='txt_montoDocumentoOrigen' value='" + datoEdit.monto_docum_orig + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Código Banco Origen" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_CodigoBancoOrigen' id='dbx_CodigoBancoOrigen' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Modalidad de Pago Nuevo" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_ModoPagoNuevo' id='dbx_ModoPagoNuevo' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Número Serie Nuevo" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroSerie' id='txt_numeroSerie' value='" + datoEdit.num_serie_nuevo + "' size='15' maxlength='20' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Número Documento Nuevo" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroDocumento' id='txt_numeroDocumento' value='" + datoEdit.num_docum_nuevo + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Monto Documento Nuevo" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_montoDocumento' id='txt_montoDocumento' value='" + datoEdit.monto_docum_nuevo + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='25%'>" + " " + "Código Banco Nuevo" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_CodigoBancoNuevo' id='dbx_CodigoBancoNuevo' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
	   				 	"<table width='99%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='2'>"+
							"<tr><td colspan='4' width='100%' align='center' style='color: blue'><p><b>(Para edición de fechas, utilizar calendario)</b></p></td></tr>"+
							"<tr>"+
								"<td class='texto' align='left' width='25%'>" + " " + "Fec. Emisión Origen" + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_fecEmisionOrigen' id='txt_fecEmisionOrigen' value='" + datoEdit.fechaEmisionOrigenDate + "' size='10' disabled='true'/>"+
									"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_fecEmisionOrigen)'/>"+
								"</td>"+
								"<td class='texto' align='left' width='25%'>" + " " + "Fec. Emisión Nuevo" + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_fecEmisionNuevo' id='txt_fecEmisionNuevo' value='" + datoEdit.fechaEmisionNuevoDate + "' size='10' disabled='true'/>"+
									"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_fecEmisionNuevo)'/>"+
								"</td>"+																					
	   						"</tr>"+
	   					"</table>"+
			   		"</tr>";	
		return texto;
	}


	var cantidadRegistros = 1;
	function cargaDatosEstadisticos()
	{
		var contenidoTablaEstadistica = "";
		
		for(var i=0;i<20;i++)
		{
			if( i < cantidadRegistros ){
				
				contenidoTablaEstadistica = contenidoTablaEstadistica + obtenerFilaTablaEstadistica(datosEdit[i]);
				//asignaValorCampoSif018(datos[i]);
				
			}
		}
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = obtenerHeaderEstadistica() + contenidoTablaEstadistica + obtenerHeaderCancelarEstadistico() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		obtenerComboModalidadPagoOrigen();
		obtenerComboModalidadPagoNuevo();
		obtenerComboCodigoBancoOrigen();
		obtenerComboCodigoBancoNuevo();
		obtenerComboEstadoDocumento()
		selectedItemCombo();
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}		
	/*Fin pantalla para campos modificables*/
	
	function asignaValor(a){

		document.ModifSif019Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ModifSif019Form.submit();
	}
	
	function cabeceraGrilla(){
		document.getElementById("datosNomina").innerHTML = 	
		'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
			'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Editar</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Eliminar</td>'+	            	
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Correlativo</td>'+	
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mes Origen Gasto</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado Doc. Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modo Pago Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modo Pago Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco Nuevo</td>'+
	        '</tr>'+
	    '</table>';
	}
	
	function obtenerHeaderGrilla(){
		
		return 	'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Editar</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Eliminar</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Correlativo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mes Origen Gasto</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado Doc. Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modo Pago Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modo Pago Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Nuevo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco Nuevo</td>'+
				'</tr>'
	}
	
	/*Funcion que genera la paginación*/
	function generarPaginacion()
	{
		var paginas = (datos.length/cantidadRegistrosPorPagina)+"";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
				
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" cargas de archivos.</font>";
			
		for(var i=0; i<paginas;i++){
			texto = texto +" "+ "<a href='#' onclick='paginarResultados("+(i+1)+");'>"+(i + 1)+"</a>" ;
		}

		var alargue = "<table><tr><td></td></tr></table><table><tr><td></td></tr></table><table><tr><td></td></tr></table>";
		
		texto = alargue + " " + texto;
		document.getElementById('datosPaginacion').innerHTML = "<table><tr><td align='center'>" + texto + '</td></tr></table>';
	}

	/*Función que realiza el paginado.*/
	function paginarResultados(pagina){
	
		var inicio = (pagina-1)*cantidadRegistrosPorPagina;
		var fin = (pagina)*cantidadRegistrosPorPagina;
		document.getElementById("datosNomina").innerHTML = "";
		var contenidoTabla = "";
		
		paginaActual = pagina;

		for(var i=inicio;i<fin;i++){
			
			if(datos[i]!= null)
				var contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
			else
				i = fin;
		}		
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderGrilla() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(paginas);
	}
	
		/*funcion que obtiene la fila de la tabla*/
	function obtenerFilaTabla(dato)
	{
		var casillaChk = "";
		
		for(i=0; i<datos.length; i++)
		{
			if(dato.id_sif019 == arrayCheck[i].id_sif019)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}
		var texto =  " <tr> "+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif019 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='discriminarFuncionEditar();'></td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/papelera_de_reciclaje.GIF' width='16' height='16' onClick='discriminaEliminar();'></td>"+
							"<td class='texto' align='center'>"+ dato.id_sif019 +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_empresa +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.mes_origen_gasto +"</td>"+
							"<td class='texto' align='center'>"+ dato.estado_doc_orig +"</td>"+
							"<td class='texto' align='center'>"+ dato.modo_pago_orig +"</td>"+
							"<td class='texto' align='center'>"+ dato.num_serie_orig +"</td>"+
							"<td class='texto' align='center'>"+ dato.num_docum_orig +"</td>"+
							"<td class='texto' align='center'>"+ dato.monto_docum_orig +"</td>"+
							"<td class='texto' align='center'>"+ dato.fecha_emision_orig +"</td>"+
							"<td class='texto' align='center'>"+ dato.codigo_banco_orig +"</td>"+
							"<td class='texto' align='center'>"+ dato.modo_pago_nuevo +"</td>"+
							"<td class='texto' align='center'>"+ dato.num_serie_nuevo +"</td>"+
							"<td class='texto' align='center'>"+ dato.num_docum_nuevo +"</td>"+
							"<td class='texto' align='center'>"+ dato.monto_docum_nuevo +"</td>"+
							"<td class='texto' align='center'>"+ dato.fecha_emision_nuevo +"</td>"+
							"<td class='texto' align='center'>"+ dato.codigo_banco_nuevo +"</td>"+
	   				 "</tr>";
		return texto;
	}
	
	/*funcion que carga los datos en la grilla*/
	var cantidadRegistrosPorPagina = 20;
	var datos = new Array();
	var paginaActual = 1;
	
	function cargaDatosEnGrilla()
	{
		var contenidoTabla = "";
		
		for(var i=0; i<datos.length; i++)
		{
			if( i < cantidadRegistrosPorPagina ){
				
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
				//asignaValorCampoSif018(datos[i]);
				//llenarCamposEstaticos(datos[i]);
			}
		}
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderGrilla() + contenidoTabla + "</table>";
		
		generarPaginacion();

	}	

	/*Funcion que limpia los datos de busqueda*/
	function limpiarDatosBusqueda(){
		
		document.ModifSif019Form.txt_rut.value = "";
		document.ModifSif019Form.txt_digitoVerificador.value = "";
		document.ModifSif019Form.txt_primerRango.value = "";
		document.ModifSif019Form.txt_segundoRango.value = "";
		document.ModifSif019Form.dbx_filtroBusqueda.value = 0;
		validaFiltroDeBusqueda();
	}
	
	/*Funcion DWR que carga el arreglo de datos de acuerdo a la consulta.*/
	function consultarDatosPorRut(){

		var rut = document.ModifSif019Form.txt_rut.value;
		var digVerificador = document.ModifSif019Form.txt_digitoVerificador.value;
		idSelectedItem = 1;
		rutEmpresa_Glob = rut;
		arrayCheck = new Array();
		DWREngine.setAsync(false);
		
		if(rut.length != 0 && digVerificador.length != 0)
		{
			if(ValidadorRUT(rut,digVerificador)== false)	
			{
				document.ModifSif019Form.txt_digitoVerificador.value = "";
			
			}else{	
				
				EditarReporteCotizacionesDWR.obtenerDatosSif019PorRut(rut, function(data){
					
					datos = data.listSif019;
					
					//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
					for(p=0; p<datos.length; p++){
						arrayCheck[p] = new ObjSolicitud(datos[p].id_sif019, datos[p].rut_empresa, 0);
					}
					
					document.getElementById("datosNomina").innerHTML = "";
					
					if(datos != null){
						
						cargaDatosEnGrilla();
						cargarCamposEstaticos();
						cargarCamposEstaticosPorRutSif019(rut);
						limpiarDatosBusqueda();
					}			
				});
			}
		}else{
			alert("Debe ingresar un rut válido para realizar una búsqueda.");
		}
			
		DWREngine.setAsync(true);	
	}
	
	/*Funcion que consulta los datos por rango de correlativo*/
	function busquedaPorCorrelativo(){

		var primerRango = document.ModifSif019Form.txt_primerRango.value;
		var segundoRango = document.ModifSif019Form.txt_segundoRango.value;

		if(primerRango == "" || segundoRango == ""){
			alert("Debe ingresar un rango válido para realizar la búsqueda.");
			return false;
		}
		
		if(parseInt(primerRango,10) > parseInt(segundoRango,10)){
			
			document.ModifSif019Form.txt_primerRango.value = "";
			document.ModifSif019Form.txt_segundoRango.value = "";
			alert("El valor del primer campo no debe ser mayor al segundo campo.");
			return false;
		}
		
		if((parseInt(segundoRango,10) - parseInt(primerRango,10)) > 100){
			
			document.ModifSif019Form.txt_primerRango.value = "";
			document.ModifSif019Form.txt_segundoRango.value = "";
			alert("El rango ingresado no debe exceder las 100 unidades.");
			return false;
		}
				
		idSelectedItem = 2;
		rangoPrimero = primerRango;
		rangoSegundo = segundoRango;
		arrayCheck = new Array();
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.obtenerDatosSif019PorCorrelativo(primerRango, segundoRango, function(data){

			datos = data.listSif019;
					
			document.getElementById("datosNomina").innerHTML = "";
					
				if(datos != null){

				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
					for(p=0; p<datos.length; p++){
						arrayCheck[p] = new ObjSolicitud(datos[p].id_sif019, datos[p].rut_empresa, 0);
					}
											
					cargaDatosEnGrilla();
					cargarCamposEstaticos();
					obtenerDataEstaticaPorID(rangoPrimero,rangoSegundo);
					limpiarDatosBusqueda();
				}			
			});
		
		DWREngine.setAsync(false);
		
	}

	/*Obtiene la data estatica del formulario, dada una busqueda por id.*/
	function obtenerDataEstaticaPorID(a,b){
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.dataEstaticaPorIdSif019(a,b, function(data){
			
			var dat = new Array();
			
			dat = data.listSif019;
			
			if(dat != null){
				for(var i=0; i<dat.length; i++)
				{
					document.ModifSif019Form.txt_fechaProceso.value = dat[i].fecha_proceso;
					document.ModifSif019Form.txt_codigoEntidad.value = dat[i].codigo_entidad;
					document.ModifSif019Form.txt_codigoArchivo.value = dat[i].codigo_archivo;
				}
			}else{
				document.ModifSif019Form.txt_fechaProceso.value = "";
				document.ModifSif019Form.txt_codigoEntidad.value = "";
				document.ModifSif019Form.txt_codigoArchivo.value = "";
			}
		});
		DWREngine.setAsync(true);
	}
		
	/*Funcion que carga datos estaticos*/
	function cargarCamposEstaticos(){
		
		DWREngine.setAsync(false);
		
		var tipoArchivo = document.ModifSif019Form.idTipoArchivo.value;
		//var fechaActual = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoArchivo = parseInt(fechaActual.substring(3,5),10)-1;
		var periodoArchivo = document.ModifSif019Form.numeroMes.value;
		
		if(periodoArchivo == 0){
			periodoArchivo = 12;
		}	

		EditarReporteCotizacionesDWR.obtenerDataEstatica(tipoArchivo, periodoArchivo, function(data){
	
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.ModifSif019Form.txt_tipoArchivo.value = data.tipoArchivoGlosa;
				document.ModifSif019Form.txt_periodoArchivo.value = data.periodoArchivoGlosa;
				
			}
		});
		DWREngine.setAsync(true);
	}

	/**Funcion que carga los datos estaticos filtrados por rut.*/
	function cargarCamposEstaticosPorRutSif019(rut){
		
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerEstaticosPorRutSif019(rut, function(data){
			var resp = null;
			resp = data.listSif019;
			
			if(resp != null){
				
				for(var i=0; i<resp.length; i++)
				{
					document.ModifSif019Form.txt_fechaProceso.value = resp[i].fecha_proceso;
					document.ModifSif019Form.txt_codigoEntidad.value = resp[i].codigo_entidad;
					document.ModifSif019Form.txt_codigoArchivo.value = resp[i].codigo_archivo;	
				}
			}else{
				document.ModifSif019Form.txt_fechaProceso.value = "";
				document.ModifSif019Form.txt_codigoEntidad.value = "";
				document.ModifSif019Form.txt_codigoArchivo.value = "";
			}
		});
		
		DWREngine.setAsync(true);
	}	
	/*Funcion que completa el digito verificador*/
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.ModifSif019Form.txt_rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.ModifSif019Form.txt_digitoVerificador.value = digitoVerificador;
	}

	/*Funcion que valida que se va a ejecutar en la busqueda, dada la eleccion del combo box*/
	function validaFiltroDeBusqueda(){
		
		var selectedItem = document.ModifSif019Form.dbx_filtroBusqueda.value;
		var filtroRut = document.getElementById("txt_rut");
		var filtroDV = document.getElementById("txt_digitoVerificador");
		var filtroCorrelativoUno = document.getElementById("txt_primerRango");
		var filtroCorrelativoDos = document.getElementById("txt_segundoRango");
		var buttonSearch = document.getElementById("btn_Buscar");
		
		if(selectedItem == 0){
				document.ModifSif019Form.txt_rut.value = "";
				document.ModifSif019Form.txt_digitoVerificador.value = "";
				document.ModifSif019Form.txt_primerRango.value = "";
				document.ModifSif019Form.txt_segundoRango.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = true;
		}
		
		if(selectedItem == 1){
				document.ModifSif019Form.txt_primerRango.value = "";
				document.ModifSif019Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;			
		}
		
		if(selectedItem == 2){
				document.ModifSif019Form.txt_rut.value = "";
				document.ModifSif019Form.txt_digitoVerificador.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = false;
				filtroCorrelativoDos.disabled = false;
				buttonSearch.disabled = false;			
		}
	}
	
	/*Construccion de mini pantalla para eliminar registros.*/
	function getCabeceraDelete(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center" style="color: blue"><b>Eliminación de Registros</b></p>' + 
						'<p align="center" style="color: blue"> Ingrese rango de correlativos a eliminar.</p>'+
					'</td>'+
				'</tr>';
	}
	
	function getRangeDelete(){
	
		var texto = "<table width='100%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>"+ 
						"<tr>"+
								"<td class='texto' align='center'>"+
									"<input type='text' name='txt_rangoUno' id='txt_rangoUno' size='10' maxlength='6' onkeypress='Upper(this,\"N\")'/>"+
							    	"&nbsp;&nbsp;&nbsp;" +
							    	"<img style='cursor:hand' border='0' src='./images/icono_flecha.GIF' width='20' height='20' onClick='agregarRangoDos();'>"+
							    	"&nbsp;&nbsp;&nbsp;" +
							    	"<input type='text' name='txt_rangoDos' id='txt_rangoDos' size='10' maxlength='6' disabled='true' onkeypress='Upper(this,\"N\")'/>"+
								"</td>"+
						"</tr>"+
					"</table>";
		return texto;			
	}
	
	function getButtonDelete(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Eliminar" onClick="deleteRegistro();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivDelete();"/>'+
					'</td>'+
				'</tr>';
	}
	
	function esconderDivDelete()
	{
		//actualizarFlagCheck(idSif019_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}
	
		
	function getGraphicsDelete(){
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = getCabeceraDelete() + getRangeDelete() + getButtonDelete() + "<tr></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	
	function agregarRangoDos(){
		var variable = document.getElementById("txt_rangoDos");
		variable.disabled = false;
	}
	
	function deleteRegistro(){
		
		var rango_uno = Trim(document.ModifSif019Form.txt_rangoUno.value);
		var rango_dos = Trim(document.ModifSif019Form.txt_rangoDos.value);
		
		
		if(rango_uno == ""){
			alert("No ha ingresado el correlativo para eliminar el registro.");
			return false;
		}
		
		if(rango_uno != "" && rango_dos == ""){
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif019. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif019SinRango(rango_uno);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{
					esconderDivCamposModificables();			
				}
			}	
		}
		
		if(rango_uno != "" && rango_dos != ""){
		
			if(parseInt(rango_uno,10) > parseInt(rango_dos,10)){
				alert("El primer campo no debe ser mayor al segundo campo.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
				return false;
			}
			
			if((rango_uno.length == 0) && (rango_dos.length > 0)){
				alert("No es posible efectuar la opración debido a que no ha ingresado el primer campo.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
				return false;
			}
			
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif019. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif019(rango_uno,rango_dos);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			} 
		}		
	}
	
	function eliminarRegistroSif019SinRango(a){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 19;
		EditarReporteCotizacionesDWR.deleteCotizacionesSinRango(idReporteDelete,a,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se ha eliminado el registro.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}else{
				alert("No es posible eliminar debido a que el correlativo ingresado no esta registrado en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	function eliminarRegistroSif019(a,b){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 19;
		EditarReporteCotizacionesDWR.deleteCotizacionesConRango(idReporteDelete,a,b,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se han eliminado los registros.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}else{
				alert("No es posible eliminar debido a que esos correlativos no estan registrados en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}				
		});
		
		DWREngine.setAsync(true);
	}
	
	function esconderDiv()
	{
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}	
	/*Fin mini pantalla para eliminar registros*/
	
	function esconderDivCamposModificables(){
		actualizarFlagCheck(idSif019_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		if(idSelectedItem == 1){
			consultarDatosUpdateados(rutEmpresa_Glob);
		}
		
		if(idSelectedItem == 2){
			consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
		}		
			
	}
	
	/**Funcion que obtiene los campos a editar, dependiendo del tipo de busqueda efectuada.*/
	function discriminarFuncionEditar(){
		
		datosEdit = new Array();
		if(datos.length == 1){
			obtenerCamposAEditarUnico();
		}else{
			obtenerCamposAEditar();
		}		
	}
	
	function obtenerCamposAEditarUnico(){

		var chkSeleccion = false;
		var rutSearch = "";
		DWREngine.setAsync(false);
		

		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				//if(document.ConsModMasSolForm.chk_CheckGrid[i].checked == true)
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					

					if(idSelectedItem == 1){

						rutSearch = arrayCheck[i].rut_empresa;
						idSif019_glob = arrayCheck[i].id_sif019;
					}
					
					if(idSelectedItem == 2){

						idSif019_glob = arrayCheck[i].id_sif019;
						rutSearch = "";
					}
		
					//EditarReporteCotizacionesDWR.obtenerDatosSif019ParaEditar(rutSearch,idSif019_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif019;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif019Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif019Form.idSif019_glob.value = idSif019_glob;
					document.ModifSif019Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}	
	}
	
	function obtenerCamposAEditar(){

		var chkSeleccion = false;
		var rutSearch = "";
		DWREngine.setAsync(false);
		

		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				//if(document.ConsModMasSolForm.chk_CheckGrid[i].checked == true)
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					

					if(idSelectedItem == 1){

						rutSearch = arrayCheck[i].rut_empresa;
						idSif019_glob = arrayCheck[i].id_sif019;
					}
					
					if(idSelectedItem == 2){

						idSif019_glob = arrayCheck[i].id_sif019;
						rutSearch = "";
					}
		
					//EditarReporteCotizacionesDWR.obtenerDatosSif019ParaEditar(rutSearch,idSif019_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif019;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif019Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif019Form.idSif019_glob.value = idSif019_glob;
					document.ModifSif019Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}			
	}
	
	/**Funcion que verifica que los campos modificables no vayan vacios.*/
	function verificaCamposModificables(){
	
		if(Trim(document.ModifSif019Form.txt_nombreEmpresa.value) == ""){
			alert("Falta ingresar el campo Nombre Empresa.");
			document.ModifSif019Form.txt_nombreEmpresa.value = nombre_empresa;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.txt_mesOrigenGasto.value) == ""){
			alert("Falta ingresar el campo Mes Gasto Origen.");
			document.ModifSif019Form.txt_mesOrigenGasto.value = mes_origen_gasto;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.dbx_EstadoDocumento.value) == 0){
			alert("Falta ingresar el campo Estado Documento.");
			document.ModifSif019Form.dbx_EstadoDocumento.value = itemDocumento;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.dbx_ModoPagoOrigen.value) == 0){
			alert("Falta ingresar el campo Modalidad de Pago.");
			document.ModifSif019Form.dbx_ModoPagoOrigen.value = itemArray;
			return false;
		}
		
		//el numero de serie puede ir vacio.
		/*if(Trim(document.ModifSif019Form.txt_numeroSerieOrigen.value) == ""){
			alert("Falta ingresar el campo Número de Serie Origen.");
			document.ModifSif019Form.txt_numeroSerieOrigen.value = numero_serie_origen;
			return false;
		}*/
		
		if(Trim(document.ModifSif019Form.txt_numeroDocumentoOrigen.value) == ""){
			alert("Falta ingresar el campo Número Documento Origen.");
			document.ModifSif019Form.txt_numeroDocumentoOrigen.value = numero_documento_origen;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.txt_montoDocumentoOrigen.value) == ""){
			alert("Falta ingresar el campo Monto Documento Origen.");
			document.ModifSif019Form.txt_montoDocumentoOrigen.value = monto_documento_origen;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.txt_fecEmisionOrigen.value) == ""){
			alert("Falta ingresar el campo Fecha Emisión Origen.");
			document.ModifSif019Form.txt_fecEmisionOrigen.value = fecha_emision_origen;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.dbx_CodigoBancoOrigen.value) == 0){
			alert("Falta ingresar el campo Código Banco Origen.");
			document.ModifSif019Form.dbx_CodigoBancoOrigen.value = itemBanco;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.dbx_ModoPagoNuevo.value) == 0){
			alert("Falta ingresar el campo Modalidad de Pago Nuevo.");
			document.ModifSif019Form.dbx_ModoPagoNuevo.value = itemPagoNuevo;
			return false;
		}
		
		//El número de seria puede ir vacío.
		/*if(Trim(document.ModifSif019Form.txt_numeroSerie.value) == ""){
			alert("Falta ingresar el campo Número Serie Nuevo.");
			document.ModifSif019Form.txt_numeroSerie.value = numero_serie_nuevo;
			return false;
		}*/
		
		if(Trim(document.ModifSif019Form.txt_numeroDocumento.value) == ""){
			alert("Falta ingresar el campo Número Documento Nuevo.");
			document.ModifSif019Form.txt_numeroDocumento.value = numero_documento_nuevo;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.txt_montoDocumento.value) == ""){
			alert("Falta ingresar el campo Monto Documento Nuevo.");
			document.ModifSif019Form.txt_montoDocumento.value = monto_documento_nuevo;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.txt_fecEmisionNuevo.value) == ""){
			alert("Falta ingresar el campo Fecha Emisión Nuevo.");
			document.ModifSif019Form.txt_fecEmisionNuevo.value = fecha_emision_nuevo;
			return false;
		}
		
		if(Trim(document.ModifSif019Form.dbx_CodigoBancoNuevo.value) == 0){
			alert("Falta ingresar el campo Código Banco Nuevo.");
			document.ModifSif019Form.dbx_CodigoBancoNuevo.value = itemCodigoBancoNuevo;
			return false;
		}
		
		return true;	
	}
	/*Funcion que hace el update a la tabla sif019n*/
	function updateCamposSif019(){
		
		var rutEmpresa = document.ModifSif019Form.txt_rutEmpresa.value;
		var nombreEmpresa = document.ModifSif019Form.txt_nombreEmpresa.value;
		var mesOrigenGasto = document.ModifSif019Form.txt_mesOrigenGasto.value;
		var estadoDocumento = document.ModifSif019Form.dbx_EstadoDocumento.value;
		var modoPagoOrigen = document.ModifSif019Form.dbx_ModoPagoOrigen.value;
		var numeroSerieOrigen = document.ModifSif019Form.txt_numeroSerieOrigen.value;
		var numeroDocumentoOrigen = document.ModifSif019Form.txt_numeroDocumentoOrigen.value;
		var montoDocumentoOrigen = document.ModifSif019Form.txt_montoDocumentoOrigen.value;
		var fecEmisionOrigen = document.ModifSif019Form.txt_fecEmisionOrigen.value;
		var codigoBancoOrigen = document.ModifSif019Form.dbx_CodigoBancoOrigen.value;
		var modoPagoNuevo = document.ModifSif019Form.dbx_ModoPagoNuevo.value;
		var numeroSerie = document.ModifSif019Form.txt_numeroSerie.value;
		var numeroDocumento = document.ModifSif019Form.txt_numeroDocumento.value;
		var montoDocumento = document.ModifSif019Form.txt_montoDocumento.value;
		var fecEmisionNuevo = document.ModifSif019Form.txt_fecEmisionNuevo.value;
		var codigoBancoNuevo = document.ModifSif019Form.dbx_CodigoBancoNuevo.value;
		
		if(verificaCamposModificables() != false){
		
			DWREngine.setAsync(false);
			EditarReporteCotizacionesDWR.updateSif019(idSif019_glob,rutEmpresa,nombreEmpresa,mesOrigenGasto,estadoDocumento,modoPagoOrigen,numeroSerieOrigen,
													  numeroDocumentoOrigen,montoDocumentoOrigen,fecEmisionOrigen,codigoBancoOrigen,modoPagoNuevo,
													  numeroSerie,numeroDocumento,montoDocumento,fecEmisionNuevo,codigoBancoNuevo,function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				
				if(resp == 0){
				
					alert("Los campos han sido modificados de manera exitosa.");
					if(idSelectedItem == 1){
						/*consultarDatosUpdateados(rutEmpresa);
						esconderDiv();*/
						esconderDivCamposModificables();
					}
					if(idSelectedItem == 2){
						/*consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
						esconderDiv();*/
						esconderDivCamposModificables();
					}
						
				}else{
					alert("Ha ocurrido un error y no se han efectuado los cambios.");
					esconderDivCamposModificables();
				}
			});
			
			DWREngine.setAsync(false);		
		}	
		
	}

	
	/*Funcion que consulta por los datos updateados de la tabla sif019n*/
	function consultarDatosUpdateados(rut){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteCotizacionesDWR.obtenerDatosModificadosPorRutSif019(idSelectedItem,rut, function(data){
			
			datos = data.listSif019;
			
			document.getElementById("datosNomina").innerHTML = "";
			
			if(datos != null){
				
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif019, datos[p].rut_empresa, 0);
				}
				
				cargaDatosEnGrilla();
			}			
		});
		DWREngine.setAsync(true);	
	
	}
	
	
	/*funcion que obtiene los datos modificados, dada una busqueda por correlativo, para actualizar la grilla*/
	function consultarDatosUpdateadosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteCotizacionesDWR.obtenerDatosModificadosPorRangoSif019(idSelectedItem, a, b, function(data){
			
			datos = data.listSif019;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif019, datos[p].rut_empresa, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);			
	}
	
	/*Funcion que selecciona por cual de los dos campos (rut o correlativo) va a efectuar la busqueda.*/
	function seleccionaBusqueda(){
		
		var r_ut = document.ModifSif019Form.txt_rut.value;
		var dv_verif = document.ModifSif019Form.txt_digitoVerificador.value;
		var primer_Rango = document.ModifSif019Form.txt_primerRango.value;
		var segundo_Rango = document.ModifSif019Form.txt_segundoRango.value;
		idSelectedItem = document.ModifSif019Form.dbx_filtroBusqueda.value;
		
		if(r_ut.length != 0 && dv_verif.length != 0 && primer_Rango.length == 0 && segundo_Rango.length == 0){
			
			//ejecutar busqueda por rut.
			consultarDatosPorRut();
		}
		
		if(r_ut.length == 0 && dv_verif.length == 0 && primer_Rango.length != 0 && segundo_Rango.length != 0){
			
			//ejecutar busqueda por correlativo.
			busquedaPorCorrelativo();
			
			//respaldo de onload cargarArregloParametricas();
		}	
	}
	
		/*******************************************************************************************/
	/*Funciones que implementan funcion eliminar registro individual de la grilla*/
	/*Funcion que discrimina por cual busqueda eliminar*/
	function discriminaEliminar(){
		var pregunta = confirm("Va a eliminar información sensible. ¿Está seguro que desea continuar?.");
		if(pregunta == true){
		
			if(idSelectedItem == 1){
				if(datos.length == 1){
					eliminarDatoIndividualUnico();
				}else{
					eliminarDatoIndividual();
				}
			}
			
			if(idSelectedItem == 2){
				if(datos.length == 1){
					eliminaDatoIndividualCorrelativoUnico();
				}else{
					eliminaDatoIndividualCorrelativo();
				}	
			}
		}else{
			if(idSelectedItem == 1){
				consultarDatosUpdateados(rutEmpresa_Glob);
			}
		
			if(idSelectedItem == 2){
				consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
			}
		}		
	}
	
	/*FUNCION QUE ELIMINA UN REGISTRO INDIVIDUAL*/
	
	function eliminarDatoIndividualUnico(){
		
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var rut="";
		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 1){
						idSif019_glob = arrayCheck[i].id_sif019;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif019(idSif019_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizarDatosPorRut(rut);
							//esconderDiv();
							esconderDivCamposModificables();	
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}
	}
	
	function eliminarDatoIndividual(){
		
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var rut="";
		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 1){
						idSif019_glob = arrayCheck[i].id_sif019;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif019(idSif019_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							/*actualizarDatosPorRut(rut);
							esconderDiv();*/
							esconderDivCamposModificables();	
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}				
	}
	
	/*funcion que elimina un registro dada una busqueda por correlativo*/
	function eliminaDatoIndividualCorrelativoUnico(){

		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var id_delete = "";
		
		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 2){
						idSif019_glob = arrayCheck[i].id_sif019;
						//rut = arrayCheck[i].rut_afiliado;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif019(idSif019_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							/*actualizaDatosCorrelativo(rangoPrimero,rangoSegundo);
							esconderDiv();*/
							esconderDivCamposModificables();
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}	
			
	}
	function eliminaDatoIndividualCorrelativo(){
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var id_delete = "";
		
		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 2){
						idSif019_glob = arrayCheck[i].id_sif019;
						//rut = arrayCheck[i].rut_afiliado;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif019(idSif019_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							/*actualizaDatosCorrelativo(rangoPrimero,rangoSegundo);
							esconderDiv();*/
							esconderDivCamposModificables();
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}		
	}	

	/*Funcion que actualiza los datos de la grilla dada la eliminacion de un registro dentro del rango de busqueda por correlativo
	mostrado en la grilla. El resultado es pintar nuevamente la grilla de la busqueda inicial pero sin el dato eliminado.*/
	function actualizaDatosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		idSelectedItem = 2;
		
		EditarReporteCotizacionesDWR.actualizarPorCorrelativo019(a, b, function(data){
					
			datos = data.listSif019;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif019, datos[p].rut_empresa, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);		
	}
	
	/**Funcion que actualiza datos con el rut.*/
	function actualizarDatosPorRut(a){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		idSelectedItem = 1;
		
		EditarReporteCotizacionesDWR.obtenerDatosPorRut019(idSelectedItem, a, function(data){
					
			datos = data.listSif019;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif019, datos[p].rut_empresa, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);
	}
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 19;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				if(data.periodoProceso == null){
					document.ModifSif019Form.txt_MesProceso.value = "";
				}else{
				document.ModifSif019Form.txt_MesProceso.value = data.periodoProceso;
				}
				document.ModifSif019Form.numeroMes.value = data.mesConsultado;
			}else{
				document.ModifSif019Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que llama a pantalla para agregar registro, en paralelo con la pantalla de modificacion para no incidir en la consulta en la grilla.*/
	function activarAgregarNuevoRegistro(){
		var mesInformado = document.ModifSif019Form.txt_MesProceso.value;
		if(mesInformado == null || mesInformado == ""){
			alert('Debe procesar el archivo 19 antes de agregar un registro');
		}else{
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1150, height=700";
		window.open("./pages/AgregarSif019.jsp","",opciones);
		}
	}
			
</script>
</head>
<body onload="cabeceraGrilla();cargarArregloParametricas();cargarMesInformado();">
<html:form action="/modificaSif019.do">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idTipoArchivo" value="7">
  <input type="hidden" name="rutEmpresa" value="0">
  <input type="hidden" name="nombreEmpresa" value=" ">
  <input type="hidden" name="modalidadPago" value="0">
  <input type="hidden" name="montoDocumento" value="0">
  <input type="hidden" name="numeroSerie" value=" ">
  <input type="hidden" name="numeroDocumento" value=" ">
  <input type="hidden" name="fechaEmisionDocumento" value="0">
  <input type="hidden" name="codigoBanco" value="0">
  <input type="hidden" name="fechaCobro" value="0">
  <input type="hidden" name="idSif018" value="0">
  <input type="hidden" name="numeroMes" value="0">
   <input type="hidden" name="idSelectedItem">
  <input type="hidden" name="idSif019_glob">
  <input type="hidden" name="rutSearch">
  
  <div id="caja_registro">
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	<table width="1020" border="0">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="javascript:closeSesion()" ><B>Cerrar</B></a>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>MANTENEDOR REPORTE DIVISIÓN RECUPERACIÓN COTIZACIONES SIF019</p1></strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario" disabled="true" size="10" value="<%=session.getAttribute("IDAnalista")%>"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" id="txt_Fecha" disabled="true" size="10" value="<%=session.getAttribute("FechaSistema")%>"/></td>				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Mes Informado</td>
					<td><input type="text" name="txt_MesProceso" id="txt_MesProceso" disabled="true" size="20" /></td>
				</tr>
			</table>
		   </td>	
		</tr>
	<br>
	<br>
	<br>
	<tr>
		<td>
			1.- Campos de Búsqueda
		</td>
	</tr>
	<br>
	<table border="1" width="970" rules="groups">
		<tr>
			<td>Filtro de Búsqueda</td>
			<td colspan="2">
				<html:select property="dbx_filtroBusqueda" styleClass="combobox" value="0" onclick="validaFiltroDeBusqueda();">
					<html:option value="0">Seleccione </html:option>
					<html:option value="1">Rut Empresa </html:option>
					<html:option value="2">Correlativo Archivo</html:option>
				</html:select>
			</td>
			<td>
			 	<strong>N° RUT *</strong> 
			</td>
			<td> 	
			 	<input type="text" name="txt_rut" id="txt_rut" size="10" maxlength="9" onkeypress="Upper(this,'N');" disabled="true" onblur="autoCompletarDigitoVerificador();"/> 
			 	 -  
			 	<input type="text" name="txt_digitoVerificador" id="txt_digitoVerificador" size="1" maxlength="1" onkeyup="Upper(this,'D')" onkeypress="Upper(this,'D')" disabled="true"/> 
			 	<input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onclick="seleccionaBusqueda();" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td colspan="3"></td>
			<td><strong>Correlativo</strong></td>
			<td>
				<input type="text" name="txt_primerRango" id="txt_primerRango" disabled="true" size="4" maxlength="6" onkeypress="Upper(this,'N')"/>
				&nbsp; - &nbsp;
				<input type="text" name="txt_segundoRango" id="txt_segundoRango" disabled="true" size="4" maxlength="6" onkeypress="Upper(this,'N')"/>
			</td>
		</tr>
	</table>	
	</table>
	<br>
	<br>
	<tr>
		<td>
			2.- Detalle documentos caducados incluidos en los documentos del archivo Nª 18
		</td>
	</tr>
	<br>
	<br>
	<table width="970">
		<tr>
			<td>Tipo Archivo</td>
			<td><input type="text" name="txt_tipoArchivo" id="txt_tipoArchivo" disabled="true"/></td>
			<td>Periodo Archivo</td>
			<td><input type="text" name="txt_periodoArchivo" id="txt_periodoArchivo" disabled="true"/></td>
			<td>Fecha Proceso</td>
			<td><input type="text" name="txt_fechaProceso" id="txt_fechaProceso" disabled="true"/></td>
		</tr>
		<tr>
			<td>Código Entidad</td>
			<td><input type="text" name="txt_codigoEntidad" id="txt_codigoEntidad" disabled="true"/></td>
			<td>Código Archivo</td>
			<td><input type="text" name="txt_codigoArchivo" id="txt_codigoArchivo" disabled="true"/></td>
		</tr>
	</table>
	<br>
	<div id="barraScroll" style="position: static; margin-top: 0px; margin-left: 0px; width: 965px; height: 445px; border: 1px solid; overflow: scroll;">
		<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 2200px; height: 150px;">
  		</div>
  	</div>
  	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 20px;">
	</div>
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 920px; background:#999999; filter:alpha(opacity=70);-moz-opacity:70%;">
 		<div id="datosEstadisticas" style="display: none; position: absolute; width: 650px; margin-top: 80px; margin-left: 200px; background-color: #F2F2F2">
		</div>
  	</div>
  	<table width="970">
  		<tr>
  			<td align="right">
  				<input type="button" name="btn_Agregar" id="btn_Agregar" class="btn_limp" value="Eliminar Masivo" onclick="getGraphicsDelete();"/>
				&nbsp;&nbsp;&nbsp;
  				<!-- <input type="button" name="btn_agregar" id="btn_agregar" class="btn_limp" value="Agregar" onclick="enviaFormulario(2);"/> -->
  				<input type="button" name="btn_agregar" id="btn_agregar" class="btn_limp" value="Agregar" onclick="activarAgregarNuevoRegistro();"/>
  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				<!--<input type="button" name="btn_cancelar" id="btn_cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);"/>-->
  				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cerrar" onclick="javascript:closeSesion()" />
  			</td>
  		</tr>
  	</table>
  </div>
</html:form>
</body>
</html:html>
