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
	var modalidadPago = new Array();
	var codBanco = new Array();
	var itemArray = 0;
	var itemBanco = 0;

	/*variables globales para manejo de registros en grilla y modificacion de los mismos*/
	var arrayCheck = new Array();
	var idSelectedItem;
	var idSif018_glob = 0;
	var rangoPrimero = 0;
	var rangoSegundo = 0;
	var rutSearchUnico = 0;
	var rutEmpresa_Glob = 0;
	
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	/*Objeto solicitud para llenar el arreglo.*/
	function ObjSolicitud(id_sif018, rut_empleador, flagCheck){
		this.id_sif018 = id_sif018;
		this.rut_empleador = rut_empleador;
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
			if(arrayCheck[i].id_sif018 == f)
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
			if(arrayCheck[i].id_sif018 == f)
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
			
	function asignaValor(a){

		document.ModifSif018Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ModifSif018Form.submit();
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
	
	/*funcion que carga el valor predeterminado del combobox de modalidad de pago*/
	function selectedItemCombo(){
		
		document.ModifSif018Form.dbx_ModoPago.value = itemArray;
		document.ModifSif018Form.dbx_CodigoBanco.value = itemBanco;
	}
	
	/*funcion que llama a la funcion cargar arreglo modalidad de pago para cargar dicho arreglo.
	Esta funcion es llamada al incio desde el onload.*/
	function cargarArregloParametricas(){
		cargarArregloModalidadPago();
		cargarArregloCodigoBanco();
	}
	
	/*Funcion que limpia los datos de busqueda*/
	function limpiarDatosBusqueda(){
		
		document.ModifSif018Form.txt_rut.value = "";
		document.ModifSif018Form.txt_digitoVerificador.value = "";
		document.ModifSif018Form.txt_primerRango.value = "";
		document.ModifSif018Form.txt_segundoRango.value = "";
		document.ModifSif018Form.dbx_filtroBusqueda.value = 0;
		validaFiltroDeBusqueda();
	}
	
	/*Funcion DWR que carga el arreglo de datos de acuerdo a la consulta.*/
	function consultarDatosPorRut(){
	
		var rut = document.ModifSif018Form.txt_rut.value;
		rutSearchUnico = rut;
		var digVerificador = document.ModifSif018Form.txt_digitoVerificador.value;
		arrayCheck = new Array();
		idSelectedItem = 1;
		DWREngine.setAsync(false);
		
		if(rut.length != 0 && digVerificador.length != 0)
		{
			if(ValidadorRUT(rut,digVerificador)== false)	
			{
				document.ModifSif018Form.txt_digitoVerificador.value = "";
			
			}else{	
				
				EditarReporteCotizacionesDWR.obtenerDatosPorRut(rut, function(data){
					
					datos = data.listSif018;
					
					for(p=0; p<datos.length; p++){
						arrayCheck[p] = new ObjSolicitud(datos[p].id_sif018, datos[p].rut_empleador, 0);
					}
					document.getElementById("datosNomina").innerHTML = "";
					
					if(datos != null){

						cargaDatosEnGrilla();
						cargarCamposEstaticos();
						cargarCamposEstaticosPorRutSif018(rut);
						limpiarDatosBusqueda();
					}			
				});
			}
		}else{
			alert("Debe ingresar un rut válido para realizar una búsqueda.");
		}
			
		DWREngine.setAsync(true);	
	}

	/**Funcion que carga los datos estaticos filtrados por rut.*/
	function cargarCamposEstaticosPorRutSif018(rut){
		

		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerEstaticosPorRutSif018(rut, function(data){
			var resp = null;
			resp = data.listSif018;
			
			if(resp != null){
				
				for(var i=0; i<resp.length; i++)
				{
					document.ModifSif018Form.txt_fechaProceso.value = resp[i].fecha_proceso;
					document.ModifSif018Form.txt_codigoEntidad.value = resp[i].codigo_entidad;
					document.ModifSif018Form.txt_codigoArchivo.value = resp[i].codigo_archivo;	
				}
			}else{
				document.ModifSif018Form.txt_fechaProceso.value = "";
				document.ModifSif018Form.txt_codigoEntidad.value = "";
				document.ModifSif018Form.txt_codigoArchivo.value = "";
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Conjunto de funciones que realizan la tarea de emular a la grilla.*/
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modalidad de pago</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Cobro</td>'+
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modalidad de pago</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Serie</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Número Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Emisión Documento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Banco</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Cobro</td>'+
				'</tr>';
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
			if(dato.id_sif018 == arrayCheck[i].id_sif018)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}
		var texto =  " <tr> "+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif018 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='seleccionarFuncionalidadEditar();'></td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/papelera_de_reciclaje.GIF' width='16' height='16' onClick='discriminaEliminar();'></td>"+
							"<td class='texto' align='center'>"+ dato.id_sif018 +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_empleador +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_empleador +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_empleador +"</td>"+
							"<td class='texto' align='center'>"+ dato.mod_pago +"</td>"+
							"<td class='texto' align='center'>"+ dato.montoDocumentoMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.numero_serie +"</td>"+
							"<td class='texto' align='center'>"+ dato.numero_documento +"</td>"+
							"<td class='texto' align='center'>"+ dato.fecha_emision_documento +"</td>"+
							"<td class='texto' align='center'>"+ dato.codigo_banco +"</td>"+
							"<td class='texto' align='center'>"+ dato.fecha_cobro +"</td>"+
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
				asignaValorCampoSif018(datos[i]);
				llenarCamposEstaticos(datos[i]);
			}
		}
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderGrilla() + contenidoTabla + "</table>";
		
		generarPaginacion();

	}
	
	/*Fin funciones que construyen la grilla*/

	function llamarPantalla()
	{
	
		var rutSrch = document.ModifSif018Form.rutEmpresa.value;
		obtenerCamposAEditar(rutSrch);
		obtenerId(rutSrch);
	}
	
	/*Funciones que construyen pantalla para editar campos del informe.*/
/*	function obtenerCamposAEditar(rutSearch){
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.obtenerDatosPorRut(rutSearch, function(data){
			
			datosEdit = data.listSif018;
			
			//document.getElementById("datosNomina").innerHTML = "";
			
			if(datosEdit != null){
				
				cargaDatosEstadisticos();
			}			
		});
		DWREngine.setAsync(true);
	}
*/
	function seleccionarFuncionalidadEditar(){
		
		datosEdit = new Array();
		if(datos.length > 1){
			obtenerCamposAEditar();
		}else{
			obtenerCamposAEditarUnicos();
		}		
	}
	
	function obtenerCamposAEditarUnicos(){
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

						rutSearch = rutSearchUnico
						idSif018_glob = arrayCheck[i].id_sif018;
					}
					
					if(idSelectedItem == 2){

						idSif018_glob = arrayCheck[i].id_sif018;
						rutSearch = "";
					}
					

					//EditarReporteCotizacionesDWR.obtenerDatosSif018ParaEditar(rutSearch,idSif018_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif018;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif018Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif018Form.idSif018_glob.value = idSif018_glob;
					document.ModifSif018Form.rutSearch.value = rutSearch;
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

						rutSearch = arrayCheck[i].rut_empleador;
						idSif018_glob = arrayCheck[i].id_sif018;
					}
					
					if(idSelectedItem == 2){

						idSif018_glob = arrayCheck[i].id_sif018;
						rutSearch = "";
					}
					

					//EditarReporteCotizacionesDWR.obtenerDatosSif018ParaEditar(rutSearch,idSif018_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif018;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif018Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif018Form.idSif018_glob.value = idSif018_glob;
					document.ModifSif018Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
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
		
		var rango_uno = Trim(document.ModifSif018Form.txt_rangoUno.value);
		var rango_dos = Trim(document.ModifSif018Form.txt_rangoDos.value);
		
		
		if(rango_uno == ""){
			alert("No ha ingresado el correlativo para eliminar el registro.");
			return false;
		}
		
		if(rango_uno != "" && rango_dos == ""){
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif018. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif018SinRango(rango_uno);
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
			
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif018. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif018(rango_uno,rango_dos);
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
	
	function eliminarRegistroSif018SinRango(a){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 18;
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
	
	function eliminarRegistroSif018(a,b){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 18;
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
	/*Fin mini pantalla para eliminar registros*/
	
	/*Construccion pantalla estadistica*/
	function obtenerHeaderEstadistica()
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
		actualizarFlagcheck(idSif018_glob);
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
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="updateCamposSif018();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivCamposModificables();"/>'+
					'</td>'+
				'</tr>';
	}
	
	var nombre_empresa = "";
	var monto_documento = "";
	var numero_serie = "";
	var numero_documento = "";
	var fecha_emision = "";
	var fecha_rendicion = "";

	function obtenerFilaTablaEstadistica(datoEdit)
	{
		itemArray = datoEdit.mod_pago;
		itemBanco = datoEdit.codigo_banco;
		nombre_empresa = datoEdit.nombre_empleador;
		monto_documento = datoEdit.monto_documento;
		numero_serie = datoEdit.numero_serie;
		numero_documento = datoEdit.numero_documento;
		fecha_emision = datoEdit.fechaEmisionDocumentoDate;
		fecha_rendicion = datoEdit.fechaRendicionDate;
		
		var texto = "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Empresa" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutEmpresa' id='txt_rutEmpresa' value='" + datoEdit.rut_empleador + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvEmpresa' id='txt_dvEmpresa' value='" + datoEdit.dv_empleador + "' disabled='true' size='2'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Empresa" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreEmpresa' id='txt_nombreEmpresa' value='" + datoEdit.nombre_empleador + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Modalidad de Pago" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_ModoPago' id='dbx_ModoPago' styleClass='dbx_codArchivo' enabled='true'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Monto Documento" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_montoDocumento' id='txt_montoDocumento' value='" + datoEdit.monto_documento + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Número Serie" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroSerie' id='txt_numeroSerie' value='" + datoEdit.numero_serie + "' size='15' maxlength='20' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Número Documento" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroDocumento' id='txt_numeroDocumento' value='" + datoEdit.numero_documento + "' size='15' maxlength='20' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Código Banco" + "</td>"+
							"<td class='texto' align='left'>"+
								"<select property='dbx_CodigoBanco' id='dbx_CodigoBanco' styleClass='dbx_codArchivo' enabled='true'></select>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
	   				 	"<table width='99%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='2'>"+
							"<tr><td colspan='4' width='100%' align='center' style='color: blue'><p><b>(Para edición de fechas, utilizar calendario)</b></p></td></tr>"+
							"<tr>"+
								"<td class='texto' align='left' width='20%'>" + " " + "Fec. Emisión Doc." + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_fecEmisionDocumento' id='txt_fecEmisionDocumento' value='" + datoEdit.fechaEmisionDocumentoDate + "' size='10' disabled='true'/>"+
									"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_fecEmisionDocumento)'/>"+
								"</td>"+
								"<td class='texto' align='left' width='20%'>" + " " + "Fecha Cobro" + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_fechaCobro' id='txt_fechaCobro' value='" + datoEdit.fechaRendicionDate + "' size='10'/>"+
									"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_fecEmisionDocumento)'/>"+
								"</td>"+																					
	   				 		"</tr>"+
	   				 	"</table>"+
			   		"</tr>";
		return texto;
	}
	
	/*Funcion que llena los campos estaticos*/
	function llenarCamposEstaticos(dato){
		
		document.ModifSif018Form.txt_fechaProceso.value = dato.fecha_proceso;
		document.ModifSif018Form.txt_codigoEntidad.value = dato.codigo_entidad;
		document.ModifSif018Form.txt_codigoArchivo.value = dato.codigo_archivo;
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
		obtenerComboModalidadPago();
		obtenerComboCodigoBanco();
		selectedItemCombo();
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	
	/*Fin pantalla de cambio.*/
	

	/**Funcion que verifica que los campos modificables no vayan vacios.*/
	function verificaCamposModificables(){

		if(Trim(document.ModifSif018Form.txt_nombreEmpresa.value) == ""){
			alert("Falta Ingresar el campo Nombre Empresa.");
			document.ModifSif018Form.txt_nombreEmpresa.value = nombre_empresa;
			return false;
		}
		
		if(Trim(document.ModifSif018Form.dbx_ModoPago.value) == 0){
			alert("Falta Ingresar el campo Modalidad de Pago.");
			document.ModifSif018Form.dbx_ModoPago.value = itemArray;
			return false;
		}
		
		if(Trim(document.ModifSif018Form.txt_montoDocumento.value) == ""){
			alert("Falta Ingresar el campo Monto Documento.");
			document.ModifSif018Form.txt_montoDocumento.value = monto_documento;
			return false;
		}
		
		if(Trim(document.ModifSif018Form.txt_numeroSerie.value) == ""){
			alert("Falta Ingresar el campo Número Serie.");
			document.ModifSif018Form.txt_numeroSerie.value = numero_serie;
			return false;
		}
		
		if(Trim(document.ModifSif018Form.txt_numeroDocumento.value) == ""){
			alert("Falta Ingresar el campo Número Documento.");
			document.ModifSif018Form.txt_numeroDocumento.value = numero_documento;
			return false;
		}
		
		if(Trim(document.ModifSif018Form.txt_fecEmisionDocumento.value) == ""){
			alert("Falta Ingresar el campo Fecha Emisión Documento.");
			document.ModifSif018Form.txt_fecEmisionDocumento.value = fecha_emision;
			return false;
		}
		
		if(Trim(document.ModifSif018Form.dbx_CodigoBanco.value) == 0){
			alert("Falta Ingresar el campo Código Banco.");
			document.ModifSif018Form.dbx_CodigoBanco.value = itemBanco;
			return false;
		}
		
		if(Trim(document.ModifSif018Form.txt_fechaCobro.value) == ""){
			alert("Falta Ingresar el campo Fecha Cobro.");
			document.ModifSif018Form.txt_fechaCobro.value = fecha_rendicion;
			return false;
		}
		
		return true;
			
	}
	
	/*Funcion que hace el update de los campos.*/
	function updateCamposSif018(){

		//var id_sif018 = document.ModifSif018Form.idSif018.value;
		var rut_Empresa = document.ModifSif018Form.txt_rutEmpresa.value;
		var nombre_Empresa = document.ModifSif018Form.txt_nombreEmpresa.value;
		var modalidad_Pago = document.ModifSif018Form.dbx_ModoPago.value;
		var monto_Documento = document.ModifSif018Form.txt_montoDocumento.value;
		var numero_Serie = document.ModifSif018Form.txt_numeroSerie.value;
		var numero_Documento = document.ModifSif018Form.txt_numeroDocumento.value;
		var fecha_EmisionDocumento = document.ModifSif018Form.txt_fecEmisionDocumento.value;
		var codigo_Banco = document.ModifSif018Form.dbx_CodigoBanco.value;
		var fecha_Cobro = document.ModifSif018Form.txt_fechaCobro.value;
		
		if(verificaCamposModificables() != false){
			
			DWREngine.setAsync(false);
			EditarReporteCotizacionesDWR.updateSif018(idSif018_glob, rut_Empresa, nombre_Empresa, modalidad_Pago, monto_Documento, numero_Serie, numero_Documento, fecha_EmisionDocumento, codigo_Banco, fecha_Cobro, function(data){
			
				var resp = null;
				resp = data.codResultado;
				
				if(resp == 0){
					alert("Los cambios se han efectuado exitosamente.");
					if(idSelectedItem == 1){
						/*consultarDatosUpdateados(rut_Empresa);
						esconderDiv();*/
						esconderDivCamposModificables();
					}
					if(idSelectedItem == 2){
						/*consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
						esconderDiv();*/
						esconderDivCamposModificables();
					}	
				}
				else{
					alert("Ha ocurrido un error y no se han efectuado los cambios.");
					esconderDivCamposModificables();
				}
			});
			
			DWREngine.setAsync(true);
		 }	
		
	}
	
	function esconderDivCamposModificables(){
	//frm
		actualizarFlagcheck(idSif018_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		if(idSelectedItem == 1){
			consultarDatosUpdateados(rutSearchUnico);
		}
		
		if(idSelectedItem == 2){
			consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
		}
	}
	
	function consultarDatosUpdateados(rut){
	
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.obtenerDatosPorRut(rut, function(data){
			
			datos = data.listSif018;
			
			document.getElementById("datosNomina").innerHTML = "";
			
			if(datos != null){
				
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif018, datos[p].rut_empleador, 0);
				}
				
				cargaDatosEnGrilla();
			}			
		});
		DWREngine.setAsync(true);
	}
	
	/**funcion que hace una consulta para actualizar la grilla con los datos que fueron updateados, dada una busqueda por rango de correlativo.*/
	function consultarDatosUpdateadosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteCotizacionesDWR.obtenerDatosModificadosPorRango018(idSelectedItem, a, b, function(data){
			
			datos = data.listSif018;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif018, datos[p].rut_empleador, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);			
	}
		
	/*Funcion asigna valor al campo del formulario*/
	function asignaValorCampoSif018(dato)
	{		
		document.ModifSif018Form.rutEmpresa.value = dato.rut_empleador;
		document.ModifSif018Form.nombreEmpresa.value = dato.nombre_empleador;
		document.ModifSif018Form.modalidadPago.value = dato.mod_pago;
		document.ModifSif018Form.montoDocumento.value = dato.monto_documento;
		document.ModifSif018Form.numeroSerie.value = dato.numero_serie;
		document.ModifSif018Form.numeroDocumento.value = dato.numero_documento;
		document.ModifSif018Form.fechaEmisionDocumento.value = dato.fecha_emision_documento;
		document.ModifSif018Form.codigoBanco.value = dato.codigo_banco;
		document.ModifSif018Form.fechaCobro.value = dato.fecha_cobro;
	}
	
	/*Funcion que carga datos estaticos*/
	function cargarCamposEstaticos(){
		
		DWREngine.setAsync(false);
		
		var tipoArchivo = document.ModifSif018Form.idTipoArchivo.value;
		//var fechaActual = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoArchivo = parseInt(fechaActual.substring(3,5),10)-1;
		var periodoArchivo = document.ModifSif018Form.numeroMes.value;

		EditarReporteCotizacionesDWR.obtenerDataEstatica(tipoArchivo, periodoArchivo, function(data){
	
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.ModifSif018Form.txt_tipoArchivo.value = data.tipoArchivoGlosa;
				document.ModifSif018Form.txt_periodoArchivo.value = data.periodoArchivoGlosa;
				
			}
		});
		DWREngine.setAsync(true);
	}
	
	/*Funcion que valida que se va a ejecutar en la busqueda, dada la eleccion del combo box*/
	function validaFiltroDeBusqueda(){
		
		var selectedItem = document.ModifSif018Form.dbx_filtroBusqueda.value;
		var filtroRut = document.getElementById("txt_rut");
		var filtroDV = document.getElementById("txt_digitoVerificador");
		var filtroCorrelativoUno = document.getElementById("txt_primerRango");
		var filtroCorrelativoDos = document.getElementById("txt_segundoRango");
		var buttonSearch = document.getElementById("btn_Buscar");
		
		if(selectedItem == 0){
				document.ModifSif018Form.txt_rut.value = "";
				document.ModifSif018Form.txt_digitoVerificador.value = "";
				document.ModifSif018Form.txt_primerRango.value = "";
				document.ModifSif018Form.txt_segundoRango.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = true;
		}
		
		if(selectedItem == 1){
				document.ModifSif018Form.txt_primerRango.value = "";
				document.ModifSif018Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;			
		}
		
		if(selectedItem == 2){
				document.ModifSif018Form.txt_rut.value = "";
				document.ModifSif018Form.txt_digitoVerificador.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = false;
				filtroCorrelativoDos.disabled = false;
				buttonSearch.disabled = false;			
		}
	}
	
	/*Funcion que completa el digito verificador*/
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.ModifSif018Form.txt_rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.ModifSif018Form.txt_digitoVerificador.value = digitoVerificador;
	}
	
	/*Funcion que obtiene el idsif018 a partir del rut.*/
	function obtenerId(rut_busqueda){
		
		var id_sif018;
		//var rut_busqueda = document.ModifSif018Form.txt_rut.value;
		

		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.obtenerIdSif018(rut_busqueda, function(data){
		
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.ModifSif018Form.idSif018.value = data.idsif018;

			}else{
				alert("Hubo un problema al obtener id para búsqueda.");
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que realiza la busqueda por correlativo.*/
	function busquedaPorCorrelativo(){
		
		var primerRango = document.ModifSif018Form.txt_primerRango.value;
		var segundoRango = document.ModifSif018Form.txt_segundoRango.value;
		
		if(primerRango == "" || segundoRango == ""){
			alert("Debe ingresar un rango válido para realizar la búsqueda.");
			return false;
		}
		
		if(parseInt(primerRango,10) > parseInt(segundoRango,10)){
			
			document.ModifSif018Form.txt_primerRango.value = "";
			document.ModifSif018Form.txt_segundoRango.value = "";
			alert("El valor del primer campo no debe ser mayor al segundo campo.");
			return false;
		}
		
		if((parseInt(segundoRango,10) - parseInt(primerRango,10)) > 100){
			
			document.ModifSif018Form.txt_primerRango.value = "";
			document.ModifSif018Form.txt_segundoRango.value = "";
			alert("El rango ingresado no debe exceder las 100 unidades.");
			return false;
		}
				
		idSelectedItem = 2;
		rangoPrimero = primerRango;
		rangoSegundo = segundoRango;
		arrayCheck = new Array();
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.obtenerDatosPorCorrelativo(idSelectedItem, primerRango, segundoRango, function(data){

			datos = data.listSif018;
					
			document.getElementById("datosNomina").innerHTML = "";
					
				if(datos != null){

				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
					for(p=0; p<datos.length; p++){
						arrayCheck[p] = new ObjSolicitud(datos[p].id_sif018, datos[p].rut_empleador, 0);
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
		
		EditarReporteCotizacionesDWR.dataEstaticaPorIdSif018(a,b, function(data){
			
			var dat = new Array();
			
			dat = data.listSif018;
			
			if(dat != null){
				for(var i=0; i<dat.length; i++)
				{
					document.ModifSif018Form.txt_fechaProceso.value = dat[i].fecha_proceso;
					document.ModifSif018Form.txt_codigoEntidad.value = dat[i].codigo_entidad;
					document.ModifSif018Form.txt_codigoArchivo.value = dat[i].codigo_archivo;
				}
			}else{
				document.ModifSif018Form.txt_fechaProceso.value = "";
				document.ModifSif018Form.txt_codigoEntidad.value = "";
				document.ModifSif018Form.txt_codigoArchivo.value = "";
			}
		});
		DWREngine.setAsync(true);
	}
		
	/*Funcion que selecciona por cual de los dos campos (rut o correlativo) va a efectuar la busqueda.*/
	function seleccionaBusqueda(){
		
		var r_ut = document.ModifSif018Form.txt_rut.value;
		var dv_verif = document.ModifSif018Form.txt_digitoVerificador.value;
		var primer_Rango = document.ModifSif018Form.txt_primerRango.value;
		var segundo_Rango = document.ModifSif018Form.txt_segundoRango.value;
		idSelectedItem = document.ModifSif018Form.dbx_filtroBusqueda.value;
		
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
		
		var pregunta = confirm("Va a eliminar datos sensibles. ¿Está seguro que desea continuar?.");
		if(pregunta == true){
			if(idSelectedItem == 1){
				if(datos.length == 1){
					eliminarUnicoDatoGrilla();
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
				consultarDatosUpdateados(rutSearchUnico);
			}
		
			if(idSelectedItem == 2){
				consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
			}
		}	
	}
	
	/**Funcion que elimina registro individual de la grilla, siempre y cuando la busqueda por rut arroje un solo registro*/
	function eliminarUnicoDatoGrilla(){
		
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var rut= rutSearchUnico;
		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 1){
						idSif018_glob = arrayCheck[i].id_sif018;
						//rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif018(idSif018_glob, function(data){
						
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
						idSif018_glob = arrayCheck[i].id_sif018;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif018(idSif018_glob, function(data){
						
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
						idSif018_glob = arrayCheck[i].id_sif018;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif018(idSif018_glob, function(data){
						
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
						idSif018_glob = arrayCheck[i].id_sif018;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif018(idSif018_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							("El registro se ha eliminado satisfactoriamente.");
							//actualizaDatosCorrelativo(rangoPrimero,rangoSegundo);
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
		
		EditarReporteCotizacionesDWR.obtenerDatosPorRut018(idSelectedItem, a, function(data){
					
			datos = data.listSif018;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif018, datos[p].rut_empleador, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);
	}
	/*Funcion que actualiza los datos de la grilla dada la eliminacion de un registro dentro del rango de busqueda por correlativo
	mostrado en la grilla. El resultado es pintar nuevamente la grilla de la busqueda inicial pero sin el dato eliminado.*/
	function actualizaDatosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		idSelectedItem = 2;
		
		EditarReporteCotizacionesDWR.actualizarPorCorrelativo018(a, b, function(data){
					
			datos = data.listSif018;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif018, datos[p].rut_empleador, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);		
	}
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 18;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				if(data.periodoProceso == null){
					document.ModifSif018Form.txt_MesProceso.value = "";
				}else{
				document.ModifSif018Form.txt_MesProceso.value = data.periodoProceso;
				}
				document.ModifSif018Form.numeroMes.value = data.mesConsultado;
			}else{
				document.ModifSif018Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que llama a pantalla para agregar registro, en paralelo con la pantalla de modificacion para no incidir en la consulta en la grilla.*/
	function activarAgregarNuevoRegistro(){
		var mesInformado = document.ModifSif018Form.txt_MesProceso.value;
		if(mesInformado == null || mesInformado == ""){
			alert('Debe procesar el archivo 18 antes de agregar un registro');
		}else{
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1150, height=700";
		window.open("./pages/AgregarSif018.jsp","",opciones);
		}
	}	
</script>
</head>
<body onload="cabeceraGrilla();cargarArregloParametricas();cargarMesInformado();">
<html:form action="/modificaSif018.do">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idTipoArchivo" value="6">
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
  <input type="hidden" name="idSif018_glob">
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
					<td><strong><p1>MANTENEDOR REPORTE DIVISIÓN RECUPERACIÓN COTIZACIONES SIF018</p1></strong></td>
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
			<td><input type="text" name="txt_primerRango" id="txt_primerRango" disabled="true" size="4" maxlength="6" onkeypress="Upper(this,'N')"/>
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
			2.- Documentos SAFEM emitidos en reemplazo de documentos caducados
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
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 920px; background:#999999; filter:alpha(opacity=80);-moz-opacity:80%;">
 		<div id="datosEstadisticas" style="display: none; position: absolute; width: 450px; margin-top: 80px; margin-left: 200px; background-color: #F2F2F2">
		</div>
  	</div>
	<div id="fondoNegroAdd" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 920px; background:#999999; filter:alpha(opacity=70);-moz-opacity:70%;">
 		<div id="datosAgregar" style="display: none; position: absolute; width: 550px; margin-top: 80px; margin-left: 200px; background-color: #F2F2F2">
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

