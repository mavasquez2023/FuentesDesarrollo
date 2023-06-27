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
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/EntradaIntercajaDWR.js"></script>

<script type="text/javascript">
	
	//DWREngine.setAsync(false);
	//var flag = true;
	
	function asignaValor(a){

		document.GenArchEntradaIntForm.opcion.value = a;
	}
	
	function enviaFormulario(a){
	
		asignaValor(a);
		document.GenArchEntradaIntForm.submit();
	}

	/*Funcion para boton examinar*/
	function comprueba_extension(formulario, archivo) 
	{
		/*añadido para flujo 4*/
		var tipoArchivoFlujo4 = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		
		/*Limpia variables.*/
		limpiaObjetosForm();
		
	   	extensiones_permitidas = new Array(".txt");
	   	mierror = "";
	   	
	   	if (!archivo) 
	   	{
	   		//Si no tengo archivo, es que no se ha seleccionado un archivo en el formulario 
	      	mierror = "No ha seleccionado ningún archivo."; 
	   		return false;
		}else
		{
	   
	      	//recupero la extensión de este nombre de archivo 
	      	extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase(); 
	
		    //compruebo si la extensión está entre las permitidas 
		    permitida = false; 
		    
		    for (var i = 0; i < extensiones_permitidas.length; i++) 
		    {
		         if (extensiones_permitidas[i] == extension) 
		         {
		         	permitida = true; 
		         	break; 
		         } 
		    }

			if (!permitida) 
	      	{
		         mierror = "Comprueba la extensión de los archivos a subir. \nSólo se pueden cargar archivos con extensiones: " + extensiones_permitidas.join(); 
		      	 alert (mierror);
		      	 eliminarArchivoServer();
		      	 return false;	
	      	
	      	}else{ 

				/*modificacion para flujo 4*/
				if(tipoArchivoFlujo4 != 6)
				{
		         	if(!leerArchivoDWR()){
		         		return false;
		         	}
		        }else{
		         	if(!leerArchivoFlujo4())
		         	{
		         		return false;
		         	}
		         }
		         /*fin modificacion para flujo 4*/
		         
		         //Se comenta para saber que lee del flujo 4
		         if(document.GenArchEntradaIntForm.flagArchivo.value != "false")
		         {
				 	validaExisteRegistro();
				 }else
				 {
				 	limpiarCampos();
				 	return true;
				 }
	      	} 
   		} 
   		return true;
	}
	
	/*Funcion que trasnfiere archivo a máquina AS400*/
	function transferirArchivoToAS400()
	{
		/*cambiar rutaArchivo por la del was*/
		var rutaArchivoWas = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value; //aqui poner la ruta que sera pegada desde campo rutaArchivo.	
		var nombreArchivo = document.GenArchEntradaIntForm.nombreDelArchivo.value;
		var tipoArchivo = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		
		DWREngine.setAsync(false);
		
		EntradaIntercajaDWR.transferirFileToAS400(rutaArchivoWas, nombreArchivo, tipoArchivo, function(data){
			var resp = null;
			resp = data;
			
			if(resp == "Ok"){
				alert("Archivo transferido a registro histórico exitosamente.");
			}else{
				alert("Ha fallado la transferencia desde WAS a AS400.");
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	function transferirArchivoToAS400ForProccess(){

		/*cambiar rutaArchivo por la del was FRM--*/	
		var rutaArchivoWas = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value; //aqui poner la ruta que sera pegada desde campo rutaArchivo.	
		var nombreArchivo = document.GenArchEntradaIntForm.nombreDelArchivo.value;

		DWREngine.setAsync(false);
		
		EntradaIntercajaDWR.transferirFileToProccessAS400(rutaArchivoWas, nombreArchivo, function(data){
			var resp = null;
			resp = data;
			
			if(resp != "Ok")
			{
				alert("Ha fallado la transferencia desde WAS a AS400.");
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion DWR que va a insertar los campos necesarios en la tabla MaestroIntercaja*/
	function insertarMaestroIntercajaDWR(){
		
		DWREngine.setAsync(false);
		var nombreArchivo = construyeNombreArchivo();
		var fechaCabecera = valorFechaCabecera();
		//var nombreArchivo = "005ERR0612";
		//var fechaCabecera = "<%=session.getAttribute("FechaSistema")%>";
		var fechaProceso = "<%=session.getAttribute("FechaSistema")%>";
		var tipoArchivo = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		var idAnalista = "<%=session.getAttribute("IDAnalista")%>";
		
		EntradaIntercajaDWR.insertarMaestroIntercaja(nombreArchivo, fechaCabecera, fechaProceso, tipoArchivo, idAnalista, function(data){
			
			var result = null;
			
			result = data.codResultado;
			
			if(result == 0)
			{
				document.GenArchEntradaIntForm.nombreDelArchivo.value = data.nombreArchivo;
			}else
			{
				alert("Ha ocurrido un error al insertar los datos en maestroIntercaja.");
			}		
		});

		DWREngine.setAsync(true);
	} 
	
	/*Funcion que envia el archivo al servidor FTP.*/
	function enviarArchivoFtp()
	{
		DWREngine.setAsync(false);
		//var fileName = "BC072012.txt";
		var fileName = document.GenArchEntradaIntForm.nombreDelArchivo.value;
		var source = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value;//ruta del archivo que será transferido a FTP.
		var tipoArchivo = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		
		EntradaIntercajaDWR.enviarArchivoFTP(fileName, source, tipoArchivo, function(data){
			 
			 var resp = null;
			 resp = data;
				
			if (resp != "Ok")
			{
				alert("Error, no se ha podido subir el archivo.");
			}
		});
		DWREngine.setAsync(true);
	}
	
	function enviarArchivoProcesarFTP()
	{
		DWREngine.setAsync(false);
		var fileName = document.GenArchEntradaIntForm.nombreDelArchivo.value;
		var source = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value;//ruta del archivo que será transferido a FTP.
		
		EntradaIntercajaDWR.enviarArchivoAProcesarFTP(fileName, source, function(data){
			var resp = null;
			resp = data;
			
			if(resp != "Ok")
			{
				alert("Error, el archivo no se ha podido transferir para su procesamiento.");
				//eliminarArchivoServer();
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	/*funcion que lee el archivo, y devuelve el tipo de archivo, y la fecha de cabecera.*/
	var fechaCabeceraTemp;
	var concat = "";
	
	function leerArchivoDWR()
	{
		DWREngine.setAsync(false);
		var rutaArchivo = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value;
		var tipoArchivo = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		
		document.GenArchEntradaIntForm.flagArchivo.value = "true";
		
		EntradaIntercajaDWR.leerArchivo(rutaArchivo, tipoArchivo, function(data)
		{
			var respuesta = null;
			
			respuesta = data.codResultado;
			
			document.GenArchEntradaIntForm.fechaCabecera.value = data.fechaCabecera;
			tipoArchivo = data.tipoArchivoLeido;
			valorTipoArchivo = data.tipoArchivo;
			
			concat = data.fechaCabecera;
			//fechaCabecera = data.fechaCabecera;
			if(respuesta == 0)
			{
				document.GenArchEntradaIntForm.valorTipoArchivo.value = data.tipoArchivo;
				
				//alert(document.GenArchEntradaIntForm.valorTipoArchivo.value);
				if(data.tipoArchivo == 0){
					valorTipoArchivo = 6;
					if(validaTipoArchivo(valorTipoArchivo) == false)
					{
						alert("Has seleccionado un tipo de archivo distinto a BASE COMUN.");
						document.GenArchEntradaIntForm.flagArchivo.value = "false";
					}	
				}else{
				
					/*Aqui se realiza la validacion del archivo de acuerdo a lo seleccionado en el combobox de tipo de archivo.*/
					if(validaTipoArchivo(valorTipoArchivo) == false)
					{
						alert("Has seleccionado un tipo de archivo distinto a" + " " + tipoArchivo + ".");
						document.GenArchEntradaIntForm.flagArchivo.value = "false";
					}
				}
			}else
			{
				alert("Archivo no se ha podido leer. Es posible que no tenga el formato esperado.");
				return false;
			}
		});
		DWREngine.setAsync(true);
		return true;
	}

	/*funcion que lee archivo para el flujo 4*/
	function leerArchivoFlujo4()
	{
		DWREngine.setAsync(false);
		var rutaArchivo = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value;
		
		document.GenArchEntradaIntForm.flagArchivo.value = "true";
		
		EntradaIntercajaDWR.leerArchivoFlujo4(rutaArchivo, function(data)
		{
			var resp = null;
			resp = data.codResultado;
			
			valorTipoArchivo = data.tipoArchivo;
			
			if(resp == 0)
			{
				document.GenArchEntradaIntForm.valorTipoArchivo.value = 6;//data.tipoArchivo;
				
				/*Aqui se realiza la validacion del archivo de acuerdo a lo seleccionado en el combobox de tipo de archivo.*/
				if(validaTipoArchivo(valorTipoArchivo) == false)
				{
					alert("Has seleccionado un tipo de archivo distinto a BASE COMUN.");//" + " " + tipoArchivo + ".");
					document.GenArchEntradaIntForm.flagArchivo.value = "false";
				}
				
			}else
			{
				alert("Archivo no se ha podido leer");
				return false;
			}	
		});
		
		DWREngine.setAsync(true);
		return true;
	}
	/*Fin funcion leer archivo para flujo 4*/
	
	
	function salirDeDWR()
	{
		document.GenArchEntradaIntForm.flagArchivo.value = "false";
		return false;
	}
	
	/* Funcion que valida si ya existe un registro cargado en la tabla maestro intercaja.
	*	Si el registro existe, y esta en status 1, no puede volver a ingresar.	
	*/
	function validaExisteRegistro()
	{
		DWREngine.setAsync(false);
		var nameFileRegistrate = construyeNombreArchivo();//document.GenArchEntradaIntForm.nombreDelArchivo.value;
		var fileTypeId = document.GenArchEntradaIntForm.valorTipoArchivo.value;
		
		EntradaIntercajaDWR.validaRegistroMaestroIntercaja(nameFileRegistrate, fileTypeId, function(data)
		{
			var resp = null;
			resp = data.codResultado;
			
			/*si resp == 0, entonces puede seguir el flujo completo, es decir... insertar en maestro intercaja, subir archivo a ftp y cargar la grilla.
			* de lo contrario muestra un mensaje de alerta mostrando que ya ha sido cargado ese tipo de archivo y consulta solo la grilla para información,
			* sin insertar ni transferir al ftp.	
			*/
			if(resp == 0)
			{
				//puede insertar.
				insertarMaestroIntercajaDWR();
				obtenerCabeceraDetalleArchivo();
				//enviarArchivoProcesarFTP();
				transferirArchivoToAS400ForProccess();
				//transferirArchivoToAS400();
				InvocarProcesoCobolDWR();
				
			}else{
				
				consultarStatusProcesoArchivo(nameFileRegistrate,fileTypeId);
				var statusProcesoConsultado = document.GenArchEntradaIntForm.statusDelProceso.value;/*HOLA*/
				
				if((parseInt(statusProcesoConsultado,10) == 1) || (parseInt(statusProcesoConsultado,10) == 2)){
					insertarMaestroIntercajaDWR();//debe dejar insertar, se llama a esta funcion y en el DAO se elige un nuevo idmaestroarchivo.
					obtenerCabeceraDetalleArchivoFallido();
					//enviarArchivoProcesarFTP();
					transferirArchivoToAS400ForProccess();
					//transferirArchivoToAS400();
					InvocarProcesoCobolDWR();
					
				}else
				{
					alert("Durante el periodo de cierre, este archivo ya fue procesado exitosamente.");
					//eliminarArchivoServer();
					limpiarCampos2();
					//consultarDatosGrillaDWR();
				}
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que redirecciona a otras funciones luego de cierto tiempo*/
	function redireccionaFuncion()
	{
		estadisticaTipoArchivoProcesoCobol();
		consultarDatosGrillaDWR();
		cargaDatosEstadisticos();
		//limpiar los campos de busqueda de archivo y tipo de archivo.
		limpiarCampos();
	}
	
	/*Funcion que limpia las variables tipo objeto del form (hidden)*/
	function limpiaObjetosForm(){
	
		document.GenArchEntradaIntForm.tipoArchivo.value = "";
		document.GenArchEntradaIntForm.valorTipoArchivo.value = "";
		document.GenArchEntradaIntForm.fechaCabecera.value = "";
		document.GenArchEntradaIntForm.fechaFinalMes.value = "";
		document.GenArchEntradaIntForm.flagArchivo.value = "";
		document.GenArchEntradaIntForm.totalRegProc.value = "";
		document.GenArchEntradaIntForm.totalRegAplNeg.value = "";
		document.GenArchEntradaIntForm.totalRegPend.value = "";
		document.GenArchEntradaIntForm.idMaestroArchivo.value = "";
		document.GenArchEntradaIntForm.glosaIdTipoArchivo.value = "";
		document.GenArchEntradaIntForm.glosaStatusProceso.value = "";
		document.GenArchEntradaIntForm.fechaProcesoDetalle.value = "";
	
	}

	/*funcion que limpia los campos de busqueda y filtro de archivos.*/
	function limpiarCampos()
	{
		//document.getElementById("limpiar").click();
		obtenerRangoParaIntercaja();
		cargarFechaInferior();
		cargarFechaSuperior();
		//document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value = "";
		document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value = 0;
	}
	
	function limpiarCampos2()
	{
		//document.getElementById("limpiar").click();
		obtenerRangoParaIntercaja();
		cargarFechaInferior();
		cargarFechaSuperior();
		document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value = "";
		document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value = 0;
		eliminarArchivoServer();
	}
	/*Funcion que deja disponible la fecha de cabecera para su uso en cualquier funcion.*/
	function valorFechaCabecera()
	{
		var fecha = document.GenArchEntradaIntForm.fechaCabecera.value;
		
		if(fecha == "")
		{
			fecha = "01/01/1900";
		}
			
		return fecha;
	}
		
	/* Funcion que construye el nombre del archivo y luego realiza la consulta para ver si ese archivo se puede insertar 
	* Dicho archivo se construye tomando en consideración la fecha en que se ha procesado y el idTipoArchivo (o tipo de archivo).
	* si el tipo de archivo es 1 se crea un acronimo que toma el valor de TRA. 
	* si el tipo de archivo es 2 se crea un acronimo que toma el valor de COL.
	* si el tipo de archivo es 3 se crea un acronimo que toma el valor de REC.
	* si el tipo de archivo es 4 se crea un acronimo que toma el valor de EST.
	* si el tipo de archivo es 5 se crea un acronimo que toma el valor de ERR.
	* Cada acronimo toma un largo de 3 caracteres.
	* De la fecha se toman el correspondiente mes y los dos últimos digitos del año. El mes queda con 2 caracteres y el año con 2 caracteres.
	* al idTipoArchivo se concatenan 2 ceros a la izquierda, quedando con un largo de 3 caracteres.
	* Finalmente el nombre del archivo esta conformado por la concatenacion de idTipoArchivo concatenado + acronimo + mes + año. 
	*/
	function construyeNombreArchivo()
	{
		fechaDate = new Date();
		dateDay = fechaDate.getDate();
		dateMonth = fechaDate.getMonth();
		
		var fechaArchivo = "<%=session.getAttribute("FechaSistema")%>";
		var idTipoArchivo;
		
		/*variables que concatenadas construyen nombre del archivo.*/
		var idArchivo = "";
		var acronimoArchivo = "";
		var mesArchivo = fechaArchivo.substring(3,5);
		var anioArchivo = fechaArchivo.substring(6,10);
		var nombreArchivo;
		var mesFinal= "";

		if((parseInt(mesArchivo,10) > parseInt(dateMonth,10)) && (parseInt(dateDay,10) < 05 )){
			mesFinal = '0'+ dateMonth;
		}else{
			mesFinal = 	mesArchivo;
		}
			
		idTipoArchivo = parseInt(document.GenArchEntradaIntForm.valorTipoArchivo.value,10);
		//idTipoArchivo = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		//idTipoArchivo = 3;
		
		switch(idTipoArchivo){
			case 1:
					acronimoArchivo = 'TR';
					nombreArchivo = acronimoArchivo + mesFinal + anioArchivo;
					break;
			case 2:
					acronimoArchivo = 'CO';
					nombreArchivo = acronimoArchivo + mesFinal + anioArchivo;
					break;
			case 3:
					acronimoArchivo = 'RE';
					nombreArchivo = acronimoArchivo + mesFinal + anioArchivo;
					break;
			case 4:
					acronimoArchivo = 'ES';
					nombreArchivo = acronimoArchivo + mesFinal + anioArchivo;
					break;
			case 5:
					acronimoArchivo = 'ER';
					nombreArchivo = acronimoArchivo + mesFinal + anioArchivo;
					break;
			case 6:
					acronimoArchivo = 'BC';
					nombreArchivo = acronimoArchivo + mesFinal + anioArchivo;
					break;		
			default :
					alert("El archivo cargado en sistema no corresponde a un archivo de entrada de Intercaja.");
		}

		return nombreArchivo;									
	}
		
	/*Funcion que valida el tipo de archivo, se valida la respuesta al leer el archivo con lo que selecciona del combobox.*/
	function validaTipoArchivo(tipoArchivo)
	{
		var tipoArchivoSelec = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		
		if(tipoArchivoSelec == tipoArchivo)
		{
			return true;
		}

		return false;
	}
	
	/*Funcion que valida la fecha actual dentro del rango de fechas permitidas.
	* la fecha debe estar entre el 25/mes/año y el 05/mes+1/año
	* si no esta dentro de ese rango, no se puede procesar el archivo y el sistema no debe hacer nada.
	*/
	function validaFechaActual()
	{
		DWREngine.setAsync(false);
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		alert("Presione 'Aceptar' para continuar con el proceso.");
		
		var rangoUno = document.GenArchEntradaIntForm.rangoIntercajaUno.value;
		var rangoDos = document.GenArchEntradaIntForm.rangoIntercajaDos.value;
		
		var file = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value;
		var fileType = document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value;
		
		if(file == "")
		{
			alert("Debe subir un archivo para procesar. Para ello presione el botón 'Volver'.");
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			return false;
		}
		
		if(fileType == 0)
		{
			alert("Debe seleccionar un tipo de archivo.");
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			return false;
		}

		var finalFec = "";
		var diafinal = "";
		var mesFinal = "";
		
		var fechaActual = "<%=session.getAttribute("FechaSistema")%>";
		var day = fechaActual.substring(0,2);
		var month = fechaActual.substring(3,5);
		var year = fechaActual.substring(6,10);
		
		//funcion DWR que pregunta por el ultimo dia del mes.
		/*
		EntradaIntercajaDWR.obtenerUltimaDiaMes(fechaActual, function(data)
		{
			var res = null;
			res = data.codResultado;
			fechaFinalMes = data.fechaUltDia;
			
			if(res == 0)
			{
				finalFec = fechaFinalMes;
				diafinal = finalFec.substring(0,2);
				mesFinal = finalFec.substring(3,5);
				
				//verificacion para periodo de cierre de mes.
				//if((parseInt(day) >= 25 && parseInt(day) <= 31) || (parseInt(day) <= 15)){
				if((parseInt(day,10) >= rangoDos && parseInt(day,10) <= 31) || (parseInt(day,10) <= rangoUno)){
					//Si cumple con la fecha de periodo de cierre de mes, entonces puede procesar archivos.
					if(buscarRutaArchivoServer() == false)
					{
						document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					}
					//buscarRutaArchivoServer();
					if(comprueba_extension(this.form, file) == false)
					{
						document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					}
					//comprueba_extension(this.form, file);
				}else{
				
					alert("No puede procesar archivos, pues esta fuera del periodo de cierre de mes.");
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					return false;
				}
			}
		});
		*/
		
		if(buscarRutaArchivoServer() == false)
		{
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
		}
		//buscarRutaArchivoServer();
		if(comprueba_extension(this.form, file) == false)
		{
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
		}
		DWREngine.setAsync(true);
		document.getElementById("pantallaDeCarga").style.visibility = "hidden";
	}
	
	/*funciones que cargan a los campos de fecha inferior y fecha superior*/
	function cargarFechaInferior()
	{
		var rangoUno = document.GenArchEntradaIntForm.rangoIntercajaUno.value;//05 pruebas 02
		var rangoDos = document.GenArchEntradaIntForm.rangoIntercajaDos.value;//25 pruebas 10
		//var rangoTres = document.GenArchEntradaIntForm.rangoIntercajaTres.value;//25
		
		fechaInferiorActual = new Date();
		mesInferiorActual = fechaInferiorActual.getMonth()+1;//esto pasa porque el primer mes es 0
		anioInferiorActual = fechaInferiorActual.getFullYear();
		
		var fechaSistemaInf = "<%=session.getAttribute("FechaSistema")%>";
		var diaSistemaInf = fechaSistemaInf.substring(0,2);
		var mesSistemaInf = fechaSistemaInf.substring(3,5);
		var fechaRango1 = "";
		
		if(diaSistemaInf <= 05){
			var mesTempInf = fechaInferiorActual.getMonth();
			if(mesTempInf < 10){
				mesTempInf = '0'+ mesTempInf;
			}	
			fechaRango1 = rangoDos + '/' + mesTempInf + '/' + anioInferiorActual;
			document.GenArchEntradaIntForm.txt_FechaInferior.value = fechaRango1;
			
		}else{
			if(mesInferiorActual < 10){
				mesInferiorActual = '0'+ mesInferiorActual;
			}
						
			fechaRango1 = rangoDos + '/' + mesInferiorActual + '/' + anioInferiorActual;
			document.GenArchEntradaIntForm.txt_FechaInferior.value = fechaRango1;
		}
		
		return document.GenArchEntradaIntForm.txt_FechaInferior.value;
	}
	
	function cargarFechaSuperior(){
		
		var rangoUno = document.GenArchEntradaIntForm.rangoIntercajaUno.value;//05 pruebas 02
		var rangoDos = document.GenArchEntradaIntForm.rangoIntercajaDos.value;//25 pruebas 10
		
		if(parseInt(rangoUno,10) < 10){
			rangoUno = '0' + rangoUno;
		}
		fechaSuperiorActual = new Date();
		mesSuperiorActual = fechaSuperiorActual.getMonth() + 2;
		anioSuperiorActual = fechaSuperiorActual.getFullYear();
		
		var fechaSistemaSup = "<%=session.getAttribute("FechaSistema")%>";
		var diaSistemaSup = fechaSistemaSup.substring(0,2);
		var mesSistemaSup = fechaSistemaSup.substring(3,5);

		var fechaRango2 = "";
		
		if(diaSistemaSup <= 05){
			var mesTempSup = fechaSuperiorActual.getMonth() + 1;
			
			if(mesTempSup < 10){
				mesTempSup = '0' + mesTempSup;
			}
			
			fechaRango2 = rangoUno + '/' + mesTempSup + '/' + anioSuperiorActual;
			document.GenArchEntradaIntForm.txt_FechaSuperior.value = fechaRango2;

		}else{

			if(mesSuperiorActual < 10){
				mesSuperiorActual = '0'+ mesSuperiorActual;
			}
			
			fechaRango2 = rangoUno + '/' + mesSuperiorActual + '/' + anioSuperiorActual;
			document.GenArchEntradaIntForm.txt_FechaSuperior.value = fechaRango2;
		}
		
		return document.GenArchEntradaIntForm.txt_FechaSuperior.value;
	}
	
	
	/*Implementacion Grilla que muestra los archivos que han sido procesados.*/
	/*Funcion DWR que realiza la consulta a la tabla maestroIntercaja.*/
	function consultarDatosGrillaDWR(){
	
		DWREngine.setAsync(false);
		//Esto se debe comentar o borrar y descomentar las de abajo.
		//var fechaInicio = "01/06/2012";
		//var fechaFin = "28/06/2012";

		//var fechaInicio = cargarFechaInferior(); //ANA 20121018
		//var fechaFin = cargarFechaSuperior(); //ANA 20121018
		
		//ANA 20121018 - OBTIENE PRIMER Y ULTIMO DIA DEL MES ACTUAL PARA LA CONSULTA DE ARCHIVOS PROCESADOS
		var fechaInicial = null;
		var fechaFinal = null;
		var fSistema = "<%=session.getAttribute("FechaSistema")%>";
		EntradaIntercajaDWR.obtenerPrimerYUltimoDiaMes(fSistema, function(data){
			
				
			fechaInicial = data.fechaConPimerDiaMes;
			fechaFinal = data.fechaConUltimoDiaMes;
		});	
		
		//FIN ANA20121018
		
		//EntradaIntercajaDWR.obtenerDataArchivosProcesados(fechaInicio, fechaFin, function(data){
		EntradaIntercajaDWR.obtenerDataArchivosProcesados(fechaInicial, fechaFinal, function(data){
		
			datos = data.lisEntradaIntercaja;
			
			document.getElementById("datosNomina").innerHTML = "";
			
			if(datos != null){
				
				cargaDatosEnGrilla();
			}
			
		});
		DWREngine.setAsync(true);
	}
	
	/*funcion que carga los datos en la grilla*/
	var cantidadRegistrosPorPagina = 10;
	var datos = new Array();
	var paginaActual = 1;
	
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
							"<td class='texto' align='left'>"+ dato.nombreArchivo +"</td>"+
							"<td class='texto' align='center'>"+ dato.tipoArchivoGlosa +"</td>"+
							"<td class='texto' align='center'>"+ dato.fechaCarga +"</td>"+
							"<td class='texto' align='center'>"+ dato.statusProcesoGlosa +"</td>"+
	   				 "</tr>";		 
		return texto;
	}
		

	function obtenerHeaderTabla()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Archivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Archivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Carga</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Status Proceso</td>'+
				'</tr>';
	}
	

	/*Función que genera la paginación*/
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
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(paginas);
	}
	
	/*Funcion que carga la cabecera de la grilla.*/
	function cargaCabecera(){
	
		document.getElementById("datosNomina").innerHTML = 	
			'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Archivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Archivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fecha Carga</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Status Proceso</td>'+          		            	    	
	        	'</tr>'+
	        '</table>';
	        
	   //no hace visible el boton reset.
	   document.getElementById("limpiar").style.visibility = "hidden";
	   //document.GenArchSalidaIntForm.btn_Enviar.style.visibility = "hidden";
	}
	/*Fin implementacion grilla.*/

	
	/*invocacion proceso cobol*/
	function InvocarProcesoCobolDWR()
	{
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		DWREngine.setAsync(false);
		var idTipoArchivoLlamada = document.GenArchEntradaIntForm.valorTipoArchivo.value;
		var nombreArchivoLlamada = document.GenArchEntradaIntForm.nombreDelArchivo.value;//construyeNombreArchivo();
		var idMaestroArchivoLlamada = document.GenArchEntradaIntForm.idMaestroArchivo.value;
		var idUsuarioLlamada = "<%=session.getAttribute("IDAnalista")%>";
		/*
		var idTipoArchivoLlamada = "2";
		var nombreArchivoLlamada = "CO072012.txt";
		var idMaestroArchivoLlamada = "42";
		var idUsuarioLlamada = "<%=session.getAttribute("IDAnalista")%>"; 
		*/
		//alert("INVOCANDO PROCESO COBOL");

		EntradaIntercajaDWR.invocarProcesoCobol(idTipoArchivoLlamada, nombreArchivoLlamada, idMaestroArchivoLlamada, idUsuarioLlamada, function(data)
		{
			var retorno = null;
			var idLogAS400Ret = null;
			
			retorno = data.codResultado;			
			document.GenArchEntradaIntForm.logAS400.value = data.idLogAS400;
			
			idLogAS400Ret = document.GenArchEntradaIntForm.logAS400.value;
			
			if(retorno == 0)
			{
				//obtenerCabeceraDetalleArchivo();
				obtenerCabeceraDetalleArchivoDespuesCobol();
				estadisticaTipoArchivoProcesoCobol();
				consultarDatosGrillaDWR();
				
				/*Modificacion para flujo 4*/
				if((idTipoArchivoLlamada == 4) || (idTipoArchivoLlamada == 6)){
					
					cargarDatosEstadisticosDos();									
					//enviarArchivoFtp();
					transferirArchivoToAS400();
					limpiarCampos2();
				}else{
				
					cargaDatosEstadisticos();
					//enviarArchivoFtp();
					transferirArchivoToAS400();
					limpiarCampos2();
				}
			
			}else{
			
				if(retorno == 99){
				
					llamarFuncionErrorLog();
					enviarMail();
					//eliminarArchivoServer();
				}else{
					
					llamarFuncionErrorSinLog();
					//eliminarArchivoServer();
				}		
			}
			
			eliminarArchivoServer();
			limpiarCampos2();	
		});
		
		DWREngine.setAsync(true);
		document.getElementById("pantallaDeCarga").style.visibility = "hidden";
		var variable = "";
	}
	
	
	/*funciones que construyen cuadro informativo con data procesada por Cobol*/
	var datosInterc = new Array();
	
	function estadisticaTipoArchivoProcesoCobol()
	{
		DWREngine.setAsync(false);
		var idTipoArchivo = document.GenArchEntradaIntForm.valorTipoArchivo.value;
		var idMaestroArchivo = document.GenArchEntradaIntForm.idMaestroArchivo.value;
		//var idTipoArchivo = 3;
		
		EntradaIntercajaDWR.estadisticaTipoArchivoprocesadoCobol(idTipoArchivo, idMaestroArchivo, function(data)
		{
			var resp = data.codResultado;
			
			if(resp != 0){
				alert("Ha ocurrido un error.");
				
			}else{
				document.GenArchEntradaIntForm.totalRegProc.value = data.totalRegProcesados;
				document.GenArchEntradaIntForm.totalRegAplNeg.value = data.totalRegAplNegocio;
				document.GenArchEntradaIntForm.totalRegPend.value = data.totalRegPendientes;
				//alert("Se han contabilizado los registros.");
			}
		});
		DWREngine.setAsync(true);
	}
	
	/*Construccion pantalla informativa de estadisticas.*/
	function obtenerHeaderEstadistica(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>Se ha procesado con éxito el archivo.</b></p>' + 
						'<p align="left"> 1.- Detalle de procesamiento.</p>'+
					'</td>'+
				'</tr>';
	}
	
	function esconderDiv()
	{
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
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Aceptar" onclick="esconderDiv();"/>'+
					'</td>'+
				'</tr>';
	}
	
	function obtenerCabeceraDetalleArchivo()
	{
		DWREngine.setAsync(false);
		//var nombreArchivo = "225REC0612";
		var nombreArchivo = document.GenArchEntradaIntForm.nombreDelArchivo.value;
		
		EntradaIntercajaDWR.obtenerCabeceraDetalleFile(nombreArchivo, function(data){
			var resp = null;
			resp = data.codResultado;
			if(resp == 0){
				document.GenArchEntradaIntForm.idMaestroArchivo.value = data.idMaestroArchivo;
				document.GenArchEntradaIntForm.glosaIdTipoArchivo.value = data.glosaIdTipoArchivo;
				document.GenArchEntradaIntForm.glosaStatusProceso.value = data.glosaStatusProceso;
				document.GenArchEntradaIntForm.fechaProcesoDetalle.value = data.fechaProceso;
			}else{
				alert("Ocurrió un error al consultar por detalle de archivo.");
			}
		});
		DWREngine.setAsync(true);
	}

	function obtenerCabeceraDetalleArchivoDespuesCobol()
	{
		DWREngine.setAsync(false);
		//var nombreArchivo = "225REC0612";
		var nombreArchivo = document.GenArchEntradaIntForm.nombreDelArchivo.value;
		var idMaestroArchivo = document.GenArchEntradaIntForm.idMaestroArchivo.value;
		
		EntradaIntercajaDWR.obtenerCabeceraDetalleFileCobol(nombreArchivo, idMaestroArchivo, function(data){
			var resp = null;
			resp = data.codResultado;
			if(resp == 0){
				document.GenArchEntradaIntForm.idMaestroArchivo.value = data.idMaestroArchivo;
				document.GenArchEntradaIntForm.glosaIdTipoArchivo.value = data.glosaIdTipoArchivo;
				document.GenArchEntradaIntForm.glosaStatusProceso.value = data.glosaStatusProceso;
				document.GenArchEntradaIntForm.fechaProcesoDetalle.value = data.fechaProceso;
			}else{
				alert("Ocurrió un error al consultar por detalle de archivo.");
			}
		});
		DWREngine.setAsync(true);
	}	
	
	/*Obtiene la cabecera de un archivo desde maestro intercaja en caso de que el proceso haya resultado fallido. (status = 2).*/
	function obtenerCabeceraDetalleArchivoFallido()
	{
		DWREngine.setAsync(false);
		//var nombreArchivo = "225REC0612";
		var nombreArchivo = document.GenArchEntradaIntForm.nombreDelArchivo.value;
		var statusProceso = 1;
		
		EntradaIntercajaDWR.obtenerCabeceraDetalleFileFallido(nombreArchivo, statusProceso, function(data){
			var resp = null;
			resp = data.codResultado;
			if(resp == 0){
				document.GenArchEntradaIntForm.idMaestroArchivo.value = data.idMaestroArchivo;
				document.GenArchEntradaIntForm.glosaIdTipoArchivo.value = data.glosaIdTipoArchivo;
				document.GenArchEntradaIntForm.glosaStatusProceso.value = data.glosaStatusProceso;
				document.GenArchEntradaIntForm.fechaProcesoDetalle.value = data.fechaProceso;
			}else{
				alert("Ocurrió un error al consultar por detalle de archivo.");
			}
		});
		DWREngine.setAsync(true);
	}
	function obtenerFilaTablaEstadistica(dato)
	{
		//var nombreDelArchivo = "225REC0612";
		var nombreDelArchivo = document.GenArchEntradaIntForm.nombreDelArchivo.value;
		var tipoArchivo = document.GenArchEntradaIntForm.glosaIdTipoArchivo.value;
		var fechaProceso = "<%=session.getAttribute("FechaSistema")%>";
		var statusproceso =	document.GenArchEntradaIntForm.glosaStatusProceso.value;
		var texto =  "<tr>"+
							"<td class='texto' align='left'>" + " " + "Nombre Archivo" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_nomFile' id='txt_nomFile' value='" + nombreDelArchivo + "' disabled='true' size='35'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+		
							"<td class='texto' align='left'>" + " " + "Tipo Archivo" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_tipFile' id='txt_tipFile' value='" + tipoArchivo + "' disabled='true' size='35'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left'>" + " " + "Fecha Proceso" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_fecProc' id='txt_fecProc' value='" + fechaProceso + "' disabled='true' size='35'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left'>" + " " + "Status Proceso" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_statProc' id='txt_statProc' value='" + statusproceso + "' disabled='true' size='35'/>"+
							"</td>"+																					
	   				 "</tr>";		 
		return texto;
	}
	
	function obtenerSegundaCabecera(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="left"> 2.- Detalle de Registros Procesados.</p>'+
					'</td>'+
				'</tr>';
	}
	
	function obtenerCantidadRegistros(){
		var totalRegProc = document.GenArchEntradaIntForm.totalRegProc.value;
		var totalAplNego = document.GenArchEntradaIntForm.totalRegAplNeg.value;
		var totalRegPend = document.GenArchEntradaIntForm.totalRegPend.value;
		
		var texto = "<tr>"+
							"<td class='texto' align='left'>" + "Registros Procesados" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_regProc' id='txt_regProc' value=" + totalRegProc + " disabled='true'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Registros Apl. Neg." + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_regAplNeg' id='txt_regAplNeg' value=" + totalAplNego + " disabled='true'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Registros Pendientes" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_regPend' id='txt_regPend' value=" + totalRegPend + " disabled='true'/>"+
							"</td>"+
					   "</tr>";
		return texto;			   		
	}
	
	var cantidadRegistros = 1;
	function cargaDatosEstadisticos()
	{
		var contenidoTablaEstadistica = "";
		
		for(var i=0;i<4;i++)
		{
			if( i < cantidadRegistros )
				
				contenidoTablaEstadistica = contenidoTablaEstadistica + obtenerFilaTablaEstadistica(datos[i]);
		}
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = obtenerHeaderEstadistica() + contenidoTablaEstadistica + obtenerSegundaCabecera() + obtenerCantidadRegistros() + obtenerHeaderCancelarEstadistico() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}

	/*Para FLUJO 4 Y ARCHIVO DE ESTADISTICAS*/
	
	function obtenerCantidadRegistrosDos(){
		
		var totalRegProc = document.GenArchEntradaIntForm.totalRegProc.value;
		var texto = "<tr>"+
						"<td class='texto' align='left'>" + "Registros Procesados" + "</td>" +
						"<td class='texto' align='left'>" +
							"<input type='text' name='txt_regProc' id='txt_regProc' value=" + totalRegProc + " disabled='true'/>"+
						"</td>"+
					 "</tr>";
		return texto;		
	}
	
	function cargarDatosEstadisticosDos(){
		
		var contenidoTablaEstadisticaDos = "";
		
		for(var i=0; i<4; i++){
		
			if(i<cantidadRegistros){
				contenidoTablaEstadisticaDos = contenidoTablaEstadisticaDos + obtenerFilaTablaEstadistica(datos[i]);
			}
		}	
		
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = obtenerHeaderEstadistica() + contenidoTablaEstadisticaDos + obtenerSegundaCabecera() + obtenerCantidadRegistrosDos() + obtenerHeaderCancelarEstadistico() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";

	}
	
	/*Fin funciones informativas*/
	
	/*Funciones que informan error cuando viene idLogAS400.*/
	function obtenerHeaderErrorLog(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>Se ha producido un error en el procesamiento del archivo.</b></p>' + 
					'</td>'+
				'</tr>';
	}

	function obtenerIdLogAS400(){
		//var idLogAS400Retorno = document.GenArchEntradaIntForm.logAS400.value;
		var texto = "<tr>" +
						'<td colspan="3" width="100%">'+
							'<p align="center"> Se ha enviado un correo al Administrador del Sistema.</p>'+
						'</td>'+
					"</tr>";
		return texto;				
	}	
	
	function obtenerHeaderCancelarCodigoError()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+	
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_AceptarErr" id="btn_AceptarErr" class="btn_limp" value="Aceptar" onclick="esconderDivError();"/>'+
					'</td>'+
				'</tr>';
	}
	
	function esconderDivError()
	{
		document.getElementById("datosCodigoError").style.display = "none";
		document.getElementById("fondoNegroLog").style.visibility = "hidden";
	}
	
	var cantidadRegLogError = 1;
	function llamarFuncionErrorLog(){
	
		var contenidoTablaLogError = "";
		
		for(var i=0;i<1;i++)
		{
			if( i < cantidadRegLogError )
				
				contenidoTablaLogError = contenidoTablaLogError + obtenerIdLogAS400();
		}
		document.getElementById("datosCodigoError").style.display = "";
		document.getElementById("datosCodigoError").innerHTML = obtenerHeaderErrorLog()+ contenidoTablaLogError + obtenerHeaderCancelarCodigoError() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegroLog").style.visibility = "visible";
		document.getElementById("datosCodigoError").style.visibility = "visible";
	}
	/*Fin funciones que informan error con idLogAS400.*/
	
	/*Funciones que informan error sin idLogAS400*/
	function llamarFuncionErrorSinLog(){
	
		document.getElementById("datosCodigoSinError").style.display = "";
		document.getElementById("datosCodigoSinError").innerHTML = obtenerHeaderErrorLog()+ obtenerHeaderCancelarCodigoSinError() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegroSinLog").style.visibility = "visible";
		document.getElementById("datosCodigoSinError").style.visibility = "visible";
	}
	
	function obtenerHeaderCancelarCodigoSinError()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+	
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_AceptarErr" id="btn_AceptarErr" class="btn_limp" value="Aceptar" onclick="esconderDivSinError();"/>'+
					'</td>'+
				'</tr>';
	}

	function esconderDivSinError()
	{
		document.getElementById("datosCodigoSinError").style.display = "none";
		document.getElementById("fondoNegroSinLog").style.visibility = "hidden";
	}	
	/*Fin funciones de error sin idLogAS400*/
	
	/*Nueva.- funcion que envia correo de existir un error.*/
	function enviarMail(){
		
		DWREngine.setAsync(false);
		var idLogAS400Retorno = document.GenArchEntradaIntForm.logAS400.value;

		EntradaIntercajaDWR.enviarMailIntercaja(idLogAS400Retorno, function(data){
	
			resp = data;
			
			if (resp == "OK"){

				//alert("Se ha enviado un mensaje indicando el error en el archivo.");
								
			}else{
				alert("No se pudo enviar un mensaje indicando el error.");
			}
		});
		DWREngine.setAsync(true);
	}
	
	/*Aqui se debe implementar una funcion DWR que vaya al WAS y guarde la ruta del archivo, y de ser posible el nombre*/
	function buscarRutaArchivoServer()
	{
		DWREngine.setAsync(false);
		
		EntradaIntercajaDWR.buscarRutaArchivo(function(data)
		{
			
			var resp = null;
			
			resp = data.codResultado;

			if(resp == 0)
			{
				if(data.rutaArchivo == null)
				{
					document.GenArchEntradaIntForm.rutaDelArchivo.value = "";
					alert("Debe ingresar un archivo .txt perteneciente al proceso de Intercaja.");
					return false;
				}else{
					document.GenArchEntradaIntForm.rutaDelArchivo.value = data.rutaArchivo;
					document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value = document.GenArchEntradaIntForm.rutaDelArchivo.value
					return true;
				}
			}else{
				alert("Archivo no encontrado.");
				return false;
			}
		});
		DWREngine.setAsync(true);
		
		//document.GenArchEntradaIntForm.theFile.disabled = true;
	}
	
	/*Funcion que elimina el archivo del was, una vez que este fue transferido a la carpeta historica.*/
	function eliminarArchivoServer(){
		
		//document.GenArchEntradaIntForm.theFile.disabled = false;
		var rutaArchivoServer = document.GenArchEntradaIntForm.rutaDelArchivo.value;
		//alert("ruta del archivo: " + rutaArchivoServer);
		
		EntradaIntercajaDWR.deleteFileServer(rutaArchivoServer, function(data){
			
			var resp = null;
			
			resp = data;
			
			/*if(resp == "Ok"){
			
				//alert("Archivo eliminado con éxito.");
			}
			else{
				//alert("Archivo no se ha podido eliminar.");
			}*/	
		});
	}
	
	function obtenerRangoParaIntercaja()
	{
		DWREngine.setAsync(false);
		
		EntradaIntercajaDWR.obtenerRangoIntercaja(function(data)
		{
			var resp = null;
			
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.GenArchEntradaIntForm.rangoIntercajaUno.value = data.rangoUno;
				document.GenArchEntradaIntForm.rangoIntercajaDos.value = data.rangoDos;
				document.GenArchEntradaIntForm.rangoIntercajaTres.value = data.rangoTres;

				/*document.GenArchEntradaIntForm.rangoIntercajaUno.value = "05";
				document.GenArchEntradaIntForm.rangoIntercajaDos.value = "25";
				document.GenArchEntradaIntForm.rangoIntercajaTres.value = "25";*/
			}else{
			
				alert("Ocurrió un problema al obtener el rango de Intercaja.");
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/* Validacion para boton cancelar. Si se ha subido un archivo al server y no presionas procesar pero si presionas boton volver, se debe eliminar el archivo
	 * del server y limpiar el campo ruta archivo.
	 */
	function limpiarServerBotonVolver()
	{
		var filePath = document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value;
	 	
		eliminarArchivoServer();
		document.GenArchEntradaIntForm.txt_nombreArchivoUpload.value = "";
		document.GenArchEntradaIntForm.dbx_tipoArchivoIntercaja.value = 0;
	}
	 
	 function consultarStatusProcesoArchivo(nameFileToProcess, idtypeFileToProcess){
	 	
	 	DWREngine.setAsync(false);
	 	EntradaIntercajaDWR.consultarStatusProceso(nameFileToProcess, idtypeFileToProcess, function(data){
	 		
	 		var resp = null;
	 		resp = data.codResultado;
	 		
	 		if(resp == 0){
	 		
	 			document.GenArchEntradaIntForm.statusDelProceso.value = data.statusProceso;
	 			document.GenArchEntradaIntForm.nombreDelArchivo.value = data.nombreArchivo;
	 		}
	 	});
	 	DWREngine.setAsync(true);
	 }
	 
	 /*Funcion de prueba para eliminar todos los archivos del was*/
	 function eliminarTodosFiles(){
	 	EntradaIntercajaDWR.borrarArchivos(function(data){
	 		var resp = null;
	 		resp = data;
	 		
	 		if(resp == 1){
	 			
	 			alert("Los archivos fueron eliminados correctamente.");
	 		}else{
	 			alert("No se eliminaron los archivos.");
	 		}
	 	});
	 }
</script>
</head>
<body onload="obtenerRangoParaIntercaja();cargarFechaInferior();cargarFechaSuperior();cargaCabecera();buscarRutaArchivoServer();consultarDatosGrillaDWR();">
<html:form action="/genArchEntrada.do" method="post" enctype="multipart/form-data"><!-- target="cajaArena" -->
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="tipoArchivo" value="">
  <input type="hidden" name="valorTipoArchivo" value="">
  <input type="hidden" name="fechaCabecera" value="">
  <input type="hidden" name="fechaFinalMes" value="">
  <input type="hidden" name="flagArchivo" value="true">
  
  <input type="hidden" name="totalRegProc" value="">
  <input type="hidden" name="totalRegAplNeg" value="">
  <input type="hidden" name="totalRegPend" value="">
  
  <input type="hidden" name="idMaestroArchivo" value="0">  
  <input type="hidden" name="glosaIdTipoArchivo" value="">
  <input type="hidden" name="glosaStatusProceso" value="">
  <input type="hidden" name="fechaProcesoDetalle" value="">  
  <input type="hidden" name="logAS400" value="">
  <input type="hidden" name="nombreDelArchivo" value=" ">
  <input type="hidden" name="rutaDelArchivo" value=" ">
  <input type="hidden" name="rangoIntercajaUno" value=" ">
  <input type="hidden" name="rangoIntercajaDos" value=" ">
  <input type="hidden" name="rangoIntercajaTres" value=" ">
  <input type="hidden" name="statusDelProceso" value=" ">
  <input type="hidden" name="txt_FechaInferior" value=" ">
  <input type="hidden" name="txt_FechaSuperior" value=" ">
           
  <div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 720px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
 	<div id="datosEstadisticas" style="display: none; position: absolute; width: 450px; margin-top: 110px; margin-left: 300px; background-color: #F2F2F2">
	</div>
  </div>
  
  <div id="fondoNegroLog" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 720px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
 	<div id="datosCodigoError" style="display: none; position: absolute; width: 450px; margin-top: 110px; margin-left: 300px; background-color: #F2F2F2">
	</div>
  </div>

  <div id="fondoNegroSinLog" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 720px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
 	<div id="datosCodigoSinError" style="display: none; position: absolute; width: 450px; margin-top: 110px; margin-left: 300px; background-color: #F2F2F2">
	</div>
  </div>  
  
  <div id="caja_registro">
	  <table width="970">
		<tr>
			<td align="right">
				<!-- <a href="#" align="right" onclick="enviaFormulario(1);">Volver</a> -->
				<a href="#" align="right" onClick="limpiarServerBotonVolver();enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="limpiarServerBotonVolver();enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>PROCESAMIENTO ARCHIVOS DE ENTRADA DESDE INTERCAJA</p1></strong></td>
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
		<!-- 
		<tr>
			<td>
				<p><p2></p>
			<p></p>
			<p>1. Periodo de cierre de mes en curso válido.<p2></p>
			<p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td>
							<strong>Periodo desde el </strong>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="txt_FechaInferior" id="txt_FechaInferior" maxlength="12" size="10" disabled="true" onChange="cargarFechaInferior();"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<strong>hasta el </strong>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="txt_FechaSuperior" id="txt_FechaSuperior" maxlength="12" size="10" disabled="true" onChange="cargarFechaSuperior();">
						</td>
						<td width="16%">							
						</td>
						<td></td>
						<td colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
		-->
		<!-- <tr>
			<td>
				<p><p2></p>
			<p></p>
			</br>
			<p>1. Selección de Archivo a procesar.<p2></p>
			<p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td>
							<strong>Periodo desde el </strong>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type=file name="theFile" id="theFile">
							<input type="button" name="btn_Upload" id="btn_Upload" class="btn_limp"	value="Subir" onClick="enviaFormulario(2);buscarRutaArchivoServer();" />
							
						</td>
						<td>
						</td>
						<td></td>
						<td colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr> -->		
		<tr>
			<td>
			<p><p2></p>
			<p></p>
			<p></p>
			</br>
			<p>2. Datos de Procesamiento.<p2></p>
			<p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Ruta Archivo *</strong></td>
					<td width="16%">
						<input type="text" name="txt_nombreArchivoUpload" id="txt_nombreArchivoUpload" disabled="true" size="50"/>
						<!-- <input type=file name="archivoupload" id="archivoupload"> -->
						<!-- <input type="text" name="txt_rutaArchivo" id="txt_rutaArchivo" maxlength="12" onkeypress="Upper(this,'A')" size="23" /> -->
					</td>
					<td>
						<input type="reset" id="limpiar" name="limpiar" value="Limpiar datos"/>
						<!-- <input type="button" name="btn_Examinar" id="btn_Examinar" class="btn_limp"	value="Examinar" onClick="comprueba_extension(this.form, this.form.archivoupload.value)" /> -->
					</td>
					<td>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Tipo Archivo *</strong></td>
					<td width="16%">
						<html:select property="dbx_tipoArchivoIntercaja" styleClass="dbx_tipoArchivoIntercaja" value="0">
							<html:option value="0">Seleccione </html:option>
							<c:forEach items="${sessionScope.ListTipoArchivoIntercaja}" var="opcion">
								<c:choose>
									<c:when test="${opcion.codigo != '7'}" >
										<option value="<c:out value='${opcion.codigo}'/>">
											<c:out value='${opcion.glosa}'/>
										</option>
									</c:when>
								</c:choose>
							</c:forEach>
						</html:select>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>					
				</tr>
			</table>
			</td>
		 </tr>
		<tr>
		<td style="font-size:11px;">(*campos obligatorios)</td>
		</tr>
		<tr>
			<td height="37" align="right">
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"	value="Volver" onClick="limpiarServerBotonVolver();enviaFormulario(1);" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Procesar" id="btn_Procesar" class="btn_limp"	value="Procesar" onClick="validaFechaActual()" /> 
								
				<!--&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Invocar" id="btn_Invocar" class="btn_limp"	value="enviar as400" onClick="transferirArchivoToAS400ForProccess();" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Invocar" id="btn_Invocar" class="btn_limp"	value="INVOCAR" onClick="InvocarProcesoCobolDWR();" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Invocar" id="btn_Invocar" class="btn_limp"	value="DELETE" onClick="eliminarTodosFiles();" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_FTP" id="btn_FTP" class="btn_limp"	value="FTP" onClick="enviarArchivoFtp();" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_AS400" id="btn_AS400" class="btn_limp"	value="AS400" onClick="transferirArchivoToAS400();" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_FTP" id="btn_FTP" class="btn_limp"	value="FTP" onClick="enviarArchivoFtp();" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_buscar" id="btn_buscar" class="btn_limp"	value="Buscar" onClick="buscarRutaArchivoServer();" /> 
				&nbsp;&nbsp;&nbsp; 
				<input type="button" name="btn_eliminar" id="btn_eliminar" class="btn_limp"	value="Eliminar" onClick="eliminarArchivoServer();" /> 
				&nbsp;&nbsp;&nbsp; --> 				
			</td>
		</tr>		 	 	
	  </table>
	
	<br/>
	<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 150px;">
	</div>
	
	<br/>
	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 20px;">
	</div>
    <br/>

	<div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 720px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
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
<!-- <iframe src="javascript:''" id="cajaArena" name="cajaArena" style="position: absolute; width: 0px; height: 0px; border: none; " tabindex="-1"></iframe> -->
</body>
</html:html>

