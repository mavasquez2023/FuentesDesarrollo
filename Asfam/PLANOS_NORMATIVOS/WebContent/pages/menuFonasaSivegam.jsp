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

<script type="text/javascript">

	/*función que signa un valor al formulario.*/
	function asignaValor(a){
		//gc();
		document.MenuGenReporteForm.opcion.value = a;
	}

    function irUrl()
    {
    
      //window.location.href='http://146.83.1.129:9080/CotizacionesFonasaWEB/bitacoraProcesos.jsp?id=0124397104979702914097210947917049710947917907109470199127401243971049797029140972109479170497109479179071094701991274';
     //window.location.href='http://localhost:9080/CotizacionesFonasaWEB/bitacoraProcesos.jsp?id=0124397104979702914097210947917049710947917907109470199127401243971049797029140972109479170497109479179071094701991274';
     
	     var id='0124397104979702914097210947917049710947917907109470199127401243971049797029140972109479170497109479179071094701991274'
	    
	     var form = document.createElement("form");
	     var input = document.createElement("input");
	
	    form.action = "http://146.83.1.120:9080/CotizacionesFonasaWEB/bitacoraProcesos.jsp";
	    form.method = "post"
	
	    input.name = "id";
            input.type="hidden";
	    input.value = id;
	    form.appendChild(input);
	
	    document.body.appendChild(form);
	    form.submit();
    
    }

	/*funcion envia formulario.*/
	function enviaFormulario(a){
	
		asignaValor(a);
		
		document.MenuGenReporteForm.submit();
	}

</script>

</head>
<body>
<html:form action="/menuGenReporte.do">
	<input type="hidden" name="opcion" value="0">
	<div id="caja_registro">
		<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	
	<table width="1020" border="0" >
		<tr>
			<td colspan="3"  align="right">
				<a href="#" align="right" onClick="enviaFormulario(4);"><B>Volver</B></a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" ><B>Cerrar Sesión</B></a>
			</td>
		</tr>
		<tr>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		
		<tr>
			<td width="32">&nbsp;</td>
				<td align="center">
					<strong><p1><font color="#1B2935"> Menú Tipo de Reportes por &Aacute;rea. </font></p1></strong>
				</td>
			<td width="17">&nbsp;</td>
		</tr>
		<tr>
		</tr>
		<br/>
		<tr>
			<!-- <th colspan="1">Seleccione una opción</th> -->
			<td width="32">&nbsp;</td>
			<td align="center">
				<strong><p1>Seleccione una opción</p1></strong>
			</td>
			<td width="17">&nbsp;</td>
			
		</tr>
		
		<tr>
			<td width="32">&nbsp;</td>
			<td align="center">
				<input type="submit" name="btn_Cotizaciones"
				class="btn_menu" id="btn_Cotizaciones"
				value="Sivegam" onClick="asignaValor(2)" /></td>
			<td width="17">&nbsp;</td>
		</tr>
		
		<tr>
			<td width="32">&nbsp;</td>
			<td align="center">
				<input type="button" name="btn_DivPrevisional"
				class="btn_menu" id="btn_DivPrevisional"
				value="Fonasa" onClick="irUrl();" /></td>
			<td width="17">&nbsp;</td>
		</tr>
		
		
		
	</table>
	</div>
</html:form>
</body>
</html:html>
