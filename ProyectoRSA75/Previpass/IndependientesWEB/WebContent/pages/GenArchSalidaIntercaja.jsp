<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA DE AFILIACION TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet"
	type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet"
	type="text/css" />

<link href="/IndependientesWEB/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery-ui.min.js"></script>

<script type="text/javascript" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" src="/IndependientesWEB/js/Calendario.js"></script>

<script type="text/javascript" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/SalidaIntercajaDWR.js"></script>
<script type="text/javascript">
	
	/*Definicion de variables globales*/
	var cantEnviosMail = 0;
	var datos = new Array();
	var fechaCorteMax;
	var finMesActual = 0;
	/*Funcion que asigna un valor al formulario, necesario para el boton volver o cancelar y salir.*/
	function asignaValor(a){

		document.GenArchSalidaIntForm.opcion.value = a;
	}
	
	function enviaFormulario(a){
	
		asignaValor(a);
		document.GenArchSalidaIntForm.submit();
	}
	
	function DesbloqueaCampos(){
		
		document.GenArchSalidaIntForm.txt_NumSesion.disabled = false;
		//document.getElementById("btn_Enviar").disabled = true;
		//document.GenArchSalidaIntForm.btn_Enviar.style.visibility = "hidden";
		obtenerRangoParaIntercaja();
	}
	
	function verificaIngresoNumSesion()
	{
		var numSes = document.GenArchSalidaIntForm.txt_NumSesion.value;
		if(numSes != ""){
			return true;
		}else{	
			return false;
		}
	}
	
	/*Funcion que consulta los datos que componen al archivo de salida hacia intercaja.*/
	function consultaSalidaAfiliadosDWR(){
	
		//Se valida que se ingresen los campos necesarios para la busqueda.
		if(Trim(document.GenArchSalidaIntForm.txt_NumSesion.value) == "")
		{
			alert("Se debe ingresar el Número de la Sesión.");
			return false;
		}
		
		if(Trim(document.GenArchSalidaIntForm.txt_FecSesion.value) == "")
		{
			alert("Se debe ingresar la Fecha de Sesión.");
			return false;		
		}
		
		if(Trim(document.GenArchSalidaIntForm.txt_FecInicio.value) == "")
		{
			alert("Falta ingresar el campo Fecha de Inicio.");
			return false;		
		}
		
		if(Trim(document.GenArchSalidaIntForm.txt_FecCorte.value) == "")
		{
			alert("Falta ingresar el campo Fecha de Corte.");
			return false;		
		}
		
		var fechaInicio = document.GenArchSalidaIntForm.txt_FecInicio.value;
		var fechaFin = document.GenArchSalidaIntForm.txt_FecCorte.value;
		//var fechaInicio = "01/01/2012";
		//var fechaFin = "31/03/2013";
		var user = "<%=session.getAttribute("IDAnalista")%>";
		
		if(Trim(fechaInicio) == "" || Trim(fechaFin) == "")
		{
			alert("Debe ingresar los campos de búsqueda fecha de inicio y fecha de corte.");
			return false;
		}
		
		if(validaFechas() == false)
		{
			//alert("La fecha de corte no puede ser menor o igual a la fecha de inicio.");
			//limpiarFechas();
			return false;
			
		}else{
			SalidaIntercajaDWR.consultaSalidaAfiliadosIntercaja(fechaInicio, fechaFin, user, function(data)
			{
				datos = data.lisSalidaIntercaja;
				document.GenArchSalidaIntForm.archivo.value = data.archivoSalidaIntercaja;
				document.GenArchSalidaIntForm.nombreArchivoIntercaja.value = data.nombreArchivo;
				document.GenArchSalidaIntForm.afiliadosNuevos.value = data.afiliacionesNuevas;
				document.GenArchSalidaIntForm.desafiliados.value = data.desafiliados;
				document.GenArchSalidaIntForm.cambioCAAF.value = data.cambioCCAF;
				document.GenArchSalidaIntForm.fallecidos.value = data.fallecidos;
				document.GenArchSalidaIntForm.desafiliadosUno.value = data.desafiliadosTipo1;
				document.GenArchSalidaIntForm.desafiliadosDos.value = data.desafiliadosTipo2;

				//limpiarCampos();
				if(document.GenArchSalidaIntForm.afiliadosNuevos.value == 0 &&
				   document.GenArchSalidaIntForm.desafiliados.value == 0 &&
				   document.GenArchSalidaIntForm.cambioCAAF.value == 0 &&
				   document.GenArchSalidaIntForm.fallecidos.value == 0	){
					
						cargaDatosEnGrillaVacio();
				}else{	
					cargaDatosEnGrilla();
				}
				//validaEnvioMail();
				insertaLogIntercajaDWR();
				
			});
		}
		
	}
	
	/*VALIDACION FECHA DE SESION*/
	/*Función que realiza una validacion de la fecha de sesion. Dicha fecha de sesion debe cumplir con ciertas caracteristicas:
	La fecha de sesion debe ser mayor o igual a la fecha de sistema
	*/
	/*function validaFechaDeSesion()
	{
		var fecSession = document.GenArchSalidaIntForm.txt_FecSesion.value;
		var fechaDeSistema = "<%=session.getAttribute("FechaSistema")%>";
		
		if(Comparar_Fecha(fecSession, fechaDeSistema))
		{
			return true;
		}
		return false;	
	}*/
	
	/*function valFecSesion()
	{
		if(verificaIngresoNumSesion()==false){
			alert("Se debe ingresar el número de la sesión.");
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			return false;
		}
		
		//FRM AQUI DESCOMENTAR ESTO.
		if(validaFechaDeSesion() == false ){
			alert("La fecha de sesión ingresada debe ser mayor a la fecha actual.");
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			document.GenArchSalidaIntForm.txt_FecInicio.value = "";
			document.GenArchSalidaIntForm.txt_FecCorte.value = "";
			return false;
		}
		
		//FRM AQUI DESCOMENTAR ESTO.
		//valida que la fecha de sesion no sea meyor a la fecha de sistema, y que esté dentro del rango permitido.
		if(validaFechaSesion() == false){
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			document.GenArchSalidaIntForm.txt_FecInicio.value = "";
			document.GenArchSalidaIntForm.txt_FecCorte.value = "";			
			return false;
		}
		
		//aqui llamar a DWR que llena fecha de inicio y de corte.
		llenarFechasCotas();
	}*/
	
	/*function validaFechaSuma()
	{
		var resultFecha;
		var numeroDias = 22;
		var fechaDeSes = document.GenArchSalidaIntForm.txt_FecSesion.value;
		resultFecha = anadirDias(numeroDias);
		
		if(Comparar_Fecha(resultFecha,fechaDeSes)){
			return true;
		}
		return false;			
	}*/
	
	/*Funcion que suma una determinada cantidad de dias a una fecha en particular.*/
	function anadirDias(num)
	{
	  	hoy = new Date();
		var i=0;
		
	  	while (i<num) {
	    	hoy.setTime(hoy.getTime()+24*60*60*1000); // añadimos 1 día
			if (hoy.getDay() != 6 && hoy.getDay() != 0)
	    	i++;  
		}
	  	
	  	mes = hoy.getMonth()+1;//se añade 1 al mes, porque este comienza desde 0.
	  	if (mes<10) {
	  		mes = '0'+mes;
	  	}
	  	
	  	fecha = hoy.getDate()+ '/' + mes + '/' + hoy.getFullYear();
	  	return fecha;
	}
	
	function llenarFechasCotas()
	{
		actual = new Date();
		mesActual = actual.getMonth();
		mesSiguiente = actual.getMonth() + 1;
		anioActual = actual.getFullYear();
		
		if(mesActual < 10){
			mesActual = '0'+ mesActual;
		}
		if(mesSiguiente < 10){
			mesSiguiente = '0'+ mesSiguiente;
		}
				
		var fechaRango1 = '25' + '/' + mesActual + '/' + anioActual;
		var fechaRango2 = '05' + '/' + mesSiguiente + '/' + anioActual;
		var fechaIngresada = document.GenArchSalidaIntForm.txt_FecSesion.value;
		
		/*if((validaFecCotaMinima(fechaRango1) == false) || (validaFecCotaMaxima(fechaRango2) == false)){
			alert("La fecha de sesión ingresada no corresponde al periodo de cierre de mes.")
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			document.GenArchSalidaIntForm.txt_FecInicio.value = "";
			document.GenArchSalidaIntForm.txt_FecCorte.value = "";			
			return false;
		}*/
		
		asignaFechaIniFechaCorteDWR();
	}	

	/*function validaFecCotaMinima(fecha)
	{
		var fechaDeSiste = document.GenArchSalidaIntForm.txt_FecSesion.value;
		
		if(Comparar_Fecha(fechaDeSiste,fecha)){
			return true;
		}
		return false;	
	}*/
		
	/*function validaFecCotaMaxima(fecha)
	{
		var fechaDeSistem = document.GenArchSalidaIntForm.txt_FecSesion.value;
		
		if(Comparar_Fecha(fecha, fechaDeSistem))
		{
			return true;
		}
		return false;			
	}*/
	
	function asignaFechaIniFechaCorteDWR()
	{
		var fSistema = "<%=session.getAttribute("FechaSistema")%>";
		//var dia = parseInt(fSistema.substring(0,2));
		//var mes = parseInt(fSistema.substring(3,5));
		var dia = fSistema.substring(0,2);
		var mes = fSistema.substring(3,5);
		var anio = fSistema.substring(6,10);
		var anioComp = fSistema.substring(6,10); //JLGN 31-01-2013
		
		/*alert("fSistema: "+ fSistema);
		alert("dia: "+ dia);
		alert("mes: "+ mes);
		alert("anio: "+ anio);
		alert("anioComp: "+ anioComp);
		alert("fSistema.substring(3,5)"+ fSistema.substring(3,5))*/
		//Antes se obtenia el mes anterior a la fecha actual
		//Segun RAC19098 la Fecha de Inicio y la fecha de corte
		//deben pertenecer al mes actual
		
		/*var mesAnterior = mes - 1;
		var mesAnteriorComp = mes - 1;*/
		var mesAnterior = mes;
		var mesAnteriorComp = mes;
		
		/*if(mesAnterior == 0){
			mesAnterior = 12;
			
			//Inicio JLGN 31-01-2013
			mesAnteriorComp = 12;
			anioComp = anioComp -1;
			//Fin JLGN 31-01-2013
		}*/
			
		/*if(mesAnterior < 10){
			mesAnterior = '0'+ mesAnterior;
		}*/
		/*
		if(dia >= 06){
				SalidaIntercajaDWR.obtenerPrimerYUltimoDiaMes(fSistema, function(data){
				var fechaInicial = null;
				var fechaFinal = null;
				
				fechaInicial = data.fechaConPimerDiaMes;
				fechaFinal = data.fechaConUltimoDiaMes;
				
				document.GenArchSalidaIntForm.txt_FecInicio.value = fechaInicial;
				document.GenArchSalidaIntForm.txt_FecCorte.value = fechaFinal;	
			});	
			
		}else{
		*/
			
			/* 	Los meses 1,3,5,7,8,10,12 siempre tienen 31 días
 	            Los meses 4,6,9,11 siempre tienen 30 días
	            El único problema es el mes de febrero dependiendo del año puede tener 28 o 29 días
	    	*/
	   		//if((dia >= 25 && dia <= 31) || (dia >= 01 && dia <= 05)){
	   		
				if( (mesAnteriorComp == 01) || (mesAnteriorComp == 03) || (mesAnteriorComp == 05) || (mesAnteriorComp == 07) || (mesAnteriorComp == 08) || (mesAnteriorComp == 10) || (mesAnteriorComp == 12) ){ 
			
					//document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anio;
					//document.GenArchSalidaIntForm.txt_FecCorte.value = "31/" + mesAnterior + "/" + anio;
					document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anioComp;
					document.GenArchSalidaIntForm.txt_FecCorte.value = "31/" + mesAnterior + "/" + anioComp;
		    	
		    	}else{
		    		 if( (mesAnteriorComp == 04) || (mesAnteriorComp == 06) || (mesAnteriorComp == 09) || (mesAnteriorComp == 11) ){ 

						//document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anio;
						//document.GenArchSalidaIntForm.txt_FecCorte.value = "30/" + mesAnterior + "/" + anio;
						document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anioComp;
						document.GenArchSalidaIntForm.txt_FecCorte.value = "30/" + mesAnterior + "/" + anioComp;
						

		 			}else{
		 				 if( mesAnteriorComp == 02 ){
		 				 
		    	    		if( (anio % 4 == 0) && (anio % 100 != 0) || (anio % 400 == 0) ){
								
								//document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anio;
								//document.GenArchSalidaIntForm.txt_FecCorte.value = "29/" + mesAnterior + "/" + anio;
								document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anioComp;
								document.GenArchSalidaIntForm.txt_FecCorte.value = "29/" + mesAnterior + "/" + anioComp;
								
		           		
		        			}else{
					
								//document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anio;
								//document.GenArchSalidaIntForm.txt_FecCorte.value = "28/" + mesAnterior + "/" + anio;
								document.GenArchSalidaIntForm.txt_FecInicio.value = "01/" + mesAnterior + "/" + anioComp;
								document.GenArchSalidaIntForm.txt_FecCorte.value = "28/" + mesAnterior + "/" + anioComp;	
		    				}        	
						}
					}		
				}
			//}
		 //}
		 fechaCorteMax = document.GenArchSalidaIntForm.txt_FecCorte.value;
	}

	function revisa_FechaSesion (fechaSes){
		var fSistema = "<%=session.getAttribute("FechaSistema")%>";
		var dia = fSistema.substring(0,2);
		var mes = fSistema.substring(3,5);
		var anio = fSistema.substring(6,10);
		var diaSes= fechaSes.substring(0,2);
		var mesSes = fechaSes.substring(3,5);
		var anioSes = fechaSes.substring(6,10);
		
		if (fechaSes.substring(0,1) == "0"){
			diaSes= fechaSes.substring(1,2);
		}
		
		//if((parseInt(mes) == parseInt(mesSes)) && (parseInt(anio) == parseInt(anioSes))){
		if((parseInt(mes,10) == parseInt(mesSes,10)) && (parseInt(anio,10) == parseInt(anioSes,10))){
			//if (parseInt(diaSes) >= 1  && parseInt(diaSes) <= finMesActual ){
			if (parseInt(diaSes,10) >= 1  && parseInt(diaSes,10) <= finMesActual ){
				return true;
			}
		}
		return false;
	}
	
	/*Funcion que valida la fecha de sesion.*/
	/*function validaFechaSesion()
	{
		var finMes;
		var inicioMes = 01;
		
		//variables de rangos de fecha
		var rangoUno = document.GenArchSalidaIntForm.rangoIntercajaUno.value;//5, 02
		var rangoDos = document.GenArchSalidaIntForm.rangoIntercajaDos.value;//25, 10
		var rangoTres = document.GenArchSalidaIntForm.rangoIntercajaTres.value;//25 (solo para fecha de sesion, debe estar entre fecha actual y 25 del mes.
		
				
		//Fecha de sistema.
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";		
		var diaSistema = fechaSistema.substring(0,2);
		var mesSistema = fechaSistema.substring(3,5);
		var anioSistema = fechaSistema.substring(6,10);
				
		//se forma fecha de inicio para comparar con fecha de sesion
		var fechaIni = fechaSistema;
		var mesCorteInf = fechaSistema.substring(3,5);
		var anioCorteInf = fechaSistema.substring(6,10); 
		var fechaInicio = rangoDos + "/" + (mesCorteInf - 1) + "/" + anioCorteInf; 
		
		//Se forma fecha de corte para comparar con fecha de sesion
		var mesCorteSup = mesCorteInf;
		var anioCorteSup = anioCorteSup;
		var anioCorteSup = rangoUno + "/" + mesCorteSup + "/" + anioCorteSup;
		
		var mes = parseInt(mesSistema);
		var anio = parseInt(anioSistema);
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
			finMes = 31;
		}
		if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
			finMes = 30;
		}
		if((anio % 4 == 0) && (anio % 100 != 0) && (anio % 400 == 0)){
			finMes = 29;
		}else{
			finMes = 28;
		}					
		
		//Validacion del rango para Intercaja.
		//if((parseInt(mesSesion) == parseInt(mesSistema)) && (parseInt(anioSesion) == parseInt(anioSistema))){
		//	if(((parseInt(diaSistema) > rangoDos ) && (parseInt(diaSistema) < finMes ) && (parseInt(mesCorteInf) == parseInt(mesSistema))) || 
		//	   ((parseInt(diaSistema) >= inicioMes) && (parseInt(diaSistema) <= rangoDos) && (parseInt(mesCorteSup) == parseInt(mesSistema)))){
		//
		//		return true;
		//
		//	}else{
		//		alert("La fecha de sesion ingresada no corresponde al periodo de cierre de mes.");
		//		return false;
		//	}
		//}
		if((parseInt(mesSesion) == parseInt(mesSistema)) && (parseInt(anioSesion) == parseInt(anioSistema))){
			if(((parseInt(diaSistema) > 10 ) && (parseInt(diaSistema) < 31 ) && (parseInt(mesCorteInf) == parseInt(mesSistema))) || 
			   ((parseInt(diaSistema) >= 01) && (parseInt(diaSistema) <= 02) && (parseInt(mesCorteSup) == parseInt(mesSistema)))){
				alert("Puedes realizar el proceso de intercaja");
				cargarFechaInferior();
				cargarFechaSuperior();
				return true;

			}else{
				alert("La fecha de sistema no corresponde al periodo de cierre de mes.");
				return false;
			}
		}
	
	}*/

	/*funcion que verifica si el anio es bisiesto o no*/
	/*function anioBisiesto(){
		var fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
		var anioInicio = parseInt(fechaInicio.substring(6,10));
	
		if ((anioInicio%4 == 0) && ((anioInicio%100 != 0) || (anioInicio%400 == 0))){
			return true;
		}else{
			return false;
		}
	}*/	
	/*FIN VALIDACION FECHA DE SESION*/
	
	/*Funcion que realiza una validacion de las fechas, en donde la fecha de corte nunca debe ser menor o igual que la fecha de inicio*/
	function validaFechas()
	{
		var fechaIni = document.GenArchSalidaIntForm.txt_FecInicio.value;
		var fechaCor = document.GenArchSalidaIntForm.txt_FecCorte.value;
		
		var fechaSes = document.GenArchSalidaIntForm.txt_FecSesion.value;
		var fechaSys = document.GenArchSalidaIntForm.txt_Fecha.value;
		/* Modificado por ANA 20121002
		var	fechaDes = document.GenArchSalidaIntForm.txt_FechaInferior.value;
		var fechaMax = document.GenArchSalidaIntForm.txt_FecMaxSesion.value;
		*/
		if(!Comparar_Fecha(fechaCor, fechaIni))
		{
			alert("La fecha de inicio no puede ser posterior a la fecha de corte.")
			return false;
		}
		
		if(!Comparar_Fecha(fechaCorteMax, fechaCor))
		{
			alert("La fecha de corte no puede ser posterior al último dia del mes anterior a la Fecha Actual.")
			return false;
		}
		
		if (!revisa_FechaSesion (fechaSes))
		{
			alert("La Fecha de Sesión no puede ser anterior al primer día del Mes Actual ni posterior al último día del Mes Actual.")
			return false;
		}
		/* Modificado por ANA 20121002
		if(!Comparar_Fecha(fechaSes, fechaSys))
		{
			alert("La Fecha de Sesión debe ser mayor o igual a la Fecha de hoy.")
			return false;
		}
		
		if(!Comparar_Fecha(fechaSes, fechaDes))
		{
			alert("La fecha de inicio del período no puede ser posterior a la fecha de Sesión.")
			return false;
		}
		
		if(!Comparar_Fecha(fechaMax, fechaSes))
		{
			alert("La Fecha de Sesión no puede ser posterior a la Fecha Máxima de Sesión.")
			return false;
		}
		*/
		return true;
	}
	
	/*Funcion que limpia los campos del formulario.*/
	function limpiarCampos(){
		
		document.GenArchSalidaIntForm.txt_NumSesion.value = "";
		document.GenArchSalidaIntForm.txt_FecSesion.value = "";
		//document.GenArchSalidaIntForm.txt_FecInicio.value = "";
		//document.GenArchSalidaIntForm.txt_FecCorte.value = "";
	}
	
	/*funcion que limpia las fechas en caso de que ocurra un error en la validacion de las mismas.*/
	/*function limpiarFechas(){
		document.GenArchSalidaIntForm.txt_FecInicio.value = "";
		document.GenArchSalidaIntForm.txt_FecCorte.value = "";
	}*/
	
	/*Funcion que realiza el envío del mail con el archivo de salida adjunto.*/
	function enviarMail()
	{
		var archivo = "";
		var afiNew;
		var desafi;
		var cambio;
		var fallecido;
		var resp = null;
		
		fechaEnvio = new Date();
		diaEnvio = fechaEnvio.getDate();
		mesEnvio = fechaEnvio.getMonth();
		anioEnvio = fechaEnvio.getFullYear();
		
		var nombreMes = "";
		//var numero = parseInt(mesEnvio);
		var numero = parseInt(mesEnvio,10);
		
		//if((parseInt(diaEnvio) >= 01 && parseInt(diaEnvio) <= 05)){
		if((parseInt(diaEnvio,10) >= 01 && parseInt(diaEnvio,10) <= 05)){
			
			//numero = parseInt(mesEnvio) - 1;
			numero = parseInt(mesEnvio,10) - 1;
			
			if(numero < 0){//por si estamos en enero, el mes anterior debe ser diciembre.
				
				numero = 11;
			}
		}
		
		switch(numero){
			case 0:
				nombreMes = "Enero";
				break;
			case 1:
				nombreMes = "Febrero";
				break;
			case 2:
				nombreMes = "Marzo";
				break;
			case 3:
				nombreMes = "Abril";
				break;
			case 4:
				nombreMes = "Mayo";
				break;
			case 5:
				nombreMes = "Junio";
				break;
			case 6:
				nombreMes = "Julio";
				break;
			case 7:
				nombreMes = "Agosto";
				break;
			case 8:
				nombreMes = "Septiembre";
				break;
			case 9:
				nombreMes = "Octubre";
				break;
			case 10:
				nombreMes = "Noviembre";
				break;
			case 11:
				nombreMes = "Diciembre";
				break;
			default:
				nombreMes = "";
																				
		}
		/*Esto es para firmar quien envia el correo.*/
		var nombreAnalista = "<%=session.getAttribute("NombreAnalista")%>";
		var apellidoPaternoAnalista = "<%=session.getAttribute("ApePatAnalista")%>";
		var apellidoMaternoAnalista = "<%=session.getAttribute("ApeMatAnalista")%>";
		var nombreDelArchivo = document.GenArchSalidaIntForm.nombreArchivoIntercaja.value;

		var nombreCompleto = nombreAnalista + " " + apellidoPaternoAnalista + " " + apellidoMaternoAnalista;
		
		if(datos.length != 0){
		
			archivo = document.GenArchSalidaIntForm.archivo.value;
			
		}
		
		afiNew = document.GenArchSalidaIntForm.afiliadosNuevos.value;
		desafi = document.GenArchSalidaIntForm.desafiliados.value;
		cambio = document.GenArchSalidaIntForm.cambioCAAF.value;
		fallecido =	document.GenArchSalidaIntForm.fallecidos.value;
				
		var fechaIni = document.GenArchSalidaIntForm.txt_FecInicio.value;
		var fechaFin = document.GenArchSalidaIntForm.txt_FecCorte.value;
		
		DWREngine.setAsync(false);
		
		SalidaIntercajaDWR.enviarMailIntercaja(archivo, fechaIni, fechaFin, afiNew, desafi, cambio, fallecido, nombreMes, anioEnvio, nombreCompleto, function(data)
		{
			resp = data;
			
			if (resp == "OK"){
				
				/*Aqui implementar DWR que updatea tabla*/
				SalidaIntercajaDWR.updateStatusSendMail(nombreDelArchivo, function(data)
				{
					var sal = null;
					sal = data.codResultado;
					
					if(sal != 0){
						alert("Ocurrió un error en el sistema.");
					}else{
						cantEnviosMail = cantEnviosMail + 1;
						alert("El correo se ha enviado exitosamente.");
						//enviarArchivoHistoricoFTP();
						transferirArchivoToAS400();
						eliminarArchivoDesdeServer();
						limpiarCampos();
					}	
				});
				
			}else{
				alert("Error, no se ha podido enviar el correo.");
			}
		});	
		DWREngine.setAsync(true);
	}
	
	/*Funcion que valida el envío del mail.*/
	function validaEnvioMail(){
	
		if (cantEnviosMail > 0){
			var respuesta = confirm("Ya se ha enviado el correo. ¿Está seguro que desea enviarlo nuevamente?");
				
			if( respuesta == true){
				enviarMail();
				esconderDiv();
				//enviarArchivoHistoricoFTP();
				//transferirArchivoToAS400();//TODO SToro Se debe comentar?
				//eliminarArchivoDesdeServer();//TODO SToro Se debe comentar?
				limpiarCampos();
			}
		}else{
			enviarMail();
			esconderDiv();
			//enviarArchivoHistoricoFTP();
			//transferirArchivoToAS400();//TODO SToro Se debe comentar?
			//eliminarArchivoDesdeServer();//TODO SToro Se debe comentar?
			limpiarCampos();

		}
	}
	
	/*Funcion que realiza el insert a las tablas de log de intercaja.*/
	function insertaLogIntercajaDWR()
	{
		var numberSession = document.GenArchSalidaIntForm.txt_NumSesion.value;
		var fechaSession = document.GenArchSalidaIntForm.txt_FecSesion.value;
		var fechaInicioSession = document.GenArchSalidaIntForm.txt_FecInicio.value;
		var fechaCorteSession = document.GenArchSalidaIntForm.txt_FecCorte.value;
		var nombreArchivo = document.GenArchSalidaIntForm.nombreArchivoIntercaja.value;
		var fechaProceso = "<%=session.getAttribute("FechaSistema")%>";
		var rutAnalista = "<%=session.getAttribute("IDAnalista")%>";
		var afiliadNews = document.GenArchSalidaIntForm.afiliadosNuevos.value;
		var desafil = document.GenArchSalidaIntForm.desafiliados.value;
		var cambioCcaf = document.GenArchSalidaIntForm.cambioCAAF.value;
		var fallecid =	document.GenArchSalidaIntForm.fallecidos.value;		
		var desafiliados1 = document.GenArchSalidaIntForm.desafiliadosUno.value;
		var desafiliados2 = document.GenArchSalidaIntForm.desafiliadosDos.value;
				
		SalidaIntercajaDWR.insertaRegistroLogIntercaja(nombreArchivo,
														numberSession, 
														fechaSession, 
														fechaInicioSession, 
														fechaCorteSession, 
														fechaProceso, 
														rutAnalista, 
														afiliadNews,
														desafil,
														cambioCcaf,
														fallecid,
														desafiliados1,
														desafiliados2,
														function(data){}
														);
	} 
	
	/*Funcion que verifica que no se duplique el numero de sesion en la tabla sesiondirectorio.*/
	function verificaNumeroSesionDWR()
	{
		var numSesion = document.GenArchSalidaIntForm.txt_NumSesion.value;
		
		SalidaIntercajaDWR.verificaNumeroSesion(numSesion, function(data){
			
			var codigo = null;
			codigo = data.codResultado;

			if(codigo != 0){
				alert("El número de la sesión ya existe");
				document.GenArchSalidaIntForm.txt_NumSesion.value = "";
			}
		});
	}
	
	/*Implementacion Grilla con estadistica antes de enviar mail.*/
	/*funcion que carga los datos en la grilla*/
	var cantidadRegistrosPorPagina = 1;
	function cargaDatosEnGrilla()
	{
		var contenidoTabla = "";
		
		for(var i=0;i<4;i++)
		{
			if( i < cantidadRegistrosPorPagina )
				
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = obtenerHeaderGrilla() + contenidoTabla + obtenerHeaderCancelarGrilla() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	
	/*funcion que carga la grilla*/
	function cargarGrilla()
	{
		cargaDatosEnGrilla();
	}															
	
	/*funcion que obtiene la fila de la tabla*/
	function obtenerFilaTabla(dato)
	{
		var afiliacionesNews = document.GenArchSalidaIntForm.afiliadosNuevos.value;
		var desaf = document.GenArchSalidaIntForm.desafiliados.value;
		var cambioCaf = document.GenArchSalidaIntForm.cambioCAAF.value;
		var fallec =	document.GenArchSalidaIntForm.fallecidos.value;
		var texto =  " <tr> "+
							"<td class='texto' align='center'>" + "<b>Nuevas Afiliaciones</b>" + "</td>"+
							"<td class='texto' align='center'>"+ afiliacionesNews +"</td>"+
	   				 "</tr>"+
	   				 "<tr> "+
							"<td class='texto' align='center'>" + "<b>Desafiliados</b>" + "</td>"+
							"<td class='texto' align='center'>"+ desaf +"</td>"+
	   				 "</tr>"+
	   				 "<tr> "+
							"<td class='texto' align='center'>" + "<b>Cambio CCAF</b>" + "</td>"+
							"<td class='texto' align='center'>"+ cambioCaf +"</td>"+
	   				 "</tr>"+
	   				 "<tr> "+
							"<td class='texto' align='center'>" + "<b>Fallecidos</b>" + "</td>"+
							"<td class='texto' align='center'>"+ fallec +"</td>"+
	   				 "</tr>";		 
		return texto;
	}
		
	function obtenerHeaderCancelarGrilla()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+	
					'<td colspan="3" width="100%" style="font-size:11px;">Presione "enviar" para mandar el correo.</td>'+				
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						//'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="esconderDiv();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Enviar" id="btn_Enviar" class="btn_limp" value="Enviar" onclick="validaEnvioMail();esconderDiv();"/>'+
					'</td>'+
				'</tr>';
				
	}
	
	function obtenerHeaderGrilla(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%"><p align="center"><b>Se ha generado con éxito el archivo de cierre de mes</b></p>' + 
					'<p align="center">Resumen de movimiento de afiliados<br>durante el periodo de cierre</p></td>' +
				'</tr>'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Movimiento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cantidad</td>'+
				'</tr>';
	}
	
	function esconderDiv()
	{
		var botonEnviar = null;
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		document.GenArchSalidaIntForm.txt_NumSesion.value = "";	
		//document.getElementById("btnEnviar").style.visibility = "visible";
	}	
	/*Fin implementacion grilla con estadisticas.*/
	
	/*Funcion dwr que obtiene el rango para intercaja, para ser usado en la generacion y carga de las fechas inferior y superior.*/
	function obtenerRangoParaIntercaja()
	{
		DWREngine.setAsync(false);
		
		SalidaIntercajaDWR.obtenerRangoIntercaja(function(data){
			var resp = null;
			
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.GenArchSalidaIntForm.rangoIntercajaUno.value = data.rangoUno;
				document.GenArchSalidaIntForm.rangoIntercajaDos.value = data.rangoDos;
				document.GenArchSalidaIntForm.rangoIntercajaTres.value = data.rangoTres;
				
			}else{
			
				alert("Ocurrió un problema al obtener el rango de Intercaja.");
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	function enviarArchivoHistoricoFTP()
	{
		DWREngine.setAsync(false);
		
		var fileName = document.GenArchSalidaIntForm.nombreArchivoIntercaja.value;
		var source = "";
		
		if(datos.length != 0){
			source = document.GenArchSalidaIntForm.archivo.value;//ruta del archivo que será transferido a FTP.
		}else{
			return false;
		}
		
		SalidaIntercajaDWR.enviarArchivoHistoricoAFTP(fileName, source, function(data){
			var resp = null;
			resp = data;
			
			if(resp == "Ok"){
				alert("Él archivo fue transferido a la carpeta de historicos.");
			}else{
				alert("Error, el archivo no se ha podido transferir.");
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	function eliminarArchivoDesdeServer(){
	
	/*Antes de eliminar el archivo, se debe verificar que el correo se ha enviado.*/
		DWREngine.setAsync(false);
		
		var nombreArchivoEnviado = document.GenArchSalidaIntForm.nombreArchivoIntercaja.value;
		
		/*SalidaIntercajaDWR.verificarFlagCorreo(nombreArchivoEnviado, function(data){
			var flagCorreo = null;
			flagCorreo = data.codRespuesta;
			
			if(flagCorreo == 3){ 
			*/	SalidaIntercajaDWR.eliminarArchivoServer(function(data){
					var resp = null;
					resp = data;
					
					if(resp == 0){
						//alert("Archivo fue eliminado satisfactoriamente del servidor.");
					}else{
						alert("Archivo no se ha podido eliminar.");
					}		
				});
			/*}else{
				alert("No se puede eliminar el archivo, debido a que no se ha enviado el correo.");
			}
		});*/
		DWREngine.setAsync(true);	
	}
	
	/*Cuando no se genera el archivo (cuando no hay data), se muestra otra pantalla resumen, sin boton enviar.*/
	
	/*Generacion de la cabecera informativa.*/
	function obtenerHeaderGrillaVacio(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
					'<p align="center"><b>No se ha generado el archivo de cierre de mes.<br>' + 
					'No existen movimientos de afiliados durante el periodo de cierre</b></p>'+
					'<p align="center">Resumen de movimiento de afiliados <br> durante el periodo de cierre</p></td>'+
				'</tr>'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Movimiento</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cantidad</td>'+
				'</tr>';
	}
	
	/*Generacion del cuerpo de pantalla informativa con estadisticas.*/
	function obtenerFilaTablaVacio(dato)
	{
		var afiliacionesNews = document.GenArchSalidaIntForm.afiliadosNuevos.value;
		var desaf = document.GenArchSalidaIntForm.desafiliados.value;
		var cambioCaf = document.GenArchSalidaIntForm.cambioCAAF.value;
		var fallec = document.GenArchSalidaIntForm.fallecidos.value;
		var texto =  " <tr> "+
							"<td class='texto' align='center'>" + "<b>Nuevas Afiliaciones</b>" + "</td>"+
							"<td class='texto' align='center'>"+ afiliacionesNews +"</td>"+
	   				 "</tr>"+
	   				 "<tr> "+
							"<td class='texto' align='center'>" + "<b>Desafiliados</b>" + "</td>"+
							"<td class='texto' align='center'>"+ desaf +"</td>"+
	   				 "</tr>"+
	   				 "<tr> "+
							"<td class='texto' align='center'>" + "<b>Cambio CCAF</b>" + "</td>"+
							"<td class='texto' align='center'>"+ cambioCaf +"</td>"+
	   				 "</tr>"+
	   				 "<tr> "+
							"<td class='texto' align='center'>" + "<b>Fallecidos</b>" + "</td>"+
							"<td class='texto' align='center'>"+ fallec +"</td>"+
	   				 "</tr>";		 
		return texto;
	}
	
	/*Generacion de cabecera de finalizacion de pantalla informativa.*/
	function obtenerHeaderCancelarGrillaVacio()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+	
					'<td colspan="3" width="100%" style="font-size:11px;">Presione "Aceptar" para volver al formulario.</td>'+				
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Aceptar" onclick="esconderDivVacio();"/>'+
					'</td>'+
				'</tr>';
	}
	
	/*Esconder div generado no activando boton envio correo.*/
	function esconderDivVacio()
	{
		var botonEnviar = null;
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		document.GenArchSalidaIntForm.txt_NumSesion.value = "";	
	}

	/*Funcion que construye cuadro informativo cuando el archivo viene vacio.*/
	var cantidadRegistrosPorPaginaVacio = 1;
	function cargaDatosEnGrillaVacio()
	{
		var contenidoTablaVacio = "";
		
		for(var i=0;i<4;i++)
		{
			if( i < cantidadRegistrosPorPaginaVacio )
				
				contenidoTablaVacio = contenidoTablaVacio + obtenerFilaTablaVacio(datos[i]);
		}
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = obtenerHeaderGrillaVacio() + contenidoTablaVacio + obtenerHeaderCancelarGrillaVacio() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	/*Fin construccion para archivo vacio.*/
	
	function validaFechaPeriodoIntercaja()
	{
		var finMes;
		var inicioMes = 01;
		
		/*variables de rangos de fecha*/
		var rangoUno = document.GenArchSalidaIntForm.rangoIntercajaUno.value;//5, 02
		var rangoDos = document.GenArchSalidaIntForm.rangoIntercajaDos.value;//25, 10
		var rangoTres = document.GenArchSalidaIntForm.rangoIntercajaTres.value;//25 (solo para fecha de sesion, debe estar entre fecha actual y 25 del mes.
		
				
		/*Fecha de la cabecera del formulario.*/
		var fecha = document.GenArchSalidaIntForm.txt_Fecha.value;
		/*var diaFecha = parseInt(fecha.substring(0,2));
		var mesFecha = parseInt(fecha.substring(3,5));
		var anioFecha = parseInt(fecha.substring(6,10));*/
		var diaFecha = parseInt(fecha.substring(0,2),10);
		var mesFecha = parseInt(fecha.substring(3,5),10);
		var anioFecha = parseInt(fecha.substring(6,10),10);
		
		
		/*Fecha de sistema.*/
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";		
		var diaSistema = fechaSistema.substring(0,2);
		var mesSistema = fechaSistema.substring(3,5);
		var anioSistema = fechaSistema.substring(6,10);
				
		/*se forma fecha de inicio para comparar con fecha de sesion*/
		var fechaIni = fechaSistema;
		var mesCorteInf = fechaSistema.substring(3,5);
		var anioCorteInf = fechaSistema.substring(6,10); 
		var fechaInicio = rangoDos + "/" + (mesCorteInf - 1) + "/" + anioCorteInf; 
		
		/*Se forma fecha de corte para comparar con fecha de sesion*/
		var mesCorteSup = mesCorteInf;
		var anioCorteSup = anioCorteSup;
		var anioCorteSup = rangoUno + "/" + mesCorteSup + "/" + anioCorteSup;
		
		/*var mes = parseInt(mesSistema);
		var anio = parseInt(anioSistema);*/
		var mes = parseInt(mesSistema,10);
		var anio = parseInt(anioSistema,10);
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
			finMes = 31;
		}
		if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
			finMes = 30;
		}
		if (mes == 2){
			if((anio % 4 == 0) && (anio % 100 != 0) && (anio % 400 == 0)){
				finMes = 29;
			}else{
				finMes = 28;
			}					
		}
		
		finMesActual = finMes;
		/*Validacion del rango para Intercaja.*/
		/*if((parseInt(mesSesion) == parseInt(mesSistema)) && (parseInt(anioSesion) == parseInt(anioSistema))){
			if(((parseInt(diaSistema) > rangoDos ) && (parseInt(diaSistema) < finMes ) && (parseInt(mesCorteInf) == parseInt(mesSistema))) || 
			   ((parseInt(diaSistema) >= inicioMes) && (parseInt(diaSistema) <= rangoDos) && (parseInt(mesCorteSup) == parseInt(mesSistema)))){

				return true;

			}else{
				alert("La fecha de sesion ingresada no corresponde al periodo de cierre de mes.");
				return false;
			}
		}
		if((parseInt(mesFecha) == parseInt(mesSistema)) && (parseInt(anioFecha) == parseInt(anioSistema))){
			if(((parseInt(diaSistema) > 10 ) && (parseInt(diaSistema) < 31 ) && (parseInt(mesCorteInf) == parseInt(mesSistema))) || 
			   ((parseInt(diaSistema) >= 01) && (parseInt(diaSistema) <= 02) && (parseInt(mesCorteSup) == parseInt(mesSistema)))){
				//alert("Puedes realizar el proceso de intercaja");
				asignaFechaIniFechaCorteDWR()

			}else{
				alert("La fecha actual no corresponde al periodo de cierre de mes.");
				return false;
			}
		}*/
		asignaFechaIniFechaCorteDWR()
		
		
		$('.datepick').each(function(){
		    $(this).datepicker({
			      showOn: "button",
			      buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			      buttonImageOnly: true,
			      buttonText: "Seleccionar fecha"
			});
		});
		
	}
	
	/*funciones que cargan a los campos de fecha inferior y fecha superior*/
	function cargarFechaInferior()
	{
		var rangoDia = document.GenArchSalidaIntForm.rangoIntercajaDos.value;
		 
		fechaInferiorActual = new Date();
		mesInferiorActual = fechaInferiorActual.getMonth()+1;//esto pasa porque el primer mes es 0
		anioInferiorActual = fechaInferiorActual.getFullYear();
		
		var fechaSistemaInf = "<%=session.getAttribute("FechaSistema")%>";
		var diaSistemaInf = fechaSistemaInf.substring(0,2);
		var mesSistemaInf = fechaSistemaInf.substring(3,5);
		var fechaRango1 = "";
		
		if(diaSistemaInf <= 05){
			var mesTempInf = fechaInferiorActual.getMonth();
			if(mesTempInf < 10){
				mesTempInf = '0'+ mesTempInf;
			}	
			fechaRango1 = rangoDia + '/' + mesTempInf + '/' + anioInferiorActual;
			document.GenArchSalidaIntForm.txt_FechaInferior.value = fechaRango1;
			
		}else{
			if(mesInferiorActual < 10){
				mesInferiorActual = '0'+ mesInferiorActual;
			}
						
			fechaRango1 = rangoDia + '/' + mesInferiorActual + '/' + anioInferiorActual;
			document.GenArchSalidaIntForm.txt_FechaInferior.value = fechaRango1;
		}
	}
	
	function cargarFechaSuperior()
	{
		var rangoDiaSup = document.GenArchSalidaIntForm.rangoIntercajaUno.value; 
		
		if(rangoDiaSup < 10){
			rangoDiaSup = '0' + rangoDiaSup;
		}
			
		fechaSuperiorActual = new Date();
		mesSuperiorActual = fechaSuperiorActual.getMonth() + 2;
		anioSuperiorActual = fechaSuperiorActual.getFullYear();

		var fechaSistemaSup = "<%=session.getAttribute("FechaSistema")%>";
		var diaSistemaSup = fechaSistemaSup.substring(0,2);
		var mesSistemaSup = fechaSistemaSup.substring(3,5);

		var fechaRango2 = "";
		
		if(diaSistemaSup <= 05){
			var mesTempSup = fechaSuperiorActual.getMonth() + 1;
			
			if(mesTempSup < 10){
				mesTempSup = '0' + mesTempSup;
			}
			
			fechaRango2 = rangoDiaSup + '/' + mesTempSup + '/' + anioSuperiorActual;
			document.GenArchSalidaIntForm.txt_FechaSuperior.value = fechaRango2;

		}else{
		
			if(mesSuperiorActual < 10){
				mesSuperiorActual = '0'+ mesSuperiorActual;
			}
			
			var fechaRango2 = rangoDiaSup + '/' + mesSuperiorActual + '/' + anioSuperiorActual;
			document.GenArchSalidaIntForm.txt_FechaSuperior.value = fechaRango2;
		}
	}
	
	/*VALIDACION DEL RANGO DE LA FECHA DE SESION
	* rango permitido, desde la fecha inferior hasta el 25 del mes siguiente.
	*/
	function validarRangoFechaDeSesion()
	{
		/*se obtiene el numero de sesion*/
		var numeroSesion = document.GenArchSalidaIntForm.txt_NumSesion.value;

		if(numeroSesion == "")
		{
			alert("Debe ingresar el número de sesión.");
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			return false;
		}
		
		/*var rangoDos = parseInt(document.GenArchSalidaIntForm.rangoIntercajaDos.value);
		var rangoTres = parseInt(document.GenArchSalidaIntForm.rangoIntercajaTres.value);*/
		var rangoDos = parseInt(document.GenArchSalidaIntForm.rangoIntercajaDos.value,10);
		var rangoTres = parseInt(document.GenArchSalidaIntForm.rangoIntercajaTres.value,10);
		
		/*variable que se lee del campo fecha de sesion*/
		var fechaSesion = document.GenArchSalidaIntForm.txt_FecSesion.value;
		//var diaSesion = parseInt(fechaSesion.substring(0,2));
		var diaSesion = parseInt(fechaSesion.substring(0,2),10);
		var mesSesion = fechaSesion.substring(3,5);
		//mesSesion = mesSesion.substring(1,1);
		//var anioSesion = parseInt(fechaSesion.substring(6,10));
		var anioSesion = parseInt(fechaSesion.substring(6,10),10);
		
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";		
		/*var diaSistema = parseInt(fechaSistema.substring(0,2));
		var mesSistema = parseInt(fechaSistema.substring(3,5));
		var anioSistema = parseInt(fechaSistema.substring(6,10));*/
		
		var diaSistema = parseInt(fechaSistema.substring(0,2),10);
		var mesSistema = parseInt(fechaSistema.substring(3,5),10);
		var anioSistema = parseInt(fechaSistema.substring(6,10),10);
		
		//var mesSesionSiguiente = parseInt(mesSesion);
		var mesSesionSiguiente = parseInt(mesSesion,10);
		var mesAnterior = mesSistema - 1;
		
		/*calcula fin de mes*/
		/*var mes = parseInt(mesSistema);
		var anio = parseInt(anioSistema);
		var inicioMes = parseInt("01");*/
		
		var mes = parseInt(mesSistema,10);
		var anio = parseInt(anioSistema,10);
		var inicioMes = parseInt("01",10);
		var mesSiguiente = mesSistema + 1;
		
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
			finMes = 31;
		}
		if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
			finMes = 30;
		}
		if((anio % 4 == 0) && (anio % 100 != 0) && (anio % 400 == 0)){
			finMes = 29;
		}else{
			finMes = 28;
		}
		
		if(anioSesion > anioSistema){
			alert("La Fecha de Sesion ingresada no debe ser mayor a la fecha máxima de sesión.");
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			return false;
		}
		//if(parseInt(mesSesion) < mesSistema){
		if(parseInt(mesSesion,10) < mesSistema){
			alert("La Fecha de Sesion ingresada se encuentra fuera del periodo de cierre de mes.");
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			return false;
		}
		//if((diaSesion < rangoDos) && (parseInt(mesSesion) == mesSistema)){
		if((diaSesion < rangoDos) && (parseInt(mesSesion,10) == mesSistema)){
			alert("La Fecha de Sesion ingresada se encuentra fuera del periodo de cierre de mes.");
			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
			return false;
		}else{
			return true;
		}	
		//if((parseInt(mesSesion) == mesSistema)){
		if((parseInt(mesSesion,10) == mesSistema)){
			if((diaSesion >= rangoDos) && (diaSesion <= finMes)){
				if(diaSesion <= diaSistema){
					alert("La Fecha de Sesion ingresada no debe ser menor a la fecha actual.");
					document.GenArchSalidaIntForm.txt_FecSesion.value = "";
					return false;
				}else	
					return true;

			}else{
				alert("La Fecha de Sesion ingresada no debe ser menor a la fecha actual.");
				document.GenArchSalidaIntForm.txt_FecSesion.value = "";
				return false;
			}	
		}
		
		//alert("mes siguiente: " + mesSesionSiguiente);
		if((mesSesionSiguiente == mesSiguiente)){
			
				if((diaSesion < rangoTres)){
					//alert("Fecha de sesion dentro del rango permitido");
					return true;
				}else{  		
		 			alert("La Fecha de Sesion ingresada no debe ser mayor a la fecha máxima de sesión.");
		 			document.GenArchSalidaIntForm.txt_FecSesion.value = "";
		 			return false;
		 		}
		 	}	
	 }
	 				
	/*FIN VALIDACION FECHA DE SESION*/	
	
	/* Nueva funcion: cargarMaxFechaSesion();
	 * Carga la maxima fecha de sesion que puede tomar. Se carga al iniciar la pantalla.
	 * es solo campo informativo.
	 * Se genera a partir de la fecha de sistema, y siempre es hasta el 25 del mes siguiente al proceso de generación del archivo de salida.
	*/
	function cargarMaxFechaSesion()
	{
		var mesSiguiente = 0;
		var fechaMaxSesion = "";
		var rangoTres = document.GenArchSalidaIntForm.rangoIntercajaTres.value;//25
		var fechaActual = "<%=session.getAttribute("FechaSistema")%>";
		//var fechaActual = "03/12/2012";
		var mesActual = fechaActual.substring(3,5);
		var anioActual = fechaActual.substring(6,10);
		
		if(parseInt(mesActual,10) == 12){
			mesSiguiente = 1;
			anioActual = parseInt(anioActual,10) + 1;
		}else{
			mesSiguiente = parseInt(mesActual,10) + 1;
		}
		
		if(parseInt(mesSiguiente,10) < 10){
			mesSiguiente = '0' + mesSiguiente;
		}
				
		fechaMaxSesion = rangoTres + "/" + mesSiguiente + "/" + anioActual;
		
		document.GenArchSalidaIntForm.txt_FecMaxSesion.value = fechaMaxSesion;					
	}
	
	/*Funcion que trasnfiere archivo a máquina AS400
	 * esta funcion es utilizada para el envio del archivo historico.
	 */
	function transferirArchivoToAS400()
	{
		var rutaArchivoWas = document.GenArchSalidaIntForm.archivo.value;
		var nombreArchivo = document.GenArchSalidaIntForm.nombreArchivoIntercaja.value;
		var tipoArchivo = document.GenArchSalidaIntForm.tipoArchivo.value;//7
		
		DWREngine.setAsync(false);
		
		SalidaIntercajaDWR.transferirFileToAS400(rutaArchivoWas, nombreArchivo, tipoArchivo, function(data){
			var resp = null;
			resp = data;
			
			if(resp == "Ok"){
				alert("Archivo transferido a registro histórico exitosamente.");
			}else{
				alert("Archivo no se ha transferido a registro histórico.");
			}
		});
		
		DWREngine.setAsync(true);
	}
	
</script>
</head>
<!-- onload="DesbloqueaCampos();cargarFechaInferior();cargarFechaSuperior();cargarMaxFechaSesion();validaFechaPeriodoIntercaja();" -->
<body onload="DesbloqueaCampos();validaFechaPeriodoIntercaja();">
<html:form action="/genArchSalida.do" >

  	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="archivo" value="">
	<input type="hidden" name="nombreArchivoIntercaja" value="">
	<input type="hidden" name="afiliadosNuevos" value="">
	<input type="hidden" name="desafiliados" value="">
	<input type="hidden" name="cambioCAAF" value="">
	<input type="hidden" name="fallecidos" value="">
	<input type="hidden" name="desafiliadosUno" value="">
	<input type="hidden" name="desafiliadosDos" value="">
	<input type="hidden" name="rangoIntercajaUno" value="">
	<input type="hidden" name="rangoIntercajaDos" value="">
	<input type="hidden" name="rangoIntercajaTres" value="">
	<input type="hidden" name="tipoArchivo" value="7">			
	
  <div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 520px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
 	<div id="datosEstadisticas" style="display: none; position: absolute; width: 450px; margin-top: 140px; margin-left: 300px; background-color: #F2F2F2">
	</div>
  </div>
  	 
  <div id="caja_registro">	
	  <table width="970">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>GENERACION DE ARCHIVO SALIDA HACIA INTERCAJA</p1></strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario" value='<%=session.getAttribute("IDAnalista")%>' disabled="true" size="10"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" size="10" value='<%=session.getAttribute("FechaSistema")%>' id="txt_Fecha" disabled="false"/></td>
				</tr>
			</table>
		   </td>	
		</tr>
		<!--
		<tr>
			<td>
				<p><p2>1. Periodo de cierre de mes en curso válido.</p2>
				<p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td>
							<strong>Periodo desde el </strong>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="txt_FechaInferior" id="txt_FechaInferior" maxlength="12" size="10" disabled="true" onChange="cargarFechaInferior();"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<strong>hasta el </strong>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="txt_FechaSuperior" id="txt_FechaSuperior" maxlength="12" size="10" disabled="true" onChange="cargarFechaSuperior();">
						</td>
						<td width="16%">							
						</td>
						<td></td>
						<td colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
		-->
		<tr>
			<td>
			<p><p2></p>
			<br>
			<p>1. Datos de Directorio.<p2></p>
			<p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>N° Sesión *</strong></td>
					<td width="16%">
						<input type="text" name="txt_NumSesion" id="txt_NumSesion" maxlength="12" 
						onkeypress="Upper(this,'N')" onChange="verificaNumeroSesionDWR();"/>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<!--
				<tr>
					<td width="16%"><strong>Fecha Máxima de Sesión</strong></td>
					<td width="16%">
						<input type="text" name="txt_FecMaxSesion" id="txt_FecMaxSesion" size="15" disabled="true" onchange="cargarMaxFechaSesion();"/>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				-->
				<tr>
					<td width="16%"><strong>Fecha Sesión *</strong></td>
					<td width="16%">
						<!-- <input type="text" name="txt_FecSesion" id="txt_FecSesion" size="15" onchange="valFecSesion();"/> -->
						<input type="text" name="txt_FecSesion" id="txt_FecSesion" class="datepick" size="15" disabled="true"/>
						<!-- onClick="ShowCalendarFor(txt_FecSesion);validarRangoFechaDeSesion();" -->
<!--						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecSesion);" >-->
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
			<td>
			<p><p2><br>
			2. Periodo de captura de movimientos de Afiliados.</p2>
			<p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Fecha Inicio</strong></td>
					<td width="16%">
						<input type="text" name="txt_FecInicio" id="txt_FecInicio" disabled="true" size="15" />
						<!-- <IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecInicio)" > -->
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Fecha Corte</strong></td>
					<td width="16%">
						<input type="text" name="txt_FecCorte" id="txt_FecCorte" class="datepick" disabled="true" size="15" />
<!--						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecCorte)" > -->
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
		<td style="font-size:11px;">(*campos obligatorios)</td>
		</tr>
		<tr>
			<td height="37" align="right">
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"	value="Cancelar" onClick="enviaFormulario(1);" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_limpiar" id="btn_limpiar" class="btn_limp" value="Limpiar" onClick="limpiarCampos();" /> 
				&nbsp;&nbsp;&nbsp; 
				<input type="button" name="btn_Generar" id="btn_Generar" class="btn_limp" value="Generar" onClick="consultaSalidaAfiliadosDWR();" />
				<!-- &nbsp;&nbsp;&nbsp; 
				<input type="button" name="btn_Enviar" id="btnEnviar" class="btn_limp" value="Enviar" onClick="validaEnvioMail();"/> 
				&nbsp;&nbsp;&nbsp; 
				<input type="button" name="btn_Enviar" id="btn_Enviar" class="btn_limp" value="cargar" onClick="cargaDatosEnGrillaVacio();"/> 
				 -->
			</td>
		</tr>		 	 	
	  </table>
	  
  </div>
</html:form>
</body>
</html:html>
