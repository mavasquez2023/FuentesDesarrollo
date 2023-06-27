<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>

<script type="text/javascript" src="<c:url value="/js/jquery-1.2.3.min.js" />"></script>
<script type="text/javascript" language="JavaScript">
<!--
	function refrescar(modificado) {
		if (modificado == 'nok') {
			alert('No es posible modificar la inconsistencia');
			window.close();
		} else {
			docto = window.opener.document;
			cambiaColor(docto);
			cambiaTexto(docto);
			habilitarBitacora(docto);
			window.close();
		}
	}
	
	function cambiaColor(documentHTML) {
		celdaEstado = documentHTML.getElementById('ESTADO_CELDA${idPago}');
		celdaEstado.className = "textos-formcolorModif";
		return null;
	}
	
	function cambiaTexto(documentHTML) {
		textoEstado = document.getElementById('descripcionEstado').value;
		textoPagado = document.getElementById('pagadoDesc').value;
		
      	objDivEst = documentHTML.getElementById('ESTADO_NOMBRE${idPago}');
      	objDivEst.innerHTML = textoEstado;
      	
      	objDivPag = documentHTML.getElementById('PAGADO_DESC${idPago}');
      	objDivPag.innerHTML = textoPagado;      	
	}
	
	function habilitarBitacora(documentHTML) {
		var objDiv = documentHTML.getElementById('B${idPago}');
		objDiv.style.display = '';
	}
	$(document).ready(function() {
		refrescar('${modificado}');
	});
//-->
</script>

<html:form action="admin/modificarInconsistencia/mod">
	<html:hidden property="estado.descripcion" name="modificarInconsistenciaForm" styleId="descripcionEstado"/>
	<html:hidden property="pagadoDesc" name="modificarInconsistenciaForm" styleId="pagadoDesc"/>
</html:form>
