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
<script type="text/javascript" language="JavaScript1.2" src="./js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroCotizacionesDWR.js"></script>

<script type="text/javascript">
	
	/*Variables globales*/
	var datos = new Array();
	var datosEdit = new Array();
	var modalidadPago = new Array();
	var codBanco = new Array();
	var estadoDocumento = new Array();
	var tipoEgreso = new Array();
	var itemArray = 0;
	var itemBanco = 0;
	var itemDocumento = 0;
	var itemEgreso = 0;
	
	/*variables globales para manejo de registros en grilla y modificacion de los mismos*/
	var arrayCheck = new Array();
	var idSelectedItem;
	var idSif017_glob = 0;
	var rangoPrimero = 0;
	var rangoSegundo = 0;
	var rutEmpresa_Glob = 0;
	
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	/*Objeto solicitud para llenar el arreglo.*/
	function ObjSolicitud(id_sif017, rut_empresa, flagCheck){
		this.id_sif017 = id_sif017;
		this.rut_empresa = rut_empresa;
		this.flagCheck = flagCheck;
	}

	/*Funcion que sera llamada por el onChange del ckechbox*/
	function cambiaFlagCheck(f){

		for(i=0; i<arrayCheck.length; i++){
			arrayCheck[i].flagCheck = 0;
		}
		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif017 == f)
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
	
	/**Actualiza flagCheck, al presionar boton cancelar.*/
	function actualizarFlagcheck(f){
	
		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif017 == f)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					arrayCheck[i].flagCheck = 0;
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
	
	/*Declaracion de objeto tipo egreso*/
	function TipoEgresoVO(id_tipo_Egreso,desc_tipo_egreso)
	{
		this.id_tipo_Egreso = id_tipo_Egreso;
		this.desc_tipo_egreso = desc_tipo_egreso;
	}
	
	/*Funcion que carga el arreglo codigo de banco*/
	function cargarArregloCodigoBanco(){
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerDataCodigoBanco("CodigoBanco", function(data){
			var codigoBanco = null;
			codigoBanco = data;
			
			for(var i=0; i<codigoBanco.length; i++){
				codBanco[i] = new CodigoBancoVO(codigoBanco[i].cod_banco_normativa,codigoBanco[i].desc_cod_banco_normativa);
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
				modalidadPago[i] = new ModalidadDePagoVO(modoPago[i].id_modalidad_pago,modoPago[i].desc_modalidad_pago);
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
	
	/*Funcion que carga el arreglo tipo egreso*/
	function cargarArregloEgreso(){
		
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerDataTipoEgreso("TipoEgreso", function(data){
			var egreso = null;
			egreso = data;
			for(var i=0; i<egreso.length; i++){
				tipoEgreso[i] = new  TipoEgresoVO(egreso[i].id_tipo_Egreso,egreso[i].desc_tipo_egreso);
			 }
		});
		DWREngine.setAsync(true);
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
	
	/*Funcion que carga el combobox con el arreglo codigo de banco*/
	function obtenerComboCodigoBanco(){
		
		var cmb = document.getElementById("dbx_CodigoBanco");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<codBanco.length; j++){
			var item = null;
			item = codBanco[j];
			
			cod = item.cod_banco_normativa;
			txt = item.desc_cod_banco_normativa;
			
			cmb.options[j+1] = new Option(txt,cod);
		}
	}
	
	/*funcion que carga el combobox con el arreglo modalidad de pago*/
	function obtenerComboModalidadPago(){
		
		var cmb = document.getElementById("dbx_ModoPago");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<modalidadPago.length; j++){
			
			var item = null;
			item = modalidadPago[j];
			
			cod = item.id_modalidad_pago;
			txt = item.desc_modalidad_pago;
			
			cmb.options[j+1] = new Option(txt, cod);
		}
	}
	
	/*cargar dbx tipoEgreso*/
	function obtenerComboEgreso(){
		var cmb = document.getElementById("dbx_Egreso");
		cmb.options[0] = new Option("Seleccione", 0);
			for(var j=0; j<tipoEgreso.length; j++)
			{
				var item = null;
				item = tipoEgreso[j];
			
				cod = item.id_tipo_Egreso;
				txt = item.desc_tipo_egreso;

				cmb.options[j+1] = new Option(txt, cod);
			}
	}
	/*funcion que carga el valor predeterminado del combobox de modalidad de pago*/
	function selectedItemCombo(){
		
		document.ModifSif017Form.dbx_ModoPago.value = itemArray;
		document.ModifSif017Form.dbx_CodigoBanco.value = itemBanco;
		document.ModifSif017Form.dbx_Egreso.value = itemEgreso;
		document.ModifSif017Form.dbx_EstadoDocumento.value = itemDocumento;
	}
	
	/*funcion que cargar arreglos. Esta funcion es llamada al incio desde el onload.*/
	function cargarArregloParametricas(){
		cargarArregloModalidadPago();
		cargarArregloCodigoBanco();
		cargarArregloEstadoDocumento();
		cargarArregloEgreso();
	}	
	
	function asignaValor(a){

		document.ModifSif017Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ModifSif017Form.submit();
	}
	
	
	/*Funciones que construyen pantalla para editar campos del informe.*/
	/*Construccion pantalla estadistica*/
	function obtenerHeaderEstadistica(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center" style="color: blue"><b>EDICIÓN REPORTE COTIZACIÓN SIF017</b></p>' + 
						'<p align="left" style="color: blue"> 1.- Detalle de campos</p>'+
					'</td>'+
				'</tr>';
	}
	
	function obtenerHeaderCancelarEstadistico()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="updateCamposSif017();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivCamposModificables();"/>'+
					'</td>'+
				'</tr>';
	}
	
	var nombre_empresa = "";
	var numero_serie = "";
	var numero_documento = "";
	var monto_documento = "";
	var numero_cartola = "";
	var fecha_emision_documento = "";
	var fecha_rendicion = "";
	function obtenerFilaTablaEstadistica(datoEdit)
	{
		itemArray = datoEdit.modalidad_de_pago;
		itemBanco = datoEdit.codigo_banco;
		itemDocumento = datoEdit.estado_documento;
		itemEgreso = datoEdit.cod_tipo_egreso;
		nombre_empresa = datoEdit.nombre_empresa;
		numero_serie = datoEdit.numero_serie;
		numero_documento = datoEdit.numero_documento;
		monto_documento = datoEdit.monto_documento;
		numero_cartola = datoEdit.numero_cartola;
		fecha_emision_documento = datoEdit.fechaEmisionDocumentoDate;
		fecha_rendicion = datoEdit.fechaRendicionDate;
		var texto = "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Empresa" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutEmpresa' id='txt_rutEmpresa' value='" + datoEdit.rut_empresa + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvEmpresa' id='txt_dvEmpresa' value='" + datoEdit.dv_empresa + "' disabled='true' size='2'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Empresa" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreEmpresa' id='txt_nombreEmpresa' value='" + datoEdit.nombre_empresa + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Tipo Egreso" + "</td>"+
							"<td align='left'>"+
								"<select property='dbx_Egreso' id='dbx_Egreso' styleClass='dbx_codArchivo2' enabled='true'></select>"+
							"</td>"+																					
	   				 "</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Modalidad de Pago" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_ModoPago' id='dbx_ModoPago' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Número Serie" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroSerie' id='txt_numeroSerie' value='" + datoEdit.numero_serie + "' size='20' maxlength='20' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Número Documento" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroDocumento' id='txt_numeroDocumento' value='" + datoEdit.numero_documento + "' size='20' maxlength='20' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+	   				    				 
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Monto Documento" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_montoDocumento' id='txt_montoDocumento' value='" + datoEdit.monto_documento + "' size='20' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Código Banco" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_CodigoBanco' id='dbx_CodigoBanco' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Número Cartola" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroCartola' id='txt_numeroCartola' value='" + datoEdit.numero_cartola + "' size='20' maxlength='20' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Estado Documento" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_EstadoDocumento' id='dbx_EstadoDocumento' styleClass='dbx_codArchivo' enabled='true' width='30'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
	   				 	"<table width='99%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='2'>"+
							"<tr><td colspan='4' width='100%' align='center' style='color: blue'><p><b>(Para edición de fechas, utilizar calendario)</b></p></td></tr>"+
							"<tr>"+
								"<td class='texto' align='left' width='20%'>" + " " + "Fecha Emisión Documento." + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_fecEmisionDocumento' id='txt_fecEmisionDocumento' value='" + datoEdit.fechaEmisionDocumentoDate + "' size='10' disabled='true'/>"+
									"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_fecEmisionDocumento)'/>"+
								"</td>"+
								"<td class='texto' align='left' width='20%'>" + " " + "Fecha Rendición." + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_fechaRendicion' id='txt_fechaRendicion' value='" + datoEdit.fechaRendicionDate + "' size='10' disabled='true'/>"+
									"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_fechaRendicion)'/>"+
								"</td>"+																					
	   				 		"</tr>"+
	   				 	"</table>"+
			   		"</tr>";	

		return texto;
	}
	
	/**********************************************************************/
	/*Funcion que verifica que los campos modificables no vayan vacios.*/
	function verificaCamposModificables(){
		
		if(Trim(document.ModifSif017Form.txt_nombreEmpresa.value) == "" ){
			alert("Falta ingresar el campo Nombre Empresa.");
			document.ModifSif017Form.txt_nombreEmpresa.value = nombre_empresa;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.dbx_Egreso.value) == 0 ){
			alert("Falta ingresar el campo Tipo Egreso.");
			document.ModifSif017Form.dbx_Egreso.value = itemEgreso;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.dbx_ModoPago.value) == 0 ){
			alert("Falta ingresar el campo Modalidad de Pago.");
			document.ModifSif017Form.dbx_ModoPago.value = itemArray;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.txt_montoDocumento.value) == "" ){
			alert("Falta ingresar el campo Monto Documento.");
			document.ModifSif017Form.txt_montoDocumento.value = monto_documento;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.txt_numeroSerie.value) == "" ){
			alert("Falta ingresar el campo Número Serie.");
			document.ModifSif017Form.txt_numeroSerie.value = numero_serie;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.txt_numeroDocumento.value) == "" ){
			alert("Falta ingresar el campo Número Documento.");
			document.ModifSif017Form.txt_numeroDocumento.value = numero_documento;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.txt_fecEmisionDocumento.value) == "" ){
			alert("Falta ingresar el campo Fecha Emisión Documento.");
			document.ModifSif017Form.txt_fecEmisionDocumento.value = fecha_emision_documento;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.dbx_CodigoBanco.value) == 0 ){
			alert("Falta ingresar el campo Código Banco.");
			document.ModifSif017Form.dbx_CodigoBanco.value = itemBanco;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.txt_numeroCartola.value) == "" ){
			alert("Falta ingresar el campo Número Cartola.");
			document.ModifSif017Form.txt_numeroCartola.value = numero_cartola;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.dbx_EstadoDocumento.value) == 0 ){
			alert("Falta ingresar el campo Estado Documento.");
			document.ModifSif017Form.dbx_EstadoDocumento.value = itemDocumento;
			return false;
		}
		
		if(Trim(document.ModifSif017Form.txt_fechaRendicion.value) == "" ){
			alert("Falta ingresar el campo Fecha Rendición.");
			document.ModifSif017Form.txt_fechaRendicion.value = fecha_rendicion;
			return false;
		}
		
		return true;	
	}
		
	/**Funcion que hace el update a la tabla sif017*/
	function updateCamposSif017(){
		
		var rutEmpresa = document.ModifSif017Form.txt_rutEmpresa.value;
		var nombreEmpresa = document.ModifSif017Form.txt_nombreEmpresa.value;
		var codTipoEgreso = document.ModifSif017Form.dbx_Egreso.value;
		var modalidadPago = document.ModifSif017Form.dbx_ModoPago.value;
		var montoDocumento = document.ModifSif017Form.txt_montoDocumento.value;
		var numeroSerie = document.ModifSif017Form.txt_numeroSerie.value;
		var numeroDocumento = document.ModifSif017Form.txt_numeroDocumento.value;
		var fechaEmisionDocumento = document.ModifSif017Form.txt_fecEmisionDocumento.value;
		var codigoBanco = document.ModifSif017Form.dbx_CodigoBanco.value;
		var numeroCartola = document.ModifSif017Form.txt_numeroCartola.value;
		var estadoDocumento = document.ModifSif017Form.dbx_EstadoDocumento.value;
		var fechaRendicion = document.ModifSif017Form.txt_fechaRendicion.value;
		
		if(verificaCamposModificables()!= false){
			DWREngine.setAsync(false);
			EditarReporteCotizacionesDWR.updateSif017(rutEmpresa,idSif017_glob,nombreEmpresa,codTipoEgreso,modalidadPago,montoDocumento,numeroSerie,numeroDocumento,
													  fechaEmisionDocumento,codigoBanco,numeroCartola,estadoDocumento,fechaRendicion,function(data){
			
				var resp = null;
				resp = data.codRespuesta;
				
				if(resp == 0){
				
					alert("Los campos han sido modificados de manera exitosa.");
					if(idSelectedItem == 1){
						//consultarDatosUpdateados(rutEmpresa);
						//esconderDiv();
						esconderDivCamposModificables();
					}
					if(idSelectedItem == 2){
						//consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
						//esconderDiv();
						esconderDivCamposModificables();
					}
						
				}else{
					alert("Ha ocurrido un error y no se han efectuado los cambios.");
					esconderDiv();
				}
			});
			
			DWREngine.setAsync(false);
		}	
	}
	
	function esconderDivCamposModificables(){
		
		actualizarFlagcheck(idSif017_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		if(idSelectedItem == 1){
			consultarDatosUpdateados(rutEmpresa_Glob);
		}
		
		if(idSelectedItem == 2){
			consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
		}
	}
	
	/*Funcion que consulta los datos que fueron updateados a partir del filtro por empresa.*/
	function consultarDatosUpdateados(rut){
	
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteCotizacionesDWR.obtenerDatosModificadosPorRut(idSelectedItem,rut, function(data){
			
			datos = data.listSif017;
			
			document.getElementById("datosNomina").innerHTML = "";
			
			if(datos != null){
				
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif017, datos[p].rut_empresa, 0);
				}
				
				cargaDatosEnGrilla();
			}			
		});
		DWREngine.setAsync(true);
	}	

	/*FUNCION QUE ELIMINA UN REGISTRO INDIVIDUAL*/
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
						idSif017_glob = arrayCheck[i].id_sif017;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif017(idSif017_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizarDatosPorRut(rut);
							esconderDivCamposModificables();
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}				
	}
	
	/**Funcion que actualiza datos con el rut.*/
	function actualizarDatosPorRut(a){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		idSelectedItem = 1;
		
		EditarReporteCotizacionesDWR.obtenerDatosPorRut017(idSelectedItem, a, function(data){
					
			datos = data.listSif017;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif017, datos[p].rut_empresa, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);
	}

	/*IMPLEMENTACION DE BUSQUEDAS POR CORRELATIVO*/
	/*Funcion que implementa la busqueda por correlativo.*/
	function busquedaPorCorrelativo(){
	
		var primerRango = document.ModifSif017Form.txt_primerRango.value;
		var segundoRango = document.ModifSif017Form.txt_segundoRango.value;
		
		if(primerRango.length == 0 || segundoRango.length == 0){
			alert("Debe ingresar un rango válido para realizar la búsqueda.");
			return false;
		}
		
		if(parseInt(primerRango,10) > parseInt(segundoRango,10)){
			
			document.ModifSif017Form.txt_primerRango.value = "";
			document.ModifSif017Form.txt_segundoRango.value = "";
			alert("El valor del primer campo no debe ser mayor al segundo campo.");
			return false;
		}
		
		if(primerRango.length != 0 && segundoRango.length == 0){
			alert("Falta ingresar el segundo campo para realizar la busqueda.");
			return false;
		}
		
		if(primerRango.length == 0 && segundoRango.length != 0){
			alert("Falta ingresar el primer campo para realizar la busqueda.");
			return false;
		}
		
		if((parseInt(segundoRango,10) - parseInt(primerRango,10)) > 100){
		
			document.ModifSif017Form.txt_primerRango.value = "";
			document.ModifSif017Form.txt_segundoRango.value = "";
			alert("El rango ingresado no debe exceder las 100 unidades.");
			return false;
		}
		
		idSelectedItem = 2;
		rangoPrimero = primerRango;
		rangoSegundo = segundoRango;
		arrayCheck = new Array();
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.busquedaPorRangoSif017(idSelectedItem, primerRango, segundoRango, function(data){
			
			datos = data.listSif017;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif017, datos[p].rut_empresa, 0);
				}			

				cargaDatosEnGrilla();

				obtenerDataEstaticaPorID(rangoPrimero,rangoSegundo);
				cargarCamposEstaticos();
				limpiarDatosBusqueda();
			}	
		});
		DWREngine.setAsync(true);
	}
	
	function consultarDatosUpdateadosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteCotizacionesDWR.obtenerDatosModificadosPorRango(idSelectedItem, a, b, function(data){
			
			datos = data.listSif017;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif017, datos[p].rut_empresa, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);			
	}

	/*funcion que obtiene data estatica por ID*/
	function obtenerDataEstaticaPorID(a,b){
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.dataEstaticaPorIdSif017(a,b, function(data){
			
			var dat = new Array();
			
			dat = data.listSif017;
			
			if(dat != null){
				for(var i=0; i<dat.length; i++)
				{
					document.ModifSif017Form.txt_fechaProceso.value = dat[i].fecha_proceso;
					document.ModifSif017Form.txt_codigoEntidad.value = dat[i].codigo_entidad;
					document.ModifSif017Form.txt_codigoArchivo.value = dat[i].codigo_archivo;
				}
			}else{
				document.ModifSif017Form.txt_fechaProceso.value = "";
				document.ModifSif017Form.txt_codigoEntidad.value = "";
				document.ModifSif017Form.txt_codigoArchivo.value = "";
			}
		});
		DWREngine.setAsync(true);
	}
		
	/*Funcion que discrimina por cual busqueda eliminar*/
	/*function discriminaEliminar(){
		
		if(idSelectedItem == 1){
			eliminarDatoIndividual();
		}
		
		if(idSelectedItem == 2){
			eliminaDatoIndividualCorrelativo();
		}
	}*/
	
	/*FUNCION QUE DISCRIMINA POR BUSQUEDA.*/
	/*Funcion que selecciona por cual de los dos campos (rut o correlativo) va a efectuar la busqueda.*/
	function seleccionaBusqueda(){
		
		var r_ut = document.ModifSif017Form.txt_rut.value;
		var dv_verif = document.ModifSif017Form.txt_digitoVerificador.value;
		var primer_Rango = document.ModifSif017Form.txt_primerRango.value;
		var segundo_Rango = document.ModifSif017Form.txt_segundoRango.value;
		idSelectedItem = document.ModifSif017Form.dbx_filtroBusqueda.value;
		
		if(r_ut.length != 0 && dv_verif.length != 0 && primer_Rango.length == 0 && segundo_Rango.length == 0){
			
			//ejecutar busqueda por rut.
			consultarDatosPorRut();
		}
		
		if(r_ut.length == 0 && dv_verif.length == 0 && primer_Rango.length != 0 && segundo_Rango.length != 0 ){
			
			//ejecutar busqueda por correlativo.
			busquedaPorCorrelativo();
			
			//respaldo de onload cargarArregloParametricas();
		}	
	}
	/**********************************************************************/
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
		obtenerComboModalidadPago();
		obtenerComboCodigoBanco();
		obtenerComboEgreso();
		obtenerComboEstadoDocumento();
		selectedItemCombo();
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	

	function discriminaCamposEditar(){
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
						idSif017_glob = arrayCheck[i].id_sif017;
					}
					
					if(idSelectedItem == 2){

						idSif017_glob = arrayCheck[i].id_sif017;
						rutSearch = "";
					}
		
					//EditarReporteCotizacionesDWR.obtenerDatosSif017ParaEditar(rutSearch,idSif017_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif017;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif017Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif017Form.idSif017_glob.value = idSif017_glob;
					document.ModifSif017Form.rutSearch.value = rutSearch;
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
						idSif017_glob = arrayCheck[i].id_sif017;
					}
					
					if(idSelectedItem == 2){

						idSif017_glob = arrayCheck[i].id_sif017;
						rutSearch = "";
					}
		
					//EditarReporteCotizacionesDWR.obtenerDatosSif017ParaEditar(rutSearch,idSif017_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif017;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif017Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif017Form.idSif017_glob.value = idSif017_glob;
					document.ModifSif017Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}			
	}
	
	function llamarPantalla()
	{
	
		var rutSrch = document.ModifSif017Form.rutEmpresa.value;
		obtenerCamposAEditar(rutSrch);
		//obtenerId(rutSrch);
	}
	/*Fin funciones que construyen modificacion del reporte*/
	
	
	/*Funciones que construyen la grilla.*/
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Egreso</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modalidad de Pago</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Cartola</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Rendición</td>'+
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Egreso</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modalidad de Pago</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Cartola</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Rendición</td>'+				
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
			if(dato.id_sif017 == arrayCheck[i].id_sif017)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}
			
		var texto =  " <tr> "+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif017 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='discriminaCamposEditar();'></td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/papelera_de_reciclaje.GIF' width='16' height='16' onClick='discriminaEliminar();'></td>"+
							"<td class='texto' align='center'>"+ dato.id_sif017 +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_empresa +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.cod_tipo_egreso +"</td>"+
							"<td class='texto' align='center'>"+ dato.modalidad_de_pago +"</td>"+
							"<td class='texto' align='center'>"+ dato.montoDocumentoEnMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.numero_serie +"</td>"+
							"<td class='texto' align='center'>"+ dato.numero_documento +"</td>"+
							"<td class='texto' align='center'>"+ dato.fech_emision_doc +"</td>"+
							"<td class='texto' align='center'>"+ dato.codigo_banco +"</td>"+
							"<td class='texto' align='center'>"+ dato.numero_cartola +"</td>"+
							"<td class='texto' align='center'>"+ dato.estado_documento +"</td>"+
							"<td class='texto' align='center'>"+ dato.fecha_rendicion +"</td>"+
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
		
		document.ModifSif017Form.txt_rut.value = "";
		document.ModifSif017Form.txt_digitoVerificador.value = "";
		document.ModifSif017Form.txt_primerRango.value = "";
		document.ModifSif017Form.txt_segundoRango.value = "";
		document.ModifSif017Form.dbx_filtroBusqueda.value = 0;
		validaFiltroDeBusqueda();
	}
	
	/*Funcion DWR que carga el arreglo de datos de acuerdo a la consulta.*/
	function consultarDatosPorRut(){
	
		var rut = document.ModifSif017Form.txt_rut.value;
		var digVerificador = document.ModifSif017Form.txt_digitoVerificador.value;
		idSelectedItem = 1;
		rutEmpresa_Glob = rut;
		arrayCheck = new Array();
		

		DWREngine.setAsync(false);
		
		if(rut.length != 0 && digVerificador.length != 0)
		{
			if(ValidadorRUT(rut,digVerificador)== false)	
			{
				document.ModifSif017Form.txt_digitoVerificador.value = "";
			
			}else{	
				
				EditarReporteCotizacionesDWR.obtenerDatosSif017PorRut(rut, function(data){
					
					datos = data.listSif017;
					
					//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
					for(p=0; p<datos.length; p++){
						arrayCheck[p] = new ObjSolicitud(datos[p].id_sif017, datos[p].rut_empresa, 0);
					}
					
					document.getElementById("datosNomina").innerHTML = "";
					
					if(datos != null){
						
						cargaDatosEnGrilla();
						cargarCamposEstaticos();

						cargarCamposEstaticosPorRutSif017(rut);
						limpiarDatosBusqueda();
					}			
				});
			}
		}else{
			alert("Debe ingresar un rut válido para realizar una búsqueda.");
		}
			
		DWREngine.setAsync(true);	
	}

	/*Funcion que carga datos estaticos*/
	function cargarCamposEstaticos(){
		
		DWREngine.setAsync(false);
		
		var tipoArchivo = document.ModifSif017Form.idTipoArchivo.value;
		//var fechaActual = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoArchivo = parseInt(fechaActual.substring(3,5),10)-1;
		var periodoArchivo = document.ModifSif017Form.numeroMes.value;
		
		if(periodoArchivo == 0){
			periodoArchivo = 12;
		}	

		EditarReporteCotizacionesDWR.obtenerDataEstatica(tipoArchivo, periodoArchivo, function(data){
	
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.ModifSif017Form.txt_tipoArchivo.value = data.tipoArchivoGlosa;
				document.ModifSif017Form.txt_periodoArchivo.value = data.periodoArchivoGlosa;
				
			}
		});
		DWREngine.setAsync(true);
	}
	
	/**Funcion que carga los datos estaticos filtrados por rut.*/
	function cargarCamposEstaticosPorRutSif017(rut){
		

		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerEstaticosPorRutSif017(rut, function(data){
			var resp = null;
			resp = data.listSif017;
			
			if(resp != null){
				
				for(var i=0; i<resp.length; i++){
					document.ModifSif017Form.txt_fechaProceso.value = resp[i].fecha_proceso;
					document.ModifSif017Form.txt_codigoEntidad.value = resp[i].codigo_entidad;
					document.ModifSif017Form.txt_codigoArchivo.value = resp[i].codigo_archivo;
				}
			}else{
			
				document.ModifSif017Form.txt_fechaProceso.value = "";
				document.ModifSif017Form.tct_codigoEntidad.value = "";
				document.ModifSif017Form.txt_codigoArchivo.value = "";
			}	
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que completa el digito verificador*/
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.ModifSif017Form.txt_rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.ModifSif017Form.txt_digitoVerificador.value = digitoVerificador;
	}

	/*Funcion que valida que se va a ejecutar en la busqueda, dada la eleccion del combo box*/
	function validaFiltroDeBusqueda(){
		
		var selectedItem = document.ModifSif017Form.dbx_filtroBusqueda.value;
		var filtroRut = document.getElementById("txt_rut");
		var filtroDV = document.getElementById("txt_digitoVerificador");
		var filtroCorrelativoUno = document.getElementById("txt_primerRango");
		var filtroCorrelativoDos = document.getElementById("txt_segundoRango");
		var buttonSearch = document.getElementById("btn_Buscar");
		
		if(selectedItem == 0){
				document.ModifSif017Form.txt_rut.value = "";
				document.ModifSif017Form.txt_digitoVerificador.value = "";
				document.ModifSif017Form.txt_primerRango.value = "";
				document.ModifSif017Form.txt_segundoRango.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = true;
		}
		
		if(selectedItem == 1){
				document.ModifSif017Form.txt_primerRango.value = "";
				document.ModifSif017Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;			
		}
		
		if(selectedItem == 2){
				document.ModifSif017Form.txt_rut.value = "";
				document.ModifSif017Form.txt_digitoVerificador.value = "";
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
		//actualizarFlagcheck(id_sif017);
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
		
		var rango_uno = Trim(document.ModifSif017Form.txt_rangoUno.value);
		var rango_dos = Trim(document.ModifSif017Form.txt_rangoDos.value);
		
		
		if(rango_uno == ""){
			alert("No ha ingresado el correlativo para eliminar el registro.");
			return false;
		}
		
		if(rango_uno != "" && rango_dos == ""){
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif017. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif017SinRango(rango_uno);
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
			
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif017. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif017(rango_uno,rango_dos);
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
	
	function eliminarRegistroSif017SinRango(a){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 17;
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
	
	function eliminarRegistroSif017(a,b){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 17;
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
		actualizarFlagcheck(idSif017_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}	
	/*Fin mini pantalla para eliminar registros*/
	
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
						idSif017_glob = arrayCheck[i].id_sif017;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif017(idSif017_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizarDatosPorRut(rut);
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
						idSif017_glob = arrayCheck[i].id_sif017;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif017(idSif017_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizarDatosPorRut(rut);
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
						idSif017_glob = arrayCheck[i].id_sif017;
						//rut = arrayCheck[i].rut_afiliado;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif017(idSif017_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizaDatosCorrelativo(rangoPrimero,rangoSegundo);
							//esconderDiv();
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
						idSif017_glob = arrayCheck[i].id_sif017;
						//rut = arrayCheck[i].rut_afiliado;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif017(idSif017_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizaDatosCorrelativo(rangoPrimero,rangoSegundo);
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
		
		EditarReporteCotizacionesDWR.actualizarPorCorrelativo(a, b, function(data){
					
			datos = data.listSif017;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif017, datos[p].rut_empresa, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);		
	}
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 17;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				if(data.periodoProceso == null){
					document.ModifSif017Form.txt_MesProceso.value = "";
				}else{
				document.ModifSif017Form.txt_MesProceso.value = data.periodoProceso;
				}
				document.ModifSif017Form.numeroMes.value = data.mesConsultado;
			}else{
				document.ModifSif017Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que llama a pantalla para agregar registro, en paralelo con la pantalla de modificacion para no incidir en la consulta en la grilla.*/
	function activarAgregarNuevoRegistro(){
		var mesInformado = document.ModifSif017Form.txt_MesProceso.value;
		if(mesInformado == null || mesInformado == ""){
			alert('Debe procesar el archivo 12 antes de agregar un registro');
		}else{
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1150, height=700";
		window.open("./pages/AgregarSif017.jsp","",opciones);
		}
	}
							
</script>
</head>
<body onload="cabeceraGrilla();cargarArregloParametricas();cargarMesInformado();">
<html:form action="/modificaSif017.do">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idTipoArchivo" value="5">
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
  <input type="hidden" name="idSif017_glob">
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
					<td><strong><p1>MANTENEDOR REPORTE DIVISIÓN RECUPERACIÓN COTIZACIONES SIF017</p1></strong></td>
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
			2.- Rendición egresos por saldo a favor empleador (SAFEM)
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

