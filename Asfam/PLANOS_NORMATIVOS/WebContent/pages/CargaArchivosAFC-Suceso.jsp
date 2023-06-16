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
<script type="text/javascript" language="JavaScript1.2" src="./dwr/interface/GeneraReporteCotizacionesDWR.js"></script>

<script type="text/javascript">
	function asignaValor(a){
		document.CargaArchivosForm.opcion.value = a;
	}
	function cargarOnload(){
		document.CargaArchivosForm.anio.value = '<%=session.getAttribute("anio")%>';
		document.CargaArchivosForm.mes.value = '<%=session.getAttribute("mes")%>';
		
	}
	
	function enviaFormulario(a){
	
		var rutaArchivo = document.CargaArchivosForm.theFile.value;
		
		if(a == 2){
			if(Trim(rutaArchivo) == ""){
				alert("Debe seleccionar un archivo para subir.");
				return false;
			}
			var tipoReporte = '<%=session.getAttribute("tipoReporteEnv")%>';
    		if(tipoReporte != "null"){
	    		eliminarArchivoXls(tipoReporte);
	    		eliminarArchivo(tipoReporte);
	    		eliminarResumenes(tipoReporte);
	    	}
		}
		
		document.getElementById("pantallaDeCarga").style.visibility = "visible";
		asignaValor(a);
		
		document.CargaArchivosForm.submit();
	}
	
	/**Funcion que elimina un archivo del servidor, cuando se presiona reprocesar.*/
	function eliminarArchivo(codReporte){
	
		var nombreArch = retornaPeriodoYNombreArchivoTxt(codReporte);
				
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteArchivoServer(codReporte,nombreArch, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){
				//alert("Archivo eliminado con éxito");
			}
		});
		DWREngine.setAsync(true);
		
	}
	
	function eliminarArchivoXls(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoXls(codReporte);
		
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteXlsServer(codReporte, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){
				//alert("Archivo xls eliminado");
			}	
		});
		DWREngine.setAsync(true);
		
	}
	
	/**Funcion que elimina archivo xls del tipo resumen.*/
	function eliminarResumenes(codReporte){
		
		var nombreArchivo = retornaPeriodoYNombreArchivoErr(codReporte);
		var codReporteMod = 2 + codReporte;
		DWREngine.setAsync(false);
		GeneraReporteCotizacionesDWR.deleteXlsServer(codReporteMod, nombreArchivo, function(data){
			var respuesta = null;
			respuesta = data.codRespuesta;
			if(respuesta == 0){
				//alert("Archivo xls eliminado");
			}	
		});
		DWREngine.setAsync(true);
	}

	/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoErr(codReporte){
		
		var anioReproceso = document.CargaArchivosForm.anio.value;
		var mesReproceso = document.CargaArchivosForm.mes.value;
		
		/*if(parseInt(mesReproceso,10) < 10 ){
			mesReproceso = '0' + mesReproceso;
		}
		
		if(anioReproceso.length == 1){
			anioReproceso = '200' + anioReproceso;
		}else{
			if(anioReproceso.length == 2){
				anioReproceso = '20' + anioReproceso;
			}
		}*/
		
		var periodo = anioReproceso.toString() + mesReproceso.toString();
		
		
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";
		
		switch(parseInt(codReporte,10)){
			case 8:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_ERR.XLSX";
				break;
			case 9:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_ERR.XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
		/**Retorna periodo y nombre del archivo en formato xls (de reportes)*/
	function retornaPeriodoYNombreArchivoXls(codReporte){
		
		var anioReproceso = document.CargaArchivosForm.anio.value;
		var mesReproceso = document.CargaArchivosForm.mes.value;
		
		/*if(parseInt(mesReproceso,10) < 10 ){
			mesReproceso = '0' + mesReproceso;
		}
		
		if(anioReproceso.length == 1){
			anioReproceso = '200' + anioReproceso;
		}else{
			if(anioReproceso.length == 2){
				anioReproceso = '20' + anioReproceso;
			}
		}*/
		
		var periodo = anioReproceso.toString() + mesReproceso.toString();
		
		
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		var nombreArchivo = "";
		
		switch(parseInt(codReporte,10)){
			case 8:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.XLSX";
				break;
			case 9:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.XLSX";
				break;
			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}

	/*Funcion que retorna el periodo en el nombre del archivo, formado por la eleccion de los combos de mes y año dados por el usuario*/
	function retornaPeriodoYNombreArchivoTxt(codReporte){
		
		var nombreArchivo = "";
		var anioReproceso = document.CargaArchivosForm.anio.value;
		var mesReproceso = document.CargaArchivosForm.mes.value;
		
		/*if(parseInt(mesReproceso,10) < 10 ){
			mesReproceso = '0' + mesReproceso;
		}
		
		if(anioReproceso.length == 1){
			anioReproceso = '200' + anioReproceso;
		}else{
			if(anioReproceso.length == 2){
				anioReproceso = '20' + anioReproceso;
			}
		}*/
		
		var periodo = anioReproceso.toString() + mesReproceso.toString();
		
		
		
		//var fechaSistema = '< %=session.getAttribute("FechaSistema")%>';
		//var periodo = obtenerPeriodoAnioMes(fechaSistema);
		
		switch(parseInt(codReporte,10)){
			case 8:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.DAT";
				break;
			case 9:
				nombreArchivo = "10105_CASIGFAM_"+ periodo + "_S.DAT";
				break;

			default:
				nombreArchivo = "";
		}
								
		return nombreArchivo;
	}
	
</script>

<body onload="cargarOnload();">
<html:form action="/CargarArchivos.do" method="post" enctype="multipart/form-data">
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
					<td><strong><p1>CARGA DE ARCHIVOS AFC - SUSESO.</p1></strong></td>
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

	  <div id="pantallaDeCarga" style="visibility:hidden; position:absolute; top: 0; left: 0; width: 1400px; height: 900px; background:#999999; filter:alpha(opacity=30);-moz-opacity:30%;">
		<table width="100%">
			<tr>
				<td height="200">
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<IMG border="0" src="./images/loading.gif" width="200" height="200">
				</td>
			</tr>
		</table>	
	  </div>

	
  </div>
 </html:form>
</body>
</html:html>
