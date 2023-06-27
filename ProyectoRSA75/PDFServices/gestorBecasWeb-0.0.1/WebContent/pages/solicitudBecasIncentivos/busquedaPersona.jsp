<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">
		function buscaSegmento() {
			var rut = $('#rutTitular').val();
			if(rut != ''){
				buscaSegmentoByRut('buscaSegmento', rut);
			}
		}
</script>
<html:form action="/solicitudBecasIncentivos/BusquedaPersona" onsubmit="return validarSubmit();">
<html:hidden property="_cmd" value="resultado"/>

	<table width="100%" class="table_transparente">
		<tr>
			<td>
				<table width="100%">
					<tr>
						<th colspan="2" nowrap="nowrap"><bean:message
								key="label.rutTitular" />
						</th>
					</tr>
					<tr>
						<td><bean:message key="label.rutTitular"/></td>
						<td><input type="text" id="rutTitular" name="rutTitular" placeholder="1234567-8"></td>
						
					</tr>
					<tr colspan="2" id="botonBusqueda" style="display: true;">
						<td></td>
						<td align="right"><html:button property="botonAceptar" styleClass="button" onclick="javascript:validarRutBusqueda();">
							<bean:message key="button.ingresar" />
						</html:button>
						</td></tr>
					<tr id="tituloSegmento" style="display: none;">
						<th colspan="2"><bean:message key="label.segmentoPostular"/></th>
					</tr>
					<tr>
						<td colspan="2" align="left"> <div id="divSegmentos"> </div> </td>						
					</tr>
					<tr id="botonSegmento" style="display: none;">
						<td colspan="2" align="right"><html:submit property="" styleClass="button"><bean:message key="button.ingresar"/></html:submit></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
	$("#rutTitular").Rut({
	   format_on: 'keyup'
	});
	function validarSubmit(){
		var res = false;
		var rut = $("#rutTitular");
		if($.Rut.validar(rut.val())){
				res = true;
			}else{
				alert("Debe ingresar Rut Válido");
				res = false;
			}
		return res;
	}
	function validarRutBusqueda(){
		var res = false;
		var rut = $("#rutTitular");
		if($.Rut.validar(rut.val())){
				buscaSegmento();
			}else{
				alert("Debe ingresar Rut Válido");
				res = false;
			}
		return res;
	}
	</script>

</html:form>