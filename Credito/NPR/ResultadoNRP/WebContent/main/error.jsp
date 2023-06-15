<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../comun/header.jsp"%>
</head>
<body>
<p class="titulo">No ha sido posible completar su requerimiento</p>
<div class="ui-widget">
	<div style="margin-top: 20px; padding: 0 .7em;" class="ui-state-highlight ui-corner-all">
		<p><span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-info"></span>
			<strong>Ha ocurrido un problema en el sistema:</strong> 
			<bean:write name="msg"/>.
		</p>
	</div>
</div>
</body>
</html>