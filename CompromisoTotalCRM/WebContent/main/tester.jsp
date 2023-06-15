<!DOCTYPE html><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="../comun/tld.jsp"%>
<html:html>
<head>
<%@ include file="../comun/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	$(document).ready(function() {
		$(".link").click(function() {
			url = $(this).html();
			$("#rut").val($("#rutInput").val());
			//$("#pass").val($("#passInput").val());
			$("#formid").attr("action", url);
			$("#formid").submit();
		});
	});
</script>
</head>
<body>
	<h1>Página Tester</h1>

	<table>
		<tr>
			<td class="alignTop">Rut: <input name="rutInput" id="rutInput"
				type="text" />
				<ul>
					<!-- Urls de modulos del sistema. Para agregar, copiar alguno y modificar url -->
					<li><span class="link">carga/listadoContratos.do</span></li>
					<li><span class="link">../Welcome.do</span></li>
				</ul></td>

			<td><iframe src="" id="mainIframe" name="mainIframe"
					frameborder="0"></iframe></td>
		</tr>
	</table>
	<form action="" method="post" target="mainIframe" id="formid"
		class="form alignTop">
		<input type="hidden" name="rut" id="rut" value="" /> <input
			type="hidden" name="pass" id="pass" value="" />
	</form>
	Compromiso Total v<%=cl.laaraucana.compromisototal.utils.Configuraciones.getMainConfig("version")%>
</body>
</html:html>
