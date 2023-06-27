<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SISTEMA DE AFILIACION TRABAJADOR INDEPENDIENTE</title>
<link href="/IndependientesWEB/css/estilos_interna.css" rel="stylesheet" type="text/css" />
<link href="/IndependientesWEB/css/grid_960.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/helper.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/js/Calendario.js"></script>

<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/engine.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/util.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/GeograficoDWR.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="/IndependientesWEB/dwr/interface/EntradaIntercajaDWR.js"></script>

<script type="text/javascript">

	function asignaValor(a){

		document.UploadFileIntForm.opcion.value = a;
	}
	
	function enviaFormulario(a)
	{
		var rutaArchivo = document.UploadFileIntForm.theFile.value;
		
		if(a == 2){
		
			if(Trim(rutaArchivo) == "")
			{
				alert("Debe seleccionar un archivo para subir.");
				return false;
			}
		}
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		asignaValor(a);
		document.UploadFileIntForm.submit();
	}
	
	/*funciones que cargan a los campos de fecha inferior y fecha superior*/
	function cargarFechaInferior(){

		var rangoUno = document.UploadFileIntForm.rangoIntercajaUno.value;//05 -- 02
		var rangoDos = document.UploadFileIntForm.rangoIntercajaDos.value;//25 -- 10
		
		fechaInferiorActual = new Date();
		mesInferiorActual = fechaInferiorActual.getMonth()+1;//esto pasa porque el primer mes es 0
		anioInferiorActual = fechaInferiorActual.getFullYear();
		
		var fechaSistemaInf = "<%=session.getAttribute("FechaSistema")%>";
		var diaSistemaInf = fechaSistemaInf.substring(0,2);
		var mesSistemaInf = fechaSistemaInf.substring(3,5);
		var fechaRango1 = "";
		
		if(diaSistemaInf <= 05){
			var mesTempInf = fechaInferiorActual.getMonth();
			if(mesTempInf < 10){
				mesTempInf = '0'+ mesTempInf;
			}	
			fechaRango1 = rangoDos + '/' + mesTempInf + '/' + anioInferiorActual;
			document.UploadFileIntForm.txt_FechaInferior.value = fechaRango1;
			
		}else{
			if(mesInferiorActual < 10){
				mesInferiorActual = '0'+ mesInferiorActual;
			}
						
			fechaRango1 = rangoDos + '/' + mesInferiorActual + '/' + anioInferiorActual;
			document.UploadFileIntForm.txt_FechaInferior.value = fechaRango1;
		}
		
		return document.UploadFileIntForm.txt_FechaInferior.value;
	}
	
	function cargarFechaSuperior(){
	
		var rangoUno = document.UploadFileIntForm.rangoIntercajaUno.value;//05 -- 02
		var rangoDos = document.UploadFileIntForm.rangoIntercajaDos.value;//25 -- 10
		
		if(parseInt(rangoUno) < 10){
			rangoUno = '0' + rangoUno;
		}
		fechaSuperiorActual = new Date();
		mesSuperiorActual = fechaSuperiorActual.getMonth() + 2;
		anioSuperiorActual = fechaSuperiorActual.getFullYear();
		
		var fechaSistemaSup = "<%=session.getAttribute("FechaSistema")%>";
		var diaSistemaSup = fechaSistemaSup.substring(0,2);
		var mesSistemaSup = fechaSistemaSup.substring(3,5);

		var fechaRango2 = "";
		
		if(diaSistemaSup <= 05){
			var mesTempSup = fechaSuperiorActual.getMonth() + 1;
			
			if(mesTempSup < 10){
				mesTempSup = '0' + mesTempSup;
			}
			
			fechaRango2 = rangoUno + '/' + mesTempSup + '/' + anioSuperiorActual;
			document.UploadFileIntForm.txt_FechaSuperior.value = fechaRango2;

		}else{

			if(mesSuperiorActual < 10){
				mesSuperiorActual = '0'+ mesSuperiorActual;
			}
			
			fechaRango2 = rangoUno + '/' + mesSuperiorActual + '/' + anioSuperiorActual;
			document.UploadFileIntForm.txt_FechaSuperior.value = fechaRango2;
		}
		
		return document.UploadFileIntForm.txt_FechaSuperior.value;
	}
	
	function obtenerRangoParaIntercaja(){
		
		DWREngine.setAsync(false);
		
		EntradaIntercajaDWR.obtenerRangoIntercaja(function(data){
			var resp = null;
			
			resp = data.codResultado;
			
			if(resp == 0){
				
				document.UploadFileIntForm.rangoIntercajaUno.value = data.rangoUno;
				document.UploadFileIntForm.rangoIntercajaDos.value = data.rangoDos;
				document.UploadFileIntForm.rangoIntercajaTres.value = data.rangoTres;

			}else{
			
				alert("Ocurrió un problema al obtener el rango de Intercaja.");
			}
		});
		
		DWREngine.setAsync(true);
	}
	
</script>
</head>
<body onload="obtenerRangoParaIntercaja();cargarFechaInferior();cargarFechaSuperior();"> 

<html:form action="/uploadFileInt.do" method="post" enctype="multipart/form-data">

  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="rangoIntercajaUno" value=" ">
  <input type="hidden" name="rangoIntercajaDos" value=" ">
  <input type="hidden" name="rangoIntercajaTres" value=" ">
  <input type="hidden" name="txt_FechaInferior" value=" ">
  <input type="hidden" name="txt_FechaSuperior" value=" ">
  <div id="caja_registro">
	  <table width="970">
		<tr>
			<td align="right">
				<a href="#" align="right" onclick="enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a>
				<br />
				<%= session.getAttribute("perfilesStr") %>
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>PROCESAMIENTO ARCHIVOS DE ENTRADA DESDE INTERCAJA</p1></strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario" value='<%=session.getAttribute("IDAnalista")%>' disabled="true" size="10"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" size="10" value='<%=session.getAttribute("FechaSistema")%>' id="txt_Fecha" disabled="true" /></td>				
				</tr>
			</table>
		   </td>	
		</tr>
		<!--  
		<tr>
			<td>
				<p><p2></p>
			<p></p>
			<p>1. Periodo de cierre de mes en curso válido.<p2></p>
			<p>
				<table width="100%" border="1" rules="groups">
					<tr>
						<td>
							<strong>Periodo desde el </strong>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="txt_FechaInferior" id="txt_FechaInferior" maxlength="12" size="10" disabled="true" onChange="cargarFechaInferior();"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<strong>hasta el </strong>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="txt_FechaSuperior" id="txt_FechaSuperior" maxlength="12" size="10" disabled="true" onChange="cargarFechaSuperior();">
						</td>
						<td width="16%">							
						</td>
						<td></td>
						<td colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
		-->
		<tr>
			<td>
			<p><p2></p>
			<p></p>
			<p></p>
			<p>1. Subir Archivo.<p2></p>
			<p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Ruta Archivo *</strong></td>
					<td>
						<input type=file name="theFile" id="theFile"> 
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
			</td>
		 </tr>
		<tr>
		<td style="font-size:11px;">(*campos obligatorios)</td>
		</tr>
		<tr>
			<td height="37" align="right">
			
				<input type="button" name="btn_Cancelar" id="btn_Cancelar" class="btn_limp"	value="Cancelar" onClick="enviaFormulario(1);" /> 
				&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Upload" id="btn_Upload" class="btn_limp"	value="Subir" onClick="enviaFormulario(2);" /> 
				&nbsp;&nbsp;&nbsp;
		
			</td>
		</tr>		 	 	
	  </table>
  </div>
  
  <div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1024px; height: 720px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
		<table width="100%">
			<tr>
				<td height="200">
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<IMG border="0" src="/IndependientesWEB/images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	</div>
 </html:form> 
</body>
</html:html>
