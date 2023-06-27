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
<script type="text/javascript" src="/IndependientesWEB/dwr/interface/NewSolDesafiliacionDWR.js"></script>

<script type="text/javascript">
	
	var vectorDocumentos  = new Array();
	var arregloEstados = new Array();
	var arregloTipos = new Array();
	var arregloAgrupaciones = new Array();
	var arregloPerfiles = null;
	var celuCodArea = "";
	var celuNroTel = "";
	var chkEmail = "";
	var chkCalle = "";
	var chkNumCalle = "";
	var chkPobla = "";
	var chkDepto = "";
	var chkRegion = 0;
	var chkProvincia = 0;
	var chkComuna = 0;
	var fchVigenciaDesde = "";
	//REQ 7002
	var mesesVigenciaDes = 0;
	var mesesVigenciaAct = 0;
	//Variable Globlal
	
	
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

		document.NewSolDesafiliacionForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.NewSolDesafiliacionForm.submit();
	}
	
	/*Funcion que valida los campos obligatorios del formulario.*/
	function validaForm()
	{
		// Poner aqui las validaciones del formulario

		if(Trim(document.NewSolDesafiliacionForm.txt_Folio.value) == "" ){
			alert("Falta ingresar campo Folio.");
			return false; 
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_ApePat.value) == "" ){
			alert("Falta ingresar campo Apellido Paterno.");
			return false; 
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_ApeMat.value) == "" ){
			alert("Falta ingresar campo Apellido Materno.");
			return false; 
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_Nombres.value) == "" ){
			alert("Falta ingresar campo Nombres.");
			return false; 
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_codAreaCelular.value) == "" ){
			alert("Falta ingresar el campo Codigo Area Celular");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_TelCelular.value) == "" ){
			alert("Falta ingresar el campo Nro Celular");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_Email.value) == "" ){
			alert("Falta ingresar el campo E-Mail");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_Calle.value) == "" ){
			alert("Falta ingresar el campo Calle");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_Numero.value) == "" ){
			alert("Falta ingresar el campo Número");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.dbx_Region.value) == 0 ){
			alert("Falta seleccionar el campo Region");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.dbx_Provincia.value) == 0 ){
			alert("Falta seleccionar el campo Provincia");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.dbx_Comuna.value) == 0 ){
			alert("Falta seleccionar el campo Comuna");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.dbx_CajaCompensacion.value) == 0 ){
			alert("Falta seleccionar el campo Caja Compensación");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_FecUltApo.value) == "" ){
			alert("Falta ingresar el campo Fecha Último Aporte");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.dbx_Motivo.value) == 0 ){
			alert("Falta seleccionar el campo Tipo de Motivo");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.dbx_DescMotivo.value) == 0 ){
			alert("Falta seleccionar el campo Descripción");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.dbx_Oficina.value) == 0 ){
			alert("Falta seleccionar el campo Oficina");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_FecSolDesaf.value) == "" ){
			alert("Falta ingresar el campo Fecha Solicitud");
			return false;
		}
		
		if(Trim(document.NewSolDesafiliacionForm.txt_Hora.value) == "" ){
			alert("Falta ingresar el campo Hora Solicitud");
			return false;
		}

		desbloqueaCamposFormulario();
		asignaValor(1);
		document.NewSolDesafiliacionForm.submit();
	}
	/* FIN VALIDA FORM */
	
	
	/*Funcion que valida el boton cancelar cuando se ha realizado una accion previa.*/
	function validaCancelar()
	{
	
		if (
		
			document.NewSolDesafiliacionForm.txt_Rut.value != "" ||
			document.NewSolDesafiliacionForm.txt_NumVerif.value != "" ||
			document.NewSolDesafiliacionForm.txt_Folio.value != "" ||
			document.NewSolDesafiliacionForm.txt_Sucursal.value != "" ||
			document.NewSolDesafiliacionForm.txt_ApePat.value != "" ||
			document.NewSolDesafiliacionForm.txt_ApeMat.value != "" ||
			document.NewSolDesafiliacionForm.txt_Nombres.value != "" ||
			document.NewSolDesafiliacionForm.txt_codAreaCelular.value != "" ||
			document.NewSolDesafiliacionForm.txt_TelCelular.value != "" ||
			document.NewSolDesafiliacionForm.txt_codAreaContacto.value != "" ||
			document.NewSolDesafiliacionForm.txt_TelContacto.value != "" ||
			document.NewSolDesafiliacionForm.txt_Email.value != "" ||
			document.NewSolDesafiliacionForm.txt_Calle.value != "" ||
			document.NewSolDesafiliacionForm.txt_Numero.value != "" ||
			document.NewSolDesafiliacionForm.txt_PoblVilla.value != "" ||
			document.NewSolDesafiliacionForm.txt_Departamento.value != "" ||
			document.NewSolDesafiliacionForm.dbx_Region.value != "0" ||
			document.NewSolDesafiliacionForm.dbx_Provincia.value != "0" ||
			document.NewSolDesafiliacionForm.dbx_Comuna.value != "0" ||
			
			/*Aqui se limpian los campos del punto 2*/
			document.NewSolDesafiliacionForm.dbx_CajaCompensacion.value != "0" ||
			//document.NewSolDesafiliacionForm.txt_FecVigAfil.value = "";
			document.NewSolDesafiliacionForm.txt_FecUltApo.value != "" ||
			document.NewSolDesafiliacionForm.dbx_Motivo.value != "0" ||
			document.NewSolDesafiliacionForm.dbx_DescMotivo.value != "0" ||
			document.NewSolDesafiliacionForm.txt_Observaciones.value != "" ||
			
			/*Aqui se limpian los campos del punto 3*/
			//document.NewSolDesafiliacionForm.txt_RutCaptador.value != "" ||
			//document.NewSolDesafiliacionForm.txt_ApePatProm.value != "" ||
			//document.NewSolDesafiliacionForm.txt_ApeMatProm.value != "" ||
			//document.NewSolDesafiliacionForm.txt_NombreProm.value != "" ||
			document.NewSolDesafiliacionForm.dbx_Oficina.value != "0" ||
			//document.NewSolDesafiliacionForm.txt_FecIngr.value != "" ||
			document.NewSolDesafiliacionForm.txt_FecSolDesaf.value != "" ||
			document.NewSolDesafiliacionForm.txt_Hora.value != "" ||
			document.NewSolDesafiliacionForm.txt_Comentarios.value != ""
			
		){
			var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			
			if(respuesta == true){
				asignaValor(2);
				document.NewSolDesafiliacionForm.submit();
			}
			
			}else{
				asignaValor(2);
				document.NewSolDesafiliacionForm.submit();
			}
		
	}
	
	/*Funcion que permite recuperar la fecha de vigencia.*/
	function recuperarFecVigencia()
	{
		var folio = Trim(document.NewSolDesafiliacionForm.txt_NFolio.value);
		
		ModSolicitudDWR.recuperarFecVigencia(folio, function(data){
			
			//document.NewSolDesafiliacionForm.txt_FecVigAfil.value = data;
			return data;
		});
	}
	
	/*funcion que permite valida la fecha de solicitud.*/
	function validaFecSolicitud()
	{
		var fechaSolicitud = "01/01/2012";
		var fechaSolIngresada = document.NewSolDesafiliacionForm.txt_FecFirma.value;
		if(Comparar_Fecha(fechaSolIngresada,fechaSolicitud))
		{
			return true;
		}
		
		return false;	
	}
	
	/*funcion que recupera la fecha de firma.*/
	function recuperarFecFirma()
	{
		var folio = Trim(document.NewSolDesafiliacionForm.txt_NFolio.value);
		
		ModSolicitudDWR.recuperarFecFirma(folio, function(data){
					
			document.NewSolDesafiliacionForm.txt_FecFirma.value = data;
		});
	}
	
	/*funcion que valida la fecha de vigencia*/
	function validaFechaVigencia()
	{
		var respuesta;
	
		var fechaVigencia = document.NewSolDesafiliacionForm.txt_FecVigAfil.value;
		var fechaIngreso = document.NewSolDesafiliacionForm.txt_FecIngr.value;
		
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
		document.NewSolDesafiliacionForm.existeCambio.value = x;
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
	
	/*funcion que obtiente los datos del trabajdor independiente*/
	function buscarTrabajorIndependiente()
	{
		var rut = Trim(document.NewSolDesafiliacionForm.txt_Rut.value);
		var dv = Trim(document.NewSolDesafiliacionForm.txt_NumVerif.value);
		
		var ok = false;
		var ok2 = true;
		var okEst = 0;
		var montoDeuda = 0;
		var mensaje = "";
					
		if (rut.length == 0 || dv.length == 0){
			alert("Para realizar una búsqueda debe ingresar rut y Dígito Verificador.");
		
		}else
		{
			if(ValidadorRUT(rut,dv))
			{
                DWREngine.setAsync(false);
				NewSolDesafiliacionDWR.obtenerDatosPorRut(rut, function(data){
				
					var solicitud = null;
										
					var persona = null;
					var solicitud = null;
					
					var telPart = null;
					var telCel = null;
					var telCom = null;
					var email = null;
					var emailComercial = null;						
					var direcPart = null;
					var direcComer = null;
					var afiliado = null;
					var ingreso = null;
					var ejecutivo = null;
					var solicitudvo = null;
					var listDocumentos = null;				
					var analistvO = null;
		   		
					solicitud = data;

					if(solicitud.resultado != "" || solicitud.codResultado != 0){
					//if(true == false){
						ok2 = false;
						alert(solicitud.resultado);	
						//alert("No se encontró la solicitud asociada al Folio/RUT.");
					
					}else{
					
						persona = solicitud.personaVO;
						solicitudVO = solicitud.solicitudVO;
						
						telPart = solicitud.telefonoPartVO;
						telCel = solicitud.telefonoCeluVO;
						telCom = solicitud.telefonoComerVO;
						
						email = solicitud.emailVO;
						emailComercial = solicitud.emailComerVO;
						direcPart = solicitud.direccionPartVO;

						afiliado = solicitud.afiliadoVO;

						ejecutivo = solicitud.analistaCaptadorVO;
						solicitudvo = solicitud.solicitudVO;
						listDocumentos = solicitud.listaDocumentoVO;
						//REQ5348	
						//FIN REQ5348													
						analistvo = solicitud.analistaVO;
						//Analista que se loguea en el sistema.
						//alert("Fecha :"+	solicitudVO.fechaVigencia);	
						
						//---------------------------------------------------------------
						
						document.NewSolDesafiliacionForm.opcion;
						document.NewSolDesafiliacionForm.resultado;
						
						//0. Informacion del Formulario
						//document.NewSolDesafiliacionForm.txt_Folio.value = solicitudVO.folio;
						//document.NewSolDesafiliacionForm.txt_Oficina.value = solicitudVO.oficina;
						document.NewSolDesafiliacionForm.txt_Sucursal.value = solicitudVO.desOficina;
						//REQ7002
						fchVigenciaDesde = solicitudVO.fechaVigencia;
						
						//1. Identificación personaVO
						document.NewSolDesafiliacionForm.txt_Rut.value = persona.idDocumento;
						document.NewSolDesafiliacionForm.txt_NumVerif.value = persona.digVerificador;
						document.NewSolDesafiliacionForm.txt_ApePat.value = persona.apellidoPaterno;
						document.NewSolDesafiliacionForm.txt_ApeMat.value = persona.apellidoMaterno;
						document.NewSolDesafiliacionForm.txt_Nombres.value = persona.nombres;

						document.NewSolDesafiliacionForm.txt_codAreaCelular.value = telCel.codArea;
						document.NewSolDesafiliacionForm.txt_TelCelular.value = telCel.nroTelefono;
						celuCodArea = telCel.codArea;
						celuNroTel = telCel.nroTelefono;
						
						document.NewSolDesafiliacionForm.txt_codAreaContacto.value = telPart.codArea;
						document.NewSolDesafiliacionForm.txt_TelContacto.value = telPart.nroTelefono;
						document.NewSolDesafiliacionForm.txt_Email.value = email.direccMail;
						chkEmail = email.direccMail;
						document.NewSolDesafiliacionForm.txt_Calle.value = direcPart.glosCalle;
						document.NewSolDesafiliacionForm.txt_Numero.value = direcPart.numDireccion;
						chkCalle = direcPart.glosCalle;
						chkNumCalle = direcPart.numDireccion;
						document.NewSolDesafiliacionForm.txt_PoblVilla.value = direcPart.poblacionVilla;
						document.NewSolDesafiliacionForm.txt_Departamento.value = direcPart.dpto;
						chkPobla = direcPart.poblacionVilla;
						chkDepto = direcPart.dpto;

						//document.NewSolDesafiliacionForm.txt_Region.value = direcPart.desRegion;
						//document.NewSolDesafiliacionForm.txt_Provincia.value = direcPart.desCiudad;
						//document.NewSolDesafiliacionForm.txt_Comuna.value = direcPart.desComuna;
						
						document.NewSolDesafiliacionForm.dbx_Region.value = direcPart.region;
						cargarProvincias(document.NewSolDesafiliacionForm.dbx_Provincia,document.NewSolDesafiliacionForm.dbx_Comuna,direcPart.region,direcPart.ciudad);
						cargarComunas(document.NewSolDesafiliacionForm.dbx_Comuna,direcPart.ciudad,direcPart.comuna);
						document.NewSolDesafiliacionForm.dbx_Region.value = direcPart.region;
						document.NewSolDesafiliacionForm.dbx_Provincia.value = direcPart.ciudad;
						document.NewSolDesafiliacionForm.dbx_Comuna.value = direcPart.comuna;
						
						chkRegion = direcPart.region;
						chkProvincia = direcPart.ciudad;
						chkComuna = direcPart.comuna;
						
						botonAgregar = document.getElementById("btn_Ingresar");
                        botonAgregar.disabled = false ;
						
						//INICIO REQ7002
						ok = validacionMesesVigencia();
						okEst = obtenerDatosAfiliado();	
						
						}
						});
						DWREngine.setAsync(true);

					if(ok2){
						if(ok ||(okEst != 1)){
							bloqueaFormularioVigencia();
						
							/*if(ok){							
								//alert("No cumple con lo(s) " +mesesVigenciaDes+ " mes(es) de afiliación mínimos para solicitar la desafiliación.");
								alert("No cumple con los " +mesesVigenciaDes+ " meses de afiliación mínimos para solicitar la desafiliación. El Afiliado tiene "+mesesVigenciaAct+" mes(es) de Afiliación");							
							}else if (okEst == 0){
								montoDeuda = obtenerDeudaAporte();
								alert("montoDeuda: "+ montoDeuda);
								alert("El trabajador tiene todos sus aportes pagados, exceptuando el correspondiente al periodo actual, por lo que no puede solicitar la desafiliación.");
							}else if (okEst == 2){
								montoDeuda = obtenerDeudaAporte();
								alert("montoDeuda: "+ montoDeuda);
							    alert("El trabajador tiene morosos uno o mas aportes, por lo que no puede solicitar la desafiliación.");
							}else{
								montoDeuda = obtenerDeudaAporte();
								alert("montoDeuda: "+ montoDeuda);
							    alert("El trabajador no tiene todos sus aportes pagados, por lo que no puede solicitar la desafiliación.");
							}*/
							if(ok && (okEst != 1)){
								mensaje = "No cumple con los " +mesesVigenciaDes+ " meses de afiliación mínimos para solicitar la desafiliación. El Afiliado tiene "+mesesVigenciaAct+" mes(es) de Afiliación";
								if(okEst == 2){
									montoDeuda = obtenerDeudaAporte();
									mensaje = mensaje + "/ " + "El trabajador tiene morosos uno o mas aportes, por lo que no puede solicitar la desafiliación. Deuda del Afiliado es de $"+montoDeuda+"."
								}else{
									montoDeuda = obtenerDeudaAporte();
									mensaje = mensaje + "/ " + "El trabajador no tiene todos sus aportes pagados, por lo que no puede solicitar la desafiliación. Deuda del Afiliado es de $"+montoDeuda+"."
								}	
								alert(mensaje);
							}else if(ok){							
								alert("No cumple con los " +mesesVigenciaDes+ " meses de afiliación mínimos para solicitar la desafiliación. El Afiliado tiene "+mesesVigenciaAct+" mes(es) de Afiliación");							
							}else if (okEst == 2){
								montoDeuda = obtenerDeudaAporte();
							    alert("El trabajador tiene morosos uno o mas aportes, por lo que no puede solicitar la desafiliación. Deuda del Afiliado es de $"+montoDeuda+".");
							}else{
								montoDeuda = obtenerDeudaAporte();
							    alert("El trabajador no tiene todos sus aportes pagados, por lo que no puede solicitar la desafiliación. Deuda del Afiliado es de $"+montoDeuda+".");
							}
							
						}else{										
							bloqueaFormulario();					
						}
					}else{										
						bloqueaFormulario();					
					}
					//FIN REQ7002						
					
							
				//DWREngine.setAsync(true);		
			}
		}

	}		
	
	function bloqueaFormularioVigencia(){

			document.NewSolDesafiliacionForm.txt_Sucursal.disabled = true;
            document.NewSolDesafiliacionForm.txt_ApePat.disabled = true;
            document.NewSolDesafiliacionForm.txt_ApeMat.disabled = true;
            document.NewSolDesafiliacionForm.txt_Nombres.disabled = true;
            document.NewSolDesafiliacionForm.txt_codAreaContacto.disabled = true;
            document.NewSolDesafiliacionForm.txt_TelContacto.disabled = true;
            document.NewSolDesafiliacionForm.txt_Folio.disabled = true;
            document.NewSolDesafiliacionForm.txt_Hora.disabled = true;
            document.NewSolDesafiliacionForm.txt_Comentarios.disabled = true;
			document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled = true;
			document.NewSolDesafiliacionForm.txt_TelCelular.disabled = true;
			document.NewSolDesafiliacionForm.txt_Email.disabled = true;
			document.NewSolDesafiliacionForm.txt_Calle.disabled = true;
			document.NewSolDesafiliacionForm.txt_Numero.disabled = true;
			document.NewSolDesafiliacionForm.txt_PoblVilla.disabled = true;
			document.NewSolDesafiliacionForm.txt_Departamento.disabled = true;
			document.NewSolDesafiliacionForm.dbx_Region.disabled = true;
			document.NewSolDesafiliacionForm.dbx_Provincia.disabled = true;
			document.NewSolDesafiliacionForm.dbx_Comuna.disabled = true;
			document.NewSolDesafiliacionForm.txt_FecVigAfil.disabled = true;
			
			document.NewSolDesafiliacionForm.dbx_CajaCompensacion.disabled = true;
			document.NewSolDesafiliacionForm.dbx_Motivo.disabled = true;
			document.NewSolDesafiliacionForm.dbx_DescMotivo.disabled = true;
			document.NewSolDesafiliacionForm.txt_Observaciones.disabled = true;
			document.NewSolDesafiliacionForm.dbx_Oficina.disabled = true;
			document.NewSolDesafiliacionForm.img_FecSolDesaf.disabled = true;
			document.NewSolDesafiliacionForm.img_FecUltApo.disabled = true;
			document.NewSolDesafiliacionForm.chk_A_Celu.disabled = true;
			document.NewSolDesafiliacionForm.chk_N_Celu.disabled = true;
			document.NewSolDesafiliacionForm.chk_A_Email.disabled = true;
			document.NewSolDesafiliacionForm.chk_N_Email.disabled = true;
			document.NewSolDesafiliacionForm.chk_A_Calle.disabled = true;
			document.NewSolDesafiliacionForm.chk_N_Calle.disabled = true;
			
			botonAgregar = document.getElementById("btn_Ingresar");
            botonAgregar.disabled = true;
	
	
	}
	
	
	
		/*funcion que bloquea los campos del formulario dependiendo del estado en que se encuentre la solicitud.*/
	function bloqueaFormulario(){
	
			//document.ModSolForm.txt_EstSolicitud.disabled = true;
			document.NewSolDesafiliacionForm.txt_Folio.disabled = false;
			document.NewSolDesafiliacionForm.txt_Hora.disabled = false;
			document.NewSolDesafiliacionForm.txt_Comentarios.disabled = false;
			//document.NewSolDesafiliacionForm.txt_Rut.disabled = true;
			//document.NewSolDesafiliacionForm.txt_NumVerif.disabled = true;
			document.NewSolDesafiliacionForm.txt_Sucursal.disabled = true;
			//document.NewSolDesafiliacionForm.txt_ApePat.disabled = true;
			//document.NewSolDesafiliacionForm.txt_ApeMat.disabled = true;
			//document.NewSolDesafiliacionForm.txt_Nombres.disabled = true;

			document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled = true;
			document.NewSolDesafiliacionForm.txt_TelCelular.disabled = true;
			//document.NewSolDesafiliacionForm.txt_codAreaContacto.disabled = true;
			//document.NewSolDesafiliacionForm.txt_TelContacto.disabled = true;
			document.NewSolDesafiliacionForm.txt_Email.disabled = true;

			document.NewSolDesafiliacionForm.txt_Calle.disabled = true;
			document.NewSolDesafiliacionForm.txt_Numero.disabled = true;
			document.NewSolDesafiliacionForm.txt_PoblVilla.disabled = true;
			document.NewSolDesafiliacionForm.txt_Departamento.disabled = true;
			
			document.NewSolDesafiliacionForm.dbx_Region.disabled = true;
			document.NewSolDesafiliacionForm.dbx_Provincia.disabled = true;
			document.NewSolDesafiliacionForm.dbx_Comuna.disabled = true;
			
			document.NewSolDesafiliacionForm.txt_FecVigAfil.disabled = true;
			
			
			document.NewSolDesafiliacionForm.dbx_CajaCompensacion.disabled = false;
			document.NewSolDesafiliacionForm.dbx_Motivo.disabled = false;
			document.NewSolDesafiliacionForm.dbx_DescMotivo.disabled = false;
			document.NewSolDesafiliacionForm.txt_Observaciones.disabled = false;
			document.NewSolDesafiliacionForm.dbx_Oficina.disabled = false;
			document.NewSolDesafiliacionForm.img_FecSolDesaf.disabled = false;
			document.NewSolDesafiliacionForm.img_FecUltApo.disabled = false;
			document.NewSolDesafiliacionForm.chk_A_Celu.disabled = false;
			document.NewSolDesafiliacionForm.chk_N_Celu.disabled = false;
			document.NewSolDesafiliacionForm.chk_A_Email.disabled = false;
			document.NewSolDesafiliacionForm.chk_N_Email.disabled = false;
			document.NewSolDesafiliacionForm.chk_A_Calle.disabled = false;
			document.NewSolDesafiliacionForm.chk_N_Calle.disabled = false;
			
			
			
			
			//5. Afiliacion y Desafiliacion
			//document.NewSolDesafiliacionForm.dbx_CajaCompensacion.disabled = true;
			//document.NewSolDesafiliacionForm.txt_FecVigAfil.disabled = true;

			//document.NewSolDesafiliacionForm.txt_FecFirma.disabled = true
			//document.NewSolDesafiliacionForm.txt_RutEjec.disabled = true;
			//document.NewSolDesafiliacionForm.txt_Hora.disabled = true;						

	}
	
			/*funcion que bloquea los campos del formulario dependiendo del estado en que se encuentre la solicitud.*/
	function desbloqueaCamposFormulario(){
	
			document.NewSolDesafiliacionForm.txt_Folio.disabled = false;
			document.NewSolDesafiliacionForm.txt_Rut.disabled = false;
			document.NewSolDesafiliacionForm.txt_NumVerif.disabled = false;
			document.NewSolDesafiliacionForm.txt_ApePat.disabled = false;
			document.NewSolDesafiliacionForm.txt_ApeMat.disabled = false;
			document.NewSolDesafiliacionForm.txt_Nombres.disabled = false;

			document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled = false;
			document.NewSolDesafiliacionForm.txt_TelCelular.disabled = false;
			document.NewSolDesafiliacionForm.txt_codAreaContacto.disabled = false;
			document.NewSolDesafiliacionForm.txt_TelContacto.disabled = false;
			document.NewSolDesafiliacionForm.txt_Email.disabled = false;

			document.NewSolDesafiliacionForm.txt_Calle.disabled = false;
			document.NewSolDesafiliacionForm.txt_Numero.disabled = false;
			document.NewSolDesafiliacionForm.txt_PoblVilla.disabled = false;
			document.NewSolDesafiliacionForm.txt_Departamento.disabled = false;
			
			document.NewSolDesafiliacionForm.dbx_Region.disabled = false;
			document.NewSolDesafiliacionForm.dbx_Provincia.disabled = false;
			document.NewSolDesafiliacionForm.dbx_Comuna.disabled = false;
			
			// Afiliacion y Desafiliacion
			document.NewSolDesafiliacionForm.dbx_CajaCompensacion.disabled = false;
			document.NewSolDesafiliacionForm.txt_FecVigAfil.disabled = false;

			document.NewSolDesafiliacionForm.txt_FecSolDesaf.disabled = false;
			document.NewSolDesafiliacionForm.txt_FecUltApo.disabled = false;			

			document.NewSolDesafiliacionForm.txt_Hora.disabled = false;						

	}
	
	function desbloqueaCampoChk(check){
			
			if(check.name == "chk_A_Celu" || check.name == "chk_N_Celu"){
			
				if(check.name == "chk_A_Celu"){
					document.NewSolDesafiliacionForm.chk_flagCelu.value = "1";
					
					if(document.NewSolDesafiliacionForm.chk_N_Celu.disabled == true)
					{
						document.NewSolDesafiliacionForm.chk_N_Celu.disabled = false;
					}else{
						document.NewSolDesafiliacionForm.chk_N_Celu.disabled = true;
					}

					if(document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled == true){
					document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled = false;
					document.NewSolDesafiliacionForm.txt_TelCelular.disabled = false;
					} else{
						document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled = true;
						document.NewSolDesafiliacionForm.txt_TelCelular.disabled = true;
						}
					}
					
				if(check.name == "chk_N_Celu"){
					document.NewSolDesafiliacionForm.chk_flagCelu.value = "2";

					if(document.NewSolDesafiliacionForm.chk_A_Celu.disabled == true)
					{
						document.NewSolDesafiliacionForm.chk_A_Celu.disabled = false;
					}else{
						document.NewSolDesafiliacionForm.chk_A_Celu.disabled = true;
					}
					if(document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled == true){
					document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled = false;
					document.NewSolDesafiliacionForm.txt_TelCelular.disabled = false;
					document.NewSolDesafiliacionForm.txt_codAreaCelular.value = "";
					document.NewSolDesafiliacionForm.txt_TelCelular.value = "";
					} else{
						document.NewSolDesafiliacionForm.txt_codAreaCelular.disabled = true;
						document.NewSolDesafiliacionForm.txt_TelCelular.disabled = true;
						document.NewSolDesafiliacionForm.txt_codAreaCelular.value = celuCodArea;
						document.NewSolDesafiliacionForm.txt_TelCelular.value = celuNroTel;
						}
					}
				}
				
			// CHECK DEL CAMPO E MAIL
			
			if(check.name == "chk_A_Email" || check.name == "chk_N_Email"){
			
				if(check.name == "chk_A_Email"){
				document.NewSolDesafiliacionForm.chk_flagEmail.value = "1";
					if(document.NewSolDesafiliacionForm.chk_N_Email.disabled == true)
					{
						document.NewSolDesafiliacionForm.chk_N_Email.disabled = false;
					}else{
						document.NewSolDesafiliacionForm.chk_N_Email.disabled = true;
					}

					if(document.NewSolDesafiliacionForm.txt_Email.disabled == true){
					document.NewSolDesafiliacionForm.txt_Email.disabled = false;
					} else{
						document.NewSolDesafiliacionForm.txt_Email.disabled = true;
						}
					}
					
				if(check.name == "chk_N_Email"){
				document.NewSolDesafiliacionForm.chk_flagEmail.value = "2";

					if(document.NewSolDesafiliacionForm.chk_A_Email.disabled == true)
					{
						document.NewSolDesafiliacionForm.chk_A_Email.disabled = false;
					}else{
						document.NewSolDesafiliacionForm.chk_A_Email.disabled = true;
					}
					if(document.NewSolDesafiliacionForm.txt_Email.disabled == true){
					document.NewSolDesafiliacionForm.txt_Email.disabled = false;
					document.NewSolDesafiliacionForm.txt_Email.value = "";
					} else{
						document.NewSolDesafiliacionForm.txt_Email.disabled = true;
						document.NewSolDesafiliacionForm.txt_Email.value = chkEmail;
						}
					}
				}
				
				// CHECK DEL CAMPO E CALLE
			
			if(check.name == "chk_A_Calle" || check.name == "chk_N_Calle"){
			
				if(check.name == "chk_A_Calle"){
				document.NewSolDesafiliacionForm.chk_flagCalle.value = "1";
					if(document.NewSolDesafiliacionForm.chk_N_Calle.disabled == true)
					{
						document.NewSolDesafiliacionForm.chk_N_Calle.disabled = false;
					}else{
						document.NewSolDesafiliacionForm.chk_N_Calle.disabled = true;
					}

					if(document.NewSolDesafiliacionForm.txt_Calle.disabled == true){
					document.NewSolDesafiliacionForm.txt_Calle.disabled = false;
					document.NewSolDesafiliacionForm.txt_Numero.disabled = false;
					document.NewSolDesafiliacionForm.txt_PoblVilla.disabled = false;
					document.NewSolDesafiliacionForm.txt_Departamento.disabled = false;
					document.NewSolDesafiliacionForm.dbx_Region.disabled = false;
					document.NewSolDesafiliacionForm.dbx_Provincia.disabled = false;
					document.NewSolDesafiliacionForm.dbx_Comuna.disabled = false;
					} else{
						document.NewSolDesafiliacionForm.txt_Calle.disabled = true;
						document.NewSolDesafiliacionForm.txt_Numero.disabled = true;
						document.NewSolDesafiliacionForm.txt_PoblVilla.disabled = true;
						document.NewSolDesafiliacionForm.txt_Departamento.disabled = true;
						document.NewSolDesafiliacionForm.dbx_Region.disabled = true;
						document.NewSolDesafiliacionForm.dbx_Provincia.disabled = true;
						document.NewSolDesafiliacionForm.dbx_Comuna.disabled = true;
						}
					}
					
				if(check.name == "chk_N_Calle"){
				document.NewSolDesafiliacionForm.chk_flagCalle.value = "2";
					if(document.NewSolDesafiliacionForm.chk_A_Calle.disabled == true)
					{
						document.NewSolDesafiliacionForm.chk_A_Calle.disabled = false;
					}else{
						document.NewSolDesafiliacionForm.chk_A_Calle.disabled = true;
					}
					if(document.NewSolDesafiliacionForm.txt_Calle.disabled == true){
					document.NewSolDesafiliacionForm.txt_Calle.disabled = false;
					document.NewSolDesafiliacionForm.txt_Numero.disabled = false;
					document.NewSolDesafiliacionForm.txt_PoblVilla.disabled = false;
					document.NewSolDesafiliacionForm.txt_Departamento.disabled = false;
					document.NewSolDesafiliacionForm.dbx_Region.disabled = false;
					document.NewSolDesafiliacionForm.dbx_Provincia.disabled = false;
					document.NewSolDesafiliacionForm.dbx_Comuna.disabled = false;
					document.NewSolDesafiliacionForm.txt_Calle.value = "";
					document.NewSolDesafiliacionForm.txt_Numero.value = "";
					document.NewSolDesafiliacionForm.txt_PoblVilla.value = "";
					document.NewSolDesafiliacionForm.txt_Departamento.value = "";
					document.NewSolDesafiliacionForm.dbx_Region.value = 0;
					document.NewSolDesafiliacionForm.dbx_Provincia.value = 0;
					document.NewSolDesafiliacionForm.dbx_Comuna.value = 0;
					} else{
						document.NewSolDesafiliacionForm.txt_Calle.disabled = true;
						document.NewSolDesafiliacionForm.txt_Numero.disabled = true;
						document.NewSolDesafiliacionForm.txt_PoblVilla.disabled = true;
						document.NewSolDesafiliacionForm.txt_Departamento.disabled = true;
						document.NewSolDesafiliacionForm.dbx_Region.disabled = true;
						document.NewSolDesafiliacionForm.dbx_Provincia.disabled = true;
						document.NewSolDesafiliacionForm.dbx_Comuna.disabled = true;

						document.NewSolDesafiliacionForm.txt_Calle.value = chkCalle;
						document.NewSolDesafiliacionForm.txt_Numero.value = chkNumCalle;
						document.NewSolDesafiliacionForm.txt_PoblVilla.value = chkPobla;
						document.NewSolDesafiliacionForm.txt_Departamento.value = chkDepto;
						document.NewSolDesafiliacionForm.dbx_Region.value = chkRegion;
						document.NewSolDesafiliacionForm.dbx_Provincia.value = chkProvincia;
						document.NewSolDesafiliacionForm.dbx_Comuna.value = chkComuna;
						}
					}
				}

	}
	
		/*Funcion que revisa el resultado*/
	function revisaResultado(){
		
		if(document.NewSolDesafiliacionForm.resultado.value != "" && document.NewSolDesafiliacionForm.resultado.value != "null"){
		
			if (document.NewSolDesafiliacionForm.resultado.value != "0")
			{
				document.NewSolDesafiliacionForm.dbx_Oficina.value = '<%=session.getAttribute("Oficina")%>';
				document.NewSolDesafiliacionForm.txt_FecUltApo.value = '<%=request.getAttribute("txt_FecUltApo")%>';
				
				document.NewSolDesafiliacionForm.dbx_Region.value = '<%=session.getAttribute("Region")%>';
				cargarProvincias(document.NewSolDesafiliacionForm.dbx_Provincia,document.NewSolDesafiliacionForm.dbx_Comuna,'<%=session.getAttribute("Region")%>','<%=session.getAttribute("Provincia")%>');
				cargarComunas(document.NewSolDesafiliacionForm.dbx_Comuna,'<%=session.getAttribute("Provincia")%>','<%=session.getAttribute("Comuna")%>');				
				document.NewSolDesafiliacionForm.dbx_CajaCompensacion.value = '<%=session.getAttribute("CajaCompensacion")%>';
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

	/* FIN GRILLA*/
	/*Funcion que limpia el formulario.*/
	function limpiarFormulario(){
	
	    document.NewSolDesafiliacionForm.txt_Folio.disabled = false;
	    document.NewSolDesafiliacionForm.txt_Hora.disabled = false;
	    document.NewSolDesafiliacionForm.txt_Comentarios.disabled = false;
	    document.NewSolDesafiliacionForm.txt_ApePat.disabled = false;
        document.NewSolDesafiliacionForm.txt_ApeMat.disabled = false;
        document.NewSolDesafiliacionForm.txt_Nombres.disabled = false;
        document.NewSolDesafiliacionForm.chk_A_Celu.disabled = false;
		document.NewSolDesafiliacionForm.chk_N_Celu.disabled = false;
		document.NewSolDesafiliacionForm.chk_A_Email.disabled = false;
		document.NewSolDesafiliacionForm.chk_N_Email.disabled = false;
		document.NewSolDesafiliacionForm.chk_A_Calle.disabled = false;
		document.NewSolDesafiliacionForm.chk_N_Calle.disabled = false;
		document.NewSolDesafiliacionForm.txt_codAreaContacto.disabled = false;
        document.NewSolDesafiliacionForm.txt_TelContacto.disabled = false;
        document.NewSolDesafiliacionForm.dbx_CajaCompensacion.disabled = false;
            
        document.NewSolDesafiliacionForm.dbx_Motivo.disabled = false;
	    document.NewSolDesafiliacionForm.dbx_DescMotivo.disabled = false;
		document.NewSolDesafiliacionForm.txt_Observaciones.disabled = false;
		document.NewSolDesafiliacionForm.img_FecSolDesaf.disabled = false;
		document.NewSolDesafiliacionForm.dbx_Oficina.disabled = false;
	    
	    botonAgregar = document.getElementById("btn_Ingresar");
        botonAgregar.disabled = false;
	    
		document.NewSolDesafiliacionForm.txt_Rut.value = "";
		document.NewSolDesafiliacionForm.txt_NumVerif.value = "";
		document.NewSolDesafiliacionForm.txt_Folio.value = "";
		document.NewSolDesafiliacionForm.txt_Sucursal.value = "";
		document.NewSolDesafiliacionForm.txt_ApePat.value = "";
		document.NewSolDesafiliacionForm.txt_ApeMat.value = "";
		document.NewSolDesafiliacionForm.txt_Nombres.value = "";
		document.NewSolDesafiliacionForm.txt_codAreaCelular.value = "";
		document.NewSolDesafiliacionForm.txt_TelCelular.value = "";
		document.NewSolDesafiliacionForm.txt_codAreaContacto.value = "";
		document.NewSolDesafiliacionForm.txt_TelContacto.value = "";
		document.NewSolDesafiliacionForm.txt_Email.value = "";
		document.NewSolDesafiliacionForm.txt_Calle.value = "";
		document.NewSolDesafiliacionForm.txt_Numero.value = "";
		document.NewSolDesafiliacionForm.txt_PoblVilla.value = "";
		document.NewSolDesafiliacionForm.txt_Departamento.value = "";
		document.NewSolDesafiliacionForm.dbx_Region.value = 0;
		document.NewSolDesafiliacionForm.dbx_Provincia.value = 0;
		document.NewSolDesafiliacionForm.dbx_Comuna.value = 0;
		
		/*Aqui se limpian los campos del punto 2*/
		document.NewSolDesafiliacionForm.dbx_CajaCompensacion.value = 0;
		//document.NewSolDesafiliacionForm.txt_FecVigAfil.value = "";
		document.NewSolDesafiliacionForm.txt_FecUltApo.value = "";
		document.NewSolDesafiliacionForm.dbx_Motivo.value = 0;		
		document.NewSolDesafiliacionForm.dbx_DescMotivo.value = 0;
		document.NewSolDesafiliacionForm.txt_Observaciones.value = "";
		
		
		/*Aqui se limpian los campos del punto 3*/
		//document.NewSolDesafiliacionForm.txt_RutCaptador.value = "";
		//document.NewSolDesafiliacionForm.txt_ApePatProm.value = "";
		//document.NewSolDesafiliacionForm.txt_ApeMatProm.value = "";
		//document.NewSolDesafiliacionForm.txt_NombreProm.value = "";
		document.NewSolDesafiliacionForm.dbx_Oficina.value = 0;
		//document.NewSolDesafiliacionForm.txt_FecIngr.value = "";
		document.NewSolDesafiliacionForm.txt_FecSolDesaf.value = "";
		document.NewSolDesafiliacionForm.txt_Hora.value = "";
		document.NewSolDesafiliacionForm.txt_Comentarios.value = "";
		
		//document.NewSolDesafiliacionForm.idPersona.value = 0;
		//document.NewSolDesafiliacionForm.idSecuenciaTelefonoPart.value = 0;
		//document.NewSolDesafiliacionForm.idSecuenciaTelefonoCelu.value = 0;
		//document.NewSolDesafiliacionForm.idSecuenciaEmail.value = 0;
		//document.NewSolDesafiliacionForm.idSecuenciaDireccionPart.value = 0;
	}

	
	/* FIN MODIFICACION ANALISTA */
	
	function mostrarDiv()
	{
		var nombre = null;
		var apePat = null;
		var apeMat = null;
		var rut = Trim(document.NewSolDesafiliacionForm.txt_NRut.value);
		
		ModSolicitudDWR.obtenerDatosPorRut(rut, function(data){
			var datos = null;
			datos = data;
			
			var persona = null;
			var solicitud = null;
			
			persona = datos.personaVO;
			solicitud = datos.solicitudVO;
			
			desBloqueaCampoIndiv(document.NewSolDesafiliacionForm.txt_nombreCompletoAfil);
			desBloqueaCampoIndiv(document.NewSolDesafiliacionForm.txt_TipoEstadoPorRut);
			
			nombre = persona.nombres;
			apePat = persona.apellidoPaterno;
			apeMat = persona.apellidoMaterno;
			document.NewSolDesafiliacionForm.txt_FolioPorRut.value = solicitud.folio;
			document.NewSolDesafiliacionForm.txt_TipoEstadoPorRut.value = solicitud.desTipoEstadoSolicitud;
			
			//bloqueaCampoIndiv(document.NewSolDesafiliacionForm.txt_FolioPorRut);
			bloqueaCampoIndiv(document.NewSolDesafiliacionForm.txt_TipoEstadoPorRut);
			
			var nombreCompleto = nombre + " " + apePat + " " + apeMat;
		
			document.NewSolDesafiliacionForm.txt_nombreCompletoAfil.value = nombreCompleto;
			bloqueaCampoIndiv(document.NewSolDesafiliacionForm.txt_nombreCompletoAfil);
			document.getElementById("grid_rut").style.display = "";
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
	
	//INICIO REQ 7002
	function validacionMesesVigencia()
	{

        var mesesVigencia = null;
		//var mesesVigenciaDes = 0;
		var fechSistema = null;
		//var flagMes = false;
		var flagMes = true;
		var mesDesde = 0;
		var anioDesde = 0;
		var mesSistema = 0;
		var anioSistema = 0;
		var anioAux = 0;
		var mesAux = 0;
		//var countMes = 0;
		var countMes = 1;
		var diferencia = 0;
		var restaMes = 0;
		var mesDiferenciaAux = 0;
		var anioDiferenciaAux = 0;
        var fSistema = "<%=session.getAttribute("FechaSistema")%>";
        
       
        DWREngine.setAsync(false);
 		NewSolDesafiliacionDWR.obtenerParametroList(function(data){
		mesesVigencia = data;
		mesesVigenciaDes = parseInt(mesesVigencia);
		
		mesDesde = parseInt(fchVigenciaDesde.substring(3,5));
		
		if (fchVigenciaDesde.substring(3,4) == "0"){
                  mesDesde = fchVigenciaDesde.substring(4,5);
            }else{
                  mesDesde = fchVigenciaDesde.substring(3,5);
            }
		
		anioDesde = parseInt(fchVigenciaDesde.substring(6,8));
		mesSistema = parseInt(fSistema.substring(3,5));
	    anioSistema = parseInt(fSistema.substring(8,10));
	    mesAux = mesSistema;
	    mesDiferenciaAux = mesAux;
		anioAux = anioSistema;
		anioDiferenciaAux = anioAux;
        
		});	
		DWREngine.setAsync(true);
		
		/*alert("mesAux: "+ mesAux);
		alert("mesDesde: "+ mesDesde);
		alert("anioAux: "+ anioAux);
		alert("anioDesde: "+ anioDesde);*/
	
		while(mesAux > mesDesde || anioAux > anioDesde){
	 
		 countMes ++;

         mesAux --;
         if (mesAux == 0){
		
		       mesAux = 12;
		       anioAux --;
	        	}
         }	

        //if(countMes <= mesesVigenciaDes){
        mesesVigenciaAct = countMes;
        if(countMes >= mesesVigenciaDes){
        	//mesesVigenciaAct = countMes;
           	flagMes = false;
        
        }
	
	return flagMes;
	
	}	
	
	
	
	function obtenerDatosAfiliado(){
	
	var estadoAporte = null ;
	var estadoAporteDes = 0 ; 
	var flagEstApo = false;
	var rut = Trim(document.NewSolDesafiliacionForm.txt_Rut.value);
	
	DWREngine.setAsync(false);
	NewSolDesafiliacionDWR.obtenerDatosAfiliado(rut,function(data){
		estadoAporte = data;
		estadoAporteDes = parseInt(estadoAporte);
		
	   });	
	DWREngine.setAsync(true);

	return estadoAporteDes;
	}
	
	
	function obtenerDeudaAporte(){	
	var montoDeuda = null ;
	var montoDeudaDes = 0 ; 
	var rut = Trim(document.NewSolDesafiliacionForm.txt_Rut.value);
	
	DWREngine.setAsync(false);
	NewSolDesafiliacionDWR.obtenerDeudaAporte(rut,function(data){
		montoDeuda = data;
		montoDeudaDes = montoDeuda;		
	   });	
	DWREngine.setAsync(true);

	return montoDeudaDes;
	}
	//FIN REQ 7002
	
</script>

</head>

<body onload="revisaResultado();">
<div id="caja_registro"><html:form action="/solDesafiliacion.do">
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="resultado"
		value="<%=request.getAttribute("resultado")%>">

	<input type="hidden" name="idPersona" value="0">
	<input type="hidden" name="idSecuenciaTelefonoPart" value="0">
	<input type="hidden" name="idSecuenciaTelefonoCelu" value="0">
	<input type="hidden" name="idSecuenciaEmail" value="0">
	<input type="hidden" name="idSecuenciaDireccionPart" value="0">
	<input type="hidden" name="existeCambio" value="0"> 
	<input type="hidden" name="existeCambio" value="0"> 
	<input type="hidden" name="chk_flagCelu" value="0"> 
	<input type="hidden" name="chk_flagEmail" value="0"> 
	<input type="hidden" name="chk_flagCalle" value="0"> 

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
			<td align="right">
			<a href="#" align="right" onclick="validaCancelar();">Volver</a> &nbsp;&nbsp;&nbsp;
			<a href="#" align="right" onClick="enviaFormulario(-1);">Cerrar	Sesión</a>
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
			</table>
			</td>
		</tr>
		<tr>
			<td height="250">
			<p><p2>1. Identificación del Trabajador Independiente</p2>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Fecha <input type="text" name="txt_Fecha" size="10"	value='<%=session.getAttribute("FechaSistema")%>' id="txt_Fecha" disabled="true" />
			<p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>N° RUT *</strong></td>
					<td width="16%"><input type="text" name="txt_Rut" id="txt_Rut" maxlength="10" size="9" onkeypress="Upper(this,'N')"
						value='<%=request.getAttribute("txt_Rut")==null? "":request.getAttribute("txt_Rut")%>' />
					<strong> - </strong>
					<input type="text" name="txt_NumVerif" id="txt_NumVerif" size="1" maxlength="1"	onkeypress="Upper(this,'D')"
						value='<%=request.getAttribute("txt_NumVerif")==null? "":request.getAttribute("txt_NumVerif")%>' />
					</td>
					<td colspan="3">
						<input type="button" name="btn_buscarIdCaptador" id="btn_buscarIdCaptador" class="btn_limp" value="Buscar"
						onClick="buscarTrabajorIndependiente();" /> (Ingrese Rut sin guión ni dígito verificador)
					</td>
					<td colspan="4"></td>
				</tr>
				
				<tr>
					<td><strong>N° Folio*</strong></td>
					<td>
						<input type="text" name="txt_Folio" id="txt_Folio" maxlength="12" onkeypress="Upper(this,'N')"
						value='<%=request.getAttribute("txt_Folio")==null? "":request.getAttribute("txt_Folio")%>' />
					</td>
					<td></td>
					<td><strong>Oficina *</strong></td>
					<td colspan="3">
						<input type="text" name="txt_Sucursal" id="txt_Sucursal" maxlength="3" size="50"/>
						<!--  
						<input type="text" name="txt_Oficina" id="txt_Oficina" maxlength="12" onkeypress="Upper(this,'N')" disabled="true" size="65"
						value='<%=request.getAttribute("txt_Folio")==null? "":request.getAttribute("txt_Folio")%>' />
						-->
					</td>
				</tr>
				
				<tr>
					<td><strong>Apellido Paterno *</strong></td>
					<td><input name="txt_ApePat" type="text" id="txt_ApePat" maxlength="50" onkeypress="Upper(this,'L')"
						value='<%=request.getAttribute("txt_ApePat")==null? "":request.getAttribute("txt_ApePat")%>' />
					</td>
					<td></td>
					<td><strong>Apellido Materno *</strong></td>
					<td>
						<input type="text" name="txt_ApeMat" id="txt_ApeMat" maxlength="50" onkeypress="Upper(this,'L')"
						value='<%=request.getAttribute("txt_ApeMat")==null? "":request.getAttribute("txt_ApeMat")%>' />
					</td>
					<td><strong>Nombres *</strong></td>
					<td>
						<input type="text" name="txt_Nombres" id="txt_Nombres" maxlength="100" onkeypress="Upper(this,'L')"
						value='<%=request.getAttribute("txt_Nombres")==null? "":request.getAttribute("txt_Nombres")%>' />
					</td>
					</tr>

				<tr>
					<td><strong>Teléfono Celular *</strong></td>
					<td>
						<input size="1" type="text" name="txt_codAreaCelular" id="txt_codAreaCelular" maxlength="2" onkeypress="Upper(this,'N')"
						value='<%=request.getAttribute("txt_codAreaCelular")==null? "":request.getAttribute("txt_codAreaCelular")%>' />
						<input size="10" type="text" name="txt_TelCelular" id="txt_TelCelular" maxlength="8" onkeypress="Upper(this,'N')"
						value='<%=request.getAttribute("txt_TelCelular")==null? "":request.getAttribute("txt_TelCelular")%>' />
					</td>
					<td>
						<strong>A</strong>
						<input type='checkbox' name='chk_A_Celu' id='chk_A_Celu' onClick="desbloqueaCampoChk(chk_A_Celu);"/>
						<strong>N</strong>
						<input type='checkbox' name='chk_N_Celu' id='chk_N_Celu' onClick="desbloqueaCampoChk(chk_N_Celu);"/>
					</td>
					<td><strong>Teléfono de Contacto</strong></td>
					<td>
						<input size="1" type="text" name="txt_codAreaContacto" id="txt_codAreaContacto" maxlength="3" onkeypress="Upper(this,'N')"
						value='<%=request.getAttribute("txt_codAreaContacto")==null? "":request.getAttribute("txt_codAreaContacto")%>' />
						<input size="10" type="text" name="txt_TelContacto"	id="txt_TelContacto" maxlength="8" onkeypress="Upper(this,'N')"
						value='<%=request.getAttribute("txt_TelContacto")==null? "":request.getAttribute("txt_TelContacto")%>' />
					</td>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td><strong>E-Mail *</strong></td>
					<td colspan="3"><input type="text" name="txt_Email" id="txt_Email" size="50" maxlength="100"
						onkeypress="Upper(this,'M')" onblur="validarEmail(this.value)"
						value='<%=request.getAttribute("txt_Email")==null? "":request.getAttribute("txt_Email")%>' />
					</td>
					<td>
						<strong>A</strong>
						<input type='checkbox' name='chk_A_Email' id='chk_A_Email' onClick="desbloqueaCampoChk(chk_A_Email);"/>
						<strong>N</strong>
						<input type='checkbox' name='chk_N_Email' id='chk_N_Email' onClick="desbloqueaCampoChk(chk_N_Email);"/>
					</td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td height="34"><strong>Calle *</strong></td>
					<td colspan="3"><input type="text" name="txt_Calle"	id="txt_Calle" size="50" maxlength="50"	onkeypress="Upper(this,'A')"
						value='<%=request.getAttribute("txt_Calle")==null? "":request.getAttribute("txt_Calle")%>' />
					</td>
					<td>
						<strong>A</strong>
						<input type='checkbox' name='chk_A_Calle' id='chk_A_Calle' onClick="desbloqueaCampoChk(chk_A_Calle);"/>
						<strong>N</strong>
						<input type='checkbox' name='chk_N_Calle' id='chk_N_Calle' onClick="desbloqueaCampoChk(chk_N_Calle);"/>
					</td>
					<td height="34"><strong>Número *</strong></td>
					<td><input type="text" name="txt_Numero" id="txt_Numero" size="5" maxlength="6"	onkeypress="Upper(this,'A')" disabled="true"
						value='<%=request.getAttribute("txt_Numero")==null? "":request.getAttribute("txt_Numero")%>' />
					</td>
					<td colspan="2"></td>
				</tr>
				
				<tr>
					<td height="34"><strong>Población o Villa</strong></td>
					<td colspan="3">
						<input type="text" name="txt_PoblVilla"	id="txt_PoblVilla" size="50" maxlength="50"	onkeypress="Upper(this,'A')" disabled="true"
						value='<%=request.getAttribute("txt_PoblVilla")==null? "":request.getAttribute("txt_PoblVilla")%>' />
					</td>
					<td></td>
					<td height="34">
						<strong>Departamento</strong></td>
					<td colspan="3">
						<input type="text" name="txt_Departamento" id="txt_Departamento" size="5" maxlength="5" disabled="true"
						onkeypress="Upper(this,'A')" value='<%=request.getAttribute("txt_Departamento")==null? "":request.getAttribute("txt_Departamento")%>' /></td>
				</tr>
				<tr>
					<td><strong>Región *</strong></td>
					<td colspan="3">
						<html:select property="dbx_Region" styleClass="dbx_geo"
						value="0"
						onchange="Cambios(1);cargarProvincias(NewSolDesafiliacionForm.dbx_Provincia,NewSolDesafiliacionForm.dbx_Comuna,this.value,0);">
						<html:option value="0">Seleccione</html:option>
						<html:options collection="ListRegiones" property="codigo"
							labelProperty="glosa" />
					</html:select>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Provincia *</strong></td>
					<td colspan="3">
						<select name="dbx_Provincia" id="dbxProvincia" style="width:330px"
						onchange="cargarComunas(NewSolDesafiliacionForm.dbx_Comuna,this.value,0);">
						<option value="0">Seleccione Región</option>
					</select>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td><strong>Comuna *</strong></td>
					<td colspan="3">
						<select name="dbx_Comuna" id="dbx_Comuna" style="width:330px"> 
						<option value="0">Seleccione Provincia</option>
					</select>
					</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
				<td colspan="7">&nbsp;</td>
				</tr>
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
					<td><strong>Fecha de Vigencia Desafiliación CCAF La Araucana *</strong></td>
					<td width="16%" colspan="3"><input size="10" type="text" name="txt_FecVigAfil" id="txt_FecVigAfil" 
						value='<%=session.getAttribute("FechaVigencia")%>' disabled="true"/>
					</td>
					<td></td>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr >
					<td height="4"><strong>Fecha Último Aporte *</strong></td>
					
					<td height="4">
						<input type="text" name="txt_FecUltApo" id="txt_FecUltApo" class="datepick" disabled="true" size="10" onchange="Cambios(1);"/>
<!--						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecUltApo);" id="img_FecUltApo">-->
					</td>
					<td></td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr height="20">
					<td height="4"><strong>Motivo de desafiliación</strong></td>
					<td></td>
					<td></td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr height="20">
					<td height="4"><strong>Tipo de Motivo*</strong></td>
					<td height="4"><strong>Descripción*</strong></td>
					<td height="4"><strong>Observaciones</strong></td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
				<tr height="20">
					<td width="34%">
						<html:select property="dbx_Motivo" styleClass="dbx_geo" value="0"
						onchange="cargarDescMotivo(NewSolDesafiliacionForm.dbx_DescMotivo,this.value,0);">
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
						<input type="text" name="txt_Observaciones" id="txt_Observaciones" size="40" maxlength="100" onkeypress="Upper(this,'A')">
					</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<p><p2>3. Antecedentes Proceso de Desafiliación</p2></p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td><strong>Rut Analista</strong></td>
					<td width="16%"><strong><input type="text"
						name="txt_RutCaptador" id="txt_RutCaptador"	value='<%=session.getAttribute("IDAnalista")%>' disabled="true"
						size="8" maxlength="12" onkeypress="Upper(this,'N')"> </strong>
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td><strong>Apellido Paterno</strong></td>
					<td width="16%"><input name="txt_ApePatProm" type="text" id="txt_ApePatProm" maxlength="50"
						value='<%=session.getAttribute("ApePatAnalista")%>' onkeypress="Upper(this,'L')" disabled="true" /></td>
					<td><strong>Apellido Materno</strong></td>
					<td><input type="text" name="txt_ApeMaProm"	id="txt_ApeMatProm" maxlength="50"
						value='<%=session.getAttribute("ApeMatAnalista")%>'	onkeypress="Upper(this,'L')" disabled="true" /></td>
					<td><strong>Nombre Analista</strong></td>
					<td colspan="3"><input type="text" name="txt_NombreProm" id="txt_NombreProm" maxlength="100"
						value='<%=session.getAttribute("NombreAnalista")%>' onkeypress="Upper(this,'L')" disabled="true" /></td>
				</tr>
				<tr>
					<td height="4"><strong>Oficina *</strong></td>
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
					<td width="16%"><input type="text" name="txt_FecIngr" id="txt_FecIngr" value='<%=session.getAttribute("FechaSistema")%>'
						disabled="true" size="10" />
					</td>
					<td>&nbsp;</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<!-- Se cambia de nombre Fecha Firma por Fecha Solicitud (solo en formulario). -->
					<td height="4"><strong>Fecha Solicitud *</strong></td>
					<td height="4">
						<input type="text" name="txt_FecSolDesaf" id="txt_FecSolDesaf" class="datepick" disabled="true" size="10" onchange="Cambios(1);"/>
<!--						<IMG style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FecSolDesaf);" id="img_FecSolDesaf">-->
					</td>
					<td colspan="2" height="4"><strong>Hora Solicitud *</strong>(formato HH:MM) </td>
					<td>
						<input type="text" name="txt_Hora" id="txt_Hora" value='<%=request.getAttribute("txt_Hora")==null? "":request.getAttribute("txt_Hora")%>'
						size="10" onchange="javascript:IsValidTime(txt_Hora)">
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
			<td height="37" align="right"><input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onClick="validaCancelar()" />
			&nbsp;&nbsp;&nbsp;
			<input type="button" name="btn_limpiar" id="btn_limpiar" class="btn_limp" value="Limpiar" onClick="limpiarFormulario()" />
			&nbsp;&nbsp;&nbsp;
			<input type="button" name="btn_Ingresar" id="btn_Ingresar" class="btn_limp" value="Ingresar" onClick="validaForm()" />
			&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>

</html:form></div>
</body>
</html:html>
