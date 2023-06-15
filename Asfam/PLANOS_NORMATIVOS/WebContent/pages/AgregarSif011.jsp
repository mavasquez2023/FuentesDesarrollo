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
<script type="text/javascript" language="JavaScript1.2" src="../dwr/interface/AgregarRegistroDivisionPrevisionalDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="../dwr/interface/GenerarListadoErroresDWR.js"></script>


<script type="text/javascript">

	var correlativoRequest = "";
	
	function asignaValor(a){

		document.AgregarSif011Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.AgregarSif011Form.submit();
	}
	
    
    
	/*************************************************************************************/
	/*  VALIDACIONES ANEXAS A CAMPOS PARA EL REPORTE SIF011 */
	
	function validadorFuenteOrigen(){
		fuenteOrigen = parseInt(document.AgregarSif011Form.dbx_FuenteOrigen.value,10);
		if (fuenteOrigen == 1){
			limpiarValoresFO();
			document.AgregarSif011Form.dbx_TipoDeclaracion.disabled = true;
			document.AgregarSif011Form.dbx_TipoBeneficiario.disabled = true;
			document.AgregarSif011Form.dbx_TipoEmision.disabled = true;
			document.AgregarSif011Form.dbx_TipoEgreso.disabled = true;
			document.AgregarSif011Form.dbx_ModPago.disabled = true;
			document.AgregarSif011Form.txt_MontoDocumento.disabled = true;
			document.AgregarSif011Form.txt_NumeroSerie.disabled = true;
			document.AgregarSif011Form.txt_NumeroDocumento.disabled = true;
			document.AgregarSif011Form.calenFecEmiDoc.disabled = true;
			document.AgregarSif011Form.dbx_CodBanco.disabled = true;
			document.AgregarSif011Form.dbx_TipoDeclaracion.value = 1;
			document.AgregarSif011Form.dbx_TipoBeneficiario.value = 1;
			document.AgregarSif011Form.dbx_TipoEmision.value = 101;
			document.AgregarSif011Form.dbx_TipoEgreso.value = 1;
			document.AgregarSif011Form.dbx_ModPago.value = 7;
			document.AgregarSif011Form.txt_MontoDocumento.value = "";
			document.AgregarSif011Form.txt_NumeroSerie.value = "";
			document.AgregarSif011Form.txt_NumeroDocumento.value = "";
			document.AgregarSif011Form.txt_FechaEmision.value = "";
			document.AgregarSif011Form.dbx_CodBanco.value = 0;		
		}
		
		if (fuenteOrigen == 2){ 
			limpiarValoresFO();
			document.AgregarSif011Form.dbx_TipoDeclaracion.disabled = true;
			document.AgregarSif011Form.txt_Rut.disabled = true;
			document.AgregarSif011Form.txt_NumVerif.disabled = true;
			document.AgregarSif011Form.txt_NombreEmpresa.disabled = true;
			document.AgregarSif011Form.dbx_TipoBeneficiario.disabled = true;
			document.AgregarSif011Form.dbx_TipoEmision.disabled = true;
			document.AgregarSif011Form.dbx_TipoEgreso.disabled = true;
			document.AgregarSif011Form.dbx_TipoDeclaracion.value = 2;
			document.AgregarSif011Form.txt_Rut.value = 70016160;
			document.AgregarSif011Form.txt_NumVerif.value = 9;
			document.AgregarSif011Form.txt_NombreEmpresa.value = "LA ARAUCANA C.C.A.F.";
			document.AgregarSif011Form.dbx_TipoBeneficiario.value = 4;
			document.AgregarSif011Form.dbx_TipoEmision.value = 103;
			document.AgregarSif011Form.dbx_TipoEgreso.value = 1;
			}
		
		if (fuenteOrigen == 3 || fuenteOrigen == 4){
			alert("fuente de origen invalida para el archivo.") 
			document.AgregarSif011Form.dbx_FuenteOrigen.value=0;
			limpiarValoresFO();	
		}
		if (fuenteOrigen == 0)
		{
			limpiarValoresFO();
		}
	}
	
	function limpiarValoresFO(){
		document.AgregarSif011Form.dbx_TipoDeclaracion.disabled = false;
		document.AgregarSif011Form.dbx_TipoBeneficiario.disabled = false;
		document.AgregarSif011Form.dbx_TipoEmision.disabled = false;
		document.AgregarSif011Form.dbx_TipoEgreso.disabled = false;
		document.AgregarSif011Form.dbx_ModPago.disabled = false;
		document.AgregarSif011Form.txt_MontoDocumento.disabled = false;
		document.AgregarSif011Form.txt_NumeroSerie.disabled = false;
		document.AgregarSif011Form.txt_NumeroDocumento.disabled = false;
		document.AgregarSif011Form.calenFecEmiDoc.disabled = false;
		document.AgregarSif011Form.dbx_CodBanco.disabled = false;
		document.AgregarSif011Form.txt_Rut.disabled = false;
		document.AgregarSif011Form.txt_NumVerif.disabled = false;
		document.AgregarSif011Form.txt_NombreEmpresa.disabled = false;
		document.AgregarSif011Form.dbx_TipoDeclaracion.value = 0;
		document.AgregarSif011Form.dbx_TipoBeneficiario.value = 0;
		document.AgregarSif011Form.dbx_TipoEmision.value = 0;
		document.AgregarSif011Form.dbx_TipoEgreso.value = 0;
		document.AgregarSif011Form.dbx_ModPago.value = 0;
		document.AgregarSif011Form.txt_MontoDocumento.value = "";
		document.AgregarSif011Form.txt_NumeroSerie.value = "";
		document.AgregarSif011Form.txt_NumeroDocumento.value = "";
		document.AgregarSif011Form.txt_FechaEmision.value = "";
		document.AgregarSif011Form.dbx_CodBanco.value = 0;
		document.AgregarSif011Form.txt_Rut.value = "";
		document.AgregarSif011Form.txt_NumVerif.value = "";
		document.AgregarSif011Form.txt_NombreEmpresa.value = "";
	}
	
	function validadorFuenteOrigenAgregar(){
		var rutempresa = document.AgregarSif011Form.txt_Rut.value;
		var dvempresa = document.AgregarSif011Form.txt_NumVerif.value;
		var nombreempresa = document.AgregarSif011Form.txt_NombreEmpresa.value;
		var rutafiliado = document.AgregarSif011Form.txt_RutAfiliado.value;
		var dvafiliado = document.AgregarSif011Form.txt_NumVerifAfiliado.value;
		var nombreafiliado = document.AgregarSif011Form.txt_NombreAfiliado.value;
		var codtipobeneficio = document.AgregarSif011Form.dbx_TipoBeneficio.value;
		var tipobeneficiario = document.AgregarSif011Form.dbx_TipoBeneficiario.value;
		var rutcausante = document.AgregarSif011Form.txt_RutCausante.value;
		var dvcausante = document.AgregarSif011Form.txt_NumVerifCausante.value;
		var nombrecausante = document.AgregarSif011Form.txt_NombreCausante.value;
		var codtipocausante = document.AgregarSif011Form.dbx_TipoCausante.value;
		var fechainiciobenef = document.AgregarSif011Form.txt_FechaInicioBeneficio.value;
		var fechaterminobenef = document.AgregarSif011Form.txt_FechaTerminoBeneficio.value;
		var diasasfam = document.AgregarSif011Form.txt_DiasAsfam.value;
		var codigotramo = document.AgregarSif011Form.dbx_CodigoTramo.value;
		var montobeneficio = document.AgregarSif011Form.txt_MontoBeneficio.value;
		var tipoemision = document.AgregarSif011Form.dbx_TipoEmision.value;
		var codtipoegreso = document.AgregarSif011Form.dbx_TipoEgreso.value;
		var modalidaddepago = document.AgregarSif011Form.dbx_ModPago.value;
		var montodocumento = document.AgregarSif011Form.txt_MontoDocumento.value;
		var nmeroserie = document.AgregarSif011Form.txt_NumeroSerie.value;
		var nmerodocumento = document.AgregarSif011Form.txt_NumeroDocumento.value;
		var fechemisiondoc = document.AgregarSif011Form.txt_FechaEmision.value;
		var codigobanco = document.AgregarSif011Form.dbx_CodBanco.value;
		var fuenteorigen = document.AgregarSif011Form.dbx_FuenteOrigen.value;
		var tipodeclaracion = document.AgregarSif011Form.dbx_TipoDeclaracion.value;
		
		if ((fuenteorigen == 1) && ((rutempresa == "") || (dvempresa == "") || (nombreempresa == "") || (rutafiliado == "") || (dvafiliado == "") || (nombreafiliado == "") 
			|| (codtipobeneficio == 0) || (rutcausante == "") || (dvcausante == "") || (nombrecausante == "") || (codtipocausante == 0) || (codigotramo == 0)
			|| (montobeneficio == 0) || (montodocumento != "") || (nmeroserie != "") || (nmerodocumento != "") || (fechemisiondoc != "") || (codigobanco != 0) 
			|| (diasasfam == 0))){
			
			alert("Error! los datos ingresados no son validos para la Fuente de Origen 1");
			return (false);
		}else{
			if ((fuenteorigen == 2)&&((nombreempresa == "") || (rutafiliado == "") || (dvafiliado == "") || (nombreafiliado == "") || (diasasfam == 0)
				|| (codtipobeneficio == 0) || (rutcausante == "") || (dvcausante == "") || (nombrecausante == "") || (codtipocausante == 0) || (codigotramo == 0)
				|| (montobeneficio == 0) || (montodocumento == 0) || (nmeroserie == "") || (nmerodocumento == "") || (fechemisiondoc == "") || (codigobanco == 0))){
			
				alert("Error! los datos ingresados no son validos para la Fuente de Origen 2");
				return (false);
			}else{
				if (fuenteorigen == 0){ 
					alert("Error! Debe seleccionar una fuente de origen");
					return (false);	
				}else{
					return true;
				}
			}
		}			
	}
	
	/*1.- La fecha de inicio de beneficio y la fecha de termino de beneficio deben estar en el mismo rango de mes, y dicho mes debe corresponder al
	informado.*/
	function validacionUnoPrima(){
		document.AgregarSif011Form.txt_FechaTerminoBeneficio.value="";
	}
	
	function validacionUno(){
		
		id_tipo_declaracion = document.AgregarSif011Form.dbx_TipoDeclaracion.value;
		
		var fechaInicio = document.AgregarSif011Form.txt_FechaInicioBeneficio.value;
		var fechaTermino = document.AgregarSif011Form.txt_FechaTerminoBeneficio.value;
		var mesInformado = document.AgregarSif011Form.numeroMes.value;
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var mesInformado = parseInt(fechaSistema.substring(3,5),10) - 1;
		var anioInformado = parseInt(fechaSistema.substring(6,10),10);
		var mesProceso = mesInformado - 1;
		if (mesInformado == 1){
			anioInformado=anioInformado-1;
			mesProceso = 12;
		}
		//deglose para el rango de fechas informadas, separadas en dias, mes y ao.
		var diaIni = parseInt(fechaInicio.substring(0,2),10);
		var mesIni = parseInt(fechaInicio.substring(3,5),10);
		var anioIni = parseInt(fechaInicio.substring(6,10),10);
		
		var diaTerm = parseInt(fechaTermino.substring(0,2),10);
		var mesTerm = parseInt(fechaTermino.substring(3,5),10);
		var anioTerm = parseInt(fechaTermino.substring(6,10),10);
		
		if (id_tipo_declaracion == 0){
			alert("Seleccione Tipo de Declaracion previo a seleccionar las fechas.");
			document.getElementById("txt_FechaInicioBeneficio").value = " ";
			document.getElementById("txt_FechaTerminoBeneficio").value = " ";
		}else{
			if((mesInformado == mesIni) && (mesInformado == mesTerm) && (id_tipo_declaracion == 2)){
				
			
				if((anioIni > anioTerm) || (anioIni < anioTerm)){
	
					alert("Error, el año ingresado debe pertenecer al periodo informado.");
					document.getElementById("txt_FechaInicioBeneficio").value = " ";
					document.getElementById("txt_FechaTerminoBeneficio").value = " ";
					return false;
				}
			
				//la fecha de inicio no debe ser mayor a la fecha de termino
				if((diaIni > diaTerm) && ((mesIni == mesTerm) || (mesIni > mesTerm) || (mesIni < mesTerm) )&& (anioIni == anioTerm)){
					alert("Error, la Fecha de Inicio no debe ser mayor a la Fecha de Termino.");
					document.getElementById("txt_FechaInicioBeneficio").value = " ";
					document.getElementById("txt_FechaTerminoBeneficio").value = " ";
					return false;
				}
			
				//el mes informado debe permanecer siempre en el mismo rango.
				if((diaIni < diaTerm) && ((mesIni > mesTerm) || (mesIni < mesTerm)) && (anioIni == anioTerm)){
					alert("Error, las fechas deben coincidir en el mismo periodo del mes informado.");
					document.getElementById("txt_FechaInicioBeneficio").value = " ";
					document.getElementById("txt_FechaTerminoBeneficio").value = " ";
					return false;
				}
					
				if((diaIni < diaTerm) && (mesIni == mesTerm) && (anioIni == anioTerm)){
					calcularDiasASFAM();
					return true;
				}		
			
			}else{
				if((mesProceso == mesIni) && (mesProceso == mesTerm) && (id_tipo_declaracion == 1)){
					
					
					if((anioIni > anioTerm) || (anioIni < anioTerm)){
		
						alert("Error, el año ingresado debe pertenecer al periodo informado.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					}	
			
					//la fecha de inicio no debe ser mayor a la fecha de termino
					if((diaIni > diaTerm) && ((mesIni == mesTerm) || (mesIni > mesTerm) || (mesIni < mesTerm) )&& (anioIni == anioTerm)){
						alert("Error, la Fecha de Inicio no debe ser mayor a la Fecha de Termino.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					}
					
					//el mes informado debe permanecer siempre en el mismo rango.
					if((diaIni < diaTerm) && ((mesIni > mesTerm) || (mesIni < mesTerm)) && (anioIni == anioTerm)){
						alert("Error, las fechas deben coincidir en el mismo periodo del mes informado.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					}			
					
					if((diaIni < diaTerm) && (mesIni == mesTerm) && (anioIni == anioTerm)){
						calcularDiasASFAM();
						return true;
					}
	
					}else{
						alert("La fecha ingresada no corresponde al periodo procesado, o al Tipo de Declaracion seleccionada.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					}
				}
			}

	}
	
	function montoDocMontoBenef(){
		
		var modalidaddepago = document.AgregarSif011Form.dbx_ModPago.value;
		var mtoDoc = document.AgregarSif011Form.txt_MontoDocumento.value;
		var mtoBenef = document.AgregarSif011Form.txt_MontoBeneficio.value;
		
		if(modalidaddepago == 1){
			if(mtoBenef.length > 0){
				document.AgregarSif011Form.txt_MontoDocumento.value = document.AgregarSif011Form.txt_MontoBeneficio.value;
			}	
		}		
	}
	function montoDocMontoBenef2(){
		
		var modalidaddepago = document.AgregarSif011Form.dbx_ModPago.value;
		var mtoDoc = document.AgregarSif011Form.txt_MontoDocumento.value;
		var mtoBenef = document.AgregarSif011Form.txt_MontoBeneficio.value;
		
		if(modalidaddepago == 1){
			if(mtoDoc.length > 0){
				document.AgregarSif011Form.txt_MontoBeneficio.value = document.AgregarSif011Form.txt_MontoDocumento.value;
			}	
		}		
	}
	
	/*2.- si el tipo de declaracion es 1 entonces el modo de pago debe ser distinto de 7 */
	function validacionDos(){
		var tipo_Declaracion = document.AgregarSif011Form.dbx_TipoDeclaracion.value;
		var fechaInicio = document.AgregarSif011Form.txt_FechaInicioBeneficio.value;
		
		if ((fechaInicio != "") && (fechaInicio != " ")){
			validacionUno();
		}
		
		if( tipo_Declaracion == 0 ){
			document.getElementById("dbx_ModPago").disabled=false;
			document.getElementById("txt_MontoDocumento").disabled=false;
			document.getElementById("txt_NumeroSerie").disabled=false;
			document.getElementById("txt_NumeroDocumento").disabled=false;
			document.getElementById("calenFecEmiDoc").disabled=false;
			document.getElementById("dbx_CodBanco").disabled=false;	
		}
		
		if( tipo_Declaracion == 1 ){
			document.AgregarSif011Form.dbx_ModPago.disabled = true;
			document.AgregarSif011Form.txt_MontoDocumento.disabled = true;
			document.getElementById("txt_NumeroSerie").disabled=true;
			document.getElementById("txt_NumeroDocumento").disabled=true;
			document.getElementById("calenFecEmiDoc").disabled=true;
			document.getElementById("dbx_CodBanco").disabled=true;	
			document.AgregarSif011Form.dbx_ModPago.value = 7;
			document.AgregarSif011Form.txt_MontoDocumento.value = "";
			document.AgregarSif011Form.txt_NumeroSerie.value = "";
			document.AgregarSif011Form.txt_NumeroDocumento.value = "";
			document.AgregarSif011Form.txt_FechaEmision.value = "";
			document.AgregarSif011Form.dbx_CodBanco.value = 0;
		}
				
		if( tipo_Declaracion == 2 ){
			if (document.AgregarSif011Form.dbx_ModPago.value == 7){
				document.getElementById("dbx_ModPago").disabled=false;
				document.getElementById("txt_MontoDocumento").disabled=false;
				document.getElementById("txt_NumeroSerie").disabled=false;
				document.getElementById("txt_NumeroDocumento").disabled=false;
				document.getElementById("calenFecEmiDoc").disabled=false;
				document.getElementById("dbx_CodBanco").disabled=false;
				document.AgregarSif011Form.dbx_ModPago.value = 0;	
				alert("La modalidad de Pago no correspondia con el Tipo de Declaracion seleccionado, cambie la modalidad de Pago una vez mas.");
			}
		}
		
		var fuenteOri = document.AgregarSif011Form.dbx_FuenteOrigen.value;
		var modalidadPago =  document.AgregarSif011Form.dbx_ModPago.value;
		if ((fuenteOri == 2) && (modalidadPago == 1))
		{
			document.AgregarSif011Form.txt_MontoDocumento.value = document.AgregarSif011Form.txt_MontoBeneficio.value;
			document.getElementById("txt_MontoDocumento").disabled=true;
		}else{
			document.getElementById("txt_MontoDocumento").disabled=false;
		}
	}
	
	function verificaValidacionDos(tipo_declaracion, modo_pago){
		
		if(tipo_declaracion == 1 && modo_pago == 7){
			return true;
		}else{
			alert("La modalidad de Pago no corresponde con el Tipo de Declaracion seleccionado.");
			return false;
		}		
	}
	
	
	/*6.- */
	function validacionSeis(tipo_emision, mes_recaudacion, mes_remuneracion, numero_declaracion){
		
		if(tipo_emision == 101){
			if((Trim(mes_recaudacion) != "") && (Trim(mes_remuneracion) != "") && (Trim(numero_declaracion) != "")){
				return true;
			}else{
				alert("Los campos Mes Recaudacion, Mes Remuneracion y Numero Declaracion son obligatorios para el Tipo Emision seleccionado");
				return false;
			}		
		}
	}
	
	/*7.- */
	function validacionSiete(cod_tipoBeneficiario, rut_empleador, dv_empleador, nombre_empleador){
		
		if(cod_tipoBeneficiario == 1){
			if((Trim(rut_empleador) != "") && (Trim(dv_empleador) != "") && (Trim(nombre_empleador) != "")){
				return true;
			}else{
				alert("Los campos Rut Empresa y Nombre Empresa son obligatorios para el Tipo Beneficiario seleccionado.");
				return false;
			}
		}else{
			return true;
		}		
	}
	
	/*8.- */
	function validacionOcho(tipo_declaracion, monto_documento, numero_serie, numero_documento){
		
		if(tipo_declaracion == 2){
			if((Trim(monto_documento) != "") && (Trim(numero_serie) != "") && (Trim(numero_documento) != "")){
				return true;
			}else{
				alert("Los campos Monto Documento, Numero Serie y Numero Documento son obligatorios para el Tipo Declaracion seleccionado.");
				return false;
			}	
		}else{
			return true;
		}	
	}
	
	/*9.-*/
	function validacionBenef(){
		
		tipoBenef = document.AgregarSif011Form.dbx_TipoBeneficio.value; 
		tipoCausan = document.AgregarSif011Form.dbx_TipoCausante.value;     
		
		if((tipoBenef == 2) && ((tipoCausan != 23) && (tipoCausan != 24) && (tipoCausan != 0))){
		  	alert("Tipo Causante no acorde al Tipo de Beneficio.");
		  	document.AgregarSif011Form.dbx_TipoCausante.value = 0;
		}
		
	}
	
	/*10.-*/
	
	function validacionNueve(fuenteOrigen, montoDocumento, numeroSerie, numeroDocumento, fechaEmision, codigoBanco){
		
		if(fuenteOrigen == 2){
			if((Trim(montoDocumento) != "") && (Trim(numeroSerie) != "") && (Trim(numeroDocumento) != "")&& (Trim(fechaEmision) != "")&& (Trim(codigoBanco) != 0)){
				return true;
			}else{
				alert("Los campos Monto Documento, Numero Serie, Numero Documento, Fecha Emision y Codigo de Banco son obligatorios para la Fuente de Origen seleccionada.");
				return false;
			}
		if (fuenteOrigen == 0){
				alert("Debe seleccionar un Tipo de Origen");
				return false;	
			}		
		}else{
			return true;
		}		
	}	
	

	function limpiarCamposFormulario(){
		
		document.AgregarSif011Form.txt_Rut.value  = "" ;
		document.AgregarSif011Form.txt_NumVerif.value  = "" ;
		document.AgregarSif011Form.txt_NombreEmpresa.value  = "" ;
		document.AgregarSif011Form.txt_NumeroDeclaracion.value  = "" ;
		document.AgregarSif011Form.txt_RutAfiliado.value  = "" ;
		document.AgregarSif011Form.txt_NumVerifAfiliado.value  = "" ;
		document.AgregarSif011Form.txt_NombreAfiliado.value  = "" ;
		document.AgregarSif011Form.dbx_TipoBeneficio.value  = 0 ;
		document.AgregarSif011Form.dbx_TipoBeneficiario.value  = 0 ;
		document.AgregarSif011Form.txt_RutCausante.value  = "" ;
		document.AgregarSif011Form.txt_NumVerifCausante.value  = "" ;
		document.AgregarSif011Form.txt_NombreCausante.value  = "" ;
		document.AgregarSif011Form.dbx_TipoCausante.value  = 0 ;
		document.AgregarSif011Form.txt_FechaInicioBeneficio.value  = "" ;
		document.AgregarSif011Form.txt_FechaTerminoBeneficio.value  = "" ;
		document.AgregarSif011Form.txt_DiasAsfam.value  = "" ;
		document.AgregarSif011Form.dbx_CodigoTramo.value  = "" ;
		document.AgregarSif011Form.txt_MontoBeneficio.value  = "" ;
		document.AgregarSif011Form.dbx_TipoEmision.value  = 0 ;
		document.AgregarSif011Form.dbx_TipoEgreso.value  = 0 ;
		document.AgregarSif011Form.dbx_ModPago.value  = 0 ;
		document.AgregarSif011Form.txt_MontoDocumento.value  = "" ;
		document.AgregarSif011Form.txt_NumeroSerie.value  = "" ;
		document.AgregarSif011Form.txt_NumeroDocumento.value  = "" ;
		document.AgregarSif011Form.txt_FechaEmision.value  = "" ;
		document.AgregarSif011Form.dbx_CodBanco.value  = 0 ;
		document.AgregarSif011Form.dbx_FuenteOrigen.value  = 0 ;
		document.AgregarSif011Form.dbx_TipoDeclaracion.value  = 0 ;
	}
	
	function agregarRegistroSif011(){
		
		var mesInformado = document.AgregarSif011Form.fecpertras.value;
		
		var rutEmpresa = document.AgregarSif011Form.txt_Rut.value;
		var dvEmpresa = document.AgregarSif011Form.txt_NumVerif.value;
		var nombreEmpresa = document.AgregarSif011Form.txt_NombreEmpresa.value;
		var numeroDeclaracion = document.AgregarSif011Form.txt_NumeroDeclaracion.value;
		var rutAfiliado = document.AgregarSif011Form.txt_RutAfiliado.value;
		var dvAfiliado = document.AgregarSif011Form.txt_NumVerifAfiliado.value;
		var nombreAfiliado = document.AgregarSif011Form.txt_NombreAfiliado.value;
		var tipoBeneficio = document.AgregarSif011Form.dbx_TipoBeneficio.value;
		var tipoBeneficiario = document.AgregarSif011Form.dbx_TipoBeneficiario.value;
		var rutCausante = document.AgregarSif011Form.txt_RutCausante.value;
		var dvCausante = document.AgregarSif011Form.txt_NumVerifCausante.value;
		var nombreCausante = document.AgregarSif011Form.txt_NombreCausante.value;
		var tipoCausante = document.AgregarSif011Form.dbx_TipoCausante.value;
		var fechaInicioBeneficio = document.AgregarSif011Form.txt_FechaInicioBeneficio.value;
		var fechaTerminoBeneficio = document.AgregarSif011Form.txt_FechaTerminoBeneficio.value;
		var diasAsfam = document.AgregarSif011Form.txt_DiasAsfam.value;
		var codigoTramo = document.AgregarSif011Form.dbx_CodigoTramo.value;
		var montoBeneficio = document.AgregarSif011Form.txt_MontoBeneficio.value;
		var tipoEmision = document.AgregarSif011Form.dbx_TipoEmision.value;
		var tipoEgreso = document.AgregarSif011Form.dbx_TipoEgreso.value;
		var modalidadPago = document.AgregarSif011Form.dbx_ModPago.value;
		var montoDocumento = document.AgregarSif011Form.txt_MontoDocumento.value;
		var numeroSerie = document.AgregarSif011Form.txt_NumeroSerie.value;
		var numeroDocumento = document.AgregarSif011Form.txt_NumeroDocumento.value;
		var fechaEmision = document.AgregarSif011Form.txt_FechaEmision.value;
		var codigoBanco = document.AgregarSif011Form.dbx_CodBanco.value;
		var fuenteOrigen = document.AgregarSif011Form.dbx_FuenteOrigen.value;
		var tipoDeclaracion = document.AgregarSif011Form.dbx_TipoDeclaracion.value;
		var validaIngreso = true;
		validaIngreso = validacionSiete(tipoBeneficiario, rutEmpresa, dvEmpresa, nombreEmpresa);
			if(validaIngreso == true){
				validaIngreso = validacionOcho(tipoDeclaracion, montoDocumento, numeroSerie, numeroDocumento);
				if(validaIngreso == true){
					validaIngreso = validacionNueve(fuenteOrigen, montoDocumento, numeroSerie, numeroDocumento, fechaEmision, codigoBanco);
					if (validaIngreso == true){
						validaIngreso = validadorFuenteOrigenAgregar();
					}
				}
			}
		if(validaIngreso == true){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
			document.AgregarSif011Form.btn_Agregar.disabled=true;
			document.AgregarSif011Form.btn_Cancelar.disabled=true;
			AgregarRegistroDivisionPrevisionalDWR.insertSif011( mesInformado,
																rutEmpresa,
																dvEmpresa,
																nombreEmpresa,
																numeroDeclaracion,
																rutAfiliado,
																dvAfiliado,
																nombreAfiliado,
																tipoBeneficio,
																tipoBeneficiario,
																rutCausante,
																dvCausante,
																nombreCausante,
																tipoCausante,
																fechaInicioBeneficio,
																fechaTerminoBeneficio,
																diasAsfam,
																codigoTramo,
																montoBeneficio,
																tipoEmision,
																tipoEgreso,
																modalidadPago,
																montoDocumento,
																numeroSerie,
																numeroDocumento,
																fechaEmision,
																codigoBanco,
																fuenteOrigen,
																tipoDeclaracion,
																function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				if(resp == 0){
					alert("Los datos se han agregado exitosamente.");
					limpiarCamposFormulario();
					document.getElementById("pantallaDeCarga").style.visibility = "hidden";
					document.AgregarSif011Form.btn_Agregar.disabled = false;
					document.AgregarSif011Form.btn_Cancelar.disabled = false;
					if(correlativoRequest == "" || correlativoRequest == "null"){
						cerrarVentana();
					}else{
						eliminarDatoErroneoSif012(correlativoRequest);	
						cerrarVentana();
					}
					//enviaFormulario(2);
				
				}else{
					alert("Error, los datos no se han insertado.");
				}	
			});
			
		}else {
			//alert("Error, los datos no cumplen la validacion.");
		}
	}
	
	/**Funcion que realiza un borrado logico de la tabla sif012, tomando en consideracion el correlativo (id)*/
	function eliminarDatoErroneoSif012(correlativo){
		
		DWREngine.setAsync(false);
		GenerarListadoErroresDWR.eliminarDatoErroneoSif012(correlativo, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos se han actualizado.");
			}else{
				alert("No es posible actualizar la grilla.");
			}
		});
		DWREngine.setAsync(true);
	}
		
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.AgregarSif011Form.txt_Rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.AgregarSif011Form.txt_NumVerif.value = digitoVerificador;
	}

	function autocompletarDigitoAfiliado(){
		var afiliado = document.AgregarSif011Form.txt_RutAfiliado.value;
		var verifAfiliado = DigitoVerificadorRut(afiliado);
		document.AgregarSif011Form.txt_NumVerifAfiliado.value = verifAfiliado;
	}
	
	function autocompletarDigitoCausante(){
		var causante = document.AgregarSif011Form.txt_RutCausante.value;
		var verifCausante = DigitoVerificadorRut(causante);
		document.AgregarSif011Form.txt_NumVerifCausante.value = verifCausante;
	}
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la insercin.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 11;
		
		DWREngine.setAsync(false);
		AgregarRegistroDivisionPrevisionalDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.AgregarSif011Form.fecpertras.value = data.rutaArchivo;
				document.AgregarSif011Form.txt_MesProceso.value = data.periodoProceso;
				document.AgregarSif011Form.numeroMes.value = data.mesConsultado;
			}else{
				document.AgregarSif011Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	/**Funcion que calcula los dias ASFAM*/
	function calcularDiasASFAM(){
		
		var fechaInicio_beneficio = document.AgregarSif011Form.txt_FechaInicioBeneficio.value;
		var fechaTermino_beneficio = document.AgregarSif011Form.txt_FechaTerminoBeneficio.value;
		
		var diaInicioBeneficio = parseInt(fechaInicio_beneficio.substring(0,2),10);
		var diaTerminoBeneficio = parseInt(fechaTermino_beneficio.substring(0,2),10);
		
		var diasASFAM = diaTerminoBeneficio - diaInicioBeneficio + 1;
		
		if((diasASFAM == 31) || (diasASFAM > 25)){
			diasASFAM = 30;
		}
		
		document.AgregarSif011Form.txt_DiasAsfam.value = diasASFAM;	
	}
	
	function cerrarVentana(){
		window.close();
	}
	
	/**Funcion que dispara una accion sobre validacion de fechas de inicio y termino dependiendo de la eleccion del combobox de fuente de origen*/


	/* ----- NUEVO ----- */
	/**Funcion que recibe como parametro el correlativo del dato erroneo de corresponde a mes que esta en el registro 12, y que debe ser agregado en la
	tabla de sif011.*/
	var datos = new Array();
	function obtenerDatoErroneo(correlativo){
		
		DWREngine.setAsync(false);
		AgregarRegistroDivisionPrevisionalDWR.obtenerDataSif012(correlativo, function(data){

			var sif012VO = null;
			sif012VO = data;
			
			if(sif012VO != null)
			{
				document.AgregarSif011Form.txt_Rut.value = sif012VO.rut_empresa;
				document.AgregarSif011Form.txt_NumVerif.value = sif012VO.dv_empresa;
				document.AgregarSif011Form.txt_NombreEmpresa.value = sif012VO.nombre_empresa;
				document.AgregarSif011Form.txt_RutAfiliado.value = sif012VO.rut_afiliado;
				document.AgregarSif011Form.txt_NumVerifAfiliado.value = sif012VO.dv_afiliado;
				document.AgregarSif011Form.txt_NombreAfiliado.value = sif012VO.nombre_afiliado;
				document.AgregarSif011Form.dbx_TipoBeneficio.value = sif012VO.cod_tipo_beneficio;
				document.AgregarSif011Form.dbx_TipoBeneficiario.value = sif012VO.tipo_beneficiario;
				document.AgregarSif011Form.txt_RutCausante.value = sif012VO.rut_causante;
				document.AgregarSif011Form.txt_NumVerifCausante.value = sif012VO.dv_causante;
				document.AgregarSif011Form.txt_NombreCausante.value = sif012VO.nombre_causante;
				document.AgregarSif011Form.dbx_TipoCausante.value = sif012VO.cod_tipo_causante;
				document.AgregarSif011Form.txt_FechaInicioBeneficio.value = sif012VO.fechaInicioBeneficioDate;
				document.AgregarSif011Form.txt_FechaTerminoBeneficio.value = sif012VO.fechaTerminoBeneficioDate;
				document.AgregarSif011Form.txt_DiasAsfam.value = sif012VO.dias_asfam;
				document.AgregarSif011Form.dbx_CodigoTramo.value = sif012VO.codigo_tramo;
				document.AgregarSif011Form.txt_MontoBeneficio.value = sif012VO.monto_beneficio;
				document.AgregarSif011Form.dbx_TipoEmision.value = sif012VO.tipo_emision;
				document.AgregarSif011Form.dbx_TipoEgreso.value = sif012VO.cod_tipo_egreso;
				document.AgregarSif011Form.dbx_ModPago.value = sif012VO.modalidad_pago;
				document.AgregarSif011Form.txt_MontoDocumento.value = sif012VO.monto_documento;
				document.AgregarSif011Form.txt_NumeroSerie.value = sif012VO.numero_serie;
				document.AgregarSif011Form.txt_NumeroDocumento.value = sif012VO.numero_documento;
				document.AgregarSif011Form.txt_FechaEmision.value = sif012VO.fechaEmisionDocumentoDate;
				document.AgregarSif011Form.dbx_CodBanco.value = sif012VO.codigo_banco;
				document.AgregarSif011Form.dbx_FuenteOrigen.value = sif012VO.referncia_origen;
				document.AgregarSif011Form.dbx_TipoDeclaracion.value = sif012VO.tipo_declaracion;
			}
		});
		DWREngine.setAsync(true);
		
	}
	
	
	function cargarPantallaAgregar(){
		
		var id = '<%=request.getParameter("idsif")%>';
		correlativoRequest = id;
		if(id == "" || id == "null")
		{
			return false;
		}else{	
			obtenerDatoErroneo(id);
		}
	}	



</script>
</head>
<body onload="cargarMesInformado();cargarPantallaAgregar();">
<html:form action="/agregarSif011.do">
<div id="caja_registro">
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="numeroMes" value="0">
	<input type="hidden" name="fecpertras" value="0">
	<table width="1100" border="0">
		<tr>
			<td align="right">
				<!-- <a href="#" align="right" onclick="enviaFormulario(1);"><B>Volver</B></a> -->
				<a href="#" align="right" onclick="cerrarVentana();"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp;
				<!-- <a href="#" align="right" onClick="enviaFormulario(-1);" ><B>Cerrar Sesin</B></a> -->
			</td>
		</tr>
		<tr>
			<td height="25"><strong><p1>AGREGAR REGISTROS REPORTE DIVISION REGIMENES LEGALES SIF011 </p1></strong></td>
		</tr>
		<tr></tr>
		
		<tr>
			<td><br/>
			<p><p2>1. Egresos de asignaciones familiares del mes por causante</p2></p>
		
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="200"></td>
					<td width="312"></td>
					<td></td>
					<td width="200"></td>
					<td width="312"></td>
				</tr>
				<tr>
					<td height="40"><strong>Mes Informado</strong></td>
					<td colspan="4">
						<input type="text" name="txt_MesProceso" id="txt_MesProceso" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')" disabled="true"/></td>
				</tr>
				<tr>
					<td height="40">
						<strong>Fuente de Origen</strong>
					</td>
					<td colspan="4">
						<html:select property="dbx_FuenteOrigen" styleClass="dbx_modPago" value="0" onblur="validadorFuenteOrigen();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoOrigen" property="id_tipo_origen" labelProperty="desc_tipo_origen"/>
						</html:select>
					</td>
				</tr>				
				<tr>
					<td height="40">
						<strong>Tipo Declaraci&oacute;n</strong>
					</td>
					<td>
						<html:select property="dbx_TipoDeclaracion" styleClass="dbx_modPago" value="0" onblur="validacionDos();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoDeclaracion" property="id_tipo_declaracion" labelProperty="desc_tipo_declaracion"/>
						</html:select>
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>N&uacute;mero Declaraci&oacute;n</strong></td>
					<td>
						<input type="text" name="txt_NumeroDeclaracion" id="txt_NumeroDeclaracion" size="20" maxlength="20" onkeyup="Upper(this,'T')" onkeypress="Upper(this,'T')"/></td>
				</tr>
				<tr>
					<td height="40">
						<strong>RUT Empresa </strong>
					</td>
					<td >
						<input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autoCompletarDigitoVerificador();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerif" id="txt_NumVerif" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Nombre Empresa</strong></td>
					<td>
						<input type="text" name="txt_NombreEmpresa" id="txt_NombreEmpresa" size="50" maxlength="80" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td height="40">
						<strong>RUT Afiliado </strong>
					</td>
					<td >
						<input type="text" name="txt_RutAfiliado" id="txt_RutAfiliado" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autocompletarDigitoAfiliado();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerifAfiliado" id="txt_NumVerifAfiliado" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Nombre Afiliado</strong></td>
					<td>
						<input type="text" name="txt_NombreAfiliado" id="txt_NombreAfiliado" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')"/></td>
				</tr>
				<tr>
					<td height="40">
						<strong>Tipo Beneficio</strong>
					</td>
					<td>
						<html:select property="dbx_TipoBeneficio" styleClass="dbx_modPago" value="0" onblur ="validacionBenef();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoBeneficio" property="id_tipo_beneficio" labelProperty="desc_tipo_beneficio"/>
						</html:select>
					</td>
					<td>&nbsp;</td>
					<td >
						<strong>Tipo Beneficiario</strong>
					</td>
					<td>
						<html:select property="dbx_TipoBeneficiario" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoBeneficiario" property="id_tipo_beneficiario" labelProperty="desc_tipo_beneficiario"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="40">
						<strong>RUT Causante </strong>
					</td>
					<td >
						<input type="text" name="txt_RutCausante" id="txt_RutCausante" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autocompletarDigitoCausante();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerifCausante" id="txt_NumVerifCausante" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Nombre Causante</strong></td>
					<td>
						<input type="text" name="txt_NombreCausante" id="txt_NombreCausante" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')"/></td>
				</tr>
				<tr>
					<td height="40">
						<strong>Tipo Causante</strong>
					</td>
					<td colspan="4">
						<html:select property="dbx_TipoCausante" styleClass="dbx_modPago" value="0" onblur="validacionBenef();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoCausante" property="id_tipo_causante" labelProperty="desc_tipo_causante"/>
						</html:select>
					</td>
				</tr>
				<tr>	
					<td height="40">
						<strong>Fecha Inicio Beneficio</strong></td>
					<td >
						<input type="text" name="txt_FechaInicioBeneficio" id="txt_FechaInicioBeneficio" size="20" maxlength="11" disabled="true">
						<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaInicioBeneficio);validacionUnoPrima();"/>
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Fecha T&eacute;rmino Beneficio</strong></td>
					<td >
						<input type="text" name="txt_FechaTerminoBeneficio" id="txt_FechaTerminoBeneficio" size="20" maxlength="11" disabled="true">
						<IMG style="cursor:hand" border="0" src="../images/Calendar.jpg" width="20" height="18" onClick="ShowCalendarFor(txt_FechaTerminoBeneficio);validacionUno();"/>
					</td>
				</tr>
				<tr>
					<td height="40"><strong>D&iacute;as ASFAM</strong></td>
					<td >
						<input type="text" name="txt_DiasAsfam" id="txt_DiasAsfam" size="20" maxlength="4" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>C&oacute;digo Tramo</strong></td>
					<td>
						<html:select property="dbx_CodigoTramo" styleClass="comboCodTramo" value="0">
							<html:option value="0">Seleccione </html:option>
							<html:option value="1">1 </html:option>
							<html:option value="2">2 </html:option>
							<html:option value="3">3 </html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="40">
						<strong>Monto Beneficio</strong></td>
					<td>
						<input type="text" name="txt_MontoBeneficio" id="txt_MontoBeneficio" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onchange="montoDocMontoBenef();"/></td>
					<td>&nbsp;</td>
					<td >
						<strong>Tipo Emisi&oacute;n</strong>
					</td>
					<td>
						<html:select property="dbx_TipoEmision" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoEmision" property="id_tipo_emision" labelProperty="desc_tipo_emision"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="40">
						<strong>Tipo Egreso</strong>
					</td>
					<td colspan="4">
						<html:select property="dbx_TipoEgreso" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoEgreso" property="id_tipo_Egreso" labelProperty="desc_tipo_egreso"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="40">
						<strong>Modalidad de pago</strong>
					</td>
					<td colspan="4">
						<html:select property="dbx_ModPago" styleClass="dbx_modPago" value="0" onblur="validacionDos();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListModalidadPago" property="id_modalidad_pago" labelProperty="desc_modalidad_pago"/>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td height="40">
						<strong>Monto Documento</strong>
					</td>
					<td colspan="4">
						<input type="text" name="txt_MontoDocumento" id="txt_MontoDocumento" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onchange="montoDocMontoBenef2();" />
					</td>
				</tr>
				<tr>
					<td height="40"><strong>N&uacute;mero de Serie</strong></td>
					<td >
						<input type="text" name="txt_NumeroSerie" id="txt_NumeroSerie" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>N&uacute;mero  Documento</strong></td>
					<td >
						<input type="text" name="txt_NumeroDocumento" id="txt_NumeroDocumento" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>	
				</tr>
				<tr>	
					<td height="40">
						<strong>Fecha Emisi&oacute;n Documento</strong></td>
					<td >
						<input type="text" name="txt_FechaEmision" id="txt_FechaEmision" size="20" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">
						<IMG name="calenFecEmiDoc" id="calenFecEmiDoc" style="cursor:hand" border="0" src="../images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaEmision)"/>
					</td>
					<td>&nbsp;</td>
					<td><strong>C&oacute;digo de Banco</strong></td>
					<td >
						<html:select property="dbx_CodBanco" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCodigoBanco" property="cod_banco_normativa" labelProperty="desc_cod_banco_normativa"/>
						</html:select>					
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td height="37" align="right" width="80%">
				<input type="button" name="btn_Agregar" id="btn_Agregar" class="btn_limp" value="Agregar" onclick="agregarRegistroSif011();"/>
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Volver" onclick="cerrarVentana();"/>
				<!-- <input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Volver" onclick="enviaFormulario(1);"/> -->
			</td>
		</tr>
				
	</table>
	 <div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1400px; height: 900px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
		<table width="100%">
			<tr>
				<td height="200">
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<IMG border="0" src="../images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	</div>
</div>
</html:form>
</body>
</html:html>