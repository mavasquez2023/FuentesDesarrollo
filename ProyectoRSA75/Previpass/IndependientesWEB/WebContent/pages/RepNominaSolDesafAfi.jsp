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
	src="/IndependientesWEB/dwr/interface/RepNominaSolDesafAfiDWR.js"></script>
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
							"<td class='texto' align='center'>"+dato.lugarAfiliacion+"</td>"+
							"<td class='texto' align='center'>"+dato.analista+"</td>"+
							"<td class='texto' align='center'>"+dato.estadoSolicitud+"</td>"+
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
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Oficina</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT Analista</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Est. Solicitud</td>'+          		            	
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
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Oficina</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT Analista</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Est. Solicitud</td>'+          		            	
	        	'</tr>'+
	        '</table>';
	}

	//--- FIN Grilla ---

	function consultaRepNominaSolAfiDWR(){
	
		var fechaIni = document.RepNomSolDesafAfiForm.txt_FecDesde.value;
		var fechaFin = document.RepNomSolDesafAfiForm.txt_FecHasta.value;
		var oficina = document.RepNomSolDesafAfiForm.dbx_Oficina.value;
		var estado = document.RepNomSolDesafAfiForm.dbx_EstSolicitud.value;
		var user = "<%=session.getAttribute("IDAnalista")%>";
		var fechaActual = "<%=session.getAttribute("FechaSistema")%>";
		
		cantEnviosMail = 0;
		
		if(validaConsultaInforme(oficina)){
		
			RepNominaSolDesafAfiDWR.consultaRepNominaSolDesafAfi(fechaIni, fechaFin, oficina, estado, user, fechaActual, function(data){
			
				datos = data.lisRepNominaSolDesafAfi;
			
				document.getElementById("datosNomina").innerHTML = "";
			
				if(datos != null){
					cargarDatosEnGrilla();
				}
				
				document.RepNomSolDesafAfiForm.archivo.value = data.archivoInforme;
				
			});
			
			document.RepNomSolDesafAfiForm.txt_FecDesdeTmp.value = document.RepNomSolDesafAfiForm.txt_FecDesde.value;
			document.RepNomSolDesafAfiForm.txt_FecHastaTmp.value = document.RepNomSolDesafAfiForm.txt_FecHasta.value;
			document.RepNomSolDesafAfiForm.dbx_OficinaTmp.value = document.RepNomSolDesafAfiForm.dbx_Oficina.value;
			document.RepNomSolDesafAfiForm.dbx_EstSolicitudTmp.value = document.RepNomSolDesafAfiForm.dbx_EstSolicitud.value;
			
			document.RepNomSolDesafAfiForm.btn_GuardarAfi.disabled = false;
			document.RepNomSolDesafAfiForm.btn_GuardarAfi0.disabled = false;
		}
	}
   
   	function validaConsultaInforme(oficina){
   	
   		var fechaIni = document.RepNomSolDesafAfiForm.txt_FecDesde.value;
		var fechaFin = document.RepNomSolDesafAfiForm.txt_FecHasta.value;
   	
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

		document.RepNomSolDesafAfiForm.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.RepNomSolDesafAfiForm.submit();
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
			archivo = document.RepNomSolDesafAfiForm.archivo.value;			
		}
		
		var user = "<%=session.getAttribute("NombreAnalista")%>" + " " + "<%=session.getAttribute("ApePatAnalista")%>" + " " + "<%=session.getAttribute("ApeMatAnalista")%>";
		
		var fechaIni = document.RepNomSolDesafAfiForm.txt_FecDesdeTmp.value;
		var fechaFin = document.RepNomSolDesafAfiForm.txt_FecHastaTmp.value;
		var oficina = document.RepNomSolDesafAfiForm.dbx_OficinaTmp.value;
		var estado = document.RepNomSolDesafAfiForm.dbx_EstSolicitudTmp.value;
		
		cantEnviosMail = cantEnviosMail + 1;
		
		RepNominaSolDesafAfiDWR.enviarMail(archivo, user, fechaIni, fechaFin, oficina, function(data){
	
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
			document.RepNomSolDesafAfiForm.txt_Fechasta.value = document.RepNomSolDesafAfiForm.txt_FecDesde.value;
		}	
	}
	
	function bloqueaCampos(){
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		
		//if ('<%=session.getAttribute("Perfil")%>' == "1"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfilesFullAnalista(arregloPerfiles)){
			document.RepNomSolDesafAfiForm.dbx_EstSolicitud.disabled = true;
		}
		
		$('#txt_FecDesde').each(function(){
		    $(this).datepicker({
			      showOn: "button",
			      buttonImage: "/IndependientesWEB/images/Calendar.jpg",
			      buttonImageOnly: true,
			      buttonText: "Seleccionar fecha",
			      onSelect: function(dateText) {
				    cambioFecha();
				  }
			});
		});
		
		
		if (validarPerfiles(arregloPerfiles, "2")){
			
			$('#txt_Fechasta').each(function(){
			    $(this).datepicker({
				      showOn: "button",
				      buttonImage: "/IndependientesWEB/images/Calendar.jpg",
				      buttonImageOnly: true,
				      buttonText: "Seleccionar fecha"
				      
				});
			});
			
		}
		
		
	}
	
	function verificaFechaHasta(){
	
		//if ('<%=session.getAttribute("Perfil")%>' == "2"){ //20120709 - ANA - ANTIGUO PERFILAMIENTO
		if (validarPerfiles(arregloPerfiles, "2")){
			//ShowCalendarFor(document.RepNomSolDesafAfiForm.txt_Fechasta);
		}	
	}
	
</script>
</head>
<body onload="cargaCabecera();bloqueaCampos();">
<html:form action="/RepNominaSolDesafAfi.do">
<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="archivo" value="">
	
	<input type="hidden" name="txt_FecDesdeTmp" value="0">
	<input type="hidden" name="txt_FecHastaTmp" value="0">
	<input type="hidden" name="dbx_OficinaTmp" value="0">
	<input type="hidden" name="dbx_EstSolicitudTmp" value="0">
	
	<h4><b>NÓMINA SOLICITUDES DE DESAFILIACIÓN</b></h4>
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
			    <input type="text" name="txt_FecDesde" id="txt_FecDesde" class="datepick" disabled="true" size="15" value='<%=session.getAttribute("FechaSistema")%>'/>
<!--			    <img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onclick="ShowCalendarFor(txt_FecDesde); cambioFecha();"/>-->
				<strong>Fecha Hasta</strong> 
				<input type="text" name="txt_FecHasta" id="txt_Fechasta" disabled="true" size="15" value='<%=session.getAttribute("FechaSistema")%>'/> 
<!--				<img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onclick="verificaFechaHasta();"/>-->
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
					<html:options collection="ListEstadoSolicitudDesafiliacionBox" property="codigo" labelProperty="glosa"/>
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
</html:html>