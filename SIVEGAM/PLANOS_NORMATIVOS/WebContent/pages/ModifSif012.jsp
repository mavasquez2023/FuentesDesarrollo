<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA DE GENERACION DE REPORTES</title>
<link href="./css/estilos_interna.css" rel="stylesheet"	type="text/css" />
<link href="./css/grid_960.css" rel="stylesheet"	type="text/css" />

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
	var idSif012_glob = 0;
	var rutAfiliado_glob = 0;
	var rangoPrimero = 0;
	var rangoSegundo = 0;
	var rutCausante = 0;
	var rutAfiliado = 0;
	var rut_Empresa_glob = 0;
	var codTramo = new Array();
	
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	/*Objeto solicitud para llenar el arreglo.*/
	function ObjSolicitud(id_sif012, rut_empresa,rut_afiliado, rut_causante, flagCheck){
		this.id_sif012 = id_sif012;
		this.rut_empresa = rut_empresa;
		this.rut_afiliado = rut_afiliado;
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
			if(arrayCheck[i].id_sif012 == folio)
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
			if(arrayCheck[i].id_sif012 == f)
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

		document.ModifSif012Form.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ModifSif012Form.submit();
	}
	
	/**Funcion de carga para codigo tramo - declaracion de objeto.*/
	function CodigoTramoVO(id_codigo_tramo, desc_codigo_tramo){

		this.id_codigo_tramo = id_codigo_tramo;
		this.desc_codigo_tramo = desc_codigo_tramo;
	}
	
	/**Funcion que carga el arreglo con el codigo tramo*/
	function cargarArregloCodigoTramo(){
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.obtenerDataCodigoTramo( "CodigoTramo", function(data){
			var codigoTramo = null;
			codigoTramo = data;
			
			for(var i=0; i<codigoTramo.length; i++){
				codTramo[i] = new CodigoTramoVO(codigoTramo[i].id_codigo_tramo, codigoTramo[i].desc_codigo_tramo);
			}
		});
		DWREngine.setAsync(false);
	}
	
	/**Funcion que carga el combo con el arreglo de codigo tramo*/
	function obtenerComboCodigoTramo(){
		
		var cmb = document.getElementById("dbx_CodigoTramo");
		cmb.options[0] = new Option("Seleccione",0);
		
		for(var j=0; j<codTramo.length; j++){
			
			var item = null;
			item = codTramo[j];
			
			cod = item.id_codigo_tramo;
			txt = item.desc_codigo_tramo;
			
			cmb.options[j+1] = new Option(txt,cod);
		}
	}
	
	/*cargar selecciona en el combo (generica por si se agregan mas combos)*/
	function selectedItemCombo(){
		
		document.ModifSif012Form.dbx_CodigoTramo.value = itemCodigoTramo;
		
	}
	
	/**Funcion que carga arreglo de parametricas (generica por si se agregan mas combos) se llama desde onloads*/
	function cargarArregloParametricas(){
		
		cargarArregloCodigoTramo();
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Inicio Pago Retroactivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Término Pago Retroactivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Días ASFAM</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Tramo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Beneficio Retroactivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Causal Reliquidación</td>'+
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
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficiario</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Inicio Pago Retroactivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Término Pago Retroactivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Días ASFAM</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Tramo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Beneficio Retroactivo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Causal Reliquidación</td>'+
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
			if(dato.id_sif012 == arrayCheck[i].id_sif012)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}	
		var texto =  " <tr> "+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='0' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif012 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='discriminaCamposAEditar();'></td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/papelera_de_reciclaje.GIF' width='16' height='16' onClick='seleccionaEliminar();'></td>"+
							"<td class='texto' align='center'>"+ dato.id_sif012 +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_empresa +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_empresa +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_afiliado +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_afiliado +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_afiliado +"</td>"+
							"<td class='texto' align='center'>"+ dato.cod_tipo_beneficio +"</td>"+
							"<td class='texto' align='center'>"+ dato.tipo_beneficiario +"</td>"+
							"<td class='texto' align='center'>"+ dato.rut_causante +"</td>"+
							"<td class='texto' align='center'>"+ dato.dv_causante +"</td>"+
							"<td class='texto' align='left'>"+ dato.nombre_causante +"</td>"+
							"<td class='texto' align='center'>"+ dato.cod_tipo_causante +"</td>"+
							"<td class='texto' align='center'>"+ dato.fecha_inicio_benef +"</td>"+
							"<td class='texto' align='center'>"+ dato.fecha_termino_benef +"</td>"+
							"<td class='texto' align='center'>"+ dato.dias_asfam +"</td>"+
							"<td class='texto' align='center'>"+ dato.codigo_tramo +"</td>"+
							"<td class='texto' align='center'>"+ dato.monto_beneficio +"</td>"+
							"<td class='texto' align='center'>"+ dato.causal_reliquidacion +"</td>"+
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
		
		document.ModifSif012Form.txt_rut.value = "";
		document.ModifSif012Form.txt_digitoVerificador.value = "";
		document.ModifSif012Form.txt_primerRango.value = "";
		document.ModifSif012Form.txt_segundoRango.value = "";
		document.ModifSif012Form.dbx_filtroBusqueda.value = 0;
		validaFiltroDeBusqueda();
	}
	
	/*Funcion DWR que carga el arreglo de datos de acuerdo a la consulta.
	Adicional se envia el id seleccionado del cobobox para saber si el rut de consulta
	es por rut de empresa o rut de afiliado.*/
	function consultarDatosPorRut(){

		var rut = document.ModifSif012Form.txt_rut.value;
		var digVerificador = document.ModifSif012Form.txt_digitoVerificador.value;
		
		
		if(idSelectedItem == 1){
			rut_Empresa_glob = rut;
		}
		
		if(idSelectedItem == 2){
			rutAfiliado = rut;
		}
			
		if(idSelectedItem == 3){
			rutCausante = rut;
		}
		
		DWREngine.setAsync(false);
		
		if(rut.length != 0 && digVerificador.length != 0)
		{
			if(ValidadorRUT(rut,digVerificador)== false)	
			{
				document.ModifSif012Form.txt_digitoVerificador.value = "";
			
			}else{	
				
				arrayCheck = new Array();
				EditarReporteDivisionPrevisionalDWR.obtenerDatosSif012PorRut(idSelectedItem, rut, function(data){
					
					datos = data.listSif012;
					
					document.getElementById("datosNomina").innerHTML = "";
					
					if(datos != null){
						
						//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
						for(p=0; p<datos.length; p++){
							arrayCheck[p] = new ObjSolicitud(datos[p].id_sif012, datos[p].rut_empresa, datos[p].rut_afiliado, datos[p].rut_causante, 0);
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
		
		var tipoArchivo = document.ModifSif012Form.idTipoArchivo.value;
		//var fechaActual = '<%=session.getAttribute("FechaSistema")%>';
		//var periodoArchivo = parseInt(fechaActual.substring(3,5),10)-1;
		var periodoArchivo = document.ModifSif012Form.numeroMes.value;
		
		if(periodoArchivo == 0){
			periodoArchivo = 12;
		}	

		EditarReporteDivisionPrevisionalDWR.obtenerDataEstatica(tipoArchivo, periodoArchivo, function(data){
	
			var resp = null;
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.ModifSif012Form.txt_tipoArchivo.value = data.tipoArchivoGlosa;
				document.ModifSif012Form.txt_periodoArchivo.value = data.periodoArchivoGlosa;
				
			}
		});
		DWREngine.setAsync(true);
	}
	
	/*Funcion que selecciona que funcion llamar dado el filtro, para la data estatica.*/
	/*function seleccionaObtenerEstaticos(idSelectedItem,rut){
		
		if(idSeletedItem == 1){
			obtenerEstaticosRut(idSelectedItem,rut);
		}
		if(idSelectedItem == 2){
			obtenerEstaticosRutAfiliado(idSelectedItem,rut);
		}
		if(idSelectedItem == 3){
			obtenerEstaticosRutCausante(idSelectedItem,rutCausante);
		}			
	}*/
	
	function obtenerEstaticosRut(id,rut){
		
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.selectDataEstaticaRutEmpresaSif012(id,rut, function(data){
			var resp = null;
			resp = data.listSif012;
			
			if(resp != null){
				
				for(var i=0; i<resp.length; i++)
				{
					document.ModifSif012Form.txt_fechaProceso.value = resp[i].fecha_proceso;
					document.ModifSif012Form.txt_codigoEntidad.value = resp[i].codigo_entidad;
					document.ModifSif012Form.txt_codigoArchivo.value = resp[i].codigo_archivo;
					document.ModifSif012Form.txt_mesCotizaciones.value = resp[i].mes_recaudacion;
					document.ModifSif012Form.txt_mesRemuneracion.value = resp[i].mes_remuneracion;
				}
			}else{
				document.ModifSif012Form.txt_fechaProceso.value = "";
				document.ModifSif012Form.txt_codigoEntidad.value = "";
				document.ModifSif012Form.txt_codigoArchivo.value = "";
				document.ModifSif012Form.txt_mesCotizaciones.value = "";
				document.ModifSif012Form.txt_mesRemuneracion.value = "";
			}
		});
		
		DWREngine.setAsync(true);
	}
	
	/*Funcion que completa el digito verificador*/
	function autoCompletarDigitoVerificador(){
		
		var strRut = document.ModifSif012Form.txt_rut.value;

		var digitoVerificador = DigitoVerificadorRut(strRut);
		
		document.ModifSif012Form.txt_digitoVerificador.value = digitoVerificador;
	}

	/*Funcion que valida que se va a ejecutar en la busqueda, dada la eleccion del combo box*/
	function validaFiltroDeBusqueda(){
		
		var selectedItem = document.ModifSif012Form.dbx_filtroBusqueda.value;
		var filtroRut = document.getElementById("txt_rut");
		var filtroDV = document.getElementById("txt_digitoVerificador");
		var filtroCorrelativoUno = document.getElementById("txt_primerRango");
		var filtroCorrelativoDos = document.getElementById("txt_segundoRango");
		var buttonSearch = document.getElementById("btn_Buscar");
		
		if(selectedItem == 0){
				document.ModifSif012Form.txt_rut.value = "";
				document.ModifSif012Form.txt_digitoVerificador.value = "";
				document.ModifSif012Form.txt_primerRango.value = "";
				document.ModifSif012Form.txt_segundoRango.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = true;
		}
		
		if(selectedItem == 1){
				document.ModifSif012Form.txt_primerRango.value = "";
				document.ModifSif012Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;			
		}
		
		if(selectedItem == 2){
				document.ModifSif012Form.txt_primerRango.value = "";
				document.ModifSif012Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;
		}
		
		if(selectedItem == 3){
				document.ModifSif012Form.txt_primerRango.value = "";
				document.ModifSif012Form.txt_segundoRango.value = "";
				filtroRut.disabled = false;
				filtroDV.disabled = false;
				buttonSearch.disabled = false;
				filtroCorrelativoUno.disabled = true;
				filtroCorrelativoDos.disabled = true;
				buttonSearch.disabled = false;
		}
			
		if(selectedItem == 4){
				document.ModifSif012Form.txt_rut.value = "";
				document.ModifSif012Form.txt_digitoVerificador.value = "";
				filtroRut.disabled = true;
				filtroDV.disabled = true;
				buttonSearch.disabled = true;
				filtroCorrelativoUno.disabled = false;
				filtroCorrelativoDos.disabled = false;
				buttonSearch.disabled = false;			
		}
	}	

	/*FUNCIONES QUE CONTRUYEN MINI PANTALLA PARA ELIMINACION MASIVA, INGRESANDO RANGO DE CORRELATIVOS DE LOS DATOS A ALIMINAR*/
	/*Construccion de mini pantalla para eliminar registros.*/
	/*funcion que obtiene la cabecera de la pantalla delete.*/
	function getCabeceraDelete(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla" border="1">'+
				'<tr>'+ 
					'<td colspan="3" width="100%">'+
						'<p align="center" style="color: blue"><b>Eliminación de Registros</b></p>' + 
						'<p align="center"> Ingrese rango de correlativos a eliminar.</p>'+
					'</td>'+
				'</tr>';
	}
	
	/*Funcion que obtiene el rango de eliminacion de los correlativos*/
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
	
	/*Funcion que obtiene los botones de la pantalla de eliminacion.*/
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
	/*Funcion que dibuja la pantalla de eliminacion de registros por rango.*/
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
	
	/*Funcion orquestadora que elimina registros dependiendo de si se va a eliminar un registro unico o bien el rango de correlativos*/
	function deleteRegistro(){
		
		var rango_uno = Trim(document.ModifSif012Form.txt_rangoUno.value);
		var rango_dos = Trim(document.ModifSif012Form.txt_rangoDos.value);
		
		
		if(rango_uno == ""){
			alert("No ha ingresado el correlativo para eliminar el registro.");
			return false;
		}
		
		if(rango_uno != "" && rango_dos == ""){
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif012. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif012SinRango(rango_uno);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
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
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
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
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{
					esconderDivCamposModificables();
				}
				return false;
			}
			
			var respuesta = confirm("Va a eliminar información sensible del reporte Sif012. ¿Está seguro que desea continuar?");
			if(respuesta == true){
				eliminarRegistroSif012(rango_uno,rango_dos);
			}else{
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			} 
		}		
	}
	
	function eliminarRegistroSif012SinRango(a){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 12;
		EditarReporteDivisionPrevisionalDWR.deleteDivisionPrevisionalSinRango(idReporteDelete,a,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se ha eliminado el registro.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}else{
				alert("No es posible eliminar debido a que el correlativo ingresado no esta registrado en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
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
	
	function eliminarRegistroSif012(a,b){
		
		DWREngine.setAsync(false);
		
		var idReporteDelete = 12;
		EditarReporteDivisionPrevisionalDWR.deleteDisivionPrevisionalConRango(idReporteDelete,a,b,function(data){
			
			var resp = null;
			resp = data.codRespuesta;
			
			if(resp == 0){
				alert("Se han eliminado los registros.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
					document.getElementById("datosEstadisticas").style.display = "none";
					document.getElementById("fondoNegro").style.visibility = "hidden";
					cabeceraGrilla();
				}else{	
					esconderDivCamposModificables();
				}
			}else{
				alert("No es posible eliminar debido a que esos correlativos no estan registrados en el sistema.");
				if((rangoPrimero == 0 && rangoSegundo == 0) && (rut_Empresa_glob == 0) && (rutAfiliado_glob == 0) && (rutCausante ==0 )){
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
	
	function esconderDiv()
	{	
		actualizarFlagcheck(idSif012_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
	}	
	/*Fin mini pantalla para eliminar registros*/
	
	/**Funcion que esconde el div de los campos modificables y limpia la grilla, dependiendo de la busqueda.*/
	function esconderDivCamposModificables(){
		actualizarFlagcheck(idSif012_glob);
		document.getElementById("datosEstadisticas").style.display = "none";
		document.getElementById("fondoNegro").style.visibility = "hidden";
		
		if(idSelectedItem == 1){
			//llamar funcion buscar datos por rut empresa.
			consultarDatosUpdateados(rut_Empresa_glob);
		}
				
		if(idSelectedItem == 2){
			//llamar funcion buscar datos por rut afiliado.
			consultarDatosUpdateados(rutAfiliado);
		}
		
		if(idSelectedItem == 3){
			//llamar funcion buscar datos por rut causante.
			consultarDatosUpdateados(rutCausante);		
		}
			
		if(idSelectedItem == 3){
			//llamar funcion buscar datos por correlativo
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
						'<p align="center" style="color: blue"><b>EDICIÓN REPORTE COTIZACIÓN SIF012</b></p>' + 
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
						'<input type="button" name="btn_Modificar" id="btn_Modificar" class="btn_limp" value="Modificar" onclick="updateCamposSif012();"/>'+
						'&nbsp;&nbsp;&nbsp;'+
						'<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Cancelar" onclick="esconderDivCamposModificables();"/>'+
					'</td>'+
				'</tr>';
	}
	
	var fecha_inic_benef = "";
	var fecha_term_benef = "";
	var dias_asfam = "";
	var itemCodigoTramo = "";
	function obtenerFilaTablaEstadistica(datoEdit)
	{
		fecha_inic_benef = datoEdit.fechaInicioBeneficioDate;
		fecha_term_benef = datoEdit.fechaTerminoBeneficioDate;
		dias_asfam = datoEdit.dias_asfam;
		itemCodigoTramo = datoEdit.codigo_tramo;
		
		var texto = "<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Empresa" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutEmpresa' id='txt_rutEmpresa' value='" + datoEdit.rut_empresa + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvEmpresa' id='txt_dvEmpresa' value='" + datoEdit.dv_empresa + "' disabled='true' size='2'/>"+ 
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Rut Afiliado" + "</td>"+
						    "<td class='texto' align='left'>"+
								"<input type='text' name='txt_rutAfiliadoTemp' id='txt_rutAfiliadoTemp' value='" + datoEdit.rut_afiliado + "' disabled='true' size='10'/>"+
								"&nbsp;&nbsp;&nbsp;" + "-" + "&nbsp;&nbsp;&nbsp;" + 
								"<input type='text' name='txt_dvAfiliadoTemp' id='txt_dvAfiliadoTemp' value='" + datoEdit.dv_afiliado + "' disabled='true' size='2'/>"+ 
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
								"<input type='text' name='txt_codCausanteTemp' id='txt_codCausanteTemp' value='" + datoEdit.cod_tipo_causante + "' disabled='true' size='10'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Empresa" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreEmpresa' id='txt_nombreEmpresa' value='" + datoEdit.nombre_empresa + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
							"</td>"+
					"</tr>"+
					"<tr>"+
							"<td class='texto' align='left' width='20%'>" + " " + "Nombre Afiliado" + "</td>"+
							"<td class='texto' align='left'>"+
								"<input type='text' name='txt_nombreAfiliado' id='txt_nombreAfiliado' value='" + datoEdit.nombre_afiliado + "' size='55' maxlength='80' onkeypress='Upper(this,\"L\")'/>"+
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
									"<td class='texto' align='left' width='20%'>" + " " + "Fecha Inicio Pago Retroactivo" + "</td>"+
									"<td class='texto' align='left'>"+
										"<input type='text' name='txt_inicioPeriodo' id='txt_inicioPeriodo' value='" + datoEdit.fechaInicioBeneficioDate + "' size='10' disabled='true'/>"+
										"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_inicioPeriodo)'/>"+
									"</td>"+
									"<td class='texto' align='left' width='25%'>" + " " + "Final Término Pago Retroactivo" + "</td>"+
									"<td class='texto' align='left'>"+
										"<input type='text' name='txt_finalPerioco' id='txt_finalPerioco' value='" + datoEdit.fechaTerminoBeneficioDate + "' size='10' disabled='true'/>"+
										"<IMG style='cursor:hand' border='0' src='./images/Calendar.jpg' width='16' height='16' onClick='ShowCalendarFor(txt_finalPerioco);validacionDeFechas();'/>"+
									"</td>"+																					
	   				 			"</tr>"+
			   			 	"</table>"+
			   		"</tr>"+	
	   				 "<tr>"+
	   				  	"<table width='100%' align='center' cellpadding='0' cellspacing='1' class='tabla' border='2'>"+	
			   				"<tr>"+
								"<td class='texto' align='left' width='20%'>" + " " + "Días ASFAM" + "</td>"+
								"<td class='texto' align='left'>"+
									"<input type='text' name='txt_diasAsfam' id='txt_diasAsfam' value='" + datoEdit.dias_asfam + "' size='10' maxlength='4' onkeypress='Upper(this,\"N\")'/>"+
								"</td>"+																					
			   				 "</tr>"+
			   				 "<tr>"+
								"<td class='texto' align='left' width='20%'>" + " " + "Código Tramo" + "</td>"+
								"<td class='texto' align='left'>"+
									"<select property='comboCodTramo' id='dbx_CodigoTramo' styleClass='comboCodTramo' enabled='true' width='5'></select>"+
									//"<input type='text' name='txt_codigoTramo' id='txt_codigoTramo' value='" + datoEdit.codigo_tramo + "' size='10' maxlength='1' onkeypress='Upper(this,\"N\")'/>"+
								"</td>"+																					
			   				 "</tr>"+
			   				 "<tr>"+
									"<td class='texto' align='left' width='20%'>" + " " + "Monto Beneficio Retroactivo" + "</td>"+
									"<td class='texto' align='left'>"+
										"<input type='text' name='txt_montoBeneficio' id='txt_montoBeneficio' value='" + datoEdit.monto_beneficio + "' size='10' maxlength='15' onkeypress='Upper(this,\"N\")'/>"+
									"</td>"+																					
			   				 "</tr>";
						"</table>"+
			   		"</tr>";
		return texto;
	}
	
	/*1.- La fecha de inicio de beneficio y la fecha de termino de beneficio deben estar en el mismo rango de mes, y dicho mes debe corresponder al
	informado.*/
	function validacionDeFechas(){
		
		var fechaInicio = document.ModifSif012Form.txt_inicioPeriodo.value;
		var fechaTermino = document.ModifSif012Form.txt_finalPerioco.value;
		var mesInf = document.ModifSif012Form.numeroMes.value;
		
		var mesInformado = parseInt(mesInf,10) - 1 ;
		
		if(mesInformado == -1){
			mesInformado = 12;
		}
		if(mesInformado == 0){
			mesInformado = 1;
		}
				
		var fechaSistema = '<%=session.getAttribute("FechaSistema")%>';
		//var mesInformado = parseInt(fechaSistema.substring(3,5),10) - 1;
		
		
		//deglose para el rango de fechas informadas, separadas en dis, mes y año.
		var diaIni = parseInt(fechaInicio.substring(0,2),10);
		var mesIni = parseInt(fechaInicio.substring(3,5),10);
		var anioIni = parseInt(fechaInicio.substring(6,10),10);
		
		var diaTerm = parseInt(fechaTermino.substring(0,2),10);
		var mesTerm = parseInt(fechaTermino.substring(3,5),10);
		var anioTerm = parseInt(fechaTermino.substring(6,10),10);
		
		if((mesInformado == mesIni) && (mesInformado == mesTerm)){
			if((diaIni < diaTerm) && (mesIni == mesTerm) && (anioIni == anioTerm)){
				calcularDiasASFAM();
				return true;
			}
			
			//el año debe corresponder a la fecha de sistema.
			if((anioIni > anioTerm) || (anioIni < anioTerm)){
			alert("Error, el año ingresado debe pertenecer al periodo informado.");
				document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
				document.getElementById("txt_finalPerioco").value = fecha_term_benef;
				document.getElementById("txt_diasAsfam").value = dias_asfam;
				return false;
			}
			
			//la fecha de inicio no debe ser mayor a la fecha de termino
			if((diaIni > diaTerm) && ((mesIni == mesTerm)|| (mesIni > mesTerm) || (mesIni < mesTerm)) && (anioIni == anioTerm)){
				alert("Error, la Fecha de Inicio no debe ser mayor a la Fecha de Término.");
				document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
				document.getElementById("txt_finalPerioco").value = fecha_term_benef;
				document.getElementById("txt_diasAsfam").value = dias_asfam;
				return false;
			}
			
			//el mes informado debe permanecer siempre en el mismo rango.
			if((diaIni < diaTerm) && ((mesIni > mesTerm) || (mesIni < mesTerm)) && (anioIni == anioTerm)){
				alert("Error, las fechas deben coincidir en el mismo periodo del mes informado.");
				document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
				document.getElementById("txt_finalPerioco").value = fecha_term_benef;
				document.getElementById("txt_diasAsfam").value = dias_asfam;
				return false;
			}
					
		}else{
			alert("La fecha ingresada no corresponde al periodo procesado.");
			document.getElementById("txt_inicioPeriodo").value = fecha_inic_benef;
			document.getElementById("txt_finalPerioco").value = fecha_term_benef;
			document.getElementById("txt_diasAsfam").value = dias_asfam;
			return false;
		}	
	}

	/**Funcion que calcula el total de días asfam.*/
	function calcularDiasASFAM(){
		
		var fechaInicio_beneficio = document.ModifSif012Form.txt_inicioPeriodo.value;
		var fechaTermino_beneficio = document.ModifSif012Form.txt_finalPerioco.value;
		
		var diasInicio = parseInt(fechaInicio_beneficio.substring(0,2),10);
		var diasTermino = parseInt(fechaTermino_beneficio.substring(0,2),10);
		
		var diasAsfam = 0;
		
		diasAsfam = diasTermino - diasInicio + 1;
		
		if(diasAsfam == 31){
			diasAsfam = 30;
		}
		
		document.ModifSif012Form.txt_diasAsfam.value = 	diasAsfam;		
	}
			
	/**Funcion que verifica que los campos modificables no vayan vacios.*/
	function verificaCamposModificables(){
	
		if(Trim(document.ModifSif012Form.txt_nombreEmpresa.value) == ""){
			alert("Falta ingresar el campo Nombre Empresa.");
			return false;
		}
		
		if(Trim(document.ModifSif012Form.txt_nombreAfiliado.value) == ""){
			alert("Falta ingresar el campo Nombre Afiliado.");
			return false;
		}
		
		if(Trim(document.ModifSif012Form.txt_nombreCausante.value) == ""){
			alert("Falta ingresar el campo Nombre Causante.");
			return false;
		}
			
		if(Trim(document.ModifSif012Form.txt_inicioPeriodo.value) == ""){
			alert("Falta ingresar el campo Fecha Inicio Pago Retroactivo.");
			return false;
		}
		
		if(Trim(document.ModifSif012Form.txt_finalPerioco.value) == ""){
			alert("Falta ingresar el campo Fecha Término Pago Retroactivo.");
			return false;
		}
		
		if(Trim(document.ModifSif012Form.txt_diasAsfam.value) == ""){
			alert("Falta ingresar el campo Días ASFAM.");
			return false;
		}
		
		if(Trim(document.ModifSif012Form.dbx_CodigoTramo.value) == ""){
			alert("Falta ingresar el campo Código Tramo.");
			return false;
		}
		
		if(Trim(document.ModifSif012Form.txt_montoBeneficio.value) == ""){
			alert("Falta ingresar el campo Monto Beneficio Retroactivo.");
			return false;
		}
		
		return true;	
	}
	
	/*Funcion que realiza el update de los campos a la tabla sif012todo*/
	function updateCamposSif012(){
		
		var rut = document.ModifSif012Form.txt_rutEmpresa.value;
		var nombreEmpresa = document.ModifSif012Form.txt_nombreEmpresa.value;
		var nombreAfiliado = document.ModifSif012Form.txt_nombreAfiliado.value;
		var nombreCausante = document.ModifSif012Form.txt_nombreCausante.value;
		var fechaInicioBeneficio = document.ModifSif012Form.txt_inicioPeriodo.value;
		var fechaFinBeneficio = document.ModifSif012Form.txt_finalPerioco.value;
		var diasAsfam = document.ModifSif012Form.txt_diasAsfam.value;
		var codigoTramo = document.ModifSif012Form.dbx_CodigoTramo.value;
		var montoBeneficio = document.ModifSif012Form.txt_montoBeneficio.value;
		
		if(verificaCamposModificables() != false){
			DWREngine.setAsync(false);
			
			EditarReporteDivisionPrevisionalDWR.updateSif012(idSif012_glob, nombreEmpresa, nombreAfiliado, nombreCausante, fechaInicioBeneficio,fechaFinBeneficio,diasAsfam,codigoTramo,montoBeneficio, function(data){
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
						//consultarDatosUpdateados(rutAfiliado);
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
	
	/*Funcion que obtiene los campos que han sido modificados para actualizarlos en la grilla*/
	function consultarDatosUpdateados(rut){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteDivisionPrevisionalDWR.obtenerDatosSif012PorRut(idSelectedItem,rut, function(data){
			
			datos = data.listSif012;
			
			document.getElementById("datosNomina").innerHTML = "";
			
			if(datos != null){
				
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif012, datos[p].rut_empresa, datos[p].rut_afiliado, datos[p].rut_causante, 0);
				}
				
				cargaDatosEnGrilla();
			}			
		});
		DWREngine.setAsync(true);	
	}
	
	/*Funcion que carga la grilla con los datos consultados.*/
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
		obtenerComboCodigoTramo();
		selectedItemCombo();
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("datosEstadisticas").style.visibility = "visible";
	}
	
	
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
						idSif012_glob = arrayCheck[i].id_sif012;
					}
					
					if(idSelectedItem == 2){
						
						rutSearch = arrayCheck[i].rut_afiliado;
						//document.ModifSif012Form.rutCausante.value = arrayCheck[i].rut_afiliado;
						idSif012_glob = arrayCheck[i].id_sif012;
					}
					if(idSelectedItem == 3){
						
						idSif012_glob = arrayCheck[i].id_sif012;
						rutSearch = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
						
						idSif012_glob = arrayCheck[i].id_sif012;
						rutSearch = "";
					}
					document.ModifSif012Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif012Form.idSif012_glob.value = idSif012_glob;
					document.ModifSif012Form.rutSearch.value = rutSearch;
		
					//EditarReporteDivisionPrevisionalDWR.obtenerDatosSif012PorRutId(idSelectedItem, idSif012_glob, rutSearch, function(data){
						
						//datosEdit = data.listSif012;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
						//if(datosEdit != null){
							
						//	cargaDatosEstadisticos();
						//}			
					//});
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
						idSif012_glob = arrayCheck[i].id_sif012;
					}
					
					if(idSelectedItem == 2){
						
						rutSearch = arrayCheck[i].rut_afiliado;
						//document.ModifSif012Form.rutCausante.value = arrayCheck[i].rut_causante;
						idSif012_glob = arrayCheck[i].id_sif012;
					}
					
					if(idSelectedItem == 3){
						
						idSif012_glob = arrayCheck[i].id_sif012;
						rutSearch = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
						
						idSif012_glob = arrayCheck[i].id_sif012;
						rutSearch = "";
					}

					document.ModifSif012Form.idSelectedItem.value = idSelectedItem;
					document.ModifSif012Form.idSif012_glob.value = idSif012_glob;
					document.ModifSif012Form.rutSearch.value = rutSearch;
							
					//EditarReporteDivisionPrevisionalDWR.obtenerDatosSif012PorRutId(idSelectedItem, idSif012_glob, rutSearch, function(data){
						
					//	datosEdit = data.listSif012;
						
						//document.getElementById("datosNomina").innerHTML = "";
						
					//	if(datosEdit != null){
							
					//		cargaDatosEstadisticos();
					//	}			
					//});
					DWREngine.setAsync(true);
					enviaFormulario(3);
				}
			}
		}			
	}
	
	function llamarPantalla()
	{
	
		var rutSrch = document.ModifSif012Form.rutEmpresa.value;
		obtenerCamposAEditar(rutSrch);
		//obtenerId(rutSrch);
	}

	/*Fin funciones pantalla modificar*/		

	/******************************** IMPLEMENTACION DE BUSQUEDAS POR CORRELATIVO *****************************************/
	/*Funcion que implementa la busqueda por correlativo.*/
	function busquedaPorCorrelativo(){
	
		var primerRango = document.ModifSif012Form.txt_primerRango.value;
		var segundoRango = document.ModifSif012Form.txt_segundoRango.value;
		
		if(primerRango == "" || segundoRango == ""){
			alert("Debe ingresar un rango válido para realizar la búsqueda.");
			return false;
		}
		
		if(parseInt(primerRango,10) > parseInt(segundoRango,10)){
			
			document.ModifSif012Form.txt_primerRango.value = "";
			document.ModifSif012Form.txt_segundoRango.value = "";
			alert("El valor del primer campo no debe ser mayor al segundo campo.");
			return false;
		}
		
		if((parseInt(segundoRango,10) - parseInt(primerRango,10)) > 100){
			
			document.ModifSif012Form.txt_primerRango.value = "";
			document.ModifSif012Form.txt_segundoRango.value = "";
			alert("El rango ingresado no debe exceder las 100 unidades.");
			return false;
		}
		
		idSelectedItem = 3;
		rangoPrimero = primerRango;
		rangoSegundo = segundoRango;
		
		
		DWREngine.setAsync(false);
		
		EditarReporteDivisionPrevisionalDWR.busquedaPorRangoSif012(idSelectedItem, primerRango, segundoRango, function(data){
			
			datos = data.listSif012;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif012, datos[p].rut_empresa, datos[p].rut_afiliado, datos[p].rut_causante, 0);
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
		
		EditarReporteDivisionPrevisionalDWR.dataEstaticaPorIdSif012(a,b, function(data){
			
			var dat = new Array();
			
			dat = data.listSif012;
			
			if(dat != null){
				for(var i=0; i<dat.length; i++)
				{
					document.ModifSif012Form.txt_fechaProceso.value = dat[i].fecha_proceso;
					document.ModifSif012Form.txt_codigoEntidad.value = dat[i].codigo_entidad;
					document.ModifSif012Form.txt_codigoArchivo.value = dat[i].codigo_archivo;
					document.ModifSif012Form.txt_mesCotizaciones.value = dat[i].mes_recaudacion;
					document.ModifSif012Form.txt_mesRemuneracion.value = dat[i].mes_remuneracion;
				}
			}else{
				document.ModifSif012Form.txt_fechaProceso.value = "";
				document.ModifSif012Form.txt_codigoEntidad.value = "";
				document.ModifSif012Form.txt_codigoArchivo.value = "";
				document.ModifSif012Form.txt_mesCotizaciones.value = "";
				document.ModifSif012Form.txt_mesRemuneracion.value = "";
			}
		});
		DWREngine.setAsync(true);
	}
	
		/******* c o r r e g i r *******/
	/*funcion que consulta los datos updateados por busqueda de correlativo, para actualizar la grilla.*/	
	function consultarDatosUpdateadosCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		arrayCheck = new Array();
		EditarReporteDivisionPrevisionalDWR.busquedaPorRangoSif012(idSelectedItem, a, b, function(data){
			
			datos = data.listSif012;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif012, datos[p].rut_empresa, datos[p].rut_afiliado, datos[p].rut_causante, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);			
	}
	
	/*************** FUNCION QUE DISCRIMINA POR BUSQUEDA.*********************/
	/*Funcion que selecciona por cual de los dos campos (rut o correlativo) va a efectuar la busqueda.*/
	function seleccionaBusqueda(){
		
		var r_ut = document.ModifSif012Form.txt_rut.value;
		var dv_verif = document.ModifSif012Form.txt_digitoVerificador.value;
		var primer_Rango = document.ModifSif012Form.txt_primerRango.value;
		var segundo_Rango = document.ModifSif012Form.txt_segundoRango.value;
		idSelectedItem = document.ModifSif012Form.dbx_filtroBusqueda.value;
		
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
		
		var pregunta = confirm("Va a eliminar información sensible. ¿Está seguro que desea continuar?");
		if(pregunta == true){
			
			if(datos.length == 1){
				eliminarDatoIndividualUnico();
			}else{
				eliminarDatoIndividual();
			}
		}else{
			if(idSelectedItem == 1){
				//llamar funcion buscar datos por rut empresa.
				consultarDatosUpdateados(rut_Empresa_glob);
			}
					
			if(idSelectedItem == 2){
				//llamar funcion buscar datos por rut afiliado.
				consultarDatosUpdateados(rutAfiliado);
			}
			
			if(idSelectedItem == 3){
				//llamar funcion buscar datos por rut causante.
				consultarDatosUpdateados(rutCausante);		
			}
				
			if(idSelectedItem == 3){
				//llamar funcion buscar datos por correlativo
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
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = arrayCheck[i].rut_empresa;
					}
					
					if(idSelectedItem == 2){
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = arrayCheck[i].rut_afiliado;
					}
					
					if(idSelectedItem == 3){
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = "";
					}
					
					EditarReporteDivisionPrevisionalDWR.eliminarRegistroIndividualSif012(idSelectedItem,idSif012_glob,rut, function(data){
						
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
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = arrayCheck[i].rut_empresa;
					}
					if(idSelectedItem == 2){
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = arrayCheck[i].rut_afiliado;
					}
					
					if(idSelectedItem == 3){
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = arrayCheck[i].rut_causante;
					}
					
					if(idSelectedItem == 4){
						idSif012_glob = arrayCheck[i].id_sif012;
						rut = "";
					}

					EditarReporteDivisionPrevisionalDWR.eliminarRegistroIndividualSif012(idSelectedItem, idSif012_glob, rut, function(data){
						
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
		EditarReporteDivisionPrevisionalDWR.actualizarGrillaRutSif012(id, rut, function(data){
		
			datos = data.listSif012;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif012, datos[p].rut_empresa, datos[p].rut_afiliado, datos[p].rut_causante, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(false);
	}
	
	/*funcion que actualiza la grilla una vez que se ha eliminado un registro de la misma, pero filtrado por correlativo*/
	function actualizarDatosPorCorrelativo(a,b){
		
		DWREngine.setAsync(false);
		EditarReporteDivisionPrevisionalDWR.actualizarGrillaCorrelativoSif012(a,b,function(data){
			datos = data.listSif012;
					
			document.getElementById("datosNomina").innerHTML = "";
					
			if(datos != null){
						
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif012, datos[p].rut_empresa, datos[p].rut_afiliado, datos[p].rut_causante, 0);
				}			

				cargaDatosEnGrilla();
			}
		});
		DWREngine.setAsync(true);
	}

	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 12;
		
		DWREngine.setAsync(false);
		AgregarRegistroDivisionPrevisionalDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				if(data.periodoProceso == null){
					document.ModifSif012Form.txt_MesProceso.value = "";
				}else{
					document.ModifSif012Form.txt_MesProceso.value = data.periodoProceso;
				}
				document.ModifSif012Form.numeroMes.value = data.mesConsultado;
			}else{
				document.ModifSif012Form.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}
	
	function parametros(){
		
		var rutRequest = '<%=request.getParameter("rut")%>';
		var dvRequest = '<%=request.getParameter("dv")%>';
		var correlativoRequest = '<%=request.getParameter("idsif")%>';
		

		if((rutRequest == 'null' || rutRequest == "") && (dvRequest == 'null' || dvRequest == "") && (correlativoRequest == 'null' || correlativoRequest == "")){
			document.getElementById("lnk_Salir").style.visibility = 'hidden';
			return false;
		}else{
			document.getElementById("lnk_Volver").style.visibility = 'hidden';
			document.getElementById("lnk_Cerrar").style.visibility = 'hidden';
			document.getElementById("btn_cancelar").style.visibility = 'hidden';
			document.getElementById("btn_delete").style.visibility = 'hidden';
			document.getElementById("btn_agregar").style.visibility = 'hidden';
			document.getElementById("lnk_Salir").style.visibility = 'visible';
			document.ModifSif012Form.dbx_filtroBusqueda.value = 4;			
			//document.ModifSif012Form.dbx_filtroBusqueda.value = 1;
			validaFiltroDeBusqueda();
			document.ModifSif012Form.txt_primerRango.value = correlativoRequest;
			document.ModifSif012Form.txt_segundoRango.value = correlativoRequest;
			//document.ModifSif012Form.txt_rut.value = rutRequest;
			//document.ModifSif012Form.txt_digitoVerificador.value = dvRequest;
			var boton = document.getElementById("btn_Buscar");
			boton.click();
		}	
	}
	
	function cerrarVentana(){
		window.close();
	}
	
	function activarAgregarNuevoRegistro(){
		var mesInformado = document.ModifSif012Form.txt_MesProceso.value;
		if(mesInformado == null || mesInformado == ""){
			alert('Debe procesar el archivo 12 antes de agregar un registro');
		}else{
			var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1150, height=700";
			window.open("./pages/AgregarSif012.jsp","",opciones);
		}
	}
	
</script>
</head>
<body onload="cabeceraGrilla();cargarArregloParametricas();cargarMesInformado();parametros();">


<html:form action="/modificaSif012.do">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="idTipoArchivo" value="2">
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
  <input type="hidden" name="rutCausante" value="0">
  <input type="hidden" name="numeroMes" value="0">  
  <input type="hidden" name="idSelectedItem">
  <input type="hidden" name="idSif012_glob">
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
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" id="lnk_Salir" name="lnk_Salir" onClick="cerrarVentana();" ><B>Salir</B></a>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>MANTENEDOR REPORTE REGIMENES LEGALES SIF012</p1></strong></td>
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
					<html:option value="2">Rut Afiliado </html:option>
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
			2.- Egreso de asignaciones familiares retroactivas por causante
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
			<td>Mes Cotizaciones</td>
			<td><input type="text" name="txt_mesCotizaciones" id="txt_mesCotizaciones" disabled="true"/></td>
			<td>Mes Remuneración</td>
			<td><input type="text" name="txt_mesRemuneracion" id="txt_mesRemuneracion" disabled="true"/></td>
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
