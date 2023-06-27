<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">
function confirmarCargaArchivo() {
	if($('#archivoCargaUsuarios').val() == ''){
		alert("Debe Seleccionar un Archivo");
	}else{
		if (confirm("¿Está Seguro de Cargar el Archivo?")){
			document.CargaMasivaUsuariosServletForm.submit();
		}
	}
}
</script>
<script>
function descargarArchivoErrores() {
	window.open("/gestorBecasWeb/adminBecasIncentivos/cargaMasivaUsuarios.do?_cmd=errores");
}
</script>

<form action="/gestorBecasWeb/CargaMasivaUsuariosServlet" method="post" enctype="multipart/form-data" name="CargaMasivaUsuariosServletForm"> 
	<div id="divSubirResultado">
		<table width="100%" class="table_transparente">
			<tr>
					<th align="left" colspan="2" nowrap="nowrap"><strong><bean:message key="label.cargaMasivaUsuarios"/></strong></th>
			</tr>
			<tr>
				<td><bean:message key="label.cargaUsuarios"/></td>
				<td>
					<input type="file" name="archivoCargaUsuarios" id="archivoCargaUsuarios"/>
				</td>
			</tr>
	
			
			
				<% 
				String display = (String) session.getAttribute("display");
				if(display != null && display.equals("1")){%>
				
			
				<tr>
				<td colspan="2" align="right"><html:button property="btn" styleClass="button" onclick="javascript:confirmarCargaArchivo();"><bean:message key="button.cargarUsuarios"/></html:button>
			
				<input type="button" class="button" id="descarga" onclick="javascript:descargarArchivoErrores();" value="Descargar Errores"></input>
				</td>
				</tr>
			
			<% } else {%>
				<tr>
				<td colspan="2" align="right"><html:button property="btn" styleClass="button" onclick="javascript:confirmarCargaArchivo();"><bean:message key="button.cargarUsuarios"/></html:button></td>
				</tr>
			<% } %> 
			
			
			
			
			
			
		</table>
	</div>
</form>
			