<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/jquery.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" src="/IndependientesWEB/js/helper.js"></script>

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/MenuPrincipalDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2"src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>

<script type="text/javascript">
	
	var arregloPerfiles = null;
	var vectorOficinas = new Array();

	cargaArreglos();

	/*función que signa un valor al formulario.*/
	function asignaValor(a)
	{
		document.MenuPpalForm.opcion.value = a;
	}

	/*funcion envia formulario.*/
	function enviaFormulario(a)
	{
		asignaValor(a);
		document.MenuPpalForm.submit();
	}

	function cargaArreglos()
	{
		GeograficoDWR.obtenerLista("ListOficinasBox", function(data)
		{
			var oficina = null
			oficina = data;
			
			for (i = 0; i < oficina.length; i++)
			{
				vectorOficinas[i] = new ObjParametro(oficina[i].codigo, oficina[i].glosa);
			}
		});
	}
	
	function obtenerComboSeleccionOficina()
	{
		var cmb2 = document.getElementById("dbx_Oficinas");
		cmb2.options[0] = new Option("Seleccione", 0);	
	
		for(var j=0; j<vectorOficinas.length; j++)
		{
			var item = null;
			item = vectorOficinas[j];
			
			var cod = item.codigo;
			var txt = item.glosa;
				
			cmb2.options[j+1] = new Option(txt, cod);
		}
	}
	
	function verificaOficinaAnalista()
	{
		var idAnalista = "<%=session.getAttribute("IDAnalista")%>";
	
		MenuPrincipalDWR.verificaOficinaAnalista(idAnalista, function(data)
		{
			var resp = null;
			resp = data;
			
			if(resp == false)
			{
				mostrarRegistroOficina();
			}
		});
		
	}
	
	function mostrarRegistroOficina()
	{
		var texto = "";
		
		var idAnalista = "<%=session.getAttribute("IDAnalista")%>";
		var idFormateado = separadorDeMiles(idAnalista) + "-" + DigitoVerificadorRut(idAnalista);
		
		var nombreAnalista = "<%=session.getAttribute("NombreAnalista")%>" + " " + "<%=session.getAttribute("ApePatAnalista")%>" + " " + "<%=session.getAttribute("ApeMatAnalista")%>";
		
		texto = '<table width="100%" align="center" cellpadding="0" cellspacing="1" class="tabla">'+
				'<tr>'+ 
					'<td colspan="5" width="450">'+
						'<p align="center"><b>Seleccione su Oficina</b></p>' + 
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td colspan="4" width="450">'+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td width="20">'+
					'</td>'+
					'<td width="70">'+
						'<strong>N° RUT:</strong>'+
					'</td>'+
					'<td width="280">'+
						idFormateado +
					'</td>'+
					'<td width="10">'+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td >'+
					'</td>'+
					'<td >'+
						'<strong>Nombre:</strong>'+
					'</td>'+
					'<td >'+
						nombreAnalista + 
					'</td>'+
					'<td >'+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td >'+
					'</td>'+
					'<td >'+
						'<strong>Oficina:</strong>'+
					'</td>'+
					'<td colspan="2" >'+
						"<select property='dbx_Oficinas' id='dbx_Oficinas' styleClass='combobox' enabled='true' width = '45'></select>"+
					'</td>'+
					'<td >'+
					"&nbsp;&nbsp;&nbsp;"+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td colspan="4" height="10">'+
					"&nbsp;&nbsp;&nbsp;"+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td colspan="5" align="right">'+
						'<input type="button" align="right" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="btnCancelarOficina();"/> '+
						"&nbsp;&nbsp;&nbsp;"+
						'<input type="button" align="right" name="btn_Guardar" id="btn_Guardar" class="btn_limp" value="Guardar" onclick="btnGuardarOficina();"/> '+
						"&nbsp;&nbsp;&nbsp;"+
					'</td>'+
				'</tr>'+
				'<tr>'+
					'<td colspan="4" height="5">'+
					"&nbsp;&nbsp;&nbsp;"+
					'</td>'+
				'</tr>'+
				'</table>';
		
		document.getElementById("divRegistrarOficina").style.display = "";
		document.getElementById("divRegistrarOficina").innerHTML = texto;
		obtenerComboSeleccionOficina();
		document.getElementById("fondoNegro").style.visibility = "visible";
		document.getElementById("divRegistrarOficina").style.visibility = "visible";
	}
	
	function btnCancelarOficina()
	{
		var cancelar = confirm("¿Esta seguro que desea cancelar el registro de una oficina?");
		
		if (cancelar == true)
		{
		  	enviaFormulario(-1);
	  	}
	}
	
	function btnGuardarOficina()
	{
		var oficinaSelect = document.MenuPpalForm.dbx_Oficinas.value;
		if (oficinaSelect == 0){
			alert("Debe seleccionar una oficina");
		}else{
			var descOficina = obtenerDescripcion(vectorOficinas, oficinaSelect);
		
			var guardar = confirm("¿Está seguro que desea registrar la oficina " + Trim(descOficina) + "?");
			
			if (guardar == true)
			{
			  	guardarOficina(oficinaSelect);
		  	}
		}  	
	}
	
	function guardarOficina(oficinaSelect)
	{
		var idAnalista = "<%=session.getAttribute("IDAnalista")%>";
		var nomAnalista = "<%=session.getAttribute("NombreAnalista")%>";
		var apePatAnalista = "<%=session.getAttribute("ApePatAnalista")%>";
		var apeMatAnalista = "<%=session.getAttribute("ApeMatAnalista")%>";
		
		MenuPrincipalDWR.guardarOficinaAnalista(idAnalista, nomAnalista, apePatAnalista, apeMatAnalista, oficinaSelect, function(data)
		{
			var resp = null;
			resp = data;
			
			if(resp == false)
			{
				alert("Ocurrió un error al registar la Oficina Seleccionada.");
				enviaFormulario(-1);
				
			}else{
				document.getElementById("divRegistrarOficina").style.display = "";
				document.getElementById("fondoNegro").style.visibility = "hidden";
				document.getElementById("divRegistrarOficina").style.visibility = "hidden";
			}
		});
	}
	
	/*funcion que bloquea los campos dependiendo del perfil*/
	function bloqueaCampos()
	{
		var botonIng = null;
		var botonMod = null;	
		
		arregloPerfiles = '<%=session.getAttribute("Perfil")%>';
		
	    if (validarPerfiles(arregloPerfiles, "1"))
	    {	
	    	//perfil Analista				
	    	/*
			botonIng = document.getElementById("btn_GeneraSolicitud");
			botonIng.disabled = false;			
			botonMod = document.getElementById("btn_ConsModSolicitud");
			botonMod.disabled = false;
			botonMod = document.getElementById("btn_RepNominaSolAfi");
			botonMod.disabled = false;							
			*/
			botonMod = document.getElementById("btn_Aporte");
			botonMod.disabled = false;	
			botonMod = document.getElementById("btn_Beneficios");
			botonMod.disabled = false;			
			botonMod = document.getElementById("btn_Desafiliacion");
			botonMod.disabled = false;
			botonMod = document.getElementById("btn_Afiliacion");
			botonMod.disabled = false;
		}
		
		if (validarPerfiles(arregloPerfiles, "2"))
		{
			//perfil Full		
			/*
			botonIng = document.getElementById("btn_GeneraSolicitud");
			botonIng.disabled = false;			
			botonMod = document.getElementById("btn_ConsModSolicitud");
			botonMod.disabled = false;
			botonMod = document.getElementById("btn_ConsModAfiliado");
			botonMod.disabled = false;
			botonMod = document.getElementById("btn_RepNominaSolAfi");
			botonMod.disabled = false;
			botonMod = document.getElementById("btn_ConsModMasivaSol");
			botonMod.disabled = false;											
			*/
			botonMod = document.getElementById("btn_Aporte");
			botonMod.disabled = false;
			botonMod = document.getElementById("btn_ProcesoIntercaja");
			botonMod.disabled = false;	
			botonMod = document.getElementById("btn_Beneficios");
			botonMod.disabled = false;	
			botonMod = document.getElementById("btn_Desafiliacion");
			botonMod.disabled = false;
			botonMod = document.getElementById("btn_Afiliacion");
			botonMod.disabled = false;
		}
		
		if (validarPerfiles(arregloPerfiles, "3"))
		{
			//perfil Aporte					
			botonMod = document.getElementById("btn_Aporte");
			botonMod.disabled = false;
		}	
				
		if (validarPerfiles(arregloPerfiles, "4"))
		{
			//perfil Beneficios en Programa								
			botonMod = document.getElementById("btn_Beneficios");
			botonMod.disabled = false;
		}
		
		
		if (validarPerfiles(arregloPerfiles, "5")){		
			//perfil Mantenedores						
			botonMod = document.getElementById("btn_Mantenedores");
			botonMod.disabled = false;
		}	
		
		if (validarPerfiles(arregloPerfiles, "6"))
		{
			botonMod = document.getElementById("btn_Beneficios");
			botonMod.disabled = false;	
		}
		

		//20120709 - ANA - ANTIGUO PERFILAMIENTO
		/*	
		if ('<%=session.getAttribute("Perfil")%>' == "1"){

			botonIng = document.getElementById("btn_ConsModAfiliado");
			botonIng.disabled = true;
			//solo perfil full puede acceder a pantalla consulta modificacion masiva de solicitudes.
			botonMod = document.getElementById("btn_ConsModMasivaSol");
			botonMod.disabled = true;
		}
		if ('<%=session.getAttribute("Perfil")%>' == "3"){
			//perfil aportes puede acceder solo a pantalla de nomina de aportes	de afiliado.
			botonIng = document.getElementById("btn_GeneraSolicitud");
			botonIng.disabled = true;			
			botonMod = document.getElementById("btn_ConsModSolicitud");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_ConsModAfiliado");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_RepNominaSolAfi");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_ConsModMasivaSol");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_Beneficios");
			botonMod.disabled = true;
		}
		if ('<%=session.getAttribute("Perfil")%>' == "4"){
			//perfil beneficios en programa puede acceder solo a menu beneficios.
			botonIng = document.getElementById("btn_GeneraSolicitud");
			botonIng.disabled = true;			
			botonMod = document.getElementById("btn_ConsModSolicitud");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_ConsModAfiliado");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_RepNominaSolAfi");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_ConsModMasivaSol");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_RepNominaApoAfi");
			botonMod.disabled = true;
			botonMod = document.getElementById("btn_ConsMasivaApo");
			botonMod.disabled = true;
		}
		*/
		
		$( ".btn_menu" ).each(function() {
			  if($( this ).prop( "disabled" )){
				  $( this ).css( "background", "none" );
				  $( this ).css( "background-color", "#666666" );
			  }
		});
		
	}
	
</script>

</head>
<!--  agregar al body verificaOficinaAnalista(); -->
<body onload="bloqueaCampos();verificaOficinaAnalista();">
<html:form action="/menuPpal.do">
	<input type="hidden" name="opcion" value="0">
	
	<div id="caja_registro">
	
	<div id="fondoNegro" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 520px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
 		<div id="divRegistrarOficina" style="display: none; position: absolute; width: 450px; margin-top: 110px; margin-left: 300px; background-color: #F2F2F2">
		</div>
  	</div>
	
	<table width="970" border="0" >
		<tr>
			<td colspan="3"  align="right">
				<a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td width="32">&nbsp;</td>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<strong><p1><font color="#1B2935"> Menú Principal</font></p1></strong>
				</td>
			<td width="17">&nbsp;</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td width="32">&nbsp;</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<strong><p1>Seleccione una opción</p1></strong>
			</td>
			<td width="17">&nbsp;</td>			
		</tr>
		
		<!--  
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_GeneraSolicitud"
				class="btn_menu" id="btn_GeneraSolicitud"
				value="Generar Nueva Solicitud" onClick="asignaValor(1)" disabled="true"/>
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ConsModSolicitud"
				class="btn_menu" id="btn_ConsModSolicitud"
				value="Consulta/Modificación Solicitud" onClick="asignaValor(2)" disabled="true"/>
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ConsModAfiliado"
				class="btn_menu" id="btn_ConsModAfiliado"
				value="Consulta/Modificación Afiliado" onClick="asignaValor(3)" disabled="true"/></td>
			<td>&nbsp;</td>
		</tr>	
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_RepNominaSolAfi"
				class="btn_menu" id="btn_RepNominaSolAfi"
				value="Nómina de Solicitudes de Afiliación" onClick="asignaValor(4)" disabled="true"/></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ConsModMasivaSol"
				class="btn_menu" id="btn_ConsModMasivaSol"
				value="Consulta/Modificación Masiva solicitudes" onClick="asignaValor(5)" disabled="true"/></td>
			<td>&nbsp;</td>
		</tr>
		-->
		
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_Afiliacion" 
				class="btn_menu" id="btn_Afiliacion"
				value="Afiliación" onClick="asignaValor(11)" disabled="true" /></td>
			<td>&nbsp;</td>
		</tr> 
		
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_Desafiliacion" 
				class="btn_menu" id="btn_Desafiliacion"
				value="Desafiliación" onClick="asignaValor(10)" disabled="true" /></td>
			<td>&nbsp;</td>
		</tr>
		
		
		
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_Aporte"
				class="btn_menu" id="btn_Aporte"
				value="Aporte" onClick="asignaValor(6)" disabled="true"/></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_ProcesoIntercaja"
				class="btn_menu" id="btn_ProcesoIntercaja"
				value="Proceso de Intercaja" onClick="asignaValor(7)" disabled="true"/></td>
			<td>&nbsp;</td>
		</tr> 
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_Beneficios"
				class="btn_menu" id="btn_Beneficios"
				value="Beneficios" onClick="asignaValor(8)" disabled="true" /></td>
			<td>&nbsp;</td> 
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" name="btn_Mantenedores" 
				class="btn_menu" id="btn_Mantenedores"
				value="Mantenedores" onClick="asignaValor(9)" disabled="true" /></td>
			<td>&nbsp;</td>
		</tr> 
		
	<!-- Comentados para Paso : consultaRepNominaApoAfiEstados  -->
		
	</table>
	</div>
</html:form>


</body>
</html:html>
