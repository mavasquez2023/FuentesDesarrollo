<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/headerCRM.jsp" flush="true"></jsp:include>
<title>Perfilar Menu Certificados</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
</head>
<body>
	<form action="<%=request.getContextPath() %>/certificados/perfilarMenu.do" method="post" class="form" id="formPerfilar">
	
		<p class="titulo" >Perfilamiento Menú Certificados Crédito</p>
	
		<table style="width:400px" align="center">
		<tr>
		<th colspan="2">Activar Menú Dinámico</th>
		</tr>
		<tr>
				<td>Si<input type="radio" id="opcion" name="opcion" value="true" 
				<c:if test="${opcion=='true' }">checked="checked"</c:if>></td>
				<td>No<input type="radio" id="opcion" name="opcion" value="false"
				<c:if test="${opcion=='false' }">checked="checked"</c:if>></td>
				
		</tr>						
		</table>
	<br>
	<div>
	<input type="submit" value="Aceptar" class="boton">
	<br>
	<br>
	</div>
	<div>
	${resultado }
	</div>
	<br><br>
	<div class="menu_div" style="width: 300px;margin-left: 250px">
	<ul>
		<li><a href="${url_ldap }" target="_blank" >Gestión Usuarios</a></li>
	</ul>
	</div>

	</form>
	
			
	
</body>

</html>