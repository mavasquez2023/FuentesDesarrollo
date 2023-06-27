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
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/ModSolicitudDWR.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>

<script type="text/javascript" src="/IndependientesWEB/dwr/interface/NewSolicitudDWR.js"></script>

<script type="text/javascript">
	
	var vectorDocumentos  = new Array();
	var arregloEstados = new Array();
	var arregloTipos = new Array();
	var arregloAgrupaciones = new Array();
	var arregloPerfiles = null;
	
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

		document.ModSolForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ModSolForm.submit();
	}
	
	//REQ5348
	function validaCamposAgrupacion(optAgrupa,razonSocialAgrup,rutAgrup, dv)
	{
		var i=0;
		for(j=0;j<optAgrupa.length;j++){
			if(optAgrupa[j].checked){
				i++;
			}
			if(optAgrupa[j].value==1 && optAgrupa[j].checked){		
				if(razonSocialAgrup == "0" ){
					alert("Falta ingresar el campo Razon Social Agrupación.");
					return false;
				}
				if(rutAgrup == "" || dv == "" ){
					alert("Falta ingresar el campo Rut o Digito Verificador de la Agrupación.");
					return false;
				}else{
					if(!ValidadorRUT(rutAgrup,dv)){
						return false;
					}
				}
				break;
			}
		}
		if(i==0){
			alert("Falta seleccionar si posee agrupación.");
			return false;
		}
		return true;		
	}
	//FIN REQ5348	
	
	/*Funcion que valida los campos obligatorios del formulario.*/
	function validaForm()
	{
		// Poner aqui las validaciones del formulario
		
		if(Trim(document.ModSolForm.txt_Folio.value) == "" ){
			alert("Falta ingresar el campo Folio.");
			return false;			
		}
		
		if(Trim(document.ModSolForm.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_NumVerif.value) == "" ){
			alert("Falta ingresar el campo Dígito Verificador.");
			return false; 
		}
		
		if(Trim(document.ModSolForm.txt_ApePat.value) == "" ){
			alert("Falta ingresar el campo Apellido Paterno.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_ApeMat.value) == "" ){
			alert("Falta ingresar el campo Apellido Materno.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_Nombres.value) == "" ){
			alert("Falta ingresar el campo Nombres.");
			return false;
		}
		
		if(document.ModSolForm.dbx_Nacionalidad.value == "0" ){
			alert("Falta ingresar el campo Nacionalidad.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_FecNac.value) == "" ){
			alert("Falta ingresar el campo Fecha de Nacimiento.");
			return false;
		}
		
		if(document.ModSolForm.dbx_Sexo.value == "0" ){
			alert("Falta ingresar el campo Tipo Sexo.");
			return false;
		}
		
		if(document.ModSolForm.dbx_EstCivil.value == "0" ){
			alert("Falta ingresar el campo Estado Civil.");
			return false;
		}
		
		if(document.ModSolForm.txt_codAreaCelular.value == "" ){ 
			alert("Falta ingresar el campo Dígito del Telefono Celular.");
			return false;
		}
		
		if(document.ModSolForm.txt_TelCelular.value == "" ){
			alert("Falta ingresar el campo Teléfono Celular.");
			return false;
		}
		
		//document.ModSolForm.txt_codAreaContacto.value == ""|| //no obligatorio
		//document.ModSolForm.txt_TelContacto.value == "" //no obligatorio
		
		if(document.ModSolForm.txt_Email.value == "" ){
			alert("Falta ingresar el campo Email.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_Calle.value) == "" ){
			alert("Falta ingresar el campo Calle del Item 1.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_Numero.value) == "" ){
			alert("Falta ingresar el campo Número del Item 1.");
			return false;
		}
		
		//Trim(document.ModSolForm.txt_PoblVilla.value) == "" //no obligatorio
		//Trim(document.ModSolForm.txt_Departamento.value == "" || //no obligatorio
		if(document.ModSolForm.dbx_Region.value == "0" ){
			alert("Falta ingresar el campo Región del Item 1.");
			return false;
		}
		
		if(document.ModSolForm.dbx_Provincia.value == "0" ){ 
			alert("Falta ingresar el campo Provincia del Item 1.");
			return false;
		}
		
		if(document.ModSolForm.dbx_Comuna.value == "0" ){
			alert("Falta ingresar el campo Comuna del Item 1.");
			return false;
		}
		
		if(document.ModSolForm.dbx_NivEstudios.value == "0" ){ 
			alert("Falta ingresar el campo Nivel de Estudios.");
			return false;
		}
		//REQ5348
		var optAgrupa		=	document.ModSolForm.rbt_Agrupacion;
		var razonSocialAgrup=	document.ModSolForm.dbx_RazonSocialAgrup.value;
		var rutAgrup		=	document.ModSolForm.txt_RutAgrup.value;
		var dv				=	document.ModSolForm.txt_NumVerifAgrup.value;
		//var tipoAgrupa=document.ModSolForm.dbx_TipoAgrup.value;		
		if(!validaCamposAgrupacion(optAgrupa,razonSocialAgrup,rutAgrup, dv)){
			return false;
		}					
		//FIN REQ5348	
		//document.ModSolForm.dbx_TitAcademico.value == "" //no obligatorio
		//document.ModSolForm.rbt_AgrupSi.checked == false //no obligatorio
		//document.ModSolForm.rbt_AgrupNo.checked == false //no obligatorio
		//document.ModSolForm.dbx_RazonSocialAgrup.value = "" //no obligatorio
		//document.ModSolForm.txt_RutAgrup.value == "" //no obligatorio
		//document.ModSolForm.txt_NumVerifAgrup.value == "" //no obligatorio
		//document.ModSolForm.dbx_TipoAgrup.value = 0 //no obligatorio
		if(document.ModSolForm.dbx_RegPrevisional.value == "0" ){
			alert("Falta ingresar el campo Régimen Previsional.");
			return false;
		}
		
		//document.ModSolForm.dbx_RegSalud.value = "0" //no obligatorio
		
		//Campos conyuge y numero de hijos ya no son obligatorios.
		/*
		if((document.ModSolForm.rbt_ConySi.checked == false && document.ModSolForm.rbt_ConyNo.checked == false) ){
			alert("Falta ingresar el campo Cónyuge.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_Hijos.value) == "" ){
			alert("Falta ingresar el campo Nº Hijos.");
			return false;
		}*/
		
		if(Trim(document.ModSolForm.txt_Actividad.value) == "" ){ 
			alert("Falta ingresar el campo Nombre de Actividad.");
			return false;
		}
		
		if((document.ModSolForm.rbt_HonSi.checked == false && document.ModSolForm.rbt_HonNo.checked == false) ){
			alert("Falta ingresar el campo Honorarios");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_CalleComerc.value) == "" ){ 
			alert("Falta ingresar el campo Calle del Item 2.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_NumeroComerc.value) == "" ){
			alert("Falta ingresar el campo Número del Item 2.");
			return false;
		}
		
		//document.ModSolForm.txt_PoblVillaComerc.value = "" //no obligatorio
		//document.ModSolForm.txt_DptoComerc.value = "" //no obligatorio
		//document.ModSolForm.txt_codAreaComerc.value == ""|| //no obligatorio
		//document.ModSolForm.txt_TelComerc.value = "" //no obligatorio
		if(document.ModSolForm.dbx_RegComerc.value == "0" ){
			alert("Falta ingresar el campo Región del Item 2.");
			return false;
		}
		
		if(document.ModSolForm.dbx_ProvinciaComerc.value == "0" ){ 
			alert("Falta ingresar el campo Provincia del Item 2.");
			return false;
		}
		
		if(document.ModSolForm.dbx_ComunaComerc.value == "0" ){
			alert("Falta ingresar el campo Comuna del Item 2.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_RentaImp.value) == "" ){
			alert("Falta ingresar el campo Renta Imponible.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_MontoUltimaCotizacion.value) == "" ){
			alert("Falta ingresar el campo Monto Ultima Cotización.");
			return false;
		}

		if(Trim(document.ModSolForm.txt_FechaUltimaCotizacion.value) == "" ){
			alert("Falta ingresar el campo Fecha Ultima Cotización.");
			return false;
		}
				
		//Trim(document.ModSolForm.txt_RentaCot.value) == "" ){
		if(Trim(document.ModSolForm.txt_ValorACot.value) == "" ){
			alert("Falta ingresar el campo Valor a Cotizar.");
			return false;
		}
		
		if(document.ModSolForm.dbx_CajaCompensacion.value == "0" ){
			alert("Falta ingresar el campo Caja de Compensación.");
			return false;
		}
		
		if(Trim(document.ModSolForm.txt_FecVigAfil.value) == "" ){
			alert("Falta ingresar el campo Fecha de Vigencia de Afiliación CCAF La Araucana");
			return false;
		}
		
		/*campos del punto 5, campos existen y ahora se consideran*/
		if(
			Trim(document.ModSolForm.txt_RutEjec.value) == "" || 
			Trim(document.ModSolForm.txt_ApePatEjec.value) == "" || 
			Trim(document.ModSolForm.txt_ApeMatEjec.value) == "" ||
			Trim(document.ModSolForm.txt_NombreEjec.value) == "" 
		){
			alert("Debe ingresar el Rut del promotor y presionar el botón Buscar.");
			return false;
		}

		//document.ModSolForm.txt_Sucursal.value == "" 
		//document.ModSolForm.txt_FecIngr.value == ""
		//document.ModSolForm.txt_FecFirma.value == ""
	
		
		if(Trim(document.ModSolForm.txt_Hora.value) == "" ){
			alert("Falta ingresar el campo Hora de Captación");
			return false;
		}
	
		if(ValidaMayoriaDeEdad(document.ModSolForm.txt_FecNac.value))
		{
			if(ValidadorRUT(document.ModSolForm.txt_NRut.value,document.ModSolForm.txt_NNumVerif.value))
			{
				if(Trim(document.ModSolForm.txt_Email.value) == "" )
				{
					return true;
				}else{
					if(validarEmail(document.ModSolForm.txt_Email.value))
					{
						return true;
					}
				}
			}
		}
	}
	
	/*Funcion que valida el boton cancelar cuando se ha realizado una accion previa.*/
	function validaCancelar()
	{
		var folio = Trim(document.ModSolForm.txt_Folio.value);
		var rut = Trim(document.ModSolForm.txt_Rut.value);
		
		if ( folio == "" && rut == "")
		{
			enviaFormulario(1);
		}else{
			//campos a validar
			ModSolicitudDWR.obtenerSolicitud(folio, rut, function(data)
			{
					var solicitud = null;
			   		solicitud = data;
			   		var cambiosDocumentos = false;
						
					var personaC = null;
					var telPartC = null
					var telCelC = null;
					var telComC = null;
					var emailC = null;
					var direcPartC = null;
					var direcComerC = null;
					var afiliadoC = null;
					var grupoFamC = null;
					var ingresoC = null;
					var ejecutivoC = null;
					var solicitudvoC = null;
					
					personaC = solicitud.personaVO;
					telPartC = solicitud.telefonoPartVO;
					telCelC = solicitud.telefonoCeluVO;
					telComC = solicitud.telefonoComerVO;
					emailC = solicitud.emailVO;
					direcPartC = solicitud.direccionPartVO;
					direcComerC = solicitud.direccionComerVO;
					afiliadoC = solicitud.afiliadoVO;
					grupoFamC = solicitud.grupoFamiliarVO;
					ingresoC = solicitud.ingresoEconomicoVO;
					ejecutivoC = solicitud.analistaVO;
					solicitudvoC = solicitud.solicitudVO;
					listDocumentos = solicitud.listaDocumentoVO;
					
					for(var i=0;i<vectorDocumentos.length;i++)
					{
						if(listDocumentos[i].estadoDocumento != (vectorDocumentos[i].estadoDocumentoAux + "") ||
							Trim(listDocumentos[i].observacionesDocumento) != Trim(vectorDocumentos[i].observaciones))
						{
							cambiosDocumentos = true;
						}
					}
					
					if(
						Trim(document.ModSolForm.txt_Folio.value) != solicitudvoC.folio ||
						Trim(document.ModSolForm.txt_Rut.value) != personaC.idDocumento ||
						Trim(document.ModSolForm.txt_NumVerif.value) !=  personaC.digVerificador ||
						Trim(document.ModSolForm.txt_ApePat.value) != personaC.apellidoPaterno ||
						Trim(document.ModSolForm.txt_ApeMat.value) != personaC.apellidoMaterno ||
						Trim(document.ModSolForm.txt_Nombres.value) != personaC.nombres ||
						document.ModSolForm.dbx_Nacionalidad.value != personaC.tipoNacionalidad ||
						Trim(document.ModSolForm.txt_FecNac.value) != personaC.fechaNacimiento ||
						document.ModSolForm.dbx_Sexo.value != personaC.tipoSexo ||
						document.ModSolForm.dbx_EstCivil.value != afiliadoC.tipoEstado ||
						document.ModSolForm.txt_codAreaCelular.value != telCelC.codArea ||
						document.ModSolForm.txt_TelCelular.value != telCelC.nroTelefono ||
						document.ModSolForm.txt_Email.value != emailC.direccMail ||
						Trim(document.ModSolForm.txt_Calle.value) != direcPartC.glosCalle ||
						Trim(document.ModSolForm.txt_Numero.value) != direcPartC.numDireccion ||
						document.ModSolForm.dbx_Region.value != direcPartC.region ||
						//document.ModSolForm.dbx_Provincia.value != 
						//document.ModSolForm.dbx_Comuna.value != 
						document.ModSolForm.dbx_NivEstudios.value != afiliadoC.tipoNivelEduc ||
						document.ModSolForm.dbx_RegPrevisional.value != afiliadoC.tipoAfp ||
						Trim(document.ModSolForm.txt_Actividad.value) != ingresoC.actEconom ||
						//(document.ModSolForm.rbt_HonSi.checked != false && 
						//document.ModSolForm.rbt_HonNo.checked != false) ||
						Trim(document.ModSolForm.txt_CalleComerc.value) != direcComerC.glosCalle ||
						Trim(document.ModSolForm.txt_NumeroComerc.value) != direcComerC.numDireccion ||
						document.ModSolForm.dbx_RegComerc.value != direcComerC.region ||
						//document.ModSolForm.dbx_ProvinciaComerc.value != 
						//document.ModSolForm.dbx_ComunaComerc.value != 
						Trim(document.ModSolForm.txt_RentaImp.value) != ingresoC.rentaImponible ||
						Trim(document.ModSolForm.txt_MontoUltimaCotizacion.value) != ingresoC.montoUltimaCotizacion ||						
						Trim(document.ModSolForm.txt_ValorACot.value) != afiliadoC.montoCotizar ||
						document.ModSolForm.dbx_CajaCompensacion.value != solicitudvoC.tipoCajaOrigen ||
						Trim(document.ModSolForm.txt_FecVigAfil.value) != solicitudvoC.fechaVigencia ||
						Trim(document.ModSolForm.txt_FecFirma.value) != solicitudvoC.fechaFirma ||
						cambiosDocumentos
			
			){
				var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
				
				if( respuesta == true){
					enviaFormulario(1);
				}
			}else{
				enviaFormulario(1);
			}
			});	
		}	
	}
	
	/*Funcion que obtiene informacion de una solicitud filtrando por folio y rut.*/
	function obtenerSolicitudDWR(){
		
		var folio = Trim(document.ModSolForm.txt_NFolio.value);
		var rut = Trim(document.ModSolForm.txt_NRut.value);
		var dv = Trim(document.ModSolForm.txt_NNumVerif.value);
		
		if ( folio.length == 0 || rut.length == 0 || dv.length == 0){
		
			alert("Para cargar una solicitud debe ingresar valores para el campo N° Folio, N° RUT y Dígito Verificador.");
		
		}else
		{
			if(ValidadorRUT(rut,dv))
			{
				document.getElementById("pantallaDeCarga").style.visibility = "visible";
			
				DWREngine.setAsync(false);
				ModSolicitudDWR.obtenerSolicitud(folio, rut, function(data){
				
					var solicitud = null;
		   		
					solicitud = data;
					
					//alert("alertaaaa"+solicitud.resultado);
					
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
						/*document.ModSolForm.txt_Folio.value = 
						document.ModSolForm.txt_Rut.value = 
											
						document.ModSolForm.txt_ApePat.value = persona.apellidoPaterno;
						document.ModSolForm.txt_ApeMat.value = persona.apellidoMaterno;
						document.ModSolForm.txt_Nombres.value = persona.nombres;*/
						//dbx_Nacionalidad
						
						//---------------------------------------------------------------
						
						document.ModSolForm.opcion;
						document.ModSolForm.resultado;
						
						//document.ModSolForm.dbx_EstSolicitud.value = solicitudvo.tipoEstadoSolicitud;
						document.ModSolForm.dbx_EstSolicitudAux.value = solicitudvo.tipoEstadoSolicitud;
						document.ModSolForm.txt_EstSolicitud.value = solicitudvo.desTipoEstadoSolicitud;
						
						//-1. Encabezado de Busqueda
						document.ModSolForm.txt_NFolio;
						document.ModSolForm.txt_NRut;
						document.ModSolForm.txt_NNumVerif;
						
						//0. Informacion del Formulario
						document.ModSolForm.txt_Folio.value = solicitudvo.folio;
						
						//1. Identificación
						document.ModSolForm.txt_Rut.value = persona.idDocumento;
						document.ModSolForm.txt_NumVerif.value = persona.digVerificador;
						document.ModSolForm.txt_ApePat.value = persona.apellidoPaterno;
						document.ModSolForm.txt_ApeMat.value = persona.apellidoMaterno;
						document.ModSolForm.txt_Nombres.value = persona.nombres;
						document.ModSolForm.dbx_Nacionalidad.value = persona.tipoNacionalidad;
						document.ModSolForm.txt_FecNac.value = persona.fechaNacimiento;
						document.ModSolForm.dbx_Sexo.value = persona.tipoSexo;
						document.ModSolForm.dbx_EstCivil.value = afiliado.tipoEstado;
						document.ModSolForm.txt_codAreaCelular.value = telCel.codArea;
						document.ModSolForm.txt_TelCelular.value = telCel.nroTelefono;
						document.ModSolForm.txt_codAreaContacto.value = telPart.codArea;
						document.ModSolForm.txt_TelContacto.value = telPart.nroTelefono;
						document.ModSolForm.txt_Email.value = email.direccMail;
						document.ModSolForm.txt_Calle.value = direcPart.glosCalle;
						document.ModSolForm.txt_Numero.value = direcPart.numDireccion;
						document.ModSolForm.txt_PoblVilla.value = direcPart.poblacionVilla;
						document.ModSolForm.txt_Departamento.value = direcPart.dpto;
						
						document.ModSolForm.dbx_Region.value = direcPart.region;
						cargarProvincias(document.ModSolForm.dbx_Provincia,document.ModSolForm.dbx_Comuna,direcPart.region,direcPart.ciudad);
						cargarComunas(document.ModSolForm.dbx_Comuna,direcPart.ciudad,direcPart.comuna);
						
						document.ModSolForm.dbx_NivEstudios.value = afiliado.tipoNivelEduc;
						document.ModSolForm.dbx_TitAcademico.value = afiliado.tipoProfesion;
						/*document.ModSolForm.rbt_Agrupacion.value
						document.ModSolForm.dbx_RazonSocialAgrup.value
						document.ModSolForm.txt_RutAgrup.value
						document.ModSolForm.txt_NumVerifAgrup.value
						document.ModSolForm.dbx_TipoAgrup.value*/
						document.ModSolForm.dbx_RegPrevisional.value = afiliado.tipoAfp;
						document.ModSolForm.dbx_RegSalud.value = afiliado.tipoRegSalud;
	
						if(grupoFam.conyugue == -1){
							document.ModSolForm.rbt_ConyNo.checked = false;
							document.ModSolForm.rbt_ConySi.checked = false;
						}else{
							if (grupoFam.conyugue == 0){
								document.ModSolForm.rbt_ConyNo.checked = true;
							}else{
								document.ModSolForm.rbt_ConySi.checked = true;
							}
						}
						//REQ5348	
						if(afiliado.idSecuenciaAgrupacion != 0){
							document.ModSolForm.dbx_RazonSocialAgrup.value = afiliado.idSecuenciaAgrupacion;								
							cambiaRutAgrup();										
							document.ModSolForm.rbt_Agrupacion[0].checked = true;
							document.ModSolForm.rbt_Agrupacion[1].checked = false;
						}else{
							document.ModSolForm.dbx_RazonSocialAgrup.value = 0;
							document.ModSolForm.rbt_Agrupacion[0].checked = false;		
							document.ModSolForm.rbt_Agrupacion[1].checked = true;
							document.ModSolForm.txt_RutAgrup.value="";
							document.ModSolForm.txt_NumVerifAgrup.value="";
						}

						if(emailComercial!=null){
							if(emailComercial.direccMail!=null && emailComercial.direccMail!=""){
								document.ModSolForm.txt_EmailComercial.value = emailComercial.direccMail;
							}else{
								document.ModSolForm.txt_EmailComercial.value = "";
							}
							if(emailComercial.idSecuenciaEmail!=null&& emailComercial.idSecuenciaEmail!="0"){
								document.ModSolForm.idSecuenciaEmailComercial.value = emailComercial.idSecuenciaEmail;
							}else{
								document.ModSolForm.idSecuenciaEmailComercial.value = "0";	
							}
						}else{
							document.ModSolForm.txt_EmailComercial.value = "";
							document.ModSolForm.idSecuenciaEmailComercial.value = "0";							
						}							
						//FIN REQ5348	
						document.ModSolForm.txt_Hijos.value = grupoFam.cantHijos;
						
						//2. Informacion Actividad Comercial
						document.ModSolForm.txt_Actividad.value = ingreso.actEconom;
						
						if (ingreso.honorario == 0){
							document.ModSolForm.rbt_HonNo.checked = true;
						}else{
							document.ModSolForm.rbt_HonSi.checked = true;
						}
						
						document.ModSolForm.txt_CalleComerc.value = direcComer.glosCalle;
						document.ModSolForm.txt_NumeroComerc.value = direcComer.numDireccion;
						document.ModSolForm.txt_PoblVillaComerc.value = direcComer.poblacionVilla;
						document.ModSolForm.txt_DptoComerc.value = direcComer.dpto;
						document.ModSolForm.txt_codAreaComerc.value = telCom.codArea;
						document.ModSolForm.txt_TelComerc.value = telCom.nroTelefono;
						
						document.ModSolForm.dbx_RegComerc.value = direcComer.region;
						cargarProvincias(document.ModSolForm.dbx_ProvinciaComerc,document.ModSolForm.dbx_ComunaComerc,direcComer.region,direcComer.ciudad);
						cargarComunas(document.ModSolForm.dbx_ComunaComerc,direcComer.ciudad,direcComer.comuna);
						
						//3. Informacion de Renta
						document.ModSolForm.txt_RentaImp.value = ingreso.rentaImponible;
						document.ModSolForm.txt_RentaCot.value = ingreso.rentaCotizada;
						document.ModSolForm.txt_ValorACot.value = afiliado.montoCotizar;
						document.ModSolForm.txt_MontoUltimaCotizacion.value = ingreso.montoUltimaCotizacion;						
						document.ModSolForm.txt_FechaUltimaCotizacion.value = ingreso.fecUltCotizacion;
						//5. Afiliacion y Desafiliacion
						document.ModSolForm.dbx_CajaCompensacion.value = solicitudvo.tipoCajaOrigen;
						
						//Fecha de Vigencia.
						//if('<%=session.getAttribute("Perfil")%>' == "2") //20120709 - ANA - ANTIGUO PERFILAMIENTO
						if (validarPerfiles(arregloPerfiles, "2"))
						{
							desBloqueaCampoIndiv(document.ModSolForm.txt_FecVigAfil);
							document.ModSolForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
						
						}else{
						
							document.ModSolForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
							bloqueaCampoIndiv(document.ModSolForm.txt_FecVigAfil);
						}
						//Fecha de resolucion
						if(solicitudvo.resolucionDirectorio!=null){
							document.ModSolForm.txt_ResolucionDirectorio.value=	solicitudvo.resolucionDirectorio;					
						}							
						/*document.ModSolForm.dbx_CajaCompensacion.value
						document.ModSolForm.dbx_MotDesafiliacion.value*/
						
						//6. Antecedentes Proceso de Afiliación (Solo informativos)
						//if('<%=session.getAttribute("Perfil")%>' == "2") //20120709 - ANA - ANTIGUO PERFILAMIENTO
						if (validarPerfiles(arregloPerfiles, "2"))
						{
							desBloqueaCampoIndiv(document.ModSolForm.txt_RutEjec);
							document.ModSolForm.txt_RutEjec.value = ejecutivo.idAnalista;
						}else{
							document.ModSolForm.txt_RutEjec.value = ejecutivo.idAnalista;
						}
						document.ModSolForm.txt_ApePatEjec.value = ejecutivo.apellidoPaterno;
						document.ModSolForm.txt_ApeMatEjec.value = ejecutivo.apellidoMaterno;
						document.ModSolForm.txt_NombreEjec.value = ejecutivo.nombres;
						document.ModSolForm.txt_codOficina.value = solicitudvo.oficina;
						document.ModSolForm.txt_Sucursal.value = solicitudvo.desOficina;
						document.ModSolForm.txt_FecIngr.value = solicitudvo.fechaIngreso;
						document.ModSolForm.txt_Hora.value = solicitudvo.horaCaptacion;						
						//Se agrega fecha de firma.
						if(solicitudvo.fechaFirma == "01/01/1900"){
							document.ModSolForm.txt_FecFirma.value = " ";
						}else{
							document.ModSolForm.txt_FecFirma.value = solicitudvo.fechaFirma;
						}	

						//Trae datos del analista.(El que se loguea).
						desBloqueaCampoIndiv(document.ModSolForm.txt_RutCaptador);
						desBloqueaCampoIndiv(document.ModSolForm.txt_ApePatCaptador);
						desBloqueaCampoIndiv(document.ModSolForm.txt_ApeMatCaptador);
						desBloqueaCampoIndiv(document.ModSolForm.txt_NombreCaptador);						
						
						document.ModSolForm.txt_RutCaptador.value = analistvo.idAnalista;
						document.ModSolForm.txt_ApePatCaptador.value = analistvo.apellidoPaterno;
						document.ModSolForm.txt_ApeMatCaptador.value = analistvo.apellidoMaterno;
						document.ModSolForm.txt_NombreCaptador.value = analistvo.nombres;
						
						bloqueaCampoIndiv(document.ModSolForm.txt_RutCaptador);
						bloqueaCampoIndiv(document.ModSolForm.txt_ApePatCaptador);
						bloqueaCampoIndiv(document.ModSolForm.txt_ApeMatCaptador);
						bloqueaCampoIndiv(document.ModSolForm.txt_NombreCaptador);
						//fin analista.
						
						//4. Campos Ocultos (ID)
						document.ModSolForm.idPersona.value = persona.idPersona;
						document.ModSolForm.idSolicitud.value = solicitudvo.idSolicitud;
						document.ModSolForm.idAfiliadoAgrupacion.value = solicitudvo.idAfiliadoAgrupacion;
						document.ModSolForm.idGrupoFam.value = grupoFam.idGrupoFam;
						document.ModSolForm.idIngEconom.value = ingreso.idIngEconom;
						document.ModSolForm.idSecuenciaTelefonoPart.value = telPart.idSecuenciaTelefono;
						document.ModSolForm.idSecuenciaTelefonoCelu.value = telCel.idSecuenciaTelefono;
						document.ModSolForm.idSecuenciaEmail.value = email.idSecuenciaEmail;
						document.ModSolForm.idSecuenciaDireccionPart.value = direcPart.idSecuenciaDireccion;
						document.ModSolForm.idSecuenciaDireccionComerc.value = direcComer.idSecuenciaDireccion;
						document.ModSolForm.idSecuenciaTelefonoComerc.value = telCom.idSecuenciaTelefono;
					
						//Busqueda de listado
						ModSolicitudDWR.getEstadosDestinoPosibles(solicitudvo.tipoEstadoSolicitud, function(data)
						{
							var resp = null;
							resp = data;
							
							var cod = "0";
							var txt = "";
							
							var cmb = document.ModSolForm.dbx_EstSolicitud;
	
							cmb.options.length = 0;
							cmb.options[0] = new Option("Seleccione..." , "0");
	
							//if('<%=session.getAttribute("Perfil")%>' == "1" && document.ModSolForm.dbx_EstSolicitudAux.value == 5) //20120709 - ANA - ANTIGUO PERFILAMIENTO
							if (validarPerfilesFullAnalista(arregloPerfiles) && document.ModSolForm.dbx_EstSolicitudAux.value == 5)
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
						
						document.ModSolForm.lbl_DocFolio.value = solicitudvo.folio;
						document.ModSolForm.lbl_DocRUT.value = separadorDeMiles(persona.idDocumento) + "-" + persona.digVerificador;
						document.ModSolForm.lbl_DocNombre.value = persona.nombres + " " + persona.apellidoPaterno + " " + persona.apellidoMaterno;
						
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
		
		document.ModSolForm.existeCambio.value = "0";
	}
	
	/*funcion que hace un update al estado de la solicitud.*/
	function updateEstadoSolDWR(){
	
		var estado = document.ModSolForm.dbx_EstSolicitud.value;
		//var estadoAux = document.ModSolForm.dbx_EstSolicitudAux.value;
		//var fecVigencia=document.ModSolForm.txt_FecVigAfil.value;
		var fecIngreso=document.ModSolForm.txt_FecIngr.value;		
		var folio = document.ModSolForm.txt_Folio.value;
		var rut = document.ModSolForm.txt_Rut.value;
	
		/*if(estado == estadoAux){
		
			alert("No ha realizado cambios al estado de solicitud cargado.");
			
		}else{*/
		if(document.ModSolForm.dbx_EstSolicitud.value == 0){
			alert("Error, Debe ingresar un estado válido.");
			return false;
		}
		
			if (folio == ""){
			
				alert("Debe buscar una solicitud para modificar su estado.");
			
			}else{
			
				document.getElementById("pantallaDeCarga").style.visibility = "visible";
			
				ModSolicitudDWR.updateEstadoSol(folio, estado, rut, fecIngreso, function(data){
				
					var resp = null;
					
					resp = data;
					
					switch(resp){
					
						case 0: document.ModSolForm.txt_EstSolicitud.value = document.ModSolForm.dbx_EstSolicitud.options[document.ModSolForm.dbx_EstSolicitud.selectedIndex].text;
								
								//Busqueda de listado
								ModSolicitudDWR.getEstadosDestinoPosibles(estado, function(data)
								{
									var resp = null;
									resp = data;
									
									var cod = "0";
									var txt = "";
									
									var cmb = document.ModSolForm.dbx_EstSolicitud;
			
									cmb.options.length = 0;
									cmb.options[0] = new Option("Seleccione..." , "0")
									
									//if('<%=session.getAttribute("Perfil")%>' == "1" && document.ModSolForm.dbx_EstSolicitudAux.value == 5)//20120709 - ANA - ANTIGUO PERFILAMIENTO
									if( validarPerfilesFullAnalista(arregloPerfiles) && document.ModSolForm.dbx_EstSolicitudAux.value == 5)
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
								
								alert("Estado de Solicitud modificado con exito.");
								break;
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
		var folio = Trim(document.ModSolForm.txt_NFolio.value);
		
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		DWREngine.setAsync(false);
		ModSolicitudDWR.obtenerSolicitudPorFolio(folio, function(data){
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
				/*document.ModSolForm.txt_ApePat.value = persona.apellidoPaterno;
				document.ModSolForm.txt_ApeMat.value = persona.apellidoMaterno;
				document.ModSolForm.txt_Nombres.value = persona.nombres;*/
				//dbx_Nacionalidad
				analistvo=	solicitud.analistaVO;		
				//---------------------------------------------------------------
				
				document.ModSolForm.opcion;
				document.ModSolForm.resultado;
						
				//document.ModSolForm.dbx_EstSolicitud.value = solicitudvo.tipoEstadoSolicitud;
				document.ModSolForm.dbx_EstSolicitudAux.value = solicitudvo.tipoEstadoSolicitud;
				document.ModSolForm.txt_EstSolicitud.value = solicitudvo.desTipoEstadoSolicitud;
						
				//-1. Encabezado de Busqueda
				document.ModSolForm.txt_NFolio;
				document.ModSolForm.txt_NRut.value = persona.idDocumento;
				document.ModSolForm.txt_NNumVerif.value = persona.digVerificador;
						
				//0. Informacion del Formulario
				document.ModSolForm.txt_Folio.value = solicitudvo.folio;
						
				//1. Identificación
				document.ModSolForm.txt_Rut.value = persona.idDocumento;
				document.ModSolForm.txt_NumVerif.value = persona.digVerificador;
				document.ModSolForm.txt_ApePat.value = persona.apellidoPaterno;
				document.ModSolForm.txt_ApeMat.value = persona.apellidoMaterno;
				document.ModSolForm.txt_Nombres.value = persona.nombres;
				document.ModSolForm.dbx_Nacionalidad.value = persona.tipoNacionalidad;
				document.ModSolForm.txt_FecNac.value = persona.fechaNacimiento;
				document.ModSolForm.dbx_Sexo.value = persona.tipoSexo;
				document.ModSolForm.dbx_EstCivil.value = afiliado.tipoEstado;
				document.ModSolForm.txt_codAreaCelular.value = telCel.codArea;
				document.ModSolForm.txt_TelCelular.value = telCel.nroTelefono;
				document.ModSolForm.txt_codAreaContacto.value = telPart.codArea;
				document.ModSolForm.txt_TelContacto.value = telPart.nroTelefono;
				document.ModSolForm.txt_Email.value = email.direccMail;
				document.ModSolForm.txt_Calle.value = direcPart.glosCalle;
				document.ModSolForm.txt_Numero.value = direcPart.numDireccion;
				document.ModSolForm.txt_PoblVilla.value = direcPart.poblacionVilla;
				document.ModSolForm.txt_Departamento.value = direcPart.dpto;
						
				document.ModSolForm.dbx_Region.value = direcPart.region;
				cargarProvincias(document.ModSolForm.dbx_Provincia,document.ModSolForm.dbx_Comuna,direcPart.region,direcPart.ciudad);
				cargarComunas(document.ModSolForm.dbx_Comuna,direcPart.ciudad,direcPart.comuna);
						
				document.ModSolForm.dbx_NivEstudios.value = afiliado.tipoNivelEduc;
				document.ModSolForm.dbx_TitAcademico.value = afiliado.tipoProfesion;
				/*document.ModSolForm.rbt_Agrupacion.value
				document.ModSolForm.dbx_RazonSocialAgrup.value
				document.ModSolForm.txt_RutAgrup.value
				document.ModSolForm.txt_NumVerifAgrup.value
				document.ModSolForm.dbx_TipoAgrup.value*/
				document.ModSolForm.dbx_RegPrevisional.value = afiliado.tipoAfp;
				document.ModSolForm.dbx_RegSalud.value = afiliado.tipoRegSalud;
	
				if(grupoFam.conyugue == -1){
					document.ModSolForm.rbt_ConyNo.checked = false;
					document.ModSolForm.rbt_ConySi.checked = false;
				}else{
					if (grupoFam.conyugue == 0){
						document.ModSolForm.rbt_ConyNo.checked = true;
					}else{
						document.ModSolForm.rbt_ConySi.checked = true;
					}
				}
				//REQ5348		
				if(afiliado.idSecuenciaAgrupacion != 0){
					document.ModSolForm.dbx_RazonSocialAgrup.value = afiliado.idSecuenciaAgrupacion;								
					cambiaRutAgrup();										
					document.ModSolForm.rbt_Agrupacion[0].checked = true;
					document.ModSolForm.rbt_Agrupacion[1].checked = false;
				}else{
					document.ModSolForm.dbx_RazonSocialAgrup.value = 0;
					document.ModSolForm.rbt_Agrupacion[0].checked = false;		
					document.ModSolForm.rbt_Agrupacion[1].checked = true;
					document.ModSolForm.txt_RutAgrup.value="";
					document.ModSolForm.txt_NumVerifAgrup.value="";
				}
				
				if(emailComercial!=null){
					if(emailComercial.direccMail!=null && emailComercial.direccMail!=""){
						document.ModSolForm.txt_EmailComercial.value=emailComercial.direccMail;
					}else{
						document.ModSolForm.txt_EmailComercial.value="";	
					}			
					if(emailComercial.idSecuenciaEmail!=null && emailComercial.idSecuenciaEmail!="0"){
						document.ModSolForm.idSecuenciaEmailComercial.value = emailComercial.idSecuenciaEmail;
					}else{
						document.ModSolForm.idSecuenciaEmailComercial.value="0";	
					}
				}else{
					document.ModSolForm.txt_EmailComercial.value="";				
					document.ModSolForm.idSecuenciaEmailComercial.value="0";										
				}					
			//FIN REQ5348
				document.ModSolForm.txt_Hijos.value = grupoFam.cantHijos;
					
				//2. Informacion Actividad Comercial
				document.ModSolForm.txt_Actividad.value = ingreso.actEconom;
					
				if (ingreso.honorario == 0){
					document.ModSolForm.rbt_HonNo.checked = true;
				}else{
					document.ModSolForm.rbt_HonSi.checked = true;
				}
				
				document.ModSolForm.txt_CalleComerc.value = direcComer.glosCalle;
				document.ModSolForm.txt_NumeroComerc.value = direcComer.numDireccion;
				document.ModSolForm.txt_PoblVillaComerc.value = direcComer.poblacionVilla;
				document.ModSolForm.txt_DptoComerc.value = direcComer.dpto;
				document.ModSolForm.txt_codAreaComerc.value = telCom.codArea;
				document.ModSolForm.txt_TelComerc.value = telCom.nroTelefono;
						
				document.ModSolForm.dbx_RegComerc.value = direcComer.region;
				cargarProvincias(document.ModSolForm.dbx_ProvinciaComerc,document.ModSolForm.dbx_ComunaComerc,direcComer.region,direcComer.ciudad);
				cargarComunas(document.ModSolForm.dbx_ComunaComerc,direcComer.ciudad,direcComer.comuna);
						
				//3. Informacion de Renta
				document.ModSolForm.txt_RentaImp.value = ingreso.rentaImponible;
				document.ModSolForm.txt_RentaCot.value = ingreso.rentaCotizada;
				document.ModSolForm.txt_ValorACot.value = afiliado.montoCotizar;
				document.ModSolForm.txt_MontoUltimaCotizacion.value = ingreso.montoUltimaCotizacion;
				document.ModSolForm.txt_FechaUltimaCotizacion.value = ingreso.fecUltCotizacion;
						
				//5. Afiliacion y Desafiliacion
				document.ModSolForm.dbx_CajaCompensacion.value = solicitudvo.tipoCajaOrigen;
						
				//Fecha de Vigencia.
				//if('<%=session.getAttribute("Perfil")%>' == "2")//20120709 - ANA - ANTIGUO PERFILAMIENTO
				if (validarPerfiles(arregloPerfiles, "2"))
				{
					desBloqueaCampoIndiv(document.ModSolForm.txt_FecVigAfil);
					document.ModSolForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
				
				}else{
						
					document.ModSolForm.txt_FecVigAfil.value = solicitudvo.fechaVigencia;
					bloqueaCampoIndiv(document.ModSolForm.txt_FecVigAfil);
				}
							
				//Fecha de Resolucion
				if(solicitudvo.resolucionDirectorio!=null){
					document.ModSolForm.txt_ResolucionDirectorio.value=solicitudvo.resolucionDirectorio;
				}
				//Fin Fecha de resolucion
				/*document.ModSolForm.dbx_CajaCompensacion.value
				document.ModSolForm.dbx_MotDesafiliacion.value*/
						
				//6. Antecedentes Proceso de Afiliación (Solo informativos)
				document.ModSolForm.txt_RutEjec.value = ejecutivo.idAnalista;
				document.ModSolForm.txt_ApePatEjec.value = ejecutivo.apellidoPaterno;
				document.ModSolForm.txt_ApeMatEjec.value = ejecutivo.apellidoMaterno;
				document.ModSolForm.txt_NombreEjec.value = ejecutivo.nombres;
				document.ModSolForm.txt_codOficina.value = solicitudvo.oficina;
				document.ModSolForm.txt_Sucursal.value = solicitudvo.desOficina;
				document.ModSolForm.txt_FecIngr.value = solicitudvo.fechaIngreso;
				document.ModSolForm.txt_Hora.value = solicitudvo.horaCaptacion;		
				//Se agrega fecha de firma.
				if(solicitudvo.fechaFirma == "01/01/1900"){
					document.ModSolForm.txt_FecFirma.value = " ";
				}else{
					document.ModSolForm.txt_FecFirma.value = solicitudvo.fechaFirma;
				}	

				//4. Campos Ocultos (ID)
				document.ModSolForm.idPersona.value = persona.idPersona;
				document.ModSolForm.idSolicitud.value = solicitudvo.idSolicitud;
				document.ModSolForm.idAfiliadoAgrupacion.value = solicitudvo.idAfiliadoAgrupacion;
				document.ModSolForm.idGrupoFam.value = grupoFam.idGrupoFam;
				document.ModSolForm.idIngEconom.value = ingreso.idIngEconom;
				document.ModSolForm.idSecuenciaTelefonoPart.value = telPart.idSecuenciaTelefono;
				document.ModSolForm.idSecuenciaTelefonoCelu.value = telCel.idSecuenciaTelefono;
				document.ModSolForm.idSecuenciaEmail.value = email.idSecuenciaEmail;
				document.ModSolForm.idSecuenciaDireccionPart.value = direcPart.idSecuenciaDireccion;
				document.ModSolForm.idSecuenciaDireccionComerc.value = direcComer.idSecuenciaDireccion;
				document.ModSolForm.idSecuenciaTelefonoComerc.value = telCom.idSecuenciaTelefono;
					
				//Trae datos del analista.(El que se loguea).
				desBloqueaCampoIndiv(document.ModSolForm.txt_RutCaptador);
				desBloqueaCampoIndiv(document.ModSolForm.txt_ApePatCaptador);
				desBloqueaCampoIndiv(document.ModSolForm.txt_ApeMatCaptador);
				desBloqueaCampoIndiv(document.ModSolForm.txt_NombreCaptador);						
										
				document.ModSolForm.txt_RutCaptador.value = analistvo.idAnalista;
				document.ModSolForm.txt_ApePatCaptador.value = analistvo.apellidoPaterno;
				document.ModSolForm.txt_ApeMatCaptador.value = analistvo.apellidoMaterno;
				document.ModSolForm.txt_NombreCaptador.value = analistvo.nombres;
										
				bloqueaCampoIndiv(document.ModSolForm.txt_RutCaptador);
				bloqueaCampoIndiv(document.ModSolForm.txt_ApePatCaptador);
				bloqueaCampoIndiv(document.ModSolForm.txt_ApeMatCaptador);
				bloqueaCampoIndiv(document.ModSolForm.txt_NombreCaptador);
				//fin analista.	
					
				//Busqueda de listado
				ModSolicitudDWR.getEstadosDestinoPosibles(solicitudvo.tipoEstadoSolicitud, function(data)
				{
					var resp = null;
					resp = data;
					
					var cod = "0";
					var txt = "";
							
					var cmb = document.ModSolForm.dbx_EstSolicitud;
	
						cmb.options.length = 0;
						cmb.options[0] = new Option("Seleccione..." , "0");
	
					//if('<%=session.getAttribute("Perfil")%>' == "1" && document.ModSolForm.dbx_EstSolicitudAux.value == 5)//20120709 - ANA - ANTIGUO PERFILAMIENTO
					if (validarPerfilesFullAnalista(arregloPerfiles) && document.ModSolForm.dbx_EstSolicitudAux.value == 5)
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
				
				document.ModSolForm.lbl_DocFolio.value = solicitudvo.folio;
				document.ModSolForm.lbl_DocRUT.value = separadorDeMiles(persona.idDocumento) + "-" + persona.digVerificador;
				document.ModSolForm.lbl_DocNombre.value = persona.nombres + " " + persona.apellidoPaterno + " " + persona.apellidoMaterno;
				
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
			
		document.ModSolForm.existeCambio.value = "0";	
	}
	
	/*Funcion que realiza un update a los distintos campos que componen el formulario de solicitud.
	 Construye la cadena que será enviada a la capa de java.*/
	function updateSolicitudDWR(){
		
		if( validaForm() == true ){
			
			var conyugue = 0;
			var honorario = 0;
			var fechaVigencia = "";
			//para valores de conyugue, ahora no es obligatorio.
			if(document.ModSolForm.rbt_ConySi.checked == false && document.ModSolForm.rbt_ConyNo.checked == false){
				conyugue = -1
			}else{
				if(document.ModSolForm.rbt_ConySi.checked == true){
					conyugue = 1
				}else{
					conyugue = 0
				}
			}	
		
			//para valores de honorario
			if(document.ModSolForm.rbt_HonSi.checked == true){
				honorario = 1;
			}else{
				honorario = 0;
			}
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
			//var email = " ";
			var poblacion = " ";
			var depto = " ";
			//var tituloAcademico = " ";
			//var agrupacion = " ";
			//var razonSocial = " ";
			//var rutAgrupacion = " ";
			//var dvAgrupacion = " ";
			//var tipoAgrupacion = " ";
			//var regSalud = "0";
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
		
			//Los siguientes comentados son obligatorios
			/*if(document.ModSolForm.txt_codAreaCelular.value != ""){
				codAreaCelular = document.ModSolForm.txt_codAreaCelular.value
			}	
			if(document.ModSolForm.txt_TelCelular.value != ""){
				telCelular = document.ModSolForm.txt_TelCelular.value;
			}*/
			
			//REQ5348
			var rSAgrupacion = "0";
			var emailComerc = " ";							
			var iDEmailComerc = "0";				
			if(document.ModSolForm.dbx_RazonSocialAgrup.value!="0"){
				rSAgrupacion = document.ModSolForm.dbx_RazonSocialAgrup.value;
			}
			if(document.ModSolForm.txt_EmailComercial.value!= ""){
				emailComerc=document.ModSolForm.txt_EmailComercial.value;
			}
			if(document.ModSolForm.idSecuenciaEmailComercial.value!= "0"){
				iDEmailComerc=document.ModSolForm.idSecuenciaEmailComercial.value;
			}			
			//FIN REQ5348
			if(document.ModSolForm.txt_codAreaContacto.value != ""){
				codAreaContacto = document.ModSolForm.txt_codAreaContacto.value
			}
			if(document.ModSolForm.txt_TelContacto.value != ""){
				telContacto = document.ModSolForm.txt_TelContacto.value;
			}
			/*if(Trim(document.ModSolForm.txt_Email.value) != ""){
				email = document.ModSolForm.txt_Email.value;
			}*/
			if(Trim(document.ModSolForm.txt_PoblVilla.value) != ""){
				poblacion = document.ModSolForm.txt_PoblVilla.value;
			}
			if(Trim(document.ModSolForm.txt_Departamento.value) != ""){
				depto = document.ModSolForm.txt_Departamento.value;
			}
			if(Trim(document.ModSolForm.txt_PoblVillaComerc.value) != ""){
				poblacionComerc = document.ModSolForm.txt_PoblVillaComerc.value;
			}
			if(Trim(document.ModSolForm.txt_DptoComerc.value) != ""){
				deptoComerc = document.ModSolForm.txt_DptoComerc.value;
			}
			if(document.ModSolForm.txt_codAreaComerc.value != ""){
				codAreaComerc = document.ModSolForm.txt_codAreaComerc.value;
			}
			if(document.ModSolForm.txt_codAreaComerc.value != ""){
				telComerc = document.ModSolForm.txt_TelComerc.value;
			}
			if(document.ModSolForm.txt_RentaCot.value != ""){
				rentaCot = document.ModSolForm.txt_RentaCot.value;
			}
			if(document.ModSolForm.txt_MontoUltimaCotizacion.value != ""){
				montoUltCot = document.ModSolForm.txt_MontoUltimaCotizacion.value;
			}			
			if(document.ModSolForm.txt_FechaUltimaCotizacion.value != ""){
				fecUltCot = document.ModSolForm.txt_FechaUltimaCotizacion.value;
			}				
			if(document.ModSolForm.txt_Hijos.value != ""){
				hijos = document.ModSolForm.txt_Hijos.value;
			}
			if(document.ModSolForm.txt_FecFirma.value != ""){
				fechaFirma = document.ModSolForm.txt_FecFirma.value;
			}else{
				fechaFirma = "01/01/1900";
			}	
			horaCaptacion = document.ModSolForm.txt_Hora.value;
			var cadenaForm = "#" + document.ModSolForm.opcion + "#" +			
						document.ModSolForm.resultado + "#" +
						document.ModSolForm.dbx_EstSolicitud.value + "#" +
               			document.ModSolForm.txt_NFolio.value + "#" +
			            document.ModSolForm.txt_NRut.value + "#" +
			            document.ModSolForm.txt_NNumVerif.value + "#" +
						document.ModSolForm.txt_Folio.value + "#" + 
						document.ModSolForm.txt_Rut.value + "#" + 
						document.ModSolForm.txt_NumVerif.value + "#" +
						document.ModSolForm.txt_ApePat.value + "#" +//10
						document.ModSolForm.txt_ApeMat.value + "#" +
						document.ModSolForm.txt_Nombres.value + "#" + 
						document.ModSolForm.dbx_Nacionalidad.value + "#" + 
						document.ModSolForm.txt_FecNac.value + "#" + 
						document.ModSolForm.dbx_Sexo.value + "#" + 
						document.ModSolForm.dbx_EstCivil.value + "#" +
						document.ModSolForm.txt_codAreaCelular.value + "#" + //codAreaCelular
						document.ModSolForm.txt_TelCelular.value + "#" + //telCelular
						codAreaContacto + "#" +
						telContacto + "#" + //document.ModSolForm.txt_TelContacto.value //20
						document.ModSolForm.txt_Email.value + "#" + //email
						document.ModSolForm.txt_Calle.value + "#" + //
						document.ModSolForm.txt_Numero.value + "#" + 
						poblacion + "#" + //document.ModSolForm.txt_PoblVilla.value
						depto + "#" + // document.ModSolForm.txt_Departamento.value
						document.ModSolForm.dbx_Region.value + "#" + 
						document.ModSolForm.dbx_Provincia.value + "#" + 
						document.ModSolForm.dbx_Comuna.value + "#" + 
						document.ModSolForm.dbx_NivEstudios.value + "#" + 
						document.ModSolForm.dbx_TitAcademico.value + "#" + //30
						" " + "#" + //document.ModSolForm.rbt_Agrupacion.checked 
						" " + "#" + //document.ModSolForm.dbx_RazonSocialAgrup.value //
						" " + "#" + //document.ModSolForm.txt_RutAgrup.value 
						" " + "#" + //document.ModSolForm.txt_NumVerifAgrup.value
						" " + "#" + //document.ModSolForm.dbx_TipoAgrup.value 
						document.ModSolForm.dbx_RegPrevisional.value + "#" + 
						document.ModSolForm.dbx_RegSalud.value  + "#" + //regSalud
						conyugue + "#" +  //document.ModSolForm.rbt_Conyugue.value 
						hijos + "#" + //document.ModSolForm.txt_Hijos.value 
						document.ModSolForm.txt_Actividad.value + "#" + //40
						honorario + "#" + //document.ModSolForm.rbt_Honorarios.value
						document.ModSolForm.txt_CalleComerc.value + "#" + //
						document.ModSolForm.txt_NumeroComerc.value + "#" + 
						poblacionComerc + "#" + //document.ModSolForm.txt_PoblVillaComerc.value
						deptoComerc + "#" + //document.ModSolForm.txt_DptoComerc.value
						codAreaComerc + "#" +
						telComerc + "#" + //document.ModSolForm.txt_TelComerc.value
						document.ModSolForm.dbx_RegComerc.value + "#" + 
						document.ModSolForm.dbx_ProvinciaComerc.value + "#" + 
						document.ModSolForm.dbx_ComunaComerc.value + "#" + 	//50
						document.ModSolForm.txt_RentaImp.value + "#" + 
						rentaCot + "#" + //document.ModSolForm.txt_RentaCot.value
						document.ModSolForm.txt_ValorACot.value + "#" +
						document.ModSolForm.dbx_CajaCompensacion.value + "#" + 
						document.ModSolForm.txt_FecVigAfil.value + "#" +
						document.ModSolForm.txt_RutEjec.value + "#" +//56
						document.ModSolForm.txt_ApePatEjec.value + "#" +//57
						document.ModSolForm.txt_ApeMatEjec.value + "#" +//58
						document.ModSolForm.txt_NombreEjec.value + "#" +//59
						document.ModSolForm.txt_Sucursal.value + "#" +//60
						document.ModSolForm.txt_FecIngr.value + "#" +//
						fechaFirma + "#" + //document.ModSolForm.txt_FecFirma.value //
						document.ModSolForm.idPersona.value + "#" + //
	 					document.ModSolForm.idSolicitud.value + "#" +//
	 					document.ModSolForm.idAfiliadoAgrupacion.value + "#" +
	 					document.ModSolForm.idGrupoFam.value + "#" +
	 					document.ModSolForm.idIngEconom.value + "#" +
	 					document.ModSolForm.idSecuenciaTelefonoPart.value + "#" +
	 					document.ModSolForm.idSecuenciaTelefonoCelu.value + "#" +
	 					document.ModSolForm.idSecuenciaEmail.value + "#" + //70
	 					document.ModSolForm.idSecuenciaDireccionPart.value + "#" +
	 					document.ModSolForm.idSecuenciaDireccionComerc.value + "#" +
	 					//document.ModSolForm.idSecuenciaTelefonoComerc.value + "#"; 
						document.ModSolForm.idSecuenciaTelefonoComerc.value + "#";
						
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
						cadenaForm += rSAgrupacion + "#";
						cadenaForm += emailComerc + "#";	
						cadenaForm += iDEmailComerc + "#";
						cadenaForm += montoUltCot + "#";						
						cadenaForm += fecUltCot + "#";//79	
						cadenaForm += horaCaptacion + "#";//80
						cadenaForm += document.ModSolForm.txt_codOficina.value + "#";//81
						//FIN REQ5348												
						
						//radio_Agrupacion = document.ModSolForm.rbt_Agrupacion;
			ModSolicitudDWR.updateSolicitud(cadenaForm, function(data){
				
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
	
		if (estado == "3" || estado == "6" || estado == "7" || estado == "8"){
		
			//document.ModSolForm.txt_EstSolicitud.disabled = true;
			document.ModSolForm.txt_Folio.disabled = true;
			document.ModSolForm.txt_Rut.disabled = true;
			document.ModSolForm.txt_NumVerif.disabled = true;
			document.ModSolForm.txt_ApePat.disabled = true;
			document.ModSolForm.txt_ApeMat.disabled = true;
			document.ModSolForm.txt_Nombres.disabled = true;
			document.ModSolForm.dbx_Nacionalidad.disabled = true;
			//document.ModSolForm.txt_FecNac.value.disabled = true;
			document.ModSolForm.dbx_Sexo.disabled = true;
			document.ModSolForm.dbx_EstCivil.disabled = true;
			document.ModSolForm.txt_codAreaCelular.disabled = true;
			document.ModSolForm.txt_TelCelular.disabled = true;
			document.ModSolForm.txt_codAreaContacto.disabled = true;
			document.ModSolForm.txt_TelContacto.disabled = true;
			document.ModSolForm.txt_Email.disabled = true;
			document.ModSolForm.txt_EmailComercial.disabled = true;			
			document.ModSolForm.txt_Calle.disabled = true;
			document.ModSolForm.txt_Numero.disabled = true;
			document.ModSolForm.txt_PoblVilla.disabled = true;
			document.ModSolForm.txt_Departamento.disabled = true;
			
			document.ModSolForm.dbx_Region.disabled = true;
			document.ModSolForm.dbx_Provincia.disabled = true;
			document.ModSolForm.dbx_Comuna.disabled = true;
			
			document.ModSolForm.dbx_NivEstudios.disabled = true;
			document.ModSolForm.dbx_TitAcademico.disabled = true;
			document.ModSolForm.rbt_Agrupacion[0].disabled = true;
			document.ModSolForm.rbt_Agrupacion[1].disabled = true;
			document.ModSolForm.dbx_RazonSocialAgrup.disabled = true;
			document.ModSolForm.txt_RutAgrup.disabled = true;
			document.ModSolForm.txt_NumVerifAgrup.disabled = true;
			/*document.ModSolForm.dbx_TipoAgrup.disabled = true;*/
			document.ModSolForm.dbx_RegPrevisional.disabled = true;
			document.ModSolForm.dbx_RegSalud.disabled = true;
	
			document.ModSolForm.rbt_ConyNo.disabled = true;
			document.ModSolForm.rbt_ConySi.disabled = true;
	
			document.ModSolForm.txt_Hijos.disabled = true;
			
			//2. Informacion Actividad Comercial
			document.ModSolForm.txt_Actividad.disabled = true;
			
			document.ModSolForm.rbt_HonNo.disabled = true;
			document.ModSolForm.rbt_HonSi.disabled = true;
			
			document.ModSolForm.txt_CalleComerc.disabled = true;
			document.ModSolForm.txt_NumeroComerc.disabled = true;
			document.ModSolForm.txt_PoblVillaComerc.disabled = true;
			document.ModSolForm.txt_DptoComerc.disabled = true;
			document.ModSolForm.txt_codAreaComerc.disabled = true;
			document.ModSolForm.txt_TelComerc.disabled = true;
			
			document.ModSolForm.dbx_RegComerc.disabled = true;
			document.ModSolForm.dbx_ProvinciaComerc.disabled = true;
			document.ModSolForm.dbx_ComunaComerc.disabled = true;
			
			//3. Informacion de Renta
			document.ModSolForm.txt_RentaImp.disabled = true;
			document.ModSolForm.txt_RentaCot.disabled = true;
			//document.ModSolForm.txt_ValorACot.disabled = true;
			document.ModSolForm.txt_MontoUltimaCotizacion.disabled = true;
			
			//5. Afiliacion y Desafiliacion
			document.ModSolForm.dbx_CajaCompensacion.disabled = true;
			document.ModSolForm.txt_FecVigAfil.disabled = true;
			/*document.ModSolForm.dbx_CajaCompensacion.disabled = true;
			document.ModSolForm.dbx_MotDesafiliacion.disabled = true;*/
			//document.ModSolForm.txt_FecFirma.disabled = true
			document.ModSolForm.txt_RutEjec.disabled = true;
			document.ModSolForm.txt_Hora.disabled = true;						
		}
		
		var botonEst = null;
		var botonGuar = null;
		//var botonGuarDoc = null;
		
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuar = document.getElementById("btn_GuardarSol");
		//botonGuarDoc = document.getElementById("btn_DocGuardar");
		
		if (estado == "3" || estado == "6" || estado == "8")
		{
			botonEst.disabled = true;
			document.ModSolForm.dbx_EstSolicitud.disabled = true;
			botonGuar.disabled = true;
		}
			
		bloqueaCampos();
	}
	
	/*funcion que desbloquea los campos del formulario dependiendo del estado en que se encuentre la solicitud.*/
	function desBloqueaFormulario(estado){
	
		if (estado == "1" || estado == "2" || estado == "4" || estado == "5"){
	
			//document.ModSolForm.txt_EstSolicitud.disabled = false;
			document.ModSolForm.txt_Folio.disabled = false;
			document.ModSolForm.txt_Rut.disabled = false;
			document.ModSolForm.txt_NumVerif.disabled = false;
			document.ModSolForm.txt_ApePat.disabled = false;
			document.ModSolForm.txt_ApeMat.disabled = false;
			document.ModSolForm.txt_Nombres.disabled = false;
			document.ModSolForm.dbx_Nacionalidad.disabled = false;
			//document.ModSolForm.txt_FecNac.value.disabled = false;
			document.ModSolForm.dbx_Sexo.disabled = false;
			document.ModSolForm.dbx_EstCivil.disabled = false;
			document.ModSolForm.txt_codAreaCelular.disabled = false;
			document.ModSolForm.txt_TelCelular.disabled = false;
			document.ModSolForm.txt_codAreaContacto.disabled = false;
			document.ModSolForm.txt_TelContacto.disabled = false;
			document.ModSolForm.txt_Email.disabled = false;
			document.ModSolForm.txt_EmailComercial.disabled = false;						
			document.ModSolForm.txt_Calle.disabled = false;
			document.ModSolForm.txt_Numero.disabled = false;
			document.ModSolForm.txt_PoblVilla.disabled = false;
			document.ModSolForm.txt_Departamento.disabled = false;
			
			document.ModSolForm.dbx_Region.disabled = false;
			document.ModSolForm.dbx_Provincia.disabled = false;
			document.ModSolForm.dbx_Comuna.disabled = false;
			
			document.ModSolForm.dbx_NivEstudios.disabled = false;
			document.ModSolForm.dbx_TitAcademico.disabled = false;
			document.ModSolForm.rbt_Agrupacion[0].disabled = false;
			document.ModSolForm.rbt_Agrupacion[1].disabled = false;		
			if(document.ModSolForm.rbt_Agrupacion[0].checked){	
				document.ModSolForm.dbx_RazonSocialAgrup.disabled = false;
			}
			//document.ModSolForm.txt_RutAgrup.disabled = false;
			//document.ModSolForm.txt_NumVerifAgrup.disabled = false;
			/*document.ModSolForm.dbx_TipoAgrup.disabled = false;*/
			document.ModSolForm.dbx_RegPrevisional.disabled = false;
			document.ModSolForm.dbx_RegSalud.disabled = false;
	
			document.ModSolForm.rbt_ConyNo.disabled = false;
			document.ModSolForm.rbt_ConySi.disabled = false;
	
			document.ModSolForm.txt_Hijos.disabled = false;
			
			//2. Informacion Actividad Comercial
			document.ModSolForm.txt_Actividad.disabled = false;
			
			document.ModSolForm.rbt_HonNo.disabled = false;
			document.ModSolForm.rbt_HonSi.disabled = false;
			
			document.ModSolForm.txt_CalleComerc.disabled = false;
			document.ModSolForm.txt_NumeroComerc.disabled = false;
			document.ModSolForm.txt_PoblVillaComerc.disabled = false;
			document.ModSolForm.txt_DptoComerc.disabled = false;
			document.ModSolForm.txt_codAreaComerc.disabled = false;
			document.ModSolForm.txt_TelComerc.disabled = false;
			
			document.ModSolForm.dbx_RegComerc.disabled = false;
			document.ModSolForm.dbx_ProvinciaComerc.disabled = false;
			document.ModSolForm.dbx_ComunaComerc.disabled = false;
			
			//3. Informacion de Renta
			document.ModSolForm.txt_RentaImp.disabled = false;
			document.ModSolForm.txt_RentaCot.disabled = false;
			//document.ModSolForm.txt_ValorACot.disabled = false;
			document.ModSolForm.txt_MontoUltimaCotizacion.disabled = false;			
			
			//5. Afiliacion y Desafiliacion
			document.ModSolForm.dbx_CajaCompensacion.disabled = false;
			document.ModSolForm.txt_FecVigAfil.disabled = false;
			/*document.ModSolForm.dbx_CajaCompensacion.disabled = false;
			document.ModSolForm.dbx_MotDesafiliacion.disabled = false;*/
			//document.ModSolForm.txt_FecFirma.disabled = false
			document.ModSolForm.txt_RutEjec.disabled = false;
			document.ModSolForm.txt_Hora.disabled = false;			
		}
		
		var botonEst = null;
		var botonGuar = null;
		
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuar = document.getElementById("btn_GuardarSol");
		
		if (estado == "1" || estado == "2" || estado == "4" || estado == "5")
		{
			botonEst.disabled = false;
			document.ModSolForm.dbx_EstSolicitud.disabled = false;
		}
		
		if (estado == "1" || estado == "2" || estado == "4" || estado == "5" || estado == "7")
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
		
		document.ModSolForm.txt_Folio.disabled = true;
		document.ModSolForm.txt_Rut.disabled = true;
		document.ModSolForm.txt_NumVerif.disabled = true;
	
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuarDoc = document.getElementById("btn_DocGuardar");
		
		//if ('<%=session.getAttribute("Perfil")%>' == "1") //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles))
		{
			botonEst.disabled = true;
			document.ModSolForm.dbx_EstSolicitud.disabled = true;
			/* Esconde el Calendario de fechaVigencia si es que el usuario no tiene el perfil que
			  permita modificar dicha fecha.*/
			document.ModSolForm.img_FecVigencia.style.visibility = "hidden";
			document.ModSolForm.txt_FecVigAfil.disabled = true;
			
			/*Esconde el calendario de fecha de solicitud si usuario no tiene el perfil para modificar.*/
			document.ModSolForm.img_FecFirma.style.visibility = "hidden";
			
			/*No muestra boton buscar analista si no tiene el perfil necesario.*/
			document.ModSolForm.btn_buscarIdCaptador.style.visibility = "hidden";
			document.ModSolForm.txt_RutEjec.disabled = true;
			//document.ModSolForm.txt_Hora.disabled = true;
		}
		
		//if ('<%=session.getAttribute("Perfil")%>' == "1" && document.ModSolForm.dbx_EstSolicitudAux.value == 5)//20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles) && document.ModSolForm.dbx_EstSolicitudAux.value == 5)
		{
			botonEst.disabled = false;
			document.ModSolForm.dbx_EstSolicitud.disabled = false;
			//document.getElementById("btn_GuardarSol").disabled = false;
		}
		
		//if('<%=session.getAttribute("Perfil")%>' == "1" && document.ModSolForm.dbx_EstSolicitudAux.value != 5)//20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles) && document.ModSolForm.dbx_EstSolicitudAux.value != 5)
		{
			botonEst.disabled = true;
			document.ModSolForm.btn_GuardarSol.disabled = true;
			document.ModSolForm.btn_GuardarEst.disabled = true;
			document.ModSolForm.dbx_EstSolicitud.disabled = true;
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
			fechaModificada = document.ModSolForm.txt_FecVigAfil.value
				
			if(Comparar_Fecha(fechaModificada,fechaCalculada)== true)
			{
				document.ModSolForm.txt_FecVigAfil.value = fechaModificada;
				return true;
				
			}else{
				return false;
			}
		}		
	}*/
	
	/*Funcion que permite recuperar la fecha de vigencia.*/
	function recuperarFecVigencia()
	{
		var folio = Trim(document.ModSolForm.txt_NFolio.value);
		
		ModSolicitudDWR.recuperarFecVigencia(folio, function(data){
			
			document.ModSolForm.txt_FecVigAfil.value = data;
		});
	}
	
	/*funcion que permite valida la fecha de solicitud.*/
	function validaFecSolicitud()
	{
		var fechaSolicitud = "01/01/2012";
		var fechaSolIngresada = document.ModSolForm.txt_FecFirma.value;
		if(Comparar_Fecha(fechaSolIngresada,fechaSolicitud))
		{
			return true;
		}
		
		return false;	
	}
	
	/*funcion que recupera la fecha de firma.*/
	function recuperarFecFirma()
	{
		var folio = Trim(document.ModSolForm.txt_NFolio.value);
		
		ModSolicitudDWR.recuperarFecFirma(folio, function(data){
					
			document.ModSolForm.txt_FecFirma.value = data;
		});
	}
	
	function desBloqueaCampos() // TODO No se esta ocupando?
	{
		document.ModSolForm.txt_Folio.disabled = false;
		document.ModSolForm.txt_Rut.disabled = false;
		document.ModSolForm.txt_NumVerif.disabled = false;
		document.ModSolForm.txt_ValorACot.disabled = false;
	}
	
	/*funcion que calcula el valor a cotizar.*/
	function calculoValorACotizar()
	{
		var rentaImp = 0;
		var rentaCot = 0;
		var valorACot = 0;
		
		if(document.ModSolForm.txt_RentaImp.value != 0 && document.ModSolForm.txt_RentaImp.value != null && document.ModSolForm.txt_RentaImp.value != "")
			rentaImp = parseFloat(document.ModSolForm.txt_RentaImp.value);
			
		if(document.ModSolForm.txt_RentaCot.value != 0 && document.ModSolForm.txt_RentaCot.value != null && document.ModSolForm.txt_RentaCot.value != "")	
			rentaCot = parseFloat(document.ModSolForm.txt_RentaCot.value);
				
		if(rentaImp != 0){
			
			valorACot = Math.round(rentaImp * 0.01);
		
		}else{
			
			if( rentaCot != 0){
			
				valorACot = Math.round(rentaCot * 0.01);
			
			}else{
				
				valorACot = 0;
			}
		}
		
		desBloqueaCampoIndiv(document.ModSolForm.txt_ValorACot);
		document.ModSolForm.txt_ValorACot.value = valorACot;
		bloqueaCampoIndiv(document.ModSolForm.txt_ValorACot);			
	}
	
	/* Funcion que calcula el valor a cotizar basado en la parametrizacion */
	function calculoValorACotizarParam(){
	
		var rentaImp = 0;
		var rentaCot = 0;
		var valorACot = 0;
	
		var tipoCalculo  = '<%=session.getAttribute("TxtSelTipoCalculoAporte")%>';
		var valorCalculo = '<%=session.getAttribute("TxtValorCalculoAporte")%>';
		
		if (tipoCalculo == 1){
		
			if(document.ModSolForm.txt_RentaImp.value != null && document.ModSolForm.txt_RentaImp.value != 0 && document.ModSolForm.txt_RentaImp.value != "")
				rentaImp = parseFloat(document.ModSolForm.txt_RentaImp.value);
				
			if(document.ModSolForm.txt_RentaCot.value != null && document.ModSolForm.txt_RentaCot.value != 0 && document.ModSolForm.txt_RentaCot.value != "")	
				rentaCot = parseFloat(document.ModSolForm.txt_RentaCot.value);
					
			if(rentaImp != 0){
				
				valorACot = Math.round(rentaImp * (valorCalculo / 100));
			
			}else{
				
				if( rentaCot != 0){
				
					valorACot = Math.round(rentaCot * (valorCalculo / 100));
				
				}else{
					
					valorACot = 0;
				}
			}
		}
		
		if (tipoCalculo == 2){
			valorACot = valorCalculo;
		}
	
		if (tipoCalculo != 1 && tipoCalculo != 2){
			alert("Error en el calculo del Aporte");
		}
	
		desBloqueaCampoIndiv(document.ModSolForm.txt_ValorACot);
		document.ModSolForm.txt_ValorACot.value = valorACot;
		bloqueaCampoIndiv(document.ModSolForm.txt_ValorACot);	
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
	
		var fechaVigencia = document.ModSolForm.txt_FecVigAfil.value;
		var fechaIngreso = document.ModSolForm.txt_FecIngr.value;
		
		if (fechaVigencia != "" && fechaIngreso != ""){
		
			DWREngine.setAsync(false);
			ModSolicitudDWR.validaFechaVigencia(fechaVigencia, fechaIngreso, function(data){
			
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
		ModSolicitudDWR.obtenerFechaVigencia(fecha, function(data){
		
			return data;
		});	
	}
	
	function Cambios(x)
	{
		document.ModSolForm.existeCambio.value = x;
	}
	
	/*funcion por filtro. dependiendo del tipo de filtro, puede buscar por folio y rut, folio, rut.
		Si el filtro es por rut, se ejecuta la grilla con la busqueda.*/
	function buscarPorFiltro()
	{
		var folio = Trim(document.ModSolForm.txt_NFolio.value);
		var rut = Trim(document.ModSolForm.txt_NRut.value);
		var dv = Trim(document.ModSolForm.txt_NNumVerif.value);
		
		if(folio.length == 0 && rut.length == 0)
		{
			alert("Para realizar una búsqueda debe ingresar un folio, un rut, o un folio/rut.");
		}
		
		if(folio.length != 0 && rut.length == 0)
		{	obtenerSolicitudFolioDWR();
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
		var rut = Trim(document.ModSolForm.txt_NRut.value);
		var digitoVer = Trim(document.ModSolForm.txt_NNumVerif.value);
		//agregar validacion para digito verificador.
		
		ModSolicitudDWR.obtenerFoliosPorRut(rut, function(data){
			datos = data;
			if(datos != null){
				
				cargaDatosEnGrilla();
			}
		});
	}															
	
	/*funcion que obtiene la fila de la tabla*/
	function obtenerFilaTabla(dato)
	{
		document.ModSolForm.txt_NFolio.value = "";
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
		document.ModSolForm.txt_NFolio.value = dato;		
		esconderDiv();
		obtenerSolicitudDWR();
		/*document.ModSolForm.txt_NRut.value = "";
		document.ModSolForm.txt_NFolio.value = "";
		document.ModSolForm.txt_NNumVerif.value = "";*/
	}
	
	var consultas = null;
	function tituloGrilla()
	{
		var apellidoPat = null;
		var apellidoMat = null;
		var nombres = null;
		var rut = Trim(document.ModSolForm.txt_NRut.value);
		
		ModSolicitudDWR.obtenerDatosPorRut(rut, function(data){
		
			consultas = data;
			
			var persona = null;
			persona = consulta.personaVO;
			
		});
	}
	/* FIN GRILLA*/
	
	/******************************************/
	/* MODIFICACION DEL ANALISTA Y/O CAPTADOR COMERCIAL*/
	function buscarCaptador(){
		
		var rut = Trim(document.ModSolForm.txt_RutEjec.value);
		var rutCompleto;
		var dv;
		if(rut.length == 0){
		
			alert("Para realizar una búsqueda debe insertar el rut de un Analista o de un Captador.");
		
		}else{
		
			dv = DigitoVerificadorRut( rut );
			rutCompleto = rut + "-" + dv;
			if(ValidadorRUT(rut,dv)){
			
				NewSolicitudDWR.obtenerCaptador(rutCompleto, function(data){
					
					var solicitud = null;
					solicitud = data;
					
					if(solicitud.idAnalista == 0)
					{
						alert("El rut ingresado no está registrado.");
						limpiarCamposBusqueda();
					}else{
						
						desBloqueaCampoIndiv(document.ModSolForm.txt_ApePatEjec);
						desBloqueaCampoIndiv(document.ModSolForm.txt_ApeMatEjec);
						desBloqueaCampoIndiv(document.ModSolForm.txt_NombreEjec);
						
						document.ModSolForm.txt_ApePatEjec.value = solicitud.apellidoPaterno;
						document.ModSolForm.txt_ApeMatEjec.value = solicitud.apellidoMaterno;
						document.ModSolForm.txt_NombreEjec.value = solicitud.nombres;
						
						bloqueaCampoIndiv(document.ModSolForm.txt_ApePatEjec);
						bloqueaCampoIndiv(document.ModSolForm.txt_ApeMatEjec);
						bloqueaCampoIndiv(document.ModSolForm.txt_NombreEjec);
					}		
				});
			
			}else{
				
				alert("El rut ingresado no es válido.");
			}
		}
	}
	
	/*funcion que limpia los campos de busqueda del analista*/
	function limpiarCamposBusqueda(){
	
		document.ModSolForm.txt_RutEjec.value = "";
		
		desBloqueaCampoIndiv(document.ModSolForm.txt_ApePatEjec);
		desBloqueaCampoIndiv(document.ModSolForm.txt_ApeMatEjec);
		desBloqueaCampoIndiv(document.ModSolForm.txt_NombreEjec);

		document.ModSolForm.txt_ApePatEjec.value = "";
		document.ModSolForm.txt_ApeMatEjec.value = "";
		document.ModSolForm.txt_NombreEjec.value = "";
		
		bloqueaCampoIndiv(document.ModSolForm.txt_ApePatEjec);
		bloqueaCampoIndiv(document.ModSolForm.txt_ApeMatEjec);
		bloqueaCampoIndiv(document.ModSolForm.txt_NombreEjec);
	}
	
	/* FIN MODIFICACION ANALISTA */
	
	function mostrarDiv()
	{
		var nombre = null;
		var apePat = null;
		var apeMat = null;
		var rut = Trim(document.ModSolForm.txt_NRut.value);
		
		ModSolicitudDWR.obtenerDatosPorRut(rut, function(data){
			var datos = null;
			datos = data;
			
			var persona = null;
			var solicitud = null;
			
			persona = datos.personaVO;
			solicitud = datos.solicitudVO;
			
			desBloqueaCampoIndiv(document.ModSolForm.txt_nombreCompletoAfil);
			desBloqueaCampoIndiv(document.ModSolForm.txt_TipoEstadoPorRut);
			
			nombre = persona.nombres;
			apePat = persona.apellidoPaterno;
			apeMat = persona.apellidoMaterno;
			document.ModSolForm.txt_FolioPorRut.value = solicitud.folio;
			document.ModSolForm.txt_TipoEstadoPorRut.value = solicitud.desTipoEstadoSolicitud;
			
			//bloqueaCampoIndiv(document.ModSolForm.txt_FolioPorRut);
			bloqueaCampoIndiv(document.ModSolForm.txt_TipoEstadoPorRut);
			
			var nombreCompleto = nombre + " " + apePat + " " + apeMat;
		
			document.ModSolForm.txt_nombreCompletoAfil.value = nombreCompleto;
			bloqueaCampoIndiv(document.ModSolForm.txt_nombreCompletoAfil);
			document.getElementById("grid_rut").style.display = "";
		});			
		
	}
	
	//--- Mantenedor de Documentos
	
	function mostrarDocumentacion()
	{
		var folio = Trim(document.ModSolForm.txt_NFolio.value);
		var rut = Trim(document.ModSolForm.txt_NRut.value);
		var dv = Trim(document.ModSolForm.txt_NNumVerif.value);
		var estado = document.ModSolForm.dbx_EstSolicitudAux.value;
		var perfil = '<%=session.getAttribute("Perfil")%>';
		
		var contenidoTabla = "";
		var desabilitado = "";
		
		//if(estado == "3" || estado == "6" || estado == "8" || perfil == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if(estado == "3" || estado == "6" || estado == "8" || validarPerfilesFullAnalista(arregloPerfiles)){
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
				"<tr><td width='50'></td><td width='200'><strong>N° Folio:</strong></td><td width='475'>"+document.ModSolForm.lbl_DocFolio.value+"</td>"+
				"</tr><tr><td></td><td><strong>N° RUT:</strong></td><td>"+document.ModSolForm.lbl_DocRUT.value+"</td></tr><tr><td></td><td>"+
				"<strong>Nombre:</strong></td><td>"+document.ModSolForm.lbl_DocNombre.value+"</td></tr></table>"+
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
		var estado = document.ModSolForm.dbx_EstSolicitudAux.value;
		
		var asterisco = "";
		var desabilitado = "";
		
		if(dato.obligatorio == 1 && dato.alta == 1){
			asterisco = " *";
		}
	
		//if(dato.alta == 0 || estado == "3" || estado == "6" || estado == "8" || perfil == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if(dato.alta == 0 || estado == "3" || estado == "6" || estado == "8" || validarPerfilesFullAnalista(arregloPerfiles)){
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
				Trim(document.ModSolForm.txt_ObservacionesDoc[i].value) != Trim(vectorDocumentos[i].observaciones))
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
		
		if(validaGuardarDocumentos()){
			
			for(var i=0;i<vectorDocumentos.length;i++)
			{
				vectorDocumentos[i].estadoDocumentoAux	=	document.getElementById("dbx_EstadoDoc_"+i).value;
				vectorDocumentos[i].observaciones 		=	document.ModSolForm.txt_ObservacionesDoc[i].value;
			}
			
			document.getElementById("mantenedorDocumentos").style.visibility = "hidden";
			document.getElementById("fondoNegro").style.visibility = "hidden";
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
	
		ModSolicitudDWR.getEstadosDestinoPosiblesDoc(tipo, function(data)
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

	//REQ5348
	function habilitaAgrupacion()
	{
		var opAgrupa	=	document.ModSolForm.rbt_Agrupacion;
		var razonSocial	=	document.ModSolForm.dbx_RazonSocialAgrup;
		var rutAgrup	=	document.ModSolForm.txt_RutAgrup;
		var dv			=	document.ModSolForm.txt_NumVerifAgrup;
		
		for(i=0;i<opAgrupa.length;i++)
		{
			if(opAgrupa[i].checked && opAgrupa[i].value==0)
			{
				 razonSocial.value=0;
				 rutAgrup.value="";
				 dv.value="";
				 break;
			}
		}
		if(opAgrupa[1].checked){
			razonSocial.disabled = true;
		}
		if(opAgrupa[0].checked){
			razonSocial.disabled = false;
		}
	}
	
	function cambiaRutAgrup()
	{
		var opAgrupa	=	document.ModSolForm.rbt_Agrupacion;
		var id			=	document.ModSolForm.dbx_RazonSocialAgrup.value;//valor seleccionado del combo agrupacion
		
		var rutAgrup	=	document.ModSolForm.txt_RutAgrup;//Rut Agrupacion a obtener
		var dvAgrup 	= 	document.ModSolForm.txt_NumVerifAgrup;//Digito Verificador a obtener.
		
		var agrupacion	= 	new ObjAgrupacion;
		agrupacion = obtenerAgrupacion(arregloAgrupaciones, id);

		desBloqueaCampoIndiv(rutAgrup);
		desBloqueaCampoIndiv(dvAgrup);

		if(id!=0 && agrupacion.rut!=0 && agrupacion.dv!=""){
			rutAgrup.value = agrupacion.rut;
			dvAgrup.value = agrupacion.dv;
		}else{
			rutAgrup.value="";
			dvAgrup.value="";
		}
		
		bloqueaCampoIndiv(rutAgrup);
		bloqueaCampoIndiv(dvAgrup);
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
<html:form action="/modSol.do">

	<input type="hidden" name="dbx_EstSolicitudAux" value="0">
	<input type="hidden" name="opcion" value="0">
	
	<input type="hidden" name="idPersona" value="0">
	<input type="hidden" name="idSolicitud" value="0">
	<input type="hidden" name="idAfiliadoAgrupacion" value="0">
	<input type="hidden" name="idGrupoFam" value="0">
	<input type="hidden" name="idIngEconom" value="0">
	<input type="hidden" name="idSecuenciaTelefonoPart" value="0">
	<input type="hidden" name="idSecuenciaTelefonoCelu" value="0">
	<input type="hidden" name="idSecuenciaEmail" value="0">
	<input type="hidden" name="idSecuenciaEmailComercial" value="0">	
	<input type="hidden" name="idSecuenciaDireccionPart" value="0">
	<input type="hidden" name="idSecuenciaDireccionComerc" value="0">
	<input type="hidden" name="idSecuenciaTelefonoComerc" value="0">	
	<input type="hidden" name="txt_RentaCot" value="0">
	<input type="hidden" name="txt_FecUltAporte" value=" ">
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
					<td><strong><p1>CONSULTA/MODIFICACION INDIVIDUAL
					SOLICITUD TRABAJADOR INDEPENDIENTE</p1></strong> <br />
					<br />
					</td>
				</tr>
				<tr>
					<td><strong>N° Folio *</strong> <input type="text"
						name="txt_NFolio" id="txt_NFolio" size="10" maxlength="12" onkeypress="Upper(this,'N')"/> <strong>N°
					RUT *</strong> <input type="text" name="txt_NRut" id="txt_NRut" size="10" maxlength="9" onkeypress="Upper(this,'N')"/>
					<strong> - </strong> <input type="text" name="txt_NNumVerif"
						id="txt_NNumVerif" size="3" maxlength="1" onkeypress="Upper(this,'D')" onchange="ValidadorRUT(document.ModSolForm.txt_NRut.value,this.value)"/> <input type="button"
						name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar"
						onClick="buscarPorFiltro();" /> <strong>Estado
					Solicitud</strong><input type="text" name="txt_EstSolicitud" id="txt_EstSolicitud" disabled="true"/><html:select property="dbx_EstSolicitud"
						styleClass="combobox" value="0">
						<html:option value="0">Seleccione</html:option>
						<!--<html:options collection="ListEstadoSolicitudAfiliacionBox"
							property="codigo" labelProperty="glosa" />-->
					</html:select><input type="button" name="btn_GuardarEst" id="btn_GuardarEst"
						class="btn_limp" value="Guardar" onClick="updateEstadoSolDWR();" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<input type="button" name="btn_Documentacion" id="btn_Documentacion" class="btn_menu" value="Documentacion" onClick="mostrarDocumentacion();" />
					</td>
				</tr>
			</table>
			
		<tr>
			<td height="404" width="805">
			<p><p2>1. Identificación del Trabajador Independiente</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>N° Folio *</strong></td>
					<td width="16%"><input type="text" name="txt_Folio"
						id="txt_Folio" maxlength="12" onkeypress="Upper(this,'N')"/></td>
					<td width="16%"><strong>N° RUT *</strong></td>
					<td width="16%"><input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeypress="Upper(this,'N')"/></td>
					<td width="16%"><strong> - </strong> <input type="text"
						name="txt_NumVerif" id="txt_NumVerif" size="3" maxlength="1" onkeypress="Upper(this,'D')"/></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno *</strong></td>
					<td><input name="txt_ApePat" type="text" id="txt_ApePat" maxlength="50" onkeypress="Upper(this,'L')" onchange="Cambios(1);"/></td>
					<td><strong>Apellido Materno *</strong></td>
					<td><input type="text" name="txt_ApeMat" id="txt_ApeMat" maxlength="50" onkeypress="Upper(this,'L')" onchange="Cambios(1);"/></td>
					<td><strong>Nombres *</strong></td>
					<td colspan="3"><input type="text" name="txt_Nombres" id="txt_Nombres" maxlength="100" onkeypress="Upper(this,'L')" onchange="Cambios(1);"/></td>
				</tr>
				<tr>
					<td><strong>Nacionalidad *</strong></td>
					<td>
					<!--  
						<html:select property="dbx_Nacionalidad" styleClass="combobox" value="0" onchange="Cambios(1);">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListNacionalidadBox" property="codigo" labelProperty="glosa" />
						</html:select>
					-->
					<select	name="dbx_Nacionalidad" id="dbx_Nacionalidad" style="combobox" value="0">
							<option value="0">Seleccione</option>
							<c:forEach items="${sessionScope.ListNacionalidadBox}" var="opcion">
								<c:choose>
									<c:when test="${opcion.estado != '0'}" >
										<option value="<c:out value='${opcion.codigo}'/>">
											<c:out value='${opcion.glosa}'/>
										</option>
									</c:when>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="30"><strong>Fecha de Nacimiento *</strong></td>
					<td><input type="text" name="txt_FecNac" id="txt_FecNac" class="datepick" disabled="true" size="10" onchange="Cambios(1);"/>
<!--						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecNac)">-->
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Sexo *</strong></td>
					<td><html:select property="dbx_Sexo" styleClass="combobox"
						value="0" onchange="Cambios(1);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListSexoBox" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td><strong>Estado Civil *</strong></td>
					<td>
					<!--  
					<html:select property="dbx_EstCivil" styleClass="combobox" value="0" onchange="Cambios(1);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListEstadoCivilBox" property="codigo" labelProperty="glosa" />
					</html:select>
					-->
					
					<select	name="dbx_EstCivil" id="dbx_EstCivil" style="combobox" value="0">
							<option value="0">Seleccione</option>
							<c:forEach items="${sessionScope.ListEstadoCivilBox}" var="opcion">
								<c:choose>
									<c:when test="${opcion.estado != '0'}" >
										<option value="<c:out value='${opcion.codigo}'/>">
											<c:out value='${opcion.glosa}'/>
										</option>
									</c:when>
								</c:choose>
							</c:forEach>
						</select>
					
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Teléfono Celular *</strong></td>
					<td>
						<input size="1" type="text" name="txt_codAreaCelular" id="txt_codAreaCelular" maxlength="2" onkeypress="Upper(this,'N')" onchange="Cambios(1);"/>
						<input size="10" type="text" name="txt_TelCelular" id="txt_TelCelular" maxlength="8" onkeypress="Upper(this,'N')" onchange="Cambios(1);"/>
					</td>
					<td><strong>Teléfono de Contacto</strong></td>
					<td>
						<input size="1" type="text" name="txt_codAreaContacto" id="txt_codAreaContacto" maxlength="3" onkeypress="Upper(this,'N')" onchange="Cambios(1);"/>
						<input size="10" type="text" name="txt_TelContacto" id="txt_TelContacto" maxlength="8" onkeypress="Upper(this,'N')" onchange="Cambios(1);"/>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>

				<tr>
					<td><strong>E-Mail *</strong></td>
					<td colspan="3">
						<input type="text" name="txt_Email"	id="txt_Email" size="50" maxlength="100" onkeypress="Upper(this,'M')" onblur="validarEmail(this.value)" onchange="Cambios(1);"/>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_Calle"
						id="txt_Calle" size="50" maxlength="50" onkeypress="Upper(this,'A')" onchange="Cambios(1);"/></td>
					<td height="34"><strong>Número *</strong></td>
					<td colspan="3"><input type="text" name="txt_Numero"
						id="txt_Numero" size="5" maxlength="6" onkeypress="Upper(this,'A')" onchange="Cambios(1);"/></td>
				</tr>
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3"><input type="text" name="txt_PoblVilla"
						id="txt_PoblVilla" size="50" maxlength="50" onkeypress="Upper(this,'A')" onchange="Cambios(1);"/></td>
					<td height="34"><strong>Departamento</strong></td>
					<td colspan="3"><input type="text" name="txt_Departamento"
						id="txt_Departamento" size="5" maxlength="5" onkeypress="Upper(this,'A')" onchange="Cambios(1);"/></td>
				</tr>
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3"><html:select property="dbx_Region" styleClass="dbx_geo"
						value="0"
						onchange="Cambios(1);cargarProvincias(ModSolForm.dbx_Provincia,ModSolForm.dbx_Comuna,this.value,0);">
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
						onchange="cargarComunas(ModSolForm.dbx_Comuna,this.value,0);">
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
				</tr>
				<tr>
					<td><strong>Nivel de Estudios *</strong></td>
					<td colspan="2">
					<!--  
						<html:select property="dbx_NivEstudios" styleClass="dbx_geo" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListNivelEducacionalBox" property="codigo" labelProperty="glosa" />
						</html:select>
					-->
						<select	name="dbx_NivEstudios" id="dbx_NivEstudios" style="combobox" value="0">
							<option value="0">Seleccione</option>
							<c:forEach items="${sessionScope.ListNivelEducacionalBox}" var="opcion">
								<c:choose>
									<c:when test="${opcion.estado != '0'}" >
										<option value="<c:out value='${opcion.codigo}'/>">
											<c:out value='${opcion.glosa}'/>
										</option>
									</c:when>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>	
					<td><strong>Título Académico</strong></td>
					<td colspan="2">
					<!--  
						<html:select property="dbx_TitAcademico" styleClass="dbx_titulo" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListProfesionBox" property="codigo" labelProperty="glosa" />
						</html:select></td>
					-->
						<select	name="dbx_TitAcademico" id="dbx_TitAcademico" style="combobox" value="0">
							<option value="0">Seleccione</option>
							<c:forEach items="${sessionScope.ListProfesionBox}" var="opcion">
								<c:choose>
									<c:when test="${opcion.estado != '0'}" >
										<option value="<c:out value='${opcion.codigo}'/>">
											<c:out value='${opcion.glosa}'/>
										</option>
									</c:when>
								</c:choose>
							</c:forEach>
						</select>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<!-- REQ5348 -->
				<tr>
					<td><strong>Agrupación (Si/No) *</strong></td>
					<td><input type="radio" name="rbt_Agrupacion" id="rbt_Agrupacion"
						value="1" onClick="habilitaAgrupacion()"/>Si<br />
					<input type="radio" name="rbt_Agrupacion" id="rbt_Agrupacion"
						value="0" onClick="habilitaAgrupacion()" onChange="habilitaAgrupacion();"/>No<br />
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>					
					<td width="16%">
						<strong> Razón Social Agrupación *</strong></td>
					<td><strong></strong>
					<html:select property="dbx_RazonSocialAgrup"
						onchange="javascript:cambiaRutAgrup()" styleClass="dbx_agrupa"
						value="0" disabled="true">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListAgrupacionFull" property="idSecuencia"
							labelProperty="razonSocial" />
					</html:select>	
					</td>
					<td><strong>RUT Agrupación</strong></td>
					<td><input size="12" maxlength="10" type="text" name="txt_RutAgrup" id="txt_RutAgrup" disabled="true"/><strong>-
					</strong> <input type="text" name="txt_NumVerifAgrup"
						id="txt_NumVerifAgrup" size="1" disabled="true"></td>					
					<td colspan="4"></td>
				</tr> 
				<!--FIN REQ5348-->
				<tr>
					<td><strong>Régimen Previsional *</strong></td>
					<td>
					<!--  
						<html:select property="dbx_RegPrevisional" styleClass="combobox" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListadoAfpBox" property="codigo" labelProperty="glosa" />
						</html:select>
					-->
						<select	name="dbx_RegPrevisional" id="dbx_RegPrevisional" style="combobox" value="0">
								<option value="0">Seleccione</option>
								<c:forEach items="${sessionScope.ListadoAfpBox}" var="opcion">
									<c:choose>
										<c:when test="${opcion.estado != '0'}" >
											<option value="<c:out value='${opcion.codigo}'/>">
												<c:out value='${opcion.glosa}'/>
											</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</select>
					
					</td>
					<td><strong>Régimen de Salud</strong></td>
					<td>
					<!--  
						<html:select property="dbx_RegSalud" styleClass="combobox" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListRegimenSaludBox" property="codigo" labelProperty="glosa" />
						</html:select>
					-->
						<select	name="dbx_RegSalud" id="dbx_RegSalud" style="combobox" value="0">
								<option value="0">Seleccione</option>
								<c:forEach items="${sessionScope.ListRegimenSaludBox}" var="opcion">
									<c:choose>
										<c:when test="${opcion.estado != '0'}" >
											<option value="<c:out value='${opcion.codigo}'/>">
												<c:out value='${opcion.glosa}'/>
											</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</select>
						
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Cónyuge (Si/No)</strong></td>
					<td><input type="radio" name="rbt_Conyugue" id="rbt_ConySi"
						value="1" />Sí<br />
					<input type="radio" name="rbt_Conyugue" id="rbt_ConyNo" value="0" />No<br />
					</td>
					<td><strong>Hijos </strong></td>
					<td><input type="text" name="txt_Hijos" id="txt_Hijos"
						size="5" maxlength="2" onkeypress="Upper(this,'N')" onchange=""/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="805">
			<p><p2> 2. Información Actividad Comercial</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Nombre Actividad *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_Actividad"
						id="txt_Actividad" size="50" maxlength="100" onkeypress="Upper(this,'L')" onchange=""/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Honorarios sector Público *</strong></td>
					<td width="16%" colspan="3"><input type="radio"
						name="rbt_Honorarios" id="rbt_HonSi" value="1" />Si<br />
					<input type="radio" name="rbt_Honorarios" id="rbt_HonNo" value="0" onchange=""/>No<br />
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_CalleComerc"
						id="txt_CalleComerc" size="50" maxlength="50" onkeypress="Upper(this,'A')" onchange=""/></td>
					<td height="34"><strong>Número *</strong></td>
					<td colspan="3"><input type="text" name="txt_NumeroComerc"
						id="txt_NumeroComerc" size="5" maxlength="6" onkeypress="Upper(this,'A')" onchange=""/></td>
				</tr>
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3"><input type="text" name="txt_PoblVillaComerc"
						id="txt_poblVillaComerc" size="50" maxlength="50" onkeypress="Upper(this,'A')" onchange=""/></td>
					<td height="34"><strong>Departamento</strong></td>
					<td colspan="3"><input type="text" name="txt_DptoComerc"
						id="txt_DptoComerc" size="5" maxlength="5" onkeypress="Upper(this,'A')" onchange=""/></td>
				</tr>
				<tr>
					<td><strong>Teléfono Comercial</strong></td>
					<td colspan="3">
						<input type="text" name="txt_codAreaComerc" id="txt_codAreaComerc" maxlength="3" size="1" onkeypress="Upper(this,'N')" onchange=""/>
						<input type="text" name="txt_TelComerc" id="txt_TelComerc" maxlength="8" size="10" onkeypress="Upper(this,'N')" onchange=""/>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>E-Mail Comercial</strong></td>
					<td colspan="3"><input type="text" name="txt_EmailComercial"
						id="txt_EmailComercial" size="50" maxlength="100" onkeypress="Upper(this,'M')" onblur="validarEmail(this.value)" onchange="Cambios(1);"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>					
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3"><html:select property="dbx_RegComerc"
						styleClass="dbx_geo" value="0"
						onchange="cargarProvincias(ModSolForm.dbx_ProvinciaComerc,ModSolForm.dbx_ComunaComerc,this.value,0);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListRegiones" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>	
					<td><strong>Provincia *</strong></td>
					<td colspan="3"><select name="dbx_ProvinciaComerc"
						id="dbx_ProvinciaComerc" style="width:330px"
						onchange="cargarComunas(ModSolForm.dbx_ComunaComerc,this.value,0);">
						<option value="0">Seleccione</option>
					</select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Comuna *</strong></td>
					<td colspan="3"><select name="dbx_ComunaComerc" id="dbx_ComunaComerc" style="width:330px">
						<option value="0">Seleccione</option>
					</select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			
			</table>
			</td>
		</tr>
		<tr>
			<td width="805">
			<p><p2>3. Información de Renta</p2></p>
			<table border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Renta Imponible (SII) *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_RentaImp" id="txt_RentaImp" maxlength="9" onkeypress="Upper(this,'N')" onchange="calculoValorACotizarParam();"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Monto Última Cotizaci&oacute;n *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_MontoUltimaCotizacion" id="txt_MontoUltimaCotizacion" maxlength="9" onkeypress="Upper(this,'N')" onchange="calculoValorACotizarParam();"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Valor a Cotizar *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_ValorACot" id="txt_ValorACot"
						disabled="true" maxlength="7" onkeypress="Upper(this,'N')"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Fecha Última Cotizaci&oacute;n *</strong></td>
					<td width="16%" colspan="3">
						<input type="text" name="txt_FechaUltimaCotizacion"
						id="txt_FechaUltimaCotizacion" class="datepick" disabled="true" size="10"
						value='<%=request.getAttribute("txt_FechaUltimaCotizacion")==null? "":request.getAttribute("txt_FechaUltimaCotizacion")%>'>
<!--						<img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg"
						width="16" height="16" onclick="ShowCalendarFor(txt_FechaUltimaCotizacion)">-->
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>								
			</table>
			</td>
		</tr>
		<tr>
			<td width="805">
			<p><p2> 4. Información de Afiliación a CCAF La Araucana y Desafiliación de Otra Caja de Compensación</p2></p>
			<table border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Caja Compensación *</strong></td>
					<td width="16%" colspan="3"><html:select property="dbx_CajaCompensacion"
						styleClass="dbx_cajaCompensacion" value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListCajas" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Fecha de Vigencia de Afiliación CCAF La
					Araucana *</strong></td>
					<td width="16%" colspan="3">
						<input type="text" name="txt_FecVigAfil" id="txt_FecVigAfil" class="datepick" disabled="true" size="10" onchange="Cambios(1);"/>
<!--						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecVigAfil);" id="img_FecVigencia">-->
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- <tr>
			<td height="100" width="805">
			<p><p2></p2><br />
			5. Desafiliación Sistema CCAF La Araucana</p2></p>
			<table border="0">
				<tr>
					<td><strong>Fecha Ultimo Aporte</strong></td>
					<td><input name="txt_FecUltAporte" type="text"
						id="txt_FecUltAporte" disabled="true" /></td>
				</tr>
				<tr>
					<td><strong>Motivo Desafiliacion</strong></td>
					<td><select name="dbx_MotDesafiliacion"
						id="dbx_MotDesafiliacion" disabled="true">
						<option value="0">Seleccione</option>
					</select></td>
				</tr>
			</table>
			</td>
		</tr> -->
		
		<tr>
			<td height="23" width="805">
			<p><p2></p2><br/>
			5. Antecedentes Proceso de Afiliación</p2></p>
			<table width="100%" border="1" rules="groups">								
				<tr>
					<td><strong>Rut Analista *</strong></td>
					<td colspan="7"><input type="text" name="txt_RutCaptador"
						id="txt_RutCaptador" size="8" maxlength="12"
						onkeypress="Upper(this,'N')"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno</strong></td>
					<td width="16%"><input name="txt_ApePatCaptador" type="text"
						id="txt_ApePatCaptador" maxlength="50" onkeypress="Upper(this,'L')" disabled="true"/></td>
					<td><strong>Apellido Materno</strong></td>
					<td><input type="text" name="txt_ApeMatCaptador"
						id="txt_ApeMatCaptador" maxlength="50" onkeypress="Upper(this,'L')" disabled="true"/></td>
					<td><strong>Nombre Captador</strong></td>
					<td colspan="3"><input type="text" name="txt_NombreCaptador"
						id="txt_NombreCaptador" maxlength="100" onkeypress="Upper(this,'L')" disabled="true"/></td>
				</tr>	
				<tr>
					<td><strong>Oficina</strong></td>
					<td colspan="2" width="16%"><input type="text" name="txt_Sucursal"
						id="txt_Sucursal" maxlength="3" disabled="true" size="50"/></td>
					<td colspan="5"></td>
				</tr>							
				<tr>
					<td><strong>Fecha Ingreso</strong></td>
					<td width="16%"><input type="text" name="txt_FecIngr" id="txt_FecIngr" disabled="true" size="10"/>
					<td><strong>Hora *</strong>(formato HH:MM)</td>
					<td colspan="5"><input type="text" name="txt_Hora" maxlength="7" size="7" id="txt_Hora" value="" onchange="javascript:IsValidTime(txt_Hora)" disabled="true"></td>
				</tr>
				<tr>
					<!-- Se cambia de nombre Fecha Firma por Fecha Solicitud (solo en formulario). -->
					<td><strong>Fecha Solicitud</strong></td>
					<td>
						<input type="text" name="txt_FecFirma" id="txt_FecFirma" class="datepick" disabled="true" size="10" />
<!--						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecFirma)" id="img_FecFirma">-->
					</td>
					<td><strong>Resolución Directorio</strong></td><td><input size="10" type="text" name="txt_ResolucionDirectorio" id="txt_ResolucionDirectorio" disabled="true" value=""></td>
					
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td>
						<strong>Rut Promotor</strong>
					</td>
					<td colspan="3">
						<input type="text" name="txt_RutEjec" id="txt_RutEjec" size="8" maxlength="12" onkeypress="Upper(this,'N')" />
						&nbsp;&nbsp;&nbsp;
						<input type="button" name="btn_buscarIdCaptador" id="btn_buscarIdCaptador" class="btn_limp"	value="Buscar" onclick="buscarCaptador();"/>
						(Ingrese Rut sin guión ni dígito verificador)
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno</strong></td>
					<td width="16%"><input name="txt_ApePatEjec" type="text"
						id="txt_ApePatEjec" maxlength="50" onkeypress="Upper(this,'L')" disabled="true"/></td>
					<td><strong>Apellido Materno</strong></td>
					<td><input type="text" name="txt_ApeMatEjec"
						id="txt_ApeMatEjec" maxlength="50" onkeypress="Upper(this,'L')" disabled="true"/></td>
					<td><strong>Nombre Promotor</strong></td>
					<td colspan="3"><input type="text" name="txt_NombreEjec"
						id="txt_NombreEjec" maxlength="100" onkeypress="Upper(this,'L')" disabled="true"/></td>
				</tr>

								
			</table>
			</td>
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
	
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 1350px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
	
		<div id="mantenedorDocumentos" style="visibility:hidden; position:absolute; margin-top: 100px; margin-left: 180px; width: 750px; height: 395px; background-color: #FFFFFF">
		</div>
	
	</div>
	
	<div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 1350px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
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
