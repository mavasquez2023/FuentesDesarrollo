<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/ConsModMasivaSolDWR.js"></script>

<script type="text/javascript">
	
	//--- Implementación de la grilla que muestra los datos requeridos. ---
	//variables globales. 
	var datos = new Array();
	var cantidadRegistrosPorPagina = 10;
	var paginaActual = 1;
	var arregloPerfiles = null;
	
	//para guardar los datos seleccionados (folio, rut, flag).
	var arrayCheck = new Array();
	
	/*Objeto solicitud para llenar el arreglo.*/
	function ObjSolicitud(folio, rut, flagCheck){
		this.folio = folio;
		this.rut = rut;
		this.flagCheck = flagCheck;
	}
	
	/*Funcion que sera llamada por el onChange del ckechbox*/
	function cambiaFlagCheck(folio){

		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].folio == folio)
			{
				if(arrayCheck[i].flagCheck == 0)
				{
					arrayCheck[i].flagCheck = 1;
				}else{
					arrayCheck[i].flagCheck = 0;
				}
			}
		}		
	}	
	
	/*Función que carga los datos en la grilla.*/
	function cargarDatosEnGrilla(){
		var contenidoTabla = "";
	
		for(var i=0;i<datos.length;i++){
			if(i < cantidadRegistrosPorPagina)
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		
		generarPaginacion();
	}

	function obtenerFilaTabla(dato){
	
		var casillaChk = "";
		
		for(i=0; i<datos.length; i++)
		{
			if(dato.folio == arrayCheck[i].folio)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}
		var texto =  " <tr> "+
							"<td>"+"<input type='checkbox' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.folio + ");' />"+"</td>"+
							"<td class='texto' align='center'>"+dato.fechaIngreso+"</td>"+
							"<td class='texto' align='center'>"+dato.folio+"</td>"+
							"<td class='texto' align='center'>"+dato.rut+"</td>"+
							"<td class='texto' align='center'>"+dato.apellidoPaterno+"</td>"+
							"<td class='texto' align='center'>"+dato.apellidoMaterno+"</td>"+
							"<td class='texto' align='center'>"+dato.nombres+"</td>"+
							"<td class='texto' align='center'>"+dato.oficina+"</td>"+
							"<td class='texto' align='center'>"+dato.estadoSolicitud+"</td>"+
					 "</tr>";
							
		return texto;
	}
	
	/*Se construye el encabezado de la grilla*/
	function obtenerHeaderTabla(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Ingreso</td>'+			            	
	            	'<td height="20" width="60" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Folio</td>'+	            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT</td>'+
	            	'<td height="20" width="150" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Paterno</td>'+			            	
	            	'<td height="20" width="150" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Materno</td>'+			            	
	            	'<td height="20" width="300" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Nombres</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Oficina</td>'+			            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Est. Solicitud</td>'+         		            	
	        	'</tr>';
	}

	/*Función que genera la paginación*/
	function generarPaginacion(){
		var paginas = (datos.length/cantidadRegistrosPorPagina)+"";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
				
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" Solicitud(es)</font>";		
		for(var i=0; i<paginas;i++){
			texto = texto +" "+ "<a href='#' onclick='paginarResultados("+(i+1)+");'>"+(i + 1)+"</a>" ;
		}

		document.getElementById('datosPaginacion').innerHTML = "<table><tr></tr></table><table><tr></tr></table><table><tr></tr></table><table><tr></tr></table><table><tr></tr><tr><td align='center'>" + texto + '</td></tr></table>';
	}

	/*Función que realiza el paginado.*/
	function paginarResultados(pagina){
	
		var inicio = (pagina-1)*cantidadRegistrosPorPagina;
		var fin = (pagina)*cantidadRegistrosPorPagina;
		document.getElementById("datosNomina").innerHTML = "";
		var contenidoTabla = "";
		
		paginaActual = pagina;

		for(var i=inicio;i<fin;i++){
			if(datos[i]!= null)
				var contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
			else
				i = fin;
		}		
		//document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "<tr><td colspan='10' id='paginacion' align='right'>Paginacion</td></tr></table>";
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(paginas);
	}

	/*Funcion que carga la cabecera de los datos que serán cargados en la grilla. Como la grilla es un div oculto es necesario mostrar la cabecera al entrar
	a la pantalla*/
	function cargaCabecera(){
	
		document.getElementById("datosNomina").innerHTML = 	
			'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Ingreso</td>'+			            	
	            	'<td height="20" width="60" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Folio</td>'+	            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT</td>'+
	            	'<td height="20" width="150" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Paterno</td>'+			            	
	            	'<td height="20" width="150" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Materno</td>'+			            	
	            	'<td height="20" width="300" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Nombres</td>'+
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Oficina</td>'+			            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Est. Solicitud</td>'+          		            	    	
	        	'</tr>'+
	        '</table>';
	}
	
	function asignaValor(a){

		document.ConsModMasSolForm.opcion.value = a;
	}
	
	function enviaFormulario(a){
	
		asignaValor(a);
		document.ConsModMasSolForm.submit();
	}
	
	/*Función que bloquea campos dependiendo del perfil del analista que se registró en el sistema*/
	function bloqueaCampos(){
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		
		//if ('<%=session.getAttribute("Perfil")%>' == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles)){

			document.ConsModMasSolForm.dbx_EstSolicitud.disabled = true;
		}
		
		$('#txt_FechaDesde').each(function(){
		    $(this).datepicker({
			      showOn: "button",
			      buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			      buttonImageOnly: true,
			      buttonText: "Seleccionar fecha",
			      onSelect: function(dateText) {
				    aniadeFechaHasta();
				  }
			});
		});
		
	}
	/*Funcion dwr. Trae los datos desde el procedimiento almacenado, para ser mostrados en la grilla.*/
	function consultaModificacionSolicitudDWR(){
				
		if(document.ConsModMasSolForm.txt_FechaDesde.value != ""){

				var fechaFin = aniadeFechaHasta();
					
		}else{
			
			alert("Debe ingresar una fecha de inicio para realizar una búsqueda.");
			return false;
		}	
		
		//var fechaFin = aniadeFechaHasta();
		var numberMes = fechaFin.substring(3,5);
		var countAnio1 = "12";
		var anioInicioDosDigitos1 = parseInt(fechaFin.substring(8,10));
		var j = parseInt(countAnio1);
		
		while( j <= anioInicioDosDigitos1 ){
			  
			 if(j == anioInicioDosDigitos1 ){
				
				j.toString();
		
				if(numberMes == "01"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/01/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "02"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/02/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "03"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/03/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "04"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/04/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "05"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/05/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "06"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/06/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "07"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/07/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "08"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/08/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "09"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/09/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "10"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/10/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "11"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/11/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
				
				if(numberMes == "12"){
					document.ConsModMasSolForm.txt_FechaDesde.value = "01/12/20"+j;
					fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
				}
			}
			j++;
		}	
				
				/*Asignar valor a estado de la solicitud actual y validar que siempre se seleccione un estado.*/		
				var estadoSolicitud = document.ConsModMasSolForm.dbx_EstSolicitud.value;//1;
				
				if(document.ConsModMasSolForm.dbx_EstSolicitud.value == 0){
					alert("Error, Debe ingresar un estado válido.");
					return false;
				}
				
				var user = "<%=session.getAttribute("IDAnalista")%>";
				var fechaActual = "<%=session.getAttribute("FechaSistema")%>";
				//antes de realizar la consulta, se limpia nuevamente el arreglo.
				arrayCheck = new Array();
				
				ConsModMasivaSolDWR.consultaMasivaSolicitudes(fechaInicio, fechaFin, estadoSolicitud, user, fechaActual, function(data){
					
					datos = data.lisConsModMasivaSol;
					
					document.getElementById("datosNomina").innerHTML = "";
					
					if(datos != null){
					
						//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
						for(p=0; p<datos.length; p++){
							arrayCheck[p] = new ObjSolicitud(datos[p].folio, datos[p].rut, 0);
						}						
						
						/*for(i=0; i<arrayCheck.length; i++){
							alert(arrayCheck[i].folio + " / " + arrayCheck[i].rut + " / " + arrayCheck[i].flagCheck);
						}*/
							
						cargarDatosEnGrilla();
						
						//Busqueda de listado para obtener posibles estados dado un estado actual
						
							ConsModMasivaSolDWR.getEstadosDestinoPosibles(estadoSolicitud, function(data)
							{
								var resp = null;
								resp = data;
								
								var cod = "0";
								var txt = "";
										
								var cmb = document.ConsModMasSolForm.dbx_EstSolicitudTemp;
								cmb.options.length = 0;
								cmb.options[0] = new Option("Seleccione..." , "0");
				
								if(document.ConsModMasSolForm.dbx_EstSolicitudAux.value == 5)
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
					}
				});
		//Se reinicia el valor de la pagina actual
		paginaActual = 1;
				
		/*Bloqueo de  seleccionar todo si no esta en los estados que corresponde*/
		if( document.ConsModMasSolForm.dbx_EstSolicitud.value == 3 || 
			document.ConsModMasSolForm.dbx_EstSolicitud.value == 5 || 
			document.ConsModMasSolForm.dbx_EstSolicitud.value == 6 || 
			document.ConsModMasSolForm.dbx_EstSolicitud.value == 7 || 
			document.ConsModMasSolForm.dbx_EstSolicitud.value == 8
		  ){
			
				document.getElementById("chbx_activado").disabled = true;
				document.getElementById("chbx_desactivado").disabled = true;

		}else{
			document.getElementById("chbx_activado").disabled = false;
			document.getElementById("chbx_desactivado").disabled = false;
		}
	}
	
	/*Funcion DWR que captura los datos de un checkbox seleccionado y con ellos realiza una consulta para captar el id
	de la solicitud, con el cual es posible efectuar el cambio de estado haciendo un update a la tabla solicitud*/
	function retornaCkeckbox()
	{
		var tipoEstadoSolicitud = document.ConsModMasSolForm.dbx_EstSolicitudTemp.value;
		var chkSeleccion = false;
		
		if(	tipoEstadoSolicitud != 0)
		{
			if(datos.length > 1)
			{
				for(i=0; i<datos.length; i++)
				{
					//if(document.ConsModMasSolForm.chk_CheckGrid[i].checked == true)
					if(arrayCheck[i].flagCheck == 1)
					{
						chkSeleccion = true;
						
						//cambiaFlagCheck(datos[i].folio);
						
						//var rutModif = datos[i].rut;
						var rutModif = arrayCheck[i].rut;
						//Estas dos lineas formatean el rut, la primera le quita los puntos y la segunda el guion.
						rutModif = rutModif.replace(/[.]/g, "");
						rutModif = rutModif.replace(/[-]/g, "");
						rutModif = rutModif.substring(0,rutModif.length-1);
						
						
						//Se updatea el tipoEstadoSolicitud pasando como parametro el idSolicitud consultado anteriormente.
						//ConsModMasivaSolDWR.updateMasivaSolicitud(datos[i].folio, tipoEstadoSolicitud, rutModif, function(data){});
						ConsModMasivaSolDWR.updateMasivaSolicitud(arrayCheck[i].folio, tipoEstadoSolicitud, rutModif, function(data){});
					}
				}
				if(chkSeleccion)
				{
					alert("Los datos seleccionados fueron modificados.");
					consultaModificacionSolicitudDWR();
				}else
				{
					alert("Debe seleccionar al menos una solicitud para modificar");
				}
			}else if(datos.length == 1){
			
				if(document.ConsModMasSolForm.chk_CheckGrid.checked == true){
					
					//Se updatea el tipoEstadoSolicitud pasando como parametro el idSolicitud consultado anteriormente.
					var rutModif = datos[0].rut;
					
					//Estas dos lineas formatean el rut, la primera le quita los puntos y la segunda el guion.
					rutModif = rutModif.replace(/[.]/g, "");
					rutModif = rutModif.replace(/[-]/g, "");
					rutModif = rutModif.substring(0,rutModif.length-1);
					
					ConsModMasivaSolDWR.updateMasivaSolicitud(datos[0].folio, tipoEstadoSolicitud, rutModif, function(data){});
					alert("Los datos seleccionados fueron modificados.");
					consultaModificacionSolicitudDWR();
				}else{
					alert("Para modificar, debe seleccionar la solicitud desplegada");
				}
			}
		}else{
		
			alert("Debe ingresar un Estado de Solicitud para modificar de forma masiva.");
		}			
	}
	
	
	/*Función que selecciona todos los checkbox de la grilla.*/
	function seleccionarTodosCheckBox()
	{
		var j = 0;
		var inicioTodos = (paginaActual-1)*cantidadRegistrosPorPagina;
		var finTodos = (paginaActual)*cantidadRegistrosPorPagina;
				
		if (finTodos > datos.length){
			finTodos = datos.length;
		}
		
		for(i = inicioTodos; i<finTodos; i++)
		{
			if((finTodos - inicioTodos) == 1)
			{
				//document.ConsModMasSolForm.chk_CheckGrid[i - ((paginaActual - 1) * (cantidadRegistrosPorPagina))].checked = 1;
				document.ConsModMasSolForm.chk_CheckGrid.checked = 1;
			}else{
				document.ConsModMasSolForm.chk_CheckGrid[j].checked = 1;
			}
			arrayCheck[i].flagCheck = 1; 
			j++;
		}
	}

	/*Función que permite no seleccionar ningun checkbox.*/	
	function deseleccionarTodosCheckBox()
	{
		var j=0;
		var inicioTodos = (paginaActual-1)*cantidadRegistrosPorPagina;
		var finTodos = (paginaActual)*cantidadRegistrosPorPagina;	
	
		if (finTodos > datos.length){
			finTodos = datos.length;
		}
		
		for(i = inicioTodos; i<finTodos; i++)
		{
			if((finTodos - inicioTodos) == 1)
			{
				//document.ConsModMasSolForm.chk_CheckGrid[i - ((paginaActual - 1) * (cantidadRegistrosPorPagina)) + 1].checked = 0;
				document.ConsModMasSolForm.chk_CheckGrid.checked = 0;
			}else{
				document.ConsModMasSolForm.chk_CheckGrid[j].checked = 0;
			}
			arrayCheck[i].flagCheck = 0;
			j++; 	
		}
	}	

	//--- FIN Grilla ---	

	/*----- Implementación de perfilamiento -----*/
	/*Función que compara la fecha de inicio de la consulta, esta no puede ser inferior al 01/01/2012*/
	function comparaFechaDesde(){
		
		var fechaMinima = "01/01/2012";
		var fechaIngresada = document.ConsModMasSolForm.txt_FechaDesde.value;
		
		if(Comparar_Fecha(fechaIngresada,fechaMinima))
		{
			return true;
		}
			
		return false;
	}
	
	/*Función que valida la fecha de inicio.*/
	function validaFechaDesde(){
		
		if(comparaFechaDesde()== false){
		
			alert("La fecha de inicio no puede ser menor al 01/01/2012");
			return false;		
		}
	}
	
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
			
	/*Function que añade fechaHasta por bloques de un mes*/
	/*function aniadeFechaHasta(){
		
		var fechaActual = "<%=session.getAttribute("FechaSistema")%>";
		fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
		
		var anioActual = parseInt(fechaActual.substring(6,10));
		var anioInicio = parseInt(fechaInicio.substring(6,10));
		
		if(anioInicio < anioActual){
			alert("La fecha de inicio no debe ser menor al 01/01/2012.")
			document.ConsModMasSolForm.txt_FechaDesde.value = "";
			return false;
		}
			var numeroMes = fechaInicio.substring(3,5);
			var monthposdos = numeroMes.substring(1);
			var monthposuno = numeroMes.substring(0,1);
			var fechaHastaDef = null;
			
			var numero = parseInt(monthposdos);
			var numerodos = parseInt(numeroMes);
			
			//para agregar año de forma dinámica
			var anioActualDosDigitos = parseInt(fechaActual.substring(8,10));
			var anioInicioDosDigitos = parseInt(fechaInicio.substring(8,10));
			
			var countAnio = "12";
			var i = parseInt(countAnio);
			while( i <= anioInicioDosDigitos ){
			  
			  if(i == anioInicioDosDigitos){
					i.toString();
					if(numeroMes < 10 && monthposdos != 0 && monthposuno == 0){	
					
						switch(numero){
							case 1:
								document.ConsModMasSolForm.txt_FechaHasta.value = "31/01/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							case 2:
								if (anioBisiesto() == true){
									document.ConsModMasSolForm.txt_FechaHasta.value = "29/02/20"+i;
									fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								}else{
									document.ConsModMasSolForm.txt_FechaHasta.value = "28/02/20"+i;
									fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								}	
								break;
							case 3:
								document.ConsModMasSolForm.txt_FechaHasta.value = "31/03/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							case 4:
								document.ConsModMasSolForm.txt_FechaHasta.value = "30/04/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							case 5:
								document.ConsModMasSolForm.txt_FechaHasta.value = "31/05/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							case 6:
								document.ConsModMasSolForm.txt_FechaHasta.value = "30/06/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							case 7:
								document.ConsModMasSolForm.txt_FechaHasta.value = "31/07/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							case 8:
								document.ConsModMasSolForm.txt_FechaHasta.value = "31/08/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							case 9:
								document.ConsModMasSolForm.txt_FechaHasta.value = "30/09/20"+i;
								fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
								break;
							default:
								alert("Error en el número de mes.");
							}
							
						}else{
							switch(numerodos){
			
								case 10:
									document.ConsModMasSolForm.txt_FechaHasta.value = "31/10/20"+i;
									fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;
									break;
								case 11:
									document.ConsModMasSolForm.txt_FechaHasta.value = "30/11/20"+i;
									fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;					
									break;
								case 12:
									document.ConsModMasSolForm.txt_FechaHasta.value = "31/12/20"+i;
									fechaHastaDef = document.ConsModMasSolForm.txt_FechaHasta.value;					
									break;
								default:
									alert("Error en el número de mes.");									
							}
						}
					}
				 i++;	
				}		
		
			return fechaHastaDef;
	}*/
	
	function aniadeFechaHasta(){
	
		fechaInicio = document.ConsModMasSolForm.txt_FechaDesde.value;
		var anioInicio = parseInt(fechaInicio.substring(6,10));
		var fechaFinalMes = "";
		
		if(anioInicio < 2012){
			alert("La fecha de inicio no debe ser menor al 01/01/2012.")
			document.ConsModMasSolForm.txt_FechaDesde.value = "";
			document.ConsModMasSolForm.txt_FechaHasta.value = "";
			return false;
		}
	
		DWREngine.setAsync(false);
		ConsModMasivaSolDWR.obtenerUltimaDiaMes(fechaInicio, function(data){
				
			var res = null;
			fechaFinalMes = data;

			document.ConsModMasSolForm.txt_FechaHasta.value = fechaFinalMes;				
		});
		
		DWREngine.setAsync(true);
		
		return fechaFinalMes;
	}
	
	/*----- fin implementación de perfilamiento -----*/
</script>

</head>

<body onload="cargaCabecera();bloqueaCampos();">
<html:form action="/modMasiva.do">
<div id="caja_registro">
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="txt_FecDesdeTmp" value="0">
	<input type="hidden" name="txt_FecHastaTmp" value="0">
	<input type="hidden" name="dbx_EstSolicitudTmp" value="0">
	<input type="hidden" name="dbx_EstSolicitudAux" value="0">
	<table width="970">
		<tr>
			<td align="right">
				<a href="#" align="right" onClick="enviaFormulario(1);">Volver</a>
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
					<td><strong><p1>CONSULTA Y CAMBIO DE ESTADO MASIVO DE SOLICITUDES</p1></strong></td>
				</tr>
				<!--<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>-->
			</table>
			</td>
		</tr>
		<tr>
			<td height="404">
			<table width="100%" border="1" rules="groups">
				<tr>
					<td>
						<p><p2>Filtro de búsqueda.</p2></p>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td height="30"><strong>Fecha Desde *</strong>
					</td>
					<td>
						<input type="text" name="txt_FechaDesde" id="txt_FechaDesde" class="datepick" disabled="true" size="10" value='<%=request.getAttribute("txt_FechaDesde")==null? "":request.getAttribute("txt_FechaDesde")%>'/>
<!--						<IMG style="cursor:hand" border="0" src="<%=request.getContextPath()%>/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaDesde);aniadeFechaHasta();" >-->
					</td>
					<td height="30"><strong>Fecha Hasta *</strong>
					</td>
					<td>
						<input type="text" name="txt_FechaHasta" id="txt_FechaHasta" disabled="true" size="10" value='<%=request.getAttribute("txt_FechaHasta")==null? "":request.getAttribute("txt_FechaHasta")%>'/>
						<!-- <IMG style="cursor:hand" border="0" src="<%=request.getContextPath()%>/images/Calendar.jpg" width="16" height="16" onClick="ShowCalendarFor(txt_FechaHasta)" > -->
					</td>
					<td>
						<strong>Estado Solicitud *</strong>
							<html:select property="dbx_EstSolicitud" styleClass="dbx_estSolicitud" >
								<html:option value="0">Seleccione</html:option>
								<html:options collection="ListEstadoSolicitudAfiliacionBox" property="codigo" labelProperty="glosa"/>					
							</html:select>
					</td>
					<td colspan="3"  align="right"><input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onClick="consultaModificacionSolicitudDWR();" /> 
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<input type="text" name="txt_EstSolicitud" id="txt_EstSolicitud" disabled="true" style="visibility: hidden"/>
					</td>
					<td colspan="3"></td>
				</tr>
			</table>
			<br/>
	        <div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 300px;">
			</div>
	
			<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 30px;">
			</div>
	        <br/>

	        <table width="100%" border="1" rules="groups">
	        	<tr>
	        		<!-- <td colspan="3"></td>
	        		<td colspan="3"></td> -->
	        		<td>
	        			<a href="#" id="chbx_activado" name="chbx_activado" onClick="seleccionarTodosCheckBox();">Seleccionar Todo</a>
	        		</td>
	        		<td>
	        			<a href="#" id="chbx_desactivado" name="chbx_desactivado" onClick="deseleccionarTodosCheckBox();">Desactivar Todo</a>
	        		</td>
	        		<td align="right" width="500">
	        			<strong>Estado Solicitud *</strong>
						<html:select property="dbx_EstSolicitudTemp" styleClass="dbx_estSolicitud" value="0">
							<html:option value="0">Seleccione...</html:option>
							<html:options collection="ListEstadoSolicitudAfiliacionBox" property="codigo" labelProperty="glosa"/>
						</html:select>
	        		</td>
	        		<td align="right" height="16%" width="178">
	        			<input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Modificar" align="right" onClick="retornaCkeckbox();"/>
	        			&nbsp;&nbsp;&nbsp;
	        			<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onClick="enviaFormulario(1);"/>
	        		</td>
	        	</tr>
	        </table>
	</table>
</div>
</html:form>
</body>
</html:html>
