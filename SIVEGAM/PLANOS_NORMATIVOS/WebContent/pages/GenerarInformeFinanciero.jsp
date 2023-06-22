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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarInformeFinancieroDWR.js"></script>


<script type="text/javascript">
	
	var periodoProcesos = new Array();
	var anios = new Array();
	
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	function asignaValor(a){

		document.GenerarInformeFinancieroForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.GenerarInformeFinancieroForm.submit();
	}
	
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var DateSystem = '<%=session.getAttribute("FechaSistema")%>';
		var mesInformado = parseInt(DateSystem.substring(3,5),10) - 1;
		var anioInformado = parseInt(DateSystem.substring(6,10),10);
		
		if(mesInformado == 0){
			mesInformado = 12;
			anioInformado = anioInformado - 1;
		}
		DWREngine.setAsync(false);
		GenerarInformeFinancieroDWR.obtenerMesInformado(mesInformado, function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			if(resp == 0){
				document.GenerarInformeFinancieroForm.txt_MesProceso.value = data.periodoProceso + " " + anioInformado;
			}else{
				document.GenerarInformeFinancieroForm.txt_MesProceso.value = "";
			}	
		});
		DWREngine.setAsync(true);
	
	}

	/**Funcion que limpia los campos informativos*/
	function limpiarDataInformativa(){
		
		document.GenerarInformeFinancieroForm.dbx_meses.value = 0;
		document.GenerarInformeFinancieroForm.dbx_anio.value = 0;
		document.GenerarInformeFinancieroForm.auxano.value = 0;
		document.GenerarInformeFinancieroForm.txt_periodo.value = "";
		document.GenerarInformeFinancieroForm.txt_fecDepositoExcedente.value= "";
	}
	
	/**Funcion que reinicia las variables a cero.*/
	function limpiarCamposACero(){
		//INGRESOS
		document.GenerarInformeFinancieroForm.txt_provision.value = 0;
		document.GenerarInformeFinancieroForm.txt_reintegro.value = 0;
		document.GenerarInformeFinancieroForm.txt_totalIngresos.value = 0;
		
		//EGRESOS
		//document.GenerarInformeFinancieroForm.txt_pagoDelMes.value = 0;
		document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value = 0;
		document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value = 0;
		document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value = 0;
		document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value = 0;
		document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value = 0;
		
		//document.GenerarInformeFinancieroForm.txt_pagosRetroactivos.value = 0;
		document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value = 0;
		document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value = 0;
		document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value = 0;
		document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value = 0;
		document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value = 0;
		
		document.GenerarInformeFinancieroForm.txt_docRevalidados.value = 0;
		document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value = 0;
		document.GenerarInformeFinancieroForm.txt_totalEgresos.value = 0;
		
		//DEVOLUCIONES
		document.GenerarInformeFinancieroForm.txt_documentosCaducados.value = 0;
		document.GenerarInformeFinancieroForm.txt_documentosAnulados.value = 0;
		document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value = 0;
		
		//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
		document.GenerarInformeFinancieroForm.txt_TotalD.value = 0;
		
		//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
		document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value = 0;
		document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value = 0;
		document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value = 0;
		document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value = 0;
		document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value = 0;
		
		//SUPERAVIT O DEFICIT FINAL
		document.GenerarInformeFinancieroForm.txt_totalF.value = 0;	
	}
	
	var totalIngresos = 0;
	var totalPagoDelMes = 0;
	var totalPagosRetroactivos = 0;
	var docRevalidados = 0;
	var comisionAdministracion = 0;
	var totalUno = 0;
	var totalDos = 0;
	var totalEgresos = 0;
	var totalDevoluciones = 0;
	var totalD = 0;
	var totalDevolucionesE = 0;
	var totalF = 0;
	/*Funcion que calcula el total del item A.*/
	function calculaIngresos(){

		var provision = document.GenerarInformeFinancieroForm.txt_provision.value;
		var reintegro = document.GenerarInformeFinancieroForm.txt_reintegro.value;
		provision = provision.replace(/\./g,'');
		reintegro = reintegro.replace(/\./g,'');
		
		totalIngresos = parseInt(provision,10) + parseInt(reintegro,10);
		document.GenerarInformeFinancieroForm.txt_totalIngresos.value = "";
		document.GenerarInformeFinancieroForm.txt_totalIngresos.value = totalIngresos;
		
	}
	
	/**Funciones que calculan el total de los egresos*/
	function totalEgresosParteUno(){
	
		//var pagoDelMes = parseInt(document.GenerarInformeFinancieroForm.txt_pagoDelMes.value,10);
		var asigFamTrabActivos = document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value;
		var asigFamPensionados = document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value;
		var asigFamTrabCesantes = document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value;
		var asigFamInstituciones = document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value;
				
		asigFamTrabActivos = asigFamTrabActivos.replace(/\./g,'');
		asigFamPensionados = asigFamPensionados.replace(/\./g,'');
		asigFamTrabCesantes = asigFamTrabCesantes.replace(/\./g,'');
		asigFamInstituciones = asigFamInstituciones.replace(/\./g,'');
		    
		//totalPagoDelMes = pagoDelMes + asigFamTrabActivos + asigFamPensionados + asigFamTrabCesantes + asigFamInstituciones;
		totalPagoDelMes = parseInt(asigFamTrabActivos,10) + parseInt(asigFamPensionados,10) + parseInt(asigFamTrabCesantes,10) + parseInt(asigFamInstituciones,10);
		//document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value = "";
		document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value = totalPagoDelMes;
		separadorTotalPagoDelMes();
	}
	
	function totalEgresosParteDos(){ 
		
		//var pagosRetroactivos = parseInt(document.GenerarInformeFinancieroForm.txt_pagosRetroactivos.value,10);
		var asigFamTrabActiv = document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value;
		var asifFamTrabPens = document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value;
		var asigFamTrabCes = document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value;
		var asigFamTrabInst = document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value;
		var totalPagosRetroactivos = document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value;
		
		asigFamTrabActiv = asigFamTrabActiv.replace(/\./g,'');
		asifFamTrabPens = asifFamTrabPens.replace(/\./g,'');
		asigFamTrabCes = asigFamTrabCes.replace(/\./g,'');
		asigFamTrabInst = asigFamTrabInst.replace(/\./g,'');
		totalPagosRetroactivos = totalPagosRetroactivos.replace(/\./g,'');
		     
		//totalPagosRetroactivos = pagosRetroactivos + asigFamTrabActiv + asifFamTrabPens + asigFamTrabCes + asigFamTrabInst;
		totalPagosRetroactivos = parseInt(asigFamTrabActiv,10) + parseInt(asifFamTrabPens,10) + parseInt(asigFamTrabCes,10) + parseInt(asigFamTrabInst,10);
		//document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value = "";
		document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value = totalPagosRetroactivos;
		
	}
	
	function totalEgreso(){
		
		totalUno = document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value;
		totalDos = document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value;
		docRevalidados = document.GenerarInformeFinancieroForm.txt_docRevalidados.value;
		comisionAdministracion = document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value;
		
		totalUno = totalUno.replace(/\./g,'');
		totalDos = totalDos.replace(/\./g,'');
		docRevalidados = docRevalidados.replace(/\./g,'');
		comisionAdministracion = comisionAdministracion.replace(/\./g,'');
		    
		totalEgresos = parseInt(totalUno,10) + parseInt(totalDos,10) + parseInt(docRevalidados,10) + parseInt(comisionAdministracion,10);
		//document.GenerarInformeFinancieroForm.txt_totalEgresos.value = "";
		document.GenerarInformeFinancieroForm.txt_totalEgresos.value = totalEgresos;
		separadorTotalEgresos();
	}
	
	/*Funcion que calcula el total de Devoluciones.*/
	function totalDevolucionesC(){
	
		var documentosCaducados = document.GenerarInformeFinancieroForm.txt_documentosCaducados.value;
		var documentosAnulados = document.GenerarInformeFinancieroForm.txt_documentosAnulados.value;
		
		documentosCaducados = documentosCaducados.replace(/\./g,'');
		documentosAnulados = documentosAnulados.replace(/\./g,'');
		  
		totalDevoluciones = parseInt(documentosCaducados,10) + parseInt(documentosAnulados,10);
		//document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value = "";
		document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value = totalDevoluciones;
		separadorTotalDevoluciones();
	}
	
	/*Funcion que calcula el total de Superavit o deficit por pago de asignacion familiar y maternal.*/
	function totalBloqueD(){
	
		//totalD = parseInt(totalIngresos,10) - parseInt(totalEgresos,10) + parseInt(totalDevoluciones,10);
		var total_ingresos = document.GenerarInformeFinancieroForm.txt_totalIngresos.value;
		var total_egresos = document.GenerarInformeFinancieroForm.txt_totalEgresos.value;
		var total_devoluciones = document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value;
		
		total_ingresos = total_ingresos.replace(/\./g,'');
		total_egresos = total_egresos.replace(/\./g,'');
		total_devoluciones = total_devoluciones.replace(/\./g,'');
		
		totalD = parseInt(total_ingresos,10)
				 - parseInt(total_egresos,10)
				 + parseInt(total_devoluciones,10);
				 

		//document.GenerarInformeFinancieroForm.txt_TotalD.value = "";
		document.GenerarInformeFinancieroForm.txt_TotalD.value = totalD;
		separadorTotalD();
	}
	
	/*funcion que calcula el total de devoluciones de saldos a favor de empleadores.*/
	function totalDevolucionesBloqueE(){
		
		var saldoFavorEmpleador = document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value;
		var devolucionDocSAFEMCaducados = document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value;
		var devolucionDocSAFEMAnulados = document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value;
		var documentosSAFEMRevalidados = document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value;
		
		saldoFavorEmpleador = saldoFavorEmpleador.replace(/\./g,'');
		devolucionDocSAFEMCaducados = devolucionDocSAFEMCaducados.replace(/\./g,'');
		devolucionDocSAFEMAnulados = devolucionDocSAFEMAnulados.replace(/\./g,'');
		documentosSAFEMRevalidados = documentosSAFEMRevalidados.replace(/\./g,'');
		    
		totalDevolucionesE = parseInt(devolucionDocSAFEMCaducados,10) + parseInt(devolucionDocSAFEMAnulados,10) - parseInt(documentosSAFEMRevalidados,10);
		//document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value = "";
		document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value = totalDevolucionesE;
		separadorTotalDevolucionesE();
	}
	
	/*Funcion que calcula el total del superavit o deficit final.*/
	function total(){
		
		var total_d = document.GenerarInformeFinancieroForm.txt_TotalD.value;
		var total_devoluciones_e = document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value;
		
		total_d = total_d.replace(/\./g,'');
		total_devoluciones_e = total_devoluciones_e.replace(/\./g,'');
		
		totalF = parseInt(total_d,10) + parseInt(total_devoluciones_e,10);
		//document.GenerarInformeFinancieroForm.txt_totalF.value = "";
		document.GenerarInformeFinancieroForm.txt_totalF.value = totalF;
		separadorTotalF();
	}
	
	/**Funcion que calcula los montos totales.*/
	function calcularInformeFinanciero(){
		totalBloqueD();
		total();
	}
	
	/**Funcion que inserta */
	function insertarInformeFinanciero(){

		//informativos
		var _periodo = document.GenerarInformeFinancieroForm.txt_periodo.value;
		var _codigoEntidad = document.GenerarInformeFinancieroForm.txt_codigoEntidad.value;
		var _nombreEntidad = document.GenerarInformeFinancieroForm.txt_nombreEntidad.value;
		var _fechaDeposito = document.GenerarInformeFinancieroForm.txt_fecDepositoExcedente.value;
		
		//Ingresos
		var _provision = document.GenerarInformeFinancieroForm.txt_provision.value;
		var _reintegro = document.GenerarInformeFinancieroForm.txt_reintegro.value;
		var _totalIngresos = document.GenerarInformeFinancieroForm.txt_totalIngresos.value;
		
		//EGRESOS
		//pago del mes
		var _asigFamTrabActivos = document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value;
		var _asigFamPensionados = document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value;
		var _asigFamTrabCesantes = document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value;
		var _asigFamInstituciones = document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value;
		var _totalPagoDelMes = document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value;
		
		//pagos retroactivos
		var _asigFamTrabActiv = document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value;
		var _asifFamTrabPens = document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value;
		var _asigFamTrabCes = document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value;
		var _asigFamTrabInst = document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value;
		var _totalPagosRetroactivos = document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value;
		
		var _docRevalidados = document.GenerarInformeFinancieroForm.txt_docRevalidados.value;
		var _comisionAdministracion = document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value;
		var _totalEgresos = document.GenerarInformeFinancieroForm.txt_totalEgresos.value;
		
		//DEVOLUCIONES
		var _documentosCaducados = document.GenerarInformeFinancieroForm.txt_documentosCaducados.value;
		var _documentosAnulados = document.GenerarInformeFinancieroForm.txt_documentosAnulados.value;
		var _totalDevoluciones = document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value;
		
		//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
		var _TotalD = document.GenerarInformeFinancieroForm.txt_TotalD.value;
		
		//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
		var _saldoFavorEmpleador = document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value;
		var _devolucionDocSAFEMCaducados = document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value;
		var _devolucionDocSAFEMAnulados = document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value;
		var _documentosSAFEMRevalidados = document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value;
		var _totalDevolucionesE = document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value;
		
		//SUPERAVIT O DEFICIT FINAL
		var _totalF = document.GenerarInformeFinancieroForm.txt_totalF.value;
		
		if(validaciones() == true ){
		
			DWREngine.setAsync(false);
			GenerarInformeFinancieroDWR.insertInformeFinanciero(_periodo, _codigoEntidad, _nombreEntidad, _fechaDeposito, _provision, _reintegro, _totalIngresos,
																_asigFamTrabActivos, _asigFamPensionados, _asigFamTrabCesantes, _asigFamInstituciones, _totalPagoDelMes, 		
																_asigFamTrabActiv, _asifFamTrabPens, _asigFamTrabCes, _asigFamTrabInst, _totalPagosRetroactivos,
																_docRevalidados, _comisionAdministracion, _totalEgresos, _documentosCaducados, _documentosAnulados, 
																_totalDevoluciones, _TotalD, _saldoFavorEmpleador, _devolucionDocSAFEMCaducados, _devolucionDocSAFEMAnulados,
																_documentosSAFEMRevalidados, _totalDevolucionesE, _totalF, function(data){
				var respuesta = null;
				respuesta = data.codRespuesta;
				
				if(respuesta == 0){
					alert("Se ha guardado el informe financiero.");
					limpiarCamposACero();
					limpiarDataInformativa();
				}
			});	
			DWREngine.setAsync(true);
		}	
	}
	
	/*Funcion que valida los campos y verifica que no vaya ninguno vacio.*/
	function validaciones(){
	
		//informativos
		if(Trim(document.GenerarInformeFinancieroForm.txt_periodo.value) == ""){
			alert("Falta ingresar el campo Periodo");
			return false;
		}	
		
		if(Trim(document.GenerarInformeFinancieroForm.txt_codigoEntidad.value) == ""){
			alert("Falta ingresar el campo Código Entidad");
			return false;
		}
		
		if(Trim(document.GenerarInformeFinancieroForm.txt_nombreEntidad.value) == ""){
			alert("Falta ingresar el campo Nombre Entidad");
			return false;
		}
				
		if(Trim(document.GenerarInformeFinancieroForm.txt_fecDepositoExcedente.value) == ""){
			alert("Falta ingresar el campo Fecha Depósito");
			return false;
		}
				
		//Ingresos
		if(Trim(document.GenerarInformeFinancieroForm.txt_provision.value) == ""){
			alert("Falta ingresar el campo Provisión");
			return false;
		}		

		if(Trim(document.GenerarInformeFinancieroForm.txt_reintegro.value) == ""){
			alert("Falta ingresar el campo Reintegro");
			return false;
		}
		
		if(Trim(document.GenerarInformeFinancieroForm.txt_totalIngresos.value) == ""){
			alert("Falta ingresar el campo Total Ingresos");
			return false;
		}
				
		//EGRESOS
		//pago del mes
		if(Trim(document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de trabajadores Activos (B.1.1)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de trabajadores Pensionados (B.1.2)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de trabajadores Cesantes (B.1.3)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de Instituciones (B.1.4)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value) == ""){
			alert("Falta ingresar el campo Total Pago del Mes");
			return false;
		}
	
		//pagos retroactivos
		if(Trim(document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de trabajadores Activos (B.2.1)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de trabajadores Pensionados (B.2.2)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de trabajadores Cesantes (B.2.3)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value) == ""){
			alert("Falta ingresar el campo Asignación Familiar de Instituciones (B.2.4)");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value) == ""){
			alert("Falta ingresar el campo Total Pagos Retroactivos");
			return false;
		}
		
		if(Trim(document.GenerarInformeFinancieroForm.txt_docRevalidados.value) == ""){
			alert("Falta ingresar el campo Documentos Revalidados");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value) == ""){
			alert("Falta ingresar el campo Comisión de Administración");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_totalEgresos.value) == ""){
			alert("Falta ingresar el campo Total Egresos");
			return false;
		}
		
		//DEVOLUCIONES
		if(Trim(document.GenerarInformeFinancieroForm.txt_documentosCaducados.value) == ""){
			alert("Falta ingresar el campo Documentos Caducados");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_documentosAnulados.value) == ""){
			alert("Falta ingresar el campo Documentos Anulados");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value) == ""){
			alert("Falta ingresar el campo Total Devoluciones");
			return false;
		}
		
		//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
		if(Trim(document.GenerarInformeFinancieroForm.txt_TotalD.value) == ""){
			alert("Falta ingresar el campo Total del item D");
			return false;
		}
		
		//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
		if(Trim(document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value) == ""){
			alert("Falta ingresar el campo Saldo Favor Empleador");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value) == ""){
			alert("Falta ingresar el campo Devolución por Documentos SAFEM caducados");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value) == ""){
			alert("Falta ingresar el campo Devolución por Documentos SAFEM Anulados");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value) == ""){
			alert("Falta ingresar el campo Documentos SAFEM Revalidados");
			return false;
		}

		if(Trim(document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value) == ""){
			alert("Falta ingresar el campo Total Devoluciones del item E");
			return false;
		}
		
		//SUPERAVIT O DEFICIT FINAL
		if(Trim(document.GenerarInformeFinancieroForm.txt_totalF.value) == ""){
			alert("Falta ingresar el campo Total");
			return false;
		}
		
		return true;

	}
	
	/*Funcion que calcula el periodo del informe, solo se puede insertar un informe por mes.*/
	function calcularPeriodoInforme(){
	
		//calculo del periodo.
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anio = parseInt(fechaSistema.substring(6,10),10);
		var mes = parseInt(fechaSistema.substring(3,5),10) - 1;
		
		if(mes == 0){
			mes = 12;
			anio = anio - 1;
		}
		
		if(mes < 10){
			mes = '0'+ mes;
		}	
		
		var periodoCalculado = mes + "/" + anio;
		
		return periodoCalculado;
		
	}
	
	function calculaMes(){
		
		//calculo del periodo.
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		var anio = parseInt(fechaSistema.substring(6,10),10);
		var mes = parseInt(fechaSistema.substring(3,5),10);
		
		var retorno =  anio*100+mes;
		if(mes==1){
		   	retorno = retorno - 89;
		}else{
			retorno = retorno - 1;
		}
				
		return retorno;
	}
	
	/*Funcion que llena los campos informativos.*/
	function llenarCamposInformativos(){
		
		//calculo del periodo.
		/*var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		//var fechaSistema = "12/01/2012";
		var anio = fechaSistema.substring(6,10);
		var mes = parseInt(fechaSistema.substring(3,5),10) - 1;
		
		if(mes == 0){
			mes = 12;
			
		}
		
		if(mes < 10){
			mes = '0'+ mes;
		}	
		
		var periodoCalculado = mes + "/" + anio;
		document.GenerarInformeFinancieroForm.txt_periodo.value = periodoCalculado;
		*/
		
		//document.GenerarInformeFinancieroForm.txt_periodo.value = calcularPeriodoInforme();
		//asignacion otras variables.
		document.GenerarInformeFinancieroForm.txt_codigoEntidad.value = "10105"; 
		document.GenerarInformeFinancieroForm.txt_nombreEntidad.value = "C.C.A.F. LA ARAUCANA";
	
	}
	
	function obtenerInformeFinanciero(periodoSelected){
	
		//var periodoDelInforme = calcularPeriodoInforme();
		var periodoDelInforme = periodoSelected;
		DWREngine.setAsync(false);
		GenerarInformeFinancieroDWR.gettInformeFinanciero(periodoDelInforme, function(data){
			var informeFinanciero = null;
			var informeFinanciero = data; 
			
			var ingreso = null;
			var pagoDelMes = null;
			var pagoRetroactivo = null;
			var egresos = null;
			var devoluciones = null;
			var devolucionDeSaldos = null;
			var infoInformeFinanciero = null;
			
			if(informeFinanciero.codResultado != 0){
				alert("No existe Informe Financiero para el periodo seleccionado. Ingrese los montos.");
				limpiarCamposACero();					
			}else{
			
				alert("Ya existe un Informe Financiero para el periodo seleccionado.");
				ingreso = informeFinanciero.ingresoVO;
				pagoDelMes = informeFinanciero.pagoDelMesVO;
				pagoRetroactivo = informeFinanciero.pagosRetroVO;
				egresos = informeFinanciero.egresosVO;
				devoluciones = informeFinanciero.devolucionesVO;
				devolucionDeSaldos = informeFinanciero.devolucionSaldosVO;
				infoInformeFinanciero = informeFinanciero.informacionInformeFinancieroVO;
				
				//informativos
				document.GenerarInformeFinancieroForm.idInfoInformeFinanciero.value = infoInformeFinanciero.idInformeFinanciero;
				document.GenerarInformeFinancieroForm.txt_periodo.value = infoInformeFinanciero.periodo;
				document.GenerarInformeFinancieroForm.txt_codigoEntidad.value = infoInformeFinanciero.codigoEntidad;
				document.GenerarInformeFinancieroForm.txt_nombreEntidad.value = infoInformeFinanciero.nombreEntidad;
				document.GenerarInformeFinancieroForm.txt_fecDepositoExcedente.value = infoInformeFinanciero.fecDepositoExcedente;
				
				//Ingresos
				document.GenerarInformeFinancieroForm.idIngresos.value = ingreso.idIngresos;
				document.GenerarInformeFinancieroForm.txt_provision.value = separadorDeMiles(ingreso.provision);
				document.GenerarInformeFinancieroForm.txt_reintegro.value = separadorDeMiles(ingreso.reintegro);
				document.GenerarInformeFinancieroForm.txt_totalIngresos.value = separadorDeMiles(ingreso.totalIngresos);
				
				//EGRESOS
				//pago del mes
				document.GenerarInformeFinancieroForm.idPagoDelMes.value = pagoDelMes.idPagoMes;
				document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value = separadorDeMiles(pagoDelMes.asigFamTrabActivos);
				document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value = separadorDeMiles(pagoDelMes.asigFamPensionados);
				document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value = separadorDeMiles(pagoDelMes.asigFamTrabCesantes);
				document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value = separadorDeMiles(pagoDelMes.asigFamInstituciones);
				document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value = separadorDeMiles(pagoDelMes.totalPagoDelMes);
				
				//pagos retroactivos
				document.GenerarInformeFinancieroForm.idPagoRetroactivos.value = pagoRetroactivo.idPagosRetroactivos;
				document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value = separadorDeMiles(pagoRetroactivo.asigFamTrabActiv);
				document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value = separadorDeMiles(pagoRetroactivo.asifFamTrabPens);
				document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value = separadorDeMiles(pagoRetroactivo.asigFamTrabCes);
				document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value = separadorDeMiles(pagoRetroactivo.asigFamTrabInst);
				document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value = separadorDeMiles(pagoRetroactivo.totalPagosRetroactivos);
				
				document.GenerarInformeFinancieroForm.idEgresos.value = egresos.idEgresos;
				document.GenerarInformeFinancieroForm.txt_docRevalidados.value = separadorDeMiles(egresos.docRevalidados);
				document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value = separadorDeMiles(egresos.comisionAdministracion);
				document.GenerarInformeFinancieroForm.txt_totalEgresos.value = separadorDeMiles(egresos.totalEgresos);
				
				//DEVOLUCIONES
				document.GenerarInformeFinancieroForm.idDevoluciones.value = devoluciones.idDevoluciones;
				document.GenerarInformeFinancieroForm.txt_documentosCaducados.value = separadorDeMiles(devoluciones.documentosCaducados);
				document.GenerarInformeFinancieroForm.txt_documentosAnulados.value = separadorDeMiles(devoluciones.documentosAnulados);
				document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value = separadorDeMiles(devoluciones.totalDevoluciones);
				
				//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
				document.GenerarInformeFinancieroForm.txt_TotalD.value = separadorDeMiles(infoInformeFinanciero.totalSuperAvitDeficit);
				
				//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
				document.GenerarInformeFinancieroForm.idDevolucionDeSaldos.value = devolucionDeSaldos.idDevolucionDeSaldos;
				document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value = separadorDeMiles(devolucionDeSaldos.saldoFavorEmpleador);
				document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value = separadorDeMiles(devolucionDeSaldos.devolucionDocSAFEMCaducados);
				document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value = separadorDeMiles(devolucionDeSaldos.devolucionDocSAFEMAnulados);
				document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value = separadorDeMiles(devolucionDeSaldos.documentosSAFEMRevalidados);
				document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value = separadorDeMiles(devolucionDeSaldos.totalDevolucionesE);
				
				//SUPERAVIT O DEFICIT FINAL
				document.GenerarInformeFinancieroForm.txt_totalF.value = separadorDeMiles(infoInformeFinanciero.totalSuperAvitDeficitFinal);
			}
		});
		
		DWREngine.setAsync(true);
		
	}
	
	/**Funcion que discrimina si lo que se presiona en el boton es insertar o modificar. Para ello se usa el idInfoInformeFinanciero*/
	function discriminaGuardar(){
		
		var idInformado = document.GenerarInformeFinancieroForm.idInfoInformeFinanciero.value;
		
		if(idInformado == 0){
			insertarInformeFinanciero();
		}		
		else{
			modificarInformeFinanciero();
		}
	}
	

	
	/*funcion que realiza la modificacion del informe financiero.*/
	function modificarInformeFinanciero(){

		//informativos
		var _idInfoInformeFinanciero = document.GenerarInformeFinancieroForm.idInfoInformeFinanciero.value;
		var _periodo = document.GenerarInformeFinancieroForm.txt_periodo.value;
		var _codigoEntidad = document.GenerarInformeFinancieroForm.txt_codigoEntidad.value;
		var _nombreEntidad = document.GenerarInformeFinancieroForm.txt_nombreEntidad.value;
		var _fechaDeposito = document.GenerarInformeFinancieroForm.txt_fecDepositoExcedente.value;
	
		//Ingresos
		var _idIngresos = document.GenerarInformeFinancieroForm.idIngresos.value;
		var _provision = document.GenerarInformeFinancieroForm.txt_provision.value;
		var _reintegro = document.GenerarInformeFinancieroForm.txt_reintegro.value;
		var _totalIngresos = document.GenerarInformeFinancieroForm.txt_totalIngresos.value;
		
		//EGRESOS
		//pago del mes
		var _idPagoDelMes = document.GenerarInformeFinancieroForm.idPagoDelMes.value;
		var _asigFamTrabActivos = document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value;
		var _asigFamPensionados = document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value;
		var _asigFamTrabCesantes = document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value;
		var _asigFamInstituciones = document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value;
		var _totalPagoDelMes = document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value;
		
		//pagos retroactivos
		var _idPagoRetroactivos = document.GenerarInformeFinancieroForm.idPagoRetroactivos.value;
		var _asigFamTrabActiv = document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value;
		var _asifFamTrabPens = document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value;
		var _asigFamTrabCes = document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value;
		var _asigFamTrabInst = document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value;
		var _totalPagosRetroactivos = document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value;
		
		var _idEgresos = document.GenerarInformeFinancieroForm.idEgresos.value;
		var _docRevalidados = document.GenerarInformeFinancieroForm.txt_docRevalidados.value;
		var _comisionAdministracion = document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value;
		var _totalEgresos = document.GenerarInformeFinancieroForm.txt_totalEgresos.value;
		
		//DEVOLUCIONES
		var _idDevoluciones = document.GenerarInformeFinancieroForm.idDevoluciones.value;
		var _documentosCaducados = document.GenerarInformeFinancieroForm.txt_documentosCaducados.value;
		var _documentosAnulados = document.GenerarInformeFinancieroForm.txt_documentosAnulados.value;
		var _totalDevoluciones = document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value;
		
		//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
		var _TotalD = document.GenerarInformeFinancieroForm.txt_TotalD.value;
		
		//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
		var _idDevolucionDeSaldos = document.GenerarInformeFinancieroForm.idDevolucionDeSaldos.value;
		var _saldoFavorEmpleador = document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value;
		var _devolucionDocSAFEMCaducados = document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value;
		var _devolucionDocSAFEMAnulados = document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value;
		var _documentosSAFEMRevalidados = document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value;
		var _totalDevolucionesE = document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value;
		
		//SUPERAVIT O DEFICIT FINAL
		var _totalF = document.GenerarInformeFinancieroForm.txt_totalF.value;
		
		if(validaciones() == true ){
		
			DWREngine.setAsync(false);
			GenerarInformeFinancieroDWR.updateInformeFinanciero(_idInfoInformeFinanciero,_idEgresos,
																_periodo, _codigoEntidad, _nombreEntidad, _fechaDeposito, _provision, _reintegro, _totalIngresos,
																_asigFamTrabActivos, _asigFamPensionados, _asigFamTrabCesantes, _asigFamInstituciones, _totalPagoDelMes, 		
																_asigFamTrabActiv, _asifFamTrabPens, _asigFamTrabCes, _asigFamTrabInst, _totalPagosRetroactivos,
																_docRevalidados, _comisionAdministracion, _totalEgresos, _documentosCaducados, _documentosAnulados, 
																_totalDevoluciones, _TotalD, _saldoFavorEmpleador, _devolucionDocSAFEMCaducados, _devolucionDocSAFEMAnulados,
																_documentosSAFEMRevalidados, _totalDevolucionesE, _totalF, function(data){
				var respuesta = null;
				respuesta = data.codRespuesta;
				
				if(respuesta == 0){
					alert("Se ha modificado el informe financiero.");
					limpiarCamposACero();
					document.GenerarInformeFinancieroForm.txt_fecDepositoExcedente.value = "";
				}else{
					alert("Hubo un problema al modificar el informe financiero.");
				}
			});	
			DWREngine.setAsync(true);
		}		
		
	}
	
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
		GenerarInformeFinancieroDWR.obtenerDataPeriodoProceso("PeriodoProceso", function(data){
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
		var cmb = document.GenerarInformeFinancieroForm.dbx_meses;
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
		
		var periodoSeleccionado = calculaMes();
		var verificadorMes = periodoSeleccionado%100;
		var verificadorAño = parseInt((periodoSeleccionado - verificadorMes)/100,10);
		
		document.GenerarInformeFinancieroForm.dbx_meses.value = verificadorMes;
		document.GenerarInformeFinancieroForm.dbx_anio.value  = verificadorAño;		
		document.GenerarInformeFinancieroForm.auxano.value = verificadorAño;
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

		var cmb = document.GenerarInformeFinancieroForm.dbx_anio;
		
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
		
		var cmb = document.GenerarInformeFinancieroForm.dbx_anio;
		cmb.options.length = 0;
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var i=0; i<anios.length; i++){
			
			var item = null;
			item = anios[i];
			
			cod = item.codigo_anio;
			txt = item.glosa_anio;
			
			cmb.options[i+1] = new Option(txt,cod);	
		}	
	}	//cargarMesInformado();
	
	/*Funcion que actualiza el campo periodo dependiendo del periodo seleccionado.*/
	var periodoSelected = "";
	function actualizaCampoPeriodoYMesInformado(){
		
		limpiarHidden();
		periodoSelected = "";
		var mesSelected = document.GenerarInformeFinancieroForm.dbx_meses.value;
		var anioSelected = document.GenerarInformeFinancieroForm.dbx_anio.value;
		
		if(parseInt(mesSelected,10) < 10){
			mesSelected = '0' + mesSelected;
		}
			
		
			
		periodoSelected = mesSelected + "/" + anioSelected;
		var mesInformadoSelected = mesSelected + " " + anioSelected;
		
		
		document.GenerarInformeFinancieroForm.txt_periodo.value = periodoSelected;
		//document.GenerarInformeFinancieroForm.txt_MesProceso.value = mesInformadoSelected;
		obtenerInformeFinanciero(periodoSelected);
	}
	
	/*Funcion que limpia los hidden*/
	function limpiarHidden(){
	
		document.GenerarInformeFinancieroForm.idIngresos.value="0";
		document.GenerarInformeFinancieroForm.idPagoDelMes.value="0";
		document.GenerarInformeFinancieroForm.idPagoRetroactivos.value="0";
		document.GenerarInformeFinancieroForm.idEgresos.value="0";
		document.GenerarInformeFinancieroForm.idDevoluciones.value="0";
		document.GenerarInformeFinancieroForm.idDevolucionDeSaldos.value="0";
		document.GenerarInformeFinancieroForm.idInfoInformeFinanciero.value="0";
		
	}
	
	/**Funciones que realizan la separacion de miles.*/
	function separadorProvision(){
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_provision.value);
		document.GenerarInformeFinancieroForm.txt_provision.value = campo;
	}
	
	function separadorReintegro(){
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_reintegro.value);
		document.GenerarInformeFinancieroForm.txt_reintegro.value = campo;
	}
	
	function separadorTotalIngresos(){
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_totalIngresos.value);
		document.GenerarInformeFinancieroForm.txt_totalIngresos.value = campo;
	}
				
	//EGRESOS
	//pago del mes		
	function separadorAsigFamTrabActivos(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value);
		document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value = campo;	
	}		
			
	function separadorAsigFamPensionados(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value);
		document.GenerarInformeFinancieroForm.txt_asigFamPensionados.value = campo;	
	}		
			
	function separadorAsigFamTrabCesantes(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value);
		document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value = campo;	
	}		
			
	function separadorAsigFamInstituciones(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value);
		document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value = campo;	
	}		
			
	function separadorTotalPagoDelMes(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value);
		document.GenerarInformeFinancieroForm.txt_totalPagoDelMes.value = campo;
	}	 	
	//pagos retroactivos		
	function separadorAsigFamTrabActiv(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value);
		document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value = campo;	
	}		
			
	function separadorAsifFamTrabPens(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value);
		document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value = campo;	
	}		
			
	function separadorAsigFamTrabCes(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value);
		document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value = campo;	
	}		
			
	function separadorAsigFamTrabInst(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value);
		document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value = campo;	
	}		
			
	function separadorTotalPagosRetroactivos(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value);
		document.GenerarInformeFinancieroForm.txt_totalPagosRetroactivos.value = campo;	
	}
			
	function separadorDocRevalidados(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_docRevalidados.value);
		document.GenerarInformeFinancieroForm.txt_docRevalidados.value = campo;	
	}		
			
	function separadorComisionAdministracion(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value);
		document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value = campo;	
	}		
			
	function separadorTotalEgresos(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_totalEgresos.value);
		document.GenerarInformeFinancieroForm.txt_totalEgresos.value = campo;		
	}
				
	//DEVOLUCIONES		
	function separadorDocumentosCaducados(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_documentosCaducados.value);
		document.GenerarInformeFinancieroForm.txt_documentosCaducados.value = campo;	
	}		
			
	function separadorDocumentosAnulados(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_documentosAnulados.value);
		document.GenerarInformeFinancieroForm.txt_documentosAnulados.value = campo;	
	}		
			
	function separadorTotalDevoluciones(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value);
		document.GenerarInformeFinancieroForm.txt_totalDevoluciones.value = campo;	
	}
				
	//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
	function separadorTotalD(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_TotalD.value);
		document.GenerarInformeFinancieroForm.txt_TotalD.value = campo;		
	}
	
	//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES		
	function separadorSaldoFavorEmpleador(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value);
		document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value = campo;	
	}		
			
	function separadorDevolucionDocSAFEMCaducados(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value);
		document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value = campo;	
	}		
			
	function separadorDevolucionDocSAFEMAnulados(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value);
		document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value = campo;	
	}		
			
	function separadorDocumentosSAFEMRevalidados(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value);
		document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value = campo;	
	}		
			
	function separadorTotalDevolucionesE(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value);
		document.GenerarInformeFinancieroForm.txt_totalDevolucionesE.value = campo;		
	}
				
	//SUPERAVIT O DEFICIT FINAL
	function separadorTotalF(){		
		var campo = separadorDeMiles(document.GenerarInformeFinancieroForm.txt_totalF.value);
		document.GenerarInformeFinancieroForm.txt_totalF.value = campo;		
	}
	/**Fin funciones separador de miles.*/
	
	/*funcion que llama a otra ventana de windows*/
	function openWindowsCuadroComparativo(){
	
		var mesDeBusqueda = document.GenerarInformeFinancieroForm.dbx_meses.value;
		var anioBusqueda = document.GenerarInformeFinancieroForm.dbx_anio.value;
		var opciones  = "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1000, height=2000";
		
		window.open("./pages/GenerarCuadroComparativo.jsp?mes="+mesDeBusqueda+"&anio="+anioBusqueda,"",opciones);
	}	
	
	function actualizaCampoPeriodoYMesInformadoConf(){
		var conf;
		conf = confirm("Se reiniciaran los datos ingresados. ¿Desea continuar?");
		if(conf == true){
			document.GenerarInformeFinancieroForm.auxano.value = document.GenerarInformeFinancieroForm.dbx_anio.value;
			actualizaCampoPeriodoYMesInformado();
		}else{
			document.GenerarInformeFinancieroForm.dbx_anio.value = document.GenerarInformeFinancieroForm.auxano.value;
		}
	}
	
	function validadorVacio(){
		if(document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asifFamTrabPens.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asigFamInstituciones.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asigFamTrabActiv.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asigFamTrabActivos.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asigFamTrabCes.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asigFamTrabCesantes.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value ==  ""){ document.GenerarInformeFinancieroForm.txt_asigFamTrabInst.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value ==  ""){ document.GenerarInformeFinancieroForm.txt_comisionAdministracion.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value ==  ""){ document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMAnulados.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value ==  ""){ document.GenerarInformeFinancieroForm.txt_devolucionDocSAFEMCaducados.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_docRevalidados.value ==  ""){ document.GenerarInformeFinancieroForm.txt_docRevalidados.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_documentosAnulados.value ==  ""){ document.GenerarInformeFinancieroForm.txt_documentosAnulados.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_documentosCaducados.value ==  ""){ document.GenerarInformeFinancieroForm.txt_documentosCaducados.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value ==  ""){ document.GenerarInformeFinancieroForm.txt_documentosSAFEMRevalidados.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_provision.value ==  ""){ document.GenerarInformeFinancieroForm.txt_provision.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_reintegro.value ==  ""){ document.GenerarInformeFinancieroForm.txt_reintegro.value = 0;}
		if(document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value ==  ""){ document.GenerarInformeFinancieroForm.txt_saldoFavorEmpleador.value = 0;}

		
	}
	
	/*Funcion que se encarga de llamar a todas las funciones iniciales que seran llamadas en el onload. Cada funcion que se desee cargar al inicio de la
	aplicacion, deberá ser llamada desde esta funcion en el orden correspondiente.*/
	function cargarEnOnload(){
		limpiarCamposACero();
		llenarCamposInformativos();
		cargarArregloParametricas();
		obtenerComboPeriodoProceso();
		selectedItemCombo();
		actualizaCampoPeriodoYMesInformado();
		//obtenerInformeFinanciero();
	}
	
	


</script>
</head>
<body onload="cargarEnOnload();">
<html:form action="/informeFinanc.do">
	<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="numeroMes" value="0">
	<input type="hidden" name="idIngresos" value="0">
	<input type="hidden" name="idPagoDelMes" value="0">
	<input type="hidden" name="idPagoRetroactivos" value="0">
	<input type="hidden" name="idEgresos" value="0">
	<input type="hidden" name="idDevoluciones" value="0">
	<input type="hidden" name="idDevolucionDeSaldos" value="0">
	<input type="hidden" name="idInfoInformeFinanciero" value="0">
	<input type="hidden" name="auxano" value="0">
	
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
			<td width="70%" height="40">
			 <table border="0">
				<tr>
					<td><strong><p><p1>INFORME FINANCIERO</p1></p></strong>
					<strong><p><p1>SISTEMA UNICO DE PRESTACIONES FAMILIARES</p1></p></strong></td>
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
					<!-- <td>Mes Informado</td>
					<td><input type="text" name="txt_MesProceso" id="txt_MesProceso" disabled="true" size="20" /></td> -->
				</tr>
			  </table>
		   </td>	
		</tr>
		<tr>
			<td width="970"><br/>
			<p><p2>1. Información</p2></p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td width="32%"><strong>Seleccione Periodo</strong></td>
						<td>
							<html:select property="dbx_meses" styleClass="combobox" value="0" onchange="actualizaCampoPeriodoYMesInformadoConf();">
								<html:option value="0">Seleccione </html:option>
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select property="dbx_anio" styleClass="comboAnio" value="0" onchange="actualizaCampoPeriodoYMesInformadoConf();">
								<html:option value="0">Seleccione </html:option>
							</html:select>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td width="32%"><strong>Periodo</strong></td>
						<td>
							<input type="text" name="txt_periodo" id="txt_periodo" maxlength="7" size="20" disabled="true"/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td width="32%"><strong>Código Entidad</strong></td>
						<td>
							<input type="text" name="txt_codigoEntidad" id="txt_codigoEntidad" maxlength="7" size="20" disabled="true"/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td width="32%"><strong>Nombre Entidad</strong></td>
						<td>
							<input type="text" name="txt_nombreEntidad" id="txt_nombreEntidad" maxlength="50" size="20" disabled="true"/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td width="32%"><strong>Fecha Depósito Excedente</strong></td>
						<td>
							<input type="text" name="txt_fecDepositoExcedente" id="txt_fecDepositoExcedente" maxlength="10" size="10" disabled="true"/>
							<IMG style="cursor:hand" border="0" src="./images/Calendar.jpg" width="20" height="18" onClick="ShowCalendarFor(txt_fecDepositoExcedente);"/>
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
			<td width="970"><br/>
			<p><p2>A(+) INGRESOS</p2></p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td width="50%"><strong>A.1 Provisión ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_provision" id="txt_provision" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>A.2 Reintegro por cobro indebido asignaciones familiares y maternales ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_reintegro" id="txt_reintegro" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total Ingresos ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_totalIngresos" id="txt_totalIngresos" maxlength="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="10"  style="text-align: right" disabled="true"/>
						</td>
					</tr>
				</table>
			</td>	
		</tr>
		<tr>
			<td width="970"><br/>
			<p><p2>B(-) EGRESOS</p2></p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td width="50%"><p><p2>B.1 PAGO DEL MES ($)</p2></p></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<!-- <input type="text" name="txt_pagoDelMes" id="txt_pagoDelMes" maxlength="10" size="10" style="text-align: right" onchange="totalEgresosParteUno();"/> -->
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.1.1 Asignación Familiar de trabajadores activos ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asigFamTrabActivos" id="txt_asigFamTrabActivos" maxlength="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="10" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.1.2 Asignación Familiar de pensionados ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asigFamPensionados" id="txt_asigFamPensionados" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.1.3 Asignación Familiar de trabajadores cesantes ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asigFamTrabCesantes" id="txt_asigFamTrabCesantes" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.1.4 Asignación Familiar de Instituciones ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asigFamInstituciones" id="txt_asigFamInstituciones" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total Pago del Mes ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_totalPagoDelMes" id="txt_totalPagoDelMes" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"></td>
						<td width="100%" align="right">
						</td>
					</tr>
					<tr>
						<td width="50%"><p><p2>B.2 PAGOS RETROACTIVOS ($)</p2></p></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<!-- <input type="text" name="txt_pagosRetroactivos" id="txt_pagosRetroactivos" maxlength="10" size="10" style="text-align: right" onchange="totalEgresosParteDos();"/> -->
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.2.1 Asignación Familiar de trabajadores activos ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asigFamTrabActiv" id="txt_asigFamTrabActiv" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.2.2 Asignación Familiar de pensionados ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asifFamTrabPens" id="txt_asifFamTrabPens" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.2.3 Asignación Familiar de trabajadores cesantes ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asigFamTrabCes" id="txt_asigFamTrabCes" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.2.4 Asignación Familiar de Instituciones ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_asigFamTrabInst" id="txt_asigFamTrabInst" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total Pagos Retroactivos ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_totalPagosRetroactivos" id="txt_totalPagosRetroactivos" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"></td>
						<td width="100%" align="right">
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.3 Documentos Revalidados (Pago Directo a Beneficiarios) ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_docRevalidados" id="txt_docRevalidados" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>B.4 Comisión de Administración ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_comisionAdministracion" id="txt_comisionAdministracion" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total Egresos ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_totalEgresos" id="txt_totalEgresos" maxlength="10" size="10" disabled="true" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br/>
			<p><p2>C(+) DEVOLUCIONES</p2></p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td width="50%"><strong>C.1 Documentos Caducados (Pagos Directos) ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_documentosCaducados" id="txt_documentosCaducados" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>C.2 Documentos Anulados (Pago Directo) ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_documentosAnulados" id="txt_documentosAnulados" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total Devoluciones ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_totalDevoluciones" id="txt_totalDevoluciones" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
				</table>
			</td>	
		</tr>		
		<tr>
			<td width="970"><br/>
			<p><p2>D SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL (A - B + C)  </p2></p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_TotalD" id="txt_TotalD" maxlength="10" size="10" disabled="true" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right"/>
						</td>
					</tr>
				</table>
			</td>		
		</tr>
		<tr>
			<td width="970"><br/>
			<p><p2>E DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES (E.2 + E.3 - E.4)  </p2></p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td width="50%"><strong>E.1 Saldo a favor empleador (SAFEM) ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_saldoFavorEmpleador" id="txt_saldoFavorEmpleador" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>E.2 Devolución por documentos SAFEM caducados ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_devolucionDocSAFEMCaducados" id="txt_devolucionDocSAFEMCaducados" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>E.3 Devolución por documentos SAFEM anulados ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_devolucionDocSAFEMAnulados" id="txt_devolucionDocSAFEMAnulados" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>
					<tr>
						<td width="50%"><strong>E.4 Documentos SAFEM revalidados ($)</strong></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="100%" align="right">
							<input type="text" name="txt_documentosSAFEMRevalidados" id="txt_documentosSAFEMRevalidados" maxlength="10" size="10" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right" onchange="validadorVacio();calculaIngresos();totalEgresosParteUno();totalEgresosParteDos();totalEgreso();totalDevolucionesC();totalBloqueD();totalDevolucionesBloqueE();total();"/>
						</td>
					</tr>					
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total Devoluciones ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_totalDevolucionesE" id="txt_totalDevolucionesE" maxlength="10" size="10" disabled="true" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br/>
			<p><p2>F SUPERAVIT O DEFICIT FINAL (D + E)  </p2></p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td width="50%"></td>
						<td></td>
						<td colspan="3"></td>
						<td></td>
						<td width="32%" align="right"><strong>Total ($)</strong></td>
						<td width="100%" align="right">
							<input type="text" name="txt_totalF" id="txt_totalF" maxlength="10" size="10" disabled="true" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" style="text-align: right"/>
						</td>
					</tr>
				</table>
			</td>		
		</tr>				
		<tr>
			<td height="37" align="right" width="970">
				<!-- <input type="button" name="btn_Calcular" id="btn_Calcular" class="btn_limp" value="Calcular" onclick="calcularInformeFinanciero();" />			
				&nbsp;&nbsp;&nbsp;&nbsp; -->
				<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="discriminaGuardar();" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Diferencias" id="btn_Diferencias" class="btn_limp" value="Diferencias" onclick="openWindowsCuadroComparativo();" />
				<!-- <input type="button" name="btn_Diferencias" id="btn_Diferencias" class="btn_limp" value="Diferencias" onclick="enviaFormulario(2);" /> -->
				&nbsp;&nbsp;&nbsp;&nbsp;
				<!--<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);" />-->
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cerrar" onclick="javascript:closeSesion()" />
				
			</td>
		</tr>
	</table>
	</div>
</html:form>
</body>
</html:html>
