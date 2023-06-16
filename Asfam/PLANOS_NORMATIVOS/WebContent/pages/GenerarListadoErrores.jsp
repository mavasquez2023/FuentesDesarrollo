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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarListadoErroresDWR.js"></script>


<script type="text/javascript">

	var datos = new Array();
	var cantidadRegistrosPorPagina = 20;
	var paginaActual = 1;
	var datosEdit = new Array();
	var arrayCheck = new Array();
	var seleccionCheck = 0;
	var correlativo_global = 0;
	var rut_global = 0;
	var dv_global = 0;
	
	function closeSesion(){		
		window.open('', '_self', ''); 
		window.close();
	}
	
	/*Objeto solicitud para llenar el arreglo del formulario 11 y 12.*/
	function ObjSolicitud(id_sif012, rut_empresa, dv_empresa, flagCheck){
		this.id_sif012 = id_sif012;
		this.rut_empresa = rut_empresa;
		this.dv_empresa = dv_empresa;
		this.flagCheck = flagCheck;
	}

	function ObjSolicitud011(id_sif011, rut_empresa, dv_empresa, flagCheck){
		this.id_sif011 = id_sif011;
		this.rut_empresa = rut_empresa;
		this.dv_empresa = dv_empresa;
		this.flagCheck = flagCheck;
	}
	
	/*Funcion que sera llamada por el onChange del ckechbox*/
	function cambiaFlagCheck(numero){

		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif012 == numero)
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

	function cambiaFlagCheck11(n){

		for(i=0; i<arrayCheck.length; i++)
		{	
			if(arrayCheck[i].id_sif011 == n)
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
	
	/**Actualiza flagCheck, vuelve todo a cero.*/
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

		document.GenerarListadoErroresForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.GenerarListadoErroresForm.submit();
	}
	
	/**Funcion que selecciona que radio button se eligió y toma la desición de que grilla va a mostrar.*/
	function obtenerValorYSeleccionarGrilla(){ 
	    
	    var opcionSeleccionada;
	    var valueRadioButton = document.GenerarListadoErroresForm.rbt_listaErrores.length;
	    
	    for (var i=0; i < valueRadioButton; i++){ 
	       if (document.GenerarListadoErroresForm.rbt_listaErrores[i].checked){
	          	 opcionSeleccionada = document.GenerarListadoErroresForm.rbt_listaErrores[i].value;
	          	 seleccionCheck = opcionSeleccionada;   	 
	       }   	 
	    } 
	    document.getElementById("datosNomina").innerHTML = "";
	    switch(parseInt(opcionSeleccionada,10)){
	    	case 2:
	    		obtenerDatosSif011(opcionSeleccionada);
	    		break;
	    		
	    	case 3:
	    		obtenerDatosSif011(opcionSeleccionada);
	    		break;
	    		
	    	case 4:
	    		obtenerDatosSif012(opcionSeleccionada);
	    		break;
	    		
	    	case 5:
	    		obtenerDatosSif012(opcionSeleccionada);
	    		break;
	    		
	    	case 6:
	    		obtenerDatosSif012(opcionSeleccionada);
	    		break;	
	    		
	    	default:
	    		document.getElementById("datosNomina").innerHTML = "";
	    			
	    }
	} 	
	
	/**funcion que carga el mes de procesamiento por defecto, para saber a que periodo corresponde la inserción.*/
	function cargarMesInformado(){
		
		var idTipoReporte = 14;
		
		DWREngine.setAsync(false);
		GenerarListadoErroresDWR.cargarMesProcesamiento(idTipoReporte, function(data){
			
			var consulta = null;
			consulta = data.codRespuesta;
			
			if(consulta != null){
				document.GenerarListadoErroresForm.txt_MesProceso.value = data.periodoProceso;
				document.GenerarListadoErroresForm.numeroMes.value = data.mesConsultado;
			}else{
				document.GenerarListadoErroresForm.txt_MesProceso.value = "";
			}		
		});
		
		DWREngine.setAsync(true);
	}


		
	/**Funcion que obtiene la cabecera de la grilla del reporte numero 11*/
	function obtenerHeaderGrillaSif011(){
		document.getElementById("datosNomina").innerHTML =
		'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modificar</td>'+	
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Correlativo</td>'+		
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Inicio Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Término Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Días ASFAM</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Tramo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Beneficio</td>'+
				'</tr>'
		'</table>';		
	}
	
		function obtenerCabeceraGrilla011(){
		
		return 	'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modificar</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Correlativo</td>'+		
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Empresa</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Afiliado</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Tipo Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Rut Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">DV Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Nombre Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Cod. Tipo Causante</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Inicio Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Fec. Término Beneficio</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Días ASFAM</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Código Tramo</td>'+
	            	'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Monto Beneficio</td>'+
				'</tr>'
	}
	
	/*Funcion que calcula el periodo, para la realizacion de la consulta*/
	function calculoPeriodo(){
		
		var fechaSist = '<%=session.getAttribute("FechaSistema")%>';
		var anio = fechaSist.substring(6,10);
		var mes = parseInt(fechaSist.substring(3,5),10) - 1;
		
		if(mes == 0){
			mes = 12;
			anio = parseInt(anio) - 1;
			anio = anio.toString();
		}
			
		if(mes < 10){
			mes = '0' + mes;
		}
		var periodoCalculado = "";
		periodoCalculado =	anio + mes;

		//periodoCalculado = "201212";		
		return periodoCalculado;
	}
	
	/**Funcion que toma el periodo y lo transforma en glosa.*/
	function imprimirGlosaPeriodo(){
		
		var periodo = calculoPeriodo();
		var mesPeriodo = parseInt(periodo.substring(4,7));
		var anioPeriodo = periodo.substring(0,4);
		var glosaPeriodo = "";
		
		DWREngine.setAsync(false);
		GenerarListadoErroresDWR.obtenerGlosaMes(mesPeriodo, function(data){
			
			glosaPeriodo = data.descripcion_periodo_proceso;
			glosaPeriodo = glosaPeriodo + " " + anioPeriodo;
		});
		DWREngine.setAsync(true);
	
		document.GenerarListadoErroresForm.txt_MesProceso.value = glosaPeriodo;
	}
	
	
	/*Funcion DWR que llena el arreglo para ser mostrado en la grilla.*/
	function obtenerDatosSif011(flagLlamada){
		
		datos = new Array();
		var periodoSelected = calculoPeriodo();
		DWREngine.setAsync(false);
		GenerarListadoErroresDWR.obtenerDataRetroSif011(periodoSelected, flagLlamada, function(data){
			datos = data.listSif011;
			
			if(datos != null){
				
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.//FRM revisar
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud011(datos[p].id_sif011, datos[p].rut_empresa, datos[p].dv_empresa, 0);
				}
				
				cargarDatosGrillaSif011();
			
			}else{
				document.getElementById("datosNomina").innerHTML = obtenerCabeceraGrilla011() + "</table>";
			}
		});
		DWREngine.setAsync(true);
	}
	
	/**Funcion que carga los datos en la grilla, para sif011*/
	//var cantidadRegistrosPorPaginaSif011 = 20;
	//var paginaActual = 1;
	
	function cargarDatosGrillaSif011(){

		var contenidoTablaSif011 = "";
		
		for(var i=0; i<datos.length; i++)
		{
			if( i < cantidadRegistrosPorPagina ){
				
				contenidoTablaSif011 = contenidoTablaSif011 + obtenerFilaTablaSif011(datos[i]);
			}
		}
		
		document.getElementById("datosNomina").innerHTML = obtenerCabeceraGrilla011() + contenidoTablaSif011 + "</table>";
		
		generarPaginacionSif011();

	}
	
	/*Funcion que obtiene los datos para mostrarlos en la grilla.*/
	function obtenerFilaTablaSif011(dato){

		var casillaChk = "";
		
		for(i=0; i<datos.length; i++)
		{
			if(dato.id_sif011 == arrayCheck[i].id_sif011)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					casillaChk = "checked='true' "; 
				}
			}
		}		
		var texto =  " <tr> "+
							//"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck11(" + dato.id_sif011 + ");seleccionaFuncionDeRegistros()'/>"+"</td>"+
							//"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='discriminaModificar();'></td>"+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck11(" + dato.id_sif011 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='seleccionaFuncionDeRegistros();'></td>"+
							"<td class='texto' align='center'>"+ dato.id_sif011 +"</td>"+
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
							"<td class='texto' align='center'>"+ dato.montoBeneficioMiles +"</td>"+
	   				 "</tr>";
		return texto;
	}
	
	/*Funcion que genera la paginación para reportes de errores del archivo 11.*/
	function generarPaginacionSif011()
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

	/*Función que realiza el paginado para reportes de errores del archivo 11.*/
	function paginarResultadosSif011(pagina){
	
		var inicio = (pagina-1)*cantidadRegistrosPorPagina;
		var fin = (pagina)*cantidadRegistrosPorPagina;
		document.getElementById("datosNomina").innerHTML = "";
		var contenidoTabla = "";
		
		paginaActual = pagina;

		for(var i=inicio;i<fin;i++){
			
			if(datos[i]!= null)
				var contenidoTabla = contenidoTabla + obtenerFilaTablaSif011(datos[i]);
			else
				i = fin;
		}		
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderGrillaSif011() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacionSif011(paginas);
	}
		
	/*Funcion que obtiene la cabecera de la grilla del reporte numero 12.*/
	function obtenerHeaderGrillaSif012(){
		
		document.getElementById("datosNomina").innerHTML = 
		'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modificar</td>'+	
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
		'</table>';	
	}

	
	function obtenerCabeceraGrilla012(){
		
		return 	'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"></td>'+
					'<td height="20" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center">Modificar</td>'+	
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
	
	/*Funcion que obtiene los datos para mostrarlos en la grilla, para sif012*/
	function obtenerFilaTablaSif012(dato){
		
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
							//"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif012 + ");seleccionaFuncionDeRegistros()'/>"+"</td>"+
							//"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='seleccionaRegistroUnico();'></td>"+
							"<td>"+"<input type='radio' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + casillaChk + " onChange='cambiaFlagCheck(" + dato.id_sif012 + ");'/>"+"</td>"+
							"<td class='texto' align='center'><img style='cursor:hand' border='0' src='./images/icono_lupa.gif' width='16' height='16' onClick='seleccionaFuncionDeRegistros();'></td>"+
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

	/**Funcion que carga los datos en la grilla, para sif012*/
	function cargarDatosGrillaSif012(){

		var contenidoTablaSif012 = "";
		
		for(var i=0; i<datos.length; i++)
		{
			if( i < cantidadRegistrosPorPagina ){
				
				contenidoTablaSif012 = contenidoTablaSif012 + obtenerFilaTablaSif012(datos[i]);
			}
		}
		
		document.getElementById("datosNomina").innerHTML = obtenerCabeceraGrilla012() + contenidoTablaSif012 + "</table>";
		
		generarPaginacionSif012();

	}
	
	/*Funcion DWR que llena el arreglo para ser mostrado en la grilla.*/
	function obtenerDatosSif012(flagLlamada){
		
		datos = new Array();
		arrayCheck = new Array();
		
		var periodoSelected = calculoPeriodo();
		DWREngine.setAsync(false);
		GenerarListadoErroresDWR.obtenerDataRetroSif012(periodoSelected, flagLlamada, function(data){
			datos = data.listSif012;
			
			if(datos != null){
				
				//Se carga el arreglo que trae la lista de solicitudes pero con los datos requeridos.//FRM revisar
				for(p=0; p<datos.length; p++){
					arrayCheck[p] = new ObjSolicitud(datos[p].id_sif012, datos[p].rut_empresa, datos[p].dv_empresa, 0);
				}
				
				cargarDatosGrillaSif012();
			
			}else{
				document.getElementById("datosNomina").innerHTML = obtenerCabeceraGrilla012() + "</table>";
			}
		});
		DWREngine.setAsync(true);
	}

	/*Funcion que genera la paginación para reportes de errores del archivo 11.*/
	function generarPaginacionSif012()
	{
		var paginas = (datos.length/cantidadRegistrosPorPagina)+"";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
				
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" registros.</font>";
			
		for(var i=0; i<paginas;i++){
			texto = texto +" "+ "<a href='#' onclick='paginarResultados("+(i+1)+");'>"+(i + 1)+"</a>" ;
		}

		var alargue = "<table><tr><td></td></tr></table><table><tr><td></td></tr></table><table><tr><td></td></tr></table>";
		
		texto = alargue + " " + texto;
		document.getElementById('datosPaginacion').innerHTML = "<table><tr><td align='center'>" + texto + '</td></tr></table>';
	}

	/*Función que realiza el paginado para reportes de errores del archivo 11.*/
	function paginarResultados(pagina){
	
		var inicio = (pagina-1)*cantidadRegistrosPorPagina;
		var fin = (pagina)*cantidadRegistrosPorPagina;
		document.getElementById("datosNomina").innerHTML = "";
		var contenidoTabla = "";
		
		paginaActual = pagina;

		for(var i=inicio;i<fin;i++){
			
			if(datos[i]!= null)
				var contenidoTabla = contenidoTabla + obtenerFilaTablaSif012(datos[i]);
			else
				i = fin;
		}		
		
		document.getElementById("datosNomina").innerHTML = obtenerHeaderGrillaSif012() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacionSif012(paginas);
	}	
	
	/**Funcion que discrimina la modificacion.*/
	function discriminaModificar(idCorrelativo,rut, dv){//FRM AGREGAR correlativo a esta funcion.
	
		var correlativo = idCorrelativo;
		var rut1 = rut;
		var dv2 = dv;
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, width=1000, height=900";
	
		//--- Nuevo ---
		
		var L = 1200;            // Largo de ventana Modal
	  	var A = 1200;            // Ancho de Ventana Modal
 		var X = event.screenX;
  		var Y = event.screenY;
  		tam = "dialogTop:"+ (Y) +"px;dialogLeft:"+ (X)  +"px;dialogHeight:"+ L +"px;dialogWidth:"+ A +"px;directories:no;menubar:no;status:no;help:no";
	
		if(seleccionCheck == 2)
		{
			//window.open("./pages/ModifSif011.jsp?rut="+rut1+"&dv="+dv2+"&idsif="+correlativo,"",opciones);
			showModalDialog("./pages/AgregarSif012.jsp?idsif="+correlativo, 'xx', tam);
			obtenerValorYSeleccionarGrilla();	
		}
		
		if(seleccionCheck == 3)
		{
			//window.open("./pages/ModifSif011.jsp?rut="+rut1+"&dv="+dv2+"&idsif="+correlativo,"",opciones);
			showModalDialog("./pages/ModifSif011.jsp?rut="+rut1+"&dv="+dv2+"&idsif="+correlativo, 'xx', tam);
			obtenerValorYSeleccionarGrilla();
		}
		
		if(seleccionCheck == 4)
		{
			//window.open("./pages/ModifSif011.jsp?rut="+rut1+"&dv="+dv2+"&idsif="+correlativo,"",opciones);
			showModalDialog("./pages/AgregarSif011.jsp?idsif="+correlativo, 'xx', tam);
			obtenerValorYSeleccionarGrilla();
		}
		
		if(seleccionCheck == 5 || seleccionCheck == 6)
		{
			//window.open("./pages/ModifSif012.jsp?rut="+rut1+"&dv="+dv2+"&idsif="+correlativo,"",opciones);
			showModalDialog("./pages/ModifSif012.jsp?rut="+rut1+"&dv="+dv2+"&idsif="+correlativo, 'xx', tam);			
			obtenerValorYSeleccionarGrilla();
		}	
	}
	
	function seleccionaRegistroUnico(){
		
		var chkSeleccion = false;
		
		if(datos.length == 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					if(seleccionCheck == 2 || seleccionCheck == 3){
						correlativo_global = arrayCheck[i].id_sif011;
						rut_global = arrayCheck[i].rut_empresa;
						dv_global = arrayCheck[i].dv_empresa;
						obtenerDatosSif011(seleccionCheck);
						discriminaModificar(correlativo_global,rut_global, dv_global);
					}
					
					if(seleccionCheck == 4 || seleccionCheck == 5 || seleccionCheck == 6){
						correlativo_global = arrayCheck[i].id_sif012;
						rut_global = arrayCheck[i].rut_empresa;
						dv_global = arrayCheck[i].dv_empresa;

						obtenerDatosSif012(seleccionCheck);
						discriminaModificar(correlativo_global,rut_global, dv_global);
					}
				}
			}
		}			
	}
	
	function seleccionaRegistroNoUnico(){
		
		var chkSeleccion = false;
		
		if(datos.length > 1)
		{
			for(i=0; i<datos.length; i++)
			{
				if(arrayCheck[i].flagCheck == 1)
				{
					chkSeleccion = true;
					if(seleccionCheck == 2 || seleccionCheck == 3){
						correlativo_global = arrayCheck[i].id_sif011;
						rut_global = arrayCheck[i].rut_empresa;
						dv_global = arrayCheck[i].dv_empresa;
						obtenerDatosSif011(seleccionCheck);
						discriminaModificar(correlativo_global,rut_global, dv_global);
					}
					
					if(seleccionCheck == 4 || seleccionCheck == 5 || seleccionCheck == 6){
						correlativo_global = arrayCheck[i].id_sif012;
						rut_global = arrayCheck[i].rut_empresa;
						dv_global = arrayCheck[i].dv_empresa;
						obtenerDatosSif012(seleccionCheck);
						discriminaModificar(correlativo_global,rut_global, dv_global);
					}
				}
			}
		}	
	}
	
	function seleccionaFuncionDeRegistros(){
		
		if(datos.length == 1){
			seleccionaRegistroUnico();
		}else{
			seleccionaRegistroNoUnico();
		}
	}
		
</script>
</head>
<body onload="imprimirGlosaPeriodo();">
<html:form action="/listadoErrores.do">
	<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="numeroMes" value="0">

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
					<td><strong><p1>LISTADO DE ERRORES</p1></strong></td>
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
					<td><input type="text" name="txt_MesProceso" id="txt_MesProceso" disabled="true" size="20"/></td>
				</tr>
			  </table>
		   </td>	
		</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr>

			<td>
				1.- Campos de Búsqueda. Elija una opción para iniciar la búsqueda.
			</td>
		</tr>
		<tr>
			<table border="1" width="970" rules="groups">
				<tr>
					<td width="16%">Filtro de Búsqueda</td>
					<td colspan="1"></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"></td>
					<td colspan="1">
						<input type="radio" name="rbt_listaErrores" id="rbt_listaErroresCero" value="0" checked>Seleccione<br>
						<input type="radio" name="rbt_listaErrores" id="rbt_listaErroresUno" value="2">Corresponde a Retro (Sif011)<br>
						<input type="radio" name="rbt_listaErrores" id="rbt_listaErroresDos" value="3">Inválido Causante (Sif011)<br>
						<input type="radio" name="rbt_listaErrores" id="rbt_listaErroresTres" value="4">Corresponde a Mes (Sif012)<br>
						<input type="radio" name="rbt_listaErrores" id="rbt_listaErroresCuatro" value="5">Excede Periodo Máximo (Sif012)<br>
						<input type="radio" name="rbt_listaErrores" id="rbt_listaErroresCinco" value="6">Inválido Causante (Sif012)<br>					
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="16%"></td>
					<td colspan="1"></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3" align="center">
						<input type="button" name="btn_buscar" id="btn_buscar" class="btn_limp" value="Buscar" onClick="obtenerValorYSeleccionarGrilla();"/>
					</td>
				</tr>
			</table>
		</tr>
		<br>
		<div name="barraScroll" id="barraScroll" style="position: static; margin-top: 0px; margin-left: 0px; width: 965px; height: 200px; border: 1px solid; overflow: scroll;">
			<div name="datosNomina" id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 2200px; height: 150px;">
  			</div>
  		</div>
  		<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 20px;">
		</div>
		<!-- <table width="970">
			<tr>
				<td height="37" align="rigth" width="805">
					<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);" />
					<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="javascript:closeSesion()" />
				</td>
			</tr>
		</table> -->
	</table>
	</div>
</html:form>
</body>
</html:html>



