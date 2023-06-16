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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarReporteAFCDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraReporteCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarPlanosAFCDWR.js"></script>
<script type="text/javascript">

	var periodoProcesos = new Array();
	var anios = new Array();
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	function asignaValor(a){

		document.GenRepAreaDivPrevForm.opcion.value = a;
		document.GenRepAreaDivPrevForm.anio.value = document.GenRepAreaDivPrevForm.dbx_anio.value;
		document.GenRepAreaDivPrevForm.mes.value = document.GenRepAreaDivPrevForm.dbx_meses.value;
	}
	
	function enviaFormulario(a){
	
		asignaValor(a);
		document.GenRepAreaDivPrevForm.submit();
	}

	/**Funcion que obtiene el periodo como una concatenacion entre el año y el mes, a partir de la fecha de sistema.*/
	function obtenerPeriodoAnioMes(fechaSistema){
		
		var anioReporte = fechaSistema.substring(6,10);
		var mesReporte = "";
		var periodoReporte;
		var periodoMes = "";
		
		var mesTempReporte = fechaSistema.substring(3,5);
		
		if(parseInt(mesTempReporte) < 10){
			if(parseInt(mesTempReporte) == 1){
				mesReporte = 12;
				anioReporte = parseInt(anioReporte,10) - 1;
				anioReporte = anioReporte.toString();
				periodoReporte = anioReporte + mesReporte;
			}else{
				mesReporte = parseInt(fechaSistema.substring(4,5));
				
				periodoMes = "0" + mesReporte;
				periodoReporte = anioReporte + periodoMes;
			}
		}else{
			mesReporte = parseInt(fechaSistema.substring(3,5));
			periodoReporte = anioReporte + mesReporte;
		}
		
		return periodoReporte;
	}
	

	/**Funcion que genera los planos en formato .dat (txt)*/
	function generarTxt(idFlagTxt,flagReporte,idMaestroSiv){
	
		var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaReporte);
		//var idMaestroSiv = obtenerMaxIdMaestroSivegam(idFlagTxt);
		//eliminar este if, solo para pruebas
		/*if(idMaestroSiv == 0){
			idMaestroSiv = 210;
		}*/
		
		
		//var mesPeriodo = periodo.substring(4,7);
		
		var mesPeriodo = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
		var anioPeriodo = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
		var periodo = anioPeriodo*100+mesPeriodo;
		
		var usser = '<%=session.getAttribute("IDAnalista")%>';
		//var flagReporte = document.GenRepAreaDivPrevForm.flagTipoAFC.value;
		
		if(idMaestroSiv == 0){
			
			alert("No es posible generar el reporte en txt, debido a que no existe información para el periodo seleccionado.");
			return false;
		
		}else{
			DWREngine.setAsync(false);
			GenerarPlanosAFCDWR.generarPlanosAfc(idFlagTxt,flagReporte,periodo,idMaestroSiv,periodo,usser,fechaReporte, function(data){
				var respuesta = null;
				respuesta = data.codRespuesta;
				
				if(respuesta == 0){
					document.GenRepAreaDivPrevForm.rutaReporteTxt.value = data.rutaArchivo;
				}
				
			});
			DWREngine.setAsync(true);
		}		
	}
	
	/** Funcion que genera el reporte en archivo .xls usando las librerías de jasperReport.
	 * recibe como parametro el id del archivo xls que se va a generar el cual debe coincidir con el
	 * establecido en la tabla parametrica.
	*/
	function generarXls(idFlagXls,flagReporte,idMasterSivegam){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoReporte = obtenerPeriodoAnioMes(fechaSistema);
		//var idMasterSivegam = obtenerMaxIdMaestroSivegam(idFlagXls);
		//var mesReporte = periodoReporte.substring(4,7);
		//var mesReporte = "12";
		var usser = '<%=session.getAttribute("IDAnalista")%>';
		
		var mesReporte = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
		var anioReporte = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
		var periodoReporte = anioReporte*100+mesReporte;
		
		if(idMasterSivegam == 0){
			
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{	
			DWREngine.setAsync(false);
			
			var reporte = parseInt(periodoReporte,10);
			
			GenerarReporteAFCDWR.generarReporteXLS(idFlagXls, periodoReporte, idMasterSivegam, reporte, usser, fechaSistema, flagReporte, function(data){
			
				var resp = null;
				
				resp = data.codRespuesta;
				
				if(resp == 0){
					//alert("Reporte generado con éxito.");
					document.GenRepAreaDivPrevForm.rutaReporteXls.value = data.rutaArchivo;
					//enviaFormulario(2);
				}else{
					alert("Reporte no generado");
				}
			});
			
			DWREngine.setAsync(true);
		}	
	}

	/**Funcion que genera el archivo excel de errores.*/
	function generarXlsErrores(idFlagXls, flagReporte, idMasterSivegam){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
				
		var mesReporte = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
		var anioReporte = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
		var periodoReporte = anioReporte*100+mesReporte;
		
		var usser = '<%=session.getAttribute("IDAnalista")%>';
		
		if(idMasterSivegam == 0){
			
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		
		}else{	
			DWREngine.setAsync(false);
			
			var reporte = parseInt(periodoReporte,10);
			
			GenerarReporteAFCDWR.generarReporteErroresXls(idFlagXls, periodoReporte, idMasterSivegam, reporte, usser, fechaSistema, flagReporte, function(data){
				var resp = null;
				resp = data.codRespuesta;
				if(resp == 0){
					document.GenRepAreaDivPrevForm.rutaReporteErr.value = data.rutaArchivo;
				}
			});
			DWREngine.setAsync(true);
		}
	}
	
	/**Funcion que obtiene el ultimo id maestro sivegam cuando se entra a la aplicacion y no se requiere procesar sino
	generar los archivos xls y txt.*/
	
	function obtenerMaxIdMaestroSivegam(idReporte){
	
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';	
		var idMaxMaestroSivegam = 0;
		//var periodoReporte = obtenerPeriodoAnioMes(fechaSistema);
	
		var mes = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
		var anio = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
		var periodoReporte = anio*100 + mes;
		
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.selectMaxIdMaestroSivegam(idReporte, periodoReporte, function(data){
			var consulta = null;
			consulta = data.maestro_sivegam;
			
			if(consulta != null){
				idMaxMaestroSivegam = consulta;
			}	
		});
		DWREngine.setAsync(true);
		
		return idMaxMaestroSivegam;
	}
		
	/*Funcion que setea el valor del idReporte, para saber exactamente cual es el reporte que se quiere generar.*/
	function seteaIdReporte(n){
		
		document.GenRepAreaDivPrevForm.idReporte.value = n;
		
	}
	
	function seteaFlagXls(n){
	
		document.GenRepAreaDivPrevForm.idFlagXLS.value = n;
	}

	function seteaFlagTxt(n){
	
		document.GenRepAreaDivPrevForm.idFlagTXT.value = n;
	}
	
	/*Funcion que obtiene la descripcion del status del proceso. Se carga al inicio del programa y luego se actualiza de acuerdo
	* a lo arrojado por el proceso cobol.
	* Si el valor no se encuentra actualizado en la tabla maestrosivegam se inicializa con el valor pendiente de la tabla
	* de lo contrario, toma el valor de la tabla maestrosivegam (funcion debe ser construida).
	*/
	function obtenerDescStatusProceso(){
		
		DWREngine.setAsync(true);
		
		GenerarReporteAFCDWR.obtenerStatusProceso(function(data){
		
			document.GenRepAreaDivPrevForm.txt_statusProceso_1.value = data.descripcion_status_proceso;		
			document.GenRepAreaDivPrevForm.txt_statusProceso_2.value = data.descripcion_status_proceso;
		
		});
		
		DWREngine.setAsync(false);
	}
	
	/*Funcion que actualiza el status del proceso una vez que se ha ejecutado el proceso cobol.*/
	function actualizaStatus(p){
	
		var botonPresionado = document.GenRepAreaDivPrevForm.idReporte.value;
		switch(parseInt(botonPresionado)){
			case 1:
				document.GenRepAreaDivPrevForm.txt_statusProceso_1.value = p;
				document.getElementById("ref_excel_1").disabled = false;
				document.getElementById("btn_Procesar_1").disabled = false;//FRM CAMBIAR A TRUE
				break;
			
			case 2:
				document.GenRepAreaDivPrevForm.txt_statusProceso_2.value = p;
				document.getElementById("ref_excel_2").disabled = false;
				document.getElementById("btn_Procesar_2").disabled = false;
				break;

			default:
				alert("Ha ocurrido un error y no se ha podido actualizar el estado del proceso.");
		}
				
	}
	
	
	/*Funcion que bloquea los botones excel antes de ejecutar cualquier proceso cobol.*/
	function bloqueaBotonesExcel(){
		var boton1 = document.getElementById("btn_Procesar_1");
		boton1.disabled = false; //FRM CAMBIAR A TRUE
		var boton2 = document.getElementById("btn_Procesar_2");
		boton2.disabled = false; 		
		return false;
	}
	
	/*Funcion que inserta en la tabla maestrosivegam.*/
	function insertToMaestroSivegam(){
		
		var idMasterSivegam;
		var fechaProceso = '<%=session.getAttribute("FechaSistema")%>';
		var usuarioSivegam = '<%=session.getAttribute("IDAnalista")%>';
		var idTipoReporte = document.GenRepAreaDivPrevForm.idReporte.value;
				
		var mes = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
		var anio = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
		var periodo = anio*100 + mes;
					
		//para corroborar que proceso ya fue ejecutado y no volver a ejecutar sino que da opcion de reprocesar.
		var statusPorProceso;
		
		switch(parseInt(idTipoReporte,10)){
			case 8:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			case 9:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			default:
			
				return false;
		}
		
		if(statusPorProceso == 3 || statusPorProceso == 2){
			
			var confirmacion = confirm("Va a ingresar a pantalla de Reprocesamiento. ¿Está seguro desea continuar?");
			
			if(confirmacion == true){
				getPantallaReproceso();
			}	
					
		}else{
			
			GenerarReporteAFCDWR.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodo, function(data){
				
				var idMasterSivegam = null;
				var idFlagFileXls = null;
				var respuesta = null;
				
				respuesta = data.codResultado;
				DWREngine.setAsync(false);
				if(respuesta == 0){
				
					document.GenRepAreaDivPrevForm.idMaestroSivegam.value = data.idMaestroSivegam;
					idMasterSivegam = document.GenRepAreaDivPrevForm.idMaestroSivegam.value;
					invocarProcesoCobol(idMasterSivegam);
					
				}else{
					alert("Se ha producido un error al registrar en Maestro Sivegam");
					
				}
			});
			
			DWREngine.setAsync(true);
		}	
	}

	/**Funcion que obtiene el status del proceso, para verificar si es necesario reprocesar o simplemente */
	var statusProcesoSivegam = 0;
	function obtenerStatusPorPeriodoyTipoReporte(tipoReporte, periodo){
		
		statusProcesoSivegam = 0;
		
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.actualizarStatusSegunPeriodoYProceso(tipoReporte,periodo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			
			if(respuesta == 0){
				statusProcesoSivegam = data.status;
			}else{
				statusProcesoSivegam = 1;
			}
		});
		DWREngine.setAsync(true);
		
		return statusProcesoSivegam;
	}
		
	/**Funcion que actualiza estado luego de una invocacion cobol.*/
	function actualizarEstado(control, tipoReporte){
		
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.actualizacionEstado(control, function(data){
			var resp = null;
			resp = data;
			if(resp != null){
				switch(parseInt(tipoReporte,10)){
					case 8:
						document.GenRepAreaDivPrevForm.txt_statusProceso_1.value = data.descripcion_status_proceso;
						break;
					case 9:
						document.GenRepAreaDivPrevForm.txt_statusProceso_2.value = data.descripcion_status_proceso;
						break;
					default:
						alert("No es posible actualizar el estado del reporte.");
				}					
			}
				
		});
		
		DWREngine.setAsync(true);
	}
		
	/*Funcion que establece comunicacion con un servidor AS400 e invoca al proceso cobol que llena las tablas para el proceso de AFC*/
	function invocarProcesoCobol(idSecuenciaSivegam){
	
		var mes = document.GenRepAreaDivPrevForm.dbx_meses.value;
		var anio = document.GenRepAreaDivPrevForm.dbx_anio.value;
		var idReport = document.GenRepAreaDivPrevForm.idReporte.value; //id se refiere al numero de reporte del cl que se quiere invocar.
		var tipoReporte = document.GenRepAreaDivPrevForm.flagTipoAFC.value;//se refiere al flag para identificar que tipo de reporte es (M o R)
		var flagReproceso = 0;// si es 0 indica que se va a ejecutar proceso por primera vez.
		if(parseInt(mes,10) < 10){
			mes = '0' + mes;
		}
				
		var periodo = anio +  mes;
		
		DWREngine.setAsync(true);	
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
					
		GenerarReporteAFCDWR.llamarProcesoCobolAFC(flagReproceso, idSecuenciaSivegam, idReport, periodo, tipoReporte, function(data){
			var resp = null;
			var statusActualizado;
			resp = data.codResultado;
			if(resp == 3){
			
				actualizarEstado(resp,idReport);
				generarTxt(idReport,tipoReporte,idSecuenciaSivegam);
				generarXls(idReport,tipoReporte,idSecuenciaSivegam);
				generarXlsErrores(idReport,tipoReporte,idSecuenciaSivegam);
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				alert("Proceso Terminado Exitosamente.");
				actualizarEstadoSCInit();
				
			}else{
				if ((resp!=2)&&(resp!=5)){
					resp=2;
				}
				actualizarEstado(resp,idReport);
				GenerarReporteAFCDWR.updateStatusAntesDeReprocesar(idSecuenciaSivegam,resp);
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				alert("Error al procesar el archivo");
				actualizarEstadoSCInit();
			}		
		});
		DWREngine.setAsync(true);
	}
	
	
	
	/**Funcion que llama a la pantalla de carga para enviar el archivo a una maquina AS400*/
	function enviarArchivo(){
		enviaFormulario(3);
	}
	
	/*Funcion que setea el tipo de reporte*/
	function cargaTipoArchivo(valor){
		
		switch(parseInt(valor)){
			case 8:
				document.GenRepAreaDivPrevForm.flagTipoAFC.value = "R";

				break;
			case 9:
				document.GenRepAreaDivPrevForm.flagTipoAFC.value = "M";

				break;
			default:
				document.GenRepAreaDivPrevForm.flagTipoAFC.value = "";	 	
		}
	
	}
	
	/**Funcion que dispara el cobol de entrada una vez recibida la respuesta desde el action.*/
	function disparaProcesoEntrada()
	{
		if(("<%=session.getAttribute("controlCobol")%>" != "") && ("<%=session.getAttribute("controlCobol")%>" != "null"))
		{
			if("<%=session.getAttribute("controlCobol")%>" == "0")
			{
				var flagReporte = '<%=session.getAttribute("identificadorFlagReporte")%>';
				var idReporteAfc = '<%=session.getAttribute("identificadorIdReporte")%>';
				var mes = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
				var anio = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
				var periodo = anio*100+mes;
				invocarCobolEntrada(idReporteAfc,flagReporte,periodo);
				enviaFormulario(6);
			}else if("<%=session.getAttribute("controlCobol")%>" == "9"){
				alert("Hubo un problema al tratar de cargar el archivo seleccionado");
				enviaFormulario(6);
			}
		}
	}
	
	/**Funcion que invoca el proceso cobol de entrada para cargar el archivo*/
	function invocarCobolEntrada(idReporteAfc,flagReporte, periodo){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var usuario = document.GenRepAreaDivPrevForm.txt_Usuario.value;
		var glosaError = "";
		var codigoError = "";		
		//var tipoReporte = document.GenRepAreaDivPrevForm.flagTipoAFC.value;
		//var idReporteAfc = document.GenRepAreaDivPrevForm.idReporte.value;
	//	document.getElementById("pantallaDeCarga").style.visibility = "visible";
					
		DWREngine.setAsync(false);
		//alert('antes de dwr');
		GenerarReporteAFCDWR.invocarProcesoEntradaAfc(idReporteAfc, flagReporte, periodo, fechaSistema, usuario, function(data){
		//alert('despues de drw');			
			var respuesta = null;
			respuesta = data.codResultado;
			if(respuesta != 2){
			
				glosaError = data.descripcionError
				codigoError = data.codError;
				alert("Error. " + glosaError + ". " + "(" + codigoError + ")");
			//	document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					
			}else{
				actualizarEstadoCarga("R");
				actualizarEstadoCarga("M");
			//	document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					
				
			}
		});
		DWREngine.setAsync(true);
	}

	/**Funcion que actualiza Estado carga.*/
	function actualizarEstadoCarga(tipoReporte){
		
		var anio = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
		var mes = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
		var periodo = anio*100 + mes;
		var estado;
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.statusProcesoCarga(periodo, tipoReporte, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){
				estado = data.status;
				estado = parseInt(estado,10);
				if((estado!=1)&&(estado!=2)&&(estado!=3)){
					estado = 1;
				}
			}else{
				estado = 0;
			}
		});
		var label;
		if(tipoReporte == "R"){
			GenerarReporteAFCDWR.statusProcesoCargaGlosa(estado, function(data){
				 label = data;
				});
			 document.GenRepAreaDivPrevForm.txt_estadoCarga1.value = label;
		}else{
			abel = GenerarReporteAFCDWR.statusProcesoCargaGlosa(estado, function(data){
				 label = data;
				});
			 document.GenRepAreaDivPrevForm.txt_estadoCarga2.value = label;
		}
		DWREngine.setAsync(true);
		return label;
	}
	
	
	/*Aqui funcion DWR que verifica si un archivo esta en su respectiva carpeta.*/
	var flagArchivo;
	function verificarArchivoEnServer(){
		
		var idTipoReporte = document.GenRepAreaDivPrevForm.idFlagTxt.value;
		var nombreArch = retornaPeriodoYNombreArchivoTxt(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteArchivoSegunPeriodo(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenRepAreaDivPrevForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;
	}
	
	/*Funcion que retorna el periodo en el nombre del archivo, formado por la eleccion de los combos de mes y año dados por el usuario*/
	function retornaPeriodoYNombreArchivoTxt(codReporte){
		
		var nombreArchivo = "";
		var anioReproceso = document.GenRepAreaDivPrevForm.dbx_anio.value;
		var mesReproceso = document.GenRepAreaDivPrevForm.dbx_meses.value;
		
		if(parseInt(mesReproceso,10) < 10 ){
			mesReproceso = '0' + mesReproceso;
		}
		
		if(anioReproceso.length == 1){
			anioReproceso = '200' + anioReproceso;
		}else{
			if(anioReproceso.length == 2){
				anioReproceso = '20' + anioReproceso;
			}
		}
		
		var periodo = anioReproceso.toString() + mesReproceso.toString();
		
		
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		
		switch(parseInt(codReporte,10)){
			case 8:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.DAT";
				break;
			case 9:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.DAT";
				break;

			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
	/**Funcion que retorna si existe un archivo xls*/
	function verificarExisteXls(){

		var idTipoReporte = document.GenRepAreaDivPrevForm.idFlagXls.value;
		var nombreArch = retornaPeriodoYNombreArchivoXls(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteXLS(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;
			alert(nombreArch);
			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenRepAreaDivPrevForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;	
	}
	
	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoXls(codReporte){
		
		var anioReproceso = document.GenRepAreaDivPrevForm.dbx_anio.value;
		var mesReproceso = document.GenRepAreaDivPrevForm.dbx_meses.value;
		
		if(parseInt(mesReproceso,10) < 10 ){
			mesReproceso = '0' + mesReproceso;
		}
		
		if(anioReproceso.length == 1){
			anioReproceso = '200' + anioReproceso;
		}else{
			if(anioReproceso.length == 2){
				anioReproceso = '20' + anioReproceso;
			}
		}
		
		var periodo = anioReproceso.toString() + mesReproceso.toString();
		
		
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";
		
		switch(parseInt(codReporte,10)){
			case 8:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.XLSX";
				break;
			case 9:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
		
	/*Funcion que verifica si existe resumen*/
	function verificaExisteErrores(){
		
		var idTipoReporte = document.GenRepAreaDivPrevForm.idFlagXls.value;
		var nombreArch = retornaPeriodoYNombreArchivoErr(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteXLS(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;

			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenRepAreaDivPrevForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;	
	}
	
	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoErr(codReporte){
		
		var anioReproceso = document.GenRepAreaDivPrevForm.dbx_anio.value;
		var mesReproceso = document.GenRepAreaDivPrevForm.dbx_meses.value;
		
		if(parseInt(mesReproceso,10) < 10 ){
			mesReproceso = '0' + mesReproceso;
		}
		
		if(anioReproceso.length == 1){
			anioReproceso = '200' + anioReproceso;
		}else{
			if(anioReproceso.length == 2){
				anioReproceso = '20' + anioReproceso;
			}
		}
		
		var periodo = anioReproceso.toString() + mesReproceso.toString();
		
		
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";
		
		switch(parseInt(codReporte,10)){
			case 8:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_ERR.XLSX";
				break;
			case 9:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_ERR.XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
		
	function descargarExcel(){
		
		var fg = verificarArchivoEnServer();
		if(fg == 0){
			enviaFormulario(3);	//verificar esto en el action FRM
		}else{
			alert("El archivo aun no se ha generado.");
		}		
	}
	
	function descargarDat(){
		
		var fg = verificarArchivoEnServer();
		if(fg == 0){
			enviaFormulario(3);	
		}else{
			alert("El archivo aun no se ha generado.");
		}
	}
	
	function descargarErrores(){
		
		var fg = verificarArchivoEnServer();
		if(fg == 0){
			enviaFormulario(3);	
		}else{
			alert("El archivo aun no se ha generado.");
		}
	}
	
	function insertarArchivo(flagTipoReporte){
		
		//var flagTipoReporte = "M";
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.insertarReporte(flagTipoReporte ,function(data){
			var resp = null;
			resp = data.codRespuesta;
			if(resp == 0){
				alert("Ok");
			}else{
				alert("Error al realizar insert en aff01.");
			}
		});
		DWREngine.setAsync(true);
	}
	
	/**Funcion que elimina un archivo del servidor, cuando se presiona reprocesar.*/
	function eliminarArchivo(codReporte){
	
		var nombreArch = retornaPeriodoYNombreArchivoTxt(codReporte);
				
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteArchivoServer(codReporte,nombreArch, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){
				//alert("Archivo eliminado con éxito");
			}
		});
		DWREngine.setAsync(true);
		
	}
	
	function eliminarArchivoXls(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoXls(codReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteXlsServer(codReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){
				//alert("Archivo xls eliminado");
			}	
		});
		DWREngine.setAsync(true);
		
	}
	
	/**Funcion que elimina archivo xls del tipo resumen.*/
	function eliminarResumenes(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoErr(codReporte);
		var codReporteMod = 2 + codReporte;
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteXlsServer(codReporteMod, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){
				//alert("Archivo xls eliminado");
			}	
		});
		DWREngine.setAsync(true);
	}
	
	function reprocesarDesdeOrigen(codReporte, tipoReporteFlag){
		/**Eliminar archivos xls, txt y xls de errores.
		* Incocar proceso cobol.
		*/
	}
	
	function ReprocesarDesdeArchivo(){
		/**Eliminar archivo txt y xls de errores*/
		/**llamar a funcion insertarArchivo()*/
	}

	/******FUNCIONES ANEXAS, QUE CARGAN COMBOS DE MES Y AÑO. **************/

	/**Funcion que carga los meses en el combo de meses.*/
	/**Objeto que sirve de ayuda para guardar los meses del año.*/
	function PerProceso(periodo_proceso, descripcion_periodo_proceso){
		this.periodo_proceso = periodo_proceso;
		this.descripcion_periodo_proceso = descripcion_periodo_proceso;
	}
	
	/**Funcion objeto que contendrá el arreglo de anios.*/
	function periodoAnios(codigo_anio,glosa_anio){
		this.codigo_anio = codigo_anio;
		this.glosa_anio = glosa_anio;
	}
	
	/*Funcion que carga el arreglo de meses*/
	function cargarArregloPeriodoProceso(){
		
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.obtenerDataPeriodoProceso("PeriodoProceso", function(data){
			var periodo = null;
			periodo = data;
			
			for(var i=0; i<periodo.length; i++){
			
				periodoProcesos[i] = new PerProceso(periodo[i].periodo_proceso, periodo[i].descripcion_periodo_proceso);
			}
		});
		DWREngine.setAsync(true);
	}
	
	/**funcionn que obtiene los datos y carga el combo de meses.*/
	function obtenerComboPeriodoProceso(){
		//var cmb = document.getElementById("dbx_meses");
		var cmb = document.GenRepAreaDivPrevForm.dbx_meses;
		cmb.options.length = 0;
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<periodoProcesos.length; j++){
			
			var item = null;
			item = periodoProcesos[j];
			
			cod = item.periodo_proceso;
			txt = item.descripcion_periodo_proceso;
			
			cmb.options[j+1] = new Option(txt,cod);
		}
	}
	
	/**FunciON que asigna el valor del combo.*/
	function selectedItemCombo(){
		var anio = '<%=session.getAttribute("anio")%>';
		if(anio == 'null' ){
			var dateSistema = '<%=session.getAttribute("FechaSistema")%>';
			anio = parseInt(dateSistema.substring(6,10),10);
			document.GenRepAreaDivPrevForm.dbx_anio.value = anio;
			var mes = parseInt(dateSistema.substring(3,5),10);
			//document.GenRepAreaDivPrevForm.dbx_meses.value = mes-1;
			//se modifica para que muestre el mes actual a procesar
			document.GenRepAreaDivPrevForm.dbx_meses.value = mes;
			var periodo = anio*100 + mes;
		}else{
			document.GenRepAreaDivPrevForm.dbx_anio.value = anio;
			var mes = '<%=session.getAttribute("mes")%>';
			document.GenRepAreaDivPrevForm.dbx_meses.value = parseInt(mes,10);			
		}	
	}
	
	function valorAnterior(){
		if(document.GenRepAreaDivPrevForm.anio.value == ""){
			document.GenRepAreaDivPrevForm.dbx_anio.value = "0";
		}else{
			document.GenRepAreaDivPrevForm.dbx_anio.value ='<%=request.getAttribute("anio")%>';
		}
		if(document.GenRepAreaDivPrevForm.mes.value == ""){
			document.GenRepAreaDivPrevForm.dbx_mes.value = "0";
		}else{
			document.GenRepAreaDivPrevForm.dbx_mes.value ='<%=request.getAttribute("mes")%>';
		}
	}
	
	/**Funcion que carga arreglo de las parametricas.*/
	function cargarArregloParametricas(){
		
		cargarArregloPeriodoProceso();
		cargarArregloAniosProceso();
	}
	
	/*Funciones que construyen el combo de años y carga la data en dicho campo.*/
	/*Funcion que carga un arreglo con años, desde el 2000 hasta el 2099*///FRM
	var anio_global = 0;
	function cargarArregloAniosProceso(){
		
		var dateSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioSist = parseInt(dateSistema.substring(6,10),10);

		var cmb = document.GenRepAreaDivPrevForm.dbx_anio;
		
		cmb.options[0] = new Option("Seleccione",0);		
		
		var anioConta = anioSist - 2000;
		var j=2000;
			for(var i=0; i< (anioConta+1); i++){
				
				j=2000+i;
				
				cod = j;
				txt = j;
				
				if(anioSist == txt){
					anio_global = i;
				}
					
				cmb.options[i+1] = new Option(txt,cod);
			}

	}
	
	/**Funcion que obtiene el combo con años*/
	function obtenerComboAnios(){
		
		var cmb = document.GenRepAreaDivPrevForm.dbx_anio;
		cmb.options.length = 0;
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var i=0; i<anios.length; i++){
			
			var item = null;
			item = anios[i];
			
			cod = item.codigo_anio;
			txt = item.glosa_anio;
			
			cmb.options[i+1] = new Option(txt,cod);	
		}	
	}	
	

	/**********************************************************************/

	/**********************************************************************/
	
	/** PANTALLA DE ELECCION PARA ELECCION DE REPROCESO.**/
	/*Construccion de mini pantalla para reproceso.*/
	function getCabeceraPantalla(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center" style="color: blue"><b>Pantalla de Reproceso.</b></p>' + 
						'<p align="center"> Elija la opción que desea ejecutar.</p>'+
					'</td>'+
				'</tr>';
	}
	
	function getCuerpoPantalla(){
	
		var texto = "<table width='100%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>"+ 
						"<tr>"+
								"<td class='texto' align='left'>"+
									"<dd><dd>"+
									"<input type='radio' name='rbt_reprocesamiento' id='rbt_desdeOrigen' value='1'>Desde el origen<br>"+
									"<br>"+
									"<dd><dd>"+
									"<input type='radio' name='rbt_reprocesamiento' id='rbt_desdeExcel' value='2'>Desde Archivo EXCEL<br>"+
									"<br>"+
								"</td>"+
						"</tr>"+
					"</table>";
		return texto;			
	}
	
	function getBarraInferiorPantalla(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_ejecutar" id="btn_ejecutar" class="btn_limp" value="Ejecutar" onClick="ejecutarReproceso();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_cancelar" id="btn_cancelar" class="btn_limp" value="Cancelar" onclick="esconderDivReproceso();"/>'+
					'</td>'+
				'</tr>';
	}
	
	function esconderDivReproceso()
	{
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}
	
	function getPantallaReproceso(){
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = getCabeceraPantalla() + getCuerpoPantalla() + getBarraInferiorPantalla() + "<tr></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	
	
	/*Funcion que interpreta eleccion del usuario (radiobutton) y envia la funcionalidad requerida.*/
	var seleccionCheck = 0;
	function ejecutarReproceso(){
		var opcionSeleccionada;
		var tipoReporte = document.GenRepAreaDivPrevForm.idReporte.value;

		var idMaestroSivegam = obtenerMaxIdMaestroSivegam(tipoReporte);
	    var valueRadioButton = document.GenRepAreaDivPrevForm.rbt_reprocesamiento.length;
	    
	    for (var i=0; i < valueRadioButton; i++){ 
	       if (document.GenRepAreaDivPrevForm.rbt_reprocesamiento[i].checked){
	          	 opcionSeleccionada = document.GenRepAreaDivPrevForm.rbt_reprocesamiento[i].value;
	          	 seleccionCheck = opcionSeleccionada;   	 
	       }   	 
	    }
	    
	    switch(parseInt(opcionSeleccionada,10)){
	    	case 1:
	    		//alert("Reprocesar");
	    		//eliminar archivo excel, txt y errores. 
	    		//TODO falta agregar parametros de entrada a estas funciones (idReporte) para identificar a que reporte se va a eliminar.
	    		esconderDivReproceso();
	    		eliminarArchivoXls(tipoReporte);
	    		eliminarArchivo(tipoReporte);
	    		eliminarResumenes(tipoReporte);
	    		//hacer update a status de la tabla maestro sivegam, usando idMaestroSivegam
	    		realizarUpdateStatusAntesDeReprocesar(idMaestroSivegam);
				break;
	    	case 2:
	    		//eliminar archivo txt y errores.
	    		esconderDivReproceso();
	    		//eliminarArchivoXls(tipoReporte);
	    		//eliminarArchivo(tipoReporte);
	    		//eliminarResumenes(tipoReporte);
	    		//llamar pantalla carga y enviar archivo excel a carpeta correspondiente.
	    		enviaFormulario(4);
	    		break;
	    	default:
	    		alert("Ocurrió un error, favor vuelva a ejecutar la acción.");		
	    }	
	}
	
	/**Funcion que hace el update a la tabla maestro sivegam, antes de reprocesar, enviando el status 4, para saber que esta en ejecucion.*/
	function realizarUpdateStatusAntesDeReprocesar(id)
	{
		var statusReproceso = 4;
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.updateStatusAntesDeReprocesar(id, statusReproceso, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				invocarProcesoCobol(id);
			}else{
				alert("Ha habido un error interno y no es posible reprocesar.");
			}
		});
		DWREngine.setAsync(true);
	}	
		
	/*********** FIN PANTALLAS DE REPROCESO **************/
	function devolucion(){
		if('<%=session.getAttribute("controlCobolReproceso")%>' != "" && '<%=session.getAttribute("controlCobolReproceso")%>' != "null")
		{
			if("<%=session.getAttribute("controlCobolReproceso")%>" == "0")
			{
				var tipoReporte = '<%=session.getAttribute("idReporteReproceso")%>';
				var idMaestroSivegam = '<%=session.getAttribute("idSivegamReprocesoAFC")%>';
				var flagReporte = '<%=session.getAttribute("idFlagReporteReproceso")%>';
				var mes = document.GenRepAreaDivPrevForm.dbx_meses.value;
				var anio = document.GenRepAreaDivPrevForm.dbx_anio.value;
				
				if(parseInt(mes,10) < 10){
					mes = '0' + mes;
				}
				
				
				periodo = anio +  mes;
				invocarCobolValidacion(idMaestroSivegam,tipoReporte,periodo,flagReporte);
				//SE LIMPIAN LAS VARIABLES DE SESION PARA QUE AL REINGRESAR EN LA APLICACION NO SE EJECUTE NUEVAMENTE LA FUNCION.
				enviaFormulario(5);
			}
		}
	}
	
	/**Funcion que realiza un borrado lógico de las tablas de cesantia, cuando se requiere corregir data desde archivo excel.*/
	function limpiarTablaInsercion(tipoReporte){
		
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.deleteLogicoSegunReporteAFC(tipoReporte, function(data){
			var resp = null;
			resp = data.codRespuesta;
			if(resp == 0){
				alert("Borrado logico realizado con éxito.");
			}
		});
		DWREngine.setAsync(true);	
	}
	
	function invocarCobolValidacion(idMaestroSivegam, idReporte, periodo, flagReporte){
	
		DWREngine.setAsync(false);
		var flagReproceso = 1; //si es 1 indica que el proceso se va a reprocesar
		
		document.getElementById("pantallaDeCarga").style.visibility = "visible";		
		generarTxt(idReporte,flagReporte,idMaestroSivegam);
		generarXlsErrores(idReporte,flagReporte,idMaestroSivegam);
		document.getElementById("pantallaDeCarga").style.visibility = "hidden";
		//gc();		
			
		DWREngine.setAsync(true);
	}
	
	/**Funciones que actualizan campo estado al iniciar la pantalla.*/
	function actualizarEstadoSCInit(){

		var mes = document.GenRepAreaDivPrevForm.dbx_meses.value;
		var anio = document.GenRepAreaDivPrevForm.dbx_anio.value;
		if(mes == 0 || anio == 0){
			selectedItemCombo();
			alert('Seleccione un mes y año antes de procesar.');
		}else{
		
		
			var tipoReporte = 8;
			
			while(tipoReporte <=9)
			{
				var anio = parseInt(document.GenRepAreaDivPrevForm.dbx_anio.value,10);
				var mes = parseInt(document.GenRepAreaDivPrevForm.dbx_meses.value,10);
				var periodo = anio*100 + mes;
				
				var statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(tipoReporte,periodo);
					
				DWREngine.setAsync(false);
				GenerarReporteAFCDWR.actualizacionEstado(statusPorProceso, function(data){
					var resp = data;
					if(resp != null)
					{
						switch(parseInt(tipoReporte,10))
						{
							case 8:
								if(data.descripcion_status_proceso == null){
									document.GenRepAreaDivPrevForm.txt_statusProceso_1.value = "PENDIENTE";
									bloqueaLinkPorId(8);
								}else{
									document.GenRepAreaDivPrevForm.txt_statusProceso_1.value = data.descripcion_status_proceso;
								}	
								break;
							case 9:
								if(data.descripcion_status_proceso == null){
									document.GenRepAreaDivPrevForm.txt_statusProceso_2.value = "PENDIENTE";
									bloqueaLinkPorId(9);
								}else{
									document.GenRepAreaDivPrevForm.txt_statusProceso_2.value = data.descripcion_status_proceso;
								}
								break;
							default:
								alert("No es posible actualizar el estado del reporte.");
						}
					}
				});
				DWREngine.setAsync(true);
				if(statusPorProceso == 3){
					desbloquearLinks(tipoReporte);
				}else{
					bloqueaLinkPorId(tipoReporte);
				}	
				tipoReporte++;
			}	
			actualizarEstadoCarga("R");
			actualizarEstadoCarga("M");
			bloqueaBotonProcesar();
		}
	}

	/*Funcion que desbloquea los links de excel, txt y ewrrores.*/
	function desbloquearLinks(tipoReporte){
		switch(parseInt(tipoReporte,10)){
			case 8:
				document.getElementById("ref_excel_1").disabled = false;
				document.getElementById("ref_dat_1").disabled = false;
				document.getElementById("ref_errores_1").disabled = false;
				break;
			
			case 9:	
				document.getElementById("ref_excel_2").disabled = false;
				document.getElementById("ref_dat_2").disabled = false;
				document.getElementById("ref_errores_2").disabled = false;
				break;
			default:
				alert("No es posible desbloquear links de descarga.");	
		}
	}
	
	/*Funcion que realiza la descarga de los archivos en formato excel*/
	function orquestadorXls(idTipoReporte){
		
		if(obtenerRutaXLSEnEstadoProcesado(idTipoReporte) == ""){
			alert("El archivo que intenta descargar no se encuentra generado en el servidor de aplicaciones.");
		}else{	
			document.GenRepAreaDivPrevForm.rutaReporte.value = rutaXlsInicial;
			enviaFormulario(2);
		}
	}
	
	
	/*Funcion que realiza la descarga de los archivos en formato dat*/
	function orquestadorTxt(idTipoReporte){
		
		if(obtenerRutaTXTEnEstadoProcesado(idTipoReporte) == ""){
			alert("El archivo que intenta descargar no se encuentra generado en el servidor de aplicaciones.");
		}else{
			document.GenRepAreaDivPrevForm.rutaReporte.value = rutaTxtInicial;
			enviaFormulario(2);
		}
	}

	/*Funcion que realiza la descarga de los archivos en formato excel de errores.*/
	function orquestadorXlsErr(idTipoReporte){
		
		if(obtenerRutaXLSErrEnEstadoProcesado(idTipoReporte) == ""){
			alert("El Archivo que intenta descargar no se encuentra generado en el servidor de aplicaciones.");
		}else{
			document.GenRepAreaDivPrevForm.rutaReporte.value = rutaXlsErrInicial;
			enviaFormulario(2);
		}	
	}

	/*Funcion que carga las rutas de los archivos en caso de que el estado de dicho reporte este en "PROCESADO"*/
	var rutaXlsInicial = "";
	var rutaTxtInicial = "";
	var rutaXlsErrInicial = "";
	
	function retornarPeriodoInicial(){
		
		var anioPeriodo = document.GenRepAreaDivPrevForm.dbx_anio.value;
		var mesPeriodo = document.GenRepAreaDivPrevForm.dbx_meses.value;
		
		if(parseInt(mesPeriodo,10) < 10){
			mesPeriodo = '0' + mesPeriodo;
		}
		
			
		var periodo = anioPeriodo.toString() + mesPeriodo.toString();
		
		return periodo;	
	}
	
	function obtenerRutaXLSEnEstadoProcesado(idTipoReporte){
		
		rutaXlsInicial = "";
		var periodoReporte = retornarPeriodoInicial();
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.obtenerRutaReporteExcel(idTipoReporte, periodoReporte, function(data){
			
			var resp = null;
			resp = data;
			if(resp != ""){
				rutaXlsInicial = data;
			}	
		});
		DWREngine.setAsync(true);
		
		return rutaXlsInicial;
	}
	
	function obtenerRutaTXTEnEstadoProcesado(idTipoReporte){
		
		rutaTxtInicial = "";
		var periodoReporte = retornarPeriodoInicial();
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.obtenerRutaReporteTxt(idTipoReporte, periodoReporte, function(data){			
			
			var resp = null;
			resp = data;
			if(resp != ""){
				rutaTxtInicial = data;
			}
		});
		DWREngine.setAsync(true);
		
		return rutaTxtInicial;	
	}
	
	function obtenerRutaXLSErrEnEstadoProcesado(idTipoReporte){
		
		rutaXlsErrInicial = "";
		var periodoReporte = retornarPeriodoInicial();
		DWREngine.setAsync(false);
		GenerarReporteAFCDWR.obtenerRutaReporteExcelErr(idTipoReporte, periodoReporte, function(data){			
			
			var resp = null;
			resp = data;
			if(resp != ""){
				rutaXlsErrInicial = data;
			}
		});
		DWREngine.setAsync(true);
		
		return rutaXlsErrInicial;	
	}
	
	/*Funcion que bloquea los links de excel, txt y ewrrores cuando se inicia la pantalla antes de actualizarEstadoSCInit.*/
	function bloquearLinksIni(){
		
		
		var tipoReporte = 8;
		
		while(tipoReporte <= 9){
			switch(parseInt(tipoReporte,10)){
				case 8:
					document.getElementById("ref_excel_1").disabled = true;
					document.getElementById("ref_dat_1").disabled = true;
					document.getElementById("ref_errores_1").disabled = true;
					break;
				
				case 9:	
					document.getElementById("ref_excel_2").disabled = true;
					document.getElementById("ref_dat_2").disabled = true;
					document.getElementById("ref_errores_2").disabled = true;
					break;
				default:
					break;	
			}
			tipoReporte++;
		}	
	}

	/**Funcion que bloquea link por tipoReporte.*/
	function bloqueaLinkPorId(numero)
	{
		switch(parseInt(numero,10)){
			case 8:
				document.getElementById("ref_excel_1").disabled = true;
				document.getElementById("ref_dat_1").disabled = true;
				document.getElementById("ref_errores_1").disabled = true;
				break;
				
			case 9:	
				document.getElementById("ref_excel_2").disabled = true;
				document.getElementById("ref_dat_2").disabled = true;
				document.getElementById("ref_errores_2").disabled = true;
				break;
			default:
				break;	
		}
	}
	
	/**Funcion que implementa funcionalidad de consulta automatica, dependiendo de si elige mes o año, para saber si los procesos fueron ejecutados.*/
	/*function consultarEstadoProcesosPorMesYAnio(){
	
		var reporte = 8;
		var periodoConsultar = retornarPeriodoInicial();
		
		while(reporte <= 9)
		{
			DWREngine.setAsync(false);
			GenerarReportesAFCDWR.ConsultarEstadosPorPeriodo(function(data){
				var resp = null;
				resp = data;
				
			});
			DWREngine.setAsync(false);
	}*/
		
	function bloqueaBotonProcesar(){
		document.GenRepAreaDivPrevForm.btn_Procesar_1.disabled = false;
		document.GenRepAreaDivPrevForm.btn_Procesar_2.disabled = false;
		var procesoC1 = document.GenRepAreaDivPrevForm.txt_estadoCarga1.value;
		var procesoC2 = document.GenRepAreaDivPrevForm.txt_estadoCarga2.value;
		var proceso1 = document.GenRepAreaDivPrevForm.txt_statusProceso_1.value;
		var proceso2 = document.GenRepAreaDivPrevForm.txt_statusProceso_2.value;
		
		if((procesoC1=="PENDIENTE")||(procesoC1=="FALLIDO") || (proceso1 =="EN EJECUCION")){
			document.GenRepAreaDivPrevForm.btn_Procesar_1.disabled = true;
		}
		if((procesoC2=="PENDIENTE")||(procesoC2=="FALLIDO") || (proceso2 =="EN EJECUCION")){
			document.GenRepAreaDivPrevForm.btn_Procesar_2.disabled = true;
		}
	}	
		
	function cargarOnload(){
		bloqueaBotonesExcel();
		bloquearLinksIni();
		cargarArregloParametricas();
		obtenerComboPeriodoProceso();
		selectedItemCombo();
		disparaProcesoEntrada();
		actualizarEstadoSCInit();
		devolucion();
		bloqueaBotonProcesar();
	}
	
	function recibirMes(){
		var mes = '<%=session.getAttribute("mes")%>';
		return mes;
	}
	
	function recibirAnio(){
		var anio = '<%=session.getAttribute("anio")%>';
		return anio;
	}
	
</script>
</head>
<body onload="cargarOnload();">
<html:form action="/GenRepoAreaDivPrev.do" method="post" enctype="multipart/form-data">

  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idReporte" value="0">
  <input type="hidden" name="idFlagXLS" value="0">
  <input type="hidden" name="idFlagTXT" value="0">
  <input type="hidden" name="rutaReporteXls" value="0">
  <input type="hidden" name="flagTipoAFC" value="">
  <input type="hidden" name="rutaReporteTxt" value="0">
  <input type="hidden" name="rutaReporteErr" value="0">
  <input type="hidden" name="rutaArchivo" value="">
  <input type="hidden" name="idMaestroSivegam" value="0">
  <input type="hidden" name="rutaReporte" value=""> 
  <input type="hidden" name="anio" value="recibirAnio()"> 
  <input type="hidden" name="mes" value="recibirMes()">      

  <div id="caja_registro">
	  <!-- <table width="970"> -->
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	  <table width="1020">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="javascript:closeSesion()" ><B>Cerrar</B></a>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>GENERACIÓN DE REPORTES ÁREA REGÍMENES LEGALES</p1></strong></td>
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
				</tr>
			</table>
		   </td>	
		</tr>
		<tr>
			<td>
				<p><p2></p>
				<p></p>
				<p>1. Reportes AFC<p2></p>
				<p>
			</td>
		</tr>
	  </table>
	   <tr>		
		<table>
			<tr>
			</tr>
				<tr>
					<td>Mes</td>
					<td>
						<html:select property="dbx_meses" styleClass="combobox" value="valorAnteriorMes()" onblur="actualizarEstadoSCInit();" onchange="actualizarEstadoSCInit();">
							<html:option value="0">Seleccione </html:option>
						</html:select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Año<td>
					<td>
						<html:select property="dbx_anio" styleClass="comboAnio" value="valorAnteriorAnio()" onblur="actualizarEstadoSCInit();" onchange="actualizarEstadoSCInit();">
							<html:option value="0">Seleccione </html:option>
						</html:select>
					</td>			
					<td></td>
					<td></td>
				</tr>
		</table>
	  </tr>
	  <br>
	  <tr>
	 	 <td width="100%">
  	 		 <!-- <table width="970" align="center" cellpadding="0" cellspacing="1"> -->
  	 		 <table width="1000" align="center" cellpadding="0" cellspacing="1">
				<tr>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código</td>	          
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descripción</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado Carga</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Carga Archivo</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Generar Reporte</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Excel</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Plano</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Errores</td>          		            	    	
	       		</tr>
	   
				<tr>
	            	<td height="20"  class="texto" style="text-align: center">AFCRET</td>
	            	<td height="20"  class="texto" style="text-align: center">Causantes con derecho a cobro de asignación familiar retroactivo</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="text" id="txt_estadoCarga1" name="txt_estadoCarga1"  disabled="true" size="15" style="background-color: transparent; border-width: 0px; text-align: center"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center"><!-- insertarArchivo()cargaTipoArchivo(R); -->
	            		<input type="button" name="btn_cargarArchivo_1" id="btn_cargarArchivo_1" class="btn_limp" value="Cargar" onClick="cargaTipoArchivo(8);enviarArchivo();">
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="text" id="txt_statusProceso_1" name="txt_statusProceso_1" disabled="true" size="15" style="background-color: transparent; border-width: 0px"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="button" name="btn_Procesar_1" id="btn_Procesar_1" class="btn_limp" value="Procesar" onClick="seteaIdReporte(8);cargaTipoArchivo(8);insertToMaestroSivegam();">
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_excel_1" name="ref_excel_1" onClick="seteaFlagXls(8);cargaTipoArchivo(8);orquestadorXls(8);">Excel</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_dat_1" name="ref_dat_1" onClick="seteaFlagTxt(8);cargaTipoArchivo(8);orquestadorTxt(8);">DAT</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_errores_1" name="ref_errores_1" onClick="seteaFlagXls(8);cargaTipoArchivo(8);orquestadorXlsErr(8);">Excel</a>
	            	</td>          	    	
	       		</tr>
	       		<tr>
	            	<td height="20"  class="texto" style="text-align: center">AFCMEN</td>    
	            	<td height="20"  class="texto" style="text-align: center">Causantes con derecho a cobro de asignación familiar mensual</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="text" id="txt_estadoCarga2" name="txt_estadoCarga2" disabled="true" size="15" style="background-color: transparent; border-width: 0px; text-align: center"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="button" name="btn_cargarArchivo_2" id="btn_cargarArchivo_2" class="btn_limp" value="Cargar" onClick="cargaTipoArchivo(9);enviarArchivo();">
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="text" id="txt_statusProceso_2" name="txt_statusProceso_2" disabled="true" size="15" style="background-color: transparent; border-width: 0px"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="button" name="btn_Procesar_2" id="btn_Procesar_2" class="btn_limp" value="Procesar" onClick="seteaIdReporte(9);cargaTipoArchivo(9);insertToMaestroSivegam();"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_excel_2" name="ref_excel_2" onClick="seteaFlagXls(9);cargaTipoArchivo(9);orquestadorXls(9);">Excel</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_dat_2" name="ref_dat_2" onClick="seteaFlagTxt(9);cargaTipoArchivo(9);orquestadorTxt(9);">DAT</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_errores_2" name="ref_errores_2" onClick="seteaFlagXls(9);cargaTipoArchivo(9);orquestadorXlsErr(9);">Excel</a>
	            	</td>
	  		</table>
	  	</td>
	  </tr>	
  	  <div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 150px;">
  	  	<!-- <table width="100%" align="center" cellpadding="0" cellspacing="1">
			<tr>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código</td>	          
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descripción</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Generar Reporte</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Reporte</td>          		            	    	
	       	</tr>
	    </table> -->
  	  </div>
	  	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1324px; height: 720px; background:#999999; filter: alpha(opacity=70);-moz-opacity:70%;">
 		<div id="datosEstadisticas" style="display: none; position: absolute; width: 500px; margin-top: 180px; margin-left: 200px; background-color: #E6E6E6"><!-- #F2F2F2 -->
		</div>
  	  </div>
	  
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
