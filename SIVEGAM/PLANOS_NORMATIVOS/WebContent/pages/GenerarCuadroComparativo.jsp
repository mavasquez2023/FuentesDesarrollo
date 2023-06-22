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
<link href="../css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="../css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="../js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/interface/GenerarCuadroComparativoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/interface/GenerarInformeFinancieroDWR.js"></script>


<script type="text/javascript">
	
	var periodoProcesos = new Array();
	var anios = new Array();
	
	function asignaValor(a){

		document.GenerarCuadroComparativoForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.GenerarCuadroComparativoForm.submit();
	}
	
	/*Funcion que limpia los campos y los mantien en cero.*/
	function camposEnCero(){
		//INGRESOS
		document.GenerarCuadroComparativoForm.txt_provision.value = 0;
		document.GenerarCuadroComparativoForm.txt_reintegro.value = 0;
		document.GenerarCuadroComparativoForm.txt_totalIngresos.value = 0;
		
		//EGRESOS
		//document.GenerarInformeFinancieroForm.txt_pagoDelMes.value = 0;
		document.GenerarCuadroComparativoForm.txt_asigFamTrabActivos.value = 0;
		document.GenerarCuadroComparativoForm.txt_asigFamPensionados.value = 0;
		document.GenerarCuadroComparativoForm.txt_asigFamTrabCesantes.value = 0;
		document.GenerarCuadroComparativoForm.txt_asigFamInstituciones.value = 0;
		document.GenerarCuadroComparativoForm.txt_totalPagoDelMes.value = 0;
		
		//document.GenerarCuadroComparativoForm.txt_pagosRetroactivos.value = 0;
		document.GenerarCuadroComparativoForm.txt_asigFamTrabActiv.value = 0;
		document.GenerarCuadroComparativoForm.txt_asifFamTrabPens.value = 0;
		document.GenerarCuadroComparativoForm.txt_asigFamTrabCes.value = 0;
		document.GenerarCuadroComparativoForm.txt_asigFamTrabInst.value = 0;
		document.GenerarCuadroComparativoForm.txt_totalPagosRetroactivos.value = 0;
		
		document.GenerarCuadroComparativoForm.txt_docRevalidados.value = 0;
		document.GenerarCuadroComparativoForm.txt_comisionAdministracion.value = 0;
		document.GenerarCuadroComparativoForm.txt_totalEgresos.value = 0;
		
		//DEVOLUCIONES
		document.GenerarCuadroComparativoForm.txt_documentosCaducados.value = 0;
		document.GenerarCuadroComparativoForm.txt_documentosAnulados.value = 0;
		document.GenerarCuadroComparativoForm.txt_totalDevoluciones.value = 0;
		
		//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
		document.GenerarCuadroComparativoForm.txt_TotalD.value = 0;
		
		//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
		document.GenerarCuadroComparativoForm.txt_saldoFavorEmpleador.value = 0;
		document.GenerarCuadroComparativoForm.txt_devolucionDocSAFEMCaducados.value = 0;
		document.GenerarCuadroComparativoForm.txt_devolucionDocSAFEMAnulados.value = 0;
		document.GenerarCuadroComparativoForm.txt_documentosSAFEMRevalidados.value = 0;
		document.GenerarCuadroComparativoForm.txt_totalDevolucionesE.value = 0;
		
		//SUPERAVIT O DEFICIT FINAL
		document.GenerarCuadroComparativoForm.txt_totalF.value = 0;	
	}

	/*Funcion que obtiene el informe financiero, para el periodo seleccionado.*/
	function obtenerInformeFinanciero(periodoSelected){
	
		//var periodoDelInforme = calcularPeriodoInforme();
		var periodoDelInforme = periodoSelected;
		DWREngine.setAsync(false);
		GenerarInformeFinancieroDWR.gettInformeFinanciero(periodoDelInforme, function(data){
			var informeFinanciero = null;
			informeFinanciero = data; 
			
			var ingreso = null;
			var pagoDelMes = null;
			var pagoRetroactivo = null;
			var egresos = null;
			var devoluciones = null;
			var devolucionDeSaldos = null;
			var infoInformeFinanciero = null;
			
			if(informeFinanciero.codResultado != 0){
				
				camposEnCero();					
			}else{
			

				ingreso = informeFinanciero.ingresoVO;
				pagoDelMes = informeFinanciero.pagoDelMesVO;
				pagoRetroactivo = informeFinanciero.pagosRetroVO;
				egresos = informeFinanciero.egresosVO;
				devoluciones = informeFinanciero.devolucionesVO;
				devolucionDeSaldos = informeFinanciero.devolucionSaldosVO;
				infoInformeFinanciero = informeFinanciero.informacionInformeFinancieroVO;
				
				//informativos
				document.GenerarCuadroComparativoForm.idInfoInformeFinanciero.value = infoInformeFinanciero.idInformeFinanciero;
				document.GenerarCuadroComparativoForm.txt_periodo.value = infoInformeFinanciero.periodo;
				document.GenerarCuadroComparativoForm.txt_codigoEntidad.value = infoInformeFinanciero.codigoEntidad;
				document.GenerarCuadroComparativoForm.txt_nombreEntidad.value = infoInformeFinanciero.nombreEntidad;
				document.GenerarCuadroComparativoForm.txt_fecDepositoExcedente.value = infoInformeFinanciero.fecDepositoExcedente;
				
				//Ingresos
				document.GenerarCuadroComparativoForm.idIngresos.value = ingreso.idIngresos;
				document.GenerarCuadroComparativoForm.txt_provision.value = separadorDeMiles(ingreso.provision);
				document.GenerarCuadroComparativoForm.txt_reintegro.value = separadorDeMiles(ingreso.reintegro);
				document.GenerarCuadroComparativoForm.txt_totalIngresos.value = separadorDeMiles(ingreso.totalIngresos);
				
				//EGRESOS
				//pago del mes
				document.GenerarCuadroComparativoForm.idPagoDelMes.value = pagoDelMes.idPagoMes;
				document.GenerarCuadroComparativoForm.txt_asigFamTrabActivos.value = separadorDeMiles(pagoDelMes.asigFamTrabActivos);
				document.GenerarCuadroComparativoForm.txt_asigFamPensionados.value = separadorDeMiles(pagoDelMes.asigFamPensionados);
				document.GenerarCuadroComparativoForm.txt_asigFamTrabCesantes.value = separadorDeMiles(pagoDelMes.asigFamTrabCesantes);
				document.GenerarCuadroComparativoForm.txt_asigFamInstituciones.value = separadorDeMiles(pagoDelMes.asigFamInstituciones);
				document.GenerarCuadroComparativoForm.txt_totalPagoDelMes.value = separadorDeMiles(pagoDelMes.totalPagoDelMes);
				
				//pagos retroactivos
				document.GenerarCuadroComparativoForm.idPagoRetroactivos.value = pagoRetroactivo.idPagosRetroactivos;
				document.GenerarCuadroComparativoForm.txt_asigFamTrabActiv.value = separadorDeMiles(pagoRetroactivo.asigFamTrabActiv);
				document.GenerarCuadroComparativoForm.txt_asifFamTrabPens.value = separadorDeMiles(pagoRetroactivo.asifFamTrabPens);
				document.GenerarCuadroComparativoForm.txt_asigFamTrabCes.value = separadorDeMiles(pagoRetroactivo.asigFamTrabCes);
				document.GenerarCuadroComparativoForm.txt_asigFamTrabInst.value = separadorDeMiles(pagoRetroactivo.asigFamTrabInst);
				document.GenerarCuadroComparativoForm.txt_totalPagosRetroactivos.value = separadorDeMiles(pagoRetroactivo.totalPagosRetroactivos);
				
				document.GenerarCuadroComparativoForm.idEgresos.value = egresos.idEgresos;
				document.GenerarCuadroComparativoForm.txt_docRevalidados.value = separadorDeMiles(egresos.docRevalidados);
				document.GenerarCuadroComparativoForm.txt_comisionAdministracion.value = separadorDeMiles(egresos.comisionAdministracion);
				document.GenerarCuadroComparativoForm.txt_totalEgresos.value = separadorDeMiles(egresos.totalEgresos);
				
				//DEVOLUCIONES
				document.GenerarCuadroComparativoForm.idDevoluciones.value = devoluciones.idDevoluciones;
				document.GenerarCuadroComparativoForm.txt_documentosCaducados.value = separadorDeMiles(devoluciones.documentosCaducados);
				document.GenerarCuadroComparativoForm.txt_documentosAnulados.value = separadorDeMiles(devoluciones.documentosAnulados);
				document.GenerarCuadroComparativoForm.txt_totalDevoluciones.value = separadorDeMiles(devoluciones.totalDevoluciones);
				
				//SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR Y MATERNAL
				document.GenerarCuadroComparativoForm.txt_TotalD.value = separadorDeMiles(infoInformeFinanciero.totalSuperAvitDeficit);
				
				//DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES
				document.GenerarCuadroComparativoForm.idDevolucionDeSaldos.value = devolucionDeSaldos.idDevolucionDeSaldos;
				document.GenerarCuadroComparativoForm.txt_saldoFavorEmpleador.value = separadorDeMiles(devolucionDeSaldos.saldoFavorEmpleador);
				document.GenerarCuadroComparativoForm.txt_devolucionDocSAFEMCaducados.value = separadorDeMiles(devolucionDeSaldos.devolucionDocSAFEMCaducados);
				document.GenerarCuadroComparativoForm.txt_devolucionDocSAFEMAnulados.value = separadorDeMiles(devolucionDeSaldos.devolucionDocSAFEMAnulados);
				document.GenerarCuadroComparativoForm.txt_documentosSAFEMRevalidados.value = separadorDeMiles(devolucionDeSaldos.documentosSAFEMRevalidados);
				document.GenerarCuadroComparativoForm.txt_totalDevolucionesE.value = separadorDeMiles(devolucionDeSaldos.totalDevolucionesE);
				
				//SUPERAVIT O DEFICIT FINAL
				document.GenerarCuadroComparativoForm.txt_totalF.value = separadorDeMiles(infoInformeFinanciero.totalSuperAvitDeficitFinal);
			}
		});
		
		DWREngine.setAsync(true);
		
	}	
	/**Funcion que obtiene los valores resumenes de los archivos, para calcular diferencias.*/
	function obtenerResumenPorArchivos(periodoSelected){
		
		var _11AT = 0;
		var _11CES = 0;
		var _12AT = 0;
		var _12PD = 0;
		var total12ATPD = 0;
		var _12CES = 0;
		var pagoDelMes = 0;
		var pagoRetroactivo = 0;
		var _16mtoDoc = 0;
		var _17mtoDoc = 0;
		var eee4 = 0;
		
		var periodoInforme = periodoSelected;
		
		DWREngine.setAsync(false);
		GenerarCuadroComparativoDWR.obtenerMontosResumenes(periodoInforme, function(data){
			var cuadroComparativo = null;
			cuadroComparativo = data;
			
			if(cuadroComparativo.codResultado != 0){
				//camposResumenesEnCero();
			}else{
				//aqui asignar valores y realizar operaciones...
				
				_11AT = separadorDeMiles(cuadroComparativo.montoBeneficioSif011AT);
				_11CES = separadorDeMiles(cuadroComparativo.montoBeneficioSif011CES);				
				_12AT = separadorDeMiles(cuadroComparativo.montoBeneficioSif012AT);
				_12PD = separadorDeMiles(cuadroComparativo.montoBeneficioSif012PD);
				_12CES = separadorDeMiles(cuadroComparativo.montoBeneficioSif012CES);
				eee4 = separadorDeMiles(cuadroComparativo.eee4);
				
				_12AT = _12AT.replace(/\./g,'');
				_12PD = _12PD.replace(/\./g,'');
				
				total12ATPD = parseInt(_12AT,10) + parseInt(_12PD,10) + parseInt(cuadroComparativo.ccu,10);
				
				_16mtoDoc = separadorDeMiles(cuadroComparativo.montoDocumentoSif016);
				_17mtoDoc = separadorDeMiles(cuadroComparativo.montoDocumentoSif017);
				
				pagoDelMes = parseInt(_11AT.replace(/\./g,''),10) + parseInt(_11CES.replace(/\./g,''),10);
				pagoRetroactivo = parseInt(total12ATPD,10) + parseInt(_12CES.replace(/\./g,''),10);
				
				document.GenerarCuadroComparativoForm.txt_resumenB1_1.value = separadorDeMiles(cuadroComparativo.montoBeneficioSif011AT);
				document.GenerarCuadroComparativoForm.txt_resumenB1_3.value = separadorDeMiles(cuadroComparativo.montoBeneficioSif011CES);
				document.GenerarCuadroComparativoForm.txt_resumenPagoMes.value = separadorDeMiles(pagoDelMes);
				
				document.GenerarCuadroComparativoForm.txt_resumenB2_1.value = separadorDeMiles(total12ATPD); 
				document.GenerarCuadroComparativoForm.txt_resumenB2_3.value = separadorDeMiles(cuadroComparativo.montoBeneficioSif012CES);
				document.GenerarCuadroComparativoForm.txt_resumenPagosRetroactivos.value = separadorDeMiles(pagoRetroactivo);
				
				document.GenerarCuadroComparativoForm.txt_resumenE1.value = separadorDeMiles(cuadroComparativo.montoDocumentoSif016);
				document.GenerarCuadroComparativoForm.txt_resumenE2.value = separadorDeMiles(cuadroComparativo.montoDocumentoSif017);
				document.GenerarCuadroComparativoForm.txt_resumenE4.value = separadorDeMiles(cuadroComparativo.eee4);
				
				var a1=document.GenerarCuadroComparativoForm.txt_provision.value;
				var a2=document.GenerarCuadroComparativoForm.txt_reintegro.value;
				document.GenerarCuadroComparativoForm.txt_resumenA1.value = separadorDeMiles(a1);
				document.GenerarCuadroComparativoForm.txt_resumenA2.value = separadorDeMiles(a2);
				
				
				//totales
				var totalA = parseInt(a1,10) + parseInt(a2,10);
				document.GenerarCuadroComparativoForm.txt_resumenIngresos.value = separadorDeMiles(totalA);
				var totalB = pagoDelMes+pagoRetroactivo;
				document.GenerarCuadroComparativoForm.txt_resumenTotalEgresos.value = separadorDeMiles(totalB);
				var c1 = document.GenerarCuadroComparativoForm.txt_resumenC1.value;
				var c2 = document.GenerarCuadroComparativoForm.txt_resumenC2.value;
				var totalC = parseInt(c1,10) + parseInt(c2,10);
				document.GenerarCuadroComparativoForm.txt_resumenDevoluciones.value = separadorDeMiles(totalC);
				var totalD = parseInt(totalA,10) - parseInt(totalB,10) + parseInt(totalC,10);
				document.GenerarCuadroComparativoForm.txt_resumenD.value = separadorDeMiles(totalD);
				var e3 = document.GenerarCuadroComparativoForm.txt_resumenE3.value;
				var totalE = parseInt(cuadroComparativo.montoDocumentoSif017,10) + parseInt(e3,10) - parseInt(cuadroComparativo.eee4,10);
				document.GenerarCuadroComparativoForm.txt_resumenTotalDevolucionesE.value = separadorDeMiles(totalE);
				var totalF = parseInt(totalD,10) + parseInt(totalE,10);
				document.GenerarCuadroComparativoForm.txt_resumenF.value = separadorDeMiles(totalF);
				
			}
		});
		DWREngine.setAsync(true);
	}
	
	/*Funcion que realiza calculos de excedentes.*/
	function compararResultados(){
		
		var trabajadorActivoB1 = document.GenerarCuadroComparativoForm.txt_asigFamTrabActivos.value;
		var trabajadorActivoRes = document.GenerarCuadroComparativoForm.txt_resumenB1_1.value;
		var variacion1 = parseInt(trabajadorActivoB1.replace(/\./g,''),10) - parseInt(trabajadorActivoRes.replace(/\./g,''),10);
		
		var trabajadoresCesantesB1 = document.GenerarCuadroComparativoForm.txt_asigFamTrabCesantes.value;
		var trabajadoresCesantesRes = document.GenerarCuadroComparativoForm.txt_resumenB1_3.value;
		var variacion2 = parseInt(trabajadoresCesantesB1.replace(/\./g,''),10) - parseInt(trabajadoresCesantesRes.replace(/\./g,''),10);
		
		var totalMes = document.GenerarCuadroComparativoForm.txt_totalPagoDelMes.value;
		var totalMesRes = document.GenerarCuadroComparativoForm.txt_resumenPagoMes.value;
		var variacion3 = parseInt(totalMes.replace(/\./g,''),10) - parseInt(totalMesRes.replace(/\./g,''),10);
		
		var trabajadorActivoB2 = document.GenerarCuadroComparativoForm.txt_asigFamTrabActiv.value;
		var trabajadorActivoRes = document.GenerarCuadroComparativoForm.txt_resumenB2_1.value;
		var variacion4 = parseInt(trabajadorActivoB2.replace(/\./g,''),10) - parseInt(trabajadorActivoRes.replace(/\./g,''),10);
		
		var trabajadorCesanteB2 = document.GenerarCuadroComparativoForm.txt_asigFamTrabCes.value;
		var trabajadorCesanteRes = document.GenerarCuadroComparativoForm.txt_resumenB2_3.value;
		var variacion5 = parseInt(trabajadorCesanteB2.replace(/\./g,''),10) - parseInt(trabajadorCesanteRes.replace(/\./g,''),10);
		
		var totalRetro = document.GenerarCuadroComparativoForm.txt_totalPagosRetroactivos.value;
		var totalRetroMes = document.GenerarCuadroComparativoForm.txt_resumenPagosRetroactivos.value;
		var variacion6 = parseInt(totalRetro.replace(/\./g,''),10) - parseInt(totalRetroMes.replace(/\./g,''),10);
		
		var safemE1 = document.GenerarCuadroComparativoForm.txt_saldoFavorEmpleador.value;
		var safemRes = document.GenerarCuadroComparativoForm.txt_resumenE1.value;
		var variacion7 = parseInt(safemE1.replace(/\./g,''),10) - parseInt(safemRes.replace(/\./g,''),10);
		
		var safemE2 = document.GenerarCuadroComparativoForm.txt_devolucionDocSAFEMCaducados.value;
		var safemRes = document.GenerarCuadroComparativoForm.txt_resumenE2.value;
		var variacion8 = parseInt(safemE2.replace(/\./g,''),10) - parseInt(safemRes.replace(/\./g,''),10);
		
		//campos sin data
		
		var provisionA1 = document.GenerarCuadroComparativoForm.txt_provision.value;
		var provisionRes = document.GenerarCuadroComparativoForm.txt_resumenA1.value;
		var provisionDif = parseInt(provisionA1.replace(/\./g,''),10) - parseInt(provisionRes.replace(/\./g,''),10);
		
		var reintegroA2 = document.GenerarCuadroComparativoForm.txt_reintegro.value;
		var reintegroRes = document.GenerarCuadroComparativoForm.txt_resumenA2.value;
		var reintegroDif = parseInt(reintegroA2.replace(/\./g,''),10) - parseInt(reintegroRes.replace(/\./g,''),10);
		
		var totalIngresos = document.GenerarCuadroComparativoForm.txt_totalIngresos.value;
		var totalIngresosRes = document.GenerarCuadroComparativoForm.txt_resumenIngresos.value;
		var totalIngresosDif = parseInt(totalIngresos.replace(/\./g,''),10) - parseInt(totalIngresosRes.replace(/\./g,''),10);
		
		var asigFamPensionadosB12 = document.GenerarCuadroComparativoForm.txt_asigFamPensionados.value;
		var asigFamPensionadosRes = document.GenerarCuadroComparativoForm.txt_resumenB1_2.value;
		var asigFamPensionadosDif = parseInt(asigFamPensionadosB12.replace(/\./g,''),10) - parseInt(asigFamPensionadosRes.replace(/\./g,''),10);
		
		var asigFamInstitucionesB14 = document.GenerarCuadroComparativoForm.txt_asigFamInstituciones.value;
		var asigFamInstitucionesRes = document.GenerarCuadroComparativoForm.txt_resumenB1_4.value;
		var asigFamInstitucionesDif = parseInt(asigFamInstitucionesB14.replace(/\./g,''),10) - parseInt(asigFamInstitucionesRes.replace(/\./g,''),10);
		
		var asifFamTrabPensB22 = document.GenerarCuadroComparativoForm.txt_asifFamTrabPens.value;
		var asifFamTrabPensRes = document.GenerarCuadroComparativoForm.txt_resumenB2_2.value;
		var asifFamTrabPensDif = parseInt(asifFamTrabPensB22.replace(/\./g,''),10) - parseInt(asifFamTrabPensRes.replace(/\./g,''),10);
		
		var asigFamTrabInstB24 = document.GenerarCuadroComparativoForm.txt_asigFamTrabInst.value;
		var asigFamTrabInstRes = document.GenerarCuadroComparativoForm.txt_resumenB2_4.value;
		var asigFamTrabInstDif = parseInt(asigFamTrabInstB24.replace(/\./g,''),10) - parseInt(asigFamTrabInstRes.replace(/\./g,''),10);
		
		var docRevalidadosB3 = document.GenerarCuadroComparativoForm.txt_docRevalidados.value;
		var docRevalidadosRes = document.GenerarCuadroComparativoForm.txt_resumenB3.value;
		var docRevalidadosDif = parseInt(docRevalidadosB3.replace(/\./g,''),10) - parseInt(docRevalidadosRes.replace(/\./g,''),10);
		
		var comisionAdministracionB4 = document.GenerarCuadroComparativoForm.txt_comisionAdministracion.value;
		var comisionAdministracionRes = document.GenerarCuadroComparativoForm.txt_resumenB4.value;
		var comisionAdministracionDif = parseInt(comisionAdministracionB4.replace(/\./g,''),10) - parseInt(comisionAdministracionRes.replace(/\./g,''),10);
		
		var totalEgresos = document.GenerarCuadroComparativoForm.txt_totalEgresos.value;
		var totalEgresosRes = document.GenerarCuadroComparativoForm.txt_resumenTotalEgresos.value;
		var totalEgresosDif = parseInt(totalEgresos.replace(/\./g,''),10) - parseInt(totalEgresosRes.replace(/\./g,''),10);
		
		var documentosCaducadosC1 = document.GenerarCuadroComparativoForm.txt_documentosCaducados.value;
		var documentosCaducadosRes = document.GenerarCuadroComparativoForm.txt_resumenC1.value;
		var documentosCaducadosDif = parseInt(documentosCaducadosC1.replace(/\./g,''),10) - parseInt(documentosCaducadosRes.replace(/\./g,''),10);
		
		var documentosAnuladosC2 = document.GenerarCuadroComparativoForm.txt_documentosAnulados.value;
		var documentosAnuladosRes = document.GenerarCuadroComparativoForm.txt_resumenC2.value;
		var documentosAnuladosDif = parseInt(documentosAnuladosC2.replace(/\./g,''),10) - parseInt(documentosAnuladosRes.replace(/\./g,''),10);
		
		var totalDevoluciones = document.GenerarCuadroComparativoForm.txt_totalDevoluciones.value;
		var totalDevolucionesRes = document.GenerarCuadroComparativoForm.txt_resumenDevoluciones.value;
		var totalDevolucionesDifC = parseInt(totalDevoluciones.replace(/\./g,''),10) - parseInt(totalDevolucionesRes.replace(/\./g,''),10);
		
		var totalD = document.GenerarCuadroComparativoForm.txt_TotalD.value;
		var totalDRes = document.GenerarCuadroComparativoForm.txt_resumenD.value;
		var totalDDif = parseInt(totalD.replace(/\./g,''),10) - parseInt(totalDRes.replace(/\./g,''),10);
		
		var devolucionDocSAFEMAnuladosE3 = document.GenerarCuadroComparativoForm.txt_devolucionDocSAFEMAnulados.value;
		var devolucionDocSAFEMAnuladosRes = document.GenerarCuadroComparativoForm.txt_resumenE3.value;
		var devolucionDocSAFEMAnuladosDif = parseInt(devolucionDocSAFEMAnuladosE3.replace(/\./g,''),10) - parseInt(devolucionDocSAFEMAnuladosRes.replace(/\./g,''),10);
		
		var documentosSAFEMRevalidadosE4 = document.GenerarCuadroComparativoForm.txt_documentosSAFEMRevalidados.value;
		var documentosSAFEMRevalidadosRes = document.GenerarCuadroComparativoForm.txt_resumenE4.value;
		var documentosSAFEMRevalidadosDif = parseInt(documentosSAFEMRevalidadosE4.replace(/\./g,''),10) - parseInt(documentosSAFEMRevalidadosRes.replace(/\./g,''),10);
		
		var totalDevolucionesE = document.GenerarCuadroComparativoForm.txt_totalDevolucionesE.value;
		var totalDevolucionesRes = document.GenerarCuadroComparativoForm.txt_resumenTotalDevolucionesE.value;
		var totalDevolucionesDif = parseInt(totalDevolucionesE.replace(/\./g,''),10) - parseInt(totalDevolucionesRes.replace(/\./g,''),10);
		
		var totalF = document.GenerarCuadroComparativoForm.txt_totalF.value;
		var totalFRes = document.GenerarCuadroComparativoForm.txt_resumenF.value;
		var totalFDif = parseInt(totalF.replace(/\./g,''),10) - parseInt(totalFRes.replace(/\./g,''),10);
		
		
		document.GenerarCuadroComparativoForm.txt_diferenciaB1_1.value = separadorDeMiles(variacion1);
		document.GenerarCuadroComparativoForm.txt_diferenciaB1_3.value = separadorDeMiles(variacion2);
		document.GenerarCuadroComparativoForm.txt_diferenciaPagoMes.value = separadorDeMiles(variacion3);
		document.GenerarCuadroComparativoForm.txt_diferenciaB2_1.value = separadorDeMiles(variacion4);
		document.GenerarCuadroComparativoForm.txt_diferenciaB2_3.value = separadorDeMiles(variacion5);
		document.GenerarCuadroComparativoForm.txt_diferenciaPagosRetroactivos.value = separadorDeMiles(variacion6);
		document.GenerarCuadroComparativoForm.txt_diferenciaE1.value = separadorDeMiles(variacion7);
		document.GenerarCuadroComparativoForm.txt_diferenciaE2.value = separadorDeMiles(variacion8);
		
		//campos sin data
		document.GenerarCuadroComparativoForm.txt_diferenciaA1.value = separadorDeMiles(provisionDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaA2.value = separadorDeMiles(reintegroDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaIngresos.value = separadorDeMiles(totalIngresosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaB1_2.value = separadorDeMiles(asigFamPensionadosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaB1_4.value = separadorDeMiles(asigFamInstitucionesDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaB2_2.value = separadorDeMiles(asifFamTrabPensDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaB2_4.value = separadorDeMiles(asigFamTrabInstDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaB3.value = separadorDeMiles(docRevalidadosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaB4.value = separadorDeMiles(comisionAdministracionDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaTotalEgresos.value = separadorDeMiles(totalEgresosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaC1.value = separadorDeMiles(documentosCaducadosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaC2.value = separadorDeMiles(documentosAnuladosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaDevoluciones.value = separadorDeMiles(totalDevolucionesDifC);
		document.GenerarCuadroComparativoForm.txt_diferenciaD.value = separadorDeMiles(totalDDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaE3.value = separadorDeMiles(devolucionDocSAFEMAnuladosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaE4.value = separadorDeMiles(documentosSAFEMRevalidadosDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaTotalDevolucionesE.value = separadorDeMiles(totalDevolucionesDif);
		document.GenerarCuadroComparativoForm.txt_diferenciaF.value = separadorDeMiles(totalFDif);

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
		var cmb = document.GenerarCuadroComparativoForm.dbx_meses;
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
	
	/*Funcion que calcula el mes al que corresponde el periodo*/
	function calculaMes(){
		
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
		
		return mes;
	}
	
	/**FunciON que asigna el valor del combo.*/
	function selectedItemCombo(){
		
		var periodoSeleccionado = calculaMes();
		document.GenerarCuadroComparativoForm.dbx_meses.value = periodoSeleccionado;

		var mes = calculaMes();
		if(mes == 12){
			document.GenerarCuadroComparativoForm.dbx_anio.value = anio_global - 1;
		}else{
			document.GenerarCuadroComparativoForm.dbx_anio.value = anio_global;
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

		var cmb = document.GenerarCuadroComparativoForm.dbx_anio;
		
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
		
		var cmb = document.GenerarCuadroComparativoForm.dbx_anio;
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
	var periodoResumen = "";
	function actualizaCampoPeriodoYMesInformado(){
		
		limpiarHidden();
		periodoSelected = "";
		periodoResumen = "";
		
		var mesSelected = document.GenerarCuadroComparativoForm.dbx_meses.value;
		var anioSelected = document.GenerarCuadroComparativoForm.dbx_anio.value;
		
		if(parseInt(mesSelected,10) < 10){
			mesSelected = '0' + mesSelected;
		}
			
		/*if(parseInt(anioSelected,10) < 10){
			anioSelected = '200' + anioSelected;
		}else{
			anioSelected = '20' + anioSelected;
		}*/	
			
		periodoSelected = mesSelected + "/" + anioSelected;
		var mesInformadoSelected = mesSelected + " " + anioSelected;
		periodoResumen = anioSelected.toString() + mesSelected.toString();
		
		document.GenerarCuadroComparativoForm.txt_periodo.value = periodoSelected;
		//document.GenerarInformeFinancieroForm.txt_MesProceso.value = mesInformadoSelected;
		obtenerInformeFinanciero(periodoSelected);
		obtenerResumenPorArchivos(periodoResumen);
		compararResultados();
	}
	
	/*Funcion que actualiza el mes desde el informe financiero.*/
	function actualizaCampoPeriodoYMesInformadoRequest(mesSelected, anioSelected){
		
		limpiarHidden();
		periodoSelected = "";
		periodoResumen = "";
		
		//var mesSelected = document.GenerarCuadroComparativoForm.dbx_meses.value;
		//var anioSelected = document.GenerarCuadroComparativoForm.dbx_anio.value;
		/*var mesSelected = mes;
		var anioSelected = anio;*/
		
		if(parseInt(mesSelected,10) < 10){
			mesSelected = '0' + mesSelected;
		}
			
		/*if(parseInt(anioSelected,10) < 10){
			anioSelected = '200' + anioSelected;
		}else{
			anioSelected = '20' + anioSelected;
		}*/	
			
		periodoSelected = mesSelected + "/" + anioSelected;
		var mesInformadoSelected = mesSelected + " " + anioSelected;
		periodoResumen = anioSelected.toString() + mesSelected.toString();
		
		document.GenerarCuadroComparativoForm.txt_periodo.value = periodoSelected;
		//document.GenerarInformeFinancieroForm.txt_MesProceso.value = mesInformadoSelected;
		obtenerInformeFinanciero(periodoSelected);
		obtenerResumenPorArchivos(periodoResumen);
		compararResultados();
	}
	/*Funcion que limpia los hidden*/
	function limpiarHidden(){
	
		document.GenerarCuadroComparativoForm.idIngresos.value="0";
		document.GenerarCuadroComparativoForm.idPagoDelMes.value="0";
		document.GenerarCuadroComparativoForm.idPagoRetroactivos.value="0";
		document.GenerarCuadroComparativoForm.idEgresos.value="0";
		document.GenerarCuadroComparativoForm.idDevoluciones.value="0";
		document.GenerarCuadroComparativoForm.idDevolucionDeSaldos.value="0";
		document.GenerarCuadroComparativoForm.idInfoInformeFinanciero.value="0";
		
	}
	
	/*Funcion que actualiza los campos mese y anio desde el request*/
	function selectedItemComboRequest(mesReq, anioReq){
		
		document.GenerarCuadroComparativoForm.dbx_meses.value = mesReq;
		document.GenerarCuadroComparativoForm.dbx_anio.value = anioReq;
		
	}
	
	/**Funcion que recibe como parametro el mes de busqueda desde el informe financiero.*/
	function RecibeMesInformeFinanciero(){
		
		var mesRequest = '<%=request.getParameter("mes")%>';
		var anioRequest = '<%=request.getParameter("anio")%>';
		if((mesRequest == 'null' || anioRequest == 'null') && (mesRequest == "" || anioRequest == "")){
			selectedItemCombo();
			actualizaCampoPeriodoYMesInformado();
		}else{
			selectedItemComboRequest(mesRequest,anioRequest);
			actualizaCampoPeriodoYMesInformadoRequest(mesRequest,anioRequest);
		}		
	}
	
	function volverAInformeFinanciero(){
		window.close();
	}
	
	/*Funcion que se encarga de llamar a todas las funciones iniciales que seran llamadas en el onload. Cada funcion que se desee cargar al inicio de la
	aplicacion, deberá ser llamada desde esta funcion en el orden correspondiente.*/
	function cargarEnOnload(){
		//limpiarCamposACero();
		//llenarCamposInformativos();
		cargarArregloParametricas();
		obtenerComboPeriodoProceso();
		//selectedItemCombo();
		RecibeMesInformeFinanciero()
		//actualizaCampoPeriodoYMesInformado();
		//obtenerInformeFinanciero();
	}		
</script>
</head>
<body onload="cargarEnOnload();">
<html:form action="/cuadroCompar.do">
	<div id="caja_registro"><input type="hidden" name="opcion"
		value="0"> <input type="hidden" name="numeroMes" value="0">
	<input type="hidden" name="idIngresos" value="0"> <input
		type="hidden" name="idPagoDelMes" value="0"> <input
		type="hidden" name="idPagoRetroactivos" value="0"> <input
		type="hidden" name="idEgresos" value="0"> <input type="hidden"
		name="idDevoluciones" value="0"> <input type="hidden"
		name="idDevolucionDeSaldos" value="0"> <input type="hidden"
		name="idInfoInformeFinanciero" value="0">

	<table>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table width="1020" border="0">
		<tr>
			<td align="right"><a href="#" align="right"
				onclick="volverAInformeFinanciero();"><B>Salir</B></a> <!-- <a href="#" align="right" onclick="enviaFormulario(1);"><B>Volver</B></a> 
				&nbsp;&nbsp;&nbsp; 
				<a href="#" align="right" onClick="enviaFormulario(-1);"><B>Cerrar Sesión</B></a> -->
			</td>
		</tr>
		<tr>
			<td width="70%" height="40">
			<table border="0">
				<tr>
					<td><strong>
					<p><p1>INFORME FINANCIERO</p1></p>
					</strong> <strong>
					<p><p1>SISTEMA UNICO DE PRESTACIONES FAMILIARES</p1></p>
					</strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario"
						disabled="true" size="10"
						value="<%=session.getAttribute("IDAnalista")%>" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" id="txt_Fecha"
						disabled="true" size="10"
						value="<%=session.getAttribute("FechaSistema")%>" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<!-- <td>Mes Informado</td>
					<td><input type="text" name="txt_MesProceso" id="txt_MesProceso" disabled="true" size="20" /></td> -->
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br />
			<p><p2>1. Información</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="32%"><strong>Seleccione Periodo</strong></td>
					<td><html:select property="dbx_meses" styleClass="combobox"
						value="0" onchange="actualizaCampoPeriodoYMesInformado();">
						<html:option value="0">Seleccione </html:option>
					</html:select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:select property="dbx_anio"
						styleClass="comboAnio" value="0"
						onchange="actualizaCampoPeriodoYMesInformado();">
						<html:option value="0">Seleccione </html:option>
					</html:select></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="32%"><strong>Periodo</strong></td>
					<td><input type="text" name="txt_periodo" id="txt_periodo"
						maxlength="7" size="20" disabled="true" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="32%"><strong>Código Entidad</strong></td>
					<td><input type="text" name="txt_codigoEntidad"
						id="txt_codigoEntidad" maxlength="7" size="20" disabled="true" />
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="32%"><strong>Nombre Entidad</strong></td>
					<td><input type="text" name="txt_nombreEntidad"
						id="txt_nombreEntidad" maxlength="50" size="20" disabled="true" />
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="32%"><strong>Fecha Depósito Excedente</strong></td>
					<td>
						<input type="text" name="txt_fecDepositoExcedente" id="txt_fecDepositoExcedente" maxlength="10" size="10" disabled="true" />
					 	<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="20" height="18" onClick="ShowCalendarFor(txt_fecDepositoExcedente);" />
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
			<td width="970"><br />
			<table width="100%" border="0" rules="groups">
				<tr>
					<td align="center" width="50%"></td>
					<td><input type="text" id="txt_infFinanc" name="txt_infFinanc"
						value="Inf. Financiero" size="10"
						style="background-color: transparent; border-width: 0px; text-align: center" />
					</td>
					<td><input type="text" id="txt_archivo" name="txt_archivo"
						value="Archivo" size="10"
						style="background-color: transparent; border-width: 0px; text-align: center" />
					</td>
					<td><input type="text" id="txt_diferenc" name="txt_diferenc"
						value="Diferencia" size="10"
						style="background-color: transparent; border-width: 0px; text-align: center" />
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			<p><p2>A(+) INGRESOS</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="50%"><strong>A.1 Provisión ($)</strong></td>
					<td><input type="text" name="txt_provision" id="txt_provision"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenA1" id="txt_resumenA1"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaA1"
						id="txt_diferenciaA1" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>A.2 Reintegro por cobro
					indebido asignaciones familiares y maternales ($)</strong></td>
					<td><input type="text" name="txt_reintegro" id="txt_reintegro"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenA2" id="txt_resumenA2"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaA2"
						id="txt_diferenciaA2" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%" align="right"><strong>Total Ingresos
					($)</strong></td>
					<td><input type="text" name="txt_totalIngresos"
						id="txt_totalIngresos" maxlength="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" size="10" style="text-align: right"
						disabled="true" value="0"onchange="totalBloqueD();" /></td>
					<td><input type="text" name="txt_resumenIngresos"
						id="txt_resumenIngresos" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaIngresos"
						id="txt_diferenciaIngresos" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br />
			<p><p2>B(-) EGRESOS</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="50%">
					<p><p2>B.1 PAGO DEL MES ($)</p2></p>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.1.1 Asignación Familiar de
					trabajadores activos ($)</strong></td>
					<td><input type="text" name="txt_asigFamTrabActivos"
						id="txt_asigFamTrabActivos" maxlength="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="10"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB1_1"
						id="txt_resumenB1_1" maxlength="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" size="10" style="text-align: right" disabled="true" value="0" />
					</td>
					<td><input type="text" name="txt_diferenciaB1_1"
						id="txt_diferenciaB1_1" maxlength="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" size="10"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.1.2 Asignación Familiar de
					pensionados ($)</strong></td>
					<td><input type="text" name="txt_asigFamPensionados"
						id="txt_asigFamPensionados" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB1_2"
						id="txt_resumenB1_2" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB1_2"
						id="txt_diferenciaB1_2" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.1.3 Asignación Familiar de
					trabajadores cesantes ($)</strong></td>
					<td><input type="text" name="txt_asigFamTrabCesantes"
						id="txt_asigFamTrabCesantes" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB1_3"
						id="txt_resumenB1_3" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB1_3"
						id="txt_diferenciaB1_3" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.1.4 Asignación Familiar de
					Instituciones ($)</strong></td>
					<td><input type="text" name="txt_asigFamInstituciones"
						id="txt_asigFamInstituciones" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB1_4"
						id="txt_resumenB1_4" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB1_4"
						id="txt_diferenciaB1_4" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%" align="right"><strong>Total Pago del
					Mes ($)</strong></td>
					<td><input type="text" name="txt_totalPagoDelMes"
						id="txt_totalPagoDelMes" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						disabled="true" value="0"style="text-align: right" /></td>
					<td><input type="text" name="txt_resumenPagoMes"
						id="txt_resumenPagoMes" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaPagoMes"
						id="txt_diferenciaPagoMes" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%">
					<p><p2>B.2 PAGOS RETROACTIVOS ($)</p2></p>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.2.1 Asignación Familiar de
					trabajadores activos ($)</strong></td>
					<td><input type="text" name="txt_asigFamTrabActiv"
						id="txt_asigFamTrabActiv" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB2_1"
						id="txt_resumenB2_1" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB2_1"
						id="txt_diferenciaB2_1" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.2.2 Asignación Familiar de
					pensionados ($)</strong></td>
					<td><input type="text" name="txt_asifFamTrabPens"
						id="txt_asifFamTrabPens" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB2_2"
						id="txt_resumenB2_2" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB2_2"
						id="txt_diferenciaB2_2" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.2.3 Asignación Familiar de
					trabajadores cesantes ($)</strong></td>
					<td><input type="text" name="txt_asigFamTrabCes"
						id="txt_asigFamTrabCes" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB2_3"
						id="txt_resumenB2_3" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB2_3"
						id="txt_diferenciaB2_3" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.2.4 Asignación Familiar de
					Instituciones ($)</strong></td>
					<td><input type="text" name="txt_asigFamTrabInst"
						id="txt_asigFamTrabInst" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB2_4"
						id="txt_resumenB2_4" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB2_4"
						id="txt_diferenciaB2_4" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%" align="right"><strong>Total Pagos
					Retroactivos ($)</strong></td>
					<td><input type="text" name="txt_totalPagosRetroactivos"
						id="txt_totalPagosRetroactivos" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						disabled="true" value="0"style="text-align: right" /></td>
					<td><input type="text" name="txt_resumenPagosRetroactivos"
						id="txt_resumenPagosRetroactivos" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaPagosRetroactivos"
						id="txt_diferenciaPagosRetroactivos" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.3 Documentos Revalidados
					(Pago Directo a Beneficiarios) ($)</strong></td>
					<td><input type="text" name="txt_docRevalidados"
						id="txt_docRevalidados" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB3" id="txt_resumenB3"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB3"
						id="txt_diferenciaB3" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>B.4 Comisión de
					Administración ($)</strong></td>
					<td><input type="text" name="txt_comisionAdministracion"
						id="txt_comisionAdministracion" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenB4" id="txt_resumenB4"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaB4"
						id="txt_diferenciaB4" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%" align="right"><strong>Total Egresos
					($)</strong></td>
					<td><input type="text" name="txt_totalEgresos"
						id="txt_totalEgresos" maxlength="10" size="10" disabled="true"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenTotalEgresos"
						id="txt_resumenTotalEgresos" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaTotalEgresos"
						id="txt_diferenciaTotalEgresos" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br />
			<p><p2>C(+) DEVOLUCIONES</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="50%"><strong>C.1 Documentos Caducados
					(Pagos Directos) ($)</strong></td>
					<td><input type="text" name="txt_documentosCaducados"
						id="txt_documentosCaducados" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenC1" id="txt_resumenC1"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaC1"
						id="txt_diferenciaC1" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>C.2 Documentos Anulados (Pago
					Directo) ($)</strong></td>
					<td><input type="text" name="txt_documentosAnulados"
						id="txt_documentosAnulados" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenC2" id="txt_resumenC2"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaC2"
						id="txt_diferenciaC2" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%" align="right"><strong>Total
					Devoluciones ($)</strong></td>
					<td><input type="text" name="txt_totalDevoluciones"
						id="txt_totalDevoluciones" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						disabled="true" value="0"style="text-align: right" /></td>
					<td><input type="text" name="txt_resumenDevoluciones"
						id="txt_resumenDevoluciones" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaDevoluciones"
						id="txt_diferenciaDevoluciones" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br />
			<p><p2>D SUPERAVIT O DEFICIT POR PAGO DE ASIGNACION FAMILIAR
			Y MATERNAL (A - B + C) </p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="50%" align="right"><strong>Total ($)</strong></td>
					<td><input type="text" name="txt_TotalD" id="txt_TotalD"
						maxlength="10" size="10" disabled="true" value="0"onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" /></td>
					<td><input type="text" name="txt_resumenD" id="txt_resumenD"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaD"
						id="txt_diferenciaD" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br />
			<p><p2>E DEVOLUCION DE SALDOS A FAVOR DE EMPLEADORES (E.2 +
			E.3 - E.4) </p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="50%"><strong>E.1 Saldo a favor empleador
					(SAFEM) ($)</strong></td>
					<td><input type="text" name="txt_saldoFavorEmpleador"
						id="txt_saldoFavorEmpleador" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenE1" id="txt_resumenE1"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaE1"
						id="txt_diferenciaE1" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>E.2 Devolución por documentos
					SAFEM caducados ($)</strong></td>
					<td><input type="text" name="txt_devolucionDocSAFEMCaducados"
						id="txt_devolucionDocSAFEMCaducados" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true"/></td>
					<td><input type="text" name="txt_resumenE2" id="txt_resumenE2"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true"/></td>
					<td><input type="text" name="txt_diferenciaE2"
						id="txt_diferenciaE2" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>E.3 Devolución por documentos
					SAFEM anulados ($)</strong></td>
					<td><input type="text" name="txt_devolucionDocSAFEMAnulados"
						id="txt_devolucionDocSAFEMAnulados" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenE3" id="txt_resumenE3"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaE3"
						id="txt_diferenciaE3" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%"><strong>E.4 Documentos SAFEM
					revalidados ($)</strong></td>
					<td><input type="text" name="txt_documentosSAFEMRevalidados"
						id="txt_documentosSAFEMRevalidados" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_resumenE4" id="txt_resumenE4"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaE4"
						id="txt_diferenciaE4" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="50%" align="right"><strong>Total
					Devoluciones ($)</strong></td>
					<td><input type="text" name="txt_totalDevolucionesE"
						id="txt_totalDevolucionesE" maxlength="10" size="10"
						disabled="true" value="0"onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" /></td>
					<td><input type="text" name="txt_resumenTotalDevolucionesE"
						id="txt_resumenTotalDevolucionesE" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaTotalDevolucionesE"
						id="txt_diferenciaTotalDevolucionesE" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="970"><br />
			<p><p2>F SUPERAVIT O DEFICIT FINAL (D + E) </p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="50%" align="right"><strong>Total ($)</strong></td>
					<td><input type="text" name="txt_totalF" id="txt_totalF"
						maxlength="10" size="10" disabled="true" value="0"onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" /></td>
					<td><input type="text" name="txt_resumenF" id="txt_resumenF"
						maxlength="10" size="10" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" style="text-align: right" disabled="true" value="0"/></td>
					<td><input type="text" name="txt_diferenciaF"
						id="txt_diferenciaF" maxlength="10" size="10"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						style="text-align: right; color: blue" disabled="true" value="0"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td height="37" align="right" width="970"><input type="button"
				name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"
				value="Cancelar" onclick="volverAInformeFinanciero();" /> <!-- <input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);" /> -->
			</td>
		</tr>
	</table>
	</div>
</html:form>
</body>
</html:html>
