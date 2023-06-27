<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic"  %>  
</br>
<script src="<%=request.getContextPath() %>/web/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/web/js/jquery.rut.min.js" type="text/javascript"></script>
	<div class="text-center">
		<p>Ingrese rut del trabajador afiliado a consultar</p>
		<form 
			name="formulario" 
			id="formulario" 
			action="<%=request.getContextPath() %><bean:write name="urlConsulta" />" 
			onsubmit="return validarSubmit();">
			<strong>Rut afiliado:</strong> <input type="text" id="rut" name="rutEmpleado" placeholder="1234567-8" >
			&nbsp;
			<input type="submit" id="consultar" value="Consultar" class="boton" >
			</br>
			<span id="rutError" class="errorMsg"><bean:write name="mensajeError" /></span>
		</form>
	</div>
<script type="text/javascript">
	$("#rut").Rut({
	   format_on: 'keyup'
	});
	function validarSubmit(){
		var res = false;
		var rut = $("#rut");
		if($.Rut.validar(rut.val())){
				$("#rutError").empty();
				res = true;
			}else{
				$("#rutError").empty();
				$("#rutError").append("Debe ingresar Rut Válido");
				res = false;
			}
		return res;
	}
</script>
