<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<META http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
<title>SISTEMA TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet"
	type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet" type="text/css" />
<link href="/IndependientesWEB/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery-ui.min.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/RepNominaSolAfiDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>

<script type="text/javascript">

	//--- INI Grilla ---
	
	var datos = new Array();
	var datosTemp = new Array();
	var cantidadRegistrosPorPagina = 15;
	var cantEnviosMail = 0;
	var arregloPerfiles = null;
	
	function cargarDatosEnGrilla(){
		var contenidoTabla = "";
	
		for(var i=0;i<datos.length;i++){
			if(i < cantidadRegistrosPorPagina)
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
		
		//document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "<tr><td colspan='10' id='paginacion' align='right'></td></tr></table>";
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";

		generarPaginacion();
	}

	function obtenerFilaTabla(dato){
		var texto =  " <tr> "+
							"<td></td>"+
							"<td class='texto' align='center'>"+dato.fechaIngreso+"</td>"+
							"<td class='texto' align='center'>"+dato.folio+"</td>"+
							"<td class='texto' align='center'>"+dato.rut+"</td>"+
							"<td class='texto' align='center'>"+dato.apellidoPaterno+"</td>"+
							"<td class='texto' align='center'>"+dato.apellidoMaterno+"</td>"+
							"<td class='texto' align='center'>"+dato.nombres+"</td>"+
							"<td class='texto' align='center'>"+dato.codigoVendedor+"</td>"+
							"<td class='texto' align='center'>"+dato.lugarAfiliacion+"</td>"+
							"<td class='texto' align='center'>"+dato.estadoSolicitud+"</td>"+
							"<td class='texto' align='center'>"+dato.rentaImponible+"</td>"+
							"</tr>";
							
		return texto;
	}

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
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Vendedor</td>'+			            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Lugar Afiliación</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Est. Solicitud</td>'+          		            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Renta Imponible</td>'+	         		            	
	        	'</tr>';
	}

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

		document.getElementById('datosPaginacion').innerHTML = "<table><tr><td align='center'>" + texto + '</td></tr></table>';
	}

	function paginarResultados(pagina){
	
		var inicio = (pagina-1)*cantidadRegistrosPorPagina;
		var fin = (pagina)*cantidadRegistrosPorPagina;
		document.getElementById("datosNomina").innerHTML = "";
		var contenidoTabla = "";
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
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Vendedor</td>'+			            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Lugar Afiliación</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Est. Solicitud</td>'+          		            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Renta Imponible</td>'+	         		            	
	        	'</tr>'+
	        '</table>';
	}

	//--- FIN Grilla ---

	function consultaRepNominaSolAfiDWR(){
	
		var fechaIni = document.RepNomSolAfiForm.txt_FecDesde.value;
		var fechaFin = document.RepNomSolAfiForm.txt_FecHasta.value;
		var oficina = document.RepNomSolAfiForm.dbx_Oficina.value;
		var estado = document.RepNomSolAfiForm.dbx_EstSolicitud.value;
		var user = "<%=session.getAttribute("IDAnalista")%>";
		var fechaActual = "<%=session.getAttribute("FechaSistema")%>";
		
		cantEnviosMail = 0;
		
		if(validaConsultaInforme(oficina)){
		
			RepNominaSolAfiDWR.consultaRepNominaSolAfi(fechaIni, fechaFin, oficina, estado, user, fechaActual, function(data){
			
				datos = data.lisRepNominaSolAfi;
			
				document.getElementById("datosNomina").innerHTML = "";
			
				if(datos != null){
					cargarDatosEnGrilla();
				}
				
				document.RepNomSolAfiForm.archivo.value = data.archivoInforme;
				
			});
			
			document.RepNomSolAfiForm.txt_FecDesdeTmp.value = document.RepNomSolAfiForm.txt_FecDesde.value;
			document.RepNomSolAfiForm.txt_FecHastaTmp.value = document.RepNomSolAfiForm.txt_FecHasta.value;
			document.RepNomSolAfiForm.dbx_OficinaTmp.value = document.RepNomSolAfiForm.dbx_Oficina.value;
			document.RepNomSolAfiForm.dbx_EstSolicitudTmp.value = document.RepNomSolAfiForm.dbx_EstSolicitud.value;
			
			document.RepNomSolAfiForm.btn_GuardarAfi.disabled = false;
			document.RepNomSolAfiForm.btn_GuardarAfi0.disabled = false;
		}
	}
   
   	function validaConsultaInforme(oficina){
   	
   		var fechaIni = document.RepNomSolAfiForm.txt_FecDesde.value;
		var fechaFin = document.RepNomSolAfiForm.txt_FecHasta.value;
   	
   		//if ('<%=session.getAttribute("Perfil")%>' == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles)){
			if (oficina == "0"){
				alert("Debe elegir una oficina para poder generar la nómina de solicitudes de afiliación.");
				return false;
			}
		}
		
		if (fechaIni != fechaFin){
		
			if(!Comparar_Fecha_Anyo(fechaIni, fechaFin, 0)){
				alert("La 'Fecha Desde' debe ser menor o igual a la 'Fecha Hasta'");
				return false;
			}
		}	
		
   		return true;
   	}
   
	function asignaValor(a){

		document.RepNomSolAfiForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.RepNomSolAfiForm.submit();
	}
	
	function validaEnvioMail(){
	
		if (cantEnviosMail > 0){
			var respuesta = confirm("Ya ha enviado el E-Mail. ¿Está seguro que desea enviarlo nuevamente?");
				
			if( respuesta == true){
				enviarMail();
			}
		}else{
			enviarMail();
		}
	}
	
	function enviarMail()
	{
		var archivo = "";
		
		if (datos.length != 0)//Esta validacion es para señalar que no hubo solicitudes por lo que no se envia archivo
		{
			archivo = document.RepNomSolAfiForm.archivo.value;			
		}
		
		var user = "<%=session.getAttribute("NombreAnalista")%>" + " " + "<%=session.getAttribute("ApePatAnalista")%>" + " " + "<%=session.getAttribute("ApeMatAnalista")%>";
		
		var fechaIni = document.RepNomSolAfiForm.txt_FecDesdeTmp.value;
		var fechaFin = document.RepNomSolAfiForm.txt_FecHastaTmp.value;
		var oficina = document.RepNomSolAfiForm.dbx_OficinaTmp.value;
		var estado = document.RepNomSolAfiForm.dbx_EstSolicitudTmp.value;
		
		cantEnviosMail = cantEnviosMail + 1;
		
		RepNominaSolAfiDWR.enviarMail(archivo, user, fechaIni, fechaFin, oficina, function(data){
	
			resp = data;
			
			if (resp == "OK"){
				alert("Correo enviado con éxito.");
			}else{
				alert("Error al enviar el correo.");
			}
		});	
	}
	
	//--- Perfilamiento ---
	function cambioFecha(){
	 
		//if ('<%=session.getAttribute("Perfil")%>' == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles)){
			document.RepNomSolAfiForm.txt_Fechasta.value = document.RepNomSolAfiForm.txt_FecDesde.value;
		}	
	}
	
	function bloqueaCampos(){
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		
		//if ('<%=session.getAttribute("Perfil")%>' == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles)){
			document.RepNomSolAfiForm.dbx_EstSolicitud.disabled = true;
		}
	}
	
	function verificaFechaHasta(){
	
		//if ('<%=session.getAttribute("Perfil")%>' == "2"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		//alert("Arreglo perfiles: " + arregloPerfiles);
		if (validarPerfiles(arregloPerfiles, "2")){
			//ShowCalendarFor(document.RepNomSolAfiForm.txt_Fechasta);
			$("#txt_Fechasta").datepicker();
			$("#txt_Fechasta").datepicker("show");
		}else{
			$("#txt_Fechasta").prop("disabled", true);
			alert('Perfil no autorizado');
		}
	}
	
</script>
</head>
<body onload="cargaCabecera();bloqueaCampos();">
<html:form action="/repNomina.do">
<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="archivo" value="">
	
	<input type="hidden" name="txt_FecDesdeTmp" value="0">
	<input type="hidden" name="txt_FecHastaTmp" value="0">
	<input type="hidden" name="dbx_OficinaTmp" value="0">
	<input type="hidden" name="dbx_EstSolicitudTmp" value="0">
	
	<h4><b>NÓMINA SOLICITUDES DE AFILIACIÓN</b></h4>
	<table width="970" border="0">
		<tr>
			<td align="right" width="970" colspan="3">
				<a href="#" align="right" onClick="enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);">Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td width="400"><!-- 970 -->
			    <strong>Fecha Desde</strong> 
			    <input type="text" name="txt_FecDesde" id="txt_FecDesde" size="15" value='<%=session.getAttribute("FechaSistema")%>'  onchange="cambioFecha();"/>
			    <!--<img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onclick="ShowCalendarFor(txt_FecDesde); cambioFecha();"/>-->
				<strong>Fecha Hasta</strong> 
				<input type="text" name="txt_FecHasta" id="txt_Fechasta" size="15" value='<%=session.getAttribute("FechaSistema")%>' onclick="verificaFechaHasta();"/> 
				<!--<img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onclick="verificaFechaHasta();"/>-->
			</td>
			<td width="20">
			</td>
			<td width="330">	
				<input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onclick="consultaRepNominaSolAfiDWR();"/>
			</td>
		</tr>
		<tr>
			<td width="400"> <!-- height="34"  -->
				<b>Oficina</b>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:select property="dbx_Oficina" styleClass="dbx_oficina" value="0">
					<html:option value="0">Seleccione</html:option>
					<html:options collection="ListOficinas" property="codigo" labelProperty="glosa"/>
				</html:select> 
			</td>
			<td width="20">
			</td>
			<td width="330">	
				<b>Estado Solicitud </b>
				&nbsp;&nbsp;&nbsp;
				<html:select property="dbx_EstSolicitud" styleClass="dbx_estSolicitud" value="0">
					<html:option value="0">Seleccione</html:option>
					<html:options collection="ListEstadoSolicitudAfiliacionBox" property="codigo" labelProperty="glosa"/>
				</html:select>
			</td>
		</tr>
	</table>
	
	<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 300px;">
	</div>
	
	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 30px;">
	</div>	

	<table width="970" border="0">	
		<tr>
			<td height="50" align="right" width="968">
				<input type="button" name="btn_GuardarAfi0" id="btn_GuardarAfi0" class="btn_limp" disabled="true" value="Excel" onclick="enviaFormulario(2);">
			    &nbsp;
				<input type="button" name="btn_GuardarAfi" id="btn_GuardarAfi" class="btn_limp" disabled="true" value="E-Mail" onclick="validaEnvioMail()";>
				&nbsp;
				<input type="submit" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);">&nbsp; 			
			    &nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>
</html:form>
</body>
<script type="text/javascript">
	$("#txt_FecDesde").datepicker();
</script>
</html:html>