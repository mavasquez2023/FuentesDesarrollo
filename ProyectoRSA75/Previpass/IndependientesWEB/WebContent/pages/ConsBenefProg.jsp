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

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/ConsBenefProgDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/RepNominaApoAfiDWR.js"></script>

<script type="text/javascript">

 
	function asignaValor(a)
	{
		document.ConsBenefProgForm.opcion.value = a;
	}

	function enviaFormulario(a)
	{
		asignaValor(a);
		document.ConsBenefProgForm.submit();
	}
	
	//--- Negocio ---

	function buscarPorRUT()
	{
	   
		var rut = Trim(document.ConsBenefProgForm.txt_NRut.value);
		var dv  = Trim(document.ConsBenefProgForm.txt_NNumVerif.value);
		
		if (rut == "" || dv == "")
		{
			alert("Para buscar un Afiliado debe ingresar valores para los campos N° RUT y digito verificador");
			return false; //Se utiliza solo para salir de la ejecución.
		}
		 
		if (validaRut(rut, dv))
		{
		
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
				
			ConsBenefProgDWR.obtenerDatosAfiliado(rut, function(data){
			
				var afiliado = null;
				afiliado = data;
				if (afiliado != null){
					
					document.ConsBenefProgForm.txt_estadoAfiliado.value = afiliado.glosaTipoestadoAfiliado;
					document.ConsBenefProgForm.txt_nombreAfiliado.value = afiliado.nombres + " " + afiliado.apellidoPaterno + " " + afiliado.apellidoMaterno;
					document.ConsBenefProgForm.txt_fechaVigencia.value = afiliado.fechaVigencia;
					
					if(afiliado.totalDias < 0){
						document.ConsBenefProgForm.txt_diasAfiliado.value = 0;
					}else{
						document.ConsBenefProgForm.txt_diasAfiliado.value = afiliado.totalDias;
					}
					
					document.ConsBenefProgForm.txt_estadoAporte.value = afiliado.glosaEstadoAporte;
						
					if (afiliado.nombres == null &&	afiliado.apellidoPaterno == null && afiliado.apellidoMaterno == null){
						limpiarFormulario(0);
						
						alert("El Rut no existe en el sistema");
					}
				}
				else{
					limpiarFormulario(0);
					alert("El Rut no existe en el sistema");
				}
			});
			
		
		}else
		{
			limpiarFormulario(1);
			alert("El RUT ingresado es invalido.");
		}

	}
		
	//--- Perfilamiento ---
	
	function bloqueaCampos()
	{
		
	}
	
	function limpiarFormulario(valor)
	{
		if (valor == 1)
		{
			document.ConsBenefProgForm.txt_estadoAfiliado.value = "";
			document.ConsBenefProgForm.txt_nombreAfiliado.value = "";
			document.ConsBenefProgForm.txt_fechaVigencia.value = "";
			document.ConsBenefProgForm.txt_diasAfiliado.value = "";
			document.ConsBenefProgForm.txt_estadoAporte.value = "";
		}
		else{
			document.ConsBenefProgForm.txt_estadoAfiliado.value = "";
			document.ConsBenefProgForm.txt_nombreAfiliado.value = "";
			document.ConsBenefProgForm.txt_fechaVigencia.value = "";
			document.ConsBenefProgForm.txt_diasAfiliado.value = "";
			document.ConsBenefProgForm.txt_estadoAporte.value = "";
			document.ConsBenefProgForm.txt_NRut.value = "";
			document.ConsBenefProgForm.txt_NNumVerif.value = "";
		}
	}
	
</script>
</head>
<body onload="bloqueaCampos();">
<html:form action="/consBenefProg.do">
<div id="caja_registro">
	
	<input type="hidden" name="opcion" value="0">
	<h4><b>BENEFICIOS EN PROGRAMAS</b></h4>
	<table width="970" border="0">
		<tr>
			<td align="right" width="970" colspan="4">
				<a href="#" align="right" onClick="enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);">Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
	</table>
	
	<table width="970" border="0">	
		<tr>
			<td width="100" colspan="2">
			    <strong>N° RUT *</strong> 
			    <input type="text" name="txt_NRut" id="txt_NRut" size="10" maxlength="9" onkeypress="Upper(this,'N')" style="text-transform: uppercase;"/>
				<strong> - </strong> 
				<input type="text" name="txt_NNumVerif" id="txt_NNumVerif" size="3" maxlength="1" onkeypress="Upper(this,'D')" style="text-transform: uppercase;" onchange="ValidadorRUT(document.SolBeneficiosForm.txt_NRut.value,this.value)"/> 
				<input type="button" name="btn_Buscar" id="btn_Buscar" class="btn_limp" value="Buscar" onClick="buscarPorRUT();" />
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
				<p>Fecha Vigencia</p>
			</td>
			<td width="150">	
				<html:text property="txt_fechaVigencia" styleClass="txt_fechaVigencia" disabled="true" size="15" value="" />
			</td>
			<td width="150">
				<p>Total dias Afiliado</p>
			</td>
			<td width="150">	
				<html:text property="txt_diasAfiliado" styleClass="txt_diasAfiliado" disabled="true" size="15" value="" />
			</td>
		</tr>
		<tr>
			<td width="150">
				<p>Estado Aporte</p>
			</td>
			<td width="150">	
				<html:text property="txt_estadoAporte" styleClass="txt_estadoAporte" disabled="true" size="15" value="" />
			</td>
		</tr>
		
	</table>
		
	
	<table width="970" border="0">	
		<tr>
			<td height="50" align="right" width="968">
				<input type="submit" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp" value="Cancelar" onclick="enviaFormulario(1);">&nbsp; 			
			    &nbsp;&nbsp;&nbsp;
			    <input type="button" name="btn_limpiar" id="btn_limpiar" class="btn_limp" value="Limpiar" onClick="limpiarFormulario(0)" /> 
				&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
			
</div>
</html:form>
</body>
</html:html>