<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<c:set var="menus" value="${SESSION_KEY_MENUS}" />
<c:set var="lista" value="${SESSION_PATH_NAVEGACION}" />
<c:set var="usuario" value="${currentUser}" />
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Cotizaci&oacute;n Previsional Electr&oacute;nica :: cp.cl</title>
	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>

</head>
<body>
<iframe id='ifrmPr' src='#' style="width:0px; height:0px; border: none; background:transparent"></iframe>
<div id="div-body">
<table width="770" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2" height="95" valign="top"><img src="<c:url value="/img/header.gif" />" width="770" height="95" /></td>
	</tr>
	<tr>
		<td colspan="2" height="20" valign="top" align="center" width="770">
			<img src="<c:url value="/img/sombra.jpg" />" width="739" height="7" />
		</td>
	</tr>
	<tr>
		<td width="180" align="left" valign="top">
			
		</td>
        <td align="left" valign="top" width="590">
        	<table border="0" cellpadding="0" cellspacing="0">
	        	<tr>
	        		<td valign="top">
	        			<div style="width: 590;">
				       		<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
					           	<tr>
					             	<td width="45%" height="20" align="left" class="imprimir" valign="top">
						             	<c:forEach var="path" items="${lista}">
						             		<c:choose>
						             			<c:when test="${path.actual}"><span class="botonera_ppalactivada"><tiles:useAttribute name="nombre" scope="session"/><c:out value="${nombre}"/></span></c:when>
						             			<c:otherwise><a href="<c:out value="${path.url}" />" class="links"><c:out value="${path.nombre}" /></a>&nbsp;&gt;&nbsp;</c:otherwise>
						             		</c:choose>
						             	</c:forEach>
									</td>
					             	<td width="55%" align="right" class="tit-usuario">
					             		<div style="font-size: 14px;font-weight: bolder;">
											<bean:write name="fechaHoy" format="dd/MM/yyyy"/>					             			
					             		</div>
					             	</td>
					           	</tr>
				         	</table>
				         	<br>
			         	</div>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>
	        			<tiles:insert attribute="main"/>
	        		</td>
	        	</tr>
        	</table>
        </td>
	</tr>
</table>
</div>
</body>
</html>