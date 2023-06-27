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

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/Calendario.js"></script>

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/RepNominaApoAfiDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"
	src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript">
	var datos = new Array();
	var datosTemp = new Array();
	var cantidadRegistrosPorPagina = 10;
	
	function cargaCabecera(){
		document.getElementById("datosNomina").innerHTML = 	
			'<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Mes Aporte</td>'+			   
	            	//JLGN 18-02-2013 Cambio de Fecha Vigencia a Fecha Vencimiento
	            	//'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Vigencia</td>'+		            		   
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Vencimiento</td>'+
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Pago</td>'+		            		            		   
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Forma de Pago</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Tipo Pago</td>'+	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Valor Pago</td>'+	       	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Aporte</td>'+	            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado Aporte</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto Pendiente</td>'+	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto Pago</td>'+	      		            	
	        	'</tr>'+
	        '</table>';
	}
		
	function asignaValor(a){
		document.RepNomApoAfiForm.opcion.value = a;
	}

	function enviaFormulario(a){	
		asignaValor(a);
		document.RepNomApoAfiForm.submit();
	}
	
	function cargarDatosEnGrilla(){
		var contenidoTabla = "";
	
		for(var i=0;i<datos.length;i++){
			if(i < cantidadRegistrosPorPagina)
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
				
		document.getElementById("datosNomina").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		
		generarPaginacion();
		// Se agrega estado 5 Deuda asumida por LA
		
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
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Mes Aporte</td>'+			   
	            	//JLGN 18-02-2013 Cambio de Fecha Vigencia a Fecha Vencimiento
	            	//'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Vigencia</td>'+
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Vencimiento</td>'+		            		   
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha Pago</td>'+		            		            		   
	            	'<td height="20" width="90" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Forma de Pago</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Tipo Pago</td>'+	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Valor Pago</td>'+	  	            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Aporte</td>'+	            	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado Aporte</td>'+
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto Pendiente</td>'+	
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto Pago</td>'+	      		            	
	        	'</tr>';
	}
	
	function obtenerFilaTabla(dato){
		var texto =  " <tr> "+
							"<td class='texto' align='center'>"+dato.mesAporte+"</td>"+
							"<td class='texto' align='center'>"+dato.fechaVigencia+"</td>"+
							"<td class='texto' align='center'>"+dato.fechaPago+"</td>"+
							"<td class='texto' align='center'>"+dato.formaPago+"</td>"+
							"<td class='texto' align='center'>"+dato.tipoPago+"</td>"+
							"<td class='texto' align='center'>"+dato.valorPago+"</td>"+
							"<td class='texto' align='center'>"+dato.montoAporte+"</td>"+
							"<td class='texto' align='center'>"+dato.estado+"</td>"+
							"<td class='texto' align='center'>"+dato.montoPendiente+"</td>"+
							"<td class='texto' align='center'>"+dato.montoPago+"</td>"+
							"</tr>";
							
		return texto;
	}
	
	// funcion para consultar aporte
	function consultaRepNominaApoAfiDWR(){
	
		var rut = document.RepNomApoAfiForm.txt_rut.value;
		//valida ingreso de rut correcto
		if ((Trim(document.RepNomApoAfiForm.txt_rut.value) == "")&& (Trim(document.RepNomApoAfiForm.txt_NNumVerif.value) == "")){
			alert("Debe ingresar N° RUT y su dígito verificador para poder generar la nómina de aportes de afiliado.");	
			document.RepNomApoAfiForm.txt_nombreAfiliado.value = "";
			document.RepNomApoAfiForm.txt_apellidoPaternoAfiliado.value = "";			
			document.RepNomApoAfiForm.txt_apellidoMaternoAfiliado.value = "";
			document.RepNomApoAfiForm.txt_estadoAfiliado.value = "";
			document.RepNomApoAfiForm.txt_oficinaAfiliacion.value = "";
		}
		
		else if (((Trim(document.RepNomApoAfiForm.txt_rut.value) == "")&& (Trim(document.RepNomApoAfiForm.txt_NNumVerif.value) != "")) || ((Trim(document.RepNomApoAfiForm.txt_rut.value) != "")&& (Trim(document.RepNomApoAfiForm.txt_NNumVerif.value) == ""))){
			alert("Si ingresa el campo N° RUT debe ingresar el dígito verificador y viceversa.");	
			document.RepNomApoAfiForm.txt_nombreAfiliado.value = "";
			document.RepNomApoAfiForm.txt_apellidoPaternoAfiliado.value = "";			
			document.RepNomApoAfiForm.txt_apellidoMaternoAfiliado.value = "";
			document.RepNomApoAfiForm.txt_estadoAfiliado.value = "";
			document.RepNomApoAfiForm.txt_oficinaAfiliacion.value = "";
		}
		
		// si rut ingresado es correcto realiza consulta
    	else if(ValidadorRUT(document.RepNomApoAfiForm.txt_rut.value,document.RepNomApoAfiForm.txt_NNumVerif.value)){
    		
	    	 /*RepNominaApoAfiDWR.consultaRepNominaApoAfiEstados(rut, function(data){
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
			});*/
    	
    	
			RepNominaApoAfiDWR.consultaRepNominaApoAfi(rut, function(data){	
				datos = data.repNominaApoAfi;
				document.getElementById("datosNomina").innerHTML = "";			
				if(datos != null){
					cargarDatosEnGrilla();
				}				
				document.RepNomApoAfiForm.archivo.value = data.archivoInforme;
				if (data.nombreAfiliado == null){
					document.RepNomApoAfiForm.txt_nombreAfiliado.value = "";
				}
				else{
					document.RepNomApoAfiForm.txt_nombreAfiliado.value = data.nombreAfiliado;
				}
				if (data.apellidoPaternoAfiliado == null){
					document.RepNomApoAfiForm.txt_apellidoPaternoAfiliado.value = "";
				}
				else{
					document.RepNomApoAfiForm.txt_apellidoPaternoAfiliado.value = data.apellidoPaternoAfiliado;
				}
				if (data.apellidoMaternoAfiliado == null){
					document.RepNomApoAfiForm.txt_apellidoMaternoAfiliado.value = "";
				}
				else{
					document.RepNomApoAfiForm.txt_apellidoMaternoAfiliado.value = data.apellidoMaternoAfiliado;
				}
				if (data.estadoAfiliado == null){
					document.RepNomApoAfiForm.txt_estadoAfiliado.value = "";
				}
				else{
					document.RepNomApoAfiForm.txt_estadoAfiliado.value = data.estadoAfiliado;	
				}	
				if (data.oficinaAfiliacion == null){
					document.RepNomApoAfiForm.txt_oficinaAfiliacion.value = "";
				}
				else{
					document.RepNomApoAfiForm.txt_oficinaAfiliacion.value = data.oficinaAfiliacion;	
				}	
				// si no existe nombres y apellidos
				if (data.nombreAfiliado == null &&	data.apellidoPaternoAfiliado == null && data.apellidoMaternoAfiliado == null){
					alert("El Rut no existe en el sistema");
				}
			});			
		}
			
	}
	
</script>
</head>
<body onload="cargaCabecera();">
<html:form action="/repNominaApo.do">
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
					<td><strong><p1>CONSULTA HISTÓRICA PAGO APORTE</p1></strong></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="970" border="0">
		<tr>
				
			<td width="150">
			    <strong>Nº Rut *</strong> 
			</td>
			<td width="250">
			    <input type="text" name="txt_rut" id="txt_rut" size="10" maxlength="9" onkeypress="Upper(this,'N')"/> <strong>
			- </strong> <input type="text" name="txt_NNumVerif" id="txt_NNumVerif"
				size="2" maxlength="1" onkeypress="Upper(this,'D')" onchange="ValidadorRUT(document.ModAfiForm.txt_rut.value,this.value)"/> 
			    <input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onclick="consultaRepNominaApoAfiDWR();"/>	
			</td>
		</tr>
		<tr>
			<td width="150">
				<b>Nombres</b>
			</td>
			<td width="250">
			<html:text property="txt_nombreAfiliado"styleClass="txt_nombreAfiliado" disabled="true" size="50" value="" />
			</td>
			<td width="150">
				<b>Apellido Paterno</b>
			</td>
			<td width="250">
				<html:text property="txt_apellidoPaternoAfiliado" styleClass="txt_apellidoPaternoAfiliado" disabled="true" size= "50" value=""/>
			</td>
		</tr>
		<tr>
			<td width="150">
				<b>Apellido Materno</b>
			</td>
			<td width="250">
				<html:text property="txt_apellidoMaternoAfiliado" styleClass="txt_apellidoMaternoAfiliado" disabled="true" size= "50" value=""/>
			</td>
			<td width="150">
				<b>Estado Afiliado</b>
			</td>
			<td width="250">
				<html:text property="txt_estadoAfiliado" styleClass="txt_estadoAfiliado" disabled="true" size= "25" value=""/>
			</td>
		</tr>
		<tr>
			<td width="150">
				<b>Oficina</b>
			</td>
			<td width="250">
				<html:text property="txt_oficinaAfiliacion" styleClass="txt_oficinaAfiliacion" disabled="true" size= "50" value=""/>
			</td>			
		</tr>
		
	</table>
	
	<div id="datosNomina" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 300px;">
	</div>
	
	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 30px;">
	</div>	
	
	<table width="970" border="0">	
		<tr>
			<td align="right" width="968">
				<input type="submit" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);">&nbsp; 			
			    &nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>
</html:form>
</body>
</html:html>