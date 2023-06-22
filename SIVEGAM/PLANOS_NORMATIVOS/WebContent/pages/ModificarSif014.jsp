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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroDivisionPrevisionalDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteDivisionPrevisionalDWR.js"></script>


<script type="text/javascript">
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	function asignaValor(a){

		document.AgregarSif014Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.AgregarSif014Form.submit();
	}
	
	/*************************************************************************************/
	/*  VALIDACIONES ANEXAS A CAMPOS PARA EL REPORTE SIF011 */
	
	//1.- validacion de periodos.
	
	function validadorFuenteOrigen(){
		fuenteOrigen = parseInt(document.AgregarSif014Form.dbx_FuenteOrigen.value,10);
		if (fuenteOrigen == 1){
			limpiarValoresFO();
			document.AgregarSif014Form.dbx_TipoBeneficiario.disabled = true;
			document.AgregarSif014Form.txt_MontoTotalReintegro.disabled = true; 
			document.AgregarSif014Form.txt_MontoTotalDeuda.disabled = true; 
			document.AgregarSif014Form.dbx_TipoBeneficiario.value = 1;
			document.AgregarSif014Form.txt_MontoTotalReintegro.value = 0; 
			document.AgregarSif014Form.txt_MontoTotalDeuda.value  = 0; 
		}
		if (fuenteOrigen == 2){
			limpiarValoresFO();
			document.AgregarSif014Form.dbx_TipoBeneficio.disabled = true;   
			document.AgregarSif014Form.dbx_TipoBeneficiario.disabled = true;
			document.AgregarSif014Form.txt_MontoTotalReintegro.disabled = true; 
			document.AgregarSif014Form.txt_MontoTotalDeuda.disabled = true; 
			document.AgregarSif014Form.dbx_TipoBeneficiario.value = 4;
			document.AgregarSif014Form.txt_MontoTotalReintegro.value = 0; 
			document.AgregarSif014Form.txt_MontoTotalDeuda.value  = 0; 
			document.AgregarSif014Form.dbx_TipoBeneficio.value = 1;  
		}
		if (fuenteOrigen == 3){
			alert("fuente de origen invalida para el archivo.") 
			document.AgregarSif014Form.dbx_FuenteOrigen.value=0;
			limpiarValoresFO();	
		}
		if (fuenteOrigen == 0){
			limpiarValoresFO();	
		}
	}
	
	function limpiarValoresFO(){
		document.AgregarSif014Form.dbx_TipoBeneficio.disabled = false;   
		document.AgregarSif014Form.dbx_TipoBeneficiario.disabled = false;
		document.AgregarSif014Form.txt_MontoTotalReintegro.disabled = false; 
		document.AgregarSif014Form.txt_MontoTotalDeuda.disabled = false; 
	}	
	
	function validacionDePeriodos(){
		
		//id_tipo_declaracion = document.AgregarSif014Form.dbx_TipoDeclaracion.value;
		
		var fechaInicio = document.AgregarSif014Form.txt_InicioPeriodoReintegro.value;
		var fechaTermino = document.AgregarSif014Form.txt_FinalPeriodoReintegro.value;
		var mesInformado = document.AgregarSif014Form.numeroMes.value;
		
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
		
		//if (id_tipo_declaracion == 0){
			//alert("Seleccione Tipo de Declaracin previo a seleccionar las fechas.");
			//document.getElementById("txt_FechaInicioBeneficio").value = " ";
			//document.getElementById("txt_FechaTerminoBeneficio").value = " ";
		//}else{
			//if(((mesInformado > mesIni) || (mesInformado > mesTerm)) && (id_tipo_declaracion == 2)){
				
			
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
				
				if((anioIni == (anioInformado - 5)) && (mesIni < mesInformado)){
					alert("Error, el mes informado no puede ser inferior a 5 aos atras.");
					document.getElementById("txt_FechaInicioBeneficio").value = " ";
					document.getElementById("txt_FechaTerminoBeneficio").value = " ";
					return false;
				}
					
				/*if((diaIni < diaTerm) && (mesIni == mesTerm) && (anioIni == anioTerm)){
					calcularDiasASFAM();
					return true;
				}*/
				
			/*}else{
				if((((mesInformado - 1) > mesIni) || ((mesInformado - 1) > mesTerm)) && (id_tipo_declaracion == 1)){
				
				
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
				}*/
			//}

	}
	
	//2.- validacion numerica del campo resultado neto.
	function validacionNumericaResultadoNeto(resultado_neto){
		
		if((parseInt(resultado_neto,10) == 0) || (parseInt(resultado_neto,10) > 0) || (parseInt(resultado_neto,10) < 0)){
			return true;
		}
		
		return false;

	}
	
	//3.- validacion de campos obligatorios cuando tipo de saldo es 2
	function validaCamposObligatoriosParaTipoSaldo(tipo_saldo, numero_serie, numero_documento, fecha_emision_documento){
		
		if(tipo_saldo == 2){
			if((Trim(numero_serie) != "") && (Trim(numero_documento) != "") && (Trim(fecha_emision_documento) != "")){
				return true;
			}
			
			return false;	
		}
	}
	
	function validacionBenef(){
		
		tipoBenef = document.AgregarSif014Form.dbx_TipoBeneficio.value; 
		tipoCausan = document.AgregarSif014Form.dbx_TipoCausante.value;     
		
		if((tipoBenef == 2)&&((tipoCausan != 23) && (tipoCausan != 24) && (tipoCausan != 0))){
		   		alert("Tipo Causante no acorde al Tipo de Beneficio.");
		   		document.AgregarSif014Form.dbx_TipoCausante.value = 0;
		   	
		}
		
	}
	
	/* FIN VALIDACIONES ANEXAS A CAMPOS PARA EL REPORTE SIF011 */
	/***********************************************************/
	
	function validaIngresoDeCampos(){
		
		if( Trim(document.AgregarSif014Form.dbx_FuenteOrigen.value) == 0 ){
			alert("Falta ingresar el campo Fuente de Origen.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut Empresa.");
			return false;
		}	
		if( Trim(document.AgregarSif014Form.txt_NumVerif.value) == "" ){
			alert("Falta ingresar el campo Dgito Verificador Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_NombreEmpresa.value) == "" ){
			alert("Falta ingresar el campo Nombre Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_RutBeneficiario.value) == "" ){
			alert("Falta ingresar el campo Rut Beneficiario.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_NumVerifBeneficiario.value) == "" ){
			alert("Falta ingresar el campo Dgito Verificador Beneficiario.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_NombreBeneficiario.value) == "" ){
			alert("Falta ingresar el campo Nombre Beneficiario.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.dbx_TipoBeneficio.value) == 0 ){
			alert("Falta ingresar el campo Tipo Beneficio.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_RutCausante.value) == "" ){
			alert("Falta ingresar el campo Rut Causante.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_NumVerifCausante.value) == "" ){
			alert("Falta ingresar el campo Dgito Verificador Causante.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_NombreCausante.value) == "" ){
			alert("Falta ingresar el campo Nombre Causante.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.dbx_TipoCausante.value) == 0 ){
			alert("Falta ingresar el campo Tipo Causante.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_InicioPeriodoReintegro.value) == "" ){
			alert("Falta ingresar el campo Inicio Periodo Reintegro.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_FinalPeriodoReintegro.value) == "" ){
			alert("Falta ingresar el campo Final Periodo Reintegro.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.dbx_TipoReintegro.value) == 0 ){
			alert("Falta ingresar el campo Tipo Reintegro.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_MontoTotalReintegro.value) == "" ){
			alert("Falta ingresar el campo Monto Total Reintegro.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_MontoReintegroMes.value) <= 0 ){
			alert("Falta ingresar el campo Monto Reintegro Mes.");
			return false;
		}
		if( Trim(document.AgregarSif014Form.txt_MontoTotalDeuda.value) == "" ){
			alert("Falta ingresar el campo Monto Total Deuda.");
			return false;
		}
		return true;
		
	}
	
	/**Funcion que limpia los campos del formulario una vez que se han ingresado en el formulario.*/
	function limpiarCamposFormulario(){
	
		document.AgregarSif014Form.txt_Rut.value   = "" ;
		document.AgregarSif014Form.txt_NumVerif.value   = "" ;
		document.AgregarSif014Form.txt_NombreEmpresa.value   = "" ;
		document.AgregarSif014Form.txt_RutBeneficiario.value   = "" ;
		document.AgregarSif014Form.txt_NumVerifBeneficiario.value   = "" ;
		document.AgregarSif014Form.txt_NombreBeneficiario.value   = "" ;
		document.AgregarSif014Form.dbx_TipoBeneficio.value   = 0 ;
		document.AgregarSif014Form.dbx_TipoBeneficiario.value   = 0 ;
		document.AgregarSif014Form.txt_RutCausante.value   = "" ;
		document.AgregarSif014Form.txt_NumVerifCausante.value   = "" ;
		document.AgregarSif014Form.txt_NombreCausante.value   = "" ;
		document.AgregarSif014Form.dbx_TipoCausante.value   = 0 ;
		document.AgregarSif014Form.txt_InicioPeriodoReintegro.value   = "" ;
		document.AgregarSif014Form.txt_FinalPeriodoReintegro.value   = "" ;
		document.AgregarSif014Form.dbx_TipoReintegro.value   = 0 ;
		document.AgregarSif014Form.txt_MontoTotalReintegro.value   = "" ;
		document.AgregarSif014Form.txt_MontoReintegroMes.value   = "" ;
		document.AgregarSif014Form.txt_MontoTotalDeuda.value   = "" ;
		document.AgregarSif014Form.dbx_FuenteOrigen.value  = 0 ;	
	}
	
	function modificarRegistroSif014(){
		
		var id = document.AgregarSif014Form.idSif014_glob.value;
		
		var mesInformado = document.AgregarSif014Form.fecpertras.value;
		
		var rutEmpresa = document.AgregarSif014Form.txt_Rut.value;
		var dvEmpresa = document.AgregarSif014Form.txt_NumVerif.value;
		var nombreEmpresa = document.AgregarSif014Form.txt_NombreEmpresa.value;
		
		var rutBeneficiario = document.AgregarSif014Form.txt_RutBeneficiario.value;
		var dvBeneficiario = document.AgregarSif014Form.txt_NumVerifBeneficiario.value;
		var nombreBeneficiario = document.AgregarSif014Form.txt_NombreBeneficiario.value;
		var tipoBeneficio = document.AgregarSif014Form.dbx_TipoBeneficio.value;
		var tipoBeneficiario = document.AgregarSif014Form.dbx_TipoBeneficiario.value;
		var rutCausante = document.AgregarSif014Form.txt_RutCausante.value;
		var dvCausante = document.AgregarSif014Form.txt_NumVerifCausante.value;
		var nombreCausante = document.AgregarSif014Form.txt_NombreCausante.value;
		var tipoCausante = document.AgregarSif014Form.dbx_TipoCausante.value;
		var inicioPeriodoReintegro = document.AgregarSif014Form.txt_InicioPeriodoReintegro.value;
		var finPeriodoReintegro = document.AgregarSif014Form.txt_FinalPeriodoReintegro.value;
		var tipoReintegro = document.AgregarSif014Form.dbx_TipoReintegro.value;
		var montoTotalReintegro = document.AgregarSif014Form.txt_MontoTotalReintegro.value;
		var montoReintegroMes = document.AgregarSif014Form.txt_MontoReintegroMes.value;
		var montoTotalDeuda = document.AgregarSif014Form.txt_MontoTotalDeuda.value;
		var fuenteOrigen = document.AgregarSif014Form.dbx_FuenteOrigen.value;

		/*if(validaCamposObligatoriosParaTipoSaldo(tipo_saldo, numero_serie, numero_documento, fecha_emision_documento) == false){
			alert("Los campos Nmero Serie, Nmero Documento y Fecha Emisin son obligatorios para el Tipo de Saldo seleccionado.");
			return false;
		}*/
			if(validaIngresoDeCampos() == true){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
			document.AgregarSif014Form.btn_Modificar.disabled=true;
			document.AgregarSif014Form.btn_Cancelar.disabled=true;
			EditarReporteDivisionPrevisionalDWR.updateSif014(id,mesInformado,rutEmpresa,dvEmpresa,nombreEmpresa,rutBeneficiario,dvBeneficiario,nombreBeneficiario,
															   tipoBeneficio,tipoBeneficiario,rutCausante,dvCausante,nombreCausante,tipoCausante,
															   inicioPeriodoReintegro,finPeriodoReintegro,tipoReintegro,montoTotalReintegro,
															   montoReintegroMes,montoTotalDeuda,fuenteOrigen,function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				document.AgregarSif014Form.btn_Modificar.disabled=false;
				document.AgregarSif014Form.btn_Cancelar.disabled=false;
				if(resp == 0){
					alert("Los datos se han modificado exitosamente.");
					//limpiarCamposFormulario();
					enviaFormulario(1);
				
				}else{
					alert("Error, los datos no se han modificado.");
				}	
			});
		
		}else{
			
			//alert("No es posible ingresar el registro, debido a que faltan campos por ingresar.");	
			return false;
		}
	  	
	}
	
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.AgregarSif014Form.txt_Rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.AgregarSif014Form.txt_NumVerif.value = digitoVerificador;
	}

	function autocompletarDigitoBeneficiario(){
		var beneficiario = document.AgregarSif014Form.txt_RutBeneficiario.value;
		var verifBeneficiario = DigitoVerificadorRut(beneficiario);
		document.AgregarSif014Form.txt_NumVerifBeneficiario.value = verifBeneficiario;
	}
	
	function autocompletarDigitoCausante(){
		var causante = document.AgregarSif014Form.txt_RutCausante.value;
		var verifCausante = DigitoVerificadorRut(causante);
		document.AgregarSif014Form.txt_NumVerifCausante.value = verifCausante;
	}
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la insercin.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 14;
		
		DWREngine.setAsync(false);
		AgregarRegistroDivisionPrevisionalDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.AgregarSif014Form.fecpertras.value = data.rutaArchivo;
				document.AgregarSif014Form.txt_MesProceso.value = data.periodoProceso;
				document.AgregarSif014Form.numeroMes.value = data.mesConsultado;
			}else{
				document.AgregarSif014Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	function cerrarVentana(){
		window.close();
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

	function obtenerDataCruzada(correlativo){

		document.AgregarSif014Form.idSelectedItem.value = '<%=session.getAttribute("idSelectedItem")%>';
		document.AgregarSif014Form.idSif014_glob.value = '<%=session.getAttribute("idSif014_glob")%>';
		document.AgregarSif014Form.rutSearch.value = '<%=session.getAttribute("rutSearch")%>';
		var idSelectedItem = '4'; 
		var idSif014_glob = document.AgregarSif014Form.idSif014_glob.value;
		var rutSearch = document.AgregarSif014Form.rutSearch.value;		
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.obtenerDatosSif014PorRutId(idSelectedItem, idSif014_glob, rutSearch, function(data){

			var sif014 = null;
			sif014 = data.listSif014[0];
			
			if(sif014 != null)
			{
				document.AgregarSif014Form.dbx_FuenteOrigen.value = sif014.fuente_origen;
				
				document.AgregarSif014Form.txt_Rut.value = sif014.rut_empresa;
				document.AgregarSif014Form.txt_NumVerif.value = sif014.dv_empresa;
				document.AgregarSif014Form.txt_NombreEmpresa.value = Trim(sif014.nombre_empresa);
				document.AgregarSif014Form.txt_RutBeneficiario.value = sif014.rut_beneficiario;
				document.AgregarSif014Form.txt_NumVerifBeneficiario.value = sif014.dv_beneficiario;
				document.AgregarSif014Form.txt_NombreBeneficiario.value = Trim(sif014.nombre_beneficiario);
				document.AgregarSif014Form.dbx_TipoBeneficio.value = sif014.tipo_beneficio;
				document.AgregarSif014Form.dbx_TipoBeneficiario.value = sif014.tipo_beneficiario;
				document.AgregarSif014Form.txt_RutCausante.value = sif014.rut_causante;
				document.AgregarSif014Form.txt_NumVerifCausante.value = sif014.dv_causante;
				document.AgregarSif014Form.txt_NombreCausante.value = Trim(sif014.nombre_causante);
				document.AgregarSif014Form.dbx_TipoCausante.value = sif014.tipo_causante;
				document.AgregarSif014Form.txt_InicioPeriodoReintegro.value = sif014.inicioPeriodoReintegroDate;
				document.AgregarSif014Form.txt_FinalPeriodoReintegro.value = sif014.finalPeriodoReintegro;
				document.AgregarSif014Form.dbx_TipoReintegro.value = sif014.tipo_reintegro;
				document.AgregarSif014Form.txt_MontoTotalReintegro.value = sif014.monto_total_reintegro;
				document.AgregarSif014Form.txt_MontoReintegroMes.value = sif014.monto_reintegro_mes;
				document.AgregarSif014Form.txt_MontoTotalDeuda.value = sif014.monto_total_deuda;
					
			}
		});
		
		DWREngine.setAsync(true);	
	}
	

</script>
</head>
<body onload="cargarMesInformado();cargarModificar();">
<html:form action="/agregarSif014.do">
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
	<input type="hidden" name="idSif014_glob">
	<input type="hidden" name="rutSearch">

	<table width="1100" border="0">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="enviaFormulario(1);"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp; 
				<!-- <a href="#" align="right" onClick="enviaFormulario(-1);"><B>Cerrar	Sesin</B></a> -->
			</td>
		</tr>
		<tr>
			<td height="25"><strong><p1> MODIFICAR REGISTROS REPORTE DIVISION REGIMENES LEGALES SIF014 </p1></strong></td>
		</tr>
		<tr></tr>
		<tr>
			<td><br />
			<p><p2>1. Detalle de compensacin por empleador</p2></p>
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
					<td colspan="4"><input type="text" name="txt_MesProceso" id="txt_MesProceso" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')"
						disabled="true" /></td>
				</tr>
				<tr>
					<td height="40"><strong>Fuente de Origen</strong></td>
					<td colspan="4"><html:select property="dbx_FuenteOrigen" styleClass="dbx_modPago" value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListTipoOrigen" property="id_tipo_origen" labelProperty="desc_tipo_origen" />
					</html:select></td>
				</tr>				
				<tr>
					<td height="40"><strong>RUT Empresa </strong></td>
					<td><input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" size="11" onblur="autoCompletarDigitoVerificador();" /> <strong> -
					</strong> <input type="text" name="txt_NumVerif" id="txt_NumVerif" size="1"
						maxlength="1" onkeyup="Upper(this,'A')"
						onkeypress="Upper(this,'A')"></td>
					<td>&nbsp;</td>
					<td><strong>Nombre Empresa</strong></td>
					<td colspan="2"><input type="text" name="txt_NombreEmpresa"
						id="txt_NombreEmpresa" size="50" maxlength="80"
						onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td height="40"><strong>RUT Beneficiario </strong></td>
					<td><input type="text" name="txt_RutBeneficiario"
						id="txt_RutBeneficiario" maxlength="9" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" size="11"
						onblur="autocompletarDigitoBeneficiario();" /> <strong> -
					</strong> <input type="text" name="txt_NumVerifBeneficiario"
						id="txt_NumVerifBeneficiario" size="1" maxlength="1"
						onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"></td>
					<td>&nbsp;</td>
					<td><strong>Nombre Beneficiario</strong></td>
					<td colspan="2"><input type="text"
						name="txt_NombreBeneficiario" id="txt_NombreBeneficiario"
						size="50" maxlength="80" onkeyup="Upper(this,'A')"
						onkeypress="Upper(this,'A')" /></td>
				</tr>
				<tr>
					<td height="40"><strong>Tipo Beneficio</strong></td>
					<td><html:select property="dbx_TipoBeneficio"
						styleClass="dbx_modPago" value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListTipoBeneficio"
							property="id_tipo_beneficio" labelProperty="desc_tipo_beneficio" />
					</html:select></td>
					<td>&nbsp;</td>
					<td><strong>Tipo Beneficiario</strong></td>
					<td><html:select property="dbx_TipoBeneficiario"
						styleClass="dbx_modPago" value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListTipoBeneficiario"
							property="id_tipo_beneficiario"
							labelProperty="desc_tipo_beneficiario" />
					</html:select></td>
				</tr>
				<tr>
					<td height="40"><strong>RUT Causante </strong></td>
					<td><input type="text" name="txt_RutCausante"
						id="txt_RutCausante" maxlength="9" onkeyup="Upper(this,'N')"
						onkeypress="Upper(this,'N')" size="11"
						onblur="autocompletarDigitoCausante();" /> <strong> - </strong> <input
						type="text" name="txt_NumVerifCausante" id="txt_NumVerifCausante"
						size="1" maxlength="1" onkeyup="Upper(this,'A')"
						onkeypress="Upper(this,'A')"></td>
					<td>&nbsp;</td>
					<td><strong>Nombre Causante</strong></td>
					<td colspan="2"><input type="text" name="txt_NombreCausante"
						id="txt_NombreCausante" size="50" maxlength="80"
						onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')" /></td>
				</tr>
				<tr>
					<td height="40"><strong>Tipo Causante</strong></td>
					<td colspan="4"><html:select property="dbx_TipoCausante"
						styleClass="dbx_modPago" value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListTipoCausante"
							property="id_tipo_causante" labelProperty="desc_tipo_causante" />
					</html:select></td>
				</tr>
				<tr>
					<td height="40"><strong>Inicio Periodo Reintegro</strong></td>
					<td><input type="text" name="txt_InicioPeriodoReintegro"
						id="txt_InicioPeriodoReintegro" size="20" maxlength="11"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						disabled="true"> <IMG style="cursor:hand" border="0"
						src="./images/Calendar.jpg" width="16" height="16"
						onClick="ShowCalendarFor(txt_InicioPeriodoReintegro)" /></td>
					<td>&nbsp;</td>
					<td><strong>Final Periodo Reintegro</strong></td>
					<td><input type="text" name="txt_FinalPeriodoReintegro"
						id="txt_FinalPeriodoReintegro" size="20" maxlength="11"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"
						disabled="true"> <IMG style="cursor:hand" border="0"
						src="./images/Calendar.jpg" width="16" height="16"
						onClick="ShowCalendarFor(txt_FinalPeriodoReintegro);validacionDePeriodos();" /></td>
				</tr>
				<tr>
					<td height="40"><strong>Tipo Reintegro</strong></td>
					<td colspan="4"><html:select property="dbx_TipoReintegro"
						styleClass="dbx_modPago" value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListTipoReintegro"
							property="id_tipo_reintegro" labelProperty="desc_tipo_reintegro" />
					</html:select></td>
				</tr>
				<tr>
					<td height="40"><strong>Monto Total Reintegro</strong></td>
					<td><input type="text" name="txt_MontoTotalReintegro"
						id="txt_MontoTotalReintegro" size="20" maxlength="6"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"></td>
					<td>&nbsp;</td>
					<td><strong>Monto Reintegro Mes</strong></td>
					<td><input type="text" name="txt_MontoReintegroMes"
						id="txt_MontoReintegroMes" size="20" maxlength="6"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')"></td>
				</tr>
				<tr>
					<td height="40"><strong>Monto Total Deuda</strong></td>
					<td colspan="4"><input type="text" name="txt_MontoTotalDeuda"
						id="txt_MontoTotalDeuda" size="20" maxlength="15"
						onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" /></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td height="37" align="right" width="80%">
				<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="modificarRegistroSif014();" /> 
				&nbsp;&nbsp;&nbsp; 
				<!-- <input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);" /> -->
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);" />
				<!--  <input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="javascript:closeSesion()" />-->
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


