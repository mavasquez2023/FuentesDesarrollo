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

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/util.js"></script>

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/SolBeneficiosDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"	src="/IndependientesWEB/dwr/interface/RepNominaApoAfiDWR.js"></script>

<script type="text/javascript">

	//--- INI Grilla ---
	
	var carga = 0;
	var datos = new Array();
	var arregloBeneficios = new Array();
	var arregloBeneficiosSel = new Array();
	var arregloDocs = new Array();
	
	var estadoAfiliado = -1;
	var estadoAporte = -1;
	
	var registrarTercero = -1;
	var rutTercero = -1;
	var nombreTercero = " ";
	//REQ6988 JLGN 11-03-2013
	var dvTercero = -1;
	var montoTotal = 0;
	
	cargaArreglos();
	
	function ObjBeneficio (idBeneficio, glosaBeneficio, glosaCortaBeneficio, codigoContable, estadoBeneficio, tipoBeneficio, strTipoBeneficio, valorPorTipo, montoMaximo, carencia, maxSolicitudes, plazoCobro, fechaIniValidez, strFechaIniValidez, fechaFinValidez, trFechaFinValidez, isCausanteUnico, listaDocumentosBeneficio)
  	{
	  	this.idBeneficio				=	idBeneficio;
	  	this.glosaBeneficio				=	glosaBeneficio;
	  	this.glosaCortaBeneficio		=	glosaCortaBeneficio;
	  	this.codigoContable				=	codigoContable;
	  	this.estadoBeneficio			=	estadoBeneficio;
	  	this.tipoBeneficio				=	tipoBeneficio;
	  	this.strTipoBeneficio			=	strTipoBeneficio;
	  	this.valorPorTipo				=	valorPorTipo;
	  	this.montoMaximo				=	montoMaximo;
	  	this.carencia					=	carencia;
	  	this.maxSolicitudes				=	maxSolicitudes;
	  	this.plazoCobro					=	plazoCobro;
	  	this.fechaIniValidez			=	fechaIniValidez;
	  	this.strFechaIniValidez			=	strFechaIniValidez;
	  	this.fechaFinValidez			=	fechaFinValidez;
	  	this.trFechaFinValidez			=	trFechaFinValidez;
	  	this.isCausanteUnico			=	isCausanteUnico;
	  	this.listaDocumentosBeneficio	=	listaDocumentosBeneficio;
	}
	
	function ObjBeneficioSel (idBeneficio, glosaCortaBeneficio, valorCopago, montoReembolsar, rutCausante, dvCausante, nombreCausante, fechaCausante, listaDocumentosBeneficio)
	{
		this.idBeneficio 				=	idBeneficio;
	  	this.glosaCortaBeneficio 		=	glosaCortaBeneficio;
	  	this.valorCopago				=	valorCopago;
	  	this.montoReembolsar			=	montoReembolsar;
	  	this.rutCausante				=	rutCausante;
	  	this.dvCausante					=	dvCausante;
	  	this.nombreCausante				=	nombreCausante;
	  	this.fechaCausante				=	fechaCausante;
	  	this.listaDocumentosBeneficio	=	listaDocumentosBeneficio;
	}
	
	function cargaArreglos()
	{
		GeograficoDWR.obtenerListaBeneficios("ListBeneficioFull", function(data)
		{
			var beneficios = null
			beneficios = data;
			
			for (i = 0; i < beneficios.length; i++){
			
				arregloBeneficios[i] = new ObjBeneficio(
					beneficios[i].idBeneficio, 
					beneficios[i].glosaBeneficio,
					beneficios[i].glosaCortaBeneficio,
					beneficios[i].codigoContable,
					beneficios[i].estadoBeneficio,
					beneficios[i].tipoBeneficio,
					beneficios[i].strTipoBeneficio,
					beneficios[i].valorPorTipo,
					beneficios[i].montoMaximo,
					beneficios[i].carencia,
					beneficios[i].maxSolicitudes,
					beneficios[i].plazoCobro,
					beneficios[i].fechaIniValidez,
					beneficios[i].strFechaIniValidez,
					beneficios[i].fechaFinValidez,
					beneficios[i].trFechaFinValidez,
					beneficios[i].isCausanteUnico,
					copiaArreglo(beneficios[i].listaDocumentosBeneficio)
				);
			}
		});
	}
	
	function cargaCabeceras()
	{
		document.getElementById("beneficiosSeleccionados").innerHTML = cargaCabeceraSel();
	}
	
	function cargaCabeceraSel()
	{
		return	'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Beneficio</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Copago</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Reembolso</td>'+
	            	'<td height="20" width="120" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT Causante</td>'+
	            	'<td height="20" width="200" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Nombre Causante</td>'+
	            	'<td height="20" width="110" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Causante</td>'+
	            	'<td height="20" width="89" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Agregar</td>'+
	        	'</tr>';
	}

	//--- INI Se puebla la lista de beneficios disponibles ---
	function cargaTablaList()
	{
		var contenidoTabla = "";
		var footer = "";
		
		for(var i = 0; i < arregloBeneficios.length; i++)
		{
			contenidoTabla = contenidoTabla + obtenerFilaTablaList(arregloBeneficios[i], i);
		}
		
		footer = "<tr><td height='" + (355 - (25 * (arregloBeneficios.length + 1))) + "'></td></tr></table>"+
			"<table border='1' rules='groups' width='740'><tr><td align='right'>"+
			"<input type='button' name='btn_DocCancelar' id='btn_DocCancelar' class='btn_limp' value='Cancelar' onclick='cancelarSelBeneficio();'/>"+
			"</td></tr></table></td></tr></table>";
		document.getElementById("listadoBeneficios").innerHTML = "";
		document.getElementById("listadoBeneficios").innerHTML = cargaCabeceraList() + contenidoTabla + footer;
	}

	function cargarTablaDoc(k)
	{
		var contenidoTabla = "";
		arregloDocs = null;
		arregloDocs = arregloBeneficiosSel[k].listaDocumentosBeneficio;
	
		contenidoTabla = "<table><tr><td align='center'><strong>Registro de Documentación</strong></td></tr><tr><td><table border='1' rules='groups'>"+
				"<tr><td width='50'></td><td width='200'><strong>Beneficio</strong></td><td width='475'>" + arregloBeneficiosSel[k].glosaCortaBeneficio + "</td>"+
				"</tr><tr><td></td><td><strong>N° RUT:</strong></td><td>" + separadorDeMiles(document.SolBeneficiosForm.txt_NRut.value) + "-" + document.SolBeneficiosForm.txt_NNumVerif.value + "</td></tr><tr><td></td><td>"+
				"<strong>Nombre:</strong></td><td>" + document.SolBeneficiosForm.txt_nombreAfiliado.value + "</td></tr></table>"+
				"<table width='100%' align='center' cellpadding='0' cellspacing='1' height='300'>"+
				"<tr>" +
	            	"<td height='20' width='10' bgcolor='#1C74A9' class='textobold' style='color: #fff; text-align: center'></td>"+
	            	"<td height='20' width='200' bgcolor='#1C74A9' class='textobold' style='color: #fff; text-align: center'>Tipo Documento</td>"+
	            	"<td height='20' width='150' bgcolor='#1C74A9' class='textobold' style='color: #fff; text-align: center'>Recibidos</td>"+
	        	"</tr>";
	
		for(var j = 0; j < arregloDocs.length; j++)
		{
			var casillaChk = "";
			var asterisco = "";
		
			if(arregloDocs[j].isObligatorio == 1)
			{
				asterisco = " *";
			}
			
			if(arregloDocs[j].estadoDocBeneficio == 1)
			{
				casillaChk = "checked='true' "; 
			}
			
			contenidoTabla += "<tr> "+
							"<td height='20'></td>"+
							"<td height='20' class='texto' align='center' >" + arregloDocs[j].glosaDocBeneficio + asterisco + "</td>" +
							"<td height='20' align='center' ><input type='checkbox' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " />" + "</td>" +
						"</tr>";
		}
		
		contenidoTabla += "<tr><td height='" + (300 - (25 * (arregloDocs.length + 1))) + "'></td></tr></table>" +
			"<table border='1' rules='groups' width='740'><tr><td align='right'>" +
			"<input type='button' name='btn_DocCancelar' id='btn_DocCancelar' class='btn_limp' value='Cancelar' onclick='validaDocCancelar();'/>"+
			"&nbsp;&nbsp;&nbsp;"+
			"<input type='button' name='btn_DocGuardar' id='btn_DocGuardar' class='btn_limp' value='Guardar' onclick='guardarDocumentos(" + k + ");'/>"+
			"</td></tr></table></td></tr></table>";
		
		document.getElementById("listadoDocBeneficios").innerHTML = "";
		document.getElementById("listadoDocBeneficios").innerHTML = contenidoTabla;
		
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("listadoDocBeneficios").style.visibility = "visible";
	}

	function validaDocCancelar()
	{
		var largo = arregloDocs.length;
		var cambios = false;
		var chk;
		
		for(var i = 0; i < largo; i++)
		{
			if(largo == 1)
			{
				chk = document.getElementById('chk_CheckGrid');
			
				if(chk.checked != (arregloDocs[i].estadoDocBeneficio == 1))
				{
					cambios = true;
				}
			}else{
				if(document.SolBeneficiosForm.chk_CheckGrid[i].checked != (arregloDocs[i].estadoDocBeneficio == 1))
				{
					cambios = true;
				}
			}	
		}

		if(cambios)
		{
			var respuesta = confirm("Ha ingresado información en el check-list de Documentos. ¿Está seguro que desea continuar?");
					
			if( respuesta == true){
				document.getElementById("listadoDocBeneficios").style.visibility = "hidden";
				document.getElementById("fondoNegro").style.visibility = "hidden";
			}
		}else{		
			document.getElementById("listadoDocBeneficios").style.visibility = "hidden";
			document.getElementById("fondoNegro").style.visibility = "hidden";
		}	
	}

	function guardarDocumentos(k)
	{
		var largo = arregloDocs.length;
			
		for(var i = 0; i < largo; i++)
		{
			if(largo == 1){
			
				if(document.SolBeneficiosForm.chk_CheckGrid.checked == true){
				
					arregloDocs[i].estadoDocBeneficio = 1;
				}else{
					arregloDocs[i].estadoDocBeneficio = 0;
				}
			}else{
				if(document.SolBeneficiosForm.chk_CheckGrid[i].checked == true)
				{
					arregloDocs[i].estadoDocBeneficio = 1;
				}else{
					arregloDocs[i].estadoDocBeneficio = 0;
				}	
			}
		}
		
		arregloBeneficiosSel[k].listaDocumentosBeneficio = arregloDocs;
		
		arregloDocs = null;
			
		document.getElementById("listadoDocBeneficios").style.visibility = "hidden";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}

	function cargaCabeceraList()
	{
		return	'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
					'<td colspan="7" align="center"><strong>Seleccione un Beneficio</strong></td>'+
				'</tr>'+
				'<tr>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Beneficio</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Tipo</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Valor Tipo</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Recurrencia</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto Máximo</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Agregar</td>'+
	        	'</tr>';
	}

	function obtenerFilaTablaList(dato, i)
	{
		var valorPorTipo = "";
	
		if(dato.tipoBeneficio == 1){
			valorPorTipo = "$" + separadorDeMiles(dato.valorPorTipo);
		}else{
			valorPorTipo = dato.valorPorTipo + "%";
		}
	
		var texto = " <tr> "+
						"<td height='20'></td>"+
						"<td height='20' class='texto' align='center'>" + dato.glosaCortaBeneficio + "</td>"+
						"<td height='20' class='texto' align='center'>" + dato.strTipoBeneficio + "</td>"+
						"<td height='20' class='texto' align='center'>" + valorPorTipo + "</td>"+
						"<td height='20' class='texto' align='center'>" + dato.maxSolicitudes + "</td>"+
						"<td height='20' class='texto' align='center'>" + dato.montoMaximo + "</td>"+
						"<td height='20' class='texto' align='center'>" + "<input type='button' name='btn_Seleccionar' id='btn_Seleccionar' class='btn_limp' value='Agregar' onclick='agregarBeneficioALista(" + i + ")'></td>"+
					"</tr>";
							
		return texto;
	}
	
	//--- FIN Se puebla la lista de beneficios disponibles ---
	
	function cancelarSelBeneficio()
	{
		document.getElementById("fondoNegro").style.visibility = "hidden";
		document.getElementById("listadoBeneficios").style.visibility = "hidden";
	}
	
	function agregarBeneficioALista(index)
	{
		if(arregloBeneficios[index].tipoBeneficio == 2)
		{
			var ben = new ObjBeneficioSel(arregloBeneficios[index].idBeneficio, arregloBeneficios[index].glosaCortaBeneficio, 0, 0, "", "", "", "", copiaArreglo(arregloBeneficios[index].listaDocumentosBeneficio));
		}else{
			var ben = new ObjBeneficioSel(arregloBeneficios[index].idBeneficio, arregloBeneficios[index].glosaCortaBeneficio, 0, arregloBeneficios[index].valorPorTipo, "", "", "", "", copiaArreglo(arregloBeneficios[index].listaDocumentosBeneficio));
			
			document.SolBeneficiosForm.txt_montoReembolsar.value = parseInt(document.SolBeneficiosForm.txt_montoReembolsar.value) + parseInt(arregloBeneficios[index].valorPorTipo);
			montoTotal = montoTotal + parseInt(arregloBeneficios[index].valorPorTipo);
		}
		
		arregloBeneficiosSel.push(ben);
		
		document.getElementById("fondoNegro").style.visibility = "hidden";
		document.getElementById("listadoBeneficios").style.visibility = "hidden";
		
		pintarListaSel();
	}
	
	function eliminarBeneficio(index)
	{
		var arregloBeneficiosSelAux = new Array();
		var j = 0;
		var sumatoria = 0;
		for (var i = 0; i < arregloBeneficiosSel.length; i++)
		{
			if (index != i)
			{
				arregloBeneficiosSelAux[j] = arregloBeneficiosSel[i];
				if(arregloBeneficios[index].tipoBeneficio == 2){
					sumatoria = sumatoria + parseInt(arregloBeneficiosSel[i].valorCopago);
				}
				else{
					sumatoria = sumatoria + parseInt(arregloBeneficiosSel[i].montoReembolsar);
				}
				j++;
			}
		}
		
		arregloBeneficiosSel = null;
		arregloBeneficiosSel = arregloBeneficiosSelAux;
		document.SolBeneficiosForm.txt_montoReembolsar.value = sumatoria;
		montoTotal = sumatoria;
		pintarListaSel();	
	}
	
	function pintarListaSel()
	{
		var txtCalendario = "";
		var txtNombre = "";
		var txtCopago = "";
		var txtNombreCausante = "";
		
		document.getElementById("beneficiosSeleccionados").innerHTML = "";
		document.getElementById("beneficiosSeleccionados").innerHTML = cargaCabeceraSel();
		
		var texto = document.getElementById("beneficiosSeleccionados").innerHTML;
		var largo = document.getElementById("beneficiosSeleccionados").innerHTML.length;
		
		texto = texto.substring(0, largo-16);
		
		for (var k = 0; k < arregloBeneficiosSel.length; k++)
		{
			var beneficio = obtenerBeneficio(arregloBeneficiosSel[k].idBeneficio);
		
			if(arregloBeneficiosSel.length == 1){
				txtCalendario = "onClick='ShowCalendarFor(txt_FecCarencia); registraCambioMatriz(8, " + k + ", document.SolBeneficiosForm.txt_FecCarencia.value ); ' ";
			}else{
				txtCalendario = "onClick='ShowCalendarFor(txt_FecCarencia[" + k + "]); registraCambioMatriz(8, " + k + ", document.SolBeneficiosForm.txt_FecCarencia[" + k + "].value ); ' ";
			}
		
			if(arregloBeneficiosSel[k].rutCausante == document.SolBeneficiosForm.txt_NRut.value && arregloBeneficiosSel[k].dvCausante == document.SolBeneficiosForm.txt_NNumVerif.value){
			
				txtNombreCausante = document.SolBeneficiosForm.txt_nombreAfiliado.value;
				txtNombreCausante = txtNombreCausante.substring(0,29);
				txtNombre = "disabled='true' value='" + txtNombreCausante + "'";
				arregloBeneficiosSel[k].nombreCausante = txtNombreCausante;
				//txtNombre = "disabled='true' value='" + document.SolBeneficiosForm.txt_nombreAfiliado.value + "'";
				//arregloBeneficiosSel[k].nombreCausante = document.SolBeneficiosForm.txt_nombreAfiliado.value;
				
			}else{
				txtNombre = "value='" + arregloBeneficiosSel[k].nombreCausante +"'";
			}
		
			if(beneficio.tipoBeneficio == 1)
			{
				txtCopago = "disabled='true' ";
			}else{
				txtCopago = "enabled='true' ";
			}
		
			texto = texto + " <tr> " +
						"<td height='20' width='10' ></td>" +
						"<td height='20' width='80' class='texto' align='center'>" + arregloBeneficiosSel[k].glosaCortaBeneficio + "</td>" +
						"<td height='20' width='80' class='texto' align='center'>" + "<input style='width: 60px' type='text' name='txt_VlrCopago' id='txt_VlrCopago' maxlength='10' onkeypress='Upper(this,\"N\")' onblur='registraCambioMatriz(3, "+ k +", this.value )' value='" + arregloBeneficiosSel[k].valorCopago + "' " + txtCopago + ">" + "</td>" +
						"<td height='20' width='80' class='texto' align='center'>" + "<input style='width: 60px' type='text' name='txt_MntReembolso' id='txt_MntReembolso' disabled='true' maxlength='10' onkeypress='Upper(this,\"N\")' onblur='registraCambioMatriz(4, "+ k +", this.value )' value='" + arregloBeneficiosSel[k].montoReembolsar + "'>" + "</td>" +
						"<td height='20' width='120' class='texto' align='center'>" + "<input style='width: 90px' type='text' name='txt_RutCausante' id='txt_RutCausante' maxlength='10' onkeypress='Upper(this,\"N\")' onblur='registraCambioMatriz(5, "+ k +", this.value )' value='" + arregloBeneficiosSel[k].rutCausante + "'> - <input style='width: 20px' type='text' name='txt_DivCausante' id='txt_DivCausante' maxlength='1' onkeypress='Upper(this,\"D\")' onblur='registraCambioMatriz(6, "+ k +", this.value )' value='" + arregloBeneficiosSel[k].dvCausante + "'>" + "</td>" +
						"<td height='20' width='200' class='texto' align='center'>" + "<input style='width: 250px' type='text' name='txt_NomCausante' id='txt_NomCausante' maxlength='29' onkeypress='Upper(this,\"L\")' onblur='registraCambioMatriz(7, "+ k +", this.value )' " + txtNombre + " >" + "</td>" +
						"<td height='20' width='110' class='texto' align='center'>" + 
						"<input style='width: 90px' type='text' name='txt_FecCarencia' id='txt_FecCarencia' disabled='true' maxlength='8' value='" + arregloBeneficiosSel[k].fechaCausante + "'> "+
						"<IMG style='cursor:hand' border='0' src='/IndependientesWEB/images/Calendar.jpg' width='16' height='16' " + txtCalendario + ">" + "</td>" +
						"<td height='20' width='89' class='texto' align='center'>" + "<IMG style='cursor:hand' border='0' src='/IndependientesWEB/images/icono_editar_nomina.gif' width='16' height='16' onClick='cargarTablaDoc(" + k + ")' >" + "<input size='20' type='button' name='btn_Eliminar' id='btn_Eliminar' class='btn_limp' value='Elim.' onclick='eliminarBeneficio(" + k + ")'></td>"+
					"</tr>";
		}
		
		texto = texto + "</tbody></table>";
					
		document.getElementById("beneficiosSeleccionados").innerHTML = texto;
	}
	
	function cargaRegistroTercero()
	{
		var texto = "";
		
		document.getElementById("registroTercero").innerHTML = texto;
	
		texto = '<table width="100%" align="center" cellpadding="0" cellspacing="1" >'+
				'<tr style="height:25px" valign="middle" >'+
					'<td colspan="7" align="center" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Registro de Tercero para Cobros</td>'+
				'</tr>'+
		'</table>'+
	    '<table width="100%" align="center" cellpadding="0" cellspacing="1" >'+
	    		'<tr>'+
	            	'<td width="50px" heigth="40">&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	        	'</tr>'+
	    		'<tr>'+
	            	'<td ></td>'+
	            	'<td ><strong>RUT</strong></td>'+
	            	'<td ><input type="text" name="txt_NRutTerc" id="txt_NRutTerc" size="10" maxlength="9" onkeypress="Upper(this,\'N\')" />'+
	            	'-'+
	            	'<input type="text" name="txt_NNumVerifTerc" id="txt_NNumVerifTerc" size="3" maxlength="1" onkeypress="Upper(this,\'D\')" onchange="ValidadorRUT(document.SolBeneficiosForm.txt_NRutTerc.value,this.value)"/></td>'+
	            	'<td ></td>'+
	        	'</tr>'+
	        	'<tr>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	        	'</tr>'+
	        	'<tr>'+
	            	'<td ></td>'+
	            	'<td ><strong>Nombre</strong></td>'+
	            	'<td ><input type="text" name="txt_nombreTercero" id="txt_nombreTercero" size="40" maxlength="30" onkeypress="Upper(this,\'L\')" /></td>'+
	            	'<td ></td>'+
	        	'</tr>'+
	        	'<tr>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	            	'<td >&nbsp;</td>'+
	        	'</tr>'+
	    '</table>'+
	    '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
	    		'<tr align="right">'+
	            	'<td colspan="3">'+
	            	'<input type="button" name="btn_CancelarTerc" id="btn_CancelarTerc" class="btn_limp" value="Cancelar" onclick="cancelarTercero();">'+
			    	'&nbsp;&nbsp;&nbsp;'+
			    	'<input type="button" name="btn_GuardarTerc" id="btn_GuardarTerc" class="btn_limp" value="Guardar" onclick="guardarTercero();">'+
			    	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
	            	'</td>'+
	            	'<td width="50px">&nbsp;</td>'+
   	        	'</tr>'+
	    '</table>';
	
		document.getElementById("registroTercero").innerHTML = texto;

		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("registroTercero").style.visibility = "visible";		
	}
	
	function cancelarTercero(){
	
		var cancelar=confirm("¿Esta seguro que desea cancelar el registro de un tercero para cobros?");
		
		if (cancelar == true)
		{
		  	document.getElementById("fondoNegro").style.visibility = "hidden";
			document.getElementById("registroTercero").style.visibility = "hidden";
		  	
		  	rutTercero = 0;
			nombreTercero = " ";
			//REQ6988 JLGN 11-03-2013
			dvTercero = 0;
			registrarSolicitudBeneficios();
	  	}
	}
	
	function guardarTercero(){
		//REQ 6988 JLGN 08-04-2013
		//Se agrega validacion de datos obligatorios cuando se ingresa un tercero
		if(validacionDatosTercero()){
			if(ValidadorRUT(document.SolBeneficiosForm.txt_NRutTerc.value,document.SolBeneficiosForm.txt_NNumVerifTerc.value)){	
				var guardar=confirm("¿Esta seguro que desear guardar estos datos de un Tercero para cobros?");
				if (guardar == true)
				{
				  	document.getElementById("fondoNegro").style.visibility = "hidden";
					document.getElementById("registroTercero").style.visibility = "hidden";
					
					rutTercero = document.SolBeneficiosForm.txt_NRutTerc.value;
					nombreTercero = document.SolBeneficiosForm.txt_nombreTercero.value;
					//REQ6988 JLGN 11-03-2013
					dvTercero = document.SolBeneficiosForm.txt_NNumVerifTerc.value;
					
					registrarSolicitudBeneficios();
				}
		  	}
		}	
	}
	
	//--- FIN Grilla ---
   
	function asignaValor(a)
	{
		document.SolBeneficiosForm.opcion.value = a;
	}

	function enviaFormulario(a)
	{
		asignaValor(a);
		document.SolBeneficiosForm.submit();
	}
	
	//--- Negocio ---
	
	function registraCambioMatriz(col, index, valor){
	
		/* Tipos para Columna
		idBeneficio			= 	1
	  	glosaCortaBeneficio	= 	2
	  	valorCopago			=	3
	  	montoReembolsar		=	4
	  	rutCausante			=	5
	  	dvCausante			=	6
	  	nombreCausante		=	7
	  	fechaCausante		=	8
		*/
	
		switch(col){
					
			case 1:	arregloBeneficiosSel[index].idBeneficio 		= valor; break;
			case 2:	arregloBeneficiosSel[index].glosaCortaBeneficio	= valor; break;
			case 3:	arregloBeneficiosSel[index].valorCopago 		= valor; calculaMontoReembolso(index); break;
			case 4:	arregloBeneficiosSel[index].montoReembolsar 	= valor; break;
			case 5:	arregloBeneficiosSel[index].rutCausante 		= valor; 
					
					if(validaRutMatriz(index)){
						break;
					}
					
					pintarListaSel();
					
					break;
			case 6:	arregloBeneficiosSel[index].dvCausante 			= valor; 
					
					if(validaRutMatriz(index)){
						break;
					} 
					
					pintarListaSel();
					
					break;
			case 7:	arregloBeneficiosSel[index].nombreCausante 		= valor; break;
			case 8:	arregloBeneficiosSel[index].fechaCausante 		= valor; break;
			
			default : alert("Ha ocurrido un error al registrar el cambio.");
		}	
	}
	
	function obtenerBeneficio(id)
	{
		for(var i = 0; i < arregloBeneficios.length; i++){
		
			if(arregloBeneficios[i].idBeneficio == id){
			
				return arregloBeneficios[i];
			}	
		}
	}
	
	function calculaMontoReembolso(index)
	{
		var largo = arregloBeneficiosSel.length;
		
		var idBen = arregloBeneficiosSel[index].idBeneficio;
		var copago = arregloBeneficiosSel[index].valorCopago;
		var beneficio = obtenerBeneficio(idBen);
		var tipoBen = beneficio.tipoBeneficio;
		var valorTipo = beneficio.valorPorTipo; //Cual de estas dos uso para calcular cuando es monto fijo?
		var pagoMax = beneficio.montoMaximo;	//Cual de estas dos uso para calcular cuando es monto fijo?
		var aux = 0;
		var respuesta = 0;
		var sumatoria = 0;
		
		copago = eliminaCerosIZQ(copago);
		
		if(copago != "" && copago != 0 && copago != 0){
	
			if (tipoBen == 1){
				if(copago < pagoMax){
					respuesta = copago;
				}else{
					respuesta = pagoMax;
				}
			}else{
				aux = Math.round(copago * (valorTipo / 100));
				
				if(aux < pagoMax){
					respuesta = aux;
				}else{
					respuesta = pagoMax;
				}
			}
		}
		
		//Registramos lo obtenido
		arregloBeneficiosSel[index].montoReembolsar = respuesta;
		
		if(largo == 1)
		{
			document.SolBeneficiosForm.txt_VlrCopago.value = copago;
			document.SolBeneficiosForm.txt_MntReembolso.value = respuesta;
		}else{
			document.SolBeneficiosForm.txt_VlrCopago[index].value = copago;
			document.SolBeneficiosForm.txt_MntReembolso[index].value = respuesta;
		}
		
		//Realizar la sumatoria
		for(var i = 0; i < arregloBeneficiosSel.length; i++)
		{
			sumatoria += parseInt(arregloBeneficiosSel[i].montoReembolsar);
		}
		document.SolBeneficiosForm.txt_montoReembolsar.value = sumatoria;
		montoTotal = sumatoria;
	}
	
	function validaRutMatriz(index){
	
		var rut = Trim(arregloBeneficiosSel[index].rutCausante);
		var dv  = Trim(arregloBeneficiosSel[index].dvCausante);
		var largo = arregloBeneficiosSel.length;
	
		if (rut != "" && dv != "")
		{
			if (!validaRut(rut, dv))
			{
				alert("El RUT ingresado en la fila " + (index + 1) + " es inválido");
				arregloBeneficiosSel[index].dvCausante = "";
			
				if(largo == 1){
					document.SolBeneficiosForm.txt_DivCausante.value = "";
				}else{
					document.SolBeneficiosForm.txt_DivCausante[index].value = "";
				}
			}
		}
	}
	
	function validaSolicitarPago(){
		//REQ 6988 JLGN 11-03-2013
		var montoMaximoBeneficio = '<%=session.getAttribute("MontoMaxBeneficio")%>';
		for(var i = 0; i < arregloBeneficiosSel.length; i++){
		
			if (!validacionesGeneralesBasicas(arregloBeneficiosSel[i], i)){
				return false;
			}
			
			if (!validacionesGeneralesAvanzadas(obtenerBeneficio(arregloBeneficiosSel[i].idBeneficio), arregloBeneficiosSel[i],  i)){
				return false;
			}	
		}
		
		/*REQ 6988 JLGN 11-03-2013
		if (montoTotal > 100000){
			alert("El Total a Reembolsar supera el monto máximo de $100.000. Debe generar alguno/s de los beneficios ingresados en otro comprobante.");
			return false;
		}*/
		if (montoTotal > montoMaximoBeneficio){
			alert("El Total a Reembolsar supera el monto máximo de $"+montoMaximoBeneficio+". Debe generar alguno/s de los beneficios ingresados en otro comprobante.");
			return false;
		}
		
		if(arregloBeneficiosSel.length==0){
			alert("Debe ingresar al menos un beneficio para la generación del comprobante.");
			return false;
		}
		return true;
	}
	
	function validacionesGeneralesBasicas(beneficioSel, i){
	
		var beneficio = obtenerBeneficio(beneficioSel.idBeneficio);
	
		if( (Trim("" + beneficioSel.valorCopago) == "0" || Trim("" + beneficioSel.valorCopago) == "") && beneficio.tipoBeneficio == 2){
			alert("No ha ingresado un valor de copago en la fila " + (i + 1) + ". Debe ingresar un valor.");
			return false;
		}
		if(beneficioSel.dvCausante == ""){
			alert("No ha ingresado un RUT en la fila " + (i + 1) +". Debe ingresar un RUT.");
			return false;
		}
		if(Trim(beneficioSel.nombreCausante) == ""){
			alert("No ha ingresado un nombre de causante en la fila " + (i + 1) + ". Debe ingresar un nombre.");
			return false;
		}
		if(beneficioSel.fechaCausante == ""){
			alert("No ha ingresado una fecha causante en la fila " + (i + 1) + ". Debe ingresar una fecha.");
			return false;
		}
		
		if(!Comparar_Fecha('<%=session.getAttribute("FechaSistema")%>', beneficioSel.fechaCausante)){
			alert("La fecha causante de la fila " + (i + 1) + " no puede ser posterior al dia de hoy. Ingrese una fecha previa.");
			return false;
		}
		if(!validarDocumentos(beneficioSel.listaDocumentosBeneficio, i)){
			return false;
		}
		
		return true;
	}
	
	function validarDocumentos(documentos, index)
	{
		for(var i = 0; i < documentos.length; i++)
		{
			if(documentos[i].isObligatorio == 1 && documentos[i].estadoDocBeneficio == 0)
			{
				alert("El documento '" + documentos[i].glosaDocBeneficio + "' del beneficio " + (index + 1) + " es obligatorio. Si no lo ha recibido, el beneficio no puede ser otorgado.");
				return false;
			}
		}
		
		return true;
	}
	
	function obtenerDiferenciaEnDias(fechaIni, fechaFin){
	
		var diferencia = 0;
	
		DWREngine.setAsync(false);
		SolBeneficiosDWR.obtenerDiferenciaEnDias(fechaIni, fechaFin, function(data)
		{
			diferencia = data;
		});
		DWREngine.setAsync(true);
		
		return diferencia;
	}
	
	function validacionesGeneralesAvanzadas(beneficio, beneficioSel, i)
	{
		var diferenciaEnDias = 0;
		var cantRecurrencia = 0;
		var cantRecCausanteUnico = 0;
	
		if(beneficio.carencia > document.SolBeneficiosForm.txt_diasAfiliado.value){
			alert("El beneficio de la fila " + (i + 1) + " requiere que el afiliado tenga " + beneficio.carencia + " días como afiliado. Elimine este Beneficio.");
			return false;
		}
		
		diferenciaEnDias = obtenerDiferenciaEnDias(beneficioSel.fechaCausante, '<%=session.getAttribute("FechaSistema")%>');
		
		if(diferenciaEnDias > beneficio.plazoCobro){
			alert("El beneficio de la fila " + (i + 1) + " ha exedido los " + beneficio.plazoCobro + " días disponibles para solicitar el Beneficio. Elimine este Beneficio.");
			return false;
		}
		
		cantRecurrencia = obtenerNumeroRecurrencia(beneficio.idBeneficio);
		
		if(cantRecurrencia > beneficio.maxSolicitudes){
			alert("El beneficio de la linea " + (i + 1) + " excede la cantidad de veces que puede ser solicitado este año. Elimine este beneficio.");
			return false;
		}
		
		if(beneficio.isCausanteUnico == 1){
			
			cantRecCausanteUnico = obtenerNumeroCausanteUnico(beneficio.idBeneficio, beneficioSel.rutCausante);
		
			if (cantRecCausanteUnico > 1){
				alert("El beneficio de la linea " + (i + 1) + " solo puede ser solicitado una vez para ese causante y ya ha sido solicitado");
				return false;
			}
		}
		
		return true;
	}
	
	function obtenerNumeroCausanteUnico(id, rutCausante){
	
		var montoQuery = 0;
		var sumaArreglo = 0;
		
		idAfi = Trim(document.SolBeneficiosForm.txt_idPersonaAfiliado.value);
		
		for(var k = 0; k < arregloBeneficiosSel.length; k++){
		
			if (arregloBeneficiosSel[k].idBeneficio == id && arregloBeneficiosSel[k].rutCausante == rutCausante){
				sumaArreglo++;
			}
		}
		
		DWREngine.setAsync(false);
		SolBeneficiosDWR.obtenerNumeroCausanteUnico(id, idAfi, rutCausante, function(data)
		{
			montoQuery = data;
		});
		DWREngine.setAsync(true);
		
		return montoQuery + sumaArreglo;
	}
	
	function obtenerNumeroRecurrencia(id){ 
		
		var montoQuery = 0;
		var sumaArreglo = 0;
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		
		idAfi = Trim(document.SolBeneficiosForm.txt_idPersonaAfiliado.value);
		
		for(var k = 0; k < arregloBeneficiosSel.length; k++){
		
			if (arregloBeneficiosSel[k].idBeneficio == id){
				sumaArreglo++;
			}
		}
		
		DWREngine.setAsync(false);
		SolBeneficiosDWR.obtenerNumeroRecurrencia(id, idAfi, fechaSistema, function(data)
		{
			montoQuery = data;
		});
		DWREngine.setAsync(true);
		
		return montoQuery + sumaArreglo;
	}
	
	function solicitarPago()
	{
		if(validaSolicitarPago())
		{
			var tercero=confirm("¿Desea registrar un Tercero para que realice los cobros asociados?");
			if (tercero == true)
			{
			  	registrarTercero = 1;
			  	cargaRegistroTercero();
		  	}else
			{
				registrarTercero = 0;
				
				registrarSolicitudBeneficios();
			}
		}	
	}
	
	function registrarSolicitudBeneficios(){
		
		var respuesta = "";
		var cadenaRegistro = "#";
		var documentos;
		
		//alert("Antes del for");
		for(var i = 0; i < arregloBeneficiosSel.length; i++)
		{
			cadenaRegistro += document.SolBeneficiosForm.txt_idPersonaAfiliado.value + "|"; // 01
			cadenaRegistro += arregloBeneficiosSel[i].idBeneficio + "|"; // 02
			cadenaRegistro += arregloBeneficiosSel[i].nombreCausante + "|"; // 03
			cadenaRegistro += arregloBeneficiosSel[i].rutCausante + "|"; // 04
			cadenaRegistro += arregloBeneficiosSel[i].fechaCausante + "|"; // 05
			cadenaRegistro += arregloBeneficiosSel[i].valorCopago + "|"; // 06
			cadenaRegistro += arregloBeneficiosSel[i].montoReembolsar + "|"; // 07
			//REQ6988 JLGN 11-03-2013
			//cadenaRegistro += rutTercero + "|"; // RUT Tercero  // 08
			cadenaRegistro += rutTercero + "%" + dvTercero + "|"; // RUT Tercero  // 08
			cadenaRegistro += nombreTercero + "|"; //09
			cadenaRegistro += "<%=session.getAttribute("IDAnalista")%>" + "|"; //10
			
			documentos = arregloBeneficiosSel[i].listaDocumentosBeneficio;
			
			cadenaRegistro += "$"; //En caso de no haber documentos, no generar cadena vacia
			
			for (var j = 0; j < documentos.length; j++)
			{
				cadenaRegistro += documentos[j].idDocBeneficio + "%";
				cadenaRegistro += documentos[j].estadoDocBeneficio + "%";
				
				cadenaRegistro += "$"; //11
			}
			
			cadenaRegistro += "|#";
		}
		//alert("despues del for");
		//alert("Cadena = " + cadenaRegistro);
		
		DWREngine.setAsync(false);
		SolBeneficiosDWR.registraBeneficios(cadenaRegistro, function(data)
		{
			respuesta = data;		
		});
		DWREngine.setAsync(true);
		
		//alert("respuesta = " + respuesta.codError);

		if(respuesta.codError == 0)
		{
		alert(respuesta.desError);
			limpiarListaBeneficiosSel();
			
			botonAgregar = document.getElementById("btn_Agregar");
			botonPagar = document.getElementById("btn_Pago");
		
		
			botonAgregar.disabled = true;
			botonPagar.disabled = true;		
		}else{
			alert("Error = " + respuesta.desError);
		}
	}
	
	function agregarBeneficio()
	{
		if (carga == 0)
		{
			cargaTablaList();
			carga = 1;
		}
		
		if(arregloBeneficiosSel.length >= 4){
			alert("El máximo de beneficios por comprobante es 4.");
			return false;
		}
		
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("listadoBeneficios").style.visibility = "visible";
		
		return true;
	}
	
	function limpiarListaBeneficiosSel()
	{
		arregloBeneficiosSel = new Array();
		cargaCabeceras();
	}

	function buscarPorRUT()
	{
		var rut = Trim(document.SolBeneficiosForm.txt_NRut.value);
		var dv  = Trim(document.SolBeneficiosForm.txt_NNumVerif.value);

		estadoAfiliado = -1;
		estadoAporte = -1;
		
		if (rut == "" || dv == "")
		{
			alert("Para buscar un Afiliado debe ingresar valores para los campos N° RUT y digito verificador");
			return false; //Se utiliza solo para salir de la ejecución.
		}
		
		limpiarFormulario();
		
		if (validaRut(rut, dv))
		{
			
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
			
			SolBeneficiosDWR.obtenerDatosAfiliado(rut, function(data){
			
				var afiliado = null;
				afiliado = data;
				
				if(afiliado.idPersonaAfiliado != 0){
					document.SolBeneficiosForm.txt_estadoAfiliado.value = afiliado.glosaTipoestadoAfiliado;
					document.SolBeneficiosForm.txt_nombreAfiliado.value = afiliado.nombres + " " + afiliado.apellidoPaterno + " " + afiliado.apellidoMaterno;
					document.SolBeneficiosForm.txt_fechaVigencia.value = afiliado.fechaVigencia;
					
					if(afiliado.totalDias < 0){
						document.SolBeneficiosForm.txt_diasAfiliado.value = 0;
					}else{
						document.SolBeneficiosForm.txt_diasAfiliado.value = afiliado.totalDias;
					}
					
					document.SolBeneficiosForm.txt_estadoAporte.value = afiliado.glosaEstadoAporte;
					
					estadoAporte = afiliado.estadoAporte;
					estadoAfiliado = afiliado.tipoEstadoAfiliado;
					
					document.SolBeneficiosForm.txt_idPersonaAfiliado.value = afiliado.idPersonaAfiliado;
					
					bloqueaDesbloqueaBotones(estadoAfiliado,estadoAporte, afiliado.fechaVigencia);
					montoTotal = 0;
				}
				else{
					limpiarFormulario();
					alert("No existe el afiliado");
				}
			});
		
		}else
		{
			alert("El RUT ingresado es invalido.");
		}
	}
		
	//--- Perfilamiento ---
	
	function limpiarFormulario()
	{
		document.SolBeneficiosForm.txt_estadoAfiliado.value = "";
		document.SolBeneficiosForm.txt_nombreAfiliado.value = "";
		document.SolBeneficiosForm.txt_fechaVigencia.value = "";
		document.SolBeneficiosForm.txt_diasAfiliado.value = "";
		document.SolBeneficiosForm.txt_montoReembolsar.value = "0";
		document.SolBeneficiosForm.txt_estadoAporte.value = "";
		document.SolBeneficiosForm.txt_idPersonaAfiliado.value = "";
		
		limpiarListaBeneficiosSel();
		
		botonAgregar = document.getElementById("btn_Agregar");
		botonPagar = document.getElementById("btn_Pago");
		
		botonAgregar.disabled = true;
		botonPagar.disabled = true;
	}
	
	function bloqueaDesbloqueaBotones(estadoAfiliado, estadoAporte, fechaVigencia)
	{
		var botonAgregar = null;
		var botonPagar = null;
		
		var estadoFecha = 1;
		
		botonAgregar = document.getElementById("btn_Agregar");
		botonPagar = document.getElementById("btn_Pago");
		
		botonAgregar.disabled = false;
		botonPagar.disabled = false;
		
		if(Comparar_Fecha(fechaVigencia, '<%=session.getAttribute("FechaSistema")%>'))
		{
			estadoFecha = 0
			alert("Afiliado aún no está vigente.\n\nVigente a partir de " + fechaVigencia + ".");
		}
		
		if(estadoAporte != 1 && estadoAporte != 0)
		{
			botonAgregar.disabled = true;
			botonPagar.disabled = true;
		}else{
			botonAgregar.disabled = false;
			botonPagar.disabled = false;
		}
	}
	
	//Inicio REQ 6988 JLGN 08-04-2013
	function validacionDatosTercero(){	
		if(document.SolBeneficiosForm.txt_NRutTerc.value == "" || document.SolBeneficiosForm.txt_NNumVerifTerc.value == ""){
			alert("Debe ingresar RUT del Tercero.");
			return false;
		}
	
		if(document.SolBeneficiosForm.txt_nombreTercero.value == ""){
			alert("Debe ingresar Nombre del Tercero.");
			return false;
		}
		return true;	
	}
	//Fin REQ 6988 JLGN 08-04-2013
	
</script>
</head>
<body onload="cargaCabeceras();">
<html:form action="/solBeneficios.do">
<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="txt_idPersonaAfiliado" value="0">
	
	<h4><b>BENEFICIOS</b></h4>
	<table width="970" border="0">
		<tr>
			<td align="right" width="970" colspan="4">
				<a href="#" align="right" onClick="enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);">Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
	</table>
	
	<table width="970" border="0">
		<tr>
			<td width="100" colspan="2">
			    <strong>N° RUT *</strong> 
			    <input type="text" name="txt_NRut" id="txt_NRut" size="10" maxlength="9" onkeypress="Upper(this,'N')"/>
			    <strong> - </strong> 
				<input type="text" name="txt_NNumVerif" id="txt_NNumVerif" size="3" maxlength="1" onkeypress="Upper(this,'D')" onchange="ValidadorRUT(document.SolBeneficiosForm.txt_NRut.value,this.value)"/> 
				<input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onClick="buscarPorRUT();" />
			</td>
			<td>
				<strong>Estado Afiliado</strong>
			</td>
			<td width="150" colspan="2">
				<html:text property="txt_estadoAfiliado" styleClass="txt_estadoAfiliado" disabled="true" size="50" value="" />
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td width="100">
				<p>Nombre Afiliado</p>
			</td>
			<td colspan="2">
				<html:text property="txt_nombreAfiliado" styleClass="txt_nombreAfiliado" disabled="true" size="50" value="" />
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td width="150">
				<p>Fecha Vigencia</p>
			</td>
			<td width="150">	
				<html:text property="txt_fechaVigencia" styleClass="txt_fechaVigencia" disabled="true" size="15" value="" />
			</td>
			<td width="150">
				<p>Total días Afiliado</p>
			</td>
			<td width="150">	
				<html:text property="txt_diasAfiliado" styleClass="txt_diasAfiliado" disabled="true" size="15" value="" />
			</td>
		</tr>
		<tr>
			<td width="150">
				<p>Total a Reembolsar</p>
			</td>
			<td width="150">	
				<html:text property="txt_montoReembolsar" styleClass="txt_montoReembolsar" disabled="true" size="15" value="0" />
			</td>
			<td width="150">
				<p>Estado Aporte</p>
			</td>
			<td width="150">	
				<html:text property="txt_estadoAporte" styleClass="txt_estadoAporte" disabled="true" size="15" value="" />
			</td>
			<td>
				<strong>SIAGF</strong>
				<a href="http://siagf.paperless.cl:8081/siagf/" target="_blank">
 					<img src="/IndependientesWEB/images/open_new_window.png" border="0"/>
				</a>
			</td>
		</tr>
	</table>
	
	<table width="970" border="0">	
		<tr>
			<td align="right" width="968">
				<input type="button" name="btn_Agregar" id="btn_Agregar" class="btn_limp" value="Agregar" disabled="true" onclick="agregarBeneficio();">&nbsp;
			    &nbsp;&nbsp;&nbsp;</td>
			<td>
		</tr>
	</table>
	
	<div id="beneficiosSeleccionados" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 225px; padding: 0px;">
	</div>
	
	<table width="970" border="0">	
		<tr>
			<td height="50" align="right" width="968">
				<input type="button" name="btn_Pago" id="btn_Pago" class="btn_limp" value="Pago" disabled="true" onclick="solicitarPago();">&nbsp; 			
			    &nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
	
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 1350px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
	
		<div id="listadoBeneficios" style="visibility:hidden; position:absolute; margin-top: 100px; margin-left: 180px; width: 750px; height: 395px; background-color: #FFFFFF">
		</div>
	
		<div id="listadoDocBeneficios" style="visibility:hidden; position:absolute; margin-top: 100px; margin-left: 180px; width: 750px; height: 395px; background-color: #FFFFFF">
		</div>
	
		<div id="registroTercero" style="visibility:hidden; position:absolute; margin-top: 250px; margin-left: 300px; width: 450px; height: 150px; background-color: #FFFFFF">
		</div>
	
	</div>
	
</div>
</html:form>
</body>
</html:html>