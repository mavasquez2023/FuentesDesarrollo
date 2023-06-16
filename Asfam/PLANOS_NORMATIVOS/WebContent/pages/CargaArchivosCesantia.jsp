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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GenerarReporteCesantiaDWR.js"></script>

<script type="text/javascript">
	function asignaValor(a){

		document.CargaCesantiaForm.opcion.value = a;
	}
	function cargarOnload(){
		document.CargaCesantiaForm.anio.value = '<%=session.getAttribute("anio")%>';
		document.CargaCesantiaForm.mes.value = '<%=session.getAttribute("mes")%>';
		
	}
	
	function enviaFormulario(a){
	
		var rutaArchivo = document.CargaCesantiaForm.theFile.value;
		
		if(a == 2){
			if(Trim(rutaArchivo) == ""){
				alert("Debe seleccionar un archivo para subir.");
				return false;
			}
		
			var idReporte = '<%=session.getAttribute("idReporteCesantiaEnv")%>';
			eliminarArchivoXls(idReporte);//Elimina excel de preceso
			eliminarArchivo(idReporte);//Elimina PLANO
			eliminarArchivoXls(idReporte);//Elimina excel de errores
		}
		
		//document.getElementById("pantallaDeCarga").style.visibility = "visible";
		
		asignaValor(a);
		document.CargaCesantiaForm.submit();
	}
	
	/**Funcion que elimina archivo XLS.*/
	function eliminarArchivoXls(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoXls(codReporte);
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.deleteArchivoXls(codReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}	
		});
		DWREngine.setAsync(true);
		
	}

	/**Funcion que elimina un archivo del servidor, cuando se presiona reprocesar.*/
	function eliminarArchivo(idReporte){
	
		var nombreArch = retornaPeriodoYNombreArchivoTxt(idReporte);
				
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.deleteArchivoTxt(idReporte,nombreArch, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}
		});
		DWREngine.setAsync(true);
		
	}
	
	/**Funcion que elimina archivo xls del tipo resumen.*/
	function eliminarResumenes(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoErr(codReporte);
		//alert("nombre archivo: " + nombreArchivo);
		DWREngine.setAsync(false);
		GenerarReporteCesantiaDWR.deleteArchivoXls(codReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){

			}	
		});
		DWREngine.setAsync(true);
	}	

	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoXls(codReporte){
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		var periodo = '<%=session.getAttribute("periodoRep")%>';//obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";
		
		switch(parseInt(codReporte,10)){
			case 10:
				nombreArchivo = "SC41_"+ periodo + ".XLSX";
				break;
			case 11:
				nombreArchivo = "SC42_"+ periodo + ".XLSX";
				break;
			case 12:
				nombreArchivo = "SC43_"+ periodo + ".XLSX";
				break;
			case 13:
				nombreArchivo = "SC44_"+ periodo + ".XLSX";
				break;	
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	/*Funcion que retorna el periodo en el nombre del archivo, formado por la eleccion de los combos de mes y año dados por el usuario*/
	function retornaPeriodoYNombreArchivoTxt(codReporte){
		
		var nombreArchivo = "";
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		var periodo = '<%=session.getAttribute("periodoRep")%>';//obtenerPeriodoAnioMes(fechaSistema);
		
		switch(parseInt(codReporte,10)){
			case 10:
				nombreArchivo = "PLANO41_"+ periodo + ".TXT";
				break;
			case 11:
				nombreArchivo = "PLANO42_"+ periodo + ".TXT";
				break;
			case 12:
				nombreArchivo = "PLANO43_"+ periodo + ".TXT";
				break;
			case 13:
				nombreArchivo = "PLANO44_"+ periodo + ".TXT";
				break;	
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoErr(codReporte){
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		var periodo = '<%=session.getAttribute("periodoRep")%>';//obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";
		
		switch(parseInt(codReporte,10)){
			case 10:
				nombreArchivo = "SC41_"+ periodo + "_ERR.XLS";
				break;
			case 11:
				nombreArchivo = "SC42_"+ periodo + "_ERR.XLS";
				break;
			case 12:
				nombreArchivo = "SC43_"+ periodo + "_ERR.XLS";
				break;
			case 13:
				nombreArchivo = "SC44_"+ periodo + "_ERR.XLS";
				break;	
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	/**Funcion que obtiene el periodo como una concatenacion entre el año y el mes, a partir de la fecha de sistema.*/
	function obtenerPeriodoAnioMes(fechaSistema){
		
		var anioReporte = fechaSistema.substring(6,10);
		var mesReporte = "";
		var periodoReporte;
		var periodoMes = "";
		
		var mesTempReporte = fechaSistema.substring(3,5);
		
		if(parseInt(mesTempReporte) < 10){
			if(parseInt(mesTempReporte) == 1){
				mesReporte = 12;
				anioReporte = parseInt(anioReporte,10) - 1;
				anioReporte = anioReporte.toString();
				periodoReporte = anioReporte + mesReporte;
			}else{
				mesReporte = parseInt(fechaSistema.substring(4,5)) - 1 ; //siempre el mes anterior.
				

				periodoMes = "0" + mesReporte;
				
				periodoReporte = anioReporte + periodoMes;
			}
		}else{
			mesReporte = parseInt(fechaSistema.substring(3,5)) - 1;
			periodoReporte = anioReporte + mesReporte;
		}
		
		return periodoReporte;
	}
	
	
</script>

<body onload="cargarOnload();">
<html:form action="/CargarArchivosCesantia.do" method="post" enctype="multipart/form-data">
  <input type="hidden" name="opcion" value="0">
  <input type="hidden" name="anio">
  <input type="hidden" name="mes">
  
  <div id="caja_registro">
	<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	  <table width="1020">
		<tr>
			<td align="right">
				<a href="#" align="right" onClick="enviaFormulario(1);">Volver</a>
				&nbsp;&nbsp;&nbsp;
				<!-- <a href="#" align="right" onClick="enviaFormulario(-1);" >Cerrar Sesión</a> -->
			</td>
		</tr>
		<tr>
			<td width="50%" height="25">
			 <table border="0">
				<tr>
					<td><strong><p1>CARGA DE ARCHIVOS CESANTIA.</p1></strong></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr><tr></tr><tr></tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Usuario</td>
					<td><input type="text" name="txt_Usuario" id="txt_Usuario" value="<%=session.getAttribute("IDAnalista")%>" disabled="true" size="10"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>Fecha</td>
					<td><input type="text" name="txt_Fecha" size="10" value="<%=session.getAttribute("FechaSistema")%>" id="txt_Fecha" disabled="true" /></td>				
				</tr>
			</table>
		   </td>	
		</tr>
		<tr>
			<td>
			<p><p2></p>
			<p></p>
			<p></p>
			</br>
			<p>1. Datos de Procesamiento.<p2></p>
			<p>
			<table width="100%" border="1" rules="groups">
				<tr>
					<td width="16%"><strong>Ruta Archivo *</strong></td>
					<td width="16%">
						<!-- <input type="text" name="txt_nombreArchivoUpload" id="txt_nombreArchivoUpload" disabled="true" size="50"/> -->
						<input type=file name="theFile" id="theFile">
						<!-- <input type="text" name="txt_rutaArchivo" id="txt_rutaArchivo" maxlength="12" onkeyup="Upper(this,'A')" onkeypress="Upper(this,'A')" size="23" /> -->
					</td>
					<td>
						<!-- <input type="reset" id="limpiar" name="limpiar" value="Limpiar datos"/> -->
						<!-- <input type="button" name="btn_Examinar" id="btn_Examinar" class="btn_limp"	value="Examinar" onClick="comprueba_extension(this.form, this.form.archivoupload.value)" /> -->
					</td>
					<td>
					</td>
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
				<input type="button" name="btn_Volver" id="btn_Volver" class="btn_limp" value="Volver" onclick="enviaFormulario(1);"/> 
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" name="btn_Aceptar" id="btn_Aceptar" class="btn_limp" value="Aceptar" onclick="enviaFormulario(2);"/> 
			</td>
		</tr>		 	 	
	  </table>
	
  </div>
 </html:form>
</body>
</html:html>

