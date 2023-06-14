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



	<table align="center" class="tabla-creditos">
		<thead>
			<tr>
				<th class="certificadoLeft">C&oacute;digo</th>
				<th class="certificadoLeft">Campo</th>
				<th class="certificadoLeft">Valor</th>
				<th class="certificadoLeft"></th>
				<!-- <th class="certificadoLeft"></th>-->				 

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${parametros}" var="par">
				<tr>
					<td class="certificadoLeft">${par.CODIGO}</td>
					<td class="certificadoLeft">${par.CAMPO}</td>
					<td class="certificadoLeft">${par.VALOR}</td>
				 
										<td class="certificadoLeft"><a href="<c:url value='/parametro.do?id=${par.CODIGO}'/>"><img src="<c:url value='/resources/img/actualizar.png'/>" width="15px" height="15px" title="Actualizar / Grabar" alt="Actualizar / Grabar"></a></td>
					<!-- <td class="certificadoLeft"><a href="<c:url value='/delete.do?id=${par.CODIGO}'/>"><img src="<c:url value='/resources/img/borrar.png'/>" width="15px" height="15px" title="Borrar" alt="Borrar"></a></td>-->
				
				</tr>
			</c:forEach>
		</tbody>

	</table>



</body>
</html>