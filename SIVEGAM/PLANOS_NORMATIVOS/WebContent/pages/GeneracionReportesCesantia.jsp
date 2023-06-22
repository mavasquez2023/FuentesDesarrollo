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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarReporteCesantiaDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarPlanosCesantiaDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraReporteCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraRepDivPrevDWR.js"></script>

<script type="text/javascript">

	var periodoProcesos = new Array();
	var anios = new Array();
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	function asignaValor(a){

		document.GenRepCesantiaForm.opcion.value = a;
		document.GenRepCesantiaForm.anio.value = document.GenRepCesantiaForm.dbx_anio.value;
		document.GenRepCesantiaForm.mes.value = document.GenRepCesantiaForm.dbx_meses.value;
	}
	
	function enviaFormulario(a){
	
		//alert('se eliminaron todos los archivos');
		asignaValor(a);
		document.GenRepCesantiaForm.submit();
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
				mesReporte = parseInt(fechaSistema.substring(4,5)) - 1 ; //siempre el mes anterior.
				

				periodoMes = "0" + mesReporte;
				
				periodoReporte = anioReporte + periodoMes;
			}
		}else{
			mesReporte = parseInt(fechaSistema.substring(3,5)) - 1;
			periodoReporte = anioReporte + mesReporte;
		}
		
		return periodoReporte;
	}
	

	/**Funcion que genera los planos en formato .dat (txt)*/
	function generarTxt(idFlagTxt, idMaestroSiv){
	//TODO PCHIONG SE DEBE PASAR COMO PARAMETRO EL idMaestroSiv
		var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaReporte);
		//var idMaestroSiv = obtenerMaxIdMaestroSivegam(idFlagTxt);
		//provisorio, se debe eliominar este if
		/*if(idMaestroSiv == 0){
			idMaestroSiv = 210;
		}*/
		//var mesPeiodo = periodo.substring(4,7);
		
		var mesReporte = completaValorMes(document.GenRepCesantiaForm.dbx_meses.value);
		var anioReporte = document.GenRepCesantiaForm.dbx_anio.value;
		var periodoReporte = anioReporte.toString() + mesReporte.toString();
		
		var usser = '<%=session.getAttribute("IDAnalista")%>';
				
		if(idMaestroSiv == 0){
			
			alert("No es posible generar el reporte en txt, debido a que no existe información para el periodo seleccionado.");
			return false;
		
		}else{
			DWREngine.setAsync(false);
			var periodo = parseInt(periodoReporte,10);
			GenerarPlanosCesantiaDWR.generarPlanosCesantia(idFlagTxt,periodoReporte,idMaestroSiv,periodo,usser,fechaReporte, function(data){
				var respuesta = null;
				respuesta = data.codResultado;
				
				if(respuesta == 0){
					document.GenRepCesantiaForm.rutaReporteTxt.value = data.rutaArchivo;
				}
				
			});
			DWREngine.setAsync(true);
		}	
		////gc();	
	}
	
	/** Funcion que genera el reporte en archivo .xls usando las librerías de jasperReport.
	 * recibe como parametro el id del archivo xls que se va a generar el cual debe coincidir con el
	 * establecido en la tabla parametrica.
	*/
	function generarXls(idFlagXls,idMasterSivegam){
	//TODO PCHIONG SE DEBE PASAR COMO PARAMETRO EL idMaestroSiv	
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoReporte = obtenerPeriodoAnioMes(fechaSistema);
		//var idMasterSivegam = obtenerMaxIdMaestroSivegam(idFlagXls);
		//var mesReporte = periodoReporte.substring(4,7);
		var usser = '<%=session.getAttribute("IDAnalista")%>';
		
		var mesReporte = completaValorMes(document.GenRepCesantiaForm.dbx_meses.value);
		var anioReporte = document.GenRepCesantiaForm.dbx_anio.value;
		var periodoReporte = anioReporte.toString() + mesReporte.toString();
		
		if(idMasterSivegam == 0){
			
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{	
			DWREngine.setAsync(false);
			
			var periodo = parseInt(periodoReporte,10);
			
			GenerarReporteCesantiaDWR.generarReporteXLS(idFlagXls, periodoReporte, idMasterSivegam, periodo, usser, fechaSistema, function(data){
			
				var resp = null;
				
				resp = data.codRespuesta;
				
				if(resp == 0){

					//document.GenRepCesantiaForm.rutaReporteXls.value = data.rutaArchivo;
					document.GenRepCesantiaForm.rutaArchivo.value = data.rutaArchivo;
					//alert('data.rutaArchivo  ' + data.rutaArchivo);
					//alert('document.GenRepCesantiaForm.rutaArchivo.value  ' + document.GenRepCesantiaForm.rutaArchivo.value);
					//enviaFormulario(2);
				}else{
					alert("Reporte no generado");
				}
			});
			
			DWREngine.setAsync(true);
		}	
		//gc();
	}

	/**Funcion que genera el archivo excel de errores.*/
	function generarXlsErrores(idFlagXls,idMasterSivegam){
	//TODO PCHIONG SE DEBE PASAR COMO PARAMETRO EL idMaestroSiv	
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoReporte = obtenerPeriodoAnioMes(fechaSistema);
		//var idMasterSivegam = obtenerMaxIdMaestroSivegam(idFlagXls);
		//var mesReporte = periodoReporte.substring(4,7);
		var usser = '<%=session.getAttribute("IDAnalista")%>';
		
		var mesReporte = completaValorMes(document.GenRepCesantiaForm.dbx_meses.value);
		var anioReporte = document.GenRepCesantiaForm.dbx_anio.value;
		var periodoReporte = anioReporte.toString() + mesReporte.toString();
		
		if(idMasterSivegam == 0){
			
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		
		}else{	
			DWREngine.setAsync(false);
			
			var periodo = parseInt(periodoReporte,10);
			
			GenerarReporteCesantiaDWR.generarReporteErroresXls(idFlagXls, periodoReporte, idMasterSivegam, periodo, usser, fechaSistema, function(data){
				var resp = null;
				resp = data.codRespuesta;
				if(resp == 0){
					//document.GenRepCesantiaForm.rutaReporteErr.value = data.rutaArchivo;
					document.GenRepCesantiaForm.rutaArchivo.value = data.rutaArchivo;
				}
			});
			DWREngine.setAsync(true);
		}
		//gc();
	}
	
	/**Funcion que obtiene el ultimo id maestro sivegam cuando se entra a la aplicacion y no se requiere procesar sino
	generar los archivos xls y txt.*/
	
	function obtenerMaxIdMaestroSivegam(idReporte){
	
		//var fechaSistema = '< % = session.getAttribute("FechaSistema")%>';	
		var idMaxMaestroSivegam = 0;
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);

		var mesReporte = completaValorMes(document.GenRepCesantiaForm.dbx_meses.value);
		var anioReporte = document.GenRepCesantiaForm.dbx_anio.value;
		var periodo = anioReporte.toString() + mesReporte.toString();
		
		
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.selectMaxIdMaestroSivegam(idReporte, periodo, function(data){
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
		
		document.GenRepCesantiaForm.idReporte.value = n;
		
	}
	
	function seteaFlagXls(n){
	
		document.GenRepCesantiaForm.idFlagXLS.value = n;
	}

	function seteaaniomes(){
		var mesReporte = completaValorMes(document.GenRepCesantiaForm.dbx_meses.value);
		var anioReporte = document.GenRepCesantiaForm.dbx_anio.value;
	
		document.GenRepCesantiaForm.anio.value = anioReporte;
		document.GenRepCesantiaForm.mes.value = mesReporte;
	}

	/*Funcion que obtiene la descripcion del status del proceso. Se carga al inicio del programa y luego se actualiza de acuerdo
	* a lo arrojado por el proceso cobol.
	* Si el valor no se encuentra actualizado en la tabla maestrosivegam se inicializa con el valor pendiente de la tabla
	* de lo contrario, toma el valor de la tabla maestrosivegam (funcion debe ser construida).
	*/
	function obtenerDescStatusProceso(){
		
		DWREngine.setAsync(true);
		GenerarReporteCesantiaDWR.obtenerStatusProceso(function(data){
			document.GenRepCesantiaForm.txt_statusProceso41.value = data.descripcion_status_proceso;		
			document.GenRepCesantiaForm.txt_statusProceso42.value = data.descripcion_status_proceso;
			document.GenRepCesantiaForm.txt_statusProceso43.value = data.descripcion_status_proceso;
			document.GenRepCesantiaForm.txt_statusProceso44.value = data.descripcion_status_proceso;
		});
		DWREngine.setAsync(false);
	}
	
	/*Funcion que actualiza el status del proceso una vez que se ha ejecutado el proceso cobol.*/
	function actualizaStatus(p){
	
		var botonPresionado = document.GenerarRepCotForm.idReporte.value;
		switch(parseInt(botonPresionado)){
			case 10:
				document.GenRepCesantiaForm.txt_statusProceso41.value = p;
				document.getElementById("ref_excel_1").disabled = false;
				document.getElementById("btn_Procesar10").disabled = false;//frm cambiar a true;
				break;
			
			case 11:
				document.GenRepCesantiaForm.txt_statusProceso42.value = p;
				document.getElementById("ref_excel_2").disabled = false;
				document.getElementById("btn_Procesar11").disabled = false;//frm cambiar a true;
				break;
				
			case 12:
				document.GenRepCesantiaForm.txt_statusProceso43.value = p;
				document.getElementById("ref_excel_3").disabled = false;
				document.getElementById("btn_Procesar12").disabled = false;//frm cambiar a true;
				break;
			case 13:
				document.GenRepCesantiaForm.txt_statusProceso44.value = p;
				document.getElementById("ref_excel_4").disabled = false;
				document.getElementById("btn_Procesar13").disabled = false;//frm cambiar a true;
				break;	
			default:
				alert("Ha ocurrido un error y no se ha podido actualizar el estado del proceso.");
		}
				
	}
	
	/*Funcion que bloquea los botones excel antes de ejecutar cualquier proceso cobol.*/
	function bloqueaBotonesExcel(){

		var link1 = document.getElementById("ref_excel_1");
		var link2 = document.getElementById("ref_excel_2");
		link1.disabled = false;
		link2.disabled = false;
		
		var link3 = document.getElementById("ref_excel_3");
		var link4 = document.getElementById("ref_excel_4");
		

		link3.disabled = false;
		link4.disabled = false;
		
		var boton1 = document.getElementById("btn_Procesar10");
		boton1.disabled = false;//frm cambiar a true;
		var boton2 = document.getElementById("btn_Procesar11");
		boton2.disabled = false;//frm cambiar a true;
		var boton3 = document.getElementById("btn_Procesar12");
		boton3.disabled = false;//frm cambiar a true;
		var boton4 = document.getElementById("btn_Procesar13");
		boton4.disabled = false;//frm cambiar a true;
		return false;
	}
	
	/*Funcion que inserta en la tabla maestrosivegam.*/
	function insertToMaestroSivegam(){
		
		var fechaProceso = '<%=session.getAttribute("FechaSistema")%>';
		var usuarioSivegam = '<%=session.getAttribute("IDAnalista")%>';
		var idTipoReporte = document.GenRepCesantiaForm.idReporte.value;
		var mes = parseInt(document.GenRepCesantiaForm.dbx_meses.value,10);
		var anio = parseInt(document.GenRepCesantiaForm.dbx_anio.value,10);
		var periodo = anio*100 + mes; 
		
		//para corroborar que proceso ya fue ejecutado y no volver a ejecutar sino que da opcion de reprocesar.
		var statusPorProceso;
		
		switch(parseInt(idTipoReporte,10)){
			case 10:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			case 11:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			case 12:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			case 13:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;	
			default:
			
				return false;
		}
		
		if(statusPorProceso == 2 || statusPorProceso == 3 || statusPorProceso == 5 ){
			
			var confirmacion = confirm("Va a ingresar a pantalla de Reprocesamiento. ¿Está seguro desea continuar?");
			
			if(confirmacion == true){
				mostrarDiv();
				getPantallaReproceso();
			}	
					
		}else{	
			
			DWREngine.setAsync(false);
			GenerarReporteCesantiaDWR.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodo, function(data){
				
				var idMasterSivegam = null;
				var idFlagFileXls = null;
				var respuesta = null;
				
				respuesta = data.codResultado;
				
				if(respuesta == 0){
				
					alert("Se han insertado los datos, invocando proceso ...");
					document.GenRepCesantiaForm.idMaestroSivegam.value = data.idMaestroSivegam;
					idMasterSivegam = document.GenRepCesantiaForm.idMaestroSivegam.value;
					
					invocarProcesoCobol(idMasterSivegam);
					
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
		GenerarReporteCesantiaDWR.actualizarStatusSegunPeriodoYProceso(tipoReporte, periodo, function(data){
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
		GenerarReporteCesantiaDWR.actualizacionEstado(control, function(data){
			var resp = null;
			resp = data;
			if(resp != null){
				switch(parseInt(tipoReporte,10)){
					case 10:
						document.GenRepCesantiaForm.txt_statusProceso41.value = data.descripcion_status_proceso;
						break;
					case 11:
						document.GenRepCesantiaForm.txt_statusProceso42.value = data.descripcion_status_proceso;
						break;
					case 12:
						document.GenRepCesantiaForm.txt_statusProceso43.value = data.descripcion_status_proceso;
						break;
					case 13:
						document.GenRepCesantiaForm.txt_statusProceso44.value = data.descripcion_status_proceso;
						break;
					default:
						alert("No es posible actualizar el estado del reporte.");
				}					
			}
				
		});
		DWREngine.setAsync(true);
	}
	
	/*Funcion que establece comunicacion con un servidor AS400 e invoca al proceso cobol que llena las tablas para el proceso de SIVEGAM*/
	function invocarProcesoCobol(idSecuencia){
	
		DWREngine.setAsync(true);

		var mes = document.GenRepCesantiaForm.dbx_meses.value;
		var anio = document.GenRepCesantiaForm.dbx_anio.value;
		
		if(parseInt(mes,10) < 10){
			mes = '0' + mes;
		}
		
		
		
		var periodo = anio +  mes;	 
		var idReport = document.GenRepCesantiaForm.idReporte.value; //id se refiere al numero de reporte del cl que se quiere invocar.
		
		//esta variable se debe eliminar una vez automatizado el proceso.
		//var periodo = "201207";
		document.getElementById("pantallaDeCarga").style.visibility = "visible";		
		GenerarReporteCesantiaDWR.llamarProcesoCobol(idSecuencia, idReport, periodo, function(data){
			var resp = null;
			var statusActualizado;
			resp = data.codResultado;
			
			updateStatusProcesoMaestroSivegam(resp,idSecuencia);//se debe actualizar status y fecha de procesamiento de maestro sivegam.
			
			if(resp == 3){
			
				//statusActualizado = data.msgRespuesta;
				//actualizaStatus(statusActualizado);
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				alert("Proceso Terminado Exitosamente.");
				actualizarEstado(resp,idReport);
				//FRM generar xls, txt y errores.
				generarXls(idReport,idSecuencia);
				generarTxt(idReport,idSecuencia);
				generarXlsErrores(idReport,idSecuencia);
				actualizarEstadoSCInit();
				bloqueaBotonesProceso();
			}else{
				//aqui llamar a una funcion (se debe crear) que cambie el status a "fallido".
				if(resp == 2)
				{
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					alert('Error al procesar el archivo');					
					actualizarEstado(resp,idReport);
					actualizarEstadoSCInit();
					bloqueaBotonesProceso();
				}
				else{
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					alert("No existe información para el mes procesado");
					actualizarEstado(resp,idReport);			
					actualizarEstadoSCInit();
					bloqueaBotonesProceso();
				}
			}		
		});
		
		DWREngine.setAsync(true);
	}	

	/**Funcion que realiza el update sel status de proceso a la tabla maestro sivegam, dada la respuesta arrojada por el proceso cobol*/
	function updateStatusProcesoMaestroSivegam(idRespuestaCobol,idSivegam){
		
		var fechaProceso = '<%=session.getAttribute("FechaSistema")%>';
		
		//if(reprocesoReporte == 1){
			//idMaestroSiv = idSivegam;
		//}else{
			//idMaestroSiv = document.GenerarRepDivPrevForm.idMaestroSivegam.value;
		//}
		
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.updateStatusProcesoSivegam(idRespuestaCobol, idSivegam, fechaProceso, function(data){
			var resp = null;
			resp = data.codRespuesta;
		});
		DWREngine.setAsync(true);
	}

	/*Aqui funcion DWR que verifica si un archivo esta en su respectiva carpeta.*/
	var flagArchivo;
	function verificarArchivoEnServer(){
		
		var idTipoReporte = document.GenRepCesantiaForm.idFlagTxt.value;
		var nombreArch = retornaPeriodoYNombreArchivoTxt(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteArchivoSegunPeriodo(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenRepCesantiaForm.rutaArchivo.value = data.rutaArchivo;
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
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);

		var mes = document.GenRepCesantiaForm.dbx_meses.value;
		var anio = document.GenRepCesantiaForm.dbx_anio.value;
		
		if(parseInt(mes,10) < 10){
			mes = '0' + mes;
		}
		
		
		
		var periodo = anio +  mes;	 

		
		switch(parseInt(codReporte,10)){
			case 10:
				nombreArchivo = "PLANO41_"+ periodo + ".TXT";
				break;
			case 11:
				nombreArchivo = "PLANO42_"+ periodo + ".TXT";
				break;
			case 12:
				nombreArchivo = "PLANO43_"+ periodo + ".TXT";
				break;
			case 13:
				nombreArchivo = "PLANO44_"+ periodo + ".TXT";
				break;	
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
	/**Funcion que retorna si existe un archivo xls*/
	function verificarExisteXls(){

		var idTipoReporte = document.GenRepCesantiaForm.idFlagXls.value;
		var nombreArch = retornaPeriodoYNombreArchivoXls(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteXLS(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;

			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenRepCesantiaForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;	
	}
	
	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoXls(codReporte){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";

		var mes = document.GenRepCesantiaForm.dbx_meses.value;
		var anio = document.GenRepCesantiaForm.dbx_anio.value;
		
		if(parseInt(mes,10) < 10){
			mes = '0' + mes;
		}
		
		
		
		var periodo = anio +  mes;	 

		
		switch(parseInt(codReporte,10)){
			case 10:
				nombreArchivo = "SC41_"+ periodo + ".XLSX";
				break;
			case 11:
				nombreArchivo = "SC42_"+ periodo + ".XLSX";
				break;
			case 12:
				nombreArchivo = "SC43_"+ periodo + ".XLSX";
				break;
			case 13:
				nombreArchivo = "SC44_"+ periodo + ".XLSX";
				break;	
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
		
	/*Funcion que verifica si existe resumen*/
	function verificaExisteErrores(){
		
		var idTipoReporte = document.GenRepCesantiaForm.idFlagXls.value;
		var nombreArch = retornaPeriodoYNombreArchivoErr(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteXLS(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;

			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenRepCesantiaForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;	
	}
	
	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoErr(codReporte){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";

		var mes = document.GenRepCesantiaForm.dbx_meses.value;
		var anio = document.GenRepCesantiaForm.dbx_anio.value;
		
		if(parseInt(mes,10) < 10){
			mes = '0' + mes;
		}
		
		
		
		var periodo = anio +  mes;	 

		
		switch(parseInt(codReporte,10)){
			case 10:
				nombreArchivo = "SC41_"+ periodo + "_ERR.XLSX";
				break;
			case 11:
				nombreArchivo = "SC42_"+ periodo + "_ERR.XLSX";
				break;
			case 12:
				nombreArchivo = "SC43_"+ periodo + "_ERR.XLSX";
				break;
			case 13:
				nombreArchivo = "SC44_"+ periodo + "_ERR.XLSX";
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
			alert("El archivo aun no ha sido generado.");
		}	
	}
	
	function descargarDat(){
		
		var fg = verificarArchivoEnServer();
		if(fg == 0){
			enviaFormulario(3);	
		}else{
			alert("El archivo aun no ha sido generado.");
		}
	}
	
	function descargarErrores(){
		
		var fg = verificarArchivoEnServer();
		if(fg == 0){
			enviaFormulario(3);	
		}else{
			alert("El archivo aun no ha sido generado.");
		}
	}
		
	function cargarArchivo(){
		var tipoReporte = document.GenRepCesantiaForm.idReporte.value;

		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.insertarReporte(tipoReporte, function(data){
			var resp = null;
			var msg = null;
			resp = data.codRespuesta;
			if(resp == 0){
				alert("Informe insertado Ok");
			}else{
				msg = data.msgRespuesta;
				alert(msg);
			}	
		});
		DWREngine.setAsync(true);
	}
	
	/**Funcion que elimina un archivo del servidor, cuando se presiona reprocesar.*/
	function eliminarArchivo(idReporte){
	
		var nombreArch = retornaPeriodoYNombreArchivoTxt(idReporte);
				
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.deleteArchivoTxt(idReporte,nombreArch, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}
		});
		DWREngine.setAsync(true);
		
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
		GenerarReporteCesantiaDWR.obtenerDataPeriodoProceso("PeriodoProceso", function(data){
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
		var cmb = document.GenRepCesantiaForm.dbx_meses;
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
	/* function selectedItemCombo(){
		var dateSistema = '< %=session.getAttribute("FechaSistema")%>';
		var mes = parseInt(dateSistema.substring(3,5),10);
		document.GenRepCesantiaForm.dbx_meses.value = mes -1;
		var anio = parseInt(dateSistema.substring(6,10),10);
		document.GenRepCesantiaForm.dbx_anio.value = anio;
		var periodo = anio*100 + mes;
		
	}*/
	function selectedItemCombo(){
		var anio = '<%=session.getAttribute("anio")%>';
		if(anio == 'null' ){
			var dateSistema = '<%=session.getAttribute("FechaSistema")%>';
			anio = parseInt(dateSistema.substring(6,10),10);
			document.GenRepCesantiaForm.dbx_anio.value = anio;
			var mes = parseInt(dateSistema.substring(3,5),10);
			document.GenRepCesantiaForm.dbx_meses.value = mes-1;
			var periodo = anio*100 + mes;
		}else{
			document.GenRepCesantiaForm.dbx_anio.value = anio;
			var mes = '<%=session.getAttribute("mes")%>';
			document.GenRepCesantiaForm.dbx_meses.value = parseInt(mes,10);			
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

		var cmb = document.GenRepCesantiaForm.dbx_anio;
		
		cmb.options[0] = new Option("Seleccione",0);		
		
		var anioConta = anioSist - 2000;
		var j=2000;
			for(var i=0; i< (anioConta+1); i++){
				
				j = 2000+i;
				
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
		
		var cmb = document.GenRepCesantiaForm.dbx_anio;
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
	
	/**Funcion que elimina archivo XLS.*/
	function eliminarArchivoXls(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoXls(codReporte);
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.deleteArchivoXls(codReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}	
		});
		DWREngine.setAsync(true);
		
	}
	
	/**Funcion que elimina archivo xls del tipo resumen.*/
	function eliminarResumenes(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoErr(codReporte);
		//alert("nombre archivo: " + nombreArchivo);
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.deleteArchivoXls(codReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}	
		});
		DWREngine.setAsync(true);
	}	
	/*Funcion que interpreta eleccion del usuario (radiobutton) y envia la funcionalidad requerida.*/
	var seleccionCheck = 0;
	function ejecutarReproceso(){
		var opcionSeleccionada;
		var tipoReporte = document.GenRepCesantiaForm.idReporte.value;

		var idMaestroSivegam = obtenerMaxIdMaestroSivegam(tipoReporte);
	    var valueRadioButton = document.GenRepCesantiaForm.rbt_reprocesamiento.length;
	    
	    for (var i=0; i < valueRadioButton; i++){ 
	       if (document.GenRepCesantiaForm.rbt_reprocesamiento[i].checked){
	          	 opcionSeleccionada = document.GenRepCesantiaForm.rbt_reprocesamiento[i].value;
	          	 seleccionCheck = opcionSeleccionada;   	 
	       }   	 
	    }
	    
	    switch(parseInt(opcionSeleccionada,10)){
	    	case 1:

	    		//eliminar archivo excel, txt y errores. 
	    		//TODO falta agregar parametros de entrada a estas funciones (idReporte) para identificar a que reporte se va a eliminar.
	    		esconderDivReproceso();
	    		eliminarArchivoXls(tipoReporte);
	    		eliminarArchivo(tipoReporte);
	    		eliminarResumenes(tipoReporte);
	    		//hacer update a status de la tabla maestro sivegam, usando idMaestroSivegam
	    		realizarUpdateStatusAntesDeReprocesar(idMaestroSivegam);
	    		//invocarProcesoCobol(idMaestroSivegam);
	    		break;
	    	case 2:
	    		//eliminar archivo txt y errores.
	    		//CMO
	    		seteaaniomes();
	    		//llamar pantalla carga y enviar archivo excel a carpeta correspondiente.
	    		enviaFormulario(3);
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
		GenerarReporteCesantiaDWR.updateStatusAntesDeReprocesar(id, statusReproceso, function(data){
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
		//alert('respuesta carga Cesantia ' + '<!--%=session.getAttribute("respuestaCargaCesantia")%-->');//CMO
		if('<%=session.getAttribute("respuestaCargaCesantia")%>' != "" && '<%=session.getAttribute("respuestaCargaCesantia")%>' != "null"){
			if("<%=session.getAttribute("codRespuestaCargaCesantia")%>" == "99")
			{
				alert('<%=session.getAttribute("respuestaCargaCesantia")%>');
			}
			if("<%=session.getAttribute("respuestaCargaCesantia")%>" == "0")
			{
				var tipoReporte = '<%=session.getAttribute("controlReporteCes")%>';
				var idMaestroSivegam = '<%=session.getAttribute("controlIdSivegam")%>';
				//CMO invocarCobolValidacion(idMaestroSivegam,tipoReporte);
				GeneraArchivosReproceso(idMaestroSivegam,tipoReporte);
				enviaFormulario(4);
				//gc();
			}
		}
	}
	
	/**Funcion que realiza un borrado lógico de las tablas de cesantia, cuando se requiere corregir data desde archivo excel.*/
	function limpiarTablaInsercion(tipoReporte){
		
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.deleteLogicoSegunReporte(tipoReporte, function(data){
			var resp = null;
			resp = data.codRespuesta;
			if(resp == 0){
				alert("Borrado logico realizado con éxito.");
			}
		});
		DWREngine.setAsync(true);	
	}
	
	function invocarCobolValidacion(idMaestroSivegam, idReporte){
		
		var anioReproceso = document.GenRepCesantiaForm.dbx_anio.value;
		var mesReproceso = document.GenRepCesantiaForm.dbx_meses.value;
		
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
		
		DWREngine.setAsync(true);
		document.getElementById("pantallaDeCarga").style.visibility = "visible";		
		GenerarReporteCesantiaDWR.reprocesarCobolValidacionCesantia(idMaestroSivegam, idReporte, periodo, function(data){
			var resp = null;
			var statusActualizado = null;
			resp = data.codResultado;
			if(resp == 3)
			{
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				actualizarEstado(resp, idReporte);
				//CMO
				//alert('generotxt');
				generarTxt(idReporte,idMaestroSivegam);
				//alert('generarXlsErrores');
				generarXlsErrores(idReporte,idMaestroSivegam);
				//alert('generacion fin');
			
			}else{
			
				if(resp == 2){
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					actualizarEstado(resp, idReporte);
					alert(data.codError + " - " + data.descripcionError);
			
				}else{//sino es 5.
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					//statusActualizado = data.msgRespuesta;
					//actualizaStatus(statusActualizado);
					actualizarEstado(resp, idReporte);
					alert("SIN DATA");
				}

			}		
		});	
		DWREngine.setAsync(true);
	}
	
	function GeneraArchivosReproceso(idMaestroSivegam, idReporte){
		
				//CMOactualizarEstado(resp, idReporte);
				//CMO
				//alert('generotxt');
				generarTxt(idReporte,idMaestroSivegam);
				//alert('generarXlsErrores');
				generarXlsErrores(idReporte,idMaestroSivegam);
				//alert('generacion fin');
	}
	
	/**Funciones que actualizan campo estado al iniciar la pantalla.*/
	function actualizarEstadoSCInit(){
		
		var mes = document.GenRepCesantiaForm.dbx_meses.value;
		var anio = document.GenRepCesantiaForm.dbx_anio.value;
		if(mes == 0 || anio == 0){
			selectedItemCombo();
			alert('Seleccione un mes y año antes de procesar.');
		}else{
			var tipoReporte = 10;
			
			while(tipoReporte <=13)
			{
				var mes = parseInt(document.GenRepCesantiaForm.dbx_meses.value,10);
				var anio = parseInt(document.GenRepCesantiaForm.dbx_anio.value,10);
				var periodo = anio*100 + mes;
				var statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(tipoReporte,periodo);
						
				DWREngine.setAsync(false);
				GenerarReporteCesantiaDWR.actualizacionEstado(statusPorProceso, function(data){
					var resp = data;
					if(resp != null)
					{
						switch(parseInt(tipoReporte,10))
						{
							case 10:
								if(data.descripcion_status_proceso == null){
									document.GenRepCesantiaForm.txt_statusProceso41.value = "PENDIENTE";
									bloqueaLinkPorId(10);
								}else{
									document.GenRepCesantiaForm.txt_statusProceso41.value = data.descripcion_status_proceso;
								}
								break;
								
							case 11:
								if(data.descripcion_status_proceso == null){
									document.GenRepCesantiaForm.txt_statusProceso42.value = "PENDIENTE";
									bloqueaLinkPorId(11);
								}else{
									document.GenRepCesantiaForm.txt_statusProceso42.value = data.descripcion_status_proceso;
								}
								break;
							
							case 12:
								if(data.descripcion_status_proceso == null){
									document.GenRepCesantiaForm.txt_statusProceso43.value = "PENDIENTE";
									bloqueaLinkPorId(12);
								}else{
									document.GenRepCesantiaForm.txt_statusProceso43.value = data.descripcion_status_proceso;
								}
								break;
							
							case 13:
								if(data.descripcion_status_proceso == null){
									document.GenRepCesantiaForm.txt_statusProceso44.value = "PENDIENTE";
									bloqueaLinkPorId(13);
								}else{
									document.GenRepCesantiaForm.txt_statusProceso44.value = data.descripcion_status_proceso;
								}
								break;
							
							default:
								alert("No es posible actualizar el estado del reporte.");
						}
					}
				});
				DWREngine.setAsync(true);
				
				if(parseInt(statusPorProceso,10) == 3){
					desbloquearLinks(tipoReporte);
				}else{
					bloqueaLinkPorId(parseInt(tipoReporte,10));
				}
				tipoReporte++;
			}
			bloqueaBotonesProceso();	
		}
	}

	/*Funcion que desbloquea los links de excel, txt y ewrrores.*/
	function desbloquearLinks(tipoReporte){
		switch(parseInt(tipoReporte,10)){
			case 10:
				document.getElementById("ref_excel_1").disabled = false;
				document.getElementById("ref_txt_1").disabled = false;
				document.getElementById("ref_errores_1").disabled = false;
				break;
			
			case 11:	
				document.getElementById("ref_excel_2").disabled = false;
				document.getElementById("ref_txt_2").disabled = false;
				document.getElementById("ref_errores_2").disabled = false;
				break;
				
			case 12:	
				document.getElementById("ref_excel_3").disabled = false;
				document.getElementById("ref_txt_3").disabled = false;
				document.getElementById("ref_errores_3").disabled = false;
				break;
				
			case 13:	
				document.getElementById("ref_excel_4").disabled = false;
				document.getElementById("ref_txt_4").disabled = false;
				document.getElementById("ref_errores_4").disabled = false;
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
			document.GenRepCesantiaForm.rutaArchivo.value = rutaXlsInicial;
			enviaFormulario(2);
		}
	}
	
	
	/*Funcion que realiza la descarga de los archivos en formato dat*/
	function orquestadorTxt(idTipoReporte){
		
		if(obtenerRutaTXTEnEstadoProcesado(idTipoReporte) == ""){
			alert("El archivo que intenta descargar no se encuentra generado en el servidor de aplicaciones.");
		}else{
			document.GenRepCesantiaForm.rutaArchivo.value = rutaTxtInicial;
			enviaFormulario(2);
		}
	}

	/*Funcion que realiza la descarga de los archivos en formato excel de errores.*/
	function orquestadorXlsErr(idTipoReporte){
		
		if(obtenerRutaXLSErrEnEstadoProcesado(idTipoReporte) == ""){
			alert("El Archivo que intenta descargar no se encuentra generado en el servidor de aplicaciones.");
		}else{
			document.GenRepCesantiaForm.rutaArchivo.value = rutaXlsErrInicial;
			enviaFormulario(2);
		}	
	}

	/*Funcion que carga las rutas de los archivos en caso de que el estado de dicho reporte este en "PROCESADO"*/
	var rutaXlsInicial = "";
	var rutaTxtInicial = "";
	var rutaXlsErrInicial = "";
	
	function retornarPeriodoInicial(){
		
		var anioPeriodo = document.GenRepCesantiaForm.dbx_anio.value;
		var mesPeriodo = document.GenRepCesantiaForm.dbx_meses.value;
		
		if(parseInt(mesPeriodo,10) < 10){
			mesPeriodo = '0' + mesPeriodo;
		}
		
		if(anioPeriodo.length == 1){
			anioPeriodo = '200' + anioPeriodo;
		}else{
			if(anioPeriodo.length == 2){
				anioPeriodo = '20' + anioPeriodo;
			}
		}
		
		var periodo = anioPeriodo.toString() + mesPeriodo.toString();
		
		return periodo;	
	}
	
	function obtenerRutaXLSEnEstadoProcesado(idTipoReporte){
		
		rutaXlsInicial = "";
		var periodoReporte = retornarPeriodoInicial();
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.obtenerRutaReporteExcel(idTipoReporte, periodoReporte, function(data){
			
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
		
		//alert('obtenerRutaTXTEnEstadoProcesado - idTipoReporte ' + idTipoReporte );
		rutaTxtInicial = "";
		var periodoReporte = retornarPeriodoInicial();
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.obtenerRutaReporteTxt(idTipoReporte, periodoReporte, function(data){			
			
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
		GenerarReporteCesantiaDWR.obtenerRutaReporteExcelErr(idTipoReporte, periodoReporte, function(data){			
			
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
		
		var tipoReporte = 10;
		
		while(tipoReporte <= 13){
			switch(parseInt(tipoReporte,10)){
				case 10:
					document.getElementById("ref_excel_1").disabled = true;
					document.getElementById("ref_txt_1").disabled = true;
					document.getElementById("ref_errores_1").disabled = true;
					break;
				
				case 11:	
					document.getElementById("ref_excel_2").disabled = true;
					document.getElementById("ref_txt_2").disabled = true;
					document.getElementById("ref_errores_2").disabled = true;
					break;
					
				case 12:
					document.getElementById("ref_excel_3").disabled = true;
					document.getElementById("ref_txt_3").disabled = true;
					document.getElementById("ref_errores_3").disabled = true;
					break;

				case 13:
					document.getElementById("ref_excel_4").disabled = true;
					document.getElementById("ref_txt_4").disabled = true;
					document.getElementById("ref_errores_4").disabled = true;
					break;
															
				default:
					break;	
			}
			tipoReporte++;
		}	
	}
	
	/*Funcion que bloquea los links por id*/
	function bloqueaLinkPorId(numero)
	{
		switch(parseInt(numero,10)){
			case 10:
				document.getElementById("ref_excel_1").disabled = true;
				document.getElementById("ref_txt_1").disabled = true;
				document.getElementById("ref_errores_1").disabled = true;
				break;
				
			case 11:	
				document.getElementById("ref_excel_2").disabled = true;
				document.getElementById("ref_txt_2").disabled = true;
				document.getElementById("ref_errores_2").disabled = true;
				break;
					
			case 12:
				document.getElementById("ref_excel_3").disabled = true;
				document.getElementById("ref_txt_3").disabled = true;
				document.getElementById("ref_errores_3").disabled = true;
				break;

			case 13:
				document.getElementById("ref_excel_4").disabled = true;
				document.getElementById("ref_txt_4").disabled = true;
				document.getElementById("ref_errores_4").disabled = true;
				break;
															
			default:
				break;	
		}
	}
	
	function cargarOnload(){
		//obtenerDescStatusProceso();
		bloqueaBotonesExcel();
		bloquearLinksIni();
		cargarArregloParametricas();
		obtenerComboPeriodoProceso();
		selectedItemCombo();
		actualizarEstadoSCInit();
		devolucion();
		bloqueaBotonesProceso();
	}
	
	function bloqueaBotonesProceso(){
	
		if(document.getElementById("txt_statusProceso41").value == 'EN EJECUCION'){
			document.getElementById("btn_Procesar10").disabled = true;
		}else{
			document.getElementById("btn_Procesar10").disabled = false;
		}
		
		if(document.getElementById("txt_statusProceso42").value == 'EN EJECUCION'){
			document.getElementById("btn_Procesar11").disabled = true;
		}else{
			document.getElementById("btn_Procesar11").disabled = false;
		}
		
		if(document.getElementById("txt_statusProceso43").value == 'EN EJECUCION'){
			document.getElementById("btn_Procesar12").disabled = true;
		}else{
			document.getElementById("btn_Procesar12").disabled = false;
		}
		
		if(document.getElementById("txt_statusProceso44").value == 'EN EJECUCION'){
			document.getElementById("btn_Procesar13").disabled = true;
		}else{
			document.getElementById("btn_Procesar13").disabled = false;
		}
		
	}
	
	function mostrarDiv() {

		div = document.getElementById('pantallaDeCarga');
		div.style.display = '';
		
	}
		
	function cerrarDiv() {
		
		div = document.getElementById('pantallaDeCarga');
		div.style.display='none';
		
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
<html:form action="/GenReportesCesantia.do" method="post" enctype="multipart/form-data">

  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idReporte" value="0">
  <input type="hidden" name="idFlagXLS" value="0"> 
  <input type="hidden" name="rutaArchivo" value="0">
  <input type="hidden" name="Error" value=" ">  
  <input type="hidden" name="idMaestroSivegam" value="0">
  <input type="hidden" name="anio" value="recibirAnio()">
  <input type="hidden" name="mes" value="recibirMes()">
      
  <div id="caja_registro">
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
					<td><strong><p1>GENERACIÓN DE REPORTES DE CESANTÍA</p1></strong></td>
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
					<td colspan="3"></td>			
				</tr>
			</table>
		   </td>	
		</tr>
		<tr>
			<td>
				<p><p2></p>
				<p></p>
				<p>1. Reportes Cesantía<p2></p>
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
						<html:select property="dbx_meses" styleClass="combobox" value="0" onblur="actualizarEstadoSCInit();" onchange="actualizarEstadoSCInit();">
							<html:option value="0">Seleccione </html:option>
						</html:select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Año<td>
					<td>
						<html:select property="dbx_anio" styleClass="comboAnio" value="0" onblur="actualizarEstadoSCInit();" onchange="actualizarEstadoSCInit();">
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
  	 		 <table width="970" align="center" cellpadding="0" cellspacing="1">
				<tr>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código</td>	          
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descripción</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Generar Reporte</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Excel</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Txt</td>
	            	<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Errores</td>          		            	    	
	       		</tr>
	   
				<tr>
	            	<td height="20"  class="texto" style="text-align: center">PLANO41</td>
	            	<td height="20"  class="texto" style="text-align: center">Egresos por Pagos de Subsidios de Cesantia</td>  	
	            	<td height="20"  class="texto" style="text-align: center">
						<input type="text" id="txt_statusProceso41" name="txt_statusProceso41" disabled="true" size="15" style="background-color: transparent; border-width: 0px"/>
					</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="button" name="btn_Procesar10" id="btn_Procesar10" class="btn_limp" value="Procesar" onClick="seteaIdReporte(10);insertToMaestroSivegam();">
	            	</td>
	            	   	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_excel_1" name="ref_excel_1" onClick="seteaFlagXls(10);seteaIdReporte(10);orquestadorXls(10);">Excel</a>
	            	</td> 
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_txt_1" name="ref_txt_1" onClick="seteaFlagXls(10);seteaIdReporte(10);orquestadorTxt(10);">TXT</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_errores_1" name="ref_errores_1" onClick="seteaFlagXls(10);seteaIdReporte(10);orquestadorXlsErr(10);">Excel</a>
	            	</td>        	    	
	       		</tr>
	       		<tr>
	            	<td height="20"  class="texto" style="text-align: center">PLANO42</td>    
	            	<td height="20"  class="texto" style="text-align: center">Reintegros por Pagos de Subsidios de Cesantia</td>
	            	<td height="20"  class="texto" style="text-align: center">
						<input type="text" id="txt_statusProceso42" name="txt_statusProceso42" disabled="true" size="15" style="background-color: transparent; border-width: 0px"/>
					</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="button" name="btn_Procesar11" id="btn_Procesar11" class="btn_limp" value="Procesar" onClick="seteaIdReporte(11);insertToMaestroSivegam();"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_excel_2" name="ref_excel_2" onClick="seteaFlagXls(11);seteaIdReporte(11);orquestadorXls(11);">Excel</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_txt_2" name="ref_txt_2" onClick="seteaFlagXls(11);seteaIdReporte(11);orquestadorTxt(11);">TXT</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_errores_2" name="ref_errores_2" onClick="seteaFlagXls(11);seteaIdReporte(11);orquestadorXlsErr(11);">Excel</a>
	            	</td>
	            </tr>	
	       		<tr>
	            	<td height="20"  class="texto" style="text-align: center">PLANO43</td>    
	            	<td height="20"  class="texto" style="text-align: center">Rendición Egresos</td>
	            	<td height="20"  class="texto" style="text-align: center">
						<input type="text" id="txt_statusProceso43" name="txt_statusProceso43" disabled="true" size="15" style="background-color: transparent; border-width: 0px"/>
					</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="button" name="btn_Procesar12" id="btn_Procesar12" class="btn_limp" value="Procesar" onClick="seteaIdReporte(12);insertToMaestroSivegam();"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center"><!-- generarXls(12); -->
	            		<a href="#" align="center" id="ref_excel_3" name="ref_excel_3" onClick="seteaFlagXls(12);seteaIdReporte(12);orquestadorXls(12)">Excel</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_txt_3" name="ref_txt_3" onClick="seteaFlagXls(12);seteaIdReporte(12);orquestadorTxt(12);">TXT</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_errores_3" name="ref_errores_3" onClick="seteaFlagXls(12);seteaIdReporte(12);orquestadorXlsErr(12);">Excel</a>
	            	</td>
	            </tr>
	       		<tr>
	            	<td height="20"  class="texto" style="text-align: center">PLANO44</td>    
	            	<td height="20"  class="texto" style="text-align: center">Reemplazo de Documentos Caducados y Anulados</td>
	            	<td height="20"  class="texto" style="text-align: center">
						<input type="text" id="txt_statusProceso44" name="txt_statusProceso44" disabled="true" size="15" style="background-color: transparent; border-width: 0px"/>
					</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<input type="button" name="btn_Procesar13" id="btn_Procesar13" class="btn_limp" value="Procesar" onClick="seteaIdReporte(13);insertToMaestroSivegam();"/>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center"><!-- generarXls(13); -->
	            		<a href="#" align="center" id="ref_excel_4" name="ref_excel_4" onClick="seteaFlagXls(13);seteaIdReporte(13);orquestadorXls(13);">Excel</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_txt_4" name="ref_txt_4" onClick="seteaFlagXls(13);seteaIdReporte(13);orquestadorTxt(13);">TXT</a>
	            	</td>
	            	<td height="20"  class="texto" style="text-align: center">
	            		<a href="#" align="center" id="ref_errores_4" name="ref_errores_4" onClick="seteaFlagXls(13);seteaIdReporte(13);orquestadorXlsErr(13);">Excel</a>
	            	</td>
	            </tr>	            	            
	  		</table>
	  	</td>
	  </tr>	
  	  <div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 150px;">
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
