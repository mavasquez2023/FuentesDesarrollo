<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- <%@ taglib uri="/WEB-INF/c.tld" prefix="c"%> -->
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

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
	src="/IndependientesWEB/dwr/interface/ConsMasivaApoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript">
	var datos = new Array();
	var datosTemp = new Array();
	var cantidadRegistrosPorPagina = 9;	
	
	function cargaCabecera(){
	    document.getElementById("datosNomina").innerHTML = 	
			'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT</td>'+			   
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Paterno</td>'+		            		   
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Materno</td>'+		            		            		   
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Nombres</td>'+	            	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado Afiliado</td>'+	            	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Oficina Afiliado</td>'+            	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Renta Imponible</td>'+
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Mes Aporte</td>'+	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Vigencia</td>'+	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Pago</td>'+  	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Forma Pago</td>'+		
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Tipo Pago</td>'+  	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Valor Pago</td>'+	   	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto Aporte</td>'+
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+	 	          		            	
	        	'</tr>'+
	        '</table>';
	        
	        
	        $( "#txt_FecPrdDesde" ).datepicker({
		      	defaultDate: "+1w",
				showOn: "button",
				buttonImage: "/IndependientesWEB/images/Calendar.jpg",
				buttonImageOnly: true,
				buttonText: "Seleccionar fecha",
		      onClose: function( selectedDate ) {
		        $( "#txt_FecPrdHasta" ).datepicker( "option", "minDate", selectedDate );
		      }
		    });
		    $( "#txt_FecPrdHasta" ).datepicker({
		      	defaultDate: "+1w",
		      	showOn: "button",
				buttonImage: "/IndependientesWEB/images/Calendar.jpg",
				buttonImageOnly: true,
				buttonText: "Seleccionar fecha",
		      onClose: function( selectedDate ) {
		        $( "#txt_FecPrdDesde" ).datepicker( "option", "maxDate", selectedDate );
		      }
		    });
	        
	}
	
		
	function cargarDatosEnGrilla(){
		var contenidoTabla = "";
	
		for(var i=0;i<datos.length;i++){
			if(i < cantidadRegistrosPorPagina)
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
				
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		
		generarPaginacion();
		
	}
	
	function generarPaginacion(){
		var paginas = (datos.length/cantidadRegistrosPorPagina)+"";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
			
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" Aporte(s)</font>";		
		
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
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(paginas);
	}
	
	function obtenerHeaderTabla(){
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT</td>'+			   
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Paterno</td>'+		            		   
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Apellido Materno</td>'+		            		            		   
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Nombres</td>'+	            	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado Afiliado</td>'+	            	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Oficina Afiliado</td>'+            	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Renta Imponible</td>'+
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Mes Aporte</td>'+	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Vigencia</td>'+	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Pago</td>'+  	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Forma Pago</td>'+		
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Tipo Pago</td>'+  	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Valor Pago</td>'+	   	
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto Aporte</td>'+
	            	'<td height="20" width="70" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>'+	 	          		            	
	        	'</tr>';
	}
	
	function obtenerFilaTabla(dato){
		var texto =  " <tr> "+
						    "<td class='texto' align='center'>"+dato.rut+"</td>"+
							"<td class='texto' align='center'>"+dato.apellidoPaternoAfiliado+"</td>"+
							"<td class='texto' align='center'>"+dato.apellidoMaternoAfiliado+"</td>"+
							"<td class='texto' align='center'>"+dato.nombreAfiliado+"</td>"+
							"<td class='texto' align='center'>"+dato.estadoAfiliado+"</td>"+
							"<td class='texto' align='center'>"+dato.oficinaAfiliacion+"</td>"+
							"<td class='texto' align='center'>"+dato.rentaImponible+"</td>"+
							"<td class='texto' align='center'>"+dato.mesAporte+"</td>"+
							"<td class='texto' align='center'>"+dato.fechaVigencia+"</td>"+
							"<td class='texto' align='center'>"+dato.fechaPago+"</td>"+
							"<td class='texto' align='center'>"+dato.formaPago+"</td>"+
							"<td class='texto' align='center'>"+dato.tipoPago+"</td>"+
							"<td class='texto' align='center'>"+dato.valorPago+"</td>"+
							"<td class='texto' align='center'>"+dato.montoAporte+"</td>"+
							"<td class='texto' align='center'>"+dato.estadoAporte+"</td>"+
							"</tr>";
							
		return texto;
	}
	
	
	// funcion que realiza consulta de los aportes
	function consultaConsMasivaApoDWR(){
	    
		var fechaIni = document.ConsMasivaApoForm.txt_FecPrdDesde.value;
		var fechaFin = document.ConsMasivaApoForm.txt_FecPrdHasta.value;
		var fechaSistema = "<%=session.getAttribute("FechaSistema")%>";
		var user = "<%=session.getAttribute("IDAnalista")%>";
		var estado = document.ConsMasivaApoForm.dbx_EstAporte.value;
		var oficina = document.ConsMasivaApoForm.dbx_Oficina.value;
		var fechaMinima = "01/01/2012";
		
		// valida que mes de periodo desde no puede ser menor a 01/01/2012
		if(Comparar_Fecha(fechaIni,fechaMinima) == false)
		{
			alert("El 'Mes Aporte Desde' no puede ser menor al 01/01/2012");
			return;
		}
		// valida que mes de periodo desde no puede ser mayor a mes periodo hasta
		else if (Comparar_Fecha(fechaFin, fechaIni) == false)
		{
			alert("El 'Mes Aporte Desde' no puede ser mayor al 'Mes Aporte Hasta'");
			return;
		}
		else{
			ConsMasivaApoDWR.consultaMasivaApo(fechaIni, fechaFin, estado, oficina, user, fechaSistema, function(data){	
				datos = data.lisConsMasivaApoVO;
				document.getElementById("datosNomina").innerHTML = "";			
				if(datos != null){
					cargarDatosEnGrilla();
				}
				document.ConsMasivaApoForm.archivo.value = data.archivoInforme;
			});	
		}
		//habilita botón excel
		document.ConsMasivaApoForm.btn_GuardarAfi0.disabled = false;
		
	}
	
	
	function asignaValor(a){
		document.ConsMasivaApoForm.opcion.value = a;
	}

	function enviaFormulario(a){	
		asignaValor(a);
		document.ConsMasivaApoForm.submit();
	}
		
	
</script>
</head>
<body onload="cargaCabecera();">
<html:form action="/consMasivaApo.do">
<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	<input type="hidden" name="archivo" value="">

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
			<td height="25">
			<table border="0">
				<tr>
					<td><strong><p1>CONSULTA MASIVA DE APORTES</p1></strong></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="970" border="0">
		<tr>
			<td width="150">
				<b>Mes Aporte Desde *</b>
			</td>
			<td width="250">
			    <input type="text" name="txt_FecPrdDesde" id="txt_FecPrdDesde" disabled="true" size="15" value='<%=session.getAttribute("FechaSistema")%>'/><!--
			    <img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onclick="ShowCalendarFor(txt_FecPrdDesde);"/>
			--></td>
			<td width="150">
				<b>Mes Aporte Hasta *</b>
			</td>
			<td width="250">
			    <input type="text" name="txt_FecPrdHasta" id="txt_FecPrdHasta" disabled="true" size="15" value='<%=session.getAttribute("FechaSistema")%>'/><!--
			    <img style="cursor:hand" border="0" src="/IndependientesWEB/images/Calendar.jpg" width="16" height="16" onclick="ShowCalendarFor(txt_FecPrdHasta);"/>
				--><input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onclick="consultaConsMasivaApoDWR();"/>	
			</td>
		</tr>
		<tr>				
			<td width="150" >
			    <b>Estado Aporte</b> 
			</td>
			<td width="250">
			   <html:select property="dbx_EstAporte" styleClass="dbx_estAporte" value="-1">
					<option value="-1">Seleccione</option>
					<c:forEach items="${sessionScope.ListEstadoAporte}" var="opcion">
								<c:choose>
									<c:when test="${opcion.estado != '0'}" >
										<option value="<c:out value='${opcion.codigo}'/>">
											<c:out value='${opcion.glosa}'/>
										</option>
									</c:when>
								</c:choose>
							</c:forEach>
				</html:select>
			</td>
			<td width="150">
			    <b>Oficina</b>
			</td>
			<td width="250">
				<html:select property="dbx_Oficina" styleClass="dbx_oficina" value="0">
					<html:option value="0">Seleccione</html:option>
					<html:options collection="ListOficinas" property="codigo" labelProperty="glosa"/>
				</html:select> 
			</td>
		</tr>
		<tr>
			<td width="150">
			&nbsp;
			</td>
		</tr>
	</table>
	
	<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 970px; height: 347px;">
	</div>
	
	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 970px; height: 20px;">
	</div>	
	
	<table width="970" border="0">	
		<tr>
			<td align="right" width="968">
			    <input type="button" name="btn_GuardarAfi0" id="btn_GuardarAfi0" class="btn_limp" disabled="true" value="Excel" onclick="enviaFormulario(2);">
			    &nbsp;
				<input type="submit" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);">&nbsp; 			
			    &nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>
</html:form>
</body>
</html:html>