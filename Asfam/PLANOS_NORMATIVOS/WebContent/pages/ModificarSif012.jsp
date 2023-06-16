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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteDivisionPrevisionalDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroDivisionPrevisionalDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarListadoErroresDWR.js"></script>

<script type="text/javascript">

	var correlativoRequest = "";
	
	function asignaValor(a){

		document.AgregarSif012Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.AgregarSif012Form.submit();
	}

	/*************************************************************************************/
	/*  VALIDACIONES ANEXAS A CAMPOS PARA EL REPORTE sif012 */
	
	/*1.- La fecha de inicio de beneficio y la fecha de termino de beneficio deben estar en el mismo rango de mes, y dicho mes debe corresponder al
	informado.*/
	
	function validadorFuenteOrigen(){
		fuenteOrigen = parseInt(document.AgregarSif012Form.dbx_FuenteOrigen.value,10);
		if (fuenteOrigen == 1){
			//limpiadorValidacionFO();
			document.AgregarSif012Form.dbx_TipoDeclaracion.value = 1;
			document.AgregarSif012Form.dbx_TipoBeneficiario.value = 1;
			document.AgregarSif012Form.dbx_CausalReliquidacion.value = 1;
			document.AgregarSif012Form.dbx_TipoEmision.value = 101;
			document.AgregarSif012Form.dbx_TipoEgreso.value = 1;
			document.AgregarSif012Form.dbx_ModPago.value = 7;
			document.AgregarSif012Form.txt_MontoDocumento.value = "";
			document.AgregarSif012Form.txt_FechaEmision.value = "";
			document.AgregarSif012Form.txt_NumeroSerie.value = "";
			document.AgregarSif012Form.txt_NumeroDocumento.value = "";
			document.AgregarSif012Form.dbx_CodBanco.value = 0;
			//document.AgregarSif012Form.txt_OrigenInfo.value = "";
			document.AgregarSif012Form.dbx_TipoDeclaracion.disabled = true;
			document.AgregarSif012Form.dbx_TipoBeneficiario.disabled = true;
			document.AgregarSif012Form.dbx_CausalReliquidacion.disabled = true;
			document.AgregarSif012Form.dbx_TipoEmision.disabled = true;
			document.AgregarSif012Form.dbx_TipoEgreso.disabled = true;
			document.AgregarSif012Form.dbx_ModPago.disabled = true;
			document.AgregarSif012Form.txt_MontoDocumento.disabled = true;
			document.AgregarSif012Form.calenFecEmiDoc.disabled = true;
			document.AgregarSif012Form.txt_NumeroSerie.disabled = true;
			document.AgregarSif012Form.txt_NumeroDocumento.disabled = true;
			document.AgregarSif012Form.dbx_CodBanco.disabled = true;
			//document.AgregarSif012Form.txt_OrigenInfo.disabled = true;
			
		}
		if(fuenteOrigen == 2){
			//limpiadorValidacionFO();
			
			document.AgregarSif012Form.dbx_TipoDeclaracion.value = 2;
			document.AgregarSif012Form.txt_Rut.value = "70016160";
			document.AgregarSif012Form.txt_NumVerif.value = "9";
			document.AgregarSif012Form.txt_NombreEmpresa.value = "LA ARAUCANA C.C.A.F.";
			document.AgregarSif012Form.dbx_TipoBeneficiario.value = 4;
			document.AgregarSif012Form.dbx_CausalReliquidacion.value = 1;
			document.AgregarSif012Form.dbx_TipoEmision.value = 103;
			document.AgregarSif012Form.dbx_TipoEgreso.value = 1;
			document.AgregarSif012Form.dbx_TipoDeclaracion.disabled = true;
			document.AgregarSif012Form.txt_Rut.disabled = true;
			document.AgregarSif012Form.txt_NumVerif.disabled = true;
			document.AgregarSif012Form.txt_NombreEmpresa.disabled = true;
			document.AgregarSif012Form.dbx_TipoBeneficiario.disabled = true;
			document.AgregarSif012Form.dbx_CausalReliquidacion.disabled = true;
			document.AgregarSif012Form.dbx_TipoEmision.disabled = true;
			document.AgregarSif012Form.dbx_TipoEgreso.disabled = true;
			
		}
		if(fuenteOrigen == 3){
			//limpiadorValidacionFO();
			document.AgregarSif012Form.dbx_TipoDeclaracion.value = 2;
			document.AgregarSif012Form.dbx_TipoBeneficiario.value = 1;
			document.AgregarSif012Form.dbx_CausalReliquidacion.value = 1;
			document.AgregarSif012Form.dbx_TipoEmision.value = 101;
			document.AgregarSif012Form.dbx_TipoEgreso.value = 1;
			document.AgregarSif012Form.dbx_ModPago.value = 1;
			document.AgregarSif012Form.dbx_TipoDeclaracion.disabled = true;
			document.AgregarSif012Form.dbx_TipoBeneficiario.disabled = true;
			document.AgregarSif012Form.dbx_CausalReliquidacion.disabled = true;
			document.AgregarSif012Form.dbx_TipoEmision.disabled = true;
			document.AgregarSif012Form.dbx_TipoEgreso.disabled = true;
			document.AgregarSif012Form.dbx_ModPago.disabled = true;
			
		}
		if(fuenteOrigen == 0){
			//limpiadorValidacionFO();
		}
	}
	
	function limpiadorValidacionFO(){
		document.AgregarSif012Form.dbx_TipoDeclaracion.disabled = false;
		document.AgregarSif012Form.dbx_TipoBeneficiario.disabled = false;
		document.AgregarSif012Form.dbx_CausalReliquidacion.disabled = false;
		document.AgregarSif012Form.dbx_TipoEmision.disabled = false;
		document.AgregarSif012Form.dbx_TipoEgreso.disabled = false;
		document.AgregarSif012Form.dbx_ModPago.disabled = false;
		document.AgregarSif012Form.txt_Rut.disabled = false;
		document.AgregarSif012Form.txt_NumVerif.disabled = false;
		document.AgregarSif012Form.txt_NombreEmpresa.disabled = false;
		document.AgregarSif012Form.txt_MontoDocumento.disabled = false;
		document.AgregarSif012Form.calenFecEmiDoc.disabled = false;
		document.AgregarSif012Form.txt_NumeroSerie.disabled = false;
		document.AgregarSif012Form.txt_NumeroDocumento.disabled = false;
		document.AgregarSif012Form.dbx_CodBanco.disabled = false;
		//document.AgregarSif012Form.txt_OrigenInfo.disabled = false;
		document.AgregarSif012Form.dbx_TipoDeclaracion.value = 0;
		document.AgregarSif012Form.dbx_TipoBeneficiario.value = 0;
		//document.AgregarSif012Form.dbx_CausalReliquidacion.value = 0;
		document.AgregarSif012Form.dbx_TipoEmision.value = 0;
		document.AgregarSif012Form.dbx_TipoEgreso.value = 0;
		document.AgregarSif012Form.dbx_ModPago.value = 0;
		document.AgregarSif012Form.txt_Rut.value = "";
		document.AgregarSif012Form.txt_NumVerif.value = "";
		document.AgregarSif012Form.txt_NombreEmpresa.value = "";
		document.AgregarSif012Form.txt_MontoDocumento.value = "";
		document.AgregarSif012Form.txt_FechaEmision.value = "";
		document.AgregarSif012Form.txt_NumeroSerie.value = "";
		document.AgregarSif012Form.txt_NumeroDocumento.value = "";
		document.AgregarSif012Form.dbx_CodBanco.value = 0;
		//document.AgregarSif012Form.txt_OrigenInfo.value = "";
	}
	
	
	
	
	
	
	function validacionUno(){
		
		id_tipo_declaracion = document.AgregarSif012Form.dbx_TipoDeclaracion.value;
		
		var fechaInicio = document.AgregarSif012Form.txt_FechaInicioBeneficio.value;
		var fechaTermino = document.AgregarSif012Form.txt_FechaTerminoBeneficio.value;
		var mesInformado = document.AgregarSif012Form.numeroMes.value;
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var mesInformado = parseInt(fechaSistema.substring(3,5),10) - 1;
		var anioInformado = parseInt(fechaSistema.substring(6,10),10);
		
		if (mesInformado == 1){
			mesInformado = 13;
		}
		if (mesInformado == 2){
			mesInformado = 14;
		}
		
		//deglose para el rango de fechas informadas, separadas en dias, mes y ao.
		var diaIni = parseInt(fechaInicio.substring(0,2),10);
		var mesIni = parseInt(fechaInicio.substring(3,5),10);
		var anioIni = parseInt(fechaInicio.substring(6,10),10);
		
		var diaTerm = parseInt(fechaTermino.substring(0,2),10);
		var mesTerm = parseInt(fechaTermino.substring(3,5),10);
		var anioTerm = parseInt(fechaTermino.substring(6,10),10);
		
		if (id_tipo_declaracion == 0){
			alert("Seleccione Tipo de Declaracin previo a seleccionar las fechas.");
			document.getElementById("txt_FechaInicioBeneficio").value = " ";
			document.getElementById("txt_FechaTerminoBeneficio").value = " ";
		}else{
			if((id_tipo_declaracion == 2)){
				
					
				//la fecha de inicio no debe ser mayor a la fecha de termino
				if((diaIni > diaTerm) && ((mesIni == mesTerm) || (mesIni > mesTerm) || (mesIni < mesTerm) )&& (anioIni == anioTerm)){
					alert("Error, la Fecha de Inicio no debe ser mayor a la Fecha de Trmino.");
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
				if(((anioIni * 100) + mesIni) >= ((anioInformado*100)+mesInformado)){
					alert("Error, la fecha seleccionada debe ser inferior al mes de proceso.");
					document.getElementById("txt_FechaInicioBeneficio").value = " ";
					document.getElementById("txt_FechaTerminoBeneficio").value = " ";
					return false;
				} 
				if(anioIni < (anioInformado - 5)){
					alert("Error, el mes informado no puede ser inferior a 5 aos atras.");
					document.getElementById("txt_FechaInicioBeneficio").value = " ";
					document.getElementById("txt_FechaTerminoBeneficio").value = " ";
					return false;
				}
				if((anioIni == (anioInformado - 5)) && (mesIni < mesInformado)){
					alert("Error, el mes informado no puede ser inferior a 5 aos atras.");
					document.getElementById("txt_FechaInicioBeneficio").value = " ";
					document.getElementById("txt_FechaTerminoBeneficio").value = " ";
					return false;
				}
				
				if((diaIni < diaTerm) && (mesIni == mesTerm) && (anioIni == anioTerm)){
					calcularDiasASFAM();
					return true;
				}
				
			}else{
				if((id_tipo_declaracion == 1)){
					
					//la fecha de inicio no debe ser mayor a la fecha de termino
					if((diaIni > diaTerm) && ((mesIni == mesTerm) || (mesIni > mesTerm) || (mesIni < mesTerm) )&& (anioIni == anioTerm)){
						alert("Error, la Fecha de Inicio no debe ser mayor a la Fecha de Trmino.");
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
					if(anioIni < (anioInformado - 5)){
						alert("Error, el mes informado no puede ser inferior a 5 aos atras.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					}
					if(((anioIni * 100) + mesIni) >= ((anioInformado*100)+(mesInformado-1))){
						alert("Error, la fecha seleccionada debe ser inferior al mes de proceso.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					} 
					
					if((anioIni == (anioInformado - 5)) && (mesIni < mesInformado)){
						alert("Error, el mes informado no puede ser inferior a 5 aos atras.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					}
					
					if((diaIni < diaTerm) && (mesIni == mesTerm) && (anioIni == anioTerm)){
						calcularDiasASFAM();
						return true;
					}			
	
					}else{
						alert("La fecha ingresada no corresponde al periodo procesado, o al Tipo de Declaracin seleccionada.");
						document.getElementById("txt_FechaInicioBeneficio").value = " ";
						document.getElementById("txt_FechaTerminoBeneficio").value = " ";
						return false;
					}
				}
			}
		
	}
	
	
	function montoDocMontoBenef(){
		
		var modalidaddepago = document.AgregarSif012Form.dbx_ModPago.value;
		var mtoDoc = document.AgregarSif012Form.txt_MontoDocumento.value;
		var mtoBenef = document.AgregarSif012Form.txt_MontoBeneficio.value;
		
		if(modalidaddepago == 1){
			if(mtoBenef.length > 0){
				document.AgregarSif012Form.txt_MontoDocumento.value = document.AgregarSif012Form.txt_MontoBeneficio.value;
			}else{
				if(mtoDoc.length > 0){
					document.AgregarSif012Form.txt_MontoBeneficio.value = document.AgregarSif012Form.txt_MontoDocumento.value;
				}
			}	
		}		
	}
	
	function montoDocMontoBenef2(){
		
		var modalidaddepago = document.AgregarSif012Form.dbx_ModPago.value;
		var mtoDoc = document.AgregarSif012Form.txt_MontoDocumento.value;
		var mtoBenef = document.AgregarSif012Form.txt_MontoBeneficio.value;
		
		if(modalidaddepago == 1){
			if(mtoDoc.length > 0){
				document.AgregarSif012Form.txt_MontoBeneficio.value = document.AgregarSif012Form.txt_MontoDocumento.value;
			}	
		}		
	}
	
	/*2.- si el tipo de declaracion es 1 entonces el modo de pago debe ser distinto de 7 */
	function validacionDos(){
		
		var tipo_Declaracion = document.AgregarSif012Form.dbx_TipoDeclaracion.value;
				
		if( tipo_Declaracion == 1 ){
			document.AgregarSif012Form.dbx_ModPago.value = 7;
		}
	}
	
	function validacionUnoPrima(){
		document.AgregarSif012Form.txt_FechaTerminoBeneficio.value="";
	}
	
	function verificaValidacionDos(tipo_declaracion, modo_pago){
		
	var tipo_Declaracion = document.AgregarSif012Form.dbx_TipoDeclaracion.value;
		var fechaInicio = document.AgregarSif012Form.txt_FechaInicioBeneficio.value;
		
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
			document.getElementById("dbx_ModPago").disabled=true;
			document.getElementById("txt_MontoDocumento").disabled=true;
			document.getElementById("txt_NumeroSerie").disabled=true;
			document.getElementById("txt_NumeroDocumento").disabled=true;
			document.getElementById("calenFecEmiDoc").disabled=true;
			document.getElementById("dbx_CodBanco").disabled=true;	
			document.AgregarSif012Form.dbx_ModPago.value = 7;
			document.AgregarSif012Form.txt_MontoDocumento.value = "";
			document.AgregarSif012Form.txt_NumeroSerie.value = "";
			document.AgregarSif012Form.txt_NumeroDocumento.value = "";
			document.AgregarSif012Form.txt_FechaEmision.value = "";
			document.AgregarSif012Form.dbx_CodBanco.value = 0;
		}
				
		if( tipo_Declaracion == 2 ){
			if (document.AgregarSif012Form.dbx_ModPago.value == 7){
				document.AgregarSif012Form.dbx_ModPago.value = 0;
				document.getElementById("dbx_ModPago").disabled=false;
				document.getElementById("txt_MontoDocumento").disabled=false;
				document.getElementById("txt_NumeroSerie").disabled=false;
				document.getElementById("txt_NumeroDocumento").disabled=false;
				document.getElementById("calenFecEmiDoc").disabled=false;
				document.getElementById("dbx_CodBanco").disabled=false;	
				alert("La modalidad de Pago no correspondia con el Tipo de Declaracion seleccionado, cambie la modalidad de Pago una vez mas.");
			}
		}
		
		var fuenteOri = document.AgregarSif012Form.dbx_FuenteOrigen.value;
		var modalidadPago =  document.AgregarSif012Form.dbx_ModPago.value;
		if ((fuenteOri == 2) || (fuenteOri == 3))
		{
			if (document.AgregarSif012Form.txt_MontoDocumento.value < document.AgregarSif012Form.txt_MontoBeneficio.value){
				alert("El Monto de Documento debe ser igual o mayor al Monto de Beneficio");
			}
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
		}else{
			return true;
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
	
	/*10.-*/
	
	function validacionNueve(fuenteOrigen, montoDocumento, numeroSerie, numeroDocumento, fechaEmision, codigoBanco){
		
		//incidente PAGO DIRECTO JLGN 14-01-2014
		if(fuenteOrigen == 1){
			if((Trim(montoDocumento) == "") && (Trim(numeroSerie) == "") && (Trim(numeroDocumento) == "")&& (Trim(fechaEmision) == "")&& (Trim(codigoBanco) == 0)){
				return true;
			}else{
				alert("Los campos Monto Documento, Numero Serie, Numero Documento, Fecha Emision y Codigo de Banco son obligatorios para la Fuente de Origen seleccionada.");
				return false;
			}
		}
		if ((fuenteOrigen == 2) || (fuenteOrigen == 3)){
			if((Trim(montoDocumento) == "") || (Trim(numeroSerie) == "") || (Trim(numeroDocumento) == "")|| (Trim(fechaEmision) == "")|| (Trim(codigoBanco) == 0)){
				alert("Los campos Monto Documento, Numero Serie, Numero Documento, Fecha Emision y Codigo de Banco son obligatorios para la Fuente de Origen seleccionada.");
				return false;
			}else{
				return true;
			}	
		}	
		
		//incidente CTACTE 06-01-2014 CMO
		if(fuenteOrigen == 4){
			return true;
		}
		
		if (fuenteOrigen == 0){
			alert("Debe seleccionar un Tipo de Origen");
			return false;		
		}	
	}
	
	
	function validacionBenef(){
		
		tipoBenef = document.AgregarSif012Form.dbx_TipoBeneficio.value; 
		tipoCausan = document.AgregarSif012Form.dbx_TipoCausante.value;     
		
		if((tipoBenef == 2)&&((tipoCausan != 23) && (tipoCausan != 24)&& (tipoCausan != 0))){
		   		alert("Tipo Causante no acorde al Tipo de Beneficio.");
		   		document.AgregarSif012Form.dbx_TipoCausante.value = 0;
		}
	}
	
	/* FIN VALIDACIONES ANEXAS A CAMPOS PARA EL REPORTE sif012 */
	/***********************************************************/
		
	function validaIngresoDeCampos(){
		fuenteOrigen = parseInt(document.AgregarSif012Form.dbx_FuenteOrigen.value,10);
		
		if(	Trim(document.AgregarSif012Form.txt_Rut.value) == "" ){
				alert("Falta ingresar el campo Rut de Empresa.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_NumVerif.value) == "" ){
				alert("Falta ingresar el campo Numero Verificador del Rut de Empresa.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_RutAfiliado.value) == "" ){
				alert("Falta ingresar el campo Rut de Afiliado.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_NumVerifAfiliado.value) == "" ){
				alert("Falta ingresar el campo Numero Verificador del Rut del afiliado.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_NombreAfiliado.value) == "" ){
				alert("Falta ingresar el campo Nombre Afiliado.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.dbx_TipoBeneficio.value) == 0 ){
				alert("Falta seleccionar el Tipo de Beneficio.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_RutCausante.value) == "" ){
				alert("Falta ingresar el campo Rut del Causante.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_NumVerifCausante.value) == "" ){
				alert("Falta ingresar el campo Numero Verificador del Rut del Causante.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_NombreCausante.value) == "" ){
				alert("Falta ingresar el campo Nombre del Causante.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.dbx_TipoCausante.value) == 0 ){
				alert("Falta seleccionar el Tipo de Causante.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_DiasAsfam.value) == "" ){
				alert("Falta seleccionar las fechas de Beneficio.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.dbx_CodigoTramo.value) == 0 ){
				alert("Falta seleccionar el Codigo de Tramo.");
				return false;
			}
		if(	Trim(document.AgregarSif012Form.txt_MontoBeneficio.value) == "" ){
				alert("Falta ingresar el campo Monto de Beneficio.");
				return false;
			}		
		if(fuenteOrigen == 2){
			if(	Trim(document.AgregarSif012Form.txt_MontoDocumento.value) == "" ){
				alert("Falta ingresar el campo Monto Documento.");
				return false;
			}
			if(	Trim(document.AgregarSif012Form.txt_NumeroSerie.value) == "" ){
				alert("Falta ingresar el campo Numero de Serie.");
				return false;
			}
			if(	Trim(document.AgregarSif012Form.txt_NumeroDocumento.value) == "" ){
				alert("Falta ingresar el campo Numero Documento.");
				return false;
			}
			if(	Trim(document.AgregarSif012Form.txt_FechaEmision.value) == "" ){
				alert("Falta seleccionar la Fecha de Emision.");
				return false;
			}
			if(	Trim(document.AgregarSif012Form.dbx_CodBanco.value) == 0 ){
				alert("Falta seleccionar el codigo de banco.");
				return false;
			}
			//if(	Trim(document.AgregarSif012Form.txt_OrigenInfo.value) == "" ){
			//	alert("Falta ingresar el campo Origen Info.");
			//	return false;
			//}
		}
		if(fuenteOrigen == 0){
			alert("Debe seleccionar una Fuente de Origen.");
			return false;
		}
		
		return true;
		
	}
	
	/**Funcion que limpia los campos del formulario una vez que se han ingresado en el formulario.*/
	function limpiarCamposFormulario(){
	
		document.AgregarSif012Form.txt_Rut.value  = "" ;
		document.AgregarSif012Form.txt_NumVerif.value  = "" ;
		document.AgregarSif012Form.txt_NombreEmpresa.value  = "" ;
		document.AgregarSif012Form.txt_RutAfiliado.value  = "" ;
		document.AgregarSif012Form.txt_NumVerifAfiliado.value  = "" ;
		document.AgregarSif012Form.txt_NombreAfiliado.value  = "" ;
		document.AgregarSif012Form.dbx_TipoBeneficio.value  = 0 ;
		document.AgregarSif012Form.dbx_TipoBeneficiario.value  = 0 ;
		document.AgregarSif012Form.txt_RutCausante.value  = "" ;
		document.AgregarSif012Form.txt_NumVerifCausante.value  = "" ;
		document.AgregarSif012Form.txt_NombreCausante.value  = "" ;
		document.AgregarSif012Form.dbx_TipoCausante.value  = 0 ;
		document.AgregarSif012Form.txt_FechaInicioBeneficio.value  = "" ;
		document.AgregarSif012Form.txt_FechaTerminoBeneficio.value  = "" ;
		document.AgregarSif012Form.txt_DiasAsfam.value  = "" ;
		document.AgregarSif012Form.dbx_CodigoTramo.value  = "" ;
		document.AgregarSif012Form.txt_MontoBeneficio.value  = "" ;
		document.AgregarSif012Form.dbx_TipoEmision.value  = 0 ;
		document.AgregarSif012Form.dbx_TipoEgreso.value  = 0 ;
		document.AgregarSif012Form.dbx_ModPago.value  = 0 ;
		document.AgregarSif012Form.txt_MontoDocumento.value  = "" ;
		document.AgregarSif012Form.txt_NumeroSerie.value  = "" ;
		document.AgregarSif012Form.txt_NumeroDocumento.value  = "" ;
		document.AgregarSif012Form.txt_FechaEmision.value  = "" ;
		document.AgregarSif012Form.dbx_CodBanco.value  = 0 ;
		document.AgregarSif012Form.dbx_FuenteOrigen.value  = 0 ;
		document.AgregarSif012Form.dbx_TipoDeclaracion.value  = 0 ;
		//document.AgregarSif012Form.dbx_CausalReliquidacion.value  = 0 ;	
		//document.AgregarSif012Form.txt_OrigenInfo.value  = 0 ;
		document.AgregarSif012Form.txt_NumeroDeclaracion.value = 0;	
	}
	
	function modificarRegistroSif012(){
		
		var id = document.AgregarSif012Form.idSif012_glob.value;
		var mesInformado = document.AgregarSif012Form.fecpertras.value;
		var numeroDeclaracion = document.AgregarSif012Form.txt_NumeroDeclaracion.value;
		var rutEmpresa = document.AgregarSif012Form.txt_Rut.value;
		var dvEmpresa = document.AgregarSif012Form.txt_NumVerif.value;
		var nombreEmpresa = document.AgregarSif012Form.txt_NombreEmpresa.value;
		var rutAfiliado = document.AgregarSif012Form.txt_RutAfiliado.value;
		var dvAfiliado = document.AgregarSif012Form.txt_NumVerifAfiliado.value;
		var nombreAfiliado = document.AgregarSif012Form.txt_NombreAfiliado.value;
		var tipoBeneficio = document.AgregarSif012Form.dbx_TipoBeneficio.value;
		var tipoBeneficiario = document.AgregarSif012Form.dbx_TipoBeneficiario.value;
		var rutCausante = document.AgregarSif012Form.txt_RutCausante.value;
		var dvCausante = document.AgregarSif012Form.txt_NumVerifCausante.value;
		var nombreCausante = document.AgregarSif012Form.txt_NombreCausante.value;
		var tipoCausante = document.AgregarSif012Form.dbx_TipoCausante.value;
		var fechaInicioBeneficio = document.AgregarSif012Form.txt_FechaInicioBeneficio.value;
		var fechaTerminoBeneficio = document.AgregarSif012Form.txt_FechaTerminoBeneficio.value;
		var diasAsfam = document.AgregarSif012Form.txt_DiasAsfam.value;
		var codigoTramo = document.AgregarSif012Form.dbx_CodigoTramo.value;
		var montoBeneficio = document.AgregarSif012Form.txt_MontoBeneficio.value;
		var tipoEmision = document.AgregarSif012Form.dbx_TipoEmision.value;
		var tipoEgreso = document.AgregarSif012Form.dbx_TipoEgreso.value;
		var modalidadPago = document.AgregarSif012Form.dbx_ModPago.value;
		var montoDocumento = document.AgregarSif012Form.txt_MontoDocumento.value;
		var numeroSerie = document.AgregarSif012Form.txt_NumeroSerie.value;
		var numeroDocumento = document.AgregarSif012Form.txt_NumeroDocumento.value;
		var fechaEmision = document.AgregarSif012Form.txt_FechaEmision.value;
		var codigoBanco = document.AgregarSif012Form.dbx_CodBanco.value;
		var fuenteOrigen = document.AgregarSif012Form.dbx_FuenteOrigen.value;
		var tipoDeclaracion = document.AgregarSif012Form.dbx_TipoDeclaracion.value;
		var causalReliquidacion = document.AgregarSif012Form.dbx_CausalReliq.value;
		var origenInfo = document.AgregarSif012Form.dbx_FuenteOrigen.value;
		var validaIngreso = true;
		validaIngreso = validacionSiete(tipoBeneficiario, rutEmpresa, dvEmpresa, nombreEmpresa);
				if(validaIngreso == true){
					validaIngreso = validacionOcho(tipoDeclaracion, montoDocumento, numeroSerie, numeroDocumento);
					if(validaIngreso == true){
						validaIngreso = validacionNueve(fuenteOrigen, montoDocumento, numeroSerie, numeroDocumento, fechaEmision, codigoBanco);
						if(validaIngreso == true){
							validaIngreso = validaIngresoDeCampos();
						}
					}
				}
		if(validaIngreso == true){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
			document.AgregarSif012Form.btn_Modificar.disabled=true;
			document.AgregarSif012Form.btn_Cancelar.disabled=true;
			EditarReporteDivisionPrevisionalDWR.updateSif012( id, mesInformado,
																rutEmpresa,
																dvEmpresa,
																nombreEmpresa,
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
																causalReliquidacion,
																origenInfo,
																numeroDeclaracion,
																function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				document.AgregarSif012Form.btn_Modificar.disabled=false;
				document.AgregarSif012Form.btn_Cancelar.disabled=false;
				if(resp == 0){
					alert("Los datos se han modificado exitosamente.");
					enviaFormulario(1);					
				}else{
					alert("Error, los datos no se han modificado.");
				}	
			});
		}else{
			//alert("Error, los datos no cumplen la validacin..");
		}	
	}

	/**Funcion que realiza un borrado logico de la tabla sif012, tomando en consideracion el correlativo (id)*/
	function eliminarDatoErroneoSif011(correlativo){
		
		DWREngine.setAsync(false);
		GenerarListadoErroresDWR.eliminarDatoErroneoSif011(correlativo, function(data){
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
		
		var strRut = document.AgregarSif012Form.txt_Rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.AgregarSif012Form.txt_NumVerif.value = digitoVerificador;
	}

	function autocompletarDigitoAfiliado(){
		var afiliado = document.AgregarSif012Form.txt_RutAfiliado.value;
		var verifAfiliado = DigitoVerificadorRut(afiliado);
		document.AgregarSif012Form.txt_NumVerifAfiliado.value = verifAfiliado;
	}
	
	function autocompletarDigitoCausante(){
		var causante = document.AgregarSif012Form.txt_RutCausante.value;
		var verifCausante = DigitoVerificadorRut(causante);
		document.AgregarSif012Form.txt_NumVerifCausante.value = verifCausante;
	}
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la insercin.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 12;
		
		DWREngine.setAsync(false);
		AgregarRegistroDivisionPrevisionalDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.AgregarSif012Form.fecpertras.value = data.rutaArchivo;
				document.AgregarSif012Form.txt_MesProceso.value = data.periodoProceso;
				document.AgregarSif012Form.numeroMes.value = data.mesConsultado;
			}else{
				document.AgregarSif012Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	/**Funcion que calcula los dias ASFAM*/
	function calcularDiasASFAM(){
		
		var fechaInicio_beneficio = document.AgregarSif012Form.txt_FechaInicioBeneficio.value;
		var fechaTermino_beneficio = document.AgregarSif012Form.txt_FechaTerminoBeneficio.value;
		
		var diaInicioBeneficio = parseInt(fechaInicio_beneficio.substring(0,2),10);
		var diaTerminoBeneficio = parseInt(fechaTermino_beneficio.substring(0,2),10);
		
		var diasASFAM = diaTerminoBeneficio - diaInicioBeneficio + 1;
		
		if(diasASFAM >= 25){
			diasASFAM = 30;
		}
		
		document.AgregarSif012Form.txt_DiasAsfam.value = diasASFAM;	
	}
	
	function cerrarVentana(){
		window.close();
	}
	
	function obtenerDataCruzada(correlativo){

		document.AgregarSif012Form.idSelectedItem.value = '<%=session.getAttribute("idSelectedItem")%>';
		document.AgregarSif012Form.idSif012_glob.value = '<%=session.getAttribute("idSif012_glob")%>';
		document.AgregarSif012Form.rutSearch.value = '<%=session.getAttribute("rutSearch")%>';
		var idSelectedItem = '4'; 
		var idSif012_glob = document.AgregarSif012Form.idSif012_glob.value;
		var rutSearch = document.AgregarSif012Form.rutSearch.value;		
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.obtenerDatosSif012PorRutId(idSelectedItem, idSif012_glob, rutSearch, function(data){

			var sif012 = null;
			sif012 = data.listSif012[0];
			
			if(sif012 != null)
			{
				document.AgregarSif012Form.txt_Rut.value = sif012.rut_empresa;
				document.AgregarSif012Form.txt_NumeroDeclaracion.value = Trim(sif012.numero_declaracion);
				document.AgregarSif012Form.dbx_CausalReliq.value = sif012.causal_reliquidacion;
				document.AgregarSif012Form.txt_NumVerif.value = sif012.dv_empresa;
				document.AgregarSif012Form.txt_NombreEmpresa.value = Trim(sif012.nombre_empresa);
				document.AgregarSif012Form.txt_RutAfiliado.value = sif012.rut_afiliado;
				document.AgregarSif012Form.txt_NumVerifAfiliado.value = sif012.dv_afiliado;
				document.AgregarSif012Form.txt_NombreAfiliado.value = Trim(sif012.nombre_afiliado);
				document.AgregarSif012Form.dbx_TipoBeneficio.value = sif012.cod_tipo_beneficio;
				document.AgregarSif012Form.dbx_TipoBeneficiario.value = sif012.tipo_beneficiario;
				document.AgregarSif012Form.txt_RutCausante.value = sif012.rut_causante;
				document.AgregarSif012Form.txt_NumVerifCausante.value = sif012.dv_causante;
				document.AgregarSif012Form.txt_NombreCausante.value = Trim(sif012.nombre_causante);
				document.AgregarSif012Form.dbx_TipoCausante.value = sif012.cod_tipo_causante;
				document.AgregarSif012Form.txt_FechaInicioBeneficio.value = sif012.fechaInicioBeneficioDate;
				document.AgregarSif012Form.txt_FechaTerminoBeneficio.value = sif012.fechaTerminoBeneficioDate;
				document.AgregarSif012Form.txt_DiasAsfam.value = sif012.dias_asfam;
				document.AgregarSif012Form.dbx_CodigoTramo.value = sif012.codigo_tramo;
				document.AgregarSif012Form.txt_MontoBeneficio.value = sif012.monto_beneficio;
				document.AgregarSif012Form.dbx_TipoEmision.value = sif012.tipo_emision;
				document.AgregarSif012Form.dbx_TipoEgreso.value = sif012.cod_tipo_egreso;
				document.AgregarSif012Form.dbx_ModPago.value = sif012.modalidad_pago;
				document.AgregarSif012Form.txt_MontoDocumento.value = sif012.monto_documento;
				//document.AgregarSif012Form.txt_OrigenInfo.value = sif012.
				
				if(Trim(sif012.numero_serie) == "null" || Trim(sif012.numero_serie) == ""){
					document.AgregarSif012Form.txt_NumeroSerie.value = 0;
				}else{	
					document.AgregarSif012Form.txt_NumeroSerie.value = Trim(sif012.numero_serie);
				}
				
				if(Trim(sif012.numero_documento) == "null" || Trim(sif012.numero_documento) == ""){
					document.AgregarSif012Form.txt_NumeroDocumento.value = 0;
				}else{
					document.AgregarSif012Form.txt_NumeroDocumento.value = Trim(sif012.numero_documento);	
				}
				
				document.AgregarSif012Form.txt_FechaEmision.value = sif012.fechaEmisionDocumentoDate;
				document.AgregarSif012Form.dbx_CodBanco.value = sif012.codigo_banco;
				document.AgregarSif012Form.dbx_FuenteOrigen.value = sif012.fuente_origen;
				document.AgregarSif012Form.dbx_TipoDeclaracion.value = sif012.tipo_declaracion;
				
				//este campo como no esta en sif012 se deja en modo "seleccione" para que el usuario escoja lo que se requiere.
				//document.AgregarSif012Form.dbx_CausalReliquidacion.value = 0;
				//document.AgregarSif012Form.txt_OrigenInfo.value = "";
					
			}
		});
		
		DWREngine.setAsync(true);	
	}
	

	function cargarModificar(){
		
		var id = '<%=session.getAttribute("idSelectedItem")%>';
		correlativoRequest = id;
		if(id == "" || id == "null")
		{
			return false;
		}else{	
			obtenerDataCruzada(id);
		}
	}	
	
	function activarCampoFechaEmision(){
		
		fuenteOrigen = parseInt(document.AgregarSif012Form.dbx_FuenteOrigen.value,10);
		if (fuenteOrigen == 1){
			document.AgregarSif012Form.calenFecEmiDoc.disabled = true;
			document.AgregarSif012Form.txt_FechaEmision.disabled = false;
		}else{
			document.AgregarSif012Form.calenFecEmiDoc.disabled = false;
			document.AgregarSif012Form.txt_FechaEmision.disabled = true;
		}
		
	}	
	
</script>
</head>
<body onload="cargarMesInformado();cargarModificar();">
<html:form action="/agregarSif012.do">
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
	<input type="hidden" name="idSelectedItem">
	<input type="hidden" name="idSif012_glob">
	<input type="hidden" name="rutSearch">
	
	<table width="1100" border="0">
		<tr>
			<td align="right">
				<!-- <a href="#" align="right" onclick="enviaFormulario(1);">Volver</a> -->
				<a href="#" align="right" onclick="enviaFormulario(1);"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp;
				<!-- <a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesin</a> -->
			</td>
		</tr>
		<tr>
			<td height="25"><strong><p1>
			MODIFICAR REGISTROS REPORTE DIVISION REGIMENES LEGALES SIF012 </p1></strong></td>
		</tr>
		<tr></tr>
		<tr>
		  <td><br/>
			<p><p2>1. Egresos de asignaciones familiares retroactivas por causante</p2></p>
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
						<input type="text" name="txt_MesProceso" id="txt_MesProceso" size="20" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')" disabled="true"/></td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Fuente de Origen</strong>					</td>
					<td colspan="4">
						<html:select property="dbx_FuenteOrigen" styleClass="dbx_modPago" value="0" onblur="activarCampoFechaEmision();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoOrigen" property="id_tipo_origen" labelProperty="desc_tipo_origen"/>
						</html:select>					</td>
				</tr>				
				<tr>
					<td  height="40">
						<strong>Tipo Declaraci&oacute;n </strong></td>
					<td>
						<html:select property="dbx_TipoDeclaracion" styleClass="dbx_modPago" value="0" >
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoDeclaracion" property="id_tipo_declaracion" labelProperty="desc_tipo_declaracion"/>
						</html:select>				 	</td>				  
				<td >&nbsp;</td>
					<td>
						<strong>Numero Declaraci&oacute;n</strong></td>
					<td colspan="2">
						<input type="text" name="txt_NumeroDeclaracion" id="txt_NumeroDeclaracion" size="20" maxlength="80" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"/></td>
			  </tr>
				<tr>
					<td  height="40">
						<strong>RUT Empresa </strong>					</td>
					<td >
						<input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autoCompletarDigitoVerificador();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerif" id="txt_NumVerif" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">					</td>
				<td >&nbsp;</td>
					<td>
						<strong>Nombre Empresa</strong></td>
					<td colspan="2">
						<input type="text" name="txt_NombreEmpresa" id="txt_NombreEmpresa" size="50" maxlength="80" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td  height="40">
						<strong>RUT Afiliado </strong>					</td>
					<td >
						<input type="text" name="txt_RutAfiliado" id="txt_RutAfiliado" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autocompletarDigitoAfiliado();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerifAfiliado" id="txt_NumVerifAfiliado" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Nombre Afiliado</strong></td>
					<td colspan="2">
						<input type="text" name="txt_NombreAfiliado" id="txt_NombreAfiliado" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')"/></td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Tipo Beneficio</strong>					</td>
					<td>
						<html:select property="dbx_TipoBeneficio" styleClass="dbx_modPago" value="0" >
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoBeneficio" property="id_tipo_beneficio" labelProperty="desc_tipo_beneficio"/>
						</html:select>					</td>
					<td>&nbsp;</td>
					<td >
						<strong>Tipo Beneficiario</strong>					</td>
					<td>
						<html:select property="dbx_TipoBeneficiario" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoBeneficiario" property="id_tipo_beneficiario" labelProperty="desc_tipo_beneficiario"/>
						</html:select>					</td>
				</tr>
				<tr>
					<td  height="40">
						<strong>RUT Causante </strong>					</td>
					<td >
						<input type="text" name="txt_RutCausante" id="txt_RutCausante" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autocompletarDigitoCausante();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerifCausante" id="txt_NumVerifCausante" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Nombre Causante</strong></td>
					<td colspan="2">
						<input type="text" name="txt_NombreCausante" id="txt_NombreCausante" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')"/></td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Tipo Causante</strong>					</td>
					<td colspan="4">
						<html:select property="dbx_TipoCausante" styleClass="dbx_modPago" value="0" >
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoCausante" property="id_tipo_causante" labelProperty="desc_tipo_causante"/>
						</html:select>					</td>
				</tr>
				<tr>	
					<td height="40">
						<strong>Fecha Inicio Beneficio</strong></td>
					<td >
						<input type="text" name="txt_FechaInicioBeneficio" id="txt_FechaInicioBeneficio" size="20" maxlength="11"  disabled="true">
						<IMG style="cursor:hand" border="0" src="./images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaInicioBeneficio);validacionUnoPrima();"/>					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Fecha T&eacute;rmino Beneficio</strong></td>
					<td >
						<input type="text" name="txt_FechaTerminoBeneficio" id="txt_FechaTerminoBeneficio" size="20" maxlength="11"  disabled="true">
						<IMG style="cursor:hand" border="0" src="./images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaTerminoBeneficio);validacionUno();"/>					</td>
				</tr>
				<tr>
					<td height="40"><strong>D&iacute;as ASFAM</strong></td>
					<td >
						<input type="text" name="txt_DiasAsfam" id="txt_DiasAsfam" size="20" maxlength="4" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">					</td>
					<td >&nbsp;</td>
					<td>
						<strong>C&oacute;digo Tramo</strong></td>
					<td>
							<html:select property="dbx_CodigoTramo" styleClass="comboCodTramo" value="0">
								<html:option value="0">Seleccione </html:option>
								<html:option value="1">1 </html:option>
								<html:option value="2">2 </html:option>
								<html:option value="3">3 </html:option>
							</html:select>					</td>
				</tr>
				<tr>
					<td height="40">
						<strong>Monto Beneficio</strong></td>
					<td>
						<input type="text" name="txt_MontoBeneficio" id="txt_MontoBeneficio" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"  onchange="montoDocMontoBenef();"/></td>
					<td>&nbsp;</td>
					<td >
						<strong>Causal Reliquidaci&oacute;n</strong>					</td>
					<td>
						<html:select property="dbx_CausalReliq" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCausalReliquidacion" property="id_causal_reliquidacion" labelProperty="desc_causal_reliquidacion"/>
						</html:select>					</td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Tipo Emisi&oacute;n</strong>					</td>
					<td colspan="4">
						<html:select property="dbx_TipoEmision" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoEmision" property="id_tipo_emision" labelProperty="desc_tipo_emision"/>
						</html:select>					</td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Tipo Egreso</strong>					</td>
					<td colspan="4">
						<html:select property="dbx_TipoEgreso" styleClass="dbx_modPago" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoEgreso" property="id_tipo_Egreso" labelProperty="desc_tipo_egreso"/>
						</html:select>					</td>
				</tr>
				<tr>
					<td  height="40">
						<strong>Monto Documento</strong>					</td>
					<td >
						<input type="text" name="txt_MontoDocumento" id="txt_MontoDocumento" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onChange="montoDocMontoBenef2();"/>					</td>
					<td>&nbsp;</td>
					<td >
						<strong>Modalidad de pago</strong>					</td>
					<td>
						<html:select property="dbx_ModPago" styleClass="dbx_modPago" value="0" >
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListModalidadPago" property="id_modalidad_pago" labelProperty="desc_modalidad_pago"/>
						</html:select>					</td>
				</tr>
				<tr>	
					<td height="40">
						<strong>Fecha Emisi&oacute;n Documento</strong></td>
					<td colspan="4">
						<input type="text" name="txt_FechaEmision" id="txt_FechaEmision" size="20" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true">
						<IMG name="calenFecEmiDoc" id="calenFecEmiDoc" style="cursor:hand" border="0" src="./images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaEmision)"/>					</td>
				</tr>
				<tr>
					<td height="40"><strong>N&uacute;mero de Serie</strong></td>
					<td >
						<input type="text" name="txt_NumeroSerie" id="txt_NumeroSerie" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">					</td>
					<td>&nbsp;</td>
					<td>
						<strong>N&uacute;mero  Documento</strong></td>
					<td >
						<input type="text" name="txt_NumeroDocumento" id="txt_NumeroDocumento" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">					</td>	
				</tr>
			
				<tr>
					<td height="40"><strong>C&oacute;digo de Banco</strong></td>
					<td colspan="4">
						<html:select property="dbx_CodBanco" styleClass="dbx_codBanco" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCodigoBanco" property="cod_banco_normativa" labelProperty="desc_cod_banco_normativa"/>
						</html:select>					</td>
				</tr>
			</table>
		  </td>
		</tr>
		<tr>
			<td height="37" align="right" width="80%">
				<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="modificarRegistroSif012();"/>
				&nbsp;&nbsp;&nbsp;
				<!-- <input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);"/> -->
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);"/>
				
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
					<IMG border="0" src="./images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	</div>
</div>
</html:form>
</body>
</html:html>


