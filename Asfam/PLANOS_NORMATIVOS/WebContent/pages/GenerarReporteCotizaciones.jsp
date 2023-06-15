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
<!-- <script type="text/javascript" language="JavaScript1.2" src="./js/Calendario.js"></script> -->

<script type="text/javascript" language="JavaScript1.2" src="./dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraRepDivPrevDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraReporteCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarPlanosCotizacionesDWR.js"></script>


<script type="text/javascript">

	/**Arreglo que almacena meses del anio*/
	var periodoProcesos = new Array();
	var verificaInvocacion = 0;
	var variableReport = 0;
	var estadoGrilla = "";
	var idMaestroSiv = 0;
	var idMaxMaestroSivegam = 0; // en caso que no se requiera procesar, esta variable obtiene el max id del reporte a generar.
	var idTipoArchivoSivegam = 0; // para obtener el idtipoarchivo, para la generacion de resumenes.
	var confirm016 = 0;
	var confirm017 = 0;
	var confirm018 = 0;
	var confirm019 = 0;
	var reprocesoReporte = 0;
	
	
	function closeSesion(){	
		window.open('', '_self', ''); 	
		window.close();
	}
	
	function asignaValor(a){

		document.GenerarRepCotForm.opcion.value = a;
	}
	
	function enviaFormulario(a){
	
		asignaValor(a);
		document.GenerarRepCotForm.submit();
	}
	
	function cargarDataInformativa(){
		
		//AQUI CARGAR LOS CAMPOS INFORMATIVOS.
		cargarPeriodoInicial();//llama a funcion para cargar periodos iniciales en la grilla.
		
		document.GenerarRepCotForm.txt_Usuario.value = '<%=session.getAttribute("IDAnalista")%>';
		document.GenerarRepCotForm.txt_Fecha.value = '<%=session.getAttribute("FechaSistema")%>';
		
	}
	
	function bloqueaDesbloqueaSif016(){
	
		var link1 = document.getElementById("ref_excel1");
		var txt1 = document.getElementById("ref_plano1");
		var resumen1 = document.getElementById("ref_resumen1");
		var error1 = document.getElementById("ref_error1");

		var procesar1 = document.getElementById("btn_Procesar1");
		
		if(statusSivegam == 3){
			confirm016 = 1;
			link1.disabled = false;
			txt1.disabled = false;
			resumen1.disabled = false;
			error1.disabled = false;
			
		}else{
			link1.disabled = true;
			txt1.disabled = true;
			resumen1.disabled = true;
			error1.disabled = true;

			//link1.onclick = function(){return false};
			//txt1.onclick = function(){return false};
			//resumen1.onclick = function(){return false};
			//error1.onclick = function(){return false};
			
		}

		if(statusSivegam == 4){
			procesar1.disabled = true;
		}else{
			procesar1.disabled = false;
		}
		
	}
	
	function bloqueaDesbloqueaSif017(){
	
		var link2 = document.getElementById("ref_excel2");
		var txt2 = document.getElementById("ref_plano2");
		var resumen2 = document.getElementById("ref_resumen2");
		var error2 = document.getElementById("ref_error2");
		
		var procesar2 = document.getElementById("btn_Procesar2");

		if(statusSivegam == 3){
			confirm017 = 0
			link2.disabled = false;
			txt2.disabled = false;
			resumen2.disabled = false;
			error2.disabled = false;
			
		}else{
			link2.disabled = true;
			txt2.disabled = true;
			resumen2.disabled = true;
			error2.disabled = true;

			//link2.onclick = function(){return false};
			//txt2.onclick = function(){return false};
			//resumen2.onclick = function(){return false};
			//error2.onclick = function(){return false};

		}

		if(statusSivegam == 4){
			procesar2.disabled = true;
		}else{
			procesar2.disabled = false;
		}
		
	}
	
	function bloqueaDesbloqueaSif018(){
	
		var link3 = document.getElementById("ref_excel3");
		var txt3 = document.getElementById("ref_plano3");
		var resumen3 = document.getElementById("ref_resumen3");
		var error3 = document.getElementById("ref_error3");
		
		var procesar3 = document.getElementById("btn_Procesar3");

		if(statusSivegam == 3){
			confirm018 = 1
			link3.disabled = false;
			txt3.disabled = false;
			resumen3.disabled = false;
			error3.disabled = false;
			
		}else{
			link3.disabled = true;
			txt3.disabled = true;
			resumen3.disabled = true;
			error3.disabled = true;

			//link3.onclick = function(){return false};
			//txt3.onclick = function(){return false};
			//resumen3.onclick = function(){return false};
			//error3.onclick = function(){return false};

		}

		if(statusSivegam == 4){
			procesar3.disabled = true;
		}else{
			procesar3.disabled = false;
		}
		
	}
	
	function bloqueaDesbloqueaSif019(){
	
		var link4 = document.getElementById("ref_excel4");
		var txt4 = document.getElementById("ref_plano4");
		var resumen4 = document.getElementById("ref_resumen4");
		var error4 = document.getElementById("ref_error4");
		
		var procesar4 = document.getElementById("btn_Procesar4");

		if(statusSivegam == 3){
			confirm019 = 0
			link4.disabled = false;
			txt4.disabled = false;
			resumen4.disabled = false;
			error4.disabled = false;
			
		}else{
			link4.disabled = true;
			txt4.disabled = true;
			resumen4.disabled = true;
			error4.disabled = true;

			//link4.onclick = function(){return false};
			//txt4.onclick = function(){return false};
			//resumen4.onclick = function(){return false};
			//error4.onclick = function(){return false};

		}

		if(statusSivegam == 4){
			procesar4.disabled = true;
		}else{
			procesar4.disabled = false;
		}
		
	}
	
	

	function cargarGrilla(){
		document.getElementById("datosNomina").innerHTML = 	
		'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
			'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descripción</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Generar Reporte</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Reporte</td>'+          		            	    	
	       	'</tr>'+
	    '</table>';
	}
	
	function cuerpoGrilla(){
	       	'<tr>'+
	            	'<td height="20"  class="texto" style="text-align: center">SIF011</td>'+	          
	            	'<td height="20"  class="texto" style="text-align: center">Egreso de asignaciones familiares del mes por causante</td>'+
	            	'<td height="20"  class="texto" style="text-align: center">Procesado</td>'+
	            	'<td height="20"  class="texto" style="text-align: center">'+
	            		'<input type="button" name="btn_Procesar" id="btn_Procesar" class="btn_limp" value="Procesar"/>'+
	            	'</td>'+
	            	'<td height="20"  class="texto" style="text-align: center">'+
	            		'<a href="#" align="center"" >Excel</a>'+
	            	'</td>'+       		            	    	
	       	'</tr>'+
	       	'<tr>'+
	            	'<td height="20" class="texto" style="text-align: center">SIF012</td>'+	          
	            	'<td height="20" class="texto" style="text-align: center">Egreso de asignaciones familiares retroactivas por causante</td>'+
	            	'<td height="20" class="texto" style="text-align: center">Procesado</td>'+
	            	'<td height="20" class="texto" style="text-align: center">'+
	            		'<input type="button" name="btn_Procesar" id="btn_Procesar" class="btn_limp" value="Procesar"/>'+
	            	'</td>'+
	            	'<td height="20" class="texto" style="color: #fff; text-align: center">'+
	            		'<a href="#" align="center"" >Excel</a>'+    		
	            	'</td>'+         		            	    	
	       	'</tr>'+
	       	'<tr>'+
	            	'<td height="20" class="texto" style="text-align: center">SIF014</td>'+	          
	            	'<td height="20" class="texto" style="text-align: center">Reintegros de asignaciones familiares por causante</td>'+
	            	'<td height="20" class="texto" style="text-align: center">En Proceso</td>'+
	            	'<td height="20" class="texto" style="text-align: center">'+
	            		'<input type="button" name="btn_Procesar" id="btn_Procesar" class="btn_limp" value="Procesar"/>'+
	            	'</td>'+
	            	'<td height="20" class="texto" style="text-align: center">'+
	            		'<a href="#" align="center"" >Excel</a>'+
	            	'</td>'+          		            	    	
	       	'</tr>';	
	}

	/*Funcion que inserta en la tabla maestrosivegam.*/
	function insertToMaestroSivegam(){
		//desincronizado
		DWREngine.setAsync(true);
		idMaestroSiv = 0;
		reprocesoReporte = 0;
		//idMaxMaestroSivegam = 0;
		
		var fechaProceso = '<%=session.getAttribute("FechaSistema")%>';
		var usuarioSivegam = '<%=session.getAttribute("IDAnalista")%>';
		var idTipoReporte = document.GenerarRepCotForm.idReporte.value;
		var anio = parseInt(document.GenerarRepCotForm.dbx_anio.value,10);
		var mes  = parseInt(document.GenerarRepCotForm.dbx_meses.value,10);
		var periodo = anio*100 + mes;
		
		var statusPorProceso;
		/*FRM - AQUI REALIZAR UN CAMBIO DADO QUE VARIABLE STATUS PROCESO INCIDE EN OTROS PROCESOS QUE NO SE HAN INVOCADO.*/
		
		switch(parseInt(idTipoReporte,10)){
			case 4:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			case 5:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			case 6:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			case 7:
				statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
				break;
			default:
			
				return false;			
		}
		//if(statusSivegam == 3){
		if(statusPorProceso == 3 || statusPorProceso == 2){
			verificaInvocacionProcesoCobol(idTipoReporte);
		
		}else{
			GeneraReporteCotizacionesDWR.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodo, function(data){
				
				var idMasterSivegam = null;
				var idFlagFileXls = null;
				var respuesta = null;
				
				respuesta = data.codResultado;
				
				if(respuesta == 0){
				

					document.GenerarRepCotForm.idMaestroSivegam.value = data.idMaestroSivegam;
					idMasterSivegam = document.GenerarRepCotForm.idMaestroSivegam.value;
					//idMaestroSiv = document.GenerarRepCotForm.idMaestroSivegam.value;
					
					//updateStatusProcesoMaestroSivegam(5);
					//if(verificaInvocacion > 0){
					//	verificaInvocacionProcesoCobol(idTipoReporte);
					//}else{
						invocarProcesoCobol(idMasterSivegam);
					//}
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
		GeneraReporteCotizacionesDWR.actualizarStatusSegunPeriodoYProceso(tipoReporte,periodo, function(data){
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
	
	/*Funcion que establece comunicacion con un servidor AS400 e invoca al proceso cobol que llena las tablas para el proceso de SIVEGAM*/
	/*function invocarProcesoCobol(idSecuencia){
	
		DWREngine.setAsync(false);
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anio = fechaSistema.substring(6,10);
		var mes = parseInt(fechaSistema.substring(4,5)) - 1 ; //siempre el mes anterior.
		var periodo = anio + '0'+ mes; 
		var idReport = document.GenerarRepCotForm.idReporte.value; //id se refiere al numero de reporte del cl que se quiere invocar.
		
		//esta variable se debe eliminar una vez automatizado el proceso.
		//var periodo = "201207";
				
		GeneraReporteCotizacionesDWR.llamarProcesoCobol(idSecuencia,idReport, periodo, function(data){
			var resp = null;
			resp = data;
			
			if(resp == "OK"){
			
				alert("Proceso invocado con éxito.");
			
			}else{
			
				alert("No se ha podido iniciar comunicacion y no se ha invocado proceso cobol.");
			}		
		});
		
		DWREngine.setAsync(true);
	}*/

	function verificaInvocacionProcesoCobol(idTipoReporte){
		
		reprocesoReporte = 0;
		var respuesta = confirm("Ud ya ejecuto el proceso. ¿Está seguro que desea volver a ejecutar?");
		if(respuesta == true){
			
			reprocesoReporte = 1;
			obtenerMaxIdMaestroSivegamReproceso(idTipoReporte);

			realizarUpdateStatusAntesDeReprocesar(idSivegMaster);
			//invocarProcesoCobol(idSivegMaster);
		}else{
			
			unificarActualizacionCamposGrilla();
		}
	}

	/**Funcion que hace el update a la tabla maestro sivegam, antes de reprocesar, enviando el status 4, para saber que esta en ejecucion.*/
	function realizarUpdateStatusAntesDeReprocesar(id)
	{
		var statusReproceso = 4;
		//desincronizado
		DWREngine.setAsync(true);
		GeneraRepDivPrevDWR.updateStatusAntesDeReprocesar(id, statusReproceso, function(data){
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
		
	/**Obtiene maximo idmaestrosivegam para un reproceso*/
	var idSivegMaster = 0;
	function obtenerMaxIdMaestroSivegamReproceso(idReporte){
	
		idSivegMaster = 0;
		
		var anio = parseInt(document.GenerarRepCotForm.dbx_anio.value,10);
		var mes  = parseInt(document.GenerarRepCotForm.dbx_meses.value,10);
		var periodoSivegam = anio*100 + mes;
		
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.selectMaxIdMaestroSivegam(idReporte, periodoSivegam, function(data){
			var consulta = null;
			consulta = data.maestro_sivegam;
			
			if(consulta != null){
				idSivegMaster = consulta;

			}	
		});
		DWREngine.setAsync(true);
	}
	
	/**Funcion que invoca un proceso cobol dado el tipo de reporte.*/
	function invocarProcesoCobol(idSecuencia){
		
		DWREngine.setAsync(true);
		
		var periodo = "";
		var mes = document.GenerarRepCotForm.dbx_meses.value;
		var anio = document.GenerarRepCotForm.dbx_anio.value;
		
		if(parseInt(mes,10) < 10){
			mes = '0' + mes;
		}
		
		if(anio.length == 2){
			anio = '20' + anio;
		}else{
			if(anio.length == 1){
				anio = '200' + anio;
			}
		}
		
		periodo = anio + mes;	
		
		var idReport = document.GenerarRepCotForm.idReporte.value; //id se refiere al numero de reporte del cl que se quiere invocar.
		
		//JLGN Se comenta llamada a pantalla de carga
		/*if(parseInt(idReport,10) == 4 || parseInt(idReport,10) == 5 || parseInt(idReport,10) == 6 || parseInt(idReport,10) == 7){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";		
		}*/		
		
		GeneraReporteCotizacionesDWR.llamarProcesoCobol(idSecuencia, idReport, periodo, function(data){
			var resp = null;
			var statusActualizado;
			resp = data.codRespuesta;

			if(resp == 3){
			
				alert("Proceso invocado con éxito.");
				
				//Se debe bloquear el boton procesar.
				/*statusActualizado = data.msgRespuesta;
				actualizaStatus(statusActualizado);*/
				updateStatusProcesoMaestroSivegam(resp, idSecuencia);//se debe actualizar status y fecha de procesamiento de maestro sivegam.
				bloquearBotonProcesar();
				//JLGN Se comenta llamada a pantalla de carga
				//document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				verificaInvocacion += 1;

			}else{
				//JLGN Se comenta llamada a pantalla de carga
				//document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				//JLGN
				if(resp != 4){
					alert("Error en el procesamiento del archivo.");				
					updateStatusProcesoMaestroSivegam(resp, idSecuencia);
				}
				unificarActualizacionCamposGrilla();
			}		
		});
		
		DWREngine.setAsync(true);
	}	

	
	/**Funcion que realiza el update del status de proceso a la tabla maestro sivegam, dada la respuesta arrojada por el proceso cobol*/
	function updateStatusProcesoMaestroSivegam(idRespuestaCobol,idSivegam){
		
		var fechaProceso = '<%=session.getAttribute("FechaSistema")%>';
		var mes = document.GenerarRepCotForm.dbx_meses.value;
		var idMaestroSiv;
		if(parseInt(mes,10) < 10){
			mes = '0' + mes;
		}
		if(reprocesoReporte == 1){
			idMaestroSiv = idSivegam;
		}else{
			idMaestroSiv = document.GenerarRepCotForm.idMaestroSivegam.value;
		}
				
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.updateStatusProcesoSivegam(idRespuestaCobol, idMaestroSiv, fechaProceso, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){

				unificarActualizacionCamposGrilla();
			}else{
				actualizarGrillaReprocesar(mes,idRespuestaCobol);//
				//alert("Error, no se ha actualizado maestro sivegam.");
			}
		});
		DWREngine.setAsync(true);
	}
	
	/*Funcion que retorna el periodo en el nombre del archivo, formado por la eleccion de los combos de mes y año dados por el usuario*/
	function retornaPeriodoYNombreArchivoCboxTxt(idReporte){
		
		var mesTxt = document.GenerarRepCotForm.dbx_meses.value;
		var anioTxt = document.GenerarRepCotForm.dbx_anio.value;
		
		if(anioTxt.length == 2){
			anioTxt = '20' + anioTxt;
		}else{
			if(anioTxt.length == 1){
				anioTxt = '200' + anioTxt;
			}
		}
		
		if(parseInt(mesTxt,10) < 10){
			mesTxt = '0' + mesTxt;
		}
		
		var periodo = anioTxt.toString() + mesTxt.toString();
		var nombreArchivo = "";
		
		switch(parseInt(idReporte,10)){
			case 4:
				nombreArchivo = "10105_NR16_"+ periodo + ".TXT";
				break;
			case 5:
				nombreArchivo = "10105_NR17_"+ periodo + ".TXT";
				break;
			case 6:
				nombreArchivo = "10105_NR18_"+ periodo + ".TXT";
				break;
			case 7:
				nombreArchivo = "10105_NR19_"+ periodo + ".TXT";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
	
	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoCboxXls(idReporte){
		
		var mesTxt = document.GenerarRepCotForm.dbx_meses.value;
		var anioTxt = document.GenerarRepCotForm.dbx_anio.value;
		
		if(anioTxt.length == 2){
			anioTxt = '20' + anioTxt;
		}else{
			if(anioTxt.length == 1){
				anioTxt = '200' + anioTxt;
			}
		}
		
		if(parseInt(mesTxt,10) < 10){
			mesTxt = '0' + mesTxt;
		}
		
		var periodo = anioTxt.toString() + mesTxt.toString();
		var nombreArchivo = "";
		
		switch(parseInt(idReporte,10)){
			case 4:
				nombreArchivo = "10105_NR16_"+ periodo + ".XLSX";
				break;
			case 5:
				nombreArchivo = "10105_NR17_"+ periodo + ".XLSX";
				break;
			case 6:
				nombreArchivo = "10105_NR18_"+ periodo + ".XLSX";
				break;
			case 7:
				nombreArchivo = "10105_NR19_"+ periodo + ".XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
	/*Retorna periodo y nombre del archivo para reporte xls version resumen.*/
	function retornaPeriodoYNombreArchivoCboxRes(idReporte){
		
		var mesTxt = document.GenerarRepCotForm.dbx_meses.value;
		var anioTxt = document.GenerarRepCotForm.dbx_anio.value;
		
		if(anioTxt.length == 2){
			anioTxt = '20' + anioTxt;
		}else{
			if(anioTxt.length == 1){
				anioTxt = '200' + anioTxt;
			}
		}
		
		if(parseInt(mesTxt,10) < 10){
			mesTxt = '0' + mesTxt;
		}
		
		var periodo = anioTxt.toString() + mesTxt.toString();
		var nombreArchivo = "";
		
		switch(parseInt(idReporte,10)){
			case 17:
				nombreArchivo = "10105_NR16_"+ periodo + "_RES.XLSX";
				break;
			case 18:
				nombreArchivo = "10105_NR17_"+ periodo + "_RES.XLSX";
				break;
			case 19:
				nombreArchivo = "10105_NR18_"+ periodo + "_RES.XLSX";
				break;
			case 20:
				nombreArchivo = "10105_NR19_"+ periodo + "_RES.XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
	
	/*Aqui funcion DWR que verifica si un archivo esta en su respectiva carpeta.*/
	var flagArchivo;
	function verificarArchivoEnServer(){
		
		var idTipoReporte = document.GenerarRepCotForm.idFlagTxt.value;
		var nombreArch = retornaPeriodoYNombreArchivoCboxTxt(idTipoReporte);

		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteArchivoSegunPeriodo(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenerarRepCotForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;
	}
	
	/**Funcion que retorna si existe un archivo xls*/
	function verificarExisteXls(){

		var idTipoReporte = document.GenerarRepCotForm.idFlagXLS.value;
		var nombreArch = retornaPeriodoYNombreArchivoCboxXls(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteXLS(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;

			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenerarRepCotForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;	
	}
	
	/*Funcion que verifica si existe resumen*/
	function verificaExisteResumen(){
		
		var idTipoReporte = document.GenerarRepCotForm.idFlagXLS.value;
		var nombreArch = retornaPeriodoYNombreArchivoCboxRes(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteXLS(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;

			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenerarRepCotForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;	
	}
	
	/**Funcion orquestadora de verificacion de archivos txt. Si el archivo ya fue generado en el periodo correspondiente, entonces no lo vuelve a 
	generar, sino que lo descarga de forma automatica desde el servidor, de lo contrario lo genera.*/
	function orquestadorArchivosTxt(idFlagTxt){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioSistema = parseInt(fechaSistema.substring(6,10),10);		
		var mesSistema = parseInt(fechaSistema.substring(3,5),10) - 1;//mes de proceso
		if(mesSistema == 0){
			mesSistema = 12;
			anioSistema = anioSistema - 1;
		}
		
		//AQUI CONTROLAR CONSULTA DE REGENERACION DEL TXT
				
		var mesTxt = document.GenerarRepCotForm.dbx_meses.value;
		var anioTxt = document.GenerarRepCotForm.dbx_anio.value;
		
		if((mesTxt == mesSistema) && (anioTxt == anioSistema))
		{
			generarTxt(idFlagTxt);
		}else{
			//var flg = verificarArchivoEnServer();
			//if(flg == 1){

				//llamar funcion para crear txt.
				generarTxt(idFlagTxt);
			//}else{

				enviaFormulario(3);
				
			//}
		}
		//gc();	
	}
	
	/**Funcion orquestadora de archivos en formato xls. Si el reporte en cuestion ya esta gerenado entonces el sistema lo busca en su respectiva ruta en el server
	y lo descarga directamente, de lo contrario lo genera.*/
	function orquestadorXls(idFlagXls){
	
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioSistema = parseInt(fechaSistema.substring(6,10),10);		
		var mesSistema = parseInt(fechaSistema.substring(3,5),10) - 1;//mes de proceso
		if(mesSistema == 0){
			mesSistema = 12;
			anioSistema = anioSistema - 1;
		}
		
		var mesXls = parseInt(document.GenerarRepCotForm.dbx_meses.value,10);
		var anioXls = parseInt(document.GenerarRepCotForm.dbx_anio.value,10);
		var periodo = anioXls*100 + mesXls;
		var estado = obtenerStatusPorPeriodoyTipoReporte(idFlagXls, periodo);
		if(estado == 3 || estado == 6 ){
			
			var flag = verificarExisteXls();
			if (flag == 0){
				var confirmacion = confirm("El archivo ya existe, ¿Desea generarlo nuevamente?");
			
					if(confirmacion == true){
						generarXls(idFlagXls);
		
					}else{			
						enviaFormulario(3);
					}	
			}else{
				generarXls(idFlagXls);
			}
		}else{
			alert("Antes de generar el documento excel debe procesar el archivo.");
		}	
		//gc();
	}
	
	/**Funcion orquestadora de archivos en xls tipo resumen.*/
	function orquestadorResumenes(idFlagReporteXls){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioSistema = parseInt(fechaSistema.substring(6,10),10);		
		var mesSistema = parseInt(fechaSistema.substring(3,5),10) - 1;//mes de proceso
		if(mesSistema == 0){
			mesSistema = 12;
			anioSistema = anioSistema - 1;
		}
				
		var mesXls = document.GenerarRepCotForm.dbx_meses.value;
		var anioXls = document.GenerarRepCotForm.dbx_anio.value;
		//AQUI CONTROLAR CONSULTA DE REGENERACION DEL RESUMEN
		if((mesXls == mesSistema) && (anioXls == anioSistema))
		{
			generarResumenXLS(idFlagReporteXls);
		}else{
			//var flag = verificaExisteResumen();
			//if(flag == 1){
				generarResumenXLS(idFlagReporteXls);
			//}else{
				//enviaFormulario(3);
			//}
		}	
		//gc();		
	}
	
	
	/**Funcion que elimina un archivo del servidor, cuando se presiona reprocesar.*/
	function eliminarArchivo(idReporte){
	
		var nombreArch = retornaPeriodoYNombreArchivoCboxTxt(idReporte);
				
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteArchivoServer(idReporte,nombreArch, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}else{
				alert("No es posible eliminar el archivo"+ nombreArch +". Verifique que no esté en uso.")
			}
		});
		DWREngine.setAsync(true);
		
	}
	
	function eliminarArchivoXls(idReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoCboxXls(idReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteXlsServer(idReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}else{
				alert("No es posible eliminar el archivo"+ nombreArchivo +". Verifique que no esté en uso.")
			}	
		});
		DWREngine.setAsync(true);
		
	}
	
	/**Funcion que elimina archivo xls del tipo resumen.*/
	function eliminarResumenes(idTipoReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoCboxRes(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteXlsServer(idTipoReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}else{
				alert("No es posible eliminar el archivo"+ nombreArchivo +". Verifique que no esté en uso.")
			}	
		});
		DWREngine.setAsync(true);
	}
	
	/*Funcion que genera el reporte en formato txt.*/
	function generarTxt(idFlagReporteTXT){
		
		obtenerMaxIdMaestroSivegam(idFlagReporteTXT);
		
		if(idMaxMaestroSivegam == 0){
			alert("No es posible generar el plano, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{
			DWREngine.setAsync(false);
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			var usser = '<%=session.getAttribute("IDAnalista")%>';
			
			var periodoTxt = "";
			var mesPeriodo = document.GenerarRepCotForm.dbx_meses.value;
			var anioPeriodo = document.GenerarRepCotForm.dbx_anio.value;
			var Periodo = 0;
			
			if(parseInt(mesPeriodo,10) < 10){
				mesPeriodo = '0' + mesPeriodo;
			}
			
			if(anioPeriodo.length == 2){
				anioPeriodo = '20' + anioPeriodo;
				}else{
				if(anioPeriodo.length == 1){
					anioPeriodo = '200' + anioPeriodo;
				}
			}
			
			periodoTxt = anioPeriodo + mesPeriodo;

			Periodo = parseInt(periodoTxt,10);
			
			GenerarPlanosCotizacionesDWR.consultaRegistros(idFlagReporteTXT, periodoTxt, idMaxMaestroSivegam, Periodo, usser, fechaReporte, function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("reporte generado en formato txt.");
					
					document.GenerarRepCotForm.rutaArchivo.value = data.rutaArchivo;
					
					enviaFormulario(3);
				}
				else{
					alert("No se ha generado el informe.");
				}		
			});
			
			DWREngine.setAsync(true);
		}
		//gc();	
	}
			
	/*Funcion que genera el reporte en archivo .xls usando las librerías de jasperReport.*/
	function generarXls(idFlagXls){
		
		obtenerMaxIdMaestroSivegam(idFlagXls);
		
		DWREngine.setAsync(true);
		
		if(idMaxMaestroSivegam == 0){
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			var usser = '<%=session.getAttribute("IDAnalista")%>';
			
			var periodoReporte = "";
			var mesReporte = document.GenerarRepCotForm.dbx_meses.value;
			var anioReporte = document.GenerarRepCotForm.dbx_anio.value;
			
			if(parseInt(mesReporte,10) < 10){
				mesReporte = '0' + mesReporte;
			}
			
			if(anioReporte.length == 2){
				anioReporte = '20' + anioReporte;
				}else{
				if(anioReporte.length == 1){
					anioReporte = '200' + anioReporte;
				}
			}
				
			periodoReporte = anioReporte + mesReporte;
			
			var flagXls = document.GenerarRepCotForm.idFlagXLS.value;
			
			if(parseInt(flagXls,10) == 4 || parseInt(flagXls,10) == 5 || parseInt(flagXls,10) == 6 || parseInt(flagXls,10) == 7){
				document.getElementById("pantallaDeCarga").style.visibility = "visible";

			}
	 
			GeneraReporteCotizacionesDWR.generarReporteXLS(idFlagXls, periodoReporte, idMaxMaestroSivegam, mesReporte, usser, fechaReporte, function(data){
			
				var resp = null;
				var actualizacionStatus;
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("Reporte generado con éxito.");
					document.GenerarRepCotForm.rutaArchivo.value = data.rutaArchivo;
					actualizacionStatus = data.msgRespuesta;
					//actualizaStatus(actualizacionStatus);
					enviaFormulario(2);
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				}else{
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					alert("Reporte no generado");
				}	
			});
			
			DWREngine.setAsync(true);
		}	
		//gc();
	}
	
	/*Funcion que setea el valor del idReporte, para saber exactamente cual es el reporte que se quiere generar.*/
	
	function seteaIdReporte(n){
		
		document.GenerarRepCotForm.idReporte.value = n;
		variableReport = document.GenerarRepCotForm.idReporte.value;
		actualizarPeriodoGrilla();//dependiendo del valor entregado, se actualiza el campo correspondiente de la grilla.
	}
	
	function seteaFlagXls(n){
	
		document.GenerarRepCotForm.idFlagXLS.value = n;
	}

	function seteaFlagTxt(a){
		document.GenerarRepCotForm.idFlagTxt.value = a;
	}
	
	/*Funcion que actualiza el campo status proceso una vez que se presiono el boton "procesar" y ademas activa el boton excel.*/
	function actualizaStatus(p){
	
		var botonPresionado = document.GenerarRepCotForm.idFlagXLS.value;
		switch(parseInt(botonPresionado)){
			case 4:
				document.GenerarRepCotForm.txt_statusProceso16.value = p;
				break;
			
			case 5:
				document.GenerarRepCotForm.txt_statusProceso17.value = p;
				break;				
			case 6:
				document.GenerarRepCotForm.txt_statusProceso18.value = p;
				break;
			case 7:
				document.GenerarRepCotForm.txt_statusProceso19.value = p;
				
				break;	
			default:
				alert("Ha ocurrido un error y no se ha podido actualizar el estado del proceso.");
		}
				
	}
	
	/*funcion que bloquea boton procesar y desbloquea boton excel*/
	function bloquearBotonProcesar(){
		
		var pressBoton;
		pressBoton = document.GenerarRepCotForm.idReporte.value;

		if(pressBoton == 4){
				document.getElementById("ref_excel1").disabled = false;
				document.getElementById("btn_Procesar1").disabled = false;
		}
		
		if(pressBoton == 5){
				document.getElementById("ref_excel2").disabled = false;
				document.getElementById("btn_Procesar2").disabled = false;
		}
		if(pressBoton == 6){
				document.getElementById("ref_excel3").disabled = false;
				document.getElementById("btn_Procesar3").disabled = false;
		}
		if(pressBoton == 7){
				document.getElementById("ref_excel4").disabled = false;
				document.getElementById("btn_Procesar4").disabled = false;	
		}
	}
		
	/*Funcion que obtiene los valores de las parametricas para asignarlas a los campos de la grilla.*/
	function obtenerDescStatusProceso(){
		
		DWREngine.setAsync(true);
		GeneraReporteCotizacionesDWR.obtenerStatusProceso(function(data){
			document.GenerarRepCotForm.txt_statusProceso16.value = data.descripcion_status_proceso;		
			document.GenerarRepCotForm.txt_statusProceso17.value = data.descripcion_status_proceso;
			document.GenerarRepCotForm.txt_statusProceso18.value = data.descripcion_status_proceso;
			document.GenerarRepCotForm.txt_statusProceso19.value = data.descripcion_status_proceso;
		});
		DWREngine.setAsync(false);
	}	

	/*Funcion que verifica el status del proceso.*/
	var resultado = 0;
	function verificaStatus(periodoProceso, idTipoProceso){
		
		DWREngine.setAsync(true);
		
		GeneraReporteCotizacionesDWR.obtenerStatus(periodoProceso, idTipoProceso, function(data){
		
			var resp = null;
			
			resp = data.codResultado;
			
			if(resp == 0){
				
				resultado = data.statusProceso;

				//document.GenerarRepCotForm.statusProcesoTemp.value = resultado;
				
			}else{
				
				//document.GenerarRepCotForm.statusProcesoTemp.value = 1;
				resultado = 1;
			}
		});
		
		DWREngine.setAsync(false);
	}
	
	/*funcion que valida el status del proceso para bloquear botones de procesamiento*/
	function bloquearPorStatus(){
		
		var link1 = document.getElementById("ref_excel1");
		var link2 = document.getElementById("ref_excel2");
		var link3 = document.getElementById("ref_excel3");
		var link4 = document.getElementById("ref_excel4");

		var botonProceso1 = document.getElementById("btn_Procesar1");
		var botonProceso2 = document.getElementById("btn_Procesar2");
		var botonProceso3 = document.getElementById("btn_Procesar3");
		var botonProceso4 = document.getElementById("btn_Procesar4");
		
		var mesPeriodo = calculaMesPeriodo();

		
		var j=4;
		
		while(j<=7){	
			if(j==4){
				
				verificaStatus(mesPeriodo, j);
				if(resultado == 3){
					botonProceso1.disabled = true;
					link1.disabled = false;
					document.GenerarRepCotForm.txt_statusProceso16.value = "PROCESADO";
				}else{
					cargarDataInformativa();
				}
				
			}
			
			if(j==5){
				
				verificaStatus(mesPeriodo, j);
				if(resultado == 3){
					botonProceso2.disabled = true;
					link2.disabled = false;
					document.GenerarRepCotForm.txt_statusProceso17.value = "PROCESADO";
				}
				else{
					cargarDataInformativa();
				}

			}

			if(j==6){
				
				verificaStatus(mesPeriodo, j);
				if(resultado == 3){
					botonProceso3.disabled = true;
					link3.disabled = false;
					document.GenerarRepCotForm.txt_statusProceso18.value = "PROCESADO";
				}else{
					cargarDataInformativa();
				}
				
			}
			
			if(j==7){
				
				verificaStatus(mesPeriodo, j);
				if(resultado == 3){
					botonProceso4.disabled = true;
					link3.disabled = false;
					document.GenerarRepCotForm.txt_statusProceso19.value = "PROCESADO";
				}else{
					cargarDataInformativa();
				}
				
			}
			j++;								
		}
	
	}
	
	function calculaMesPeriodo(){
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anio = parseInt(fechaSistema.substring(6,10),10);
		var mes = parseInt(fechaSistema.substring(3,5),10);
		var periodo = 0;
		
		periodo = anio*100 + mes;
		
		if(mes == 01){
			periodo = periodo - 89;
		}
		
		return periodo-1;		
	}	

	/*********** MODIFICACIONES VARIAS -- INSERCION DE COMBO PARA PERIODO PROCESO ************************/
	/**Objeto que sirve de ayuda para guardar los meses del año.*/
	function PerProceso(periodo_proceso, descripcion_periodo_proceso){
		this.periodo_proceso = periodo_proceso;
		this.descripcion_periodo_proceso = descripcion_periodo_proceso;
	}
	
	/*Funcion que carga el arreglo de meses*/
	function cargarArregloPeriodoProceso(){
		
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.obtenerDataPeriodoProceso("PeriodoProceso", function(data){
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
		var cmb = document.GenerarRepCotForm.dbx_meses;
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
		
		var periodoSeleccionado = calculaMesPeriodo();
		var verificadorMes = periodoSeleccionado%100;
		var verificadorAño = parseInt((periodoSeleccionado - verificadorMes)/100,10);
		
		document.GenerarRepCotForm.dbx_meses.value = verificadorMes;
		document.GenerarRepCotForm.dbx_anio.value  = verificadorAño;
			
	}	
	
	/**Funcion que carga arreglo de las parametricas.*/
	function cargarArregloParametricas(){
		
		cargarArregloPeriodoProceso();
		cargarArregloAniosProceso();
	}
	
	/**Funcion objeto que contendrá el arreglo de anios.*/
	function periodoAnios(codigo_anio,glosa_anio){
		this.codigo_anio = codigo_anio;
		this.glosa_anio = glosa_anio;
	}
	
	var anios = new Array();
	
	/*Funcion que carga un arreglo con años, desde el 2000 hasta el 2099*/
	var anio_global = 0;
	function cargarArregloAniosProceso(){
		
		var dateSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anioSist = parseInt(dateSistema.substring(6,10),10);

		var cmb = document.GenerarRepCotForm.dbx_anio;
		
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
		
		var cmb = document.GenerarRepCotForm.dbx_anio;
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
	
	/******************** FIN MODIFICACIONES VARIAS *************************/	
	
	/*Funcion que genera el resumen en xls para los archivos de cotizaciones.*/
	function generarResumenXLS(idFlagReporteXls){

		//primero se obtiene el idtipòarchivo correspondiente a la homologacion del tipo de proceso. (devuelve idTipoArchivoSivegam)
		obtenerHomologacionTipoArchivo(idFlagReporteXls)
		
		//se consulta por maximo idmaestro sivegam con el tipo de archivo homologado.
		obtenerMaxIdMaestroSivegam(idTipoArchivoSivegam);
		
		if(idMaxMaestroSivegam == 0){
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			var usser = '<%=session.getAttribute("IDAnalista")%>';
			var periodoReport = "";
			var mesReport = document.GenerarRepCotForm.dbx_meses.value;
			var anioReport = document.GenerarRepCotForm.dbx_anio.value;
			
			if(parseInt(mesReport,10) < 10){
				mesReport = '0' + mesReport;
			}
			
			if(anioReport.length == 2){
				anioReport = '20' + anioReport;
				}else{
				if(anioReport.length == 1){
					anioReport = '200' + anioReport;
				}
			}
			
			periodoReport = anioReport + mesReport;
			
			DWREngine.setAsync(false);
	
			//var flagXlss = document.GenerarRepCotForm.idFlagXls.value;
			GeneraReporteCotizacionesDWR.generarReporteXLS(idFlagReporteXls, periodoReport, idMaxMaestroSivegam, mesReport, usser, fechaReporte, function(data){
			
				var resp = null;
				
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("Reporte generado con éxito.");
					
					document.GenerarRepCotForm.rutaArchivo.value = data.rutaArchivo;
					enviaFormulario(2);
				}else{
					alert("Reporte no generado");
				}	
			});
			
			DWREngine.setAsync(true);
		}					
	//gc();
	}
	
	/**Funcion que contiene los valores iniciales del periodo cuando se ingresa a la pantalla, para procesar reportes.*/
	function cargarPeriodoInicial(){
	
		document.GenerarRepCotForm.txt_periodo16.value = "SIN PERIODO";
		document.GenerarRepCotForm.txt_periodo17.value = "SIN PERIODO";
		document.GenerarRepCotForm.txt_periodo18.value = "SIN PERIODO";
		document.GenerarRepCotForm.txt_periodo19.value = "SIN PERIODO";
	
	}
	
	/**Funcion que actualiza periodo de la grilla cuando se presiona boton procesar //variableReport.*/
	function actualizarPeriodoGrilla(){
		
		//var idReportado = document.GenerarRepCotForm.idReporte.value;
		var periodoGrilla = "";
		var glosaMes = "";
		var mesGrilla = document.GenerarRepCotForm.dbx_meses.value;
		var anioGrilla = document.GenerarRepCotForm.dbx_anio.value;
		
		if(parseInt(mesGrilla,10) < 10){
			mesGrilla = '0' + mesGrilla;
		}
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.obtenerMesesParaPeriodoGrilla(mesGrilla, function(data){
			var consult = null;
			consult = data.descripcion_periodo_proceso;
			
			if(consult != null){
			
				glosaMes = consult;
			
			}else{
				glosaMes = "";
			}	
		});
		DWREngine.setAsync(true);
		
		if(anioGrilla.length == 2){
			anioGrilla = '20' + anioGrilla;
		}else{
			if(anioGrilla.length == 1){
				anioGrilla = '200' + anioGrilla;
			}
		}
		
		periodoGrilla = glosaMes + " " + anioGrilla;
		
		/**Se consulta por el estado, para actualizarlo en estado ("En ejecucion").*/
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.actualizaEstadoGrilla(function(data){
			
			var glosa = null;
			glosa = data.descripcion_status_proceso;
			
			if(glosa != null){
				estadoGrilla = glosa;
			}else{
				estadoGrilla = "";
			}		
		});
		DWREngine.setAsync(true);
		
		switch(parseInt(variableReport,10)){
			case 4:
			 	document.GenerarRepCotForm.txt_periodo16.value = periodoGrilla;
			 	document.GenerarRepCotForm.txt_statusProceso16.value = estadoGrilla;
			 	break;
			case 5:
				document.GenerarRepCotForm.txt_periodo17.value = periodoGrilla;
				document.GenerarRepCotForm.txt_statusProceso17.value = estadoGrilla;
			 	break;
			case 6:
				document.GenerarRepCotForm.txt_periodo18.value = periodoGrilla;
				document.GenerarRepCotForm.txt_statusProceso18.value = estadoGrilla;
			 	break;
			case 7:
				document.GenerarRepCotForm.txt_periodo19.value = periodoGrilla;
				document.GenerarRepCotForm.txt_statusProceso19.value = estadoGrilla;
			 	break; 	 	 	
			default:
				alert("Error. No es posible actualizar el periodo.");
				cargarPeriodoInicial();
		}
	}//
	
	/**Funcion que obtiene el ultimo id maestro sivegam cuando se entra a la aplicacion y no se requiere procesar sino
	generar los archivos xls y txt.*/
	function obtenerMaxIdMaestroSivegam(idReporte){
	
		idMaxMaestroSivegam = 0;
		
		var anio = parseInt(document.GenerarRepCotForm.dbx_anio.value,10);
		var mes  = parseInt(document.GenerarRepCotForm.dbx_meses.value,10);
		var periodoSivegam = anio*100 + mes;
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.selectMaxIdMaestroSivegam(idReporte, periodoSivegam, function(data){
			var consulta = null;
			consulta = data.maestro_sivegam;
			
			if(consulta != null){
				idMaxMaestroSivegam = consulta;

			}	
		});
		DWREngine.setAsync(true);
	}

	/**Obtener tipoArchivo dado el idtipoProceso, para generar los resumenes de division previsional.*/
	function obtenerHomologacionTipoArchivo(idTipoProceso){

		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.selectTipoArchivoHomologado(idTipoProceso, function(data){
			var consulta = null;
			consulta = data.id_tipo_archivo;
			
			if(consulta != 0){
				idTipoArchivoSivegam = consulta;

			}else{
				alert("Error, no existe el id consultado para Tipo Archivo.");
			}
		});
		DWREngine.setAsync(true);
	}

	/**Funcion que actualiza el periodo y el status del proceso al entrar en la pantalla.*/
	var statusSivegam = 0;
	var periodoSivegamConsultado = 0;
	var flagProceso = false;
	function actualizarGrillaAlIniciar(){
		
		var anio = parseInt(document.GenerarRepCotForm.dbx_anio.value,10);
		var mes  = parseInt(document.GenerarRepCotForm.dbx_meses.value,10);
		var periodoSivegam = anio*100 + mes;
		
		//verifica que exista un idmax de maestro sivegam, de lo contrario no hay data.
		if(idMaxMaestroSivegam != 0){

			DWREngine.setAsync(false);
			GeneraReporteCotizacionesDWR.obtenerInformacionAActualizar(idMaxMaestroSivegam, function(data){
				var resp = null;
				resp = data;
					
				if(resp != null){
					periodoSivegamConsultado = data.periodo_proceso;
					statusSivegam = data.status_proceso;	
				}
					
			});
			DWREngine.setAsync(true);
		
		}else{
			periodoSivegamConsultado = 0;
			statusSivegam = 1;

		}			
	}
	
	/**Funcion que actualiza los campos de la grilla.*/
	function actualizarCamposSif016(){
		
		var idReporteInicial = 4;
		var anio_siv = document.GenerarRepCotForm.dbx_anio.value;
		
		obtenerMaxIdMaestroSivegam(idReporteInicial);

		actualizarGrillaAlIniciar();
		
		DWREngine.setAsync(false);
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado,10)%100;
		GeneraReporteCotizacionesDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null){
				
				if(data.glosaPeriodoProcesoMes == "SIN PERIODO"){
					document.GenerarRepCotForm.txt_periodo16.value = data.glosaPeriodoProcesoMes;
				}else{
					document.GenerarRepCotForm.txt_periodo16.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepCotForm.dbx_anio.value;
				}

				document.GenerarRepCotForm.txt_statusProceso16.value = data.glosaStatusProceso;
				
				bloqueaDesbloqueaSif016();
			}
			
		});
		DWREngine.setAsync(true);			
	}
	
	function actualizarCamposSif017(){
		
		var idReporteInicial = 5;
		var anio_siv = document.GenerarRepCotForm.dbx_anio.value;
		
		obtenerMaxIdMaestroSivegam(idReporteInicial);
		actualizarGrillaAlIniciar();
		
		DWREngine.setAsync(false);
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado,10)%100;
		GeneraReporteCotizacionesDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null)
			{
				if(data.glosaPeriodoProcesoMes == "SIN PERIODO"){
					document.GenerarRepCotForm.txt_periodo17.value = data.glosaPeriodoProcesoMes;
				}else{
					document.GenerarRepCotForm.txt_periodo17.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepCotForm.dbx_anio.value;
				}
				
				document.GenerarRepCotForm.txt_statusProceso17.value = data.glosaStatusProceso;
				
				bloqueaDesbloqueaSif017();
			}
			
		});
		DWREngine.setAsync(true);
	}
	
	function actualizarCamposSif018(){

		var idReporteInicial = 6;
		var anio_siv = document.GenerarRepCotForm.dbx_anio.value;
		
		obtenerMaxIdMaestroSivegam(idReporteInicial);

		actualizarGrillaAlIniciar();
		
		DWREngine.setAsync(false);
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado,10)%100;
		GeneraReporteCotizacionesDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null)
			{
				if(data.glosaPeriodoProcesoMes == "SIN PERIODO"){
					document.GenerarRepCotForm.txt_periodo18.value = data.glosaPeriodoProcesoMes;
				}else{
					document.GenerarRepCotForm.txt_periodo18.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepCotForm.dbx_anio.value;
				}
				
				document.GenerarRepCotForm.txt_statusProceso18.value = data.glosaStatusProceso;	
				
				bloqueaDesbloqueaSif018();
			}
			
		});
		DWREngine.setAsync(true);			
	} //obtenerDescStatusProceso();
	
	function actualizarCamposSif019(){

		var idReporteInicial = 7;
		var anio_siv = document.GenerarRepCotForm.dbx_anio.value;
		
		obtenerMaxIdMaestroSivegam(idReporteInicial);

		actualizarGrillaAlIniciar();
		
		DWREngine.setAsync(false);
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado,10)%100;
		GeneraReporteCotizacionesDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null)
			{
				if(data.glosaPeriodoProcesoMes == "SIN PERIODO"){
					document.GenerarRepCotForm.txt_periodo19.value = data.glosaPeriodoProcesoMes;
				}else{
					document.GenerarRepCotForm.txt_periodo19.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepCotForm.dbx_anio.value;
				}
				
				document.GenerarRepCotForm.txt_statusProceso19.value = data.glosaStatusProceso;	
				
				bloqueaDesbloqueaSif019();
			}
			
		});
		DWREngine.setAsync(true);			
	}
		
	function unificarActualizacionCamposGrilla(){
		
		var mes = document.GenerarRepCotForm.dbx_meses.value;
		var anio = document.GenerarRepCotForm.dbx_anio.value;
		if(mes == 0 || anio == 0){
			selectedItemCombo();
			alert('Seleccione un mes y año antes de procesar.');
		}else{


			actualizarCamposSif016();
			actualizarCamposSif017();
			actualizarCamposSif018();
			actualizarCamposSif019();
		
		}
	}
	
	function actualizarGrillaReprocesar(mes,statusCobol){
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null){
				document.GenerarRepCotForm.txt_periodo19.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepCotForm.dbx_anio.value;
				document.GenerarRepCotForm.txt_statusProceso19.value = data.glosaStatusProceso;	
				bloqueaDesbloqueaSif019();
			}
			
		});
		DWREngine.setAsync(true);
	}
	
	function cargarEnOnload(){
		
		cargarDataInformativa();
		cargarArregloParametricas();
		obtenerComboPeriodoProceso();
		selectedItemCombo();
		unificarActualizacionCamposGrilla();
	}
	
	function validacionGeneracionEXCELsif016(){
		
		var anio = parseInt(document.GenerarRepCotForm.dbx_anio.value,10);
		var mes  = parseInt(document.GenerarRepCotForm.dbx_meses.value,10);
		var periodoMes = anio*100 + mes;
		var idTipoReporte11 = obtenerStatusPorPeriodoyTipoReporte(1,periodoMes);
		var idTipoReporte12 = obtenerStatusPorPeriodoyTipoReporte(2,periodoMes);
				
		if ((idTipoReporte11 == 3) && (idTipoReporte12 == 3))
		{
		    orquestadorXls(4);
		}
		else {
		 	alert ("No se puede generar el archivo porque se debe procesar el archivo 11, y el archivo 12");

		}
	}
	
	function validacionGeneracionPROCsif016(){
		
		var anio = parseInt(document.GenerarRepCotForm.dbx_anio.value,10);
		var mes  = parseInt(document.GenerarRepCotForm.dbx_meses.value,10);
		var periodoMes = anio*100 + mes;
		var idTipoReporte11 = obtenerStatusPorPeriodoyTipoReporte(1,periodoMes);
		var idTipoReporte12 = obtenerStatusPorPeriodoyTipoReporte(2,periodoMes);
				
		if ((idTipoReporte11 == 3) && (idTipoReporte12 == 3))
		{
			seteaIdReporte(4);
		    insertToMaestroSivegam(4);
		}
		else {
		 	alert ("No se puede procesar el archivo porque se debe procesar previamente el archivo 11, y el archivo 12");

		}
	}
	
	function controlErrores(){
		alert("TODO: FUNCION DE CONTROL DE ERRORES");
	}
	
	function invocarProcesoCobolErrores(idSecuencia, idReport){
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		DWREngine.setAsync(false);
		var aux = 0;
		var periodo = "";
		var mes = document.GenerarRepCotForm.dbx_meses.value;
		var anio = document.GenerarRepCotForm.dbx_anio.value;
		
		if(mes < 10){
			mes = '0' + mes;
		}
		
		periodo = anio + mes;
		
		
			
		GeneraRepDivPrevDWR.llamarProcesoCobolCLVAL(idReport, periodo, function(data){
			var resp = null;
			resp = data.codRespuesta;
			if(resp == 3){
				aux = 0;
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				alert("Proceso invocado con éxito.");
				
				
				
			}else{
				aux = 1;
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				alert("Error en el procesamiento del archivo.");
				
			}		
		});
		DWREngine.setAsync(true);
		var idMax = obtenerMaxIdMaestroSivegam(idReport);
		
		if((idMaxMaestroSivegam == 0)||(aux != 0)){
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{		
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			var usser = '<%=session.getAttribute("IDAnalista")%>';
			var mesReport = parseInt(periodo,10);
			
			
			var flagXlss = document.GenerarRepCotForm.idFlagXLS.value;
			GeneraRepDivPrevDWR.generarReporteXLS(idSecuencia, periodo, idMaxMaestroSivegam, mesReport, usser, fechaReporte, function(data){
			
				var resp = null;
				
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("Reporte generado con éxito.");
					document.GenerarRepCotForm.rutaArchivo.value = data.rutaArchivo;
					enviaFormulario(2);
				}else{
					alert("Reporte no generado");
				}	
			});
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					
			
		}	
	}	
	
	//JLGN Ejecuta cada cierto tiempo la funcion javascript	
	setInterval('unificarActualizacionCamposGrilla()',30000);
	
</script>
</head>
<body onload="cargarEnOnload();">
<html:form action="/genRepCot.do" method="post" enctype="multipart/form-data">

  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idReporte" value="0"> 
  <input type="hidden" name="idMaestroSivegam" value=" ">   
  <input type="hidden" name="idFlagXLS" value="0">
  <input type="hidden" name="idFlagTxt" value="0">
  <input type="hidden" name="rutaArchivo" value="0">
  <input type="hidden" name="statusProcesoTemp" value="0">
          
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
					<td><strong><p1>GENERACION DE REPORTES DE COTIZACIONES</p1></strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario" disabled="true" size="10"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" id="txt_Fecha" disabled="true" size="10"/></td>				
				</tr>
			</table>
		   </td>	
		</tr>
				<tr>
			<td>
				<p><p2></p>
				<p></p>
				<p>1. Periodo Proceso<p2></p>
				<p>
			</td>
		</tr>
		<tr>	
		<table>
			<tr>
			</tr>
				<tr>
					<td>Mes</td>
					<td>
						<html:select property="dbx_meses" styleClass="combobox" value="0" onblur="unificarActualizacionCamposGrilla();" onchange="unificarActualizacionCamposGrilla();">
							<html:option value="0">Seleccione </html:option>
						</html:select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Año<td>
					<td>
						<html:select property="dbx_anio" styleClass="combobox" value="0" onblur="unificarActualizacionCamposGrilla();" onchange="unificarActualizacionCamposGrilla();">
							<html:option value="0">Seleccione </html:option>
						</html:select>
					</td>			
					<td></td>
					<td></td>
				</tr>

		</table>
		</tr>
		<tr>
			<td>
				<p><p2></p>
				<p></p>
				<p>2. Reportes División Recuperación Cotizaciones<p2></p>
				<p>
			</td>
		</tr>
	  </table>
  
  	 <tr>
  	 	<td width="100%">
		<table width="1225" align="center" cellpadding="0" cellspacing="1">
			<tr>
				<td height="20" width="50" valign="middle" bgcolor="#7BAEBD"
					class="texto" style="color: #fff; text-align: center">Código</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="490">Descripción</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="130">Ejecutar
				Proceso</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="75">Periodo</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="124">Estado</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="117">Generar Reporte</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="99">Generar Plano</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="99">Descargar Errores</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="123">Generar	Resumen</td>
			</tr>
			<tr>
				<td height="20" class="texto" style="text-align: center">SIF016</td>
				<td height="20" class="texto" style="text-align: left" width="490">Detalle
				de compensación por empleador</td>
				<td height="20" class="texto" style="text-align: center" width="130">
				<input type="button" name="btn_Procesar1" id="btn_Procesar1"
					class="btn_limp" value="Procesar"
					onClick="validacionGeneracionPROCsif016();" /></td>
				<td height="20" class="texto" style="text-align: center" width="75">
				<input type="text" id="txt_periodo16" name="txt_periodo16"
					disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="124">
				<input type="text" id="txt_statusProceso16"
					name="txt_statusProceso16" disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="117">
				<a href="#" align="center" id="ref_excel1" name="ref_excel1"
					onClick="seteaFlagXls(4);validacionGeneracionEXCELsif016();">Excel</a>
				<!-- <a href="#" align="center" id="ref_excel1" name="ref_excel1" onClick="seteaFlagXls(4);generarXls(4);">Excel</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_plano1" id="ref_plano1"
					onClick="seteaFlagTxt(4);orquestadorArchivosTxt(4);">Txt</a> <!-- <a href="#" align="center" name="ref_plano1" id="ref_plano1" onClick="seteaFlagTxt(4);generarTxt(4);">Txt</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_error1" id="ref_error1"
					onClick="seteaFlagXls(24);invocarProcesoCobolErrores(24,4);">Error</a> <!-- <a href="#" align="center" name="ref_plano1" id="ref_plano1" onClick="seteaFlagTxt(4);generarTxt(4);">Txt</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="123">
				<a href="#" align="center" name="ref_resumen1" id="ref_resumen1"
					onClick="seteaFlagXls(17);orquestadorResumenes(17);">Resumen</a> <!-- <a href="#" align="center" name="ref_resumen1" id="ref_resumen1" onClick="seteaFlagXls(17);generarResumenXLS(17);">Resumen</a> -->
				</td>
			</tr>
			<tr>
				<td height="20" class="texto" style="text-align: center">SIF017</td>
				<td height="20" class="texto" style="text-align: left" width="490">Rendición
				egresos por saldo a favor empleador (Safem)</td>
				<td height="20" class="texto" style="text-align: center" width="130">
				<input type="button" name="btn_Procesar2" id="btn_Procesar2"
					class="btn_limp" value="Procesar"
					onClick="seteaIdReporte(5);insertToMaestroSivegam();" /></td>
				<td height="20" class="texto" style="text-align: center" width="75">
				<input type="text" id="txt_periodo17" name="txt_periodo17"
					disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="124">
				<input type="text" id="txt_statusProceso17"
					name="txt_statusProceso17" disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto"
					style="color: #fff; text-align: center" width="117"><a
					href="#" align="center" id="ref_excel2" name="ref_excel2"
					onClick="seteaFlagXls(5);orquestadorXls(5);">Excel</a> <!-- <a href="#" align="center" id="ref_excel2" name="ref_excel2" onClick="seteaFlagXls(5);generarXls(5);">Excel</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_plano2" id="ref_plano2"
					onClick="seteaFlagTxt(5);orquestadorArchivosTxt(5);">Txt</a> <!-- <a href="#" align="center" name="ref_plano2" id="ref_plano2" onClick="seteaFlagTxt(5);generarTxt(5);">Txt</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_error2" id="ref_error2"
					onClick="seteaFlagXls(25);invocarProcesoCobolErrores(25,5);">Error</a> <!-- <a href="#" align="center" name="ref_plano2" id="ref_plano2" onClick="seteaFlagTxt(5);generarTxt(5);">Txt</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="123">
				<a href="#" align="center" name="ref_resumen2" id="ref_resumen2"
					onClick="seteaFlagXls(18);orquestadorResumenes(18);">Resumen</a> <!-- <a href="#" align="center" name="ref_resumen2" id="ref_resumen2" onClick="seteaFlagXls(18);generarResumenXLS(18);">Resumen</a> -->
				</td>
			</tr>
			<tr>
				<td height="20" class="texto" style="text-align: center">SIF018</td>
				<td height="20" class="texto" style="text-align: left" width="490">Documentos
				Safem emitidos en reemplazo de documentos caducados</td>
				<td height="20" class="texto" style="text-align: center" width="130">
				<input type="button" name="btn_Procesar3" id="btn_Procesar3"
					class="btn_limp" value="Procesar"
					onClick="seteaIdReporte(6);insertToMaestroSivegam();" /></td>
				<td height="20" class="texto" style="text-align: center" width="75">
				<input type="text" id="txt_periodo18" name="txt_periodo18"
					disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="124">
				<input type="text" id="txt_statusProceso18"
					name="txt_statusProceso18" disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="117">
				<a href="#" align="center" id="ref_excel3" name="ref_excel3"
					onClick="seteaFlagXls(6);orquestadorXls(6);">Excel</a> <!-- <a href="#" align="center" id="ref_excel3" name="ref_excel3" onClick="seteaFlagXls(6);generarXls(6);">Excel</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_plano3" id="ref_plano3"
					onClick="seteaFlagTxt(6);orquestadorArchivosTxt(6);">Txt</a> <!-- <a href="#" align="center" name="ref_plano3" id="ref_plano3" onClick="seteaFlagTxt(6);generarTxt(6);">Txt</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_error3" id="ref_error3"
					onClick="seteaFlagXls(26);invocarProcesoCobolErrores(26,6);">Error</a> <!-- <a href="#" align="center" name="ref_plano3" id="ref_plano3" onClick="seteaFlagTxt(6);generarTxt(6);">Txt</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="123">
				<a href="#" align="center" name="ref_resumen3" id="ref_resumen3"
					onClick="seteaFlagXls(19);orquestadorResumenes(19);">Resumen</a> <!-- <a href="#" align="center" name="ref_resumen3" id="ref_resumen3" onClick="seteaFlagXls(19);generarResumenXLS(19);">Resumen</a> -->
				</td>
			</tr>
			<tr>
				<td height="20" class="texto" style="text-align: center">SIF019</td>
				<td height="20" class="texto" style="text-align: left" width="490">Detalle
				de documentos caducados incluidos en los documentos del archivo 18</td>
				<td height="20" class="texto" style="text-align: center" width="130">
				<input type="button" name="btn_Procesar4" id="btn_Procesar4"
					class="btn_limp" value="Procesar"
					onClick="seteaIdReporte(7);insertToMaestroSivegam();" /></td>
				<td height="20" class="texto" style="text-align: center" width="75">
				<input type="text" id="txt_periodo19" name="txt_periodo19"
					disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="124">
				<input type="text" id="txt_statusProceso19"
					name="txt_statusProceso19" disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="117">
				<a href="#" align="center" id="ref_excel4" name="ref_excel4"
					onClick="seteaFlagXls(7);orquestadorXls(7);">Excel</a> <!-- <a href="#" align="center" id="ref_excel4" name="ref_excel4" onClick="seteaFlagXls(7);generarXls(7);">Excel</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_plano4" id="ref_plano4"
					onClick="seteaFlagTxt(7);orquestadorArchivosTxt(7);">Txt</a> <!-- <a href="#" align="center" name="ref_plano4" id="ref_plano4" onClick="seteaFlagTxt(7);generarTxt(7);">Txt</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="99">
				<a href="#" align="center" name="ref_error4" id="ref_error4"
					onClick="seteaFlagXls(27);invocarProcesoCobolErrores(27,7);">Error</a> <!-- <a href="#" align="center" name="ref_plano4" id="ref_plano4" onClick="seteaFlagTxt(7);generarTxt(7);">Txt</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="123">
				<a href="#" align="center" name="ref_resumen4" id="ref_resumen4"
					onClick="seteaFlagXls(20);orquestadorResumenes(20);">Resumen</a> <!-- <a href="#" align="center" name="ref_resumen4" id="ref_resumen4" onClick="seteaFlagXls(20);generarResumenXLS(20);">Resumen</a> -->
				</td>
			</tr>
		</table>
		</td>
		</tr>    
  	   <div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 150px;">
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
