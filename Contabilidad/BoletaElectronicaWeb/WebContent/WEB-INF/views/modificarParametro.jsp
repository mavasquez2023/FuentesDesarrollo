<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<body>

	<jsp:include page="./comun/header.jsp" flush="true" />
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />




	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Mantenedor de parámetros</td>
		</tr>
		<tr>
	</table>
	<br>


	<form action="/BoletaElectronicaWeb/update.do" id="form"
		method="post">


		<table align="left" class="tabla-creditos"
			style="border: 0 px; width: 30%; margin-left: 25%; border-color: #FFFFFF;">



			<tr>
				<td class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;">C&oacute;digo</td>
				<td class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;"><input
					type="text" name="CODIGO" value="${parametro.CODIGO}"
					readonly="readonly" style="background-color: #CCCCCC"></td>
			</tr>
			<tr>
				<td class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;">Descripci&oacute;n</td>
				<td class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;"><input
					type="text" name="CAMPO" value="${parametro.CAMPO}"></td>
			</tr>
			<tr>
				<td class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;">Valor</td>
				<td class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;"><input
					type="text" name="VALOR" value="${parametro.VALOR}"></td>
			</tr>

			<tr>
				<td colspan="2" height="20px" class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;"></td>
			</tr>
			<tr>
				<td colspan="2" class="certificadoLeft"
					style="border: 0 px; font-size: 15px; border-color: #FFFFFF; text-align: left;"><input
					type="button" value="Modificar" id="update" /></td><!--  &nbsp;&nbsp;&nbsp;<input
					type="button" value="Grabar" id="grabar" />-->

			</tr>

		</table>
		<input type="hidden" id="idparam" name="idpar"
			value="${parametro.idpar}" />
	</form>

	<script type="text/javascript">
		$(document).ready(function() {

			$('#update').click(function() {

				$('#form').submit();
			})

			$('#grabar').click(function() {

				$('#idparam').val(0);

				$('#form').submit();
			})

		});
	</script>

</body>
</html>