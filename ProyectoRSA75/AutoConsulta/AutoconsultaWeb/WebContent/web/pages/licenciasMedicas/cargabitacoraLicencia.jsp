<!-- enviroment definition -->
<%@ page language="java" %><%@ 
taglib uri="/tags/struts-html" prefix="html" %><%@ 
taglib uri="/tags/struts-bean" prefix="bean" %><%@ 
taglib uri="/tags/struts-logic" prefix="logic" %>     
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<html:rewrite page="/web/includes/artered-araucana-internet.css" />" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<html:rewrite page="/web/js/jquery.js" />"></script>
	<title>Bitácora de licencia médica</title>
</head>
<body style="background: none;">
	<div  style="text-align: center;">
		<div id="titulo-bitacora">Bitácora de licencia médica</div>
		<div id="bitacora">
			<br>
			<div id="tablaBitacora" class="tabla-bitacora">
			</div>
			<div id="loading" style="position:; top:35%; left:55%; z-index: auto" >
				<img src="<%=request.getContextPath() %>/web/images/3d-loader.gif">
			</div>
		</div>
		<br>
		<br>
		<div style="text-align: center;"><input type="button" class="boton" value="Cerrar" onclick="cerrar();" />
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function () {
				$.ajax({
	 				data : {nroLicencia:'<%=request.getParameter("nroLicencia")%>', fechaPago: '<%=request.getParameter("fechaPago")%>' },
	 				url : '<%=request.getContextPath()%>/web/getBitacoraLicenciaMedica.do',
	 				type : 'POST',
					success : function(response) {
						$("#tablaBitacora").empty();
	 					$("#tablaBitacora").append(response);
	 				},complete: function(response){
	 					$("#loading").empty().addClass("hide");
	 				}
	 			});
	});
		
		function cerrar(){
			window.close();
		}
	</script>
</body>
</html>