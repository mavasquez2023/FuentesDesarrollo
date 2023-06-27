<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page errorPage="error.jsp" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<html:form action="/upload.do" method="post" enctype="multipart/form-data">

<HEAD>
<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>

<script type="text/javascript">

function validarInicio(){

	var entrada = '<%=request.getParameter("cmd")%>';
	var mensaje = '<%=request.getAttribute("respuesta")%>';
	var codError = '<%=request.getAttribute("codError")%>';
	
	if (mensaje != "" && mensaje != "null")
	{
		alert(mensaje);
	}
	
	if(entrada != "subida" && codError == "0")
	{
		window.close();
	}
}


function cargarArchivo()
{
    var archivo = document.getElementById("theFile").value;
    var opcion = document.UploadForm.cbOpcion.value;
    
    if( archivo=="" )
    {
         alert("Debe seleccionar el archivo a cargar.");
         return false;
    }
    
    if( opcion==-1 )
    {
        alert("Debe seleccionar una opción");
        return false;
    }
    
    document.UploadForm.submit();
}

</script>

</HEAD>
<BODY bgcolor="white" onload="validarInicio();">

<input type="hidden" name="respuesta" value="">

<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="725" height="81">

<table id="tablaAtchivo" width="100%" border="0" style="border:#999 1px solid;" class="texto">
			
			<tr>
				<td colspan="2">
					<strong>Carga y Consumo Masivo.</strong>
				</td>
			</tr>
			<tr>
				<td colspan="2"> 
					<input type="file" name="theFile" id="theFile" class= "texto">
				</td>
				
			</tr>
			<tr>
			  <td>
			    <select id="cbOpcion" name="cbOpcion">
			    	<option value="-1">Seleccione Opcion...</option>
					<option value="1">Consulta Masiva</option>
					<option value="2">Consumo Masivo</option>
			    </select>
			    </td>
			    <td>
					<input type="button" onclick="cargarArchivo();" name="logFile" id="logFile" value="Aceptar" style="background-image: url(/lme/img/buttom.JPG);border: 0;width: 87px;height: 21px; color: white;"/>	
				</td>
			</tr>
			
		</table>


</BODY>
</html:form>