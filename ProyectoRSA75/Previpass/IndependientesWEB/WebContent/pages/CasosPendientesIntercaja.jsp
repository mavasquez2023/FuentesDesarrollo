<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA DE AFILIACION TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet"
	type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet"
	type="text/css" />

<link href="/IndependientesWEB/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery-ui.min.js"></script>

<script type="text/javascript" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" src="/IndependientesWEB/js/Calendario.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/PendientesIntercajaDWR.js"></script>

<script type="text/javascript">
	
	DWREngine.setAsync(false);
	
	/*Declaracion de variables globales*/
	var datos = new Array();
	var detalles = new Array();
	var detalleColisiones = new Array();
	var cantidadRegistrosPorPagina = 20;	
	var paginaActual = 1;
	var cantidadRegistros = 1;
	var cantidadRegistrosCol = 2;
	
	/*Creacion para arreglo de archivos*/
	var arregloArchivos = new Array();

	function ObjParametro (codigo, glosa)
	{
		this.codigo = codigo;
		this.glosa = glosa;
	}
	
	function obtenerArchivosPorTipoYFechas(){
	
		document.CasosPendientesIntForm.btn_Buscar.disabled = false;
		document.CasosPendientesIntForm.dbx_periodoArchivoIntercaja.disabled = false;
		
		var primeraFecha = document.CasosPendientesIntForm.txt_FechaInferior.value;
		var segundaFecha = document.CasosPendientesIntForm.txt_FechaSuperior.value;
		var tipoArchivoFiltro = document.CasosPendientesIntForm.dbx_tipoArchivoIntercaja.value;
		
		if(primeraFecha == "" || segundaFecha == ""){
			alert("Debe ingresar el rango de fechas.");
			return false;
		}
			
		PendientesIntercajaDWR.getFiltroArchivosPorFechayTipo(primeraFecha, segundaFecha, tipoArchivoFiltro, function(data){
			
			var archivosIntercaja = null;
			archivosIntercaja = data;
			
			var cod = "0";
			var txt = "";
					
			var cmb = document.CasosPendientesIntForm.dbx_periodoArchivoIntercaja;
			cmb.options.length = 0;
			cmb.options[0] = new Option("Seleccione..." , "0");
			
			for(var i=0; i<archivosIntercaja.length; i++)
			{
				var item = null;
				item = archivosIntercaja[i];
			
				cod = item.codigo;
				txt = item.periodo;
							
				cmb.options[i+1] = new Option(txt, cod);
			}
								
		});
	}
	/*Fin para creacion arreglo archivos.*/
	
			
	function asignaValor(a){

		document.CasosPendientesIntForm.opcion.value = a;
	}
	
	function enviaFormulario(a){
	
		asignaValor(a);
		document.CasosPendientesIntForm.submit();
	}
	
	/*Funcion que valida que la fecha superior no sea inferior a la fecha de inicio*/
	/*function validaFechasIngresadas(fechaInicio, fechaFin){
		
		//para fecha de inicio
		var diaInicio = parseInt(fechaInicio.substring(0,2));
		var mesInicio = parseInt(fechaInicio.substring(3,5));
		var anioInicio = parseInt(fechaInicio.substring(6,10));
		
		//fecha fin
		var diaFin = parseInt(fechaFin.substring(0,2));
		var mesFin = parseInt(fechaFin.substring(3,5));
		var anioFin = parseInt(fechaFin.substring(6,10));
		
		if((anioFin == anioInicio) && (mesFin == mesInicio) && (diaFin == diaInicio)){
			return true;
		}
		if((anioFin > anioInicio)){
			return true;
		}
		if((anioFin == anioInicio) && (mesFin > mesInicio)){
			return true;
		}
		if((anioFin == anioInicio) && (mesFin == mesInicio) && (diaFin > diaInicio)){
			return true;
		}
		
		return false;
	}*/
	
	/*Limpia los campòs de fechas en caso de error*/
	function limpiarFechas(){
		
		document.CasosPendientesIntForm.txt_FechaInferior.value = "";
		document.CasosPendientesIntForm.txt_FechaSuperior.value = "";
	}

	/*Funcion que valida fechas desde y hasta*/
	function validaFechaDesdeHasta(){

		var fechaInferior = document.CasosPendientesIntForm.txt_FechaInferior.value;
		var fechaSuperior = document.CasosPendientesIntForm.txt_FechaSuperior.value;
		var fechaSistema  = "<%=session.getAttribute("FechaSistema")%>";
		
		if(fechaInferior == ""){
			alert("Debes seleccionar una Fecha Desde.");
			document.CasosPendientesIntForm.txt_FechaInferior.value = "";
			return false;
		}
		
		if(fechaSuperior == ""){
			alert("Debes seleccionar una Fecha Hasta.");
			document.CasosPendientesIntForm.txt_FechaSuperior.value = "";
			return false;
		}
		
		//validaFechaFinContraSistema();
		/*if(Comparar_Fecha(fechaSuperior,fechaSistema) == false){
			alert("La Fecha Hasta no debe ser menor que Fecha Actual.");
			document.CasosPendientesIntForm.txt_FechaSuperior.value = "";
			//limpiarFechas();
			return false;
		}*/
		
		if(Comparar_Fecha(fechaSuperior,fechaInferior) == false){
			alert("La Fecha Hasta no debe ser menor que Fecha Desde.");
			document.CasosPendientesIntForm.txt_FechaSuperior.value = "";
			//limpiarFechas();
			return false;
		}		
	}
	
	/*function validaFechaInicioContraSistema()
	{
		var fechaInicio = document.CasosPendientesIntForm.txt_FechaInferior.value;
		
		alert("Fecha inicio = " + fechaInicio);
		
		if(fechaInicio == ""){
			alert("Debes ingresar la Fecha Desde.");
			return false;
		}
		
		//fecha de sistema
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";
		
		alert("fecha sistema = " + fechaSistema);
		
		var diaSistema = parseInt(fechaSistema.substring(0,2));
		var mesSistema = parseInt(fechaSistema.substring(3,5));
		var anioSistema = parseInt(fechaSistema.substring(6,10));
		
		alert("dia sistema = " + diaSistema);
		alert("mes sistema = " + mesSistema);
		alert("año sistema = " + anioSistema);
		
		//para fecha de inicio
		var diaInicio = parseInt(fechaInicio.substring(0,2));
		var mesInicio = parseInt(fechaInicio.substring(3,5));
		var anioInicio = parseInt(fechaInicio.substring(6,10));
		
		alert("dia inicio = " + diaInicio);
		alert("mes inicio = " + mesInicio);
		alert("año inicio = " + anioInicio);
		
		//valida los años para fecha inicio
		if(anioInicio > anioSistema ){
			alert("La Fecha Desde no debe ser mayor a la fecha actual.");
			limpiarFechas();
			return false;
		}
		
		//valida meses para fecha inicio
		if((mesInicio > mesSistema) && (anioInicio == anioSistema)){
			alert("La Fecha Desde no debe ser mayor a la fecha actual.");
			limpiarFechas();
			return false;
		}
		
		//valida los dias de inicio
		if((diaInicio > diaSistema) && (mesInicio == mesSistema) && (anioInicio == anioSistema)){
			alert("La Fecha Desde no debe ser mayor a la fecha actual.");
			limpiarFechas();
			return false;
		}
	}*/
	
	/*function validaFechaFinContraSistema(){
	
		var fechaFin = document.CasosPendientesIntForm.txt_FechaSuperior.value;
		
		if(fechaFin == ""){
			alert("Debes ingresar la Fecha Hasta.");
			return false;
		}
		
		//fecha de sistema
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";
		var diaSistema = parseInt(fechaSistema.substring(0,2));
		var mesSistema = parseInt(fechaSistema.substring(3,5));
		var anioSistema = parseInt(fechaSistema.substring(6,10));
		
		//fecha fin
		var diaFin = parseInt(fechaFin.substring(0,2));
		var mesFin = parseInt(fechaFin.substring(3,5));
		var anioFin = parseInt(fechaFin.substring(6,10));
		
		if(anioFin > anioSistema){
			alert("La Fecha Hasta no debe ser mayor a la fecha actual.");
			document.CasosPendientesIntForm.txt_FechaSuperior.value = "";
			//limpiarFechas();
			return false;
		}
		
		if((mesFin > mesSistema) && (anioFin == anioSistema)){
			alert("La Fecha Hasta no debe ser mayor a la fecha actual.");
			document.CasosPendientesIntForm.txt_FechaSuperior.value = "";
			//limpiarFechas();
			return false;
		}
		
		if((diaFin > diaSistema) && (mesFin == mesSistema) && (anioFin == anioSistema)){
			alert("La Fecha Hasta no debe ser mayor a la fecha actual.");
			document.CasosPendientesIntForm.txt_FechaSuperior.value = "";
			//limpiarFechas();
			return false;
		}
	}*/
	
	/*funcion que valida que la fecha desde no sea menor al 01/01/2012*/
	function validaFechaLimiteInferior()
	{
		var fechaInicio = document.CasosPendientesIntForm.txt_FechaInferior.value;
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";
		
		//validaFechaInicioContraSistema();
		if(Comparar_Fecha(fechaSistema,fechaInicio) == false){
			alert("La Fecha Desde no debe ser mayor a la fecha actual.");
			limpiarFechas();
			return false;
		}
		
		var fechaInferior = document.CasosPendientesIntForm.txt_FechaInferior.value;
		var fechaLimiteInferior = "01/01/2012";
		
		if(Comparar_Fecha(fechaInferior,fechaLimiteInferior) == false){
			alert("La fecha Desde debe ser mayor al 01/01/2012.");
			limpiarFechas();
			return false;
		}
	}
	
	/*Funcion que consulta los casos pendientes a la tabla log casos pendientes. */
	function consultarCasosPendientes()
	{
		var fechaInferior = document.CasosPendientesIntForm.txt_FechaInferior.value;
		var fechaSuperior = document.CasosPendientesIntForm.txt_FechaSuperior.value;
		var tipoArchivo = document.CasosPendientesIntForm.dbx_tipoArchivoIntercaja.value;
		var idMaestroArchivo = document.CasosPendientesIntForm.dbx_periodoArchivoIntercaja.value;
		
		document.CasosPendientesIntForm.idTipoArchivo.value = tipoArchivo;
		
		/*Valida que los campos no vengan vacios o nulos*/
		if(fechaInferior == ""){
			alert("Debes ingresar la Fecha Desde.");
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			return false;
		}
		
		if(fechaSuperior == ""){
			alert("Debes ingresar la Fecha Hasta.");
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			return false;
		}
		
		if(tipoArchivo == 0){
			alert("Debes seleccionar un Tipo de Archivo.");
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			return false;
		}
		
		if(idMaestroArchivo == 0){
			alert("Debes seleccionar un Periodo de Archivo.");
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			return false;
		}
		
		document.getElementById("pantallaDeCarga").style.visibility = "visible";

		alert("Presione 'Aceptar' para continuar con la búsqueda.");
		
		PendientesIntercajaDWR.obtenerCasosPendientes(idMaestroArchivo, tipoArchivo, function(data)
		{
			datos = data.lisPendientesIntercaja;
			
			if(datos != null){

				cargaDatosEnGrilla();
			}
		});
		document.getElementById("pantallaDeCarga").style.visibility = "hidden";
	}
	
	/*Funcion que carga la cabecera de la grilla.*/
	function cargaCabecera(){
	
		document.CasosPendientesIntForm.dbx_periodoArchivoIntercaja.disabled = true;
		
		document.getElementById("datosNomina").innerHTML = 	
			'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nº Registro Error</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descripción Error</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Archivo Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Archivo Origen</td>'+
	        	'</tr>'+
	        '</table>';
	        
	     
	     $( "#txt_FechaInferior" ).datepicker({
	      	defaultDate: "+1w",
			showOn: "button",
			buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			buttonImageOnly: true,
			buttonText: "Seleccionar fecha",
	      onClose: function( selectedDate ) {
	        $( "#txt_FechaSuperior" ).datepicker( "option", "minDate", selectedDate );
	      }
	    });
	    $( "#txt_FechaSuperior" ).datepicker({
	      	defaultDate: "+1w",
	      	showOn: "button",
			buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			buttonImageOnly: true,
			buttonText: "Seleccionar fecha",
	      onClose: function( selectedDate ) {
	        $( "#txt_FechaInferior" ).datepicker( "option", "maxDate", selectedDate );
	      }
	    });   
	     
	}
	
	/*Funcion que carga los datos consultados en la grilla, con tope máximo por página de 10 registros.*/
	function cargaDatosEnGrilla()
	{
		var contenidoTabla = "";
		
		for(var i=0; i<datos.length; i++)
		{
			if( i < cantidadRegistrosPorPagina )
				
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		
		generarPaginacion();

	}

	/*funcion que obtiene la fila de la tabla*/
	function obtenerFilaTabla(dato)
	{
		var texto =  " <tr> "+
							"<td class='texto' align='center'>" + "<a href='#' onclick='obtenerDetallePorIdDetalleFile(" + dato.correlCasePendiente + ");'>" + dato.correlCasePendiente + "</a>" +"</td>"+
							"<td class='texto' align='center'>" + dato.descripcionError +"</td>"+
							"<td class='texto' align='center'>" + dato.nombreArchivo +"</td>"+
							"<td class='texto' align='center'>" + dato.tipoArchivo +"</td>"+
	   				 "</tr>"; 
		return texto;
	}
	
	function obtenerHeaderTabla()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nº Registro Error</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descripción Error</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Archivo Origen</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Archivo Origen</td>'+
				'</tr>';
	}

	/*Función que genera la paginación*/
	function generarPaginacion(){
		var paginas = (datos.length/cantidadRegistrosPorPagina)+"";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
				
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" casos pendientes en el Proceso de Intercaja.</font>";
			
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
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(paginas);
	}
	
	/*****************************************************************************************/
	/*Funciones que levantan pantalla informativa con Detalles de caso pendiente*/
	
	function obtenerDetallePorIdDetalleFile(idDetalle){
	
		var idFileType = parseInt(document.CasosPendientesIntForm.idTipoArchivo.value);
		
		if((idFileType == 4)){
		
			alert("El tipo de archivo ESTADISTICAS no aplica.");
			return false;
		
		}
			
		PendientesIntercajaDWR.obtenerDetallePendiente(idDetalle, idFileType, function(data){
			detalles = data.lisPendientesIntercaja;
			
			if(detalles != null){
				
				switch(idFileType){
					case 1:
						cargaDatosEstadisticos();
						break;
					case 2:
						obtenerDataGrillaColisiones(idDetalle);
						cargaDatosDetalleColisiones();
						break;
					case 3:
						cargaDatosEstadisticos();
						break;
					/*case 4:
						alert("No existe pantalla para estadisticos.");
						break;*/
					case 5:
						cargaDatosDetalleErrores();
						break;					

					default:
						alert("Ha ocurrido un error en el proceso de obtención de detalle de registro pendientes");
				}
			}else{
				alert("No se ha encontrado registros asociados al registro de error");
			}		
		});
	}
		
	function obtenerHeaderDetalle()
	{
		return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>CASO PENDIENTE</b></p>' + 
						'<p align="left"> 1.- Detalle de Registro Erróneo.</p>'+
					'</td>'+
				'</tr>';
	}
	
	function obtenerFilaTablaDetalle(detalle)
	{

		var texto =  "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>"+
						"<tr>"+
								"<td class='texto' align='left'>" + " " + "Nº Registro Error" + "</td>"+
							    "<td class='texto' align='left'>"+
									"<input type='text' name='txt_numReg' id='txt_numReg' value='" + detalle.numeroRegistroTabla + "' disabled='true' size='65'/>"+
								"</td>"+
						"</tr>"+
						"<tr>"+		
								"<td class='texto' align='left'>" + " " + "Descripción del Error" + "</td>"+
								"<td align='left'>"+
									"<input  name='txt_descErr' id='txt_descErr' value='" + detalle.descripcionError + "' disabled='true' size='65'/>"+
								"</td>"+
						"</tr>"+
						"<tr>"+
								"<td class='texto' align='left'>" + " " + "Nombre Archivo Origen" + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_nombArch' id='txt_nombArch' value='" + detalle.nombreArchivo + "' disabled='true' size='65' />"+
								"</td>"+
						"</tr>"+
						"<tr>"+
								"<td class='texto' align='left'>" + " " + "Tipo Archivo Origen" + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_tipArchOr' id='txt_tipArchOr' value='" + detalle.tipoArchivo + "' disabled='true' size='65'/>"+
								"</td>"+																					
		   				 "</tr>"+
		   			"</table>";
		return texto;
	}

	function obtenerSegundaCabecera(){
		return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="left"> 2.- Datos Detalle Registros.</p>'+
					'</td>'+
				'</tr>';
	}
		
	function obtenerDetalleRegistros(detalle){
		
		var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
						"<tr>"+
							"<td class='texto' align='left'>" + "Rut Independiente" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_rutIndep' id='txt_rutIndep' value=" + detalle.rutAfiliado + " disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Fecha Solicitud" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_fecSolic' id='txt_fecSolic' value=" + detalle.fechaSolicitud + " disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Fecha Inicio" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_fecInicio' id='txt_fecInicio' value=" + detalle.fechaInicio + " disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>" + 
					   "<tr>" +
							"<td class='texto' align='left'>" + "Nombre Afiliado" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_nameAfil' id='txt_nameAfil' value='" + detalle.nombreCompleto + "' disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>" + 
					   "<tr>" +
							"<td class='texto' align='left'>" + "CCAF de Origen" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_ccafOrigen' id='txt_ccafOrigen' value='" + detalle.ccafCajaOrigenGlosa + "' disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>" +
					   "<tr>" +
							"<td class='texto' align='left'>" + "CCAF de Destino" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_ccafDestino' id='txt_ccafDestino' value='" + detalle.ccafCajaDestinoGlosa + "' disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>" +					   					   
					"</table>";  
		return texto;			   		
	}

	function obtenerHeaderCancelarDetalle()
	{
		return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Aceptar" onclick="esconderDiv();"/>'+
					'</td>'+
				'</tr>';
	}
	
	function esconderDiv()
	{
		document.getElementById("datosDetalleError").style.display = "none";
		document.getElementById("fondoNegroDetalles").style.visibility = "hidden";
	}

	function cargaDatosEstadisticos()
	{
		var contenidoTablaDetalles = "";
		var contenidoSegundoEncabezado = "";
		for(var i=0;i<4;i++)
		{
			if( i < cantidadRegistros ){
				
				contenidoTablaDetalles = contenidoTablaDetalles + obtenerFilaTablaDetalle(detalles[i]);
			}
			if( i < cantidadRegistros){
				contenidoSegundoEncabezado = contenidoSegundoEncabezado +  obtenerDetalleRegistros(detalles[i]);
			}		
		}
		document.getElementById("datosDetalleError").style.display = "";
		document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalle() + contenidoTablaDetalles + obtenerSegundaCabecera()+ contenidoSegundoEncabezado + obtenerHeaderCancelarDetalle() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegroDetalles").style.visibility = "visible";
		document.getElementById("datosDetalleError").style.visibility = "visible";
	}
	
	/*Para pantalla de archivo COLISIONES*/
	function obtenerDetalleRegistrosColisiones(detalle){
		
		var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
						"<tr>"+
							"<td class='texto' align='left'>" + "Rut Independiente" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_rutIndep' id='txt_rutIndep' value=" + detalle.rutAfiliado + " disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>" +
							"<td class='texto' align='left'>" + "Nombre Afiliado" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_nameAfil' id='txt_nameAfil' value='" + detalle.nombreCompleto + "' disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>" + 
					"</table>" + "<br/>";
			
			texto = texto +
				'<table width="90%" align="center" cellpadding="0" cellspacing="1">'+
					'<tr>'+
		            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">CCAF</td>'+
		            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha de Solicitud</td>'+
		            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Recibe Afiliado</td>'+
					'</tr>';
			
			//obtenerDataGrillaColisiones(detalle.correlCasePendiente);
						
		return texto;			   		
	}
	
	function obtenerDataGrillaColisiones(numeroRegistro){
		
		PendientesIntercajaDWR.obtenerDataGrillaColisiones(numeroRegistro, function(data){
			detalleColisiones = data.lisPendientesIntercaja;
			
			if(detalleColisiones != null){
			
				//alert("Se han obtenido los datos satisfactoriamente.");
			
			}else{
			
				alert("Error, no se puede obtener la data solicitada.");
			}	
		});	
	}	
	
	function cargarMiniGrillaColisiones(detalleColision){
	
		var texto =  " <tr> "+
							"<td class='texto' align='center'>" + detalleColision.glosaCaja +"</td>"+
							"<td class='texto' align='center'>" + detalleColision.fechaSolicitud +"</td>"+
							"<td class='texto' align='center'>" + detalleColision.recibeAfiliado +"</td>"+
	   				 "</tr>";		 
		return texto;
	}
	
	function cargaDatosDetalleColisiones()
	{
		var contenidoTablaDetalles = "";
		var contenidoSegundoEncabezado = ""; 
		var contenidoGrillaColisiones = "";
		for(var i=0;i<4;i++)
		{
			if( i < cantidadRegistros ){
				
				contenidoTablaDetalles = contenidoTablaDetalles + obtenerFilaTablaDetalle(detalles[i]);
			}
			if( i < cantidadRegistros){
				contenidoSegundoEncabezado = contenidoSegundoEncabezado +  obtenerDetalleRegistrosColisiones(detalles[i]);
			}
			if(i < cantidadRegistrosCol){
				contenidoGrillaColisiones = contenidoGrillaColisiones + cargarMiniGrillaColisiones(detalleColisiones[i]);
			}	
		}
		document.getElementById("datosDetalleError").style.display = "";
		document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalle() + contenidoTablaDetalles + obtenerSegundaCabecera()+ contenidoSegundoEncabezado + contenidoGrillaColisiones + "<br/>" +  obtenerHeaderCancelarDetalle() +  "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegroDetalles").style.visibility = "visible";
		document.getElementById("datosDetalleError").style.visibility = "visible";
	}
	/*Fin modificacion para pantalla COLISIONES.*/

	/*Pantalla para archivo ERRORES*/
	function obtenerDetalleRegistrosErrores(detalle){

		var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
						"<tr>"+
							"<td class='texto' align='left'>" + "Rut Independiente" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_rutIndep' id='txt_rutIndep' value=" + detalle.rutAfiliado + " disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>"+
						"<tr>"+
							"<td class='texto' align='left'>" + "Fecha de Solicitud" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_fecSolicitud' id='txt_fecSolicitud' value=" + detalle.fechaSolicitud + " disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>" +
							"<td class='texto' align='left'>" + "Nombre Afiliado" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_nameAfil' id='txt_nameAfil' value='" + detalle.nombreCompleto + "' disabled='true' size='45'/>"+
							"</td>"+
					   "</tr>" +
					"</table>";
		
		var idTipoError = parseInt(detalle.idTipoError);

		switch(idTipoError){
			case 1:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " + 
									"AL REGISTRO DEBIDO A QUE EXISTE UNA DUPLICACIÓN DE RUT EN EL ARCHIVO." + 
								"</td>" +
					   		"</tr>";
				break;
			case 2:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " +
									"AL REGISTRO DEBIDO A QUE NO EXISTE UN AFILIADO ASOCIADO AL RUT."+
								"</td>" +
					   		"</tr>";			
				break;
			case 3:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " +
									"AL REGISTRO DEBIDO A QUE NO EXISTE EL RUT ASOCIADO A UN AFILIADO." + 
								"</td>" +
					   		"</tr>";			
				break;
			case 4:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " + 
									"AL REGISTRO DEBIDO A QUE NO EXISTE UNA SOLICITUD ASOCIADA AL AFILIADO." + 
								"</td>" +
					   		"</tr>";			
				break;
			case 5:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " +
									"AL REGISTRO DEBIDO A QUE LA SOLICITUD NO SE ENCUENTRA EN ESTADO PRE-APROBADA O INCOMPLETA." + 
								"</td>" +
					   		"</tr>";
				break;
			case 6:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " +
									"AL REGISTRO DEBIDO A QUE EXISTE UNA SOLICITUD DE DESAFILIACION EN CURSO." + 
								"</td>" +
					   		"</tr>";
				break;
			case 7:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " +
									"AL REGISTRO DEBIDO A QUE EL AFILIADO NO SE ENCUENTRA EN EL ESTADO EN PROCESO." +
								 "</td>" +
					   		"</tr>";			
				break;
			case 8:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "NO ES POSIBLE APLICAR REGLAS DE NEGOCIO " + 
									"AL REGISTRO DEBIDO A QUE EL RUT ES INCONSISTENTE." +
								"</td>" +
					   		"</tr>";			
				break;
			case 9:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "EL AFILIADO INFORMADO A SISTEMA DE INTERCAJA " + 
									"YA SE ENCUENTRA INCORPORADO EN BASE." +
								"</td>" +
					   		"</tr>";			
				break;
			case 10:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "EL AFILIADO INFORMADO A SISTEMA DE INTERCAJA " + 
									"NO CUMPLE CON LA ANTIGUEDAD SUFICIENTE PARA LLEVAR A CABO EL PROCESO DE DESAFILIACION." + 
								"</td>" +
					   		"</tr>";			
				break;				
			case 11:
				texto = texto + 
						"<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>" +
							"<br/>"+
							"<tr>"+
								"<td class='texto' align='left'>" + "EL MOVIMIENTO DE AFILIADO INFORMADO A SISTEMA INTERCAJA " + 
									"NO ES POSIBLE LLEVARLO A CABO DEBIDO A QUE ES INEXISTENTE EN BASE." +
								"</td>" +
					   		"</tr>";
				break;
			default:
				alert("Error general del sistema, no se ha podido alcanzar el error específico.");																																					
		}	
		return texto;			   		
	}

	function cargaDatosDetalleErrores()
	{
		var contenidoTablaDetalles = "";
		var contenidoSegundoEncabezado = "";
		for(var i=0;i<4;i++)
		{
			if( i < cantidadRegistros ){
				
				contenidoTablaDetalles = contenidoTablaDetalles + obtenerFilaTablaDetalle(detalles[i]);
			}
			if( i < cantidadRegistros){
				contenidoSegundoEncabezado = contenidoSegundoEncabezado +  obtenerDetalleRegistrosErrores(detalles[i]);
			}		
		}
		document.getElementById("datosDetalleError").style.display = "";
		document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalle() + contenidoTablaDetalles + obtenerSegundaCabecera()+ contenidoSegundoEncabezado + obtenerHeaderCancelarDetalle() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegroDetalles").style.visibility = "visible";
		document.getElementById("datosDetalleError").style.visibility = "visible";
	}
	/*Fin modificacion para pantalla ERRORES.*/

</script>
</head>
<body onload="cargaCabecera();">
<html:form action="/casosPendInt.do">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idTipoArchivo" value=" ">
   
  <div id="caja_registro">
	  <table width="970">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>CONSULTA CASOS PENDIENTES - PROCESO DE INTERCAJA</p1></strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario" value='<%=session.getAttribute("IDAnalista")%>' disabled="true" size="10"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" size="10" value='<%=session.getAttribute("FechaSistema")%>' id="txt_Fecha" disabled="true" /></td>				
				</tr>
			</table>
		   </td>	
		</tr>
		<tr>
			<td>
				<p><p2></p>
			<p></p>
			<p>1. Filtros de Búsqueda.<p2></p>
			<p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td><strong>Fecha Desde *</strong></td>
						<td>	
							<!-- <input type="text" name="txt_FechaInferior" id="txt_FechaInferior" maxlength="12" size="10" disabled="true" onChange="cargarFechaInferior();"/> -->
							<input type="text" name="txt_FechaInferior" id="txt_FechaInferior" maxlength="12" size="10" disabled="true" />
							<!--<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaInferior);validaFechaLimiteInferior();" >
						--></td>
						<td><strong>Fecha Hasta *</strong></td>
						<td>	
							<!-- <input type="text" name="txt_FechaSuperior" id="txt_FechaSuperior" maxlength="12" size="10" disabled="true" onChange="cargarFechaSuperior();"> -->
							<input type="text" name="txt_FechaSuperior" id="txt_FechaSuperior" maxlength="12" size="10" disabled="true" ><!--
							<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaSuperior);validaFechaDesdeHasta();" >
						--></td>						
						<td></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td width="16%"><strong>Tipo Archivo *</strong></td>
						<td width="16%">
							<html:select property="dbx_tipoArchivoIntercaja" styleClass="dbx_tipoArchivoIntercaja" value="0" onclick="obtenerArchivosPorTipoYFechas();">
								<html:option value="0">Seleccione </html:option>
								<c:forEach items="${sessionScope.ListTipoArchivoIntercaja}" var="opcion">
									<c:choose>
										<c:when test="${opcion.codigo != '4'and opcion.codigo != '7'}" >
											<option value="<c:out value='${opcion.codigo}'/>">
												<c:out value='${opcion.glosa}'/>
											</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</html:select>
						</td>
						<td width="16%"><strong>Periodo Archivo *</strong></td>
						<td width="16%">
							<html:select property="dbx_periodoArchivoIntercaja" styleClass="combobox" value="0">
								<html:option value="0">Seleccione </html:option>
							</html:select>
						</td>
						<td></td>
						<td colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
	  </table>
	<br/>	
	<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 300px;">
	</div>
	
	<br/>
	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 40px;">
	</div>
   	<br/>

  <div id="fondoNegroDetalles" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 800px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
 	<div id="datosDetalleError" style="display: none; position: absolute; width: 650px; margin-top: 110px; margin-left: 300px; background-color: #F2F2F2">
	</div>
  </div>
		
	  <table width="970" border="0">
	  </br>
	  </br>
	  </br>	
		<tr>
		<td style="font-size:11px;">(*campos obligatorios)</td>
		</tr>
		<tr>
			<td height="37" align="right">
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"	value="Cancelar" onClick="enviaFormulario(1);" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp"	value="Buscar"  disabled="true" onClick="consultarCasosPendientes();" /> 
				&nbsp;&nbsp;&nbsp;
			</td>
		</tr>		 	 	
	  </table>
	<br/>
	
	<div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 800px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
		<table width="100%">
			<tr>
				<td height="200">
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<IMG border="0" src="/IndependientesWEB/images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	</div>
	
  </div>
 </html:form> 
</body>
</html:html>
