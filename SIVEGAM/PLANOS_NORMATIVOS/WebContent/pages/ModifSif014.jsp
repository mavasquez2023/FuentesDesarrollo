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

<script type="text/javascript" language="JavaScript1.2" src="./js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteDivisionPrevisionalDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroDivisionPrevisionalDWR.js"></script>

<script type="text/javascript">
	
	/*Variables globales*/
	var datos = new Array();
	var datosEdit = new Array();
	var arrayCheck = new Array();
	var idSelectedItem = 0;
	var idSif014_glob = 0;
	var rutAfiliado_glob = 0;
	var rangoPrimero = 0;
	var rangoSegundo = 0;
	var rutCausante = 0;
	var rutBeneficiario = 0;
	var rut_Empresa_glob = 0;
	
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	/*Objeto solicitud para llenar el arreglo.*/
	function ObjSolicitud(id_sif014, rut_empresa, rut_beneficiario, rut_causante, flagCheck){
		this.id_sif014 = id_sif014;
		this.rut_empresa = rut_empresa;
		this.rut_beneficiario = rut_beneficiario;
		this.rut_causante = rut_causante;
		this.flagCheck = flagCheck;
	}	

	/*Funcion que sera llamada por el onChange del ckechbox*/
	function cambiaFlagCheck(folio){

		for(i=0; i<arrayCheck.length; i++)
		{	
			arrayCheck[i].flagCheck = 0;
		}		
		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif014 == folio)
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

	/**Actualiza flagCheck, al presionar boton cancelar.*/
	function actualizarFlagcheck(f){
	
		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif014 == f)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					arrayCheck[i].flagCheck = 0;
				}else{
					arrayCheck[i].flagCheck = 0;
				}
			}
		}	
	}
		
	function asignaValor(a){

		document.ModifSif014Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ModifSif014Form.submit();
	}
	
	function cabeceraGrilla(){
		document.getElementById("datosNomina").innerHTML = 	
		'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
			'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Editar</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Eliminar</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Correlativo</td>'+		
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Bebeficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Inicio Periodo Reint.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Final Periodo Reint.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Total Reintegro</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Reintegro Mes</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Total Deuda</td>'+
	        '</tr>'+
	    '</table>';
	}
	
	function obtenerHeaderGrilla(){
		
		return 	'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Editar</td>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Eliminar</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Correlativo</td>'+		
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Bebeficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Inicio Periodo Reint.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Final Periodo Reint.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Total Reintegro</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Reintegro Mes</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Total Deuda</td>'+
				'</tr>'
	}
	
	/*Funcion que genera la paginación*/
	function generarPaginacion()
	{
		var paginas = (datos.length/cantidadRegistrosPorPagina)+"";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
				
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" cargas de archivos.</font>";
			
		for(var i=0; i<paginas;i++){
			texto = texto +" "+ "<a href='#' onclick='paginarResultados("+(i+1)+");'>"+(i + 1)+"</a>" ;
		}

		var alargue = "<table><tr><td></td></tr></table><table><tr><td></td></tr></table><table><tr><td></td></tr></table>";
		
		texto = alargue + " " + texto;
		document.getElementById('datosPaginacion').innerHTML = "<table><tr><td align='center'>" + texto + '</td></tr></table>';
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
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderGrilla() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(paginas);
	}
	
		/*funcion que obtiene la fila de la tabla*/
	function obtenerFilaTabla(dato)
	{
		var casillaChk = "";
		
		for(i=0; i<datos.length; i++)
		{
			if(dato.id_sif014 == arrayCheck[i].id_sif014)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}		
		var texto =  " <tr> "+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif014 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='discriminaCamposAEditar();'></td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/papelera_de_reciclaje.GIF' width='16' height='16' onClick='seleccionaEliminar();'></td>"+
							"<td class='texto' align='center'>"+ dato.id_sif014 +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_empresa +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_beneficiario +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_beneficiario +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_beneficiario +"</td>"+
							"<td class='texto' align='center'>"+ dato.tipo_beneficio +"</td>"+
							"<td class='texto' align='center'>"+ dato.tipo_beneficiario +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_causante +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_causante +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_causante +"</td>"+
							"<td class='texto' align='center'>"+ dato.tipo_causante +"</td>"+
							"<td class='texto' align='center'>"+ dato.inicio_period_reinte +"</td>"+
							"<td class='texto' align='center'>"+ dato.final_period_reinte +"</td>"+
							"<td class='texto' align='center'>"+ dato.monto_total_reintegro +"</td>"+
							"<td class='texto' align='center'>"+ dato.monto_reintegro_mes +"</td>"+
							"<td class='texto' align='center'>"+ dato.monto_total_deuda +"</td>"+
	   				 "</tr>";
		return texto;
	}
	
	/*funcion que carga los datos en la grilla*/
	var cantidadRegistrosPorPagina = 20;
	var datos = new Array();
	var paginaActual = 1;
	
	function cargaDatosEnGrilla()
	{
		var contenidoTabla = "";
		
		for(var i=0; i<datos.length; i++)
		{
			if( i < cantidadRegistrosPorPagina ){
				
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
				//asignaValorCampoSif018(datos[i]);
				//llenarCamposEstaticos(datos[i]);
			}
		}
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderGrilla() + contenidoTabla + "</table>";
		
		generarPaginacion();

	}	

	/*Funcion que limpia los datos de busqueda*/
	function limpiarDatosBusqueda(){
		
		document.ModifSif014Form.txt_rut.value = "";
		document.ModifSif014Form.txt_digitoVerificador.value = "";
		document.ModifSif014Form.txt_primerRango.value = "";
		document.ModifSif014Form.txt_segundoRango.value = "";
		document.ModifSif014Form.dbx_filtroBusqueda.value = 0;
		validaFiltroDeBusqueda();
	}
	
	/*Funcion DWR que carga el arreglo de datos de acuerdo a la consulta.
	Adicional se envia el id seleccionado del cobobox para saber si el rut de consulta
	es por rut de empresa o rut de afiliado.*/
	function consultarDatosPorRut(){

		var rut = document.ModifSif014Form.txt_rut.value;
		var digVerificador = document.ModifSif014Form.txt_digitoVerificador.value;
		idSelectedItem = document.ModifSif014Form.dbx_filtroBusqueda.value;
		
		if(idSelectedItem == 1){
			rut_Empresa_glob = rut;
		}
			
		if(idSelectedItem == 2){
			rutBeneficiario = rut;
		}
		
		if(idSelectedItem == 3){
			rutCausante = rut;
		}
		
		DWREngine.setAsync(false);
		
		if(rut.length != 0 && digVerificador.length != 0)
		{
			if(ValidadorRUT(rut,digVerificador)== false)	
			{
				document.ModifSif014Form.txt_digitoVerificador.value = "";
			
			}else{	
				
				EditarReporteDivisionPrevisionalDWR.obtenerDatosSif014PorRut(idSelectedItem, rut, function(data){
					
					datos = data.listSif014;
					
					document.getElementById("datosNomina").innerHTML = "";
					
					if(datos != null){
					
						//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
						for(p=0; p<datos.length; p++){
							arrayCheck[p] = new ObjSolicitud(datos[p].id_sif014, datos[p].rut_empresa, datos[p].rut_beneficiario, datos[p].rut_causante, 0);
						}
							
						cargaDatosEnGrilla();
						cargarCamposEstaticos();
						obtenerEstaticosRut(idSelectedItem,rut);
						limpiarDatosBusqueda();
					}			
				});
			}
		}else{
			alert("Debe ingresar un rut válido para realizar una búsqueda.");
		}
			
		DWREngine.setAsync(true);	
	}


	/*Funcion que carga datos estaticos*/
	function cargarCamposEstaticos(){
		
		DWREngine.setAsync(false);
		
		var tipoArchivo = document.ModifSif014Form.idTipoArchivo.value;
		//var fechaActual = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoArchivo = parseInt(fechaActual.substring(3,5),10)-1;
		var periodoArchivo = document.ModifSif014Form.numeroMes.value;
		
		if(periodoArchivo == 0){
			periodoArchivo = 12;
		}	

		EditarReporteDivisionPrevisionalDWR.obtenerDataEstatica(tipoArchivo, periodoArchivo, function(data){
	
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.ModifSif014Form.txt_tipoArchivo.value = data.tipoArchivoGlosa;
				document.ModifSif014Form.txt_periodoArchivo.value = data.periodoArchivoGlosa;
				
			}
		});
		DWREngine.setAsync(true);
	}
	
	/**Funcion que obtiene campos estaticos de la tabla.*/
	function obtenerEstaticosRut(id,rut){
		
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.selectDataEstaticaRutSif014(id,rut, function(data){
			var resp = null;
			resp = data.listSif014;
			
			if(resp != null){
				
				for(var i=0; i<resp.length; i++)
				{
					document.ModifSif014Form.txt_fechaProceso.value = resp[i].fecha_proceso;
					document.ModifSif014Form.txt_codigoEntidad.value = resp[i].codigo_entidad;
					document.ModifSif014Form.txt_codigoArchivo.value = resp[i].codigo_archivo;
				}
			}else{
				document.ModifSif014Form.txt_fechaProceso.value = "";
				document.ModifSif014Form.txt_codigoEntidad.value = "";
				document.ModifSif014Form.txt_codigoArchivo.value = "";
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que completa el digito verificador*/
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.ModifSif014Form.txt_rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.ModifSif014Form.txt_digitoVerificador.value = digitoVerificador;
	}

	/*Funcion que valida que se va a ejecutar en la busqueda, dada la eleccion del combo box*/
	function validaFiltroDeBusqueda(){
		
		var selectedItem = document.ModifSif014Form.dbx_filtroBusqueda.value;
		var filtroRut = document.getElementById("txt_rut");
		var filtroDV = document.getElementById("txt_digitoVerificador");
		var filtroCorrelativoUno = document.getElementById("txt_primerRango");
		var filtroCorrelativoDos = document.getElementById("txt_segundoRango");
		var buttonSearch = document.getElementById("btn_Buscar");
		
		if(selectedItem == 0){
				document.ModifSif014Form.txt_rut.value = "";
				document.ModifSif014Form.txt_digitoVerificador.value = "";
				document.ModifSif014Form.txt_primerRango.value = "";
				document.ModifSif014Form.txt_segundoRango.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = true;
		}
		
		if(selectedItem == 1){
				document.ModifSif014Form.txt_primerRango.value = "";
				document.ModifSif014Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;			
		}
		
		if(selectedItem == 2){
				document.ModifSif014Form.txt_primerRango.value = "";
				document.ModifSif014Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;
		}
		
		if(selectedItem == 3){
				document.ModifSif014Form.txt_primerRango.value = "";
				document.ModifSif014Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;
		}
		
		if(selectedItem == 4){
				document.ModifSif014Form.txt_rut.value = "";
				document.ModifSif014Form.txt_digitoVerificador.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = false;
				filtroCorrelativoDos.disabled = false;
				buttonSearch.disabled = false;			
		}
	}	

	/*Construccion de mini pantalla para eliminar registros.*/
	function getCabeceraDelete(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center" style="color: blue"><b>Eliminación de Registros</b></p>' + 
						'<p align="center"> Ingrese rango de correlativos a eliminar.</p>'+
					'</td>'+
				'</tr>';
	}
	
	function getRangeDelete(){
	
		var texto = "<table width='100%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='1'>"+ 
						"<tr>"+
								"<td class='texto' align='center'>"+
									"<input type='text' name='txt_rangoUno' id='txt_rangoUno' size='10' maxlength='6' onkeypress='Upper(this,\"N\")'/>"+
							    	"&nbsp;&nbsp;&nbsp;" +
							    	"<img style='cursor:hand' border='0' src='./images/icono_flecha.GIF' width='20' height='20' onClick='agregarRangoDos();'>"+
							    	"&nbsp;&nbsp;&nbsp;" +
							    	"<input type='text' name='txt_rangoDos' id='txt_rangoDos' size='10' disabled='true' maxlength='6' onkeypress='Upper(this,\"N\")'/>"+
								"</td>"+
						"</tr>"+
					"</table>";
		return texto;			
	}
	
	function getButtonDelete(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Eliminar" onClick="deleteRegistro();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivDelete();"/>'+
					'</td>'+
				'</tr>';
	}
	
	function getGraphicsDelete(){
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = getCabeceraDelete() + getRangeDelete() + getButtonDelete() + "<tr></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	
	function agregarRangoDos(){
		var variable = document.getElementById("txt_rangoDos");
		variable.disabled = false;
	}
	
	function deleteRegistro(){
		
		var rango_uno = Trim(document.ModifSif014Form.txt_rangoUno.value);
		var rango_dos = Trim(document.ModifSif014Form.txt_rangoDos.value);
		
		
		if(rango_uno == ""){
			alert("No ha ingresado el correlativo para eliminar el registro.");
			return false;
		}
		
		if(rango_uno != "" && rango_dos == ""){
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif014. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif014SinRango(rango_uno);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}	
		}
		
		if(rango_uno != "" && rango_dos != ""){
		
			if(parseInt(rango_uno,10) > parseInt(rango_dos,10)){
				alert("El primer campo no debe ser mayor al segundo campo.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
				return false;
			}
			
			if((rango_uno.length == 0) && (rango_dos.length > 0)){
				alert("No es posible efectuar la opración debido a que no ha ingresado el primer campo.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
				return false;
			}
				
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif014. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif014(rango_uno,rango_dos);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			} 
		}		
	}
	
	function eliminarRegistroSif014SinRango(a){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 14;
		EditarReporteDivisionPrevisionalDWR.deleteDivisionPrevisionalSinRango(idReporteDelete,a,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se ha eliminado el registro.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}else{
				alert("No es posible eliminar debido a que el correlativo ingresado no esta registrado en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	function eliminarRegistroSif014(a,b){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 14;
		EditarReporteDivisionPrevisionalDWR.deleteDisivionPrevisionalConRango(idReporteDelete,a,b,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se han eliminado los registros.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}else{
				alert("No es posible eliminar debido a que esos correlativos no estan registrados en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}				
		});
		
		DWREngine.setAsync(true);
	}
	
	/**Funcion que actualiza luego de ejecutar alguna accion en popup de eliminacion masiva*/
	function actualizarSistema(){
	
		if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutBeneficiario == 0) && (rutCausante == 0 )){
	
			document.getElementById("datosEstadisticas").style.display = "none";
			document.getElementById("fondoNegro").style.visibility = "hidden";
			cabeceraGrilla();
	
		}else{	
	
			esconderDivCamposModificables();
		}
	}
	
	function esconderDivDelete()
	{
		actualizarFlagcheck(idSif014_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}	
	/*Fin mini pantalla para eliminar registros*/
	
	function esconderDiv()
	{
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}
	
	function esconderDivCamposModificables(){
	
		actualizarFlagcheck(idSif014_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		if(idSelectedItem == 1){
			consultarDatosUpdateados(rut_Empresa_glob);
		}
		
		if(idSelectedItem == 2){
			consultarDatosUpdateados(rutBeneficiario);
		}
		
		if(idSelectedItem == 3){
			consultarDatosUpdateados(rutCausante);
		}
		
		if(idSelectedItem == 4){
			consultarDatosUpdateadosCorrelativo(rangoPrimero,rangoSegundo);
		}
	}
	
	/*Funciones que construyen pantalla para editar campos del informe.*/
	/*Construccion pantalla estadistica*/
	function obtenerHeaderEstadistica(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="4" width="100%">'+
						'<p align="center" style="color: blue"><b>EDICIÓN REPORTE COTIZACIÓN SIF014</b></p>' + 
						'<p align="left" style="color: blue"> 1.- Detalle de campos</p>'+
					'</td>'+
				'</tr>';
	}
	
	function obtenerHeaderCancelarEstadistico()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+					
				'</tr>'+
				'<tr>'+
					'<td colspan="4" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="updateCamposSif014();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivCamposModificables();"/>'+
					'</td>'+
				'</tr>';
	}
	
	var fecha_inic_benef = "";
	var fecha_term_benef = "";
	var dias_asfam = "";
	var nombre_empresa = "";
	var nombre_beneficiario = "";
	var nombre_causante = "";
	var monto_reintegro = "";
	function obtenerFilaTablaEstadistica(datoEdit)
	{
		nombre_empresa = datoEdit.nombre_empresa;
		nombre_beneficiario = datoEdit.nombre_beneficiario;
		nombre_causante = datoEdit.nombre_causante;
		fecha_inic_benef = datoEdit.fechaInicioBeneficioDate;
		fecha_term_benef = datoEdit.fechaTerminoBeneficioDate;
		monto_reintegro = datoEdit.monto_reintegro_mes;
		
		var texto = "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Empresa" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutEmpresa' id='txt_rutEmpresa' value='" + datoEdit.rut_empresa + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvEmpresa' id='txt_dvEmpresa' value='" + datoEdit.dv_empresa + "' disabled='true' size='2'/>"+ 
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Beneficiario" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutBeneficiarioTemp' id='txt_rutBeneficiarioTemp' value='" + datoEdit.rut_beneficiario + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvBeneficiarioTemp' id='txt_dvBeneficiarioTemp' value='" + datoEdit.dv_beneficiario + "' disabled='true' size='2'/>"+ 
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Causante" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutCausanteTemp' id='txt_rutCausanteTemp' value='" + datoEdit.rut_causante + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvCausanteTemp' id='txt_dvCausanteTemp' value='" + datoEdit.dv_causante + "' disabled='true' size='2'/>"+ 
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Cod. Causante" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_codCausante' id='txt_codCausante' value='" + datoEdit.tipo_causante + "' disabled='true' size='10'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Empresa" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreEmpresa' id='txt_nombreEmpresa' value='" + datoEdit.nombre_empresa + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Beneficiario" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreBeneficiario' id='txt_nombreBeneficiario' value='" + datoEdit.nombre_beneficiario + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Causante" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreCausante' id='txt_nombreCausante' value='" + datoEdit.nombre_causante + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
					"<tr>" + 	
							"<table width='99%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='2'>"+
								"<tr><td colspan='4' width='100%' align='center' style='color: blue'><p><b>(Para edición de fechas, utilizar calendario)</b></p></td></tr>"+
								"<tr>"+
									"<td class='texto' align='left' width='20%'>" + " " + "Inicio Periodo Reintegro" + "</td>"+
									"<td class='texto' align='left'>"+
										"<input type='text' name='txt_inicioPeriodo' id='txt_inicioPeriodo' value='" + datoEdit.inicioPeriodoReintegroDate + "' size='10' disabled='true'/>"+
										"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_inicioPeriodo)'/>"+
									"</td>"+
									"<td class='texto' align='left' width='20%'>" + " " + "Final Periodo Reintegro" + "</td>"+
									"<td class='texto' align='left'>"+
										"<input type='text' name='txt_finalPerioco' id='txt_finalPerioco' value='" + datoEdit.finalPeriodoReintegro + "' size='10' disabled='true'/>"+
										"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_finalPerioco);validacionDePeriodos();'/>"+
									"</td>"+																					
		   						 "</tr>"+
		   					"</table>"+
			   		"</tr>"+	 
	   				"<tr>"+
	   					"<table width='100%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='2'>"+	
			   				"<tr>"+
								"<td class='texto' align='left' width='20%'>" + " " + "Monto Reintegro Mes" + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_montoReintegro' id='txt_montoReintegro' value='" + datoEdit.monto_reintegro_mes + "' size='10' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
								"</td>"+																					
		   				 	"</tr>";
		   				"</table>"+
			   		"</tr>"; 

		return texto;
	}
	
	function validacionDePeriodos(){
		
		var fechaInicio = document.AgregarSif014Form.txt_inicioPeriodo.value;
		var fechaTermino = document.AgregarSif014Form.txt_inicioPeriodo.value;
		var mesInf = document.AgregarSif014Form.numeroMes.value;
		
		var mesInformado = parseInt(mesInf,10) - 1;
		
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var mesInformado = parseInt(fechaSistema.substring(3,5),10) - 1;
		
		//desglose para el rango de fechas informadas, separadas en dis, mes y año.
		var diaIni = parseInt(fechaInicio.substring(0,2),10);
		var mesIni = parseInt(fechaInicio.substring(3,5),10);
		var anioIni = parseInt(fechaInicio.substring(6,10),10);
		
		var diaTerm = parseInt(fechaTermino.substring(0,2),10);
		var mesTerm = parseInt(fechaTermino.substring(3,5),10);
		var anioTerm = parseInt(fechaTermino.substring(6,10),10);
		
		if((mesInformado == mesIni) && (mesInformado == mesTerm)){
			if((diaIni < diaTerm) && (mesIni == mesTerm) && (anioIni == anioTerm)){
				return true;
			}
			
			if((diaIni > diaTerm) && ((mesIni == mesTerm) || (mesIni > mesTerm) || (mesIni < mesTerm)) && (anioIni == anioTerm)){
				alert("Error, la Fecha de Inicio no debe ser mayor a la Fecha de Término.");
				document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
				document.getElementById("txt_inicioPeriodo").value = fecha_term_benef;
				return false;
			}
			
			//el mes informado debe permanecer siempre en el mismo rango.
			if((diaIni < diaTerm) && ((mesIni > mesTerm) || (mesIni < mesTerm)) && (anioIni == anioTerm)){
				alert("Error, las fechas deben coincidir en el mismo periodo del mes informado.");
				document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
				document.getElementById("txt_inicioPeriodo").value = fecha_term_benef;
				return false;
			}			
		
			if((anioIni > anioTerm) || (anioIni < anioTerm)){
				alert("Error, el año ingresado debe pertenecer al periodo informado.");
				document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
				document.getElementById("txt_inicioPeriodo").value = fecha_term_benef;
				return false;
			}
					
		}else{
				alert("La fecha ingresada no corresponde al periodo procesado.");
				document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
				document.getElementById("txt_inicioPeriodo").value = fecha_term_benef;
				return false;
		}
		
		return false;
	}
		
	var cantidadRegistros = 1;
	function cargaDatosEstadisticos()
	{
		var contenidoTablaEstadistica = "";
		
		for(var i=0;i<20;i++)
		{
			if( i < cantidadRegistros ){
				
				contenidoTablaEstadistica = contenidoTablaEstadistica + obtenerFilaTablaEstadistica(datosEdit[i]);
				//asignaValorCampoSif018(datos[i]);
				
			}
		}
		document.getElementById("datosEstadisticas").style.display = "";
		document.getElementById("datosEstadisticas").innerHTML = obtenerHeaderEstadistica() + contenidoTablaEstadistica + obtenerHeaderCancelarEstadistico() + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	
	/**Funcion que valida que los campos modificables no vayan vacios.*/
	function verificaCamposModificables(){

		if(Trim(document.ModifSif014Form.txt_nombreEmpresa.value) == ""){
			alert("Falta ingresar el campo Nombre Empresa.");
			document.ModifSif014Form.txt_nombreEmpresa.value = nombre_empresa;
			return false;
		}
		
		if(Trim(document.ModifSif014Form.txt_nombreBeneficiario.value) == ""){
			alert("Falta ingresar el campo Nombre Beneficiario.");
			document.ModifSif014Form.txt_nombreBeneficiario.value = nombre_beneficiario;
			return false;
		}
		
		if(Trim(document.ModifSif014Form.txt_nombreCausante.value) == ""){
			alert("Falta ingresar el campo Nombre Causante.");
			document.ModifSif014Form.txt_nombreCausante.value = nombre_causante;
			return false;
		}
							
		if(Trim(document.ModifSif014Form.txt_inicioPeriodo.value) == ""){
			alert("Falta ingresar el campo Inicio Periodo Reintegro.");
			document.ModifSif014Form.txt_inicioPeriodo.value = fecha_inic_benef;
			return false;
		}
		
		if(Trim(document.ModifSif014Form.txt_finalPerioco.value) == ""){
			alert("Falta ingresar el campo Final Periodo Reintegro.");
			document.ModifSif014Form.txt_finalPerioco.value = fecha_term_benef;
			return false;
		}
		
		if(Trim(document.ModifSif014Form.txt_montoReintegro.value) == ""){
			alert("Falta ingresar el campo Monto Reintegro Mes.");
			document.ModifSif014Form.txt_montoReintegro.value = monto_reintegro;
			return false;
		}
		
		return true;	
	}

	/**Funcion que realiza el update de los campos modificables a la tabla sif014todo*/
	function updateCamposSif014(){
		
		var rut = document.ModifSif014Form.txt_rutEmpresa.value;
		var nombreEmpresa = document.ModifSif014Form.txt_nombreEmpresa.value;
		var nombreBeneficiario = document.ModifSif014Form.txt_nombreBeneficiario.value;
		var nombreCausante = document.ModifSif014Form.txt_nombreCausante.value;
		var inicioPeriodo = document.ModifSif014Form.txt_inicioPeriodo.value;
		var finalPeriodo = document.ModifSif014Form.txt_finalPerioco.value;
		var montoReintegro = document.ModifSif014Form.txt_montoReintegro.value;
		
		if(verificaCamposModificables() != false){
			DWREngine.setAsync(false);
			
			EditarReporteDivisionPrevisionalDWR.updateSif014(idSif014_glob, nombreEmpresa, nombreBeneficiario, nombreCausante, inicioPeriodo, finalPeriodo, montoReintegro, function(data){
				var resp = null;
				
				resp = data.codRespuesta;
				
				if(resp == 0)
				{
					alert("Los campos han sido modificados de manera exitosa.");
					if(idSelectedItem == 1){
						//consultarDatosUpdateados(rut);
						//esconderDiv();
						esconderDivCamposModificables();
					}
					if(idSelectedItem == 2){
						//consultarDatosUpdateados(rutBeneficiario);
						//esconderDiv();
						esconderDivCamposModificables();
					}
					
					if(idSelectedItem == 3){
						//consultarDatosUpdateados(rutCausante);
						//esconderDiv();
						esconderDivCamposModificables();
					}
					if(idSelectedItem == 4){
						//consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
						//esconderDiv();
						esconderDivCamposModificables();
					}
						
				}
				else{
					alert("Ha ocurrido un error y no se han efectuado los cambios.");
					esconderDiv();
				}		
			});
			
			DWREngine.setAsync(true);
		}
	}
	
	/**Funcion que consulta los datos que fueron modificados en la grilla.*/
	function consultarDatosUpdateados(rut){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteDivisionPrevisionalDWR.obtenerDatosSif014PorRut(idSelectedItem,rut, function(data){
			
			datos = data.listSif014;
			
			document.getElementById("datosNomina").innerHTML = "";
			
			if(datos != null){
				
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif014, datos[p].rut_empresa, datos[p].rut_beneficiario, datos[p].rut_causante, 0);
				}
				
				cargaDatosEnGrilla();
			}			
		});
		DWREngine.setAsync(true);		
	}
	
	/*function obtenerCamposAEditar(rutSearch){
		
		//var idSelectedItem = document.ModifSif014Form.dbx_filtroBusqueda.value;
		var idSelectedItem = 1;
		DWREngine.setAsync(false);
		
		EditarReporteDivisionPrevisionalDWR.obtenerDatosSif014PorRut(idSelectedItem, rutSearch, function(data){
			
			datosEdit = data.listSif014;
			
			//document.getElementById("datosNomina").innerHTML = "";
			
			if(datosEdit != null){
				
				cargaDatosEstadisticos();
			}			
		});
		DWREngine.setAsync(true);
	}
	
	function llamarPantalla()
	{
	
		var rutSrch = document.ModifSif014Form.rutEmpresa.value;
		obtenerCamposAEditar(rutSrch);
		//obtenerId(rutSrch);
	}/*
	
		/*Funcion que discrimina que datos traer*/
	function discriminaCamposAEditar(){
		
		datosEdit = new Array();
		if(datos.length == 1){
	
			obtenerCamposAEditarDatoUnico();
	
		}else{
	
			obtenerCamposAEditar();
		}
	}
	
	/*Funcion que obtiene los campos a editar cuando la lista trae un solo dato que se muestra en la grilla*/
	function obtenerCamposAEditarDatoUnico(){

		var chkSeleccion = false;
		var rutSearch = "";
		
		DWREngine.setAsync(false);
		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				//if(document.ConsModMasSolForm.chk_CheckGrid[i].checked == true)
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					

					if(idSelectedItem == 1){
						
						rutSearch = arrayCheck[i].rut_empresa;
						idSif014_glob = arrayCheck[i].id_sif014;
					}
					
					if(idSelectedItem == 2){
						
						rutSearch = arrayCheck[i].rut_beneficiario;
						//document.ModifSif012Form.rutCausante.value = arrayCheck[i].rut_afiliado;
						idSif014_glob = arrayCheck[i].id_sif014;
					}
					if(idSelectedItem == 3){
						
						idSif014_glob = arrayCheck[i].id_sif014;
						rutSearch = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
						
						idSif014_glob = arrayCheck[i].id_sif014;
						rutSearch = "";
					}

					//EditarReporteDivisionPrevisionalDWR.obtenerDatosSif014PorRutId(idSelectedItem, idSif014_glob, rutSearch, function(data){
						
						//datosEdit = data.listSif014;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif014Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif014Form.idSif014_glob.value = idSif014_glob;
					document.ModifSif014Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}
		
	}

	function obtenerCamposAEditar(){
		var chkSeleccion = false;
		var rutSearch = "";
		
		DWREngine.setAsync(false);
		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				//if(document.ConsModMasSolForm.chk_CheckGrid[i].checked == true)
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					

					if(idSelectedItem == 1){
						
						rutSearch = arrayCheck[i].rut_empresa;
						idSif014_glob = arrayCheck[i].id_sif014;
					}
					
					if(idSelectedItem == 2){
						
						rutSearch = arrayCheck[i].rut_beneficiario;
						//document.ModifSif012Form.rutCausante.value = arrayCheck[i].rut_causante;
						idSif014_glob = arrayCheck[i].id_sif014;
					}
					
					if(idSelectedItem == 3){
						
						idSif014_glob = arrayCheck[i].id_sif014;
						rutSearch = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
					
						idSif014_glob = arrayCheck[i].id_sif014;
						rutSearch = "";
					}

					//EditarReporteDivisionPrevisionalDWR.obtenerDatosSif014PorRutId(idSelectedItem, idSif014_glob, rutSearch, function(data){
						
						//datosEdit = data.listSif014;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif014Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif014Form.idSif014_glob.value = idSif014_glob;
					document.ModifSif014Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}			
	}
	
	/******************************** IMPLEMENTACION DE BUSQUEDAS POR CORRELATIVO *****************************************/
	/*Funcion que implementa la busqueda por correlativo.*/
	function busquedaPorCorrelativo(){
	
		var primerRango = document.ModifSif014Form.txt_primerRango.value;
		var segundoRango = document.ModifSif014Form.txt_segundoRango.value;
		
		if(primerRango == "" || segundoRango == ""){
			alert("Debe ingresar un rango válido para realizar la búsqueda.");
			return false;
		}
		
		if(parseInt(primerRango,10) > parseInt(segundoRango,10)){
			
			document.ModifSif014Form.txt_primerRango.value = "";
			document.ModifSif014Form.txt_segundoRango.value = "";
			alert("El valor del primer campo no debe ser mayor al segundo campo.");
			return false;
		}
				
		if((parseInt(segundoRango,10) - parseInt(primerRango,10)) > 100){
			
			document.ModifSif014Form.txt_primerRango.value = "";
			document.ModifSif014Form.txt_segundoRango.value = "";
			alert("El rango ingresado no debe exceder las 100 unidades.");
			return false;
		}
		
		idSelectedItem = 4;
		rangoPrimero = primerRango;
		rangoSegundo = segundoRango;
		
		DWREngine.setAsync(false);
		
		EditarReporteDivisionPrevisionalDWR.busquedaPorRangoSif014(idSelectedItem, primerRango, segundoRango, function(data){
			
			datos = data.listSif014;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif014, datos[p].rut_empresa, datos[p].rut_beneficiario, datos[p].rut_causante, 0);
				}		

				cargaDatosEnGrilla();
				obtenerDataEstaticaPorID(primerRango,segundoRango);
				cargarCamposEstaticos();
				limpiarDatosBusqueda();
			}	
		});
		DWREngine.setAsync(true);
	}	

	/*funcion que obtiene data estatica por ID*/
	function obtenerDataEstaticaPorID(a,b){
		
		DWREngine.setAsync(false);
		
		EditarReporteDivisionPrevisionalDWR.dataEstaticaPorIdSif014(a,b, function(data){
			
			var dat = new Array();
			
			dat = data.listSif014;
			
			if(dat != null){
				for(var i=0; i<dat.length; i++)
				{
					document.ModifSif014Form.txt_fechaProceso.value = dat[i].fecha_proceso;
					document.ModifSif014Form.txt_codigoEntidad.value = dat[i].codigo_entidad;
					document.ModifSif014Form.txt_codigoArchivo.value = dat[i].codigo_archivo;
				}
			}else{
				document.ModifSif014Form.txt_fechaProceso.value = "";
				document.ModifSif014Form.txt_codigoEntidad.value = "";
				document.ModifSif014Form.txt_codigoArchivo.value = "";
			}
		});
		DWREngine.setAsync(true);
	}
	
	/*funcion que consulta los datos updateados por busqueda de correlativo, para actualizar la grilla.*/	
	function consultarDatosUpdateadosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteDivisionPrevisionalDWR.actualizarGrillaSif014Correlativo(idSelectedItem, a, b, function(data){
			
			datos = data.listSif014;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif014, datos[p].rut_empresa, datos[p].rut_beneficiario, datos[p].rut_causante, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);			
	}	
	
		/*************** FUNCION QUE DISCRIMINA POR BUSQUEDA.*********************/
	/*Funcion que selecciona por cual de los dos campos (rut o correlativo) va a efectuar la busqueda.*/
	function seleccionaBusqueda(){
		
		var r_ut = document.ModifSif014Form.txt_rut.value;
		var dv_verif = document.ModifSif014Form.txt_digitoVerificador.value;
		var primer_Rango = document.ModifSif014Form.txt_primerRango.value;
		var segundo_Rango = document.ModifSif014Form.txt_segundoRango.value;
		idSelectedItem = document.ModifSif014Form.dbx_filtroBusqueda.value;
		
		if(r_ut.length != 0 && dv_verif.length != 0 && primer_Rango.length == 0 && segundo_Rango.length == 0){
			
			//ejecutar busqueda por rut.
			consultarDatosPorRut();
		}
		
		if(r_ut.length == 0 && dv_verif.length == 0 && primer_Rango.length != 0 && segundo_Rango.length != 0){
			
			//ejecutar busqueda por correlativo.
			busquedaPorCorrelativo();
			
			//respaldo de onload cargarArregloParametricas();
		}	
	}
	
	/**Funciones que realizan la eliminacion de un dato en la grilla*/
	/*FUNCION QUE ELIMINA UN REGISTRO INDIVIDUAL*/
	function seleccionaEliminar(){
		
		var pregunta = confirm("Va a eliminar información sensible. ¿Está seguro que desea continuar?.");
		if(pregunta == true){
			
			if(datos.length == 1){
				eliminarDatoIndividualUnico();
			}else{
				eliminarDatoIndividual();
			}
		}else{
		
			if(idSelectedItem == 1){
				consultarDatosUpdateados(rut_Empresa_glob);
			}
			
			if(idSelectedItem == 2){
				consultarDatosUpdateados(rutBeneficiario);
			}
			
			if(idSelectedItem == 3){
				consultarDatosUpdateados(rutCausante);
			}
			
			if(idSelectedItem == 4){
				consultarDatosUpdateadosCorrelativo(rangoPrimero,rangoSegundo);
			}
		}
	}
	
	/**Funcion que elimina un dato de la grilla cuando hay un solo dato.*/
	function eliminarDatoIndividualUnico(){
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var rut="";
		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 1){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = arrayCheck[i].rut_empresa;
					}
					
					if(idSelectedItem == 2){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = arrayCheck[i].rut_beneficiario;
					}
					
					if(idSelectedItem == 3){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = "";
					}

					EditarReporteDivisionPrevisionalDWR.eliminarRegistroIndividualSif014(idSelectedItem, idSif014_glob, rut, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							
							if(idSelectedItem == 1){
								actualizarDatosPorRut(rut);
							}
							if(idSelectedItem == 2){
								actualizarDatosPorRut(rut);
							}
							if(idSelectedItem == 3){
								actualizarDatosPorRut(rut);
							}
							if(idSelectedItem == 4){
								actualizarDatosPorCorrelativo(rangoUno,rangoDos);
							}
							esconderDiv();	
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}		
	}
	
	function eliminarDatoIndividual(){
		
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var rut="";
		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 1){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = arrayCheck[i].rut_empresa;
					}
					if(idSelectedItem == 2){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = arrayCheck[i].rut_beneficiario;
					}
					
					if(idSelectedItem == 3){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
						idSif014_glob = arrayCheck[i].id_sif014;
						rut = "";
					}					

					EditarReporteDivisionPrevisionalDWR.eliminarRegistroIndividualSif014(idSelectedItem, idSif014_glob, rut, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							if(idSelectedItem == 1){
								actualizarDatosPorRut(idSelectedItem, rut);
							}
							if(idSelectedItem == 2){
								actualizarDatosPorRut(idSelectedItem, rut);
							}
							if(idSelectedItem == 3){
								actualizarDatosPorRut(idSelectedItem, rut);
							}
							if(idSelectedItem == 4){
								actualizarDatosPorCorrelativo(rangoUno,rangoDos);
							}
							esconderDiv();	
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}				
	}
	
	/*Funcion que actualiza la grilla una vez que se ha eliminado un registro de la misma, pero filtrado por rut*/
	function actualizarDatosPorRut(id, rut){
		
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.actualizarGrillaRutSif014(id, rut, function(data){
		
			datos = data.listSif014;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif014, datos[p].rut_empresa, datos[p].rut_beneficiario, datos[p].rut_causante, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(false);
	}
	
	/*funcion que actualiza la grilla una vez que se ha eliminado un registro de la misma, pero filtrado por correlativo*/
	function actualizarDatosPorCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.actualizarGrillaCorrelativoSif014(a,b,function(data){
			datos = data.listSif014;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif014, datos[p].rut_empresa, datos[p].rut_beneficiario, datos[p].rut_causante, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);
	}
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 14;
		
		DWREngine.setAsync(false);
		AgregarRegistroDivisionPrevisionalDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				if(data.periodoProceso == null){
					document.ModifSif014Form.txt_MesProceso.value = "";
				}else{
					document.ModifSif014Form.txt_MesProceso.value = data.periodoProceso;
				}
				document.ModifSif014Form.numeroMes.value = data.mesConsultado;
			}else{
				document.ModifSif014Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}

	/*Funcion que llama a pantalla para agregar registro, en paralelo con la pantalla de modificacion para no incidir en la consulta en la grilla.*/
	function activarAgregarNuevoRegistro(){
		var mesInformado = document.ModifSif014Form.txt_MesProceso.value;
		if(mesInformado == null || mesInformado == ""){
			alert('Debe procesar el archivo 14 antes de agregar un registro');
		}else{
			var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1150, height=700";
			window.open("./pages/AgregarSif014.jsp","",opciones);
		}
	}		
	/*Fin funciones pantalla modificar*/	
</script>
</head>
<body onload="cabeceraGrilla();cargarMesInformado();">
<html:form action="/modificaSif014.do">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idTipoArchivo" value="3">
  <input type="hidden" name="rutEmpresa" value="0">
  <input type="hidden" name="nombreEmpresa" value=" ">
  <input type="hidden" name="modalidadPago" value="0">
  <input type="hidden" name="montoDocumento" value="0">
  <input type="hidden" name="numeroSerie" value=" ">
  <input type="hidden" name="numeroDocumento" value=" ">
  <input type="hidden" name="fechaEmisionDocumento" value="0">
  <input type="hidden" name="codigoBanco" value="0">
  <input type="hidden" name="fechaCobro" value="0">
  <input type="hidden" name="idSif018" value="0">
  <input type="hidden" name="numeroMes" value="0">
  <input type="hidden" name="idSelectedItem">
  <input type="hidden" name="idSif014_glob">
  <input type="hidden" name="rutSearch">
  <div id="caja_registro">
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	<table width="1020" border="0">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="javascript:closeSesion()" ><B>Cerrar</B></a>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>MANTENEDOR REPORTE REGIMENES LEGALES SIF014</p1></strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario" disabled="true" size="10" value="<%=session.getAttribute("IDAnalista")%>"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" id="txt_Fecha" disabled="true" size="10" value="<%=session.getAttribute("FechaSistema")%>"/></td>				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Mes Informado</td>
					<td><input type="text" name="txt_MesProceso" id="txt_MesProceso" disabled="true" size="20" /></td>
				</tr>
			</table>
		   </td>	
		</tr>
	<br>
	<br>
	<br>
	<tr>
		<td>
			1.- Campos de Búsqueda.
		</td>
	</tr>
	<br>
	<table border="1" width="970" rules="groups">
		<tr>
			<td>Filtro de Búsqueda</td>
			<td colspan="2">
				<html:select property="dbx_filtroBusqueda" styleClass="combobox" value="0" onclick="validaFiltroDeBusqueda();">
					<html:option value="0">Seleccione </html:option>
					<html:option value="1">Rut Empresa </html:option>
					<html:option value="2">Rut Beneficiario </html:option>
					<html:option value="3">Rut Causante </html:option>
					<html:option value="4">Correlativo Archivo</html:option>
				</html:select>
			</td>
			<td>
			 	<strong>N° RUT *</strong> 
			</td>
			<td> 	
			 	<input type="text" name="txt_rut" id="txt_rut" size="10" maxlength="9" onkeypress="Upper(this,'N');" disabled="true" onblur="autoCompletarDigitoVerificador();"/> 
			 	 -  
			 	<input type="text" name="txt_digitoVerificador" id="txt_digitoVerificador" size="1" maxlength="1" onkeyup="Upper(this,'D')" onkeypress="Upper(this,'D')" disabled="true"/> 
			 	<input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onclick="seleccionaBusqueda();" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td colspan="3"></td>
			<td><strong>Correlativo</strong></td>
			<td>
				<input type="text" name="txt_primerRango" id="txt_primerRango" disabled="true" size="4" maxlength="6" onkeypress="Upper(this,'N')"/>
				&nbsp; - &nbsp;
				<input type="text" name="txt_segundoRango" id="txt_segundoRango" disabled="true" size="4" maxlength="6" onkeypress="Upper(this,'N')"/>
			</td>
		</tr>
	</table>	
	</table>
	<br>
	<br>
	<tr>
		<td>
			2.- Reintegros de asignaciones familiares por causantes
		</td>
	</tr>
	<br>
	<br>
	<table width="970">
		<tr>
			<td>Tipo Archivo</td>
			<td><input type="text" name="txt_tipoArchivo" id="txt_tipoArchivo" disabled="true"/></td>
			<td>Periodo Archivo</td>
			<td><input type="text" name="txt_periodoArchivo" id="txt_periodoArchivo" disabled="true"/></td>
			<td>Fecha Proceso</td>
			<td><input type="text" name="txt_fechaProceso" id="txt_fechaProceso" disabled="true"/></td>
		</tr>
		<tr>
			<td>Código Entidad</td>
			<td><input type="text" name="txt_codigoEntidad" id="txt_codigoEntidad" disabled="true"/></td>
			<td>Código Archivo</td>
			<td><input type="text" name="txt_codigoArchivo" id="txt_codigoArchivo" disabled="true"/></td>
		</tr>

	</table>
	<br>
	<div id="barraScroll" style="position: static; margin-top: 0px; margin-left: 0px; width: 965px; height: 445px; border: 1px solid; overflow: scroll;">
		<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 2200px; height: 150px;">
  		</div>
  	</div>
  	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 20px;">
	</div>
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 920px; background:#999999; filter:alpha(opacity=70);-moz-opacity:70%;">
 		<div id="datosEstadisticas" style="display: none; position: absolute; width: 650px; margin-top: 80px; margin-left: 200px; background-color: #F2F2F2">
		</div>
  	</div>
  	<table width="970">
  		<tr>
  			<td align="right">
  				<input type="button" name="btn_delete" id="btn_delete" class="btn_limp" value="Eliminar Masivo" onclick="getGraphicsDelete();"/>
				&nbsp;&nbsp;&nbsp;
  				<!-- <input type="button" name="btn_agregar" id="btn_agregar" class="btn_limp" value="Agregar" onclick="enviaFormulario(2);"/> -->
  				<input type="button" name="btn_agregar" id="btn_agregar" class="btn_limp" value="Agregar" onclick="activarAgregarNuevoRegistro();"/>
  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				<!--<input type="button" name="btn_cancelar" id="btn_cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);"/>-->
  				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cerrar" onclick="javascript:closeSesion()" />
  			</td>
  		</tr>
  	</table>
  </div>
</html:form>
</body>
</html:html>	

