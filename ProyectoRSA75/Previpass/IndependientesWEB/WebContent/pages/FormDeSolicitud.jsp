<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- <%@ taglib uri="/WEB-INF/c.tld" prefix="c"%> -->
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA TRABAJADOR INDEPENDIENTE</title>
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
<script type="text/javascript"
	src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript"
	src="/IndependientesWEB/dwr/interface/NewSolicitudDWR.js"></script>

<script type="text/javascript"><!--
	
	var arregloAgrupaciones = new Array();
	
	cargaArreglos();
	
	function asignaValor(a){

		document.NewSolForm.opcion.value = a;
		
	}
	
	/*Funcion que valida los campos obligatorios del formulario */
	function validaForm(){
	
		if(document.NewSolForm.dbx_Oficina.value == 0 ){
			alert("Falta ingresar el campo Oficina.");
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_Folio.value) == "" ){
			alert("Falta ingresar el campo Folio.");
			return false;
		} 
		
		if(Trim(document.NewSolForm.txt_Rut.value) == "" ){
			alert("Falta ingresar el campo Rut.");
			return false;
		}	
			
		if(Trim(document.NewSolForm.txt_NumVerif.value) == "" ){
			alert("Falta ingresar el Dígito Verificador."); 
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_ApePat.value) == "" ){
			alert("Falta ingresar el campo Apellido Paterno.");
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_ApeMat.value) == "" ){
			alert("Falta ingresar el campo Apellido Materno.");
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_Nombres.value) == "" ){
			alert("Falta ingresar el campo Nombres.");
			return false;
		}
		
		if(document.NewSolForm.dbx_Nacionalidad.value == 0 ){
			alert("Falta ingresar el campo Nacionalidad.");
			return false;
		}
		
		if(document.NewSolForm.txt_FecNac.value == "" ){
			alert("Falta ingresar el campo Fecha de Nacimiento.");
			return false;
		}
		
		if(document.NewSolForm.dbx_Sexo.value == 0 ){
			alert("Falta ingresar el campo Tipo Sexo.");
			return false;
		}
		
		if(document.NewSolForm.dbx_EstCivil.value == 0 ){
			alert("Falta ingresar el campo Estado Civil.");
			return false;
		}
		
		
		if(document.NewSolForm.txt_codAreaCelular.value == "" ){
			alert("Falta ingresar el campo Dígito del Teléfono Celular");
			return false;
		}
		
		if(document.NewSolForm.txt_TelCelular.value == "" ){
			alert("Falta ingresar el campo Teléfono Celular");
			return false;
		}
		
		//document.NewSolForm.txt_codAreaContacto.value == "" //no obligatorio
		//document.NewSolForm.txt_TelContacto.value == "" //no obligatorio
		if(Trim(document.NewSolForm.txt_Email.value) == "" ){
			alert("Falta ingresar el campo Email.");
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_Calle.value) == "" ){
			alert("Falta ingresar el campo Calle del Item 1.");
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_Numero.value) == "" ){
			alert("Falta ingresar el campo Número del Item 1.");
			return false;
		}
			
		//Trim(document.NewSolForm.txt_PoblVilla.value) == "" //no obligatorio
		//Trim(document.NewSolForm.txt_Departamento.value == "" || //no obligatorio
		
		if(document.NewSolForm.dbx_Region.value == 0 ){
			alert("Falta ingresar el campo Región del Item 1.");
			return false;	
		}
		
		if(document.NewSolForm.dbx_Provincia.value == 0 ){
			alert("Falta ingresar el campo Provincia del Item 1."); 
			return false;
		}
		
		if(document.NewSolForm.dbx_Comuna.value == 0 ){
			alert("Falta ingresar el campo Comuna del Item 1.");
			return false;
		}
		
		if(document.NewSolForm.dbx_NivEstudios.value == 0 ){
			alert("Falta ingresar el campo Nivel de Estudios."); 
			return false;
		}
		
		//document.NewSolForm.dbx_TitAcademico.value == "" //no obligatorio
		//document.NewSolForm.rbt_AgrupSi.checked == false //no obligatorio
		//document.NewSolForm.rbt_AgrupNo.checked == false //no obligatorio
		//REQ5348
		var optAgrupa 			= document.NewSolForm.rbt_Agrupacion;
		var razonSocialAgrup	= document.NewSolForm.dbx_RazonSocialAgrup.value;
		var rutAgrup 			= document.NewSolForm.txt_RutAgrup.value;
		var dv 					= document.NewSolForm.txt_NumVerifAgrup.value;	
		
		if(!validaCamposAgrupacion(optAgrupa,razonSocialAgrup,rutAgrup, dv))
		{
			return false;
		}					
		//FIN REQ5348		
		if(document.NewSolForm.dbx_RegPrevisional.value == 0 ){
			alert("Falta ingresar el campo Régimen Previsional.");
			return false;
		}
		
		//document.NewSolForm.dbx_RegSalud.value = 0 //no obligatorio
		
		
		/*if((document.NewSolForm.rbt_ConySi.checked == false && document.NewSolForm.rbt_ConyNo.checked == false) ){ 
			alert("Falta ingresar el campo Cónyuge.");
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_Hijos.value) == "" ){
			alert("Falta ingresar el campo Nº Hijos.");
			return false;
		}*/
		
		/*campos del punto 2*/
		if(Trim(document.NewSolForm.txt_Actividad.value) == "" ){ 
			alert("Falta ingresar el campo Nombre de Actividad.");
			return false;
		}
		
		if((document.NewSolForm.rbt_HonSi.checked == false && document.NewSolForm.rbt_HonNo.checked == false) ){
			alert("Falta ingresar el campo Honorarios.");
			return false;
		}
		
		if(Trim(document.NewSolForm.txt_CalleComerc.value) == "" ){ 
			alert("Falta ingresar el campo Calle del Item 2.");
			return false;
		}	
			
		if(Trim(document.NewSolForm.txt_NumeroComerc.value) == "" ){
			alert("Falta ingresar el campo Número del Item 2.");
			return false;
		}
		
		//document.NewSolForm.txt_PoblVillaComerc.value = "" //no obligatorio
		//document.NewSolForm.txt_DptoComerc.value = "" //no obligatorio
		//document.NewSolForm.txt_codAreaComerc.value == "" //no obligatorio
		//document.NewSolForm.txt_TelComerc.value = "" //no obligatorio
		
		if(document.NewSolForm.dbx_RegComerc.value == 0 ){
			alert("Falta ingresar el campo Región del Item 2.");
			return false;
		}
		
		if(document.NewSolForm.dbx_ProvinciaComerc.value == 0 ){
			alert("Falta ingresar el campo Provincia del Item 2.");
			return false; 
		}
		
		if(document.NewSolForm.dbx_ComunaComerc.value == 0 ){
			alert("Falta ingresar el campo Comuna del Item 2.");
			return false;
		}
		
		/*campos del punto 3*/
		if(Trim(document.NewSolForm.txt_RentaImp.value) == "" ){
			alert("Falta ingresar el campo Renta Imponible.");
			return false;
		}
		//REQ5348
		if(Trim(document.NewSolForm.txt_MontoUltimaCotizacion.value) == "" ){
			alert("Falta ingresar el campo Monto Ultima Cotización.");
			return false;
		}	

		if(Trim(document.NewSolForm.txt_FechaUltimaCotizacion.value) == "" ){
			alert("Falta ingresar el campo Fecha Ultima Cotización.");
			return false;
		}	
		
		//FIN REQ5348		
		//Trim(document.NewSolForm.txt_RentaCot.value) == "" ||
		
		if(Trim(document.NewSolForm.txt_ValorACot.value) == "" ){
			alert("Falta ingresar el campo Valor a Cotizar.");
			return false;
		}
		
		/*Aqui se limpian los campos del punto 4*/
		if(document.NewSolForm.dbx_CajaCompensacion.value == 0 ){
			alert("Falta ingresar el campo Caja de Compensación.");
			return false;
		}
		
		if(document.NewSolForm.txt_FecVigAfil.value == "" ){
			alert("Falta ingresar el campo Fecha de Vigencia de Afiliación CCAF La Araucana");
			return false;
		}
		/*campos del punto 5, campos existen y ahora se consideran*/
		if(
			Trim(document.NewSolForm.txt_RutEjec.value) == "" || 
			Trim(document.NewSolForm.txt_ApePatEjec.value) == "" || 
			Trim(document.NewSolForm.txt_ApeMatEjec.value) == "" ||
			Trim(document.NewSolForm.txt_NombreEjec.value) == "" 
		){
			alert("Debe ingresar el Rut del promotor y presionar el botón Buscar.");
			return false;
		}		
		
		if(Trim(document.NewSolForm.txt_Sucursal.value) == "No Seleccionado"){
			alert("Debe volver a ingresar el campo Oficina.");
			return false;
		}
		
		//document.NewSolForm.txt_FecIngr.value == ""
		
		if(document.NewSolForm.txt_Hora.value == "" ){
			alert("Falta ingresar el campo Hora Captación.");
			return false;
		}
		
		if(document.NewSolForm.txt_FecFirma.value == "" ){
			alert("Falta ingresar el campo Fecha Solicitud.");
			return false;
		}else{	
		
			if( validaFecSolicitud() == false )
			{
				alert("La Fecha de Solicitud no puede ser anterior al 01/01/2012.");
				return false;
			}
		}	
		
		if(ValidaMayoriaDeEdad(document.NewSolForm.txt_FecNac.value))
		{
			if(ValidaFechaVigencia(document.NewSolForm.txt_FecVigAfil.value))
			{
				if(ValidadorRUT(document.NewSolForm.txt_Rut.value,document.NewSolForm.txt_NumVerif.value))   
				{
					if(Trim(document.NewSolForm.txt_Email.value) == "" )
					{
							asignaValor(1);
							desBloqueaCampos();
							document.NewSolForm.submit();
					}else{
						if(validarEmail(document.NewSolForm.txt_Email.value))
						{
							asignaValor(1);
							desBloqueaCampos();
							document.NewSolForm.submit();
						}
					}
				}
			}
		}
	}
	
	/*Funcion que desbloquea los campos del formulario*/
	function desBloqueaCampos(){
	
		document.NewSolForm.txt_Folio.disabled = false;
		document.NewSolForm.txt_Rut.disabled = false;
		document.NewSolForm.txt_FecNac.disabled = false;
		document.NewSolForm.txt_FecVigAfil.disabled = false;
		document.NewSolForm.txt_ValorACot.disabled = false;
		document.NewSolForm.txt_ApePatEjec.disabled = false;
		document.NewSolForm.txt_ApeMatEjec.disabled = false;
		document.NewSolForm.txt_NombreEjec.disabled = false;
		document.NewSolForm.txt_FecFirma.disabled = false;
		document.NewSolForm.txt_Hora.disabled = false;
	}
	/* REQ5348 */	
	function habilitaAgrupacion()
	{
		var opAgrupa	=	document.NewSolForm.rbt_Agrupacion;
		var razonSocial	=	document.NewSolForm.dbx_RazonSocialAgrup;
		var rutAgrup	=	document.NewSolForm.txt_RutAgrup;
		var dv			=	document.NewSolForm.txt_NumVerifAgrup;
		
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
		var opAgrupa	=	document.NewSolForm.rbt_Agrupacion;
		var id			=	document.NewSolForm.dbx_RazonSocialAgrup.value;//valor seleccionado del combo agrupacion
		
		var rutAgrup	=	document.NewSolForm.txt_RutAgrup;//Rut Agrupacion a obtener
		var dvAgrup 	= 	document.NewSolForm.txt_NumVerifAgrup;//Digito Verificador a obtener.
		
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
	
	function validaCamposAgrupacion(optAgrupa,razonSocialAgrup,rutAgrup, dv)
	{
		var i=0;
		
		for(j=0;j<optAgrupa.length;j++){
		
			if(optAgrupa[j].checked){
				i++;
			}
			if(optAgrupa[j].value == 1 && optAgrupa[j].checked){		
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
	
	/* FIN REQ5348 */	
	/*Funcion que valida los datos luego de cancelar alguna accion.*/
	function validaCancelar()
	{		
		if (
			document.NewSolForm.dbx_Oficina.value != "0" ||
			document.NewSolForm.txt_Folio.value != "" ||
			document.NewSolForm.txt_Rut.value != "" ||
			document.NewSolForm.txt_NumVerif.value != "" ||
			Trim(document.NewSolForm.txt_ApePat.value) != "" ||
			Trim(document.NewSolForm.txt_ApeMat.value) != "" ||
			Trim(document.NewSolForm.txt_Nombres.value) != "" ||
			document.NewSolForm.dbx_Nacionalidad.value != "0" ||
			document.NewSolForm.txt_FecNac.value != "" ||
			document.NewSolForm.dbx_Sexo.value != "0" ||
			document.NewSolForm.dbx_EstCivil.value != "0" ||
			document.NewSolForm.txt_codAreaCelular.value != "" ||
			document.NewSolForm.txt_TelCelular.value != "" ||
			document.NewSolForm.txt_codAreaContacto.value != "" ||
			document.NewSolForm.txt_TelContacto.value != "" ||
			Trim(document.NewSolForm.txt_Email.value) != "" ||
			Trim(document.NewSolForm.txt_Calle.value) != "" ||
			Trim(document.NewSolForm.txt_Numero.value) != "" ||
			Trim(document.NewSolForm.txt_PoblVilla.value) != "" ||
			Trim(document.NewSolForm.txt_Departamento.value) != "" ||
			document.NewSolForm.dbx_Region.value != "0" ||
			document.NewSolForm.dbx_Provincia.value != "0" ||
			document.NewSolForm.dbx_Comuna.value != "0" ||
			document.NewSolForm.dbx_NivEstudios.value != "0" ||
			document.NewSolForm.dbx_TitAcademico.value != "0" ||
			
			//REQ5348
			//radio button agrupacion
			//document.NewSolForm.rbt_Agrupacion
			//document.NewSolForm.rbt_Agrupacion[0].checked == false ||
			//document.NewSolForm.rbt_Agrupacion[1].checked == true ||
			document.NewSolForm.dbx_RazonSocialAgrup.value != "0" ||
			document.NewSolForm.txt_RutAgrup.value != "" ||
			document.NewSolForm.txt_NumVerifAgrup.value != "" ||
			//FIN REQ5348
			document.NewSolForm.dbx_RegPrevisional.value != "0" ||
			document.NewSolForm.dbx_RegSalud.value != "0" ||

			//radio button conyugue
			document.NewSolForm.rbt_ConySi.checked != false ||
			document.NewSolForm.rbt_ConyNo.checked != false ||
			document.NewSolForm.txt_Hijos.value != "" ||
		
			/*Aqui se limpian los campos del punto 2*/
			document.NewSolForm.txt_Actividad.value != "" ||

			//radio button honorarios
			document.NewSolForm.rbt_HonSi.checked != false ||
			document.NewSolForm.rbt_HonNo.checked != false ||
			Trim(document.NewSolForm.txt_CalleComerc.value) != "" ||
			Trim(document.NewSolForm.txt_NumeroComerc.value) != "" ||
			Trim(document.NewSolForm.txt_PoblVillaComerc.value) != "" || 
			Trim(document.NewSolForm.txt_DptoComerc.value) != "" ||
			document.NewSolForm.txt_codAreaComerc.value != "" ||
			document.NewSolForm.txt_TelComerc.value != "" ||
			document.NewSolForm.dbx_RegComerc.value != "0" ||
			document.NewSolForm.dbx_ProvinciaComerc.value != "0" ||
			document.NewSolForm.dbx_ComunaComerc.value != "0" ||
		
			/*Aqui se limpian los campos del punto 3*/
			document.NewSolForm.txt_RentaImp.value != "" ||
			document.NewSolForm.txt_RentaCot.value != "0" ||
			document.NewSolForm.txt_ValorACot.value != "" ||
		
			/*Aqui se limpian los campos del punto 4*/
			document.NewSolForm.dbx_CajaCompensacion.value != "0" 
			// || document.NewSolForm.txt_FecVigAfil.value != "" -- Comentado porque el campo se llena por defecto
		
			/*Aqui se limpian los campos del punto 5*/
			/*document.NewSolForm.txt_RutEjec.value != "" ||
			document.NewSolForm.txt_ApePatEjec.value != "" ||
			document.NewSolForm.txt_ApeMatEjec.value != "" ||
			document.NewSolForm.txt_NombreEjec.value != "" ||
			document.NewSolForm.txt_Sucursal.value != "" ||
			document.NewSolForm.txt_FecIngr.value != "" 
			|| document.NewSolForm.txt_FecFirma.value != ""*/
		){
			var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			
			if(respuesta == true){
				asignaValor(2);
				document.NewSolForm.submit();
			}
			
		}else{
			asignaValor(2);
			document.NewSolForm.submit();
		}
	}
	
	/*Funcion que obtiene los datos de una solicitud, asociada a un rut.*/
	function obtenerNewSolicitudDWR()
	{
		limpiarFormulario(1);
		
		var rut = Trim(document.NewSolForm.txt_Rut.value);
		var dv = Trim(document.NewSolForm.txt_NumVerif.value);
	
		if (rut.length != 0 && dv.length != 0){
		
			if(ValidadorRUT(rut,dv))
			{		NewSolicitudDWR.obtenerSolicitud(rut, function(data){
					var solicitud = null;
					solicitud = data;
					if(solicitud.resultado != "" || solicitud.codResultado != 0){
						if(solicitud.codResultado != 1){
							document.NewSolForm.txt_Rut.value = "";
							document.NewSolForm.txt_NumVerif.value = "";
							
						}						
						if(solicitud.codResultado == 2){
							//Por validacion de intercaja
							alert(solicitud.resultado);
						}
						
					}else{
					
						var persona = null;
						var telPart = null
						var telCel = null;
						var telCom = null;
						var email = null;
						var direcPart = null;
						var direcComer = null;
						var afiliado = null;
						var grupoFam = null;
						var ingreso = null;
						var ejecutivo = null;
						var solicitudvo = null;
						//REQ5348						
						var emailComercial = null;
						//FIN REQ5348	
				
						persona = solicitud.personaVO;
						telPart = solicitud.telefonoPartVO;
						telCel = solicitud.telefonoCeluVO;
						telCom = solicitud.telefonoComerVO;
						email = solicitud.emailVO;
						direcPart = solicitud.direccionPartVO;
						direcComer = solicitud.direccionComerVO;
						afiliado = solicitud.afiliadoVO;
						grupoFam = solicitud.grupoFamiliarVO;
						ingreso = solicitud.ingresoEconomicoVO;
						ejecutivo = solicitud.analistaVO;
						//REQ5348
						emailComercial=solicitud.emailComerVO;						
						//FIN REQ5348						
						solicitudvo = solicitud.solicitudVO;
						
						
						if(afiliado.tipoEstadoAfiliado == 1 || afiliado.tipoEstadoAfiliado == 2 || afiliado.tipoEstadoAfiliado == 3 || afiliado.tipoEstadoAfiliado == 6)
						{
							switch(afiliado.tipoEstadoAfiliado){
								case 1:
									alert("Existe una solicitud de afiliación en proceso.");
									break;
								case 2:
									alert("Afiliado vigente en la CCAF La Araucana.");
									break;
								case 3:
									alert("Afiliado bloqueado en la CCAF La Araucana.");
									break;
								case 6:
									alert("Afiliado Fallecido.");
									break;
								default:
									alert("No existe el afiliado.");
							}
							
							
							document.NewSolForm.txt_Rut.value = "";
							document.NewSolForm.txt_NumVerif.value = "";
							
						}else{
						
							document.NewSolForm.opcion;
							document.NewSolForm.resultado;
							
							//document.NewSolForm.dbx_EstSolicitud.value = afiliado.tipoEstadoAfiliado;
							//document.NewSolForm.dbx_tipoEstadoAfiliadoAux.value = afiliado.tipoEstadoAfiliado;
							//document.NewSolForm.txt_EstSolicitud.value = afiliado.desTipoEstadoAfiliado;
							
							//-1. Encabezado de Busqueda
							//document.NewSolForm.txt_NFolio.value = solicitudvo.folio;
							//document.NewSolForm.txt_NRut;
							//document.NewSolForm.txt_NNumVerif;
							
							//0. Informacion del Formulario
							//document.NewSolForm.txt_Fecha;
							//document.NewSolForm.txt_Folio.value = solicitudvo.folio;
							
							//1. Identificación
							//document.NewSolForm.txt_Rut.value = persona.idDocumento;
							//document.NewSolForm.txt_NumVerif.value = persona.digVerificador;
							
							//trae informacion de la oficina.
							document.NewSolForm.dbx_Oficina.value = 0;//ejecutivo.oficina;
							
							document.NewSolForm.txt_ApePat.value = persona.apellidoPaterno;
							document.NewSolForm.txt_ApeMat.value = persona.apellidoMaterno;
							document.NewSolForm.txt_Nombres.value = persona.nombres;
							document.NewSolForm.dbx_Nacionalidad.value = persona.tipoNacionalidad;
							document.NewSolForm.txt_FecNac.value = persona.fechaNacimiento;
							document.NewSolForm.dbx_Sexo.value = persona.tipoSexo;
							document.NewSolForm.dbx_EstCivil.value = afiliado.tipoEstado;
							
							document.NewSolForm.txt_codAreaCelular.value = telCel.codArea;
							document.NewSolForm.txt_TelCelular.value = telCel.nroTelefono;
							
							document.NewSolForm.txt_codAreaContacto.value = telPart.codArea;
							document.NewSolForm.txt_TelContacto.value = telPart.nroTelefono;
							
							document.NewSolForm.txt_Email.value = email.direccMail;
							document.NewSolForm.txt_Calle.value = direcPart.glosCalle;
							document.NewSolForm.txt_Numero.value = direcPart.numDireccion;
							document.NewSolForm.txt_PoblVilla.value = direcPart.poblacionVilla;
							document.NewSolForm.txt_Departamento.value = direcPart.dpto;
						
							document.NewSolForm.dbx_Region.value = direcPart.region;
							cargarProvincias(document.NewSolForm.dbx_Provincia,document.NewSolForm.dbx_Comuna,direcPart.region,direcPart.ciudad);
							cargarComunas(document.NewSolForm.dbx_Comuna,direcPart.ciudad,direcPart.comuna);
							
							document.NewSolForm.dbx_NivEstudios.value = afiliado.tipoNivelEduc;
							document.NewSolForm.dbx_TitAcademico.value = afiliado.tipoProfesion;
							document.NewSolForm.dbx_RegPrevisional.value = afiliado.tipoAfp;
							document.NewSolForm.dbx_RegSalud.value = afiliado.tipoRegSalud;
	
							/*if (grupoFam.conyugue == 0){
								document.NewSolForm.rbt_ConyNo.checked = true;
							}else{
								document.NewSolForm.rbt_ConySi.checked = true;
							}*/
							if(grupoFam.conyugue == -1){
								document.NewSolForm.rbt_ConyNo.checked = false;
								document.NewSolForm.rbt_ConySi.checked = false;
							}else{
								if (grupoFam.conyugue == 0){
									document.NewSolForm.rbt_ConyNo.checked = true;
								}else{
									document.NewSolForm.rbt_ConySi.checked = true;
								}
							}
						//REQ5348														
							if(afiliado.idSecuenciaAgrupacion!=0){
								document.NewSolForm.dbx_RazonSocialAgrup.value=afiliado.idSecuenciaAgrupacion;								
								cambiaRutAgrup();	
								document.NewSolForm.rbt_Agrupacion[0].checked = true;								
								document.NewSolForm.dbx_RazonSocialAgrup.disabled=false;																					
							}else{
								document.NewSolForm.rbt_Agrupacion[0].checked = false;
							}
							if(emailComercial!=null){
								document.NewSolForm.txt_EmailComercial.value=emailComercial.direccMail;								
							}
						//FIN REQ5348							
							document.NewSolForm.txt_Hijos.value = grupoFam.cantHijos;
						
							//2. Informacion Actividad Comercial
							document.NewSolForm.txt_Actividad.value = ingreso.actEconom;
						
							if (ingreso.honorario == 0){
								document.NewSolForm.rbt_HonNo.checked = true;
							}else{
								document.NewSolForm.rbt_HonSi.checked = true;
							}
						
							document.NewSolForm.txt_CalleComerc.value = direcComer.glosCalle;
							document.NewSolForm.txt_NumeroComerc.value = direcComer.numDireccion;
							document.NewSolForm.txt_PoblVillaComerc.value = direcComer.poblacionVilla;
							document.NewSolForm.txt_DptoComerc.value = direcComer.dpto;
							document.NewSolForm.txt_codAreaComerc.value = telCom.codArea;
							document.NewSolForm.txt_TelComerc.value = telCom.nroTelefono;
						
							document.NewSolForm.dbx_RegComerc.value = direcComer.region;
							cargarProvincias(document.NewSolForm.dbx_ProvinciaComerc,document.NewSolForm.dbx_ComunaComerc,direcComer.region,direcComer.ciudad);
							cargarComunas(document.NewSolForm.dbx_ComunaComerc,direcComer.ciudad,direcComer.comuna);
						
							//3. Informacion de Renta
							document.NewSolForm.txt_RentaImp.value = ingreso.rentaImponible;
							document.NewSolForm.txt_RentaCot.value = ingreso.rentaCotizada;
							document.NewSolForm.txt_MontoUltimaCotizacion.value = ingreso.montoUltimaCotizacion;							
							document.NewSolForm.txt_FechaUltimaCotizacion.value = ingreso.fecUltCotizacion;							
							document.NewSolForm.txt_ValorACot.value = afiliado.montoCotizar;
							
							document.NewSolForm.dbx_CajaCompensacion.value = solicitudvo.tipoCajaOrigen;
							
							/*document.NewSolForm.txt_RutEjec.value = ejecutivo.idAnalista;
							document.NewSolForm.txt_ApePatEjec.value = ejecutivo.apellidoPaterno;
							document.NewSolForm.txt_ApeMatEjec.value = ejecutivo.apellidoMaterno;
							document.NewSolForm.txt_NombreEjec.value = ejecutivo.nombres;
							document.NewSolForm.txt_Sucursal.value = ejecutivo.desOficina;
							document.NewSolForm.txt_FecFirma.value = solicitudvo.fechaFirma;
							*/
							if(solicitudvo.horaCaptacion!=null && solicitudvo.horaCaptacion!=""){
								document.NewSolForm.txt_Hora.value = solicitudvo.horaCaptacion;
							}else{
								document.NewSolForm.txt_Hora.disabled=false;
							}
							/*if(solicitudvo.resolucionDirectorio!=null){
								document.NewSolForm.txt_ResolucionDirectorio.value = solicitudvo.resolucionDirectorio;
							}*/													
							//4. Campos Ocultos (ID)
							document.NewSolForm.idPersona.value = persona.idPersona;
							//document.NewSolForm.idSolicitud.value = solicitudvo.idSolicitud;
							//document.NewSolForm.idAfiliadoAgrupacion.value = 
							document.NewSolForm.idGrupoFam.value = grupoFam.idGrupoFam;
							document.NewSolForm.idIngEconom.value = ingreso.idIngEconom;
							document.NewSolForm.idSecuenciaTelefonoPart.value = telPart.idSecuenciaTelefono;
							document.NewSolForm.idSecuenciaTelefonoCelu.value = telCel.idSecuenciaTelefono;
							document.NewSolForm.idSecuenciaEmail.value = email.idSecuenciaEmail;
							document.NewSolForm.idSecuenciaDireccionPart.value = direcPart.idSecuenciaDireccion;
							document.NewSolForm.idSecuenciaDireccionComerc.value = direcComer.idSecuenciaDireccion;
							document.NewSolForm.idSecuenciaTelefonoComerc.value = telCom.idSecuenciaTelefono;
							
						}
					}
				});		
			}else{
				document.NewSolForm.txt_NumVerif.value = "";
			}
		}
	}
	
	/*Funcion que revisa el resultado*/
	function revisaResultado(){
		if(document.NewSolForm.resultado.value != "" && document.NewSolForm.resultado.value != "null"){
		
			if (document.NewSolForm.resultado.value != "0")
			{
				document.NewSolForm.dbx_Oficina.value = '<%=session.getAttribute("Oficina")%>';
				document.NewSolForm.dbx_Nacionalidad.value = '<%=session.getAttribute("Nacionalidad")%>';
				document.NewSolForm.dbx_Sexo.value = '<%=session.getAttribute("Sexo")%>';
				document.NewSolForm.dbx_EstCivil.value = '<%=session.getAttribute("EstCivil")%>';
				
				document.NewSolForm.dbx_Region.value = '<%=session.getAttribute("Region")%>';
				cargarProvincias(document.NewSolForm.dbx_Provincia,document.NewSolForm.dbx_Comuna,'<%=session.getAttribute("Region")%>','<%=session.getAttribute("Provincia")%>');
				cargarComunas(document.NewSolForm.dbx_Comuna,'<%=session.getAttribute("Provincia")%>','<%=session.getAttribute("Comuna")%>');
				
				document.NewSolForm.dbx_NivEstudios.value = '<%=session.getAttribute("NivEstudios")%>';
				document.NewSolForm.dbx_TitAcademico.value = '<%=session.getAttribute("TitAcademico")%>';
				document.NewSolForm.dbx_RegPrevisional.value = '<%=session.getAttribute("RegPrevisional")%>';
				document.NewSolForm.dbx_RegSalud.value = '<%=session.getAttribute("RegSalud")%>';
				
				if ('<%=session.getAttribute("Conyugue")%>' == 0){
					document.NewSolForm.rbt_ConySi.checked = 'true';
				}else{
					document.NewSolForm.rbt_ConyNo.checked = 'true';
				}
				
				if ('<%=session.getAttribute("Honorarios")%>' == 0){
					document.NewSolForm.rbt_HonNo.checked = 'true';
				}else{
					document.NewSolForm.rbt_HonSi.checked = 'true';
				}
				
				document.NewSolForm.dbx_RegComerc.value = '<%=session.getAttribute("RegComerc")%>';
				cargarProvincias(document.NewSolForm.dbx_ProvinciaComerc,document.NewSolForm.dbx_ComunaComerc,'<%=session.getAttribute("RegComerc")%>','<%=session.getAttribute("ProvinciaComerc")%>');
				cargarComunas(document.NewSolForm.dbx_ComunaComerc,'<%=session.getAttribute("ProvinciaComerc")%>','<%=session.getAttribute("ComunaComerc")%>');
				
				document.NewSolForm.dbx_CajaCompensacion.value = '<%=session.getAttribute("CajaCompensacion")%>';
			}
			alert('<%=session.getAttribute("msgRespuesta")%>');
		}
		
		
		$('.datepick').each(function(){
		    $(this).datepicker({
			      showOn: "button",
			      buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			      buttonImageOnly: true,
			      buttonText: "Seleccionar fecha"
			});
		});
		
			/*$(function() {
			    $( "#txt_FecNac").datepicker({
			      showOn: "button",
			      buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			      buttonImageOnly: true,
			      buttonText: "Seleccionar fecha"
			    });
		  });*/
		
	}
	
	/*Funcion que limpia el formulario.*/
	function limpiarFormulario(valor){
	
		if (valor == 0){
		
			document.NewSolForm.dbx_Oficina.value = 0;
			//document.NewSolForm.txt_Fecha.value = "";
			document.NewSolForm.txt_Folio.value = "";
			document.NewSolForm.txt_Rut.value = "";
			document.NewSolForm.txt_NumVerif.value = "";
			
			/*Aqui se limpian los campos del punto 4*/
			document.NewSolForm.dbx_CajaCompensacion.value = 0;
			//document.NewSolForm.txt_FecVigAfil.value = "";
		
		}
		
		document.NewSolForm.txt_ApePat.value = "";
		document.NewSolForm.txt_ApeMat.value = "";
		document.NewSolForm.txt_Nombres.value = "";
		document.NewSolForm.dbx_Nacionalidad.value = 0;
		document.NewSolForm.txt_FecNac.value = "";
		document.NewSolForm.dbx_Sexo.value = 0;
		document.NewSolForm.dbx_EstCivil.value = 0;
		document.NewSolForm.txt_codAreaCelular.value = "";
		document.NewSolForm.txt_TelCelular.value = "";
		document.NewSolForm.txt_codAreaContacto.value = "";
		document.NewSolForm.txt_TelContacto.value = "";
		document.NewSolForm.txt_Email.value = "";
		document.NewSolForm.txt_Calle.value = "";
		document.NewSolForm.txt_Numero.value = "";
		document.NewSolForm.txt_PoblVilla.value = "";
		document.NewSolForm.txt_Departamento.value = "";
		document.NewSolForm.dbx_Region.value = 0;
		document.NewSolForm.dbx_Provincia.value = 0;
		document.NewSolForm.dbx_Comuna.value = 0;
		document.NewSolForm.dbx_NivEstudios.value = 0;
		document.NewSolForm.dbx_TitAcademico.value = 0;
		
		//REQ5348
		//radio button agrupacion
		/* Se comentan debido a que no se utilizan en esta fase.*/			
		document.NewSolForm.dbx_RazonSocialAgrup.value = 0;
		document.NewSolForm.txt_RutAgrup.value = "";
		document.NewSolForm.txt_NumVerifAgrup.value = "";
		//FIN REQ5348
		document.NewSolForm.dbx_RegPrevisional.value = 0;
		document.NewSolForm.dbx_RegSalud.value = 0;

		//radio button conyugue
		for(i = 0; i<2; i++){
			document.NewSolForm.rbt_Conyugue[i].checked = false;
		}
		
		document.NewSolForm.txt_Hijos.value = "";
		
		/*Aqui se limpian los campos del punto 2*/
		document.NewSolForm.txt_Actividad.value = "";

		//radio button honorarios
		for(k = 0; k<2; k++){
			document.NewSolForm.rbt_Honorarios[k].checked = false;
		}
		
		document.NewSolForm.txt_CalleComerc.value = "";
		document.NewSolForm.txt_NumeroComerc.value = "";
		document.NewSolForm.txt_PoblVillaComerc.value = ""; 
		document.NewSolForm.txt_DptoComerc.value = "";
		document.NewSolForm.txt_codAreaComerc.value = "";
		document.NewSolForm.txt_TelComerc.value = "";
		document.NewSolForm.dbx_RegComerc.value = 0;
		document.NewSolForm.dbx_ProvinciaComerc.value = 0;
		document.NewSolForm.dbx_ComunaComerc.value = 0;
		
		/*Aqui se limpian los campos del punto 3*/
		document.NewSolForm.txt_RentaImp.value = "";
		document.NewSolForm.txt_RentaCot.value = "";
		document.NewSolForm.txt_ValorACot.value = "";
		document.NewSolForm.txt_MontoUltimaCotizacion.value = "";
		document.NewSolForm.txt_FechaUltimaCotizacion.value = "";		
		
		/*Aqui se limpian los campos del punto 5 - NO SE DEBiERAN LIMPIAR*/
		//document.NewSolForm.txt_RutEjec.value = "";
		//document.NewSolForm.txt_ApePatEjec.value = "";
		//document.NewSolForm.txt_ApeMatEjec.value = "";
		//document.NewSolForm.txt_NombreEjec.value = "";
		//document.NewSolForm.txt_Sucursal.value = "";
		//document.NewSolForm.txt_FecIngr.value = "";
		
		limpiarCamposBusqueda();
		document.NewSolForm.txt_FecFirma.value = "";
		
		document.NewSolForm.idPersona.value = 0;
		document.NewSolForm.idGrupoFam.value = 0;
		document.NewSolForm.idIngEconom.value = 0;
		document.NewSolForm.idSecuenciaTelefonoPart.value = 0;
		document.NewSolForm.idSecuenciaTelefonoCelu.value = 0;
		document.NewSolForm.idSecuenciaEmail.value = 0;
		document.NewSolForm.idSecuenciaDireccionPart.value = 0;
		document.NewSolForm.idSecuenciaDireccionComerc.value = 0;
		document.NewSolForm.idSecuenciaTelefonoComerc.value = 0;	
	}

	/*Funcion que selecciona la oficina.*/
	function seleccionaOficina(){
	
		var selIndex = document.NewSolForm.dbx_Oficina.selectedIndex;
		
		if(selIndex == 0){
		
			desBloqueaCampoIndiv(document.NewSolForm.txt_Sucursal);
			document.NewSolForm.txt_Sucursal.value = "No Seleccionado";
			bloqueaCampoIndiv(document.NewSolForm.txt_Sucursal);
		}else{
	
			desBloqueaCampoIndiv(document.NewSolForm.txt_Sucursal);
			document.NewSolForm.txt_Sucursal.value = document.NewSolForm.dbx_Oficina.options[selIndex].text;
			bloqueaCampoIndiv(document.NewSolForm.txt_Sucursal);
		}
	}
	
	function enviaFormulario(a){
	
		asignaValor(a);
		document.NewSolForm.submit();
	}
	
	/*Funcion que calcula el valor a cotizar.*/
	function calculoValorACotizar(){
		
		var rentaImp = 0;
		var rentaCot = 0;
		var valorACot = 0;
		
		if(document.NewSolForm.txt_RentaImp.value != 0 && document.NewSolForm.txt_RentaImp.value != null && document.NewSolForm.txt_RentaImp.value != "")
			rentaImp = parseFloat(document.NewSolForm.txt_RentaImp.value);
			
		if(document.NewSolForm.txt_RentaCot.value != 0 && document.NewSolForm.txt_RentaCot.value != null && document.NewSolForm.txt_RentaCot.value != "")	
			rentaCot = parseFloat(document.NewSolForm.txt_RentaCot.value);
				
		if(rentaImp != 0){
			
			valorACot = Math.round(rentaImp * 0.01);
		
		}else{
			
			if( rentaCot != 0){
			
				valorACot = Math.round(rentaCot * 0.01);
			
			}else{
				
				valorACot = 0;
			}
		}
		
		desBloqueaCampoIndiv(document.NewSolForm.txt_ValorACot);
		document.NewSolForm.txt_ValorACot.value = valorACot;
		bloqueaCampoIndiv(document.NewSolForm.txt_ValorACot);			
	}
	
	/* Funcion que calcula el valor a cotizar basado en la parametrizacion */
	function calculoValorACotizarParam(){
	
		var rentaImp = 0;
		var rentaCot = 0;
		var valorACot = 0;
	
		var tipoCalculo  = '<%=session.getAttribute("TxtSelTipoCalculoAporte")%>';
		var valorCalculo = '<%=session.getAttribute("TxtValorCalculoAporte")%>';
		
		if (tipoCalculo == 1){
		
			if(document.NewSolForm.txt_RentaImp.value != 0 && document.NewSolForm.txt_RentaImp.value != null && document.NewSolForm.txt_RentaImp.value != "")
				rentaImp = parseFloat(document.NewSolForm.txt_RentaImp.value);
				
			if(document.NewSolForm.txt_RentaCot.value != 0 && document.NewSolForm.txt_RentaCot.value != null && document.NewSolForm.txt_RentaCot.value != "")	
				rentaCot = parseFloat(document.NewSolForm.txt_RentaCot.value);
					
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
	
		desBloqueaCampoIndiv(document.NewSolForm.txt_ValorACot);
		document.NewSolForm.txt_ValorACot.value = valorACot;
		bloqueaCampoIndiv(document.NewSolForm.txt_ValorACot);	
	}
	
	/*Funcion que valida la mayoria de edad.*/
	function ValidaMayoriaDeEdad(fecha){
	
		if(Comparar_Fecha_Anyo('<%=session.getAttribute("FechaSistema")%>',fecha, 18)){
			alert("Para poder ingresar una solicitud, el solicitante debe ser mayor de 18 años.");
			return false;
		}
		return true;
	}
	
	/*Funcion que valida la fecha de vigencia.*/
	function ValidaFechaVigencia(fecha)
	{
		if(!Comparar_Fecha_Anyo('<%=session.getAttribute("FechaSistema")%>',fecha, 0)){
			alert("La fecha de vigencia debe ser mayor o igual a la fecha actual");
			return false;
		}
		return true;
	}
	
	//					<input size="10" type="text" name="txt_FecVigAfil" id="txt_FecVigAfil" disabled="true" value='<%=request.getAttribute("txt_FecVigAfil")==null? "":request.getAttribute("txt_FecVigAfil")%>'/>
	//					<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecVigAfil)">
	
	/*Funcion que obtiene al analista captador.*/
	function buscarCaptador(){
		
		var rut = Trim(document.NewSolForm.txt_RutEjec.value);
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
						
						desBloqueaCampoIndiv(document.NewSolForm.txt_ApePatEjec);
						desBloqueaCampoIndiv(document.NewSolForm.txt_ApeMatEjec);
						desBloqueaCampoIndiv(document.NewSolForm.txt_NombreEjec);
						
						document.NewSolForm.txt_ApePatEjec.value = solicitud.apellidoPaterno;
						document.NewSolForm.txt_ApeMatEjec.value = solicitud.apellidoMaterno;
						document.NewSolForm.txt_NombreEjec.value = solicitud.nombres;
						
						bloqueaCampoIndiv(document.NewSolForm.txt_ApePatEjec);
						bloqueaCampoIndiv(document.NewSolForm.txt_ApeMatEjec);
						bloqueaCampoIndiv(document.NewSolForm.txt_NombreEjec);
					}		
				});
			
			}else{
				
				alert("El rut ingresado no es válido.");
			}
		}
	}
	
	/*Funcion que limpia los campos de busqueda para analista.*/
	function limpiarCamposBusqueda()
	{
		document.NewSolForm.txt_RutEjec.value = "";
		
		desBloqueaCampoIndiv(document.NewSolForm.txt_ApePatEjec);
		desBloqueaCampoIndiv(document.NewSolForm.txt_ApeMatEjec);
		desBloqueaCampoIndiv(document.NewSolForm.txt_NombreEjec);

		document.NewSolForm.txt_ApePatEjec.value = "";
		document.NewSolForm.txt_ApeMatEjec.value = "";
		document.NewSolForm.txt_NombreEjec.value = "";
		
		bloqueaCampoIndiv(document.NewSolForm.txt_ApePatEjec);
		bloqueaCampoIndiv(document.NewSolForm.txt_ApeMatEjec);
		bloqueaCampoIndiv(document.NewSolForm.txt_NombreEjec);
	
	}
	
	/*Funcion que valida la fecha de solicitud.*/
	function validaFecSolicitud()
	{
		var fechaSolicitud = "01/01/2012";
		var fechaSolIngresada = document.NewSolForm.txt_FecFirma.value;
		
		if(Comparar_Fecha(fechaSolIngresada,fechaSolicitud))
		{
			return true;
		}
		
		return false;	
	}

	function IsValidTime(timeStr) 
	{
		// Checks if time is in HH:MM:SS AM/PM format.
		// The seconds and AM/PM are optional.
		var timeObj=timeStr.value;
		
		if(timeStr!=null && timeStr!="")
		{
			var timePat = /^\d{1,2}:\d{2}([ap]m)?$/;	
			var matchArray = timeObj.match(timePat);
			if (matchArray == null) 
			{
				alert("La hora no esta en un formato valido.");
				timeStr.value="";
				return false;
			}
			hour = timeObj.substring(0,2);
			minute = timeObj.substring(3,5);
			if (hour < 0  || hour > 23) 
			{
				alert("La hora debe estar entre 0 y 23");
				timeStr.value="";
				return false;
			}
			if (minute<0 || minute > 59) 
			{
				alert ("Minutos deben estar entre 0 y 59.");
				timeStr.value="";
				return false;
			}
		}
		return true;
	}	

	

	function cargaArreglos(){
		GeograficoDWR.obtenerListaAgrupacion("ListAgrupacionFull", function(data){
		
			var agrupaciones = null
			agrupaciones = data;
			
			for (i = 0; i < agrupaciones.length; i++){
				arregloAgrupaciones[i] = new ObjAgrupacion(agrupaciones[i].idSecuencia, agrupaciones[i].razonSocial, agrupaciones[i].idDocumento, agrupaciones[i].digVerDocumento);
			}
		});
	}
	
	
	

--></script>

</head>

<body onload="revisaResultado();">
<div id="caja_registro"><html:form action="/newSol.do">
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="resultado"
		value="<%=request.getAttribute("resultado")%>">

	<input type="hidden" name="idPersona" value="0">
	<input type="hidden" name="idGrupoFam" value="0">
	<input type="hidden" name="idIngEconom" value="0">
	<input type="hidden" name="idSecuenciaTelefonoPart" value="0">
	<input type="hidden" name="idSecuenciaTelefonoCelu" value="0">
	<input type="hidden" name="idSecuenciaEmail" value="0">
	<input type="hidden" name="idSecuenciaDireccionPart" value="0">
	<input type="hidden" name="idSecuenciaDireccionComerc" value="0">
	<input type="hidden" name="idSecuenciaTelefonoComerc" value="0">
	<!-- REQ5348 -->
	<input type="hidden" name="txt_RentaCot" value="0">
	<!-- Estos son los campos de agrupacion comentados mas abajo 
	<input type="hidden" name="rbt_Agrupacion" value="0">
	<input type="hidden" name="dbx_RazonSocialAgrup" value="0">
	<input type="hidden" name="txt_RutAgrup" value="0">
	<input type="hidden" name="txt_NumVerifAgrup" value="0">
	-->
	<!-- FIN REQ5348 -->
	<table width="970">
		<tr>
			<td align="right"><a href="#" align="right"
				onclick="validaCancelar();">Volver</a> &nbsp;&nbsp;&nbsp; <a
				href="#" align="right" onClick="enviaFormulario(-1);">Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			<table border="0">
				<tr>
					<td><strong><p1>INGRESO SOLICITUD TRABAJADOR
					INDEPENDIENTE</p1></strong></td>
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
					<td>Oficina *</td>
					<td><html:select property="dbx_Oficina"
						styleClass="dbx_oficina" value="0" onchange="seleccionaOficina();">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListOficinas" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" size="10"
						value='<%=session.getAttribute("FechaSistema")%>' id="txt_Fecha"
						disabled="true" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td height="404">
			<p><p2>1. Identificación del Trabajador Independiente</p2>
			<p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>N° Folio *</strong></td>
					<td width="16%"><input type="text" name="txt_Folio" 
						id="txt_Folio" maxlength="12" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Folio")==null? "":request.getAttribute("txt_Folio")%>' /></td>
					<td width="16%"><strong>N° RUT *</strong></td>
					<td width="16%"><input type="text" name="txt_Rut" id="txt_Rut"
						maxlength="10" size="9" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Rut")==null? "":request.getAttribute("txt_Rut")%>' />
					<strong> - </strong> <input type="text" name="txt_NumVerif"
						id="txt_NumVerif" size="1" maxlength="1"
						onkeypress="Upper(this,'D')" style="text-transform: uppercase;" onblur="obtenerNewSolicitudDWR();"
						value='<%=request.getAttribute("txt_NumVerif")==null? "":request.getAttribute("txt_NumVerif")%>' />
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno *</strong></td>
					<td><input name="txt_ApePat" type="text" id="txt_ApePat"
						maxlength="50" onkeypress="Upper(this,'L')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_ApePat")==null? "":request.getAttribute("txt_ApePat")%>' /></td>
					<td><strong>Apellido Materno *</strong></td>
					<td><input type="text" name="txt_ApeMat" id="txt_ApeMat"
						maxlength="50" onkeypress="Upper(this,'L')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_ApeMat")==null? "":request.getAttribute("txt_ApeMat")%>' /></td>
					<td><strong>Nombres *</strong></td>
					<td colspan="3"><input type="text" name="txt_Nombres"
						id="txt_Nombres" maxlength="100" onkeypress="Upper(this,'L')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Nombres")==null? "":request.getAttribute("txt_Nombres")%>' /></td>
				</tr>
				<tr>
					<td><strong>Nacionalidad *</strong></td>
					
					<td><!-- <select property="dbx_Nacionalidad"	styleClass="combobox" value="0"> -->
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
						</select></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="30"><strong>Fecha de Nacimiento *</strong></td>
					<td><input type="text" name="txt_FecNac" id="txt_FecNac" class="datepick"
						disabled="true" size="10"
						value='<%=request.getAttribute("txt_FecNac")==null? "":request.getAttribute("txt_FecNac")%>' />
					<!--<IMG style="cursor:hand" border="0"
						src="/IndependientesWEB/images/Calendar.jpg" width="16"
						height="16" onClick="ShowCalendarFor(txt_FecNac)">
						--></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Sexo *</strong></td>
					<td><html:select property="dbx_Sexo" styleClass="combobox"
						value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListSexoBox" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					
					<td><strong>Estado Civil *</strong></td>
					
					<td>
					<!-- 
						<html:select property="dbx_EstCivil" styleClass="combobox" value="0">
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
					<td><input size="1" type="text" name="txt_codAreaCelular"
						id="txt_codAreaCelular" maxlength="2" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_codAreaCelular")==null? "":request.getAttribute("txt_codAreaCelular")%>' />
					<input size="10" type="text" name="txt_TelCelular"
						id="txt_TelCelular" maxlength="8" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_TelCelular")==null? "":request.getAttribute("txt_TelCelular")%>' />
					</td>
					<td><strong>Teléfono de Contacto</strong></td>
					<td><input size="1" type="text" name="txt_codAreaContacto"
						id="txt_codAreaContacto" maxlength="3"
						onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_codAreaContacto")==null? "":request.getAttribute("txt_codAreaContacto")%>' />
					<input size="10" type="text" name="txt_TelContacto"
						id="txt_TelContacto" maxlength="8" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_TelContacto")==null? "":request.getAttribute("txt_TelContacto")%>' />
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>

				<tr>
					<td><strong>E-Mail *</strong></td>
					<td colspan="3"><input type="text" name="txt_Email"
						id="txt_Email" size="50" maxlength="100"
						onkeypress="Upper(this,'M')" style="text-transform: uppercase;" onblur="validarEmail(this.value)"
						value='<%=request.getAttribute("txt_Email")==null? "":request.getAttribute("txt_Email")%>' />
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_Calle"
						id="txt_Calle" size="50" maxlength="50"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Calle")==null? "":request.getAttribute("txt_Calle")%>' /></td>
					<td height="34"><strong>Número *</strong></td>
					<td colspan="3"><input type="text" name="txt_Numero"
						id="txt_Numero" size="5" maxlength="6"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Numero")==null? "":request.getAttribute("txt_Numero")%>' /></td>
				</tr>
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3"><input type="text" name="txt_PoblVilla"
						id="txt_PoblVilla" size="50" maxlength="50"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_PoblVilla")==null? "":request.getAttribute("txt_PoblVilla")%>' /></td>
					<td height="34"><strong>Departamento</strong></td>
					<td colspan="3"><input type="text" name="txt_Departamento"
						id="txt_Departamento" size="5" maxlength="5"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Departamento")==null? "":request.getAttribute("txt_Departamento")%>' /></td>
				</tr>
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3"><html:select property="dbx_Region"
						styleClass="dbx_geo" value="0"
						onchange="cargarProvincias(NewSolForm.dbx_Provincia,NewSolForm.dbx_Comuna,this.value,0);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListRegiones" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Provincia *</strong></td>
					<td colspan="3"><select style="width:330px"
						name="dbx_Provincia" id="dbx_Provincia"
						onchange="cargarComunas(NewSolForm.dbx_Comuna,this.value,0);">
						<option value="0">Seleccione</option>
					</select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Comuna *</strong></td>
					<td colspan="3"><select style="width:330px" name="dbx_Comuna"
						id="dbx_Comuna">
						<option value="0">Seleccione</option>
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
						<html:select property="dbx_NivEstudios"	styleClass="dbx_geo" value="0">
							<html:option value="0">Seleccione</html:option>
							<html:options collection="ListNivelEducacionalBox"	property="codigo" labelProperty="glosa" />
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
						</html:select>
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
					</td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<!-- REQ5348 -->
				<tr>
					<td><strong>Agrupación (Si/No) *</strong></td>
					<td><input type="radio" name="rbt_Agrupacion"
						id="rbt_Agrupacion" value="1"
						onclick="javascript:habilitaAgrupacion()" />Si<br />
					<input type="radio" name="rbt_Agrupacion" id="rbt_Agrupacion"
						value="0" onclick="javascript:habilitaAgrupacion()" />No<br />
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td width="16%"><strong> Razón Social Agrupación</strong> *</td>
					<td><html:select property="dbx_RazonSocialAgrup"
						onchange="javascript:cambiaRutAgrup()" styleClass="dbx_agrupa"
						value="0" disabled="true">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListAgrupacionFull"
							property="idSecuencia" labelProperty="razonSocial" />
					</html:select></td>
					<td><strong>RUT Agrupación</strong></td>
					<td><input size="12" maxlength="10" type="text"
						name="txt_RutAgrup" id="txt_RutAgrup" disabled="true" /><strong>-
					</strong> <input type="text" name="txt_NumVerifAgrup" id="txt_NumVerifAgrup"
						size="1" disabled="true"></td>
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
						value="1" />Si<br />
					<input type="radio" name="rbt_Conyugue" id="rbt_ConyNo" value="0" />No<br />
					</td>
					<td><strong>Hijos </strong></td>
					<td><input type="text" name="txt_Hijos" id="txt_Hijos"
						size="5" maxlength="2" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Hijos")==null? "":request.getAttribute("txt_Hijos")%>' /></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<p><p2>2. Información Actividad Comercial</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Nombre Actividad *</strong></td>
					<td width="16%" colspan="3"><input type="text"
						name="txt_Actividad" id="txt_Actividad" size="50" maxlength="100"
						onkeypress="Upper(this,'L')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_Actividad")==null? "":request.getAttribute("txt_Actividad")%>' /></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Honorarios sector Público *</strong></td>
					<td width="16%" colspan="3"><input type="radio"
						name="rbt_Honorarios" id="rbt_HonSi" value="1" />Si<br />
					<input type="radio" name="rbt_Honorarios" id="rbt_HonNo" value="0" />No<br />
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_CalleComerc"
						id="txt_CalleComer" size="50" maxlength="50"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_CalleComerc")==null? "":request.getAttribute("txt_CalleComerc")%>' /></td>
					<td height="34"><strong>Número *</strong></td>
					<td colspan="3"><input type="text" name="txt_NumeroComerc"
						id="txt_NumeroComerc" size="5" maxlength="6"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_NumeroComerc")==null? "":request.getAttribute("txt_NumeroComerc")%>' /></td>
				</tr>
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3"><input type="text" name="txt_PoblVillaComerc"
						id="txt_PoblVillaComerc" size="50" maxlength="50"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_PoblVillaComerc")==null? "":request.getAttribute("txt_PoblVillaComerc")%>' /></td>
					<td height="34"><strong>Departamento</strong></td>
					<td colspan="3"><input type="text" name="txt_DptoComerc"
						id="txt_DptoComerc" size="5" maxlength="5"
						onkeypress="Upper(this,'A')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_DptoComerc")==null? "":request.getAttribute("txt_DptoComerc")%>' /></td>
				</tr>
				<tr>
					<td><strong>Teléfono Comercial</strong></td>
					<td colspan="3"><input type="text" name="txt_codAreaComerc"
						id="txt_codAreaComerc" maxlength="3" size="1"
						onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_codAreaComerc")==null? "":request.getAttribute("txt_codAreaComerc")%>' />
					<input size="10" type="text" name="txt_TelComerc"
						id="txt_TelComerc" maxlength="8" onkeypress="Upper(this,'N')"
						value='<%=request.getAttribute("txt_TelComerc")==null? "":request.getAttribute("txt_TelComerc")%>' /></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><strong>E-Mail Comercial</strong></td>
					<td colspan="3"><input type="text" name="txt_EmailComercial"
						id="txt_EmailComercial" size="50" maxlength="100"
						onkeypress="Upper(this,'M')" style="text-transform: uppercase;" onblur="validarEmail(this.value)"
						value='<%=request.getAttribute("txt_EmailComercial")==null? "":request.getAttribute("txt_EmailComercial")%>'></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3"><html:select property="dbx_RegComerc"
						styleClass="dbx_geo" value="0"
						onchange="cargarProvincias(NewSolForm.dbx_ProvinciaComerc,NewSolForm.dbx_ComunaComerc,this.value,0);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListRegiones" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><strong>Provincia *</strong></td>
					<td colspan="3"><select name="dbx_ProvinciaComerc"
						style="width:330px" id="dbx_ProvinciaComerc"
						onchange="cargarComunas(NewSolForm.dbx_ComunaComerc,this.value,0);">
						<option value="0">Seleccione</option>
					</select></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><strong>Comuna *</strong></td>
					<td colspan="3">
						<select style="width:330px"	name="dbx_ComunaComerc" id="dbx_ComunaComerc">
							<option value="0">Seleccione</option>
						</select>
					</td>
					<td colspan="4"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<p><p2>3. Información de Renta</p2></p>
			<table border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Renta Imponible (SII) *</strong></td>
					<td width="16%" colspan="3"><input type="text"
						name="txt_RentaImp" id="txt_RentaImp" maxlength="9"
						onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						onchange="calculoValorACotizarParam();"
						value='<%=request.getAttribute("txt_RentaImp")==null? "":request.getAttribute("txt_RentaImp")%>' /></td>
					<td></td>
					<td colspan="3"></td>

				</tr>
				<tr>
					<td width="16%"><strong>Monto Última
					Cotizaci&oacute;n AFP *</strong></td>
					<td width="16%" colspan="3"><input type="text"
						name="txt_MontoUltimaCotizacion" id="txt_MontoUltimaCotizacion"
						maxlength="9" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"
						value='<%=request.getAttribute("txt_MontoUltimaCotizacion")==null? "":request.getAttribute("txt_MontoUltimaCotizacion")%>' /></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Valor a Cotizar *</strong></td>
					<td width="16%" colspan="3"><input type="text"
						name="txt_ValorACot" id="txt_ValorACot" maxlength="7"
						onkeypress="Upper(this,'N')" style="text-transform: uppercase;" disabled="true"
						value='<%=request.getAttribute("txt_ValorACot")==null? "":request.getAttribute("txt_ValorACot")%>' /></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Fecha Última
					Cotizaci&oacute;n *</strong></td>
					<td width="16%" colspan="3"><input type="text"
						name="txt_FechaUltimaCotizacion" id="txt_FechaUltimaCotizacion" class="datepick"
						readonly size="10"
						value='<%=request.getAttribute("txt_FechaUltimaCotizacion")==null? "":request.getAttribute("txt_FechaUltimaCotizacion")%>'>
					<!--<img style="cursor:hand" border="0"
						src="/IndependientesWEB/images/Calendar.jpg" width="16"
						height="16" onclick="ShowCalendarFor(txt_FechaUltimaCotizacion)">
					--></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<p><p2>4. Información de Afiliación a CCAF La Araucana y
			Desafiliación de Otra Caja de Compensación</p2></p>
			<table border="1" rules="groups">
				<tr>
					<td width="34%"><strong>Caja Compensación *</strong></td>
					<td width="34%" colspan="3"><html:select
						property="dbx_CajaCompensacion" styleClass="dbx_cajaCompensacion"
						value="0">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListCajas" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="2"><strong>Fecha de Vigencia de
					Afiliación CCAF La Araucana *</strong></td>
					<td width="16%" colspan="3"><input size="10" type="text"
						name="txt_FecVigAfil" id="txt_FecVigAfil" disabled="true"
						value='<%=session.getAttribute("FechaVigencia")%>' /></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<p><p2>5. Antecedentes Proceso de Afiliación</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td><strong>Rut Analista *</strong></td>
					<td width="16%"><strong><input type="text"
						name="txt_RutCaptador" id="txt_RutCaptador"
						value='<%=session.getAttribute("IDAnalista")%>' disabled="true"
						size="8" maxlength="12" onkeypress="Upper(this,'N')"> </strong></td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno</strong></td>
					<td width="16%"><input name="txt_ApePatProm" type="text"
						id="txt_ApePatProm" maxlength="50"
						value='<%=session.getAttribute("ApePatAnalista")%>'
						onkeypress="Upper(this,'L')" style="text-transform: uppercase;" disabled="true" /></td>
					<td><strong>Apellido Materno</strong></td>
					<td><input type="text" name="txt_ApeMaProm"
						id="txt_ApeMatProm" maxlength="50"
						value='<%=session.getAttribute("ApeMatAnalista")%>'
						onkeypress="Upper(this,'L')" style="text-transform: uppercase;" disabled="true" /></td>
					<td><strong>Nombre Analista</strong></td>
					<td colspan="3"><input type="text" name="txt_NombreProm"
						id="txt_NombreProm" maxlength="100"
						value='<%=session.getAttribute("NombreAnalista")%>'
						onkeypress="Upper(this,'L')" style="text-transform: uppercase;" disabled="true" disabled="true" /></td>
				</tr>
				<tr>
					<td><strong>Oficina</strong></td>
					<td colspan="2" width="16%"><input type="text"
						name="txt_Sucursal" id="txt_Sucursal" value="No Seleccionado"
						maxlength="3" disabled="true" size="50" /></td>
					<td colspan="5"></td>
				</tr>
				<tr>
				<tr>
					<td height="4"><strong>Fecha Ingreso</strong></td>
					<td width="16%"><input type="text" name="txt_FecIngr"
						id="txt_FecIngr" value='<%=session.getAttribute("FechaSistema")%>'
						disabled="true" size="10" /></td>
					<td height="4"><strong>Hora Captación*</strong>(formato HH:MM)</td>
					<td><input type="text" name="txt_Hora" id="txt_Hora"
						value='<%=request.getAttribute("txt_Hora")==null? "":request.getAttribute("txt_Hora")%>'
						size="10" onchange="javascript:IsValidTime(txt_Hora)"></td>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<!-- Se cambia de nombre Fecha Firma por Fecha Solicitud (solo en formulario). -->
					<td height="4"><strong>Fecha Solicitud *</strong></td>
					<td height="4"><input type="text" name="txt_FecFirma"
						id="txt_FecFirma" class="datepick" disabled="true" size="10"
						value='<%=request.getAttribute("txt_FecFirma")==null? "":request.getAttribute("txt_FecFirma")%>' />
					<!--<IMG style="cursor:hand" border="0"
						src="/IndependientesWEB/images/Calendar.jpg" width="16"
						height="16" onClick="ShowCalendarFor(txt_FecFirma)">
						--></td>
					<td height="43"><!-- <strong>Resolución Directorio</strong> -->
					</td>
					<td height="43"><!-- <input size="10" type="text" name="txt_ResolucionDirectorio" id="txt_ResolucionDirectorio" disabled="true" value=""> -->
					</td>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Rut Promotor *</strong></td>
					<td colspan="3"><input type="text" name="txt_RutEjec"
						id="txt_RutEjec" size="8" maxlength="12"
						onkeypress="Upper(this,'N')" style="text-transform: uppercase;" /> &nbsp;&nbsp;&nbsp; <input
						type="button" name="btn_buscarIdCaptador"
						id="btn_buscarIdCaptador" class="btn_limp" value="Buscar"
						onClick="buscarCaptador();" /> (Ingrese Rut sin guión ni dígito
					verificador)</td>

					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno</strong></td>
					<td width="16%"><input name="txt_ApePatEjec" type="text"
						id="txt_ApePatEjec" maxlength="50" onkeypress="Upper(this,'L')" style="text-transform: uppercase;"
						disabled="true" /></td>
					<td><strong>Apellido Materno</strong></td>
					<td><input type="text" name="txt_ApeMatEjec"
						id="txt_ApeMatEjec" maxlength="50" onkeypress="Upper(this,'L')" style="text-transform: uppercase;"
						disabled="true" /></td>
					<td><strong>Nombre Promotor</strong></td>
					<td colspan="3"><input type="text" name="txt_NombreEjec"
						id="txt_NombreEjec" maxlength="100" onkeypress="Upper(this,'L')" style="text-transform: uppercase;"
						disabled="true" disabled="true" /></td>
				</tr>


			</table>
			</td>
		</tr>
		<tr>
			<td style="font-size:11px;">(*campos obligatorios)</td>
		</tr>
		<tr>
			<td height="37" align="right"><input type="button"
				name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"
				value="Cancelar" onClick="validaCancelar()" /> &nbsp;&nbsp;&nbsp; <input
				type="button" name="btn_limpiar" id="btn_limpiar" class="btn_limp"
				value="Limpiar" onClick="limpiarFormulario(0)" />
			&nbsp;&nbsp;&nbsp; <input type="button" name="btn_Ingresar"
				id="btn_Ingresar" class="btn_limp" value="Ingresar"
				onClick="validaForm()" /> &nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>

</html:form></div>
</body>
</html:html>
