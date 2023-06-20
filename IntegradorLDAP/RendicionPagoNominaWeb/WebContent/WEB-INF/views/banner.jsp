<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<img src="<c:url value='/resources/img/cabecera_claves.jpg'/>" /> 
<div class="mensaje bg_gris" style="width: 917px">Usuario logeado:&nbsp; <strong>${usuario}</strong>&nbsp;&nbsp; | &nbsp; <a href="<c:url value='/'/>" title="Volver"><i class="fas fa-home"></i></a>&nbsp; | &nbsp;<a href="<c:url value='/logout.do'/>" title="Salir"><i class="far fa-times-circle"></i></a></div>
 
