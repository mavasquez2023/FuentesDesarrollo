<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">
</script>
<html:form action="/admin/eliminarUsuario" onsubmit="return validarSubmit();">
<html:hidden property="_cmd" value="eliminar"/>

	<table width="100%" class="table_transparente">
		<tr>
			<td>
				<table width="100%">
					<tr>
						<th colspan="2" nowrap="nowrap"><bean:message
								key="label.eliminarUsuario" />
						</th>
					</tr>
					<tr>
						<td><bean:message key="label.rutUsuario"/></td>
						<td><input type="text" id="rutUsuario" name="rutUsuario" placeholder="1234567-8"></td>  
						</td>
					</tr>
					<tr>
				
					<tr>
						<td colspan="2" align="right"><html:submit property="" styleClass="button" ><bean:message key="button.eliminar"/></html:submit></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
	$("#rutUsuario").Rut({
	   format_on: 'keyup'
	});
	function validarSubmit(){
		var res = false;
		var rut = $("#rutUsuario");
		if($.Rut.validar(rut.val())){
				res = true;
			}else{
				alert("Debe ingresar Rut V�lido");
				res = false;
			}
		return res;
	}
	</script>

</html:form>