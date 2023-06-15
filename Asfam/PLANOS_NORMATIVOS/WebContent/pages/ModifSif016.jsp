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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/EditarReporteCotizacionesDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/AgregarRegistroCotizacionesDWR.js"></script>

<script type="text/javascript">
	
	/*Variables globales*/
	var datos = new Array();
	var datosEdit = new Array();

	/*variables globales para manejo de registros en grilla y modificacion de los mismos*/
	var arrayCheck = new Array();
	var idSelectedItem;
	var idSif016_glob = 0;
	var rangoPrimero = 0;
	var rangoSegundo = 0;
	var rutEmpresa_Glob = 0;
	
	
	function closeSesion(){		
		window.open('', '_self', ''); 	
		window.close();
	}
	
	/*Objeto solicitud para llenar el arreglo.*/
	function ObjSolicitud(id_sif016, rut_empresa, flagCheck){
		this.id_sif016 = id_sif016;
		this.rut_empresa = rut_empresa;
		this.flagCheck = flagCheck;
	}

	/*Funcion que sera llamada por el onChange del ckechbox*/
	function cambiaFlagCheck(f){

		for(i=0; i<arrayCheck.length; i++){
			arrayCheck[i].flagCheck = 0;
		}
		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif016 == f)
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
			if(arrayCheck[i].id_sif016 == f)
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

		document.ModifSif016Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		ancho= 1024;
		alto=800;
		var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, "
		+ "width=" + ancho + ", height=" + alto + ", "
		+ "top=" + (screen.availHeight - alto)/2  +", left=" + (screen.availWidth - ancho)/2;
		window.open('', 'MOD16', opciones);
		document.ModifSif016Form.target= 'MOD16';
		document.ModifSif016Form.submit();
	}
	
	/*Funciones que construyen la grilla*/
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Num. Total Trabajador</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Num. Total de Cargas</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cargas Retroactivas</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mto ASFAM Mes</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mto. ASFAM Mes Retro.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mto. Reintegros Mes</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Total Pago AsigFam.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Total de Cotización</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Otros Descuentos</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Resultado Neto</td>'+
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Num. Total Trabajador</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Num. Total de Cargas</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cargas Retroactivas</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mto ASFAM Mes</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mto. ASFAM Mes Retro.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Mto. Reintegros Mes</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Total Pago AsigFam.</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Total de Cotización</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Otros Descuentos</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Resultado Neto</td>'+
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
			if(dato.id_sif016 == arrayCheck[i].id_sif016)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}
			
		var texto =  " <tr> "+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif016 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='discriminaCamposEditar();'></td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/papelera_de_reciclaje.GIF' width='16' height='16' onClick='discriminaEliminar();'></td>"+
							"<td class='texto' align='center'>"+ dato.id_sif016 +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_empresa +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.num_total_trabajador +"</td>"+
							"<td class='texto' align='center'>"+ dato.num_total_cargas +"</td>"+
							"<td class='texto' align='center'>"+ dato.cargas_retroactivas +"</td>"+
							"<td class='texto' align='center'>"+ dato.montoAsfamMesMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.montoAsfamMesRetroMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.montoReintegroMesMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.totalPagoAsigFamMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.totalCotizacionesMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.otrosDescuentosMiles +"</td>"+
							"<td class='texto' align='center'>"+ dato.resultadoNetoMiles +"</td>"+
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
		
		document.ModifSif016Form.txt_rut.value = "";
		document.ModifSif016Form.txt_digitoVerificador.value = "";
		document.ModifSif016Form.txt_primerRango.value = "";
		document.ModifSif016Form.txt_segundoRango.value = "";
		document.ModifSif016Form.dbx_filtroBusqueda.value = 0;
		validaFiltroDeBusqueda();
	}
	
	/*Funcion DWR que carga el arreglo de datos de acuerdo a la consulta.*/
	function consultarDatosPorRut(){

		var rut = document.ModifSif016Form.txt_rut.value;
		var digVerificador = document.ModifSif016Form.txt_digitoVerificador.value;
		idSelectedItem = 1;
		rutEmpresa_Glob = rut;
		arrayCheck = new Array();
		
		DWREngine.setAsync(false);
		
		if(rut.length != 0 && digVerificador.length != 0)
		{
			if(ValidadorRUT(rut,digVerificador)== false)	
			{
				document.ModifSif016Form.txt_digitoVerificador.value = "";
			
			}else{	
				
				EditarReporteCotizacionesDWR.obtenerDatosSif016PorRut(rut, function(data){
					
					datos = data.listSif016;
					
					//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
					for(p=0; p<datos.length; p++){
						arrayCheck[p] = new ObjSolicitud(datos[p].id_sif016, datos[p].rut_empresa, 0);
					}
					
					document.getElementById("datosNomina").innerHTML = "";
					
					if(datos != null){
						
						cargaDatosEnGrilla();
						cargarCamposEstaticos();
						cargarCamposEstaticosPorRutSif016(rut);
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
		
		var tipoArchivo = document.ModifSif016Form.idTipoArchivo.value;
		//var fechaActual = '<%=session.getAttribute("FechaSistema")%>'; //TODO SToro Periodo Cambiado
		//var periodoArchivo = parseInt(fechaActual.substring(3,5),10)-1;
		var periodoArchivo = document.ModifSif016Form.numeroMes.value;
		
		if(periodoArchivo == 0){
			periodoArchivo = 12;
		}	

		EditarReporteCotizacionesDWR.obtenerDataEstatica(tipoArchivo, periodoArchivo, function(data){
	
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.ModifSif016Form.txt_tipoArchivo.value = data.tipoArchivoGlosa;
				document.ModifSif016Form.txt_periodoArchivo.value = data.periodoArchivoGlosa;
				
			}
		});
		DWREngine.setAsync(true);
	}

	/**Funcion que carga los datos estaticos filtrados por rut.*/
	function cargarCamposEstaticosPorRutSif016(rut){
		

		DWREngine.setAsync(false);
		EditarReporteCotizacionesDWR.obtenerEstaticosPorRutSif016(rut, function(data){
			var resp = null;
			resp = data.listSif016;
			
			if(resp != null){
				
				for(var i=0; i<resp.length; i++)
				{
					document.ModifSif016Form.txt_fechaProceso.value = resp[i].fecha_proceso;
					document.ModifSif016Form.txt_codigoEntidad.value = resp[i].codigo_entidad;
					document.ModifSif016Form.txt_codigoArchivo.value = resp[i].codigo_archivo;
					document.ModifSif016Form.txt_mesRecaudacion.value = resp[i].mes_recaudacion;
					document.ModifSif016Form.txt_mesRemuneracion.value = resp[i].mes_remuneracion;
				}
			}else{
				document.ModifSif016Form.txt_fechaProceso.value = "";
				document.ModifSif016Form.txt_codigoEntidad.value = "";
				document.ModifSif016Form.txt_codigoArchivo.value = "";
				document.ModifSif016Form.txt_mesRecaudacion.value = "";
				document.ModifSif016Form.txt_mesRemuneracion.value = "";
			}
		});
		
		DWREngine.setAsync(true);
	}	
	/*Funcion que completa el digito verificador*/
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.ModifSif016Form.txt_rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.ModifSif016Form.txt_digitoVerificador.value = digitoVerificador;
	}

	/*Funcion que valida que se va a ejecutar en la busqueda, dada la eleccion del combo box*/
	function validaFiltroDeBusqueda(){
		
		var selectedItem = document.ModifSif016Form.dbx_filtroBusqueda.value;
		var filtroRut = document.getElementById("txt_rut");
		var filtroDV = document.getElementById("txt_digitoVerificador");
		var filtroCorrelativoUno = document.getElementById("txt_primerRango");
		var filtroCorrelativoDos = document.getElementById("txt_segundoRango");
		var buttonSearch = document.getElementById("btn_Buscar");
		
		if(selectedItem == 0){
				document.ModifSif016Form.txt_rut.value = "";
				document.ModifSif016Form.txt_digitoVerificador.value = "";
				document.ModifSif016Form.txt_primerRango.value = "";
				document.ModifSif016Form.txt_segundoRango.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = true;
		}
		
		if(selectedItem == 1){
				document.ModifSif016Form.txt_primerRango.value = "";
				document.ModifSif016Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;			
		}
		
		if(selectedItem == 2){
				document.ModifSif016Form.txt_rut.value = "";
				document.ModifSif016Form.txt_digitoVerificador.value = "";
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
						'<p align="center" style="color: blue"> Ingrese rango de correlativos a eliminar.</p>'+
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
							    	"<input type='text' name='txt_rangoDos' id='txt_rangoDos' size='10' maxlength='6' disabled='true' onkeypress='Upper(this,\"N\")'/>"+
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
	
	function esconderDivDelete()
	{
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
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
		
		var rango_uno = Trim(document.ModifSif016Form.txt_rangoUno.value);
		var rango_dos = Trim(document.ModifSif016Form.txt_rangoDos.value);
		
		
		if(rango_uno == ""){
			alert("No ha ingresado el correlativo para eliminar el registro.");
			return false;
		}
		
		if(rango_uno != "" && rango_dos == ""){
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif016. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif016SinRango(rango_uno);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{
					esconderDivCamposModificados();			
				}
			}	
		}
		
		if(rango_uno != "" && rango_dos != ""){
			if(parseInt(rango_uno,10) > parseInt(rango_dos,10)){
				alert("El primer campo no debe ser mayor al segundo campo.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificados();
				}
				return false;
			}
			
			if((rango_uno.length == 0) && (rango_dos.length > 0)){
				alert("No es posible efectuar la opración debido a que no ha ingresado el primer campo.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificados();
				}
				return false;
			}
			
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif016. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif016(rango_uno,rango_dos);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificados();
				}
			} 
		}		
	}
	
	function eliminarRegistroSif016SinRango(a){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 16;
		EditarReporteCotizacionesDWR.deleteCotizacionesSinRango(idReporteDelete,a,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se ha eliminado el registro.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificados();
				}
			}else{
				alert("No es posible eliminar debido a que el correlativo ingresado no esta registrado en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificados();
				}
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	function eliminarRegistroSif016(a,b){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 16;
		EditarReporteCotizacionesDWR.deleteCotizacionesConRango(idReporteDelete,a,b,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se han eliminado los registros.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificados();
				}
			}else{
				alert("No es posible eliminar debido a que esos correlativos no estan registrados en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rutEmpresa_Glob == 0)){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificados();
				}
			}				
		});
		
		DWREngine.setAsync(true);
	}
	
	function esconderDiv()
	{
		actualizarFlagcheck(idSif016_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}	
	/*Fin mini pantalla para eliminar registros*/


	/*Funciones que construyen pantalla para editar campos del informe.*/
	/*Construccion pantalla estadistica*/
	function obtenerHeaderEstadistica(consulta)
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center" style="color: blue"><b>EDICIÓN REPORTE COTIZACIÓN SIF016</b></p>' + 
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
					'<td colspan="3" width="100%" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: right">'+
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="updateCamposSif016();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivCamposModificados();"/>'+
					'</td>'+
				'</tr>';
	}
	
	var nombre_empresa = "";
	var numero_trabajadores = "";
	var numero_cargas = "";
	var cargas_retroactivas = "";
	var mto_asfam_mes = "";
	var mto_asfam_mes_retro = "";
	var mto_reintegro_mes = "";
	var total_pago_asigFam = "";
	var total_cotizacion = "";
	var otros_descuentos = "";
	var result_neto = "";
	
	function obtenerFilaTablaEstadistica(datoEdit)
	{
		nombre_empresa = datoEdit.nombre_empresa;
		numero_trabajadores = datoEdit.num_total_trabajador;
		numero_cargas = datoEdit.num_total_cargas;
		cargas_retroactivas = datoEdit.cargas_retroactivas;
		mto_asfam_mes = datoEdit.mto_asfam_mes;
		mto_asfam_mes_retro = datoEdit.mto_asfam_mes_retro;
		mto_reintegro_mes = datoEdit.mto_reintegros_mes;
		total_pago_asigFam = datoEdit.total_pago_asigfam;
		total_cotizacion = datoEdit.total_de_cotizacion;
		otros_descuentos = datoEdit.otros_descuentos;
		result_neto = datoEdit.resultado_neto;
		
		var texto = "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Empresa" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutEmpresa' id='txt_rutEmpresa' value='" + datoEdit.rut_empresa + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvEmpresa' id='txt_dvEmpresa' value='" + datoEdit.dv_empresa + "' disabled='true' size='2'/>"+ 
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Empresa" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreEmpresa' id='txt_nombreEmpresa' value='" + datoEdit.nombre_empresa + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Número Total Trabajadores" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroTrabajadores' id='txt_numeroTrabajadores' value='" + datoEdit.num_total_trabajador + "' size='15' maxlength='6' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Número Total de Cargas" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_numeroCargas' id='txt_numeroCargas' value='" + datoEdit.num_total_cargas + "' size='15' maxlength='6' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+	   				    				 
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Cargas Retroactivas" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_cargasRetroactivas' id='txt_cargasRetroactivas' value='" + datoEdit.cargas_retroactivas + "' size='15' maxlength='6' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Monto ASFAM mes" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_montoAsfamMes' id='txt_montoAsfamMes' value='" + datoEdit.mto_asfam_mes + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Monto ASFAM Mes Retro" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_montoAsfamMesRetro' id='txt_montoAsfamMesRetro' value='" + datoEdit.mto_asfam_mes_retro + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Monto Reintegro Mes" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_montoReintegroMes' id='txt_montoReintegroMes' value='" + datoEdit.mto_reintegros_mes + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Total Pago ASIGFAM" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_totalPagoAsigFam' id='txt_totalPagoAsigFam' value='" + datoEdit.total_pago_asigfam + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Total de Cotizacion" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_totalDeCotizacion' id='txt_totalDeCotizacion' value='" + datoEdit.total_de_cotizacion + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Otros Descuentos" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_otrosDescuentos' id='txt_otrosDescuentos' value='" + datoEdit.otros_descuentos + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>"+
	   				 "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Resultado Neto" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_resultadoNeto' id='txt_resultadoNeto' value='" + datoEdit.resultado_neto + "' size='15' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
							"</td>"+																					
	   				 "</tr>";

		return texto;
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

		if(Trim(document.ModifSif016Form.txt_nombreEmpresa.value) == ""){
			alert("Falta Ingresar el campo Nombre Empresa.");
			document.ModifSif016Form.txt_nombreEmpresa.value = nombre_empresa;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_numeroTrabajadores.value) == ""){
			alert("Falta Ingresar el campo Número Total Trabajadores.");
			document.ModifSif016Form.txt_numeroTrabajadores.value = numero_trabajadores;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_numeroCargas.value) == ""){
			alert("Falta Ingresar el campo Número Total de Cargas.");
			document.ModifSif016Form.txt_numeroCargas.value = numero_cargas;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_cargasRetroactivas.value) == ""){
			alert("Falta Ingresar el campo Cargas Retroactivas.");
			document.ModifSif016Form.txt_cargasRetroactivas.value = cargas_retroactivas;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_montoAsfamMes.value) == ""){
			alert("Falta Ingresar el campo Monto ASFAM mes.");
			document.ModifSif016Form.txt_montoAsfamMes.value = mto_asfam_mes;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_montoAsfamMesRetro.value) == ""){
			alert("Falta Ingresar el campo Monto ASFAM Mes Retro.");
			document.ModifSif016Form.txt_montoAsfamMesRetro.value = mto_asfam_mes_retro;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_montoReintegroMes.value) == ""){
			alert("Falta Ingresar el campo Monto Reintegro Mes.");
			document.ModifSif016Form.txt_montoReintegroMes.value = mto_reintegro_mes;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_totalPagoAsigFam.value) == ""){
			alert("Falta Ingresar el campo Total Pago ASIGFAM.");
			document.ModifSif016Form.txt_totalPagoAsigFam.value = total_pago_asigFam;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_totalDeCotizacion.value) == ""){
			alert("Falta Ingresar el campo Total de Cotizacion.");
			document.ModifSif016Form.txt_totalDeCotizacion.value = total_cotizacion;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_otrosDescuentos.value) == ""){
			alert("Falta Ingresar el campo Otros Descuentos.");
			document.ModifSif016Form.txt_otrosDescuentos.value = otros_descuentos;
			return false;
		}
		
		if(Trim(document.ModifSif016Form.txt_resultadoNeto.value) == ""){
			alert("Falta Ingresar el campo Resultado Neto.");
			document.ModifSif016Form.txt_resultadoNeto.value = result_neto;
			return false;
		}
		
		return true;
	}
	
	/*Funcion que realiza el update a la tabla sif016a2, dados los campos modificables.*/
	function updateCamposSif016(){
	
		var rutEmpresa = document.ModifSif016Form.txt_rutEmpresa.value;
		var nombreEmpresa = document.ModifSif016Form.txt_nombreEmpresa.value;
		var numTrabajadores = document.ModifSif016Form.txt_numeroTrabajadores.value;
		var numCargas = document.ModifSif016Form.txt_numeroCargas.value;
		var cargasRetro = document.ModifSif016Form.txt_cargasRetroactivas.value;
		var mtoAsfamMes = document.ModifSif016Form.txt_montoAsfamMes.value;
		var mtoAsfamMesRetro = document.ModifSif016Form.txt_montoAsfamMesRetro.value;
		var mtoReintegroMes = document.ModifSif016Form.txt_montoReintegroMes.value;
		var totalPagoAsigFam = document.ModifSif016Form.txt_totalPagoAsigFam.value;
		var totalCotizacion = document.ModifSif016Form.txt_totalDeCotizacion.value;
		var otrosDescuentos = document.ModifSif016Form.txt_otrosDescuentos.value;
		var resultadoNeto = document.ModifSif016Form.txt_resultadoNeto.value;
		//var  = document.ModifSif016Form..value;
		
		if(verificaCamposModificables() != false){
			DWREngine.setAsync(false);
			
			EditarReporteCotizacionesDWR.updateSif016(rutEmpresa,idSif016_glob,nombreEmpresa,numTrabajadores,numCargas,cargasRetro,
													  mtoAsfamMes,mtoAsfamMesRetro,mtoReintegroMes,totalPagoAsigFam,totalCotizacion,
													  otrosDescuentos,resultadoNeto,function(data){
	
				var resp = null;
				resp = data.codRespuesta;
				
				if(resp == 0){
				
					alert("Los campos han sido modificados de manera exitosa.");
					if(idSelectedItem == 1){
						//consultarDatosUpdateados(rutEmpresa);
						//esconderDiv();
						esconderDivCamposModificados();
					}
					if(idSelectedItem == 2){
						//consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
						//esconderDiv();
						esconderDivCamposModificados();
					}
						
				}else{
					alert("Ha ocurrido un error y no se han efectuado los cambios.");
					esconderDivCamposModificados();
				}
			});
			DWREngine.setAsync(true);
		}	
	}

	/**Funcion "cancelar" de la pantalla de modificacion de registros.*/
	function esconderDivCamposModificados(){
		
		actualizarFlagcheck(idSif016_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		if(idSelectedItem == 1){
			consultarDatosUpdateados(rutEmpresa_Glob);
		}
		
		if(idSelectedItem == 2){
			consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
		}
			
	}
	/*Funcion que actualiza la grilla una vez que se han modificado los datos, dada una busqueda por rut de empresa*/
	function consultarDatosUpdateados(rut){
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		
		EditarReporteCotizacionesDWR.obtenerDatosModificadosPorRut016(idSelectedItem,rut, function(data){
			
			datos = data.listSif016;
			
			document.getElementById("datosNomina").innerHTML = "";
			
			if(datos != null){
				
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif016, datos[p].rut_empresa, 0);
				}
				
				cargaDatosEnGrilla();
			}			
		});
		DWREngine.setAsync(true);		
	}

	/**funcion que hace una consulta para actualizar la grilla con los datos que fueron updateados, dada una busqueda por rango de correlativo.*/
	function consultarDatosUpdateadosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteCotizacionesDWR.obtenerDatosModificadosPorRango016(idSelectedItem, a, b, function(data){
			
			datos = data.listSif016;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif016, datos[p].rut_empresa, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);			
	}
			
	
/*	function obtenerCamposAEditar(rutSearch){
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.obtenerDatosSif016PorRut(rutSearch, function(data){
			
			datosEdit = data.listSif016;
			
			//document.getElementById("datosNomina").innerHTML = "";
			
			if(datosEdit != null){
				
				cargaDatosEstadisticos();
			}			
		});
		DWREngine.setAsync(true);
	}
*/
	/**Funcion que discrimina que funcion se va a utilizar cuando es necesario editar algun registro dependiendo del largo de los datos.*/
	function discriminaCamposEditar(){
	
		datosEdit = new Array();
		
		if(datos.length == 1){
			obtenerCamposAEditarUnico();
		}else{
			obtenerCamposAEditar();
		}	
	}
	
	/*Funcion que obtiene los campos a editar cuando en la grilla existe un solo registro*/
	function obtenerCamposAEditarUnico(){

		var chkSeleccion = false;
		var rutSearch = "";
		DWREngine.setAsync(false);
		

		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;

					if(idSelectedItem == 1){
						
						rutSearch = arrayCheck[i].rut_empresa;
						idSif016_glob = arrayCheck[i].id_sif016;
					}
					
					if(idSelectedItem == 2){
						
						idSif016_glob = arrayCheck[i].id_sif016;
						rutSearch = arrayCheck[i].rut_empresa;
					}
					

					//EditarReporteCotizacionesDWR.obtenerDatosSif016ParaEditar(rutSearch,idSif016_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif016;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif016Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif016Form.idSif016_glob.value = idSif016_glob;
					document.ModifSif016Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}			
	}	
	/**Funcion que obtiene los datos para editar.*/
	function obtenerCamposAEditar(){

		var chkSeleccion = false;
		var rutSearch = "";
		DWREngine.setAsync(false);
		

		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					

					if(idSelectedItem == 1){

						rutSearch = arrayCheck[i].rut_empresa;
						idSif016_glob = arrayCheck[i].id_sif016;
					}
					
					if(idSelectedItem == 2){

						idSif016_glob = arrayCheck[i].id_sif016;
						rutSearch = arrayCheck[i].rut_empresa;
					}
					

					//EditarReporteCotizacionesDWR.obtenerDatosSif016ParaEditar(rutSearch,idSif016_glob,idSelectedItem, function(data){
						
						//datosEdit = data.listSif016;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
							//cargaDatosEstadisticos();
						//}			
					//});
					document.ModifSif016Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif016Form.idSif016_glob.value = idSif016_glob;
					document.ModifSif016Form.rutSearch.value = rutSearch;
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}			
	}
	
	function llamarPantalla()
	{
	
		var rutSrch = document.ModifSif016Form.rutEmpresa.value;
		obtenerCamposAEditar(rutSrch);
		//obtenerId(rutSrch);
	}

	/*FUNCION QUE DISCRIMINA POR BUSQUEDA.*/
	/*Funcion que selecciona por cual de los dos campos (rut o correlativo) va a efectuar la busqueda.*/
	function seleccionaBusqueda(){
		
		var r_ut = document.ModifSif016Form.txt_rut.value;
		var dv_verif = document.ModifSif016Form.txt_digitoVerificador.value;
		var primer_Rango = document.ModifSif016Form.txt_primerRango.value;
		var segundo_Rango = document.ModifSif016Form.txt_segundoRango.value;
		idSelectedItem = document.ModifSif016Form.dbx_filtroBusqueda.value;
		
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
	/**********************************************************************/	
	/*Fin funciones para pantalla modificar*/
	
	function busquedaPorCorrelativo(){
	
		var primerRango = document.ModifSif016Form.txt_primerRango.value;
		var segundoRango = document.ModifSif016Form.txt_segundoRango.value;

		if(primerRango == "" || segundoRango == ""){
			alert("Debe ingresar un rango válido para realizar la búsqueda.");
			return false;
		}
		
		if(parseInt(primerRango,10) > parseInt(segundoRango,10)){
			
			document.ModifSif016Form.txt_primerRango.value = "";
			document.ModifSif016Form.txt_segundoRango.value = "";
			alert("El valor del primer campo no debe ser mayor al segundo campo.");
			return false;
		}
		
		if((parseInt(segundoRango,10) - parseInt(primerRango,10)) > 100){
		
			document.ModifSif016Form.txt_primerRango.value = "";
			document.ModifSif016Form.txt_segundoRango.value = "";
			alert("El rango ingresado no debe exceder las 100 unidades.");
			return false;
		}
				
		idSelectedItem = 2;
		rangoPrimero = primerRango;
		rangoSegundo = segundoRango;
		arrayCheck = new Array();
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.busquedaPorRangoSif016(idSelectedItem, primerRango, segundoRango, function(data){
			
			datos = data.listSif016;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif016, datos[p].rut_empresa, 0);
				}			

				cargaDatosEnGrilla();

				obtenerDataEstaticaPorID(rangoPrimero,rangoSegundo);
				cargarCamposEstaticos();
				limpiarDatosBusqueda();
			}	
		});
		DWREngine.setAsync(true);
	}
	
	/*funcion que obtiene la data estatica del reporte sif016 pero filtrando por rango.*/
	function obtenerDataEstaticaPorID(a,b){
		
		DWREngine.setAsync(false);
		
		EditarReporteCotizacionesDWR.dataEstaticaPorIdSif016(a,b, function(data){
			
			var dat = new Array();
			
			dat = data.listSif016;
			
			if(dat != null){
				for(var i=0; i<dat.length; i++)
				{
					document.ModifSif016Form.txt_fechaProceso.value = dat[i].fecha_proceso;
					document.ModifSif016Form.txt_codigoEntidad.value = dat[i].codigo_entidad;
					document.ModifSif016Form.txt_codigoArchivo.value = dat[i].codigo_archivo;
					document.ModifSif016Form.txt_mesRecaudacion.value = dat[i].mes_recaudacion;
					document.ModifSif016Form.txt_mesRemuneracion.value = dat[i].mes_remuneracion;
				}
			}else{
				document.ModifSif016Form.txt_fechaProceso.value = "";
				document.ModifSif016Form.txt_codigoEntidad.value = "";
				document.ModifSif016Form.txt_codigoArchivo.value = "";
				document.ModifSif016Form.txt_mesRecaudacion.value = "";
				document.ModifSif016Form.txt_mesRemuneracion.value = "";
			}
		});
		DWREngine.setAsync(true);
	}	
	
	/*******************************************************************************************/
	/*Funciones que implementan funcion eliminar registro individual de la grilla*/
	/*Funcion que discrimina por cual busqueda eliminar*/
	function discriminaEliminar(){
		
		var pregunta = confirm("Va a eliminar información sensible. ¿Está seguro que desea continuar?.");
		if(pregunta == true){
			if(idSelectedItem == 1){
				if(datos.length == 1){
					eliminarDatoIndividualUnico();
				}else{	
					eliminarDatoIndividual();
				}
			}
			
			if(idSelectedItem == 2){
				if(datos.length == 1){
					eliminarDatoIndividualCorrelativoUnico();
				}else{	
					eliminaDatoIndividualCorrelativo();
				}	
			}
			
		}else{
			
			if(idSelectedItem == 1){
				consultarDatosUpdateados(rutEmpresa_Glob);
			}
			
			if(idSelectedItem == 2){
				consultarDatosUpdateadosCorrelativo(rangoPrimero, rangoSegundo);
			}	
		}	
	}
	
	/**Funcion que elimina un registro individual cuando la consulta trae un solo registro, dada una consulta por rut empresa.*/
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
						idSif016_glob = arrayCheck[i].id_sif016;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif016(idSif016_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizarDatosPorRut(rut);
							//esconderDiv();
							esconderDivCamposModificados();	
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}
	}
	/*FUNCION QUE ELIMINA UN REGISTRO INDIVIDUAL*/
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
						idSif016_glob = arrayCheck[i].id_sif016;
						rut = arrayCheck[i].rut_empresa;
					}
					

					EditarReporteCotizacionesDWR.eliminarRegistroIndividualSif016(idSif016_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizarDatosPorRut(rut);
							//esconderDiv();
							esconderDivCamposModificados();		
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}				
	}
	
	/**Funcion que elimina registro indivudual dada una busqueda por correlativo, cuando en la consulta se trae un solo registro.*/
	function eliminarDatoIndividualCorrelativoUnico(){
		
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var id_delete = "";
		
		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 2){
						idSif016_glob = arrayCheck[i].id_sif016;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif016(idSif016_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizaDatosCorrelativo(rangoPrimero,rangoSegundo);
							//esconderDiv();
							esconderDivCamposModificados();	
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}	
	}
	
	/*funcion que elimina un registro dada una busqueda por correlativo*/
	function eliminaDatoIndividualCorrelativo(){
		var chkSeleccion = false;
		DWREngine.setAsync(false);
		var id_delete = "";
		
		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					
					if(idSelectedItem == 2){
						idSif016_glob = arrayCheck[i].id_sif016;
					}
					EditarReporteCotizacionesDWR.eliminarRegistroCorrelativoSif016(idSif016_glob, function(data){
						
						var resp = null;
						resp = data.codRespuesta;
						
						if(resp == 0){
							
							alert("El registro se ha eliminado satisfactoriamente.");
							//actualizaDatosCorrelativo(rangoPrimero,rangoSegundo);
							//esconderDiv();
							esconderDivCamposModificados();	
						}
					});
					DWREngine.setAsync(true);
				}
			}
		}		
	}	
	/**Funcion que actualiza datos con el rut.*/
	function actualizarDatosPorRut(a){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		idSelectedItem = 1;
		
		EditarReporteCotizacionesDWR.obtenerDatosPorRut016(idSelectedItem, a, function(data){
					
			datos = data.listSif016;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif016, datos[p].rut_empresa, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);
	}
	/*Funcion que actualiza los datos de la grilla dada la eliminacion de un registro dentro del rango de busqueda por correlativo
	mostrado en la grilla. El resultado es pintar nuevamente la grilla de la busqueda inicial pero sin el dato eliminado.*/
	function actualizaDatosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		idSelectedItem = 2;
		
		EditarReporteCotizacionesDWR.actualizarPorCorrelativo016(a, b, function(data){
					
			datos = data.listSif016;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
					
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif016, datos[p].rut_empresa, 0);
				}						

				cargaDatosEnGrilla();
			}			
		});
		
		DWREngine.setAsync(true);		
	}	
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 16;
		
		DWREngine.setAsync(false);
		AgregarRegistroCotizacionesDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				if(data.periodoProceso == null){
					document.ModifSif016Form.txt_MesProceso.value = "";
				}else{
					document.ModifSif016Form.txt_MesProceso.value = data.periodoProceso;
				}
				document.ModifSif016Form.numeroMes.value = data.mesConsultado;
			}else{
				document.ModifSif016Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}

	/*Funcion que llama a pantalla para agregar registro, en paralelo con la pantalla de modificacion para no incidir en la consulta en la grilla.*/
	function activarAgregarNuevoRegistro(){
		var mesInformado = document.ModifSif016Form.txt_MesProceso.value;
		if(mesInformado == null || mesInformado == ""){
			alert('Debe procesar el archivo 16 antes de agregar un registro');
		}else{
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1150, height=700";
		window.open("./pages/AgregarSif016.jsp","",opciones);
		}
	}
			
</script>
</head>
<body onload="cabeceraGrilla();cargarMesInformado();">
<html:form action="/modificaSif016.do">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idTipoArchivo" value="4">
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
  <input type="hidden" name="idSif016_glob">
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
					<td><strong><p1>MANTENEDOR REPORTE DIVISIÓN RECUPERACIÓN COTIZACIONES SIF016</p1></strong></td>
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
			1.- Campos de Búsqueda
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
					<html:option value="2">Correlativo Archivo</html:option>
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
			2.- Detalle de compensación por empleador
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
		<tr>
			<td>Mes Recaudación</td>
			<td><input type="text" name="txt_mesRecaudacion" id="txt_mesRecaudacion" disabled="true"/></td>
			<td>Mes Remuneración</td>
			<td><input type="text" name="txt_mesRemuneracion" id="txt_mesRemuneracion" disabled="true"/></td>
		</tr>
	</table>
	<br>
	<div id="barraScroll" style="position: static; margin-top: 0px; margin-left: 0px; width: 965px; height: 445px; border: 2px solid; overflow: scroll;">
		<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 2200px; height: 150px;">
  		</div>
  	</div>
  	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 20px;">
	</div>
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 920px; background:#999999; filter:alpha(opacity=60);-moz-opacity:60%;">
 		<div id="datosEstadisticas" style="display: none; position: absolute; width: 450px; margin-top: 80px; margin-left: 200px; background-color: #F2F2F2">
		</div>
  	</div>
  	<table width="970">
  		<tr>
  			<td align="right">
  				<input type="button" name="btn_Agregar" id="btn_Agregar" class="btn_limp" value="Eliminar Masivo" onclick="getGraphicsDelete();"/>
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

