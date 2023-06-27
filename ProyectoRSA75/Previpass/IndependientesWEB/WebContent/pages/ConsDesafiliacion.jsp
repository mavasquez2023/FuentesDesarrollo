<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld"  prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- <%@ taglib uri="/WEB-INF/c.tld" prefix="c"%> -->
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet" type="text/css" />

<link href="/IndependientesWEB/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery-ui.min.js"></script>

<script type="text/javascript" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" src="/IndependientesWEB/js/Calendario.js"></script>

<script type="text/javascript" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/ModSolDesafiliacionDWR.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>

<script type="text/javascript">
	
	var vectorDocumentos  = new Array();
	var arregloEstados = new Array();
	var arregloTipos = new Array();
	var arregloAgrupaciones = new Array();
	var arregloPerfiles = null;
	var cambioEstDoc = 0;
	var cambioEstDocFlag = false;
	
	//var radio_Agrupacion = null;
	
	cargaArreglos();
	
	function cargaArreglos(){
	
		GeograficoDWR.obtenerLista("ListEstadoDocumentoBox", function(data){
		
			var estados = null
			estados = data;
			
			for (i = 0; i < estados.length; i++){
			
				arregloEstados[i] = new ObjParametro(estados[i].codigo, estados[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListTipoDocumentoBox", function(data){
		
			var tipos = null
			tipos = data;
			
			for (i = 0; i < tipos.length; i++){
			
				arregloTipos[i] = new ObjParametro(tipos[i].codigo, tipos[i].glosa);
			}
		});

		GeograficoDWR.obtenerListaAgrupacion("ListAgrupacionFull", function(data){
		
			var agrupaciones = null
			agrupaciones = data;
			
			for (i = 0; i < agrupaciones.length; i++){
				arregloAgrupaciones[i] = new ObjAgrupacion(agrupaciones[i].idSecuencia, agrupaciones[i].razonSocial, agrupaciones[i].idDocumento, agrupaciones[i].digVerDocumento);
			}
		});
	}
	
	function ObjParametro (codigo, glosa)
	{
		this.codigo = codigo;
		this.glosa = glosa;
	}
	
	function ObjDocumento (idDocumento, tipoDocumento, estadoDocumento,  estadoDocumentoAux, observaciones, alta, obligatorio)
  	{
	  	this.idDocumento 				= idDocumento;
	  	this.tipoDocumento 				= tipoDocumento;
	  	this.estadoDocumento			= estadoDocumento;
	  	this.estadoDocumentoAux			= estadoDocumentoAux;
	  	this.observaciones 				= observaciones;
	  	this.alta						= alta;
	  	this.obligatorio				= obligatorio;
	}
	
	function asignaValor(a){

		document.ConsDesafiliacionForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ConsDesafiliacionForm.submit();
	}	
	
	/*Funcion que valida los campos obligatorios del formulario.*/
	function validaForm()
	{
		// Poner aqui las validaciones del formulario
		
		if(Trim(document.ConsDesafiliacionForm.txt_Folio.value) == "" ){
			alert("Falta ingresar el campo Folio.");
			return false;			
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_NumVerif.value) == "" ){
			alert("Falta ingresar el campo Dígito Verificador.");
			return false; 
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_ApePat.value) == "" ){
			alert("Falta ingresar el campo Apellido Paterno.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_ApeMat.value) == "" ){
			alert("Falta ingresar el campo Apellido Materno.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_Nombres.value) == "" ){
			alert("Falta ingresar el campo Nombres.");
			return false;
		}
		
		if(document.ConsDesafiliacionForm.txt_codAreaCelular.value == "" ){ 
			alert("Falta ingresar el campo Dígito del Telefono Celular.");
			return false;
		}
		
		if(document.ConsDesafiliacionForm.txt_TelCelular.value == "" ){
			alert("Falta ingresar el campo Teléfono Celular.");
			return false;
		}
		
		if(document.ConsDesafiliacionForm.txt_Email.value == "" ){
			alert("Falta ingresar el campo Email.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_Calle.value) == "" ){
			alert("Falta ingresar el campo Calle.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_Numero.value) == "" ){
			alert("Falta ingresar el campo Número.");
			return false;
		}
		
		//Trim(document.ConsDesafiliacionForm.txt_PoblVilla.value) == "" //no obligatorio
		//Trim(document.ConsDesafiliacionForm.txt_Departamento.value == "" || //no obligatorio
		
		if(document.ConsDesafiliacionForm.dbx_Region.value == "0" ){
			alert("Falta seleccionar el campo Región.");
			return false;
		}
		
		if(document.ConsDesafiliacionForm.dbx_Provincia.value == "0" ){ 
			alert("Falta seleccionar el campo Provincia.");
			return false;
		}
		
		if(document.ConsDesafiliacionForm.dbx_Comuna.value == "0" ){
			alert("Falta seleccionar el campo Comuna.");
			return false;
		}
		
		if(document.ConsDesafiliacionForm.dbx_CajaCompensacion.value == "0" ){
			alert("Falta seleccionar el campo Caja de Compensación.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_FecVigAfil.value) == "" ){
			alert("Falta ingresar el campo Fecha de Vigencia de Afiliación CCAF La Araucana.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_FecUltApo.value) == "" ){
			alert("Falta ingresar el campo Fecha Último Aporte");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.dbx_Motivo.value) == "0"){
			alert("Falta seleccionar el campo Tipo de Motivo.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.dbx_DescMotivo.value) == "0"){
			alert("Falta seleccionar el campo Descripción.");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.dbx_Oficina.value) == "0" ){
			alert("Falta seleccionar el campo Oficina");
			return false;
		}
		
		if(Trim(document.ConsDesafiliacionForm.txt_FecSolDesaf.value) == "" ){
			alert("Falta ingresar el campo Fecha Solicitud.");
			return false;
		}

		if(Trim(document.ConsDesafiliacionForm.txt_Hora.value) == "" ){
			alert("Falta ingresar el campo Hora de Captación.");
			return false;
		}
	
		/*

			if(ValidadorRUT(document.ConsDesafiliacionForm.txt_NRut.value,document.ConsDesafiliacionForm.txt_NNumVerif.value))
			{
				if(Trim(document.ConsDesafiliacionForm.txt_Email.value) == "" )
				{
					return true;
				}else{
					if(validarEmail(document.ConsDesafiliacionForm.txt_Email.value))
					{
						return true;
					}
				}
			}
		*/
		return true;
	}
	
	/*Funcion que valida el boton cancelar cuando se ha realizado una accion previa.*/
	function validaCancelar()
	{
	
		if (
			/*Aqui se limpian los campos del punto 0*/
			document.ConsDesafiliacionForm.txt_NFolio.value != "" ||
			document.ConsDesafiliacionForm.txt_NRut.value != "" ||
			document.ConsDesafiliacionForm.txt_NNumVerif.value != "" ||
			
			/*Aqui se limpian los campos del punto 1*/
			document.ConsDesafiliacionForm.txt_Folio.value != "" ||
			document.ConsDesafiliacionForm.txt_Rut.value != "" ||
			document.ConsDesafiliacionForm.txt_NumVerif.value != "" ||
			//document.ConsDesafiliacionForm.txt_Sucursal.value != "" ||
			document.ConsDesafiliacionForm.txt_ApePat.value != "" ||
			document.ConsDesafiliacionForm.txt_ApeMat.value != "" ||
			document.ConsDesafiliacionForm.txt_Nombres.value != "" ||
			document.ConsDesafiliacionForm.txt_codAreaCelular.value != "" ||
			document.ConsDesafiliacionForm.txt_TelCelular.value != "" ||
			document.ConsDesafiliacionForm.txt_codAreaContacto.value != "" ||
			document.ConsDesafiliacionForm.txt_TelContacto.value != "" ||
			document.ConsDesafiliacionForm.txt_Email.value != "" ||
			document.ConsDesafiliacionForm.txt_Calle.value != "" ||
			document.ConsDesafiliacionForm.txt_Numero.value != "" ||
			document.ConsDesafiliacionForm.txt_PoblVilla.value != "" ||
			document.ConsDesafiliacionForm.txt_Departamento.value != "" ||
			document.ConsDesafiliacionForm.dbx_Region.value != "0" ||
			document.ConsDesafiliacionForm.dbx_Provincia.value != "0" ||
			document.ConsDesafiliacionForm.dbx_Comuna.value != "0" ||
			
			/*Aqui se limpian los campos del punto 2*/
			document.ConsDesafiliacionForm.dbx_CajaCompensacion.value != "0" ||
			//document.ConsDesafiliacionForm.txt_FecVigAfil.value = "";
			document.ConsDesafiliacionForm.txt_FecUltApo.value != "" ||
			document.ConsDesafiliacionForm.dbx_Motivo.value != "0" ||
			document.ConsDesafiliacionForm.dbx_DescMotivo.value != "0" ||
			document.ConsDesafiliacionForm.txt_Observaciones.value != "" ||
			
			/*Aqui se limpian los campos del punto 3*/
			//document.ConsDesafiliacionForm.txt_RutCaptador.value != "" ||
			//document.ConsDesafiliacionForm.txt_ApePatProm.value != "" ||
			//document.ConsDesafiliacionForm.txt_ApeMatProm.value != "" ||
			//document.ConsDesafiliacionForm.txt_NombreProm.value != "" ||
			document.ConsDesafiliacionForm.dbx_Oficina.value != "0" ||
			//document.ConsDesafiliacionForm.txt_FecIngr.value != "" ||
			document.ConsDesafiliacionForm.txt_FecSolDesaf.value != "" ||
			document.ConsDesafiliacionForm.txt_Hora.value != "" ||
			document.ConsDesafiliacionForm.txt_Comentarios.value != ""
			
		){
			var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			
			if(respuesta == true){
				enviaFormulario(1);
			}
			
			}else{
				enviaFormulario(1);
			}
		
	}
	
	/*Funcion que obtiene informacion de una solicitud filtrando por folio y rut.*/
	function obtenerSolicitudDWR(){

		var folio = Trim(document.ConsDesafiliacionForm.txt_NFolio.value);
		var rut = Trim(document.ConsDesafiliacionForm.txt_NRut.value);
		var dv = Trim(document.ConsDesafiliacionForm.txt_NNumVerif.value);
		
		if ( folio.length == 0 || rut.length == 0 || dv.length == 0){
		
			alert("Para cargar una solicitud debe ingresar valores para el campo N° Folio, N° RUT y Dígito Verificador.");
		
		}else
		{
			if(ValidadorRUT(rut,dv))
			{
				document.getElementById("pantallaDeCarga").style.visibility = "visible";
			
				DWREngine.setAsync(false);
				ModSolDesafiliacionDWR.obtenerSolicitud(folio, rut, function(data){
				
					var solicitud = null;
		   		
					solicitud = data;
					
					if(solicitud.resultado != "" || solicitud.codResultado != 0){
						alert(solicitud.resultado);
						//alert("No se encontró la solicitud asociada al Folio/RUT.");
					
					}else{
					
						var persona = null;
						var telPart = null
						var telCel = null;
						var telCom = null;
						var email = null;
						var emailComercial = null;						
						var direcPart = null;
						var direcComer = null;
						var afiliado = null;
						var grupoFam = null;
						var ingreso = null;
						var ejecutivo = null;
						var solicitudvo = null;
						var listDocumentos = null;
						var afiliadoagrupacionvo = null;						
						var analistvO = null;					
					
						persona = solicitud.personaVO;
						telPart = solicitud.telefonoPartVO;
						telCel = solicitud.telefonoCeluVO;
						telCom = solicitud.telefonoComerVO;
						email = solicitud.emailVO;
						emailComercial = solicitud.emailComerVO;
						direcPart = solicitud.direccionPartVO;
						direcComer = solicitud.direccionComerVO;
						afiliado = solicitud.afiliadoVO;
						grupoFam = solicitud.grupoFamiliarVO;
						ingreso = solicitud.ingresoEconomicoVO;
						ejecutivo = solicitud.analistaCaptadorVO;
						solicitudvo = solicitud.solicitudVO;
						listDocumentos = solicitud.listaDocumentoVO;

						//REQ5348
						afiliadoagrupacionvo=solicitud.afiliadoAgrupacionVO;	
						//FIN REQ5348													
						analistvo = solicitud.analistaVO;
						//Analista que se loguea en el sistema.
												
						//fin analista.								
						/*document.ConsDesafiliacionForm.txt_Folio.value = 
						document.ConsDesafiliacionForm.txt_Rut.value = 
											
						document.ConsDesafiliacionForm.txt_ApePat.value = persona.apellidoPaterno;
						document.ConsDesafiliacionForm.txt_ApeMat.value = persona.apellidoMaterno;
						document.ConsDesafiliacionForm.txt_Nombres.value = persona.nombres;*/
						//dbx_Nacionalidad
						
						//---------------------------------------------------------------
						
						document.ConsDesafiliacionForm.opcion;
						document.ConsDesafiliacionForm.resultado;
								
						document.ConsDesafiliacionForm.dbx_EstSolicitud.value = solicitudvo.tipoEstadoSolicitud;
						document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value = solicitudvo.tipoEstadoSolicitud;
						document.ConsDesafiliacionForm.txt_EstSolicitud.value = solicitudvo.desTipoEstadoSolicitud;
						
						//-1. Encabezado de Busqueda
						document.ConsDesafiliacionForm.txt_NFolio;
						document.ConsDesafiliacionForm.txt_NRut;
						document.ConsDesafiliacionForm.txt_NNumVerif;
						
						//0. Informacion del Formulario
						document.ConsDesafiliacionForm.txt_Folio.value = solicitudvo.folio;
						
						//1. Identificación
						document.ConsDesafiliacionForm.txt_Rut.value = persona.idDocumento;
						document.ConsDesafiliacionForm.txt_NumVerif.value = persona.digVerificador;
						document.ConsDesafiliacionForm.txt_ApePat.value = persona.apellidoPaterno;
						document.ConsDesafiliacionForm.txt_ApeMat.value = persona.apellidoMaterno;
						document.ConsDesafiliacionForm.txt_Nombres.value = persona.nombres;

						document.ConsDesafiliacionForm.txt_codAreaCelular.value = telCel.codArea;
						document.ConsDesafiliacionForm.txt_TelCelular.value = telCel.nroTelefono;
						document.ConsDesafiliacionForm.txt_codAreaContacto.value = telPart.codArea;
						document.ConsDesafiliacionForm.txt_TelContacto.value = telPart.nroTelefono;
						document.ConsDesafiliacionForm.txt_Email.value = email.direccMail;
						document.ConsDesafiliacionForm.txt_Calle.value = direcPart.glosCalle;
						document.ConsDesafiliacionForm.txt_Numero.value = direcPart.numDireccion;
						document.ConsDesafiliacionForm.txt_PoblVilla.value = direcPart.poblacionVilla;
						document.ConsDesafiliacionForm.txt_Departamento.value = direcPart.dpto;
						
						document.ConsDesafiliacionForm.dbx_Region.value = direcPart.region;
						cargarProvincias(document.ConsDesafiliacionForm.dbx_Provincia,document.ConsDesafiliacionForm.dbx_Comuna,direcPart.region,direcPart.ciudad);
						cargarComunas(document.ConsDesafiliacionForm.dbx_Comuna,direcPart.ciudad,direcPart.comuna);
						
						//5. Afiliacion y Desafiliacion
						document.ConsDesafiliacionForm.dbx_CajaCompensacion.value = solicitudvo.tipoCajaOrigen;
						
						document.ConsDesafiliacionForm.txt_Hora.value = solicitudvo.horaCaptacion;
						//Fecha ultimo aporte
						document.ConsDesafiliacionForm.txt_FecUltAporte.value = solicitudvo.fechaUltAporte;
						document.ConsDesafiliacionForm.txt_FecIngr.value = solicitudvo.fechaIngreso;
						
						
						document.ConsDesafiliacionForm.txt_Comentarios.value = solicitudvo.comentarios;
						document.ConsDesafiliacionForm.txt_Observaciones.value = solicitudvo.observaciones;
						document.ConsDesafiliacionForm.txt_FecSolDesaf.value = solicitudvo.fechaFirma;
						document.ConsDesafiliacionForm.dbx_Oficina.value = analistvo.oficina;
						document.ConsDesafiliacionForm.dbx_Motivo.value = solicitudvo.tipoMotivoDesafiliacion;
						//document.ConsDesafiliacionForm.dbx_DescMotivo.value = solicitudvo.descTipoMotivoDesafiliacion;
						cargarDescMotivo(document.ConsDesafiliacionForm.dbx_DescMotivo,solicitudvo.tipoMotivoDesafiliacion,solicitudvo.descTipoMotivoDesafiliacion);
						
						//Fecha de Vigencia.
						//if('<%=session.getAttribute("Perfil")%>' == "2") //20120709 - ANA - ANTIGUO PERFILAMIENTO
						if (validarPerfiles(arregloPerfiles, "2"))
						{
							desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_FecVigAfil);
							document.ConsDesafiliacionForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
						
						}else{
						
							document.ConsDesafiliacionForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
							bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_FecVigAfil);
						}
						
						//Fecha de resolucion
						//if(solicitudvo.resolucionDirectorio!=null){
						//	document.ConsDesafiliacionForm.txt_ResolucionDirectorio.value=	solicitudvo.resolucionDirectorio;					
						//}							
						//Se agrega fecha de firma.
						//if(solicitudvo.fechaFirma == "01/01/1900"){
						//	document.ConsDesafiliacionForm.txt_FecSolDesaf.value = " ";
						//}else{
						//	document.ConsDesafiliacionForm.txt_FecSolDesaf.value = solicitudvo.fechaFirma;
						//}	

						//Trae datos del analista.(El que se loguea).
						//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_RutCaptador);
						//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApePatCaptador);
						//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApeMatCaptador);
						//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_NombreCaptador);						

						//document.ConsDesafiliacionForm.txt_RutCaptador.value = analistvo.idAnalista;
						//document.ConsDesafiliacionForm.txt_ApePatCaptador.value = analistvo.apellidoPaterno;
						//document.ConsDesafiliacionForm.txt_ApeMatCaptador.value = analistvo.apellidoMaterno;
						//document.ConsDesafiliacionForm.txt_NombreCaptador.value = analistvo.nombres;
						
						//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_RutCaptador);
						//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApePatCaptador);
						//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApeMatCaptador);
						//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_NombreCaptador);
						//fin analista.
						
						//4. Campos Ocultos (ID)
						
						document.ConsDesafiliacionForm.idPersona.value = persona.idPersona;
						document.ConsDesafiliacionForm.idSolicitud.value = solicitudvo.idSolicitud;
						document.ConsDesafiliacionForm.idAfiliadoAgrupacion.value = solicitudvo.idAfiliadoAgrupacion;
						document.ConsDesafiliacionForm.idSecuenciaTelefonoPart.value = telPart.idSecuenciaTelefono;
						document.ConsDesafiliacionForm.idSecuenciaTelefonoCelu.value = telCel.idSecuenciaTelefono;
						document.ConsDesafiliacionForm.idSecuenciaEmail.value = email.idSecuenciaEmail;
						document.ConsDesafiliacionForm.idSecuenciaDireccionPart.value = direcPart.idSecuenciaDireccion;
						
						//Busqueda de listado
						
						ModSolDesafiliacionDWR.getEstadosDestinoPosibles(solicitudvo.tipoEstadoSolicitud, function(data)
						{
							var resp = null;
							resp = data;
							
							var cod = "0";
							var txt = "";
							
							var cmb = document.ConsDesafiliacionForm.dbx_EstSolicitud;
	
							cmb.options.length = 0;
							cmb.options[0] = new Option("Seleccione..." , "0");
	
							if (validarPerfilesFullAnalista(arregloPerfiles) && document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value == 2)
							{

								cmb.options[1] = new Option("INGRESADA" , "1");
							}
							else
							{
								for(var i=0; i<resp.length; i++)
								{
									var item = null;
									item = resp[i];
								
									cod = item.codigo;
									txt = item.glosa;
								
									cmb.options[i+1] = new Option(txt, cod);
								}
							}	
						});
						
						//--- INI Mantenedor de documentos
						
						document.ConsDesafiliacionForm.lbl_DocFolio.value = solicitudvo.folio;
						document.ConsDesafiliacionForm.lbl_DocRUT.value = separadorDeMiles(persona.idDocumento) + "-" + persona.digVerificador;
						document.ConsDesafiliacionForm.lbl_DocNombre.value = persona.nombres + " " + persona.apellidoPaterno + " " + persona.apellidoMaterno;
						
						//--- INI Llenado de matriz
						
						for(i = 0; i < listDocumentos.length ; i++){
							vectorDocumentos[i] = new ObjDocumento(	listDocumentos[i].idSecuenciaDocumento,
																	listDocumentos[i].tipoDocumento,
																	listDocumentos[i].estadoDocumento,
																	listDocumentos[i].estadoDocumento,//Aux
																	listDocumentos[i].observacionesDocumento,
																	listDocumentos[i].alta,
																	listDocumentos[i].obligatorio);
						}
						//--- FIN Llenado de matriz
						
						//--- FIN Mantenedor de documentos
						
						bloqueaFormulario(solicitudvo.tipoEstadoSolicitud);
						desBloqueaFormulario(solicitudvo.tipoEstadoSolicitud);
					}
				});
				DWREngine.setAsync(true);
				
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			}
		}
		
		document.ConsDesafiliacionForm.existeCambio.value = "0";
	}
	
	/*funcion que hace un update al estado de la solicitud.*/
	function updateEstadoSolDWR(){
		
		var estado = document.ConsDesafiliacionForm.dbx_EstSolicitud.value;
		//var estadoAux = document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value;
		//var fecVigencia=document.ConsDesafiliacionForm.txt_FecVigAfil.value;
		var fecIngreso=document.ConsDesafiliacionForm.txt_FecIngr.value;		
		var folio = document.ConsDesafiliacionForm.txt_Folio.value;
		var rut = document.ConsDesafiliacionForm.txt_Rut.value;
	
		/*if(estado == estadoAux){
		
			alert("No ha realizado cambios al estado de solicitud cargado.");
			
		}else{*/
		
		if(cambioEstDocFlag == true){
			estado = 2;
			
		}

		if(estado == 0){
			alert("Error, Debe ingresar un estado válido.");
			return false;
		}
		
			if (folio == ""){
			
				alert("Debe buscar una solicitud para modificar su estado.");
			
			}else{
			
				document.getElementById("pantallaDeCarga").style.visibility = "visible";
			
				ModSolDesafiliacionDWR.updateEstadoSol(folio, estado, rut, fecIngreso, function(data){
				
					var resp = null;
					
					resp = data;
					
					switch(resp){
					
						case 0: 
								if(estado == 2){
									document.ConsDesafiliacionForm.txt_EstSolicitud.value = "RECHAZADA";
								}else{
								document.ConsDesafiliacionForm.txt_EstSolicitud.value = document.ConsDesafiliacionForm.dbx_EstSolicitud.options[document.ConsDesafiliacionForm.dbx_EstSolicitud.selectedIndex].text;
								}
								
								//Busqueda de listado
								ModSolDesafiliacionDWR.getEstadosDestinoPosibles(estado, function(data)
								{
									var resp = null;
									resp = data;
									
									var cod = "0";
									var txt = "";
									
									var cmb = document.ConsDesafiliacionForm.dbx_EstSolicitud;
			
									cmb.options.length = 0;
									cmb.options[0] = new Option("Seleccione..." , "0")
									

									if( validarPerfilesFullAnalista(arregloPerfiles) && document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value == 2)
									{
										cmb.options[1] = new Option("INGRESADA" , "1");
									}
									else
									{
										for(var i=0; i<resp.length; i++)
										{
											var item = null;
											item = resp[i];
										
											cod = item.codigo;
											txt = item.glosa;
	
											cmb.options[i+1] = new Option(txt, cod);
										}
									}	
								});
								
								bloqueaFormulario(estado)
								desBloqueaFormulario(estado);
								if(estado == 2 && cambioEstDocFlag == true){
								alert("Se actualiza estado de solicitud a Rechazada debido a que existe documentacion Rechazada o No Recibida.");
								cambioEstDocFlag = false;
								break;
								}
								else
								{
								alert("Estado de Solicitud modificado con exito.");
								break;
								}
						case 99: alert("Se produjo un error al modificar el estado.");
								break;
						default : alert("Error desconocido al modificar el Estado de la Solicitud");
					
					}
				
				});
				
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			}
		//}
	}
	
	/*funcion que obtiene informacion de una solicitud filtrada por folio.*/
	function obtenerSolicitudFolioDWR()
	{
		var folio = Trim(document.ConsDesafiliacionForm.txt_NFolio.value);
		
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		DWREngine.setAsync(false);
		ModSolDesafiliacionDWR.obtenerSolicitudPorFolio(folio, function(data){
			var solicitud = null;
		   	solicitud = data;
		   	
		   	if(solicitud.resultado != "" || solicitud.codResultado != 0)
		   	{
				alert(solicitud.resultado);
				//alert("No se encontró la solicitud asociada al Folio.");
			
			}else
			{
				var persona = null;
				var telPart = null
				var telCel = null;
				var telCom = null;
				var email = null;
				var emailComercial = null;				
				var direcPart = null;
				var direcComer = null;
				var afiliado = null;
				var grupoFam = null;
				var ingreso = null;
				var ejecutivo = null;
				var solicitudvo = null;
				var listDocumentos = null;
				var afiliadoagrupacionvo = null;		
				var analistvo=null;
					
				persona = solicitud.personaVO;
				telPart = solicitud.telefonoPartVO;
				telCel = solicitud.telefonoCeluVO;
				email = solicitud.emailVO;
				direcPart = solicitud.direccionPartVO;
				afiliado = solicitud.afiliadoVO;
				ejecutivo = solicitud.analistaCaptadorVO;
				solicitudvo = solicitud.solicitudVO;
				listDocumentos = solicitud.listaDocumentoVO;

				analistvo=	solicitud.analistaVO;		
				//---------------------------------------------------------------
				
				document.ConsDesafiliacionForm.opcion;
				document.ConsDesafiliacionForm.resultado;
						
				document.ConsDesafiliacionForm.dbx_EstSolicitud.value = solicitudvo.tipoEstadoSolicitud;
				document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value = solicitudvo.tipoEstadoSolicitud;
				document.ConsDesafiliacionForm.txt_EstSolicitud.value = solicitudvo.desTipoEstadoSolicitud;
						
				//-1. Encabezado de Busqueda
				document.ConsDesafiliacionForm.txt_NFolio;
				document.ConsDesafiliacionForm.txt_NRut.value = persona.idDocumento;
				document.ConsDesafiliacionForm.txt_NNumVerif.value = persona.digVerificador;
						
				//0. Informacion del Formulario
				document.ConsDesafiliacionForm.txt_Folio.value = solicitudvo.folio;
						
				//1. Identificación
				document.ConsDesafiliacionForm.txt_Rut.value = persona.idDocumento;
				document.ConsDesafiliacionForm.txt_NumVerif.value = persona.digVerificador;
				document.ConsDesafiliacionForm.txt_ApePat.value = persona.apellidoPaterno;
				document.ConsDesafiliacionForm.txt_ApeMat.value = persona.apellidoMaterno;
				document.ConsDesafiliacionForm.txt_Nombres.value = persona.nombres;
				
				document.ConsDesafiliacionForm.txt_codAreaCelular.value = telCel.codArea;
				document.ConsDesafiliacionForm.txt_TelCelular.value = telCel.nroTelefono;
				document.ConsDesafiliacionForm.txt_codAreaContacto.value = telPart.codArea;
				document.ConsDesafiliacionForm.txt_TelContacto.value = telPart.nroTelefono;
				document.ConsDesafiliacionForm.txt_Email.value = email.direccMail;
				document.ConsDesafiliacionForm.txt_Calle.value = direcPart.glosCalle;
				document.ConsDesafiliacionForm.txt_Numero.value = direcPart.numDireccion;
				document.ConsDesafiliacionForm.txt_PoblVilla.value = direcPart.poblacionVilla;
				document.ConsDesafiliacionForm.txt_Departamento.value = direcPart.dpto;
						
				document.ConsDesafiliacionForm.dbx_Region.value = direcPart.region;
				cargarProvincias(document.ConsDesafiliacionForm.dbx_Provincia,document.ConsDesafiliacionForm.dbx_Comuna,direcPart.region,direcPart.ciudad);
				cargarComunas(document.ConsDesafiliacionForm.dbx_Comuna,direcPart.ciudad,direcPart.comuna);
				
				//2. Desafiliacion de la CCAF la araucana						
				document.ConsDesafiliacionForm.dbx_CajaCompensacion.value = solicitudvo.tipoCajaOrigen;
				
				document.ConsDesafiliacionForm.txt_Hora.value = solicitudvo.horaCaptacion;
				//Fecha ultimo aporte
				document.ConsDesafiliacionForm.txt_FecUltAporte.value = solicitudvo.fechaUltAporte;
				
				document.ConsDesafiliacionForm.txt_Comentarios.value = solicitudvo.comentarios;
				document.ConsDesafiliacionForm.txt_Observaciones.value = solicitudvo.observaciones;
				document.ConsDesafiliacionForm.txt_FecSolDesaf.value = solicitudvo.fechaFirma;
				document.ConsDesafiliacionForm.dbx_Oficina.value = analistvo.oficina;
				document.ConsDesafiliacionForm.dbx_Motivo.value = solicitudvo.tipoMotivoDesafiliacion;
				//document.ConsDesafiliacionForm.dbx_DescMotivo.value = solicitudvo.descTipoMotivoDesafiliacion;
				cargarDescMotivo(document.ConsDesafiliacionForm.dbx_DescMotivo,solicitudvo.tipoMotivoDesafiliacion,solicitudvo.descTipoMotivoDesafiliacion);
						
				//Fecha de Vigencia.
				//if('<%=session.getAttribute("Perfil")%>' == "2")//20120709 - ANA - ANTIGUO PERFILAMIENTO
				if (validarPerfiles(arregloPerfiles, "2"))
				{
					desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_FecVigAfil);
					document.ConsDesafiliacionForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
				
				}else{
						
					document.ConsDesafiliacionForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
					bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_FecVigAfil);
				}

						

				document.ConsDesafiliacionForm.txt_codOficina.value = solicitudvo.oficina;
				document.ConsDesafiliacionForm.txt_FecIngr.value = solicitudvo.fechaIngreso;
				document.ConsDesafiliacionForm.txt_Hora.value = solicitudvo.horaCaptacion;		
				//Se agrega fecha de firma.
				//if(solicitudvo.fechaFirma == "01/01/1900"){
				//	document.ConsDesafiliacionForm.txt_FecSolDesaf.value = " ";
				//}else{
				//	document.ConsDesafiliacionForm.txt_FecSolDesaf.value = solicitudvo.fechaFirma;
				//}	

				//4. Campos Ocultos (ID)
				document.ConsDesafiliacionForm.idPersona.value = persona.idPersona;
				document.ConsDesafiliacionForm.idSolicitud.value = solicitudvo.idSolicitud;
				document.ConsDesafiliacionForm.idAfiliadoAgrupacion.value = solicitudvo.idAfiliadoAgrupacion;
				document.ConsDesafiliacionForm.idSecuenciaTelefonoPart.value = telPart.idSecuenciaTelefono;
				document.ConsDesafiliacionForm.idSecuenciaTelefonoCelu.value = telCel.idSecuenciaTelefono;
				document.ConsDesafiliacionForm.idSecuenciaEmail.value = email.idSecuenciaEmail;
				document.ConsDesafiliacionForm.idSecuenciaDireccionPart.value = direcPart.idSecuenciaDireccion;
					
				//Trae datos del analista.(El que se loguea).
				//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_RutCaptador);
				//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApePatCaptador);
				//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApeMatCaptador);
				//desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_NombreCaptador);						
						
				//document.ConsDesafiliacionForm.txt_RutCaptador.value = analistvo.idAnalista;
				//document.ConsDesafiliacionForm.txt_ApePatCaptador.value = analistvo.apellidoPaterno;
				//document.ConsDesafiliacionForm.txt_ApeMatCaptador.value = analistvo.apellidoMaterno;
				//document.ConsDesafiliacionForm.txt_NombreCaptador.value = analistvo.nombres;
										
				//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_RutCaptador);
				//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApePatCaptador);
				//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApeMatCaptador);
				//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_NombreCaptador);
				//fin analista.	
					
				//Busqueda de listado
				ModSolDesafiliacionDWR.getEstadosDestinoPosibles(solicitudvo.tipoEstadoSolicitud, function(data)
				{
					var resp = null;
					resp = data;
					
					var cod = "0";
					var txt = "";
							
					var cmb = document.ConsDesafiliacionForm.dbx_EstSolicitud;
	
						cmb.options.length = 0;
						cmb.options[0] = new Option("Seleccione..." , "0");
	
					//if('<%=session.getAttribute("Perfil")%>' == "1" && document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value == 5)//20120709 - ANA - ANTIGUO PERFILAMIENTO
					if (validarPerfilesFullAnalista(arregloPerfiles) && document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value == 2)
					{
						cmb.options[1] = new Option("INGRESADA" , "1");
					}
					else
					{
						for(var i=0; i<resp.length; i++)
						{
							var item = null;
							item = resp[i];
						
							cod = item.codigo;
							txt = item.glosa;
								
							cmb.options[i+1] = new Option(txt, cod);
						}
					}	
				});
				
				//--- INI Mantenedor de documentos
				
				document.ConsDesafiliacionForm.lbl_DocFolio.value = solicitudvo.folio;
				document.ConsDesafiliacionForm.lbl_DocRUT.value = separadorDeMiles(persona.idDocumento) + "-" + persona.digVerificador;
				document.ConsDesafiliacionForm.lbl_DocNombre.value = persona.nombres + " " + persona.apellidoPaterno + " " + persona.apellidoMaterno;
				
				//--- INI Llenado de matriz
				for(i = 0; i < listDocumentos.length ; i++){
					vectorDocumentos[i] = new ObjDocumento(	listDocumentos[i].idSecuenciaDocumento,
															listDocumentos[i].tipoDocumento,
															listDocumentos[i].estadoDocumento,
															listDocumentos[i].estadoDocumento,//Aux
															listDocumentos[i].observacionesDocumento,
															listDocumentos[i].alta,
															listDocumentos[i].obligatorio);
				}
				//--- FIN Llenado de matriz
				
				//--- FIN Mantenedor de documentos
						
				bloqueaFormulario(solicitudvo.tipoEstadoSolicitud);
				desBloqueaFormulario(solicitudvo.tipoEstadoSolicitud);
			  }
			});
			DWREngine.setAsync(true);
			
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			
		document.ConsDesafiliacionForm.existeCambio.value = "0";	
	}
	
	/*Funcion que realiza un update a los distintos campos que componen el formulario de solicitud.
	 Construye la cadena que será enviada a la capa de java.*/
	function updateSolicitudDWR(){
		
		if( validaForm() == true ){
			
			var conyugue = 0;
			var honorario = 0;
			var fechaVigencia = "";

		
			//para fecha de vigencia
			if( validaFechaVigencia() == false)
			
			{
				alert("La Fecha de Vigencia no puede ser menor al primer día del mes subsiguiente a la fecha de ingreso de la solicitud");
				recuperarFecVigencia();
				return false;
			}
			
			if( validaFecSolicitud() == false )
			{
				alert("La Fecha de Solicitud no puede ser anterior al 01/01/2012.");
				recuperarFecFirma();
				return false;
			}	
			
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
						
			// Para validacion de campos no Obligatorios
			//var codAreaCelular = "0";
			//var telCelular = "0";
			var codAreaContacto = "0";
			var telContacto = "0";
			var email = " ";
			var poblacion = " ";
			var depto = " ";

			var poblacionComerc = " ";
			var deptoComerc = " ";
		
			var codAreaComerc = "0";
			var telComerc = "0";
			var rentaCot = "0";
			var montoUltCot = "0";			
			var fecUltCot = " ";	
			var horaCaptacion = " ";					
			var hijos = "0";
			var fechaFirma = " ";
			
			var observaciones = " ";
			var comentarios = " ";
		
			//Los siguientes comentados son obligatorios
			/*if(document.ConsDesafiliacionForm.txt_codAreaCelular.value != ""){
				codAreaCelular = document.ConsDesafiliacionForm.txt_codAreaCelular.value
			}	
			if(document.ConsDesafiliacionForm.txt_TelCelular.value != ""){
				telCelular = document.ConsDesafiliacionForm.txt_TelCelular.value;
			}*/
			
			if(document.ConsDesafiliacionForm.txt_codAreaContacto.value != ""){
				codAreaContacto = document.ConsDesafiliacionForm.txt_codAreaContacto.value
			}
			if(document.ConsDesafiliacionForm.txt_TelContacto.value != ""){
				telContacto = document.ConsDesafiliacionForm.txt_TelContacto.value;
			}
			if(Trim(document.ConsDesafiliacionForm.txt_Email.value) != ""){
				email = document.ConsDesafiliacionForm.txt_Email.value;
			}
			if(Trim(document.ConsDesafiliacionForm.txt_PoblVilla.value) != ""){
				poblacion = document.ConsDesafiliacionForm.txt_PoblVilla.value;
			}
			if(Trim(document.ConsDesafiliacionForm.txt_Departamento.value) != ""){
				depto = document.ConsDesafiliacionForm.txt_Departamento.value;
			}
			if(document.ConsDesafiliacionForm.txt_Observaciones.value != ""){
				observaciones = document.ConsDesafiliacionForm.txt_Observaciones.value;
			}
			if(document.ConsDesafiliacionForm.txt_Comentarios.value != ""){
				comentarios = document.ConsDesafiliacionForm.txt_Comentarios.value;
			}
			if(document.ConsDesafiliacionForm.txt_FecSolDesaf.value != ""){
				fechaFirma = document.ConsDesafiliacionForm.txt_FecSolDesaf.value;
			}else{
				fechaFirma = "01/01/1900";
			}	
			horaCaptacion = document.ConsDesafiliacionForm.txt_Hora.value;
			var cadenaForm = "#" + document.ConsDesafiliacionForm.opcion + "#" +			
						document.ConsDesafiliacionForm.resultado + "#" +
						document.ConsDesafiliacionForm.dbx_EstSolicitud.value + "#" +
               			document.ConsDesafiliacionForm.txt_NFolio.value + "#" +//3
			            document.ConsDesafiliacionForm.txt_NRut.value + "#" +
			            document.ConsDesafiliacionForm.txt_NNumVerif.value + "#" +
						document.ConsDesafiliacionForm.txt_Folio.value + "#" + 
						document.ConsDesafiliacionForm.txt_Rut.value + "#" + 
						document.ConsDesafiliacionForm.txt_NumVerif.value + "#" +
						document.ConsDesafiliacionForm.txt_ApePat.value + "#" +//9
						document.ConsDesafiliacionForm.txt_ApeMat.value + "#" +//10
						document.ConsDesafiliacionForm.txt_Nombres.value + "#" + 
						document.ConsDesafiliacionForm.txt_codAreaCelular.value + "#" + //codAreaCelular
						document.ConsDesafiliacionForm.txt_TelCelular.value + "#" + //telCelular
						codAreaContacto + "#" +
						telContacto + "#" +  //15
						document.ConsDesafiliacionForm.txt_Email.value + "#" + //email
						document.ConsDesafiliacionForm.txt_Calle.value + "#" + //
						document.ConsDesafiliacionForm.txt_Numero.value + "#" + //
						poblacion + "#" + //
						depto + "#" + //20
						document.ConsDesafiliacionForm.dbx_Region.value + "#" + 
						document.ConsDesafiliacionForm.dbx_Provincia.value + "#" + 
						document.ConsDesafiliacionForm.dbx_Comuna.value + "#" + //
						document.ConsDesafiliacionForm.dbx_CajaCompensacion.value + "#" + //24
						document.ConsDesafiliacionForm.txt_FecVigAfil.value + "#" + //25 fecha vigencia afiliacion
						document.ConsDesafiliacionForm.txt_FecIngr.value + "#" +//26
						fechaFirma + "#" + //27
						document.ConsDesafiliacionForm.idPersona.value + "#" + //28
	 					document.ConsDesafiliacionForm.idSolicitud.value + "#" +//
	 					document.ConsDesafiliacionForm.idAfiliadoAgrupacion.value + "#" +//30
	 					document.ConsDesafiliacionForm.idSecuenciaTelefonoPart.value + "#" +//
	 					document.ConsDesafiliacionForm.idSecuenciaTelefonoCelu.value + "#" +//
	 					document.ConsDesafiliacionForm.idSecuenciaEmail.value + "#" + //
	 					document.ConsDesafiliacionForm.idSecuenciaDireccionPart.value + "#";//34
						
						cadenaForm = cadenaForm + "|";
						
						for(var i=0;i<vectorDocumentos.length;i++)
						{
							vectorDocumentos[i].estadoDocumento = vectorDocumentos[i].estadoDocumentoAux;
						
							var docObs = " ";
						
							if(vectorDocumentos[i].observaciones != ""){
								docObs = vectorDocumentos[i].observaciones;
							}
						
							cadenaForm = cadenaForm + vectorDocumentos[i].idDocumento + "|";
							cadenaForm = cadenaForm + vectorDocumentos[i].tipoDocumento + "|";
							cadenaForm = cadenaForm + vectorDocumentos[i].estadoDocumentoAux + "|";
							cadenaForm = cadenaForm + docObs + "|";
						}
						cadenaForm += "#";
						//REQ5348
						cadenaForm += horaCaptacion + "#";//36
						cadenaForm += document.ConsDesafiliacionForm.dbx_Oficina.value + "#";//37
						//FIN REQ5348
						
						cadenaForm += document.ConsDesafiliacionForm.dbx_Motivo.value + "#";//38
						cadenaForm += document.ConsDesafiliacionForm.dbx_DescMotivo.value + "#";//39
						cadenaForm += observaciones + "#";//40
						cadenaForm += comentarios + "#";//41
						cadenaForm += <%=session.getAttribute("IDAnalista")%> + "#";//42
						cadenaForm += document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value + "#";//43
						
			ModSolDesafiliacionDWR.updateSolicitud(cadenaForm, function(data){
				
				var resp = null;
				resp = data;
				
				if(resp == 0){
					alert("Solicitud modificada con éxito.");
				}else{
					alert("Ocurrió un error al modificar la solicitud.");
				}
				
			});
			
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			
			buscarPorFiltro();
		}	
	}
	
	/*funcion que bloquea los campos del formulario dependiendo del estado en que se encuentre la solicitud.*/
	function bloqueaFormulario(estado){
	
		if (estado == "4" || estado == "5"){
		
			//document.ConsDesafiliacionForm.txt_EstSolicitud.disabled = true;
			document.ConsDesafiliacionForm.txt_Folio.disabled = true;
			document.ConsDesafiliacionForm.txt_Rut.disabled = true;
			document.ConsDesafiliacionForm.txt_NumVerif.disabled = true;
			document.ConsDesafiliacionForm.txt_ApePat.disabled = true;
			document.ConsDesafiliacionForm.txt_ApeMat.disabled = true;
			document.ConsDesafiliacionForm.txt_Nombres.disabled = true;
			document.ConsDesafiliacionForm.txt_codAreaCelular.disabled = true;
			document.ConsDesafiliacionForm.txt_TelCelular.disabled = true;
			document.ConsDesafiliacionForm.txt_codAreaContacto.disabled = true;
			document.ConsDesafiliacionForm.txt_TelContacto.disabled = true;
			document.ConsDesafiliacionForm.txt_Email.disabled = true;
			document.ConsDesafiliacionForm.txt_Calle.disabled = true;
			document.ConsDesafiliacionForm.txt_Numero.disabled = true;
			document.ConsDesafiliacionForm.txt_PoblVilla.disabled = true;
			document.ConsDesafiliacionForm.txt_Departamento.disabled = true;
			
			document.ConsDesafiliacionForm.dbx_Region.disabled = true;
			document.ConsDesafiliacionForm.dbx_Provincia.disabled = true;
			document.ConsDesafiliacionForm.dbx_Comuna.disabled = true;
			
			document.ConsDesafiliacionForm.dbx_Motivo.disabled = true;
			document.ConsDesafiliacionForm.dbx_DescMotivo.disabled = true;
			document.ConsDesafiliacionForm.txt_Observaciones.disabled = true;
			
			document.ConsDesafiliacionForm.dbx_Oficina.disabled = true;
			document.ConsDesafiliacionForm.txt_Comentarios.disabled = true;
			
			//5. Afiliacion y Desafiliacion
			document.ConsDesafiliacionForm.dbx_CajaCompensacion.disabled = true;
			document.ConsDesafiliacionForm.txt_FecVigAfil.disabled = true;
			document.ConsDesafiliacionForm.txt_Hora.disabled = true;						
		}
		
		var botonEst = null;
		var botonGuar = null;
		//var botonGuarDoc = null;
		
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuar = document.getElementById("btn_GuardarSol");
		//botonGuarDoc = document.getElementById("btn_DocGuardar");
		
		if (estado == "5" || estado == "4")
		{
			botonEst.disabled = true;
			document.ConsDesafiliacionForm.dbx_EstSolicitud.disabled = true;
			botonGuar.disabled = true;
		}
			
		bloqueaCampos();
	}
	
	/*funcion que desbloquea los campos del formulario dependiendo del estado en que se encuentre la solicitud.*/
	function desBloqueaFormulario(estado){
	
		if (estado == "1" || estado == "2" || estado == "3"){
	
			//document.ConsDesafiliacionForm.txt_EstSolicitud.disabled = false;
			document.ConsDesafiliacionForm.txt_Folio.disabled = false;
			document.ConsDesafiliacionForm.txt_Rut.disabled = false;
			document.ConsDesafiliacionForm.txt_NumVerif.disabled = false;
			document.ConsDesafiliacionForm.txt_ApePat.disabled = false;
			document.ConsDesafiliacionForm.txt_ApeMat.disabled = false;
			document.ConsDesafiliacionForm.txt_Nombres.disabled = false;

			document.ConsDesafiliacionForm.txt_codAreaCelular.disabled = false;
			document.ConsDesafiliacionForm.txt_TelCelular.disabled = false;
			document.ConsDesafiliacionForm.txt_codAreaContacto.disabled = false;
			document.ConsDesafiliacionForm.txt_TelContacto.disabled = false;
			document.ConsDesafiliacionForm.txt_Email.disabled = false;

			document.ConsDesafiliacionForm.txt_Calle.disabled = false;
			document.ConsDesafiliacionForm.txt_Numero.disabled = false;
			document.ConsDesafiliacionForm.txt_PoblVilla.disabled = false;
			document.ConsDesafiliacionForm.txt_Departamento.disabled = false;
			
			document.ConsDesafiliacionForm.dbx_Region.disabled = false;
			document.ConsDesafiliacionForm.dbx_Provincia.disabled = false;
			document.ConsDesafiliacionForm.dbx_Comuna.disabled = false;
			
			
			//5. Afiliacion y Desafiliacion
			document.ConsDesafiliacionForm.dbx_CajaCompensacion.disabled = false;
			document.ConsDesafiliacionForm.txt_FecVigAfil.disabled = true;
			document.ConsDesafiliacionForm.txt_Hora.disabled = false;			
		}
		
		var botonEst = null;
		var botonGuar = null;
		
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuar = document.getElementById("btn_GuardarSol");
		
		if (estado == "1" || estado == "2" || estado == "3")
		{
			botonEst.disabled = false;
			document.ConsDesafiliacionForm.dbx_EstSolicitud.disabled = false;
		}
		
		if (estado == "1" || estado == "2" || estado == "3" || estado == "4")
		{
			botonGuar.disabled = false;
		}
		
		bloqueaCampos();
	}
	
	/*Funcion que bloquea los campos del formulario dependiendo del perfil del analista.*/
	function bloqueaCampos()
	{
		var botonEst = null;
		var botonGuarDoc = null;
		var esconderCalendar = null;
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		
		document.ConsDesafiliacionForm.txt_Folio.disabled = true;
		document.ConsDesafiliacionForm.txt_Rut.disabled = true;
		document.ConsDesafiliacionForm.txt_NumVerif.disabled = true;
	
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuarDoc = document.getElementById("btn_DocGuardar");
		
		//if ('<%=session.getAttribute("Perfil")%>' == "1") //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles))
		{
			botonEst.disabled = true;
			document.ConsDesafiliacionForm.dbx_EstSolicitud.disabled = true;
			/* Esconde el Calendario de fechaVigencia si es que el usuario no tiene el perfil que
			  permita modificar dicha fecha.*/
			document.ConsDesafiliacionForm.txt_FecVigAfil.disabled = true;
			
		}
		

		if (validarPerfilesFullAnalista(arregloPerfiles) && document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value == 2)
		{
			botonEst.disabled = false;
			document.ConsDesafiliacionForm.dbx_EstSolicitud.disabled = false;
			//document.getElementById("btn_GuardarSol").disabled = false;
		}
		
		if (validarPerfilesFullAnalista(arregloPerfiles) && document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value != 2)
		{
			botonEst.disabled = true;
			document.ConsDesafiliacionForm.btn_GuardarSol.disabled = true;
			document.ConsDesafiliacionForm.btn_GuardarEst.disabled = true;
			document.ConsDesafiliacionForm.dbx_EstSolicitud.disabled = true;
		}
		
		$('.datepick').each(function(){
		    $(this).datepicker({
			      showOn: "button",
			      buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			      buttonImageOnly: true,
			      buttonText: "Seleccionar fecha"
			});
		});
		
	}
	
	/*funcion que permite modificar la fecha de vigencia.*/
	/*function editarFecVigencia()
	{
		var fechaCalculada = null;
		var fechaModificada = null;
			
		//if('<%=session.getAttribute("Perfil")%>' == "2") //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfiles(arregloPerfiles, "2"))
		{
			fechaCalculada = '<%=session.getAttribute("FechaVigencia")%>';
			fechaModificada = document.ConsDesafiliacionForm.txt_FecVigAfil.value
				
			if(Comparar_Fecha(fechaModificada,fechaCalculada)== true)
			{
				document.ConsDesafiliacionForm.txt_FecVigAfil.value = fechaModificada;
				return true;
				
			}else{
				return false;
			}
		}		
	}*/
	
	/*Funcion que permite recuperar la fecha de vigencia.*/
	function recuperarFecVigencia()
	{
		var folio = Trim(document.ConsDesafiliacionForm.txt_NFolio.value);
		
		ModSolDesafiliacionDWR.recuperarFecVigencia(folio, function(data){
			
			document.ConsDesafiliacionForm.txt_FecVigAfil.value = data;
		});
	}
	
	/*funcion que permite valida la fecha de solicitud.*/
	function validaFecSolicitud()
	{
		var fechaSolicitud = "01/01/2012";
		var fechaSolIngresada = document.ConsDesafiliacionForm.txt_FecSolDesaf.value;
		if(Comparar_Fecha(fechaSolIngresada,fechaSolicitud))
		{
			return true;
		}
		
		return false;	
	}
	
	/*funcion que recupera la fecha de firma.*/
	function recuperarFecFirma()
	{
		var folio = Trim(document.ConsDesafiliacionForm.txt_NFolio.value);
		
		ModSolDesafiliacionDWR.recuperarFecFirma(folio, function(data){
					
			document.ConsDesafiliacionForm.txt_FecSolDesaf.value = data;
		});
	}
	
	function desBloqueaCampos() // TODO No se esta ocupando?
	{
		document.ConsDesafiliacionForm.txt_Folio.disabled = false;
		document.ConsDesafiliacionForm.txt_Rut.disabled = false;
		document.ConsDesafiliacionForm.txt_NumVerif.disabled = false;
		document.ConsDesafiliacionForm.txt_ValorACot.disabled = false;
	}
	
	/*funcion que valida la mayoria de edad*/
	function ValidaMayoriaDeEdad(fecha){
	
		if(Comparar_Fecha_Anyo('<%=session.getAttribute("FechaSistema")%>',fecha, 18)){
			alert("Para poder ingresar una solicitud, el solicitante debe ser mayor de 18 años.");
			return false;
		}
		return true;
	}
	
	/*funcion que valida la fecha de vigencia*/
	function validaFechaVigencia()
	{
		var respuesta;
	
		var fechaVigencia = document.ConsDesafiliacionForm.txt_FecVigAfil.value;
		var fechaIngreso = document.ConsDesafiliacionForm.txt_FecIngr.value;
		
		if (fechaVigencia != "" && fechaIngreso != ""){
		
			DWREngine.setAsync(false);
			ModSolDesafiliacionDWR.validaFechaVigencia(fechaVigencia, fechaIngreso, function(data){
			
				var resp = null;
				resp = data;
			
				if(resp == 1){
					respuesta = true;
				}else if(resp == 0){
					respuesta = false;
				}else{
					alert("Ocurrió un error al ejecutar validaFechaVigencia");
				}
			});
			DWREngine.setAsync(true);
			
			return respuesta;
		}
	}
	
	function obtenerFechaVigencia(fecha)
	{
		ModSolDesafiliacionDWR.obtenerFechaVigencia(fecha, function(data){
		
			return data;
		});	
	}
	
	function Cambios(x)
	{
		document.ConsDesafiliacionForm.existeCambio.value = x;
	}
	
	/*funcion por filtro. dependiendo del tipo de filtro, puede buscar por folio y rut, folio, rut.
		Si el filtro es por rut, se ejecuta la grilla con la busqueda.*/
	function buscarPorFiltro()
	{
		var folio = Trim(document.ConsDesafiliacionForm.txt_NFolio.value);
		var rut = Trim(document.ConsDesafiliacionForm.txt_NRut.value);
		var dv = Trim(document.ConsDesafiliacionForm.txt_NNumVerif.value);
		
		if(folio.length == 0 && rut.length == 0)
		{
			alert("Para realizar una búsqueda debe ingresar un folio, un rut, o un folio/rut.");
		}

		if(folio.length != 0 && rut.length == 0)
		{	
		obtenerSolicitudFolioDWR();
		}else{
			if(folio.length != 0 && rut.length != 0){
				obtenerSolicitudDWR();
			}else{
				if(folio.length == 0 && rut.length != 0 && ValidadorRUT(rut,dv) == true){
					cargarGrilla();
				}
			}								
		}	
	}
	
	/***************************************************************************/
	/* AQUI IMPLEMENTACION DE LA GRILLA PARA MOSTRAR DATOS DE BUSQUEDA POR RUT */
	var datos = new Array();
	var cantidadRegistrosPorPagina = 30;
	
	/*funcion que carga los datos en la grilla*/
	function cargaDatosEnGrilla()
	{
		var contenidoTabla = "";
		
		for(var i=0;i<datos.length;i++)
		{
			if( i < cantidadRegistrosPorPagina )
				
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
		document.getElementById("datosSolicitudPorRut").style.display = "";
		document.getElementById("datosSolicitudPorRut").innerHTML = obtenerHeaderGrilla() + contenidoTabla + obtenerHeaderCancelarGrilla() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
	}
	
	/*funcion que carga la grilla*/
	function cargarGrilla()
	{
		var rut = Trim(document.ConsDesafiliacionForm.txt_NRut.value);
		var digitoVer = Trim(document.ConsDesafiliacionForm.txt_NNumVerif.value);
		//agregar validacion para digito verificador.

		ModSolDesafiliacionDWR.obtenerFoliosPorRut(rut, function(data){
			datos = data;
			if(datos != null){
				
				cargaDatosEnGrilla();
			}
		});
	}															
	
	/*funcion que obtiene la fila de la tabla*/
	function obtenerFilaTabla(dato)
	{
		document.ConsDesafiliacionForm.txt_NFolio.value = "";
		var texto =  " <tr> "+
							"<td class='texto' align='center'>" + "<a href='#' onclick='eligeFolio("+(dato.folio)+");'>" + dato.folio + "</a>" + "</td>"+
							"<td class='texto' align='center'>"+dato.desTipoEstadoSolicitud+"</td>"+
	   				 "</tr>";		 
		return texto;
	}
		
	function obtenerHeaderCancelarGrilla()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right"><input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="esconderDiv();"/></td>'+
				'</tr>';
	}
	
	function obtenerHeaderGrilla(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nº Folio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Estado Solicitud</td>'+
				'</tr>';
	}
	
	/*funcion que esconde el div que contiene a la grilla*/
	function esconderDiv()
	{
		document.getElementById("datosSolicitudPorRut").style.display = "none";

	}
	
	/*funcion que guarda los folios*/
	var contenidoFolio = null;
	function guardaFolio()
	{
		for(var i=0;i<datos.length; i++)
		{
				contenidoFolio = datos[i];
		}
		
		return contenidoFolio;
	}
	
	/*funcion que luego de elegir el folio esconde el div que contiene a la grilla y obtiene los datos de la solicitud.*/
	function eligeFolio(dato)
	{	
		document.ConsDesafiliacionForm.txt_NFolio.value = dato;		
		esconderDiv();
		obtenerSolicitudDWR();
		/*document.ConsDesafiliacionForm.txt_NRut.value = "";
		document.ConsDesafiliacionForm.txt_NFolio.value = "";
		document.ConsDesafiliacionForm.txt_NNumVerif.value = "";*/
	}
	
	var consultas = null;
	function tituloGrilla()
	{
		var apellidoPat = null;
		var apellidoMat = null;
		var nombres = null;
		var rut = Trim(document.ConsDesafiliacionForm.txt_NRut.value);
		
		ModSolDesafiliacionDWR.obtenerDatosPorRut(rut, function(data){
		
			consultas = data;
			
			var persona = null;
			persona = consulta.personaVO;
			
		});
	}
	/* FIN GRILLA*/
	
	/*funcion que limpia los campos de busqueda del analista*/
	function limpiarCamposBusqueda(){
	
		document.ConsDesafiliacionForm.txt_RutEjec.value = "";
		
		desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApePatEjec);
		desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApeMatEjec);
		desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_NombreEjec);

		document.ConsDesafiliacionForm.txt_ApePatEjec.value = "";
		document.ConsDesafiliacionForm.txt_ApeMatEjec.value = "";
		document.ConsDesafiliacionForm.txt_NombreEjec.value = "";
		
		bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApePatEjec);
		bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_ApeMatEjec);
		bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_NombreEjec);
	}
	
	/* FIN MODIFICACION ANALISTA */
	
	function mostrarDiv()
	{
		var nombre = null;
		var apePat = null;
		var apeMat = null;
		var rut = Trim(document.ConsDesafiliacionForm.txt_NRut.value);
		
		ModSolDesafiliacionDWR.obtenerDatosPorRut(rut, function(data){
			var datos = null;
			datos = data;
			
			var persona = null;
			var solicitud = null;
			
			persona = datos.personaVO;
			solicitud = datos.solicitudVO;
			
			desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_nombreCompletoAfil);
			desBloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_TipoEstadoPorRut);
			
			nombre = persona.nombres;
			apePat = persona.apellidoPaterno;
			apeMat = persona.apellidoMaterno;
			document.ConsDesafiliacionForm.txt_FolioPorRut.value = solicitud.folio;
			document.ConsDesafiliacionForm.txt_TipoEstadoPorRut.value = solicitud.desTipoEstadoSolicitud;
			
			//bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_FolioPorRut);
			bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_TipoEstadoPorRut);
			
			var nombreCompleto = nombre + " " + apePat + " " + apeMat;
		
			document.ConsDesafiliacionForm.txt_nombreCompletoAfil.value = nombreCompleto;
			bloqueaCampoIndiv(document.ConsDesafiliacionForm.txt_nombreCompletoAfil);
			document.getElementById("grid_rut").style.display = "";
		});			
		
	}
	
	//--- Mantenedor de Documentos
	
	function mostrarDocumentacion()
	{
		var folio = Trim(document.ConsDesafiliacionForm.txt_NFolio.value);
		var rut = Trim(document.ConsDesafiliacionForm.txt_NRut.value);
		var dv = Trim(document.ConsDesafiliacionForm.txt_NNumVerif.value);
		var estado = document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value;
		var perfil = '<%=session.getAttribute("Perfil")%>';

		var contenidoTabla = "";
		var desabilitado = "";
		
		if(estado == "2" || estado == "5" || validarPerfilesFullAnalista(arregloPerfiles)){
			desabilitado = " disabled = 'true'";
		}
		
		if ( folio.length == 0 || rut.length == 0 || dv.length == 0)
		{
			alert("Para mostrar los documento debe buscar una solicitud valida.");
		
		}else{
			
			for(var i=0;i<vectorDocumentos.length;i++)
			{
				contenidoTabla = contenidoTabla + obtenerFilaTablaDoc(vectorDocumentos[i], i);
			}
			
			document.getElementById("mantenedorDocumentos").innerHTML = obtenerHeaderTablaDoc() + contenidoTabla + "<tr><td height='" + (300 - (25 * (vectorDocumentos.length + 1))) + "'></td></tr></table>"+
			"<table border='1' rules='groups' width='740'><tr><td align='right'>"+
			"<input type='button' name='btn_DocCancelar' id='btn_DocCancelar' class='btn_limp' value='Cancelar' onclick='validaDocCancelar();'/>"+
			"&nbsp;&nbsp;&nbsp;"+
			"<input type='button' name='btn_DocGuardar' id='btn_DocGuardar' class='btn_limp' value='Guardar' " + desabilitado + "onclick='guardarDocumentos();'/>"+
			"</td></tr></table></td></tr></table>";
	
			document.getElementById("fondoNegro").style.visibility = "visible";
			document.getElementById("mantenedorDocumentos").style.visibility = "visible";
			
			for(var i=0;i<vectorDocumentos.length;i++){
				obtenerComboEstadosDoc(vectorDocumentos[i].estadoDocumento, i, vectorDocumentos[i].estadoDocumentoAux);
			}
		}
	}
	
	function obtenerHeaderTablaDoc()
	{
		return 	"<table><tr><td align='center'><strong>Mantenedor de Documentación</strong></td></tr><tr><td><table border='1' rules='groups'>"+
				"<tr><td width='50'></td><td width='200'><strong>N° Folio:</strong></td><td width='475'>"+document.ConsDesafiliacionForm.lbl_DocFolio.value+"</td>"+
				"</tr><tr><td></td><td><strong>N° RUT:</strong></td><td>"+document.ConsDesafiliacionForm.lbl_DocRUT.value+"</td></tr><tr><td></td><td>"+
				"<strong>Nombre:</strong></td><td>"+document.ConsDesafiliacionForm.lbl_DocNombre.value+"</td></tr></table>"+
				"<table width='100%' align='center' cellpadding='0' cellspacing='1' height='300'>"+
				"<tr>"+
	            	"<td height='20' width='10' bgcolor='#1C74A9' class='textobold' style='color: #fff; text-align: center'></td>"+
	            	"<td height='20' width='200' bgcolor='#1C74A9' class='textobold' style='color: #fff; text-align: center'>Tipo Documento</td>"+
	            	"<td height='20' width='150' bgcolor='#1C74A9' class='textobold' style='color: #fff; text-align: center' colspan='2'>Estado Documento</td>"+
	            	"<td height='20' width='100' bgcolor='#1C74A9' class='textobold' style='color: #fff; text-align: center'>Observaciones</td>"+
	        	"</tr>";
	}
	
	function obtenerFilaTablaDoc(dato, i){
	
		var perfil = '<%=session.getAttribute("Perfil")%>';
		var estado = document.ConsDesafiliacionForm.dbx_EstSolicitudAux.value;
		
		var asterisco = "";
		var desabilitado = "";
		
		if(dato.obligatorio == 1 && dato.alta == 1){
			asterisco = " *";
		}

		if(dato.alta == 0 || estado == "2" || estado == "5" || validarPerfilesFullAnalista(arregloPerfiles)){
			desabilitado = " disabled = 'true'";
		}
	
		var texto =  " <tr> "+
							"<td height='20'></td>"+
							"<td height='20' class='texto' align='center'>"+obtenerDescripcion(arregloTipos, dato.tipoDocumento)+ asterisco + "</td>"+
							"<td height='20' class='texto' align='center'>"+obtenerDescripcion(arregloEstados, dato.estadoDocumento)+"</td>"+
							"<td height='20'>"+"<select property='dbx_EstadoDoc_"+i+"' id='dbx_EstadoDoc_"+i+"' styleClass='combobox' " + desabilitado + ">"+"</select>"+"</td>"+
							"<td height='20'>"+"<input size='40' type='text' name='txt_ObservacionesDoc' id='txt_ObservacionesDoc' maxlength='100' onkeypress='Upper(this,\"A\")' value='"+Trim(dato.observaciones)+"' "+ desabilitado +"></td>"+
						"</tr>";
		return texto;
	}
	
	function validaDocCancelar(){
		
		var cambios = false;
		
		for(var i=0;i<vectorDocumentos.length;i++)
		{
			if(document.getElementById("dbx_EstadoDoc_"+i).value != (vectorDocumentos[i].estadoDocumentoAux + "") ||
				Trim(document.ConsDesafiliacionForm.txt_ObservacionesDoc.value) != Trim(vectorDocumentos[i].observaciones))
			{
				cambios = true;
			}
		}

		if(cambios)
		{
			var respuesta = confirm("Ha ingresado información en el Mantenedor de Documentos. ¿Está seguro que desea continuar?");
					
			if( respuesta == true){
				document.getElementById("mantenedorDocumentos").style.visibility = "hidden";
				document.getElementById("fondoNegro").style.visibility = "hidden";
			}
		}else{		
			document.getElementById("mantenedorDocumentos").style.visibility = "hidden";
			document.getElementById("fondoNegro").style.visibility = "hidden";
		}	
	}
	
	function guardarDocumentos(){
		
		var cambioEst = false;
		if(validaGuardarDocumentos()){
			
			for(var i=0;i<vectorDocumentos.length;i++)
			{
				vectorDocumentos[i].estadoDocumentoAux	=	document.getElementById("dbx_EstadoDoc_"+i).value;
				vectorDocumentos[i].observaciones 		=	document.ConsDesafiliacionForm.txt_ObservacionesDoc.value;
				
				if(document.getElementById("dbx_EstadoDoc_"+i).value == 2 || document.getElementById("dbx_EstadoDoc_"+i).value == 3)
				{
					cambioEst = true;
				}
			}
			
			document.getElementById("mantenedorDocumentos").style.visibility = "hidden";
			document.getElementById("fondoNegro").style.visibility = "hidden";
		}
		
		if(cambioEst == true){
			//cambiar solicitud desafiliacion a estado 2	
			cambioEstDocFlag = true;
			updateEstadoSolDWR();	
		}
	}
	
	function validaGuardarDocumentos(){
		
		for(var i=0;i<vectorDocumentos.length;i++)
		{
			if(vectorDocumentos[i].obligatorio == 1){
			
				if (document.getElementById("dbx_EstadoDoc_"+i).value == 0){
					alert("Debe seleccionar un Estado para cada documento obligatorio marcado con '*'");
					return false; 
				}
			}
		}
		return true;
	}
	
	function obtenerComboEstadosDoc(tipo, i, sel){
	
		var cmb = document.getElementById("dbx_EstadoDoc_"+i);
	
		ModSolDesafiliacionDWR.getEstadosDestinoPosiblesDoc(tipo, function(data)
		{
			var resp = null;
			resp = data;
			
			var cod = "0";
			var txt = "";
			
			for(var j=0; j<resp.length; j++)
			{
				var item = null;
				item = resp[j];
			
				cod = item.codigo;
				txt = item.glosa;

				cmb.options[j] = new Option(txt, cod);
				
				if(sel == cod){
					cmb.options[j].selected = true;
				}
			}
		});
	}

	function IsValidTime(timeStr)
	{
		// Checks if time is in HH:MM:SS AM/PM format.
		// The seconds and AM/PM are optional.
		var timeObj=timeStr.value;
		if(timeStr!=null && timeStr!=""){
			var timePat = /^\d{1,2}:\d{2}([ap]m)?$/;
			var matchArray = timeObj.match(timePat);
			if (matchArray == null) {
				alert("La hora no esta en un formato valido.");
				timeStr.value="";
				return false;
			}
			hour = timeObj.substring(0,2);
			minute = timeObj.substring(3,5);
			if (hour < 0  || hour > 23) {
				alert("La hora debe estar entre 0 y 23");
				timeStr.value="";
				return false;
			}
			if (minute<0 || minute > 59) {
				alert ("Minutos deben estar entre 0 y 59.");
				timeStr.value="";
				return false;
			}
		}
		return true;
	}	
	//FIN REQ5348	
</script>
</head>

<body onload="bloqueaCampos();">
<html:form action="/consDesafiliacion.do">

	<input type="hidden" name="dbx_EstSolicitudAux" value="0">
	<input type="hidden" name="opcion" value="0">
	
	<input type="hidden" name="idPersona" value="0">
	<input type="hidden" name="idSolicitud" value="0">
	<input type="hidden" name="idAfiliadoAgrupacion" value="0">
	<input type="hidden" name="idSecuenciaTelefonoPart" value="0">
	<input type="hidden" name="idSecuenciaTelefonoCelu" value="0">
	<input type="hidden" name="idSecuenciaEmail" value="0">
	<input type="hidden" name="idSecuenciaEmailComercial" value="0">	
	<input type="hidden" name="idSecuenciaDireccionPart" value="0">
	<input type="hidden" name="dbx_MotDesafiliacion" value="0">	
	<input type="hidden" name="existeCambio" value="0"> 
	<!-- Id Documentos -->
	<input type="hidden" name="lbl_DocFolio" value="">
	<input type="hidden" name="lbl_DocRUT" value="">
	<input type="hidden" name="lbl_DocNombre" value="">
	
	<input type="hidden" name="txt_codOficina" value="0">
	
	<div id="grid_rut" style="display: none; position: absolute; width: 350px; height: 155px; margin-top: 140px; margin-left: 435px; background-color: #F2F2F2">
		<table width="345">	
			<tr>
				<td colspan="3" align="right">
					<a href="#" align="right" onclick="esconderDiv();">Volver</a>
				</td>
			</tr>
		</table>
		<table width="345" style="background-color: #A9D0F5">
			<tr>
				<td>
					<input type="text" name="txt_nombreCompletoAfil" id="txt_nombreCompletoAfil" size="50" disabled="true" style="background-color: #A9D0F5"/>
				</td>
			</tr>	
		</table>
		<table width="345">
			<tr>
				<td width="50%">
					<p align="center"><font color="#1B2935">Folio</font></p>
				</td>
				<td colspan="2">
					<p align="center"> <font color="#1B2935">Estado Solicitud</font></p>
				</td>
			</tr>
			<tr>
				<td colspan="1">
					<a href="#txt_FolioPorRut" name="txt_FolioPorRut" id="txt_FolioPorRut" onclick="eligeFolio();">
						<input type="text" name="txt_FolioPorRut" id="txt_FolioPorRut" align="center" style="background-color: #F2F2F2"/>
					</a>
				</td>
				<td colspan="2">
					<input type="text" name="txt_TipoEstadoPorRut" id="txt_TipoEstadoPorRut" disabled="true;" style="background-color: #F2F2F2"/>
				</td>
			</tr>
		</table>
	</div>

	<div id="datosSolicitudPorRut" style="display: none; position: absolute; width: 350px; margin-top: 140px; margin-left: 435px; background-color: #F2F2F2">
	</div>
	
	<div id="caja_registro">
	<table width="970">
		<tr>
			<td colspan="3" align="right">
				<a href="#" align="right" onclick="validaCancelar();">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td width="100%" height="25">
			<table>
				<tr>
					<td><strong><p1>CONSULTA/MODIFICACION SOLICITUD DESAFILIACION TRABAJADOR INDEPENDIENTE</p1></strong> <br />
					<br />
					</td>
				</tr>
				<tr>
					<td>
						<strong>N° Folio *</strong> 
						<input type="text" name="txt_NFolio" id="txt_NFolio" size="10" maxlength="12" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"/>
						<strong>N° RUT *</strong>
						<input type="text" name="txt_NRut" id="txt_NRut" size="10" maxlength="9" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"/>
						<strong> - </strong>
						<input type="text" name="txt_NNumVerif" id="txt_NNumVerif" size="3" maxlength="1" onkeypress="Upper(this,'D')" style="text-transform: uppercase;" onchange="ValidadorRUT(document.ConsDesafiliacionForm.txt_NRut.value,this.value)"/>
						<input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onClick="buscarPorFiltro();" />
						<strong>Estado Solicitud</strong>
						<input type="text" name="txt_EstSolicitud" id="txt_EstSolicitud" disabled="true"/>
						<html:select property="dbx_EstSolicitud" styleClass="combobox" value="0">
							<html:option value="0">Seleccione</html:option>
							<!--<html:options collection="ListEstadoSolicitudAfiliacionBox"	property="codigo" labelProperty="glosa" />-->
						</html:select>
						<input type="button" name="btn_GuardarEst" id="btn_GuardarEst" class="btn_limp" value="Guardar" onClick="updateEstadoSolDWR();" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<input type="button" name="btn_Documentacion" id="btn_Documentacion" class="btn_menu" value="Documentacion" onClick="mostrarDocumentacion();" />
					</td>
				</tr>
			</table>
			
		<tr>
			<td height="250">
			<p><p2>1. Identificación del Trabajador Independiente</p2>
			
			<p>
			<table width="100%" border="1" rules="groups">
				
				<tr>
					<td width="16%"><strong>N° Folio *</strong></td>
					<td width="16%"><input type="text" name="txt_Folio"	id="txt_Folio" maxlength="12" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"/></td>
					<td colspan="2">&nbsp;</td>
					<td width="16%"><strong>N° RUT *</strong></td>
					<td width="16%"><input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"/></td>
					<td width="16%"><strong> - </strong> <input type="text"
						name="txt_NumVerif" id="txt_NumVerif" size="3" maxlength="1" onkeypress="Upper(this,'D')" style="text-transform: uppercase;"/></td>
					<td></td>
				</tr>
				
				<tr>
					<td><strong>Apellido Paterno *</strong></td>
					<td><input name="txt_ApePat" type="text" id="txt_ApePat" maxlength="50" onkeypress="Upper(this,'L')" style="text-transform: uppercase;" onchange="Cambios(1);"/></td>
					<td colspan="2">&nbsp;</td>
					<td><strong>Apellido Materno *</strong></td>
					<td><input type="text" name="txt_ApeMat" id="txt_ApeMat" maxlength="50" onkeypress="Upper(this,'L')" style="text-transform: uppercase;" onchange="Cambios(1);"/></td>
					<td><strong>Nombres *</strong></td>
					<td><input type="text" name="txt_Nombres" id="txt_Nombres" maxlength="100" onkeypress="Upper(this,'L')" style="text-transform: uppercase;" onchange="Cambios(1);"/></td>
				</tr>

				<tr>
					<td><strong>Teléfono Celular *</strong></td>
					<td>
						<input size="1" type="text" name="txt_codAreaCelular" id="txt_codAreaCelular" maxlength="2" onkeypress="Upper(this,'N')" style="text-transform: uppercase;" onchange="Cambios(1);"/>
						<input size="10" type="text" name="txt_TelCelular" id="txt_TelCelular" maxlength="8" onkeypress="Upper(this,'N')" style="text-transform: uppercase;" onchange="Cambios(1);"/>
					</td>
					<td colspan="2">&nbsp;</td>
					<td><strong>Teléfono de Contacto</strong></td>
					<td>
						<input size="1" type="text" name="txt_codAreaContacto" id="txt_codAreaContacto" maxlength="3" onkeypress="Upper(this,'N')" style="text-transform: uppercase;" onchange="Cambios(1);"/>
						<input size="10" type="text" name="txt_TelContacto" id="txt_TelContacto" maxlength="8" onkeypress="Upper(this,'N')" style="text-transform: uppercase;" onchange="Cambios(1);"/>
					</td>
					<td></td>
					<td></td>
				</tr>

				<tr>
					<td><strong>E-Mail *</strong></td>
					<td colspan="3">
						<input type="text" name="txt_Email"	id="txt_Email" size="50" maxlength="100" onkeypress="Upper(this,'M')" style="text-transform: uppercase;" onblur="validarEmail(this.value)" onchange="Cambios(1);"/>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_Calle"
						id="txt_Calle" size="50" maxlength="50" onkeypress="Upper(this,'A')" style="text-transform: uppercase;" onchange="Cambios(1);"/></td>
					<td height="34"><strong>Número *</strong></td>
					<td colspan="3"><input type="text" name="txt_Numero"
						id="txt_Numero" size="5" maxlength="6" onkeypress="Upper(this,'A')" style="text-transform: uppercase;" onchange="Cambios(1);"/></td>
				</tr>
				
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3"><input type="text" name="txt_PoblVilla"
						id="txt_PoblVilla" size="50" maxlength="50" onkeypress="Upper(this,'A')" style="text-transform: uppercase;" onchange="Cambios(1);"/></td>
					<td height="34"><strong>Departamento</strong></td>
					<td colspan="3"><input type="text" name="txt_Departamento"
						id="txt_Departamento" size="5" maxlength="5" onkeypress="Upper(this,'A')" style="text-transform: uppercase;" onchange="Cambios(1);"/></td>
				</tr>
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3"><html:select property="dbx_Region" styleClass="dbx_geo"
						value="0"
						onchange="Cambios(1);cargarProvincias(ConsDesafiliacionForm.dbx_Provincia,ConsDesafiliacionForm.dbx_Comuna,this.value,0);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListRegiones" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				
				<tr>	
					<td><strong>Provincia *</strong></td>
					<td colspan="3"><select name="dbx_Provincia" id="dbxProvincia" style="width:330px"
						onchange="cargarComunas(ConsDesafiliacionForm.dbx_Comuna,this.value,0);">
						<option value="0">Seleccione Región</option>
					</select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Comuna *</strong></td>
					<td colspan="3"><select name="dbx_Comuna" id="dbx_Comuna" style="width:330px"> 
						<option value="0">Seleccione Provincia</option>
					</select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="8">&nbsp;</tr>
				<tr></tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<p><p2>2. Desafiliación a CCAF La Araucana</p2></p>
			<table border="1" rules="groups">
				<tr>
					<td width="34%"><strong>Caja Compensación *</strong></td>
					<td width="34%" colspan="3">
						<html:select property="dbx_CajaCompensacion" styleClass="dbx_cajaCompensacion" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListCajas" property="codigo" labelProperty="glosa" />
						</html:select>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Fecha de Vigencia de Afiliación CCAF La Araucana *</strong></td>
					<td width="16%" colspan="3"><input size="10" type="text" name="txt_FecVigAfil" id="txt_FecVigAfil" 
						value='<%=session.getAttribute("FechaVigencia")%>' disabled="true"/>
					</td>
					<td></td>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr >
					<td height="4"><strong>Fecha Último Aporte *</strong></td>
					
					<td height="4">
						<input type="text" name="txt_FecUltApo" id="txt_FecUltAporte" class="datepick" disabled="true" size="10" onchange="Cambios(1);"/><!--
						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecUltApo);" id="img_FecUltApo">
					--></td>
					<td></td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr height="20">
					<td height="4"><strong>Motivo de desafiliación</strong></td>
					<td></td>
					<td></td>
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr height="20">
					<td height="4"><strong>Tipo de Motivo*</strong></td>
					<td height="4"><strong>Descripción*</strong></td>
					<td height="4"><strong>Observaciones</strong></td>
					<td colspan="4">&nbsp;</td>
				</tr>
				
				<tr height="20">
					<td width="34%">
						<html:select property="dbx_Motivo" styleClass="dbx_geo" value="0"
						onchange="cargarDescMotivo(ConsDesafiliacionForm.dbx_DescMotivo,this.value,0);">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListMotivos" property="codigo" labelProperty="glosa" />
						</html:select>
					</td>

					<td width="34%">
						<html:select property="dbx_DescMotivo"	styleClass="dbx_geo" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListDescMotivos" property="codigo" labelProperty="glosa" />
						</html:select>
					</td>
					
					<td width="34%">
						<input type="text" name="txt_Observaciones" id="txt_Observaciones" size="40" maxlength="100" onkeypress="Upper(this,'A')" style="text-transform: uppercase;">
					</td>
					<td colspan="4">&nbsp;</td>
				</tr>
				
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<p><p2>3. Antecedentes Proceso de Desafiliación</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td><strong>Rut Analista *</strong></td>
					<td width="16%"><strong><input type="text"
						name="txt_RutCaptador" id="txt_RutCaptador"	value='<%=session.getAttribute("IDAnalista")%>' disabled="true"
						size="8" maxlength="12" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"> </strong>
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno</strong></td>
					<td width="16%"><input name="txt_ApePatProm" type="text" id="txt_ApePatProm" maxlength="50"
						value='<%=session.getAttribute("ApePatAnalista")%>' onkeypress="Upper(this,'L')" style="text-transform: uppercase;" disabled="true" /></td>
					<td><strong>Apellido Materno</strong></td>
					<td><input type="text" name="txt_ApeMaProm"	id="txt_ApeMatProm" maxlength="50"
						value='<%=session.getAttribute("ApeMatAnalista")%>'	onkeypress="Upper(this,'L')" style="text-transform: uppercase;" disabled="true" /></td>
					<td><strong>Nombre Analista</strong></td>
					<td colspan="3"><input type="text" name="txt_NombreProm" id="txt_NombreProm" maxlength="100"
						value='<%=session.getAttribute("NombreAnalista")%>' onkeypress="Upper(this,'L')" style="text-transform: uppercase;" disabled="true" disabled="true" /></td>
				</tr>
				<tr>
					<td height="4"><strong>Oficina</strong></td>
					<td>
						<html:select property="dbx_Oficina"	styleClass="dbx_oficina" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListOficinas" property="codigo" labelProperty="glosa" />
						</html:select></td>
					<td colspan="5"></td>
				</tr>
				<tr>
				<tr>
					<td height="4"><strong>Fecha Ingreso</strong></td>
					<td width="16%"><input type="text" name="txt_FecIngr" id="txt_FecIngr" disabled="true" size="10" />
					</td>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<!-- Se cambia de nombre Fecha Firma por Fecha Solicitud (solo en formulario). -->
					<td height="4"><strong>Fecha &nbsp; Solicitud *</strong></td>
					<td height="4">
						<input type="text" name="txt_FecSolDesaf" id="txt_FecSolDesaf" class="datepick" disabled="true" size="10" onchange="Cambios(1);"/><!--
						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecSolDesaf);" id="img_FecSolDesaf">
					--></td>
					<td colspan="2" height="4"><strong>Hora Solicitud *</strong>(formato HH:MM) </td>
					<td>
						<input type="text" name="txt_Hora" id="txt_Hora" size="10" onchange="javascript:IsValidTime(txt_Hora)">
					</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="4"><strong>Comentarios </strong></td>
					<td height="4">
						<input type="text" name="txt_Comentarios" id="txt_Comentarios" size="50" maxlength="100" onkeypress="Upper(this,'A')">
					</td>
					<td colspan="5">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td style="font-size:11px;">(*campos obligatorios)</td>
		</tr>
		<tr>
			<td height="37" align="right" width="805">
				<label id="resultado"></label>
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelar();"/>
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_GuardarSol" id="btn_GuardarSol" class="btn_limp" value="Guardar" onclick="updateSolicitudDWR();"/>
			</td>
		</tr>
	</table>
	
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 900px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
	
		<div id="mantenedorDocumentos" style="visibility:hidden; position:absolute; margin-top: 100px; margin-left: 180px; width: 750px; height: 395px; background-color: #FFFFFF">
		</div>
	
	</div>
	
	<div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 900px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
		<table width="100%">
			<tr>
				<td height="200">
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<IMG border="0" src="/IndependientesWEB/images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	</div>

</div>
</html:form>
</body>
</html:html>