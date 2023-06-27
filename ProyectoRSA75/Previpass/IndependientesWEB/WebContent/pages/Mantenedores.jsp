<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<META http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
<title>SISTEMA TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet" type="text/css" />

<link href="/IndependientesWEB/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery-ui.min.js"></script>

<script type="text/javascript" language="JavaScript1.2"src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2"src="/IndependientesWEB/js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2"src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2"src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2"src="/IndependientesWEB/dwr/interface/MantenedoresDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>

<script type="text/javascript">

	//--- variables globales para comprar datos en boton cancelar al modificar
	//-- Tablas Globales
	var glosaTabGlob = "";
	//-- Tabla Perfiles
	var perfilInicial = 0;
	//-- Tabla Promotores
	var apePatProm = "";
	var apeMatProm = "";
	var nombresProm = "";
	var tipoOrgInicial = 0;
	var tipoCargoInicial = 0;
	var direccionProm = "";
	var codAreaProm = "";
	var telefonoProm = "";
	var fechaInicioProm = "";
	//-- Tabla TipoDoc
	var glosaTipoDoc = "";
	var tipoSol = 0;
	//-- Tabla Beneficios en Dinero
	var glosaCortaBen = "";
	var glosaBen = "";
	var codigoContBen = "";
	var tipoPagoBen = 0;
	var valorPagoBen = "";
	var montoPagarMaxBen = "";
	var carenciaBen = "";
	var recurrenciaBen = "";
	var plazoCobroBen = "";
	var fechaInicioBen = "";
	var fechaFinBen = "";
	//-- Tabla Documentos requeridos para Beneficio en Dinero
	var glosaCortaDocBenDin = "";
	var glosaDocBenDin = "";
	//-- Tabla Relacion entre Beneficios en Dinero y Documentos de Beneficio
	var beneficioRelBenDoc = 0;
	var documentoRelBenDoc = 0;

	var idBeneficio = "";

	//--- INI Grilla ---
	var vectorEntidades  = new Array();
	var vectorPerfiles = new Array();
	var vectorEstados = new Array();
	var vectorTipoPagoApo = new Array();
	var vectorTipoPagoBen = new Array();
	var vectorTipoOrg = new Array();
	var vectorTipoCargo = new Array();
	var vectorBeneficios = new Array();
	var vectorDocumentos = new Array();
	var vectorTipoSol = new Array();
	var comboDocInicial = 0;
	var comboBenInicial = 0;
	var botonMod = null;
	var botonIng = null;
	var glosaCortaBeneficio = "";
	var glosaCortaDocumento = "";

	
	var datos = new Array();
	var cantidadRegistrosPorPagina = 15;
	var paginaActual = 1;
	var opcionPagina = 0;
	var filaActual = 0;
	var filaCasoEspecial = 0;
	
	var arregloAgrupaciones = new Array();
	
	var tipoCalculo = "";	
	var glosaTipoCalculo = "";
	var entidadFiltro = 0;
	var rutPerfilFiltro = "";
	var dvPerfilFiltro = "";	
	var rutPromotorFiltro = "";
	var dvPromotorFiltro = "";
	var estEntFiltro = false;
	var estPerfFiltro = false;
	var estPromFiltro = false;
		
	cargaArreglos();

	function cargaArreglos(){
	
		GeograficoDWR.obtenerLista("ListEntidadesBox", function(data){
		
			var entidades = null
			entidades = data;
			
			for (i = 0; i < entidades.length; i++){
			
				vectorEntidades[i] = new ObjParametro(entidades[i].codigo, entidades[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListTipoPagoApoBox", function(data){
		
			var tipoPagoApo = null
			tipoPagoApo = data;
			
			for (i = 0; i < tipoPagoApo.length; i++){
			
				vectorTipoPagoApo[i] = new ObjParametro(tipoPagoApo[i].codigo, tipoPagoApo[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerTipoPago(
			function(data){
				tipoCalculo = data;
				glosaTipoCalculo = obtenerDescripcion(vectorTipoPagoApo,tipoCalculo);
						}
		);
		
		GeograficoDWR.obtenerLista("ListTipoPagoBenBox", function(data){
		
			var tipoPagoBen = null
			tipoPagoBen = data;
			
			for (i = 0; i < tipoPagoBen.length; i++){
			
				vectorTipoPagoBen[i] = new ObjParametro(tipoPagoBen[i].codigo, tipoPagoBen[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListPerfiles", function(data){
		
			var perfiles = null
			perfiles = data;
			
			for (i = 0; i < perfiles.length; i++){
				
				vectorPerfiles[i] = new ObjParametro(perfiles[i].codigo, perfiles[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListEstados", function(data){
		
			var estados = null
			estados = data;
			
			for (i = 0; i < estados.length; i++){
				
				vectorEstados[i] = new ObjParametro(estados[i].codigo, estados[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListTipoOrgBox", function(data){
		
			var tipoOrg = null
			tipoOrg = data;
			
			for (i = 0; i < tipoOrg.length; i++){
			
				vectorTipoOrg[i] = new ObjParametro(tipoOrg[i].codigo, tipoOrg[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListTipoCargoBox", function(data){
		
			var tipoCargo = null
			tipoCargo = data;
			
			for (i = 0; i < tipoCargo.length; i++){
			
				vectorTipoCargo[i] = new ObjParametro(tipoCargo[i].codigo, tipoCargo[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListBeneficiosBox", function(data){
		
			var beneficios = null
			beneficios = data;
			
			for (i = 0; i < beneficios.length; i++){
				vectorBeneficios[i] = new ObjParametro(beneficios[i].codigo, beneficios[i].glosa);
			}
		});
		
		GeograficoDWR.obtenerLista("ListDocumentosBox", function(data){
		
			var documentos = null
			documentos = data;

			for (i = 0; i < documentos.length; i++){
				vectorDocumentos[i] = new ObjParametro(documentos[i].codigo, documentos[i].glosa);
				
			}
		});
		
		GeograficoDWR.obtenerLista("ListTipoSolBox", function(data){
		
			var tipoSol = null
			tipoSol = data;
			
			for (i = 0; i < tipoSol.length; i++){
				
				vectorTipoSol[i] = new ObjParametro(tipoSol[i].codigo, tipoSol[i].glosa);
			}
		});
	}

	function cargarDatosEnGrilla(opcion){
		var contenidoTabla = "";
			if (opcion == "1"){
				for(var i=0;i<datos.length;i++){
					if(i < cantidadRegistrosPorPagina)
						contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);
					}
					document.getElementById("datosNomina").innerHTML = cargaCabeceraFiltroTablasGlobales() + cargaCabeceraTablasGlobales() + contenidoTabla + "</table>";
					
					generarPaginacion(opcion);
					obtenerComboEntidadesFiltro();
					document.MantenedoresForm.dbx_EntidadFiltro.value = entidadFiltro;
		}
			if (opcion == "2"){
				for(var i=0;i<datos.length;i++){
					if(i < cantidadRegistrosPorPagina)
						contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);
					}
					document.getElementById("datosNomina").innerHTML = cargaCabeceraFiltroPerfiles() + cargaCabeceraTablaPerfUsuarios() + contenidoTabla + "</table>";
					generarPaginacion(opcion);
					document.MantenedoresForm.txt_rutFilPerf.value = rutPerfilFiltro;
					document.MantenedoresForm.txt_NNumVerifFilPerf.value = dvPerfilFiltro;
		}
		
		if (opcion == "3"){
				for(var i=0;i<datos.length;i++){
					if(i < cantidadRegistrosPorPagina)
						contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);
					}
					document.getElementById("datosNomina").innerHTML = cargaCabeceraFiltroPromotores() + cargaCabeceraTablaPromotores() + contenidoTabla + "</table>";
					generarPaginacion(opcion);
					document.MantenedoresForm.txt_rutFilProm.value = rutPromotorFiltro;
					document.MantenedoresForm.txt_NNumVerifFilProm.value = dvPromotorFiltro;
		}
		
		if (opcion == "4"){
				for(var i=0;i<datos.length;i++){
					if(i < cantidadRegistrosPorPagina)
						contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);
					}
					document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaTipoDocSol() + contenidoTabla + "</table>";
					generarPaginacion(opcion);
		}
		
		if (opcion == "5"){
				for(var i=0;i<datos.length;i++){
					if(i < cantidadRegistrosPorPagina)
						contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);
					}
					document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaParrillaBenefDinero() + contenidoTabla + "</table>";
					generarPaginacion(opcion);
		}
		
		if (opcion == "6"){
				for(var i=0;i<datos.length;i++){
					if(i < cantidadRegistrosPorPagina)
						contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);
					}
					document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaDocsBenefDinero() + contenidoTabla + "</table>";
					generarPaginacion(opcion);
		}
		
		if (opcion == "7"){
				for(var i=0;i<datos.length;i++){
					if(i < cantidadRegistrosPorPagina)
						contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);
					}
					document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaRelBenefDinDocsBenefs() + contenidoTabla + "</table>";
					generarPaginacion(opcion);
		}

	}

	function obtenerFilaTabla(dato,opcion,i){
	    filaCasoEspecial = i;
		if (opcion == "1"){
		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+dato.glosa+"</td>"+
							"<td class='texto' align='center'>"+dato.entidad+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'> <img style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_lupa.gif' width='16' height='16' onclick='modifRegistro(1,"+i+")'> </td>"
							"</tr>";
		return texto;}
		
		if (opcion == "2"){
		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+dato.rut+"</td>"+
							"<td class='texto' align='center'>"+dato.perfil+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'> <img style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_lupa.gif' width='16' height='16' onclick='modifRegistro(2,"+i+")'> </td>"
							"</tr>";
		return texto;}
		
		if (opcion == "3"){
		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+dato.rut+"</td>"+
							"<td class='texto' align='center'>"+dato.nombres+" "+dato.apellidoPaterno+" "+dato.apellidoMaterno+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'> <img style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_lupa.gif' width='16' height='16' onclick='modifRegistro(3,"+i+")'> </td>"
							"</tr>";
		return texto;}
		
		if (opcion == "4"){
		var obl = "";
		if(dato.obligatorio == 1)
			obl = "SI";
			else
			obl = "NO";

		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+dato.nombre+"</td>"+
							"<td class='texto' align='center'>"+obl+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'> <img style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_lupa.gif' width='16' height='16' onclick='modifRegistro(4,"+i+")'> </td>"
							"</tr>";
		return texto;}
		
		if (opcion == "5"){
		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+dato.glosaCorta+"</td>"+
							"<td class='texto' align='center'>"+dato.glosa+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'> <img style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_lupa.gif' width='16' height='16' onclick='modifRegistro(5,"+i+")'> </td>"
							"</tr>";
		return texto;}
		
		if (opcion == "6"){
		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+dato.glosaCorta+"</td>"+
							"<td class='texto' align='center'>"+dato.glosa+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'> <img style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_lupa.gif' width='16' height='16' onclick='modifRegistro(6,"+i+")'> </td>"
							"</tr>";
		return texto;}
		
		if (opcion == "7"){
		glosaCortaBeneficio = obtenerDescripcion(vectorBeneficios,dato.idBeneficio);
		glosaCortaDocumento = obtenerDescripcion(vectorDocumentos,dato.idDocBenef);
		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+glosaCortaBeneficio+"</td>"+
							"<td class='texto' align='center'>"+glosaCortaDocumento+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'> <img style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_lupa.gif' width='16' height='16' onclick='modifRegistro(7,"+i+")'> </td>"
							"</tr>";
		return texto;}
	}

	function generarPaginacion(opcion){
		var paginas = (datos.length/cantidadRegistrosPorPagina)+"";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
			
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" Resultados(es)</font>";		
		
		for(var i=0; i<paginas;i++){
		
			texto = texto +" "+ "<a href='#' onclick='paginarResultados("+(i+1)+","+opcion+");'>"+(i + 1)+"</a>" ;

		}
		document.getElementById('datosPaginacion').innerHTML = "<table><tr><td align='center'>" + texto + '</td></tr></table>';
	}
	
	function paginarResultados(pagina,opcion){
	
		
		if(opcionPagina != opcion){
			pagina=1;
		}else{	
		paginaActual = pagina;
		}
		
		
		var inicio = (pagina-1)*cantidadRegistrosPorPagina;
		var fin = (pagina)*cantidadRegistrosPorPagina;
		document.getElementById("datosNomina").innerHTML = "";
		var contenidoTabla = "";
		for(var i=inicio;i<fin;i++){
			if(datos[i]!= null){
				var contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i],opcion,i);

				}
			else
				i = fin;
		}
		if (opcion == "1"){
		document.getElementById("datosNomina").innerHTML = cargaCabeceraFiltroTablasGlobales() + cargaCabeceraTablasGlobales() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(opcion);
		obtenerComboEntidadesFiltro();
		document.MantenedoresForm.dbx_EntidadFiltro.value = entidadFiltro;
		}
		
		if (opcion == "2"){
		document.getElementById("datosNomina").innerHTML = cargaCabeceraFiltroPerfiles() + cargaCabeceraTablaPerfUsuarios() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(opcion);
		document.MantenedoresForm.txt_rutFilPerf.value = rutPerfilFiltro;
		document.MantenedoresForm.txt_NNumVerifFilPerf.value = dvPerfilFiltro;
		}
		
		if (opcion == "3"){
		document.getElementById("datosNomina").innerHTML = cargaCabeceraFiltroPromotores() + cargaCabeceraTablaPromotores() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(opcion);
		document.MantenedoresForm.txt_rutFilProm.value = rutPromotorFiltro;
		document.MantenedoresForm.txt_NNumVerifFilProm.value = dvPromotorFiltro;
		}
		
		if (opcion == "4"){
		document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaTipoDocSol() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(opcion);
		}
		
		if (opcion == "5"){
		document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaParrillaBenefDinero() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(opcion);
		}
		
		if (opcion == "6"){
		document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaDocsBenefDinero() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(opcion);
		}
		
		if (opcion == "7"){
		document.getElementById("datosNomina").innerHTML = cargaCabeceraTablaRelBenefDinDocsBenefs() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(opcion);
		}
	}
	
	function cargaCabeceraFiltroTablasGlobales(){
		return '<tr>'+
					'<td width="550" class="texto" align="left" ><strong>&nbspEntidad</strong>'+
					'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>' +
						'<select property="dbx_Entidad" id="dbx_EntidadFiltro" styleClass="dbx_comboEntidades" enabled="true">'+
						'</select>'+ '&nbsp;&nbsp;&nbsp;&nbsp;' +
					'</td>'+
					'<td>'+
			   			'<input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Filtrar" onclick="filtraEntidad();"/> '+
					'</td>'+
				'</tr>';
	}
	
	function cargaCabeceraTablasGlobales(){
			return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="1%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="45%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Glosa</td>'+			            	
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Entidad</td>'+	            	
	            	'<td height="20" width="8%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" width="5%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	        	'</tr>';
	}
	
	function cargaCabeceraFiltroPerfiles(){
		return '<tr>'+
				'<td>&nbsp;'+
			    '<strong>Nº Rut </strong> '+
				'</td>'+
				'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>' +
				'<td >'+
			    '<input type="text" name="txt_rutFilPerf" id="txt_rutFilPerf" size="10" maxlength="9" onkeypress="Upper(this,\'N\')"/> <strong>'+
				'- </strong> <input type="text" name="txt_NNumVerifFilPerf" id="txt_NNumVerifFilPerf"'+
				'size="2" maxlength="1" onchange="ValidadorRUT(document.MantenedoresForm.txt_rutFilPerf.value,this.value)" onkeypress="Upper(this,\'D\')"/> '+
			    '<input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Filtrar" onclick="filtraPerfil();"/>	'+
			'</td>'+
		'</tr>'+
		'<tr height="20"></tr>';
	}
	
	function cargaCabeceraTablaPerfUsuarios(){
	        return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="5" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Rut</td>'+			            	
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Perfil</td>'+	            	
	            	'<td height="20" width="8%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	        	'</tr>';
	}
	
	function cargaCabeceraFiltroPromotores(){
		return '<tr>'+
				'<td>&nbsp;'+
			    '<strong>Nº Rut </strong> '+
				'</td>'+
				'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>' +
				'<td >'+
			    '<input type="text" name="txt_rutFilProm" id="txt_rutFilProm" size="10" maxlength="9" onkeypress="Upper(this,\'N\')"/> <strong>'+
				'- </strong> <input type="text" name="txt_NNumVerifFilProm" id="txt_NNumVerifFilProm"'+
				'size="2" maxlength="1" onchange="ValidadorRUT(document.MantenedoresForm.txt_rutFilProm.value,this.value)" onkeypress="Upper(this,\'D\')"/> '+
			    '<input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Filtrar" onclick="filtraPromotor();"/>	'+
			'</td>'+
		'</tr>'+
		'<tr height="20"></tr>';
	}
	
	function cargaCabeceraTablaPromotores(){
			return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="5" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Rut</td>'+			            	
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Nombre</td>'+	            	
	            	'<td height="20" width="8%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	        	'</tr>';
	}
	
	function cargaCabeceraTablaTipoDocSol(){
			return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="5" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Glosa</td>'+	            	
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Obligatorio</td>'+			            	
	            	'<td height="20" width="8%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	        	'</tr>';
	}
	
	function cargaCabeceraTablaParrillaBenefDinero(){
			return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="5" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">GlosaCorta</td>'+			            	
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Glosa</td>'+	            	
	            	'<td height="20" width="8%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	        	'</tr>';
	}
	
	function cargaCabeceraTablaDocsBenefDinero(){
			return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="5" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Glosa Corta</td>'+			            	
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Glosa</td>'+	            	
	            	'<td height="20" width="8%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	        	'</tr>';
	}
	
	function cargaCabeceraTablaRelBenefDinDocsBenefs(){
			return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="5" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Beneficio</td>'+			            	
	            	'<td height="20" width="50" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Documento</td>'+	            	
	            	'<td height="20" width="8%" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	        	'</tr>';
	}
		//--- FIN Grilla ---
		
	/* AQUI CONSULTO LA LISTA DE LOS DATOS DE TABLAS GLOBALES*/
	function consultaDatosTabGlob(op){
			estEntFiltro = false;
			
			MantenedoresDWR.consultaMantTabGlob(
				function(data){

					datos = data.lisMantTabGlob;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(op);
							}else{
							paginarResultados(paginaActual,1);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});
		}
		/* AQUI CONSULTO LA LISTA DE LOS DATOS DE TABLAS GLOBALES FILTRADOS POR ENTIDAD*/
		function filtraEntidad(){
			estEntFiltro = true;
			paginaActual = 1;
			
			entidadFiltro = document.MantenedoresForm.dbx_EntidadFiltro.value;
			paginarResultados(paginaActual,1);
			MantenedoresDWR.filtraMantTabGlob(entidadFiltro,
				function(data){
					datos = data.lisMantTabGlob;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(1);
							}else{
							paginarResultados(paginaActual,1);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});
		}
		
		/* AQUI CONSULTO LA LISTA DE LOS DATOS DE LA TABLA PERFILES*/
		function consultaDatosTabPerf(op){

		estPerfFiltro = false;
		
			MantenedoresDWR.consultaMantTabPerf(
				function(data){
					datos = data.lisMantTabPerf;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(op);
							}else{
							paginarResultados(paginaActual,2);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value; 
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});		
		}
		
		function filtraPerfil(){
		paginaActual = 1;
		estPerfFiltro = true;
		rutPerfilFiltro = document.MantenedoresForm.txt_rutFilPerf.value;
		dvPerfilFiltro = document.MantenedoresForm.txt_NNumVerifFilPerf.value;
	
		var rut = document.MantenedoresForm.txt_rutFilPerf.value;
		if(ValidadorRUT(document.MantenedoresForm.txt_rutFilPerf.value,document.MantenedoresForm.txt_NNumVerifFilPerf.value)){
			MantenedoresDWR.filtraMantTabPerf(rut,
				function(data){	
					datos = data.lisMantTabPerf;
					document.getElementById("datosNomina").innerHTML = "";
					if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(2);
							}else{
							paginarResultados(paginaActual,2);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
					});			
			}
		}
		
		
		/* AQUI CONSULTO LA LISTA DE LOS DATOS DE LA TABLA PROMOTORES*/
		function consultaDatosTabProm(op){

		estPromFiltro = false; 
		
			MantenedoresDWR.consultaMantTabProm(
				function(data){
					datos = data.lisMantTabProm;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(op);
							}else{
							paginarResultados(paginaActual,3);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});		
		}
		
		function filtraPromotor(){
		paginaActual = 1;
		estPromFiltro = true;
		rutPromotorFiltro = document.MantenedoresForm.txt_rutFilProm.value;
		dvPromotorFiltro = document.MantenedoresForm.txt_NNumVerifFilProm.value;
	
		var rut = document.MantenedoresForm.txt_rutFilProm.value;
		if(ValidadorRUT(document.MantenedoresForm.txt_rutFilProm.value,document.MantenedoresForm.txt_NNumVerifFilProm.value)){
			MantenedoresDWR.filtraMantTabProm(rut,
				function(data){	
					datos = data.lisMantTabProm;
					document.getElementById("datosNomina").innerHTML = "";
					if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(3);
							}else{
							paginarResultados(paginaActual,3);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
					});			
			}
		}
		
		/* AQUI CONSULTO LA LISTA DE LOS DATOS DE LA TABLA TIPO DOCUMENTO*/
		function consultaDatosTabTipoDoc(op){

		var user = "<%=session.getAttribute("IDAnalista")%>";
		
			MantenedoresDWR.consultaMantTabTipoDoc(
				function(data){
					datos = data.lisMantTabTipoDoc;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(op);
							}else{
							paginarResultados(paginaActual,4);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});		
		}
		
		/* AQUI CONSULTO LA LISTA DE LOS DATOS DE LA TABLA BENEFICIOS*/
		function consultaDatosTabBenef(op){

		var user = "<%=session.getAttribute("IDAnalista")%>";
		
			MantenedoresDWR.consultaMantTabBenef(
				function(data){
					datos = data.lisMantTabBenef;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(op);
							}else{
							paginarResultados(paginaActual,5);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});		
		}
		
		/* AQUI CONSULTO LA LISTA DE LOS DATOS DE LA TABLA BENEFICIOS EN DINERO*/
		function consultaDatosTabDocsBenefsDin(op){

		var user = "<%=session.getAttribute("IDAnalista")%>";
		
			MantenedoresDWR.consultaMantTabDocsBenefsDin(
				function(data){
					datos = data.lisMantTabDocsBenefsDin;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(op);
							}else{
							paginarResultados(paginaActual,6);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});		
		}
		
		
		/* AQUI CONSULTO LA LISTA DE LOS DATOS DE LA TABLA RELACION ENTRE BENEFICIOS EN DINERO Y DOCUMENTOS BENEFICIOS*/
		function consultaDatosTabRelBenefsDinDocsBenefs(op){

		var user = "<%=session.getAttribute("IDAnalista")%>";
		
			MantenedoresDWR.consultaMantTabRelBenefsDinDocsBenefs(
				function(data){
					datos = data.lisMantTabRelBenefsDinDocsBenefs;
					document.getElementById("datosNomina").innerHTML = "";
						if(datos != null && paginaActual == 1){
							cargarDatosEnGrilla(op);
							}else{
							paginarResultados(paginaActual,7);
							}
							opcionPagina = 	document.MantenedoresForm.dbx_Mantenedor.value;
						document.MantenedoresForm.archivo.value = data.archivoInforme;
			});		
		}
   
	function asignaValor(a){

		document.MantenedoresForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.MantenedoresForm.submit();
	}
	
		function esconderDiv()
	{
		document.getElementById("datosDetalleError").style.display = "none";
		document.getElementById("fondoNegroDetalles").style.visibility = "hidden";
		document.getElementById("datosDetalleErrorGrande").style.display = "none";
	}
	
	// INI OBETENER COMBO DE ENTIDADES
	function obtenerComboEntidades(){
		var cmb = document.getElementById("dbx_Entidad");
		cmb.options[0] = new Option("Seleccione", 0);
			for(var j=0; j<vectorEntidades.length; j++)
			{
				var item = null;
				item = vectorEntidades[j];
			
				cod = item.codigo;
				txt = item.glosa;

				cmb.options[j+1] = new Option(txt, cod);
			}
	}
	
	function obtenerComboEntidadesFiltro(){
		var cmb = document.getElementById("dbx_EntidadFiltro");
		cmb.options[0] = new Option("Seleccione", 0);
			for(var j=0; j<vectorEntidades.length; j++)
			{
				var item = null;
				item = vectorEntidades[j];
			
				cod = item.codigo;
				txt = item.glosa;

				cmb.options[j+1] = new Option(txt, cod);
			}
	}
	
	// INI OBETENER COMBO DE PERFILES
	function obtenerComboPerfiles (){
	
		var cmb3 = document.getElementById("dbx_Perfil");		
		cmb3.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorPerfiles.length; j++)
			{
				var item = null;
				item = vectorPerfiles[j];
			
				cod = item.codigo;
				txt = item.glosa;

				cmb3.options[j+1] = new Option(txt, cod);
			}
	}
	
	// INI OBETENER COMBO DE TIPO CALCULO APORTE
	function obtenerComboSeleccionTipoCalculoAporte(){
	
		var cmb2 = document.getElementById("dbx_GlosaTipoPagoApo");
		cmb2.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorTipoPagoApo.length; j++)
			{
				var item = null;
				item = vectorTipoPagoApo[j];
			
				cod = item.codigo;
				txt = item.glosa;
				
				cmb2.options[j+1] = new Option(txt, cod);
	
			}
	}
	
	// INI OBETENER COMBO DE TIPO CALCULO TIPO PAGO BENEFICIO
	function obtenerComboSeleccionTipoCalculoBeneficio(){
	
		var cmb2 = document.getElementById("dbx_GlosaTipoPagoBen");
		cmb2.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorTipoPagoBen.length; j++)
			{
				var item = null;
				item = vectorTipoPagoBen[j];
			
				cod = item.codigo;
				txt = item.glosa;
				
				cmb2.options[j+1] = new Option(txt, cod);
	
			}
	}
	
	// INI OBETENER COMBO DE TIPO ORGANIZACION
	function obtenerComboTipoOrganizacion(){
	
		var cmb4 = document.getElementById("dbx_TipoOrg");
		cmb4.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorTipoOrg.length; j++)
			{
				var item = null;
				item = vectorTipoOrg[j];
			
				cod = item.codigo;
				txt = item.glosa;
				
				cmb4.options[j+1] = new Option(txt, cod);
			}
	}
	

	// INI OBETENER COMBO DE TIPO CARGO
	function obtenerComboTipoCargo(){
	
		var cmb5 = document.getElementById("dbx_TipoCargo");
		cmb5.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorTipoCargo.length; j++)
			{
				var item = null;
				item = vectorTipoCargo[j];
			
				cod = item.codigo;
				txt = item.glosa;
				
				cmb5.options[j+1] = new Option(txt, cod);
			}
	}
		
	// INI OBETENER COMBO DE BENEFICIOS
	function obtenerComboBeneficios(){
	
		var cmb6 = document.getElementById("dbx_BeneficioBenDinDoc");
		cmb6.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorBeneficios.length; j++)
			{
				var item = null;
				item = vectorBeneficios[j];
			
				cod = item.codigo;
				txt = item.glosa;
				
				cmb6.options[j+1] = new Option(txt, cod);
			}
	}
	
	// INI OBETENER COMBO DE DOCUMENTOS
	
	function obtenerComboDocumentos(){
	
		var cmb7 = document.getElementById("dbx_DocumentoBenDinDoc");
		cmb7.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorDocumentos.length; j++)
			{
				var item = null;
				item = vectorDocumentos[j];
			
				cod = item.codigo;
				txt = item.glosa;
				
				cmb7.options[j+1] = new Option(txt, cod);
			}
	}
	
	// INI OBETENER COMBO DE TIPO SOLICITUD 
	function obtenerComboTipoSol(){
	
		var cmb3 = document.getElementById("dbx_TipoSol");		
		cmb3.options[0] = new Option("Seleccione", 0);	
			for(var j=0; j<vectorTipoSol.length; j++)
			{
				var item = null;
				item = vectorTipoSol[j];
			
				cod = item.codigo;
				txt = item.glosa;

				cmb3.options[j+1] = new Option(txt, cod);
			}
	}

	
	//*** OBTENER HEADER DE CADA POP UP PARA AGREGAR NUEVO REGISTRO***//
	function obtenerHeaderDetalleIngresar(op)
	{
		switch(op){
			case 1:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>INGRESO NUEVO EN TABLA GLOBAL</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 2:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>INGRESO NUEVA TABLA PERFILAMIENTO DE USUARIOS</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 3:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>INGRESO NUEVO EN TABLA PROMOTOR</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 4:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>INGRESO NUEVA TABLA TIPO DOCUMENTO</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 5:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>INGRESO NUEVA TABLA BENEFICIOS EN DINERO</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 6:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>INGRESO NUEVA TABLA DOCUMENTO BENEFICIO EN DINERO</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 7:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>INGRESO NUEVA TABLA RELACION BENEFICIOS EN DINERO - DOCUMENTO</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			default:
					
					break;
		}
	}
	
	
		//*** OBTENER HEADER DE CADA POP UP AL MODIFICAR***//
	function obtenerHeaderDetalleModificar(op)
	{
		switch(op){
			case 1:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>MODIFICAR EN TABLA GLOBAL EXISTENTE</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 2:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>MODIFICAR EN TABLA PERFILAMIENTO DE USUARIOS EXISTENTE</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 3:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>MODIFICAR EN TABLA PROMOTOR EXISTENTE</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 4:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>MODIFICAR EN TABLA TIPO DOCUMENTO EXISTENTE</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 5:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>MODIFICAR EN TABLA BENEFICIOS EN DINERO EXISTENTE</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 6:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>MODIFICAR TABLA DOCUMENTO BENEFICIO EN DINERO EXISTENTE</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			case 7:
					return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center"><b>MODIFICAR TABLA RELACION BENEFICIOS EN DINERO - DOCUMENTO EXISTENTE</b></p>' + 
					'</td>'+
				'</tr>';
				break;
			default:
					break;
		}
	}

	function obtenerDetalleRegistrosIngresar(op){
	
		switch(op){
			case 1:
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					   "<tr>"+
							"<td class='texto' align='left' width='70'>" + "Glosa *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_GlosaTabGlob' id='txt_GlosaTabGlob' enabled='true' maxlength='100' size='45' onkeypress='Upper(this,\"TG\")'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left' width='70'>" + "Entidad *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<select property='dbx_Entidad' id='dbx_Entidad' styleClass='dbx_comboEntidades' enabled='true' width = '45'></select>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				
				case 2:
				
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left' width='60'>" + "RUT *" + "</td>" +
							"<td class='texto' align='left'>" +
							"<input type='text' name='txt_RutPerf' id='txt_RutPerf' size='10' maxlength='9' onkeypress='Upper(this,\"N\")'/>"+
							"<strong> - </strong>"+
							"<input type='text' name='txt_NNumVerif' id='txt_NNumVerif' size='2' maxlength='1' onkeypress='Upper(this,\"D\")'/>"+
							"</td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left' width='60'>" + "Perfil *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<select property='dbx_Perfil' id='dbx_Perfil' styleClass='dbx_comboPerfiles' enabled='true' width = '45'></select>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				
				case 3:
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td><strong>Rut *</strong></td>" +	
							"<td width='16%'>" +
						    "<input type='text' name='txt_RutProm' id='txt_RutProm' maxlength='10' size='9' onkeypress='Upper(this,\"N\")'>" +
							"<strong> - </strong>" +
							"<input type='text' name='txt_NumVerif' id='txt_NumVerif' size='1' maxlength='1' onkeypress='Upper(this,\"D\")'>" +
							"</td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td><strong>Apellido Paterno *</strong></td>" +
							"<td><input name='txt_ApePat' type='text' id='txt_ApePat' maxlength='50' onkeypress='Upper(this,\"L\")'></td>" +
							"<td align= 'left'><strong>Apellido Materno *</strong></td>" +
							"<td><input type='text' name='txt_ApeMat' id='txt_ApeMat' maxlength='50' onkeypress='Upper(this,\"L\")'></td>" +
							"<td align= 'left'><strong>Nombres *</strong></td>" +
							"<td colspan='3'><input type='text' name='txt_Nombres' id='txt_Nombres' maxlength='100' onkeypress='Upper(this,\"L\")'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td align= 'left'><strong>Tipo Organizacion *</strong></td>" +
							"<td><select property='dbx_TipoOrg' id='dbx_TipoOrg' styleClass='dbx_comboEntidades' enabled='true' width = '45'></select></td>"+
							"<td align= 'left'><strong>Tipo Cargo *</strong></td>" +
							"<td><select property='dbx_TipoCargo' id='dbx_TipoCargo' styleClass='dbx_comboEntidades' enabled='true' width = '45'></select></td>"+
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
						   "<td><strong>Dirección </strong></td>" +
						   "<td><input type='text' name='txt_Calle' id='txt_Calle' maxlength='50' ></td>" +
						   "<td align= 'left'><strong>Teléfono</strong></td>" +
						   "<td>" +
						   "<input size='1' type='text' name='txt_codAreaContacto' id='txt_codAreaContacto' maxlength='2' onkeypress='Upper(this,\"N\")'>" +
						   " " +
						   "<input size='10' type='text' name='txt_TelContacto' id='txt_TelContacto' maxlength='8' onkeypress='Upper(this,\"N\")'>" + "</td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
							"<td><strong>Fecha Inicio *</strong></td>" +
							"<td>" +
							"<input type='text' name='txt_FecIni' id='txt_FecIni' class='datepick' disabled='true' size='10' value=''> &nbsp;&nbsp;" +
							//"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' onclick='ShowCalendarFor(txt_FecIni)'>" + "</td>" +
					"</tr>";
				return texto;
				break;
				
				case 4:
				
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "Glosa *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_GlosaTipoDoc' id='txt_GlosaTipoDoc' enabled='true' size='45' maxlength='100'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td align= 'left'>Tipo Solicitud *</td>" +
							"<td><select property='dbx_TipoSol' id='dbx_TipoSol' styleClass='dbx_comboPerfiles' enabled='true' width = '45'></select></td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Obligatorio" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='checkbox' name='chk_Obligatorio' id='chk_Obligatorio'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				
				case 5:
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr width='100'>"+
							"<td><strong>Glosa Corta *</strong></td>" +
							"<td><input name='txt_GlosaCorta' width = '50' type='text' id='txt_GlosaCorta' maxlength='30' ></td>" +
							"<td align= 'left'><strong>Glosa *</strong></td>" +
							"<td><input type='text' name='txt_Glosa' id='txt_Glosa' maxlength='100' ></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td><strong>Codigo Contable *</strong></td>" +
							"<td><input name='txt_CodCont' type='text' id='txt_CodCont' maxlength='5' onkeypress='Upper(this,\"N\")'></td>" +
							"<td align= 'left'><strong>Tipo Pago *</strong></td>" +
							"<td>" +
								"<select property='dbx_GlosaTipoPagoBen' id='dbx_GlosaTipoPagoBen' styleClass='dbx_comboEntidades' enabled='true' width = '45'>"+
								"</select>"+
							"</td>"+
							"<td align= 'left'><strong>Valor Pago *</strong></td>" +
							"<td colspan='3'><input type='text' name='txt_ValPago' id='txt_ValPago' maxlength='9' onkeypress='Upper(this,\"N\")'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td align= 'left'><strong>Monto Pagar Max *</strong></td>" +
							"<td ><input type='text' name='txt_MontPagMax' id='txt_MontPagMax' maxlength='9' onkeypress='Upper(this,\"N\")'></td>" +
							"<td align= 'left'><strong>Carencia </strong></td>" +
							"<td ><input type='text' name='txt_Carencia' id='txt_Carencia' maxlength='3' size='1' onkeypress='Upper(this,\"N\")'><strong> Días</strong></td>" +
							"<td align= 'left'><strong>Recurrencia *</strong></td>" +
							"<td ><input type='text' name='txt_Recu' id='txt_Recu' maxlength='2' onkeypress='Upper(this,\"N\")' size='1'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
						   "<td><strong>Plazo Cobro *</strong></td>" +
						   "<td><input type='text' name='txt_PlazoCobro' id='txt_PlazoCobro' maxlength='3' size='1' onkeypress='Upper(this,\"N\")'><strong> Días</strong></td>" +
						   "<td align = 'left'><strong>Fecha Inicio Validez *</strong></td>" +
						   "<td>" +
						   "<input type='text' name='txt_FecIniVal' id='txt_FecIniVal' class='datepick' disabled='true' size='10' value=''>" +" "+
						   //"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' onclick='ShowCalendarFor(txt_FecIniVal)'>" + "</td>" +
						   "<td align = 'left'><strong>Fecha Fin Validez *</strong></td>" +
						   "<td>" +
						   "<input type='text' name='txt_FecFinVal' id='txt_FecFinVal' class='datepick' disabled='true' size='10' value=''>" +" "+
						   //"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' onclick='ShowCalendarFor(txt_FecFinVal)'>" + "</td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
							"<td><strong>Es Causante Unico </strong></td>" +
							"<td class='texto' align='left'>" +
							"<input type='checkbox' name='chk_Causante' id='chk_Causante' value='1'/>"+
							"</td>"+
					"</tr>";
				return texto;
				break;
				
				case 6:
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "Glosa Corta *" + "</td>" +
							"<td class='texto' align='left'>" +
							"<input type='text' name='txt_GlosaCortaDocBenDin' id='txt_GlosaCortaDocBenDin' enabled='true' maxlength='30' size='45'/>"+
							"</td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Glosa *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_GlosaDocBenDin' id='txt_GlosaDocBenDin' enabled='true' maxlength='100' size='45'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;

				case 7:
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td align= 'left'><strong>Beneficio *</strong></td>" +
							"<td><select property='dbx_BeneficioBenDinDoc' id='dbx_BeneficioBenDinDoc' styleClass='dbx_comboEntidades' enabled='true' width = '45'></select></td>"+
					"</tr>"+
					   "<tr>"+
							"<td align= 'left'><strong>Documento *</strong></td>" +
							"<td><select property='dbx_DocumentoBenDinDoc' id='dbx_DocumentoBenDinDoc' styleClass='dbx_comboEntidades' enabled='true' width = '45'></select></td>"+
					   "</tr>"+
					   "<tr>" +
							"<td width='100'><strong>Obligatorio</strong></td>" +
							"<td class='texto' align='left'>" +
							"<input type='checkbox' name='chk_ObligatorioBenDinDoc' id='chk_ObligatorioBenDinDoc'/>"+
							"</td>"+
					"</tr>";
				return texto;
				break;
				
			default:
				break;				
		}
	}

	function obtenerDetalleRegistrosModificar(op,fila){
	
		switch(op){
			case 1:
			
			glosaTabGlob = datos[fila].glosa;
			
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "Código " + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_CodigoTabGlob' id='txt_CodigoTabGlob' disabled='true' value = '"+datos[fila].codigo+"'size='45'/>"+
							"</td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Glosa *" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_GlosaTabGlob' id='txt_GlosaTabGlob' enable='true' value = '"+datos[fila].glosa+"' onkeypress='Upper(this,\"TG\")'size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Entidad " + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_EntidadTabGlob' id='txt_EntidadTabGlob' disabled='true' value = '"+datos[fila].entidad+"' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Estado " + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_EstadoTabGlob' id='txt_EstadoTabGlob' disabled='true' value = '"+datos[fila].estado+"' size='45'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				
				case 21:
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "Código" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_CodigoTabGlob' id='txt_CodigoTabGlob' disabled='true' value = '"+datos[fila].codigo+"'size='45'/>"+
							"</td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Glosa *" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<select property='dbx_GlosaTipoPagoApo' id='dbx_GlosaTipoPagoApo' styleClass='combobox' enabled='true' width = '45'></select>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Entidad" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_EntidadTabGlob' id='txt_EntidadTabGlob' disabled='true' value = '"+datos[fila].entidad+"' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Estado" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_EstadoTabGlob' id='txt_EstadoTabGlob' disabled='true' value = '"+datos[fila].estado+"' size='45'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				
				case 22:
				//PORCENTAJE

				if(tipoCalculo == 1){
				glosaTipoCalculo = obtenerDescripcion(vectorTipoPagoApo,tipoCalculo);

				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "Código" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_CodigoTabGlob' id='txt_CodigoTabGlob' disabled='true' value = '"+datos[fila].codigo+"'size='45'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left'>" + "Tipo *" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_TipoTabGlob' id='txt_TipoTabGlob' disabled='true' value = '"+glosaTipoCalculo+"'size='45' /></td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Porcentaje" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_GlosaTabGlob' id='txt_GlosaTabGlob' enable='true' value = '"+datos[fila].glosa+"' size='1' maxlength='3' onkeypress='Upper(this,\"N\")'/><strong> %</strong></td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Entidad" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_EntidadTabGlob' id='txt_EntidadTabGlob' disabled='true' value = '"+datos[fila].entidad+"' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Estado *" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_EstadoTabGlob' id='txt_EstadoTabGlob' disabled='true' value = '"+datos[fila].estado+"' size='45'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				}
				else{
				tipoCalculo = 2;
				glosaTipoCalculo = obtenerDescripcion(vectorTipoPagoApo,tipoCalculo);
				
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "Código" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_CodigoTabGlob' id='txt_CodigoTabGlob' disabled='true' value = '"+datos[fila].codigo+"'size='45'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left'>" + "Tipo" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_TipoTabGlob' id='txt_TipoTabGlob' disabled='true' value = '"+glosaTipoCalculo+"'size='45' /></td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Monto *" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_GlosaTabGlob' id='txt_GlosaTabGlob' enable='true' value = '"+datos[fila].glosa+"' size='45' onkeypress='Upper(this,\"N\")'/></td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Entidad" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_EntidadTabGlob' id='txt_EntidadTabGlob' disabled='true' value = '"+datos[fila].entidad+"' size='45'/>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Estado" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_EstadoTabGlob' id='txt_EstadoTabGlob' disabled='true' value = '"+datos[fila].estado+"' size='45'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				
				}
				
				
				case 2:

				var rut = datos[fila].rut;								
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "RUT" + "</td>" +
							"<td class='texto' align='left'>" +
							"<input type='text' name='txt_RutPerf' id='txt_RutPerf' size='10' disabled='true' value = '"+obtenerRut(rut)+"'/>"+
							"<strong>-</strong>"+
							"<input type='text' name='txt_NNumVerif' id='txt_NNumVerif' size='2' disabled='true' value = '"+obtenerDV(rut)+"'/>"+
							"</td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Perfil *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<select property='dbx_Perfil' id='dbx_Perfil' styleClass='dbx_comboPerfiles' enabled='true' width = '45'></select>"+
							"</td>"+
					   "</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Estado" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_Estado' id='txt_Estado' disabled='true' value = '"+datos[fila].estado+"' size='45'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;

				case 3:
				var rut = datos[fila].rut;
				
				apePatProm = datos[fila].apellidoPaterno;
				apeMatProm = datos[fila].apellidoMaterno;
				nombresProm = datos[fila].nombres;
				direccionProm = datos[fila].glosaCalle;
				if(datos[fila].glosaCalle == null)
					{
						direccionProm = "";
						datos[fila].glosaCalle = "";
					}
				codAreaProm = datos[fila].codArea;
				telefonoProm = datos[fila].nroTelefono;
				fechaInicioProm = datos[fila].fechaInicio;
				
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td><strong>Rut</strong></td>" +	
							"<td width='16%'>" +
						    "<input type='text' name='txt_RutProm' id='txt_RutProm' maxlength='10' size='9' disabled = 'true' value = '"+obtenerRut(rut)+"'>" +
							"<strong> - </strong>" +
							"<input type='text' name='txt_NumVerif' id='txt_NumVerif' size='1' maxlength='1' disabled = 'true' value = '"+obtenerDV(rut)+"'>" +
							"<td align='left'><strong>Estado </strong></td>" +	
							"<td width='16%'>" +
						    "<input type='text' name='txt_Estado' id='txt_Estado' maxlength='10' size='9' disabled = 'true' value = '"+datos[fila].estado+"'>" +
						    "</td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td><strong>Apellido Paterno *</strong></td>" +
							"<td><input name='txt_ApePat' type='text' id='txt_ApePat' maxlength='50' value = '"+datos[fila].apellidoPaterno+"' onkeypress='Upper(this,\"L\")'></td>" +
							"<td align= 'left'><strong>Apellido Materno *</strong></td>" +
							"<td><input type='text' name='txt_ApeMat' id='txt_ApeMat' maxlength='50' value = '"+datos[fila].apellidoMaterno+" 'onkeypress='Upper(this,\"L\")'></td>" +
							"<td align= 'left'><strong>Nombres *</strong></td>" +
							"<td colspan='3'><input type='text' name='txt_Nombres' id='txt_Nombres' maxlength='100' value = '"+datos[fila].nombres+"' onkeypress='Upper(this,\"L\")'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td align= 'left'><strong>Tipo Organizacion *</strong></td>" +
							"<td><select property='dbx_TipoOrg' id='dbx_TipoOrg' styleClass='dbx_comboEntidades' enabled='true' width = '45' ></select></td>"+
							"<td align= 'left'><strong>Tipo Cargo *</strong></td>" +
							"<td><select property='dbx_TipoCargo' id='dbx_TipoCargo' styleClass='dbx_comboEntidades' enabled='true' width = '45' ></select></td>"+
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
						   "<td><strong>Dirección </strong></td>" +
						   "<td><input type='text' name='txt_Calle' id='txt_Calle' maxlength='50' value = '"+datos[fila].glosaCalle+"'></td>" +
						   "<td align= 'left'><strong>Teléfono</strong></td>" +
						   "<td>" +
						   "<input size='1' type='text' name='txt_codAreaContacto' id='txt_codAreaContacto' maxlength='2' value = '"+datos[fila].codArea+"' onkeypress='Upper(this,\"N\")'>" +
						   " " +
						   "<input size='10' type='text' name='txt_TelContacto' id='txt_TelContacto' maxlength='8' value = '"+datos[fila].nroTelefono+"' onkeypress='Upper(this,\"N\")'>" + "</td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
							"<td><strong>Fecha Inicio</strong></td>" +
							"<td>" +
							"<input type='text' name='txt_FecIni' id='txt_FecIni' class='datepick' disabled='true' size='10' value='"+datos[fila].fechaInicio+"'> &nbsp;" +
							//"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' onclick='ShowCalendarFor(txt_FecIni)' >" + "</td>" +
							"<td align='left'><strong>Fecha Baja </strong></td>" +
							"<td>" +
							"<input type='text' name='txt_FecBaja' id='txt_FecBaja' disabled='true' size='10' value='"+datos[fila].fechaBaja+"'>" +
					"</tr>";
				return texto;
				break;
				
				case 4:
				
				glosaTipoDoc = datos[fila].nombre;
				
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "Código" + "</td>" +
							"<td class='texto' align='left'>" + 
								"<input type='text' name='txt_CodigoTipoDoc' id='txt_CodigoTipoDoc' disabled='true' value = '"+datos[fila].codigo+"'size='45'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left'>" + "Glosa *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_GlosaTipoDoc' id='txt_GlosaTipoDoc' enabled='true' size='45' value = '"+datos[fila].nombre+"' />"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left'>" + "TipoSolicitud *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<select property='dbx_TipoSol' id='dbx_TipoSol' styleClass='dbx_comboPerfiles' enabled='true' width = '45'></select>"+
							"</td>"+
					   "</tr>"+
				   "<tr>"+
						"<td class='texto' align='left'>" + "Obligatorio " + "</td>" +
						"<td class='texto' align='left'>" +
							"<input type='checkbox' name='chk_Obligatorio' id='chk_Obligatorio' value='1'/>"+
						"</td>"+
				   "</tr>"+
				   "<tr>"+
						"<td class='texto' align='left'>" + "Estado" + "</td>" +
						"<td class='texto' align='left'>" + 
							"<input type='text' name='txt_EstadoTipoDoc' id='txt_EstadoTipoDoc' disabled='true' value = '"+datos[fila].estado+"' size='45'/>"+
						"</td>"+
				   "</tr>";
				return texto;
				break;
				
				case 5:

				glosaCortaBen = datos[fila].glosaCorta;
				glosaBen = datos[fila].glosa;
				codigoContBen = datos[fila].codigoContable;
				tipoPagoBen = datos[fila].tipoPago;
				valorPagoBen = datos[fila].valorPago;
				montoPagarMaxBen = datos[fila].montoPagarMax;
				carenciaBen = datos[fila].carencia;
				recurrenciaBen = datos[fila].recurrencia;
				plazoCobroBen = datos[fila].plazoCobro;
				fechaInicioBen = datos[fila].fechaIniValidez;
				fechaFinBen = datos[fila].fechaFinValidez;
				
				if(datos[fila].tipoPago == 2){
				if(datos[fila].valorPago < 1 || datos[fila].valorPago >100)
					alert("Favor modificar valor Pago, este debe ser entre 1 y 100");

				idBeneficio = datos[fila].idBeneficio;
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr width='100'>"+
							"<td><strong>Id Beneficio </strong></td>" +
							"<td><input name='txt_IdBeneficio' width = '50' type='text' disabled = 'true' id='txt_IdBeneficio' maxlength='30' value = '"+datos[fila].idBeneficio+"'></td>" +
							"<td align= 'left'><strong>Estado </strong></td>" +
							"<td><input type='text' name='txt_Estado' id='txt_Estado' disabled = 'true' maxlength='100' value = '"+datos[fila].estado+"'></td>" +
					"</tr>"+
					"<tr width='100'>"+
							"<td><strong>Glosa Corta *</strong></td>" +
							"<td><input name='txt_GlosaCorta' width = '50' type='text' id='txt_GlosaCorta' maxlength='30' value = '"+datos[fila].glosaCorta+"'></td>" +
							"<td align= 'left'><strong>Glosa *</strong></td>" +
							"<td><input type='text' name='txt_Glosa' id='txt_Glosa' maxlength='100' value = '"+datos[fila].glosa+"'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td><strong>Codigo Contable *</strong></td>" +
							"<td><input name='txt_CodCont' type='text' id='txt_CodCont' maxlength='5' value = '"+datos[fila].codigoContable+"' onkeypress='Upper(this,\"N\")'></td>" +
							"<td align= 'left'><strong>Tipo Pago *</strong></td>" +
							"<td><select property='dbx_GlosaTipoPagoBen' id='dbx_GlosaTipoPagoBen' styleClass='dbx_comboEntidades' enabled='true' width = '45'></select></td>"+
							"<td align= 'left'><strong>Valor Pago *</strong></td>" +
							"<td colspan='3'><input type='text' name='txt_ValPago' id='txt_ValPago' maxlength='9' value = '"+datos[fila].valorPago+"' onkeypress='Upper(this,\"N\")'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td align= 'left'><strong>Monto Pagar Max *</strong></td>" +
							"<td ><input type='text' name='txt_MontPagMax' id='txt_MontPagMax' maxlength='9' value = '"+datos[fila].montoPagarMax+"' onkeypress='Upper(this,\"N\")'></td>" +
							"<td align= 'left'><strong>Carencia </strong></td>" +
							"<td ><input type='text' name='txt_Carencia' id='txt_Carencia' maxlength='3' value = '"+datos[fila].carencia+"' size='1' onkeypress='Upper(this,\"N\")'><strong> Días</strong></td>" +
							"<td align= 'left'><strong>Recurrencia *</strong></td>" +
							"<td ><input type='text' name='txt_Recu' id='txt_Recu' maxlength='2' value = '"+datos[fila].recurrencia+"' size='1' onkeypress='Upper(this,\"N\")'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
						   "<td><strong>Plazo Cobro *</strong></td>" +
						   "<td><input type='text' name='txt_PlazoCobro' id='txt_PlazoCobro' maxlength='3' value = '"+datos[fila].plazoCobro+"' size='1' onkeypress='Upper(this,\"N\")'><strong> Días</strong></td>" +
						   "<td align = 'left'><strong>Fecha Inicio Validez*</strong></td>" +
						   "<td>" +
						   "<input type='text' name='txt_FecIniVal' id='txt_FecIniVal' class='datepick' disabled = 'true' size='10' value = '"+datos[fila].fechaIniValidez+"'>" +" "+
						   //"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' onclick='ShowCalendarFor(txt_FecIniVal)'>" + "</td>" +
						   "<td right = 'left'><strong>Fecha Fin Validez*</strong></td>" +
						   "<td>" +
						   "<input type='text' name='txt_FecFinVal' id='txt_FecFinVal' class='datepick' disabled = 'true' size='10' value = '"+datos[fila].fechaFinValidez+"'>" +" "+
						   //"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16'onclick='ShowCalendarFor(txt_FecFinVal)'>" + "</td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
							"<td><strong>Es Causante Unico </strong></td>" +
							"<td class='texto' align='left'>" +
							"<input type='checkbox' name='chk_Causante' id='chk_Causante' value = '1'/>"+
							"</td>"+
					"</tr>";
				return texto;
				break;
				}
				else{
				
				idBeneficio = datos[fila].idBeneficio;
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr width='100'>"+
							"<td><strong>Id Beneficio </strong></td>" +
							"<td><input name='txt_IdBeneficio' width = '50' type='text' disabled = 'true' id='txt_IdBeneficio' maxlength='30' value = '"+datos[fila].idBeneficio+"'></td>" +
							"<td align= 'left'><strong>Estado </strong></td>" +
							"<td><input type='text' name='txt_Estado' id='txt_Estado' disabled = 'true' maxlength='100' value = '"+datos[fila].estado+"'></td>" +
					"</tr>"+
					"<tr width='100'>"+
							"<td><strong>Glosa Corta *</strong></td>" +
							"<td><input name='txt_GlosaCorta' width = '50' type='text' id='txt_GlosaCorta' maxlength='30' value = '"+datos[fila].glosaCorta+"'></td>" +
							"<td align= 'left'><strong>Glosa *</strong></td>" +
							"<td><input type='text' name='txt_Glosa' id='txt_Glosa' maxlength='100' value = '"+datos[fila].glosa+"'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td><strong>Codigo Contable *</strong></td>" +
							"<td><input name='txt_CodCont' type='text' id='txt_CodCont' maxlength='5' value = '"+datos[fila].codigoContable+"'></td>" +
							"<td align= 'left'><strong>Tipo Pago *</strong></td>" +
							"<td><select property='dbx_GlosaTipoPagoBen' id='dbx_GlosaTipoPagoBen' styleClass='dbx_comboEntidades' enabled='true' width = '45'></select></td>"+
							"<td align= 'left'><strong>Valor Pago *</strong></td>" +
							"<td colspan='3'><input type='text' name='txt_ValPago' id='txt_ValPago' maxlength='9' value = '"+datos[fila].valorPago+"'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>"+
							"<td align= 'left'><strong>Monto Pagar Max *</strong></td>" +
							"<td ><input type='text' name='txt_MontPagMax' id='txt_MontPagMax' maxlength='9' value = '"+datos[fila].montoPagarMax+"' onkeypress='Upper(this,\"N\")'></td>" +
							"<td align= 'left'><strong>Carencia </strong></td>" +
							"<td ><input type='text' name='txt_Carencia' id='txt_Carencia' maxlength='3' value = '"+datos[fila].carencia+"' size='1' onkeypress='Upper(this,\"N\")'><strong> Días</strong></td>" +
							"<td align= 'left'><strong>Recurrencia *</strong></td>" +
							"<td ><input type='text' name='txt_Recu' id='txt_Recu' maxlength='2' value = '"+datos[fila].recurrencia+"' size='1'></td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
						   "<td><strong>Plazo Cobro *</strong></td>" +
						   "<td><input type='text' name='txt_PlazoCobro' id='txt_PlazoCobro' maxlength='3' value = '"+datos[fila].plazoCobro+"' size='1' onkeypress='Upper(this,\"N\")'><strong> Días</strong></td>" +
						   "<td align = 'left'><strong>Fecha Inicio Validez*</strong></td>" +
						   "<td>" +
						   "<input type='text' name='txt_FecIniVal' id='txt_FecIniVal' class='datepick' disabled = 'true' size='10' value = '"+datos[fila].fechaIniValidez+"'>" +" "+
						   //"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' onclick='ShowCalendarFor(txt_FecIniVal)'>" + "</td>" +
						   "<td  align = 'left'><strong>Fecha Fin Validez*</strong></td>" +
						   "<td>" +
						   "<input type='text' name='txt_FecFinVal' id='txt_FecFinVal' class='datepick' disabled = 'true' size='10' value = '"+datos[fila].fechaFinValidez+"'>" +" "+
						   //"<img style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' onclick='ShowCalendarFor(txt_FecFinVal)'>" + "</td>" +
					"</tr>"+
					"<tr height='3'>"+
					"<tr>" +
							"<td><strong>Es Causante Unico </strong></td>" +
							"<td class='texto' align='left'>" +
							"<input type='checkbox' name='chk_Causante' id='chk_Causante' value = '1'/>"+
							"</td>"+
					"</tr>";
				return texto;
				break;
				
				}
				
				case 6:
				
				glosaCortaDocBenDin = datos[fila].glosaCorta;
				glosaDocBenDin = datos[fila].glosa;
	
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'>" + "ID Documento " + "</td>" +
							"<td class='texto' align='left'>" +
							"<input type='text' name='txt_IdDocBenDin' id='txt_IdDocBenDin' disabled='true' maxlength='30' size='45' value = '"+datos[fila].idDocumento+"'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left'>" + "Glosa Corta *" + "</td>" +
							"<td class='texto' align='left'>" +
							"<input type='text' name='txt_GlosaCortaDocBenDin' id='txt_GlosaCortaDocBenDin' enabled='true' maxlength='30' size='45' value = '"+datos[fila].glosaCorta+"'/>"+
							"</td>"+
					"</tr>"+
					   "<tr>"+
							"<td class='texto' align='left'>" + "Glosa *" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_GlosaDocBenDin' id='txt_GlosaDocBenDin' enabled='true' maxlength='100' size='45' value = '"+datos[fila].glosa+"'/>"+
							"</td>"+
					   "</tr>"+
					   	"<tr>"+
							"<td class='texto' align='left'>" + "Estado" + "</td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_EstadoDocBenDin' id='txt_EstadoDocBenDin' disabled='true' maxlength='100' size='45' value = '"+datos[fila].estado+"'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
				
				case 7:
				
				var texto = "<table width='90%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='0'>" +
					"<tr>"+
							"<td class='texto' align='left'><strong>" + "ID Relacion " + "</strong></td>" +
							"<td class='texto' align='left'>" +
							"<input type='text' name='txt_IdBenDinDoc' id='txt_IdBenDinDoc' disabled='true' maxlength='30' size='45' value = '"+datos[fila].idRelBenefDoc+"'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td align= 'left'><strong>Beneficio *</strong></td>" +
							"<td><select property='dbx_BeneficioBenDinDoc' id='dbx_BeneficioBenDinDoc' styleClass='dbx_comboEntidades' enabled='true' width = '45' ></select></td>"+
					"</tr>"+
					   "<tr>"+
							"<td align= 'left'><strong>Documento *</strong></td>" +
							"<td><select property='dbx_DocumentoBenDinDoc' id='dbx_DocumentoBenDinDoc' styleClass='dbx_comboEntidades' enabled='true' width = '45' ></select></td>"+
					   "</tr>"+
					   "<tr>" +
							"<td ><strong>Obligatorio </strong></td>" +
							"<td class='texto' align='left'>" +
							"<input type='checkbox' name='chk_ObligatorioBenDinDoc' id='chk_ObligatorioBenDinDoc' />"+
							"</td>"+
					"</tr>"+
						"<tr>"+
							"<td class='texto' align='left'><strong>" + "Estado" + "</strong></td>" +
							"<td class='texto' align='left'>" +
								"<input type='text' name='txt_EstadoBenDinDoc' id='txt_EstadoBenDinDoc' disabled='true' maxlength='100' size='45' value = '"+datos[fila].estado+"'/>"+
							"</td>"+
					   "</tr>";
				return texto;
				break;
		default:
			break;				
		}
	}

	function obtenerHeaderCancelarDetalleIngresar(opcion){
		switch(opcion){
			case 1:
				return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabGlob();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Limpiar" id="btn_Limpiar" class="btn_limp" value="Limpiar" onclick="limpiarTabGlobNew();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="guardarDatoTablaGlobal();"/>'+
						'</td>'+
					'</tr>';
				break;
				
			case 2:				
				return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabPerf();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Limpiar" id="btn_Limpiar" class="btn_limp" value="Limpiar" onclick="limpiarPerfil();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="guardarDatoPerfil();"/>'+
						'</td>'+
					'</tr>';
				break;
			
			case 3:
				return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabProm();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Limpiar" id="btn_Limpiar" class="btn_limp" value="Limpiar" onclick="limpiarPromotor();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="guardarDatoTablaPromotores();"/>'+
						'</td>'+
					'</tr>';
				break;
			
			case 4:				
				return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTipoDoc();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Limpiar" id="btn_Limpiar" class="btn_limp" value="Limpiar" onclick="limpiarTipoDoc();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="guardarDatoTipoDoc();"/>'+
						'</td>'+
					'</tr>';
				break;
				
			case 5:
				return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabBenDin();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Limpiar" id="btn_Limpiar" class="btn_limp" value="Limpiar" onclick="limpiarBeneficio();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="guardarDatoTablaBeneficiosDinero();"/>'+
						'</td>'+
					'</tr>';
				break;
				
			case 6:
				return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabDocBenDin();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Limpiar" id="btn_Limpiar" class="btn_limp" value="Limpiar" onclick="limpiarDocBenDin();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="guardarDatoTablaDocBenefDin();"/>'+
						'</td>'+
					'</tr>';
				break;
				
			case 7:
				return '<table width="98%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabRelBenDoc();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Limpiar" id="btn_Limpiar" class="btn_limp" value="Limpiar" onclick="limpiarBenDinDoc();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="guardarDatoTablaBenDinDoc();"/>'+
						'</td>'+
					'</tr>';
				break;
				
			default:
				break;
			}
	}
	
	function obtenerHeaderCancelarDetalleModificar(opcion){
		switch(opcion){
			case 1:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabGlobModif();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoTablaGlobal(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarAltaTabGlob" id="btn_DarAltaTabGlob" class="btn_est" value="Dar de Alta" onclick="cambiarEstado(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarBajaTabGlob" id="btn_DarBajaTabGlob" class="btn_est" value="Dar de Baja" onclick="cambiarEstado(0);"/>'+
						'</td>'+
					'</tr>';
					break;
					
				case 21:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="esconderDiv();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoTablaGlobal(21);"/>'+
						'</td>'+
					'</tr>';
					break;
						
				case 22:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="cancelarDatoTablaGlobal(22);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoTablaGlobal(22);"/>'+
						'</td>'+
					'</tr>';
					break;
					
				case 2:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabPerfModif();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoPerfil();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarAltaPerf" id="btn_DarAltaPerf" class="btn_est" value="Dar de Alta" onclick="cambiarEstadoPerfil(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarBajaPerf" id="btn_DarBajaPerf" class="btn_est" value="Dar de Baja" onclick="cambiarEstadoPerfil(0);"/>'+
						'</td>'+
					'</tr>';
					break;
					
					case 3:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabPromModif();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoPromotor();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarAltaProm" id="btn_DarAltaProm" class="btn_est" value="Dar de Alta" onclick="cambiarEstadoPromotor(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarBajaProm" id="btn_DarBajaProm" class="btn_est" value="Dar de Baja" onclick="cambiarEstadoPromotor(0);"/>'+
						'</td>'+
					'</tr>';
					break;
					
					case 4:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTipoDocModif();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoTipoDoc();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarAlta" id="btn_DarAlta" class="btn_est" value="Dar de Alta" onclick="cambiarEstadoTipoDoc(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarBaja" id="btn_DarBaja" class="btn_est" value="Dar de Baja" onclick="cambiarEstadoTipoDoc(0);"/>'+
						'</td>'+
					'</tr>';
					break;
					
					case 5:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabBenDinModif();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoBenef();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarAltaBenef" id="btn_DarAltaBenef" class="btn_est" value="Dar de Alta" onclick="cambiarEstadoBenef(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarBajaBenef" id="btn_DarBajaBenef" class="btn_est" value="Dar de Baja" onclick="cambiarEstadoBenef(0);"/>'+
						'</td>'+
					'</tr>';
					break;
					
					case 6:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabDocBenDinModif();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoDocBenDin();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarAltaDocBenefDin" id="btn_DarAltaDocBenefDin" class="btn_est" value="Dar de Alta" onclick="cambiarEstadoDocBenDin(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarBajaDocBenefDin" id="btn_DarBajaDocBenefDin" class="btn_est" value="Dar de Baja" onclick="cambiarEstadoDocBenDin(0);"/>'+
						'</td>'+
					'</tr>';
					break;
					
					case 7:
				return '<table width="90%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="0">'+
					'<tr>'+					
					'</tr>'+
					'<tr>'+
						'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
							'<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="validaCancelarTabRelBenDocModif();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Modificar" onclick="modificarDatoBenDinDoc();"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarAltaBenDinDoc" id="btn_DarAltaBenDinDoc" class="btn_est" value="Dar de Alta" onclick="cambiarEstadoBenDinDoc(1);"/>'+
							'&nbsp;&nbsp;&nbsp;' +
							'<input type="button" name="btn_DarBajaDBenDinDoc" id="btn_DarBajaBenDinDoc" class="btn_est" value="Dar de Baja" onclick="cambiarEstadoBenDinDoc(0);"/>'+
						'</td>'+
					'</tr>';
					break;
				
			default:
				break;
			}
	}
	
	function nuevoRegistro(valor){
	
		var opcion = parseInt(valor);
		
		switch(opcion){
		
			case 1:

			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleIngresar(opcion) + obtenerDetalleRegistrosIngresar(opcion) + obtenerHeaderCancelarDetalleIngresar(opcion)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboEntidades();
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
			case 2:

			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleIngresar(opcion) + obtenerDetalleRegistrosIngresar(opcion) + obtenerHeaderCancelarDetalleIngresar(opcion)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboPerfiles();
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
			case 3:

			document.getElementById("datosDetalleErrorGrande").style.display = "";
			document.getElementById("datosDetalleErrorGrande").innerHTML = obtenerHeaderDetalleIngresar(opcion) + obtenerDetalleRegistrosIngresar(opcion) + obtenerHeaderCancelarDetalleIngresar(opcion)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboTipoOrganizacion();
			obtenerComboTipoCargo();
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleErrorGrande").style.visibility = "visible";
			break;
			
			case 4:

			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleIngresar(opcion) + obtenerDetalleRegistrosIngresar(opcion) + obtenerHeaderCancelarDetalleIngresar(opcion)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboTipoSol();
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
			case 5:

			document.getElementById("datosDetalleErrorGrande").style.display = "";
			document.getElementById("datosDetalleErrorGrande").innerHTML = obtenerHeaderDetalleIngresar(opcion) + obtenerDetalleRegistrosIngresar(opcion) + obtenerHeaderCancelarDetalleIngresar(opcion)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboSeleccionTipoCalculoBeneficio();
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleErrorGrande").style.visibility = "visible";
			break;
			
			case 6:

			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleIngresar(opcion) + obtenerDetalleRegistrosIngresar(opcion) + obtenerHeaderCancelarDetalleIngresar(opcion)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
			case 7:

			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleIngresar(opcion) + obtenerDetalleRegistrosIngresar(opcion) + obtenerHeaderCancelarDetalleIngresar(opcion)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboDocumentos();
			obtenerComboBeneficios();
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
			default:
				break;
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
	
	function modifRegistroCasoEspecial(glosa){
	
			document.MantenedoresForm.dbx_EntidadFiltro.value = 31;
			if (estEntFiltro == true)
            	filtraEntidad();
            else
            	consultaDatosTabGlob("1");
            alert("Debe modificar el valor asociado al nuevo tipo seleccionado");
            modifRegistro(1,filaCasoEspecial);
	}

	function modifRegistro(valor,fila){
		var opcion = parseInt(valor);
		switch(opcion){
		
		case 1:
			filaActual = fila;
			// CASO ESPECIAL DE MODIFICACION PARA SELECCION TIPO CALCULO APORTE
			if(datos[fila].entidad == "SELECCION TIPO CALCULO APORTE"){
			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(21,fila) + obtenerHeaderCancelarDetalleModificar(21)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboSeleccionTipoCalculoAporte();
			document.MantenedoresForm.dbx_GlosaTipoPagoApo.value = tipoCalculo;	
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			}
			
			else if (datos[fila].entidad == "VALOR CALCULO APORTE"){
			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(22,fila) + obtenerHeaderCancelarDetalleModificar(22)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			}
			else{
			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(opcion,fila) + obtenerHeaderCancelarDetalleModificar(1)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			
			var codEstado = obtenerCodigo(vectorEstados, datos[fila].estado);
			// EN ALTA
			if (codEstado == 1){
				botonMod = document.getElementById("btn_DarAltaTabGlob");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarBajaTabGlob");
				botonMod.disabled = false;
			}
			// EN BAJA
			else{
				botonMod = document.getElementById("btn_DarBajaTabGlob");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarAltaTabGlob");
				botonMod.disabled = false;
			}
			
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			}
			break;
			
		case 2:
		
			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(opcion,fila) + obtenerHeaderCancelarDetalleModificar(2)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboPerfiles();
			perfilInicial = obtenerCodigo(vectorPerfiles, datos[fila].perfil);
			document.MantenedoresForm.dbx_Perfil.value = perfilInicial;	
			var codEstado = obtenerCodigo(vectorEstados, datos[fila].estado);
			// EN ALTA
			if (codEstado == 1){
				botonMod = document.getElementById("btn_DarAltaPerf");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarBajaPerf");
				botonMod.disabled = false;
			}
			// EN BAJA
			else{
				botonMod = document.getElementById("btn_DarBajaPerf");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarAltaPerf");
				botonMod.disabled = false;
			}
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
			
		case 3:
		
			document.getElementById("datosDetalleErrorGrande").style.display = "";
			document.getElementById("datosDetalleErrorGrande").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(opcion,fila) + obtenerHeaderCancelarDetalleModificar(3)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboTipoOrganizacion();
			obtenerComboTipoCargo();
			tipoOrgInicial = datos[fila].tipoOrgan;
			tipoCargoInicial = datos[fila].tipoCargo;
			document.MantenedoresForm.dbx_TipoOrg.value = datos[fila].tipoOrgan;
			document.MantenedoresForm.dbx_TipoCargo.value = datos[fila].tipoCargo;	
			
			var codEstado = obtenerCodigo(vectorEstados, datos[fila].estado);
			// EN ALTA
			if (codEstado == 1){
				botonMod = document.getElementById("btn_DarAltaProm");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarBajaProm");
				botonMod.disabled = false;
			}
			// EN BAJA
			else{
				botonMod = document.getElementById("btn_DarBajaProm");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarAltaProm");
				botonMod.disabled = false;
			}
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleErrorGrande").style.visibility = "visible";
			break;
			
		case 4:			
			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(opcion,fila) + obtenerHeaderCancelarDetalleModificar(4)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			
			obtenerComboTipoSol();
			tipoSol = datos[fila].tipoSolicitud;
			document.MantenedoresForm.dbx_TipoSol.value = datos[fila].tipoSolicitud;	
			
			var obligatorio = datos[fila].obligatorio;
			if (obligatorio == 1){
				document.MantenedoresForm.chk_Obligatorio.checked = true;
			}
			else{
				document.MantenedoresForm.chk_Obligatorio.checked = false;
			}
			var codEstado = obtenerCodigo(vectorEstados, datos[fila].estado);
			// EN ALTA
			if (codEstado == 1){
				botonMod = document.getElementById("btn_DarAlta");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarBaja");
				botonMod.disabled = false;
			}
			// EN BAJA
			else{
				botonMod = document.getElementById("btn_DarBaja");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarAlta");
				botonMod.disabled = false;
			}
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
		case 5:
		
			document.getElementById("datosDetalleErrorGrande").style.display = "";
			document.getElementById("datosDetalleErrorGrande").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(opcion,fila) + obtenerHeaderCancelarDetalleModificar(5)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			obtenerComboSeleccionTipoCalculoBeneficio();
			document.MantenedoresForm.dbx_GlosaTipoPagoBen.value = datos[fila].tipoPago;
			
			var obligatorio = datos[fila].isCausanteUnico;
			if (obligatorio == 1){
				document.MantenedoresForm.chk_Causante.checked = true;
			}
			else{
				document.MantenedoresForm.chk_Causante.checked = false;
			}
			
			var codEstado = obtenerCodigo(vectorEstados, datos[fila].estado);
			// EN ALTA
			if (codEstado == 1){
				botonMod = document.getElementById("btn_DarAltaBenef");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarBajaBenef");
				botonMod.disabled = false;
			}
			// EN BAJA
			else{
				botonMod = document.getElementById("btn_DarBajaBenef");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarAltaBenef");
				botonMod.disabled = false;
			}
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleErrorGrande").style.visibility = "visible";
			break;
			
		case 6:
		
			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(opcion,fila) + obtenerHeaderCancelarDetalleModificar(6)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			
			var codEstado = obtenerCodigo(vectorEstados, datos[fila].estado);
			// EN ALTA
			if (codEstado == 1){
				botonMod = document.getElementById("btn_DarAltaDocBenefDin");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarBajaDocBenefDin");
				botonMod.disabled = false;
			}
			// EN BAJA
			else{
				botonMod = document.getElementById("btn_DarBajaDocBenefDin");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarAltaDocBenefDin");
				botonMod.disabled = false;
			}
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
		case 7:
		
			document.getElementById("datosDetalleError").style.display = "";
			document.getElementById("datosDetalleError").innerHTML = obtenerHeaderDetalleModificar(opcion) + obtenerDetalleRegistrosModificar(opcion,fila) + obtenerHeaderCancelarDetalleModificar(7)+ "<tr><td colspan='10' align='right'></td></tr></table>";
			
			obtenerComboDocumentos();
			obtenerComboBeneficios();
			
			beneficioRelBenDoc = datos[fila].idBeneficio;
			documentoRelBenDoc = datos[fila].idDocBenef;
			
			document.MantenedoresForm.dbx_BeneficioBenDinDoc.value = datos[fila].idBeneficio;
			document.MantenedoresForm.dbx_DocumentoBenDinDoc.value = datos[fila].idDocBenef;
			
			var obligatorio = datos[fila].obligatorio;
			if (obligatorio == 1){
				document.MantenedoresForm.chk_ObligatorioBenDinDoc.checked = true;
			}
			else{
				document.MantenedoresForm.chk_ObligatorioBenDinDoc.checked = false;
			}
			
			var codEstado = obtenerCodigo(vectorEstados, datos[fila].estado);
			// EN ALTA
			if (codEstado == 1){
				botonMod = document.getElementById("btn_DarAltaBenDinDoc");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarBajaBenDinDoc");
				botonMod.disabled = false;
			}
			// EN BAJA
			else{
				botonMod = document.getElementById("btn_DarBajaBenDinDoc");
				botonMod.disabled = true;
				botonMod = document.getElementById("btn_DarAltaBenDinDoc");
				botonMod.disabled = false;
			}
			document.getElementById("fondoNegroDetalles").style.visibility = "visible";
			document.getElementById("datosDetalleError").style.visibility = "visible";
			break;
			
		default:
			break;
			
		}
		
	}
	
	
	////-----------------------  MAntenedor TABLA GLOBAL -----------------------////
	
	function guardarDatoTablaGlobal(){
		
		var glosa = document.MantenedoresForm.txt_GlosaTabGlob.value;
		var entidad = document.MantenedoresForm.dbx_Entidad.value;
		
		var alerta = "Falta por completar los siguientes datos:";
		var error = false;
		
		 if(glosa == ""){
				alerta = (alerta+"\n- Glosa");
				error = true;
				}
		if(entidad == "" || entidad == 0){
				alerta = (alerta+"\n- Entidad");
				error = true;
				}		
		if (error == true){
		alert(alerta);}
		else{
		
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
			
		MantenedoresDWR.saveDataGlobalTable(glosa, entidad, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos fueron guardados correctamente.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				// RECARGAR DATOS
            	if (estEntFiltro == true)
            		filtraEntidad();
            	else
            		consultaDatosTabGlob("1");
			}else{
				alert("Ocurrió un problema al insertar los datos.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			}	
		});
		esconderDiv();
		
		}

	}
	
	function validaCancelarTabGlob(){
		
		if(document.MantenedoresForm.txt_GlosaTabGlob.value != "" ||  document.MantenedoresForm.dbx_Entidad.value != 0){
		
		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}

	}
	
	function validaCancelarTabGlobModif(){
		
		if(document.MantenedoresForm.txt_GlosaTabGlob.value != glosaTabGlob){
		
		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}
	
	function cancelarDatoTablaGlobal(caso){
		switch(caso){
			case 22:
					var codigo = document.MantenedoresForm.txt_CodigoTabGlob.value;
					var glosa = document.MantenedoresForm.txt_GlosaTabGlob.value;
					var entidad = document.MantenedoresForm.txt_EntidadTabGlob.value;
					
					
					var alerta = "Falta por completar los siguientes datos:";
					var alertaValor = "El valor de la glosa debe ser entre 1 y 100";
					var error = false;
					var errorValor = false;
					
					 if(tipoCalculo==1){
						if(glosa>100 || glosa <1){
						errorValor = true;
						}
					}
					 if (codigo == ""){
						alerta = (alerta+"\n- Código");
						error = true;
						}
					 if(glosa == ""){
							alerta = (alerta+"\n- Glosa");
							error = true;
							}
					if(entidad == "" || entidad == 0){
							alerta = (alerta+"\n- Entidad");
							error = true;
							}		
					if (error == true || errorValor == true){
						if(error==true)
							alert(alerta);
						if(errorValor == true)
							alert(alertaValor);}
					else{
					esconderDiv();
					}
					break;
					
				default:
					break;
			}
	}
	
	function modificarDatoTablaGlobal(caso){
		switch(caso){
			case 21:
					var codigo = document.MantenedoresForm.txt_CodigoTabGlob.value;
					var glosa = document.MantenedoresForm.dbx_GlosaTipoPagoApo.value;
					var entidad = document.MantenedoresForm.txt_EntidadTabGlob.value;
					
					var glosaInicio = tipoCalculo
					var cambioTipoCalculo = false;
					
					if(glosa!=glosaInicio){
						cambioTipoCalculo = true;
					}
					var alerta = "Falta por completar los siguientes datos:";
					var error = false;
					
					 if (codigo == ""){
						alerta = (alerta+"\n- Código");
						error = true;
						}
					 if(glosa == ""){
							alerta = (alerta+"\n- Glosa");
							error = true;
							}
					if(entidad == "" || entidad == 0){
							alerta = (alerta+"\n- Entidad");
							error = true;
							}		
					if (error == true){
						alert(alerta);}
						
					else{
					document.getElementById("pantallaDeCarga").style.visibility = "visible";
					MantenedoresDWR.modifDataGlobalTable(codigo, glosa, entidad, function(data){
						var resp = null;
						resp = data.codRespuesta;
			
						if(resp == 0){
							
							document.getElementById("pantallaDeCarga").style.visibility = "hidden";
							alert("Los datos fueron modificados correctamente.");
							// RECARGAR DATOS
			            	if (estEntFiltro == true)
            					filtraEntidad();
            				else
            					consultaDatosTabGlob("1");
						}else{
							alert("Ocurrió un problema al modificar los datos.");
							document.getElementById("pantallaDeCarga").style.visibility = "hidden";
						}	
					});
					
					esconderDiv();
					
					if(cambioTipoCalculo == true)
					tipoCalculo = glosa;
						modifRegistroCasoEspecial(glosa);
					}

					break;
			case 22:
					var codigo = document.MantenedoresForm.txt_CodigoTabGlob.value;
					var glosa = document.MantenedoresForm.txt_GlosaTabGlob.value;
					var entidad = document.MantenedoresForm.txt_EntidadTabGlob.value;
					
					
					var alerta = "Falta por completar los siguientes datos:";
					var alertaValor = "El valor de la glosa debe ser entre 1 y 100";
					var error = false;
					var errorValor = false;
					
					 if(tipoCalculo==1){
						if(glosa>100 || glosa <1){
						errorValor = true;
						}
					}
					 if (codigo == ""){
						alerta = (alerta+"\n- Código");
						error = true;
						}
					 if(glosa == ""){
							alerta = (alerta+"\n- Glosa");
							error = true;
							}
					if(entidad == "" || entidad == 0){
							alerta = (alerta+"\n- Entidad");
							error = true;
							}		
					if (error == true || errorValor == true){
						if(error==true)
							alert(alerta);
						if(errorValor == true)
							alert(alertaValor);}
					else{
					document.getElementById("pantallaDeCarga").style.visibility = "visible";
					MantenedoresDWR.modifDataGlobalTable(codigo, glosa, entidad, function(data){
						var resp = null;
						resp = data.codRespuesta;
			
						if(resp == 0){
							
							document.getElementById("pantallaDeCarga").style.visibility = "hidden";
							alert("Los datos fueron modificados correctamente.");
							// RECARGAR DATOS
							if (estEntFiltro == true)
            					filtraEntidad();
            				else
            					consultaDatosTabGlob("1");
						}else{
							alert("Ocurrió un problema al modificar los datos.");
							document.getElementById("pantallaDeCarga").style.visibility = "hidden";
						}	
					});
					
					esconderDiv();
					}
					break;
					
			default:
					var codigo = document.MantenedoresForm.txt_CodigoTabGlob.value;
					var glosa = document.MantenedoresForm.txt_GlosaTabGlob.value;
					var entidad = document.MantenedoresForm.txt_EntidadTabGlob.value;
					
					var alerta = "Falta por completar los siguientes datos:";
					var error = false;
					
					 if (codigo == ""){
						alerta = (alerta+"\n- Código");
						error = true;
						}
					 if(glosa == ""){
							alerta = (alerta+"\n- Glosa");
							error = true;
							}
					if(entidad == "" || entidad == 0){
							alerta = (alerta+"\n- Entidad");
							error = true;
							}		
					if (error == true){
					alert(alerta);}
					else{
					document.getElementById("pantallaDeCarga").style.visibility = "visible";

					MantenedoresDWR.modifDataGlobalTable(codigo, glosa, entidad, function(data){
						var resp = null;
						resp = data.codRespuesta;
			
						if(resp == 0){
							alert("Los datos fueron modificados correctamente.");
							document.getElementById("pantallaDeCarga").style.visibility = "hidden";
							// RECARGAR DATOS
            				if (estEntFiltro == true)
            					filtraEntidad();
            				else
            					consultaDatosTabGlob("1");
						}else{
							alert("Ocurrió un problema al modificar los datos.");
							document.getElementById("pantallaDeCarga").style.visibility = "hidden";
						}	
					});
					esconderDiv();
					}
					break;
		}
	}
	
	function cambiarEstado(estado){
	
		var codigo = document.MantenedoresForm.txt_CodigoTabGlob.value;
		var entidad = document.MantenedoresForm.txt_EntidadTabGlob.value;
		
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		MantenedoresDWR.cambiarEstadoGlobalTable(codigo,entidad, estado, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Estado cambiado correctamente.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				// RECARGAR DATOS
				if (estEntFiltro == true)
  					filtraEntidad();
  				else
  					consultaDatosTabGlob("1");
            	
			}else{
				alert("Ocurrió un problema al cambiar el estado.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			}	
		});
		esconderDiv();
		
	}
	
	function limpiarTabGlob(){	
		document.MantenedoresForm.txt_CodigoTabGlob.value = "";
		document.MantenedoresForm.txt_GlosaTabGlob.value = "";
		document.MantenedoresForm.dbx_Entidad.value = 0;		
	}
	
	function limpiarTabGlobNew(){
		document.MantenedoresForm.txt_GlosaTabGlob.value = "";
		document.MantenedoresForm.dbx_Entidad.value = 0;		
	}
	
	//----------------------- MAntenedor Perfiles -----------------------////
		
	function guardarDatoPerfil(){
		var rut = document.MantenedoresForm.txt_RutPerf.value;
		var perfil = document.MantenedoresForm.dbx_Perfil.value;
		if ((Trim(document.MantenedoresForm.txt_RutPerf.value) == "")&& (Trim(document.MantenedoresForm.txt_NNumVerif.value) == "")){
			alert("Debe ingresar N° RUT y su dígito verificador para poder generar la nómina de aportes de afiliado.");	
		}
		else if (((Trim(document.MantenedoresForm.txt_RutPerf.value) == "")&& (Trim(document.MantenedoresForm.txt_NNumVerif.value) != "")) || ((Trim(document.MantenedoresForm.txt_RutPerf.value) != "")&& (Trim(document.MantenedoresForm.txt_NNumVerif.value) == ""))){
			alert("Si ingresa el campo N° RUT debe ingresar el dígito verificador y viceversa.");	
		}
		else if(ValidadorRUT(document.MantenedoresForm.txt_RutPerf.value,document.MantenedoresForm.txt_NNumVerif.value)){
			if (document.MantenedoresForm.dbx_Perfil.value == 0){
				alert("Debe ingresar Perfil");
			}
			else{
				MantenedoresDWR.saveDataPerfiles(rut, perfil, function(data){
					var resp = null;
					resp = data.codRespuesta;
					msgResp = data.msgRespuesta;
					
					// RECARGAR DATOS
            		if (estPerfFiltro == true)
	  					filtraPerfil();
	  				else
	  				   	consultaDatosTabPerf("2");
	  				   	
					alert(msgResp);
				});
				esconderDiv();
			}
		}
	}
	
	function validaCancelarTabPerf(){

		if(document.MantenedoresForm.txt_RutPerf.value != "" ||
		   document.MantenedoresForm.dbx_Perfil.value != 0 ||
		   document.MantenedoresForm.txt_NNumVerif.value != ""){
		
		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}
	
	function validaCancelarTabPerfModif(){
		
		if(document.MantenedoresForm.dbx_Perfil.value != perfilInicial){
		
		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}
	
	function modificarDatoPerfil(){
		var rut = Trim(document.MantenedoresForm.txt_RutPerf.value);
		var perfil = document.MantenedoresForm.dbx_Perfil.value;
		if (document.MantenedoresForm.dbx_Perfil.value == 0){
			alert("Debe ingresar Perfil");
		}
		else{
			MantenedoresDWR.modifDataPerfiles(rut, perfil, perfilInicial, function(data){
				var resp = null;
				resp = data.codRespuesta;
				msgResp = data.msgRespuesta;
				alert(msgResp);
				// RECARGAR DATOS
				if (estPerfFiltro == true)
  					filtraPerfil();
  				else
  				   	consultaDatosTabPerf("2");
			});
			esconderDiv();
		}
	}
	
	function cambiarEstadoPerfil(estado){
	
		var rut = Trim(document.MantenedoresForm.txt_RutPerf.value);
				
		MantenedoresDWR.cambiarEstadoPerfiles(rut, perfilInicial, estado, function(data){
			var resp = null;
			resp = data.codRespuesta;
			msgResp = data.msgRespuesta;
			alert(msgResp);
			// RECARGAR DATOS
            if (estPerfFiltro == true)
				filtraPerfil();
			else
			   	consultaDatosTabPerf("2");
		});
		esconderDiv();
	}
	
	function limpiarPerfil() {
		document.MantenedoresForm.txt_RutPerf.value = "";
		document.MantenedoresForm.txt_NNumVerif.value = "";
		document.MantenedoresForm.dbx_Perfil.value = 0;		
	}

	
	//----------------------- MAntenedor Promotores -----------------------////
		
	function guardarDatoTablaPromotores(){
	
		var rut = document.MantenedoresForm.txt_RutProm.value;
		var numVerif = document.MantenedoresForm.txt_NumVerif.value;
		var apePat = document.MantenedoresForm.txt_ApePat.value;
		var apeMat = document.MantenedoresForm.txt_ApeMat.value;
		var nombres = document.MantenedoresForm.txt_Nombres.value;
		var tipoOrg = document.MantenedoresForm.dbx_TipoOrg.value;
		var tipoCargo = document.MantenedoresForm.dbx_TipoCargo.value;
		var calle = document.MantenedoresForm.txt_Calle.value;
		var codAreaContacto = document.MantenedoresForm.txt_codAreaContacto.value;
		var telContacto = document.MantenedoresForm.txt_TelContacto.value;
		var fecIni = document.MantenedoresForm.txt_FecIni.value;
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";
		
		var alerta = "Falta por completar los siguientes datos:";
		var error = false;
		
		if(ValidadorRUT(rut,numVerif)){		
			 if (rut == ""){
				alerta = (alerta+"\n- Rut");
				error = true;
				}
			 if(numVerif == ""){
					alerta = (alerta+"\n- Dig Verificador");
					error = true;
					}
			 if(apePat == ""){
					alerta = (alerta+"\n- Apellido Paterno");
					error = true;
					}
			 if(apeMat == ""){
					alerta = (alerta+"\n- Apellido Materno");
					error = true;
					}
			 if(nombres == ""){
					alerta = (alerta+"\n- Nombres");
					error = true;
					}
			 if(tipoOrg == 0){
					alerta = (alerta+"\n- Tipo Organizacion");
					error = true;
					}
			 if(tipoCargo == 0){
					alerta = (alerta+"\n- Tipo Cargo");
					error = true;
					}
			 /*if(codAreaContacto == ""){
					alerta = (alerta+"\n- Codigo area");
					error = true;
					}
			 if(telContacto == ""){
					alerta = (alerta+"\n- Telefono");
					error = true;
					}
			*/
			 if(fecIni == ""){
					alerta = (alerta+"\n- Fecha Inicio");
					error = true;
					}
			if (error == true){
			alert(alerta);}
			else if (Comparar_Fecha(fechaSistema, fecIni) == false)

                        {

                                alert("El campo 'Fecha Inicio' no puede ser mayor a la Fecha Actual");

                                return;

                        }

			
			else{			
				MantenedoresDWR.savePromotorsTable(rut, numVerif, apePat, apeMat, nombres, tipoOrg, tipoCargo, calle, codAreaContacto, telContacto, fecIni, function(data){
					var resp = null;
					resp = data.codRespuesta;
					msgResp = data.msgRespuesta;
			
					
					if(resp == 0){
						alert("Los datos fueron guardados correctamente.");
						// RECARGAR DATOS
						if (estPromFiltro == true)
							filtraPromotor();
						else
							consultaDatosTabProm("3");
					}else{
						alert(msgResp);
					}	
				});
				esconderDiv();
			}
		}
	}
	
		function validaCancelarTabProm(){
		
		if(document.MantenedoresForm.txt_RutProm.value != "" ||
		   document.MantenedoresForm.txt_NumVerif.value != "" ||
			document.MantenedoresForm.txt_ApePat.value != "" ||
			document.MantenedoresForm.txt_ApeMat.value != "" ||
			document.MantenedoresForm.txt_Nombres.value != "" ||
			document.MantenedoresForm.dbx_TipoOrg.value != 0 ||
			document.MantenedoresForm.dbx_TipoCargo.value != 0 ||
			document.MantenedoresForm.txt_Calle.value != "" ||
			document.MantenedoresForm.txt_codAreaContacto.value != "" ||
			document.MantenedoresForm.txt_TelContacto.value != "" ||
			document.MantenedoresForm.txt_FecIni.value != "")
		{
		
		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}		
				
	function validaCancelarTabPromModif(){
		if(
			Trim(document.MantenedoresForm.txt_ApePat.value) != Trim(apePatProm) ||
			Trim(document.MantenedoresForm.txt_ApeMat.value) != Trim(apeMatProm) ||
			Trim(document.MantenedoresForm.txt_Nombres.value) != Trim(nombresProm) ||
			document.MantenedoresForm.dbx_TipoOrg.value != tipoOrgInicial ||
			document.MantenedoresForm.dbx_TipoCargo.value != tipoCargoInicial ||
			Trim(document.MantenedoresForm.txt_Calle.value) != Trim(direccionProm) ||
			Trim(document.MantenedoresForm.txt_codAreaContacto.value) != Trim(codAreaProm) ||
			Trim(document.MantenedoresForm.txt_TelContacto.value) != Trim(telefonoProm) ||
			document.MantenedoresForm.txt_FecIni.value != fechaInicioProm
			){
		
		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}
	
	function modificarDatoPromotor(){
	
		var rut = Trim(document.MantenedoresForm.txt_RutProm.value);
		var numVerif = document.MantenedoresForm.txt_NumVerif.value;
		var apePat = document.MantenedoresForm.txt_ApePat.value;
		var apeMat = document.MantenedoresForm.txt_ApeMat.value;
		var nombres = document.MantenedoresForm.txt_Nombres.value;
		var tipoOrg = document.MantenedoresForm.dbx_TipoOrg.value;
		var tipoCargo = document.MantenedoresForm.dbx_TipoCargo.value;
		var calle = document.MantenedoresForm.txt_Calle.value;
		var codAreaContacto = document.MantenedoresForm.txt_codAreaContacto.value;
		var telContacto = document.MantenedoresForm.txt_TelContacto.value;
		var fecIni = document.MantenedoresForm.txt_FecIni.value;
		var fecBaja = document.MantenedoresForm.txt_FecBaja.value;
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";
		
		var alerta = "Falta por completar los siguientes datos:";
		var alertaFechas = "Fecha Inicio debe ser menor a la fecha baja";
		var error = false;
		var errorFechas = false;
		
		if (fecBaja != ""){
			errorFechas = Comparar_Fecha(fecIni,fecBaja);
		}
		
		 if (rut == ""){
			alerta = (alerta+"\n- Rut");
			error = true;
			}
		 if(numVerif == ""){
				alerta = (alerta+"\n- Dig Verificador");
				error = true;
				}
		 if(apePat == ""){
				alerta = (alerta+"\n- Apellido Paterno");
				error = true;
				}
		 if(apeMat == ""){
				alerta = (alerta+"\n- Apellido Materno");
				error = true;
				}
		 if(nombres == ""){
				alerta = (alerta+"\n- Nombres");
				error = true;
				}
		 if(tipoOrg == 0){
				alerta = (alerta+"\n- Tipo Organizacion");
				error = true;
				}
		 if(tipoCargo == 0){
				alerta = (alerta+"\n- Tipo Cargo");
				error = true;
				}
		 /*if(codAreaContacto == ""){
				alerta = (alerta+"\n- Codigo area");
				error = true;
				}
		 if(telContacto == ""){
				alerta = (alerta+"\n- Telefono");
				error = true;
				}
		*/
		if (error == true || errorFechas == true){
			if(error == true)
				alert(alerta);
			if(errorFechas == true)
				alert(alertaFechas);
			}
			
			else if (Comparar_Fecha(fechaSistema, fecIni) == false)

                        {

                                alert("El campo 'Fecha Inicio' no puede ser mayor a la Fecha Actual");

                                return;

                        }

			
			
		else{

			MantenedoresDWR.modifDataPromotor(rut, apePat, apeMat, nombres, tipoOrg, tipoCargo, calle, codAreaContacto, telContacto, fecIni,function(data){
				var resp = null;
				resp = data.codRespuesta;
				msgResp = data.msgRespuesta;
				alert(msgResp);
				
				// RECARGAR DATOS
				if (estPromFiltro == true)
				filtraPromotor();
			else
				consultaDatosTabProm("3");
				
			});
			esconderDiv();
		}
	}
	
		
	function cambiarEstadoPromotor(estado){
	
		var idCaptador = Trim(document.MantenedoresForm.txt_RutProm.value);
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";
		
			MantenedoresDWR.cambiarEstadoPromotor(idCaptador, estado, fechaSistema, function(data){
			var resp = null;
			resp = data.codRespuesta;
			msgResp = data.msgRespuesta;
			alert(msgResp);
			// RECARGAR DATOS
			if (estPromFiltro == true)
				filtraPromotor();
			else
				consultaDatosTabProm("3");
		});
		esconderDiv();
		
	}
	
	function limpiarPromotor() {
	
		document.MantenedoresForm.txt_RutProm.value = "";
		document.MantenedoresForm.txt_NumVerif.value = "";

		document.MantenedoresForm.txt_ApePat.value = "";
		document.MantenedoresForm.txt_ApeMat.value = "";
		document.MantenedoresForm.txt_Nombres.value = "";

		document.MantenedoresForm.dbx_TipoOrg.value = 0;	
		document.MantenedoresForm.dbx_TipoCargo.value = 0;	

		document.MantenedoresForm.txt_Calle.value = "";
		document.MantenedoresForm.txt_codAreaContacto.value = "";
		document.MantenedoresForm.txt_TelContacto.value = "";

		document.MantenedoresForm.txt_FecIni.value = "";
	}
	
	
		//-------------------------- MAntenedor de Tipos de documentos para solicitud --------------------------//	
	
	
		function limpiarTipoDoc() {
		document.MantenedoresForm.txt_GlosaTipoDoc.value = "";
		document.MantenedoresForm.chk_Obligatorio.checked = false;		
	}
		
	function guardarDatoTipoDoc(){
		var glosa = document.MantenedoresForm.txt_GlosaTipoDoc.value;
		var tipoSol = document.MantenedoresForm.dbx_TipoSol.value;
		var obligatorio = 0;
		
		if (document.MantenedoresForm.chk_Obligatorio.checked == true ){
			 obligatorio = 1;
		}
				
		//if (document.MantenedoresForm.txt_GlosaTipoDoc.value == ""){
		//	alert("Debe ingresar Glosa");
		//}
		
		var alerta = "Falta por completar los siguientes datos:";
		var error = false;

		 if (glosa == ""){
			alerta = (alerta+"\n- Glosa");
			error = true;
			}
		 if(tipoSol == 0){
				alerta = (alerta+"\n- Tipo Solicitud");
				error = true;
				}
				
		if (error == true){
				alert(alerta);
			}
		
		else{
			MantenedoresDWR.saveDataTipoDoc(glosa, obligatorio, tipoSol, function(data){
				var resp = null;
				resp = data.codRespuesta;
				msgResp = data.msgRespuesta;
				alert(msgResp);
				// RECARGAR DATOS
				consultaDatosTabTipoDoc("4");
			});
			esconderDiv();
		}
	}
	
		function validaCancelarTipoDoc(){
		
		if(document.MantenedoresForm.txt_GlosaTipoDoc.value != "")
		{

		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
		}
		
		function validaCancelarTipoDocModif(){
		
		if(document.MantenedoresForm.txt_GlosaTipoDoc.value != glosaTipoDoc)
		{

		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
		}
		
		
	
	function modificarDatoTipoDoc(){
		var codigo = document.MantenedoresForm.txt_CodigoTipoDoc.value;
		var glosa = document.MantenedoresForm.txt_GlosaTipoDoc.value;
		var tipoSol = document.MantenedoresForm.dbx_TipoSol.value;
		var obligatorio = 0;

		if (document.MantenedoresForm.chk_Obligatorio.checked == true ){
			 obligatorio = 1;
		}
				
		//if (document.MantenedoresForm.txt_GlosaTipoDoc.value == ""){
		//	alert("Debe ingresar Glosa");
		//}
		
		
		var alerta = "Falta por completar los siguientes datos:";
		var error = false;

		 if (document.MantenedoresForm.txt_GlosaTipoDoc.value == ""){
			alerta = (alerta+"\n- Glosa");
			error = true;
			}
		 if(tipoSol == 0){
				alerta = (alerta+"\n- Tipo Solicitud");
				error = true;
				}
				
		if (error == true){
				alert(alerta);
			}

		else{
			MantenedoresDWR.modifDataTipoDoc(codigo, glosa, obligatorio, tipoSol, function(data){
				var resp = null;
				resp = data.codRespuesta;
				msgResp = data.msgRespuesta;
				alert(msgResp);
				// RECARGAR DATOS
				consultaDatosTabTipoDoc("4");
			});
			esconderDiv();
		}
	}
	
	function cambiarEstadoTipoDoc(estado){
	
		var codigo = document.MantenedoresForm.txt_CodigoTipoDoc.value;
				
		MantenedoresDWR.cambiarEstadoTipoDoc(codigo, estado, function(data){
			var resp = null;
			resp = data.codRespuesta;
			msgResp = data.msgRespuesta;
			alert(msgResp);	
			// RECARGAR DATOS
			consultaDatosTabTipoDoc("4");
		});
		esconderDiv();
	}
	
		//-------------------------- MAntenedor de Beneficios en Dinero --------------------------//
		
		function validaCancelarTabBenDin(){
		
		if(document.MantenedoresForm.txt_GlosaCorta.value != "" ||
			document.MantenedoresForm.txt_Glosa.value != "" ||
			document.MantenedoresForm.txt_CodCont.value != "" ||
			document.MantenedoresForm.dbx_GlosaTipoPagoBen.value != 0 ||
			document.MantenedoresForm.txt_ValPago.value != "" ||
			document.MantenedoresForm.txt_MontPagMax.value != "" ||
			document.MantenedoresForm.txt_Carencia.value != "" ||
			document.MantenedoresForm.txt_Recu.value != "" ||
			document.MantenedoresForm.txt_PlazoCobro.value != "" ||
			document.MantenedoresForm.txt_FecIniVal.value != "" ||
			document.MantenedoresForm.txt_FecFinVal.value != "")
		{

		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
		}
		
		function validaCancelarTabBenDinModif(){
		
		if(document.MantenedoresForm.txt_GlosaCorta.value != glosaCortaBen ||
			document.MantenedoresForm.txt_Glosa.value != glosaBen ||
			document.MantenedoresForm.txt_CodCont.value != codigoContBen ||
			document.MantenedoresForm.dbx_GlosaTipoPagoBen.value != tipoPagoBen ||
			document.MantenedoresForm.txt_ValPago.value != valorPagoBen ||
			document.MantenedoresForm.txt_MontPagMax.value != montoPagarMaxBen ||
			document.MantenedoresForm.txt_Carencia.value != carenciaBen ||
			document.MantenedoresForm.txt_Recu.value != recurrenciaBen ||
			document.MantenedoresForm.txt_PlazoCobro.value != plazoCobroBen ||
			document.MantenedoresForm.txt_FecIniVal.value != fechaInicioBen ||
			document.MantenedoresForm.txt_FecFinVal.value != fechaFinBen)
		{

		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
		}
		
				
	
		function guardarDatoTablaBeneficiosDinero(){
		
		var glosaCorta = document.MantenedoresForm.txt_GlosaCorta.value;
		var glosa = document.MantenedoresForm.txt_Glosa.value;
		var codCont = document.MantenedoresForm.txt_CodCont.value;
		var tipoPago = document.MantenedoresForm.dbx_GlosaTipoPagoBen.value;
		var valPago = document.MantenedoresForm.txt_ValPago.value;
		var montPagMax = document.MantenedoresForm.txt_MontPagMax.value;
		var carencia = document.MantenedoresForm.txt_Carencia.value;
		var recurrencia = document.MantenedoresForm.txt_Recu.value;
		var plazoCobro = document.MantenedoresForm.txt_PlazoCobro.value;
		var fecIniVal = document.MantenedoresForm.txt_FecIniVal.value;
		var fecFinVal = document.MantenedoresForm.txt_FecFinVal.value;
		

		
		var causante = 0;
        if (document.MantenedoresForm.chk_Causante.checked == true ){
                         causante = 1;}
		
		var alerta = "Falta por completar los siguientes datos:";
		var alertaValor = "El valor Pago debe ser entre 0 y 100";
		var alertaCarencia = "Carencia debe ser mayor o igual a 0 Días";
		var alertaFechas = "Fecha Inicio Validéz debe ser menor a la Fecha Fin Validéz";
		var error = false;
		var errorValor = false;
		var errorCarencia = false;
		var errorFechas = false;

		errorFechas = Comparar_Fecha(fecIniVal,fecFinVal);

		if(tipoPago == 2){
			if(valPago>100 || valPago<1){
			errorValor = true;
			}
		}
		
		if(carencia == ""){
			carencia = 0;
		}
		
		if(carencia < 0){
			errorCarencia = true;
		}
		
		 if (glosaCorta == ""){
			alerta = (alerta+"\n- Glosa Corta");
			error = true;
			}
		 if(glosa == ""){
				alerta = (alerta+"\n- Glosa");
				error = true;
				}
		 if(codCont == ""){
				alerta = (alerta+"\n- Codigo Contable");
				error = true;
				}
		 if(tipoPago == 0){
				alerta = (alerta+"\n- Tipo Pago");
				error = true;
				}
		 if(valPago == ""){
				alerta = (alerta+"\n- Valor Pago");
				error = true;
				}
		 if(montPagMax == ""){
				alerta = (alerta+"\n- Monto Pagar Maximo");
				error = true;
				}
		 if(recurrencia == ""){
				alerta = (alerta+"\n- Recurrencia");
				error = true;
				}
		 if(plazoCobro == ""){
				alerta = (alerta+"\n- Plazo Cobro");
				error = true;
				}
		 if(fecIniVal == ""){
				alerta = (alerta+"\n- Fecha Inicio Validez");
				error = true;
				}
		 if(fecFinVal == ""){
				alerta = (alerta+"\n- Fecha Fin Validez");
				error = true;
				}
		if (error == true || errorValor == true || errorCarencia == true || errorFechas == true){
			if(error==true)
				alert(alerta);
			if(errorValor == true)
				alert(alertaValor);
			if(errorCarencia == true)
				alert(alertaCarencia);
			if(errorFechas == true)
				alert(alertaFechas);
			}
		else{
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
		MantenedoresDWR.saveBenefTable(glosaCorta, glosa, codCont, tipoPago, valPago, montPagMax, carencia, recurrencia, plazoCobro, fecIniVal, fecFinVal, causante, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos fueron guardados correctamente.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				// RECARGAR DATOS
				consultaDatosTabBenef("5");
				cargaArreglos();
				
			}else{
				alert("Ocurrió un problema al insertar los datos.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			}	
		});
		esconderDiv();
		}
	}
	
	function cambiarEstadoBenef(estado){
	document.getElementById("pantallaDeCarga").style.visibility = "visible";
		MantenedoresDWR.cambiarEstadoBenef(idBeneficio, estado, function(data){
			var resp = null;
			resp = data.codRespuesta;
			msgResp = data.msgRespuesta;
			alert(msgResp);
			document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			// RECARGAR DATOS
			consultaDatosTabBenef("5");	
		});
		esconderDiv();
	}
	
	function modificarDatoBenef(){
	
		var glosaCorta = document.MantenedoresForm.txt_GlosaCorta.value;
		var glosa = document.MantenedoresForm.txt_Glosa.value;
		var codCont = document.MantenedoresForm.txt_CodCont.value;
		var tipoPago = document.MantenedoresForm.dbx_GlosaTipoPagoBen.value;
		var valPago = document.MantenedoresForm.txt_ValPago.value;
		var montPagMax = document.MantenedoresForm.txt_MontPagMax.value;
		var carencia = document.MantenedoresForm.txt_Carencia.value
		var recurrencia = document.MantenedoresForm.txt_Recu.value
		var plazoCobro = document.MantenedoresForm.txt_PlazoCobro.value;
		var fecIniVal = document.MantenedoresForm.txt_FecIniVal.value;
		var fecFinVal = document.MantenedoresForm.txt_FecFinVal.value;
		
		var causante = 0;
        if (document.MantenedoresForm.chk_Causante.checked == true ){
        	causante = 1;}

		var alerta = "Falta por completar los siguientes datos:";
		var alertaValor = "El valor Pago debe ser entre 1 y 100";
		var alertaCarencia = "Carencia debe ser mayor o igual a 0 Días";
		var alertaFechas = "Fecha Inicio Validéz debe ser menor a la Fecha Fin Validéz";
		var error = false;
		var errorValor = false;
		var errorCarencia = false;
		var errorFechas = false;
			
		errorFechas = Comparar_Fecha(fecIniVal,fecFinVal);

		if(tipoPago == 2){
			if(valPago>100 || valPago<1){
			errorValor = true;
			}
		}
		
		if(carencia == ""){
			carencia = 0;
		}
		
		if(carencia < 0){
			errorCarencia = true;
		}
		
		 if (glosaCorta == ""){
			alerta = (alerta+"\n- Glosa Corta");
			error = true;
			}
		 if(glosa == ""){
				alerta = (alerta+"\n- Glosa");
				error = true;
				}
		 if(codCont == ""){
				alerta = (alerta+"\n- Codigo Contable");
				error = true;
				}
		 if(tipoPago == 0){
				alerta = (alerta+"\n- Tipo Pago");
				error = true;
				}
		 if(valPago == ""){
				alerta = (alerta+"\n- Valor Pago");
				error = true;
				}
		 if(montPagMax == ""){
				alerta = (alerta+"\n- Monto Pagar Maximo");
				error = true;
				}
		 if(recurrencia == ""){
				alerta = (alerta+"\n- Recurrencia");
				error = true;
				}
		 if(plazoCobro == ""){
				alerta = (alerta+"\n- Plazo Cobro");
				error = true;
				}
		 if(fecIniVal == ""){
				alerta = (alerta+"\n- Fecha Inicio Validez");
				error = true;
				}
		 if(fecFinVal == ""){
				alerta = (alerta+"\n- Fecha Fin Validez");
				error = true;
				}
		if (error == true || errorValor == true || errorCarencia == true || errorFechas == true){
			if(error==true)
				alert(alerta);
			if(errorValor == true)
				alert(alertaValor);
			if(errorCarencia == true)
				alert(alertaCarencia);
			if(errorFechas == true)
				alert(alertaFechas);
				}
		else{
			document.getElementById("pantallaDeCarga").style.visibility = "visible";
		MantenedoresDWR.modifDataBenef(idBeneficio, glosaCorta, glosa, codCont, tipoPago, valPago, montPagMax, carencia, recurrencia, plazoCobro, fecIniVal, fecFinVal, causante, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos fueron guardados correctamente.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
				// RECARGAR DATOS
				consultaDatosTabBenef("5");
				cargaArreglos();
			}else{
				alert("Ocurrió un problema al insertar los datos.");
				document.getElementById("pantallaDeCarga").style.visibility = "hidden";
			}	
		});
		esconderDiv();
		}
	}
	
	function limpiarBeneficio() {
	
		document.MantenedoresForm.txt_GlosaCorta.value = "";
		document.MantenedoresForm.txt_Glosa.value = "";

		document.MantenedoresForm.txt_CodCont.value = "";
		document.MantenedoresForm.dbx_GlosaTipoPagoBen.value = 0;
		document.MantenedoresForm.txt_ValPago.value = "";

		document.MantenedoresForm.txt_MontPagMax.value = "";
		document.MantenedoresForm.txt_Carencia.value = "";
		document.MantenedoresForm.txt_Recu.value = "";

		document.MantenedoresForm.txt_PlazoCobro.value = "";
		document.MantenedoresForm.txt_FecIniVal.value = "";
		document.MantenedoresForm.txt_FecFinVal.value = "";
		document.MantenedoresForm.chk_Causante.checked = false;		
	}
	
	//-------------------------- MAntenedor de Documentos de Beneficios en Dinero --------------------------//
	
	function guardarDatoTablaDocBenefDin(){
		
		var glosaCorta = document.MantenedoresForm.txt_GlosaCortaDocBenDin.value;
		var glosa = document.MantenedoresForm.txt_GlosaDocBenDin.value;
		
		var alerta = "Falta por completar los siguientes datos:";
		var error = false;
		
		 if (glosaCorta == ""){
			alerta = (alerta+"\n- Glosa Corta");
			error = true;
			}
		 if(glosa == ""){
				alerta = (alerta+"\n- Glosa");
				error = true;
				}
		if (error == true){
		alert(alerta);}
		else{
		
		MantenedoresDWR.saveDocBenefDinTable(glosaCorta, glosa, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos fueron guardados correctamente.");
				// RECARGAR DATOS
				consultaDatosTabDocsBenefsDin("6");
				cargaArreglos();
			}else{
				alert("Ocurrió un problema al insertar los datos.");
			}	
		});

		esconderDiv();
		}
	}		
	
	function validaCancelarTabDocBenDin(){
		
		if(document.MantenedoresForm.txt_GlosaCortaDocBenDin.value != "" ||
		   document.MantenedoresForm.txt_GlosaDocBenDin.value  != "")
		{

		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}
				
		
		function validaCancelarTabDocBenDinModif(){
		
			if(document.MantenedoresForm.txt_GlosaCortaDocBenDin.value != glosaCortaDocBenDin ||
			   document.MantenedoresForm.txt_GlosaDocBenDin.value  != glosaDocBenDin)
			{
	
			var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
				if(respuesta == true){
					esconderDiv();
				}
			}
			else{
			esconderDiv();
			}
		}
	
	function cambiarEstadoDocBenDin(estado){
	
	var idDocBenDin = document.MantenedoresForm.txt_IdDocBenDin.value;
	
		MantenedoresDWR.cambiarEstadoDocBenDin(idDocBenDin, estado, function(data){
			var resp = null;
			resp = data.codRespuesta;
			msgResp = data.msgRespuesta;
			alert(msgResp);	
			// RECARGAR DATOS
			consultaDatosTabDocsBenefsDin("6");
		});
		esconderDiv();
	}
	
	function modificarDatoDocBenDin(){
	
		var glosaCorta = document.MantenedoresForm.txt_GlosaCortaDocBenDin.value;
		var glosa = document.MantenedoresForm.txt_GlosaDocBenDin.value;
		var idDocBenDin = document.MantenedoresForm.txt_IdDocBenDin.value;
		
		var alerta = "Falta por completar los siguientes datos:";
		var error = false;
		
		 if (glosaCorta == ""){
			alerta = (alerta+"\n- Glosa Corta");
			error = true;
			}
		 if(glosa == ""){
				alerta = (alerta+"\n- Glosa");
				error = true;
				}
		if (error == true){
		alert(alerta);}
		else{
			
		MantenedoresDWR.modifDataDocBenDin(idDocBenDin, glosaCorta, glosa, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos fueron guardados correctamente.");
				// RECARGAR DATOS
				consultaDatosTabDocsBenefsDin("6");
				cargaArreglos();
			}else{
				alert("Ocurrió un problema al insertar los datos.");
			}	
		});
		esconderDiv();
		}
	}
	
	function limpiarDocBenDin(){	
		document.MantenedoresForm.txt_GlosaCortaDocBenDin.value = "";
		document.MantenedoresForm.txt_GlosaDocBenDin.value = "";		
	}
	
	
	//-------------------------- Mantenedor de Documentos Requeridos para Beneficios en Dinero --------------------------//

	function guardarDatoTablaBenDinDoc(){
		
		var beneficio = document.MantenedoresForm.dbx_BeneficioBenDinDoc.value;
		var documento = document.MantenedoresForm.dbx_DocumentoBenDinDoc.value;

		
		var obligatorio = 0;
		if (document.MantenedoresForm.chk_ObligatorioBenDinDoc.checked == true ){
                         obligatorio = 1;
                }

		var alerta = "Falta por completar los siguientes datos:";
		var error = false;
				
		 if (beneficio == "" || beneficio == 0){
			alerta = (alerta+"\n- Beneficio");
			error = true;
			}
		 if(documento == "" || documento == 0){
				alerta = (alerta+"\n- Documento");
				error = true;
				}
		if (error == true){
		alert(alerta);}
		else{
			
		MantenedoresDWR.saveBenDinDocTable(beneficio, documento, obligatorio, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos fueron guardados correctamente.");
				// RECARGAR DATOS
				consultaDatosTabRelBenefsDinDocsBenefs("7");
			}else{
				alert("Ocurrió un problema al insertar los datos.");
			}	
		});
		esconderDiv();
		}
	}
	
	function validaCancelarTabRelBenDoc(){
		
		if(document.MantenedoresForm.dbx_BeneficioBenDinDoc.value != 0 ||
		   document.MantenedoresForm.dbx_DocumentoBenDinDoc.value != 0)
		{

		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}
	
	function validaCancelarTabRelBenDocModif(){
		
		if(document.MantenedoresForm.dbx_BeneficioBenDinDoc.value != beneficioRelBenDoc ||
		   document.MantenedoresForm.dbx_DocumentoBenDinDoc.value != documentoRelBenDoc)
		{

		var respuesta = confirm("Ha ingresado información en el formulario. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				esconderDiv();
			}
		}
		else{
		esconderDiv();
		}
	}
	
	function cambiarEstadoBenDinDoc(estado){

		var idBenDinDoc = document.MantenedoresForm.txt_IdBenDinDoc.value;

		MantenedoresDWR.cambiarEstadoBenDinDoc(idBenDinDoc, estado, function(data){
			var resp = null;
			resp = data.codRespuesta;
			msgResp = data.msgRespuesta;
			alert(msgResp);	
			// RECARGAR DATOS
			consultaDatosTabRelBenefsDinDocsBenefs("7");
		});
		esconderDiv();
	}
	
	
	function modificarDatoBenDinDoc(){
	
		var idBenDinDoc = document.MantenedoresForm.txt_IdBenDinDoc.value;
		var beneficio = document.MantenedoresForm.dbx_BeneficioBenDinDoc.value;
		var documento = document.MantenedoresForm.dbx_DocumentoBenDinDoc.value;
		var obligatorio = 0;
		if (document.MantenedoresForm.chk_ObligatorioBenDinDoc.checked == true ){
                         obligatorio = 1;
                }
		
		var alerta = "Falta por completar los siguientes datos:";
		var error = false;
		
		 if (beneficio == "" || beneficio == 0){
			alerta = (alerta+"\n- Beneficio");
			error = true;
			}
		 if(documento == "" || documento == 0){
				alerta = (alerta+"\n- Documento");
				error = true;
				}
		if (error == true){
		alert(alerta);}
		else{
			
		MantenedoresDWR.modifDataBenDinDoc(idBenDinDoc, beneficio, documento, obligatorio, function(data){
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Los datos fueron guardados correctamente.");
				// RECARGAR DATOS
				consultaDatosTabRelBenefsDinDocsBenefs("7");
			}else{
				alert("Ocurrió un problema al insertar los datos.");
			}	
		});
		esconderDiv();
		}
	}
	
	
	function limpiarBenDinDoc(){
		document.MantenedoresForm.dbx_BeneficioBenDinDoc.value = 0;
		document.MantenedoresForm.dbx_DocumentoBenDinDoc.value = 0;
		document.MantenedoresForm.chk_ObligatorioBenDinDoc.checked = false;
	}

	
	function ListaMantenedores(num){
	
	switch (num){
			
	
			case "0":
						alert("Seleccione un mantenedor");
						break;
			case "1":
						paginaActual = 1;
						consultaDatosTabGlob(num);
						botonIng = document.getElementById("btn_Ingresar");
						botonIng.disabled = false;
						break;
			case "2":
						paginaActual = 1;
						consultaDatosTabPerf(num);
						botonIng = document.getElementById("btn_Ingresar");
						botonIng.disabled = false;
						break;
			case "3":
						paginaActual = 1;
						consultaDatosTabProm(num);
						botonIng = document.getElementById("btn_Ingresar");
						botonIng.disabled = false;
						break;
			case "4":
						paginaActual = 1;
						consultaDatosTabTipoDoc(num);
						botonIng = document.getElementById("btn_Ingresar");
						botonIng.disabled = false;
						break;
			case "5":
						paginaActual = 1;
						consultaDatosTabBenef(num);
						botonIng = document.getElementById("btn_Ingresar");
						botonIng.disabled = false;
						break;
			case "6":
						paginaActual = 1;
						consultaDatosTabDocsBenefsDin(num);
						botonIng = document.getElementById("btn_Ingresar");
						botonIng.disabled = false;
						break;			
			case "7":
						paginaActual = 1;
						consultaDatosTabRelBenefsDinDocsBenefs(num);
						botonIng = document.getElementById("btn_Ingresar");
						botonIng.disabled = false;
						break;			
			default:
						alert("opcion no existente");
						break;
			}
		}
</script>

</head>
<body>
<html:form action="/Mantenedores.do">
	<div id="caja_registro"><input type="hidden" name="opcion"
		value="0"> <input type="hidden" name="archivo" value="">
	<input type="hidden" name="dbx_MantenedorTmp" value="0">
	<input type="hidden" name="dbx_EstSolicitudTmp" value="0">
	<br>
	<h4><b>MANTENEDORES</b></h4>

	<table width="970" border="0">
		<tr>
			<td colspan="3" align="right"><a href="#" align="right"
				onclick="enviaFormulario(0)">Volver</a> &nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onclick="enviaFormulario(-1)">Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>			
			<td > <!-- height="34"  -->
				<b>Mantenedor</b>
				&nbsp;&nbsp;
				<html:select property="dbx_Mantenedor" styleClass="dbx_mantenedor" value="0">
					<html:option value="0">Seleccione Mantenedor</html:option>
					<html:options collection="ListMantenedores" property="codigo" labelProperty="glosa"/>
				</html:select> 
			</td>
				<td width="408">	
				<input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onclick="ListaMantenedores(dbx_Mantenedor.value);"/>
				</td>
		</tr>
	</table>

	<div id="datosNomina"
		style="position:static; margin-top: 0px; margin-left: 0px; width: 970px; height: 300px;">
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

	<div id="datosPaginacion"
		style="position:static; margin-top: 0px; margin-left: 0px; width: 970px; height: 30px;">
	</div>
	
	<div id="fondoNegroDetalles" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 520px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
 		<div id="datosDetalleError" style="display: none; position: absolute; width: 650px; margin-top: 110px; margin-left: 300px; background-color: #F2F2F2">
		</div>
		<div id="datosDetalleErrorGrande" style="display: none; position: absolute; width: 950px; height: 250px; margin-top: 110px; margin-left: 50px; background-color: #F2F2F2">
		</div>
	</div>

	<table width="970" border="0">
		<tr>
			<td height="50" align="right" width="968">
			<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"	value="Cancelar" onClick="enviaFormulario(0)" />
			&nbsp;&nbsp;&nbsp;
			<input type="button" name="btn_Ingresar" id="btn_Ingresar" class="btn_limp" disabled="true" value="Nuevo" onClick="nuevoRegistro(dbx_Mantenedor.value)" />
			&nbsp;&nbsp;&nbsp;
		</td>
	</table>
	
	
	</div>
</html:form>
</body>
</html:html>