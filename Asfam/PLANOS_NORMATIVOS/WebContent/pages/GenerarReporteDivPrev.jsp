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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraRepDivPrevDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarPlanosDivisionPrevisionalDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraReporteCotizacionesDWR.js"></script>

<script type="text/javascript">
	
    
	/**Arreglo que almacena meses del anio*/
	var periodoProcesos = new Array();
	var variableReport = 0;
	var estadoGrilla = "";
	var verificaInvocacion = 0;
	var idMaestroSiv = 0;
	var idMaxMaestroSivegam = 0; // en caso que no se requiera procesar, esta variable obtiene el max id del reporte a generar.
	var idTipoArchivoSivegam = 0; // para obtener el idtipoarchivo, para la generacion de resumenes.
	var reprocesoReporte = 0;
	//JLGN
	var varPantallaCarga = 0;//Muestra pantalla de Carga 0 = NO , 1 = SI
	
	function closeSesion(){	
		window.open('', '_self', ''); 	
		window.close();
	}
	
	function asignaValor(a){

		document.GenerarRepDivPrevForm.opcion.value = a;
	}
	
	function enviaFormulario(a){
		asignaValor(a);
		document.GenerarRepDivPrevForm.submit();
	}
	
	function cargarDataInformativa(){
		
		//AQUI CARGAR LOS CAMPOS INFORMATIVOS.
		
		cargarPeriodoInicial();//Aqui se cargan los periodos iniciales al entrar en la pantalla.
		document.GenerarRepDivPrevForm.txt_Usuario.value = '<%=session.getAttribute("IDAnalista")%>';
		document.GenerarRepDivPrevForm.txt_Fecha.value = '<%=session.getAttribute("FechaSistema")%>';
	}
	
	/**Funcion que bloquea o desbloquea los botones, dado el status del proceso al iniciar la pantalla.*/
	function bloqueaDesbloqueaSif011(){
		
		var link1 = document.getElementById("ref_excel11");
		var txt1 = document.getElementById("ref_plano11");
		var resumen1 = document.getElementById("ref_resumen11");
		var error1 = document.getElementById("ref_error11");
		var procesar1 = document.getElementById("btn_Procesar11");
		if(statusSivegam == 3){
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
	
	
	function bloqueaDesbloqueaSif012(){
		
		var link2 = document.getElementById("ref_excel12");
		var txt2 = document.getElementById("ref_plano12");
		var resumen2 = document.getElementById("ref_resumen12");
		var error2 = document.getElementById("ref_error12");
		var procesar2 = document.getElementById("btn_Procesar12");
		
		if(statusSivegam == 3){
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
	
	function bloqueaDesbloqueaSif014(){
	
		var link3 = document.getElementById("ref_excel14");
		var txt3 = document.getElementById("ref_plano14");
		var resumen3 = document.getElementById("ref_resumen14");
		var error3 = document.getElementById("ref_error14");
		var procesar3 = document.getElementById("btn_Procesar14");
		
		if(statusSivegam == 3){
			
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
	
	function bloquearDesbloquearBotones(){
		
		var link1 = document.getElementById("ref_excel11");
		var link2 = document.getElementById("ref_excel12");
		var link3 = document.getElementById("ref_excel14");

		var txt1 = document.getElementById("ref_plano11");
		var txt2 = document.getElementById("ref_plano12");
		var txt3 = document.getElementById("ref_plano14");
		
		var resumen1 = document.getElementById("ref_resumen11");
		var resumen2 = document.getElementById("ref_resumen12");
		var resumen3 = document.getElementById("ref_resumen14");
		
		var error1 = document.getElementById("ref_error11");
		var error2 = document.getElementById("ref_error12");
		var error3 = document.getElementById("ref_error14");
		
		if(statusSivegam == 3){
		
			//desbloqueo de boton excel al iniciar pantalla
			link1.disabled = false;
			link2.disabled = false;
			link3.disabled = false;
			
			//desbloqueo de botones txt al iniciar pantalla.
			txt1.disabled = false;
			txt2.disabled = false;
			txt3.disabled = false;
			
			//desbloqueo de boton resumen al iniciar pantalla. 
			resumen1.disabled = false;
			resumen2.disabled = false;
			resumen3.disabled = false;
			
			//desbloqueo de boton error al iniciar pantalla. 
			error1.disabled = false;
			error2.disabled = false;
			error3.disabled = false;
			
		}else{
			//bloqueo de boton excel al iniciar pantalla
			link1.disabled = true;
			link2.disabled = true;
			link3.disabled = true;
			
			//bloqueo de botones txt al iniciar pantalla.
			txt1.disabled = true;
			txt2.disabled = true;
			txt3.disabled = true;
			
			//bloqueo de boton resumen al iniciar pantalla. 
			resumen1.disabled = true;
			resumen2.disabled = true;
			resumen3.disabled = true;

			//bloqueo de boton error al iniciar pantalla. 
			error1.disabled = true;
			error2.disabled = true;
			error3.disabled = true;
		}
	
		//return false;
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

	/*Conjunto de funciones que emulan y construyen la grilla.*/
	/*Funcion que obtiene la cabecera de la grilla.*/
	function obtenerHeaderTabla()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Códigoo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descripción</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Generar Reporte</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Descargar Reporte</td>'+
				'</tr>';
	}
	
	/*Funcion que construye el cuerpo de la grilla.*/
	function cuerpoGrilla(){
	
	    return  "<tr>"+
	            	"<td height='20'  class='texto' style='text-align: center'>SIF011</td>"+	          
	            	"<td height='20'  class='texto' style='text-align: center'>Egreso de asignaciones familiares del mes por causante</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>Procesado</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>"+
	            		"<input type='button' name='btn_Procesar' id='btn_Procesar' class='btn_limp' value='Procesar'/>"+
	            	"</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>"+
	            		"<a href='#' align='center' >Excel</a>"+
	            	"</td>"+       		            	    	
	       	"</tr>"+
	       	"<tr>"+
	            	"<td height='20'  class='texto' style='text-align: center'>SIF012</td>"+	          
	            	"<td height='20'  class='texto' style='text-align: center'>Egreso de asignaciones familiares retroactivas por causante</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>Procesado</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>"+
	            		"<input type='button' name='btn_Procesar' id='btn_Procesar' class='btn_limp' value='Procesar'/>"+
	            	"</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>"+
	            		"<a href='#' align='center' >Excel</a>"+   		
	            	"</td>"+         		            	    	
	       	"</tr>"+
	       	"<tr>"+
	            	"<td height='20'  class='texto' style='text-align: center'>SIF014</td>"+	          
	            	"<td height='20'  class='texto' style='text-align: center'>Reintegros de asignaciones familiares por causante</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>En Proceso</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>"+
	            		"<input type='button' name='btn_Procesar' id='btn_Procesar' class='btn_limp' value='Procesar'/>"+
	            	"</td>"+
	            	"<td height='20'  class='texto' style='text-align: center'>"+
	            		"<a href='#' align='center'>Excel</a>"+
	            	"</td>"+          		            	    	
	       	"</tr>";

	}
	
	/*Funcion que muestra la grilla, esta se carga con los datos al iniciar la pantalla de generar reportes para division previsional*/
	function cargaDatosEnGrilla(){
	
		//document.getElementById("datosNomina").style.display = "";
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + cuerpoGrilla + "</table>";
		document.getElementById("datosNomina").style.visibility = "visible";
	}
	/*FIN Conjunto de funciones que emulan y construyen la grilla.*/


	/*Funcion que inserta en la tabla maestrosivegam.*/
	
	function insertToMaestroSivegam(){
		//desincronizado
		DWREngine.setAsync(true);
		
		idMaestroSiv = 0;
		var fechaProceso = '<%=session.getAttribute("FechaSistema")%>';
		var usuarioSivegam = '<%=session.getAttribute("IDAnalista")%>';
		var idTipoReporte = document.GenerarRepDivPrevForm.idReporte.value;
		var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
		var statusPorProceso;
		/*FRM - AQUI REALIZAR UN CAMBIO DADO QUE VARIABLE STATUS PROCESO INCIDE EN OTROS PROCESOS QUE NO SE HAN INVOCADO.*/
		
		if((idTipoReporte == 1)||(idTipoReporte == 2)||(idTipoReporte == 3)){
			statusPorProceso = obtenerStatusPorPeriodoyTipoReporte(idTipoReporte,periodo);
		}else{
			return false;			
		}
		
		if(statusPorProceso == 3 || statusPorProceso == 2){
			verificaInvocacionProcesoCobol(idTipoReporte);
		
		}else{	
		    
		    //var aux = 1;
		    var asfam = 0;
		    var status11 = obtenerStatusPorPeriodoyTipoReporte(1,periodo);
		    var status14 = obtenerStatusPorPeriodoyTipoReporte(3,periodo);

		   	if((status11==0)&&(status14==0)){ // Inc. Proyecto if((status11==1)&&(status14==1)){
//		   		cobolSIP05ASFAM(periodo);
//		   		aux = document.GenerarRepDivPrevForm.statusAsfam.value;
		   		asfam = 1;
		   	}

			//if(aux == 0){
				//alert("antes de insertar en maestro sivegam");
				GeneraRepDivPrevDWR.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodo, function(data){
				//alert("despues de insertar en maestro sivegam");
					
					var idMasterSivegam = null;
					var respuesta = null;
					
					respuesta = data.codResultado;
					
					if(respuesta == 0){
					
						//alert("Se han insertado los datos, invocando proceso cobol...");
						document.GenerarRepDivPrevForm.idMaestroSivegam.value = data.idMaestroSivegam;
						idMasterSivegam = document.GenerarRepDivPrevForm.idMaestroSivegam.value;
						idMaestroSiv = document.GenerarRepDivPrevForm.idMaestroSivegam.value;
						
						//if(verificaInvocacion > 0){
							//verificaInvocacionProcesoCobol(idTipoReporte);
						//}else{
							if(varPantallaCarga == 1){ 
								invocarProcesoCobol(idMasterSivegam, asfam);
							}else{
								invocarProcesoCobol2(idMasterSivegam, asfam);
							}
								
						//}
						unificarActualizacionCamposGrilla();
					}
				});
				
				DWREngine.setAsync(true);
//			}else{
//				alert("Hubo un error al procesar.");
//				document.GenerarRepDivPrevForm.txt_statusProceso11.value = "PENDIENTE";
//				document.GenerarRepDivPrevForm.txt_statusProceso14.value = "PENDIENTE";
			//}
			
			//unificarActualizacionCamposGrilla();
		}	
	}

	/**Funcion que obtiene el status del proceso, para verificar si es necesario reprocesar o simplemente */
	var statusProcesoSivegam = 0;
	function obtenerStatusPorPeriodoyTipoReporte(tipoReporte, periodo){
		
		statusProcesoSivegam = 0;
		
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.actualizarStatusSegunPeriodoYProceso(tipoReporte,periodo, function(data){
		//GeneraReporteCotizacionesDWR.actualizarStatusSegunPeriodoYProceso(tipoReporte,periodo, function(data){
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
	
	/**Funcion que re-invoca proceso cobol*/
	function verificaInvocacionProcesoCobol(idTipoReporte){
		
		reprocesoReporte = 0;
		var respuesta;
		if((idTipoReporte != 1)&&(idTipoReporte != 2)){
			respuesta = confirm("Ud ya ejecuto el proceso. ¿Está seguro que desea volver a ejecutar?");
		}else{
			respuesta = true;
		}
		if(respuesta == true){
			
			reprocesoReporte = 1;
			obtenerMaxIdMaestroSivegamReproceso(idTipoReporte);
			realizarUpdateStatusAntesDeReprocesar(idSivegMaster);
		}else{
			
			unificarActualizacionCamposGrilla();
		}
	
	}

	/**Funcion que hace el update a la tabla maestro sivegam, antes de reprocesar, enviando el status 4, para saber que esta en ejecucion.*/
	function realizarUpdateStatusAntesDeReprocesar(id)
	{
		var statusReproceso = 4;
		DWREngine.setAsync(true);
		GeneraRepDivPrevDWR.updateStatusAntesDeReprocesar(id, statusReproceso, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				invocarProcesoCobol(id,0);
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
		var mes = parseInt(document.GenerarRepDivPrevForm.dbx_meses.value,10);
		var anio = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10);
		var periodoSivegam = anio*100 + mes;
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.selectMaxIdMaestroSivegam(idReporte, periodoSivegam, function(data){
			
			var consulta = null;
			consulta = data.maestro_sivegam;
			
			if(consulta != null){
				idSivegMaster = consulta;
				//alert(idMaxMaestroSivegam);
			}	
		});
		DWREngine.setAsync(true);
	}
			
	/*Funcion que establece comunicacion con un servidor AS400 e invoca al proceso cobol que llena las tablas para el proceso de SIVEGAM*/
	function invocarProcesoCobol(idSecuencia, asfam){
		//desincronizado
		DWREngine.setAsync(true);
		
		var periodo = "";
		var mes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anio = document.GenerarRepDivPrevForm.dbx_anio.value;
		
		if(mes < 10){
			mes = '0' + mes;
		}
		
		periodo = anio + mes;
		
		//var periodo = anio + '0'+ mes; 
		var idReport = document.GenerarRepDivPrevForm.idReporte.value; //id se refiere al numero de reporte del cl que se quiere invocar.
		
		//alert(periodo);
		//esta variable se debe eliminar una vez automatizado el proceso.
		//var periodo = "201207";
		if(parseInt(idReport,10) == 1 || parseInt(idReport,10) == 2 || parseInt(idReport,10) == 3){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
			//alert("Este proceso se puede tardar varios minutos.");
			
			
		}
		
		//alert("llamar a proceso cobol");	
		GeneraRepDivPrevDWR.llamarProcesoCobol(idSecuencia, idReport, periodo, asfam, function(data){
			var resp = null;
			//var statusActualizado;
			resp = data.codRespuesta;
			//alert("respuesta retornada es: " + resp);
			if(resp == 3){
			
				alert("Proceso invocado con éxito.");
				
				/*Se comenta esta linea pero se debe bloquear este boton(procesar)-- cambio, ya no se bloquea, tiene opcion de reprocesar*/
				//statusActualizado = data.msgRespuesta;
				//actualizaStatus(statusActualizado);
				updateStatusProcesoMaestroSivegam(resp,idSecuencia);//se debe actualizar status y fecha de procesamiento de maestro sivegam.
				bloquearBotonProcesar();
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				verificaInvocacion += 1;
				
			}else{
				//aqui llamar a una funcion (se debe crear) que cambie el status a "fallido".
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				//JLGN
				if(resp != 4){
					alert("Error en el procesamiento del archivo.");
					if(resp == 1){
						updateStatusProcesoMaestroSivegam('1',idSecuencia);
					}else{
						updateStatusProcesoMaestroSivegam('2',idSecuencia);
					}
				}
				unificarActualizacionCamposGrilla();
			}		
		});
		
		DWREngine.setAsync(true);
	}	
	
	//Inicio JLGN	
	/*Funcion que establece comunicacion con un servidor AS400 e invoca al proceso cobol que llena las tablas para el proceso de SIVEGAM*/
	function invocarProcesoCobol2(idSecuencia, asfam){
		//desincronizado
		DWREngine.setAsync(true);
		
		var periodo = "";
		var mes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anio = document.GenerarRepDivPrevForm.dbx_anio.value;
		
		if(mes < 10){
			mes = '0' + mes;
		}
		
		periodo = anio + mes;
		
		var idReport = document.GenerarRepDivPrevForm.idReporte.value; //id se refiere al numero de reporte del cl que se quiere invocar.
		
		GeneraRepDivPrevDWR.llamarProcesoCobol(idSecuencia, idReport, periodo, asfam, function(data){
			var resp = null;
			resp = data.codRespuesta;
			if(resp == 3){
			
				alert("Proceso invocado con éxito.");				
				/*Se comenta esta linea pero se debe bloquear este boton(procesar)-- cambio, ya no se bloquea, tiene opcion de reprocesar*/
				updateStatusProcesoMaestroSivegam(resp,idSecuencia);//se debe actualizar status y fecha de procesamiento de maestro sivegam.
				bloquearBotonProcesar();
				verificaInvocacion += 1;
				
			}else{
				//JLGN
				if(resp != 4){
					alert("Error en el procesamiento del archivo.");
					if(resp == 1){
						updateStatusProcesoMaestroSivegam('1',idSecuencia);
					}else{
						updateStatusProcesoMaestroSivegam('2',idSecuencia);
					}
				}
				unificarActualizacionCamposGrilla();
			}		
		});
		
		DWREngine.setAsync(true);
	}
	//Fin JLGN
	
	/**Funcion que realiza el update sel status de proceso a la tabla maestro sivegam, dada la respuesta arrojada por el proceso cobol*/
	function updateStatusProcesoMaestroSivegam(idRespuestaCobol,idSivegam){
		
		var fechaProceso = '<%=session.getAttribute("FechaSistema")%>';
		
		if(reprocesoReporte == 1){
			idMaestroSiv = idSivegam;
		}else{
			idMaestroSiv = document.GenerarRepDivPrevForm.idMaestroSivegam.value;
		}
		
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.updateStatusProcesoSivegam(idRespuestaCobol, idMaestroSiv, fechaProceso, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				//alert("Maestro sivegam actualizada con exito.");//FRM
				unificarActualizacionCamposGrilla();
			}//else{
				//actualizarGrillaReprocesar(mes,idRespuestaCobol);//FRM //TODO SToro Buscar motivo de borrado o si se debe implementar la funciuon
				//alert("Error, no se ha actualizado maestro sivegam.");
			//}
		});
		DWREngine.setAsync(true);
	}
	
	/*Funcion que retorna el periodo en el nombre del archivo, formado por la eleccion de los combos de mes y año dados por el usuario*/
	function retornaPeriodoYNombreArchivoCboxTxt(idReporte){
		
		var mesTxt = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anioTxt = document.GenerarRepDivPrevForm.dbx_anio.value;
		
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
			case 1:
				nombreArchivo = "10105_NR11_"+ periodo + ".TXT";
				break;
			case 2:
				nombreArchivo = "10105_NR12_"+ periodo + ".TXT";
				break;
			case 3:
				nombreArchivo = "10105_NR14_"+ periodo + ".TXT";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}

	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoCboxXls(idReporte){
		
		var mesTxt = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anioTxt = document.GenerarRepDivPrevForm.dbx_anio.value;
		
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
			case 1:
				nombreArchivo = "10105_NR11_"+ periodo + ".XLSX";
				break;
			case 2:
				nombreArchivo = "10105_NR12_"+ periodo + ".XLSX";
				break;
			case 3:
				nombreArchivo = "10105_NR14_"+ periodo + ".XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
	/*Retorna periodo y nombre del archivo para reporte xls version resumen.*/
	function retornaPeriodoYNombreArchivoCboxRes(idReporte){
		
		var mesTxt = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anioTxt = document.GenerarRepDivPrevForm.dbx_anio.value;
		
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
			case 14:
				nombreArchivo = "10105_NR11_"+ periodo + "_RES.XLSX";
				break;
			case 15:
				nombreArchivo = "10105_NR12_"+ periodo + "_RES.XLSX";
				break;
			case 16:
				nombreArchivo = "10105_NR14_"+ periodo + "_RES.XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
	function invocarProcesoCobolErrores(idSecuencia, idReport){
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		DWREngine.setAsync(false);
		var aux = 0;
		var periodo = "";
		var mes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anio = document.GenerarRepDivPrevForm.dbx_anio.value;
		
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
				//alert("Proceso invocado con éxito.");
				
				
				
			}else{
				aux = 1;
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				//alert("Error la validacion del archivo.");
				
			}		
		});
		DWREngine.setAsync(true);
		var idMax = obtenerMaxIdMaestroSivegam(idReport);
		
		if((idMaxMaestroSivegam == 0)||(aux != 0)){
			alert("No es posible generar el reporte en excel, por problemas en la validacion del archivo.");
			return false;
		}else{		
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			var usser = '<%=session.getAttribute("IDAnalista")%>';
			var mesReport = parseInt(periodo,10);
			
			
			var flagXlss = document.GenerarRepDivPrevForm.idFlagXls.value;
			GeneraRepDivPrevDWR.generarReporteXLS(idSecuencia, periodo, idMaxMaestroSivegam, mesReport, usser, fechaReporte, function(data){
			
				var resp = null;
				
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("Reporte generado con éxito.");
					document.GenerarRepDivPrevForm.rutaArchivo.value = data.rutaArchivo;
					enviaFormulario(2);
				}else{
					alert("Reporte no generado");
				}	
			});
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					
			
		}	
	}	
	
	/*Aqui funcion DWR que verifica si un archivo esta en su respectiva carpeta.*/
	var flagArchivo;
	function verificarArchivoEnServer(){
		
		var idTipoReporte = document.GenerarRepDivPrevForm.idFlagTxt.value;
		var nombreArch = retornaPeriodoYNombreArchivoCboxTxt(idTipoReporte);
		
		//alert(nombreArch);
		//alert(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteArchivoSegunPeriodo(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenerarRepDivPrevForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;
	}
	
	/**Funcion que retorna si existe un archivo xls*/
	function verificarExisteXls(){

		var idTipoReporte = document.GenerarRepDivPrevForm.idFlagXls.value;
		var nombreArch = retornaPeriodoYNombreArchivoCboxXls(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteXLS(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;
			//alert(nombreArch);
			if(consulta == 0){
				//enviaFormulario(3);
				flagArchivo = 0;
				document.GenerarRepDivPrevForm.rutaArchivo.value = data.rutaArchivo;
			}else{
				flagArchivo = 1;
			}
		});
		DWREngine.setAsync(true);
		return flagArchivo;	
	}
	
	/*Funcion que verifica si existe resumen*/
	function verificaExisteResumen(){
		
		var idTipoReporte = document.GenerarRepDivPrevForm.idFlagXls.value;
		var nombreArch = retornaPeriodoYNombreArchivoCboxRes(idTipoReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.consultarExisteResumen(idTipoReporte,nombreArch, function(data){
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta == 0){
				//alert("verificaExisteResumen 1");
				flagArchivo = 0;
				document.GenerarRepDivPrevForm.rutaArchivo.value = data.rutaArchivo;
				//alert("Ruta = " + document.GenerarRepDivPrevForm.rutaArchivo.value);
				
			}else{
				//alert("verificaExisteResumen 2");
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
				
		var mesTxt = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anioTxt = document.GenerarRepDivPrevForm.dbx_anio.value;
		
		if((mesTxt == mesSistema) && (anioTxt == anioSistema))
		{
			generarTxt(idFlagTxt);
		
		}else{	
			
			//var flg = verificarArchivoEnServer();
			//if(flg == 1){
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
				
		var mesXls = parseInt(document.GenerarRepDivPrevForm.dbx_meses.value,10);
		var anioXls = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10);
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
				
		var mesXls = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anioXls = document.GenerarRepDivPrevForm.dbx_anio.value;
		
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
				//alert("Archivo eliminado con éxito");
			}else{
				alert("No es posible eliminar el archivo"+ nombreArch +". Puede que el archivo no exista en el servidor o este en uso.")
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
				//alert("Archivo xls eliminado");
			}else{
				alert("No es posible eliminar el archivo"+ nombreArchivo +". Puede que el archivo no exista en el servidor o este en uso.")
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
				//alert("Archivo xls eliminado");
			}else{
				alert("No es posible eliminar el archivo"+ nombreArchivo +". Puede que el archivo no exista en el servidor o este en uso.")
			}	
		});
		DWREngine.setAsync(true);
	}
		
	/*Funcion que genera el reporte en formato txt.*/
	function generarTxt(idFlagReporteTXT){
		
		//primero obtener el maximo id.
		obtenerMaxIdMaestroSivegam(idFlagReporteTXT);
		
		if(idMaxMaestroSivegam == 0){
			alert("No es posible generar el plano, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{
			DWREngine.setAsync(false);
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			var usser = '<%=session.getAttribute("IDAnalista")%>';
			var periodoTxt = "";
			var mesPeriodo = document.GenerarRepDivPrevForm.dbx_meses.value;
			var anioPeriodo = document.GenerarRepDivPrevForm.dbx_anio.value;
			var Periodo = 0;
			
			if(parseInt(mesPeriodo,10) < 10){
				mesPeriodo = '0' + mesPeriodo;
			}
				
			periodoTxt = anioPeriodo + mesPeriodo;
			//alert(periodoTxt);
			Periodo = parseInt(periodoTxt,10);
			
			GenerarPlanosDivisionPrevisionalDWR.consultaRegistros(idFlagReporteTXT, periodoTxt, idMaxMaestroSivegam, Periodo, usser, fechaReporte, function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("reporte generado en formato txt.");
					
					document.GenerarRepDivPrevForm.rutaArchivo.value = data.rutaArchivo;
					//alert("el archivo fue generado en: " + document.GenerarRepDivPrevForm.rutaArchivo.value);
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
	function generarXls(idFlagReporteXls){

		//llamar a funcion para obtener idmax de maestro sivegam.
		obtenerMaxIdMaestroSivegam(idFlagReporteXls);
		
		if(idMaxMaestroSivegam == 0){
			alert("No es posible generar el reporte en excel, debido a que no existe información para el periodo seleccionado.");
			return false;
		}else{		
			var fechaReporte = '<%=session.getAttribute("FechaSistema")%>';
			var usser = '<%=session.getAttribute("IDAnalista")%>';
			
			var periodoReporte = "";
			var mesReporte = document.GenerarRepDivPrevForm.dbx_meses.value;
			var anioReporte = document.GenerarRepDivPrevForm.dbx_anio.value;
			
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
			
			
			var flagXls = document.GenerarRepDivPrevForm.idFlagXls.value;
			//periodoReporte = anioReporte + mesReporte;
			//alert("periodo calculado = " + periodoReporte);
			
			if(parseInt(flagXls,10) == 1 || parseInt(flagXls,10) == 2 || parseInt(flagXls,10) == 3){
				document.getElementById("pantallaDeCarga").style.visibility = "visible";
				//alert("Este proceso se puede tardar varios minutos.");
	
			}
			
			mesReporte = parseInt(periodoReporte,10);
			
			DWREngine.setAsync(true);
					
			
			GeneraRepDivPrevDWR.generarReporteXLS(idFlagReporteXls, periodoReporte, idMaxMaestroSivegam, mesReporte, usser, fechaReporte, function(data){
				
				var resp = null;
				var actualizacionStatus;
				
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("Reporte generado con éxito.");
					
					document.GenerarRepDivPrevForm.rutaArchivo.value = data.rutaArchivo;
					
					actualizacionStatus = data.msgRespuesta;
					//actualizaStatus(actualizacionStatus);
					
					//habilitaResumen(flagXls);
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
	
	/*Funcion que genera el resumen en xls*/
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
			var mesReport = document.GenerarRepDivPrevForm.dbx_meses.value;
			var anioReport = document.GenerarRepDivPrevForm.dbx_anio.value;
			
			if(parseInt(mesReport,10) < 10){
				mesReport = '0' + mesReport;
			}
			
			
			
			periodoReport = anioReport + mesReport;
			
			mesReport = parseInt(periodoReport,10);
			
			DWREngine.setAsync(false);
	
			var flagXlss = document.GenerarRepDivPrevForm.idFlagXls.value;
			GeneraRepDivPrevDWR.generarReporteXLS(idFlagReporteXls, periodoReport, idMaxMaestroSivegam, mesReport, usser, fechaReporte, function(data){
			
				var resp = null;
				
				resp = data.codRespuesta;
				
				if(resp == 0){
					alert("Reporte generado con éxito.");
					
					document.GenerarRepDivPrevForm.rutaArchivo.value = data.rutaArchivo;
					enviaFormulario(2);
				}else{
					alert("Reporte no generado");
				}	
			});
			
			DWREngine.setAsync(true);
		}	
		//gc();				
	}
	
	function cobolSIP05ASFAM(periodo, id){
		
    	document.getElementById("pantallaDeCarga").style.visibility = "visible";
		DWREngine.setAsync(false);
		var aux = 0;
		GeneraRepDivPrevDWR.cobolSIP05ASFAM(periodo, id,function(data){
			var resp = null;
			resp = data.codRespuesta;
			var codResultado = data.codResultado;
			//alert(resp);
				
				if(resp == 3){
					//alert("Proceso SIP05ASFAM ejecutado correctamente.");
					
					aux = 0;
					
				}else{
					//alert("El proceso SIP05ASFAM ha resultado fallido.");
					aux = 1;
				}	
		});
		DWREngine.setAsync(true);
		document.GenerarRepDivPrevForm.statusAsfam.value = aux;
    	document.getElementById("pantallaDeCarga").style.visibility = "hidden";

	}
	
	/*Funcion que setea el valor del idReporte, para saber exactamente cual es el reporte que se quiere generar.*/
	function seteaIdReporte(n){
		
		document.GenerarRepDivPrevForm.idReporte.value = n;
		variableReport = document.GenerarRepDivPrevForm.idReporte.value;
		actualizarPeriodoGrilla();//dependiendo del valor entregado, se actualiza el campo correspondiente de la grilla.
	}
	
	function seteaFlagXls(a){
	    document.GenerarRepDivPrevForm.idFlagXls.value = a;
	}
	
	function seteaFlagTxt(a){
		document.GenerarRepDivPrevForm.idFlagTxt.value = a;
	}
	/*Funcion que habilita el boton resumen*/
	function habilitaResumen(flag){
		
		var opciones = parseInt(flag);
		switch(opciones){
			case 1:
				document.getElementById("ref_resumen11").disabled = false;
				break;
			case 2:
				document.getElementById("ref_resumen12").disabled = false;
				break;
			case 3:
				document.getElementById("ref_resumen14").disabled = false;
				break;
			default:
				alert("No es posible habilitar el boton Resumen.");		
		}
	}
	
	/*Funcion que actualiza el campo status proceso una vez que se presiono el boton "procesar" y ademas activa el boton excel.*/
	function actualizaStatus(p){
	
		var botonPresionado = document.GenerarRepDivPrevForm.idFlagXls.value;
		switch(parseInt(botonPresionado)){
			case 1:
				document.GenerarRepDivPrevForm.txt_statusProceso11.value = p;			
				break;
			
			case 2:
				document.GenerarRepDivPrevForm.txt_statusProceso12.value = p;				
				break;
				
			case 3:
				document.GenerarRepDivPrevForm.txt_statusProceso14.value = p;				
				break;
			default:
				alert("Ha ocurrido un error y no se ha podido actualizar el estado del proceso.");
		}
				
	}
	
	/*funcion que bloquea boton procesar y desbloquea boton excel*/
	function bloquearBotonProcesar(){
	
		var pressBoton = document.GenerarRepDivPrevForm.idReporte.value;
		
		if(pressBoton == 1){
				document.getElementById("ref_excel11").disabled = false;
				document.getElementById("btn_Procesar11").disabled = false;
		}
		if(pressBoton == 2){
				document.getElementById("ref_excel12").disabled = false;
				document.getElementById("btn_Procesar12").disabled = false;
		}
		if(pressBoton == 3){
				document.getElementById("ref_excel14").disabled = false;
				document.getElementById("btn_Procesar14").disabled = false;
		}
	}
	
	/*Funcion que obtiene los valores de las parametricas para asignarlas a los campos de la grilla.*/
	function obtenerDescStatusProceso(){
		
		DWREngine.setAsync(true);
		GeneraRepDivPrevDWR.obtenerStatusProceso(function(data){
			document.GenerarRepDivPrevForm.txt_statusProceso11.value = data.descripcion_status_proceso;
			document.GenerarRepDivPrevForm.txt_statusProceso12.value = data.descripcion_status_proceso;
			document.GenerarRepDivPrevForm.txt_statusProceso14.value = data.descripcion_status_proceso;
		});
		DWREngine.setAsync(false);
	}
	
	/*Funcion que verifica el status del proceso.*/
	function verificaStatus(periodoProceso, idTipoProceso){
		
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.obtenerStatus(periodoProceso, idTipoProceso, function(data){
		
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.GenerarRepDivPrevForm.statusProcesoTemp.value = data.statusProceso;
			
			}else{
				
				document.GenerarRepDivPrevForm.statusProcesoTemp.value = 1;
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/*funcion que valida el status del proceso para bloquear botones de procesamiento*/
	function bloquearPorStatus(){
		
		var link1 = document.getElementById("ref_excel11");
		var link2 = document.getElementById("ref_excel12");
		var link3 = document.getElementById("ref_excel14");

		var resumen1 = document.getElementById("ref_resumen11");
		var resumen2 = document.getElementById("ref_resumen12");
		var resumen3 = document.getElementById("ref_resumen14");

		var botonProceso1 = document.getElementById("btn_Procesar11");
		var botonProceso2 = document.getElementById("btn_Procesar12");
		var botonProceso3 = document.getElementById("btn_Procesar13");
		
		var mesPeriodo = calculaMesPeriodo();
		
		for(var j=0; j<=3; j++){
			
			if(j==1){
				
				verificaStatus(mesPeriodo, j);
				if(document.GenerarRepDivPrevForm.statusProcesoTemp.value == 3){
					botonProceso1.disabled = true;
					link1.disabled = false;
					resumen1.disabled = false;
					document.GenerarRepDivPrevForm.txt_statusProceso11.value = "PROCESADO";
				}else{
					cargarDataInformativa();
				}
				
			}
			
			if(j==2){
				
				verificaStatus(mesPeriodo, j);
				if(document.GenerarRepDivPrevForm.statusProcesoTemp.value == 3){
					botonProceso2.disabled = true;
					link2.disabled = false;
					resumen2.disabled = false;
					document.GenerarRepDivPrevForm.txt_statusProceso12.value = "PROCESADO";
				}
				else{
					cargarDataInformativa();
				}

			}

			if(j==3){
				
				verificaStatus(mesPeriodo, j);
				if(document.GenerarRepDivPrevForm.statusProcesoTemp.value == 3){
					botonProceso3.disabled = true;
					link3.disabled = false;
					resumen3.disabled = false;
					document.GenerarRepDivPrevForm.txt_statusProceso14.value = "PROCESADO";
				}else{
					cargarDataInformativa();
				}
				
			}								
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
	
	/*Funcion que carga el arreglo codigo de banco*/
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
		var cmb = document.GenerarRepDivPrevForm.dbx_meses;
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
		
		document.GenerarRepDivPrevForm.dbx_meses.value = verificadorMes;
		document.GenerarRepDivPrevForm.dbx_anio.value  = verificadorAño;
		
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

		var cmb = document.GenerarRepDivPrevForm.dbx_anio;
		
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
		
		var cmb = document.GenerarRepDivPrevForm.dbx_anio;
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
	/**Funcion que contiene los valores iniciales del periodo cuando se ingresa a la pantalla, para procesar reportes.*/
	function cargarPeriodoInicial(){
	
		document.GenerarRepDivPrevForm.txt_periodo11.value = "SIN PERIODO";
		document.GenerarRepDivPrevForm.txt_periodo12.value = "SIN PERIODO";
		document.GenerarRepDivPrevForm.txt_periodo14.value = "SIN PERIODO";
	
	}
	
	/**Funcion que actualiza periodo de la grilla cuando se presiona boton procesar //variableReport.*/

	function actualizarPeriodoGrilla(){
		
		//var idReportado = document.GenerarRepCotForm.idReporte.value;
		var periodoGrilla = "";
		var glosaMes = "";
		var mesGrilla = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anioGrilla = document.GenerarRepDivPrevForm.dbx_anio.value;
		
		//alert("valor Año = " + anioGrilla);//TODO SToro Reparar
		
		if(parseInt(mesGrilla,10) < 10){
			mesGrilla = '0' +  mesGrilla;
		}
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.obtenerMesesParaPeriodoGrilla(mesGrilla, function(data){
			var consult = null;
			consult = data.descripcion_periodo_proceso;
			
			if(consult != null){
			
				glosaMes = consult;
			
			}else{
				glosaMes = "";
			}	
		});
		DWREngine.setAsync(true);
	
		
		periodoGrilla = glosaMes + " " + anioGrilla;
		
		/**Se consulta por el estado, para actualizarlo en estado ("En ejecucion").*/
		
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.actualizaEstadoGrilla(function(data){
			
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
			case 1:
			 	document.GenerarRepDivPrevForm.txt_periodo11.value = periodoGrilla;
			 	document.GenerarRepDivPrevForm.txt_statusProceso11.value = estadoGrilla;
			 	break;
			case 2:
				document.GenerarRepDivPrevForm.txt_periodo12.value = periodoGrilla;
				document.GenerarRepDivPrevForm.txt_statusProceso12.value = estadoGrilla;
			 	break;
			case 3:
				document.GenerarRepDivPrevForm.txt_periodo14.value = periodoGrilla;
				document.GenerarRepDivPrevForm.txt_statusProceso14.value = estadoGrilla;
			 	break; 	 	 	
			default:
				alert("Error. No es posible actualizar el periodo");
				cargarPeriodoInicial();
		}
	}//
	
	/**Funcion que obtiene el ultimo id maestro sivegam cuando se entra a la aplicacion y no se requiere procesar sino
	generar los archivos xls y txt.*/
	function obtenerMaxIdMaestroSivegam(idReporte){
		
		idMaxMaestroSivegam = 0;
		var mes = parseInt(document.GenerarRepDivPrevForm.dbx_meses.value,10);
		var anio = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10);
		var periodoSivegam = anio*100 + mes;
		var consulta = null;
	
		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.selectMaxIdMaestroSivegam(idReporte, periodoSivegam, function(data){
			
			consulta = data.maestro_sivegam;
			
			if(consulta != 0){
				idMaxMaestroSivegam = consulta;
				
			}
		});
		DWREngine.setAsync(false);
		return consulta;
	}
	
	/**Obtener tipoArchivo dado el idtipoProceso, para generar los resumenes de division previsional.*/
	/*para */
	function obtenerHomologacionTipoArchivo(idTipoProceso){

		DWREngine.setAsync(false);
		GeneraRepDivPrevDWR.selectTipoArchivoHomologado(idTipoProceso, function(data){
			var consulta = null;
			consulta = data.id_tipo_archivo;
			
			if(consulta != 0){
				idTipoArchivoSivegam = consulta;
				//alert(idTipoArchivoSivegam);
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
		
		var mes = parseInt(document.GenerarRepDivPrevForm.dbx_meses.value,10);
		var anio = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10);
		var periodoSivegam = anio*100 + mes;
		
	
		//verifica que exista un idmax de maestro sivegam, de lo contrario no hay data.
		if(idMaxMaestroSivegam != 0){

			DWREngine.setAsync(false);
			GeneraRepDivPrevDWR.obtenerInformacionAActualizar(idMaxMaestroSivegam, function(data){
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
	function actualizarCamposSif011(){
		
		var idReporteInicial = 1;
		var anio_siv = document.GenerarRepDivPrevForm.dbx_anio.value;
		
		obtenerMaxIdMaestroSivegam(idReporteInicial);
		
		
		
		actualizarGrillaAlIniciar();
			
		DWREngine.setAsync(false);
		
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado)%100;
		
		GeneraRepDivPrevDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null){
				
				if(data.glosaPeriodoProcesoMes == "SIN PERIODO"){
					document.GenerarRepDivPrevForm.txt_periodo11.value = data.glosaPeriodoProcesoMes;
				}else{
					document.GenerarRepDivPrevForm.txt_periodo11.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepDivPrevForm.dbx_anio.value;				
				}
				
				document.GenerarRepDivPrevForm.txt_statusProceso11.value = data.glosaStatusProceso;
				bloqueaDesbloqueaSif011();
			}
			
		});
		DWREngine.setAsync(true);			
	}
	
	function actualizarCamposSif012(){
		
		var idReporteInicial = 2;
		var anio_siv = document.GenerarRepDivPrevForm.dbx_anio.value;
		
		obtenerMaxIdMaestroSivegam(idReporteInicial);
		actualizarGrillaAlIniciar();
		
		DWREngine.setAsync(false);
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado)%100;
		GeneraRepDivPrevDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null)
			{
				if(data.glosaPeriodoProcesoMes == "SIN PERIODO"){
					document.GenerarRepDivPrevForm.txt_periodo12.value = data.glosaPeriodoProcesoMes;
				}else{
					document.GenerarRepDivPrevForm.txt_periodo12.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepDivPrevForm.dbx_anio.value;				
				}
				
				document.GenerarRepDivPrevForm.txt_statusProceso12.value = data.glosaStatusProceso;
				bloqueaDesbloqueaSif012();
			}
			
		});
		DWREngine.setAsync(true);
	}
	
	function actualizarCamposSif014(){

		var idReporteInicial = 3;
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado)%100;
		var anio_siv = document.GenerarRepDivPrevForm.dbx_anio.value;
		
		obtenerMaxIdMaestroSivegam(idReporteInicial);

		actualizarGrillaAlIniciar();
		
		DWREngine.setAsync(false);
		periodoSivegamConsultado = parseInt(periodoSivegamConsultado)%100;
		GeneraRepDivPrevDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null){
				if(data.glosaPeriodoProcesoMes == "SIN PERIODO"){
					document.GenerarRepDivPrevForm.txt_periodo14.value = data.glosaPeriodoProcesoMes;
				}else{
					document.GenerarRepDivPrevForm.txt_periodo14.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepDivPrevForm.dbx_anio.value;				
				}
				document.GenerarRepDivPrevForm.txt_statusProceso14.value = data.glosaStatusProceso;
				bloqueaDesbloqueaSif014();
			}
			
		});
		DWREngine.setAsync(true);			
	} //obtenerDescStatusProceso();
	
	/*function actualizarGrillaReprocesar(mes,statusCobol){
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.buscarGlosaStatusYGlosaMes(periodoSivegamConsultado,statusSivegam, function(data){
			var resp = null;
			resp = data;
			
			if(resp != null){
				document.GenerarRepDivPrevForm.txt_periodo19.value = data.glosaPeriodoProcesoMes + " " + document.GenerarRepCotForm.dbx_anio.value;
				document.GenerarRepDivPrevForm.txt_statusProceso19.value = data.glosaStatusProceso;	
				bloqueaDesbloqueaSif019();
			}
			
		});
		DWREngine.setAsync(true);
	}*/
	
	function unificarActualizacionCamposGrilla(){

		var mes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var anio = document.GenerarRepDivPrevForm.dbx_anio.value;
		if(mes == 0 || anio == 0){
			selectedItemCombo();
			alert('Seleccione un mes y año antes de procesar.');
		}else{
		
			var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
			var periodoSivegam = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
			actualizarCamposSif011();
			GeneraRepDivPrevDWR.selectMaxIdMaestroSivegam(11, periodoSivegam, function(data){
				var consulta = null;
				consulta = data.maestro_sivegam;
				
				if(consulta != 0){
					idMaxMaestroSivegam = consulta;
					
				}
			});
			
			actualizarCamposSif012();
			GeneraRepDivPrevDWR.selectMaxIdMaestroSivegam(12, periodoSivegam, function(data){
				var consulta = null;
				consulta = data.maestro_sivegam;
				
				if(consulta != 0){
					idMaxMaestroSivegam = consulta;
					
				}
			});
			
			actualizarCamposSif014();
			GeneraRepDivPrevDWR.selectMaxIdMaestroSivegam(14, periodoSivegam, function(data){
				
				var consulta = null;
				consulta = data.maestro_sivegam;
				
				if(consulta != 0){
					idMaxMaestroSivegam = consulta;
					
				}
			});
		
		}
	}
	
	function validacionGeneracionTXTsif011(){
	
		var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
		var idTipoReporte12 = obtenerStatusPorPeriodoyTipoReporte(2, periodo);
		var idTipoReporte16 = obtenerStatusPorPeriodoyTipoReporte(4, periodo);
		
		if ((idTipoReporte12 == 3) && (idTipoReporte16 == 3)) 
		{
		 	orquestadorArchivosTxt(1);
		}else {
			alert ("No se puede generar el archivo sin haber procesado los archivos 12 y 16");
		}
	}
	
	function validacionGeneracionTXTsif012(){
	
		var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
		var idTipoReporte16 = obtenerStatusPorPeriodoyTipoReporte(4, periodo);
		
		if (idTipoReporte16 == 3)
		{
		 	orquestadorArchivosTxt(2);
		}else {
			alert ("No se puede generar el archivo sin haber procesado el archivo 16");
		}
	}
	
	function validacionGeneracionPROCsif012(){
			
			seteaIdReporte(2);
		 	insertToMaestroSivegam();
		
	}
	
	function validacionProcesamientoSif012(){
	    var retorno = 0;
		var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
		var idTipoReporte16 = obtenerStatusPorPeriodoyTipoReporte(4, periodo);
		
		if (idTipoReporte16 == 3)
		{
		 	retorno = 1;
		}
		return retorno;
	}
	
	function validacionProcesamientoSif011(){
	
		var retorno = 0;
		var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
		var idTipoReporte12 = obtenerStatusPorPeriodoyTipoReporte(2, periodo);
		var idTipoReporte16 = obtenerStatusPorPeriodoyTipoReporte(4, periodo);
		
		if ((idTipoReporte16 == 3) && (idTipoReporte12 == 3))
		{
		 	retorno = 1;
		}else if ((idTipoReporte16 == 3) && (idTipoReporte12 != 3))
		{
		 	retorno = 3;
		} else if ((idTipoReporte16 != 3) && (idTipoReporte12 == 3))
		{
		 	retorno = 2;
		}
		return retorno;
	}
	
	/*
	ESTA FUNCION ACTUALIZA EL ESTADO RECIBE VALIDADOR RETORNO, QUE INDICA SI SE DEBE EJECUTAR EL UPDATE O NO,
	Y ARCHIVO, QUE INDICA QUE ARCHIVO SE MODIFICARA. ESTA MODIFICACION SE EFECTUARA SOBRE EL ULTIMO REGISTRO
	INGRESADO EN LA TABLA, CORRESPONDIENTE AL TIPO DE ARCHIVO INDICADO, DEL MES CORRESPONDIENTE, QUE TENGA ESTADO 3,
	RELACIONADO AL IDMAESTRODIVEGAM RELACIONADO QUE LLAMA A ESTA FUNCION
	*/
	function updateStatus12PorReproceso112(archivo){
		    var retorno=0;
		    var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
			var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
			var id;
			DWREngine.setAsync(false);
			obtenerMaxIdMaestroSivegam(archivo);
			id = idMaxMaestroSivegam;
			if(id.length == 0){
				obtenerMaxIdMaestroSivegam(archivo);
				id = idMaxMaestroSivegam;
				GeneraRepDivPrevDWR.updateStatus12PorReproceso112(id, periodo, function(data){
				var resp = null;
				resp = data.codRespuesta;
			
					if(resp == 0){
						retorno=0;
					}else{
				    	retorno=1;
						alert("Error, no se ha actualizado maestro sivegam.");
					}
				});
			}else{
				GeneraRepDivPrevDWR.updateStatus12PorReproceso112(id, periodo, function(data){
				var resp = null;
				resp = data.codRespuesta;
			
					if(resp == 0){
						retorno=0;
					}else{
				    	retorno=1;
						alert("Error, no se ha actualizado maestro sivegam.");
					}
				});
			}
			DWREngine.setAsync(true);
		    return (retorno);
	}
	
	function GeneracionPROCsif011(){
	    var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
	    var aux=0;
	    var existeReporte = obtenerStatusPorPeriodoyTipoReporte(1, periodo);
	    //JLGN
	    varPantallaCarga = 1;//Muestra pantalla de Carga
	    
		if (existeReporte == 0){
			seteaIdReporte(1);
			insertToMaestroSivegam();
		}else{
			var conf = confirm("Ud ya ejecuto el proceso. ¿Está seguro que desea volver a ejecutar?");
			if(conf == true){
				var estado = validacionProcesamientoSif011();
			    switch (estado){
			    			
				    case 0 :	seteaIdReporte(1);
				     			insertToMaestroSivegam();
				    			break;
				    			
					case 1 :   aux = updateStatus12PorReproceso112(2);
								if (aux!=0){
									alert("Error al cambiar el estado del archivo 12");
								}else{
									alert("El archivo 12 ha pasado a estado Pendiente Reproceso.");
									aux = updateStatus12PorReproceso112(4);
									if (aux!=0){
										alert("Error al cambiar el estado del archivo 16");
									}else{
										alert("El archivo 16 ha pasado a estado Pendiente Reproceso.");
										seteaIdReporte(1);
										insertToMaestroSivegam();
									}
								}
								break;
								
					case 2 :    aux = updateStatus12PorReproceso112(2);
								if (aux!=0){
									alert("Error al cambiar el estado del archivo 12");
								}else{
									alert("El archivo 12 ha pasado a estado Pendiente Reproceso.");
									seteaIdReporte(1);
									insertToMaestroSivegam();
								}
								break;	
					
					case 3 :    aux = updateStatus12PorReproceso112(4);
								if (aux!=0){
									alert("Error al cambiar el estado del archivo 16");
								}else{
									alert("El archivo 16 ha pasado a estado Pendiente Reproceso.");
									seteaIdReporte(1);
									insertToMaestroSivegam();
								}
								break;
					
					default :   alert("Error al verificar los estados de los archivos 12 y 16");					
								break;
				}
			}	
		}
	}
	
	
	function GeneracionPROCsif012(){
	    var periodoMes = document.GenerarRepDivPrevForm.dbx_meses.value;
		var periodo = parseInt(document.GenerarRepDivPrevForm.dbx_anio.value,10)*100 + parseInt(periodoMes,10);
	    var aux=0;
	    var existeReporte = obtenerStatusPorPeriodoyTipoReporte(2, periodo);
	    var existeReporte11 = obtenerStatusPorPeriodoyTipoReporte(1, periodo);
	    //JLGN 
	    varPantallaCarga = 0;//No muestra pantalla de carga
	    
	    if(existeReporte11 == 3){
			if (existeReporte == 0){
				validacionGeneracionPROCsif012();
			}else{
				var conf = confirm("Ud ya ejecuto el proceso. ¿Está seguro que desea volver a ejecutar?");
				if(conf == true){
					var estado = validacionProcesamientoSif012();
			    	switch (estado){
			    	case 0 : 	validacionGeneracionPROCsif012();
			    			break;
				    			
					case 1 :    aux = updateStatus12PorReproceso112(4);
								if (aux!=0){
									alert("Error al cambiar el estado del archivo 16");
								}else{
									alert("El archivo 16 ha pasado a estado Pendiente Reproceso.");
									validacionGeneracionPROCsif012();
								}
								break;
				
					default :   alert("Error al verificar el estado del archivo 16");					
								break;
					}
				}
			}
		}else{
			alert("No se puede procesar el archivo 12 sin haber procesado el archivo 11.");
		}
	}
	
	function GeneracionPROCsif014(){
		//JLGN
	    varPantallaCarga = 1;//Muestra pantalla de Carga
	    
		seteaIdReporte(3);
		insertToMaestroSivegam();
	}
	
	//JLGN Ejecuta cada cierto tiempo la funcion javascript	
	setInterval('unificarActualizacionCamposGrilla()',30000);
	
</script>
</head>
<body onload="cargarDataInformativa();cargarArregloParametricas();obtenerComboPeriodoProceso();selectedItemCombo();unificarActualizacionCamposGrilla();">
<html:form action="/genRepDivPrev.do" method="post" enctype="multipart/form-data">

  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idReporte" value="0">
  <input type="hidden" name="idMaestroSivegam" value=" ">
  <input type="hidden" name="idFlagXls" value="0">
  <input type="hidden" name="idFlagTxt" value="0">
  <input type="hidden" name="flagReporte" value=" ">
  <input type="hidden" name="rutaArchivo" value=" ">
  <input type="hidden" name="statusProcesoTemp" value=" ">
  <input type="hidden" name="statusAsfam" value="0">
   
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
					<td><strong><p1>GENERACION DE REPORTES DE REGIMENES LEGALES</p1></strong></td>
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
				<p>2. Reportes División Previsional<p2></p>
				<p>
			</td>
		</tr>
		<!-- <tr>
			<input type="button" name="btn_txt" id="btn_txt" class="btn_limp"	value="txt" onClick="generarTxt();" />
		</tr> -->
		<!-- <tr>
			<td height="37" align="right">
				<input type="button" name="btn_Procesar" id="btn_Procesar" class="btn_limp"	value="Procesar" onClick="invocarProcesoCobol()" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_txt" id="btn_txt" class="btn_limp"	value="txt" onClick="generarTxt();" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Generar" id="btn_Generar" class="btn_limp"	value="Generar" onClick="generarXls();" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_xls" id="btn_xls" class="btn_limp"	value="xls" onClick="enviaFormulario(2);" />
			</td>
		</tr> -->
	  </table>
	  
	  <tr>
	 	 <td width="100%">
		<table width="1197" align="center" cellpadding="0" cellspacing="1">
			<tr>
				<td height="20" width="50" valign="middle" bgcolor="#7BAEBD"
					class="texto" style="color: #fff; text-align: center">Código</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="427">Descripción</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="136">Ejecutar
				Proceso</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="82">Periodo</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="106">Estado</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="127">Generar Reporte</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="124">Generar	Plano</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="124">Descargar Errores</td>
				<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto"
					style="color: #fff; text-align: center" width="127">Generar	Resumen</td>
			</tr>

			<tr>
				<td height="20" class="texto" style="text-align: center">SIF011</td>
				<td height="20" class="texto" style="text-align: left" width="427">Egreso
				de asignaciones familiares del mes por causante</td>
				<td height="20" class="texto" style="text-align: center" width="136">
				<input type="button" name="btn_Procesar11" id="btn_Procesar11"
					class="btn_limp" value=" Procesar "
					onClick="GeneracionPROCsif011();"></td>
				<td height="20" class="texto" style="text-align: center" width="82">
				<input type="text" id="txt_periodo11" name="txt_periodo11"
					disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="106">
				<input type="text" id="txt_statusProceso11"
					name="txt_statusProceso11" disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="127">
				<a href="#" align="center" name="ref_excel11" id="ref_excel11"
					onClick="seteaFlagXls(1);orquestadorXls(1);">Excel</a> <!-- <a href="#" align="center" name="ref_excel11" id="ref_excel11" onClick="seteaFlagXls(1);generarXls(1);">Excel</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="124">
				<a href="#" align="center" name="ref_plano11" id="ref_plano11"
					onClick="seteaFlagTxt(1);validacionGeneracionTXTsif011();">Txt</a>
				<!-- <a href="#" align="center" name="ref_plano11" id="ref_plano11" onClick="seteaFlagTxt(1);generarTxt(1);">Txt</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="124">
				<a href="#" align="center" name="ref_error11" id="ref_error11"
					onClick="seteaFlagTxt(21);invocarProcesoCobolErrores(21,1);">Error</a>
				<!-- <a href="#" align="center" name="ref_plano11" id="ref_plano11" onClick="seteaFlagTxt(1);generarTxt(1);">Txt</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="127">
				<a href="#" align="center" name="ref_resumen11" id="ref_resumen11"
					onClick="seteaFlagXls(14);orquestadorResumenes(14);">Resumen</a> <!-- <a href="#" align="center" name="ref_resumen11" id="ref_resumen11" onClick="seteaFlagXls(14);generarResumenXLS(14);">Resumen</a> -->
				</td>
			</tr>
			<tr>
				<td height="20" class="texto" style="text-align: center">SIF012</td>
				<td height="20" class="texto" style="text-align: left" width="427">Egreso
				de asignaciones familiares retroactivas por causante</td>
				<td height="20" class="texto" style="text-align: center" width="136">
				<input type="button" name="btn_Procesar12" id="btn_Procesar12"
					class="btn_limp" value="Procesar" onClick="GeneracionPROCsif012();" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="82">
				<input type="text" id="txt_periodo12" name="txt_periodo12"
					disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="106">
				<input type="text" id="txt_statusProceso12"
					name="txt_statusProceso12" disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="127">
				<a href="#" align="center" name="ref_excel12" id="ref_excel12"
					onClick="seteaFlagXls(2);orquestadorXls(2);">Excel</a> <!-- <a href="#" align="center" name="ref_excel12" id="ref_excel12" onClick="seteaFlagXls(2);generarXls(2);">Excel</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="124">
				<a href="#" align="center" name="ref_plano12" id="ref_plano12"
					onClick="seteaFlagTxt(2);validacionGeneracionTXTsif012();">Txt</a>
				<!-- <a href="#" align="center" name="ref_plano12" id="ref_plano12" onClick="seteaFlagTxt(2);generarTxt(2);">Txt</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="124">
				<a href="#" align="center" name="ref_error12" id="ref_error12"
					onClick="seteaFlagTxt(22);invocarProcesoCobolErrores(22,2);">Error</a>
				</td>
				<td height="20" class="texto" style="text-align: center" width="127">
				<a href="#" align="center" name="ref_resumen12" id="ref_resumen12"
					onClick="seteaFlagXls(15);orquestadorResumenes(15);">Resumen</a> <!-- <a href="#" align="center" name="ref_resumen12" id="ref_resumen12" onClick="seteaFlagXls(15);generarResumenXLS(15);">Resumen</a> -->
				</td>
			</tr>
			<tr>
				<td height="20" class="texto" style="text-align: center">SIF014</td>
				<td height="20" class="texto" style="text-align: left" width="427">Reintegros
				de asignaciones familiares por causante</td>
				<td height="20" class="texto" style="text-align: center" width="136">
				<input type="button" name="btn_Procesar14" id="btn_Procesar14"
					class="btn_limp" value="Procesar"
					onClick="GeneracionPROCsif014();" /></td>
					<!--  onClick="seteaIdReporte(3);insertToMaestroSivegam();" /></td> -->
				<td height="20" class="texto" style="text-align: center" width="82">
				<input type="text" id="txt_periodo14" name="txt_periodo14"
					disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="106">
				<input type="text" id="txt_statusProceso14"
					name="txt_statusProceso14" disabled="true" size="13"
					style="background-color: transparent; border-width: 0px; text-align: center" />
				</td>
				<td height="20" class="texto" style="text-align: center" width="127">
				<a href="#" align="center" name="ref_excel14" id="ref_excel14"
					onClick="seteaFlagXls(3);orquestadorXls(3);">Excel</a> <!-- <a href="#" align="center" name="ref_excel14" id="ref_excel14" onClick="seteaFlagXls(3);generarXls(3);">Excel</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="124">
				<a href="#" align="center" name="ref_plano14" id="ref_plano14"
					onClick="seteaFlagTxt(3);orquestadorArchivosTxt(3);">Txt</a> <!-- <a href="#" align="center" name="ref_plano14" id="ref_plano14" onClick="seteaFlagTxt(3);generarTxt(3);">Txt</a> -->
				</td><td height="20" class="texto" style="text-align: center" width="124">
				<a href="#" align="center" name="ref_error14" id="ref_error14"
					onClick="seteaFlagTxt(23);invocarProcesoCobolErrores(23,3);">Error</a> <!-- <a href="#" align="center" name="ref_plano14" id="ref_plano14" onClick="seteaFlagTxt(3);generarTxt(3);">Txt</a> -->
				</td>
				<td height="20" class="texto" style="text-align: center" width="127">
				<a href="#" align="center" name="ref_resumen14" id="ref_resumen14"
					onClick="seteaFlagXls(16);orquestadorResumenes(16);">Resumen</a> <!-- <a href="#" align="center" name="ref_resumen14" id="ref_resumen14" onClick="seteaFlagXls(16);generarResumenXLS(16);">Resumen</a> -->
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
 </div>
 
</body>
</html:html>
