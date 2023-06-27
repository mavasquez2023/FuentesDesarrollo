<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>
<script language="javascript">
function excelView(){		
		window.location= "${contextRoot}/cargaResultado/exportarExcel.do?_cmd=excel";
	}
function operacionArchivo(obj){	
	if(obj == 1){

		divSubir = document.getElementById("divSubirResultado");
		divSubir.style.display="";
		
		divBajar = document.getElementById("divBajarResultado");
		divBajar.style.display = "none";

		btn = document.getElementById("btnBuscar");
		btn.style.display = "none";
		
	}else{
		divSubir = document.getElementById("divSubirResultado");
		divSubir.style.display="none";
		
		divBajar = document.getElementById("divBajarResultado");
		divBajar.style.display = "";
		
		btn = document.getElementById("btnBuscar");
		btn.style.display = "";
		
	}

}
function actualizaBecaOculta(obj){
	$("#idBeca").val(obj);	
}

function confirmarCargaArchivo() {
	if($('#idBeca').val() == ''){
		alert("Debe Seleccionar una Beca");
	}else{
		if($('#subirResultado').val() == ''){
			alert("Debe Seleccionar un Archivo");
		}else{
			if (confirm("¿Está Seguro de Cargar el Archivo?")){
				document.CargaResultadoServletForm.submit();
			}
		}
	}
}


</script>
<html:form action="/cargaResultado/buscaCargaResultado">
	<html:hidden property="_flagValidar" value="true" />
	<html:hidden property="_cmd" value="resultado" />
	<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.administracion.carga.resultado.titulo"></bean:message></th>			
		</tr>
		<tr>
			<td><bean:message key="label.administracion.carga.resultado.beca"></bean:message></td>
			<td>
				<html:select property="opcBeca" styleId="opcBeca" onchange="javascript:actualizaBecaOculta(this.value);">
						<html:option value=""><bean:message key="label.common.seleccione.text" /></html:option>
						<logic:iterate id="beca" name="becasDVOs">
							<option value="<bean:write name='beca' property='idBeca'/>"><bean:write name='beca' property='nombre'/></option>
						</logic:iterate>
				 </html:select>
			 </td>
		</tr>
		<tr>
			<td><bean:message key="label.administracion.carga.resultado.operacion"></bean:message></td>
			<td>
				<input type="radio"  name="operacion" value="0" checked="checked" onchange="javascript:operacionArchivo(this.value);"/><bean:message key="label.administracion.carga.resultado.bajar"></bean:message>
				<input type="radio"  name="operacion" value="1" onchange="javascript:operacionArchivo(this.value);"/><bean:message key="label.administracion.carga.resultado.subir"></bean:message>
			</td>
		</tr>
		<logic:empty name="becasDVOs">
			<tr>
				<td colspan="2" bgcolor="Khaki" align="center"><strong><bean:message key="label.administracion.carga.resultado.sinBecas"/></strong></td>
			</tr>
		</logic:empty>
		<tr>
			<td colspan="2" align="right"><html:submit property="btn" styleId="btnBuscar" styleClass="button"><bean:message key="button.aceptar"/></html:submit></td>
		</tr>
	</table>
</html:form>
<form action="/gestorBecasWeb/CargaResultadoServlet" method="post" enctype="multipart/form-data" name="CargaResultadoServletForm"> <!--  ojo, es un servlet, no un action de Struts -->	
	<div id="divSubirResultado" style="display:none;">
		<table width="100%">
			<tr>
					<th align="left" colspan="2"><strong><bean:message key="label.administracion.carga.resultado.subir.archivo"/></strong></th>
			</tr>
			<tr>
				<td><bean:message key="label.administracion.carga.resultado.subir.archivo"/></td>
				<td>
					<input type="file" name="subirResultado" id="subirResultado"/>
					<input type="hidden" id="idBeca" name="idBeca"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="Khaki" align="center"><strong><bean:message key="label.administracion.carga.resultado.leyenda"/></strong></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><html:button property="btn" styleId="btnCargar" styleClass="button" onclick="javascript:confirmarCargaArchivo();"><bean:message key="button.cargar"/></html:button></td>
			</tr>
		</table>
	</div>
</form>
	<div id="divBajarResultado">
<logic:notEmpty name="cargaResultadoVOs">
		<div id="divScroll" style="overflow:auto;padding-right: 0px; padding-top: 0px; padding-left: 0px; padding-bottom: 0px;scrollbar-arrow-color : #A4B9CA; scrollbar-face-color : #F3F7F8;
			scrollbar-track-color :#EDF2F5 ;height:208px; left: 126px; top: 179; width: 590px"; align=center>
			<table width="100%">
				<tr>
					<th align="left" colspan="15"><strong><bean:message key="label.administracion.carga.resultado.resultado"/></strong></th>
				</tr>
				<tr>
					<td align="left" colspan="15"><strong><bean:message key="label.administracion.carga.resultado.beca"/>: <bean:write name="becaSession"/></strong></td>
				</tr>
				<tr>
					<th><bean:message key="label.informe.carga.resultado.folioInscripcion"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.rutAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nombreAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.apellidoAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.rutPostulante"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nombresPostulante"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.apellidosPostulante"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.codigoBeca"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.descripcionBeca"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.segmento"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nivelEducacional"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nota"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.fechaInscripcion"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.oficvinaIncripcion"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.EjecutivoAtencion"></bean:message></th>
				</tr>
				<%int cont = 0;%>
				<logic:iterate id="beca" indexId="i" name="cargaResultadoVOs">
					<tr	<%if(cont == 0){cont = 1;%>class="tr1"<%} else {cont = 0;%>valign=top<%}%>>
				
					
						<td nowrap="nowrap"><bean:write name="beca" property="folioInscripcion" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="rutAfiliado" />-<bean:write name="beca" property="dvAfiliado" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nombresAfiliado" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="apellidosAfiliado" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="rutPostulante" />-<bean:write name="beca" property="dvPostulante" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nombresPostulante" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="apellidosPostulantes" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="codigoBeca" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="descripcionBeca" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="segmento" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nivelEstudio" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nota" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="fechaIncripcion" formatKey="format.date"/></td>
						<td nowrap="nowrap"><bean:write name="beca" property="oficinaInscripcion" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="ejecutivoAtencion" /></td>
					</tr>
				</logic:iterate>
			</table>
		</div>
		<table width="100%">
		<tr>
			<td align="center">
  					 <a onclick="javascript:excelView();setTimeout('hideLoading();',3000);" href="#">
  							 <img src="${contextRoot}/images/excel.jpg" />
  					 </a>
 			</td>	
		</tr>
	</table>
</logic:notEmpty>
</div>
