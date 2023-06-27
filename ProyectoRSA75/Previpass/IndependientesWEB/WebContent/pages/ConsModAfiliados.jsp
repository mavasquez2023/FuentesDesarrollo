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

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/Calendario.js"></script>

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/ModAfiliadoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/RepNominaApoAfiDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>

<script type="text/javascript">
	var arregloAgrupaciones = new Array();
	var arregloPerfiles = null;
	
	cargaArreglos();
	function asignaValor(a){

		document.ModAfiForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		desBloqueaCampos();
		document.ModAfiForm.submit();
	}

	/*funcion que valida los campos obligatorios del formulario.*/
	function validaForm(){
		
		// Poner aqui las validaciones del formulario
		
			if(Trim(document.ModAfiForm.txt_Folio.value) == "" ){
				alert("Falta ingresar el campo Folio.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_Rut.value) == "" ){
				alert("Falta ingresar el campo Rut.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_NumVerif.value) == "" ){ 
				alert("Falta ingresar el campo Dígito Verificador.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_ApePat.value) == "" ){
				alert("Falta ingresar el campo Apellido Paterno");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_ApeMat.value) == "" ){
				alert("Falta ingresar el campo Apellido Materno.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_Nombres.value) == "" ){
				alert("Falta ingresar el campo Nombres.");
				return false;
			}
			
			if(document.ModAfiForm.dbx_Nacionalidad.value == "0" ){
				alert("Falta ingresar el campo Nacionalidad.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_FecNac.value) == "" ){
				alert("Falta ingresar el campo Fecha de Nacimiento.");
				return false;	
			}
			
			if(document.ModAfiForm.dbx_Sexo.value == "0" ){
				alert("Falta ingresar el campo Tipo Sexo.");
				return false;
			}
			
			if(document.ModAfiForm.dbx_EstCivil.value == "0" ){
				alert("Falta ingresar el campo Estado Civil.");
				return false;
			}
			
			if(document.ModAfiForm.txt_codAreaCelular.value == "" ){
				alert("Falta ingresar el campo Dígito del Teléfono Celular.");
				return false;
			}
			
			if(document.ModAfiForm.txt_TelCelular.value == "" ){
				alert("Falta ingresar el campo Telefono Celular");
				return false;
			}
			
			//document.ModAfiForm.txt_codAreaContacto.value == "" //no obligatorio
			//document.ModAfiForm.txt_TelContacto.value == "" //no obligatorio
			if(document.ModAfiForm.txt_Email.value == "" ){
				alert("Falta Ingresar el campo Email.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_Calle.value) == "" ){
				alert("Falta ingresar el campo Calle del Item 1.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_Numero.value) == "" ){
				alert("Falta ingresar el campo Número del Item 1.");
				return false;
			}
			//Trim(document.ModAfiForm.txt_PoblVilla.value) == "" //no obligatorio
			//Trim(document.ModAfiForm.txt_Departamento.value == "" || //no obligatorio
			
			if(document.ModAfiForm.dbx_Region.value == "0" ){
				alert("Falta ingresar el campo Región del Item 1.");
				return false;
			}
			
			if(document.ModAfiForm.dbx_Provincia.value == "0" ){ 
				alert("Falta ingresar el campo Provincia del Item 1.");
				return false;
			}
			
			if(document.ModAfiForm.dbx_Comuna.value == "0" ){
				alert("Falta ingresar el campo Comuna del Item 1.");
				return false;
			}
			
			if(document.ModAfiForm.dbx_NivEstudios.value == "0" ){ 
				alert("Falta ingresar el campo Nivel de Estudios.");
				return false;
			}
			
			if(!document.ModAfiForm.rbt_Agrupacion[0].checked && !document.ModAfiForm.rbt_Agrupacion[1].checked){
				alert("Falta seleccionar si posee Agrupación.");
				return false;
			}else if(document.ModAfiForm.rbt_Agrupacion[0].checked && document.ModAfiForm.dbx_RazonSocialAgrup.value==0){
				alert("Falta seleccionar Agrupación.");
				return false;
			}
			//document.ModAfiForm.dbx_TitAcademico.value == "" //no obligatorio
			//document.ModAfiForm.rbt_AgrupSi.checked == false //no obligatorio
			//document.ModAfiForm.rbt_AgrupNo.checked == false //no obligatorio
			//document.ModAfiForm.dbx_RazonSocialAgrup.value = "" //no obligatorio
			//document.ModAfiForm.txt_RutAgrup.value == "" //no obligatorio
			//document.ModAfiForm.txt_NumVerifAgrup.value == "" //no obligatorio
			//document.ModAfiForm.dbx_TipoAgrup.value = 0 //no obligatorio
			
			if(document.ModAfiForm.dbx_RegPrevisional.value == "0" ){
				alert("Falta ingresar el campo Régimen Previsional.");
				return false;
			}
			
			//document.ModAfiForm.dbx_RegSalud.value = "0" //no obligatorio
			
			//Campos conyuge e hijos ahora no son obligatorios
			/*		
			if((document.ModAfiForm.rbt_ConySi.checked == false && document.ModAfiForm.rbt_ConyNo.checked == false) ){
				alert("Falta ingresar el campo Cónyuge.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_Hijos.value) == "" ){
				alert("Falta ingresar el campo Nº Hijos.");
				return false;
			}*/
			
			if(Trim(document.ModAfiForm.txt_Actividad.value) == "" ){ 
				alert("Falta ingresar el campo Nombre de Actividad.");
				return false;
			}
			
			if((document.ModAfiForm.rbt_HonSi.checked == false && document.ModAfiForm.rbt_HonNo.checked == false) ){
				alert("Falta ingresar el campo Honorarios.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_CalleComerc.value) == "" ){ 
				alert("Falta ingresar el campo Calle del Item 2.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_NumeroComerc.value) == "" ){
				alert("Falta ingresar el campo Numero del Item 2.");
				return false;
			}
			
			//document.ModAfiForm.txt_PoblVillaComerc.value = "" //no obligatorio
			//document.ModAfiForm.txt_DptoComerc.value = "" //no obligatorio
			//document.ModAfiForm.txt_codAreaComerc.value == "" //no obligatorio
			//document.ModAfiForm.txt_TelComerc.value = "" //no obligatorio
			
			if(document.ModAfiForm.dbx_RegComerc.value == "0" ){
				alert("Falta ingresar el campo Región del Item 2.");
				return false;
			}
			
			if(document.ModAfiForm.dbx_ProvinciaComerc.value == "0" ){ 
				alert("Falta ingresar el campo Provincia del Item 2.");
				return false;
			}
			
			if(document.ModAfiForm.dbx_ComunaComerc.value == "0" ){
				alert("Falta ingresar el campo Comuna del Item 2.");
				return false;
			}
			if(Trim(document.ModAfiForm.txt_RentaImp.value) == "" ){
				alert("Falta ingresar el campo Renta Imponible.");
				return false;
			}
			
			if(Trim(document.ModAfiForm.txt_MontoUltimaCotizacion.value) == "" ){
				alert("Falta ingresar el campo Monto Ultima Cotización.");
				return false;
			}			
			
			//Trim(document.ModAfiForm.txt_RentaCot.value) == "" ||
			if(Trim(document.ModAfiForm.txt_ValorACot.value) == "" ){
				alert("Falta ingresar el campo Valor a Cotizar.");
				return false;
			}	
			if(Trim(document.ModAfiForm.txt_FechaUltimaCotizacion.value) == "" ){
				alert("Falta ingresar el campo Fecha Ultima Cotización.");
				return false;
			}	
			if(ValidaMayoriaDeEdad(document.ModAfiForm.txt_FecNac.value))
			{
				if(ValidadorRUT(document.ModAfiForm.txt_NRut.value,document.ModAfiForm.txt_NNumVerif.value))
				{
					if(Trim(document.ModAfiForm.txt_Email.value) == "" )
					{
						return true;
					}else{
						if(validarEmail(document.ModAfiForm.txt_Email.value))
						{
							return true;
						}
					}
				}
			}
	}
	
	/*funcion que valida el boton cancelar luego de haber ejecutado una accion.*/
	function validaCancelar(){
		
		var rut = Trim(document.ModAfiForm.txt_Rut.value);
		var digitoRut = Trim(document.ModAfiForm.txt_NumVerif.value);
		
		if (rut == "" && digitoRut == "" )
		{
			enviaFormulario(1);
			
		}else{
		
			ModAfiliadoDWR.obtenerAfiliado(rut, function(data){
					
				var solicitud = null;
				solicitud = data;
				
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
			
			//Aqui validacion de los campos
			if(
				Trim(document.ModAfiForm.txt_Folio.value) != solicitudvoC.folio ||
				Trim(document.ModAfiForm.txt_Rut.value) != personaC.idDocumento ||
				Trim(document.ModAfiForm.txt_NumVerif.value) != personaC.digVerificador ||
				Trim(document.ModAfiForm.txt_ApePat.value) != personaC.apellidoPaterno ||
				Trim(document.ModAfiForm.txt_ApeMat.value) != personaC.apellidoMaterno ||
				Trim(document.ModAfiForm.txt_Nombres.value) != personaC.nombres ||
				document.ModAfiForm.dbx_Nacionalidad.value != personaC.tipoNacionalidad ||
				Trim(document.ModAfiForm.txt_FecNac.value) != personaC.fechaNacimiento ||
				document.ModAfiForm.dbx_Sexo.value != personaC.tipoSexo ||
				document.ModAfiForm.dbx_EstCivil.value != afiliadoC.tipoEstado ||
				document.ModAfiForm.txt_codAreaCelular.value != telCelC.codArea ||
				document.ModAfiForm.txt_TelCelular.value != telCelC.nroTelefono ||
				//document.ModAfiForm.txt_codAreaContacto.value != "" //no obligatorio
				//document.ModAfiForm.txt_TelContacto.value != "" //no obligatorio
				document.ModAfiForm.txt_Email.value != emailC.direccMail ||
				Trim(document.ModAfiForm.txt_Calle.value) != direcPartC.glosCalle ||
				Trim(document.ModAfiForm.txt_Numero.value) != direcPartC.numDireccion ||
				//Trim(document.ModAfiForm.txt_PoblVilla.value) != "" //no obligatorio
				//Trim(document.ModAfiForm.txt_Departamento.value != "" || //no obligatorio
				document.ModAfiForm.dbx_Region.value != direcPartC.region ||
				//document.ModAfiForm.dbx_Provincia.value !=  
				//document.ModAfiForm.dbx_Comuna.value != 
				document.ModAfiForm.dbx_NivEstudios.value != afiliadoC.tipoNivelEduc ||
				//document.ModAfiForm.dbx_TitAcademico.value != "" //no obligatorio
				//document.ModAfiForm.rbt_AgrupSi.checked != false //no obligatorio
				//document.ModAfiForm.rbt_AgrupNo.checked != false //no obligatorio
				//document.ModAfiForm.dbx_RazonSocialAgrup.value = "" //no obligatorio
				//document.ModAfiForm.txt_RutAgrup.value != "" //no obligatorio
				//document.ModAfiForm.txt_NumVerifAgrup.value != "" //no obligatorio
				//document.ModAfiForm.dbx_TipoAgrup.value = 0 //no obligatorio
				document.ModAfiForm.dbx_RegPrevisional.value != afiliadoC.tipoAfp ||
				//document.ModAfiForm.dbx_RegSalud.value = "0" //no obligatorio
				//(document.ModAfiForm.rbt_ConySi.checked != false &&
				//document.ModAfiForm.rbt_ConyNo.checked != false) ||
				//Trim(document.ModAfiForm.txt_Hijos.value) != "" ||
				Trim(document.ModAfiForm.txt_Actividad.value) != ingresoC.actEconom ||
				//(document.ModAfiForm.rbt_HonSi.checked != false && 
				//document.ModAfiForm.rbt_HonNo.checked != false) ||
				Trim(document.ModAfiForm.txt_CalleComerc.value) != direcComerC.glosCalle ||
				Trim(document.ModAfiForm.txt_NumeroComerc.value) != direcComerC.numDireccion ||
				//document.ModAfiForm.txt_PoblVillaComerc.value = "" //no obligatorio
				//document.ModAfiForm.txt_DptoComerc.value = "" //no obligatorio
				//document.ModAfiForm.txt_codAreaComerc.value != "" //no obligatorio
				//document.ModAfiForm.txt_TelComerc.value = "" //no obligatorio
				document.ModAfiForm.dbx_RegComerc.value != direcComerC.region ||
				//document.ModAfiForm.dbx_ProvinciaComerc.value != 
				//document.ModAfiForm.dbx_ComunaComerc.value != 
				Trim(document.ModAfiForm.txt_RentaImp.value) != ingresoC.rentaImponible ||
				//Trim(document.ModAfiForm.txt_RentaCot.value) != "" ||
				Trim(document.ModAfiForm.txt_ValorACot.value) != afiliadoC.montoCotizar	
			){
				var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
				
				if( respuesta == true){
					enviaFormulario(1);
					//asignaValor(1);
					//document.ModAfiForm.submit();
				}
			}else{
				asignaValor(1);
				document.ModAfiForm.submit();
			}
		  });
	  }	
	}
	
	/*funcion que obtiene los datos de un afiliado.*/
	function obtenerAfiliadoDWR(){
		
		var rut = Trim(document.ModAfiForm.txt_NRut.value);
		var dv = Trim(document.ModAfiForm.txt_NNumVerif.value);
		
		RepNominaApoAfiDWR.consultaRepNominaApoAfiEstados(rut, function(data){
			datos = data;
				if(datos != null){
					if(datos.tipoSolicitud == 2){
						if( datos.tipoEstadoSolicitud == 1 ||
							datos.tipoEstadoSolicitud == 3 ||
							datos.tipoEstadoSolicitud == 4 ||
							datos.tipoEstadoAfiliado != 4)
						   {
						   		alert("El afiliado tiene una solicitud de desafiliación en curso con vigencia a partir del "+datos.fechaVigencia);
						   }
					  }
				}
		});
		
		if (rut.length == 0 || dv.length == 0){
			alert("Para cargar los datos de un afiliado debe ingresar N° RUT y su dígito verificador");
			 
		}else{
		
			if( (rut.length == 0 &&	dv.length != 0) || (rut.length != 0 && dv.length == 0)){
				
				alert("Si ingresa el campo N° RUT debe ingresar el dígito verificador y viceversa.");
				
			}else{
				ModAfiliadoDWR.obtenerAfiliado(rut, function(data){
				
					var solicitud = null;
					solicitud = data;

					if(solicitud.resultado != "" || solicitud.codResultado != 0){
						
						alert("No se encontró el afiliado asociado al RUT.");
					
					}else{
					
						var persona = null;
						var telPart = null
						var telCel = null;
						var telCom = null;
						var email = null;
						var emailComer = null;
						var direcPart = null;
						var direcComer = null;
						var afiliado = null;
						var grupoFam = null;
						var ingreso = null;
						var ejecutivo = null;
						var solicitudvo = null;
				
						persona = solicitud.personaVO;
						telPart = solicitud.telefonoPartVO;
						telCel = solicitud.telefonoCeluVO;
						telCom = solicitud.telefonoComerVO;
						email = solicitud.emailVO;
						emailComer = solicitud.emailComerVO;
						direcPart = solicitud.direccionPartVO;
						direcComer = solicitud.direccionComerVO;
						afiliado = solicitud.afiliadoVO;
						grupoFam = solicitud.grupoFamiliarVO;
						ingreso = solicitud.ingresoEconomicoVO;
						ejecutivo = solicitud.analistaVO;
						solicitudvo = solicitud.solicitudVO;
						afiliadoagrupacionvo=solicitud.afiliadoAgrupacionVO;	
						
						if(afiliado.tipoEstadoAfiliado == 1 || afiliado.tipoEstadoAfiliado == 7)
						{
							alert("No existe el afiliado.");
						}else{
						
							document.ModAfiForm.opcion;
							document.ModAfiForm.resultado;
							
							//document.ModAfiForm.dbx_EstSolicitud.value = afiliado.tipoEstadoAfiliado;
							//document.ModAfiForm.dbx_tipoEstadoAfiliadoAux.value = afiliado.tipoEstadoAfiliado;
							document.ModAfiForm.txt_EstSolicitud.value = afiliado.desTipoEstadoAfiliado;
							
							//-1. Encabezado de Busqueda
							//document.ModAfiForm.txt_NFolio.value = solicitudvo.folio;
							document.ModAfiForm.txt_NRut;
							document.ModAfiForm.txt_NNumVerif;
							
							//0. Informacion del Formulario
							document.ModAfiForm.txt_Fecha;
							document.ModAfiForm.txt_Folio.value = solicitudvo.folio;
							
							//1. Identificación
							document.ModAfiForm.txt_Rut.value = persona.idDocumento;
							document.ModAfiForm.txt_NumVerif.value = persona.digVerificador;
							document.ModAfiForm.txt_ApePat.value = persona.apellidoPaterno;
							document.ModAfiForm.txt_ApeMat.value = persona.apellidoMaterno;
							document.ModAfiForm.txt_Nombres.value = persona.nombres;
							document.ModAfiForm.dbx_Nacionalidad.value = persona.tipoNacionalidad;
							document.ModAfiForm.txt_FecNac.value = persona.fechaNacimiento;
							document.ModAfiForm.dbx_Sexo.value = persona.tipoSexo;
							document.ModAfiForm.dbx_EstCivil.value = afiliado.tipoEstado;
							
							document.ModAfiForm.txt_codAreaCelular.value = telCel.codArea;
							document.ModAfiForm.txt_TelCelular.value = telCel.nroTelefono;
							
							document.ModAfiForm.txt_codAreaContacto.value = telPart.codArea;
							document.ModAfiForm.txt_TelContacto.value = telPart.nroTelefono;
							
							document.ModAfiForm.txt_Email.value = email.direccMail;
							document.ModAfiForm.idSecuenciaEmail.value = email.idSecuenciaEmail;								
							
							document.ModAfiForm.txt_Calle.value = direcPart.glosCalle;
							document.ModAfiForm.txt_Numero.value = direcPart.numDireccion;
							document.ModAfiForm.txt_PoblVilla.value = direcPart.poblacionVilla;
							document.ModAfiForm.txt_Departamento.value = direcPart.dpto;
						
							document.ModAfiForm.dbx_Region.value = direcPart.region;
							cargarProvincias(document.ModAfiForm.dbx_Provincia,document.ModAfiForm.dbx_Comuna,direcPart.region,direcPart.ciudad);
							cargarComunas(document.ModAfiForm.dbx_Comuna,direcPart.ciudad,direcPart.comuna);
						
							document.ModAfiForm.dbx_NivEstudios.value = afiliado.tipoNivelEduc;
							document.ModAfiForm.dbx_TitAcademico.value = afiliado.tipoProfesion;
							document.ModAfiForm.dbx_RegPrevisional.value = afiliado.tipoAfp;
							document.ModAfiForm.dbx_RegSalud.value = afiliado.tipoRegSalud;
							
							if(grupoFam.conyugue == -1){
								document.ModAfiForm.rbt_ConySi.checked = false;
								document.ModAfiForm.rbt_ConyNo.checked = false;
							}else{
								if (grupoFam.conyugue == 0){
									document.ModAfiForm.rbt_ConyNo.checked = true;
								}else{
									document.ModAfiForm.rbt_ConySi.checked = true;
								}
							}
							
						//REQ5348							
							if(afiliado.idSecuenciaAgrupacion!=0){
								document.ModAfiForm.dbx_RazonSocialAgrup.value=afiliado.idSecuenciaAgrupacion;								
								cambiaRutAgrup();
								if(afiliado.tipoEstadoAfiliado == "2"){
									document.ModAfiForm.rbt_Agrupacion[0].checked = true;	
									document.ModAfiForm.rbt_Agrupacion[1].checked = false;
								}else{
									document.ModAfiForm.rbt_Agrupacion[0].checked = true;	
									document.ModAfiForm.rbt_Agrupacion[1].checked = false;																							
								}
							}else{
								document.ModAfiForm.txt_RutAgrup.value="";
								document.ModAfiForm.txt_NumVerifAgrup.value="";
								bloqueaCampoIndiv(document.ModAfiForm.txt_RutAgrup);
								bloqueaCampoIndiv(document.ModAfiForm.txt_NumVerifAgrup);		
								if(afiliado.tipoEstadoAfiliado == "2"){
									document.ModAfiForm.rbt_Agrupacion[0].checked = false;	
									document.ModAfiForm.rbt_Agrupacion[1].checked = true;																		
								}else{
									document.ModAfiForm.rbt_Agrupacion[0].checked = false;	
									document.ModAfiForm.rbt_Agrupacion[1].checked = true;
										
									document.ModAfiForm.dbx_RazonSocialAgrup.value=0;															
								}	
							}
							if(emailComer!=null){							
								document.ModAfiForm.txt_EmailComercial.value=emailComer.direccMail;
								document.ModAfiForm.idSecuenciaEmailComerc.value=emailComer.idSecuenciaEmail;									
							}	
						//FIN REQ5348
							
							document.ModAfiForm.txt_Hijos.value = grupoFam.cantHijos;
						
							//2. Informacion Actividad Comercial
							document.ModAfiForm.txt_Actividad.value = ingreso.actEconom;
						
							if (ingreso.honorario == 0){
								document.ModAfiForm.rbt_HonNo.checked = true;
							}else{
								document.ModAfiForm.rbt_HonSi.checked = true;
							}
						
							document.ModAfiForm.txt_CalleComerc.value = direcComer.glosCalle;
							document.ModAfiForm.txt_NumeroComerc.value = direcComer.numDireccion;
							document.ModAfiForm.txt_PoblVillaComerc.value = direcComer.poblacionVilla;
							document.ModAfiForm.txt_DptoComerc.value = direcComer.dpto;
							document.ModAfiForm.txt_codAreaComerc.value = telCom.codArea;
							document.ModAfiForm.txt_TelComerc.value = telCom.nroTelefono;
						
							document.ModAfiForm.dbx_RegComerc.value = direcComer.region;
							cargarProvincias(document.ModAfiForm.dbx_ProvinciaComerc,document.ModAfiForm.dbx_ComunaComerc,direcComer.region,direcComer.ciudad);
							cargarComunas(document.ModAfiForm.dbx_ComunaComerc,direcComer.ciudad,direcComer.comuna);
						
							//3. Informacion de Renta
							document.ModAfiForm.txt_RentaImp.value = ingreso.rentaImponible;
							document.ModAfiForm.txt_RentaCot.value = ingreso.rentaCotizada;
							document.ModAfiForm.txt_MontoUltimaCotizacion.value = ingreso.montoUltimaCotizacion;
							if(ingreso.fecUltCotizacion!=null){
								document.ModAfiForm.txt_FechaUltimaCotizacion.value = ingreso.fecUltCotizacion;							
							}else{
								document.ModAfiForm.txt_FechaUltimaCotizacion.value = "";														
							}
							document.ModAfiForm.txt_ValorACot.value = afiliado.montoCotizar;
	
							//4. Campos Ocultos (ID)
							document.ModAfiForm.idPersona.value = persona.idPersona;
							document.ModAfiForm.idSolicitud.value = solicitudvo.idSolicitud;
							//document.ModAfiForm.idAfiliadoAgrupacion.value = 
							document.ModAfiForm.idGrupoFam.value = grupoFam.idGrupoFam;
							document.ModAfiForm.idIngEconom.value = ingreso.idIngEconom;
							document.ModAfiForm.idSecuenciaTelefonoPart.value = telPart.idSecuenciaTelefono;
							document.ModAfiForm.idSecuenciaTelefonoCelu.value = telCel.idSecuenciaTelefono;
							//document.ModAfiForm.idSecuenciaEmail.value = email.idSecuenciaEmail;
							document.ModAfiForm.idSecuenciaDireccionPart.value = direcPart.idSecuenciaDireccion;
							document.ModAfiForm.idSecuenciaDireccionComerc.value = direcComer.idSecuenciaDireccion;
							document.ModAfiForm.idSecuenciaTelefonoComerc.value = telCom.idSecuenciaTelefono;
	
							//Busqueda de listado
							ModAfiliadoDWR.getEstadosDestinoPosibles(afiliado.tipoEstadoAfiliado, function(data)
							{
								var resp = null;
								resp = data;
								
								var cod = "0";
								var txt = "";
								
								var cmb = document.ModAfiForm.dbx_EstSolicitud;
		
								cmb.options[0] = new Option("Seleccione..." , "0")
		
								for(var i=0; i<resp.length; i++)
								{
									var item = null;
									item = resp[i];
								
									cod = item.codigo;
									txt = item.glosa;
								
									cmb.options[i+1] = new Option(txt, cod);
								}
							});
							bloqueaFormulario(afiliado.tipoEstadoAfiliado)
							desBloqueaFormulario(afiliado.tipoEstadoAfiliado);
						}	
					}
				});
			}
		}
	}

	/*funcion que realiza un update al tipoestadosfiliado.*/
	function updateEstadoAfiliadoDWR(){
	
		var estadoAfi = document.ModAfiForm.dbx_EstSolicitud.value;
		var estadoAfiAux = document.ModAfiForm.dbx_tipoEstadoAfiliadoAux.value;
		
		var rut = Trim(document.ModAfiForm.txt_Rut.value);
		var digitoRut = Trim(document.ModAfiForm.txt_NumVerif.value);
				
		if(estadoAfi == estadoAfiAux){
		
			alert("No ha realizado cambios al estado cargado.");
			
		}else{
		
			//if (folio == ""){
			if (rut == "" && digitoRut == "" ){
				alert("Debe buscar un RUT y su Digito Verificador para modificar el estado.");
			
			}else{
			
				ModAfiliadoDWR.updateEstadoAfiliado(rut, estadoAfi, function(data){
				
					var resp = null;
					
					resp = data;
					
					switch(resp){
					
						case 0: document.ModAfiForm.txt_EstSolicitud.value = document.ModAfiForm.dbx_EstSolicitud.options[document.ModAfiForm.dbx_EstSolicitud.selectedIndex].text;
								
								//Busqueda de listado
								ModAfiliadoDWR.getEstadosDestinoPosibles(estadoAfi, function(data)
								{
									var resp = null;
									resp = data;
									
									var cod = "0";
									var txt = "";
									
									var cmb = document.ModAfiForm.dbx_EstSolicitud;
			
									cmb.options.length = 0;
									cmb.options[0] = new Option("Seleccione..." , "0")
			
									for(var i=0; i<resp.length; i++)
									{
										var item = null;
										item = resp[i];
									
										cod = item.codigo;
										txt = item.glosa;

										cmb.options[i+1] = new Option(txt, cod);
									}
								});
						
								bloqueaFormulario(estadoAfi)
								desBloqueaFormulario(estadoAfi);
						
								alert("Estado de Afiliado modificado con exito.");
								break;
						case 99: alert("Se produjo un error al modificar el estado.");
								break;
						default : alert("Desastre!!!");
					
					}
				
				});
			}
		}
	}
	
	/*Funcion que permite modificar los datos del afiliado. Construye la cadena de informacion.*/
	function updateDatosAfiliadoDWR(){
		
		if( validaForm() == true ){
			
			var conyugue = 0;
			var honorario = 0;
		
			//para valores de conyugue
			if(document.ModAfiForm.rbt_ConySi.checked == false && document.ModAfiForm.rbt_ConyNo.checked == false){
				conyugue = -1
			}else{
				if(document.ModAfiForm.rbt_ConySi.checked == true){
					conyugue = 1
				}else{
					conyugue = 0
				}
			}	
		
			//para valores de honorario
			if(document.ModAfiForm.rbt_HonSi.checked == true){
			
				honorario = 1;
		
			}else{
			
				honorario = 0;
			}
		
			// Para validacion de campos no Obligatorios
			//var codAreaCel = "0";
			//var telCelular = "0";
			var codAreaCont = "0";
			var telContacto = "0";
			//var email = " ";
			var poblacion = " ";
			var depto = " ";
			//var tituloAcademico = " ";
			var agrupacion = "0";
			//var razonSocial = " ";
			//var tipoAgrupacion = " ";
			//var regSalud = "0";
			var poblacionComerc = " ";
			var deptoComerc = " ";
			var emailComercial = " ";		
			var iDemailComercial = " ";				
			var codAreaComerc = "0";
			var telComerc = "0";
			var rentaCot = "0";
			var hijos = "0";
			
			//Estos campos ahora son obligatorios
			/*if(document.ModAfiForm.txt_codAreaCelular.value != ""){
				codAreaCel = document.ModAfiForm.txt_codAreaCelular.value;
			}		
			if(document.ModAfiForm.txt_TelCelular.value != ""){
				telCelular = document.ModAfiForm.txt_TelCelular.value;
			}*/
			if(document.ModAfiForm.txt_codAreaContacto.value != ""){
				codAreaCont = document.ModAfiForm.txt_codAreaContacto.value;
			}
			if(document.ModAfiForm.txt_TelContacto.value != ""){
				telContacto = document.ModAfiForm.txt_TelContacto.value;
			}
			
			/*if(Trim(document.ModAfiForm.txt_Email.value) != ""){
				email = document.ModAfiForm.txt_Email.value;
			}*/
			
			if(Trim(document.ModAfiForm.txt_PoblVilla.value) != ""){
				poblacion = document.ModAfiForm.txt_PoblVilla.value;
			}
			if(Trim(document.ModAfiForm.txt_Departamento.value) != ""){
				depto = document.ModAfiForm.txt_Departamento.value;
			}
			if(Trim(document.ModAfiForm.txt_PoblVillaComerc.value) != ""){
				poblacionComerc = document.ModAfiForm.txt_PoblVillaComerc.value;
			}
			if(Trim(document.ModAfiForm.txt_DptoComerc.value) != ""){
				deptoComerc = document.ModAfiForm.txt_DptoComerc.value;
			}
			if(document.ModAfiForm.txt_codAreaComerc.value != ""){
				codAreaComerc = document.ModAfiForm.txt_codAreaComerc.value;
			}
			if(document.ModAfiForm.txt_TelComerc.value != ""){
				telComerc = document.ModAfiForm.txt_TelComerc.value;
			}
			if(document.ModAfiForm.txt_RentaCot.value != ""){
				rentaCot = document.ModAfiForm.txt_RentaCot.value;
			}
			if(document.ModAfiForm.txt_Hijos.value != ""){
				hijos = document.ModAfiForm.txt_Hijos.value;
			}
			if(document.ModAfiForm.txt_EmailComercial.value != ""){
				emailComercial = document.ModAfiForm.txt_EmailComercial.value;
			}		
			if(document.ModAfiForm.idSecuenciaEmailComerc.value != "0"){
				iDemailComercial = document.ModAfiForm.idSecuenciaEmailComerc.value;
			}			
			
			idAgrupacion = document.ModAfiForm.dbx_RazonSocialAgrup.value;
			if(idAgrupacion!=0){
				agrupacion+=idAgrupacion;				
			}
			
		
			var cadenaForm = "#" + document.ModAfiForm.opcion + "#" +//01
	 					document.ModAfiForm.resultado + "#" +
	 					document.ModAfiForm.dbx_EstSolicitud.value + "#" + //despues venía Nfolio pero se saca por no formar parte de la búsqueda
	 					document.ModAfiForm.txt_NRut.value + "#" +
	 					document.ModAfiForm.txt_NNumVerif.value + "#" +
	 					document.ModAfiForm.txt_Folio.value + "#" +
	 					document.ModAfiForm.txt_Rut.value + "#" +
	 					document.ModAfiForm.txt_NumVerif.value + "#" +
	 					document.ModAfiForm.txt_ApePat.value + "#" +
	 					document.ModAfiForm.txt_ApeMat.value + "#" +//10
	 					document.ModAfiForm.txt_Nombres.value + "#" +
	 					document.ModAfiForm.dbx_Nacionalidad.value + "#" +
	 					document.ModAfiForm.txt_FecNac.value + "#" +
	 					document.ModAfiForm.dbx_Sexo.value + "#" +
	 					document.ModAfiForm.dbx_EstCivil.value + "#" +
	 					document.ModAfiForm.txt_codAreaCelular.value + "#" + //codAreaCel
	 					document.ModAfiForm.txt_TelCelular.value + "#" + //telCelular
	 					codAreaCont + "#" +
	 					telContacto + "#" + //document.ModAfiForm.txt_TelContacto.value
	 					document.ModAfiForm.txt_Email.value + "#" + //email
	 					document.ModAfiForm.txt_Calle.value + "#" +
	 					document.ModAfiForm.txt_Numero.value + "#" +//20
	 					poblacion + "#" + //document.ModAfiForm.txt_PoblVilla.value
	 					depto + "#" + //document.ModAfiForm.txt_Departamento.value
	 					document.ModAfiForm.dbx_Region.value + "#" +
	 					document.ModAfiForm.dbx_Provincia.value + "#" +
	 					document.ModAfiForm.dbx_Comuna.value + "#" +
	 					document.ModAfiForm.dbx_NivEstudios.value + "#" +
	 					document.ModAfiForm.dbx_TitAcademico.value + "#" + 
	 					" " + "#" + //document.ModAfiForm.rbt_Agrupacion.checked 
	 					" " + "#" + //document.ModAfiForm.dbx_RazonSocialAgrup.value  
	 					" " + "#" + //document.ModAfiForm.txt_RutAgrup.value //30
	 					" " + "#" + //document.ModAfiForm.txt_NumVerifAgrup.value 
	 					" " + "#" + //document.ModAfiForm.dbx_TipoAgrup.value 
	 					document.ModAfiForm.dbx_RegPrevisional.value + "#" +
	 					document.ModAfiForm.dbx_RegSalud.value + "#" +
	 					conyugue + "#" + //document.ModAfiForm.rbt_Conyugue.value
						hijos + "#" + //document.ModAfiForm.txt_Hijos.value
	 					document.ModAfiForm.txt_Actividad.value + "#" + 
	 					honorario + "#" + //document.ModAfiForm.rbt_Honorarios.value
	 					document.ModAfiForm.txt_CalleComerc.value + "#" +
	 					document.ModAfiForm.txt_NumeroComerc.value + "#" +//40
	 					poblacionComerc + "#" + //document.ModAfiForm.txt_PoblVillaComerc.value
	 					deptoComerc + "#" + //document.ModAfiForm.txt_DptoComerc.value
	 					codAreaComerc + "#" +
	 					telComerc + "#" + //document.ModAfiForm.txt_TelComerc.value
	 					document.ModAfiForm.dbx_RegComerc.value + "#" +
	 					document.ModAfiForm.dbx_ProvinciaComerc.value + "#" +
	 					document.ModAfiForm.dbx_ComunaComerc.value + "#" +
	 					document.ModAfiForm.txt_RentaImp.value + "#" +
	 					rentaCot + "#" + //document.ModAfiForm.txt_RentaCot.value
	 					document.ModAfiForm.txt_ValorACot.value  + "#" + 
	 					document.ModAfiForm.idPersona.value + "#" +//50
	 					document.ModAfiForm.idSolicitud.value + "#" +
	 					document.ModAfiForm.idGrupoFam.value + "#" +
	 					document.ModAfiForm.idIngEconom.value + "#" +
	 					document.ModAfiForm.idSecuenciaTelefonoPart.value + "#" +
	 					document.ModAfiForm.idSecuenciaTelefonoCelu.value + "#" +
	 					document.ModAfiForm.idSecuenciaEmail.value + "#" + 
	 					document.ModAfiForm.idSecuenciaDireccionPart.value + "#" +
	 					document.ModAfiForm.idSecuenciaDireccionComerc.value + "#" + 
	 					document.ModAfiForm.idSecuenciaTelefonoComerc.value + "#"+//59	 					
						emailComercial + "#"+
						iDemailComercial + "#"+
						document.ModAfiForm.txt_MontoUltimaCotizacion.value + "#"+//65	
						document.ModAfiForm.txt_FechaUltimaCotizacion.value + "#"+//66	
						agrupacion +"#";//67														
				ModAfiliadoDWR.updateAfiliado(cadenaForm, function(data){
				
			var resp = null;
			resp = data;
			
			if(resp == 0){
				alert("Afiliado modificado con éxito.");
			}else{
				alert("Ocurrió un error al modificar el afiliado.");
			}
				
			});
		}	
	}
	
	/*funcion que bloquea los campos del formulario, dependiendo del tipo de estado en que se encuentre.*/
	function bloqueaFormulario(estado){
	
		if (estado == "4" || estado == "5" || estado == "6" || estado == "7")
		{
			//0. Informacion del Formulario
			document.ModAfiForm.txt_Folio.disabled = true;
			
			//1. Identificación
			document.ModAfiForm.txt_Rut.disabled = true;
			document.ModAfiForm.txt_NumVerif.disabled = true;
			document.ModAfiForm.txt_ApePat.disabled = true;
			document.ModAfiForm.txt_ApeMat.disabled = true;
			document.ModAfiForm.txt_Nombres.disabled = true;
			document.ModAfiForm.dbx_Nacionalidad.disabled = true;
			document.ModAfiForm.txt_FecNac.disabled = true;
			document.ModAfiForm.dbx_Sexo.disabled = true;
			document.ModAfiForm.dbx_EstCivil.disabled = true;
			document.ModAfiForm.txt_codAreaCelular.disabled = true;
			document.ModAfiForm.txt_TelCelular.disabled = true;
			document.ModAfiForm.txt_codAreaContacto.disabled = true;
			document.ModAfiForm.txt_TelContacto.disabled = true;
			document.ModAfiForm.txt_Email.disabled = true;
			document.ModAfiForm.txt_Calle.disabled = true;
			document.ModAfiForm.txt_Numero.disabled = true;
			document.ModAfiForm.txt_PoblVilla.disabled = true;
			document.ModAfiForm.txt_Departamento.disabled = true;
			document.ModAfiForm.dbx_Region.disabled = true;
			document.ModAfiForm.dbx_Provincia.disabled = true;
			document.ModAfiForm.dbx_Comuna.disabled = true;
			document.ModAfiForm.dbx_NivEstudios.disabled = true;
			document.ModAfiForm.dbx_TitAcademico.disabled = true;
			document.ModAfiForm.dbx_RegPrevisional.disabled = true;
			document.ModAfiForm.dbx_RegSalud.disabled = true;
	
			document.ModAfiForm.rbt_ConyNo.disabled = true;
			document.ModAfiForm.rbt_ConySi.disabled = true;
	
			document.ModAfiForm.txt_Hijos.disabled = true;
		
			//2. Informacion Actividad Comercial
			document.ModAfiForm.txt_Actividad.disabled = true;
		
			document.ModAfiForm.rbt_HonNo.disabled = true;
			document.ModAfiForm.rbt_HonSi.disabled = true;
		
			document.ModAfiForm.txt_CalleComerc.disabled = true;
			document.ModAfiForm.txt_NumeroComerc.disabled = true;
			document.ModAfiForm.txt_PoblVillaComerc.disabled = true;
			document.ModAfiForm.txt_DptoComerc.disabled = true;
			document.ModAfiForm.txt_codAreaComerc.disabled = true;
			document.ModAfiForm.txt_TelComerc.disabled = true;
		
			document.ModAfiForm.dbx_RegComerc.disabled = true;
			document.ModAfiForm.dbx_ProvinciaComerc.disabled = true;
			document.ModAfiForm.dbx_ComunaComerc.disabled = true;
			document.ModAfiForm.txt_EmailComercial.disabled=true;
			
			//3. Informacion de Renta
			document.ModAfiForm.txt_RentaImp.disabled = true;
			document.ModAfiForm.txt_RentaCot.disabled = true;
			document.ModAfiForm.txt_ValorACot.disabled = true;
			
			for(ira=0;ira<document.ModAfiForm.rbt_Agrupacion.length;ira++){	
				document.ModAfiForm.rbt_Agrupacion[ira].disabled = true;									
			}
			document.ModAfiForm.dbx_RazonSocialAgrup.disabled=true;					
			document.ModAfiForm.txt_MontoUltimaCotizacion.disabled=true;
		}
		
		var botonEst = null;
		var botonGuar = null;
		
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuar = document.getElementById("btn_GuardarAfi");
		
		if (estado == "4" || estado == "5" || estado == "6" || estado == "7")
		{
			botonEst.disabled = true;
		}
		
		if (estado == "1" || estado == "4" || estado == "5" || estado == "6" || estado == "7")
		{
			botonGuar.disabled = true;
		}
		
		bloqueaCampos();
	}
	
	/*funcion que desbloquea el formulario, dependiendo del estado en que se encuentre.*/
	function desBloqueaFormulario(estado){
	
		if (estado == "1" || estado == "2" || estado == "3")
		{
			//0. Informacion del Formulario
			document.ModAfiForm.txt_Folio.disabled = false;
			
			//1. Identificación
			document.ModAfiForm.txt_Rut.disabled = false;
			document.ModAfiForm.txt_NumVerif.disabled = false;
			document.ModAfiForm.txt_ApePat.disabled = false;
			document.ModAfiForm.txt_ApeMat.disabled = false;
			document.ModAfiForm.txt_Nombres.disabled = false;
			document.ModAfiForm.dbx_Nacionalidad.disabled = false;
			document.ModAfiForm.txt_FecNac.disabled = false;
			document.ModAfiForm.dbx_Sexo.disabled = false;
			document.ModAfiForm.dbx_EstCivil.disabled = false;
			document.ModAfiForm.txt_codAreaCelular.disabled = false;
			document.ModAfiForm.txt_TelCelular.disabled = false;
			document.ModAfiForm.txt_codAreaContacto.disabled = false;
			document.ModAfiForm.txt_TelContacto.disabled = false;
			document.ModAfiForm.txt_Email.disabled = false;
			document.ModAfiForm.txt_Calle.disabled = false;
			document.ModAfiForm.txt_Numero.disabled = false;
			document.ModAfiForm.txt_PoblVilla.disabled = false;
			document.ModAfiForm.txt_Departamento.disabled = false;
			document.ModAfiForm.dbx_Region.disabled = false;
			document.ModAfiForm.dbx_Provincia.disabled = false;
			document.ModAfiForm.dbx_Comuna.disabled = false;
			document.ModAfiForm.dbx_NivEstudios.disabled = false;
			document.ModAfiForm.dbx_TitAcademico.disabled = false;
			document.ModAfiForm.dbx_RegPrevisional.disabled = false;
			document.ModAfiForm.dbx_RegSalud.disabled = false;
	
			document.ModAfiForm.rbt_ConyNo.disabled = false;
			document.ModAfiForm.rbt_ConySi.disabled = false;
	
			document.ModAfiForm.txt_Hijos.disabled = false;
		
			//2. Informacion Actividad Comercial
			document.ModAfiForm.txt_Actividad.disabled = false;
		
			document.ModAfiForm.rbt_HonNo.disabled = false;
			document.ModAfiForm.rbt_HonSi.disabled = false;
		
			document.ModAfiForm.txt_CalleComerc.disabled = false;
			document.ModAfiForm.txt_NumeroComerc.disabled = false;
			document.ModAfiForm.txt_PoblVillaComerc.disabled = false;
			document.ModAfiForm.txt_DptoComerc.disabled = false;
			document.ModAfiForm.txt_codAreaComerc.disabled = false;
			document.ModAfiForm.txt_TelComerc.disabled = false;
		
			document.ModAfiForm.dbx_RegComerc.disabled = false;
			document.ModAfiForm.dbx_ProvinciaComerc.disabled = false;
			document.ModAfiForm.dbx_ComunaComerc.disabled = false;
			document.ModAfiForm.txt_EmailComercial.disabled=false;
			
			//3. Informacion de Renta
			document.ModAfiForm.txt_RentaImp.disabled = false;
			document.ModAfiForm.txt_RentaCot.disabled = false;
			document.ModAfiForm.txt_ValorACot.disabled = false;
			
			for(ira=0;ira<document.ModAfiForm.rbt_Agrupacion.length;ira++){	
				document.ModAfiForm.rbt_Agrupacion[ira].disabled = false;									
			}
			if(document.ModAfiForm.rbt_Agrupacion[0].checked){
				document.ModAfiForm.dbx_RazonSocialAgrup.disabled=false;		
			}
			document.ModAfiForm.txt_MontoUltimaCotizacion.disabled=false;
			
		}
		
		var botonEst = null;
		var botonGuar = null;
		
		botonEst = document.getElementById("btn_GuardarEst");
		botonGuar = document.getElementById("btn_GuardarAfi");
		
		if (estado == "1" || estado == "2" || estado == "3")
		{
			botonEst.disabled = false;
		}
		
		if (estado == "2" || estado == "3")
		{
			botonGuar.disabled = false;
		}
		
		bloqueaCampos();
	}
	
	/*funcion que bloquea los campos del formulario dependiendo del perfil del analista.*/
	function bloqueaCampos(){
	
		var botonEst = null;
		var botonAfi = null;
		
		document.ModAfiForm.txt_Folio.disabled = true;
		document.ModAfiForm.txt_Rut.disabled = true;
		document.ModAfiForm.txt_NumVerif.disabled = true;
			 
		//if ('<%=session.getAttribute("Perfil")%>' == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		if (validarPerfilesFullAnalista(arregloPerfiles)){
			botonEst = document.getElementById("btn_GuardarEst");
			botonEst.disabled = true;
			botonAfi = document.getElementById("btn_GuardarAfi");
			botonAfi.disabled = true;
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
	
	function desBloqueaCampos()
	{
		document.ModAfiForm.txt_Folio.disabled = false;
		document.ModAfiForm.txt_Rut.disabled = false;
		document.ModAfiForm.txt_NumVerif.disabled = false;
		document.ModAfiForm.txt_FecNac.disabled = false;
		document.ModAfiForm.txt_ValorACot.disabled = false;
	}

	/*funcion que realiza el calculo del valor a cotizar.*/
	function calculoValorACotizar(){
		
		var rentaImp = 0;
		var rentaCot = 0;
		var valorACot = 0;
		
		if(document.ModAfiForm.txt_RentaImp.value != 0 && document.ModAfiForm.txt_RentaImp.value != null && document.ModAfiForm.txt_RentaImp.value != "")
			rentaImp = parseFloat(document.ModAfiForm.txt_RentaImp.value);
			
		if(document.ModAfiForm.txt_RentaCot.value != 0 && document.ModAfiForm.txt_RentaCot.value != null && document.ModAfiForm.txt_RentaCot.value != "")	
			rentaCot = parseFloat(document.ModAfiForm.txt_RentaCot.value);
				
		if(rentaImp != 0){
			
			valorACot = Math.round(rentaImp * 0.01);
		
		}else{
			
			if( rentaCot != 0){
			
				valorACot = Math.round(rentaCot * 0.01);
			
			}else{
				
				valorACot = 0;
			}
		}
		
		desBloqueaCampoIndiv(document.ModAfiForm.txt_ValorACot);
		document.ModAfiForm.txt_ValorACot.value = valorACot;
		bloqueaCampoIndiv(document.ModAfiForm.txt_ValorACot);			
	}
	
	/* Funcion que calcula el valor a cotizar basado en la parametrizacion */
	function calculoValorACotizarParam(){
	
		var rentaImp = 0;
		var rentaCot = 0;
		var valorACot = 0;
	
		var tipoCalculo  = '<%=session.getAttribute("TxtSelTipoCalculoAporte")%>';
		var valorCalculo = '<%=session.getAttribute("TxtValorCalculoAporte")%>';
		
		if (tipoCalculo == 1){
		
			if(document.ModAfiForm.txt_RentaImp.value != 0 && document.ModAfiForm.txt_RentaImp.value != null && document.ModAfiForm.txt_RentaImp.value != "")
				rentaImp = parseFloat(document.ModAfiForm.txt_RentaImp.value);
				
			if(document.ModAfiForm.txt_RentaCot.value != 0 && document.ModAfiForm.txt_RentaCot.value != null && document.ModAfiForm.txt_RentaCot.value != "")	
				rentaCot = parseFloat(document.ModAfiForm.txt_RentaCot.value);
					
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
	
		desBloqueaCampoIndiv(document.ModAfiForm.txt_ValorACot);
		document.ModAfiForm.txt_ValorACot.value = valorACot;
		bloqueaCampoIndiv(document.ModAfiForm.txt_ValorACot);	
	}
	
	/*funcion que valida la mayoria de edad*/
	function ValidaMayoriaDeEdad(fecha){
	
		if(Comparar_Fecha_Anyo('<%=session.getAttribute("FechaSistema")%>',fecha, 18)){
			alert("Para poder ingresar una solicitud, el solicitante debe ser mayor de 18 años.");
			return false;
		}
		return true;
	}
		
	/*function desBloqueaCampos(){
	
		//document.ModAfiForm.txt_Folio.disabled = false;
		//document.ModAfiForm.txt_Rut.disabled = false;
		//document.ModAfiForm.txt_NumVerif.disabled = false;
	    //document.ModAfiForm.txt_NRut.disabled = false;
		//document.ModAfiForm.txt_NNumVerif.disabled = false;
	}*/	
	
	//REQ5348
	function habilitaAgrupacion()
	{
		var opAgrupa	=	document.ModAfiForm.rbt_Agrupacion;
		var razonSocial	=	document.ModAfiForm.dbx_RazonSocialAgrup;
		var rutAgrup	=	document.ModAfiForm.txt_RutAgrup;
		var dv			=	document.ModAfiForm.txt_NumVerifAgrup;
		
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
		var opAgrupa	=	document.ModAfiForm.rbt_Agrupacion;
		var id			=	document.ModAfiForm.dbx_RazonSocialAgrup.value;//valor seleccionado del combo agrupacion
		
		var rutAgrup	=	document.ModAfiForm.txt_RutAgrup;//Rut Agrupacion a obtener
		var dvAgrup 	= 	document.ModAfiForm.txt_NumVerifAgrup;//Digito Verificador a obtener.
		
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

	function cargaArreglos(){
		GeograficoDWR.obtenerListaAgrupacion("ListAgrupacionFull", function(data){
		
			var agrupaciones = null
			agrupaciones = data;
			
			for (i = 0; i < agrupaciones.length; i++){
				arregloAgrupaciones[i] = new ObjAgrupacion(agrupaciones[i].idSecuencia, agrupaciones[i].razonSocial, agrupaciones[i].idDocumento, agrupaciones[i].digVerDocumento);
			}
		});
	}
//FIN REQ5348		

</script>
</head>
<body onload="bloqueaCampos();">

<div id="caja_registro"><html:form action="/modAfi.do">
	<input type="hidden" name="dbx_tipoEstadoAfiliadoAux" value="0">
	<input type="hidden" name="opcion" value="0">
	
	<input type="hidden" name="idPersona" value="0">
	<input type="hidden" name="idSolicitud" value="0">
	<input type="hidden" name="idAfiliadoAgrupacion" value="0">
	<input type="hidden" name="idGrupoFam" value="0">
	<input type="hidden" name="idIngEconom" value="0">
	<input type="hidden" name="idSecuenciaTelefonoPart" value="0">
	<input type="hidden" name="idSecuenciaTelefonoCelu" value="0">
	<input type="hidden" name="idSecuenciaEmail" value="0">
	<input type="hidden" name="idSecuenciaEmailComerc" value="0">	
	<input type="hidden" name="idSecuenciaDireccionPart" value="0">
	<input type="hidden" name="idSecuenciaDireccionComerc" value="0">
	<input type="hidden" name="idSecuenciaTelefonoComerc" value="0">
	<input type="hidden" name="txt_RentaCot" value="0">	

	<table width="970" border="0">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="validaCancelar();">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td height="25" width="803"><strong><p1>
			CONSULTA/MODIFICACIóN DE AFILIADO </p1></strong></td>
			<td width="6">&nbsp;</td>
		</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<!-- <strong>N° Folio </strong> <input type="text"
				name="txt_NFolio" id="txt_NFolio" size="10" maxlength="12" onkeypress="Upper(this,'N')"/> -->
			 <td width="803"><strong>N° RUT *</strong> <input type="text" name="txt_NRut" id="txt_NRut" size="10" maxlength="9" onkeypress="Upper(this,'N')"/> <strong>
			- </strong> <input type="text" name="txt_NNumVerif" id="txt_NNumVerif"
				size="2" maxlength="1" onkeypress="Upper(this,'D')" onchange="ValidadorRUT(document.ModAfiForm.txt_NRut.value,this.value)"/> <input type="button" name="btn_Buscar" id="btn_Buscar"
				class="btn_limp" value="Buscar " onclick="obtenerAfiliadoDWR();" />
			&nbsp;&nbsp; <strong>Estado Afiliado</strong><input type="text" name="txt_EstSolicitud" id="txt_EstSolicitud" disabled="true"/><html:select
				property="dbx_EstSolicitud" styleClass="combobox" value="0">
				<html:option value="0">Seleccione</html:option>
				<!--<html:options collection="ListEstadoAfiliadoBox"
					property="codigo" labelProperty="glosa" />-->
			</html:select><input type="button" name="btn_GuardarEst" id="btn_GuardarEst"
				class="btn_limp" value="Guardar"
				onclick="updateEstadoAfiliadoDWR();" /></td>
		</tr>
		<tr>
			<td height="404" width="803">
			<p><p2>1. Identificación del Trabajador Independiente</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>N° Folio *</strong></td>
					<td width="16%"><input type="text" name="txt_Folio"
						id="txt_Folio" maxlength="12" onkeypress="Upper(this,'N')"/></td>
					<td width="16%"><strong>N° RUT *</strong></td>
					<td width="16%"><input type="text" name="txt_Rut" id="txt_Rut" maxlength="9" onkeypress="Upper(this,'N')"/></td>
					<td width="16%"><strong> - </strong> <input type="text"
						name="txt_NumVerif" id="txt_NumVerif" size="3" maxlength="1" onkeypress="Upper(this,'A')"/></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno *</strong></td>
					<td><input name="txt_ApePat" type="text" id="txt_ApePat" maxlength="50" onkeypress="Upper(this,'L')"/></td>
					<td><strong>Apellido Materno *</strong></td>
					<td><input type="text" name="txt_ApeMat" id="txt_ApeMat" maxlength="50" onkeypress="Upper(this,'L')"/></td>
					<td><strong>Nombres *</strong></td>
					<td colspan="3"><input type="text" name="txt_Nombres" id="txt_Nombres" maxlength="100" onkeypress="Upper(this,'L')"/></td>
				</tr>
				<tr>
					<td><strong>Nacionalidad *</strong></td>
					<td>
					<!--  
						<html:select property="dbx_Nacionalidad" styleClass="combobox" value="0">
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
					<td><input type="text" name="txt_FecNac" id="txt_FecNac" class="datepick" disabled="true"/><!--
						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecNac)">
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
					<td>
						<input size="1" type="text" name="txt_codAreaCelular" id="txt_codAreaCelular" maxlength="2" onkeypress="Upper(this,'N')"/>
						<input size="10" type="text" name="txt_TelCelular" id="txt_TelCelular" maxlength="8" onkeypress="Upper(this,'N')"/>
					</td>
					<td><strong>Teléfono de Contacto</strong></td>
					<td>
						<input size="1" type="text" name="txt_codAreaContacto" id="txt_codAreaContacto" maxlength="3" onkeypress="Upper(this,'N')"/>
						<input size="10" type="text" name="txt_TelContacto" id="txt_TelContacto" maxlength="8" onkeypress="Upper(this,'N')"/>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>

				<tr>
					<td><strong>E-Mail *</strong></td>
					<td colspan="3"><input type="text" name="txt_Email"
						id="txt_Email" size="50" maxlength="100" onkeypress="Upper(this,'M')" onblur="validarEmail(this.value)"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_Calle"
						id="txt_Calle" size="50" maxlength="50" onkeypress="Upper(this,'A')"/></td>
					<td height="34"><strong>Número *</strong></td>
					<td colspan="3"><input type="text" name="txt_Numero"
						id="txt_Numero" size="5" maxlength="6" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3"><input type="text" name="txt_PoblVilla"
						id="txt_PoblVilla" size="50" maxlength="50" onkeypress="Upper(this,'A')"/></td>
					<td height="34"><strong>Departamento</strong></td>
					<td colspan="3"><input type="text" name="txt_Departamento"
						id="txt_Departamento" size="5" maxlength="5" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3"><html:select property="dbx_Region" styleClass="dbx_geo"
						value="0"
						onchange="cargarProvincias(ModAfiForm.dbx_Provincia,ModAfiForm.dbx_Comuna,this.value,0);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListRegiones" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>	
					<td><strong>Provincia *</strong></td>
					<td colspan="3"><select name="dbx_Provincia" id="dbx_Provincia" style="width:330px"
						onchange="cargarComunas(ModAfiForm.dbx_Comuna,this.value,0);">
						<option value="0">Seleccione</option>
					</select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>	
					<td><strong>Comuna *</strong></td>
					<td colspan="3"><select name="dbx_Comuna" id="dbx_Comuna" style="width:330px">
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
					<td><input type="radio" name="rbt_Agrupacion" id="rbt_Agrupacion"
						value="1" onclick="javascript:habilitaAgrupacion()"/>Si<br />
					<input type="radio" name="rbt_Agrupacion" id="rbt_Agrupacion"
						value="0" onclick="javascript:habilitaAgrupacion()"/>No<br />
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td width="16%">
						<strong> Razón Social Agrupación *</strong></td>
					<td>
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
						value="1" />Si<br />
					<input type="radio" name="rbt_Conyugue" id="rbt_ConyNo" value="0" />No<br />
					</td>
					<td><strong>Hijos </strong></td>
					<td><input type="text" name="txt_Hijos" id="txt_Hijos"
						size="5" maxlength="2" onkeypress="Upper(this,'N')"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="803">
			<p><p2>2. Información Actividad Comercial</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Nombre Actividad *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_Actividad"
						id="txt_Actividad" size="50" maxlength="100" onkeypress="Upper(this,'L')"/></td>
					<td ></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Honorarios sector Público *</strong></td>
					<td width="16%" colspan="3"><input type="radio"
						name="rbt_Honorarios" id="rbt_HonSi" value="1" />Si<br />
					<input type="radio" name="rbt_Honorarios" id="rbt_HonNo" value="2" />No<br />
					</td>
					<td ></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_CalleComerc"
						id="txt_CalleComerc" size="50" maxlength="50" onkeypress="Upper(this,'A')"/></td>
					<td height="34"><strong>Número *</strong></td>
					<td colspan="3"><input type="text" name="txt_NumeroComerc"
						id="txt_NumeroComerc" size="5" maxlength="6" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3"><input type="text" name="txt_PoblVillaComerc"
						id="txt_poblVillaComerc" size="50" maxlength="50" onkeypress="Upper(this,'A')"/></td>
					<td height="34"><strong>Departamento</strong></td>
					<td colspan="3"><input type="text" name="txt_DptoComerc"
						id="txt_DptoComerc" size="5" maxlength="5" onkeypress="Upper(this,'A')"/></td>
				</tr>
				<tr>
					<td><strong>Teléfono Comercial</strong></td>
					<td colspan="3">
						<input type="text" name="txt_codAreaComerc" id="txt_codAreaComerc" maxlength="3" size="1" onkeypress="Upper(this,'N')"/>
						<input type="text" name="txt_TelComerc" id="txt_TelComerc" maxlength="8" size="10" onkeypress="Upper(this,'N')"/>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>	
					<td><strong>E-Mail Comercial</strong></td>
					<td colspan="3"><input type="text" name="txt_EmailComercial"
						id="txt_EmailComercial" size="50" maxlength="100"
						onkeypress="Upper(this,'M')"
						onblur="validarEmail(this.value)"></td>
					<td></td>
					<td colspan="3"></td>
				</tr>				
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3"><html:select property="dbx_RegComerc"
						styleClass="dbx_geo" value="0"
						onchange="cargarProvincias(ModAfiForm.dbx_ProvinciaComerc,ModAfiForm.dbx_ComunaComerc,this.value,0);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListRegiones" property="codigo"
							labelProperty="glosa" />
					</html:select></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>	
					<td><strong>Provincia *</strong></td>
					<td colspan="3"><select name="dbx_ProvinciaComerc" style="width:330px"
						id="dbx_ProvinciaComerc"
						onchange="cargarComunas(ModAfiForm.dbx_ComunaComerc,this.value,0);">
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
			<td width="803">
			<p><p2>3. Información de Renta</p2></p>
			<table  width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Renta Imponible (SII) *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_RentaImp" id="txt_RentaImp" maxlength="9" onkeypress="Upper(this,'N')" onchange="calculoValorACotizarParam();"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Monto Última Cotizaci&oacute;n AFP *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_MontoUltimaCotizacion" id="txt_MontoUltimaCotizacion" maxlength="9" onkeypress="Upper(this,'N')" onchange="calculoValorACotizarParam();"/></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Valor a Cotizar *</strong></td>
					<td width="16%" colspan="3"><input type="text" name="txt_ValorACot" id="txt_ValorACot" disabled="true" maxlength="7" onkeypress="Upper(this,'N')" /></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"><strong>Fecha Última Cotización *</strong></td>
					<td width="16%" colspan="3">
						<input type="text" name="txt_FechaUltimaCotizacion"
						id="txt_FechaUltimaCotizacion" class="datepick" disabled="true" size="10"
						value='<%=request.getAttribute("txt_FechaUltimaCotizacion")==null? "":request.getAttribute("txt_FechaUltimaCotizacion")%>'><!--
						<img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onclick="ShowCalendarFor(txt_FechaUltimaCotizacion)">
					--></td>
					<td></td>
					<td colspan="3"></td>
				</tr>				
			</table>
			</td>
		</tr>
		<tr>
			<td height="37" align="right" width="805">
				<input type="submit" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelar();"/>
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_GuardarAfi" id="btn_GuardarAfi" class="btn_limp" value="Guardar" onclick="updateDatosAfiliadoDWR();"/>
				&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>

</html:form></div>
</body>
</html:html>
