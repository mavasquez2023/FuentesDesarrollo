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
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/Calendario.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2"	src="/IndependientesWEB/dwr/interface/HistoricoBeneficiosDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"	src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"	src="/IndependientesWEB/dwr/interface/RepNominaApoAfiDWR.js"></script>

<script type="text/javascript">

	//--- INI Grilla ---
	
	var arregloPerfiles = null;
	var datos = new Array();
	var cantidadRegistrosPorPagina = 14;
	var folioSeleccionado = 0;
	var estadoSeleccionado = 0;
	var paginaActual = 1;
	
	function cargaCabeceras()
	{
		document.getElementById("datosHistorico").innerHTML = obtenerHeaderTabla();
	}
	
	function cargarDatosEnGrilla()
	{
		var contenidoTabla = "";
	
		for(var i = 0; i < datos.length; i++)
		{
			if(i < cantidadRegistrosPorPagina)
				contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
		}
		
		document.getElementById("datosHistorico").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";

		generarPaginacion();
	}

	function obtenerFilaTabla(dato)
	{
		var check = "";
		var reversa = "";
	
		if (dato.folioReversado == "0")
		{
			reversa = "N/A";
		}else{
			reversa = dato.folioReversado;
		}
	
		if (dato.estado == 4 || dato.estado == 5)
		{
			check = "disabled='true' ";
		}else{
			if(dato.folio == folioSeleccionado)
			{
				check = "checked='true' ";
			}
		}
	
		var texto =  " <tr> "+
							"<td><input type='checkbox' name='chk_CheckGrid' id='chk_CheckGrid' value='1' " + check + " onClick='cambiaFlagCheck(" + dato.folio + ");' /></td>"+
							"<td class='texto' align='center'>" + dato.folio + "</td>" +
							"<td class='texto' align='center'>" + dato.glosaCortaBeneficio + "</td>" +
							"<td class='texto' align='center'>" + dato.strFechaSolicitud + "</td>" +
							"<td class='texto' align='center'>" + dato.strRutCausante + "</td>" +
							"<td class='texto' align='center'>" + dato.strMontoPagar + "</td>" +
							"<td class='texto' align='center'>" + dato.glosaEstado + "</td>" +
							"<td class='texto' align='center'>" + dato.strIdAnalista + "</td>" +
							"<td class='texto' align='center'>" + reversa + "</td>" +
						"</tr>";
							
		return texto;
	}

	function obtenerHeaderTabla()
	{
		return '<table width="100%" align="center" cellpadding="0" cellspacing="1">'+
				'<tr>'+
	            	'<td height="20" width="10" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center"></td>' +
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Folio</td>' +
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Beneficio</td>' +
	            	'<td height="20" width="80" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Fecha</td>' +
	            	'<td height="20" width="60" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT Causante</td>' +
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Monto</td>' +
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Estado</td>' +
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">RUT Analista</td>' +
	            	'<td height="20" width="100" valign="middle" bgcolor="#1C74A9" class="textobold" style="color: #fff; text-align: center">Folio Reversa</td>' +
	        	'</tr>';
	}

	function cambiaFlagCheck(folio)
	{
		if(folioSeleccionado == 0)
		{
			folioSeleccionado = folio;
			estadoSeleccionado = obtenerEstado(folio);
		}else{
			if(folioSeleccionado == folio)
			{
				folioSeleccionado = 0;
				estadoSeleccionado = 0;
			}else{
				folioSeleccionado = folio;
				estadoSeleccionado = obtenerEstado(folio);
			}
		}
		activarBotones();
	}

	function activarBotones()
	{
		var botonAnula = null;
		var botonReversa = null;
		
		botonAnula = document.getElementById("btn_Anular");
		botonReversa = document.getElementById("btn_Reversar");
		
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		
		if (validarPerfiles(arregloPerfiles, "1")){
			botonAnula.disabled = false;
			//botonReversa.disabled = false;
		}		
		if (validarPerfiles(arregloPerfiles, "2")){
			botonAnula.disabled = false;
			//botonReversa.disabled = false;
		}				
		if (validarPerfiles(arregloPerfiles, "6")){
			botonAnula.disabled = false;
			botonReversa.disabled = false;
		}
		
		if(estadoSeleccionado == 1 || estadoSeleccionado == 2)
		{
			botonAnula.disabled = false;
			botonReversa.disabled = true;
		}else if(estadoSeleccionado == 3)
		{
			botonAnula.disabled = true;
			botonReversa.disabled = false;
		}else
		{
			botonAnula.disabled = true;
			botonReversa.disabled = true;
		}
		paginarResultados(paginaActual);
	}

	function obtenerEstado(folio)
	{
		for(var i = 0; i < datos.length; i++)
		{
			if(datos[i].folio == folio)
				return datos[i].estado;
		}
		return 0;
	}

	function generarPaginacion()
	{
		var paginas = (datos.length/cantidadRegistrosPorPagina) + "";
		
		if(datos.length % cantidadRegistrosPorPagina == 0)
			paginas = parseInt(paginas.split("\.")[0]);
		else
			paginas = parseInt(paginas.split("\.")[0])+1;
		
		var texto = "<font class='texto'>Se ha(n) encontrado "+datos.length+" Solicitud(es)</font>";		
		
		for(var i=0; i<paginas;i++)
		{
			texto = texto +" "+ "<a href='#' onclick='paginarResultados("+(i+1)+");'>"+(i + 1)+"</a>" ;
		}

		document.getElementById('datosPaginacion').innerHTML = "<table><tr><td align='center'>" + texto + '</td></tr></table>';
	}

	function paginarResultados(pagina)
	{
		paginaActual = pagina;
		
		var inicio = (pagina-1)*cantidadRegistrosPorPagina;
		var fin = (pagina)*cantidadRegistrosPorPagina;
		document.getElementById("datosHistorico").innerHTML = "";
		var contenidoTabla = "";
		
		for(var i=inicio;i<fin;i++)
		{
			if(datos[i]!= null)				
				var contenidoTabla = contenidoTabla + obtenerFilaTabla(datos[i]);
			else{
				i = fin;
			}
		}		
		document.getElementById("datosHistorico").innerHTML = obtenerHeaderTabla() + contenidoTabla + "</table>";
		var paginas = (datos.length/10)+""; 
		paginas = parseInt(paginas.split("\.")[0])+1;
		generarPaginacion(paginas);
	}

	//--- FIN Grilla ---

	function consultaHistoricoBeneficiosDWR()
	{
		var rut = document.ConsHistBenef.txt_NRut.value;
		var dv = document.ConsHistBenef.txt_NNumVerif.value;
		
		paginaActual = 1;
		
		if(validaRut(rut, dv)){
		
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
		
			HistoricoBeneficiosDWR.consultaHistoricoBeneficios(rut, function(data){
			
				if (data.idPersonaAfiliado != 0){
				
				datos = data.listaBeneficios;
			
				document.getElementById("datosHistorico").innerHTML = "";
			
				if(datos != null){
					cargarDatosEnGrilla();
					botonAnula = document.getElementById("btn_Anular");
                    botonReversa = document.getElementById("btn_Reversar");
                    botonActualizar = document.getElementById("btn_Actualizar");
                    botonActualizar.disabled = false;
                    botonAnula.disabled = true;
                    botonReversa.disabled = true;
	
				}
					document.ConsHistBenef.txt_estadoAfiliado.value = data.estadoAfiliado;
					document.ConsHistBenef.txt_nombreAfiliado.value = data.nombreAfiliado;
					document.ConsHistBenef.txt_cantReembolsos.value = data.cantReembolsos;
					document.ConsHistBenef.txt_montoReembolsado.value = data.montoReembolsado;
				}
				else{
					document.ConsHistBenef.txt_estadoAfiliado.value = "";
					document.ConsHistBenef.txt_nombreAfiliado.value = "";
					document.ConsHistBenef.txt_cantReembolsos.value = "0";
					document.ConsHistBenef.txt_montoReembolsado.value = "";
					alert("No existe el afiliado");
				}
			});
		}else{
			alert("El RUT ingresado es inválido.");
		}
	}
   
   	function anularBeneficio()
   	{
   		if (folioSeleccionado != 0)
   		{
	   		HistoricoBeneficiosDWR.anularBeneficio(folioSeleccionado, function(data){
	   		
	   			if(data.codError == 0)
	   			{
	   				alert("Beneficio anulado con éxito");
	   				consultaHistoricoBeneficiosDWR();
	   			}else{
	   				alert("Error = " + data.desError);
	   			}
	   		});
	   	}else{
	   		alert("Debe seleccionar un Beneficio a anular.");
	   	}	
   	}
   
   	function reversarBeneficio()
   	{
   		if (folioSeleccionado != 0)
   		{
	   		HistoricoBeneficiosDWR.reversarBeneficio(folioSeleccionado, function(data){
	   		
	   			if(data.codError == 0)
	   			{
	   				alert("Beneficio reversado con éxito");
	   				consultaHistoricoBeneficiosDWR();
	   			}else{
	   				alert("Error = " + data.desError);
	   			}
	   		});
	   	}else{
	   		alert("Debe seleccionar un Beneficio a reversar.");
	   	}	
   	}
   
	function asignaValor(a)
	{
		document.ConsHistBenef.opcion.value = a;
	}

	function enviaFormulario(a){
	
		asignaValor(a);
		document.ConsHistBenef.submit();
	}

	//--- Perfilamiento ---
	
	/*function bloqueaCampos(){
		
		if ('<%=session.getAttribute("Perfil")%>' == "1"){

			//document.ConsHistBenef.btn_Buscar.disabled = true;
		}
	}*/
	
	//Inicio REQ 6988 JLGN 11-03-2013
	function actualizarHistBeneficioDWR()
   	{
   		var rut = document.ConsHistBenef.txt_NRut.value;
   		HistoricoBeneficiosDWR.actualizarBeneficio(rut, function(data){
	   		
  			if(data.codError == 0)
  			{
  				consultaHistoricoBeneficiosDWR();
  			}else{
  				alert("Error = " + data.desError);
  			}
  		});	   		
   	}
	//Fin REQ 6988 JLGN 11-03-2013	
	
</script>
</head>
<body onload="cargaCabeceras();">
<html:form action="/consHistBenef.do">
<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	
	<h4><b>CONSULTA HISTÓRICA DE BENEFICIOS</b></h4>
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
			<td width="400">
				<strong>N° RUT *</strong> 
			    <input type="text" name="txt_NRut" id="txt_NRut" size="10" maxlength="9" onkeypress="Upper(this,'N')"/>
				<strong> - </strong> 
				<input type="text" name="txt_NNumVerif" id="txt_NNumVerif" size="3" maxlength="1" onkeypress="Upper(this,'D')" />
				<input type="button" align="right" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onclick="consultaHistoricoBeneficiosDWR();"/>
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
				<p>Beneficios Cobrados</p>
			</td>
			<td width="150">
				<html:text property="txt_cantReembolsos" styleClass="txt_cantReembolsos" disabled="true" size="15" value="0" />
			</td>
			<td width="150">
				<p>Total Cobrado</p>
			</td>
			<td width="150">	
				<html:text property="txt_montoReembolsado" styleClass="txt_montoReembolsado" disabled="true" size="15" value="" />
			</td>
		</tr>
		<!-- Inicio REQ 6988 JLGN 11-03-2013 -->
		<tr>
			<td width="150">				
			</td>
			<td width="150">
			</td>
			<td width="150">
			</td>
			<td width="150" align="right">	
				<input type="button" align="right" name="btn_Actualizar" id="btn_Actualizar" class="btn_limp" value="Actualizar" disabled="true" onclick="actualizarHistBeneficioDWR();"/>
			</td>
		</tr>		
		<!-- Fin REQ 6988 JLGN 11-03-2013 -->
	</table>
	
	<div id="datosHistorico" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 300px;">
	</div>
	
	<div id="datosPaginacion" style="position:static; margin-top: 0px; margin-left: 0px; width: 965px; height: 30px;">
	</div>	

	<table width="970" border="0">	
		<tr>
			<td height="50" align="right" width="968">
				<input type="button" name="btn_Anular" id="btn_Anular" class="btn_limp" value="Anular" disabled="true" onclick="anularBeneficio();">&nbsp;
			    &nbsp;&nbsp;&nbsp;
			    <input type="button" name="btn_Reversar" id="btn_Reversar" class="btn_limp" value="Reversar" disabled="true" onclick="reversarBeneficio();">&nbsp; 			
			    &nbsp;&nbsp;&nbsp;
				<input type="submit" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);">&nbsp; 			
			    &nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>
</html:form>
</body>
</html:html>