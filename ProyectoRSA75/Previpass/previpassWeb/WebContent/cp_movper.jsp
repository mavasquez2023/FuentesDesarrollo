<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<body>
		<form method="post" id="dispatch" action="${autorizacion.urlCPEMOVPER}" >
		<input type="hidden" name="listaRutEmpresas" value="${autorizacion.empresasAutorizadas}" />
		<input type="hidden" name="listaAFP" value="${autorizacion.listaEntidades}" />
		<input type="hidden" name="holding" value="${autorizacion.grupoConvenios}" />
		<input type="hidden" name="origen" value="CP" />
		<input type="hidden" name="periodo" value="${autorizacion.periodo}" />
		</form>
		<script type="text/javascript">
			var o = document.getElementById('dispatch');
			o.submit();
		</script>

	</body>
</html>



