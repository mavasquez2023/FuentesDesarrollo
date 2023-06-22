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
<link rel="stylesheet" href="./css/flora.datepicker.css" type="text/css" media="screen" title="Flora (Default)">

<script src="./js/jquery-1.2.6.js"></script>
<script src="./js/ui.datepicker.js"></script>
<script src="./js/ui.datepicker-es.js"></script>

<script type="text/javascript" language="JavaScript1.2" src="./js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteCotizacionesDWR.js"></script>


<script type="text/javascript">	

	function asignaValor(a){

		document.AgregarSif016Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.AgregarSif016Form.submit();
	}

	/*************************************************************************************/
	/*  VALIDACIONES ANEXAS A CAMPOS PARA EL REPORTE SIF011 */
	
	//1.- validacion de periodos.
	function validacionDePeriodos(){
		var fechaEmision = document.AgregarSif016Form.txt_FechaDeclaracion.value;
		var mesInformado = document.AgregarSif016Form.numeroMes.value;
		var anioInformado= document.AgregarSif016Form.txt_MesProceso.value;
		anioInformado= anioInformado.substring(anioInformado.length-4);
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y ao.
		var diaEmision = parseInt(fechaEmision.substring(0,2),10);
		var mesEmision = parseInt(fechaEmision.substring(3,5),10);
		var anioEmision = parseInt(fechaEmision.substring(6,10),10);
		
		//solo se debe cumplir esto. lo dems es falso.
		if((mesEmision > mesInformado) || (mesEmision < mesInformado) || (anioInformado != anioEmision)){
			alert("La Fecha de Declaracion no puede ser diferente al mes del periodo procesado.");
			document.getElementById("txt_FechaDeclaracion").value = "";
			return false;
		}
		
		return true;

	}
	
	function validadorFechaRemu(){
		var fechaEmision = document.AgregarSif016Form.txt_FechaRenumeracion.value;
		var mesInformado = document.AgregarSif016Form.numeroMes.value;
		var anioInformado= document.AgregarSif016Form.txt_MesProceso.value;
		anioInformado= anioInformado.substring(anioInformado.length-4);
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y ao.
		var diaEmision = parseInt(fechaEmision.substring(0,2),10);
		var mesEmision = parseInt(fechaEmision.substring(3,5),10);
		var anioEmision = parseInt(fechaEmision.substring(6,10),10);
		
		//solo se debe cumplir esto. lo dems es falso.
		if((mesEmision < mesInformado)&&(anioInformado == anioEmision)){
			return true;
		}else{
			alert("La Fecha de Renumeracion informada debe ser menor al mes del periodo procesado.");
			document.getElementById("txt_FechaRenumeracion").value = "";
			return false;
		}
	}
	
	function validaCargas (){
		
		var numeroTotalCargas = document.AgregarSif016Form.txt_NumeroTotalCargas.value;
		var montoAsfamMes = document.AgregarSif016Form.txt_MontoAsfamMes.value;
		var montoAsfamRetro = document.AgregarSif016Form.txt_MontoAsfamRetro.value;
		var cargasRetroactivas = document.AgregarSif016Form.txt_CargasRetroactivas.value;
		
		if ((((numeroTotalCargas != "") && (montoAsfamMes == "")) || ((numeroTotalCargas == "") && (montoAsfamMes != ""))) || (((cargasRetroactivas != "") && (montoAsfamRetro == "")) || ((cargasRetroactivas == "") && (montoAsfamRetro != "")))){
			alert("Debe ingresar Monto por carga indicada.");
			return false;
		}else{
			return true;
		}
	}
	
	function sumaPAF(){
	
		var montoAsfamMes = parseInt(document.AgregarSif016Form.txt_MontoAsfamMes.value,10);
		var montoAsfamRetro = parseInt(document.AgregarSif016Form.txt_MontoAsfamRetro.value,10);
		
		document.AgregarSif016Form.txt_TotalPagoAsigFamiliar.value = montoAsfamMes + montoAsfamRetro;
		calculaNeto();
	}
	
	function validaPorTipoSaldo(){
		
		var tipoSaldo = document.AgregarSif016Form.dbx_TipoSaldo.value;
		
		if (tipoSaldo == 1){
			document.AgregarSif016Form.dbx_ModPago.disabled = true; 
			document.AgregarSif016Form.txt_MontoDocumento.disabled = true;
			document.AgregarSif016Form.txt_NumeroSerie.disabled = true;
			document.AgregarSif016Form.txt_NumeroDocumento.disabled = true;
			//document.AgregarSif016Form.txt_FechaEmision_cal.disabled = true;
			document.AgregarSif016Form.dbx_CodBanco.disabled = true;
			document.AgregarSif016Form.dbx_ModPago.value   = 0 ;
			document.AgregarSif016Form.txt_MontoDocumento.value   = "" ;
			document.AgregarSif016Form.txt_NumeroSerie.value   = "" ;
			document.AgregarSif016Form.txt_NumeroDocumento.value   = "" ;
			document.AgregarSif016Form.txt_FechaEmision.value   = "" ;
			document.AgregarSif016Form.dbx_CodBanco.value  = "" ;
		}else{
			document.AgregarSif016Form.dbx_ModPago.disabled = false;
			document.AgregarSif016Form.txt_MontoDocumento.disabled = false;
			//document.AgregarSif016Form.dbx_ModPago.value = 2; 
			document.AgregarSif016Form.txt_MontoDocumento.value = document.AgregarSif016Form.txt_ResultadoNeto.value;	
			document.AgregarSif016Form.txt_NumeroSerie.disabled = false;
			document.AgregarSif016Form.txt_NumeroDocumento.disabled = false;
			//document.AgregarSif016Form.txt_FechaEmision_cal.disabled = false;
			document.AgregarSif016Form.dbx_CodBanco.disabled = false;
		}	
		ModPagValidador();	
		
	}
	
	function validadorSaldo(){
		
		var tipoSaldo = document.AgregarSif016Form.dbx_TipoSaldo.value;
		var ModPago = document.AgregarSif016Form.dbx_ModPago.value; 
		var MontoDocumento = document.AgregarSif016Form.txt_MontoDocumento.value;
		var NumeroSerie = document.AgregarSif016Form.txt_NumeroSerie.value;
		var NumeroDocumento = document.AgregarSif016Form.txt_NumeroDocumento.value;
		var FechaEmision = document.AgregarSif016Form.txt_FechaEmision.value;
		var CodBanco = document.AgregarSif016Form.dbx_CodBanco.value;
		var ResultadoNeto = document.AgregarSif016Form.txt_ResultadoNeto.value;
	
		if ((tipoSaldo == 2) && ((ModPago == 0) || (MontoDocumento == "") || (NumeroSerie == "") || (NumeroDocumento == "") || (FechaEmision == "") || (CodBanco == 0))){
			if (MontoDocumento != ResultadoNeto){
				alert("Para el tipo de saldo indicado se debe insertar los campos Modo de Pago, Monto de Documento, Numero de Serie, Numero de Documento, Fecha de Emision y Cdigo de Banco obligatoriamente.");
				return false;
			}else{
				alert("El Monto del Documento debe ser igual al Resultado Neto.");
				return false;
			}			
		}else{
			return true;
		}
	}
	
	function ModPagValidador(){
		if(document.AgregarSif016Form.dbx_ModPago.value != 0){
			document.AgregarSif016Form.txt_MontoDocumento.disabled = false;
			document.AgregarSif016Form.txt_NumeroSerie.disabled = false;   
			document.AgregarSif016Form.txt_NumeroDocumento.disabled = false;
			//document.AgregarSif016Form.txt_FechaEmision_cal.disabled = false;
			document.AgregarSif016Form.dbx_CodBanco.disabled = false;
		}else{
			document.AgregarSif016Form.txt_MontoDocumento.disabled = true;   
			document.AgregarSif016Form.txt_NumeroSerie.disabled = true;   
			document.AgregarSif016Form.txt_NumeroDocumento.disabled = true;
			//document.AgregarSif016Form.txt_FechaEmision_cal.disabled = true;
			document.AgregarSif016Form.dbx_CodBanco.disabled = true;
			document.AgregarSif016Form.txt_MontoDocumento.value = "";   
			document.AgregarSif016Form.txt_NumeroSerie.value = "";   
			document.AgregarSif016Form.txt_NumeroDocumento.value = "";
			document.AgregarSif016Form.txt_FechaEmision.value = "";
			document.AgregarSif016Form.dbx_CodBanco.value = 0;
		}
	}
	
	function calculaNeto(){
		var TotalPagoAsigFam = document.AgregarSif016Form.txt_TotalPagoAsigFamiliar.value;
		var TotalCotizacion = document.AgregarSif016Form.txt_TotalCotizacion.value;
		var OtrosDescuentos = document.AgregarSif016Form.txt_OtrosDescuentos.value;
		var MontoReintegrosMes = document.AgregarSif016Form.txt_MontoReintegrosMes.value;
		document.AgregarSif016Form.txt_ResultadoNeto.value = parseInt(TotalPagoAsigFam,10) - parseInt(TotalCotizacion,10) - parseInt(OtrosDescuentos,10) - parseInt(MontoReintegrosMes,10);
		if (document.AgregarSif016Form.txt_ResultadoNeto.value > 0){
			document.AgregarSif016Form.dbx_TipoSaldo.value = 2;  
		}else {
			document.AgregarSif016Form.dbx_TipoSaldo.value = 1;  
		}
		validaPorTipoSaldo();
	}
	
	/* FIN VALIDACIONES ANEXAS A CAMPOS PARA EL REPORTE SIF011 */
	/***********************************************************/
		
	function validaIngresoDeCampos(){
		
		if( Trim(document.AgregarSif016Form.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut Empresa.");
			return false;
		}	
		if( Trim(document.AgregarSif016Form.txt_NumVerif.value) == "" ){
			alert("Falta ingresar el campo Dígito Verificador Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_NombreEmpresa.value) == "" ){
			alert("Falta ingresar el campo Nombre Empresa.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_NumeroDeclaracion.value) == "" ){
			alert("Falta ingresar el campo Número Declaración.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_FechaDeclaracion.value) == "" ){
			alert("Falta ingresar el campo Fecha Declaración.");
			return false;
		}
		if(!validacionDePeriodos()){
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_NumeroTotalTrabajador.value) == "" ){
			alert("Falta ingresar el campo Nmero Total Trabajador.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_MontoReintegrosMes.value) == "" ){
			alert("Falta ingresar el campo Monto Reintegro Mes.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_OtrosDescuentos.value) == "" ){
			alert("Falta ingresar el campo Otros Descuentos.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.dbx_TipoSaldo.value) == 0 ){
			alert("Falta ingresar el campo Tipo Saldo.");
			return false;
		}
		if(Trim(document.AgregarSif016Form.dbx_TipoSaldo.value) == 2){
			if( Trim(document.AgregarSif016Form.dbx_ModPago.value) == 0 ){
			alert("Falta ingresar el campo Modalidad de Pago.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_MontoDocumento.value) == "" ){
			alert("Falta ingresar el campo Monto Documento.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_NumeroSerie.value) == "" ){
			alert("Falta ingresar el campo Nmero Serie.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_NumeroDocumento.value) == "" ){
			alert("Falta ingresar el campo Nmero Documento.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.txt_FechaEmision.value) == "" ){
			alert("Falta ingresar el campo Fecha Emisin.");
			return false;
		}
		if( Trim(document.AgregarSif016Form.dbx_CodBanco.value) == 0){
			alert("Falta ingresar el campo Cdigo Banco.");
			return false;
		}
		}
		return true;
	}	

	/**Funcion que limpia los campos del formulario una vez que se han ingresado en el formulario.*/
	function limpiarCamposFormulario(){

		document.AgregarSif016Form.txt_Rut.value   = "" ;
		document.AgregarSif016Form.txt_NumVerif.value   = "" ;
		document.AgregarSif016Form.txt_NombreEmpresa.value   = "" ;
		document.AgregarSif016Form.txt_NumeroDeclaracion.value   = "" ;
		document.AgregarSif016Form.txt_FechaDeclaracion.value   = "" ;
		document.AgregarSif016Form.txt_NumeroTotalTrabajador.value   = "" ;
		document.AgregarSif016Form.txt_NumeroTotalCargas.value   = 0 ;
		document.AgregarSif016Form.txt_CargasRetroactivas.value   = 0 ;
		document.AgregarSif016Form.txt_MontoAsfamMes.value   = 0 ;
		document.AgregarSif016Form.txt_MontoAsfamRetro.value   = 0 ;
		document.AgregarSif016Form.txt_MontoReintegrosMes.value   = 0 ;
		document.AgregarSif016Form.txt_TotalPagoAsigFamiliar.value   = 0 ;
		document.AgregarSif016Form.txt_TotalCotizacion.value   = 0 ;
		document.AgregarSif016Form.txt_OtrosDescuentos.value   = 0 ;
		document.AgregarSif016Form.txt_ResultadoNeto.value   = 0 ;
		document.AgregarSif016Form.dbx_TipoSaldo.value   = "" ;
		document.AgregarSif016Form.dbx_ModPago.value   = "" ;
		document.AgregarSif016Form.txt_MontoDocumento.value   = "" ;
		document.AgregarSif016Form.txt_NumeroSerie.value   = "" ;
		document.AgregarSif016Form.txt_NumeroDocumento.value   = "" ;
		document.AgregarSif016Form.txt_FechaEmision.value   = "" ;
		document.AgregarSif016Form.dbx_CodBanco.value  = "" ;
	}
	
			
	function modificarRegistroSif016(){
		sumaPAF();
		calculaNeto();
		
		var id = document.AgregarSif016Form.idSif016_glob.value;
		
		var rutEmpresa = document.AgregarSif016Form.txt_Rut.value;
		var dvEmpresa = document.AgregarSif016Form.txt_NumVerif.value;
		var nombreEmpresa = document.AgregarSif016Form.txt_NombreEmpresa.value;
		
		var numeroDeclaracion = document.AgregarSif016Form.txt_NumeroDeclaracion.value;
		var fechaDeclaracion = document.AgregarSif016Form.txt_FechaDeclaracion.value;
		var numeroTotalTrabajador = document.AgregarSif016Form.txt_NumeroTotalTrabajador.value;
		var numeroTotalCargas = document.AgregarSif016Form.txt_NumeroTotalCargas.value;
		var cargasRetroactivas = document.AgregarSif016Form.txt_CargasRetroactivas.value;
		var montoAsfamMes = document.AgregarSif016Form.txt_MontoAsfamMes.value;
		var montoAsfamRetro = document.AgregarSif016Form.txt_MontoAsfamRetro.value;
		var montoReintegroMes = document.AgregarSif016Form.txt_MontoReintegrosMes.value;
		var totalPagoAsigFam = document.AgregarSif016Form.txt_TotalPagoAsigFamiliar.value;
		var totalCotizacion = document.AgregarSif016Form.txt_TotalCotizacion.value;
		var otrosDescuentos = document.AgregarSif016Form.txt_OtrosDescuentos.value;
		var resultadoNeto = document.AgregarSif016Form.txt_ResultadoNeto.value;
		var tipoSaldo = document.AgregarSif016Form.dbx_TipoSaldo.value;
		
		var modalidadPago = document.AgregarSif016Form.dbx_ModPago.value;
		var montoDocumento = document.AgregarSif016Form.txt_MontoDocumento.value;
		var numeroSerie = document.AgregarSif016Form.txt_NumeroSerie.value;
		var numeroDocumento = document.AgregarSif016Form.txt_NumeroDocumento.value;
		var fechaEmision = document.AgregarSif016Form.txt_FechaEmision.value;
		var codigoBanco = document.AgregarSif016Form.dbx_CodBanco.value;
		
		var validador = true;
		
		//validador = validaCargas();
		//if (validador == true){
			//validador = validadorSaldo();
			//if (validador == true){
				validador = validaIngresoDeCampos();
			//}
		//}
		
		if(validador == true){
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
			document.AgregarSif016Form.btn_Agregar.disabled=true;
			document.AgregarSif016Form.btn_Cancelar.disabled=true;
			EditarReporteCotizacionesDWR.updateSif016(id,rutEmpresa,dvEmpresa,nombreEmpresa,numeroDeclaracion,fechaDeclaracion,numeroTotalTrabajador,
														numeroTotalCargas,cargasRetroactivas,montoAsfamMes,montoAsfamRetro,montoReintegroMes,totalPagoAsigFam,
														totalCotizacion,otrosDescuentos,resultadoNeto,tipoSaldo,modalidadPago,montoDocumento,
														numeroSerie,numeroDocumento,fechaEmision,codigoBanco,function(data){
				
				var resp = null;
				resp = data.codRespuesta;
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				document.AgregarSif016Form.btn_Agregar.disabled=false;
				document.AgregarSif016Form.btn_Cancelar.disabled=false;
				if(resp == 0){
					alert("Los datos se han agregado exitosamente.");
					limpiarCamposFormulario();
					window.close();
					//enviaFormulario(1);				
				}else{
					alert("Error, los datos no se han insertado.");
					return false;
				}	
			});
			
		}else{
			
			//alert("No es posible ingresar el registro, debido a que hay errores en los campos ingresados.");	
			return false;
		}	
		
	}
	
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.AgregarSif016Form.txt_Rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.AgregarSif016Form.txt_NumVerif.value = digitoVerificador;
	}

	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la insercin.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 16;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.AgregarSif016Form.txt_MesProceso.value = data.periodoProceso;
				document.AgregarSif016Form.numeroMes.value = data.mesConsultado;
			}else{
				document.AgregarSif016Form.txt_MesProceso.value = "";
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
		document.AgregarSif016Form.dbx_TipoSaldo.disabled = true;
	}	

	function obtenerDataCruzada(correlativo){

		document.AgregarSif016Form.idSelectedItem.value = '<%=session.getAttribute("idSelectedItem")%>';
		document.AgregarSif016Form.idSif016_glob.value = '<%=session.getAttribute("idSif016_glob")%>';
		document.AgregarSif016Form.rutSearch.value = '<%=session.getAttribute("rutSearch")%>';
		var idSelectedItem = '2'; 
		var idSif016_glob = document.AgregarSif016Form.idSif016_glob.value;
		var rutSearch = document.AgregarSif016Form.rutSearch.value;		
		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerDatosSif016ParaEditar(rutSearch,idSif016_glob,idSelectedItem, function(data){

			var sif016 = null;
			sif016 = data.listSif016[0];
			
			if(sif016 != null)
			{
			
				document.AgregarSif016Form.txt_Rut.value = sif016.rut_empresa;
				document.AgregarSif016Form.txt_NumVerif.value = sif016.dv_empresa;
				document.AgregarSif016Form.txt_NombreEmpresa.value = Trim(sif016.nombre_empresa);
				document.AgregarSif016Form.txt_NumeroDeclaracion.value = sif016.numero_declaracion;
				document.AgregarSif016Form.txt_FechaDeclaracion.value = sif016.fechaDeclaracion;				
				document.AgregarSif016Form.txt_NumeroTotalTrabajador.value = sif016.num_total_trabajador;
				document.AgregarSif016Form.txt_NumeroTotalCargas.value = sif016.num_total_cargas;
				document.AgregarSif016Form.txt_CargasRetroactivas.value = sif016.cargas_retroactivas;
				document.AgregarSif016Form.txt_MontoAsfamMes.value = sif016.mto_asfam_mes;
				document.AgregarSif016Form.txt_MontoAsfamRetro.value = sif016.mto_asfam_mes_retro;
				document.AgregarSif016Form.txt_MontoReintegrosMes.value = sif016.mto_reintegros_mes;
				document.AgregarSif016Form.txt_TotalPagoAsigFamiliar.value = sif016.total_pago_asigfam;
				document.AgregarSif016Form.txt_TotalCotizacion.value = sif016.total_de_cotizacion;
				document.AgregarSif016Form.txt_ResultadoNeto.value = sif016.resultado_neto;
				document.AgregarSif016Form.dbx_TipoSaldo.value = sif016.tipo_de_saldo;
				document.AgregarSif016Form.dbx_ModPago.value = sif016.modalidad_pago;
				document.AgregarSif016Form.txt_MontoDocumento.value = sif016.monto_documento;
				document.AgregarSif016Form.txt_NumeroSerie.value = sif016.numero_serie;
				document.AgregarSif016Form.txt_NumeroDocumento.value = sif016.numero_documento;
				document.AgregarSif016Form.dbx_CodBanco.value = sif016.codigo_banco;
				document.AgregarSif016Form.txt_FechaEmision.value = sif016.fechaEmision;
				document.AgregarSif016Form.txt_OtrosDescuentos.value = sif016.otros_descuentos;
					
			}
		});
		
		DWREngine.setAsync(true);	
	}	
</script>
<script>
  $( function() {
  	$( "#txt_FechaEmision" ).datepicker({
      showOn: "both",
      buttonImage: "images/calendar.gif",
      buttonImageOnly: true,
      dateFormat: "dd/mm/yy"
    });
    $( "#txt_FechaDeclaracion" ).datepicker({
      showOn: "both",
      buttonImage: "images/calendar.gif",
      buttonImageOnly: true,
      dateFormat: "dd/mm/yy"
    });
  } );
  
  </script>
</head>
<body onload="cargarMesInformado();cargarModificar();">
<html:form action="/agregarSif016.do">
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
	<input type="hidden" name="numeroMes" value="0" onblur="validacionDePeriodos();">
	<input type="hidden" name="idSelectedItem">
	<input type="hidden" name="idSif016_glob">
	<input type="hidden" name="rutSearch">
	
	<table width="1100" border="0">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="enviaFormulario(1);"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp;
				<!-- <a href="#" align="right" onClick="enviaFormulario(-1);" ><B>Cerrar Sesin</B></a> -->
			</td>
		</tr>
		<tr>
			<td height="25"><strong>
			<p1>
			MODIFICAR REGISTROS REPORTE DIVISI&Oacute;N RECUPERACI&Oacute;N COTIZACIONES SIF016 </p1>
			</strong></td>
			
		</tr>
		<tr></tr>
		<tr>
			<td><br/>
			<p><p2>1. Detalle de compensaci&oacute;n por empleador</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td height="40"><strong>Mes Informado</strong></td>
					<td colspan="4"><input type="text" name="txt_MesProceso" id="txt_MesProceso" size="50" maxlength="80" onkeyup="Upper(this,'L')" onkeypress="Upper(this,'L')" disabled="disabled" /></td>
				</tr>
				<tr>
					<td height="40"><strong>Nmero Declaraci&oacute;n</strong></td>
					<td >
						<input type="text" name="txt_NumeroDeclaracion" id="txt_NumeroDeclaracion" size="20" maxlength="20" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Fecha Declaraci&oacute;n</strong></td>
					<td >
						<input type="text" name="txt_FechaDeclaracion" id="txt_FechaDeclaracion" size="20" maxlength="11" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
						<!-- IMG style="cursor:hand" border="0" src="./images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaDeclaracion);validacionDePeriodos();"/-->
					</td>	
				</tr>
				<tr>
					<td height="40" >
						<strong>RUT Empresa </strong>
					</td>
					<td >
						<input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" size="11" onblur="autoCompletarDigitoVerificador();"/>
						<strong> - </strong>
						<input type="text" name="txt_NumVerif" id="txt_NumVerif" size="1" maxlength="1" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')">
					</td>
					<td >&nbsp;</td>
					<td>
						<strong>Nombre Empresa</strong></td>
					<td>
						<input type="text" name="txt_NombreEmpresa" id="txt_NombreEmpresa" size="50" maxlength="80" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td height="40"><strong>N&uacute;mero Total Trabajador</strong></td>
					<td >
						<input type="text" name="txt_NumeroTotalTrabajador" id="txt_NumeroTotalTrabajador" size="20" maxlength="6" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>N&uacute;mero Total de Cargas</strong></td>
					<td >
						<input type="text" name="txt_NumeroTotalCargas" id="txt_NumeroTotalCargas" size="20" value="0" maxlength="6" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>	

				</tr>
				<tr>
					<td  height="40">
						<strong>Cargas Retroactivas</strong>
					</td>
					<td colspan="4">
						<input type="text" name="txt_CargasRetroactivas" id="txt_CargasRetroactivas" value="0" size="20" maxlength="6" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" />
					</td>
				</tr>
				<tr>
					<td height="40"><strong>Monto ASFAM Mes</strong></td>
					<td >
						<input type="text" name="txt_MontoAsfamMes" id="txt_MontoAsfamMes" size="20" value="0" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onblur="sumaPAF();">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Monto ASFAM Retro</strong></td>
					<td >
						<input type="text" name="txt_MontoAsfamRetro" id="txt_MontoAsfamRetro" size="20" value="0" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onblur="sumaPAF();calculaNeto();">
					</td>	

				</tr>
				<tr>
					<td height="40"><strong>Monto Reintegros Mes</strong></td>
					<td >
						<input type="text" name="txt_MontoReintegrosMes" id="txt_MontoReintegrosMes" size="20" value="0" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onblur="calculaNeto();">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Total Pago Asig. Fam.</strong></td>
					<td >
						<input type="text" name="txt_TotalPagoAsigFamiliar" id="txt_TotalPagoAsigFamiliar" size="20" value="0" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')">
					</td>	

				</tr>
				<tr>
					<td height="40"><strong>Total de Cotizaci&oacute;n</strong></td>
					<td >
						<input type="text" name="txt_TotalCotizacion" id="txt_TotalCotizacion" value="0" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onblur="calculaNeto();">
					</td>
					<td>&nbsp;</td>
					<td>
						<strong>Otros Descuentos</strong></td>
					<td >
						<input type="text" name="txt_OtrosDescuentos" id="txt_OtrosDescuentos" value="0" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" onblur="calculaNeto();">
					</td>	

				</tr>
				<tr>
					<td  height="40">
						<strong>Resultado Neto</strong>
					</td>
					<td >
						<input type="text" name="txt_ResultadoNeto" id="txt_ResultadoNeto" size="20" value="0" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" disabled="true" />
					</td>
					<td>&nbsp;</td>
					<td >
						<strong>Tipo de Saldo</strong>
					</td>
					<td>
						<html:select property="dbx_TipoSaldo" styleClass="dbx_modPago" value="0" onblur="validaPorTipoSaldo();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListTipoSaldo" property="id_tipo_saldo" labelProperty="desc_tipo_saldo"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="40" >
						<strong>Modalidad de pago</strong>
					</td>
					<td colspan="4">
						<html:select property="dbx_ModPago" styleClass="dbx_modPago" value="0" onblur="ModPagValidador();">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListModalidadPago" property="id_modalidad_pago" labelProperty="desc_modalidad_pago"/>
						</html:select>
					</td>

				</tr>
				
				<tr>
					<td  height="40">
						<strong>Monto Documento</strong>
					</td>
					<td colspan="4">
						<input type="text" name="txt_MontoDocumento" value="0" id="txt_MontoDocumento" size="20" maxlength="15" onkeyup="Upper(this,'N')" onkeypress="Upper(this,'N')" />
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
						<input type="text" name="txt_FechaEmision" id="txt_FechaEmision" size="20" maxlength="11">
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
				<input type="button" name="btn_Agregar" id="btn_Agregar" class="btn_limp" value="Modificar" onclick="modificarRegistroSif016();"/>
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);" />
				<!--<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cerrar" onclick="javascript:closeSesion()" />-->
				
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


